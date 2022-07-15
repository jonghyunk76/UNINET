package com.yni.fta.fm.sm.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.fta.common.tools.StringUtil;
import com.yni.fta.fm.sm.SM7004;

import kr.yni.frame.service.YniAbstractService;
import kr.yni.frame.util.JsonUtil;
import kr.yni.frame.util.StringHelper;

/**
 * 시스템관리 > 인터페이스 > 스케쥴 관리
 * 
 * @author carlos
 *
 */
@Service("sm7004")
public class SM7004Impl extends YniAbstractService implements SM7004 {

    @Resource(name = "sm7004Dao")
    private SM7004Dao sm7004Dao;

    public List selectScheduleList(Map map) throws Exception {
        return sm7004Dao.selectScheduleList(map);
    }
    
    public Map selectScheduleDupCheck(Map map) throws Exception {
        return sm7004Dao.selectScheduleDupCheck(map);
    }

    public Map selectLastInterfaceScheduleId(Map map) throws Exception {
        return sm7004Dao.selectLastInterfaceScheduleId(map);
    }
    
    public Map selectScheduleInfo(Map map) throws Exception {
        return sm7004Dao.selectScheduleInfo(map);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public int insertScheduleInfo(Map map) throws Exception {
        Map dupInfo = sm7004Dao.selectScheduleDupCheck(map);
        
        if (dupInfo != null) {
            int nDupCnt = StringHelper.null2zero(dupInfo.get("DUPLICATE"));
            if (nDupCnt > 0) {
                // 이미 등록된 인터페이스 스케줄 입니다
                throw new RuntimeException(getMessage("MSG_EXISTS_IF_SCD_INFO"));
            }
        }

        int rtnCnt = sm7004Dao.insertScheduleInfo(map);
        Map lastKeyInfo = sm7004Dao.selectLastInterfaceScheduleId(map);
        
        String interfaceScheduleId = "";
        if (lastKeyInfo != null && !lastKeyInfo.isEmpty()) {
            interfaceScheduleId = StringHelper.null2void(lastKeyInfo.get("INTERFACE_SCHEDULE_ID"));
        }
        
        map.put("INTERFACE_SCHEDULE_ID", interfaceScheduleId);
        
        // gridData가 있는 경우에는 스케쥴 맵핑정보도 같이 저장
        List list = JsonUtil.getList(StringHelper.null2void(map.get("gridData")));
        
        if (list != null && list.size() > 0) {
        	sm7004Dao.deleteScheduleMappingAllInfo(map);
        	
        	for(int i = 0; i < list.size(); i++) {
        		Map gmap = (Map) list.get(i);
        		
        		gmap.put("SCHEDULE_SEQ_NEW", (i+1));
        		gmap.put("SCHEDULE_CD", StringHelper.null2void(map.get("SCHEDULE_CD")));
        		gmap.put("COMPANY_CD", StringHelper.null2void(map.get("COMPANY_CD")));
        		gmap.put("SESSION_USER_ID", StringHelper.null2void(map.get("USER_ID")));
        	}
        	
        	sm7004Dao.batchScheduleMappingInfo(list);
        }
        
        return rtnCnt;
    }

    public int updateScheduleInfo(Map map) throws Exception {
    	int cnt = sm7004Dao.updateScheduleInfo(map);
    	
    	// gridData가 있는 경우에는 스케쥴 맵핑정보도 같이 저장
    	List list = JsonUtil.getList(StringHelper.null2void(map.get("gridData")));    	

        if (list != null && list.size() > 0) {
        	sm7004Dao.deleteScheduleMappingAllInfo(map);
        	
        	for(int i = 0; i < list.size(); i++) {
        		Map gmap = (Map) list.get(i);
        		
        		gmap.put("SCHEDULE_SEQ_NEW", (i+1));
        		gmap.put("SCHEDULE_CD", StringHelper.null2void(map.get("SCHEDULE_CD")));
        		gmap.put("COMPANY_CD", StringHelper.null2void(map.get("COMPANY_CD")));
        		gmap.put("SESSION_USER_ID", StringHelper.null2void(map.get("USER_ID")));
        	}
        	
        	sm7004Dao.batchScheduleMappingInfo(list);
        }
        
        return cnt;
    }
    
    public int deleteScheduleInfo(Map map) throws Exception {
        int rCnt = 0;
        
        rCnt = sm7004Dao.deleteScheduleInfo(map);
        //rCnt += sm7004Dao.deleteScheduleMappingAllInfo(map);
        sm7004Dao.deleteScheduleMappingAllInfo(map);
        
        return rCnt;
    }
    
    public List selectScheduleMappingList(Map map) throws Exception {
        return sm7004Dao.selectScheduleMappingList(map);
    }
    
    public Map selectLastInterfaceScheduleMappingId(Map map) throws Exception {
        return sm7004Dao.selectLastInterfaceScheduleMappingId(map);
    }
    
    public Map selectScheduleMappingInfo(Map map) throws Exception {
        return sm7004Dao.selectScheduleMappingInfo(map);
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public int insertScheduleMappingInfo(Map map) throws Exception {
        int rtnCnt = sm7004Dao.insertScheduleMappingInfo(map);
        Map lastKeyInfo = sm7004Dao.selectLastInterfaceScheduleMappingId(map);
        
        String interfaceScheduleMappingId = "";
        if (lastKeyInfo != null && !lastKeyInfo.isEmpty()) {
            interfaceScheduleMappingId = StringHelper.null2void(lastKeyInfo.get("INTERFACE_SCHEDULE_MAPPING_ID"));
        }
        
        map.put("INTERFACE_SCHEDULE_MAPPING_ID", interfaceScheduleMappingId);
        
        return rtnCnt;
    }

    public int updateScheduleMappingInfo(Map map) throws Exception {
        return sm7004Dao.updateScheduleMappingInfo(map);
    }
    
    public int deleteScheduleMappingInfo(Map map) throws Exception {
        return sm7004Dao.deleteScheduleMappingInfo(map);
    }
    
    public int deleteScheduleMappingAllInfo(Map map) throws Exception {
        return sm7004Dao.deleteScheduleMappingAllInfo(map);
    }
}
