package com.yni.fta.fm.sm.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yni.fta.common.tools.PythonSupporter;
import com.yni.fta.fm.sm.SM8003;

import kr.yni.frame.service.YniAbstractService;
import kr.yni.frame.util.JsonUtil;
import kr.yni.frame.util.StringHelper;

/**
 * Python 인터페이스 클래스
 * 
 * @author YNI-Framework
 *
 */
@Service("SM8003") 
public class SM8003Impl extends YniAbstractService implements SM8003 {

	/**
	 * 파이선 파일 실행 후 tariff 정보를 생성한다.
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map executePythonFile(Map map) throws Exception {
		Map rstMap = new LinkedHashMap();
		
		String rst = PythonSupporter.excuteScript(map);
		
		log.debug(rst);
		
		List rlist = null; 
				
		try {
			rlist = JsonUtil.getList(rst);
		} catch(Exception e) {
			rlist = new LinkedList();
			
			rst = "[ERROR] " + e.getMessage() + "\n" + rst;
		}
		
		log.debug("data size = " + rlist.size());
		
		if(rlist != null && rlist.size() > 0) {
			List rstList = new LinkedList();
			
		    for(int i = 0; i < rlist.size(); i++) {
		    	Map tmpMap = (Map) rlist.get(i);
		    	
	            String hsCd = null;
	            String naCd = null;
	            String hs_desc = null;
	            String rate_se_desc = null;
	            String rate_desc = null;
	            
	            for (Iterator it = tmpMap.entrySet().iterator(); it.hasNext();) {
	                Map.Entry entry = (Map.Entry) it.next();
	                String key = StringHelper.null2void(entry.getKey());
	                String ckey = key.toUpperCase().replaceAll(" ", "");
	                
	                if(ckey.indexOf("HSCODE") >= 0) hsCd = StringHelper.null2void(tmpMap.get(key));
	                else if(ckey.indexOf("NATION") >= 0) naCd = StringHelper.null2void(tmpMap.get(key));
	                else if(ckey.indexOf("HS_DESC") >= 0) hs_desc = StringHelper.null2void(tmpMap.get(key));
	            }
	            
//	            log.debug("hs code = " + hsCd + ", nation code = " + naCd);
	            
	            if(hsCd != null && naCd != null) {
		            for (Iterator it = tmpMap.entrySet().iterator(); it.hasNext();) {
		                Map.Entry entry = (Map.Entry) it.next();
		                String key = StringHelper.null2void(entry.getKey());
		                String ckey = key.toUpperCase().replaceAll(" ", "");
		                
		                if(!(ckey.indexOf("HSCODE") >= 0 || ckey.indexOf("NATION") >= 0 || ckey.indexOf("HS_DESC") >= 0 || ckey.indexOf("RATE_DESC") >= 0 || ckey.indexOf("RATE_SE_DESC") >= 0)) {
		                	Map pMap = new HashMap();
		                	
		                	pMap.put("HS_CD", hsCd);
		                	pMap.put("HS_DESC", hs_desc);
		                	pMap.put("NATION_CD", naCd);
		                	pMap.put("TARRATE_SE_NM", key); // 세율구분명
		                	pMap.put("RATE_SE_DESC", StringHelper.null2void(tmpMap.get(key+"_RATE_SE_DESC"))); // 세율구분명 원본
		                	pMap.put("TARRATE", StringHelper.null2void(tmpMap.get(key))); // 세율
		                	pMap.put("RATE_DESC", StringHelper.null2void(tmpMap.get(key+"_RATE_DESC"))); // 원문 세율설명
		                	
//		                	log.debug("data = " + pMap.toString());
		                	
		                	rstList.add(pMap);
		                }
		            }
	            }
		    }
		    
			rstMap.put("rows", rstList);
		}
		
		rstMap.put("RESULT_DATA", rst);
		
		return rstMap;
    }
	
}
