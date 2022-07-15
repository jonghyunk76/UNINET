package com.yni.fta.fm.sm.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.yni.frame.dao.YniAbstractDAO;
import kr.yni.frame.util.StringHelper;

/**
 * 시스템관리 > 회사정보설정   
 * 
 * @author carlos
 *
 */
@Repository("sm7001Dao")
public class SM7001Dao extends YniAbstractDAO {
	
    /**
     * 회사정보 리스트 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List selectCompanyList(Map map) throws Exception {
        return this.listWithRowPaging("SM7001.selectCompanyList", map);
    }
    
    /**
     * 회사정보 정보 중복건수 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map selectCompanyDupCheck(Map map) throws Exception {
        return (Map) this.selectByPk("SM7001.selectCompanyDupCheck", map);
    }
    
    /**
     * 회사 CI 및 직인 파일 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map selectStempCIImg(Map map) throws Exception {
        return (Map) this.selectByPk("SM7001.selectStempCIImg", map);
    }
    
    /**
     * 회사정보 정보 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map selectCompanyInfo(Map map) throws Exception {
        return (Map) this.selectByPk("SM7001.selectCompanyInfo", map);
    }
    
    /**
     * 회사정보 신규등록
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int insertCompanyInfo(Map map) throws Exception {
        return this.update("SM7001.insertCompanyInfo", map);
    }

    /**
     * 회사정보 수정
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int updateCompanyInfo(Map map) throws Exception {
        return this.update("SM7001.updateCompanyInfo", map);
    }
    
    /**
     * 회사정보 삭제
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int deleteCompanyInfo(Map map) throws Exception {
        return this.delete("SM7001.deleteCompanyInfo", map);
    }
    
    /**
     * 회사정보 그룹정보 수정
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int updateCompnayGroupCd(Map map) throws Exception {
        return this.update("SM7001.updateCompnayGroupCd", map);
    }
    
    /**
     * 회사정보 그룹 리스트 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List selectCompnayGroupList(Map map) throws Exception {
        return this.listWithRowPaging("SM7001.selectCompnayGroupList", map);
    }
    
    /**
     * C/O 발급 고객사 저장
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int updateHubCertCustomerInfo(Map map) throws Exception {
        return this.update("SM7001.updateHubCertCustomerInfo", map);
    }
    /**
     * C/O 발급 고객사 삭제
     * 
     * @param map
     * @return
     * @throws Exception
     */
	public int deleteHubCertCustomerInfo(Map map) throws Exception {
		return this.delete("SM7001.deleteHubCertCustomerInfo", map);
	}
	/**
     * 거래 고객사 저장
     * 
     * @param map
     * @return
     * @throws Exception
     */
	public int updateHubSalesCustomerInfo(Map map) throws Exception {
		return this.update("SM7001.updateHubSalesCustomerInfo", map);
	}

	/**
     * 거래 고객사 삭제
     * 
     * @param map
     * @return
     * @throws Exception
     */	
	public int deleteHubSalesCustomerInfo(Map map) throws Exception {
		return this.delete("SM7001.deleteHubSalesCustomerInfo", map);
	}

	/**
     * 서명권자 관리
     * 
     * @param map
     * @return
     * @throws Exception
     */	
	public int updateHubSignatureInfo(Map map) throws Exception {
		if(StringHelper.null2void(map.get("SIGNATURE_SEQ")).isEmpty()) {
			Map seq = (Map) this.selectByPk("SM7001.selectSignatureNumber", map);
			
			map.put("SIGNATURE_SEQ", StringHelper.null2void(seq.get("SIGNATURE_SEQ")));
		}
		
		return this.update("SM7001.updateHubSignatureInfo", map);
	}

	/**
     * 서명권자 삭제
     * 
     * @param map
     * @return
     * @throws Exception
     */	
	public int deleteHubSignatureInfo(Map map) throws Exception {
		return this.delete("SM7001.deleteHubSignatureInfo", map);
	}

    /**
     * FTA 시스템 옵션 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
	public Map selectSysconfigOptionInfo(Map map) throws Exception {
		return (Map) this.selectByPk("SM7001.selectSysconfigOptionInfo", map);
	}

	/**
     * FTA 시스템 옵션 수정
     * 
     * @param map
     * @return
     * @throws Exception
     */
	public int updateSysconfigOptionInfo(Map map) throws Exception {
         return this.update("SM7001.updateSysconfigOptionInfo", map);
	}
	
	/**
     * 인증 ID 수정
     * 
     * @param map
     * @return
     * @throws Exception
     */
	public int updateCompanyCertIDInfo(Map map) throws Exception {
        return this.update("SM7001.updateCompanyCertIDInfo", map);
	}

	/**
     * C/O 발급 고객사 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List selectHubCertCustomerList(Map map) throws Exception {
        return this.list("SM7001.selectHubCertCustomerList", map);
    }
    
    /**
	 * HUB 매출고객사 리스트 조회
	 * 
	 * @param map
     * @return
     * @throws Exception
	 */
	public List selectHubSalesCustomerList(Map map) throws Exception {
		return this.list("SM7001.selectHubSalesCustomerList", map);
	}
	
	/**
	 * HUB 서명권자 리스트 조회
	 * 
	 * @param map
     * @return
     * @throws Exception
	 */
	public List selectHubSignatureList(Map map) throws Exception {
		return this.list("SM7001.selectHubSignatureList", map);
	}
	
	/**
     * 인증 ID 중복 체크
     * 
     * @param map
     * @return
     * @throws Exception
     */
	public Map selectHubCertID(Map map) throws Exception{
		return (Map) this.selectByPk("SM7001.selectHubCertID", map);
	}
    
}
