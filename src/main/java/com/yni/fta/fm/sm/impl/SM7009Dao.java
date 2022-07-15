package com.yni.fta.fm.sm.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.yni.frame.dao.YniAbstractDAO;

/**
 * 시스템관리 > 메뉴관리
 * 
 * @author sbj1000
 *
 */
@Repository("sm7009Dao")
public class SM7009Dao extends YniAbstractDAO {

	/**
	 * 메뉴관리
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectMenuList(Map map) throws Exception {
		return this.listWithRowPaging("SM7009.selectMenuList", map);
	}
	
	public List selectMenuLanguageList(Map map) throws Exception {
		return list("SM7009.selectMenuLanguageList", map);
	}
	
	/**
     * 메뉴 정보 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map selectMenuInfo(Map map) throws Exception {
        return (Map) selectByPk("SM7009.selectMenuInfo", map);
    }
	
	/**
     * 메뉴 정보 추가
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int insertMenuInfo(Map map) throws Exception {
        log.debug("insertMenuInfo = " +  map.toString());
        return this.update("SM7009.insertMenuInfo", map);
    }
    
    /**
     * 메뉴 정보 수정
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int updateMenuInfo(Map map) throws Exception {
        log.debug("updateMenuInfo = " +  map.toString());
        return this.update("SM7009.updateMenuInfo", map);
    }
    
    /**
     * 메뉴 언어 정보 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map selectMenuLanguageInfo(Map map) throws Exception {
        return (Map) selectByPk("SM7009.selectMenuLanguageInfo", map);
    }
	
	/**
     * 메뉴 언어 정보 추가
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int insertMenuLanguageInfo(Map map) throws Exception {
        log.debug("insertMenuLanguageInfo = " +  map.toString());
        return this.update("SM7009.insertMenuLanguageInfo", map);
    }
    
    /**
     * 메뉴 언어 정보 수정
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int updateMenuLanguageInfo(Map map) throws Exception {
        log.debug("updateMenuLanguageInfo = " +  map.toString());
        return this.update("SM7009.updateMenuLanguageInfo", map);
    }
}
