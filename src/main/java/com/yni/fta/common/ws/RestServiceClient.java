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
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import kr.yni.frame.config.Configurator;
import kr.yni.frame.config.ConfiguratorFactory;
import kr.yni.frame.util.JsonUtil;
import kr.yni.frame.util.StringHelper;

/**
 * Access Token Client 프로그램
 * 
 * @author ador2
 *
 */
public class RestServiceClient {
	
	private static Log log = LogFactory.getLog(RestServiceClient.class);
	
	private static Map<String, String> tokens = new HashMap<String, String>();
	
	public RestServiceClient() {}
	
	public void setTokenId(String id, String token) {
		this.tokens.put(id, token);
	}
	
	public String getTokenId(String id) {
		return tokens.get(id);
	}
	
	public String getToken(Map map) throws Exception {
		String token = null;
		
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(StringHelper.null2void(map.get("TOKEN_URL")));
			
			List<NameValuePair> postParams = new ArrayList<NameValuePair>();
			
	        postParams.add(new BasicNameValuePair("grant_type", "password"));
	        postParams.add(new BasicNameValuePair("username", StringHelper.null2void(map.get("USER_ID"))));
	        postParams.add(new BasicNameValuePair("password", StringHelper.null2void(map.get("USER_PW"))));
	        postParams.add(new BasicNameValuePair("client_id", StringHelper.null2void(map.get("CLIENT_ID"))));
	        postParams.add(new BasicNameValuePair("client_secret", StringHelper.null2void(map.get("CLIENT_SECRET"))));
	        
			httppost.setEntity(new UrlEncodedFormEntity(postParams));
	        
	        // Execute HTTP Post Request
	        HttpResponse response = httpclient.execute(httppost);
	        
	        JSONObject json_auth = new JSONObject(EntityUtils.toString(response.getEntity()));
	        token = json_auth.getString("access_token");
	        
	        log.debug("Create Token data = " + token);
		} catch (Exception e) {
			log.error(e);
		}
		
		return token;
	}
	
	/**
	 * 서버ID에 대한 properties 설정파일을 읽어 Token 인증번호를 구함 
	 * 
	 * @param serverId 서버ID
	 * @return
	 * @throws Exception
	 */
	public String getToken4Property(String serverId) throws Exception {
		String token = this.getTokenId(serverId);
		
		if(token != null && !token.isEmpty()) {
			return token;
		}
		
		try {
			Configurator configurator = ConfiguratorFactory.getInstance().getConfigurator();
			
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(StringHelper.null2void(configurator.getString(serverId + ".secure.token.url")));
			
			List<NameValuePair> postParams = new ArrayList<NameValuePair>();
			
	        postParams.add(new BasicNameValuePair("grant_type", "password"));
	        postParams.add(new BasicNameValuePair("username", StringHelper.null2void(configurator.getString(serverId + ".secure.token.user.name"))));
	        postParams.add(new BasicNameValuePair("password", StringHelper.null2void(configurator.getString(serverId + ".secure.token.password"))));
	        postParams.add(new BasicNameValuePair("client_id", StringHelper.null2void(configurator.getString(serverId + ".secure.token.client.id"))));
	        postParams.add(new BasicNameValuePair("client_secret", StringHelper.null2void(configurator.getString(serverId + ".secure.token.secret.key"))));
	        
			httppost.setEntity(new UrlEncodedFormEntity(postParams));
	        
	        // Execute HTTP Post Request
	        HttpResponse response = httpclient.execute(httppost);
	        
	        JSONObject json_auth = new JSONObject(EntityUtils.toString(response.getEntity()));
	        token = json_auth.getString("access_token");
	        
	        log.debug("Create Token data = " + token);
	        
	        this.setTokenId(serverId, token);
		} catch (Exception e) {
			log.error(e);
		}
		
		return token;
	}
	
	/**
	 * TOKEN 인증을 적용한 데이터 통신
	 * 
	 * @param map
	 * @return 응답결과를 Map에 담아 리턴
	 * @throws Exception
	 */
	public Map executeInterface(Map map) throws Exception {
		CloseableHttpResponse response = null;
		Map result = null;
		
        try {
        	String url = StringHelper.null2void(map.get("REQ_URL"));
        	String serverId = StringHelper.null2void(map.get("SERVER_ID"));
        	String token = StringHelper.null2void(map.get("ACCESS_TOKEN"));
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
            
	    	httpPost.setHeader("Content-Type", "application/json");
	        httpPost.setHeader("Cache-Control", "no-cache");
	        httpPost.setHeader("Authorization", "OAuth " + token);
	        
	        String json_req = StringHelper.null2void(map.get("REQ_PARAMS"));
	        
	        log.debug("Request parameter = " + json_req + ", URL = " + httpPost.getURI().getPath());
	        
	        // 한글 인코딩을 위하여 인코딩 정보를 설정한다.
	        httpPost.setEntity(new StringEntity(json_req, "utf-8"));
	        
	        response = httpclient.execute(httpPost);
	        int statusCode = response.getStatusLine().getStatusCode();
	        
	        // 실패한 경우에 Token를 다시 받은 후 재실행을 요청한다.
	        if(statusCode != 200) {
	        	this.tokens.remove(serverId);
	        	token = this.getToken4Property(serverId);
	        	
	        	httpPost.setHeader("Authorization", "OAuth " + token);
	        	
	        	response = httpclient.execute(httpPost);
		        statusCode = response.getStatusLine().getStatusCode();
	        }
	        
	        if(log.isDebugEnabled()) {
	            log.debug("\nSending 'POST' request to URL : " + httpPost.getURI());            
	            log.debug("Post parameters : " + httpPost.getEntity().toString());
	            log.debug("Response Code : " + statusCode );
	        }
	        
	        BufferedReader rd = null;
	    	String line = null;
	    	StringBuffer buf = new StringBuffer();
	    	
	        if(statusCode == 200) { // 정상적으로 처리된 경우
	        	try {
	    	    	rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "utf-8"));
	    	        
	    	        while ((line = rd.readLine()) != null) {
	    	        	buf.append(line);
	    	        }
	        	} catch(Exception e) {
	        		throw e;
	        	} finally {
	        		if(rd != null) rd.close();
	        	}
	        } else {
	        	throw new Exception("Not found access token.");
	        }
	        
	        log.debug("Reponse data = " + buf.toString());
	        
	        if(buf.length() > 0) {
	        	result = JsonUtil.getMap(buf.toString());
	        }
        } catch(Exception e) {
        	log.error(e);
        	throw e;
        }
        
		return result;
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
        
        for (int i = 0; i < hashSize; i++) {
            String key = (String) keyset.next();
            
            nvps.add(new BasicNameValuePair(key, StringHelper.null2void(params.get(key))));
        }
        
        return nvps;
    }
    
}
