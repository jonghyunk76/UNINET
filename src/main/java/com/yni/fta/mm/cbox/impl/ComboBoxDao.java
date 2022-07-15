package com.yni.fta.mm.cbox.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.yni.frame.dao.YniAbstractDAO;

/**
 * 콤보박스 데이터를 획득을 위한 데이터 관리
 * 
 * @author YNI-maker
 *
 */
@Repository("comboBoxDao")
public class ComboBoxDao extends YniAbstractDAO {

	/**
	 * 회사정보 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectCompany(Map map) throws Exception {
		return this.list("MMCOMBO.selectCompany", map);
	}
	
	/**
	 * FTA 관리 사이트 회사정보 조회(클라우드용)
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectFTAMgmtSysCompany(Map map) throws Exception {
		return this.list("MMCOMBO.selectFTAMgmtSysCompany", map);
	}
	
	/**
	 * 협력사 사이트 회사정보 조회(클라우드용)
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectSupplierSysCompany(Map map) throws Exception {
		return this.list("MMCOMBO.selectSupplierSysCompany", map);
	}
	
	/**
	 * 사업부 정보 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectDivision(Map map) throws Exception {
		return this.list("MMCOMBO.selectDivision", map);
	}
	
	/**
	 * 표준코드 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectStandardCode(Map map) throws Exception {
		return this.list("MMCOMBO.selectStandardCode", map);
	}
	
	/**
	 * 생산라인(제품군) 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectProductLine(Map map) throws Exception {
		return this.list("MMCOMBO.selectProductLine", map);
	}
	
	/**
	 * FTA 협정 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectFta(Map map) throws Exception {
		return this.list("MMCOMBO.selectFta", map);
	}
	
	/**
	 * 서명권자 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectSignature(Map map) throws Exception {
		return this.list("MMCOMBO.selectSignature", map);
	}
	
	/**
	 * 고객판매유형 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectCustomerSalesType(Map map) throws Exception {
		return this.list("MMCOMBO.selectCustomerSalesType", map);
	}
	
	/**
	 * NALADISA 버전 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectNaladisaVersion(Map map) throws Exception {
		return this.list("MMCOMBO.selectNaladisaVersion", map);
	}
	
	/**
	 * TRACE 유형 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectTraceType(Map map) throws Exception {
		return this.list("MMCOMBO.selectTraceType", map);
	}
	
	/**
	 * SYSTEM 유형 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectSystemType(Map map) throws Exception {
		return this.list("MMCOMBO.selectSystemType", map);
	}
	
	/**
	 * 상위 메뉴 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectHighMenu(Map map) throws Exception {
		return this.list("MMCOMBO.selectHighMenu", map);
	}
	
	/**
	 * 실사 정보 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectInvestigate(Map map) throws Exception {
		return this.list("MMCOMBO.selectInvestigate", map);
	}
	
	/**
	 * 실사대상에 포함된 원산지 확인서 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectInvestigateCoNo(Map map) throws Exception {
		return this.list("MMCOMBO.selectInvestigateCoNo", map);
	}
	
	/**
	 * 실사대상에 포함된 제품 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectInvestigateProduct(Map map) throws Exception {
		return this.list("MMCOMBO.selectInvestigateProduct", map);
	}
	
	/**
	 * 현대차 FTA HUB 사용 고객사 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectHubCertCustomer(Map map) throws Exception {
		return this.list("MMCOMBO.selectHubCertCustomer", map);
	}
	
	/**
     * 현대차 FTA HUB 서명권자 명 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List selectHubSignature(Map map) throws Exception {
        return this.list("MMCOMBO.selectHubSignature", map);
    }
    
    /**
	 * FTA Nation 협정 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectFtaNation(Map map) throws Exception {
		return this.list("MMCOMBO.selectFtaNation", map);
	}
	
	/**
	 * FTA 국가목록 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectFtaNationByCode(Map map) throws Exception {
		return this.list("MMCOMBO.selectFtaNationByCode", map);
	}
	
	/**
	 * 원산지 확인서 등록 품목 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectReceiveCoItem(Map map) throws Exception {
		return this.list("MMCOMBO.selectReceiveCoItem", map);
	}
	
	/**
	 * 기관발급 표준코드 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectKcsStandardCode(Map map) throws Exception {
		return this.list("MMCOMBO.selectKcsStandardCode", map);
	}
	
	/**
	 * 해외법인 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectForeignCustomer(Map map) throws Exception {
		return this.list("MMCOMBO.selectForeignCustomer", map);
	}
	
	/**
	 * 해외법인 협력사 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectForeignVendor(Map map) throws Exception {
		return this.list("MMCOMBO.selectForeignVendor", map);
	}
	
	/**
	 * 그리드 설정정보 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectGridColumnSetList(Map map) throws Exception {
		return this.list("GRID.selectGridColumnSetList", map);
	}
	
	/**
	 * 그리드 설정정보 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectDatagridInfo(Map map) throws Exception {
		return this.list("GRID.selectDatagridInfo", map);
	}
	
	/**
	 * 사용자 그리드 설정정보 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteUserDatagridInfo(Map map) throws Exception {
		return this.delete("GRID.deleteUserDatagridInfo", map);
	}
	
	/**
	 * 사용자 그리드 설정정보 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertUserDatagridInfo(List list) throws Exception {
		return this.executeBatch("GRID.insertUserDatagridInfo", list);
	}
	
	/**
	 * 그리드 목록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectDatagridTableList(Map map) throws Exception {
		return this.list("GRID.selectDatagridTableList", map);
	}
	
	/**
	 * 그리드 필드 목록(실제 데이터를 표시하는 컬럼만 조회)
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectDatagridColumnList(Map map) throws Exception {
		return this.list("GRID.selectDatagridColumnList", map);
	}
	
	/**
	 * 통관 차수 콤보 데이터
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectComboSttOdr(Map map) throws Exception {
		return this.list("MMCCPOP.selectComboSttOdr", map);
	}
	
    /**
     * 사용자 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List selectFtaUserList(Map map) throws Exception {
        return this.list("MMCOMBO.selectFtaUserList", map);
    }
    
    /**
     * 사이트 담당자 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List selectSiteUserList(Map map) throws Exception {
        return this.list("MMCOMBO.selectSiteUserList", map);
    }	
	
}
