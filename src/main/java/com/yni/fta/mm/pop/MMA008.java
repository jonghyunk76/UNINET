package com.yni.fta.mm.pop;

import java.util.List;
import java.util.Map;

/**
 * FTA Rule > [MX] Trace List 관리
 * 
 * @author YNI-maker
 *
 */
public interface MMA008 {

	/**
	 * [MX] Trace 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectMainList(Map map) throws Exception;
}
