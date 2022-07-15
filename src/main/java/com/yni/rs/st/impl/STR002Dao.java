package com.yni.rs.st.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.yni.frame.dao.YniAbstractDAO;

/**
 * 마감 및 통계 > 마감관리
 * 
 * @author YNI-maker
 *
 */
@Repository("stR002Dao")
public class STR002Dao extends YniAbstractDAO {

	/**
	 * 서비스 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectServiceList(Map map) throws Exception {
		return this.listWithRowPaging("STR002.selectServiceList", map);
	}
	
	/**
     * 서비스 등록
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int insertServiceMst(Map map) throws Exception {
        return this.update("STR002.insertServiceMst", map);
    }

    /**
     * 서비스 수정
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int updateServiceMst(Map map) throws Exception {
        return this.update("STR002.updateServiceMst", map);
    }
    
    /**
     * 서비스 삭제
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int deleteServiceMst(Map map) throws Exception {
        return this.update("STR002.deleteServiceMst", map);
    }
    
    /**
	 * 서비스 실행계획 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectServicePlanList(Map map) throws Exception {
		return this.listWithRowPaging("STR002.selectServicePlanList", map);
	}
	
	/**
     * 서비스 실행계획 등록
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int insertServicePlan(List list) throws Exception {
        return this.executeBatch("STR002.insertServicePlan", list);
    }
    
    /**
     * 서비스 실행계획 삭제
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int deleteServicePlan(Map map) throws Exception {
        return this.update("STR002.deleteServicePlan", map);
    }
    
    /**
	 * 서비스 마스터 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectServiceMstList(Map map) throws Exception {
		return this.listWithRowPaging("STR002.selectServiceMstList", map);
	}
    
}
