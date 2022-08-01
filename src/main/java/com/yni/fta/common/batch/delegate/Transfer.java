package com.yni.fta.common.batch.delegate;

import java.util.Map;

import com.yni.fta.common.batch.vo.BatchVo;
import com.yni.fta.common.batch.vo.ParameterVo;

public interface Transfer {
	
	/**
	 * 데이터 수신
	 * 
	 * @param batchVo 배치정보
	 * @param map
	 * @param pvo
	 * @return 배치결과
	 * @throws Exception
	 */
	boolean receive(BatchVo batchVo, Map map, ParameterVo pvo) throws Exception;
	
	/**
	 * 데이터 전송
	 * 
	 * @param batchVo = 배치정보
	 * @param map = 인터페이스 정보
	 * @param params = 전송할 데이터
	 * @return
	 * @throws Exception
	 */
	boolean send(BatchVo batchVo, Map map, Object datas) throws Exception;
	
}
