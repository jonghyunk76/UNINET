package com.yni.fta.mm.DEMO.imp;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.fta.mm.DEMO.DE0000;

import kr.yni.frame.service.YniAbstractService;

/**
 * Demo Interface implement Class
 * 
 * @author YNI-maker
 *
 */
@Service("de0000")
public class DE0000Impl extends YniAbstractService implements DE0000 {
	
	@Resource(name="de0000Dao")
	private DE0000Dao de0000Dao;
	
	/**
	 * 리스트 조회
	 * 
	 * @param map 검색 조건
	 */
	public List selectMainList(Map map) throws Exception {
		return de0000Dao.selectMainList(map);
	}
	
}
