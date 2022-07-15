package com.yni.fta.mm.pop;

import java.util.List;
import java.util.Map;

/**
 * 배치 > 인터페이스 이력 데이터 상세 조회
 * 
 * @author YNI-maker
 *
 */
public interface MMA027 {

	/**
	 * 데이터 그리드 해더 정보 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectHeaderInfo(Map map) throws Exception;
	
	/**
	 * 서비스 방식에서 데이터 그리드 해더 정보 조회
	 * 
	 * @param map 검색 조건
	 */
	List selectServiceHeaderInfo(Map map) throws Exception;
	
	/**
	 * 인터페이스 이력 상세 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectInterfaceHistoryList(Map map) throws Exception;
	
}
