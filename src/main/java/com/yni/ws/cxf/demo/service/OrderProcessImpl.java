package com.yni.ws.cxf.demo.service;

import java.util.LinkedList;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.yni.ws.cxf.demo.vo.OrderVo;

import kr.yni.frame.service.YniAbstractService;

@WebService(endpointInterface = "com.yni.ws.cxf.demo.service.OrderProcess",
			serviceName = "OrderProcessService",
			portName = "OrderProcessPort",
			targetNamespace = "demo.ws.yni.com")
public class OrderProcessImpl extends YniAbstractService implements OrderProcess {
	
	@Override
	public String processOrder(@WebParam(name="userName") String user) {
		log.debug("input parameter : user name = " + user);
		
		if(user == null) {
			return "error";
		} else if(user.equals("aaa")) {
			return "test_return1";
		} else {
			return "test_return2";
		}
	}
	
	@Override
	public String getUserId(@WebParam(name="userId") String user) {
		log.debug("input parameter : user name = " + user);
		
		return user;
	}
	
	public List getOrder(@WebParam(name="order") OrderVo vo) {
		log.debug("Order Vo value = " + vo.getOrderNumber());
		List rlist = new LinkedList();
		
		OrderVo ovo = new OrderVo();
		ovo.setOrderNumber("12345");
		rlist.add(ovo);
		
		ovo = new OrderVo();
		ovo.setOrderNumber("67890");
		rlist.add(ovo);
		
		return rlist;
	}
	
}
