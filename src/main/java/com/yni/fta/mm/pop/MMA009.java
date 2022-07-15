package com.yni.fta.mm.pop;

import java.util.List;
import java.util.Map;

/**
 * FTA Rule > [MX] 품목분류 관리
 * 
 * @author YNI-maker
 *
 */
public interface MMA009 {

	/**
	 * [MX] 품목분류 관리 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectMainList(Map map) throws Exception;
}
