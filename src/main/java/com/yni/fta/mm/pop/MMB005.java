package com.yni.fta.mm.pop;

import java.util.List;
import java.util.Map;

/**
 *공통 > 잠정신고 인터페이스
 * 
 * @author YNI-maker
 *
 */
public interface MMB005 {

	/**
	 * 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectMainList(Map map) throws Exception;
	
}
