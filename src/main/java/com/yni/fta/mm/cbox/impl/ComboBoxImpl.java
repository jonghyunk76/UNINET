package com.yni.fta.mm.cbox.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.fta.mm.cbox.ComboBox;

import kr.yni.frame.Constants;
import kr.yni.frame.service.YniAbstractService;
import kr.yni.frame.util.JsonUtil;
import kr.yni.frame.util.StringHelper;

/**
 * 콤보박스 데이터를 획득을 위한 기능 구현
 * 
 * @author YNI-maker
 *
 */
@Service("comboBox")
public class ComboBoxImpl extends YniAbstractService implements ComboBox {
	
	@Resource(name="comboBoxDao")
	private ComboBoxDao comboBoxDao;

	public List selectCompany(Map map) throws Exception {
		return comboBoxDao.selectCompany(map);
	}
	
	public List selectFTAMgmtSysCompany(Map map) throws Exception {
		return comboBoxDao.selectFTAMgmtSysCompany(map);
	}
	
	public List selectSupplierSysCompany(Map map) throws Exception {
		return comboBoxDao.selectSupplierSysCompany(map);
	}
	
	public List selectDivision(Map map) throws Exception {
		return comboBoxDao.selectDivision(map);
	}
	
	public List selectStandardCode(Map map) throws Exception {
		return comboBoxDao.selectStandardCode(map);
	}
	
	public List selectProductLine(Map map) throws Exception {
		return comboBoxDao.selectProductLine(map);
	}
	
	public List selectFta(Map map) throws Exception {
		return comboBoxDao.selectFta(map);
	}
	
	public List selectSignature(Map map) throws Exception {
		return comboBoxDao.selectSignature(map);
	}
	
	public List selectCustomerSalesType(Map map) throws Exception {
		return comboBoxDao.selectCustomerSalesType(map);
	}
	
	public List selectNaladisaVersion(Map map) throws Exception {
		return comboBoxDao.selectNaladisaVersion(map);
	}
	
	public List selectTraceType(Map map) throws Exception {
		return comboBoxDao.selectTraceType(map);
	}
	
	public List selectSystemType(Map map) throws Exception {
		return comboBoxDao.selectSystemType(map);
	}
	
	public List selectHighMenu(Map map) throws Exception {
		return comboBoxDao.selectHighMenu(map);
	}
	
	public List selectInvestigate(Map map) throws Exception {
		return comboBoxDao.selectInvestigate(map);
	}
	
	public List selectInvestigateCoNo(Map map) throws Exception {
		return comboBoxDao.selectInvestigateCoNo(map);
	}
	
	public List selectInvestigateProduct(Map map) throws Exception {
		return comboBoxDao.selectInvestigateProduct(map);
	}
	
	public List selectHubCertCustomer(Map map) throws Exception {
		return comboBoxDao.selectHubCertCustomer(map);
	}
	
	public List selectHubSignature(Map map) throws Exception {
        return comboBoxDao.selectHubSignature(map);
    }
	
	public List selectFtaNation(Map map) throws Exception {
		return comboBoxDao.selectFtaNation(map);
	}
	
	/**
	 * FTA 국가목록 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectFtaNationByCode(Map map) throws Exception {
		return comboBoxDao.selectFtaNationByCode(map);
	}
	
	public List selectReceiveCoItem(Map map) throws Exception {
		return comboBoxDao.selectReceiveCoItem(map);
	}
	
	public List selectKcsStandardCode(Map map) throws Exception {
		return comboBoxDao.selectKcsStandardCode(map);
	}
	
	public List selectForeignCustomer(Map map) throws Exception {
		return comboBoxDao.selectForeignCustomer(map);
	}
	
	public List selectForeignVendor(Map map) throws Exception {
		return comboBoxDao.selectForeignVendor(map);
	}
	
	public List selectGridColumnSetList(Map map) throws Exception {
		return comboBoxDao.selectGridColumnSetList(map);
	}
	
	public List selectDatagridInfo(Map map) throws Exception {
		return comboBoxDao.selectDatagridInfo(map);
	}
	
	public int deleteUserDatagridInfo(Map map) throws Exception {
		return comboBoxDao.deleteUserDatagridInfo(map);
	}
	
	@SuppressWarnings("unchecked")
	public int insertUserDatagridInfo(Map map) throws Exception {
		int cnt = 0;
		List gridData = new LinkedList();
		List gridData1 = JsonUtil.getList(StringHelper.null2void(map.get("gridData1")));
		List gridData2 = JsonUtil.getList(StringHelper.null2void(map.get("gridData2")));
    	
		if(gridData1.size() > 0 || gridData2.size() > 0) {
			// 기존 정보 삭제
			comboBoxDao.deleteUserDatagridInfo(map);
			
			for(int i = 0; i < gridData1.size(); i++) {
				Map gridMap = (Map) gridData1.get(i);
				
				gridMap.put("COMPANY_CD", StringHelper.null2void(map.get("COMPANY_CD")));
				gridMap.put("PID", StringHelper.null2void(map.get("PID")));
				gridMap.put("USER_ID", StringHelper.null2void(map.get("USER_ID")));
				gridMap.put("USER_SORT_NO", i+1);
				gridMap.put("USER_HIDDEN_YN", "false");
				gridMap.put("USER_SORT_TYPE", StringHelper.null2void(gridMap.get("USER_SORT_TYPE")));
				gridMap.put("USER_ATTRIBUTE1", StringHelper.null2void(gridMap.get("USER_ATTRIBUTE1")));
				gridMap.put("USER_ATTRIBUTE2", StringHelper.null2void(gridMap.get("USER_ATTRIBUTE2")));
				gridMap.put("USER_ATTRIBUTE3", StringHelper.null2void(gridMap.get("USER_ATTRIBUTE3")));
				gridMap.put("USER_ATTRIBUTE4", StringHelper.null2void(gridMap.get("USER_ATTRIBUTE4")));
				gridMap.put("USER_ATTRIBUTE5", StringHelper.null2void(gridMap.get("USER_ATTRIBUTE5")));
				
				gridData.add(gridMap);
			}
			
			for(int i = 0; i < gridData2.size(); i++) {
				Map gridMap = (Map) gridData2.get(i);
				
				gridMap.put("COMPANY_CD", StringHelper.null2void(map.get("COMPANY_CD")));
				gridMap.put("PID", StringHelper.null2void(map.get("PID")));
				gridMap.put("USER_ID", StringHelper.null2void(map.get("USER_ID")));
				gridMap.put("USER_SORT_NO", "0");
				gridMap.put("USER_HIDDEN_YN", "true");
				gridMap.put("USER_SORT_TYPE", "");
				gridMap.put("USER_ATTRIBUTE1", StringHelper.null2void(gridMap.get("USER_ATTRIBUTE1")));
				gridMap.put("USER_ATTRIBUTE2", StringHelper.null2void(gridMap.get("USER_ATTRIBUTE2")));
				gridMap.put("USER_ATTRIBUTE3", StringHelper.null2void(gridMap.get("USER_ATTRIBUTE3")));
				gridMap.put("USER_ATTRIBUTE4", StringHelper.null2void(gridMap.get("USER_ATTRIBUTE4")));
				gridMap.put("USER_ATTRIBUTE5", StringHelper.null2void(gridMap.get("USER_ATTRIBUTE5")));
				
				gridData.add(gridMap);
			}
			
			// 사용자 설정정보 등록
			comboBoxDao.insertUserDatagridInfo(gridData);
		}
		
		return cnt;
	}
	
	/**
	 * 그리드 목록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectDatagridTableList(Map map) throws Exception {
		return comboBoxDao.selectDatagridTableList(map);
	}
	
	/**
	 * 그리드 필드 목록(실제 데이터를 표시하는 컬럼만 조회)
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectDatagridColumnList(Map map) throws Exception {
		return comboBoxDao.selectDatagridColumnList(map);
	}
	
	/**
	 * 통관 차수 콤보 데이터
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectComboSttOdr(Map map) throws Exception {
		return comboBoxDao.selectComboSttOdr(map);
	}
	
	/**
	 * 사용자 조회
	 */
    public List selectFtaUserList(Map map) throws Exception {
        return comboBoxDao.selectFtaUserList(map);
    }	
    /**
     * 사이트 담당자 조회
     */
    public List selectSiteUserList(Map map) throws Exception {
        return comboBoxDao.selectSiteUserList(map);
    }	
}
