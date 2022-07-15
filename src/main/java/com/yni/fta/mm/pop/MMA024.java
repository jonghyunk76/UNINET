package com.yni.fta.mm.pop;

import java.util.List;
import java.util.Map;

/**
 * 공통 > 제품군 조회
 * 
 * @author YNI-maker
 *
 */
public interface MMA024 {
	
	/**
	 * 제품군 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectMainList(Map map) throws Exception;
}
