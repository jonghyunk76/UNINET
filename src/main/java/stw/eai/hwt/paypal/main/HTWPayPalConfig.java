package stw.eai.hwt.paypal.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import com.paypal.core.rest.PayPalRESTException;
import com.paypal.core.rest.PayPalResource;
import com.siebel.eai.SiebelBusinessServiceException;

public class HTWPayPalConfig {

	public static HashMap<String,String> getPayPalConfig() throws SiebelBusinessServiceException {
		
		Properties properties	= new Properties();
		FileInputStream	is		= null;
		HashMap<String,String> map = new HashMap<String,String>();
		
		try {
//			is	= new FileInputStream(new File("D:\\SBA811\\ses\\siebsrvr\\BIN\\sdk_config.properties"));
			is	= new FileInputStream(new File("C:/iFTA/workspace_2021/STW EAI/STW EAI/bin/sdk_config.properties"));
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
			map.put("mode", properties.getProperty("mode"));	// 신규			
			
		} catch (Exception e){
			
			throw new SiebelBusinessServiceException(e,"doCapture:init:","doCapture:init: "+e.getMessage());
			
		} finally {
			properties = null;
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			is = null;
		}
		return map;
	}
	
}
