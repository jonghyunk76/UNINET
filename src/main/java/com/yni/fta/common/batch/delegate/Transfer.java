package com.yni.fta.common.batch.delegate;

import java.util.Map;

import com.yni.fta.common.batch.vo.BatchVo;
import com.yni.fta.common.batch.vo.ParameterVo;

public interface Transfer {
	
	/**
	 * 데이터 수신
	 * 
	 * @param batchVo 배치정보
	 * @param map 인터페이스 정보
	 * @param datas 요청할 데이터
	 * @return 배치결과
	 * @throws Exception
	 */
	Object receive(BatchVo batchVo, Map map, Object datas) throws Exception;
	
	/**
	 * 데이터 전송
	 * 
	 * @param batchVo 배치정보
	 * @param map 인터페이스 정보
	 * @param datas 전송할 데이터
	 * @return 배치결과
	 * @throws Exception
	 */
	Object send(BatchVo batchVo, Map map, Object datas) throws Exception;
	
}
