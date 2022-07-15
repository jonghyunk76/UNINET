package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.yni.frame.dao.YniAbstractDAO;

/**
 * FTA Rule > HS Code 조회
 * 
 * @author YNI-maker
 *
 */
@Repository("mmA015Dao")
public class MMA015Dao extends YniAbstractDAO {

	/**
	 * 표준 Code 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectMainList(Map map) throws Exception {
		return this.listWithRowPaging("MMA015.selectMainList", map);
	}
	
	/**
	 * 기관발급용 표준 Code 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectKcsStandardCodeList(Map map) throws Exception {
		return this.listWithRowPaging("MMA015.selectKcsStandardCodeList", map);
	}
	
}
