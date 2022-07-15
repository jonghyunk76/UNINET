package com.yni.rs.st.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.rs.st.STR003;

import kr.yni.frame.service.YniAbstractService;
import kr.yni.frame.util.JsonUtil;
import kr.yni.frame.util.StringHelper;

/**
 * 서비스 이력 리스트 조회
 * 
 * @author YNI-maker
 *
 */
@Service("stR003")
public class STR003Impl extends YniAbstractService implements STR003 {
	
	@Resource(name="stR003Dao")
	private STR003Dao stR003Dao;

	/**
	 * 서비스 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectServiceHistoryList(Map map) throws Exception {
		return stR003Dao.selectServiceHistoryList(map);
	}
	
	/**
	 * 인터페이스 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectInterfaeHistoryList(Map map) throws Exception {
		return stR003Dao.selectInterfaeHistoryList(map);
	}
	
}
