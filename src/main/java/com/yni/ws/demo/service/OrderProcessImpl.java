package com.yni.ws.demo.service;

import javax.jws.WebService;

import kr.yni.frame.service.YniAbstractService;

@WebService(endpointInterface = "com.yni.ws.demo.service.OrderProcess")
public class OrderProcessImpl extends YniAbstractService implements OrderProcess {
	
	@Override
	public String processOrder(String user) {
		log.debug("input parameter : user name = " + user);
		
		if(user == null) {
			return "error";
		} else if(user.equals("aaa")) {
			return "test_return1";
		} else {
			return "test_return2";
		}
	}

}
