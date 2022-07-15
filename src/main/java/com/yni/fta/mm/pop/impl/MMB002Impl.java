package com.yni.fta.mm.pop.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service; 

import com.yni.fta.mm.pop.MMB002;
import com.yni.fta.mm.smp.impl.SMP1002Dao;

import kr.yni.frame.Constants;
import kr.yni.frame.service.YniAbstractService;
import kr.yni.frame.util.DataMapHelper;
import kr.yni.frame.util.JsonUtil;
import kr.yni.frame.util.StringHelper;
import kr.yni.frame.web.upload.FormFile;

/**
 * 공통 > 통관 공통팝업 구현 클래스
 * 
 * @author YNI-maker
 *
 */
@Service("MMB002")
public class MMB002Impl extends YniAbstractService implements MMB002 {
	
	@Resource(name="MMB002Dao")
	private MMB002Dao MMB002Dao;
	
	@Resource(name = "smp1002Dao")
	private SMP1002Dao smp1002Dao;	
	
	/**
	 * 거래처 리스트 조회
	 * 
	 * @param map 검색 조건
	 */
	public List selectClientList(Map map) throws Exception {
		return MMB002Dao.selectClientList(map);
	}
	
	/**
	 * 표준 품명 리스트 조회
	 * 
	 * @param map 검색 조건
	 */
	public List selectStardProductNameList(Map map) throws Exception {
		return MMB002Dao.selectStardProductNameList(map);
	}	
	
	/**
	 * 장치장 리스트 조회
	 * 
	 * @param map 검색 조건
	 */
	public List selectShedList(Map map) throws Exception {
		return MMB002Dao.selectShedList(map);
	}		
	
	/**
	 * 세관, 항구, 공항... 리스트
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectKcsCodeList(Map map) throws Exception {
		return MMB002Dao.selectKcsCodeList(map);
    }
	
	/**
	 * 과 리스트
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectKwaList(Map map) throws Exception {
		return MMB002Dao.selectKwaList(map);
    }	

	/**
	 * 세관 과 연계 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertCsmhKwaMapping(Map map) throws Exception {
		String userId = StringHelper.null2void(map.get("SESSION_USER_ID"));
		String impExpSeCd = StringHelper.null2void(map.get("IMP_EXP_SE_CD"));
		String csMhCd = StringHelper.null2void(map.get("CSMH_CD"));
		List list = JsonUtil.getList(StringHelper.null2void(map.get("gridData")));	// 그리드
		
		for(int i = 0; i < list.size(); i++) {
			Map pmap = (Map) list.get(i);
            pmap.put("IMP_EXP_SE_CD", impExpSeCd);
            pmap.put("CSMH_CD", csMhCd);
			pmap.put("CREATE_BY", userId);
		}
		
		return MMB002Dao.insertCsmhKwaMapping(list, map);
	}

	/**
	 * 세관 과 연계 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteCsmhKwaMapping(Map map) throws Exception {	
		List list = JsonUtil.getList(StringHelper.null2void(map.get("gridData")));		
		
		return MMB002Dao.deleteCsmhKwaMapping(list, map);
	}
	
	/**
	 * 세관 과 제외 리스트
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectCsmhKwaExcludeList(Map map) throws Exception {
		return MMB002Dao.selectCsmhKwaExcludeList(map);
    }
	
	/**
	 * 브랜드 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectCommonBrandList(Map map) throws Exception {
		return MMB002Dao.selectCommonBrandList(map);
	}
	
	/**
	 * 브랜드 일괄 등록/수정/삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateCommonBrandInfo(Map map) throws Exception {
		List list = JsonUtil.getList(StringHelper.null2string(map.get("gridData"), "[]"));
		List delList = new LinkedList(); // delete
		List merList = new LinkedList(); // insert or update
		int cnt = 0;
		
		for(int i = 0; i < list.size(); i++) {
			Map pmap = (Map) list.get(i);
			
			if(StringHelper.null2void(pmap.get("FIELD_STATE")).equals("D")) {
				delList.add(pmap);
			}
			if(StringHelper.null2void(pmap.get("FIELD_STATE")).equals("I") ||
					StringHelper.null2void(pmap.get("FIELD_STATE")).equals("U")) {
				pmap.put("COMPANY_CD", StringHelper.null2void(map.get("COMPANY_CD")));
				pmap.put("USER_ID", StringHelper.null2void(map.get("USER_ID")));
				
				merList.add(pmap);
			}
		}
		
		cnt += MMB002Dao.deleteCommonBrandInfo(delList);
		cnt += MMB002Dao.updateCommonBrandInfo(merList);
		
		return cnt;
	}
	
	/**
	 * 브랜드 일괄 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteCommonBrandInfo(Map map) throws Exception {
		List list = JsonUtil.getList(StringHelper.null2string(map.get("gridData"), "[]"));
		
		return MMB002Dao.deleteCommonBrandInfo(list);
	}
	
	/**
	 * 화물운송주선업자 리스트
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectFreightTransportAgencyList(Map map) throws Exception {
		return MMB002Dao.selectFreightTransportAgencyList(map);
    }	
	
	/**
	 * 관세사 리스트
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectCustomsBrokerAgentList(Map map) throws Exception {
		return MMB002Dao.selectCustomsBrokerAgentList(map);
    }	
	
	/**
	 * 해외거래처 리스트
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectOverBcncList(Map map) throws Exception {
		return MMB002Dao.selectOverBcncList(map);
    }	
	
	/**
	 * 통관 첨부파일 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectEntryFileList(Map map) throws Exception {
		return MMB002Dao.selectEntryFileList(map);
    }
	
	/**
	 * 통관 첨부파일 상세
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map selectEntryFileInfo(Map map) throws Exception {
		return MMB002Dao.selectEntryFileInfo(map);
	}	
	
	/**
	 * 통관 첨부파일 리스트 조회(첨부 전체 다운로드)
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectEntryFileAllList(Map map) throws Exception {
		return MMB002Dao.selectEntryFileAllList(map);
    }	

	/**
	 * 통관 첨부파일 마스터 등록/상세 등록,수정
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map mergeEntryFile(Map map) throws Exception {
		String userId = StringHelper.null2void(map.get("SESSION_USER_ID"));
		String companyCd = StringHelper.null2void(map.get("SESSION_COMPANY_CD"));
		String impDclrtSn = StringHelper.null2void(map.get("IMP_DCLRT_SN"));
		String entrAtchfMastrSn = StringHelper.null2void(map.get("ENTR_ATCHF_MASTR_SN"));
		String entrTy = StringHelper.null2void(map.get("ENTR_TY"));
		List gridData = JsonUtil.getList(StringHelper.null2void(map.get("gridData")));
		List list = JsonUtil.getList(StringHelper.null2void(map.get("gridData")));	// 그리드
		
		Map rstMap = new HashMap();
		List<FormFile> formFileList = DataMapHelper.getFormFile(map);
		
		List insertList = new ArrayList();	// Insert List
		List updateList = new ArrayList();	// Update List
		
		// 통관 첨부파일 마스터 일련번호가 없는 경우 등록
		if (entrAtchfMastrSn.equals("")) {
			entrAtchfMastrSn = MMB002Dao.insertEntryFileMaster(map);
			map.put("ENTR_ATCHF_MASTR_SN", entrAtchfMastrSn);
			// MMB002Dao.updateImportDclrtEntryFile(map);
		}
			
		for (int i = 0; i < gridData.size(); i++) {
			Map staMap = (Map) gridData.get(i);
			
			//String entrAtchfMastrSn1 = StringHelper.null2void(staMap.get("ENTR_ATCHF_MASTR_SN"));
			String entrAtchfDetailSn = StringHelper.null2void(staMap.get("ENTR_ATCHF_DETAIL_SN"));
			String atchfTy = StringHelper.null2void(staMap.get("ATCHF_TY"));
			String fileTy = StringHelper.null2void(staMap.get("FILE_TY"));
			
			
			for(int j = 0; j < formFileList.size(); j++) {
				FormFile file = formFileList.get(j);
				String field = file.getFieldName();
				
				if(field.equals(atchfTy)) {
					Map mergeMap = new HashMap();

					
					mergeMap.put("COMPANY_CD", companyCd);
					mergeMap.put("ENTR_ATCHF_MASTR_SN", entrAtchfMastrSn);
					mergeMap.put("ENTR_ATCHF_DETAIL_SN", entrAtchfDetailSn);
					mergeMap.put("FILE_TY", fileTy);
					mergeMap.put("FILE_NM", file.getOriginalFilename());
					mergeMap.put("ATCHF", file.getFileData());
					mergeMap.put("FILE_SIZE", file.getFileSize());
					mergeMap.put("FILE_TYP", file.getOriginalExtension());
					mergeMap.put("SESSION_USER_ID", userId);

					if (entrAtchfDetailSn.equals("")) {
						// Insert List
						insertList.add(mergeMap);
					} else {
						// Update List
						updateList.add(mergeMap);
					}
					
					map.remove(atchfTy);
				}				
			}			
		}

		if (insertList.size() > 0) {
			// Insert List
			MMB002Dao.insertEntryFileDetail(insertList);
		}
		
		if (updateList.size() > 0) {
			// Update List
			MMB002Dao.updateEntryFileDetail(updateList);
		}

		rstMap.put("ENTR_ATCHF_MASTR_SN", entrAtchfMastrSn);
		
		return rstMap;
	}	
	
	/**
	 * 통관 첨부파일 마스터 등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public String insertEntryFileMaster(Map map) throws Exception {
		return MMB002Dao.insertEntryFileMaster(map);
	}
	
	/**
	 * 통관 첨부파일 상세 등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception

	public int insertEntryFileDetail(Map map) throws Exception {
		String userId = StringHelper.null2void(map.get("SESSION_USER_ID"));
		String impExpSeCd = StringHelper.null2void(map.get("IMP_EXP_SE_CD"));
		String csMhCd = StringHelper.null2void(map.get("CSMH_CD"));
		List list = JsonUtil.getList(StringHelper.null2void(map.get("gridData")));	// 그리드
		
		for(int i = 0; i < list.size(); i++) {
			Map pmap = (Map) list.get(i);
            pmap.put("IMP_EXP_SE_CD", impExpSeCd);
            pmap.put("CSMH_CD", csMhCd);
			pmap.put("CREATE_BY", userId);
		}
		
		return MMB002Dao.insertEntryFileDetail(list, map);
	}	
	 */	
	/**
	 * 통관 첨부파일 상세 수정
	 * 
	 * @param map
	 * @return
	 * @throws Exception

	public int updateEntryFileDetail(Map map) throws Exception {
		String userId = StringHelper.null2void(map.get("SESSION_USER_ID"));
		String impExpSeCd = StringHelper.null2void(map.get("IMP_EXP_SE_CD"));
		String csMhCd = StringHelper.null2void(map.get("CSMH_CD"));
		List list = JsonUtil.getList(StringHelper.null2void(map.get("gridData")));	// 그리드
		
		for(int i = 0; i < list.size(); i++) {
			Map pmap = (Map) list.get(i);
            pmap.put("IMP_EXP_SE_CD", impExpSeCd);
            pmap.put("CSMH_CD", csMhCd);
			pmap.put("CREATE_BY", userId);
		}
		
		return MMB002Dao.updateEntryFileDetail(list);
	}	
	 */
	/**
	 * 통관 첨부파일 마스터 삭제 
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteEntryFileMaster(Map map) throws Exception {
        return MMB002Dao.deleteEntryFileMaster(map);
    }		
	
	/**
	 * 통관 첨부파일 상세 삭제(Info)
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteEntryFileDetailInfo(Map map) throws Exception {
        return MMB002Dao.deleteEntryFileDetailInfo(map);
    }	
	
	/**
	 * 통관 첨부파일 상세 삭제(List)
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteEntryFileDetailList(Map map) throws Exception {	
		List<?> list = JsonUtil.getList(StringHelper.null2void(map.get("gridData")));		
		
		return MMB002Dao.deleteEntryFileDetailList(list, map);
	}	
	
	/**
     * 표준품명 세번코드 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List<?> selectStardHscodeList(Map map) throws Exception {
    	List rstList = null;
    	String hscode = StringHelper.null2void(map.get("HS_CD"));
    	
    	rstList = MMB002Dao.selectStardHscodeList(map);
    	
    	// 10단위만 비교하도록 하위 소스는 주소처리(2021-02-05), 실제 표준품명은 10단위로 등록되는 데이터임
//    	// 9자리 비교
//    	if((rstList == null || rstList.size() == 0) && hscode.length() >= 9) {
//    		map.put("HS_CD", hscode.substring(0, 9));
//    		rstList = MMB002Dao.selectStardHscodeList(map);
//    	}
//    	
//    	// 8자리 비교
//    	if((rstList == null || rstList.size() == 0) && hscode.length() >= 8) {
//    		map.put("HS_CD", hscode.substring(0, 8));
//    		rstList = MMB002Dao.selectStardHscodeList(map);
//    	}
//    	
//    	// 7자리 비교
//    	if((rstList == null || rstList.size() == 0) && hscode.length() >= 7) {
//    		map.put("HS_CD", hscode.substring(0, 7));
//    		rstList = MMB002Dao.selectStardHscodeList(map);
//    	}
//    	
//    	// 6자리 비교
//    	if((rstList == null || rstList.size() == 0) && hscode.length() >= 6) {
//    		map.put("HS_CD", hscode.substring(0, 6));
//    		rstList = MMB002Dao.selectStardHscodeList(map);
//    	}
//    	
//    	// 5자리 비교
//    	if((rstList == null || rstList.size() == 0) && hscode.length() >= 5) {
//    		map.put("HS_CD", hscode.substring(0, 5));
//    		rstList = MMB002Dao.selectStardHscodeList(map);
//    	}
//    	
//    	// 4자리 비교
//    	if((rstList == null || rstList.size() == 0) && hscode.length() >= 4) {
//    		map.put("HS_CD", hscode.substring(0, 4));
//    		rstList = MMB002Dao.selectStardHscodeList(map);
//    	}
    	
        return rstList;
    }
    
    /**
     * 표준품명코드 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List<?> selectStardProductcodeList(Map map) throws Exception {
    	return MMB002Dao.selectStardProductcodeList(map);
    }
    
    /**
     * 해외 원산지 발행기관 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List<?> selectOverseaIssueOrganList(Map map) throws Exception {
    	return MMB002Dao.selectOverseaIssueOrganList(map);
    }
    
	/**
	 * 대행사 리스트 조회
	 * 
	 * @param map 검색 조건
	 */
	public List selectAgencyList(Map map) throws Exception {
		return MMB002Dao.selectAgencyList(map);
	}   
	
	/**
	 * 운수기관 리스트 조회
	 * 
	 * @param map 검색 조건
	 */
	public List selectTratInsttList(Map map) throws Exception {
		return MMB002Dao.selectTratInsttList(map);
	}
	
	/**
	 * 우편번호별관할세관 리스트 조회
	 * 
	 * @param map 검색 조건
	 */
	public List selectZipCmpcCsmhList(Map map) throws Exception {
		return MMB002Dao.selectZipCmpcCsmhList(map);
	}
	
	/**
	 * 법령부호별 서류명 리스트 조회
	 * 
	 * @param map 검색 조건
	 */
	public List selectLwodPapeKndList(Map map) throws Exception {
		return MMB002Dao.selectLwodPapeKndList(map);
	}
	
	/**
	 * 기본값일괄수정 수입 리스트 조회
	 * 
	 * @param map 검색 조건
	 */
	public List selectImpBcncSetupList(Map map) throws Exception {
		return MMB002Dao.selectImpBcncSetupList(map);
	}	
	
	/**
	 * 기본값일괄수정 수출 리스트 조회
	 * 
	 * @param map 검색 조건
	 */
	public List selectExpBcncSetupList(Map map) throws Exception {
		return MMB002Dao.selectExpBcncSetupList(map);
	}	
    
	/**
	 * updateBcncSetupBatchModify
	 * 기본값일괄 수정
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateBcncSetupBatchModify(Map map) throws Exception {
		List gridList = JsonUtil.getList(StringHelper.null2void(map.get("gridData")));
		String userId = StringHelper.null2void(map.get("USER_ID"));
		String tableName = StringHelper.null2void(map.get("TABLE_NAME"));
		String modBatchItem = StringHelper.null2void(map.get("MOD_BATCH_ITEM"));
		String modBatchValue = StringHelper.null2void(map.get("MOD_BATCH_VALUE"));
		String categoryCd = "CC_M_SETUP_BATCH_MODIFY_ITEM";
    	String lang = StringHelper.null2string(map.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE);
    	List dataList = new ArrayList();
    	int cnt = 0;
    	
        if (gridList == null || gridList.size() <= 0) {
            throw new RuntimeException(this.getMessage("MSG_NOT_EXIST_DATA", null, lang));
        } else {
        	
    		// Get Function
    		Map codeMap = new HashMap();
    		map.put("CATEGORY_CD", categoryCd);
    		map.put("CODE", modBatchItem);
    		codeMap = smp1002Dao.selectSysCodeDetail(map);
    		String colType = StringHelper.null2string((String) codeMap.get("TXT_VALUE2"),  "CHAR");
    		
        	for(int i = 0; i < gridList.size(); i++) {
        		Map gridMap = (Map) gridList.get(i);
        		
        		gridMap.put("TABLE_NAME", tableName);
        		gridMap.put("COL_TYPE", colType);
        		gridMap.put("MOD_BATCH_ITEM", modBatchItem);
        		gridMap.put("MOD_BATCH_VALUE", modBatchValue);
        		gridMap.put("USER_ID", userId);
        		
        		dataList.add(gridMap);
        		
        		// cnt = MMB002Dao.updateBcncSetupBatchModify(gridMap);
        	}
        	
        	cnt = MMB002Dao.updateBcncSetupBatchModify(dataList);
        }
        
		return cnt;
	}
		
}
