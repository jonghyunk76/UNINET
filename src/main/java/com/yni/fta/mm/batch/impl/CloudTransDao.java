package com.yni.fta.mm.batch.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;

import com.yni.fta.common.Consistent;
import com.yni.fta.common.batch.vo.BatchVo;
import com.yni.fta.common.batch.vo.JobVo;
import com.yni.fta.common.batch.vo.ParameterVo;
import com.yni.fta.common.tools.StringUtil;

import kr.yni.frame.dao.YniAbstractDAO;
import kr.yni.frame.util.JcoMapHelper;
import kr.yni.frame.util.JsonUtil;
import kr.yni.frame.util.StringHelper;

/**
 * TOMS Cloud HUB 인터페이스 클래스
 * 
 * @author jonghyunkim
 *
 */
public class CloudTransDao extends YniAbstractDAO {
	
	private static final String DEFAULT_ENCODING = "utf-8";
    private static Log log = LogFactory.getLog(CloudTransDao.class);
	
    public CloudTransDao() { }
    
    /**
     * TOMS Cloud HUB 서버 연결 체크
     * 
     * @param batchVo
     * @throws Exception
     */
    public boolean selectConnect2Server(BatchVo batchVo) {
    	boolean rst = true;
    	Map returnMap = new HashMap();
    	
    	try {
	    	ParameterVo params = batchVo.getParameter();
	    	Map paramMap = params.getMap();
	    	
	    	String url = StringHelper.null2void(paramMap.get(Consistent.IF_PARAMETER_URL)); // URL = 클라우드 주소 + 업무경로
	    	
	    	CloseableHttpResponse response = this.getReponse(url, paramMap);
	    	int statusCode = response.getStatusLine().getStatusCode();
	        
	    	String lang = batchVo.getLanguage();
	    	if(lang == null) lang = this.properties.getProperty("application.time.nation");
	    	
	        if(statusCode == 200) {  // 정상적으로 처리된 경우
	        	returnMap.put("SERVER_CONNECT_YN", "Y");
	        	
	        	batchVo.setReturnData(returnMap);
	        } else {
	        	if(statusCode == 404) {
	        		batchVo.setErrorMessage(this.getMessage("MSG_NOT_FOUND_SERVER", null, lang));
	        	} else {
	        		batchVo.setErrorMessage(this.getMessage("MSG_FAIL_SERVER_RESPONSE", null, lang) + "[" + this.getMessage("ERROR,CODE", null, lang) +" = " + statusCode + "]");
	        	}
	        	
	        	returnMap.put("SERVER_CONNECT_YN", "N");
	        	
	        	batchVo.setReturnData(returnMap);
	        	
	        	rst = false;
	        }
    	} catch(Exception e) {
    		if(log.isErrorEnabled()) log.error(e);
    		batchVo.setErrorMessage(e.getMessage());
    		
    		returnMap.put("SERVER_CONNECT_YN", "N");
        	
        	batchVo.setReturnData(returnMap);
        	
        	rst = false;
    	}
        
    	log.debug("return value = " + batchVo.getReturnData());
    	
        return rst;
    }
    
    /**
     * TOMS Cloud 가입사 라이센스 인증
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public boolean selectCertifyHubCompany(BatchVo batchVo) {
    	boolean rst = true;
    	Map returnMap = new HashMap();
    	
    	String lang = batchVo.getLanguage();
    	if(lang == null) lang = this.properties.getProperty("application.time.nation");
    	
    	try {
	    	ParameterVo params = batchVo.getParameter();
	    	Map paramMap = params.getMap();
	    	
	    	String url = StringHelper.null2void(paramMap.get(Consistent.IF_PARAMETER_URL)); // URL = 클라우드 주소 + 업무경로
	    	String key = StringHelper.null2void(paramMap.get(Consistent.IF_PARAMETER_TOMS_FTA_CERT_KEY));
	    	String businessNo = StringHelper.null2void(paramMap.get(Consistent.IF_PARAMETER_BUSINESS_NO)); // 사업자 등록번호
	    	
	    	log.info("License key = " + key + ", Business Number = " + businessNo);
	    	
	    	CloseableHttpResponse response = this.getReponse(url, paramMap);
	    	int statusCode = response.getStatusLine().getStatusCode();
	        
	        if(statusCode == 200) { // 정상적으로 처리된 경우
	        	List returnList = this.getList(response, key);
	        	
	        	if(returnList != null && returnList.size() > 0) {
	        		returnMap = (Map) returnList.get(0);
	        		
	        		batchVo.setReturnData(returnMap);
	        	}
	        } else {
	        	if(statusCode == 404) {
	        		batchVo.setErrorMessage(this.getMessage("MSG_NOT_FOUND_SERVER", null, lang));
	        	} else {
	        		batchVo.setErrorMessage(this.getMessage("MSG_FAIL_SERVER_RESPONSE", null, lang) + "[" + this.getMessage("ERROR,CODE", null, lang) +" = " + statusCode + "]");
	        	}
	        	
	        	returnMap.put("SERVICE_OPER_YN", "N");
	        	
	        	batchVo.setReturnData(returnMap);
	        	
	        	rst = false;
	        }
    	} catch(Exception e) {
    		if(log.isErrorEnabled()) log.error(e);
    		batchVo.setErrorMessage(e.getMessage());
    		
    		returnMap.put("SERVICE_OPER_YN", "N");
        	
        	batchVo.setReturnData(returnMap);
        	
        	rst = false;
    	}
        
        return true;
    }
    
	/**
     * TOMS Cloud HUB 송/수신
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List<Object> callRemoteFunction(BatchVo batchVo) throws Exception {
    	List<Object> returnList = null;
    	Map returnMap = new HashMap();
    	
    	String lang = batchVo.getLanguage();
    	if(lang == null) lang = this.properties.getProperty("application.time.nation");
    	
    	try {
	    	ParameterVo params = batchVo.getParameter();
	    	Map paramMap = params.getMap();
	    	
	    	String url = StringHelper.null2void(paramMap.get(Consistent.IF_PARAMETER_URL)); // URL = 클라우드 주소 + 업무경로
	    	String key = StringHelper.null2void(paramMap.get(Consistent.IF_PARAMETER_TOMS_FTA_CERT_KEY));
	    	String businessNo = StringHelper.null2void(paramMap.get(Consistent.IF_PARAMETER_BUSINESS_NO)); // 사업자 등록번호
	    	
	    	log.debug("License key = " + key + ", Business Number = " + businessNo);
	    	
	    	CloseableHttpResponse response = this.getReponse(url, paramMap);
	    	int statusCode = response.getStatusLine().getStatusCode();
	        
	        if(statusCode == 200) { // 정상적으로 처리된 경우
	        	returnList = this.getList(response, key); // HUB에서 추출한 정보
	        	
	        	String id = batchVo.getJcoId();
	            JcoMapHelper jcoMap = new JcoMapHelper(id);
	            
	            List<String> tableParam = jcoMap.getTableParameterName();
	            
	            for (int i = 0; i < tableParam.size(); i++) {
	            	Map inMap = new HashMap();
	            	
	            	String tableName = tableParam.get(i);
	            	List list = jcoMap.getTableColumnName(i);
	            	int itemRowCount = 0;
	            	String companyCd = StringHelper.null2void(batchVo.getMap().get(Consistent.IF_JOB_COMPANY_CD));
	            	
	            	inMap.put(Consistent.IF_JOB_COMPANY_CD, companyCd);
	            	inMap.put(Consistent.IF_BATCH_INTERFACE_CD, id);
	            	
	            	Map infMap = this.selectInterfaceItemMst(inMap);
	            	
	            	inMap.put("SOURCE_TABLE", StringHelper.null2string(infMap.get(Consistent.IF_BATCH_SOURCE_TABLE), tableName));
		        	inMap.put("TARGET_TABLE", infMap.get(Consistent.IF_BATCH_TARGET_TABLE));
		        	
	            	// SY 테이블의 기 저장데이터 삭제
	                this.deleteSourceTable(inMap);
	                // TR 테이블의 기 저장데이터 삭제
	                this.deleteTargetTable(inMap);
	                
	            	for(int v = 0; v < returnList.size(); v++) {
	            		Map vMap = (Map) returnList.get(v);
	            		List<Map<String, Object>> dataList = new ArrayList();
	            		
		            	for(int t = 0; t < list.size(); t++) {
		            		Map<String, Object> colAtrMap = (Map) list.get(t);
		            		Map<String, Object> dataMap = new HashMap();
		            		
		                	String colName = StringHelper.null2void(colAtrMap.get("COLUMN_NAME"));
		                	String type = StringHelper.null2void(colAtrMap.get("COLUMN_TYPE"));
		                	
		                	dataMap.put("COLUMN_NAME", colName);
		                	dataMap.put("COLUMN_TYPE", type);
		                	log.debug(colName + " > " + type);
		                	if(type.equals("4")) {
		                		String byteStr = StringHelper.null2void(vMap.get(colName));
		                		
		                		if(!byteStr.isEmpty()) {
		                			byte[] byteData = StringUtil.fileBase64Decoder(byteStr);
		                			
		                			dataMap.put("COLUMN_VALUE", byteData);
		                		}
		                		
		                		log.debug("byte type = " + dataMap.toString());
		                		
		                		// BYTE 타입의 데이터는 이력 테이블에 등록할 수 없기 때문에 삭제한다.
		                		vMap.put(colName, "");
		                	} else {
		                		dataMap.put("COLUMN_VALUE", StringHelper.null2void(vMap.get(colName)));
		                	}
		                	
		                	dataList.add(dataMap);
		                }
		            	
		            	inMap.put("COLUMN_LIST", dataList);
		            	
		                // SY 테이블 저장
		                this.insertSourceTable(inMap);
	            	}
	            }
	        } else {
	        	if(statusCode == 404) {
	        		batchVo.setErrorMessage(this.getMessage("MSG_NOT_FOUND_SERVER", null, lang));
	        	} else {
	        		batchVo.setErrorMessage(this.getMessage("MSG_FAIL_SERVER_RESPONSE", null, lang) + "[" + this.getMessage("ERROR,CODE", null, lang) +" = " + statusCode + "]");
	        	}
	        }
    	} catch(Exception e) {
    		if(log.isErrorEnabled()) log.error(e);
    		batchVo.setErrorMessage(e.getMessage());
    	}
        
        return returnList;
    }
    
    /**
     * 서버 요청
     * 
     * @param url 요청 URL
     * @param params 요청 파라메터
     * @return 응답객체
     * @throws Exception
     */
    public CloseableHttpResponse getReponse(String url, Map params) throws Exception {
    	log.debug("Request URL = " + url);
    	String encode = null;
    	
    	try {
	    	encode = this.properties.getProperty("application.file.encoding");
	    	if(encode == null) encode = this.DEFAULT_ENCODING;
    	} catch(Exception e) {
    		encode = this.DEFAULT_ENCODING;
    	}
    	
    	CloseableHttpResponse response = null;
    	
        try {
        	CloseableHttpClient httpclient = null;
        	
        	if(url.startsWith("https://")) {
        		SSLConnectionSocketFactory scsf = new SSLConnectionSocketFactory(
        			     SSLContexts.custom().loadTrustMaterial(null, new TrustSelfSignedStrategy()).build(), 
        			        NoopHostnameVerifier.INSTANCE);
        		
        		httpclient = HttpClients.custom().setSSLSocketFactory(scsf).build();
        		
//        		SSLContextBuilder builder = new SSLContextBuilder();
//    	    	builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
//    	    	SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build());
//    	    	
//    	    	httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        	} else {
        		httpclient = HttpClientBuilder.create().build();
        	}
        	
            HttpPost httpPost = new HttpPost(url);
            
	    	httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded; text/html; charset=UTF-8");
	        httpPost.setHeader("Cache-Control", "no-cache");
	        
	        List<NameValuePair> nvps = null;
	        
	        if(params != null) {
	        	nvps = makeParams(params);
	        }
	        
	        // 한글 인코딩을 위하여 인코딩 정보를 설정한다.
	        httpPost.setEntity(new UrlEncodedFormEntity(nvps, encode));
	        
	        response = httpclient.execute(httpPost);
	        int statusCode = response.getStatusLine().getStatusCode();
	        
	        if(log.isDebugEnabled()) {
	            log.debug("\nSending 'POST' request to URL : " + httpPost.getURI());            
	            log.debug("Post parameters : " + httpPost.getEntity().toString());
	            log.debug("Response Code : " + statusCode );
	        }
        } catch(Exception e) {
        	throw e;
        }
        
        return response;
    }
    
    /**
     * HTTP 요청 시 넘겨줄 파라메터 생성
     * @param params
     * @return
     * @throws Exception
     */
    private List<NameValuePair> makeParams(Map<String, Object> params) throws Exception {
        int hashSize = params.size();
        Iterator keyset = params.keySet().iterator();
        List <NameValuePair> nvps = new ArrayList <NameValuePair>();
        
        for (int i = 0; i < hashSize; i++) {
            String key = (String) keyset.next();
            
            nvps.add(new BasicNameValuePair(key, StringHelper.null2void(params.get(key))));
        }
        
        return nvps;
    }
    
    /**
     * 응답된 HTML 데이터에서 복호화된 값을 리턴
     * 
     * @param response
     * @param key
     * @return
     * @throws Exception
     */
    public List getList(CloseableHttpResponse response, String key) throws Exception {
    	BufferedReader rd = null;
    	StringBuffer result = new StringBuffer();
    	String line = null;
    	
    	try {
	    	rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	        
	        while ((line = rd.readLine()) != null) {
	            result.append(line);
	        }
    	} catch(Exception e) {
    		throw e;
    	} finally {
    		if(rd != null) rd.close();
    	}
    	
        return this.getListForHtml(result.toString(), key);
    }
    
    /**
     * 응답된 HTML 데이터에서 복호화된 값을 리턴
     * 
     * @param response
     * @param key
     * @return
     * @throws Exception
     */
    public Map getMap(CloseableHttpResponse response, String key) throws Exception {
    	BufferedReader rd = null;
    	StringBuffer result = new StringBuffer();
    	String line = null;
    	
    	try {
    		rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	        
	        while ((line = rd.readLine()) != null) {
	            result.append(line);
	        }
    	} catch(Exception e) {
    		throw e;
    	} finally {
    		if(rd != null) rd.close();
    	}
    	
        return this.getMapForHtml(result.toString(), key);
    }
    
    /**
     * 수신된  List정보를 복호화해서 리턴
     * 
     * @param html
     * @param key
     * @return
     * @throws Exception
     */
    private List<Object> getListForHtml(String html, String key) throws Exception {
    	List dataList = null;
    	
    	if(html != null && html.length() > 2) {
    		log.debug("HTML data = " + html);

    		dataList = JsonUtil.getList(html);
    		dataList = StringUtil.aes256ToList(dataList, key, "decode");
    		
    		log.debug("HTML data list(after) = " + dataList.size() + ", data = " + dataList.get(0));
    	} else {
    		dataList = new ArrayList();
    	}
    	
    	return dataList;
    }
    
    /**
     * 수신된  Map정보를 복호화해서 리턴
     * 
     * @param html
     * @param key
     * @return
     * @throws Exception
     */
    private Map<String, Object> getMapForHtml(String html, String key) throws Exception {
    	Map dataMap = null;
    	
    	if(html != null && html.length() > 2) {
    		dataMap = JsonUtil.getMap(html);
    		
    		log.debug("HTML data map(before) = " + dataMap.toString());
    		
    		dataMap = StringUtil.aes256ToMap(dataMap, key, "decode");
    		
    		log.debug("HTML data map(after) = " + dataMap.toString());
    	} else {
    		dataMap = new HashMap();
    	}
    	
    	return dataMap;
    }
    
    /**
	 * HUB 가입 협력사 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectPoVendorForHubList(JobVo jobVo) throws Exception {
		Map paramMap = jobVo.getMap();
    	
		return this.list("DI3008.selectPoVendorForHubList", paramMap);
	}
	
	/**
     * TOMS Cloud HUB 데이터를 저장하고 있는 SY테이블을 조회한다.
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List<Object> selectSourceTable(Map map) throws Exception {
        List<Object> returnValue = null;
        
        try {
            returnValue = list("MMHUBBATCH.selectSourceTable", map);
        } catch (Exception exp) {
        	if(log.isErrorEnabled()) log.error(exp.getCause());
            throw exp;
        }

        return returnValue;
    }
    
    /**
     * TOMS Cloud HUB 데이터를  SY테이블에서 삭제한다.
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int deleteSourceTable(Map map) throws Exception {
        int returnValue = 0;
        
        try {
            returnValue = delete("MMHUBBATCH.deleteSourceTable", map);
        } catch (Exception exp) {
        	if(log.isErrorEnabled()) log.error(exp.getCause());
            throw exp;
        }

        return returnValue;
    }
    
    /**
     * TOMS Cloud HUB 데이터를  TR테이블에서 삭제한다.
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int deleteTargetTable(Map map) throws Exception {
        int returnValue = 0;
        
        try {
            returnValue = delete("MMHUBBATCH.deleteTargetTable", map);
        } catch (Exception exp) {
        	if(log.isErrorEnabled()) log.error(exp.getCause());
            throw exp;
        }

        return returnValue;
    }
    
	/**
     * TOMS Cloud HUB 데이터를  SY테이블에 저장한다.
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int insertSourceTable(Map map) throws Exception {
        int returnValue = 0;
        
        try {
            returnValue = update("MMHUBBATCH.insertSourceTable", map);
        } catch (Exception exp) {
        	if(log.isErrorEnabled()) log.error(exp.getCause());
            throw exp;
        }

        return returnValue;
    }
    
	/**
     * 인터페이스 항목정보를 조회한다.
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map<String, Object> selectInterfaceItemMst(Map map) throws Exception {
        Map<String, Object> returnValue = null;
        
        try {
            returnValue = (Map) this.selectByPk("MMHUBBATCH.selectInterfaceItemMst", map);
        } catch (Exception exp) {
        	if(log.isErrorEnabled()) log.error(exp.getCause());
            throw exp;
        }

        return returnValue;
    }
    
}
