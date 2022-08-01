package com.yni.fta.common.ws;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yni.fta.common.batch.vo.BatchVo;
import com.yni.fta.common.tools.FTAPassSupporter;
import com.yni.fta.common.tools.XmlUtil;

import kr.yni.frame.Constants;
import kr.yni.frame.util.FileUtil;
import kr.yni.frame.util.StringHelper;

/**
 * FTA PASS 연계를 위한 웹서비스 클라이언트 클래스
 * 
 * @author ador2
 *
 */
public class WebServiceClient {
	
	private static Log log = LogFactory.getLog(WebServiceClient.class);
	
	public boolean doInvoke(BatchVo batchVo, String url, Map map) throws Exception {
		
		return true;
	}
	
}
