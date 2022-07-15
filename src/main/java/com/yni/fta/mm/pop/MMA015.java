package com.yni.fta.mm.pop;

import java.util.List;
import java.util.Map;

/**
 * FTA Rule > HS Code 조회
 * 
 * @author YNI-maker
 *
 */
public interface MMA015 {

	/**
	 * 표준 Code 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectMainList(Map map) throws Exception;
	
	/**
	 * 기관발급용 표준 Code 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectKcsStandardCodeList(Map map) throws Exception;
	
}
