package com.yni.fta.common.batch.delegate.impl;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yni.fta.common.batch.delegate.Transfer;
import com.yni.fta.common.batch.vo.BatchVo;
import com.yni.fta.common.batch.vo.ParameterVo;
import com.yni.fta.common.ws.HttpServiceClient;
import com.yni.fta.common.ws.RestServiceClient;

import kr.yni.frame.config.Configurator;
import kr.yni.frame.config.ConfiguratorFactory;
import kr.yni.frame.util.DataMapHelper;
import kr.yni.frame.util.StringHelper;

public class HttpTransfer implements Transfer {
	
	private static Log log = LogFactory.getLog(HttpTransfer.class);
	
	/**
	 * 데이터 수신
	 * 
	 * @param batchVo 배치정보
	 * @param map
	 * @param pvo
	 * @return 배치결과
	 * @throws Exception
	 */
	public boolean receive(BatchVo batchVo, Map map, ParameterVo pvo) throws Exception {
		boolean result = true;
		
    	String secure = StringHelper.null2string(map.get("SECURE_YN"), "N"); // 보안설정(https) 적용여부
    	String serverId = StringHelper.null2void(map.get("SERVER_ID"));
    	String ip = StringHelper.null2void(map.get("SERVER_IP"));
    	int port = StringHelper.null2zero(map.get("SERVER_PORT"));
    	String path = StringHelper.null2void(map.get("DATA_PATH")); // 요청URL
		
    	Configurator configurator = ConfiguratorFactory.getInstance().getConfigurator();
    	String cnnType = StringHelper.null2void(configurator.getString(serverId + ".secure.type")); // TOKEN, SOAP, AES
			
		log.debug("Receive protocal info = " + cnnType + ", server id = " + serverId);
		
		Map httpMap = pvo.getMap();
		String reqURL = null;
		
		if(port == 80) reqURL = ip + path;
		else reqURL = ip + ":" + port + path;
		
		// SSL 보안 적용
		if(secure.equals("Y")) reqURL = "https://" + reqURL;
		else reqURL = "http://" + reqURL;
		
		try {
			if(cnnType.equals("TOKEN")) { // 토큰 인증서 연결
				RestServiceClient client = new RestServiceClient();
				String token = client.getToken4Property(serverId); // Access Token 구하기
				
				httpMap.put("SERVER_ID", serverId);
				httpMap.put("REQ_URL", reqURL);
				httpMap.put("ACCESS_TOKEN", token);
				
				log.debug("http receive parameter = " + httpMap);
				
				Map res_data = client.executeInterface(httpMap);
				
				batchVo.setReturnData(res_data);
			} else if(cnnType.equals("SOAP")) { // SOAP 통신
				Map res_data = null;
				
				batchVo.setReturnData(res_data);
			} else if(cnnType.equals("AES")) { // 암호화
				HttpServiceClient client = new HttpServiceClient();
				String key = StringHelper.null2void(configurator.getString(serverId + ".secure.aes.secret.key"));
				
				result = client.executeInterface(batchVo, reqURL, httpMap, key);
			} else { // SSL, TLS 통신
				HttpServiceClient client = new HttpServiceClient();
				
				result = client.executeInterface(batchVo, reqURL, httpMap, null);
			}
		} catch(Exception e) {
			log.error(e);
			
			batchVo.setErrorMessage("서버와의 통신이 실패했습니다.");
		}
		
		return result;
	}
	
	/**
	 * 데이터 전송
	 * 
	 * @param batchVo = 배치정보
	 * @param map = 인터페이스 정보
	 * @param params = 전송할 데이터
	 * @return
	 * @throws Exception
	 */
	public boolean send(BatchVo batchVo, Map map, Map params) throws Exception {
		boolean result = true;
		
		String secure = StringHelper.null2string(map.get("SECURE_YN"), "N"); // 보안설정(https) 적용여부
    	String serverId = StringHelper.null2void(map.get("SERVER_ID"));
    	String ip = StringHelper.null2void(map.get("SERVER_IP"));
    	int port = StringHelper.null2zero(map.get("SERVER_PORT"));
    	String path = StringHelper.null2void(map.get("DATA_PATH")); // 요청URL
    	
		Configurator configurator = ConfiguratorFactory.getInstance().getConfigurator();
    	String cnnType = StringHelper.null2void(configurator.getString(serverId + ".secure.type")); // TOKEN, SOAP, AES
			
		log.debug("Send protocal info = " + cnnType + ", server id = " + serverId);
		
		String reqURL = null;
		
		if(port == 80) reqURL = ip + path;
		else reqURL = ip + ":" + port + path;
		
		// SSL 보안 적용
		if(secure.equals("Y")) reqURL = "https://" + reqURL;
		else reqURL = "http://" + reqURL;
		
		try {
			if(cnnType.equals("TOKEN")) { // 토큰 인증서 연결
				Map httpMap = params;
				RestServiceClient client = new RestServiceClient();
				String token = client.getToken4Property(serverId); // Access Token 구하기
				
				httpMap.put("SERVER_ID", serverId);
				httpMap.put("REQ_URL", reqURL);
				httpMap.put("ACCESS_TOKEN", token);
				
				log.debug("http send parameter = " + httpMap);
				
				Map res_data = client.executeInterface(httpMap);
				
				batchVo.setReturnData(res_data);
			} else if(cnnType.equals("SOAP")) { // SOAP 통신
				
			} else if(cnnType.equals("AES")) { // 암호화
				HttpServiceClient client = new HttpServiceClient();
				String key = StringHelper.null2void(configurator.getString(serverId + ".secure.aes.secret.key"));
				
				result = client.executeInterface(batchVo, reqURL, params, key);
			} else { // SSL, TLS 통신
				HttpServiceClient client = new HttpServiceClient();
				
				result = client.executeInterface(batchVo, reqURL, params, null);
			}
		} catch(Exception e) {
			log.error(e);
			
			batchVo.setErrorMessage("서버와의 통신이 실패했습니다.");
		}
		
		return result;
	}

}
