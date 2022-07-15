package com.yni.fta.mm.smp.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.yni.frame.dao.YniAbstractDAO;

/**
 * FTA Rule > FTA협정등록
 * 
 * @author YNI-maker
 *
 */
@Repository("smp1001Dao")
public class SMP1001Dao extends YniAbstractDAO {
	
	/**
	 * FTA협정 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectMainList(Map map) throws Exception {
		return this.listWithRowPaging("SM1001.selectMainList", map);
	}
}
