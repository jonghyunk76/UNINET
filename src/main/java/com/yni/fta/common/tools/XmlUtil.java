package com.yni.fta.common.tools;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.json.XML;

import kr.yni.frame.util.JsonUtil;

public class XmlUtil {
	
	private static Log log = LogFactory.getLog(XmlUtil.class);
	
	/**
	 * MXL 정보를 Map에 담아 리턴
	 * 
	 * @param xml
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> xml2Map(String xml) throws Exception {
		JSONObject xmlJSONObj = XML.toJSONObject(xml);

		Map<String, Object> result = JsonUtil.getMap(xmlJSONObj.toString());
		
		log.debug("Convert map to XML = " + result.toString());
		
		return result;
	}

	/**
	 * List<Map> 정보를 Json으로 변환해선 리턴
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public static <K, V> String  map2Xml(List list) throws Exception {
		String jsonData = JsonUtil.getViewJson(list);

		String xml = XML.toString(new JSONObject(jsonData));

		return xml;
	}

	/**
	 * XML정보를 Json타입으로 변환해서 리턴
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	public static String json2Xml(String json) throws Exception {
		JSONObject jsonData = new JSONObject(json);

		String xml = XML.toString(jsonData);
		
		log.debug("Convert json to XML = " + xml);
		
		return xml;

	}
}
