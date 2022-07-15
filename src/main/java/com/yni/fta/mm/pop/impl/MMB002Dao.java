package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.yni.frame.dao.YniAbstractDAO;

/**
 * 공통 > 통관 공통팝업 DAO 클래스
 * 
 * @author YNI-maker
 *
 */
@Repository("MMB002Dao")
public class MMB002Dao extends YniAbstractDAO {

	/**
	 * 거래처 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectClientList(Map map) throws Exception {
		return this.listWithRowPaging("MMB002.selectClientList", map);
	}
	
	/**
	 * 표준 품명 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectStardProductNameList(Map map) throws Exception {
		return this.listWithRowPaging("MMB002.selectStardProductNameList", map);
	}
	
	/**
	 * 장치장 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectShedList(Map map) throws Exception {
		return this.listWithRowPaging("MMB002.selectShedList", map);
	}		
	
    /**
     * 세관, 항구, 공항... 리스트
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List<?> selectKcsCodeList(Map map) throws Exception {
        return this.listWithRowPaging("MMB002.selectKcsCodeList", map);
    }
    
    /**
     * 세관 과 리스트
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List<?> selectKwaList(Map map) throws Exception {
        return this.listWithRowPaging("MMB002.selectKwaList", map);
    }    
    
	/**
	 * 세관 과 연계 등록
	 * 
	 * @param list
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertCsmhKwaMapping(List list, Map map) throws Exception {
		return this.executeBatch("MMB002.insertCsmhKwaMapping", list, map);
	}

	/**
	 * 세관 과 연계 삭제
	 * 
	 * @param list
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteCsmhKwaMapping(List list, Map map) throws Exception {
		return this.executeBatch("MMB002.deleteCsmhKwaMapping", list, map);
	}    
    
    /**
     * 세관 과 제외 리스트
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List<?> selectCsmhKwaExcludeList(Map map) throws Exception {
        return this.listWithRowPaging("MMB002.selectCsmhKwaExcludeList", map);
    }	
    
	/**
	 * 브랜드 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectCommonBrandList(Map map) throws Exception {
		return this.list("MMCCPOP.selectCommonBrandList", map);
	}
	
	/**
	 * 브랜드 일괄 등록/수정/삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteCommonBrandInfo(List list) throws Exception {
		return this.executeBatch("MMCCPOP.deleteCommonBrandInfo", list);
	}
	
	/**
	 * 브랜드 일괄 등록/수정/삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateCommonBrandInfo(List list) throws Exception {
		return this.executeBatch("MMCCPOP.updateCommonBrandInfo", list);
	}

    /**
     * 화물운송주선업자 리스트
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List<?> selectFreightTransportAgencyList(Map map) throws Exception {
        return this.listWithRowPaging("MMB002.selectFreightTransportAgencyList", map);
    } 
    
    /**
     * 관세사 리스트
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List<?> selectCustomsBrokerAgentList(Map map) throws Exception {
        return this.listWithRowPaging("MMB002.selectCustomsBrokerAgentList", map);
    }
    
    /**
     * 해외거래처 리스트
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List<?> selectOverBcncList(Map map) throws Exception {
        return this.listWithRowPaging("MMB002.selectOverBcncList", map);
    }      
    
    /**
     * 통관 첨부파일 리스트 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List<?> selectEntryFileList(Map map) throws Exception {
        return this.listWithRowPaging("MMB002.selectEntryFileList", map);
    } 
    
    /**
     * 통관 첨부파일 상세
     * @param map
     * @return
     * @throws Exception
     */
    public Map selectEntryFileInfo(Map map) throws Exception {
        return (Map) selectByPk("MMB002.selectEntryFileInfo", map);
    }
    
    /**
     * 통관 첨부파일 리스트 조회(첨부 전체 다운로드)
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List<?> selectEntryFileAllList(Map map) throws Exception {
        return this.listWithRowPaging("MMB002.selectEntryFileInfo", map);
    }     
   
	/**
	 * 수입신고서 통관  첨부파일 마스터 업데이트
	 * @param map
	 * @return
	 * @throws Exception
	 */
    public int updateImportDclrtEntryFile(Map map) throws Exception {
        return this.delete("MMB002.updateImportDclrtEntryFile", map);
    }
    
    /**
     * 통관 첨부파일 마스터 등록
     * @param map
     * @return
     * @throws Exception
     */
	public String insertEntryFileMaster(Map map) throws Exception {
		return (String) this.insert("MMB002.insertEntryFileMaster", map);
	}	
	
	/**
	 * 통관 첨부파일 상세 등록
	 * 
	 * @param list
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertEntryFileDetail(List list) throws Exception {
		return this.executeBatch("MMB002.insertEntryFileDetail", list);
	}	
	
	/**
	 * 통관 첨부파일 상세 수정
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateEntryFileDetail(List list) throws Exception {
		return this.executeBatch("MMB002.updateEntryFileDetail", list);
	}	
   
	/**
	 * 통관 첨부파일 마스터 삭제
	 * @param map
	 * @return
	 * @throws Exception
	 */
    public int deleteEntryFileMaster(Map map) throws Exception {
        return this.delete("MMB002.deleteEntryFileMaster", map);
    }
    
	/**
	 * 통관 첨부파일 상세 삭제(Info)
	 * @param map
	 * @return
	 * @throws Exception
	 */
    public int deleteEntryFileDetailInfo(Map map) throws Exception {
        return this.delete("MMB002.deleteEntryFileDetail", map);
    }
    
	/**
	 * 통관 첨부파일 상세 삭제(리스트)
	 * 
	 * @param list
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteEntryFileDetailList(List list, Map map) throws Exception {
		return this.executeBatch("MMB002.deleteEntryFileDetail", list, map);
	}      
	
	/**
     * 표준품명 세번코드 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List<?> selectStardHscodeList(Map map) throws Exception {
        return this.list("MMB002.selectStardHscodeList", map);
    }
    
    /**
     * 표준품명코드 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List<?> selectStardProductcodeList(Map map) throws Exception {
        return this.list("MMB002.selectStardProductcodeList", map);
    }
    
    /**
     * 해외 원산지 발행기관 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List<?> selectOverseaIssueOrganList(Map map) throws Exception {
        return this.list("MMB002.selectOverseaIssueOrganList", map);
    }
    
	/**
	 * 대행사 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectAgencyList(Map map) throws Exception {
		return this.listWithRowPaging("MMB002.selectAgencyList", map);
	}  
	
	/**
	 * 운수기관 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectTratInsttList(Map map) throws Exception {
		return this.listWithRowPaging("MMB002.selectTratInsttList", map);
	}
	
	/**
	 * 우편번호별관할세관 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectZipCmpcCsmhList(Map map) throws Exception {
		return this.listWithRowPaging("MMB002.selectZipCmpcCsmhList", map);
	}	
    
	/**
	 * 법령부호별 서류명 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectLwodPapeKndList(Map map) throws Exception {
		return this.list("MMB002.selectLwodPapeKndList", map);
	}
	
	/**
	 * 기본값일괄수정 수입 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectImpBcncSetupList(Map map) throws Exception {
		return this.list("BI3002.selectImpBcncSetupList", map);
	}	
	
	/**
	 * 기본값일괄수정 수출 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectExpBcncSetupList(Map map) throws Exception {
		return this.list("BI3002.selectExpBcncSetupList", map);
	}
	
	/**
	 * updateBcncSetupBatchModify
	 * 기본값일괄 수정
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateBcncSetupBatchModify(Map map) throws Exception {
		return this.update("BI3002.updateBcncSetupBatchModify", map);
	}
	
	/**
	 * updateBcncSetupBatchModify
	 * 기본값일괄 수정
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateBcncSetupBatchModify(List list) throws Exception {
		 return this.executeBatch("BI3002.updateBcncSetupBatchModify", list);
	}	 	
    
}
