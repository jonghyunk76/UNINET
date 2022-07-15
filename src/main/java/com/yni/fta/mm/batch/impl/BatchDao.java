package com.yni.fta.mm.batch.impl;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yni.fta.common.Consistent;
import com.yni.fta.common.postgresql.PostgresqlDao;

import kr.yni.frame.dao.YniAbstractDAO;
import kr.yni.frame.util.StringHelper;

@Repository("batchDao")
public class BatchDao extends YniAbstractDAO {
    
	public BatchDao() { }
	
	/**
     * 배치시작시간 업데이트
     * 
     * @param map
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes" })
    public int updateScheduleBatchDate(Map map) throws Exception {
        int returnValue = update("MMBATCH.updateScheduleBatchDate", map);

        return returnValue;
    }
	
    /**
     * 인터페이스 마스터키를 조회하는 매소드
     * 
     * @return
     * @throws Exception
     */
    public String selectTransKey() throws Exception {
        String returnValue = StringHelper.null2void(selectByPk("MMBATCH.selectTranID"));
        
        return returnValue;
    }

    /**
     * 데이터 이관 마스터 정보 셋팅
     * 
     * @param map
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes" })
    public int insertTransInfoData(Map map) throws Exception {
    	int returnValue;
    	Connection conn = null;
    	try {
	    	conn = super.getConnection();
	    	conn.setAutoCommit(false);
	    	returnValue = update("MMBATCH.insertTransInfoData", map);
	        conn.commit(); 
    	} catch (Exception exp) {
            log.debug(exp);
            conn.rollback();
            throw exp;
        } finally {
            if (conn != null) {
            	conn.close();
            }
        }
        
        return returnValue;
    }

    /**
     * ERP데이터에서 추출한 데이터 건수 업데이트
     * 
     * @param map
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes" })
    public int updateJcoCountData(Map map) throws Exception {
        int returnValue = update("MMBATCH.updateJcoCountData", map);
        
        return returnValue;
    }

    /**
     * 인터페이스할 데이터를 전송테이블에 MIGRATION한다.
     * 
     * @param map
     * @return
     * @throws Exception
     */
    @Transactional
    public int insertTransDtlData(final List<Object> colList) throws Exception {
        int returnValue = executeBatch("MMBATCH.insertTransDtlData", colList);
        
        return returnValue;
    }
    
    /**
     * 인터페이스할 데이터를 전송테이블에 직접 MIGRATION한다.
     * 
     * @param map
     * @return
     * @throws Exception
     */
    @Transactional
    public int insertDirectToTargetData(Map map) throws Exception {
        return this.update("MMBATCH.insertDirectToTargetData", map);
    }
    
    /**
     * 이관결과 업데이트
     * 
     * @param map
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes" })
    public int updateTransInfoData(Map map) throws Exception {
        int returnValue = update("MMBATCH.updateTransInfoData", map);

        return returnValue;
    }

    /**
     * 검증할 테이블및 컬럼, 속성 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Object> selectSchedulerItemList(Map map) throws Exception {
        List<Object> returnValue = list("MMBATCH.selectSchedulerItemList", map);

        return returnValue;
    }
    
    /**
     * 항목 마스터의 상세정보 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Map<String, Object> selectSchedulerItemDetail(Map map) throws Exception {
        Map returnValue = (Map) this.selectByPk("MMBATCH.selectSchedulerItemDetail", map);

        return returnValue;
    }
    
    /**
     * 이력 테이블에 있는 데이터를 이관 대상 테이블로 이관<BR> 
     * - 이력 테이블에 에러가 0개인 경우 데이터 이관
     * 
     * @param map
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes" })
    public int insertToTargetData(Map map) throws Exception {
        int returnValue = update("MMBATCH.insertToTargetData", map);
        
        return returnValue;
    }
    
    /**
     * 소스 테이블에 있는 데이터를 이관 대상 테이블로 이관<BR> 
     * - 이력 테이블에 에러가 0개인 경우 데이터 이관
     * 
     * @param map
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes" })
    public int insertTargetFromSourceData(Map map) throws Exception {
        int returnValue = update("MMBATCH.insertTargetFromSourceData", map);
        
        return returnValue;
    }
    
    /**
     * 스케쥴정보 조회
     * 
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Object> selectInterfaceScheduleList(Map map) throws Exception {
        List<Object> returnValue = list("MMBATCH.selectInterfaceScheduleList", map);
        
        return returnValue;
    }

    /**
     * 스케쥴-인터페이스 매핑 정보 조회
     * 
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Object> selectInterfaceMappingList(Map map) throws Exception {
        List<Object> returnValue = list("MMBATCH.selectInterfaceMappingList", map);
        
        return returnValue;
    }
    
    /**
     * 스케쥴 서비스 목록 조회
     * 
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Object> selectServiceMappingList(Map map) throws Exception {
        List<Object> returnValue = list("RSBATCH.selectServiceMappingList", map);
        
        return returnValue;
    }
    
    /**
     * 서비스 Plan 목록 조회
     * 
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Object> selectServicePlanList(Map map) throws Exception {
        List<Object> returnValue = list("RSBATCH.selectServicePlanList", map);
        
        return returnValue;
    }
    
    /**
     * 서비스 이력Key를 조회하는 매소드
     * 
     * @return
     * @throws Exception
     */
    public String selectServiceTranID() throws Exception {
        String returnValue = StringHelper.null2void(selectByPk("RSBATCH.selectServiceTranID"));
        
        return returnValue;
    }
    
    /**
     * 서비스 이력 등록
     * 
     * @param map
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes" })
    public int insertServiceHistory(Map map) throws Exception {
    	int returnValue;
    	Connection conn = null;
    	try {
	    	conn = super.getConnection();
	    	conn.setAutoCommit(false);
	    	returnValue = update("RSBATCH.insertServiceHistory", map);
	        conn.commit(); 
    	} catch (Exception exp) {
            log.debug(exp);
            conn.rollback();
            throw exp;
        } finally {
            if (conn != null) {
            	conn.close();
            }
        }
        
        return returnValue;
    }
    
    /**
     * 서비스 이력정보 업데이트
     * 
     * @param map
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes" })
    public int updateServiceHistoryStatus(Map map) throws Exception {
        int returnValue = update("RSBATCH.updateServiceHistoryStatus", map);
        
        return returnValue;
    }
    
    /**
     * 서비스 이력을 이용한 재전송 정보 초기화
     * 
     * @param map
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes" })
    public int updateServiceResetHistory(Map map) throws Exception {
        int returnValue = update("RSBATCH.updateServiceResetHistory", map);
        
        return returnValue;
    }
    
    /**
     * 송신할 데이터 인터페이스 이력에서 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List<Object> selectSendDataList(Map map) throws Exception {
    	int pidx = StringHelper.null2zero(map.get("PAGE_INDEX")); // 1부터 시작
    	int psize = StringHelper.null2zero(map.get("PAGE_SIZE"));
    	
    	if(psize > 0) {
    		return this.listWithPaging("RSBATCH.selectSendDataList", map, pidx, psize);
    	} else {
    		return this.list("RSBATCH.selectSendDataList", map);
    	}
    }
    
    /**
     * 인터페이스 될 배치항목을 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List<Object> selectInterfaceBatchItemList(Map map) throws Exception {
        return list("MMBATCH.selectInterfaceBatchItemList", map);
    }
    
    /**
     * 인터페이스 중에 실패가 발생하면 이와 관련된 인터페이스 테이블의 모든 데이터를 삭제한다.
     * 
     * @param map
     * @return 성공시 0을 리턴한다.
     * @throws Exception
     */
    @Transactional
    public int deleteInterfaceTable(final List<Object> colList) throws Exception {
        int returnValue = executeBatch("MMBATCH.deleteInterfaceTable", colList);
        
        return returnValue;
    }

    /**
     * 이관결과 업데이트
     * 
     * @param map
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes" })
    public int updateInterfaceScheduleDate(Map map) throws Exception {
        int returnValue = update("MMBATCH.updateInterfaceScheduleDate", map);
        
        return returnValue;
    }

    /**
     * 배치 실행결과를 리턴
     * 
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Object> selectBatchResultInfo(Map map) throws Exception {
        List<Object> returnValue = list("MMBATCH.selectBatchResultInfo", map);

        return returnValue;
    }

    /**
     * 마감업무를 담당하는 프로시져 호출
     * 
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public Map executeProcedureProcess(Map map) throws Exception {
    	Map<String, Object> returnMap = new HashMap<String, Object>();
    	
    	String func = StringHelper.null2void(map.get(Consistent.IF_PARAMETER_FUNCTION_NAME));    	
    	String db = StringHelper.null2void(this.properties.get("application.db.type"));
    	
    	if(func.toUpperCase().endsWith("USPINSERTFCR")) { // 원산지 판정 프로시져 실행
    		String rst = "-1";
    		String yyyymm = StringHelper.null2void(map.get("FROM_DATE"));
    		
    		if(yyyymm.length() >= 6) {
	    		map.put("YYYYMM", yyyymm.substring(0, 6));
	    		
	    		if (db.equals("POSTGRESQL")) {
	    			String ERROR_MESSAGE = "";
	            	PostgresqlDao postgre = new PostgresqlDao();
	            	
	            	ERROR_MESSAGE = postgre.updateMainInfo(map);
	            	
	            	map.put("ERROR_MESSAGE", ERROR_MESSAGE);
	    		} else {
	    			executeProcedure("MMBATCH.executeFCRProcedureProcess", map);
	    		}
	        	
	        	rst = StringHelper.null2void(map.get("ERROR_MESSAGE"));
    		}
        	
    		if(log.isDebugEnabled()) log.debug("Batch Ststus in Procedure = " + map.toString());
    		
        	if(rst.equals("0")) {
    	        returnMap.put("O_LINES", "0");  		// 처리한 레코드 수
    	        returnMap.put("O_RETURN", "S"); 		// 처리결과(S(성공),E(에러),N(데이터 없음))
    	        returnMap.put("O_RETURNMSG", ""); 		// 처리결과 메시지
        	} else {
        		returnMap.put("O_LINES", "0");  		// 처리한 레코드 수
    	        returnMap.put("O_RETURN", "E"); 		// 처리결과(S(성공),E(에러),N(데이터 없음))
    	        returnMap.put("O_RETURNMSG", StringHelper.null2void(map.get("ERROR_MESSAGE"))); 	// 처리결과 메시지
        	}
        	
            return returnMap;
    	} else if(func.toUpperCase().endsWith("INVINSERTFCR")) { // 개별법 원산지 판정 프로시져 실행
    		String rst = "-1";
    		String form = StringHelper.null2void(map.get("FROM_DATE"));
    		
    		if(form.length() >= 6) {
	    		if (db.equals("POSTGRESQL")) {
	    			String ERROR_MESSAGE = "";
	            	PostgresqlDao postgre = new PostgresqlDao();
	            	
	            	ERROR_MESSAGE = postgre.executeInvoiceFCRProcedureProcess(map);
	            	
	            	map.put("ERROR_MESSAGE", ERROR_MESSAGE);
	    		} else {
	    			executeProcedure("MMBATCH.executeInvoiceFCRProcedureProcess", map);
	    		}
	        	
	        	rst = StringHelper.null2void(map.get("ERROR_MESSAGE"));
    		}
        	
    		if(log.isDebugEnabled()) log.debug("Batch Ststus in Procedure(By invoice) = " + map.toString());
    		
        	if(rst.equals("0")) {
    	        returnMap.put("O_LINES", "0");  		// 처리한 레코드 수
    	        returnMap.put("O_RETURN", "S"); 		// 처리결과(S(성공),E(에러),N(데이터 없음))
    	        returnMap.put("O_RETURNMSG", ""); 		// 처리결과 메시지
        	} else {
        		returnMap.put("O_LINES", "0");  		// 처리한 레코드 수
    	        returnMap.put("O_RETURN", "E"); 		// 처리결과(S(성공),E(에러),N(데이터 없음))
    	        returnMap.put("O_RETURNMSG", StringHelper.null2void(map.get("ERROR_MESSAGE"))); 	// 처리결과 메시지
        	}
        	
            return returnMap;
    	} else {
    		log.info("> Procedure parameter info = " + map.toString());
    		// 프로시져 수행
    		if(func.toUpperCase().startsWith("SEND_")) {
    			if(db.equals("POSTGRESQL")) {
	            	PostgresqlDao postgre = new PostgresqlDao();
	            	postgre.executeErpSenderProcess(map);
	    		} else {
	    			executeProcedure("MMBATCH.executeErpSenderProcess", map);
	    		}
    		} else {
	    		if(db.equals("POSTGRESQL")) {
	            	PostgresqlDao postgre = new PostgresqlDao();
	            	postgre.executeProcedureProcess(map);
	    		} else {
	    			executeProcedure("MMBATCH.executeProcedureProcess", map);
	    		}
    		}
    		
    		returnMap.put("O_LINES", StringHelper.null2void(map.get("O_LINES")));  		// 처리한 레코드 수
            returnMap.put("O_RETURN", StringHelper.null2void(map.get("O_RETURN"))); 	// 처리결과(S(성공),E(에러),N(데이터 없음))
            
            if(StringHelper.null2void(map.get("O_RETURN")).equals("S")) {
            	returnMap.put("O_RETURNMSG", ""); 	// 처리결과 메시지
            } else {
            	returnMap.put("O_RETURNMSG", StringHelper.null2void(map.get("O_RETURNMSG"))); 	// 처리결과 메시지
            }
    	}
    	
        return returnMap;
    }
    
    /**
     * Procedure 수행 후 실패한 경우 전송마스터에 실패 메시지로 업데이트한다.
     * 
     * @param map
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes" })
    public int updateProcedureResult(Map map) throws Exception {
        int returnValue = update("MMBATCH.updateProcedureResult", map);
        
        return returnValue;
    }

    /**
     * 마지막 완료된 시간을 기록한다.
     * 
     * @param map
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes" })
    public int updateFinishedTime(Map map) throws Exception {
        int returnValue = update("MMBATCH.updateFinishedTime", map);

        return returnValue;
    }
    
    /**
     * 원산지 수취 완료 대상 조회
     * 
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Object> selectCooCertifyList(Map map) throws Exception {
        List<Object> returnValue = list("MMBATCH.selectCooCertifyList", map);
        
        return returnValue;
    }
    
    /**
     * 배치처리결과를 조회한다.
     * 
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Object> selectResultMessage(Map map) throws Exception {
        List<Object> returnValue = list("MMBATCH.selectResultMessage", map);
        
        return returnValue;
    }
    
    /**
     * 배치 실행할 기준년월을 조회한다.
     * 
     * @param map
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Object> selectBatchYYYYMM(Map map) throws Exception {
        List<Object> returnValue = list("MMBATCH.selectBatchYYYYMM", map);
        
        return returnValue;
    }
    
    /**
     * 배치 실행할 기준년월을 조회한다.
     * 
     * @param map
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Object> selectInterfaceSchedulerDetail(Map map) throws Exception {
        List<Object> returnValue = list("MMBATCH.selectInterfaceSchedulerDetail", map);
        
        return returnValue;
    }
    
    /**
     * 배치 실행할 사업부를 조회한다.
     * 
     * @param map
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Object> selectDivisionList(Map map) throws Exception {
        List<Object> returnValue = list("MMBATCH.selectDivisionList", map);
        
        return returnValue;
    }
    
    /**
     * 현대차 연계 조직별 ID구하기
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List<Object> selectHubOrganizatList(Map map) throws Exception {
        List<Object> returnValue = list("MMBATCH.selectHubOrganizatList", map);
        
        return returnValue;
    }
    
    /**
     * 송신된 제출서중 처리되지 않은 목록 구하기
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List<Object> selectHubNoComplateList(Map map) throws Exception {
        List<Object> returnValue = list("MMBATCH.selectHubNoComplateList", map);
        
        return returnValue;
    }
    
    /**
     * 자동메일발송 예약리스트 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List<Object> selectMailSendBatchList(Map map) throws Exception {
        List<Object> returnValue = list("MMBATCH.selectMailSendBatchList", map);
        
        return returnValue;
    }
    
    /**
	 * 협력사 원산지 요청 품목 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
    public List selectOrderInfoDtlList(Map map) throws Exception {
		return list("DI3209.selectOrderInfoDtlList", map);
	}
    
    /**
	 * 메일발송 이력 마스터 정보 등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public String insertMailMst(Map map) throws Exception {
		String mailInfo = (String)insert("mmMail.insertMailMst", map);
		
        return mailInfo;
	}
	
	/**
	 * 메일발송 시 첨부할 파일 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
    public List selectMailSendFileList(Map map) throws Exception {
		return list("mmMail.selectMailSendFileList", map);
	}
    
	/**
	 * 메일발송이력 협력사 정보 등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertMailVendor(Map map) throws Exception {
		return update("mmMail.insertMailVendor", map);
	}
	
	/**
	 * 메일발송 품목리스트 등록
	 * 
	 * @param list
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertMailVendorItem(List list, Map map) throws Exception {
		return executeBatch("mmMail.insertMailVendorItem", list, map);
	}
	
	/**
	 * 문자발송 정보 등록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertSendSMS(Map map) throws Exception {
		return update("mmMail.insertSendSMS", map);
	}
	
	/**
	 * 메일에러 업데이트
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateMail(Map map) throws Exception {
		return update("mmMail.updateMail", map);
	}
	
	/**
	 * 자동메일발송 처리이력 등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateMailSendHistoryInfo(Map map) throws Exception {
		return update("mmMail.updateMailSendHistoryInfo", map);
	}
	
	/**
	 * 자동메일발송 정보 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map selectMailNextScheduleInfo(Map map) throws Exception {
		return (Map) selectByPk("mmMail.selectMailNextScheduleInfo", map);
	}
	
	/**
	 * 다음 자동메일발송 이력 등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertMailSendHistoryInfo(Map map) throws Exception {
		return update("mmMail.insertMailSendHistoryInfo", map);
	}
	
	/**
     * 인터페이스 소스테이블 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List<Object> selectSourceTable(Map map) throws Exception {
        List<Object> returnValue = list("MMBATCH.selectSourceTable", map);

        return returnValue;
    }
    
    /**
     * FTA PASS와 인터페이스할 대상정보 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List<Object> selectFtaPassDoList(Map map) throws Exception {
        List<Object> returnValue = list("MMBATCH.selectFtaPassDoList", map);

        return returnValue;
    }
	
    /**
     * FTA PASS 요청 상태 수정
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int updatePassReqStatus(Map map) throws Exception {
        return this.update("MMBATCH.updatePassReqStatus", map);
    }
    
}
