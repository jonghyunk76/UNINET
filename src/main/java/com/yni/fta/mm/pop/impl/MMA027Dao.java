package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.yni.frame.dao.YniAbstractDAO;

/**
 * 배치 > 인터페이스 이력 데이터 상세 조회
 * 
 * @author YNI-maker
 *
 */
@Repository("mma027Dao")
public class MMA027Dao extends YniAbstractDAO {

	/**
	 * 데이터 그리드 해더 정보 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectHeaderInfo(Map map) throws Exception {
		return this.list("MMA027.selectHeaderInfo", map);
	}
	
	/**
	 * 인터페이스 이력 상세 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectInterfaceHistoryList(Map map) throws Exception {
		return this.listWithRowPaging("MMA027.selectInterfaceHistoryList", map);
	}
	
}
