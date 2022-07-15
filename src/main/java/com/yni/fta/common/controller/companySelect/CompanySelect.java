package com.yni.fta.common.controller.companySelect;

import java.util.List;
import java.util.Map;

/**
 * 마감 및 통계 > 마감관리
 * 
 * @author YNI-maker
 *
 */
public interface CompanySelect {
	
	/**
	 * 마감 실행 및 이력 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List showCompanies(Map map) throws Exception;
}
