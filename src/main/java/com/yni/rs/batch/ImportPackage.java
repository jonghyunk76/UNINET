package com.yni.rs.batch;

import java.util.Map;

import com.yni.fta.common.batch.vo.BatchVo;

public interface ImportPackage {
	
	/**
	 * 요청 파라메터 값 생성
	 * - JCO XML에 지정된 import 파라메터와 맵핑되는 정보를 생성해야 함
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Object getParameter(Map map) throws Exception;
	
	/**
	 * 직접 요청하기 위한 매소드
	 * - JCO XML 설정이 없는 경우에만 실행됨
	 * 
	 * @param batchVo
	 * @return
	 * @throws Exception
	 */
	void executeBatch(Object batchVo) throws Exception;
	
}
