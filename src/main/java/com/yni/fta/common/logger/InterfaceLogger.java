package com.yni.fta.common.logger;

import java.util.Date;
import java.util.Map;
import java.util.StringJoiner;

import javax.servlet.http.HttpServletRequest;

import kr.yni.frame.util.DateHelper;
import kr.yni.frame.util.StringHelper;

public class InterfaceLogger {
	
	private static String out_time = "Y"; // 시간 출력여부
	private static String out_ip = "Y"; // Remote IP 출력여부
	private static String out_trans_id = "Y"; // Transaction ID 출력여부
	
	public InterfaceLogger() {}
	
	public InterfaceLogger(Map map) {
		out_time = StringHelper.null2string(map.get("OUT_TIME"), "Y");
		out_ip = StringHelper.null2string(map.get("OUT_IP"), "Y");
		out_trans_id = StringHelper.null2string(map.get("OUT_TRANS_ID"), "Y");
	}
	
	/**
	 * 디버그용 로그 생성
	 * 
	 * @param map
	 * @return
	 */
	public static String debug(String msg) {
		String label = "DEBUG[";
		
		return label+getHeaderMessage()+"] " + msg;
	}
	
	/**
	 * 정보용 로그 생성
	 * @param map
	 * @return
	 */
	public static String info(String msg) {
		String label = "INFO[";
		
		return label+getHeaderMessage()+"] " + msg;
	}
	
	/**
	 * 에러용 로그생성
	 * @param map
	 * @return
	 */
	public static String error(String msg) {
		String label = "ERROR[";
		
		return label+getHeaderMessage()+"] " + msg;
	}
	
	/**
	 * fatal용 로그생성
	 * @param map
	 * @return
	 */
	public static String fatal(String msg) {
		String label = "FATAL[";
		
		return label+getHeaderMessage()+"] " + msg;
	}
	
	/**
	 * 경고형 로그생성
	 * @param map
	 * @return
	 */
	public static String warring(String msg) {
		String label = "WARRING[";
		
		return label+getHeaderMessage()+"] " + msg;
	}
	
	public static String getHeaderMessage() {
		return getHeaderMessage(null);
	}
	
	public static String getHeaderMessage(HttpServletRequest request) {
	    StringJoiner msg = new StringJoiner(" ");
		
		if(out_time.equals("Y")) {
			Date date = new Date();
			msg.add(DateHelper.toString(date, null, null));
		}
		
		if(request != null && out_ip.equals("Y")) {
			msg.add(request.getRemoteAddr());
		}
		
		if(request != null && out_trans_id.equals("Y")) {
			msg.add(StringHelper.null2void(request.getAttribute("IF_TRANS_ID")));
		}
		
		return msg.toString();
	}
	
	public static String getRealtimeMessage(HttpServletRequest req, String type, String name, String sta, long time) {
	    StringJoiner msg = new StringJoiner(" ");
		
	    // 1.송신, 수신 구분
	    if(type.equals("S")) {
	    	msg.add("[Send]");
	    } else {
	    	msg.add("[Receive]");
	    }
	    
	    // 2.일자 시간
		if(out_time.equals("Y")) {
			Date date = new Date();
			msg.add(DateHelper.toString(date, null, null));
		}
		
		// 3.요청명
		msg.add(name);
		
		// 4.처리상태
		if(sta.equals("0")) msg.add("success");
		else if(sta.equals("1")) msg.add("error");
		else if(sta.equals("2")) msg.add("empty");
		else msg.add("unkown");
		
		// 5.소요시간
		msg.add(Long.toString(time)+"msec.");
		
		return msg.toString();
	}
	
}
