package com.yni.fta.common.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

import com.yni.fta.common.Consistent;

import kr.yni.frame.Constants;
import kr.yni.frame.context.ApplicationContextFactory;
import kr.yni.frame.exception.FrameException;
import kr.yni.frame.util.StringHelper;

public class PostgresqlDao{

	private static Log log = LogFactory.getLog(PostgresqlDao.class);
	
	/**
	 * 원산지 판정 실행
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public String updateMainInfo(Map map) throws Exception {
		String ERROR_MESSAGE = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = this.getConnection();
			
			String companyCd = StringHelper.null2void(map.get("COMPANY_CD"));
			String yyyyMm = StringHelper.null2void(map.get("YYYYMM"));
			String salesMgmtNo = StringHelper.null2void(map.get("SALES_MGMT_NO"));
			
			String callQuery = "CALL uspInsertFCR('"+companyCd+"', '"+yyyyMm+"', '"+salesMgmtNo+"', '')";
			ps = conn.prepareStatement(callQuery);

			if(log.isDebugEnabled()) log.debug("Call procedure : " + callQuery);

			rs = ps.executeQuery();
			rs.next();
			ERROR_MESSAGE = rs.getString(1);
			
			if(log.isDebugEnabled()) log.debug("result(unsettledInsertFCR) = " + ERROR_MESSAGE);
		} catch (SQLException e) {
			throw new FrameException(e.getMessage());
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}			
			if (conn != null) {
				conn.close();
			}
		}		
		return ERROR_MESSAGE;	
	}
	
	/**
	 * 개별법 원산지 판정 실행
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public String executeInvoiceFCRProcedureProcess(Map map) throws Exception {
		String ERROR_MESSAGE = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = this.getConnection();
			
			String companyCd = StringHelper.null2void(map.get("COMPANY_CD"));
			String form = StringHelper.null2void(map.get("FROM_DATE"));
    		String to = StringHelper.null2void(map.get("TO_DATE"));
			String salesMgmtNo = StringHelper.null2void(map.get("SALES_MGMT_NO"));
			
			String callQuery = "CALL INVINSERTFCR('"+companyCd+"', '"+form+"', '"+to+"', '"+salesMgmtNo+"', '')";
			ps = conn.prepareStatement(callQuery);

			if(log.isDebugEnabled()) log.debug("Call procedure : " + callQuery);

			rs = ps.executeQuery();
			rs.next();
			ERROR_MESSAGE = rs.getString(1);
			
			if(log.isDebugEnabled()) log.debug("result(INVINSERTFCR) = " + ERROR_MESSAGE);
		} catch (SQLException e) {
			throw new FrameException(e.getMessage());
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}			
			if (conn != null) {
				conn.close();
			}
		}		
		return ERROR_MESSAGE;	
	}
	
	/**
	 * 개별법(인보이스) 원산지 판정 실행
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public String invoiceInsertFCR(Map map) throws Exception {
		String ERROR_MESSAGE = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = this.getConnection();
			
			String companyCd = StringHelper.null2void(map.get("COMPANY_CD"));
			String fromDate = StringHelper.null2void(map.get("FROM_DATE"));
			String toDate = StringHelper.null2void(map.get("TO_DATE"));
			String salesMgmtNo = StringHelper.null2void(map.get("SALES_MGMT_NO"));
			
			String callQuery = "CALL invInsertFCR('"+companyCd+"', '"+fromDate+"', '"+toDate+"', '"+salesMgmtNo+"', '')";
			ps = conn.prepareStatement(callQuery);

			if(log.isDebugEnabled()) log.debug("Call procedure : " + callQuery);

			rs = ps.executeQuery();
			rs.next();
			ERROR_MESSAGE = rs.getString(1);
			
			if(log.isDebugEnabled()) log.debug("result(invInsertFCR) = " + ERROR_MESSAGE);
		} catch (SQLException e) {
			throw new FrameException(e.getMessage());
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}			
			if (conn != null) {
				conn.close();
			}
		}
		
		return ERROR_MESSAGE;	
	}
	
	/**
	 * RP(Remote Procedure) call
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map callRemoteProcedure(Map map) throws Exception {
		String BATCH_STATUS = null;
		String ERROR_MESSAGE = null;
		int TOTAL_ROWS;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = this.getConnection();
			
			//conn.setAutoCommit(false);
			String func = StringHelper.null2void(map.get(Consistent.IF_PARAMETER_FUNCTION_NAME));  
			String companyCd = StringHelper.null2void(map.get("COMPANY_CD"));
			String fromDate = StringHelper.null2void(map.get("FROM_DATE"));
			String toDate = StringHelper.null2void(map.get("TO_DATE"));
			
			String callQuery = "CALL "+func+"('"+companyCd+"', '"+fromDate+"', '"+toDate+"', '', '', 0)";
			ps = conn.prepareStatement(callQuery);

			if(log.isDebugEnabled()) log.debug("Call procedure : " + callQuery);

			rs = ps.executeQuery();
			rs.next();
			BATCH_STATUS = rs.getString(1);
			ERROR_MESSAGE = rs.getString(2);
			TOTAL_ROWS = rs.getInt(3);
			map.put("BATCH_STATUS", BATCH_STATUS);
			map.put("ERROR_MESSAGE", ERROR_MESSAGE);
			map.put("TOTAL_ROWS", TOTAL_ROWS);

			//conn.commit();
			 		
		} catch (SQLException e) {
			throw new FrameException(e.getMessage());
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}			
			if (conn != null) {
				conn.close();
			}
		}		
		return map;	
	}	
	
	/**
	 * 마감업무를 담당하는 프로시져 호출 executeProcedureProcess 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map executeProcedureProcess(Map map) throws Exception {
		String O_RETURN = null;
		String O_RETURNMSG = null;
		int O_LINES;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = this.getConnection();
			
			//conn.setAutoCommit(false);
			String func = StringHelper.null2void(map.get(Consistent.IF_PARAMETER_FUNCTION_NAME));  
			String companyCd = StringHelper.null2void(map.get("COMPANY_CD"));
			String fromDate = StringHelper.null2void(map.get("FROM_DATE"));
			String toDate = StringHelper.null2void(map.get("TO_DATE"));
			String ifCd = StringHelper.null2void(map.get("IF_CD"));
			
			String callQuery = "CALL "+func+"('"+companyCd+"', '"+fromDate+"', '"+toDate+"', '"+ifCd+"', '', '', 0)";
			ps = conn.prepareStatement(callQuery);

			if(log.isDebugEnabled()) log.debug("Call procedure : " + callQuery);

			rs = ps.executeQuery();
			rs.next();
			O_RETURN = rs.getString(1);
			O_RETURNMSG = rs.getString(2);
			O_LINES = rs.getInt(3);
			map.put("O_RETURN", O_RETURN);
			map.put("O_RETURNMSG", O_RETURNMSG);
			map.put("O_LINES", O_LINES);
			
			//conn.commit();
			 		
		} catch (SQLException e) {
			throw new FrameException(e.getMessage());
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}			
			if (conn != null) {
				conn.close();
			}
		}		
		return map;	
	}	
	
	/**
	 * 마감업무를 담당하는 프로시져 호출 executeErpSenderProcess 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map executeErpSenderProcess(Map map) throws Exception {
		String O_RETURN = null;
		String O_RETURNMSG = null;
		int O_LINES;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = this.getConnection();
			
			//conn.setAutoCommit(false);
			String func = StringHelper.null2void(map.get(Consistent.IF_PARAMETER_FUNCTION_NAME));  
			String companyCd = StringHelper.null2void(map.get("COMPANY_CD"));
			String fromDate = StringHelper.null2void(map.get("FROM_DATE"));
			String toDate = StringHelper.null2void(map.get("TO_DATE"));
			int tranNo = Integer.parseInt(StringHelper.null2void(map.get(Consistent.IF_PARAMETER_TRANS_ID)));
			
			String callQuery = "CALL "+func+"('"+companyCd+"', '"+fromDate+"', '"+toDate+"', "+tranNo+", '', '', 0)";
			ps = conn.prepareStatement(callQuery);

			if(log.isDebugEnabled()) log.debug("Call procedure : " + callQuery);

			rs = ps.executeQuery();
			rs.next();
			O_RETURN = rs.getString(1);
			O_RETURNMSG = rs.getString(2);
			O_LINES = rs.getInt(3);
			map.put("O_RETURN", O_RETURN);
			map.put("O_RETURNMSG", O_RETURNMSG);
			map.put("O_LINES", O_LINES);
			
			//conn.commit();
			 		
		} catch (SQLException e) {
			throw new FrameException(e.getMessage());
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}			
			if (conn != null) {
				conn.close();
			}
		}		
		return map;	
	}
	
	/**
	 * 미정산 매출에 대한 FCR 생성
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public String executeNoSalesProcedureProcess(Map map) throws Exception {

		String ERROR_MESSAGE = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = this.getConnection();
			
			String companyCd = StringHelper.null2void(map.get("COMPANY_CD"));
			String divisionCd = StringHelper.null2void(map.get("DIVISION_CD"));
			String coDocno = StringHelper.null2void(map.get("CO_DOC_NO"));
			
			String callQuery = "CALL unsettledInsertFCR('"+companyCd+"', '"+divisionCd+"', '"+coDocno+"', '')";
			ps = conn.prepareStatement(callQuery);

			if(log.isDebugEnabled()) log.debug("Call procedure(unsettledInsertFCR) : " + callQuery);

			rs = ps.executeQuery();
			rs.next();
			ERROR_MESSAGE = rs.getString(1);
			
			if(log.isDebugEnabled()) log.debug("result(unsettledInsertFCR) = " + ERROR_MESSAGE);
		} catch (SQLException e) {
			throw new FrameException(e.getMessage());
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}			
			if (conn != null) {
				conn.close();
			}
		}		
		return ERROR_MESSAGE;	
	}
	
	/**
	 * Excel Temporary Data 데이터 오류 체크
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map executeInvClosingMove(Map map) throws Exception {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String RETURN_CD = null;
		String RETURN_MSG = null;
		int RETURN_CNT;	
		
		try {
			conn = this.getConnection();
			String func = "MI_FTA_CO001_PROC";
			String companyCd = StringHelper.null2void(map.get("COMPANY_CD"));
			String fromDate = StringHelper.null2void(map.get("FROM_DATE"));
			String toDate = StringHelper.null2void(map.get("TO_DATE"));
			
			String callQuery = "CALL "+func+"('"+companyCd+"', '"+fromDate+"', '"+toDate+"', '', '', 0)";
			
			ps = conn.prepareStatement(callQuery);

			if(log.isDebugEnabled()) log.debug("Call procedure("+func+") : " + callQuery);

			rs = ps.executeQuery();
			rs.next();
			RETURN_CD = rs.getString(1);
			RETURN_MSG = rs.getString(2);
			RETURN_CNT = rs.getInt(3);
			map.put("RETURN_CD", RETURN_CD);
			map.put("RETURN_MSG", RETURN_MSG);
			map.put("RETURN_CNT", RETURN_CNT);
			
			if(log.isDebugEnabled()) log.debug("result("+func+") = " + RETURN_MSG);
		} catch (SQLException e) {
			throw new FrameException(e.getMessage());
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}			
			if (conn != null) {
				conn.close();
			}
		}		
		return map;	
	}	
	
	/**
	 * HUB로부터 수신한 확인서 등록 배치 프로시져 수행
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map executeCoRegisterProcess(Map map) throws Exception {

		String O_RETURN = null;
		String O_RETURNMSG = null;
		int O_LINES;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = this.getConnection();
			
			//conn.setAutoCommit(false);
			String func = StringHelper.null2void(map.get(Consistent.IF_PARAMETER_FUNCTION_NAME));  
			String companyCd = StringHelper.null2void(map.get("COMPANY_CD"));
			String vendorCd = StringHelper.null2void(map.get("VENDOR_CD"));
			String clCoDocNo = StringHelper.null2void(map.get("CL_CO_DOC_NO"));
			String clVendorCd = StringHelper.null2void(map.get("CL_VENDOR_CD"));
			String clCoYyyyMm = StringHelper.null2void(map.get("CL_CO_YYYYMM"));
			
			String callQuery = "CALL "+func+"('"+companyCd+"', '"+vendorCd+"', '"+clCoDocNo+"', '"+clVendorCd+"', '"+clCoYyyyMm+"', '', '', 0)";
			ps = conn.prepareStatement(callQuery);

			if(log.isDebugEnabled()) log.debug("Call procedure : " + callQuery);

			rs = ps.executeQuery();
			rs.next();
			O_RETURN = rs.getString(1);
			O_RETURNMSG = rs.getString(2);
			O_LINES = rs.getInt(3);
			map.put("O_RETURN", O_RETURN);
			map.put("O_RETURNMSG", O_RETURNMSG);
			map.put("O_LINES", O_LINES);

			//conn.commit();
			 		
		} catch (SQLException e) {
			throw new FrameException(e.getMessage());
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}			
			if (conn != null) {
				conn.close();
			}
		}		
		return map;	
	}
	
	/**
	 * 원산지 지위변동 보고서 정보 생성
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map executeFCRAnalysisProcess(Map map) throws Exception {

		String ERROR_MESSAGE = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Map retMap = new HashMap();
		
		try {
			conn = this.getConnection();
			String companyCd = StringHelper.null2void(map.get("COMPANY_CD"));
			String yyyyMm = StringHelper.null2void(map.get("YYYYMM"));
			String salesMgmtNo = StringHelper.null2void(map.get("SALES_MGMT_NO"));
			
			String callQuery = "CALL OA_ORGIN_ANALYSIS('"+companyCd+"', '"+yyyyMm+"', '"+salesMgmtNo+"', '')";
			ps = conn.prepareStatement(callQuery);

			if(log.isDebugEnabled()) log.debug("Call procedure : " + callQuery);

			rs = ps.executeQuery();
			rs.next();
			ERROR_MESSAGE = rs.getString(1);
			retMap.put("ERROR_MESSAGE", ERROR_MESSAGE);
			
			if(log.isDebugEnabled()) log.debug("result(unsettledInsertFCR) = " + ERROR_MESSAGE);
		} catch (SQLException e) {	
			throw new FrameException(e.getMessage());
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}			
			if (conn != null) {
				conn.close();
			}
		}		
		return retMap;	
	}	
	
	/**
	 * Excel Temporary Data 데이터 오류 체크
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map executeNetCostErrorCheck(Map map) throws Exception {

		String O_RETURN = null;
		String O_RETURNMSG = null;
		int O_LINES;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = this.getConnection();
			//conn.setAutoCommit(false);
			String func = "CH_VALID_NET_COST";  
			String companyCd = StringHelper.null2void(map.get("P_COMPANY_CD"));
			String divisionCd = StringHelper.null2void(map.get("P_DIVISION_CD"));
			String yyyyMm = StringHelper.null2void(map.get("P_YYYYMM"));
			String userId = StringHelper.null2void(map.get("P_USER_ID"));

			
			String callQuery = "CALL "+func+"('"+companyCd+"', '"+divisionCd+"', '"+yyyyMm+"', '"+userId+"', '', '', 0)";
			ps = conn.prepareStatement(callQuery);

			if(log.isDebugEnabled()) log.debug("Call procedure : " + callQuery);

			rs = ps.executeQuery();
			rs.next();
			O_RETURN = rs.getString(1);
			O_RETURNMSG = rs.getString(2);
			O_LINES = rs.getInt(3);
			map.put("O_RETURN", O_RETURN);
			map.put("O_RETURNMSG", O_RETURNMSG);
			map.put("O_LINES", O_LINES);

			//conn.commit();
			 		
		} catch (SQLException e) {
			throw new FrameException(e.getMessage());
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}			
			if (conn != null) {
				conn.close();
			}
		}		
		return map;	
	}	
	
	/**
	 * Excel 소스 자동 맵핑
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map executeChangeSource(Map map) throws Exception {

		String O_RETURN = null;
		String O_RETURNMSG = null;
		int O_LINES;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = this.getConnection();
			
			//conn.setAutoCommit(false);
			String func = "PKG_CLOSING_EXCEL";  
			String companyCd = StringHelper.null2void(map.get("COMPANY_CD"));
			String ifCd = StringHelper.null2void(map.get("IF_CD"));
			String userId = StringHelper.null2void(map.get("USER_ID"));
			
			String callQuery = "CALL "+func+"('"+companyCd+"', '"+ifCd+"', '"+userId+"', '', '', 0)";
			ps = conn.prepareStatement(callQuery);

			if(log.isDebugEnabled()) log.debug("Call procedure : " + callQuery);

			rs = ps.executeQuery();
			rs.next();
			O_RETURN = rs.getString(1);
			O_RETURNMSG = rs.getString(2);
			O_LINES = rs.getInt(3);
			map.put("O_RETURN", O_RETURN);
			map.put("O_RETURNMSG", O_RETURNMSG);
			map.put("O_LINES", O_LINES);
			
			//conn.commit();
			 		
		} catch (SQLException e) {
			throw new FrameException(e.getMessage());
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}			
			if (conn != null) {
				conn.close();
			}
		}		
		return map;	
	}	
	
	/**
	 * Excel ERP 통합 마감
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map executeErpClosing(Map map) throws Exception {
		String O_RETURN = null;
		String O_RETURNMSG = null;
		int O_LINES;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = this.getConnection();
			
			//conn.setAutoCommit(false);
			String func = "PKG_CLOSING_ERP";  
			String companyCd = StringHelper.null2void(map.get("COMPANY_CD"));
			String ifCd = StringHelper.null2void(map.get("IF_CD"));
			String yyyyMM = StringHelper.null2void(map.get("YYYYMM"));
			
			String callQuery = "CALL "+func+"('"+companyCd+"', '"+ifCd+"', '"+yyyyMM+"', '', '', 0)";
			ps = conn.prepareStatement(callQuery);

			if(log.isDebugEnabled()) log.debug("Call procedure : " + callQuery);

			rs = ps.executeQuery();
			rs.next();
			O_RETURN = rs.getString(1);
			O_RETURNMSG = rs.getString(2);
			O_LINES = rs.getInt(3);
			map.put("O_RETURN", O_RETURN);
			map.put("O_RETURNMSG", O_RETURNMSG);
			map.put("O_LINES", O_LINES);
			
			//conn.commit();
			 		
		} catch (SQLException e) {
			throw new FrameException(e.getMessage());
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}			
			if (conn != null) {
				conn.close();
			}
		}		
		return map;	
	}
	
	/**
	 * upload 데이터 오류 체크
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map executeItemErrorCheck(Map map) throws Exception {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String RETURN_CD = null;
		String RETURN_MSG = null;
		int RETURN_CNT;	
		
		try {
			conn = this.getConnection();
			String func = "CH_RCV_KR_CO_VALIDATION";
			String companyCd = StringHelper.null2void(map.get("COMPANY_CD"));
			String userId = StringHelper.null2void(map.get("USER_ID"));
			String ftaGroupCd = StringHelper.null2void(map.get("FTA_GROUP_CD"));
			String globalCd = StringHelper.null2void(map.get("GLOBAL_CD"));
			String issueDate = StringHelper.null2void(map.get("ISSUE_DATE"));
			String applyDate = StringHelper.null2void(map.get("APPLY_DATE"));
			String endDate = StringHelper.null2void(map.get("END_DATE"));
			
			String callQuery = "CALL "+func+"('"+companyCd+"', '"+userId+"', '"+ftaGroupCd+"', '"+globalCd+"', '"+issueDate+"', '"+applyDate+"', '"+endDate+"', '', '', 0)";
			
			ps = conn.prepareStatement(callQuery);

			if(log.isDebugEnabled()) log.debug("Call procedure("+func+") : " + callQuery);

			rs = ps.executeQuery();
			rs.next();
			
			RETURN_CD = rs.getString(1);
			RETURN_MSG = rs.getString(2);
			RETURN_CNT = rs.getInt(3);
			map.put("RETURN_CD", RETURN_CD);
			map.put("RETURN_MSG", RETURN_MSG);
			map.put("RETURN_CNT", RETURN_CNT);
			
			if(log.isDebugEnabled()) log.debug("result("+func+") = " + RETURN_MSG);
		} catch (SQLException e) {
			throw new FrameException(e.getMessage());
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}			
			if (conn != null) {
				conn.close();
			}
		}
		
		return map;	
	}
	
	/**
	 * upload 데이터 오류 체크(베트남 증명서)
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map executeVientnamItemErrorCheck(Map map) throws Exception {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String RETURN_CD = null;
		String RETURN_MSG = null;
		int RETURN_CNT;	
		
		try {
			conn = this.getConnection();
			String func = "CH_RCV_VN_CO_VALIDATION";
			String companyCd = StringHelper.null2void(map.get("COMPANY_CD"));
			String userId = StringHelper.null2void(map.get("USER_ID"));
			String ftaGroupCd = StringHelper.null2void(map.get("FTA_GROUP_CD"));
			String issueDate = StringHelper.null2void(map.get("ISSUE_DATE"));
			String langCd = StringHelper.null2void(map.get("LANGUAGE_CD"));
			
			String callQuery = "CALL "+func+"('"+companyCd+"', '"+userId+"', '"+ftaGroupCd+"', '"+issueDate+"', '"+langCd+"', '', '', 0)";
			
			ps = conn.prepareStatement(callQuery);

			if(log.isDebugEnabled()) log.debug("Call procedure("+func+") : " + callQuery);

			rs = ps.executeQuery();
			rs.next();
			
			RETURN_CD = rs.getString(1);
			RETURN_MSG = rs.getString(2);
			RETURN_CNT = rs.getInt(3);
			map.put("RETURN_CD", RETURN_CD);
			map.put("RETURN_MSG", RETURN_MSG);
			map.put("RETURN_CNT", RETURN_CNT);
			
			if(log.isDebugEnabled()) log.debug("result("+func+") = " + RETURN_MSG);
		} catch (SQLException e) {
			throw new FrameException(e.getMessage());
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}			
			if (conn != null) {
				conn.close();
			}
		}
		
		return map;	
	}
	
	/**
	 * 타 시스템 엑셀 양식에 대한 데이터 오류 체크
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map executeExternalFormData(Map map) throws Exception {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String O_RETURN = null;
		String O_RETURNMSG = null;
		int O_LINES;
    
		try {
			conn = this.getConnection();
			String func = "RCV_EXTERNAL_FORM_PROC";
			String companyCd = StringHelper.null2void(map.get("COMPANY_CD"));
			String vendorCd = StringHelper.null2void(map.get("VENDOR_CD"));
			String extSysCd = StringHelper.null2void(map.get("EXT_SYS_CD"));
			String fileName = StringHelper.null2void(map.get("FILE_NAME"));
			
			String callQuery = "CALL "+func+"('"+companyCd+"', '"+vendorCd+"', '"+extSysCd+"', '"+fileName+"', '', '', 0)";
			
			ps = conn.prepareStatement(callQuery);

			if(log.isDebugEnabled()) log.debug("Call procedure("+func+") : " + callQuery);

			rs = ps.executeQuery();
			rs.next();
			
			O_RETURN = rs.getString(1);
			O_RETURNMSG = rs.getString(2);
			O_LINES = rs.getInt(3);
			
			map.put("O_RETURN", O_RETURN);
			map.put("O_RETURNMSG", O_RETURNMSG);
			map.put("O_LINES", O_LINES);
			
			if(log.isDebugEnabled()) log.debug("result("+func+") = " + O_RETURNMSG);
		} catch (SQLException e) {
			throw new FrameException(e.getMessage());
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}			
			if (conn != null) {
				conn.close();
			}
		}		
		return map;	
	}
	
	/**
	 * 철판사급 화인서 업데이트
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map excuteMappingCoItem(Map map) throws Exception {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String RETURN_CD = null;
		String RETURN_MSG = null;
		int RETURN_CNT;	
		
		try {
			conn = this.getConnection();
			String func = "MI_FTA_RC001_SUB_PROC";
			String companyCd = StringHelper.null2void(map.get("COMPANY_CD"));
			
			String callQuery = "CALL "+func+"('"+companyCd+"', '', '', 0)";
			
			ps = conn.prepareStatement(callQuery);

			if(log.isDebugEnabled()) log.debug("Call procedure("+func+") : " + callQuery);

			rs = ps.executeQuery();
			rs.next();
			RETURN_CD = rs.getString(1);
			RETURN_MSG = rs.getString(2);
			RETURN_CNT = rs.getInt(3);
			map.put("RETURN_CD", RETURN_CD);
			map.put("RETURN_MSG", RETURN_MSG);
			map.put("RETURN_CNT", RETURN_CNT);
			
			if(log.isDebugEnabled()) log.debug("result("+func+") = " + RETURN_MSG);
		} catch (SQLException e) {
			throw new FrameException(e.getMessage());
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}			
			if (conn != null) {
				conn.close();
			}
		}		
		return map;	
	}
	
	/**
	 * 통관 OpenAPI 요청
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map excuteCcOpenAPICallRequest(Map map) throws Exception {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String O_API_IEM_REC_TY_CD = null;
		String O_TRNT_URL = null;
		String O_OAPI_IEM_GROUP = null;
		String O_RETURN = "";
		String O_RETURNMSG = "";			
		
		
		try {
			conn = this.getConnection();
			String func = "CC_OPENAPI_GET_PROC"; 
			String COMPANY_CD = StringHelper.null2void(map.get("COMPANY_CD"));
			String OAPI_IEM_CD = StringHelper.null2void(map.get("OAPI_IEM_CD"));
			String OAPI_IEM_GROUP = StringHelper.null2void(map.get("OAPI_IEM_GROUP"));
			String userId = StringHelper.null2void(map.get("USER_ID"));
			String P_PARA_KEY_01 = StringHelper.null2void(map.get("P_PARA_KEY_01"));
			String P_PARA_VAL_01 = StringHelper.null2void(map.get("P_PARA_VAL_01"));
			String P_PARA_KEY_02 = StringHelper.null2void(map.get("P_PARA_KEY_02"));
			String P_PARA_VAL_02 = StringHelper.null2void(map.get("P_PARA_VAL_02"));
			String P_PARA_KEY_03 = StringHelper.null2void(map.get("P_PARA_KEY_03"));
			String P_PARA_VAL_03 = StringHelper.null2void(map.get("P_PARA_VAL_03"));
			String P_PARA_KEY_04 = StringHelper.null2void(map.get("P_PARA_KEY_04"));
			String P_PARA_VAL_04 = StringHelper.null2void(map.get("P_PARA_VAL_04"));
			String P_PARA_KEY_05 = StringHelper.null2void(map.get("P_PARA_KEY_05"));
			String P_PARA_VAL_05 = StringHelper.null2void(map.get("P_PARA_VAL_05"));		
			String P_PARA_KEY_06 = StringHelper.null2void(map.get("P_PARA_KEY_06"));
			String P_PARA_VAL_06 = StringHelper.null2void(map.get("P_PARA_VAL_06"));	
			String P_PARA_KEY_07 = StringHelper.null2void(map.get("P_PARA_KEY_07"));
			String P_PARA_VAL_07 = StringHelper.null2void(map.get("P_PARA_VAL_07"));	
			String P_PARA_KEY_08 = StringHelper.null2void(map.get("P_PARA_KEY_08"));
			String P_PARA_VAL_08 = StringHelper.null2void(map.get("P_PARA_VAL_08"));	
			String P_PARA_KEY_09 = StringHelper.null2void(map.get("P_PARA_KEY_09"));
			String P_PARA_VAL_09 = StringHelper.null2void(map.get("P_PARA_VAL_09"));	
			String P_PARA_KEY_10 = StringHelper.null2void(map.get("P_PARA_KEY_10"));
			String P_PARA_VAL_10 = StringHelper.null2void(map.get("P_PARA_VAL_10"));				
			
			String callQuery = "CALL "+func+"('"+COMPANY_CD+"', '"+OAPI_IEM_CD+"', '"+OAPI_IEM_GROUP+"', '"+userId+"', '', '', '', '"+P_PARA_KEY_01+"', '"+P_PARA_VAL_01+"', '"+P_PARA_KEY_02+"', '"+P_PARA_VAL_02+"', '"+P_PARA_KEY_03+"', '"+P_PARA_VAL_03+"', '"+P_PARA_KEY_04+"', '"+P_PARA_VAL_04+"', '"+P_PARA_KEY_05+"', '"+P_PARA_VAL_05+"', '"+P_PARA_KEY_06+"', '"+P_PARA_VAL_06+"', '"+P_PARA_KEY_07+"', '"+P_PARA_VAL_07+"', '"+P_PARA_KEY_08+"', '"+P_PARA_VAL_08+"', '"+P_PARA_KEY_09+"', '"+P_PARA_VAL_09+"', '"+P_PARA_KEY_10+"', '"+P_PARA_VAL_10+"', '"+O_RETURN+"', '"+O_RETURNMSG+"')";
			
			ps = conn.prepareStatement(callQuery);

			if(log.isDebugEnabled()) log.debug("Call procedure("+func+") : " + callQuery);
			System.out.println("callQuery : " + callQuery);
			rs = ps.executeQuery();
			rs.next();
			
			O_API_IEM_REC_TY_CD = rs.getString(1);
			O_TRNT_URL = rs.getString(2);
			O_OAPI_IEM_GROUP = rs.getString(3);
			O_RETURN = rs.getString(4);
			O_RETURNMSG = rs.getString(5);
			map.put("O_API_IEM_REC_TY_CD", O_API_IEM_REC_TY_CD);
			map.put("O_TRNT_URL", O_TRNT_URL);
			map.put("O_OAPI_IEM_GROUP", O_OAPI_IEM_GROUP);
			map.put("O_RETURN", O_RETURN);
			map.put("O_RETURNMSG", O_RETURNMSG);					
			if (O_RETURN.equals("E")) {
				throw new FrameException(O_RETURNMSG);
			}
			// if(log.isDebugEnabled()) log.debug("result("+func+") = " + RETURN_MSG);
		} catch (SQLException e) {
			throw new FrameException(e.getMessage());
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}			
			if (conn != null) {
				conn.close();
			}
		}		
		return map;	
	}
	
	/**
	 * OpenAPI Return List and Insert
	 * Call cc_openapi_select_proc
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map excuteCcOpenapiSelectProc(Map map) throws Exception {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String O_SELECT_01 = "";
		String O_SELECT_02 = "";
		String O_SELECT_03 = "";
		String O_SELECT_04 = "";
		String O_SELECT_05 = "";
		String O_RETURN = "";
		String O_RETURNMSG = "";		
		
		
		try {
			conn = this.getConnection();
			String func = "CC_OPENAPI_SELECT_PROC"; 
			String COMPANY_CD = StringHelper.null2void(map.get("COMPANY_CD"));
			String OAPI_IEM_CD = StringHelper.null2void(map.get("OAPI_IEM_CD"));
			String O_OAPI_IEM_GROUP = StringHelper.null2void(map.get("O_OAPI_IEM_GROUP"));
			String TARGET_INSERT_AT = StringHelper.null2void(map.get("TARGET_INSERT_AT")); 
			String userId = StringHelper.null2void(map.get("USER_ID"));
			
			
			String callQuery = "CALL "+func+"('"+COMPANY_CD+"', '"+OAPI_IEM_CD+"', '"+O_OAPI_IEM_GROUP+"', '"+TARGET_INSERT_AT+"','"+O_SELECT_01+"', '"+O_SELECT_02+"', '"+O_SELECT_03+"', '"+O_SELECT_04+"', '"+O_SELECT_05+"', '"+O_RETURN+"', '"+O_RETURNMSG+"')";
			
			ps = conn.prepareStatement(callQuery);

			if(log.isDebugEnabled()) log.debug("Call procedure("+func+") : " + callQuery);
			System.out.println("callQuery : " + callQuery);
			rs = ps.executeQuery();
			rs.next();
			
			O_SELECT_01 = rs.getString(1);
			O_SELECT_02 = rs.getString(2);
			O_SELECT_03 = rs.getString(3);
			O_SELECT_04 = rs.getString(4);
			O_SELECT_05 = rs.getString(5);
			O_RETURN = rs.getString(6);
			O_RETURNMSG = rs.getString(7);
			
			map.put("O_SELECT_01", O_SELECT_01);
			map.put("O_SELECT_02", O_SELECT_02);
			map.put("O_SELECT_03", O_SELECT_03);
			map.put("O_SELECT_04", O_SELECT_04);
			map.put("O_SELECT_05", O_SELECT_05);
			map.put("O_RETURN", O_RETURN);
			map.put("O_RETURNMSG", O_RETURNMSG);			
			// if(log.isDebugEnabled()) log.debug("result("+func+") = " + RETURN_MSG);
			
			if (O_RETURN.equals("E")) {
				throw new FrameException(O_RETURNMSG);
			}			
		} catch (SQLException e) {
			throw new FrameException(e.getMessage());
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}			
			if (conn != null) {
				conn.close();
			}
		}		
		return map;	
	}	
	
	
	/**
	 * 한-인도 추가 소명자료 업로드 프로시져 호출
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map executeInterfaceIndia(Map map) throws Exception {
		String O_RETURN = null;
		String O_RETURNMSG = null;
		int O_LINES;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = this.getConnection();
			
			//conn.setAutoCommit(false);
			String func = "RCV_PKRIN_DOC_PROC";  
			String companyCd = StringHelper.null2void(map.get("COMPANY_CD"));
			String vendorCd = StringHelper.null2void(map.get("VENDOR_CD"));
			String userId = StringHelper.null2void(map.get("USER_ID"));
			
			String callQuery = "CALL "+func+"('"+companyCd+"', '"+vendorCd+"', '"+userId+"', '', '', 0)";
			ps = conn.prepareStatement(callQuery);

			if(log.isDebugEnabled()) log.debug("Call procedure : " + callQuery);

			rs = ps.executeQuery();
			rs.next();
			
			O_RETURN = rs.getString(1);
			O_RETURNMSG = rs.getString(2);
			O_LINES = rs.getInt(3);
			
			map.put("O_RETURN", O_RETURN);
			map.put("O_RETURNMSG", O_RETURNMSG);
			map.put("O_LINES", O_LINES);
		} catch (SQLException e) {
			throw new FrameException(e.getMessage());
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}			
			if (conn != null) {
				conn.close();
			}
		}		
		return map;	
	}
	
	/**
	 * 한-인도 추가 소명자료 발급정보 생성 프로시져 호출
	 *  
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map executeIssueIndiaInfo(Map map) throws Exception {
		String O_RETURN = null;
		String O_RETURNMSG = null;
		int O_LINES;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = this.getConnection();
			
			//conn.setAutoCommit(false);
			String func = "ISSUE_PKRIN_DOC_PROC";  
			String companyCd = StringHelper.null2void(map.get("COMPANY_CD"));
			String coDocNo = StringHelper.null2void(map.get("CO_DOC_NO"));
			String coReqNo = StringHelper.null2void(map.get("CO_REQ_NO"));
			String userId = StringHelper.null2void(map.get("USER_ID"));
			
			String callQuery = null;
			
			if(coReqNo.isEmpty()) {
				callQuery = "CALL "+func+"('"+companyCd+"', '"+coDocNo+"', null, '"+userId+"', '', '', 0)";
			} else {
				callQuery = "CALL "+func+"('"+companyCd+"', '"+coDocNo+"', "+coReqNo+", '"+userId+"', '', '', 0)";
			}
			
			ps = conn.prepareStatement(callQuery);

			if(log.isDebugEnabled()) log.debug("Call procedure : " + callQuery);

			rs = ps.executeQuery();
			rs.next();
			
			O_RETURN = rs.getString(1);
			O_RETURNMSG = rs.getString(2);
			O_LINES = rs.getInt(3);
			
			map.put("O_RETURN", O_RETURN);
			map.put("O_RETURNMSG", O_RETURNMSG);
			map.put("O_LINES", O_LINES);
		} catch (SQLException e) {
			throw new FrameException(e.getMessage());
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}			
			if (conn != null) {
				conn.close();
			}
		}		
		return map;	
	}
	
	/**
	 * 통관 이관 이관
	 *  
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map exeuteEntrTransProc(Map map) throws Exception {
		String O_RETURN = null;
		String O_RETURN_MSG = null;
		int O_LINES;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String companyCd = StringHelper.null2void(map.get("COMPANY_CD"));
		String docStleSe = StringHelper.null2void(map.get("DOC_STLE_SE"));
		String tranrFormCd = StringHelper.null2void(map.get("TRANR_FORM_CD"));
		String entrTranrSetupSn = StringHelper.null2void(map.get("ENTR_TRANR_SETUP_SN"));
		String fnName = StringHelper.null2void(map.get("FN_NAME"));
		String userId = StringHelper.null2void(map.get("USER_ID"));
		
		String func = "";
		try {
			conn = this.getConnection();			
			
			if (!fnName.equals("")) {
				func = fnName;
			} else {
				map.put("O_RETURN", O_RETURN);
				map.put("O_RETURN_MSG", O_RETURN_MSG);				
				return map;
			}

			String callQuery = null;
			
			callQuery = "CALL "+func+"('"+companyCd+"', '"+tranrFormCd+"',  '"+entrTranrSetupSn+"', '"+userId+"', '', '')";
			
			ps = conn.prepareStatement(callQuery);

			if(log.isDebugEnabled()) log.debug("Call procedure : " + callQuery);
			
			System.out.println("callQuery : " + callQuery);
			
			rs = ps.executeQuery();
			rs.next();
			
			O_RETURN = rs.getString(1);
			O_RETURN_MSG = rs.getString(2);

			map.put("O_RETURN", O_RETURN);
			map.put("O_RETURN_MSG", O_RETURN_MSG);
		} catch (SQLException e) {
			throw new FrameException(e.getMessage());
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}			
			if (conn != null) {
				conn.close();
			}
		}		
		return map;	
	}	
	
	/**
	 * 엑셀 인터페이스를 위한 테이블 생성
	 * 
	 * @param columnList
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	public boolean createExcelInterfaceObject(List columnList, Map map) throws Exception {
		Connection conn = null;
		Statement ps = null;
		boolean rst = true;
		
		try {
			conn = this.getConnection();
			
			// 쿼리 생성
			String tbl = StringHelper.null2void(map.get("SOURCE_TABLE"));
			String dbType = StringHelper.null2void(map.get("DB_TYPE"));
			StringBuffer sb = new StringBuffer();
			
			if(dbType.equals("ORACLE")) sb.append("CREATE TABLE "+tbl+"( COMPANY_CD NVARCHAR2(20) NOT NULL, EXCEL_UPLOAD_HIST_ID NUMBER(22) NOT NULL, ROW_NUM NUMBER(10), ERROR_YN NVARCHAR2(1), COMMENTS NVARCHAR2(2000),");
			else if(dbType.equals("MSSQL")) sb.append("CREATE TABLE "+tbl+"( COMPANY_CD NVARCHAR(20) NOT NULL, EXCEL_UPLOAD_HIST_ID NUMERIC(22) NOT NULL, ROW_NUM NUMERIC(10), ERROR_YN NVARCHAR(1), COMMENTS NVARCHAR(2000),");
			else if(dbType.equals("POSTGRESQL")) sb.append("CREATE TABLE "+tbl+"( COMPANY_CD VARCHAR(20) NOT NULL, EXCEL_UPLOAD_HIST_ID NUMERIC(22) NOT NULL, ROW_NUM NUMERIC(10), ERROR_YN VARCHAR(1), COMMENTS VARCHAR(2000),");
			
			for(int i = 0; i < columnList.size(); i++) {
				Map columnMap = (Map) columnList.get(i);
				
				String len = StringHelper.null2string(columnMap.get("DATA_LENGTH"), "0");
				String nn = StringHelper.null2string(columnMap.get("REQUIRED_YN"), "N");
				
				sb.append(StringHelper.null2void(columnMap.get("FROM_COL_ID")));
				// 데이터의 타입은 문자열, 문자열의 길이는 2000자, 필수아님으로 생성
				if(dbType.equals("ORACLE")) sb.append(" NVARCHAR2(2000) NULL,"); 
				else if(dbType.equals("MSSQL")) sb.append(" NVARCHAR(2000) NULL,");
				else if(dbType.equals("POSTGRESQL")) sb.append(" VARCHAR(2000) NULL,");
//				sb.append(StringHelper.null2void(columnMap.get("DATA_TYPE")));
//				if(!len.equals("0")) sb.append("("+len+") ");
//				if(nn.equals("Y")) sb.append("NOT NULL,");
//				else sb.append("NULL,");
			}
			
			if(dbType.equals("ORACLE")) sb.append("CREATE_DATE DATE NOT NULL)");
			else if(dbType.equals("MSSQL")) sb.append("CREATE_DATE datetime2 NOT NULL)");
			else if(dbType.equals("POSTGRESQL")) sb.append("CREATE_DATE timestamp NOT NULL)");
			
			if(log.isDebugEnabled()) log.debug("Create table query = " + sb.toString());
			
			ps = conn.createStatement();
			ps.executeUpdate(sb.toString());
			
			ps.executeUpdate("CREATE INDEX "+tbl+"_IDX1 ON "+tbl+"(COMPANY_CD, EXCEL_UPLOAD_HIST_ID)");
		} catch (SQLException e) {
			log.error(e.getMessage());
			rst = false;
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}		
		return rst;	
	}
	
	/**
	 * 엑셀 인터페이스를 위한 테이블 삭제
	 * 
	 * @param columnList
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	public boolean dropExcelInterfaceObject(Map map) throws Exception {
		Connection conn = null;
		Statement ps = null;
		boolean rst = true;
		
		try {
			conn = this.getConnection();
			
			// 쿼리 생성
			String tbl = StringHelper.null2void(map.get("SOURCE_TABLE"));
			String dbType = StringHelper.null2void(map.get("DB_TYPE"));
			StringBuffer sb = new StringBuffer();
			
			if (dbType.equals("ORACLE")) {
				sb.append("BEGIN EXECUTE IMMEDIATE 'DROP TABLE "+tbl+"'; EXCEPTION WHEN OTHERS THEN NULL;END;");
			} else {
				sb.append("DROP TABLE IF EXISTS "+tbl);
			}
			
			if(log.isDebugEnabled()) log.debug("drop table query = " + sb.toString());
			
			ps = conn.createStatement();
			ps.executeUpdate(sb.toString());
		} catch (SQLException e) {
			log.error(e.getMessage());
			rst = false;
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}		
		return rst;	
	}
	
	/**
	 * 엑셀 인터페이스를 위한 VIEW 생성
	 * 
	 * @param columnList
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	public boolean createExcelInterfaceViewObject(List columnList, Map map) throws Exception {
		Connection conn = null;
		Statement ps = null;
		boolean rst = true;
		
		try {
			conn = this.getConnection();
			
			// 쿼리 생성
			String tbl = StringHelper.null2void(map.get("SOURCE_TABLE"));
			String viewNm = StringHelper.null2void(map.get("SOURCE_TABLE"))+"_V";
			String dbType = StringHelper.null2void(map.get("DB_TYPE"));
			StringBuffer sb = new StringBuffer();
			
			sb.append("CREATE VIEW "+viewNm+" AS SELECT EUH.COMPANY_CD, EUH.EXCEL_UPLOAD_HIST_ID, EUH.YYYYMM, EUH.STATUS, ");
			
			for(int i = 0; i < columnList.size(); i++) {
				Map columnMap = (Map) columnList.get(i);

				sb.append("CO1."+StringHelper.null2void(columnMap.get("FROM_COL_ID"))+", ");
			}
			            
            if (dbType.equals("MSSQL")) {
                sb.append("CO1.ROW_NUM, CO1.CREATE_DATE FROM EXCEL_UPLOAD_HIST EUH WITH (NOLOCK) JOIN "+tbl+" CO1 WITH (NOLOCK) ON EUH.COMPANY_CD = CO1.COMPANY_CD AND EUH.EXCEL_UPLOAD_HIST_ID = CO1.EXCEL_UPLOAD_HIST_ID");   
            } else {
                sb.append("CO1.ROW_NUM, CO1.CREATE_DATE FROM EXCEL_UPLOAD_HIST EUH JOIN "+tbl+" CO1 ON EUH.COMPANY_CD = CO1.COMPANY_CD AND EUH.EXCEL_UPLOAD_HIST_ID = CO1.EXCEL_UPLOAD_HIST_ID");
            }
			

			if(log.isDebugEnabled()) log.debug("Create table query = " + sb.toString());
			
			ps = conn.createStatement();
			ps.executeUpdate(sb.toString());
		} catch (SQLException e) {
			log.error(e.getMessage());
			rst = false;
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}		
		return rst;	
	}	
	
	/**
	 * 엑셀 인터페이스를 위한 VIEW 삭제
	 * 
	 * @param columnList
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	public boolean dropExcelInterfaceViewObject(Map map) throws Exception {
		Connection conn = null;
		Statement ps = null;
		boolean rst = true;
		
		try {
			conn = this.getConnection();
			
			// 쿼리 생성
			String viewNm = StringHelper.null2void(map.get("SOURCE_TABLE"))+"_V";
			String dbType = StringHelper.null2void(map.get("DB_TYPE"));
			StringBuffer sb = new StringBuffer();
			
			if (dbType.equals("ORACLE")) {
				sb.append("BEGIN EXECUTE IMMEDIATE 'DROP VIEW "+viewNm+"'; EXCEPTION WHEN OTHERS THEN NULL;END;");
			} else {
				sb.append("DROP VIEW IF EXISTS "+viewNm);
			}
			
			if(log.isDebugEnabled()) log.debug("drop view query = " + sb.toString());
			
			ps = conn.createStatement();
			ps.executeUpdate(sb.toString());
		} catch (SQLException e) {
			log.error(e.getMessage());
			rst = false;
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}		
		return rst;	
	}	
	
	/**
	 * DB컨넥션 후 Connection객체를 반환한다.
	 * @return
	 * @throws SQLException
	 */
	private Connection getConnection() throws SQLException{
		ApplicationContext ctx = ApplicationContextFactory.getAppContext();
		BasicDataSource dataSource = (BasicDataSource) ctx.getBean("dataSource");
		
		Connection conn = dataSource.getConnection();
		
		conn.setAutoCommit(true);
		
		return conn;
	}
	
	/**
	 * 수입 입항보고제출여부 업데이트(CC_IMP_DCLRT.ETRY_RER_PRES_AT)
	 * ccOpenapiBatchBlEntyProc
	 * @param map
	 * @return
	 * @throws Exception
	 */ 
	public Map ccOpenapiBatchBlEntyProc(Map map) throws Exception {
		String O_RETURN = null;
		String O_RETURN_MSG = null;
		String O_ETRY_RER_PRES_AT = "N";
		String O_UPD_AT = "";
		
		int O_LINES;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String companyCd = StringHelper.null2void(map.get("COMPANY_CD"));
		String impDclrtSn = StringHelper.null2void(map.get("IMP_DCLRT_SN")); 
		String oapiIemCd = StringHelper.null2void(map.get("OAPI_IEM_CD"));
		String blYy = StringHelper.null2void(map.get("P_PARA_VAL_01"));
		String sttDe = StringHelper.null2void(map.get("STT_DE"));
		if (blYy.length() != 4) {
			blYy = sttDe.substring(0,4);
		}
		String hblNo = StringHelper.null2void(map.get("P_PARA_VAL_04"));
		String userId = StringHelper.null2void(map.get("USER_ID"));
		
		// 수입신고서 입항보고제출여부 업데이트
		String func = "CC_OPENAPI_BATCH_BL_ENTY_PROC";
		try {
			conn = this.getConnection();						

			String callQuery = null;
			
			callQuery = "CALL "+func+"('"+companyCd+"', '"+impDclrtSn+"',  '"+oapiIemCd+"', '', '', '"+userId+"', '"+blYy+"', '"+hblNo+"', '', '', '', '')";
			
			ps = conn.prepareStatement(callQuery);

			if(log.isDebugEnabled()) log.debug("Call procedure : " + callQuery);
			System.out.println("ccOpenapiBatchBlEntyProc : " + callQuery);
			rs = ps.executeQuery();
			rs.next();
			
			O_RETURN = rs.getString(1);
			O_RETURN_MSG = rs.getString(2);
			O_ETRY_RER_PRES_AT = rs.getString(3);
			O_UPD_AT = rs.getString(4);

			map.put("O_RETURN", O_RETURN);
			map.put("O_RETURN_MSG", O_RETURN_MSG);
			map.put("O_ETRY_RER_PRES_AT", O_ETRY_RER_PRES_AT);
			map.put("O_UPD_AT", O_UPD_AT);
			
		} catch (SQLException e) {
			throw new FrameException(e.getMessage());
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}			
			if (conn != null) {
				conn.close();
			}
		}		
		return map;	
	}		
}
