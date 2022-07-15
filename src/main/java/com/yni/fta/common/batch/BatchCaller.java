package com.yni.fta.common.batch;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yni.fta.common.Consistent;
import com.yni.fta.common.batch.period.ProcessFactory;
import com.yni.fta.common.batch.vo.BatchVo;
import com.yni.fta.common.batch.vo.JobVo;
import com.yni.fta.common.batch.vo.ParameterVo;
import com.yni.fta.mm.batch.BatchService;

import kr.yni.frame.batch.logger.impl.BatchLoggerImpl;
import kr.yni.frame.resources.MessageResource;
import kr.yni.frame.util.DateHelper;
import kr.yni.frame.util.StringHelper;

public class BatchCaller {
	
	private static Log log = LogFactory.getLog(BatchCaller.class);

    private JobVo jobVo;

    private BatchVo batchVo;
    
    public BatchCaller() {
        jobVo = new JobVo();
        batchVo = new BatchVo();
    }

    public BatchCaller(JobVo jvo, BatchVo bvo) {
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
     * @param map 파라메터
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
    		String ifCd = StringHelper.null2void(map.get(Consistent.IF_BATCH_INTERFACE_CD));
    		String fncName = StringHelper.null2void(map.get(Consistent.IF_PARAMETER_FUNCTION_NAME));
    		
	        // 스케쥴 정보 설정
	        jobVo.setCreateBy(StringHelper.null2void(map.get("SESSION_USER_ID")));
	        jobVo.setUpdateBy(StringHelper.null2void(map.get("SESSION_USER_ID")));
	        jobVo.setScheduleCode(StringHelper.null2string(map.get(Consistent.IF_JOB_SCHEDULE_CD), "MANUALLY_BATCH"));
	        jobVo.setCompanyCode((StringHelper.null2void(map.get(Consistent.IF_JOB_COMPANY_CD))));
	        jobVo.setBatchLogger(new BatchLoggerImpl());
	        jobVo.setStatus("1"); // 배치 실행중으로 업데이트 수행
	        jobVo.setPyyyymm(DateHelper.getSimpleDate("yyyy") + DateHelper.getSimpleDate("MM"));
	
	        // 배치 정보 설정
	        batchVo.setInterfaceCode(ifCd);
	        batchVo.setParentTransId(StringHelper.null2void(map.get(Consistent.IF_PARENT_HISTORY_ID)));
	        batchVo.setInterfaceMethod(StringHelper.null2string(map.get(Consistent.IF_BATCH_INTERFACE_METHOD), "M")); //인터페이스 방식(S:SAP(RFC), O:Oracle, E:Excel, M:MS-SQL, H:XML(FTA HUB), C:클라우드, U:직접입력, A:SAP(PO), F:XML(FTA PASS), P:Python 연계)
	        batchVo.setItemType(StringHelper.null2string(map.get(Consistent.IF_BATCH_ITEM_TYPE), "T")); // 인터페이스 형태(T:table, P:procedure, C:HTTP)
	        batchVo.setJcoId(ifCd);
	        batchVo.setLanguage(StringHelper.null2string(map.get("DEFAULT_LANGUAGE"), StringHelper.null2string(map.get("SESSION_DEFAULT_LANGUAGE"), Consistent.IF_BATCH_LANGUAGE)));
	        batchVo.setValidCheckYn(StringHelper.null2string(map.get(Consistent.IF_BATCH_VALID_CHECK_YN), "Y"));
	        batchVo.setFilePath(StringHelper.null2void(map.get(Consistent.IF_BATCH_FILE_PATH))); // 실행 프로그램 및 URL
	        
	        // 파라메터 정보 설정
	        ParameterVo paramVo = new ParameterVo();
	        
            paramVo.setCompanyCode(jobVo.getCompanyCode());
	        paramVo.setFromDate(StringHelper.null2string(map.get(Consistent.IF_PARAMETER_FROM_DATE), DateHelper.getCurrentDateAsString()));
	        paramVo.setToDate(StringHelper.null2string(map.get(Consistent.IF_PARAMETER_TO_DATE), DateHelper.getCurrentDateAsString()));
	        paramVo.setBatchFlag("U"); // 배치구분(D:일배치, M:월배치, W:주배치, U:User Manually)
	        paramVo.setRequestType(StringHelper.null2void(map.get(Consistent.IF_PARAMETER_REQUEST_TYPE))); // 작업을 요청 타입(AS:매출조정, DS:일배치 매출, MS:월배치 매출 등)-다양한 구분으로 활용가능
	        
	        if(batchVo.getInterfaceMethod().equals("A")) { // SAP PO Http 통신 연계
		        paramVo.setDivisionCode(StringHelper.null2void(map.get(Consistent.IF_PARAMETER_DIVISION_CD)));
		        paramVo.setItemCode(StringHelper.null2void(map.get(Consistent.IF_PARAMETER_ITEM_CD)));
	        }
	        
	        // 현차 FTA HUB인 경우에는 파라메터 정보로 XML파일 경로를 추가로 등록함
	        if(batchVo.getInterfaceMethod().equals("H")) {
		        Map addParams = new HashMap();
		        
		        addParams.put("XML_FILE_PATH", StringHelper.null2void(map.get("XML_FILE_PATH")));
		        addParams.put("XML_FILE_NAME", StringHelper.null2void(map.get("XML_FILE_NAME")));
		        
		        paramVo.setPutAll(addParams);
	        }
	        
	        // TOMS Cloud HUB
	        if(batchVo.getInterfaceMethod().equals("C")) { // TOMS Cloud HUB 연계인 경우
	        	paramVo.setBusinessNo(StringHelper.null2void(map.get(Consistent.IF_PARAMETER_BUSINESS_NO)));
	        	paramVo.setClVendorCode(StringHelper.null2void(map.get(Consistent.IF_PARAMETER_VENDOR_CD)));
	        	paramVo.setUrl(StringHelper.null2void(map.get(Consistent.IF_PARAMETER_URL)));
	        	paramVo.setTomsFtaCertKey(StringHelper.null2void(map.get(Consistent.IF_PARAMETER_TOMS_FTA_CERT_KEY)));
	        }
	        
	        // FTA PASS
	        if(batchVo.getInterfaceMethod().equals("F")) {
		        Map addParams = new HashMap();
		        
		        addParams.put("XML_FILE_PATH", StringHelper.null2void(map.get("XML_FILE_PATH")));
		        addParams.put("XML_FILE_NAME", StringHelper.null2void(map.get("XML_FILE_NAME")));
		        
		        paramVo.setPutAll(addParams);
	        }
	        
	        // Tariff 크롤링
	        if(batchVo.getInterfaceMethod().equals("P")) { // Python 연계인 경우
	        	paramVo.setUrl(StringHelper.null2void(map.get(Consistent.IF_BATCH_FILE_PATH)));
	        	paramVo.setUrl(StringHelper.null2void(map.get(Consistent.IF_BATCH_FILE_PATH)));
	        	
	        	Map pmap = paramVo.getMap();
	        	
	        	pmap.put("NATION_CD", StringHelper.null2void(map.get("NATION_CD")));
	        	pmap.put("HS_CODE", StringHelper.null2void(map.get("HS_CODE")));
	        }
	        
	        // 엑셀업로드인 경우 업로드한 사용자에 대한 데이터 처리를 위해 사용자ID를 추가로 등록함
	        if(batchVo.getInterfaceMethod().equals("E")) { // 엑셀인 경우
	        	paramVo.setUserId(StringHelper.null2void(map.get("SESSION_USER_ID")));
	        }
	        
	        if(batchVo.getItemType().equals("P")) {
	        	batchVo.setJobInfo(jobVo.getMap());
	        	batchVo.setTotalRows("0");
	        	batchVo.setTransStatus("1");
	        	
	        	// 수행할 function명을 지정한다.
                paramVo.setFunctionName(fncName);
                paramVo.setIfCode(StringHelper.null2void(map.get("PARAM_IF_CD")));
	        } else {
	        	paramVo.setIfCode(ifCd);
	        }
	        
	        batchVo.setParameter(paramVo);
	        
	        process = factory.cerate(jobVo, batchVo);
            boolean result = process.applyBatch();
	        
            log.debug("Result = " + result + ", batch = " + batchVo.getReturnData());
            
	        // 최종 결과 업데이트
	        if(result) {
	        	batchVo.setTransStatus("1");
	            batchVo.setBatchStatus("S");
	            
	            if(batchVo.getErrorMessage().isEmpty()) {
		            if(StringHelper.null2void(batchVo.getItemType()).equals("P")) {
	                	batchVo.setErrorMessage(messageResource.getMessage("DTA, TANOC,CMPTE", null, batchVo.getLanguage()));
	                } else {
	                	if(batchVo.getInterfaceMethod().equals("H")) { // 현대차 허브 연계인 경우
	                		batchVo.setErrorMessage(messageResource.getMessage("DTA, ANYIS, CMPTE", null, batchVo.getLanguage()));
	                	} else {
	                		batchVo.setErrorMessage(messageResource.getMessage("DTA, INF, CMPTE", null, batchVo.getLanguage()));
	                	}
	                }
	            }
	        } else {
	        	batchVo.setTransStatus("2");
	            batchVo.setBatchStatus("E");
	            batchVo.setErrorMessage(StringHelper.null2string(batchVo.getErrorMessage(), messageResource.getMessage("MSG_UNSPECIFIED_ERROR", null, batchVo.getLanguage())));
	        }
	        
	        // 직접 데이터를 리턴하는 경우에는 해당 값을 최종으로 리턴시킴
            if(batchVo.getReturnData() != null) {
            	resultMap.put(Consistent.IF_BATCH_RETURN_DATA, batchVo.getReturnData());
            }
    	} catch(Exception e) {
    		batchVo.setTransStatus("2");
            batchVo.setBatchStatus("E");
            batchVo.setErrorMessage(e.getMessage());
    	} finally {
    		try {
    			// 마지막 실행 시간 업데이트
    	        batch.updateFinishedTime(batchVo.getMap());
    	        
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
