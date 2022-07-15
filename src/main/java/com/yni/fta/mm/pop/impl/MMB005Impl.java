package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.fta.mm.pop.MMB005;

import kr.yni.frame.service.YniAbstractService;

/**
 * 공통 > 잠정신고 구현 클래스
 * 
 * @author YNI-maker
 *
 */
@Service("MMB005")
public class MMB005Impl extends YniAbstractService implements MMB005 {
	
	@Resource(name="MMB005Dao")
	private MMB005Dao MMB005Dao;
	
	/**
	 * 리스트 조회
	 * 
	 * @param map 검색 조건
	 */
	public List selectMainList(Map map) throws Exception {
		return MMB005Dao.selectMainList(map);
	}
	
}
