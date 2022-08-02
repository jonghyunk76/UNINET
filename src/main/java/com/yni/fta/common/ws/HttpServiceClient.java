package com.yni.fta.common.ws;

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
import org.apache.http.ssl.SSLContexts;

import com.yni.fta.common.batch.vo.BatchVo;
import com.yni.fta.common.tools.StringUtil;

import kr.yni.frame.dao.YniAbstractDAO;
import kr.yni.frame.util.JsonUtil;
import kr.yni.frame.util.StringHelper;

public class HttpServiceClient extends YniAbstractDAO {
	
	private static Log log = LogFactory.getLog(HttpServiceClient.class);
	
	private static final String DEFAULT_ENCODING = "utf-8";
	
	/**
	 * Http 요청하기
	 * 
	 * @param batchVo 배치정보
	 * @param url 요청URL
	 * @param datas 전송할 데이터
	 * @param key AES 암.복호화 Key
	 * @return
	 * @throws Exception
	 */
	public Object executeInterface(BatchVo batchVo, String url, Map datas, String key) throws Exception {
    	Map returnMap = new HashMap();
    	
    	try {	
	    	String lang = batchVo.getLanguage();
	    	if(lang == null) lang = this.properties.getProperty("application.time.nation");
	    	
	    	CloseableHttpResponse response = this.getReponse(url, datas);
	    	int statusCode = response.getStatusLine().getStatusCode();
	        
	        if(statusCode == 200) { // 정상적으로 처리된 경우
	        	List returnList = this.getList(response, key);
	        	
	        	if(returnList != null && returnList.size() > 0) {
	        		returnMap = (Map) returnList.get(0);
	        	}
	        } else {
	        	if(statusCode == 404) {
	        		batchVo.setErrorMessage(this.getMessage("MSG_NOT_FOUND_SERVER", null, lang));
	        	} else {
	        		batchVo.setErrorMessage(this.getMessage("MSG_FAIL_SERVER_RESPONSE", null, lang) + "[" + this.getMessage("ERROR,CODE", null, lang) +" = " + statusCode + "]");
	        	}
	        	
	        	returnMap.put("SERVICE_OPER_YN", "N");
	        }
    	} catch(Exception e) {
    		if(log.isErrorEnabled()) log.error(e);
    		batchVo.setErrorMessage(e.getMessage());
    		
    		returnMap.put("SERVER_CONNECT_YN", "N");
    	}
        
    	log.debug("return value = " + batchVo.getReturnData());
    	
        return returnMap;
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
     * 
     * @param params
     * @return
     * @throws Exception
     */
    private List<NameValuePair> makeParams(Map<String, Object> params) throws Exception {
        int hashSize = params.size();
        Iterator keyset = params.keySet().iterator();
        List <NameValuePair> nvps = new ArrayList <NameValuePair>();
        
        for(int i = 0; i < hashSize; i++) {
            String key = (String) keyset.next();
            
            nvps.add(new BasicNameValuePair(key, StringHelper.null2void(params.get(key)))); // POST방식으로 전송하기 위한 데이터 추가
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
    		
    		if(dataList != null && dataList.size() > 0) {
	    		// AES256 복호화
	    		if(key != null && !key.isEmpty()) {
	    			dataList = StringUtil.aes256ToList(dataList, key, "decode");
	    		}
	    		
	    		log.debug("HTML data list(after) = " + dataList.size() + ", data = " + dataList.get(0));
    		} else {
    			log.warn("Html conversion data is null >> " + dataList);
    		}
    	} else {
    		dataList = new ArrayList();
    	}
    	
    	return dataList;
    }
    
}
