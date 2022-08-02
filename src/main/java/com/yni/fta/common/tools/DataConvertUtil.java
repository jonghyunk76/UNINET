package com.yni.fta.common.tools;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.XML;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.JsonObject;
import com.siebel.data.SiebelPropertySet;

import kr.yni.frame.util.JsonUtil;

public class DataConvertUtil {
	
	private static Log log = LogFactory.getLog(DataConvertUtil.class);
	
	/**
	 * 요청하는 데이터 타입에 따라 변환 후 린터
	 * 
	 * @param data 소스
	 * @param type 데이터 타입(JSON, FILE, XML, OBJECT)
	 * @return
	 * @throws Exception
	 */
	public static Object getConvertData(Object data, String type) throws Exception {
		Object iobj = data;
		
		try {
			// FILE과 OBJECT타입은 변환하지 않고 그대로 리턴됨
			if(data != null) {
				if(data instanceof SiebelPropertySet) {
					SiebelPropertySet out = (SiebelPropertySet) data;
					JsonObject json = EAIJSONConverter.PropertySetToJsonObject(out, null);
					
					if(type.equals("JSON")) iobj = json;
					else if(type.equals("XML")) iobj = XML.toString(json);
				} else if(data instanceof Map) {
					Map out = (Map) data;
					String json = JsonUtil.getViewJson(out);
					
					if(type.equals("JSON")) {
						JSONParser parser = new JSONParser();
						Object obj = parser.parse(json);
						
						iobj = (JSONObject) obj;
					} else if(type.equals("XML")) iobj = XML.toString(json);
				} else if(data instanceof List) {
					List out = (List) data;
					String json = JsonUtil.getViewJson(out);
					
					if(type.equals("JSON")) {
						JSONParser parser = new JSONParser();
						Object obj = parser.parse(json);
						
						iobj = (JSONObject) obj;
					}
					else if(type.equals("XML")) iobj = XML.toString(json);
				} else if(data instanceof String) {
					String json = data.toString();
					
					if(type.equals("JSON")) {
						JSONParser parser = new JSONParser();
						Object obj = parser.parse(json);
						
						iobj = (JSONObject) obj;
					}
					else if(type.equals("XML")) iobj = json;
				}
	        }
		} catch(Exception e) {
			log.error("데이터 변환 실패 : " + e);
			throw e;
		}
		
		return iobj;
	}
	
	
	/**
	 * 요청하는 데이터 타입에 따라 변환 후 린터
	 * 
	 * @param data 소스
	 * @param type 데이터 타입(JSON, FILE, XML, OBJECT)
	 * @return
	 * @throws Exception
	 */
	public static Map getConvertMap(Object data, String type) throws Exception {
		Map result = null;
		
		if(data instanceof String) {
			String str = data.toString();
			
			if(type.equals("JSON")) {
				result = JsonUtil.getMap(str);
			} else if(type.equals("XML")) {
				result = JsonUtil.getXMLMap(str);
			}
		}
		
		return result;
	}
	
	/**
	 * Oject 객체의 타입에 따라 Map으로 변환
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static Map getObjectToMap(Object data) throws Exception {
		if(data instanceof Map) {
			return (Map) data;
		} else if(data instanceof JsonObject) {
			return JsonUtil.getMap(data.toString());
		} else {
			log.info("Map객체로 변환하기 위한 소스개발을 추가해야 합니다.");
		}
		
		return null;
	}
	
}
