package com.yni.fta.common.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kr.yni.frame.Constants;
import kr.yni.frame.config.Configurator;
import kr.yni.frame.config.ConfiguratorFactory;

public class CommonUtil {
	
	private static Log log = LogFactory.getLog(CommonUtil.class);
	
	/**
	 * Grid List 생성
	 * 
	 * @param xml
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> GridListCreate(List list) throws Exception {		
		Map map = new HashMap();
		Map modelMap = new HashMap();
		
		if ((list != null) && (!list.isEmpty())) {
		    map.put("rows", list);
		    map.put("total", ((Map)list.get(0)).get("TOTALCOUNT"));
		    
			if ((map instanceof Map)) {
			    Map mapData = (Map)map;
			    modelMap.putAll(mapData);	    
			}		    
		}
		  
		modelMap.put("success", Boolean.valueOf(true));
		
		return modelMap;
	}
	
	/**
     * 시스템에 설정된 언어를 가져옴
     *  
     * @param comp 회사코드
     * @return
     */
    public static String getContextLanguage(String comp){
		String resultLang = null;
		String companyCode = null;
		
    	try {
    		if(comp == null || comp.isEmpty()) companyCode = "application";
    		else companyCode = comp;
    		
			Configurator configurator = ConfiguratorFactory.getInstance().getConfigurator();
			resultLang = configurator.getString(companyCode + ".context.language");
		} catch (Exception e){
			e.printStackTrace();	
		}
		
    	if(resultLang == null) resultLang = Constants.DEFAULT_LANGUAGE;
    	
		return resultLang;
	}
    
	/**
     * getKeyListMap
     * Get Key from Map of List 
     * @param comp 회사코드
     * @return
     */
    public static List getKeyListMap(List list){    	
    	List keyList = new ArrayList();
    	
		if (!list.isEmpty()) {
			keyList = getKeyMap((Map) list.get(0));		
		}
		
		return keyList;
	}    
    
    /**
     * getKeyMap
     * Get Key from Map
     * @param map
     * @return
     */
    public static List getKeyMap(Map map){    	
    	List keyList = new ArrayList();
    	
        @SuppressWarnings("unchecked")
		Iterator<String> keys = map.keySet().iterator();
        while (keys.hasNext()){
        	String key = keys.next();
        	keyList.add(key);
        	System.out.println(keyList);
        }
        
		return keyList;
	}        	
}
