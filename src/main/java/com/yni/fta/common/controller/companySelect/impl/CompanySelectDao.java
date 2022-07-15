package com.yni.fta.common.controller.companySelect.impl;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import kr.yni.frame.dao.YniAbstractDAO;

/**
 * companySelectDao
 */
@Repository("companySelectDao")
public class CompanySelectDao extends YniAbstractDAO {
	
	/**
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List getCompanyList(Map map) throws Exception {
		return list("Skip.companySelect", map);
	}
}
