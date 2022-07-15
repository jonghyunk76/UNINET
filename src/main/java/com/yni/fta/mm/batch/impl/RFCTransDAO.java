package com.yni.fta.mm.batch.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sap.mw.jco.JCO;
import com.yni.fta.common.Consistent;
import com.yni.fta.common.batch.vo.BatchVo;

import kr.yni.frame.dao.JcoDAO;
import kr.yni.frame.exception.FrameException;
import kr.yni.frame.util.JcoMapHelper;
import kr.yni.frame.util.StringHelper;

/**
 * SAP RFC 인터페이스 데이터 추출 클래스
 * 
 * @author ador2
 *
 */
public class RFCTransDAO extends JcoDAO {
	
    private JCO.Client client = null; 
    
    public RFCTransDAO() {
        super();
    }

    /**
     * JCO를 이용한 RFC 처리결과를 List에 저장한 후 리턴하는 클래스
     * 
     * @param batchVo 배치 실행계획과 결과를 저장한 VO
     * @return RFC 수행결과
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List<Object> callRemoteFunction(BatchVo batchVo) throws Exception {
    	log.debug("SAP connection Start....");
    	
    	List<Object> rstList = null;

        client = this.getConnection(); // SAP connection
        
        try {
        	
            JcoMapHelper jcoMap = new JcoMapHelper(batchVo.getJcoId()); // JCO ID에  해당하는 jco정보 획득
            
            String compCd = null;
            Map paramMap = null;
            if(batchVo.getParameter() != null) {
            	paramMap = batchVo.getParameter().getMap();
            	compCd = batchVo.getParameter().getCompanyCode();
            } else {
            	throw new FrameException("JCO Inerface Error : not found input paramter.");
            }
            
            Map importParam = jcoMap.getImportParameter(paramMap);
            String jcoFunc = jcoMap.getFunctionName();
            
            // 평화정공 베트남법인의 구매정보에 대해서 RFC함수명을 강제 치환시킴(2021-07-13)
            if(compCd.equals("7500") && jcoFunc.equals("ZFTAN_RFC_PO_LEDGER")) {
            	jcoFunc = "ZFTAN_RFC_PO_LEDGER_VIETNAM";
            }
            
            if(log.isDebugEnabled()) log.debug("company = " + compCd + ", function name = " + jcoFunc + ", import parameter = " + importParam);
            
            JCO.Function function = this.execute(client, jcoFunc, importParam);
            
            List returnParam = null;
            Map msgCodeMap = null;
            Map msgStrMap = null;

            // get Export Parameter
            List<String> paramList = jcoMap.getExportParameterName();
            
            // export 파라메터가 없는 경우에는 무조건 성공으로 기록한다.(2017.12.13 YNI-Maker)
            if(paramList.size() == 0) {
        	    msgCodeMap = new HashMap();
        	    msgStrMap = new HashMap();
        	    
        	    msgCodeMap.put(Consistent.IF_BATCH_BATCH_STATUS, "S");
        	    msgStrMap.put(Consistent.IF_BATCH_ERROR_MESSAGE, null);
            }
            
            for(int i = 0; i < paramList.size(); i++) {
                String paramName = paramList.get(i);

                if(jcoMap.getExportParameterType(paramName) == JCO.TYPE_TABLE) {
                    returnParam = this.getTableFromExportParameter(function, paramName, returnParam);
                    
                    if(returnParam != null & returnParam.size() > 0) {
                        batchVo.setExportObject(paramName, returnParam);
                    }
                } else if(jcoMap.getExportParameterType(paramName) == JCO.TYPE_STRUCTURE) {
                	returnParam = this.getStructureFromExportParameter(function, paramName, returnParam);
                	
                	if(returnParam != null & returnParam.size() > 0) {
                		batchVo.setExportObject(paramName, returnParam);
                    }
                } else {
                    returnParam = this.getListFromExportParameter(function, paramName, returnParam);
                    
                    if(returnParam != null & returnParam.size() > 0) {
                        if (i == 0) {   // 배치결과 코드 획득
                            msgCodeMap = (Map) returnParam.get(i);
                        } else if (i == 1) {  // 배치결과 메시지
                            msgStrMap = (Map) returnParam.get(i);
                        } else {
                        	batchVo.setExportObject(paramName, returnParam);
                        }
                    }
                }
            }

            if (msgCodeMap == null) {
                batchVo.setBatchStatus("E");
                batchVo.setErrorMessage("An unknown error has occurred during execution.");
            } else {
            	batchVo.setBatchStatus(StringHelper.null2void(msgCodeMap.get(Consistent.IF_BATCH_BATCH_STATUS)));
            	if(log.isDebugEnabled()) log.debug("complete... get batch table list.");
            }
            
            if ("S".equals(batchVo.getBatchStatus())) {
            	batchVo.setBatchStatus("S");
            	
                // get Table Parameter
            	rstList = this.getTableFromTableParameter(function, jcoMap.getTableParameterName(), rstList, batchVo.getOffset(), batchVo.getMaxRows());
                
                if(rstList == null) {
                    rstList = new ArrayList<Object>();
                }
                
                int uint = 0;
                		
                for(int i = 0; i < rstList.size(); i++) {
            		Map rstMap = (Map) rstList.get(i);
            		
            		// 회사코드가 누락된 경우, 요청하고 있는 회사정보를 이용해서 회사코드를 등록한다.(2017.12.13, YNI-Maker)
            		if(StringHelper.null2void(rstMap.get("BUKRS")).isEmpty()) {
            			rstMap.put("BUKRS", compCd);
            			uint++;
                    }
            		
            		if(batchVo.getJcoId().equals("RPT_PP_001")) {
            			// 표준BOM이 최신으로 관리될 경우, 매월 데이터를 인터페이스해야 하기 때문에 생산BOM같은 형태로 반영함(이로인해, 누락가능한 마감월을 자동생성하게 했음)(2017.12.18, YNI-Maker)
	            		if(StringHelper.null2void(rstMap.get("YYYYMM")).isEmpty()) {
	            			String date = batchVo.getParameter().getFromDate();
	            			
	            			if(date != null && date.length() >= 6) {
	            				rstMap.put("YYYYMM", date.substring(0, 6));
	            			}
	                    }
	            		
	            		// 원재료 코드가 없는 경우 'No'로 적용하고, 추후 배치 처리 시 해당 원재료는 빼도록 함(2017.12.19)-화신적용
	            		if(StringHelper.null2void(rstMap.get("MATNR")).isEmpty()) {
	            			rstMap.put("MATNR", "No");
	            		}
            		}
            		
            		if(batchVo.getJcoId().equals("RPT_MM_004")) {
            			if(StringHelper.null2void(rstMap.get("IN_SEQ")).isEmpty()) {
            				rstMap.put("IN_SEQ", i);
            			}
            		}
            	}
            	
                log.debug("table's row number = " + rstList.size() + ", update count(Company code - BUKRS) = " + uint);
                
                if(paramList.size() == 0) {
                	msgCodeMap.put(Consistent.IF_BATCH_TOTAL_ROWS, rstList.size());
                }
                
                if (msgStrMap.get(Consistent.IF_BATCH_ERROR_MESSAGE) != null) {
                    String message = StringHelper.null2void(msgStrMap.get(Consistent.IF_BATCH_ERROR_MESSAGE));
                    
                    batchVo.setErrorMessage(message);
                    log.debug("data row count = " + message);
                }
                
                this.deleteFunctionList(batchVo.getMap(), jcoFunc);
            } else {
                rstList = new ArrayList<Object>();
                // NullPointerException처리 필요
                String message = null;

                if ("E".equals(msgCodeMap.get(Consistent.IF_BATCH_BATCH_STATUS))) {
                	batchVo.setBatchStatus("E");
                	
                    if (msgStrMap.get(Consistent.IF_BATCH_ERROR_MESSAGE) != null) {
                        message = StringHelper.null2void(msgStrMap.get(Consistent.IF_BATCH_ERROR_MESSAGE));
                    }
                    batchVo.setErrorMessage(message);
                    
                    throw new FrameException(message);
                } else {
                    message = "message's value is empty.";
                    batchVo.setErrorMessage(message);
                    
                    this.deleteFunctionList(batchVo.getMap(), jcoFunc);
                }
            }
            
            if(log.isInfoEnabled()) {
                log.info("Request to select the results(Interface code = " + batchVo.getInterfaceCode() + " | Code = " + msgCodeMap + " | Message = " + msgStrMap);
            }
        } catch (Exception e) {
        	if(log.isErrorEnabled()) log.error(e.toString());
            throw e;
        } finally {
            if (client != null) {
                this.releaseClient(client); // SAP Client 객체 반환
            }
        }

        return rstList;
    }
    
    /**
     * ERP와 싱크를 맞추기 위해 SAP에 저장된 임시데이터 데이터에 대해 삭제를 요청한다. 
     * @param map
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void deleteFunctionList(Map map, String jcoFunc) throws Exception {
        JcoMapHelper jcoMap = new JcoMapHelper("RFC000"); // JCO ID에  해당하는 jco정보 획득
        
        boolean runYn = false;
        // 대상 인터페이스 여부 체크
        for(int i = 0; i < Consistent.SYNC_INTERFACE_KIND.length; i++) {
            if(Consistent.SYNC_INTERFACE_KIND[i].equals(map.get(Consistent.IF_BATCH_INTERFACE_CD))) {
                runYn = true;
                break;
            }
        }
        
        if(runYn) {
            Map<String, Object> importParam = new HashMap<String, Object>();
            importParam.put(Consistent.IF_PARAMETER_COMPANY_CD, map.get("COMPANY_CODE"));
            importParam.put(Consistent.IF_PARAMETER_IF_CD, map.get(Consistent.IF_BATCH_INTERFACE_CD));
            
            JCO.Function function = this.execute(client, jcoFunc, importParam);
            
            List returnParam = null;
            // 메시지가 null인 경우 처리
            Map msgCodeMap = null;
            Map msgStrMap = null;
            
            // get Export Parameter
            List<String> paramList = jcoMap.getExportParameterName();
            for (int i = 0; i < paramList.size(); i++) {
                String paramName = paramList.get(i);
    
                returnParam = this.getListFromExportParameter(function, paramName, returnParam);
                if (returnParam != null & returnParam.size() > 0) {
                    if (i == 0) {
                        msgCodeMap = (Map) returnParam.get(i);
                    } else if (i == 1) {
                        msgStrMap = (Map) returnParam.get(i);
                    }
                }
            }
            if(log.isInfoEnabled()) {
                log.info("Request to delete the results(Interface code = " + map.get(Consistent.IF_BATCH_INTERFACE_CD) 
                        + " | Code = " + msgCodeMap + " | Message = " + msgStrMap);
            }
        } else {
            if(log.isDebugEnabled()) {
                log.debug(map.get(Consistent.IF_BATCH_INTERFACE_CD) + " is not target of interface");
            }
        }
    }
    
}
