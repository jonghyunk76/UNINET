package com.yni.fta.mm.pop;

import java.util.List;
import java.util.Map;

/**
 * FTA Rule > NALADISA 리스트
 * 
 * @author YNI-maker
 *
 */
public interface MMA012 {

	/**
	 * NALADISA 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectMainList(Map map) throws Exception;
}
