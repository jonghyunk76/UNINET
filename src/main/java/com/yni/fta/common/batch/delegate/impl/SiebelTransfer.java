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
	public boolean receive(BatchVo batchVo, Map map, ParameterVo pvo) throws Exception {
		boolean result = true;
		Map returnMap = new HashMap();
		Map batch = batchVo.getMap();
		Map param = pvo.getMap();
		
		String source = StringHelper.null2void(batch.get("DATA_PATH"));
		SiebelPropertySet input = (SiebelPropertySet) param.get("REQ_PARAMS");
		
		try {
			SiebelPropertySet output = new SiebelPropertySet();
			STWDhlClient sendRouting = new STWDhlClient();
			
			// sendRouting.doInvokeMethod("Routing", input, output);
			sendRouting.doInvokeMethod(source, input, output);
			
			JsonObject jobj = EAIJSONConverter.PropertySetToJsonObject(output, null);
			
			log.debug("Return name = " + source + ", data = " + jobj.toString());
			
			if(jobj != null) {
				batchVo.setReturnData(jobj);
			}
		} catch(Exception e) {
			log.error(e);
			result = false;
			 
			returnMap.put("SERVER_CONNECT_YN", "N");
        	
        	batchVo.setReturnData(returnMap);
			batchVo.setErrorMessage("서버와의 통신이 실패했습니다.");
		}
		
		return result;
	}

	@Override
	public boolean send(BatchVo batchVo, Map map, Map params) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
	
}
