package com.yni.rs.st.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.rs.st.STR002;

import kr.yni.frame.service.YniAbstractService;
import kr.yni.frame.util.JsonUtil;
import kr.yni.frame.util.StringHelper;

/**
 * 마감 및 통계 > 마감관리
 * 
 * @author YNI-maker
 *
 */
@Service("stR002")
public class STR002Impl extends YniAbstractService implements STR002 {
	
	@Resource(name="stR002Dao")
	private STR002Dao stR002Dao;

	/**
	 * 서비스 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectServiceList(Map map) throws Exception {
		return stR002Dao.selectServiceList(map);
	}

	/**
     * 서비스 등록
     * 
     * @param map
     */
    public int insertServiceMst(Map map) throws Exception {
        int cnt = stR002Dao.insertServiceMst(map);
        
        // gridData가 있는 경우에는 스케쥴 맵핑정보도 같이 저장
        List list = JsonUtil.getList(StringHelper.null2void(map.get("gridData")));
        
        if (list != null && list.size() > 0) {
        	stR002Dao.deleteServicePlan(map);
        	
        	for(int i = 0; i < list.size(); i++) {
        		Map gmap = (Map) list.get(i);
        		
        		gmap.put("PLAN_NO", (i+1));
        		gmap.put("SERVICE_ID", StringHelper.null2void(map.get("SERVICE_ID")));
        		gmap.put("COMPANY_CD", StringHelper.null2void(map.get("COMPANY_CD")));
        		gmap.put("SESSION_USER_ID", StringHelper.null2void(map.get("USER_ID")));
        	}
        	
        	stR002Dao.insertServicePlan(list);
        }
        return cnt;
    }

    /**
     * 서비스 수정
     * 
     * @param map
     */
    public int updateServiceMst(Map map) throws Exception {
        int cnt = stR002Dao.updateServiceMst(map);
        
        // gridData가 있는 경우에는 스케쥴 맵핑정보도 같이 저장
        List list = JsonUtil.getList(StringHelper.null2void(map.get("gridData")));
        
        if (list != null && list.size() > 0) {
        	stR002Dao.deleteServicePlan(map);
        	
        	for(int i = 0; i < list.size(); i++) {
        		Map gmap = (Map) list.get(i);
        		
        		gmap.put("PLAN_NO", Integer.toString(i+1));
        		gmap.put("SERVICE_ID", StringHelper.null2void(map.get("SERVICE_ID")));
        		gmap.put("COMPANY_CD", StringHelper.null2void(map.get("COMPANY_CD")));
        		gmap.put("USER_ID", StringHelper.null2void(map.get("USER_ID")));
        	}
        	
        	stR002Dao.insertServicePlan(list);
        }
        
        return cnt;
    }
	
    /**
     * 서비스 삭제
     * 
     * @param map
     */
    public int deleteServiceMst(Map map) throws Exception {
    	stR002Dao.deleteServicePlan(map);
    	
        return stR002Dao.deleteServiceMst(map);
    }
    
    /**
	 * 서비스 실행계획 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectServicePlanList(Map map) throws Exception {
		return stR002Dao.selectServicePlanList(map);
	}
	
	/**
	 * 서비스 마스터 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectServiceMstList(Map map) throws Exception {
		return stR002Dao.selectServiceMstList(map);
	}
	
}
