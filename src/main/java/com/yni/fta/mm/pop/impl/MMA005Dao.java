package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.yni.frame.dao.YniAbstractDAO;

/**
 * 공통팝업 > 품목 조회
 * 
 * @author YNI-maker
 *
 */
@Repository("mmA005Dao")
public class MMA005Dao extends YniAbstractDAO {

	/**
	 * 품목 조회 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectMainList(Map map) throws Exception {
		return this.listWithRowPaging("MMA005.selectMainList", map);
	}
	
	/**
	 * BOM 자재 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectBomItemList(Map map) throws Exception {
		return this.list("MMA005.selectBomItemList", map);
	}
	
}
