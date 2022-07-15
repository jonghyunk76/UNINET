package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.fta.mm.pop.MMB001;

import kr.yni.frame.service.YniAbstractService;

/**
 * 공통 > 자료 송/수신 구현 클래스
 * 
 * @author YNI-maker
 *
 */
@Service("MMB001")
public class MMB001Impl extends YniAbstractService implements MMB001 {
	
	@Resource(name="MMB001Dao")
	private MMB001Dao MMB001Dao;
	
	/**
	 * 리스트 조회
	 * 
	 * @param map 검색 조건
	 */
	public List selectMainList(Map map) throws Exception {
		return MMB001Dao.selectMainList(map);
	}
	
}
