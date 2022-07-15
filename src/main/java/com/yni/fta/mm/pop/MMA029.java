package com.yni.fta.mm.pop;

import java.util.List;
import java.util.Map;

/**
 * 공통 > 도움말
 * 
 * @author YNI-maker
 *
 */
public interface MMA029 {

	/**
	 * 도움말 목록 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectHelpMenuList(Map map) throws Exception;
	
	/**
	 * 도움말 검색
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectKeyContents(Map map) throws Exception;
	
}
