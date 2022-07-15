package com.yni.fta.fm.sm.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.fta.common.postgresql.PostgresqlDao;
import com.yni.fta.fm.sm.SM7005;

import kr.yni.frame.Constants;
import kr.yni.frame.service.YniAbstractService;
import kr.yni.frame.util.JsonUtil;
import kr.yni.frame.util.StringHelper;

/**
 * 시스템관리 > 인터페이스 > 항목 관리
 * 
 * @author carlos
 *
 */
@Service("sm7005")
public class SM7005Impl extends YniAbstractService implements SM7005 {

    @Resource(name = "sm7005Dao")
    private SM7005Dao sm7005Dao;

    public List selectInterfaceItemMstList(Map map) throws Exception {
        return sm7005Dao.selectInterfaceItemMstList(map);
    }
    
    /**
     * 인터페이스 항목 정보 중복건수 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map selectcInterfaceDupCheck(Map map) throws Exception {
        return sm7005Dao.selectcInterfaceDupCheck(map);
    }
    
    /**
     * INTERFACE_ITEM_MST_ID 반환
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map selectLastInterfaceItemMstId(Map map) throws Exception {
        return sm7005Dao.selectLastInterfaceItemMstId(map);
    }
    
    public Map selectInterfaceItemMstInfo(Map map) throws Exception {
        return sm7005Dao.selectInterfaceItemMstInfo(map);
    }
    
    public List selectInterfaceItemMstCombo(Map map) throws Exception {
        return sm7005Dao.selectInterfaceItemMstCombo(map);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public int insertInterfaceItemMstInfo(Map map) throws Exception {
        
        Map dupInfo = sm7005Dao.selectcInterfaceDupCheck(map);
        
        if (dupInfo != null) {
            int nDupCnt = StringHelper.null2zero(dupInfo.get("DUPLICATE"));
            if (nDupCnt > 0) {
                // 이미 등록된 인터페이스 아이템 입니다
                throw new RuntimeException(getMessage("MSG_EXISTS_IF_ITEM_INFO"));
            }
        }
        
        int rtnCnt = sm7005Dao.insertInterfaceItemMstInfo(map);
        Map lastKeyInfo = sm7005Dao.selectLastInterfaceItemMstId(map);
        
        String interfaceItemMstId = "";
        if (lastKeyInfo != null && !lastKeyInfo.isEmpty()) {
            interfaceItemMstId = StringHelper.null2void(lastKeyInfo.get("INTERFACE_ITEM_MST_ID"));
        }
        
        map.put("INTERFACE_ITEM_MST_ID", interfaceItemMstId);
        
        this.updateExcuteInterfaceItemDtlBatch(map); // 엑셀 테이블 설정인 경우에는 일괄 테이블을 등록한다.
        
        return rtnCnt;
    }

    public int updateInterfaceItemMstInfo(Map map) throws Exception {
    	this.updateExcuteInterfaceItemDtlBatch(map); // 엑셀 테이블 설정인 경우에는 일괄 테이블을 등록한다.
    	
        return sm7005Dao.updateInterfaceItemMstInfo(map);
    }
    
    public int updateExcuteInterfaceItemDtlBatch(Map map) throws Exception {
    	int rst = 0;
    	
    	String createTblYn = StringHelper.null2string(map.get("CREATE_TBL_YN"), "N");
    	String itemType = StringHelper.null2void(map.get("ITEM_TYPE"));
    	String flag = StringHelper.null2void(map.get("flag"));
    	String lang = StringHelper.null2string(map.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE);
    	
    	if(itemType.equals("E")) {
	    	List gridList = JsonUtil.getList(StringHelper.null2void(map.get("gridData")));
	    	
			if (gridList == null || gridList.size() <= 0) {
	            return 0;
	        }
			
			sm7005Dao.deleteInterfaceItemDtlAllInfo(map);
			
			for(int i = 0; i < gridList.size(); i++) {
				Map gridMap = (Map) gridList.get(i);
				
				gridMap.put("COMPANY_CD", StringHelper.null2void(map.get("COMPANY_CD")));
				gridMap.put("INTERFACE_ITEM_MST_ID", StringHelper.null2void(map.get("INTERFACE_ITEM_MST_ID")));
				gridMap.put("IF_CD", StringHelper.null2void(map.get("IF_CD")));
				gridMap.put("SESSION_USER_ID", StringHelper.null2void(map.get("SESSION_USER_ID")));
			}
			
			rst = sm7005Dao.insertExcuteInterfaceItemDtlBatch(gridList);
			
			String db = StringHelper.null2void(this.properties.get("application.db.type"));
			map.put("DB_TYPE", db);
			
			List columnList = sm7005Dao.selectInterfaceItemDtlList(map);
			
			// 테이블 삭제 및 생성
			boolean createYn = true;
			
			if(createTblYn.equals("Y")) {
				PostgresqlDao dbo = new PostgresqlDao();
				if(flag.equals("update")) {
					dbo.dropExcelInterfaceViewObject(map);							// Drop View
					dbo.dropExcelInterfaceObject(map);								// Drop Table
					createYn = dbo.createExcelInterfaceObject(columnList, map);		// Create Table
					dbo.createExcelInterfaceViewObject(columnList, map);			// Create View
				}
			}
			
			if(!createYn) {
				throw new RuntimeException("Failed to create table.");
			}
    	}
    	
    	return rst;
    }
    
    
    public int deleteInterfaceItemMstInfo(Map map) throws Exception {
        int rCnt = 0;
        
        sm7005Dao.deleteInterfaceItemDtlAllInfo(map);
        rCnt = sm7005Dao.deleteInterfaceItemMstInfo(map);
        
        String itemType = StringHelper.null2void(map.get("ITEM_TYPE"));
        String lang = StringHelper.null2string(map.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE);
        
        // 인터페이스 테이블 삭제
    	if(itemType.equals("E")) {
	        PostgresqlDao dbo = new PostgresqlDao();
	        dbo.dropExcelInterfaceViewObject(map);							// Drop View
			boolean createYn = dbo.dropExcelInterfaceObject(map);
			
			if(!createYn) {
				throw new RuntimeException("Failed to dorp table.");
			}
    	}
    	
        return rCnt;
    }

    public List selectInterfaceItemDtlList(Map map) throws Exception {
    	String lang = StringHelper.null2string(map.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE);
    	List rstList = sm7005Dao.selectInterfaceItemDtlList(map);
    	
    	// 컬럼명칭 추가
    	for(int i = 0; i < rstList.size(); i++) {
    		Map tmap = (Map) rstList.get(i);
    		String colName = StringHelper.null2void(tmap.get("REMARK"));
    		
    		if(!colName.isEmpty()) {
    			tmap.put("COL_NAME", this.getMessage(colName, null, lang));
    		}
    	}
    	
    	return rstList;
    }
    
    /**
     * INTERFACE_ITEM_DTL_ID 반환
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map selectLastInterfaceItemDtlId(Map map) throws Exception {
        return sm7005Dao.selectLastInterfaceItemDtlId(map);
    }
    
    public Map selectInterfaceItemDtlInfo(Map map) throws Exception {
        return sm7005Dao.selectInterfaceItemDtlInfo(map);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public int insertInterfaceItemDtlInfo(Map map) throws Exception {
    	int rtnCnt = 0;
    	String gdata = StringHelper.null2void(map.get("gridData"));
    	
    	if(gdata.isEmpty()) {
	    	rtnCnt = sm7005Dao.insertInterfaceItemDtlInfo(map);
	        Map lastKeyInfo = sm7005Dao.selectLastInterfaceItemDtlId(map);
	        
	        String interfaceItemDtlId = "";
	        
	        if (lastKeyInfo != null && !lastKeyInfo.isEmpty()) {
	            interfaceItemDtlId = StringHelper.null2void(lastKeyInfo.get("INTERFACE_ITEM_DTL_ID"));
	        }
	        
	        map.put("INTERFACE_ITEM_DTL_ID", interfaceItemDtlId);
    	} else {
    		List gridList = JsonUtil.getList(gdata);
    		
    		if(gridList == null || gridList.size() <= 0) {
	            return 0;
	        }
    		
    		sm7005Dao.deleteInterfaceItemDtlAllInfo(map); // 기존 인터페이스 ID에 대한 상세정보 삭제
			
			for(int i = 0; i < gridList.size(); i++) {
				Map gridMap = (Map) gridList.get(i);
				
				gridMap.put("COMPANY_CD", StringHelper.null2void(map.get("COMPANY_CD")));
				gridMap.put("INTERFACE_ITEM_MST_ID", StringHelper.null2void(map.get("INTERFACE_ITEM_MST_ID")));
				gridMap.put("IF_CD", StringHelper.null2void(map.get("IF_CD")));
				gridMap.put("COL_SEQ", Integer.toString(i+1));
				gridMap.put("SESSION_USER_ID", StringHelper.null2void(map.get("SESSION_USER_ID")));
			}
			
			rtnCnt = sm7005Dao.insertExcuteInterfaceItemDtlBatch(gridList);
    	}
        
        return rtnCnt;
    }

    public int updateInterfaceItemDtlInfo(Map map) throws Exception {
        return sm7005Dao.updateInterfaceItemDtlInfo(map);
    }
    
    public int deleteInterfaceItemDtlInfo(Map map) throws Exception {
        return sm7005Dao.deleteInterfaceItemDtlInfo(map);
    }
    public int deleteInterfaceItemDtlAllInfo(Map map) throws Exception {
        return sm7005Dao.deleteInterfaceItemDtlAllInfo(map);
    }
}
