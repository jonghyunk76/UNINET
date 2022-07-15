package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.fta.mm.pop.MMA034;

import kr.yni.frame.service.YniAbstractService;
import kr.yni.frame.util.JsonUtil;
import kr.yni.frame.util.StringHelper;

/**
 * 공통 > 고객사 품목 조회
 * 
 * @author jonghyunkim
 *
 */
@Service("mmA034")
public class MMA034Impl extends YniAbstractService implements MMA034 {

    @Resource(name = "mmA034Dao")
    private MMA034Dao mmA034Dao;

    /**
     * 데이터 그리드 등록 정보 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List selectDatagridMstList(Map map) throws Exception {
        return mmA034Dao.selectDatagridMstList(map);
    }

    /**
     * 데이터 그리드 정보 삭제
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int deleteDatagridMstInfo(Map map) throws Exception {
        return mmA034Dao.deleteDatagridMstInfo(map);
    }
    
    /**
     * 데이터 그리드 정보 등록
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int insertDatagridMstInfo(Map map) throws Exception {
    	int cnt = 0;
    	String gridId = StringHelper.null2void(map.get("GRID_ID"));
    	String gridName = StringHelper.null2void(map.get("GRID_LANG_CODE"));
    	String userId = StringHelper.null2void(map.get("USER_ID"));
    	String headerId1 = StringHelper.null2void(map.get("HEADER_ID_1"));
    	String headerId2 = StringHelper.null2void(map.get("HEADER_ID_2"));
    	
    	List gridData1 = JsonUtil.getList(StringHelper.null2void(map.get("gridData1")));
		List gridData2 = JsonUtil.getList(StringHelper.null2void(map.get("gridData2")));
    	
		log.debug(StringHelper.null2void(map.get("gridData1")));
		
		mmA034Dao.deleteDatagridMstInfo(map);
		
		if(gridData1.size() > 0) {
			for(int i = 0; i < gridData1.size(); i++) {
				Map gmap = (Map) gridData1.get(i);
				
				gmap.put("GRID_ID", gridId);
				gmap.put("GRID_LANG_CODE", gridName);
				gmap.put("HEADER_ID", headerId1);
				gmap.put("HEADER_NO", "1");
				gmap.put("SORT_NO", i+1);
				gmap.put("USER_ID", userId);
				gmap.put("CELL_EDITOR", StringHelper.replace(StringHelper.null2void(gmap.get("CELL_EDITOR")), "＂", "\""));
				gmap.put("CELL_FORMATTER", StringHelper.replace(StringHelper.null2void(gmap.get("CELL_FORMATTER")), "＂", "\""));
				gmap.put("CELL_STYLER", StringHelper.replace(StringHelper.null2void(gmap.get("CELL_STYLER")), "＂", "\""));
			}
			
			cnt = mmA034Dao.insertDatagridMstInfo(gridData1);
		}
    	
		if(gridData2.size() > 0) {
			for(int i = 0; i < gridData2.size(); i++) {
				Map gmap = (Map) gridData2.get(i);
				
				gmap.put("GRID_ID", gridId);
				gmap.put("GRID_LANG_CODE", gridName);
				gmap.put("HEADER_ID", headerId2);
				gmap.put("HEADER_NO", "2");
				gmap.put("SORT_NO", i+1);
				gmap.put("USER_ID", userId);
				gmap.put("CELL_EDITOR", StringHelper.replace(StringHelper.null2void(gmap.get("CELL_EDITOR")), "＂", "\""));
				gmap.put("CELL_FORMATTER", StringHelper.replace(StringHelper.null2void(gmap.get("CELL_FORMATTER")), "＂", "\""));
				gmap.put("CELL_STYLER", StringHelper.replace(StringHelper.null2void(gmap.get("CELL_STYLER")), "＂", "\""));
			}
			
			cnt = mmA034Dao.insertDatagridMstInfo(gridData2);
		}
		
        return cnt;
    }
    
}
