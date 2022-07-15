package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.yni.frame.dao.YniAbstractDAO;

/**
 * 공통 > FTA협정선택 화면
 * 
 * @author YNI-maker
 *
 */
@Repository("mmA025Dao")
public class MMA025Dao extends YniAbstractDAO {
	
	/**
	 * 제품군 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectMainList(Map map) throws Exception {
		return this.listWithRowPaging("MMA025.selectMainList", map);
	}
}
