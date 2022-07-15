package com.yni.fta.mm.pop;

import java.util.List;
import java.util.Map;

/**
 * 공통 > 가입신청서 작성
 * 
 * @author YNI-maker
 *
 */
public interface MMA030 {

	/**
	 * 신규가입정보 등록여부 체크
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map selectJoinCompanyNo(Map map) throws Exception;
	
	/**
	 * 신규가입정보 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map selectJoinCompanyInfo(Map map) throws Exception;
	
	/**
	 * 신규가입정보 등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int insertJoinCompanyInfo(Map map) throws Exception;
	
	/**
	 * 신규가입정보 수정
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int updateJoinCompanyInfo(Map map) throws Exception;
	
}
