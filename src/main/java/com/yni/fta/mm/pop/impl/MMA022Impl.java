package com.yni.fta.mm.pop.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.fta.common.tools.FileReaderSupporter;
import com.yni.fta.mm.pop.MMA022;

import kr.yni.frame.Constants;
import kr.yni.frame.reader.type.ExcelSSReader;
import kr.yni.frame.service.YniAbstractService;
import kr.yni.frame.util.DataMapHelper;
import kr.yni.frame.util.FileUtil;
import kr.yni.frame.util.StringHelper;
import kr.yni.frame.web.upload.FormFile;

/**
 * 공통 > 한국 확인서 form 조회 및 업로드 실행
 * 
 * @author carlos
 */
@Service("mmA022")
public class MMA022Impl extends YniAbstractService implements MMA022 {

    @Resource(name = "mmA022Dao")
    private MMA022Dao mmA022Dao;

    /**
     * Excel Upload 결과 조회
     * 
     * @param map Map
     * @return List
     * @throws Exception
     */
    public List selectExcelSampleList(Map map) throws Exception {
        return mmA022Dao.selectExcelSampleList(map);
    }

    /**
     * Excel Upload & Save
     * 
     * @param map Map
     * @return int
     * @throws Exception
     */
    public int saveExcelSample(Map map) throws Exception {
        List<FormFile> formFileList = DataMapHelper.getFormFile(map);
        if(formFileList == null || formFileList.size() <= 0) {
            throw new RuntimeException(this.getMessage("MSG_FILE_NOT_FOUND")); /*파일이 존재하지 않습니다.*/
        }

        FormFile formFile = formFileList.get(0);
        
        // 첨부파일체크
        this.checkFormFile(formFile);

        if(formFile == null) {
            throw new RuntimeException(this.getMessage("MSG_FILE_NOT_FOUND")); /*파일이 존재하지 않습니다.*/
        }

        Map<String, String> saveFileMap = null;
        try {
            saveFileMap = FileUtil.transferTo(formFile); 
        } catch(Exception e) {
            throw new RuntimeException(this.getMessage("MSG_FILE_NOT_FOUND")); /*파일이 존재하지 않습니다.*/
        }
        
        if(log.isDebugEnabled()) log.debug("Origin file info = " + formFile.getOriginalFilename() +
        		"Origin file info = " + formFile.getFileName());
        
        String fileName = StringHelper.null2void(formFile.getFileName());
        
        if (saveFileMap == null || saveFileMap.isEmpty() || fileName.equals("")) {
            throw new RuntimeException(this.getMessage("MSG_FILE_NOT_FOUND")); /*파일이 존재하지 않습니다.*/
        }

        FileReaderSupporter reader = new FileReaderSupporter(new ExcelSSReader());

        List fileContents = null;
        String sheetName = null;
        
        try {
        	// 시트목록을 구한다.
            List<Map<String, Object>> sheetList = reader.getSheet(null, fileName);
            
            if(sheetList == null || sheetList.size() == 0) {
            	throw new RuntimeException(this.getMessage("WRKSH,MSG_NOT_FOUND_01"));
            } else {
            	sheetName = StringHelper.null2void(sheetList.get(0).get("CODE"));
            }
            
            fileContents = reader.getContents(fileName, 0, sheetName);
        } catch(Exception e) {
            throw new RuntimeException(this.getMessage("MSG_FILE_NOT_FOUND")); /*파일이 존재하지 않습니다.*/
        }

        if (fileContents == null || fileContents.size() <= 0) {
            // 파일전송 중 오류가 발생했습니다.
            throw new RuntimeException(this.getMessage("MSG_FILE_TRANS_ERROR") + "(" + this.getMessage("MSG_NO_INSERT") + ")");
        }

        List<Map<String, Object>> insertList = new ArrayList<Map<String, Object>>();
        Map<String, Object> insertData = null;
        Map<String, Object> fileContent = null;

        String companyCd = StringHelper.null2void(map.get("COMPANY_CD")); //회사코드
        String vendorCd  = StringHelper.null2void(map.get("VENDOR_CD"));  //협력사코드
        String userId    = StringHelper.null2void(map.get("USER_ID"));    //사용자ID
        
        int keyIdx = 0;
        int nFileContentsCnt = fileContents.size();

        for(int ii = 1; ii < nFileContentsCnt; ii++) {

            keyIdx = ii + 1;

            fileContent = (LinkedHashMap<String, Object>) fileContents.get(ii);
            
            insertData = new LinkedHashMap<String, Object>();
            
            insertData.put("COMPANY_CD",       companyCd); //회사코드
            insertData.put("VENDOR_CD",        vendorCd); //협력사코드
            insertData.put("USER_ID",          userId); //사용자ID
            insertData.put("SEQ",              ii); //index값
            insertData.put("ITEM_CD",          fileContent.get("A" + keyIdx)); //품목코드
            insertData.put("ITEM_NAME",        fileContent.get("B" + keyIdx)); //품목명
            insertData.put("FTA_CD",           StringHelper.null2void(fileContent.get("C" + keyIdx)).toUpperCase()); //FTA코드
            insertData.put("FTA_NAME",         fileContent.get("D" + keyIdx)); //FTA명
            insertData.put("HS_CODE",          fileContent.get("E" + keyIdx)); //HS코드
            insertData.put("RULE_CONTENTS",    fileContent.get("F" + keyIdx)); //결정기준
            insertData.put("RVC_RATE",         StringHelper.null2void(fileContent.get("G" + keyIdx))); //부가가치비율(%)
            insertData.put("FTA_CO_YN",        StringHelper.null2void(fileContent.get("H" + keyIdx)).toUpperCase()); //역내산
            insertData.put("ORIGIN_NATION_CD",        StringHelper.null2void(fileContent.get("I" + keyIdx)).toUpperCase()); //원산지 국가
            insertData.put("NONORIGIN_REASON_CD",        StringHelper.null2void(fileContent.get("J" + keyIdx)).toUpperCase()); //역외사유코드
            insertData.put("NONORIGIN_REASON_DESC",        fileContent.get("K" + keyIdx)); //역외사유 설명
            
            insertList.add(insertData);
        }
        
        //엑셀 임시테이블에 데이터 있는지 확인
        Map mapExcelReturnCnt = mmA022Dao.selectExcelSampleCnt(map);
        if(mapExcelReturnCnt != null) {
            int excelCnt = StringHelper.null2zero(mapExcelReturnCnt.get("EXCEL_CNT"));
            if(excelCnt > 0) {
                mmA022Dao.deleteExcelSample(map);
            }
        }
        
        //엑셀 임시테이블에 데이터 저장
        int resultCnt = mmA022Dao.saveExcelSample(insertList);
        
        //업로드를 위해 만든 excel temp파일 삭제
        String uploadFileFullPath = this.properties.get("application.path") + StringHelper.null2void(saveFileMap.get("DOWNLOAD_URL"));
        File uplodedFile = new File(uploadFileFullPath);

        if(uplodedFile.exists()) {
            uplodedFile.delete();
        }

        return resultCnt;
    }
    
    /**
     * 데이터 유효성 체크
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map executeItemErrorCheck(Map map) throws Exception {
    	return mmA022Dao.executeItemErrorCheck(map);
    }
    
    /**
     * 파일의 유효성 체크
     * 
     * @param formFile FormFile
     */
    private void checkFormFile(FormFile formFile) {
        if (formFile == null) {
            throw new RuntimeException(this.getMessage("MSG_FILE_NOT_FOUND")); /*파일이 존재하지 않습니다.*/
        }
        
        String contentType = StringHelper.null2void(formFile.getContentType());
        String originalExtension = StringHelper.null2void(formFile.getOriginalExtension());
        int fileSize = formFile.getFileSize();

        if (!(originalExtension.equals(".xlsx") || originalExtension.equals(".xls"))) {
            throw new RuntimeException(this.getMessage("MSG_EXTENSION_XLSX")); /*확장자는 .xlsx만 가능합니다.*/
        }
        
        if (Constants.FILE_MAX_UPLOAD_SIZE < fileSize) {
            throw new RuntimeException(this.getMessage("MSG_FILE_SIZE_OVER")); /*파일 용량이 초과하였습니다.*/
        }
    }
    
    /**
     * Excel Upload 결과 조회
     * 
     * @param map Map
     * @return List
     * @throws Exception
     */
    public List selectVientnamExcelSampleList(Map map) throws Exception {
        return mmA022Dao.selectVientnamExcelSampleList(map);
    }
    
    /**
     * Excel Upload & Save
     * 
     * @param map Map
     * @return int
     * @throws Exception
     */
    public int saveVientnamExcelSample(Map map) throws Exception {
        List<FormFile> formFileList = DataMapHelper.getFormFile(map);
        if(formFileList == null || formFileList.size() <= 0) {
            throw new RuntimeException(this.getMessage("MSG_FILE_NOT_FOUND")); /*파일이 존재하지 않습니다.*/
        }

        FormFile formFile = formFileList.get(0);
        
        // 첨부파일체크
        this.checkFormFile(formFile);

        if(formFile == null) {
            throw new RuntimeException(this.getMessage("MSG_FILE_NOT_FOUND")); /*파일이 존재하지 않습니다.*/
        }

        Map<String, String> saveFileMap = null;
        try {
            saveFileMap = FileUtil.transferTo(formFile); 
        } catch(Exception e) {
            throw new RuntimeException(this.getMessage("MSG_FILE_NOT_FOUND")); /*파일이 존재하지 않습니다.*/
        }
        
        if(log.isDebugEnabled()) log.debug("Origin file info = " + formFile.getOriginalFilename() +
        		"Origin file info = " + formFile.getFileName());
        
        String fileName = StringHelper.null2void(formFile.getFileName());
        
        if (saveFileMap == null || saveFileMap.isEmpty() || fileName.equals("")) {
            throw new RuntimeException(this.getMessage("MSG_FILE_NOT_FOUND")); /*파일이 존재하지 않습니다.*/
        }

        FileReaderSupporter reader = new FileReaderSupporter(new ExcelSSReader());

        List fileContents = null;
        String sheetName = null;
        
        try {
        	// 시트목록을 구한다.
            List<Map<String, Object>> sheetList = reader.getSheet(null, fileName);
            
            if(sheetList == null || sheetList.size() == 0) {
            	throw new RuntimeException(this.getMessage("WRKSH,MSG_NOT_FOUND_01"));
            } else {
            	sheetName = StringHelper.null2void(sheetList.get(0).get("CODE"));
            }
            
            fileContents = reader.getContents(fileName, 0, sheetName);
        } catch(Exception e) {
            throw new RuntimeException(this.getMessage("MSG_FILE_NOT_FOUND")); /*파일이 존재하지 않습니다.*/
        }

        if (fileContents == null || fileContents.size() <= 0) {
            // 파일전송 중 오류가 발생했습니다.
            throw new RuntimeException(this.getMessage("MSG_FILE_TRANS_ERROR") + "(" + this.getMessage("MSG_NO_INSERT") + ")");
        }

        List<Map<String, Object>> insertList = new ArrayList<Map<String, Object>>();
        Map<String, Object> insertData = null;
        Map<String, Object> fileContent = null;

        String companyCd = StringHelper.null2void(map.get("COMPANY_CD")); //회사코드
        String userId    = StringHelper.null2void(map.get("USER_ID"));    //사용자ID
        
        int keyIdx = 0;
        int nFileContentsCnt = fileContents.size();

        for(int ii = 1; ii < nFileContentsCnt; ii++) {

            keyIdx = ii + 1;

            fileContent = (LinkedHashMap<String, Object>) fileContents.get(ii);
            
            insertData = new LinkedHashMap<String, Object>();
            
            insertData.put("COMPANY_CD",       companyCd); //회사코드
            insertData.put("USER_ID",          userId); //사용자ID
            insertData.put("SEQ",              ii); //index값
            insertData.put("DIVISION_CD",          fileContent.get("A" + keyIdx)); // 사업부코드
            insertData.put("VENDOR_CD",        fileContent.get("B" + keyIdx)); // 협력사 코드
            insertData.put("VENDOR_NAME",           fileContent.get("C" + keyIdx)); // 협력사명
            insertData.put("RCV_CO_DOC_NO",         fileContent.get("D" + keyIdx)); //증명서 발급번호
            insertData.put("RCV_ISSUE_DATE",          fileContent.get("E" + keyIdx)); // 증명서 발급일자
            insertData.put("INVOICE_NO",    fileContent.get("F" + keyIdx)); // 인보이스 번호
            insertData.put("INVOICE_DATE",         StringHelper.null2void(fileContent.get("G" + keyIdx))); // 인보이스 발행일자
            insertData.put("ITEM_CD",        fileContent.get("H" + keyIdx)); // 품목코드
            insertData.put("ITEM_NAME",        fileContent.get("I" + keyIdx)); // 품목명
            insertData.put("FTA_CD",        StringHelper.null2void(fileContent.get("J" + keyIdx)).toUpperCase()); // 협정코드
            insertData.put("FTA_NAME",        fileContent.get("K" + keyIdx)); // 협정명
            insertData.put("RCV_HS_CODE",        fileContent.get("L" + keyIdx)); // HS코드
            insertData.put("RCV_RULE_CONTENTS",        fileContent.get("M" + keyIdx)); // 원산지 결정기준
            insertData.put("RVC_RATE",        fileContent.get("N" + keyIdx)); // 부가가치비율
            insertData.put("RCV_FTA_CO_YN",        StringHelper.null2void(fileContent.get("O" + keyIdx)).toUpperCase()); // 충족여부(Y/N)
            insertData.put("ORIGIN_NATION_CD",        StringHelper.null2void(fileContent.get("P" + keyIdx)).toUpperCase()); //원산지 국가
            
//            log.debug("excel index"+ii+" > "+insertData);
            
            insertList.add(insertData);
        }
        
        //엑셀 임시테이블에 데이터 있는지 확인
        Map mapExcelReturnCnt = mmA022Dao.selectVientnamExcelSampleCnt(map);
        if(mapExcelReturnCnt != null) {
            int excelCnt = StringHelper.null2zero(mapExcelReturnCnt.get("EXCEL_CNT"));
            if(excelCnt > 0) {
                mmA022Dao.deleteVientnamExcelSample(map);
            }
        }
        
        //엑셀 임시테이블에 데이터 저장
        int resultCnt = mmA022Dao.saveVientnamExcelSample(insertList);
        
        //업로드를 위해 만든 excel temp파일 삭제
        String uploadFileFullPath = this.properties.get("application.path") + StringHelper.null2void(saveFileMap.get("DOWNLOAD_URL"));
        File uplodedFile = new File(uploadFileFullPath);

        if(uplodedFile.exists()) {
            uplodedFile.delete();
        }

        return resultCnt;
    }
    
    /**
     * 베트남 데이터 유효성 체크
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map executeVientnamItemErrorCheck(Map map) throws Exception {
    	return mmA022Dao.executeVientnamItemErrorCheck(map);
    }
    
    /**
     * 베트남 원산지 증명서 저장
     * 
     * @param map Map
     * @return int
     * @throws Exception
     */
    public int insertVientnamExcelList(Map map) throws Exception {
    	// 엑셀파일의 내용으로 원산지 증명서 마스터 정보 생성
    	int cnt = mmA022Dao.insertVientnamImpCoMaster(map);
    	
    	// 엑셀파일의 내용으로 원산지 증명서 상세정보 생성
    	mmA022Dao.insertVientnamImpCoDetail(map);
    	
    	// 등록완료된 엑셀 업로드내역 삭제
    	mmA022Dao.deleteVientnamExcelSample(map);
    	
    	return cnt;
    }
    
    /**
     * 베트남 원산지 증명서 업로드내역 전체 삭제
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int deleteVientnamExcelSample(Map map) throws Exception {
    	return mmA022Dao.deleteVientnamExcelSample(map);
    }
    
    /**
     * 베트남 원산지 증명서 업로드내역 삭제
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int deleteVientnamExcelSampleRow(Map map) throws Exception {
    	return mmA022Dao.deleteVientnamExcelSampleRow(map);
    }
    
}
