package com.yni.fta.common.controller;

import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import kr.yni.frame.Constants;
import kr.yni.frame.config.Configurator;
import kr.yni.frame.config.ConfiguratorException;
import kr.yni.frame.config.ConfiguratorFactory;
import kr.yni.frame.util.StringHelper;

public class MultipartResolverController extends CommonsMultipartResolver{
	
	private static final Log log = LogFactory.getLog(MultipartResolverController.class);
			
	public MultipartResolverController() { }
	
	public void setMaxInMemorySize(int size) {
		Configurator configurator;
		
		try {
			if(size == -1) {
				configurator = ConfiguratorFactory.getInstance().getConfigurator();
				size = Constants.FILE_MAX_MEMORY_SIZE;
			}
		} catch (ConfiguratorException e) {
			e.printStackTrace();
		}
		
		if(log.isDebugEnabled()) log.debug("max in memory size = " + size);
		
		super.setMaxInMemorySize(size);
	}
	
	public void setMaxUploadSize(long size) {
		Configurator configurator;
		
		try {
			if(size == -1) {
				configurator = ConfiguratorFactory.getInstance().getConfigurator();
				size = Constants.FILE_MAX_UPLOAD_SIZE;
			}
		} catch (ConfiguratorException e) {
			e.printStackTrace();
		}
		
		if(log.isDebugEnabled()) log.debug("max upload size = " + size);
		
		super.setMaxUploadSize(size);
	}
	
	/**
	 * Cleanup any resources used for the multipart handling, like a storage for the uploaded files.
	 * @param request - the request to cleanup resources for
	 */
	public void cleanupMultipart(MultipartHttpServletRequest request) {
		Map multipartFiles = request.getFileMap();
		
		for (Iterator it = multipartFiles.values().iterator(); it.hasNext();) {
			CommonsMultipartFile file = (CommonsMultipartFile) it.next();
			
			if(file.getFileItem().isInMemory()) file.getFileItem().delete();
		}
	}
	
}
