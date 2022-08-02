package com.yni.fta.common.batch.delegate.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.gson.JsonObject;
import com.siebel.data.SiebelPropertySet;
import com.yni.fta.common.batch.delegate.Transfer;
import com.yni.fta.common.batch.vo.BatchVo;
import com.yni.fta.common.batch.vo.ParameterVo;
import com.yni.fta.common.tools.EAIJSONConverter;

import kr.yni.frame.util.StringHelper;
import stw.eai.dhl.siebel.STWDhlClient;

public class SiebelTransfer implements Transfer {
	
	private static Log log = LogFactory.getLog(SiebelTransfer.class);
	
	@Override
	public Object receive(BatchVo batchVo, Map map, Object datas) throws Exception {
		Map returnMap = new HashMap();
		Map batch = batchVo.getMap();
		
		String source = StringHelper.null2void(batch.get("DATA_PATH"));
		SiebelPropertySet input = (SiebelPropertySet) datas;
		SiebelPropertySet output = new SiebelPropertySet();
		
		try {
			STWDhlClient sendRouting = new STWDhlClient();
			
			// sendRouting.doInvokeMethod("Routing", input, output);
			sendRouting.doInvokeMethod(source, input, output);
			
			log.debug("Result data = " + output);
		} catch(Exception e) {
			log.error(e);
			returnMap.put("SERVER_CONNECT_YN", "N");
        	
        	batchVo.setReturnData(returnMap);
			batchVo.setErrorMessage("서버와의 통신이 실패했습니다.");
		}
		
		return output;
	}

	@Override
	public Object send(BatchVo batchVo, Map map, Object datas) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
