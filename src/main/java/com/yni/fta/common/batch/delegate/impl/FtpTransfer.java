package com.yni.fta.common.batch.delegate.impl;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yni.fta.common.batch.delegate.Transfer;
import com.yni.fta.common.batch.vo.BatchVo;
import com.yni.fta.common.batch.vo.ParameterVo;

public class FtpTransfer implements Transfer {
	
	private static Log log = LogFactory.getLog(FtpTransfer.class);
	
	@Override
	public Object receive(BatchVo batchVo, Map map, Object datas) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object send(BatchVo batchVo, Map map, Object datas) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
