package com.yni.fta.common.tools;

import java.net.Socket;

/**
 * java socket을 지원하는 class
 * 
 * @author ador2
 *
 */
public class SocketSupporter {
	
	/**
	 * 서버가 열려있는지 체크하고, 열러있는면 true를 리턴한다.
	 * 
	 * @param host ip
	 * @param port
	 * @return true/false 
	 */
	public boolean availablePort(String host, int port) {
		boolean result = false;
	 
		try {
		    (new Socket(host, port)).close();
		    
		    result = true;
		} catch(Exception e) {
			result = false;
		}
		
	    return result;
	}
	
}
