package com.yni.fta.mm.pop;

import java.util.List;
import java.util.Map;

/**
 *공통 > 가격신고서 인터페이스
 * 
 * @author YNI-maker
 *
 */
public interface MMB003 {

	/**
	 * 가격신고서 초기 작성시 보여줄 정보 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map selectEmptyImpPrnoInfo(Map map) throws Exception;
	
	/**
	 * 가격신고서 등록된 정보 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map selectRegistImpPrnoInfo(Map map) throws Exception;
	
	/**
	 * 가격신고서 정보 등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	String insertImpPrnoInfo(Map map) throws Exception;
	
	/**
	 * 가격신고서 정보 수정
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int updateImpPrnoInfo(Map map) throws Exception;
	
	/**
	 * 가격신고서 정보 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int deleteImpPrnoInfo(Map map) throws Exception;
	
}
