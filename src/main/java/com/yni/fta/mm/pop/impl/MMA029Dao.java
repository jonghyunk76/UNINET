package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.yni.frame.dao.YniAbstractDAO;

/**
 * 공통 > 도움말
 * 
 * @author YNI-maker
 *
 */
@Repository("mma029Dao")
public class MMA029Dao extends YniAbstractDAO {

	/**
	 * 도움말 목록 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectHelpMenuList(Map map) throws Exception {
		return this.list("MMA029.selectHelpMenuList", map);
	}
	
	/**
	 * 도움말 검색
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectKeyContents(Map map) throws Exception {
		return this.list("MMA029.selectKeyContents", map);
	}
	
}
