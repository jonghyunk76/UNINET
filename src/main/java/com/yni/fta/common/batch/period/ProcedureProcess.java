package com.yni.fta.common.batch.period;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;

import com.yni.fta.common.Consistent;
import com.yni.fta.common.batch.BatchProcess;
import com.yni.fta.common.batch.vo.BatchVo;
import com.yni.fta.common.batch.vo.JobVo;

import kr.yni.frame.batch.logger.BatchLogger;
import kr.yni.frame.exception.FrameException;
import kr.yni.frame.resources.MessageResource;
import kr.yni.frame.util.JcoMapHelper;
import kr.yni.frame.util.StringHelper;
import kr.yni.frame.util.SystemHelper;

/**
 * 타 시스템을 통한 데이터 인터페이스를 처리하는 클래스
 * 
 * @author jonghyun3.kim
 *
 */
public class ProcedureProcess extends BatchProcess {
	
    public ProcedureProcess() { }

    public ProcedureProcess(JobVo job, BatchVo batch) {
        super(job.getBatchLogger());

        this.setJobVo(job);
        this.setBatchVo(batch);
    }
    
    public ProcedureProcess(BatchVo batch, BatchLogger logger) {
        this.setBatchVo(batch);
        this.logger = logger;
    }
    
    @SuppressWarnings("rawtypes")
    protected boolean applyBatch() throws Exception {
        boolean result = true;
        Map resultMap = null;
        String resultValue = new String();

        try {
            result = startBatch();

            if (result) {
                Map paramMap = null;

                if (batchVo.getParameter() != null) {
                    paramMap = batchVo.getParameter().getMap();
                } else {
                    throw new FrameException("Inerface Error : not found input paramter.");
                }
                
                resultMap = batchTarget.executeProcedureProcess(paramMap);
                
                resultValue = StringHelper.null2void(resultMap.get("O_RETURN"));
                
                if("S".equals(resultValue)) {
                	String func = StringHelper.null2void(paramMap.get(Consistent.IF_PARAMETER_FUNCTION_NAME)); 
                	
                	if(func.toUpperCase().startsWith("SEND_")) {
                		// JCO ID에 해당하는 jco정보 획득
                        JcoMapHelper jcoMap = new JcoMapHelper(batchVo.getJcoId());
                        
                    	if(jcoMap != null) {
                    		// table 정보를 담은 리스트
                    		String mapName = jcoMap.getImportName();
                    		List<String> tableParam = jcoMap.getTableParameterName();
                            List rstList = null;
                            
                    		for (int i = 0; i < tableParam.size();) {
                                Map rpcMap = new HashMap();

                                String tableName = tableParam.get(i);
                                List list = jcoMap.getTableColumnName(i);

                                log.debug("column count = " + list.size() + ", contents=" + list.get(list.size() - 1));

                                rpcMap.put(Consistent.IF_JOB_COMPANY_CD, batchVo.getMap().get(Consistent.IF_JOB_COMPANY_CD));
                                rpcMap.put(Consistent.IF_BATCH_TRANS_ID, batchVo.getTransId());
                                rpcMap.put("SOURCE_TABLE", tableName);
                                rpcMap.put("COLUMN_LIST", list);

                                // RPC 테이블 데이터 조회
                                rstList = batchTarget.selectSourceTable(rpcMap);

                                if (log.isDebugEnabled()) {
                                    log.debug("RPC's Data size = " + rstList.size());
                                }
                                
                                // transaction 처음만 처리(JCO XML설정 시 테이블이 여러개라면 수정해서 적용할 것)
                                break;
                            }
                    		
                            // 조회건수 debug
                            if (rstList != null) {
                                String cnt = Integer.toString(rstList.size());

                                // 생성건수
                                batchVo.setTotalRows(cnt);
                                if(rstList.size() == 0) resultValue = "N";
                            } else {
                            	batchVo.setTotalRows("0");
                            	resultValue = "N";
                            }
                            
                            // ERP전송을 위한 데이터가 있다면 SAP 전송을 시도한다.
                            if(!batchVo.getTotalRows().equals("0")) {
	                            paramMap.put(mapName, rstList);
	                            
	                            batchVo.setInterfaceMethod("A"); // 송신을 위해 임시로 SAP PO방식으로 변경
	                            
	                            batchTarget.selectRemoteObject(batchVo);
	                            
	                            batchVo.setInterfaceMethod("P"); // 본래 프로시져 수행방식으로 변경
                            }
                    	}
                	}
                }
                
                if("S".equals(resultValue)) {
                	batchVo.setErrorMessage("");
                	batchVo.setTotalRows(StringHelper.null2void(resultMap.get("O_LINES")));
                    result = true;
                } else if("N".equals(resultValue)) {
                	Locale rLocale = SystemHelper.getLocale(batchVo.getLanguage());
                	MessageResource messageSource = MessageResource.getMessageInstance();
                	
                	batchVo.setBatchStatus("N");
                	batchVo.setErrorMessage(messageSource.getMessage("MSG_NOT_EXIST_DATA", null, rLocale));
                    result = true;
                } else {
                    batchVo.setErrorMessage(StringHelper.null2void(resultMap.get("O_RETURNMSG")));
                    result = false;
                }
            }
        } catch (Exception e) {
        	log.error(e);
            batchVo.setErrorMessage(e.getMessage());

            result = false;
        }
    	
        return result;
    }
    
}
