package com.yni.fta.common.batch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

import com.google.gson.JsonObject;
import com.yni.fta.common.Consistent;
import com.yni.fta.common.batch.delegate.DataHandler;
import com.yni.fta.common.batch.vo.BatchVo;
import com.yni.fta.common.batch.vo.JobVo;
import com.yni.fta.common.batch.vo.ParameterVo;
import com.yni.fta.common.beans.target.InterfaceTarget;
import com.yni.fta.common.tools.BatchSupporter;
import com.yni.fta.common.tools.DataConvertUtil;
import com.yni.fta.common.tools.ObjectSupporter;

import kr.yni.frame.batch.logger.BatchLogger;
import kr.yni.frame.batch.logger.impl.BatchLoggerImpl;
import kr.yni.frame.context.ApplicationContextFactory;
import kr.yni.frame.exception.FrameException;
import kr.yni.frame.mapper.ParamReader;
import kr.yni.frame.mapper.element.Jco;
import kr.yni.frame.mapper.util.JcoMapValidator;
import kr.yni.frame.util.DataMapHelper;
import kr.yni.frame.util.JsonUtil;
import kr.yni.frame.util.StringHelper;

/**
 * 배치 프로세스를 정의하고, 시작과 종료에 대한 로그를 기록하는 클래스
 * 
 * @author jonghyun3.kim
 *
 */
public abstract class BatchProcess {

    protected Log log = LogFactory.getLog(this.getClass());
    
    protected InterfaceTarget batchTarget;

    protected BatchLogger logger;

    protected JobVo jobVo;

    protected BatchVo batchVo;

    public BatchProcess() {
    }

    protected BatchProcess(BatchLogger logger) {
        this(logger, null);
    }

    protected BatchProcess(BatchLogger blogger, InterfaceTarget target) {
        if (target != null) {
            this.batchTarget = target;
        } else {
            ApplicationContext ctx = ApplicationContextFactory.getAppContext();
            batchTarget = (InterfaceTarget) ctx.getBean("batchTargetService");
        }

        if (blogger != null) {
            this.logger = blogger;
        } else {
            if (jobVo.getBatchLogger() != null) {
                this.logger = jobVo.getBatchLogger();
            } else {
                this.logger = new BatchLoggerImpl();
            }
        }
    }

    protected void setJobVo(JobVo vo) {
        this.jobVo = vo;
    }

    protected void setBatchVo(BatchVo vo) {
        this.batchVo = vo;
    }
    
    /**
     * 배치 시작
     * 
     * @param jobVo 스케쥴 공통 파라메터 & 이전에 수행된 배치정보
     * @param batchVo 현재 실행중인 배치정보
     * @return 실행 결과
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public boolean startBatch() throws Exception {
        boolean checker = true;
        String transID = null;

        try {
            if (log.isDebugEnabled())
                log.debug("startBatch() job info : " + jobVo.toString() + " / batch info : " + batchVo.toString());

            // 인터페이스의 마스터Key 조회
            String transId = batchTarget.selectTransKey();
            batchVo.setTransId(StringHelper.null2void(transId));
            batchVo.setParentTransId(StringHelper.null2string(batchVo.getParentTransId(), transId));
            
            transID = StringHelper.null2void(batchVo.getTransId());
            if (log.isDebugEnabled()) {
                log.debug("1.Get trans number : " + transID);
            }

            // 초기값 설정
            batchVo.setTotalRows("0");
            batchVo.setJobInfo(jobVo.getMap());

            Map map = batchVo.getMap();
            ParameterVo parameter = batchVo.getParameter();

            if (parameter != null) {
                parameter.setTransId(transID);
                map.put(Consistent.IF_PARAMETER_FROM_DATE, parameter.getFromDate());
                map.put(Consistent.IF_PARAMETER_TO_DATE, parameter.getToDate());
            }

            // 소스데이터 이관 마스터 정보 셋팅
            if (!transID.isEmpty()) {
                batchTarget.insertTransInfoData(map);
            }

            // 상위 배치 수행이 정상적으로 완료되었지는 체크
            checker = batchRunable(jobVo, batchVo);

            // 타 시스템간 배치 수행요청
            if (StringHelper.null2void(batchVo.getItemType()).equals("T") || StringHelper.null2void(batchVo.getItemType()).equals("E")) {
                if(checker) checker = this.executeBatch();
            } else if (StringHelper.null2void(batchVo.getItemType()).equals("C")) {
            	String path = parameter.getUrl();
            	
            	log.debug("URL path = " + path);
            	
            	if(path.endsWith("/selectConnect2Server")) {  // 서버 연결 체크
            		checker = batchTarget.selectConnect2Server(batchVo);
            	} else if(path.endsWith("/selectCertifyHubCompany")) { // TOMS Cloud 가입사 라이센스 인증
            		checker = batchTarget.selectCertifyHubCompany(batchVo);
            	} else if(path.endsWith("/updateCoMasterForHubList")) { // 원산지 확인서 등록결과를 수신측에 알림
            		checker = batchTarget.selectCertifyHubCompany(batchVo);
            	} else {
            		if(checker) checker = this.executeBatch();
            	}
            } else {
                if (log.isInfoEnabled())
                    log.info("interface tpye is not T/E/C(Data Transaction) : " + StringHelper.null2void(batchVo.getItemType()));
            }

            if (checker) { // 배치 실행결과
            	batchVo.setBatchStatus("S");
            } else {
                batchVo.setBatchStatus("E");

                // 실패한 경우 모든 작업를 중지 시키기 위해 <code>Exception</code>을 발생함
                // throw new FrameException("MSG_UNSPECIFIED_ERROR");
            }

            jobVo.setBatchList(jobVo.getBatchVoSize(), batchVo);
        } catch (Exception exp) {
            try {
                logger.logMessage("[ERROR] " + exp.getMessage());
                checker = false;
            } catch (Exception ex) {
                throw ex;
            }

            throw exp;
        }
        
        return checker;
    }
    
    /**
     * 인터페이스 배치를 실행한다.
     * 
     * @param jobVo 스케쥴 공통 파라메터 & 이전에 수행된 배치정보
     * @param batchVo 현재 실행중인 배치정보
     * @return 실행결과
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Boolean executeBatch() throws Exception {
        List sourceList = null;
        String message = null;
        int rstCnt = 0;

        try {
        	long stime = System.currentTimeMillis(); // 수행 시간 계산용
        	
        	// 유효성 체크여부(미체크 시에는 DB대 DB 테이블간 즉시 이관시킨다.
        	String validCheck = StringHelper.null2void(batchVo.getValidCheckYn());
        	boolean valid = true;
        	if(validCheck.equals("N")) valid = false;
        	
        	log.info("Validation check("+validCheck+") = " + valid);
        	
        	// ERP데이터 추출
            List<Object> erpData = batchTarget.selectRemoteObject(batchVo);
            //Locale locale = SystemHelper.getLocale(StringHelper.null2string(batchVo.getLanguage(), Constants.DEFAULT_LANGUAGE));
            String method = StringHelper.null2void(batchVo.getInterfaceMethod());
            
            long ftime = System.currentTimeMillis();
            if(log.isInfoEnabled()) log.info("0. batchTarget.selectRemoteObject.(execute time=" + (ftime - stime) + " msec.");
            
            // ERP데이터의 유효성 검증
            if(valid) {
	            stime = System.currentTimeMillis(); // 수행 시간 계산용
	            
	            if (erpData != null && erpData.size() > 0) {
	            	if(log.isDebugEnabled()) log.debug("get ERP Data = " + erpData.get(0));
	            	
	                if ("E".equals(method)) { // Excel은 화면에서 Validation하므로 추가 validation없이 진행한다.
	                	sourceList = erpData;
	                } else {
	                	sourceList = JcoMapValidator.executeCheck(batchVo.getJcoId(), erpData, true);
	                	
	                	log.info(sourceList.get(0));
	                }
	            } else {
	                sourceList = new ArrayList();
	            }
	            
	            ftime = System.currentTimeMillis();
            } else {
            	sourceList = erpData;
            }
            
            if(log.isInfoEnabled()) log.info("1. JcoMapValidator.executeCheck.(execute time=" + (ftime - stime) + " msec.");
            
            // ERP데이터에서 추출한 데이터 건수 업데이트
            stime = System.currentTimeMillis(); // 수행 시간 계산용
            
            if (sourceList != null && sourceList.size() > 0) {
                batchVo.setTotalRows(StringHelper.null2string(sourceList.size(), "0"));

                rstCnt = batchTarget.updateJcoCountData(batchVo);
            }
            
            ftime = System.currentTimeMillis();
            if(log.isInfoEnabled()) log.info("2. batchTarget.updateJcoCountData.(execute time=" + (ftime - stime) + " msec.");
            
            // ERP에서 획득한 데이터을 전송테이블에 이관
            stime = System.currentTimeMillis(); // 수행 시간 계산용
            
            // 대상 테이블의 컬럼, 속성 조회하는 매소드
            List<Object> result = batchTarget.selectSchedulerItemList(batchVo.getMap());
            
            if (rstCnt > -1) {
            	if(valid) {
            		rstCnt = batchTarget.insertTransDtlData(batchVo, sourceList); // 성공시 0이 리턴된다.
            	} else {
            		rstCnt = batchTarget.insertTransDtlData(batchVo, result); // 성공시 0이 리턴된다.
            	}
            }
            
            ftime = System.currentTimeMillis();
            if(log.isInfoEnabled()) log.info("3. batchTarget.insertTransDtlData.(execute time=" + (ftime - stime) + " msec.");
            
            // 이관결과 업데이트
            stime = System.currentTimeMillis(); // 수행 시간 계산용
            if (rstCnt > -1) {
                rstCnt = batchTarget.updateTransInfoData(batchVo.getMap());
            } else {
                return false;
            }
            
            ftime = System.currentTimeMillis();
            if(log.isInfoEnabled()) log.info("4. batchTarget.updateTransInfoData.(execute time=" + (ftime - stime) + " msec.");
            
            stime = System.currentTimeMillis(); // 수행 시간 계산용
            if (rstCnt > 0) {
                // 8. 에러가 0개인 경우 데이터를 인터페이스 테이블에 등록
                if (result != null && result.size() > 0) {
                	if(method.equals("H") || method.equals("C") || method.equals("F")) { // 허브 데이터 이관(바이너리 데이터 처리를 위해 소스 > 대상 테이블로 바로 이관시키며, 추후에는 모두 이 매소드를 적용하도록 변경할 예정임.2017-02-27) 
                		rstCnt = batchTarget.insertTargetFromSourceData(batchVo, result);
                	} else {
                		rstCnt = batchTarget.insertToTargetData(batchVo, result);
                	}
                } else {
                    return false;
                }
            }

            ftime = System.currentTimeMillis();
            if(log.isInfoEnabled()) log.info("5. batchTarget.insertToTargetData.(execute time=" + (ftime - stime) + " msec.");
            
            if ("S".equals(batchVo.getBatchStatus())) {
                message = "success";
            } else {
                if ("E".equals(batchVo.getBatchStatus())) {
                    message = batchVo.getErrorMessage();

                    throw new FrameException(message);
                } else {
                    message = "input data is empty.";
                }
            }
        } catch (Exception exp) {
            try {
            	message = exp.getMessage();
            	
                batchVo.setErrorMessage(exp.getMessage());

                batchTarget.updateProcedureResult(batchVo.getMap());
            } catch (Exception ex) {
                throw ex;
            }
            throw exp;
        } finally {
            logger.logMessage("(ERP -> TOMS) result message = " + message);
        }

        return true;
    }
    
    /**
     * 서비스 배치 시작(연계서버용)
     * 
     * @return
     * @throws Exception
     */
    public boolean startService() throws Exception {
    	boolean checker = true;
        String transID = null;

        try {
            if (log.isDebugEnabled())
                log.debug("startService() job info : " + jobVo.toString() + " / batch info : " + batchVo.toString());
            
            Map map = batchVo.getMap();
            
            // 서비스 등록 시 지정한 인터페이스 이력Key를 조회
            String transId = StringHelper.null2void(map.get(Consistent.IF_BATCH_TRANS_ID));
            
            batchVo.setTransId(StringHelper.null2void(transId));
            batchVo.setParentTransId(StringHelper.null2string(batchVo.getParentTransId(), transId));
            
            transID = StringHelper.null2void(batchVo.getTransId());
            
            if (log.isDebugEnabled()) log.debug("Get trans number : " + transID);

            // 초기값 설정
            batchVo.setTotalRows("0");
            batchVo.setJobInfo(jobVo.getMap());
            
            // 소스데이터 이관 마스터 정보 셋팅
            if (!transID.isEmpty()) {
            	Map hisData = DataMapHelper.toCloneMap(map);
            	
            	hisData.put(Consistent.IF_BATCH_INTERFACE_PARAM, map.get("TRNAS_READTIME"));
            	
            	batchTarget.insertTransInfoData(hisData);
            }
            
            // 서비스 수행요청
            checker = this.serviceBatch(map);
            
            if (checker) { // 배치 실행결과
            	batchVo.setBatchStatus("S");
            } else {
                batchVo.setBatchStatus("E");
            }

            jobVo.setBatchList(jobVo.getBatchVoSize(), batchVo);
        } catch (Exception exp) {
            try {
                logger.logMessage("[ERROR] " + exp.getMessage());
                checker = false;
            } catch (Exception ex) {
                throw ex;
            }

            throw exp;
        }
        
    	return checker;
    }
    
    /**
     * 서비스 배치를 실행한다.
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "unused", "unchecked" })
	public Boolean serviceBatch(Map map) throws Exception {
    	String serverStatus = StringHelper.null2string(map.get("CURRENT_STAT"), "R"); // R:기동, S:중지, E:장애
    	String snd_rev_type = StringHelper.null2void(map.get("INTERFACE_MTH")); // 데이터 송/수신(R:Receive, S:Send)
    	String process_type = StringHelper.null2void(map.get("PROCESS_TYPE")); // 처리방식 : Siebel(I), SOAP(S), HTTP(H), SMTP(T), FTP(F), JCO(J), Bypass(B), Procedure(P)
    	
    	log.debug("transaction information = " + map.toString());
    	
    	Jco jco = ParamReader.getJcoParameter(batchVo.getJcoId());;
    	boolean result = true;
    	int rstCnt = 0;
    	String message = "";
    	BatchSupporter bs = new BatchSupporter(batchVo);
    	
    	try {
    		// 서버 접속상태 체크(서버 사용여부가 중지 또는 장애인 경우에는 하위 로직을 수행하지 않도록 함)
    		if(!serverStatus.equals("R")) {
    			return false;
    		}
    		
    		ParameterVo pvo = batchVo.getParameter();
    		long data_size = 0;
    		
	    	// 수신서버 작업
	    	if(snd_rev_type.equals("R")) {
	    		Map<String, Object> res_data = null;
	    		
		    	// 1.요청 파라메터 클래스 호출(클래스 호출)
	    		//   - 서비스 타입이 R(Realtime)인 경우에는 com.yni.rs.batch.서비스ID.receive.Import.getParameter() 매소드 호출
	    		Object impParam = bs.callMethod("Import", "getParameter", map, pvo.getMap()); // 클래스명, 매소드명, 배치정보, 서비스 요청 파라미터
	    		
	    		if(impParam == null) {
	    			impParam = pvo.getMap();
	    		} else {
	    			log.debug("Apply to user customizing import parameter.");
	    		}
	    		
	    		if(impParam != null) log.debug("Class type = " + impParam.getClass() + ", address = " + impParam.hashCode());
	    		log.debug("Process type = " + process_type);
	    		log.debug("call method parameter(before) = " + impParam);
	    		
	    		// 2.파라메터 데이터를 JCO XML에 설정된 정보와 맵핑하고 DB에 이력 등록
	    		Object iobj = null;
	    		
	    		if(jco != null && impParam instanceof Map) {
	    			iobj = bs.getImportPrameter(batchVo, (Map) impParam);
	    		} else {
	    			iobj = impParam;
	    		}
	    		
	    		if(impParam.hashCode() == iobj.hashCode()) {
	    			log.debug("call method parameter(after) parameter = No change...");
	    		} else {
	    			log.debug("call method parameter(after) parameter = " + iobj);
	    		}
	    		
	    		batchVo.setImportData(iobj); // import할 파리메터 저장
	    		
	    		data_size += ObjectSupporter.getObjectSize(iobj); // 요청 데이터 크기
	    		
	    		// 3.서버 통신방법에 따라 데이터 요청(bypass는 제외)
	    		if(!process_type.equals("B")) {
		    		boolean cnnrst = true;
		    		
		    		if(jco != null) {
						cnnrst = bs.receive(batchVo, pvo, map); // DBA객체, 배치VO, 사용자 파라메터, Import 파라메터, 배치정보
					} else {
						bs.callMethod("Import", "executeBatch", map, batchVo);
						
						if(batchVo.getBatchStatus().equals("E")) {
							cnnrst = false;
						}
					}
		    		
		    		// 이력 데이터 업데이트
		    		Map resMap = new HashMap();
		    		resMap.put("INTERFACE_HISTORY_ID", batchVo.getTransId());
		    		
		    		try {
		    			Object robj = batchVo.getReturnData();
		    			
		    			if(iobj != null) resMap.put("REQUEST_PARAM", iobj.toString()); // 수신측 요청 파라메터(요청측 데이터 원본 - 문자열)
		    			if(robj != null) resMap.put("RECEIVE_PARAM", robj.toString()); // 수신측 응답 데이터(Json타입의 데이터 - 문자열)
		    		} catch(Exception e) {
		    			log.error(e.getMessage());
		    		}
		    		
		    		batchTarget.updateProcedureResult(resMap);
		    		
		    		if(cnnrst) {
		    			res_data = DataConvertUtil.getObjectToMap(batchVo.getReturnData());
		    		} else {
		    			return false;
		    		}
		    		
		    		log.debug("response data = " + res_data);
		    		
		    		// 4.결과 수신
		    		Map emap = null;
		    		
		    		if(jco != null) emap = bs.getExportParameter(batchVo, res_data);
		    		else emap = res_data;
		    		
		    		emap.put("INTERFACE_MTH", snd_rev_type);
		    		emap.put("SERVICE_ID", map.get("SERVICE_ID"));
		    		
		    		// 5.요청 파라메터 클래스 호출(클래스 호출)
		    		Map expParam = (Map) bs.callMethod("Export", "getParameter", map, emap); // 클래스명, 매소드명, 배치정보, 파라미터
		    		
		    		if(expParam == null || expParam.size() == 0) {
		    			expParam = emap;
		    		} else {
		    			log.debug("Apply to user customizing export parameter.");
		    		}
		    		
		    		log.debug("result message = " + expParam.toString());
		    		
		    		String ercode = StringHelper.null2void(expParam.get("BATCH_STATUS")); // E:에러, S:성공, N:데이터 없음
		    		String ermsg = StringHelper.null2void(expParam.get("ERROR_MESSAGE"));
		    		
		    		if(ercode.equals("E")) {
		    			batchVo.setErrorMessage(ermsg);
		    			return false;
		    		}
	    		}
	    		
	    		// 6.결과데이터 맵핑(데이터 유효성 체크 포함)
	    		Map rmap = null;
	    		if(jco != null) rmap = bs.getTableData(batchVo, res_data);
	    		else rmap = res_data;
	    				
	    		if(rmap != null) data_size += ObjectSupporter.getObjectSize(rmap); // 결과 데이터 크기
	    		
	    		log.debug("result table size = " + data_size + ", data = " + rmap.toString());
	    		
	    		// 7.이력 테이블에 저장(중계서버에는 사용하지 않음)
//	    		int cnt = 0;
//	    		
//	    		if (rmap != null) {
//	    			cnt = bs.insertTableData(batchTarget, batchVo, rmap);
//	            }
	    		
	    		batchVo.setTotalRows(Long.toString(data_size)); // 데이터 크기(byte)
	    		batchVo.setSubmitStatus(snd_rev_type); // 통신유형
	    		
	    		// 8.전체건수 업데이트
	    		batchTarget.updateJcoCountData(batchVo);
	    	}
	    	
	    	// 송신서버 작업
	    	if(snd_rev_type.equals("S")) {
	    		// 1.데이터 추출(서비스 ID로 연결된 인터페이스 이력 테이블에서 테이블명(INTERFACE_HISTORY_DATA_ID)과 import파라메터 name이 같은 데이터를 조회함)
				boolean rst = bs.getInterfaceData(batchTarget, batchVo, map);
				
				if(!rst) {
					return false;
				}
				
				// 2.데이터 추가 가공을 위한 클래스 호출(개발자 수행)
				Object impParam = bs.callMethod("Import", "getParameter", map, pvo.getMap()); // 클래스명, 매소드명, 배치정보, 파라미터+전송할 데이터
	    		
				if(impParam == null) {
	    			impParam = pvo.getMap();
	    		} else {
	    			log.debug("Apply to user customizing import parameter.");
	    		}
				
				log.debug("send finally data = " + impParam);
				
				batchVo.setImportData(impParam); // import할 파리메터 저장
				batchVo.setTotalRows(Long.toString(ObjectSupporter.getObjectSize(impParam)));
				
	    		// 4.데이터 전송
				boolean cnnrst = true;
				
				if(jco != null) {
					cnnrst = bs.sendByJCO(batchTarget, batchVo, batchVo.getParameter().getMap(), map);
				} else {
					bs.callMethod("Import", "executeBatch", map, batchVo);
					
					if(batchVo.getBatchStatus().equals("E")) {
						cnnrst = false;
					}
				}
				
				// 이력 데이터 업데이트
	    		Map resMap = new HashMap();
	    		resMap.put("INTERFACE_HISTORY_ID", batchVo.getTransId());
	    		
	    		try {
	    			Object robj = batchVo.getReturnData();
	    			
	    			if(impParam != null) resMap.put("REQUEST_PARAM", impParam.toString()); // 수신측 요청 파라메터(요청측 데이터 원본 - 문자열)
	    			if(robj != null) resMap.put("RECEIVE_PARAM", robj.toString()); // 수신측 응답 데이터(Json타입의 데이터 - 문자열)
	    		} catch(Exception e) {
	    			log.error(e.getMessage());
	    		}
	    		
	    		batchTarget.updateProcedureResult(resMap);
	    		
	    		// 5.전송결과 실패인 경우 하위 로직은 수행하지 않음
				if(!cnnrst) {
	    			return false;
	    		}
				
				// Map expParam = (Map) bs.callMethod("Export", "getParameter", map, emap); // 클래스명, 매소드명, 배치정보, 파라미터
				
				// 6.전체건수 업데이트
	    		batchTarget.updateJcoCountData(batchVo);
	    	}
    	} catch (Exception exp) {
    		result = false;
    		message = exp.getMessage();
    		
    		batchVo.setErrorMessage(exp.getMessage());
        }
    	
    	logger.logMessage("result message = " + message);
    	
    	return result;
    }
    
    /**
     * 사용자 인증
     * 
     * @return 인증결과 및 사용자 정보
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    protected List userCertify(Map<String, Object> map) throws Exception {
        List<Map> resultList = batchTarget.checkMemberInfo(map);

        if (resultList == null || resultList.size() < 1) {
            logger.logMessage("[ERROR] User authentication failed. The reason is empty user's information.");
            throw new Exception("User authentication failed. user info is null");
        }

        return resultList;
    }

    /**
     * 수행할 인터페이스 목록 조회 get Schedule-Interface Mapping info(schedule_code, company_code, if_code, if_parent_code)
     * 
     * @param map 검색조건
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    protected List getInterfaceMappingList(Map map) throws Exception {
        return batchTarget.selectInterfaceMappingList(map);
    }

    /**
     * 인터페이스 실행 전에 우선 실행할 상위 인터페이스가 있는지 확인한 후 본 인터페이스를 실행한다.
     * 
     * @param jobVo 스케쥴 공통 파라메터 & 이전에 수행된 배치정보
     * @param batchVo 현재 실행중인 배치정보
     * @return 실행 결과
     */
    protected boolean batchRunable(JobVo jobVo, BatchVo batchVo) throws Exception {
        boolean execResult = true;

        String if_parent = batchVo.getParentCode();

        if (if_parent != null && !if_parent.isEmpty()) {
            boolean extCode = true;
            List<BatchVo> batchList = jobVo.getBatchList();

            // 인터페이스 실행 전 상위 인터페이스가 있다면 실행 결과를 체크하여 성공했을 경우 본 인터페이스를 실행한다.
            for (int k = 0; k < batchList.size(); k++) {
                BatchVo completeVo = batchList.get(k);

                if (completeVo.getInterfaceCode() != null) {
                    // 상위 인터페이스의 실행결과를 확인한 후 실행
                    if (if_parent.equals(completeVo.getInterfaceCode())) {
                        if (!"E".equals(completeVo.getBatchStatus())) {
                            logger.logMessage("Import Map Data(IF_CD:" + batchVo.getInterfaceCode() + " / IF_PARENT_CD:" + if_parent + ")");
                        } else {
                            execResult = false;
                            logger.logMessage("Parent interface is failed.");

                            break;
                        }
                    }
                }
            }

            if (extCode) {
                logger.logMessage("Fieled Parent interface does not exist.");
            }
        } else {
            logger.logMessage("Import Map Data(IF_CD:" + batchVo.getInterfaceCode() + " / IF_PARENT_CD:" + if_parent + ")");
        }

        return execResult;
    }

    /**
     * 배치수행일자를 업데이터한다.
     * 
     * @param map 검색조건
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    protected void updateInterfaceScheduleDate(Map map) throws Exception {
        batchTarget.updateInterfaceScheduleDate(map);
    }

    /**
     * 배치 실행중 오류로 인해 생성된 메시지와 상태를 업데이트한다.
     * 
     * @param map 검색조건
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    protected void updateExceptionResult(Map map) throws Exception {
        batchTarget.updateProcedureResult(map);
    }

    /**
     * 배치가 완료된 시간을 업데이트한다.
     * 
     * @param map 검색조건
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    protected void updateFinishedTime(Map map) throws Exception {
        batchTarget.updateFinishedTime(map);
    }

    /**
     * 배치 실행할 기준년월을 조회한다.
     * 
     * @param map
     * @return 시스템 설정정보
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    protected List<Object> selectBatchYYYYMM(Map map) throws Exception {
        return batchTarget.selectBatchYYYYMM(map);
    }

    protected abstract boolean applyBatch() throws Exception;
    
}
