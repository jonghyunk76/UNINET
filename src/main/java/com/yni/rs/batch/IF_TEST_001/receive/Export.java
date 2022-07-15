package com.yni.rs.batch.IF_TEST_001.receive;

import java.util.HashMap;
import java.util.Map;

import com.yni.fta.common.parameter.YniAbstractBatch;
import com.yni.rs.batch.ExportPackage;

import kr.yni.frame.util.StringHelper;

public class Export extends YniAbstractBatch implements ExportPackage {
	
	/**
	 * 송수신 결과를 리턴하기 위한 파라메터 조정
	 * 
	 * @param map = 처리결과 Map 정보
	 * @return 조정결과(아래와 같은 표준코드와 상태 코드로 조정)
	 *        - BATCH_STATUS : S(성공), E(실패), N(데이터 없음)
	 *        - ERROR_MESSAGE : 에러메시지
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map getParameter(Map map) throws Exception {
		log.debug("IF_TEST_001 Receive Export : " + map);
		
		Map rmap = new HashMap();
		String ercode = StringHelper.null2void(map.get("BATCH_STATUS")); // E:에러, S:성공, N:데이터 없음
		String ermsg = StringHelper.null2void(map.get("ERROR_MESSAGE"));
		
		if(ercode.equals("1")) {
			rmap.put("BATCH_STATUS", "E");
			rmap.put("ERROR_MESSAGE", ermsg);
		}
		
		return rmap;
	}

}
