package com.yni.fta.mm.batch.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yni.fta.common.Consistent;
import com.yni.fta.common.batch.vo.BatchVo;
import com.yni.fta.common.postgresql.PostgresqlDao;

import kr.yni.frame.dao.YniAbstractDAO;
import kr.yni.frame.exception.FrameException;
import kr.yni.frame.util.JcoMapHelper;
import kr.yni.frame.util.StringHelper;
import com.sap.mw.jco.JCO;

/**
 * 이기중 데이터베이스 인터페이스 데이터 추출 클래스
 * 
 * @author ador2
 *
 */
public class RPCTransDao extends YniAbstractDAO {

    /**
     * RP(Remote Procedure)를 호출한다.
     * 
     * @param map
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Object> callRemoteProcedure(BatchVo batchVo) throws Exception {
        List<Object> rstList = null;
        String db = StringHelper.null2void(this.properties.get("application.db.type"));

        try {
            // JCO ID에 해당하는 jco정보 획득
            JcoMapHelper jcoMap = new JcoMapHelper(batchVo.getJcoId());

            Map paramMap = null;
            if (batchVo.getParameter() != null) {
                paramMap = batchVo.getParameter().getMap();
                
                // 20160426::수동배치가 아닌경우 BUKRS가 없을 수 있다. 그럴경우 회사코드를 맵핑한다. 추후 프로시저를 변경할 경우 이 코드를 날리자.
                String bukrs = StringHelper.null2void(paramMap.get("I_BUKRS"));
                String companyCd = StringHelper.null2void(paramMap.get("COMPANY_CD"));
                if (StringHelper.isNull(bukrs)) {
                    paramMap.put("I_BUKRS", companyCd);
                }
                
                if (StringHelper.isNull(batchVo.getBatchStatus())) {
                    // 20160426::수동배치가 아닌경우 BATCH_STATUS가 없을 수 있다. 그럴경우 'S'로 맵핑한다.
                    batchVo.setBatchStatus("S");
                }
            } else {
                throw new FrameException("RPC Inerface Error : not found input paramter.");
            }

            Map inputParam = jcoMap.getImportParameter(paramMap);

            // table 정보를 담은 리스트
            List<String> tableParam = jcoMap.getTableParameterName();

            // ERP데이터 인터페이스 전에 RPC테이블 내역을 삭제한다.
            for (int i = 0; i < tableParam.size(); i++) {
                Map rpcMap = new HashMap();

                String tableName = tableParam.get(i);
                List list = jcoMap.getTableColumnName(i);

                log.debug("column count = " + list.size() + ", contents=" + list.get(list.size() - 1));

                rpcMap.put(Consistent.IF_JOB_COMPANY_CD, batchVo.getMap().get(Consistent.IF_JOB_COMPANY_CD));
                rpcMap.put("SOURCE_TABLE", tableName);
                rpcMap.put("COLUMN_LIST", list);

                // RPC 테이블 내역을 삭제한다.
                this.deleteRpcTable(rpcMap);

                // transaction 처음만 처리(JCO XML설정 시 테이블이 여러개라면 수정해서 적용할 것)
                break;
            }

            // 수행할 프로시져 명을 구한다.
            inputParam.put("FUNCTION_NAME", jcoMap.getFunctionName());

            if (log.isInfoEnabled())
                log.info("Remote Procedure Name = " + jcoMap.getFunctionName() + ", parameter = " + inputParam);

            // RP(Remote Procedure) call
            if (db.equals("POSTGRESQL")) {
            	PostgresqlDao postgre = new PostgresqlDao();
            	postgre.callRemoteProcedure(inputParam);
            } else {
            	this.executeProcedure("MMRPCBATCH.callRemoteProcedure", inputParam);
            }

            // rp call result
            List returnParam = new ArrayList<Object>();

            // get Export Parameter
            List<String> outputList = jcoMap.getExportParameterName();
            
            for (int i = 0; i < outputList.size(); i++) {
                String exp_name = outputList.get(i);
                
                String returnMsg = null;
                
                if (jcoMap.getExportParameterType(exp_name) == JCO.TYPE_CHAR) {
                    returnMsg = StringHelper.null2void(inputParam.get(exp_name)).trim();

                    if(!exp_name.isEmpty() && exp_name.equals(Consistent.IF_BATCH_BATCH_STATUS)) {
                    	batchVo.setBatchStatus(returnMsg);
                    }
                    if(!exp_name.isEmpty() && exp_name.equals(Consistent.IF_BATCH_ERROR_MESSAGE)) {
                    	if(StringHelper.null2void(inputParam.get(Consistent.IF_BATCH_BATCH_STATUS)).trim().equals("S")) {
                    		batchVo.setErrorMessage("");
                    	} else {
                    		batchVo.setErrorMessage(returnMsg);
                    	}
                    }
					if(!exp_name.isEmpty() && exp_name.equals(Consistent.IF_BATCH_TOTAL_ROWS)) {
						batchVo.setTotalRows(returnMsg);
					}
                }
            }

            if (log.isDebugEnabled()) {
                log.debug("RPC(Remote Procedure Controller) result : " + batchVo.toString());
            }

            // ERP에서 이관시킨 테이블 내역을 조회한다.
            if ("S".equals(batchVo.getBatchStatus())) {
                for (int i = 0; i < tableParam.size();) {
                    Map rpcMap = new HashMap();

                    String tableName = tableParam.get(i);
                    List list = jcoMap.getTableColumnName(i);

                    log.debug("column count = " + list.size() + ", contents=" + list.get(list.size() - 1));

                    rpcMap.put(Consistent.IF_JOB_COMPANY_CD, batchVo.getMap().get(Consistent.IF_JOB_COMPANY_CD));
                    rpcMap.put("SOURCE_TABLE", tableName);
                    rpcMap.put("COLUMN_LIST", list);

                    // RPC 테이블 데이터 조회
                    rstList = this.selectRpcTable(rpcMap);

                    if (log.isDebugEnabled()) {
                        log.debug("RPC's Data size = " + rstList.size());
                    }
                    
                    // transaction 처음만 처리(JCO XML설정 시 테이블이 여러개라면 수정해서 적용할 것)
                    break;
                }

                // 조회건수 debug
                if (rstList != null) {
                    String message = Integer.toString(rstList.size());

                    // 생성건수
                    batchVo.setTotalRows(message);

                    log.debug("data row count = " + message);
                }
            } else {
                rstList = new ArrayList<Object>();
                // NullPointerException처리 필요
                String message = batchVo.getErrorMessage();

                if ("E".equals(batchVo.getBatchStatus())) {
                    throw new FrameException(message);
                } else {
                    message = this.getMessage("MSG_NOT_EXIST_DATA", null, batchVo.getLanguage());

                    batchVo.setErrorMessage(message);
                }
            }

            if (log.isInfoEnabled()) {
                log.info("Request to select the results(Interface code = " + batchVo.getInterfaceCode() + " | Result Description = "
                        + batchVo.getErrorMessage());
            }
        } catch (Exception exp) {
        	if(log.isErrorEnabled()) log.error(exp.getCause());
            throw exp;
        }

        return rstList;
    }

	/**
     * RP(Remote Procedure)를 호출 후 저장된 ERP Data를 획득한다.
     * 
     * @param map
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Object> selectRpcTable(Map map) throws Exception {
        List<Object> returnValue = null;
        try {
            returnValue = list("MMRPCBATCH.selectRpcTable", map);
        } catch (Exception exp) {
            log.debug(exp);

            throw exp;
        }

        return returnValue;
    }

    /**
     * RP(Remote Procedure)를 통해 인터페이스된 데이터를 삭제한다.
     * 
     * @param map
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes" })
    public int deleteRpcTable(Map map) throws Exception {
        int returnValue = 0;
        Connection conn = null;
        try {
        	conn = super.getConnection();
        	conn.setAutoCommit(false);
            returnValue = update("MMRPCBATCH.deleteRpcTable", map);
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
}
