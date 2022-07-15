package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.yni.frame.dao.YniAbstractDAO;

/**
 * 공통 > 자료 송/수신 DAO 클래스
 * 
 * @author YNI-maker
 *
 */
@Repository("MMB001Dao")
public class MMB001Dao extends YniAbstractDAO {

	/**
	 * 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectMainList(Map map) throws Exception {
		return this.listWithRowPaging("MMB001.selectMainList", map);
	}
}
