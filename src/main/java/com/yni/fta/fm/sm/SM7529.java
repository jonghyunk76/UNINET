package com.yni.fta.fm.sm;

import java.util.List;
import java.util.Map;

/**
 * 환경설정 > Set up > Web Socket관리 Service Interface Class
 * 
 * @author YNI-maker
 *
 */
public interface SM7529 {

	/**
	 * Web Socket 세션 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectWebSocketList(Map map) throws Exception;
	
	/**
	 * Web Socket 세션 강제종료 시키기
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int removeUserWebSocket(Map map) throws Exception;
	
	/**
	 * Web Socket 초기화
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int removeAllWebSocket(Map map) throws Exception;
	
}
