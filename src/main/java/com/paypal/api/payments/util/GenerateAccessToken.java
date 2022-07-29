package com.paypal.api.payments.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import com.paypal.core.rest.OAuthTokenCredential;
import com.paypal.core.rest.PayPalRESTException;
import com.paypal.core.rest.PayPalResource;
import com.siebel.eai.SiebelBusinessServiceException;

public class GenerateAccessToken {

	public static String getAccessToken() throws PayPalRESTException{

		// ###AccessToken
		// Retrieve the access token from
		// OAuthTokenCredential by passing in
		// ClientID and ClientSecret
		Properties properties	= new Properties();
		FileInputStream	is		= null;
		String clientID			= "";
		String clientSecret		= "";
		String token			= "";
		
		try {
//			is	= new FileInputStream(new File("D:\\SBA811\\ses\\siebsrvr\\BIN\\sdk_config.properties"));
			is	= new FileInputStream(new File("C:\\Project\\01. 臾몄꽌\\F_HTW CRM �슂泥��옄猷�\\6. Interface\\�씤�꽣�럹�씠�뒪 怨듭쑀 �뤃�뜑\\�냼�뒪\\STW EAI\\STW EAI\\bin\\sdk_config.properties"));
			try {
				PayPalResource.initConfig(is);
				is.close();
			} catch (PayPalRESTException e) {
				throw new SiebelBusinessServiceException(e,"doCapture:init:","doCapture:init: "+e.getMessage());
			} finally {
				is.close();
				is	= null;
			}
			
//			is	= new FileInputStream(new File("D:\\SBA811\\ses\\siebsrvr\\BIN\\sdk_config.properties"));
			is	= new FileInputStream(new File("C:/iFTA/workspace_2021/STW EAI/STW EAI/bin/sdk_config.properties"));
			properties.load(is);
			is.close();
			
			HashMap<String,String> map = new HashMap<String,String>();
			map.put("http.ConnectionTimeOut", properties.getProperty("http.ConnectionTimeOut"));
			map.put("http.Retry", properties.getProperty("http.Retry"));
			map.put("http.ReadTimeOut", properties.getProperty("http.ReadTimeOut"));
			map.put("http.MaxConnection", properties.getProperty("http.MaxConnection"));
			map.put("http.UseProxy", properties.getProperty("http.UseProxy"));
			map.put("http.ProxyPort", properties.getProperty("http.ProxyPort"));
			map.put("http.ProxyHost", properties.getProperty("http.ProxyHost"));
			map.put("http.ProxyUserName", properties.getProperty("http.ProxyUserName"));
			map.put("http.ProxyPassword", properties.getProperty("http.ProxyPassword"));
			map.put("http.GoogleAppEngine", properties.getProperty("http.GoogleAppEngine"));
			map.put("service.EndPoint", properties.getProperty("service.EndPoint"));
			map.put("clientID", properties.getProperty("clientID"));
			map.put("clientSecret", properties.getProperty("clientSecret"));
			
			clientID		= properties.getProperty("clientID");
			clientSecret	= properties.getProperty("clientSecret");
			
			System.out.println("ID: "+clientID);
			System.out.println("PW: "+clientSecret);
			token			= new OAuthTokenCredential(clientID, clientSecret,map).getAccessToken();
			System.out.println("TK: "+token);
		} catch (PayPalRESTException e) {
			e.printStackTrace();
		} catch (Exception e){
			
		}
		finally
		{
			properties	= null;
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			is			= null;
		}
		return token;
	}
}
