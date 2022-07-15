package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.fta.mm.pop.MMA001;

import kr.yni.frame.service.YniAbstractService;

/**
 * FTA Rule > FTA협정등록
 * 
 * @author YNI-maker
 *
 */
@Service("mmA001")
public class MMA001Impl extends YniAbstractService implements MMA001 {
	
	@Resource(name="mmA001Dao")
	private MMA001Dao mmA001Dao;
	
	public Map selectClosingInfo(Map map) throws Exception {
		return mmA001Dao.selectClosingInfo(map);
	}
	
	public Map selectDataInterfaceCount(Map map) throws Exception {
		return mmA001Dao.selectDataInterfaceCount(map);
	}
	
	public List selectVendorCoCount(Map map) throws Exception {
		return mmA001Dao.selectVendorCoCount(map);
	}
	
	public Map selectInOutCount(Map map) throws Exception {
		return mmA001Dao.selectInOutCount(map);
	}
	
	public List selectDeterminateCount(Map map) throws Exception {
		return mmA001Dao.selectDeterminateCount(map);
	}
	
	public List selectMonthlyDeterminateCount(Map map) throws Exception {
		return mmA001Dao.selectMonthlyDeterminateCount(map);
	}
	
	public Map selectIssueReportCount(Map map) throws Exception {
		return mmA001Dao.selectIssueReportCount(map);
	}
	
	public int updateWorkDate(Map map) throws Exception {
		return mmA001Dao.updateWorkDate(map);
	}
	
	public Map selectSupplierIssueStatus(Map map) throws Exception {
		return mmA001Dao.selectSupplierIssueStatus(map);
	}
	
	public List selectExportTariffInfo(Map map) throws Exception {
		return mmA001Dao.selectExportTariffInfo(map);
	}
	
	public Map selectSystemConfig(Map map) throws Exception {
		return mmA001Dao.selectSystemConfig(map);
	}
	
	public Map selectSystemMenuInfo(Map map) throws Exception {
		return mmA001Dao.selectSystemMenuInfo(map);
	}
	
	public int updateDwMonthFcrStatus(Map map) throws Exception {
		return mmA001Dao.updateDwMonthFcrStatus(map);
	}
	
}
