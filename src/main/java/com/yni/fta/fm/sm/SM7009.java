package com.yni.fta.fm.sm;

import java.util.List;
import java.util.Map;

/**
 * 시스템관리 > 메뉴관리
 * 
 * @author sbj1000
 *
 */
public interface SM7009 {
	
	/**
	 * 메뉴리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectMenuList(Map map) throws Exception;
	List selectMenuLanguageList(Map map) throws Exception;
	public Map selectMenuInfo(Map map) throws Exception;
	public int insertMenuInfo(Map map) throws Exception;
	public int updateMenuInfo(Map map) throws Exception;
	public Map selectMenuLanguageInfo(Map map) throws Exception;
	public int insertMenuLanguageInfo(Map map) throws Exception;
	public int updateMenuLanguageInfo(Map map) throws Exception;
}
