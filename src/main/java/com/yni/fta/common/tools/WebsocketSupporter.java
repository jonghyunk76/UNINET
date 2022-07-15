package com.yni.fta.common.tools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import kr.yni.frame.service.YniAbstractService;
import kr.yni.frame.util.StringHelper;

@ServerEndpoint("/toms/kcs/webscoket")
public class WebsocketSupporter extends YniAbstractService {
	
	public static List<Session> clients = Collections.synchronizedList(new ArrayList<>());
	
	public WebsocketSupporter() {}
	
	/***
     * 웹 소켓이 연결되면 호출되는 이벤트
     */
    @OnOpen
    public void handleOpen(Session session) throws Exception {
    	// session의 timeout을 무제한으로 변경함 - 시간을 넣으면 ping, pong의 사양이 제대로 걸리지 않고, 어느 순간 갑자기 커넥션을 종료시켜버리는 경우가 있어 추가함(2022-03-08)
    	session.setMaxIdleTimeout(0);
    	// binary의 buffer size를 설정함. 추후, 파일 업로드가 사용되면 주석을 풀고 적정한 수취를 입력할 것
//    	session.setMaxBinaryMessageBufferSize(1024 * 1024 * 10);
    	
    	clients.add(session);
    	
    	try {
	    	String uid = (String) session.getUserProperties().get("tomsSession");
	    	log.debug("Websocket opened..[session key = " + uid + ", client count = " + clients.size() + "]");
    	} catch(Exception e) {
    		log.error(e);
    	}
    }
    
    /**
     * 웹 소켓으로부터 메시지가 오면 호출되는 이벤트
     * @param message = 최초 사용되는 세션을 유지해서 구분한다.
     * @param session = client session
     * @return
     */
    @OnMessage
    public void handleMessage(String message, Session session) throws IOException {
    	String uid = (String) session.getUserProperties().get("tomsSession");
    	
    	// 최초 메시지에 해당하는 이력번호가 없는 경우에 session를 추가 설정함
    	if(uid == null) {
    		log.debug("create session name = " + message);
    		session.getUserProperties().put("tomsSession", message);
        }
    	
    	log.debug("Websocket send... [message = " + message +", session name = " + uid + ", client count = " + clients.size() + "]");
    	
    	if(session.isOpen()) {
    		session.getBasicRemote().sendText(message);
    	}
    }
    
    /**
     * 웹 소켓이 닫히면 호출되는 이벤트
     */
    @OnClose
    public void handleClose(Session session)  throws Exception {
    	log.debug("Websocket is now disconnected... [client count = " + clients.size() + "]");
    	
    	try {
    		if(session != null) {
    			clients.remove(session);
    		}
    	} catch(Exception e) {
    		log.debug("Web session remove fail...");
    	}
    	
    	log.debug("Websocket remove count = " + clients.size());
    }
    
    /**
     * 웹 소켓이 에러가 나면 호출되는 이벤트
     * 
     * @param t
     */
    @OnError
    public void handleError(Session session, Throwable t) throws Exception {
    	session.close(); // 에러가 발생하면 소켓을 닫아 재연결을 시도하게 한다.
        log.error(t.getMessage());
    }
    
    /**
     * 트랜젝션 번호에 맞는 세션정보를 찾는아 Session를 리턴한다.
     * 
     * @param uid
     * @return
     */
    public Session getSession(String uid) throws Exception {
    	Session rstSession = null;
    	Iterator<Session> iterator = clients.iterator();
    	
    	log.debug("request session id = " + uid + ", size = " + clients.size());
    	
        while(iterator.hasNext()){
            Session session = iterator.next();
            
            if(session.getUserProperties().get("tomsSession").equals(uid)) {
            	rstSession = session;
            }
        }
        
        log.debug("get Websocket..[session = " + rstSession + "]");
        
        return rstSession;
    }
    
    public static class WebSocketController extends WebsocketSupporter {
	    
    	/**
	     * 클라이언트 접속 리스트를 구한다.
	     * 
	     * @return 클라이언트 접속 리스트
	     */
	    public static List getClients(String key) throws Exception {
	    	List rstList = new LinkedList(); 
	    	Map semap = null;
	    	
	    	for(int i = 0; i < clients.size(); i++) {
	    		Session session = clients.get(i); 
	    		Map upmap = session.getUserProperties();
	    		String sekey = StringHelper.null2void(upmap.get("tomsSession"));
	    		
	    		semap = new HashMap();
	    		
	    		semap.put("SESSION_ID", session.getId());
	    		semap.put("SESSION_KEY", sekey);
	    		semap.put("URL", session.getRequestURI().getPath());
	    		semap.put("TIME_OUT", session.getMaxIdleTimeout());
	    		semap.put("BUFFER_SIZE", session.getMaxBinaryMessageBufferSize());
	    		
	    		if(key != null && !key.isEmpty()) {
	    			if(sekey.equals(key)) {
	    				rstList.add(semap);
	    			}
	    		} else {
	    			rstList.add(semap);
	    		}
	    	}
	    	
	    	return rstList;
	    }
	    
	    /**
	     * 웹 소켓의 세션을 강제로 닫는다.
	     */
	    public static int close(String uid)  throws Exception {
	    	int cnt = 0;
	    	Session target = null;
	    	
	    	for(int i = 0; i < clients.size(); i++) {
	    		Session session = clients.get(i);
	    		Map upmap = session.getUserProperties();
	    		
	    		if(upmap.get("tomsSession").equals(uid)) {
	    			target = session;
	    			if(session.isOpen()) target.close();
	    			
	    			cnt++;
	    		}
	    	}
	    	
	    	return cnt;
	    }
	    
	    /**
	     * 모든 웹소켓 세션을 초기화한다.
	     * @return
	     * @throws Exception
	     */
	    public static int clearAll() throws Exception {
	    	int cnt = 0;
	    	List<Session> noSession = new ArrayList();
	    	
	    	// 모든 세션을 닫는다.
	    	for(int i = 0; i < clients.size(); i++) {
	    		Session session = clients.get(i);
	    		Map upmap = session.getUserProperties();
	    		
	    		if(session.isOpen()) session.close();
	    		else noSession.add(session);
	    		
	    		cnt++;
	    	}
	    	
	    	// 완전히 닫혀지지 않은 세션이 있으면 제거한다.
	    	for(int i = 0; i < noSession.size(); i++) {
	    		String uid1 = (String) noSession.get(i).getUserProperties().get("tomsSession");
	    		
	    		for(int j = 0; j < clients.size(); j++) {
	    			Session session = clients.get(j);
		    		String uid2 = (String) session.getUserProperties().get("tomsSession");
		    		
		    		if(uid1.equals(uid2)) {
		    			session.close();
		    			clients.remove(j);
		    			
		    			break;
		    		}
	    		}
	    	}
	    	
	    	return cnt;
	    }
	    
    }
    
}
