package com.yni.fta.mm.DEMO.imp;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.yni.frame.dao.YniAbstractDAO;

/**
 * Demo DAO Class
 * 
 * @author YNI-maker
 *
 */
@Repository("de0000Dao")
public class DE0000Dao extends YniAbstractDAO {

	/**
	 * 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectMainList(Map map) throws Exception {
		return this.listWithRowPaging("DE0000.selectMainList", map);
	}
}
