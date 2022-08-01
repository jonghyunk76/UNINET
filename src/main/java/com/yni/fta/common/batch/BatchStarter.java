package com.yni.fta.common.batch;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.yni.fta.common.Consistent;
import com.yni.fta.common.batch.period.ProcessFactory;
import com.yni.fta.common.batch.vo.BatchVo;
import com.yni.fta.common.batch.vo.JobVo;
import com.yni.fta.common.batch.vo.ParameterVo;
import com.yni.fta.common.beans.target.InterfaceTarget;
import com.yni.fta.common.tools.CommonUtil;
import com.yni.fta.common.tools.FileSupporter;
import com.yni.fta.common.tools.HubSupporter;
import com.yni.fta.common.tools.MailSupporter;
import com.yni.fta.common.tools.QuartzSupporter;
import com.yni.fta.common.tools.SocketSupporter;
import com.yni.fta.common.ws.WebServiceClient;

import kr.yni.frame.Constants;
import kr.yni.frame.batch.logger.BatchLogger;
import kr.yni.frame.batch.logger.impl.BatchLoggerImpl;
import kr.yni.frame.config.Configurator;
import kr.yni.frame.config.ConfiguratorFactory;
import kr.yni.frame.context.ApplicationContextFactory;
import kr.yni.frame.dao.oracle.OracleTypeHelper;
import kr.yni.frame.mail.MailAttachment;
import kr.yni.frame.mail.MailSender;
import kr.yni.frame.pool.BatchPoolManager;
import kr.yni.frame.resources.MessageResource;
import kr.yni.frame.util.DateHelper;
import kr.yni.frame.util.FileUtil;
import kr.yni.frame.util.JsonUtil;
import kr.yni.frame.util.StringHelper;
import kr.yni.frame.web.upload.FormFile;

/**
 * <p>
 * 배치 수행 클래스
 * </p>
 * 
 * @author jonghyun3.kim
 *
 */
public class BatchStarter implements Job {

    private static Log log = LogFactory.getLog(BatchStarter.class);

    private static String memberID;

    private JobVo jobVo;

    private InterfaceTarget batchTarget;

    private long stime = 0;

    /**
     * class initializer
     */
    static {
        configure();
    }

    /**
     * properties 파일에 설정된 값을 구한다.
     */
    private static void configure() {
        try {
            Configurator configurator = ConfiguratorFactory.getInstance().getConfigurator();

            memberID = StringHelper.null2void(configurator.getString("batch.user.id"));

            if (log.isDebugEnabled()) {
                log.debug("user id=" + memberID);
            }
        } catch (Exception ex) {
            if (log.isErrorEnabled()) {
                log.error("exception: \n" + ex);
            }
        }
    }

    public BatchStarter() {
        stime = System.currentTimeMillis(); // 수행 시간 계산용

        if (jobVo == null) {
            jobVo = new JobVo();
        }

        jobVo.setCreateBy(memberID);
        jobVo.setUpdateBy(memberID);
    }

    public void execute(JobExecutionContext context) throws JobExecutionException {
        stime = System.currentTimeMillis(); // 수행 시간 계산용

        BatchLogger logger = new BatchLoggerImpl();
        BatchVo batchVo = null;
        boolean batchStatus = true;
        
        String scheduleCode = StringHelper.null2void(context.getJobDetail().getName());
        String companyCode = StringHelper.null2void(context.getJobDetail().getGroup());
        
        jobVo.setScheduleCode(scheduleCode);
        jobVo.setCompanyCode(companyCode);
        jobVo.setBatchLogger(logger);

        if (log.isDebugEnabled()) {
            log.debug("Job Information : " + jobVo.toString());
        }

        ApplicationContext ctx = ApplicationContextFactory.getAppContext();
        batchTarget = (InterfaceTarget) ctx.getBean("batchTargetService");

        try {
            // 배치 수행상태를 실행중(1)으로 업데이트 수행
            jobVo.setStatus("1");
            batchTarget.updateInterfaceScheduleDate(jobVo.getMap());
            
            // 스케쥴 명칭에 따라 수행할 매소드를 호출한다.
            // 연계서버(RS)인 경우에는 서비스 계획에 의한 배치를 실행, 이외에는 인터페이스 항목별 개별 처리되는 배치를 실행함
            if("RS".equals(Constants.APPLICATION_SYSTEM_ID)) {
            	batchVo = this.executeInterfaceBatchForService();
            } else {
	            if(scheduleCode.equals("DAILY_BATCH")) { // 일마감 배치
	            	batchVo = this.executeInterfaceBatchForERP();
	            } else if(scheduleCode.equals("MONTHLY_BATCH")) { // 월마감 배치
	            	batchVo = this.executeInterfaceBatchForERP();
	            } else if(scheduleCode.equals("FTA_HUB_BATCH")) { // HKMC Vaatz
	            	batchVo = this.executeInterfaceBatchForHUB();
	            } else if(scheduleCode.equals("TOMS_HUB_BATCH")) { // TOMS HUB
	            	batchVo = this.executeInterfaceBatchForCLOUD();
	            } else if(scheduleCode.equals("AUTO_MAIL_BATCH")) { // 협력사 확인서 요청 메일 자동발송
	            	batchVo = this.executeMailSendForTOMS();
	            } else if(scheduleCode.equals("FTA_PASS_BATCH")) { // FTA PASS
	            	batchVo = this.executeInterfaceBatchForPass();
	            } else {
	            	logger.logMessage("[ERROR] " + scheduleCode + " is not found code.");
	            }
            }
            
            if(batchVo.getBatchStatus().equals("E")) {
            	batchStatus = false;
            }
        } catch (Exception exp) {
            try {
                logger.logMessage("[ERROR] " + exp.getMessage());

                batchVo.setErrorMessage(exp.getCause().getMessage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            exp.printStackTrace();
        } finally {
            try {
            	if(batchVo != null) {
	                if (!batchStatus) {
	                    // 배치가 완료된 최종 시간 및 상태를 업데이트한다.
	                    batchTarget.updateFinishedTime(batchVo.getMap());
	                }
            	} else {
	               	logger.logMessage("[ERROR] Batch VO is null.");
	            }
	            
                // 스케쥴 상태를 대기중으로 변경, 최종 완료된 시간을 기록하고 사용자 배치 시간을 초기화한다.
                jobVo.setStatus("0");
                batchTarget.updateInterfaceScheduleDate(jobVo.getMap());
                
                // 사내에서 지정한 표준주기설정이 있다면 지정된 시간으로 스케쥴을 조정한다.(2019.10.26)
            	List schList = batchTarget.selectInterfaceScheduleList(jobVo.getMap());
            	
                if(schList.size() > 0) {
            		QuartzSupporter.setStandardFireTime(schList, jobVo);
            	}
            	
                long ftime = System.currentTimeMillis();

                if (log.isInfoEnabled()) {
                    log.info("finished batch(execute time = " + (ftime - stime) + " msec.)");
                }
                logger.logMessage("finished batch(execute time = " + (ftime - stime) + " msec.)");
                logger.logEnd(batchStatus);
            } catch (Exception exp) {
                try {
                    logger.logMessage("[ERROR] " + exp.getMessage());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                exp.printStackTrace();
            }
        }
    }
    
    /**
     * FTA 월/일마감 배치 수행
     * 
     * @return
     * @throws Exception
     */
    private BatchVo executeInterfaceBatchForERP() throws Exception{
    	BatchVo batchVo = null;
    	BatchFactory factory = new ProcessFactory();
    	BatchProcess process = null;
    	BatchLogger logger = jobVo.getBatchLogger();
    	MessageResource messageResource = MessageResource.getMessageInstance();
    	
    	String lang = CommonUtil.getContextLanguage(jobVo.getCompanyCode());
    	String divisionCode = null;
        String fromDate = null;
        String toDate = null;
        String itemCode = null;
        String batchFlag = null;
        
        boolean result = true;
        
    	// 사용자 지정 정산월을 조회
        List<Object> yyyymmList = batchTarget.selectBatchYYYYMM(jobVo.getMap());
        
        if(yyyymmList != null && yyyymmList.size() == 0) {
        	batchVo = new BatchVo();
        	
        	batchVo.setTransStatus("2");
            batchVo.setBatchStatus("E");
            batchVo.setErrorMessage(messageResource.getMessage("MSG_NOT_FOUND_SCHEDULE_INFO", null, lang));
            
        	return batchVo;
        }
        
        // 배치를 수행할 마감월 리스트
        List<String> dateArray = new LinkedList<String>();
        
        Map dataBatchMap = (Map) yyyymmList.get(0);
        
        String startDate = StringHelper.null2void(dataBatchMap.get("YYYYMM"));
        String endDate = StringHelper.null2string(dataBatchMap.get("TO_YYYYMM"), startDate);
        String batchType = StringHelper.null2void(jobVo.getScheduleCode()).toUpperCase();
        int prevNum = StringHelper.null2zero(dataBatchMap.get("BATCH_PREV_NUM"), -1);
        
        // 배치처리할 월을 구한다.
        if(!startDate.isEmpty()) {
	        if(batchType.equals("DAILY_BATCH")) {
	        	fromDate = startDate;
	        	dateArray.add(endDate);
	        } else {
	        	int strtYear = Integer.parseInt(startDate.substring(0, 4));
		        int strtMonth = Integer.parseInt(startDate.substring(4, 6));
		        int endYear = Integer.parseInt(endDate.substring(0, 4));
		        int endMonth = Integer.parseInt(endDate.substring(4, 6));
		        int month_diff = (endYear - strtYear) * 12 + (endMonth - strtMonth); // strtDate - endDate (개월수 차이)
		        
		        for(int i = 0; i <= month_diff; i++) {
		            String targetYear = DateHelper.getCalcDateAsString(Integer.toString(strtYear), Integer.toString(strtMonth), "01", i, "month", "year");
		            String targetMonth = DateHelper.getCalcDateAsString(Integer.toString(strtYear), Integer.toString(strtMonth), "01", i, "month", "month");
		            String lastDay = DateHelper.getLastDay(targetYear, targetMonth);
		            
		            dateArray.add(targetYear + targetMonth + lastDay);
		        }
	        }
        } else {
        	String toYear = DateHelper.getSimpleDate("yyyy");
            String toMonth = DateHelper.getSimpleDate("MM");
            String toDay = DateHelper.getSimpleDate("dd");
            
            if(batchType.equals("DAILY_BATCH")) {
            	fromDate = DateHelper.getCalcDateAsString(toYear, toMonth, toDay, prevNum, "day");
            	dateArray.add(fromDate);
            } else {
            	String targetYear = DateHelper.getCalcDateAsString(toYear, toMonth, toDay, prevNum, "month", "year");
                String targetMonth = DateHelper.getCalcDateAsString(toYear, toMonth, toDay, prevNum, "month", "month");
                String lastDay = DateHelper.getLastDay(toYear, targetMonth);
                
                dateArray.add(targetYear + targetMonth + lastDay);
            }
        }
        
        for(int s = 0; s < dateArray.size(); s++) {
        	toDate = dateArray.get(s);
        	
	        // 스케쥴 주기별로 기준일자를 구한다.
	        if(batchType.equals("DAILY_BATCH")) {
	            batchFlag = "D";
	        } else if(batchType.equals("MONTHLY_BATCH")) {
	            jobVo.setPyyyymm(toDate.substring(0, 6));
	            fromDate = toDate.substring(0, 6) + "01";
	
	            batchFlag = "M";
	        }
	        
	        if(log.isDebugEnabled()) log.debug((s+1) + ". Batch type = " + batchType + ", From date = " + fromDate + ", To date = " + toDate);
	        
	        // 타 시스템간의 인터페이스 수행
	        // 인터페이스 항목 중 엑셀방식인 경우 화면에서 직접 처리하는 것으로 변경하여, 본 작업에서는 제외시킴(2016.12.27, YNI-Maker)
	        List mappingList = batchTarget.selectInterfaceMappingList(jobVo.getMap());
	
	        if (mappingList == null || mappingList.size() < 1) {
	        	if(jobVo.getMap() != null) logger.logMessage("job VO info. = "+jobVo.getMap().toString());
	        	else logger.logMessage("job VO is null.");
	        		
	            logger.logMessage("Can not run the interface. interface item is empty");
	        } else {
	            if (log.isDebugEnabled()) log.debug("interface start for batch....");
	            
	            String parentHistoryId = ""; 
	            
	            // 해당 스케쥴에서 지정한 인터페이스 항목에 대해 배치를 수행한다.
	            for (int i = 0; i < mappingList.size(); i++) {
	                Map<String, String> mappingMap = (Map<String, String>) mappingList.get(i);
	                String ifCd = mappingMap.get(Consistent.IF_BATCH_INTERFACE_CD);
	                
	                batchVo = new BatchVo();
	
	                batchVo.setPutAll(mappingMap);
	                batchVo.setJcoId(ifCd);
	                batchVo.setLanguage(lang);
	                batchVo.setParentTransId(parentHistoryId);
	                
	                // 파라메터 설정
	                ParameterVo paramVo = new ParameterVo();
	
	                paramVo.setCompanyCode(jobVo.getCompanyCode());
	                paramVo.setFromDate(fromDate);
	                paramVo.setToDate(toDate);
	                paramVo.setDivisionCode(divisionCode);
	                paramVo.setItemCode(itemCode);
	                paramVo.setBatchFlag(batchFlag);
	                
	                if(StringHelper.null2void(batchVo.getItemType()).equals("P")) {
	                    batchVo.setTotalRows("-1");
	                    batchVo.setJobInfo(jobVo.getMap());
	                    // 인터페이스 데이터 추출 작업이 없기 때문에 성공으로 등록함.
	                    batchVo.setTransStatus("1");
	                    // 수행할 function명을 지정한다.
	                    paramVo.setFunctionName(ifCd);
	                } else {
	                	paramVo.setIfCode(ifCd);
	                }
	                
	                // 파라메터를 BatchVo에 저장
	                batchVo.setParameter(paramVo);
	                
	                process = factory.cerate(jobVo, batchVo);
	                result = process.applyBatch();
	
	                if (result) {
	                    batchVo.setTransStatus("1");
	                    batchVo.setBatchStatus("S");
	                    
	                    if(batchVo.getErrorMessage().isEmpty()) {
		                    if(StringHelper.null2void(batchVo.getItemType()).equals("P")) {
		                    	batchVo.setErrorMessage(messageResource.getMessage("DTA, TANOC, CMPTE", null, batchVo.getLanguage()));
		                    } else {
		                    	batchVo.setErrorMessage(messageResource.getMessage("DTA, INF, CMPTE", null, batchVo.getLanguage()));
		                    }
	                    }
	                } else {
	                    batchVo.setTransStatus("2");
	                    batchVo.setBatchStatus("E");
	                    batchVo.setErrorMessage(StringHelper.null2string(batchVo.getErrorMessage(), messageResource.getMessage("MSG_UNSPECIFIED_ERROR", null, batchVo.getLanguage())));
	                }
	                
	                // 배치가 완료된 최종 시간 및 상태를 업데이트한다.
	                batchTarget.updateFinishedTime(batchVo.getMap());
	                
	                if(i == 0) {
	                	parentHistoryId = batchVo.getTransId();
	                }
	                
	                if(!result) {
	                	break;
	                }
	            }
	        }
	        
	        if (log.isDebugEnabled()) log.debug("batch result = " + result);
	        
        } // end for
        
        return batchVo;
    }
    
    /**
     * HKMC 바츠 연계
     * 
     * @return
     * @throws Exception
     */
    private BatchVo executeInterfaceBatchForHUB() throws Exception {
    	BatchVo batchVo = null;
    	BatchFactory factory = new ProcessFactory();
    	BatchProcess process = null;
    	BatchLogger logger = jobVo.getBatchLogger();
    	
    	boolean result = true;
    	
    	MessageResource messageResource = MessageResource.getMessageInstance();
    	String lang = CommonUtil.getContextLanguage(jobVo.getCompanyCode());
    	
    	// 인터페이스 항목 중 엑셀방식을 제외한 배치할 항목을 조회한다.
        List mappingList = batchTarget.selectInterfaceMappingList(jobVo.getMap());
        
        if (mappingList == null || mappingList.size() < 1) {
            logger.logMessage("Can not run the interface. interface item is empty");
        } else {
        	if (log.isDebugEnabled()) log.debug("interface start for batch....");
        	
        	// 배치마감월
        	jobVo.setPyyyymm(DateHelper.getSimpleDate("yyyy") + DateHelper.getSimpleDate("MM"));
        	
        	List organList = batchTarget.selectHubOrganizatList(jobVo.getMap());
        	
        	if(organList != null) {
    			for(int k = 0; k < organList.size(); k++) {
    				Map organInfo = (Map) organList.get(k);
    				
    				String hubId = StringHelper.null2void(organInfo.get("HUB_CERT_ID"));
    				String filePath = HubSupporter.getConfigFilePath("R", null, hubId);
		        	File[] files = FileUtil.getDirFileList(filePath, ".xml");
		        	String fileName = null;
		        	
		        	if(files != null) {
				        for(int f = 0; f < files.length; f++) {
				    		File file = files[f];
				    		
				    		fileName = file.getName();
				    		HubSupporter supporter = new HubSupporter(filePath + File.separator + fileName);
				    		String fileCode = supporter.getInterfaceId();
				    		String parentHistoryId = ""; 
				    		
				    		if(fileCode != null) {
				    			boolean resultStatus = true;
				    			
					    		// 해당 스케쥴에서 지정한 인터페이스 항목에 대해 배치를 수행한다.
					            for (int i = 0; i < mappingList.size(); i++) {
					            	Map<String, String> mappingMap = (Map<String, String>) mappingList.get(i);
					            	batchVo = new BatchVo();
					            	
				            		if(supporter.isRunning(fileCode, mappingMap.get(Consistent.IF_BATCH_INTERFACE_CD), mappingMap.get(Consistent.IF_BATCH_ITEM_TYPE))) {
				
				                        batchVo.setPutAll(mappingMap);
				                        batchVo.setJcoId(mappingMap.get(Consistent.IF_BATCH_INTERFACE_CD));
				                        batchVo.setLanguage(lang);
				                        batchVo.setParentTransId(parentHistoryId);
				                        batchVo.setFilePath(mappingMap.get(Consistent.IF_BATCH_FILE_PATH)); // 실행 프로그램 및 URL
				                        
				                        // 파라메터 설정
				                        ParameterVo paramVo = new ParameterVo();
				                        
				                        paramVo.setCompanyCode(jobVo.getCompanyCode());
				                        paramVo.setFromDate(DateHelper.getCurrentDateAsString());
				            	        paramVo.setToDate(DateHelper.getCurrentDateAsString());
				                        paramVo.setBatchFlag("D"); // 배치구분(D:일배치, M:월배치, W:주배치, U:User Manually)
				                        
				                        if(StringHelper.null2void(batchVo.getItemType()).equals("P")) {
				                            batchVo.setTotalRows("-1");
				                            batchVo.setJobInfo(jobVo.getMap());
				                            // 인터페이스 데이터 추출 작업이 없기 때문에 성공으로 등록함.
				                            batchVo.setTransStatus("1");
				                            // 수행할 function명을 지정한다.
				                            paramVo.setFunctionName(mappingMap.get(Consistent.IF_BATCH_INTERFACE_CD));
				                            paramVo.setIfCode(fileCode);
				                        } else {
				                        	paramVo.setIfCode(mappingMap.get(Consistent.IF_BATCH_INTERFACE_CD));
				                        }
				                        
				                        // 현차 FTA HUB인 경우에는 파라메터 정보로 XML파일 경로를 추가로 등록할 것
				            	        if(batchVo.getInterfaceMethod().equals("H")) { // 현대차 허브 연계인 경우
				            		        Map addParams = new HashMap();
				            		        
				            		        addParams.put("XML_FILE_PATH", StringHelper.null2void(filePath + File.separator + fileName));
				            		        addParams.put("XML_FILE_NAME", StringHelper.null2void(fileName));
				            		        
				            		        paramVo.setPutAll(addParams);
				            	        }
				            	        
				            	        // 파라메터를 BatchVo에 저장
				                        batchVo.setParameter(paramVo);
				                        
				            	        process = factory.cerate(jobVo, batchVo);
				                        result = process.applyBatch();
				                        
				                        if (result) {
				                            batchVo.setTransStatus("1");
				                            batchVo.setBatchStatus("S");
				                            
				                            if(StringHelper.null2void(batchVo.getItemType()).equals("P")) {
				                            	batchVo.setErrorMessage(messageResource.getMessage("DTA, TANOC, CMPTE", null, batchVo.getLanguage()));
				                            } else {
				                            	batchVo.setErrorMessage(messageResource.getMessage("DTA, ANYIS, CMPTE", null, batchVo.getLanguage()));
				                            }
				                        } else {
				                            batchVo.setTransStatus("2");
				                            batchVo.setBatchStatus("E");
				                            batchVo.setErrorMessage(StringHelper.null2string(batchVo.getErrorMessage(), messageResource.getMessage("MSG_UNSPECIFIED_ERROR", null, batchVo.getLanguage())));
				                            
				                            resultStatus = false;
				                        }
				                        
				                        // 배치가 완료된 최종 시간 및 상태를 업데이트한다.
				                        batchTarget.updateFinishedTime(batchVo.getMap());
				                        
				                        if(fileCode.equals(mappingMap.get(Consistent.IF_BATCH_INTERFACE_CD))) {
				                        	parentHistoryId = batchVo.getTransId();
				                        }
				            		}
					            }
					            
					            // 완료된 파일은 완료 파일 디렉토리로 이동 시킴
					            String movePath = null;
					    		
					            if(resultStatus) {
					            	movePath = HubSupporter.getConfigFilePath("R", fileCode, hubId);
					            } else {
					            	movePath = filePath + File.separator + "temp";
					            }
					            
					            if(movePath != null) {
					    			FileUtil.fileMove(filePath, fileName, movePath);
					    		}
			            	}
			            }
		        	} // end if(files != null)
    			}
        	} // end if(organList != null)
        }
        
        if (log.isDebugEnabled()) log.debug("batch result = " + result);
        
        if(batchVo == null) {
        	batchVo = new BatchVo();
        }
        
        if (result) {
            batchVo.setTransStatus("1");
            batchVo.setBatchStatus("S");
        } else {
            batchVo.setTransStatus("2");
            batchVo.setBatchStatus("E");
        }
        
        // 전날에 처리되지 않은 파일을 검색하여 재전송하는 기능 추가(20190806).단, 1주일 이후에는 전송하지 않도록 한다.
        // 1.아직 결과통보를 받기 못한 제출서 조회
        List noComplateList = batchTarget.selectHubNoComplateList(jobVo.getMap());
        
    	// 2.발송대기 폴더에 파일을 Copy(복사 후 전송내역은 전송파일 경로 조회에서 확인이 가능함)
        for(int i = 0; i < noComplateList.size(); i++) {
        	Map noFile = (Map) noComplateList.get(i);
        	
        	String hubId = StringHelper.null2void(noFile.get("VENDOR_CD"));
        	String sendDate = StringHelper.null2void(noFile.get("SEND_DATE"));
        	String fileName = StringHelper.null2void(noFile.get("FILE_NAME"));
        	String backup = HubSupporter.getConfigFilePath("S", "BACKUP", true, hubId);
        	
        	String backPath = backup + File.separator + sendDate; // 송신완료 후 파일
        	String outPath = HubSupporter.getConfigFilePath("S", null, hubId); // 송신전 파일
        	
        	if(new File(backPath + File.separator + fileName).exists()) {
	        	FileSupporter.fileCopy(backPath, fileName, outPath, false);
	        	
	        	logger.logMessage("Not yet, Resend Request...[backup path = " + backPath + ", file name = " + fileName + ", move path = " + outPath);
        	} else {
        		logger.logMessage("Not yet, Not exists file = " + backPath + ", file name = " + fileName);
        	}
        }
        
        // 3. 대량의 확인서를 동시에 전송하는 경우, 미발송건(TEMP에 저장된 파일)이 발생하여 강제로 재전송할 수 있게 추가함(2021.09.29)
        Configurator configurator = ConfiguratorFactory.getInstance().getConfigurator();
        String sendPath = StringHelper.null2void(configurator.getString("xtrus.data.outbound"));
        String tempPath = sendPath+File.separator+"temp";
        
        File[] files = FileUtil.getDirFileList(tempPath, "xml");
        
        log.debug("temp path = " + tempPath + ", send path = " + sendPath);
        
        if(files != null) {
        	for(int i = 0; i < files.length; i++) {
        		boolean suc = FileUtil.fileMove(tempPath, files[i].getName(), sendPath);
        		
        		logger.logMessage("Not yet, Resend Request...[tmep path = " + tempPath + ", file name = " + files[i].getName() + ", move path = " + sendPath + ", result = " + suc);
        		
        		TimeUnit.SECONDS.sleep(3); // 3초간 지연
        	}
        }
        
        return batchVo;
    }
    
    /**
     * TOMS HUB 배치 수행
     * 
     * @return
     * @throws Exception
     */
    private BatchVo executeInterfaceBatchForCLOUD() throws Exception {
    	BatchVo batchVo = null;
    	BatchFactory factory = new ProcessFactory();
    	BatchProcess process = null;
    	BatchLogger logger = jobVo.getBatchLogger();
    	
    	MessageResource messageResource = MessageResource.getMessageInstance();
    	String lang = CommonUtil.getContextLanguage(jobVo.getCompanyCode());
    	
    	// 인터페이스 항목 중 엑셀방식을 제외한 배치할 항목을 조회한다.
        List mappingList = batchTarget.selectInterfaceMappingList(jobVo.getMap());
        
        if (mappingList == null || mappingList.size() < 1) {
            logger.logMessage("Can not run the interface. interface item is empty");
        } else {
        	if (log.isDebugEnabled()) log.debug("interface start for batch....");
            
        	// 현재 년/월/일 구하기
            String toYear = DateHelper.getSimpleDate("yyyy");
            String toMonth = DateHelper.getSimpleDate("MM");
            String toDay = DateHelper.getSimpleDate("dd");

            String targetYear = DateHelper.getCalcDateAsString(toYear, toMonth, toDay, -1, "month", "year");
            String targetMonth = DateHelper.getCalcDateAsString(toYear, toMonth, toDay, -1, "month", "month");
            String lastDay = DateHelper.getLastDay(toYear, targetMonth);

            jobVo.setPyyyymm(targetYear + targetMonth);

            String fromDate = targetYear + targetMonth + "01";
            String toDate = targetYear + targetMonth + lastDay;
            
            String parentHistoryId = ""; 
            
            // 1.TOMS HUB 가입 협력사를 가져온다.
            for (int i = 0; i < mappingList.size(); i++) {
                Map<String, String> mappingMap = (Map<String, String>) mappingList.get(i);
                
                // 클라우드 가입 협력사 리스트 가져오기
                if(mappingMap.get(Consistent.IF_BATCH_INTERFACE_CD).equals("TCH_IN_0001")) {
	                boolean result = true;
	                batchVo = new BatchVo();
	
	                batchVo.setPutAll(mappingMap);
	                batchVo.setJcoId(mappingMap.get(Consistent.IF_BATCH_INTERFACE_CD));
	                batchVo.setLanguage(lang);
	                batchVo.setParentTransId(parentHistoryId); // 상위의 연관된 아이디를 등록한다.
	                
	                // 파라메터 설정
	                ParameterVo paramVo = new ParameterVo();
	
	                paramVo.setCompanyCode(jobVo.getCompanyCode());
	                paramVo.setFromDate(fromDate);
	    	        paramVo.setToDate(toDate);
	                paramVo.setBatchFlag("D"); // 배치구분(D:일배치, M:월배치, W:주배치, U:User Manually)
	                paramVo.setIfCode(mappingMap.get(Consistent.IF_BATCH_INTERFACE_CD));
	                paramVo.setUrl(mappingMap.get(Consistent.IF_PARAMETER_URL) + mappingMap.get("FILE_PATH")); // TOMS Cloud 주소
	            	paramVo.setTomsFtaCertKey(mappingMap.get(Consistent.IF_PARAMETER_TOMS_FTA_CERT_KEY));
	            	paramVo.setBusinessNo(mappingMap.get(Consistent.IF_PARAMETER_BUSINESS_NO)); // 회사정보에 등록된 사업자 등록번호
	            	
                	// 파라메터를 BatchVo에 저장
	                batchVo.setParameter(paramVo);
	                
	    	        process = factory.cerate(jobVo, batchVo);
	                result = process.applyBatch();
	                
	                logger.logMessage("get TOMS HUB Member.... (result = " + result + ")");
	                
	                parentHistoryId = StringHelper.null2void(batchVo.getTransId());
	                
	                if (result) {
	                    batchVo.setTransStatus("1");
	                    batchVo.setBatchStatus("S");
	                    
	                    if(StringHelper.null2void(batchVo.getItemType()).equals("P")) {
	                    	batchVo.setErrorMessage(messageResource.getMessage("DTA, TANOC, CMPTE", null, batchVo.getLanguage()));
	                    } else {
	                    	batchVo.setErrorMessage(messageResource.getMessage("DTA, ANYIS, CMPTE", null, batchVo.getLanguage()));
	                    }
	                } else {
	                    batchVo.setTransStatus("2");
	                    batchVo.setBatchStatus("E");
	                    batchVo.setErrorMessage(StringHelper.null2string(batchVo.getErrorMessage(), messageResource.getMessage("MSG_UNSPECIFIED_ERROR", null, batchVo.getLanguage())));
	                }
	                
	                // 배치가 완료된 최종 시간 및 상태를 업데이트한다.
	                batchTarget.updateFinishedTime(batchVo.getMap());
	                
	                if(result) {
	                	batchVo.setParentTransId(parentHistoryId);	                	
	                	batchVo.setItemType("P");
	                	paramVo.setFunctionName("PKG_LINK_TOMS_HUB");
	                	
	                	batchVo.setParameter(paramVo);
	                	
	                	process = factory.cerate(jobVo, batchVo);
		                result = process.applyBatch();
		                
		                logger.logMessage("execute Batch procedure.... (" + result + ")");
		                
		                if (result) {
		                    batchVo.setTransStatus("1");
		                    batchVo.setBatchStatus("S");
		                    
		                    if(StringHelper.null2void(batchVo.getItemType()).equals("P")) {
		                    	batchVo.setErrorMessage(messageResource.getMessage("DTA, TANOC, CMPTE", null, batchVo.getLanguage()));
		                    } else {
		                    	batchVo.setErrorMessage(messageResource.getMessage("DTA, ANYIS, CMPTE", null, batchVo.getLanguage()));
		                    }
		                } else {
		                    batchVo.setTransStatus("2");
		                    batchVo.setBatchStatus("E");
		                    batchVo.setErrorMessage(StringHelper.null2string(batchVo.getErrorMessage(), messageResource.getMessage("MSG_UNSPECIFIED_ERROR", null, batchVo.getLanguage())));
		                }
		                
		                // 배치가 완료된 최종 시간 및 상태를 업데이트한다.
		                batchTarget.updateFinishedTime(batchVo.getMap());
	        		}
            	}
            }
            
            // 2.로컬에 등록된 클라우드 가입 협력사 조회
        	List vList = batchTarget.selectPoVendorForHubList(jobVo);
        	
        	// 3.TOMS HUB에 가입된 사업자 등록번호를 구한다.(동일 회사에 사업자등록번호가 다중으로 존재하는 경우를 처리하기 위함)
        	String biznum = null;
        	
        	for(int v = 0; v < vList.size(); v++) {
        		Map vMap = (Map) vList.get(v);
        		
        		if(StringHelper.null2void(vMap.get("JOIN_STATUS")).equals("O")) {
        			biznum = StringHelper.null2void(vMap.get("CL_BUSINESS_NO"));
        			break;
        		}
        	}
        	
        	logger.logMessage("TOMS HUB Business no = " + biznum);
        	
        	// 4.TOMS HUB 협력사의 원산지 확인서를 가져온다.
        	for(int v = 0; v < vList.size(); v++) {
        		Map vMap = (Map) vList.get(v);
        		
        		String join = StringHelper.null2void(vMap.get("JOIN_STATUS"));
        		
        		if(!join.equals("Y")) {
        			continue;
        		}
        		
	            for(int i = 0; i < mappingList.size(); i++) {
	                Map<String, String> mappingMap = (Map<String, String>) mappingList.get(i);
	                
	                String interfaceCd = StringHelper.null2void(mappingMap.get(Consistent.IF_BATCH_INTERFACE_CD));
	                
	                if(interfaceCd.equals("TCH_IN_0002") || interfaceCd.equals("TCH_IN_0003") || interfaceCd.equals("TCH_IN_0008")) {
		                boolean result = true;
		                batchVo = new BatchVo();
		                
		                batchVo.setPutAll(mappingMap);
		                batchVo.setJcoId(interfaceCd);
		                batchVo.setLanguage(lang);
		                batchVo.setParentTransId(parentHistoryId); // 상위의 연관된 아이디를 등록한다.
		                
		                // 파라메터 설정
		                ParameterVo paramVo = new ParameterVo();
		
		                paramVo.setCompanyCode(jobVo.getCompanyCode());
		                paramVo.setFromDate(fromDate);
		    	        paramVo.setToDate(toDate);
		                paramVo.setBatchFlag("D"); // 배치구분(D:일배치, M:월배치, W:주배치, U:User Manually)
		                paramVo.setIfCode(mappingMap.get(Consistent.IF_BATCH_INTERFACE_CD));
                		paramVo.setBusinessNo(StringHelper.null2string(biznum,  mappingMap.get(Consistent.IF_PARAMETER_BUSINESS_NO))); // TOMS HUB에 가입된 사업자등록번호
                		paramVo.setClVendorCode(StringHelper.null2void(vMap.get("CL_VENDOR_CD"))); // TOMS HUB 가입협력사 코드
                		paramVo.setUrl(vMap.get("SERVICE_URL") + mappingMap.get("FILE_PATH")); // 협력사 사이트 주소
                        paramVo.setTomsFtaCertKey(mappingMap.get(Consistent.IF_PARAMETER_TOMS_FTA_CERT_KEY)); // TOMS 라이센스
                		
                		// 파라메터를 BatchVo에 저장
    	                batchVo.setParameter(paramVo);
    	                
    	    	        process = factory.cerate(jobVo, batchVo);
    	    	        result = process.applyBatch();
    	    	        
    	    	        logger.logMessage("get TOMS D/O.... (result = " + result + ", Vendor = " + paramVo.getClVendorCode() + ")");
    	    	        
    	    	        if (result) {
		                    batchVo.setTransStatus("1");
		                    batchVo.setBatchStatus("S");
		                    
		                    if(StringHelper.null2void(batchVo.getItemType()).equals("P")) {
		                    	batchVo.setErrorMessage(messageResource.getMessage("DTA, TANOC, CMPTE", null, batchVo.getLanguage()));
		                    } else {
		                    	batchVo.setErrorMessage(messageResource.getMessage("DTA, ANYIS, CMPTE", null, batchVo.getLanguage()));
		                    }
		                } else {
		                    batchVo.setTransStatus("2");
		                    batchVo.setBatchStatus("E");
		                    batchVo.setErrorMessage(StringHelper.null2string(batchVo.getErrorMessage(), messageResource.getMessage("MSG_UNSPECIFIED_ERROR", null, batchVo.getLanguage())));
		                }
		                
		                // 배치가 완료된 최종 시간 및 상태를 업데이트한다.
		                batchTarget.updateFinishedTime(batchVo.getMap());
		                
		                if(result) {
		                	if(interfaceCd.equals("TCH_IN_0002") || interfaceCd.equals("TCH_IN_0003")) {
			                	batchVo.setParentTransId(parentHistoryId);
			                	batchVo.setItemType("P");
			                	paramVo.setFunctionName("PKG_LINK_TOMS_HUB");
			                	
			                	batchVo.setParameter(paramVo);
			                	
			                	process = factory.cerate(jobVo, batchVo);
				                result = process.applyBatch();
				                
				                logger.logMessage("execute Batch procedure.... (" + result + ")");
				                
				                if (result) {
				                    batchVo.setTransStatus("1");
				                    batchVo.setBatchStatus("S");
				                    
				                    if(StringHelper.null2void(batchVo.getItemType()).equals("P")) {
				                    	batchVo.setErrorMessage(messageResource.getMessage("DTA, TANOC, CMPTE", null, batchVo.getLanguage()));
				                    } else {
				                    	batchVo.setErrorMessage(messageResource.getMessage("DTA, ANYIS, CMPTE", null, batchVo.getLanguage()));
				                    }
				                } else {
				                    batchVo.setTransStatus("2");
				                    batchVo.setBatchStatus("E");
				                    batchVo.setErrorMessage(StringHelper.null2string(batchVo.getErrorMessage(), messageResource.getMessage("MSG_UNSPECIFIED_ERROR", null, batchVo.getLanguage())));
				                }
				                
				                // 배치가 완료된 최종 시간 및 상태를 업데이트한다.
				                batchTarget.updateFinishedTime(batchVo.getMap());
		                	}
		        		}
		                
		                if(i == 0) {
		                	parentHistoryId = batchVo.getTransId();
		                }
        			}
	            } // end for(int i = 0; i < mappingList.size(); i++)
            } // end for(int v = 0; v < vList.size(); v++)
        }
        
        return batchVo;
    }
    
    /**
     * 협력사 원산지 확인서 요청메일 자동발송 배치
     * 
     * @return
     * @throws Exception
     */
    private BatchVo executeMailSendForTOMS() throws Exception {
    	BatchVo batchVo = new BatchVo();
    	boolean result = true;
    	
    	BatchLogger logger = jobVo.getBatchLogger();
    	MailSupporter supporter = null;
    	String receiverMail = null;
    	String phone = null;
    	boolean debugYn = false;
    	String mailId = null;
    	String status = "2";
    	List formFileList = null;
    	List<String> filePathList = null;
    	
    	Map mailParam = jobVo.getMap();
    	
    	MessageResource messageSource = MessageResource.getMessageInstance();
		
    	// context-mail.xml에 회사코드+"_MailSender"에 대한 resource가 있는지 확인한다.(2019.08.14)
		ApplicationContext applicationContext = ApplicationContextFactory.getAppContext();
		String resourceName = StringHelper.null2void(mailParam.get("COMPANY_CD"))+"_MailSender";
		JavaMailSenderImpl mailResource = null;
		boolean chflag = true;
		
    	if(log.isDebugEnabled()) log.debug("sending E-mail(Parameter = " + mailParam.toString());
    	
    	try {
			mailResource = (JavaMailSenderImpl) applicationContext.getBean(resourceName);
			chflag = false;
		} catch(Exception e) {
			if(log.isErrorEnabled()) log.error(e.getMessage());
			chflag = true;
		}
		
		// 회사별 메일정보가 없는 경우에는 default로 설정된 메일정보를 이용한다.
		if(chflag) {
			mailResource = MailSender.getInstance();
		}
		
    	// 1.메일발송 예약내용 조회
    	List mailList = batchTarget.selectMailSendBatchList(mailParam);
    	
    	// 2.메일발송 실행
    	for(int i = 0; i < mailList.size(); i++) {
    		Map mailMap = (Map) mailList.get(i);
    		
    		List ilist = batchTarget.selectOrderInfoDtlList(mailMap); // 대상 자재조회
			
    		// 요청할 자재가 없으면 메일을 전송하지 않도록 추가함(김종현, 2021-07-21)
    		if(ilist == null || ilist.size() == 0) {
    			continue;
    		}
    		
    		status = "2";
    		String username = StringHelper.null2void(mailMap.get("MAIL_ID"));
    		String password = StringHelper.null2void(mailMap.get("MAIL_PASSWORD"));
    		
    		// 사용자별로 등록한 메일 계정이 있으면 메일사용자 정보를 먼저 변경한다.
    		if(!username.isEmpty() && !password.isEmpty()) {
    			if(mailResource != null) {
    				mailResource.setUsername(username);
    				mailResource.setPassword(password);
    			}
    		}
    		
    		try {
    			List files = new ArrayList();
    			
    			// 회사별로 설정된 정보가 있다면 해당 SMTP정보를 이용하도록 한다.(2019.08.14)
    			if(chflag) {
    				supporter = new MailSupporter();
    			} else {
    				supporter = new MailSupporter(mailResource);
    			}
    			
    			String coments = StringHelper.null2void(mailMap.get("COMMENTS")); // 원본 메일 내용(특수기호를 포함한 문서)
    			String titleTm = StringHelper.null2void(mailMap.get("SUBJECT"));
    			String senderMail = StringHelper.null2void(mailMap.get("WRITE_EMAIL"));
    			String sender = StringHelper.null2void(mailMap.get("WRITER"));
    			
    			String rederEmail = StringHelper.null2void(mailMap.get("REDER_EMAIL"));
    			String smsSendYn = StringHelper.null2string(mailMap.get("SMS_SEND_YN"), "N");
    			
    			// 받는 사람메일 & SMS 수신자 번호
    			if(Constants.APPLICATION_LEVEL.equals("O")) { // application 운영중일 경우
    				receiverMail = StringHelper.null2void(mailMap.get("EMAIL"));
    				phone = StringHelper.null2void(mailMap.get("CELL_PHONE_NO"));
    			} else {
    				receiverMail = Constants.TEST_USER_EAMIL;
    				phone = Constants.TEST_USER_SMS;
    				debugYn = true;
    			}
    			
    			if(log.isDebugEnabled()) log.debug("Receiver Mail = " + receiverMail + "," + rederEmail + ", SMS[" + smsSendYn + "] = " + phone);
    			
    			mailMap.put("EMAIL", "To=" + receiverMail + ",CC=" + rederEmail);
    			mailMap.put("CELL_PHONE_NO", phone);
    			if(!smsSendYn.equals("Y")) {
    				mailMap.put("SMS_MSG", "");
    			}
    			
    			// 메일내용 및 제목 수정
    			Object[] msgParam = new Object[9];
    			
    			// 메일 발송내역 만들기(김종현, 2019-04-28)
    			msgParam[0] = StringHelper.null2void(mailMap.get("SESSION_COMPANY_NAME"));
    			msgParam[1] = StringHelper.null2void(mailMap.get("VENDOR_NAME"));
    			msgParam[2] = StringHelper.null2void(mailMap.get("VENDOR_CD"));
    			msgParam[3] = StringHelper.null2void(mailMap.get("WRITER"));
    			msgParam[4] = StringHelper.null2void(mailMap.get("WRITE_CONTECTS"));
    			msgParam[5] = StringHelper.null2void(mailMap.get("WRITE_EMAIL"));
    			msgParam[6] = StringHelper.null2void(mailMap.get("TCH_REQUEST_DATE"));
    			msgParam[7] = StringHelper.null2void(mailMap.get("APPLY_DATE")); // 제출 시작일자
    			msgParam[8] = StringHelper.null2void(mailMap.get("END_DATE"));   // 제출 만료일자
    			
    			for(int a = 0; a < msgParam.length; a++) {
    				String bstr = "{"+a+"}";
    				
    				coments = coments.replace(bstr, msgParam[a].toString());
    				titleTm = titleTm.replace(bstr, msgParam[a].toString());
    			}
    			
    			mailMap.put("COMMENTS_TM", coments); // 메일내용
    			
    			if(StringHelper.null2string(mailMap.get("ITEM_ATTACH_YN"), "Y").equals("N")) {
    				ilist = new ArrayList();
    			}
    		    String contents = supporter.getCooContents(mailMap, ilist, messageSource, StringHelper.null2void(mailMap.get("SESSION_DEFAULT_LANGUAGE")));
    		    mailMap.put("CONTENTS", contents);
    			
    			mailId = batchTarget.insertMailMst(mailMap); // 마스터 메일이력 등록
    			
    			mailMap.put("MAIL_SEND_MST_ID", mailId);
    			
    			if(mailId != null) {
    				Map fileParam = new HashMap();
    				
    				fileParam.put("MAIL_SEND_MST_ID", mailMap.get("REV_MAIL_SEND_MST_ID"));
    				
    				formFileList = batchTarget.selectMailSendFileList(fileParam);
    				filePathList = new ArrayList<String>();
    				
    				Configurator configurator = ConfiguratorFactory.getInstance().getConfigurator();
    				String db = StringHelper.null2void(configurator.getString("application.db.type"));
    	    		String fileSavePath = Constants.APPLICATION_REAL_PATH + "/upload/Temp/";
    	    		
    				for(int f = 0; f < formFileList.size(); f++) {
    					Map fileMap = (Map) formFileList.get(f);
    					String FileFullName = StringHelper.null2void(fileMap.get("ORIGIN_FILE_NAME"));
    					byte[] data = null;
    					
    					if (db.equals("MSSQL") || db.equals("POSTGRESQL")) {
    		            	data = (byte[]) fileMap.get("REALFILE");
    		            } else {
    		            	Blob blob = (Blob) fileMap.get("REALFILE");
    		            	data = OracleTypeHelper.getBytes(blob);
    		            }
    					
    		    		String originalExtension = FileFullName.substring(FileFullName.lastIndexOf("."));
    					String newFileName = UUID.randomUUID().toString() + originalExtension; 
    					FileOutputStream outputStream = null;
    					
    					try {
	    					File tfile = new File(fileSavePath+newFileName);
	    					
	    					if(tfile.exists()) tfile.delete();
	    					
	    					outputStream = new FileOutputStream(fileSavePath+newFileName);
	    					outputStream.write(data);
	    					
	    					MailAttachment ma = new MailAttachment();
							
							ma.setName(FileFullName);
							ma.setPath(fileSavePath+newFileName);
							
							if(log.isDebugEnabled()) log.debug("attachment file = " + fileSavePath + newFileName + ", file name = " + FileFullName);
							
							files.add(ma);
    					} catch(Exception e) {
    						log.error(e);
    					} finally {
    						if(outputStream != null) outputStream.close();
    					}
    					
						filePathList.add(fileSavePath + newFileName);
    				}
    				
    				// 요청대상 협력사 등록
    				batchTarget.insertMailVendor(mailMap);
    				
    				// 요청대상 협력사의 자재등록
					for(int k = 0; k < ilist.size(); k++) {
						Map imap = (Map) ilist.get(k);
						
						imap.put("COMPANY_CD", StringHelper.null2void(imap.get("COMPANY_CD")));
						imap.put("MAIL_SEND_MST_ID", mailId);
					}
					
					if(StringHelper.null2string(mailMap.get("ITEM_ATTACH_YN"), "Y").equals("Y")) {
						batchTarget.insertMailVendorItem(ilist, mailMap);
					}
    				
    				if(log.isDebugEnabled()) log.debug("completed insertMailVendorItem().");
    				
					// 실제 이메일 발송
					if(senderMail.isEmpty()) {
						if(sender.isEmpty()) {
							supporter.setFrom(supporter.getSmtpUserMail());
						} else {
							supporter.setFrom(supporter.getSmtpUserMail(), sender);
						}
					} else {
						if(sender.isEmpty()) {
							supporter.setFrom(senderMail);
						} else {
							supporter.setFrom(senderMail, sender);
						}
					}
					
					supporter.setSubject(titleTm);
					supporter.setDebug(debugYn);
					supporter.setHtmlMsg(contents.replaceAll("\n", "<br>")); // HTML 타입으로 메시지 전송
					
					if(log.isDebugEnabled()) log.debug("mal title = " + titleTm + ", debug(Y/N) = " + debugYn + ", mail contents = " + contents);
					if(log.isFatalEnabled()) log.fatal("host=" + supporter.getHost() + ", port=" + supporter.getPort() + ", mail="+ senderMail + ", sender name = " + sender + ", user name=" + supporter.getUsername() + " / " + StringHelper.toSubString(mailResource.getPassword(), 0, 3)+"*******");

					if(files.size() > 0) supporter.attach(files);
					
					supporter.addTo(new String(supporter.getEmailAddr(receiverMail).getBytes(Constants.APPLICATION_CONTEXT_CHARSET), "8859_1"));
					if(!rederEmail.isEmpty()) {
						supporter.addCC(new String(supporter.getEmailAddr(rederEmail).getBytes(Constants.APPLICATION_CONTEXT_CHARSET), "8859_1"));
					}
					
					supporter.send();
    			}
    			
				// SMS발송
				if(smsSendYn.equals("Y") && !phone.isEmpty()) {
					Map smsMap = new HashMap();
					
					smsMap.put("COMPANY_CD", StringHelper.null2void(mailMap.get("COMPANY_CD")));
					smsMap.put("SEND_SEQ", mailId);
					smsMap.put("USER_ID", StringHelper.null2void(mailMap.get("USER_ID")));
					smsMap.put("SEND_MSG", StringHelper.null2void(mailMap.get("SMS_MSG")));
					smsMap.put("RECE_DEPTNAME", StringHelper.null2void(mailMap.get("VENDOR_NAME")));
					smsMap.put("RECE_USERID", StringHelper.null2void(mailMap.get("VENDOR_CD")));
					smsMap.put("RECE_USERNAME", StringHelper.null2void(mailMap.get("INCHARGE_NAME")));
					smsMap.put("RECE_PHONE", phone);
					
					batchTarget.insertSendSMS(smsMap);
					
					if(log.isDebugEnabled()) log.debug("Requested SMS.... Phone number : " + phone);
				}
    		} catch(Exception e) {
    			try {
    				String failMsg = e.getMessage();
    				if(failMsg == null) failMsg = "Mail send failed.";
    				
    				Map expMap = new HashMap();
    				
    				expMap.put("MAIL_SEND_MST_ID", mailId);
    				expMap.put("SEND_STATUS", "3");
    				expMap.put("SEND_RESULT_MSG", failMsg);
    				expMap.put("UPDATE_BY", mailMap.get("UPDATE_BY"));
    				
    				batchTarget.updateMail(expMap);
    				
    				status = "3";
    			} catch(Exception ep) {
    				log.error(e);
    				result = false;
    			}
    		} finally {
    			try {
	    			// 3. 메일발송결과 업데이트
	        		Map expMap = new HashMap();
	    			
	        		mailMap.put("STATUS", status);
	        		
	        		Map uMap = new HashMap();
	        		
	        		uMap.put("STATUS", status);
	        		uMap.put("UPDATE_BY", StringHelper.null2void(mailMap.get("UPDATE_BY")));
	        		uMap.put("COMPANY_CD", StringHelper.null2void(mailMap.get("COMPANY_CD")));
	        		uMap.put("DUAL_COMPANY_CD", StringHelper.null2void(mailMap.get("DUAL_COMPANY_CD")));
	        		uMap.put("DUAL_COMPANY_TYPE", StringHelper.null2void(mailMap.get("DUAL_COMPANY_TYPE")));
	        		uMap.put("MAIL_TYPE", StringHelper.null2void(mailMap.get("MAIL_TYPE")));
	        		
	        		batchTarget.updateMailSendHistoryInfo(uMap); // 메일발송 예약(대기중인 내용에 대해 성공 또는 실패로 업데이트)
	        		
	        		// 4. 다음예약시간 등록
	        		Map resMap = batchTarget.selectMailNextScheduleInfo(mailMap); // 예약정보 수취
	        		Map nextMap =  QuartzSupporter.getNextTimeToMap(resMap); // 다음예약시간 값 가져오기
	        		
	        		logger.logMessage("next schedule = " + nextMap.toString() + ", send info = " + uMap.toString());
	        		
	        		if(nextMap.size() > 0) {
	    	    		resMap.putAll(nextMap);
	    	    		
	    	    		resMap.put("STATUS", "1");
	    	    		
	    	    		batchTarget.insertMailSendHistoryInfo(resMap);
	        		} else {
	        			logger.logMessage("not found the reservation info.");
	        		}
	        		
	        		if(filePathList != null && filePathList.size() > 0) {
	        			for(int l = 0; l < filePathList.size(); l++) {
	        				File dfile = new File(filePathList.get(l));
	        				boolean dchk = dfile.delete();
	        				
	        				if(log.isDebugEnabled()) log.debug("delete file("+dchk+") = " + filePathList.get(l));
	        			}
	        		}
    			} catch(Exception e) {
    				log.error(e);
    				result = false;
    			}
    		}
    	}
    	
    	if (result) {
            batchVo.setTransStatus("1");
            batchVo.setBatchStatus("S");
        } else {
            batchVo.setTransStatus("2");
            batchVo.setBatchStatus("E");
        }
    	return batchVo;
    }
    
    /**
     * FTA PASS 바츠 연계
     * 
     * @return
     * @throws Exception
     */
    private BatchVo executeInterfaceBatchForPass() throws Exception {
    	BatchVo batchVo = null;
    	BatchFactory factory = new ProcessFactory();
    	BatchProcess process = null;
    	BatchLogger logger = jobVo.getBatchLogger();
    	
    	boolean result = true;
    	
    	MessageResource messageResource = MessageResource.getMessageInstance();
    	String lang = CommonUtil.getContextLanguage(jobVo.getCompanyCode());
    	
    	// 인터페이스 항목 중 엑셀방식을 제외한 배치할 항목을 조회한다.
        List mappingList = batchTarget.selectInterfaceMappingList(jobVo.getMap());
        
        if (mappingList == null || mappingList.size() < 1) {
            logger.logMessage("Can not run the interface. interface item is empty");
        } else {
        	if (log.isDebugEnabled()) log.debug("interface start for batch....");
        	
        	// 배치마감월
        	jobVo.setPyyyymm(DateHelper.getSimpleDate("yyyy") + DateHelper.getSimpleDate("MM"));
        	
        	List reqList = batchTarget.selectFtaPassDoList(jobVo.getMap());
        	String filePath = Constants.APPLICATION_REAL_PATH + "/upload/pass/"+jobVo.getCompanyCode()+"/"; // XML_FILE_PATH
        	
        	if(reqList != null) {
        		
        		// 1. FTA PASS와 인터페이스 수행
    			for(int k = 0; k < reqList.size(); k++) {
    				Map reqInfo = (Map) reqList.get(k);
    				int dupCnt = StringHelper.null2zero(reqInfo.get("DUP_CNT"));
    				
    				// 이미 확인서 제출된 경우라면 요청상태를 보류로 변경시킴
    				if(dupCnt == 0) {
    					// 1.1 인터페이스 요청정보 생성
	    				reqInfo.put("OUT_FILE_PATH", filePath);
			        	
	    				// 1.2 PASS에 원산지 확인서 요청(웹서비스 통신)
			        	File xml_file = null;
						try {
							xml_file = null; //WebServiceClient.getResponseXML(reqInfo);
						} catch(Exception e) {
							log.error(e.getMessage());
						}
    				} else {
    					reqInfo.put("STATUS", "2");
    					reqInfo.put("USER_ID", "FTA_PASS_BATCH");
    					reqInfo.put("REJECT_REASON", "배치수행 중 이미 제출된 확인서 정보가 있어 자동 보류됨");
    					
    					batchTarget.updatePassReqStatus(reqInfo);
    				}
    			}
    			
				// 2. 수신한 XML정보 등록
    			boolean resultStatus = true;
    			String parentHistoryId = ""; 
    			
	    		// 해당 스케쥴에서 지정한 인터페이스 항목에 대해 배치를 수행한다.
	            for (int i = 0; i < mappingList.size(); i++) {
	            	Map<String, String> mappingMap = (Map<String, String>) mappingList.get(i);
	            	batchVo = new BatchVo();
	            	
                    batchVo.setPutAll(mappingMap);
                    batchVo.setJcoId(mappingMap.get(Consistent.IF_BATCH_INTERFACE_CD));
                    batchVo.setLanguage(lang);
                    batchVo.setParentTransId(parentHistoryId);
                    batchVo.setFilePath(mappingMap.get(Consistent.IF_BATCH_FILE_PATH)); // 실행 프로그램 및 URL
                    
                    // 파라메터 설정
                    ParameterVo paramVo = new ParameterVo();
                    
                    paramVo.setCompanyCode(jobVo.getCompanyCode());
                    paramVo.setFromDate(DateHelper.getCurrentDateAsString());
        	        paramVo.setToDate(DateHelper.getCurrentDateAsString());
                    paramVo.setBatchFlag("D"); // 배치구분(D:일배치, M:월배치, W:주배치, U:User Manually)
                    
                    if(StringHelper.null2void(batchVo.getItemType()).equals("P")) {
                        batchVo.setTotalRows("-1");
                        batchVo.setJobInfo(jobVo.getMap());
                        // 인터페이스 데이터 추출 작업이 없기 때문에 성공으로 등록함.
                        batchVo.setTransStatus("1");
                        // 수행할 function명을 지정한다.
                        paramVo.setFunctionName(mappingMap.get(Consistent.IF_BATCH_INTERFACE_CD));
                    } else {
                    	// 실행할 디렉토리 경로를 추가로 지정함
                    	Map addParams = new HashMap();
                    	
        		        addParams.put("XML_FILE_PATH", filePath);
        		        // addParams.put("XML_FILE_NAME", ""); // 수신된 파일을 동시에 처리하기 위해 파일은 지정하지 않음
        		        
        		        paramVo.setPutAll(addParams);
        		        paramVo.setIfCode(mappingMap.get(Consistent.IF_BATCH_INTERFACE_CD));
                    }
        	        
        	        // 파라메터를 BatchVo에 저장
                    batchVo.setParameter(paramVo);
                    
        	        process = factory.cerate(jobVo, batchVo);
                    result = process.applyBatch();
                    
                    if (result) {
                        batchVo.setTransStatus("1");
                        batchVo.setBatchStatus("S");
                        
                        if(StringHelper.null2void(batchVo.getItemType()).equals("P")) {
                        	batchVo.setErrorMessage(messageResource.getMessage("DTA, TANOC, CMPTE", null, batchVo.getLanguage()));
                        } else {
                        	batchVo.setErrorMessage(messageResource.getMessage("DTA, ANYIS, CMPTE", null, batchVo.getLanguage()));
                        }
                    } else {
                        batchVo.setTransStatus("2");
                        batchVo.setBatchStatus("E");
                        batchVo.setErrorMessage(StringHelper.null2string(batchVo.getErrorMessage(), messageResource.getMessage("MSG_UNSPECIFIED_ERROR", null, batchVo.getLanguage())));
                        
                        resultStatus = false;
                    }
                    
                    // 배치가 완료된 최종 시간 및 상태를 업데이트한다.
                    batchTarget.updateFinishedTime(batchVo.getMap());
                    
                    parentHistoryId = batchVo.getTransId();
	            }
				
    			// 3. 일괄 배치 수행
				
//		        	if(files != null) {
//				        for(int f = 0; f < files.length; f++) {
//				    		File file = files[f];
//				    		
//				    		fileName = file.getName();
//				    		HubSupporter supporter = new HubSupporter(filePath + File.separator + fileName);
//				    		String fileCode = supporter.getInterfaceId();
//				    		String parentHistoryId = ""; 
//				    		
//				    		if(fileCode != null) {
//				    			
//					            
//					            // 완료된 파일은 완료 파일 디렉토리로 이동 시킴
//					            String movePath = null;
//					    		
//					            if(resultStatus) {
//					            	movePath = HubSupporter.getConfigFilePath("R", fileCode, hubId);
//					            } else {
//					            	movePath = filePath + File.separator + "temp";
//					            }
//					            
//					            if(movePath != null) {
//					    			FileUtil.fileMove(filePath, fileName, movePath);
//					    		}
//			            	}
//			            }
//		        	} // end if(files != null)
        	} // end if(organList != null)
        }
        
        if (log.isDebugEnabled()) log.debug("batch result = " + result);
        
        if(batchVo == null) {
        	batchVo = new BatchVo();
        }
        
        if (result) {
            batchVo.setTransStatus("1");
            batchVo.setBatchStatus("S");
        } else {
            batchVo.setTransStatus("2");
            batchVo.setBatchStatus("E");
        }
        
        return batchVo;
    }
    
    /**
     * FTA 관세율 배치 수행(일단위 배치 수행)
     * 
     * @return
     * @throws Exception
     */
    private BatchVo executeInterfaceBatchForTariff() throws Exception{
    	BatchVo batchVo = null;
    	BatchFactory factory = new ProcessFactory();
    	BatchProcess process = null;
    	BatchLogger logger = jobVo.getBatchLogger();
    	MessageResource messageResource = MessageResource.getMessageInstance();
    	
    	boolean result = true;
    	String lang = CommonUtil.getContextLanguage(jobVo.getCompanyCode());
    	
    	// 사용자 지정 정산월을 조회
        List<Object> yyyymmList = batchTarget.selectBatchYYYYMM(jobVo.getMap());
        
        if(yyyymmList != null && yyyymmList.size() == 0) {
        	batchVo = new BatchVo();
        	
        	batchVo.setTransStatus("2");
            batchVo.setBatchStatus("E");
            batchVo.setErrorMessage(messageResource.getMessage("MSG_NOT_FOUND_SCHEDULE_INFO", null, lang));
            
        	return batchVo;
        }
        
        // 배치를 수행할 마감월 리스트
        List<String> dateArray = new LinkedList<String>();
        
        Map dataBatchMap = (Map) yyyymmList.get(0);
        
        int prevNum = StringHelper.null2zero(dataBatchMap.get("BATCH_PREV_NUM"), -1);
        
        // 타 시스템간의 인터페이스 수행
        // 인터페이스 항목 중 엑셀방식인 경우 화면에서 직접 처리하는 것으로 변경하여, 본 작업에서는 제외시킴(2016.12.27, YNI-Maker)
        List mappingList = batchTarget.selectInterfaceMappingList(jobVo.getMap());

        if (mappingList == null || mappingList.size() < 1) {
        	if(jobVo.getMap() != null) logger.logMessage("job VO info. = "+jobVo.getMap().toString());
        	else logger.logMessage("job VO is null.");
        		
            logger.logMessage("Can not run the interface. interface item is empty");
        } else {
            if (log.isDebugEnabled()) log.debug("interface start for batch....");
            
            String parentHistoryId = ""; 
            String toYear = DateHelper.getSimpleDate("yyyy");
            String toMonth = DateHelper.getSimpleDate("MM");
            String toDay = DateHelper.getSimpleDate("dd");
            String fromDate = DateHelper.getCalcDateAsString(toYear, toMonth, toDay, prevNum, "day");
            String toDate = fromDate;
            
            // 해당 스케쥴에서 지정한 인터페이스 항목에 대해 배치를 수행한다.
            for (int i = 0; i < mappingList.size(); i++) {
                Map<String, String> mappingMap = (Map<String, String>) mappingList.get(i);
                String ifCd = mappingMap.get(Consistent.IF_BATCH_INTERFACE_CD);
                
                batchVo = new BatchVo();

                batchVo.setPutAll(mappingMap);
                batchVo.setJcoId(ifCd);
                batchVo.setLanguage(lang);
                batchVo.setParentTransId(parentHistoryId);
                
                // 파라메터 설정
                ParameterVo paramVo = new ParameterVo();

                paramVo.setCompanyCode(jobVo.getCompanyCode());
                paramVo.setFromDate(fromDate);
                paramVo.setToDate(toDate);
                paramVo.setBatchFlag("D"); // 배치구분(D:일배치, M:월배치, W:주배치, U:User Manually)
                paramVo.setIfCode(mappingMap.get(Consistent.IF_BATCH_INTERFACE_CD)); // TFC_KR_001:Tradenavi tariff crawling
                paramVo.setUrl(mappingMap.get(Consistent.IF_PARAMETER_URL) + mappingMap.get("FILE_PATH")); // 웹 크롤링 요청 주소
            	
                // ITEM_TYPE(P:프로시져, T:테이블, C:HTTP 처리, E:통합 엑셀업로드)
                if(StringHelper.null2void(batchVo.getItemType()).equals("P")) {
                    batchVo.setTotalRows("-1");
                    batchVo.setJobInfo(jobVo.getMap());
                    // 인터페이스 데이터 추출 작업이 없기 때문에 성공으로 등록함.
                    batchVo.setTransStatus("1");
                    // 수행할 function명을 지정한다.
                    paramVo.setFunctionName(ifCd);
                } else {
                	paramVo.setIfCode(ifCd);
                }
                
                // 파라메터를 BatchVo에 저장
                batchVo.setParameter(paramVo);
                
                process = factory.cerate(jobVo, batchVo);
                result = process.applyBatch();

                if (result) {
                    batchVo.setTransStatus("1");
                    batchVo.setBatchStatus("S");
                    
                    if(batchVo.getErrorMessage().isEmpty()) {
	                    if(StringHelper.null2void(batchVo.getItemType()).equals("P")) {
	                    	batchVo.setErrorMessage(messageResource.getMessage("DTA, TANOC, CMPTE", null, batchVo.getLanguage()));
	                    } else {
	                    	batchVo.setErrorMessage(messageResource.getMessage("DTA, INF, CMPTE", null, batchVo.getLanguage()));
	                    }
                    }
                } else {
                    batchVo.setTransStatus("2");
                    batchVo.setBatchStatus("E");
                    batchVo.setErrorMessage(StringHelper.null2string(batchVo.getErrorMessage(), messageResource.getMessage("MSG_UNSPECIFIED_ERROR", null, batchVo.getLanguage())));
                }
                
                // 배치가 완료된 최종 시간 및 상태를 업데이트한다.
                batchTarget.updateFinishedTime(batchVo.getMap());
                
                if(i == 0) {
                	parentHistoryId = batchVo.getTransId();
                }
                
                if(!result) {
                	break;
                }
            }
        }
        
        return batchVo;
    }
    
    /**
     * 서비스 Plan에 따라 배치작업을 수행하는 매소드
     * @return
     * @throws Exception
     */
    private BatchVo executeInterfaceBatchForService() throws Exception{
    	BatchVo batchVo = null;
    	BatchFactory factory = new ProcessFactory();
    	BatchProcess process = null;
    	BatchLogger logger = jobVo.getBatchLogger();
    	MessageResource messageResource = MessageResource.getMessageInstance();
    	
    	String lang = CommonUtil.getContextLanguage(jobVo.getCompanyCode());
    	String divisionCode = null;
        String fromDate = null;
        String toDate = null;
        String itemCode = null;
        String batchFlag = null;
        
        boolean result = true;
        
        // 스케쥴내 서비스 조회
	    List services = batchTarget.selectServiceMappingList(jobVo.getMap());
	    
        if (services == null || services.size() < 1) {
        	if(jobVo.getMap() != null) logger.logMessage("job VO info. = "+jobVo.getMap().toString());
        	else logger.logMessage("Job VO is null.");
        		
            logger.logMessage("interface item is empty.");
        } else {
            if (log.isDebugEnabled()) log.debug("interface start for batch....");
            
            // 해당 스케쥴에서 지정한 서비스에 대한 배치를 수행한다.
            for(int i = 0; i < services.size(); i++) {
                Map<String, String> service = (Map<String, String>) services.get(i);
                
                // 서비스 수행이력 등록(개발 필요)
                // service 이력번호 조회
                String parentHistoryId = batchTarget.selectServiceTranID(); // service 이력번호
                
                service.put("SERVICE_HISTORY_ID", parentHistoryId);
                
                // service 이력 등록
                batchTarget.insertServiceHistory(service);
                
                // 현재일시 조회
                String now = DateHelper.getCurrentDateTimeAsString();
                
                // 서비스 Plan 조회
                List plans = batchTarget.selectServicePlanList(service);
                
                // 서비스 Plan에 대한 배치를 수행한다.
                for(int p = 0; p < plans.size(); p++) {
	                Map<String, String> plan = (Map<String, String>) plans.get(p);
	                
	                log.debug("======================================================================================");
	                
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
	                batchVo.setLanguage(lang);
	                batchVo.setItemType("S"); // 인터페이스 방식(S:서비스, T:테이블, P:프로시져)
	                batchVo.setParentTransId(parentHistoryId);
	                
	                // 파라메터 생성
	                ParameterVo paramVo = new ParameterVo();
	                
	                paramVo.setCompanyCode(jobVo.getCompanyCode());
	            	
	                // 파라메터를 BatchVo에 저장
	                batchVo.setParameter(paramVo);
	                
	                // 원본 파라메터 정보를 서비스 이력에 업데이트 한다.(RSBATCH.updateServiceHistoryStatus)
	                Map planMap = new HashMap();
	              	
	                planMap.put("COMPANY_CD", plan.get("COMPANY_CD"));
	                planMap.put("SERVICE_HISTORY_ID", plan.get("SERVICE_HISTORY_ID"));
	                planMap.put("INTERFACE_HISTORY_ID", plan.get("INTERFACE_HISTORY_ID"));
	                planMap.put("REQUEST_PARAM", JsonUtil.getViewJson(paramVo.getMap()));
	                planMap.put("TRANS_STATUS", serverState); // 서비스 상태(1:처리전, 2:처리중, 3:완료, 4:에러, 5:서버중지, 6:서버장애,9:전송취소)
		    		
	                batchTarget.updateServiceHistoryStatus(planMap);
	                
	                if(serverState.equals("2")) { // 처리중 상태에서만 배치를 수행함
		                // 배치 수행요청
		                process = factory.cerate(jobVo, batchVo);
		                result = process.applyBatch();
		
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
		                batchTarget.updateFinishedTime(batchVo.getMap());
		                // 서비스 최종 실행상태를 업데이트한다.
		                planMap.put("TRANS_STATUS", serverState); // 서비스 상태(1:처리전, 2:처리중, 3:완료, 4:에러, 5:서버중지, 6:서버장애,9:전송취소)
		                planMap.put("REQUEST_PARAM", "");
			    		
		                batchTarget.updateServiceHistoryStatus(planMap);
	                }
	            }
            }
        }
        
        if (log.isDebugEnabled()) log.debug("batch result = " + result);
        
        return batchVo;
    }
    
}
