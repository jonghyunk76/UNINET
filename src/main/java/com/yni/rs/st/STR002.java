package com.yni.rs.st;

import java.util.List;
import java.util.Map;

public interface STR002 {
	
	/**
	 * 서비스 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectServiceList(Map map) throws Exception;
	
	/**
     * 서비스 등록
     * @param map
     * @return
     * @throws Exception
     */
    int insertServiceMst(Map map) throws Exception;

    /**
     * 서비스 등록
     * @param map
     * @return
     * @throws Exception
     */
    int updateServiceMst(Map map) throws Exception;
    
    /**
     * 서비스 삭제
     * @param map
     * @return
     * @throws Exception
     */
    int deleteServiceMst(Map map) throws Exception;
	
    /**
	 * 서비스 실행계획 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectServicePlanList(Map map) throws Exception;
	
	/**
	 * 서비스 마스터 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectServiceMstList(Map map) throws Exception;
	
}
