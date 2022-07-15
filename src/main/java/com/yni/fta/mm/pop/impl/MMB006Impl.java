package com.yni.fta.mm.pop.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.stereotype.Service;

import com.yni.fta.common.tools.ExcelFileSupporter;
import com.yni.fta.common.tools.FileReaderSupporter;
import com.yni.fta.common.tools.StringUtil;
import com.yni.fta.mm.pop.MMB006;

import kr.yni.frame.Constants;
import kr.yni.frame.mapper.util.JcoMapValidator.DataHelper;
import kr.yni.frame.reader.type.ExcelSSReader;
import kr.yni.frame.service.YniAbstractService;
import kr.yni.frame.util.DataMapHelper;
import kr.yni.frame.util.FileUtil;
import kr.yni.frame.util.JsonUtil;
import kr.yni.frame.util.StringHelper;
import kr.yni.frame.web.upload.FormFile;

/**
 * 공통 > 인보이스/인보이스 항목 업로드 구현 클래스
 * 
 * @author YNI-maker
 *
 */
@Service("MMB006")
public class MMB006Impl extends YniAbstractService implements MMB006 {
	
	@Resource(name="MMB006Dao")
	private MMB006Dao MMB006Dao;
	
	/**
	 * 최근 설정된 값 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map selectMaxInvUpSetupNo(Map map) throws Exception {
		return MMB006Dao.selectMaxInvUpSetupNo(map);
	}
	
	/**
	 * 인보이스 업로드 설정정보 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map selectInvUpSetupInfo(Map map) throws Exception {
		return MMB006Dao.selectInvUpSetupInfo(map);
	}
	
	/**
	 * 인보이스 업로드 설정 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectInvUpSetupList(Map map) throws Exception {
		map.remove("FORM_SETUP_SN");
		
		return MMB006Dao.selectInvUpSetupList(map);
	}
	
	/**
	 * 인보이스 업로드 설정정보 등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public String insertInvUpSetupInfo(Map map) throws Exception {
		String nostr = MMB006Dao.insertInvUpSetupInfo(map);
		
		if(nostr == null || nostr.isEmpty()) {
			throw new Exception(this.getMessage("MSG_NOT_EXIST_DATA"));
		} else {
			map.put("FORM_SETUP_SN", nostr);
			
			this.insertInvUpMasterInfo(map);
		}
		
		return nostr;
	}
	
	/**
	 * 인보이스 업로드 설정정보 수정
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateInvUpSetupInfo(Map map) throws Exception {
		if(!StringHelper.null2void(map.get("FORM_SETUP_SN")).isEmpty()) {
			this.deleteInvUpMasterInfo(map);
		}
		
		this.insertInvUpMasterInfo(map);
		
		return MMB006Dao.updateInvUpSetupInfo(map);
	}
	
	/**
	 * 인보이스 업로드 설정정보 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteInvUpSetupInfo(Map map) throws Exception {
		this.deleteInvUpMasterInfo(map);
		
		return MMB006Dao.deleteInvUpSetupInfo(map);
	}
	
	/**
	 * 인보이스 항목 기본정보  조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectBaseInvUpList(Map map) throws Exception {
		return MMB006Dao.selectBaseInvUpList(map);
	}
	
	/**
	 * 인보이스 항목 업로드 설정정보 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectInvUpMasterInfo(Map map) throws Exception {
		return MMB006Dao.selectInvUpMasterInfo(map);
	}
	
	/**
	 * 인보이스 항목 업로드 설정정보 등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public int insertInvUpMasterInfo(Map map) throws Exception {
		List list = JsonUtil.getList(StringHelper.null2string(map.get("gridData"), "[]"));
		
		for(int i = 0; i < list.size(); i++) {
			Map pmap = (Map) list.get(i);
			
			pmap.put("COMPANY_CD", StringHelper.null2void(map.get("COMPANY_CD")));
			pmap.put("BCNC_CD", StringHelper.null2void(map.get("BCNC_CD")));
			pmap.put("FORM_SETUP_SN", StringHelper.null2void(map.get("FORM_SETUP_SN")));
			pmap.put("IMP_EXP_SE_CD", StringHelper.null2void(map.get("IMP_EXP_SE_CD")));
			pmap.put("DATA_SE", StringHelper.null2void(map.get("DATA_SE")));
			pmap.put("USER_ID", StringHelper.null2void(map.get("USER_ID")));
			pmap.put("QY_SSUM_AT", StringHelper.null2string(map.get("QY_SSUM_AT"), "N"));
			pmap.put("AMT_SSUM_AT", StringHelper.null2string(map.get("AMT_SSUM_AT"), "N"));
			pmap.put("NETWGHT_SSUM_AT", StringHelper.null2string(map.get("NETWGHT_SSUM_AT"), "N"));
			pmap.put("TOT_WT_SSUM_AT", StringHelper.null2string(map.get("TOT_WT_SSUM_AT"), "N"));
			pmap.put("PKNG_CO_SSUM_AT", StringHelper.null2string(map.get("PKNG_CO_SSUM_AT"), "N"));
			pmap.put("ADAMT_SSUM_AT", StringHelper.null2string(map.get("ADAMT_SSUM_AT"), "N"));
			pmap.put("DDC_AMT_SSUM_AT", StringHelper.null2string(map.get("DDC_AMT_SSUM_AT"), "N"));
		}
		
		return MMB006Dao.insertInvUpMasterInfo(list, map);
	}
	
	/**
	 * 인보이스 항목 업로드 설정정보 수정
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateInvUpMasterInfo(Map map) throws Exception {
		List list = JsonUtil.getList(StringHelper.null2string(map.get("gridData"), "[]"));
		
		for(int i = 0; i < list.size(); i++) {
			Map pmap = (Map) list.get(i);
			
			pmap.put("COMPANY_CD", StringHelper.null2void(map.get("COMPANY_CD")));
			pmap.put("BCNC_CD", StringHelper.null2void(map.get("BCNC_CD")));
			pmap.put("FORM_SETUP_SN", StringHelper.null2void(map.get("FORM_SETUP_SN")));
			pmap.put("IMP_EXP_SE_CD", StringHelper.null2void(map.get("IMP_EXP_SE_CD")));
			pmap.put("USER_ID", StringHelper.null2void(map.get("USER_ID")));
		}
		
		return MMB006Dao.updateInvUpMasterInfo(list, map);
	}
	
	/**
	 * 인보이스 항목 업로드 설정정보 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteInvUpMasterInfo(Map map) throws Exception {
		return MMB006Dao.deleteInvUpMasterInfo(map);
	}
	
	/**
	 * 인보이스/패킹리스트 업로드 이력 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectUploadHistList(Map map) throws Exception {
		return MMB006Dao.selectUploadHistList(map);
	}
	
	/**
	 * 인보이스 마스터 정보 업로드 이력 등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertInvUploadHist(Map map) throws Exception {
		MMB006Dao.deleteInvUploadHist(map);
		
		List list = JsonUtil.getList(StringHelper.null2string(map.get("gridData"), "[]"));
		String ilNo = StringHelper.null2void(map.get("CNR_NO"));
		
		for(int i = 0; i < list.size(); i++) {
			Map pmap = (Map) list.get(i);
			String cell_id = StringHelper.null2void(pmap.get("CELL_ID"));
			String cell_val = StringHelper.null2void(pmap.get("CELL_VAL"));
			
			if(cell_id.equals("INVE_NO")) {
				if(!cell_val.isEmpty()) {
					map.put(cell_id, cell_val);
				}
			} else {
				map.put(cell_id, cell_val);
			}
		}
		
		// IL번호(계약번호)가 있는 경우에는 거래처 셋업에 업데이트를 수행한다.
		if(!ilNo.isEmpty()) {
			map.put("CNR_NO", ilNo);
		}
		
		MMB006Dao.updateImportSetupFileNo(map); // SET-UP에 정보 업데이트
		
		return MMB006Dao.insertInvUploadHist(map);
	}
	
	/**
	 * 인보이스 마스터 정보 업로드 이력 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteInvUploadHist(Map map) throws Exception {
		return MMB006Dao.deleteInvUploadHist(map);
	}
	
	/**
	 * 패킹리스트 업로드 이력 등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertPlUploadHist(Map map) throws Exception {
		int cnt = 0;
		List list = JsonUtil.getList(StringHelper.null2string(map.get("gridData"), "[]"));
		String step = StringHelper.null2void(map.get("TAB_NM"));
		String cnrNo = "";
		
		if(list != null && list.size() > 0) {
			Map smap = MMB006Dao.selectPlHistMaxNo(map); // 기등록된 데이터에서 다음에 적용할 이력번호 조회
			int seq = StringHelper.null2zero(smap.get("PL_UPLO_SN"));
			String invno = StringHelper.null2void(map.get("INVE_NO"));
			
			for(int i = 0; i < list.size(); i++) {
				Map pmap = (Map) list.get(i);
				
				pmap.put("USER_ID", StringHelper.null2void(map.get("USER_ID")));
				pmap.put("IMP_BCNC_SETUP_SN", StringHelper.null2void(map.get("IMP_BCNC_SETUP_SN")));
				pmap.put("DOC_STLE_SE", StringHelper.null2void(map.get("DOC_STLE_SE")));
				pmap.put("INVE_NO", StringHelper.null2string(pmap.get("INVE_NO"), invno));
				pmap.put("COMPANY_CD", StringHelper.null2void(map.get("COMPANY_CD")));
				pmap.put("TAB_NM", step);
				
				String pcrn = StringHelper.null2void(pmap.get("CNR_NO"));
				
				if(!pcrn.isEmpty()) {
					cnrNo = pcrn;
				}
				
				seq++;
			}
		}
		
		cnt = MMB006Dao.insertPlUploadHist(list); // 패킹리스트 등록
		MMB006Dao.updateInvUploadHist(map); // 인보이스 마스터 정보 생성 및 업데이트(수량, 단위, 순중량, 총중량의 합을 업데이트함)
		
		if(!cnrNo.isEmpty()) {
			map.put("CNR_NO", cnrNo);
			
			MMB006Dao.updateImportSetupFileNo(map); // SET-UP에 정보 업데이트(계약번호)
		}
		
		return cnt;
	}
	
	/**
	 * 패킹리스트 업로드 이력 수정
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updatePlUploadHist(Map map) throws Exception {
		List list = JsonUtil.getList(StringHelper.null2string(map.get("gridData"), "[]"));
		
		return MMB006Dao.updatePlUploadHist(list, map); // 패킹리스트 수정
	}
	
	/**
	 * 패킹리스트 업로드 이력 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deletePlUploadHist(Map map) throws Exception {
		return MMB006Dao.deletePlUploadHist(map);
	}
	
	/**
	 * 패킹리스트 업로드 이력 일괄 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteBatchUploadHist(Map map) throws Exception {
		List list = JsonUtil.getList(StringHelper.null2string(map.get("gridData"), "[]"));
		
		return MMB006Dao.deleteBatchUploadHist(list, map);
	}
	
	/**
	 * excel 업로드한 파일내역 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List executeExcelUploadFile(Map map) throws Exception {
		List returnList = new LinkedList();
    	String lang = StringHelper.null2string(map.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE);
    	List<FormFile> formFileList = DataMapHelper.getFormFile(map);

    	if (formFileList == null || formFileList.size() <= 0) {
            throw new RuntimeException(this.getMessage("MSG_FILE_NOT_FOUND"));
        }

        FormFile formFile = formFileList.get(0);

        ExcelFileSupporter.checkFormFileForExcel(formFile, lang); // 엑셀 파일의 유효성 체크
        
        Map<String, String> saveFileMap = null; // 파일 정보를 담을 Map
        
        try {
            saveFileMap = FileUtil.transferTo(formFile); //  서버에 파일을 저장함(파일은 랜덤하게 지정됨)
        } catch (Exception e) {
            throw new RuntimeException(this.getMessage("MSG_FILE_NOT_FOUND"));
        }

        String fileName = StringHelper.null2void(formFile.getFileName());

        if (saveFileMap == null || saveFileMap.isEmpty() || fileName.equals("")) {
            throw new RuntimeException(this.getMessage("MSG_FILE_NOT_FOUND"));
        }
        
        String sheetName = StringHelper.null2void(map.get("SHEET_NAME"));
        int readIndex = 0;
        
        try {
            FileReaderSupporter reader = new FileReaderSupporter(new ExcelSSReader());
            List<Map<String, Object>> fileContents = null;
            
            // 시트목록을 구한다.
            List<Map<String, Object>> sheetList = reader.getSheet(null, fileName);
            
            if(sheetList == null || sheetList.size() == 0) {
            	throw new RuntimeException(this.getMessage("WRKSH,MSG_NOT_FOUND_01"));
            }
            
            String sheetRange = StringHelper.null2string(map.get("SHEET_NO"), "1");
            String[] sheets = sheetRange.split(",");
            
            // 요청한 시트 갯수만큼 loop실행
            for(int s = 0; s < sheets.length; s++) {
            	if(!DataHelper.isNumber(sheets[s])) {
            		throw new RuntimeException("CC_Enter numbers only in sheet numbers.");
            	}
            	
            	int sheetNo = Integer.parseInt(sheets[s]);
            	
            	if(sheetNo < 1) sheetNo = 0;
            	else sheetNo = sheetNo - 1;
            	
            	if(sheetNo > (sheetList.size()-1)) break; // 최대 시트갯수만큼만 수행
            	
	        	sheetName = StringHelper.null2void(sheetList.get(sheetNo).get("CODE"));
	            
	        	log.debug("Sheet name = " + sheetName + ", index = " + sheetNo);
	        	
	            fileContents = reader.getContents(fileName, readIndex, sheetName, true);
	            
	            if (fileContents == null || fileContents.size() <= 0) {
	                throw new Exception("file contents is null or size zero");
	            }
	            
	            for(int i = 0; i < fileContents.size(); i++) {
	        		Map tmpMap = (Map) fileContents.get(i);
	        		
	        		Map retMap = new HashMap();
	                String value = "";
	                String key = "";
	                
	                for (Iterator it = tmpMap.entrySet().iterator(); it.hasNext();) {
	                    Map.Entry entry = (Map.Entry) it.next();
	                    key = StringHelper.null2void(entry.getKey());
	                    value = StringHelper.null2void(tmpMap.get(key)).trim();
	                    
	                    retMap.put(StringUtil.getAlphabet(key), value);
	                }
	                
	                returnList.add(retMap);
	        	}
            }
        } catch (Exception e) {
            log.error(ExceptionUtils.getFullStackTrace(e));
            // 파일전송 중 오류가 발생했습니다.
            throw new RuntimeException(this.getMessage("MSG_FILE_TRANS_ERROR") + "(" + this.getMessage("MSG_NO_INSERT") + ")");
        } finally {
        	File uplodedFile = FileUtil.getFile(null, fileName);
        	
            if (uplodedFile.exists()) {
                uplodedFile.delete();
            }
        }
        
		return returnList;
	}
}
