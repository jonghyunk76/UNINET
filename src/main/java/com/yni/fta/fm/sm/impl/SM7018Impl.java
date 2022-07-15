package com.yni.fta.fm.sm.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.fta.fm.sm.SM7018;

import kr.yni.frame.service.YniAbstractService;

/**
 * 게시판 > 사용자 로그
 * 
 * @author YNI-maker
 *
 */
@Service("sm7018")
public class SM7018Impl extends YniAbstractService implements SM7018 {
	
	@Resource(name="sm7018Dao")
	private SM7018Dao sm7018Dao;
	
	/**
	 * 사용자 로그 목록 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List selectUseLogList(Map map) throws Exception {
		return sm7018Dao.selectUseLogList(map);
    }
	
	/**
	 * 사용자 로그 통계정보 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List selectUseLogStatusList(Map map) throws Exception {
		return sm7018Dao.selectUseLogStatusList(map);
    }
	
}
