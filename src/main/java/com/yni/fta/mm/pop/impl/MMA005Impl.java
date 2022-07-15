package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.fta.mm.pop.MMA005;

import kr.yni.frame.service.YniAbstractService;

/**
 * 공통팝업 > 품목 조회
 * 
 * @author YNI-maker
 *
 */
@Service("mmA005")
public class MMA005Impl extends YniAbstractService implements MMA005 {
	
	@Resource(name="mmA005Dao")
	private MMA005Dao mmA005Dao;
	
	/**
	 * 품목 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectMainList(Map map) throws Exception {
		return mmA005Dao.selectMainList(map);
	}
	
	/**
	 * BOM 자재 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectBomItemList(Map map) throws Exception {
		return mmA005Dao.selectBomItemList(map);
	}
	
}
