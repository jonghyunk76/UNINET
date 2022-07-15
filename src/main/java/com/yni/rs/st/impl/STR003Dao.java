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
@Repository("stR003Dao")
public class STR003Dao extends YniAbstractDAO {

	/**
	 * 서비스 이력 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectServiceHistoryList(Map map) throws Exception {
		return this.listWithRowPaging("STR003.selectServiceHistoryList", map);
	}
	
	/**
	 * 인터페이스 이력 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectInterfaeHistoryList(Map map) throws Exception {
		return this.listWithRowPaging("STR003.selectInterfaeHistoryList", map);
	}
	
}
