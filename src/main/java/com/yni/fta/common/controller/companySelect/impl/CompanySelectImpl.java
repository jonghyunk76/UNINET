package com.yni.fta.common.controller.companySelect.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.yni.fta.common.controller.CertifySkipController;
import com.yni.fta.common.controller.companySelect.CompanySelect;

import kr.yni.frame.service.YniAbstractService;

/**
 * 마감 및 통계 > 마감관리
 * 
 * @author YNI-maker
 *
 */
@Service("companySelect")
public class CompanySelectImpl extends YniAbstractService implements CompanySelect {
	
	@Resource(name="companySelectDao")
	private CompanySelectDao companySelectDao;
	
	private Log log = LogFactory.getLog(CertifySkipController.class); 
	
	@Override
	public List showCompanies(Map map) throws Exception {
		log.debug("test");
		return companySelectDao.getCompanyList(map);
	}
	
}
