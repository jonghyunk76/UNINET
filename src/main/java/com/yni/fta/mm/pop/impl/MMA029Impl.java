package com.yni.fta.mm.pop.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.fta.mm.pop.MMA029;

import kr.yni.frame.service.YniAbstractService;
import kr.yni.frame.util.StringHelper;
import kr.yni.frame.util.TreeHelper;

/**
 * 공통 > 도움말
 * 
 * @author YNI-maker
 *
 */
@Service("mma029")
public class MMA029Impl extends YniAbstractService implements MMA029 {
	
	@Resource(name="mma029Dao")
	private MMA029Dao mma029Dao;
	
	/**
	 * 도움말 목록 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectHelpMenuList(Map map) throws Exception {
		List mlist = TreeHelper.getMenuData(mma029Dao.selectHelpMenuList(map));
		//log.debug("mlist >>> " + mlist.size());
		return mlist;
	}
	
	/**
	 * 도움말 검색
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectKeyContents(Map map) throws Exception {
		return mma029Dao.selectKeyContents(map);
	}
	
}
