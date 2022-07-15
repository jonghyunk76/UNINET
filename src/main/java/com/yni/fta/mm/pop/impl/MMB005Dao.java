package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.yni.frame.dao.YniAbstractDAO;

/**
 * 공통 > 잠정신고 DAO 클래스
 * 
 * @author YNI-maker
 *
 */
@Repository("MMB005Dao")
public class MMB005Dao extends YniAbstractDAO {

	/**
	 * 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectMainList(Map map) throws Exception {
		return this.listWithRowPaging("MMB005.selectMainList", map);
	}
}
