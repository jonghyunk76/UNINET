package com.yni.fta.fm.sm.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.fta.fm.sm.SM7009;

import kr.yni.frame.service.YniAbstractService;

/**
 * 시스템관리 > 메뉴관리
 * 
 * @author sbj1000
 *
 */
@Service("sm7009")
public class SM7009Impl extends YniAbstractService implements SM7009 {
	
	@Resource(name="sm7009Dao")
		private SM7009Dao sm7009Dao;

	public List selectMenuList(Map map) throws Exception {
		return sm7009Dao.selectMenuList(map);
	}
	
	public List selectMenuLanguageList(Map map) throws Exception {
		return sm7009Dao.selectMenuLanguageList(map);
	}
	
	public Map selectMenuInfo(Map map) throws Exception {
        return sm7009Dao.selectMenuInfo(map);
    }
	
	public int insertMenuInfo(Map map) throws Exception {        
        int rtnCnt = sm7009Dao.insertMenuInfo(map);
        return rtnCnt;
    }
	
	public int updateMenuInfo(Map map) throws Exception {        
        int rtnCnt = sm7009Dao.updateMenuInfo(map);
        return rtnCnt;
    }
	
	public Map selectMenuLanguageInfo(Map map) throws Exception {
        return sm7009Dao.selectMenuLanguageInfo(map);
    }
	
	public int insertMenuLanguageInfo(Map map) throws Exception {        
        int rtnCnt = sm7009Dao.insertMenuLanguageInfo(map);
        return rtnCnt;
    }
	
	public int updateMenuLanguageInfo(Map map) throws Exception {        
        int rtnCnt = sm7009Dao.updateMenuLanguageInfo(map);
        return rtnCnt;
    }
	
}
