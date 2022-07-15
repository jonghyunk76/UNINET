package com.yni.fta.mm.batch.impl;


import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.constants.Style;
import org.apache.axis.message.SOAPHeaderElement;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;

import com.yni.fta.common.Consistent;
import com.yni.fta.common.batch.vo.BatchVo;

import kr.yni.frame.dao.YniAbstractDAO;
import kr.yni.frame.exception.FrameException;
import kr.yni.frame.util.JcoMapHelper;
import kr.yni.frame.util.JsonUtil;
import kr.yni.frame.util.StringHelper;

/**
 * SAP PO 웹서비스 방식의 ERP 인터페이스 수행
 * 
 * @author ador2
 * @since 2020-12-10
 */
public class POTransDao extends YniAbstractDAO {
	
    public POTransDao() { }

    /**
     * SAP PO를 이용한 RFC 처리결과를 List에 저장한 후 리턴하는 클래스
     * - SAP PO 웹서비스 통신
     * 
     * @param batchVo 배치 실행계획과 결과를 저장한 VO
     * @return RFC 수행결과
     * @throws Exception
     */
    public List<Object> callRemoteFunction(BatchVo batchVo) throws Exception {
    	log.debug("SAP PO connection Start....");
    	
    	List<Object> rstList = null;
        
        try {
        	// 접속정보 조회
        	String poURL = this.properties.getProperty("infc.sap.po.url")+batchVo.getFilePath();
        	String poID = this.properties.getProperty("infc.sap.po.id");
        	String poPW = this.properties.getProperty("infc.sap.po.pw");
        	
        	// 요청 파라메터 생성
        	Map paramMap = batchVo.getParameter().getMap();
        	
        	log.info("connect info( RUL = " + poURL + ", ID = " + poID + ", Password = " + poPW + "\nParameter = " + paramMap);
        	
        	JcoMapHelper jcoMap = new JcoMapHelper(batchVo.getJcoId());
        	String itemCd = StringHelper.null2void(paramMap.get(Consistent.IF_PARAMETER_ITEM_CD));
        	
        	// 특정 제품코드가 없고, 실시간 BOM을 가져와야 하는 경우 실행
    		if(itemCd.isEmpty()) {
	        	// 실시간 제품BOM을 추출하기 위해 가져올 품목정보 생성(서연이화만 적용하고 있음)
	        	if(batchVo.getJcoId().equals("RPT_PP_005")) {
	        		// 매출 인터페이스 임시 테이블(TR_XXXX)와 실 매출테이블(SALES)에서 찾아 보낼 대상품목을 지정됨.
        			String fromdate = StringHelper.null2void(paramMap.get(Consistent.IF_PARAMETER_FROM_DATE));
        			String todate = StringHelper.null2void(paramMap.get(Consistent.IF_PARAMETER_TO_DATE));
        			String reqType = StringHelper.null2void(paramMap.get(Consistent.IF_PARAMETER_REQUEST_TYPE));
        			
        			if(todate.isEmpty()) paramMap.put(Consistent.IF_PARAMETER_TO_DATE, fromdate);
        			
        			String mapName = jcoMap.getImportName();
        			List prodList = null;
        			
        			if(reqType.equals("AS")) { // 매출조정에 대한 대상품목 조회(TR_xxx에 저장된 매출조정 정보를 대상으로 수행) 
        				prodList = this.selectAdjustProductList(paramMap);
        			} else { // 매출에 대한 대상품목 조회
        				prodList = this.selectReqProductList(paramMap);
        			}
        			
        			if(prodList != null && prodList.size() > 0) {
        				paramMap.put(mapName, prodList);
        			} else {
        				batchVo.setBatchStatus("N");
                		batchVo.setTotalRows("0");
                        batchVo.setErrorMessage(this.getMessage("MSG_NOT_EXIST_DATA", null, batchVo.getLanguage()));
                        
                        return new LinkedList();
        			}
        		}
        	}
        	
            Map<String, Object> inputParam = jcoMap.getImportParameter(paramMap);
            
            log.info("Request parameter(JSON) = " + JsonUtil.getViewJson(inputParam));
            
            if(batchVo.getFilePath().startsWith("SOAP:")) { // SAP(PO)-웹서비스(설정경로의 시작을 SOAP:으로 시작해야 하며, 요청경로 생성 시 구분자는 삭제함)
            	poURL = poURL.replaceAll("SOAP:", "");
            	rstList = InterfaceMethod.connectWebservice(poURL, poID, poPW, inputParam, batchVo);
            } else { // SAP(PO)-http 통신통신
            	rstList = InterfaceMethod.connectHttp(poURL, poID, poPW, inputParam, batchVo);
            }
            
            if(rstList != null && rstList.size() > 0) {
        		batchVo.setBatchStatus("S");
        		batchVo.setTotalRows(Integer.toString(rstList.size()));
        		batchVo.setErrorMessage("");
        	} else {
        		batchVo.setBatchStatus("N");
        		batchVo.setTotalRows("0");
                batchVo.setErrorMessage(this.getMessage("MSG_NOT_EXIST_DATA", null, batchVo.getLanguage()));
        	}
        } catch (Exception e) {
            e.printStackTrace();
            
            batchVo.setBatchStatus("E");
    		batchVo.setTotalRows("0");
            batchVo.setErrorMessage(e.getMessage());
            
            throw e;
        }
        
        return rstList;
    }
    
    /**
     * 실시간 제품BOM을 요청한 리스트 조회
     * - 매출조정 기준으로 데이터 추출
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List<Object> selectAdjustProductList(Map map) throws Exception {
        List<Object> returnValue = null;
        try {
            returnValue = list("MMBATCH.selectAdjustProductList", map);
        } catch (Exception exp) {
            log.debug(exp);

            throw exp;
        }

        return returnValue;
    }
    
    /**
     * 실시간 제품BOM을 요청한 리스트 조회
     * - 매출기준으로 데이터 추출
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List<Object> selectReqProductList(Map map) throws Exception {
        List<Object> returnValue = null;
        try {
            returnValue = list("MMBATCH.selectReqProductList", map);
        } catch (Exception exp) {
            log.debug(exp);

            throw exp;
        }

        return returnValue;
    }
    
    public static class InterfaceMethod {
    	
    	private static Log log = LogFactory.getLog(InterfaceMethod.class);
    	
    	/**
    	 * http 통신을 위한 매소드
    	 * 
    	 * @param path
    	 * @param id
    	 * @param pass
    	 * @param reqParam
    	 * @return
    	 * @throws Exception
    	 */
    	public static List<Object> connectHttp(String path, String id, String pass, Map<String, Object> reqParam, BatchVo batchVo) throws Exception {
    		List<Object> list = null;
    		HttpURLConnection conn = null;
    		InputStream in = null;
    		
    		try {
	            URL url = new URL(path);
	            
	            String userpass = id + ":" + pass;
	            String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userpass.getBytes()));
	            String jsonData = JsonUtil.getViewJson(reqParam);
	            
	            conn = (HttpURLConnection) url.openConnection();
	            conn.setRequestProperty ("Authorization", basicAuth);
	            conn.setConnectTimeout(5000);
	            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
	            conn.setDoOutput(true);
	            conn.setDoInput(true);
	            conn.setRequestMethod("POST");
	
	            OutputStream os = conn.getOutputStream();
	            os.write(jsonData.getBytes("UTF-8"));
	            os.close();
	            
	            in = new BufferedInputStream(conn.getInputStream());
	            String result = org.apache.commons.io.IOUtils.toString(in, "UTF-8");
	            
	            int code = conn.getResponseCode();
	            
	            log.debug("Response result = " + result);

	            if (code == HttpURLConnection.HTTP_OK) {
		            JcoMapHelper jcoMap = new JcoMapHelper(batchVo.getJcoId());
		            String tableName = jcoMap.getTableName();
		            
		            try {
			            JSONObject jsonObject = new JSONObject(result);
			            
			            if(tableName != null && !tableName.isEmpty()) {
			            	Object listObj = jsonObject.toMap().get(tableName);
			            	
			            	if(listObj instanceof List) {
				            	list = (List) listObj;
				            }
			            }
		            } catch(Exception e) {
		            	log.error(e);
		            }
	            } else {
	            	throw new FrameException("Server connection failed.");
	            }
    		} catch(Exception e) {
    			e.printStackTrace();
    			throw e;
    		} finally {
    			try {
    				if(in != null) in.close();
    				if(conn != null) conn.disconnect();
    			} catch(Exception e) {
    				throw e;
    			}
    		}
    		
            return list;
    	}
    	
    	/**
    	 * 웹서시브 통신을 위한 매소드
    	 * 
    	 * @param path
    	 * @param id
    	 * @param pass
    	 * @param reqParam
    	 * @return
    	 * @throws Exception
    	 */
    	public static List<Object> connectWebservice(String path, String id, String pass, Map<String, Object> reqParam, BatchVo batchVo) throws Exception {
    		List<Object> list = null;

    		try {
    			String jsonData = JsonUtil.getViewJson(reqParam);
    			
    			SOAPHeaderElement actionHeader = new SOAPHeaderElement("", "Action");
    			actionHeader.setObjectValue("NA");
    			
            	Service ws = new Service();
            	Call call = (Call)ws.createCall();
            	
    			call.setTargetEndpointAddress(new URL(path));
    			call.setOperation("inquiry");
    			call.setOperationStyle(Style.WRAPPED);
    			call.addHeader(actionHeader);
    			call.setUsername(id);
    			call.setPassword(pass);
    			
    			String returnText = (String)call.invoke(new Object[]{jsonData});
    			
    			list = JsonUtil.getList(returnText);
    		} catch(Exception e) {
    			e.printStackTrace();
    			throw e;
    		}
    		
            return list;
    	}
    	
    }
}
