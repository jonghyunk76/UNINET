package com.yni.fta.mm.pop.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.fta.common.tools.FileReaderSupporter;
import com.yni.fta.mm.pop.MMA023;

import kr.yni.frame.Constants;
import kr.yni.frame.reader.type.ExcelSSReader;
import kr.yni.frame.service.YniAbstractService;
import kr.yni.frame.util.DataMapHelper;
import kr.yni.frame.util.FileUtil;
import kr.yni.frame.util.StringHelper;
import kr.yni.frame.web.upload.FormFile;

/**
 * 공통 > 멕시코 확인서 form 조회 및 업로드 실행
 * 
 * @author carlos
 */
@Service("mmA023")
public class MMA023Impl extends YniAbstractService implements MMA023 {

    @Resource(name = "mmA023Dao")
    private MMA023Dao mmA023Dao;
    
    /**
     * Excel Upload 결과 조회
     * 
     * @param map Map
     * @return List
     * @throws Exception
     */
    public List selectExcelSampleList(Map map) throws Exception {
        return mmA023Dao.selectExcelSampleList(map);
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
            insertData.put("STATUS",           fileContent.get("A" + keyIdx)); //상태
            insertData.put("ITEM_CD",          fileContent.get("B" + keyIdx)); //품목코드
            insertData.put("ITEM_NAME",        fileContent.get("C" + keyIdx)); //품목명
            insertData.put("FTA_CD",           fileContent.get("D" + keyIdx)); //FTA코드
            insertData.put("FTA_NAME",         fileContent.get("E" + keyIdx)); //FTA명
            insertData.put("FTA_GROUP_CD",     fileContent.get("F" + keyIdx)); //FTA 그룹코드
            insertData.put("HS_CODE",          fileContent.get("G" + keyIdx)); //HS코드
            insertData.put("NALADISA_CODE",    fileContent.get("H" + keyIdx)); //Naladisa코드
            insertData.put("RULE_CONTENTS",    fileContent.get("I" + keyIdx)); //결정기준
            insertData.put("FTA_CO_YN_Y",      fileContent.get("J" + keyIdx)); //역내산
            insertData.put("FTA_CO_YN_N",      fileContent.get("K" + keyIdx)); //역외산
            insertData.put("ISSUE_DATE",       fileContent.get("L" + keyIdx)); //발행일자
            insertData.put("APPLY_DATE",       fileContent.get("M" + keyIdx)); //포괄시작일자
            insertData.put("END_DATE",         fileContent.get("N" + keyIdx)); //포괄종료일자
            insertData.put("RVC_RATE",         StringHelper.null2void(fileContent.get("O" + keyIdx))); //부가가치비율(%)
            insertData.put("TRACE_VALUE",      StringHelper.null2void(fileContent.get("P" + keyIdx))); //TraceValue
            insertData.put("CURRENCY",         fileContent.get("Q" + keyIdx)); //통화
            insertData.put("SALES_UNIT_PRICE", StringHelper.null2void(fileContent.get("R" + keyIdx))); //판매단가
            insertData.put("SALES_CURRENCY",   fileContent.get("S" + keyIdx)); //판가통화
            insertData.put("NONORIGIN_REASON_CD",        fileContent.get("T" + keyIdx)); // 역외사유코드
            insertData.put("NONORIGIN_REASON_DESC",        fileContent.get("U" + keyIdx)); //역외사유 설명
            
            if("Y".equals(fileContent.get("J" + keyIdx))) { //역내산
                insertData.put("FTA_CO_YN", "Y");
            }
            if("Y".equals(fileContent.get("K" + keyIdx))) { //역외산
                insertData.put("FTA_CO_YN", "N");
            }

            insertList.add(insertData);
        }
        
        //엑셀 임시테이블에 데이터 있는지 확인
        Map mapExcelReturnCnt = mmA023Dao.selectExcelSampleCnt(map);
        if(mapExcelReturnCnt != null) {
            int excelCnt = StringHelper.null2zero(mapExcelReturnCnt.get("EXCEL_CNT"));
            if(excelCnt > 0) {
                mmA023Dao.deleteExcelSample(map);
            }
        }
        
        //엑셀 임시테이블에 데이터 저장
        int resultCnt = mmA023Dao.saveExcelSample(insertList);
        
        // 데이터 유효성 체크
        mmA023Dao.executeItemErrorCheck(map);
        
        //업로드를 위해 만든 excel temp파일 삭제
        String uploadFileFullPath = this.properties.get("application.path") + StringHelper.null2void(saveFileMap.get("DOWNLOAD_URL"));
        File uplodedFile = new File(uploadFileFullPath);

        if(uplodedFile.exists()) {
            uplodedFile.delete();
        }

        return resultCnt;
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
    
}
