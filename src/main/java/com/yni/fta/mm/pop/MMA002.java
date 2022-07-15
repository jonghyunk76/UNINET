package com.yni.fta.mm.pop;

import java.util.List;
import java.util.Map;

/**
 * 공통팝업 : 사업부 및 회사 조회
 * 
 * @author YNI-maker
 *
 */
public interface MMA002 {

	/**
	 * 사업부 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectMainList(Map map) throws Exception;
	
	/**
	 * 회사 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectMainCompanyList(Map map) throws Exception;
	
	/**
	 * 계약회사 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectContractCompanyList(Map map) throws Exception;
	
}
