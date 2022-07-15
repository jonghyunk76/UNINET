package com.yni.fta.common.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.yni.fta.common.controller.companySelect.CompanySelect;

import kr.yni.frame.web.action.WebAction;


public class CertifySkipController implements Controller {
    
    private Log log = LogFactory.getLog(CertifySkipController.class); 
    
    @Resource(name="companySelect")
	private CompanySelect companySelect;
    
    @Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	return WebAction.returnDataSet(companySelect.showCompanies(null));
	}
    

}