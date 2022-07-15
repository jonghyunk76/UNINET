package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.yni.frame.dao.YniAbstractDAO;

/**
 * FTA Rule > [MX] Trace List 관리
 * 
 * @author YNI-maker
 *
 */
@Repository("mmA008Dao")
public class MMA008Dao extends YniAbstractDAO {

	/**
	 * [MX] Trace List 관리 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectMainList(Map map) throws Exception {
		return this.listWithRowPaging("MMA008.selectMainList", map);
	}
}
