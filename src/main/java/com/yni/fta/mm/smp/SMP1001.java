package com.yni.fta.mm.smp;

import java.util.List;
import java.util.Map;

/**
 * FTA Rule > FTA협정등록
 * 
 * @author YNI-maker
 *
 */
public interface SMP1001 {
	
	/**
	 * FTA협정 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectMainList(Map map) throws Exception;
}
