package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.yni.frame.dao.YniAbstractDAO;

/**
 * FTA Rule > FTA 옵션등록
 * 
 * @author YNI-maker
 *
 */
@Repository("mmA014Dao")
public class MMA014Dao extends YniAbstractDAO {

	/**
	 * FTA옵션 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectMainList(Map map) throws Exception {
		return this.listWithRowPaging("MMA014.selectMainList", map);
	}
}
