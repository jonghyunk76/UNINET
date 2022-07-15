package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.yni.frame.dao.YniAbstractDAO;
import kr.yni.frame.util.StringHelper;

/**
 * FTA Rule > FTA협정등록
 * 
 * @author YNI-maker
 *
 */
@Repository("mmA001Dao")
public class MMA001Dao extends YniAbstractDAO {
	
	public Map selectClosingInfo(Map map) throws Exception {
		return (Map)this.selectByPk("MMA001.selectClosingInfo", map);
	}
	
	public Map selectDataInterfaceCount(Map map) throws Exception {
		return (Map)this.selectByPk("MMA001.selectDataInterfaceCount", map);
	}
	
	public List selectVendorCoCount(Map map) throws Exception {
		map.put("COVER_DATE_FROM", StringHelper.null2void(map.get("START_YYYYMM")).substring(0, 4)+"0101");
		map.put("COVER_DATE_TO", StringHelper.null2void(map.get("YYYYMM")).substring(0, 4)+"1231");
		
		List ftaList = this.list("DI3010.selectReceiveCoFtaList", map);
		
		map.put("CO_FTA_LIST", ftaList);
		
		return this.list("MMA001.selectVendorCoCount", map);
	}
	
	public Map selectInOutCount(Map map) throws Exception {
		return (Map)this.selectByPk("MMA001.selectInOutCount", map);
	}
	
	public List selectDeterminateCount(Map map) throws Exception {
		return this.list("MMA001.selectDeterminateCount", map);
	}
	
	public List selectMonthlyDeterminateCount(Map map) throws Exception {
		return this.list("MMA001.selectMonthlyDeterminateCount", map);
	}
	
	public Map selectIssueReportCount(Map map) throws Exception {
		return (Map)this.selectByPk("MMA001.selectIssueReportCount", map);
	}
	
	public int updateWorkDate(Map map) throws Exception {
		return this.update("MMA001.updateWorkDate", map);
	}
	
	public Map selectSupplierIssueStatus(Map map) throws Exception {
		return (Map)this.selectByPk("MMA001.selectSupplierIssueStatus", map);
	}
	
	public List selectExportTariffInfo(Map map) throws Exception {
		return this.listWithRowPaging("MMA001.selectExportTariffInfo", map);
	}
	
	public Map selectSystemConfig(Map map) throws Exception {
		return (Map)this.selectByPk("MMA001.selectSystemConfig", map);
	}
	
	public Map selectSystemMenuInfo(Map map) throws Exception {
		return (Map)this.selectByPk("MMA001.selectSystemMenuInfo", map);
	}
	
	public int updateDwMonthFcrStatus(Map map) throws Exception {
		this.update("MMA001.deleteDwMonthFcrStatus", map);
		
		return this.update("MMA001.updateDwMonthFcrStatus", map);
	}
	
}
