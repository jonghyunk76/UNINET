package com.yni.fta.common.batch.delegate.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yni.fta.common.batch.delegate.Transfer;
import com.yni.fta.common.batch.vo.BatchVo;
import com.yni.fta.common.batch.vo.ParameterVo;
import com.yni.fta.common.tools.DataConvertUtil;
import com.yni.fta.common.ws.HttpServiceClient;
import com.yni.fta.common.ws.RestServiceClient;
import com.yni.fta.common.ws.WebServiceClient;

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
	 * @param datas Map타입의 전송정보
	 * @return 배치결과
	 * @throws Exception
	 */
	public Object receive(BatchVo batchVo, Map map, Object datas) throws Exception {
		Object res_data = null;
		
		String impFormat = StringHelper.null2void(map.get("IMP_DATA_FORMAT")); // Import 데이터 형식 : JSON, FILE, XML, OBJECT
    	String secure = StringHelper.null2string(map.get("SECURE_YN"), "N"); // 보안설정(https) 적용여부
    	String serverId = StringHelper.null2void(map.get("SERVER_ID"));
    	String ip = StringHelper.null2void(map.get("SERVER_IP"));
    	int port = StringHelper.null2zero(map.get("SERVER_PORT"));
    	String path = StringHelper.null2void(map.get("DATA_PATH")); // 요청URL
		
    	Configurator configurator = ConfiguratorFactory.getInstance().getConfigurator();
    	String cnnType = StringHelper.null2void(configurator.getString(serverId + ".secure.type")); // TOKEN, SOAP, AES
			
		log.debug("Receive protocal info = " + cnnType + ", server id = " + serverId);
		
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
				
				res_data = client.executeInterface(batchVo, reqURL, datas, token);
			} else if(cnnType.equals("SOAP")) { // SOAP 통신
				WebServiceClient client = new WebServiceClient();
				
				res_data = null;
			} else if(cnnType.equals("BASIC")) { // 암호화
				HttpServiceClient client = new HttpServiceClient();
				String key = StringHelper.null2void(configurator.getString(serverId + ".secure.aes.secret.key"));
				
				res_data = client.executeInterface(batchVo, reqURL, DataConvertUtil.getConvertMap(datas, impFormat), key);
			} else { // SSL, TLS 통신
				HttpServiceClient client = new HttpServiceClient();
				
				res_data = client.executeInterface(batchVo, reqURL, DataConvertUtil.getConvertMap(datas, impFormat), null);
			}
		} catch(Exception e) {
			log.error(e);
			
			batchVo.setErrorMessage("서버와의 통신이 실패했습니다.");
		}
		
		return res_data;
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
	public Object send(BatchVo batchVo, Map map, Object datas) throws Exception {
		Object res_data = null;
		
		String impFormat = StringHelper.null2void(map.get("IMP_DATA_FORMAT")); // Import 데이터 형식 : JSON, FILE, XML, OBJECT
		int pageNum = StringHelper.null2zero(map.get("TRAN_ROW_NUM")); // 한번에 전송할 데이터건수
		String secure = StringHelper.null2string(map.get("SECURE_YN"), "N"); // 보안설정(https) 적용여부
    	String serverId = StringHelper.null2void(map.get("SERVER_ID"));
    	String ip = StringHelper.null2void(map.get("SERVER_IP"));
    	int port = StringHelper.null2zero(map.get("SERVER_PORT"));
    	String path = StringHelper.null2void(map.get("DATA_PATH")); // 요청URL
    	
    	// 서비스 호출방식은 properties을 적용함
		Configurator configurator = ConfiguratorFactory.getInstance().getConfigurator();
    	String cnnType = StringHelper.null2void(configurator.getString(serverId + ".secure.type")); // TOKEN, SOAP, BASIC, Rest
			
		log.debug("Send protocal info = " + cnnType + ", server id = " + serverId);
		
		String reqURL = null;
		
		if(port == 80) reqURL = ip + path;
		else reqURL = ip + ":" + port + path;
		
		// SSL 보안 적용
		if(secure.equals("Y")) reqURL = "https://" + reqURL;
		else reqURL = "http://" + reqURL;
		
		try {
			List<Object> plist = new ArrayList<Object>();
			
			// 응답요청을 위한 파라메터 변경 수행
			if(datas instanceof List) {
				plist = (List) datas;
			} else {
				plist.add(datas);
			}
			
			for(int i = 0; i < plist.size(); i++) {
				if(cnnType.equals("TOKEN")) { // 토큰 인증서 연결
					RestServiceClient client = new RestServiceClient();
					String token = client.getToken4Property(serverId); // Access Token 구하기
					
					res_data = client.executeInterface(batchVo, reqURL, plist.get(i), token);
				} else if(cnnType.equals("SOAP")) { // SOAP 통신
					WebServiceClient client = new WebServiceClient();
					
					res_data = null;
				} else if(cnnType.equals("BASIC")) { // 암호화
					HttpServiceClient client = new HttpServiceClient();
					String key = StringHelper.null2void(configurator.getString(serverId + ".secure.aes.secret.key"));
					
					res_data = client.executeInterface(batchVo, reqURL, DataConvertUtil.getConvertMap(plist.get(i), impFormat), key);
				} else { // SSL, TLS 통신
					HttpServiceClient client = new HttpServiceClient();
					
					res_data = client.executeInterface(batchVo, reqURL, DataConvertUtil.getConvertMap(plist.get(i), impFormat), null);
				}
			}
		} catch(Exception e) {
			log.error(e);
			
			batchVo.setErrorMessage("서버와의 통신이 실패했습니다.");
		}
		
		return res_data;
	}

}
