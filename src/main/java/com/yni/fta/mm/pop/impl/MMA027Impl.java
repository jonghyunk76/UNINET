package com.yni.fta.mm.pop.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.fta.mm.pop.MMA027;

import kr.yni.frame.Constants;
import kr.yni.frame.service.YniAbstractService;
import kr.yni.frame.util.JcoMapHelper;
import kr.yni.frame.util.StringHelper;

/**
 * 배치 > 인터페이스 이력 데이터 상세 조회
 * 
 * @author YNI-maker
 *
 */
@Service("mma027")
public class MMA027Impl extends YniAbstractService implements MMA027 {
	
	@Resource(name="mma027Dao")
	private MMA027Dao mma027Dao;
	
	/**
	 * 데이터 그리드 해더 정보 조회
	 * 
	 * @param map 검색 조건
	 */
	public List selectHeaderInfo(Map map) throws Exception {
		String ifCode = StringHelper.null2void(map.get("IF_CD"));
		String lang = StringHelper.null2string(map.get(Constants.KEY_DEFAULT_LANGUAGE), Constants.DEFAULT_LANGUAGE);
		
		JcoMapHelper jcoMap = new JcoMapHelper(ifCode);
		List<String> tableParam = jcoMap.getTableParameterName();
		
		List reList = new LinkedList();
		
		// 에러여부
		Map colMap = new HashMap();
    	
    	colMap.put("field", "ERROR_YN_NAME");
    	colMap.put("title", this.getMessage("TXT_ERROR_YN", null, lang));
    	colMap.put("width", "90");
    	colMap.put("align", "center");
    	colMap.put("halign", "center");
    	colMap.put("sortable", "true");
    	colMap.put("hidden", "false");
    	colMap.put("editor", "null");
    	colMap.put("checkbox", "null");
    	colMap.put("rowspan", "0");
    	colMap.put("colspan", "0");
    	colMap.put("frozen", "false");
    	colMap.put("styler", "null");
    	
    	reList.add(colMap);
    	
    	// 에러메시지
		colMap = new HashMap();
    	
    	colMap.put("field", "ERROR_MESSAGE");
    	colMap.put("title", this.getMessage("TXT_ERROR_MESSAGE", null, lang));
    	colMap.put("width", "250");
    	colMap.put("align", "left");
    	colMap.put("halign", "center");
    	colMap.put("sortable", "true");
    	colMap.put("hidden", "false");
    	colMap.put("editor", "null");
    	colMap.put("checkbox", "null");
    	colMap.put("rowspan", "0");
    	colMap.put("colspan", "0");
    	colMap.put("frozen", "false");
    	colMap.put("styler", "null");
    	
    	reList.add(colMap);
    	
        for (int i = 0; i < tableParam.size(); i++) {
        	List list = jcoMap.getTableColumnName(i);
        	
        	for(int t = 0; t < list.size(); t++) {
            	Map<String, Object> cols = (Map) list.get(t);
            	
            	String colName = StringHelper.null2void(cols.get("COLUMN_NAME"));
            	String msg = this.getMessage(StringHelper.null2void(cols.get("COLUMN_MESSAGE")), null, lang);
            	String desc = StringHelper.null2void(cols.get("COLUMN_DESC"));
            	String type = StringHelper.null2void(cols.get("COLUMN_TYPE"));
            	int length = StringHelper.null2zero(cols.get("COLUMN_LENG")) * 10;
            	String trans = StringHelper.null2void(cols.get("COLUMN_TRANS"));
            	String align = "center";
            	
            	if(type.equals("7")) align = "right";
            	if(length < 100) length = 100;
            	if(length > 150) length = 150;
            	if(msg.isEmpty()) msg = desc;
            	
            	colMap = new HashMap();
            	
            	colMap.put("field", trans);
            	colMap.put("title", msg);
            	colMap.put("width", Integer.toString(length));
            	colMap.put("align", align);
            	colMap.put("halign", "center");
            	colMap.put("sortable", "true");
            	colMap.put("hidden", "false");
            	colMap.put("editor", "null");
            	colMap.put("checkbox", "null");
            	colMap.put("rowspan", "0");
            	colMap.put("colspan", "0");
            	colMap.put("frozen", "false");
            	colMap.put("styler", "null");
            	
            	reList.add(colMap);
        	}
        }
        
        return reList;
	}
	
	/**
	 * 서비스 방식에서 데이터 그리드 해더 정보 조회
	 * 
	 * @param map 검색 조건
	 */
	public List selectServiceHeaderInfo(Map map) throws Exception {
		String ifCode = StringHelper.null2void(map.get("IF_CD"));
		String lang = StringHelper.null2string(map.get(Constants.KEY_DEFAULT_LANGUAGE), Constants.DEFAULT_LANGUAGE);
		
		JcoMapHelper jcoMap = new JcoMapHelper(ifCode);
		List<String> tableParam = jcoMap.getTableParameterName();
		
		List reList = new LinkedList();
		
		// 에러여부
		Map colMap = new HashMap();
    	
    	colMap.put("field", "ERROR_YN_NAME");
    	colMap.put("title", this.getMessage("TXT_ERROR_YN", null, lang));
    	colMap.put("width", "90");
    	colMap.put("align", "center");
    	colMap.put("halign", "center");
    	colMap.put("sortable", "true");
    	colMap.put("hidden", "false");
    	colMap.put("editor", "null");
    	colMap.put("checkbox", "null");
    	colMap.put("rowspan", "0");
    	colMap.put("colspan", "0");
    	colMap.put("frozen", "false");
    	colMap.put("styler", "null");
    	
    	reList.add(colMap);
    	
    	// 에러메시지
		colMap = new HashMap();
    	
    	colMap.put("field", "ERROR_MESSAGE");
    	colMap.put("title", this.getMessage("TXT_ERROR_MESSAGE", null, lang));
    	colMap.put("width", "250");
    	colMap.put("align", "left");
    	colMap.put("halign", "center");
    	colMap.put("sortable", "true");
    	colMap.put("hidden", "false");
    	colMap.put("editor", "null");
    	colMap.put("checkbox", "null");
    	colMap.put("rowspan", "0");
    	colMap.put("colspan", "0");
    	colMap.put("frozen", "false");
    	colMap.put("styler", "null");
    	
    	reList.add(colMap);
    	
        for (int i = 0; i < tableParam.size(); i++) {
        	List list = jcoMap.getTableColumnName(i);
        	
        	for(int t = 0; t < list.size(); t++) {
            	Map<String, Object> cols = (Map) list.get(t);
            	
            	String colName = StringHelper.null2void(cols.get("COLUMN_NAME"));
            	String msg = this.getMessage(StringHelper.null2void(cols.get("COLUMN_MESSAGE")), null, lang);
            	String desc = StringHelper.null2void(cols.get("COLUMN_DESC"));
            	String type = StringHelper.null2void(cols.get("COLUMN_TYPE"));
            	int length = StringHelper.null2zero(cols.get("COLUMN_LENG")) * 10;
            	String trans = StringHelper.null2void(cols.get("COLUMN_TRANS"));
            	String align = "center";
            	
            	if(type.equals("7")) align = "right";
            	if(length < 100) length = 100;
            	if(length > 150) length = 150;
            	if(msg.isEmpty()) msg = desc;
            	
            	colMap = new HashMap();
            	
            	// 조회되는 속성에 따라 필드ID를 결정함
            	if(colName.toUpperCase().startsWith("ATTRIBUTE")) colMap.put("field", colName); 
            	else colMap.put("field", trans);
            	colMap.put("title", msg);
            	colMap.put("width", Integer.toString(length));
            	colMap.put("align", align);
            	colMap.put("halign", "center");
            	colMap.put("sortable", "true");
            	colMap.put("hidden", "false");
            	colMap.put("editor", "null");
            	colMap.put("checkbox", "null");
            	colMap.put("rowspan", "0");
            	colMap.put("colspan", "0");
            	colMap.put("frozen", "false");
            	colMap.put("styler", "null");
            	
            	reList.add(colMap);
        	}
        }
        
        return reList;
	}
	
	/**
	 * 인터페이스 이력 상세 리스트 조회
	 * 
	 * @param map 검색 조건
	 */
	public List selectInterfaceHistoryList(Map map) throws Exception {
		return mma027Dao.selectInterfaceHistoryList(map);
	}
	
}
