package com.yni.fta.common.batch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yni.fta.common.Consistent;
import com.yni.fta.common.batch.period.ProcessFactory;
import com.yni.fta.common.batch.vo.BatchVo;
import com.yni.fta.common.batch.vo.JobVo;
import com.yni.fta.common.batch.vo.ParameterVo;
import com.yni.fta.common.tools.SocketSupporter;
import com.yni.fta.mm.batch.BatchService;

import kr.yni.frame.batch.logger.impl.BatchLoggerImpl;
import kr.yni.frame.resources.MessageResource;
import kr.yni.frame.util.DateHelper;
import kr.yni.frame.util.JsonUtil;
import kr.yni.frame.util.StringHelper;

public class BatchServiceCaller {
	
	private static Log log = LogFactory.getLog(BatchServiceCaller.class);
	
	private JobVo jobVo;

    private BatchVo batchVo;
    
    public BatchServiceCaller() {
        jobVo = new JobVo();
        batchVo = new BatchVo();
    }

    public BatchServiceCaller(JobVo jvo, BatchVo bvo) {
        this.jobVo = jvo;
        this.batchVo = bvo;
    }
    
    public JobVo getJobVo() {
    	return this.jobVo;
    }
    
    public BatchVo getBatchVo() {
    	return this.batchVo;
    }
	
    /**
     * 사용자에 의한 수동배치 수행<br>
     * 
     * @param map 파라메터(COMPANY_CD, SERVICE_ID 필수값)
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public Map execute(Map map, BatchService batch) throws Exception {
    	Map resultMap = new HashMap();
    	BatchProcess process = null;
    	BatchFactory factory = new ProcessFactory();
    	MessageResource messageResource = MessageResource.getMessageInstance();
    	
    	try {
    		// 스케쥴 정보 설정
	        jobVo.setCreateBy(StringHelper.null2void(map.get("SESSION_USER_ID")));
	        jobVo.setUpdateBy(StringHelper.null2void(map.get("SESSION_USER_ID")));
	        jobVo.setScheduleCode(StringHelper.null2string(map.get(Consistent.IF_JOB_SCHEDULE_CD), "REALTIME_BATCH"));
	        jobVo.setCompanyCode((StringHelper.null2void(map.get(Consistent.IF_JOB_COMPANY_CD))));
	        jobVo.setBatchLogger(new BatchLoggerImpl());
	        jobVo.setStatus("1"); // 배치 실행중으로 업데이트 수행
	        jobVo.setPyyyymm(DateHelper.getSimpleDate("yyyy") + DateHelper.getSimpleDate("MM"));
	        
	        
	        String serviceHistoryId = StringHelper.null2void(map.get("SERVICE_HISTORY_ID"));
	        
	        map.put("SCHEDULE_CD", "REALTIME_BATCH");
	        if(serviceHistoryId.isEmpty()) {
	    		serviceHistoryId = batch.selectServiceTranID(); // service 이력번호
	    		
	    		map.put("SERVICE_HISTORY_ID", serviceHistoryId);
	    		
	    		// service 이력 등록
	    		batch.insertServiceHistory(map);
	        } else {
	        	// service 이력번호와 수행시간, 상태 갱신
	    		batch.updateServiceResetHistory(map);
	        }
    		
	        // 현재일시 조회
	        String now = DateHelper.getCurrentDateTimeAsString();
	        
    		// 서비스 Plan 조회
            List plans = batch.selectServicePlanList(map);
            
            log.debug("Plan total count = " + plans.size());
            
            for(int p = 0; p < plans.size(); p++) {
            	Map<String, String> plan = (Map<String, String>) plans.get(p);
            	
            	log.debug("======================================================================================" + now);
            	
            	plan.put("TRNAS_READTIME", now); // 현재일시를 저장시켜 서비스 실행단위의 그룹를 정의하는데 사용함
            	
                String ifCd = plan.get(Consistent.IF_BATCH_INTERFACE_CD);
                String ip = StringHelper.null2void(plan.get("SERVER_IP"));
            	int port = StringHelper.null2zero(plan.get("SERVER_PORT"));
                String useYn = StringHelper.null2string(plan.get("SERVER_USE_YN"), "Y");
                String serverState = "2";
                		
                // 서버 연결상태를 확인한다.
            	if(useYn.equals("N")) {
            		serverState = "5";
            	} else {
            		SocketSupporter socket = new SocketSupporter();
            		boolean cnnyn = socket.availablePort(ip, port);
            		
            		if(!cnnyn) {
            			serverState = "6";
            		}
            	}
                
                batchVo = new BatchVo();
                
                batchVo.setPutAll(plan);
                batchVo.setJcoId(ifCd);
                batchVo.setInterfaceCode(ifCd);
                batchVo.setLanguage(StringHelper.null2string(map.get("DEFAULT_LANGUAGE"), StringHelper.null2string(map.get("SESSION_DEFAULT_LANGUAGE"), Consistent.IF_BATCH_LANGUAGE)));
                batchVo.setItemType("S"); // 인터페이스 방식(S:서비스, T:테이블, P:프로시져)
                batchVo.setParentTransId(serviceHistoryId);
                
                // 실시간배치로 요청한 파라메터 정보가 있으면 직접 파라메터 정보를 담는다.
                String strParam = StringHelper.null2void(map.get("REQUEST_PARAM"));
                
                Map params = new HashMap();
                ParameterVo paramVo = new ParameterVo();
                
                if(!strParam.isEmpty()) {
                	String dataFormat = StringHelper.null2void(plan.get("REQ_DATA_FORMAT"));
                	
                	log.debug("data format = " + dataFormat + ", parameter = " + strParam);
                	
                	// 데이터 포맷에 맞게 파라메터 정보를 생성한다. 추후, XML/SOAP 등은 추가 예정임
                	if(dataFormat.equals("JSON")) params = JsonUtil.getMap(strParam);
	                
	                paramVo.setPutAll(params);
                }
                
                batchVo.setParameter(paramVo);
                
                // 원본 파라메터 정보를 서비스 이력에 업데이트 한다.(RSBATCH.updateServiceHistoryStatus)
                Map planMap = new HashMap();
              	
                planMap.put("COMPANY_CD", plan.get("COMPANY_CD"));
                planMap.put("SERVICE_HISTORY_ID", plan.get("SERVICE_HISTORY_ID"));
                planMap.put("INTERFACE_HISTORY_ID", plan.get("INTERFACE_HISTORY_ID"));
                planMap.put("REQUEST_PARAM", JsonUtil.getViewJson(paramVo.getMap()));
                planMap.put("TRANS_STATUS", serverState); // 서비스 상태(1:처리전, 2:처리중, 3:완료, 4:에러, 5:서버중지, 6:서버장애,9:전송취소)
	    		
                batch.updateServiceHistoryStatus(planMap);
	    		
                log.debug("batch type = " + batchVo.getItemType() + ", parameter = " + paramVo.getMap());
                
                if(serverState.equals("2")) { // 처리중 상태에서만 배치를 수행함
	                // 배치 수행요청
	                process = factory.cerate(jobVo, batchVo);
	                boolean result = process.applyBatch();
	                
	                log.debug("Result = " + result + ", batch = " + batchVo.getReturnData());
	                
	    	        // 직접 데이터를 리턴하는 경우에는 해당 값을 최종으로 리턴시킴
	                if(batchVo.getReturnData() != null) {
	                	resultMap.put(Consistent.IF_BATCH_RETURN_DATA, batchVo.getReturnData());
	                }
	                
	                if (result) {
	                	serverState = "3"; // 서비스 성공
	                	
	                    batchVo.setTransStatus("1");
	                    batchVo.setBatchStatus("S");
	                    
	                    if(batchVo.getErrorMessage().isEmpty()) {
	                    	batchVo.setErrorMessage(messageResource.getMessage("DTA, INF, CMPTE", null, batchVo.getLanguage()));
	                    }
	                } else {
	                	serverState = "4"; // 서비스 실패
	                	
	                    batchVo.setTransStatus("2");
	                    batchVo.setBatchStatus("E");
	                    batchVo.setErrorMessage(StringHelper.null2string(batchVo.getErrorMessage(), messageResource.getMessage("MSG_UNSPECIFIED_ERROR", null, batchVo.getLanguage())));
	                }
	                
	                // 배치가 완료된 최종 시간 및 상태를 업데이트한다.
	                batch.updateFinishedTime(batchVo.getMap());
	             	// 서비스 최종 실행상태를 업데이트한다.
	                planMap.put("TRANS_STATUS", serverState); // 서비스 상태(1:처리전, 2:처리중, 3:완료, 4:에러, 5:서버중지, 6:서버장애,9:전송취소)
	                planMap.put("REQUEST_PARAM", "");
		    		
	                batch.updateServiceHistoryStatus(planMap);
                }
            }
    	} catch(Exception e) {
    		if(log.isErrorEnabled()) log.error(batchVo.getErrorMessage());
    	} finally {
    		try {
    			// 스케쥴 상태를 대기중으로 변경, 최종 완료된 시간을 기록하고 사용자 배치 시간을 초기화한다.
                jobVo.setStatus("0");
                batch.updateInterfaceScheduleDate(jobVo.getMap());
    		} catch(Exception e) {
    			if(log.isErrorEnabled()) log.error(batchVo.getErrorMessage());
    		}
    	}
    	
    	resultMap.put("O_RETURN", batchVo.getBatchStatus());
    	
    	// 에러메시지 변환
    	// ORA-00001 : 중복데이터 오류
    	if(batchVo.getErrorMessage().indexOf("ORA-00001") > -1) {
    		batchVo.setErrorMessage(messageResource.getMessage("MSG_IS_DUPLICATED, [,KEY, DTA, ERROR,]", null, batchVo.getLanguage()));
    	}
    	
		resultMap.put("O_RETURNMSG", batchVo.getErrorMessage());
    	
    	return resultMap;
    }
    
}
