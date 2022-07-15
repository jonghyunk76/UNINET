package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.yni.frame.dao.YniAbstractDAO;

/**
 * 공통 > 제품군 조회
 * 
 * @author YNI-maker
 *
 */
@Repository("mmA024Dao")
public class MMA024Dao extends YniAbstractDAO {
	
	/**
	 * 제품군 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectMainList(Map map) throws Exception {
		return this.listWithRowPaging("MMA024.selectMainList", map);
	}
}
