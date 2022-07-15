package com.yni.fta.common.tools;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import kr.yni.frame.Constants;
import kr.yni.frame.resources.MessageResource;
import kr.yni.frame.util.StringHelper;

/**
 * 데이터 그리드 정보 생성 클래스
 * 
 * @author ador2
 *
 */
public class DatagridSupporter {
	
	/**
	 * 데이터 그리드 해더 변경
	 * 
	 * @param hlist = DB에서 조회한 해더정보
	 * @param pmap = 사용자 파라메터
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List getGridHeader(List hlist, Map pmap) throws Exception {
		List reList = new LinkedList();
		MessageResource messageSource = MessageResource.getMessageInstance();
		String lang = StringHelper.null2string(pmap.get(Constants.KEY_DEFAULT_LANGUAGE), Constants.DEFAULT_LANGUAGE);
		
		for(int t = 0; t < hlist.size(); t++) {
        	Map<String, Object> cols = (Map) hlist.get(t);
        	
        	String field = StringHelper.replace(StringHelper.replace(StringHelper.null2string(cols.get("CELL_FIELD"), "null"), "＂", "\""), "`", "'");
        	String title = StringHelper.replace(StringHelper.replace(StringHelper.null2string(cols.get("CELL_TITLE"), "null"), "＂", "\""), "`", "'");
        	int width = StringHelper.null2zero(cols.get("CELL_WIDTH"));
        	String align = StringHelper.null2string(cols.get("CELL_ALIGN"), "center");
        	String halign = StringHelper.null2string(cols.get("CELL_HALIGN"), "center");
        	String sortable = StringHelper.null2string(cols.get("CELL_SORTABLE"), "true");
        	String hidden = StringHelper.null2string(cols.get("CELL_HIDDEN"), "false");
        	String editor = StringHelper.replace(StringHelper.replace(StringHelper.null2string(cols.get("CELL_EDITOR"), "null"), "＂", "\""), "`", "'");
        	String checkbox = StringHelper.null2string(cols.get("CELL_CHECKBOX"), "null");
        	String formater = StringHelper.replace(StringHelper.replace(StringHelper.null2string(cols.get("CELL_FORMATTER"), "null"), "＂", "\""), "`", "'");
        	String rowspan = StringHelper.null2string(cols.get("CELL_ROWSPAN"), "0");
        	String colspan = StringHelper.null2string(cols.get("CELL_COLSPAN"), "0");
        	String frozen = StringHelper.null2string(cols.get("CELL_FROZEN"), "false");
        	String styler = StringHelper.replace(StringHelper.replace(StringHelper.null2string(cols.get("CELL_STYLER"), "null"), "＂", "\""), "`", "'");
        	
        	Map colMap = new LinkedHashMap();
        	
        	colMap.put("field", field);
        	colMap.put("title", title);
        	colMap.put("width", Integer.toString(width));
        	colMap.put("align", align);
        	colMap.put("halign", halign);
        	colMap.put("sortable", sortable);
        	colMap.put("hidden", hidden);
        	colMap.put("editor", editor);
        	colMap.put("checkbox", checkbox);
        	colMap.put("formatter", formater);
        	colMap.put("rowspan", rowspan);
        	colMap.put("colspan", colspan);
        	colMap.put("frozen", frozen);
        	colMap.put("styler", styler);
        	
        	reList.add(colMap);
    	}
        
        return reList;
	}
	
}
