package com.yni.fta.common.tools;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kr.yni.frame.util.FileUtil;

public class FileSupporter extends FileUtil {
	
	private static Log log = LogFactory.getLog(FileSupporter.class);
			
	public FileSupporter() { }
	
	/**
     * 파일을 지정된 디렉토리로 이동
     * 
     * @param path 파일경로
     * @param fileName 파일명
     * @param movePath 이동파일경로
     * @param del 이동전 파일 삭제여부
     * @throws Exception
     */
    public static boolean fileCopy(String path, String fileName, String newPath, boolean del) throws Exception{
    	boolean rst = true;
    	byte[] buffer = new byte[1024];
        int length;
        InputStream is = null;
        OutputStream os = null;
        
        File source = new File(path + File.separator + fileName);
    	File dest = new File(newPath + File.separator + fileName);
    	
        try {
	    	if(source.isFile()){
	    		is = new FileInputStream(source);
	            os = new FileOutputStream(dest);
	            
	            while ((length = is.read(buffer)) > 0) {
	                os.write(buffer, 0, length);
	            }
	    	} else {
	    		rst = false;
	    	}
        } finally {
            if(is != null) is.close();
            if(os != null) os.close();
            
            if(rst) {
        		if(del) {
        			source.delete();
        			
        			log.debug("deleted before file...");
        		}
        	}
        }
    	
    	return rst;
    }
    
    public static boolean createFile2Btye(byte[] data, String path, String fileName) throws Exception {
    	boolean result = true;
    	DataOutputStream dis = null;
    	
    	if(path == null) path = getFullPath(); // 임시저장 디렉토리
    	
    	try {
    		File dest = new File(path + fileName);    		
    		FileOutputStream fout = new FileOutputStream(dest); 
    		dis = new DataOutputStream(fout); 
    		
    		dis.write(data);
    		dis.flush();
    		
    		log.debug("created file = " + path + fileName + ", size = " + dest.length());
    	} catch(Exception e) {
    		throw e;
    	} finally {
    		try {
    			if(dis != null) dis.close();
    		} catch(Exception ex) {
    			throw ex;
    		}
    	}
    	
    	return result;
    }
    
}
