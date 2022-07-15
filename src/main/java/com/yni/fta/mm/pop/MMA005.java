package com.yni.fta.mm.pop;

import java.util.List;
import java.util.Map;

/**
 * 공통팝업 > 품목 조회
 * 
 * @author YNI-maker
 *
 */
public interface MMA005 {

	/**
	 * 품목 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectMainList(Map map) throws Exception;
	
	/**
	 * BOM 자재 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectBomItemList(Map map) throws Exception;
	
}
