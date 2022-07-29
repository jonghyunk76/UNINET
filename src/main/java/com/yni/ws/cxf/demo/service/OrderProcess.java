package com.yni.ws.cxf.demo.service;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.yni.ws.cxf.demo.vo.OrderVo;

@WebService
public interface OrderProcess {
	
	String processOrder(@WebParam(name="userName") String user);
	
	String getUserId(@WebParam(name="userId") String user);
	
	List getOrder(@WebParam(name="order") OrderVo vo);
	
}
