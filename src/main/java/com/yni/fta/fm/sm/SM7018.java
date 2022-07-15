package com.yni.fta.fm.sm;

import java.util.List;
import java.util.Map;

/**
 * 운영관리 > 마감현황 및 관리
 * 
 * @author YNI-maker
 *
 */
public interface SM7018 {
	
	/**
	 * 사용자 로그 목록 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectUseLogList(Map map) throws Exception;
	
	/**
	 * 사용자 로그 통계정보 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectUseLogStatusList(Map map) throws Exception;
	
}
