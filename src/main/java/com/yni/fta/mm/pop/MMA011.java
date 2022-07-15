package com.yni.fta.mm.pop;

import java.util.List;
import java.util.Map;

/**
 * FTA Rule > FTA협정등록
 * 
 * @author YNI-maker
 *
 */
public interface MMA011 {
	
	/**
	 * FTA협정 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectMainList(Map map) throws Exception;
}
