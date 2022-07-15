package com.yni.fta.common.batch.delegate.impl;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yni.fta.common.batch.delegate.Transfer;
import com.yni.fta.common.batch.vo.BatchVo;
import com.yni.fta.common.batch.vo.ParameterVo;

public class SmtpTransfer implements Transfer {
	
	private static Log log = LogFactory.getLog(SmtpTransfer.class);

	@Override
	public boolean receive(BatchVo batchVo, Map map, ParameterVo pvo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean send(BatchVo batchVo, Map map, Map params) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
