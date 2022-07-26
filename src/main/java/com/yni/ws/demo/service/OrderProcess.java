package com.yni.ws.demo.service;

import javax.jws.WebService;

@WebService
public interface OrderProcess {
	
	String processOrder(String user);
	
}
