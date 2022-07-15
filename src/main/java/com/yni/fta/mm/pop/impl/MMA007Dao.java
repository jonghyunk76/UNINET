package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.yni.frame.dao.YniAbstractDAO;

/**
 * FTA Rule > [MX] NALADISA Code 조회
 * 
 * @author YNI-maker
 *
 */
@Repository("mmA007Dao")
public class MMA007Dao extends YniAbstractDAO {

	/**
	 * [MX] NALADISA Code 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectMainList(Map map) throws Exception {
		return this.listWithRowPaging("MMA007.selectMainList", map);
	}
}
