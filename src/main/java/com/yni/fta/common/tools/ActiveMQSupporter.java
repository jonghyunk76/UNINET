package com.yni.fta.common.tools;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

//import javax.jms.Connection;
//import javax.jms.ConnectionFactory;
//import javax.jms.Destination;
//import javax.jms.JMSException;
//import javax.jms.MapMessage;
//import javax.jms.Message;
//import javax.jms.MessageConsumer;
//import javax.jms.MessageProducer;
//import javax.jms.Session;

//import org.apache.activemq.ActiveMQConnection;
//import org.apache.activemq.ActiveMQConnectionFactory;
//import org.apache.activemq.command.ActiveMQQueue;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kr.yni.frame.util.StringHelper;

public class ActiveMQSupporter {
	
	protected static Log log = LogFactory.getLog(ActiveMQSupporter.class);
	
	public void send(Map msg) throws Exception {
		this.send(null, null, msg);
	}
	
	public void send(String subject, Map msg) throws Exception {
		this.send(null, subject, msg);
	}
	
	/**
	 * Message 전송
	 * 
	 * @param url = tcp://localhost:61616
	 * @param subject = 큐명칭
	 * @param msg = MQ에 보낼 정보
	 * @throws Exception
	 */
	public void send(String url, String subject, Map msg) throws Exception {
		/*
		Connection conn = null;
		Session session = null;
		
		if(subject != null) subject = "TOMS_QUEUE_" + subject;
		else subject = "TOMS_QUEUE";
		if(url == null) url = ActiveMQConnection.DEFAULT_BROKER_URL;
		
		try {
			ConnectionFactory cf = new ActiveMQConnectionFactory(url);
			
			conn = cf.createConnection();
			session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination destination1 = new ActiveMQQueue(subject); // send Queue
			MessageProducer producer = session.createProducer(destination1);
			Destination destination2 = session.createQueue(subject); // receive Queue
			
			MapMessage message = session.createMapMessage();
			
			if(msg != null && msg.size() > 0) {
				Iterator iter = msg.entrySet().iterator();
				
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					
					message.setObject(StringHelper.null2void(entry.getKey()), entry.getValue());
				}
			}
			
			log.debug("Message Information = " + message.toString());
			
			message.setJMSReplyTo(destination2);
			producer.send(destination1, message);
		} catch (JMSException e) {
			log.error(e);
			
			throw e;
		} finally {
			try {
				if(session != null) { session.close(); }
				if(conn != null) { conn.close(); }
			} catch (JMSException ex) {
				log.error(ex);
			}
		}
		*/
	}
	
	/**
	 * 메시지 수신
	 * 
	 * @param url
	 * @param if_code
	 * @return
	 * @throws Exception
	 */
	public Map getMessage(String url, String subject) throws Exception {
		Map rst = new HashMap();
		/*
		Connection conn = null;
		Session session = null;
		if(subject != null) subject = "TOMS_QUEUE_" + subject;
		else subject = "TOMS_QUEUE";
		if(url == null) url = ActiveMQConnection.DEFAULT_BROKER_URL;
		
		try {
			ConnectionFactory cf = new ActiveMQConnectionFactory(url);
			
			conn = cf.createConnection();
			session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);

			Destination destination = new ActiveMQQueue(subject);
			MessageConsumer consumer = session.createConsumer(destination);
			
			conn.start();
			
			while(true) {
				Message message = consumer.receive(1);
				
				if(message != null) {
					MapMessage mapMsg = (MapMessage) message;
					
					Enumeration<String> en = mapMsg.getMapNames();
					
					while (en.hasMoreElements()) {
					    String key = en.nextElement();
					    
					    rst.put(key, mapMsg.getObject(key));
					}
					
					log.debug("receive map = " + rst.toString());
				} else {
					break;
				}
			}
		} catch (JMSException e) {
			log.error(e);
			
			throw e;
		} finally {
			try {
				if(session != null) { session.close(); }
				if(conn != null) { conn.close(); }
			} catch (JMSException ex) {
				log.error(ex);
			}
		}
		*/
		return rst;
	}
	
}
