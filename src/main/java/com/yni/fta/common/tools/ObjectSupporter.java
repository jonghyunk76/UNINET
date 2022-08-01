package com.yni.fta.common.tools;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.gson.JsonObject;

public class ObjectSupporter {
	
	private static Log log = LogFactory.getLog(ObjectSupporter.class);
	
	public static long getObjectSize(Object obj) {
		long len = 0;
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = null;
		
		try {
			if(obj instanceof JsonObject) {
				len = ((JsonObject) obj).getAsByte();
			} else if(obj instanceof String) {
				len = obj.toString().getBytes().length;
			} else {
				oos = new ObjectOutputStream(baos);
				oos.writeObject(obj);
				
				len = baos.size();
			}
		} catch(Exception e) {
			log.error(e);
		} finally {
			try {
				if(oos != null) oos.close();
			} catch(Exception e) {
				log.error(e);
			}
		}
		
		return len;
	}
	
}
