package com.yni.rs.st;

import java.util.List;
import java.util.Map;

public interface STR003 {
	
	/**
	 * 서비스 이력 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectServiceHistoryList(Map map) throws Exception;
	
	/**
	 * 인터페이스 이력 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectInterfaeHistoryList(Map map) throws Exception;
	
}
