package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.yni.frame.dao.YniAbstractDAO;

/**
 * FTA Rule > [MX] 품목분류 관리
 * 
 * @author YNI-maker
 *
 */
@Repository("mmA009Dao")
public class MMA009Dao extends YniAbstractDAO {

	/**
	 * [MX] 품목분류 관리
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectMainList(Map map) throws Exception {
		return this.listWithRowPaging("MMA009.selectMainList", map);
	}
}
