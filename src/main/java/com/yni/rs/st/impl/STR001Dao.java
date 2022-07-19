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
@Repository("stR001Dao")
public class STR001Dao extends YniAbstractDAO {

	/**
	 * 서비스 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectServerList(Map map) throws Exception {
		return this.listWithRowPaging("STR001.selectServerList", map);
	}
	
	/**
     * 서버 등록
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int insertServerMst(Map map) throws Exception {
        return this.update("STR001.insertServerMst", map);
    }
    
    /**
     * 서버 수정
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int updateServerMst(Map map) throws Exception {
        return this.update("STR001.updateServerMst", map);
    }
    
    /**
     * 서버 삭제
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int deleteServerMst(Map map) throws Exception {
        return this.update("STR001.deleteServerMst", map);
    }
    
    /**
	 * 송수신 건수 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectConnCountOfChart(Map map) throws Exception {
		return this.list("STR001.selectConnCountOfChart", map);
	}
	
	/**
	 * 데이터 건수 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectConnTrafficOfChart(Map map) throws Exception {
		return this.list("STR001.selectConnTrafficOfChart", map);
	}
	
}
