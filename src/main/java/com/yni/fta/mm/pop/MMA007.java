package com.yni.fta.mm.pop;

import java.util.List;
import java.util.Map;

/**
 * FTA Rule > [MX] NALADISA Code 조회
 * 
 * @author YNI-maker
 *
 */
public interface MMA007 {

	/**
	 * [MX]NALADISA Code 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectMainList(Map map) throws Exception;
}
