package com.yni.fta.common.batch.delegate;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yni.fta.common.batch.delegate.impl.FtpTransfer;
import com.yni.fta.common.batch.delegate.impl.HttpTransfer;
import com.yni.fta.common.batch.delegate.impl.JcoTransfer;
import com.yni.fta.common.batch.delegate.impl.SmtpTransfer;
import com.yni.fta.common.batch.vo.BatchVo;
import com.yni.fta.common.batch.vo.ParameterVo;

import kr.yni.frame.util.StringHelper;

public class DataHandler {
	
	private static Log log = LogFactory.getLog(DataHandler.class);
	
	private Transfer trans;
	
	private BatchVo batchVo;
	
	public DataHandler(BatchVo batchVo, String protocal) {
		log.debug("Transfer protocal type = " + protocal);
    	
    	if(protocal.equals("HTTP")) {
    		trans = new HttpTransfer();
    	} else if(protocal.equals("JCO")) {
    		trans = new JcoTransfer();
    	} else if(protocal.equals("FTP")) {
    		trans = new FtpTransfer();
    	} else if(protocal.equals("SMTP")) {
    		trans = new SmtpTransfer();
    	}
    	
    	this.batchVo = batchVo;
	}
	
	/**
	 * 데이터 수신
	 * 
	 * @param map = 인터페이스 정보
	 * @param pvo = 배치 파라메터
	 * @return
	 * @throws Exception
	 */
	public boolean receive(Map map, ParameterVo pvo) throws Exception {
    	if(trans == null || batchVo == null) {
    		return false;
    	} else {
	    	return trans.receive(batchVo, map, pvo);
    	}
	}
	
	/**
	 * 데이터 송신
	 * 
	 * @param map = 인터페이스 정보
	 * @param datas = 전송 데이터
	 * @return
	 * @throws Exception
	 */
	public boolean send(Map map, Map datas) throws Exception {
    	if(trans == null || batchVo == null) {
    		return false;
    	} else {
	    	return trans.send(batchVo, map, datas);
    	}
	}
	
}
