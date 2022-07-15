package com.yni.fta.mm.pop;

import java.util.List;
import java.util.Map;

/**
 * FTA Rule > FTA 옵션등록
 * 
 * @author YNI-maker
 *
 */
public interface MMA014 {

	/**
	 * FTA옵션 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectMainList(Map map) throws Exception;
}
