package com.yni.rs.batch;

import java.util.Map;

public interface ExportPackage {
	
	/**
	 * 송수신 결과를 리턴하기 위한 파라메터 조정
	 * 
	 * @param map = 처리결과 Map 정보
	 * @return 조정결과(아래와 같은 표준코드와 상태 코드로 조정)
	 *        - BATCH_STATUS : S(성공), E(실패), N(데이터 없음)
	 *        - ERROR_MESSAGE : 에러메시지
	 * @exception
	 */
	Map getParameter(Map map) throws Exception;
	
}
