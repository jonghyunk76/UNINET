package com.yni.rs.batch.DHL_ROUTE_1.receive;

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
		log.debug("Receive Export : " + map);
		
		Map rmap = new HashMap();
		String code = StringHelper.null2void(map.get("result")); // SUCCESS
		String msg = StringHelper.null2void(map.get("Error"));
		
		if(code.equals("SUCCESS")) {
			rmap.put("BATCH_STATUS", "S"); // E:에러, S:성공, N:데이터 없음
		} else {
			rmap.put("BATCH_STATUS", "E");
		}
		
		rmap.put("ERROR_MESSAGE", msg);
		
		return rmap;
	}

}
