package com.yni.fta.mm.pop.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.stereotype.Service;

import com.yni.fta.common.tools.FileReaderSupporter;
import com.yni.fta.fm.sm.impl.SM7001Dao;
import com.yni.fta.common.Consistent;
import com.yni.fta.common.batch.BatchCaller;
import com.yni.fta.common.tools.ExcelFileSupporter;
import com.yni.fta.mm.batch.BatchService;
import com.yni.fta.mm.pop.MMA016;

import kr.yni.frame.Constants;
import kr.yni.frame.exception.FrameException;
import kr.yni.frame.reader.type.ExcelSSReader;
import kr.yni.frame.service.YniAbstractService;
import kr.yni.frame.util.DataMapHelper;
import kr.yni.frame.util.DateHelper;
import kr.yni.frame.util.FileUtil;
import kr.yni.frame.util.JcoMapHelper;
import kr.yni.frame.util.JsonUtil;
import kr.yni.frame.util.StringHelper;
import kr.yni.frame.web.upload.FormFile;

/**
 * 공통 > 엑셀 업로드(Excel Upload)
 * 
 * @author carlos
 *
 */
@Service("mmA016")
public class MMA016Impl extends YniAbstractService implements MMA016 {

    @Resource(name = "mmA016Dao")
    private MMA016Dao mmA016Dao;
    
    @Resource(name = "mmA002Dao")
    private MMA002Dao mmA002Dao;
    
    @Resource(name="batchService")
	private BatchService batch;
    
    @Resource(name = "sm7001Dao")
    private SM7001Dao sm7001Dao;
    
    /**
     * Excel Upload 결과 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List selectExcelData(Map map) throws Exception {
        return mmA016Dao.selectExcelData(map);
    }
    
    /**
     * Excel Upload 실행
     * 
     * @param map
     * @return 해더정보
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	public Map executeExcelUpload(Map map) throws Exception {
    	Map returnMap = new HashMap();
    	String lang = StringHelper.null2string(map.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE);
    	List<FormFile> formFileList = DataMapHelper.getFormFile(map);

    	if (formFileList == null || formFileList.size() <= 0) {
            throw new RuntimeException(this.getMessage("MSG_FILE_NOT_FOUND"));
        }

        FormFile formFile = formFileList.get(0);

        ExcelFileSupporter.checkFormFileForExcel(formFile, lang);
        
        Map<String, String> saveFileMap = null;
        
        try {
            saveFileMap = FileUtil.transferTo(formFile);
        } catch (Exception e) {
            throw new RuntimeException(this.getMessage("MSG_FILE_NOT_FOUND"));
        }

        String fileName = StringHelper.null2void(formFile.getFileName());

        if (saveFileMap == null || saveFileMap.isEmpty() || fileName.equals("")) {
            throw new RuntimeException(this.getMessage("MSG_FILE_NOT_FOUND"));
        }

        String uploadFileFullPath = null;
        String sheetName = StringHelper.null2void(map.get("SHEET_NAME"));
        String view = StringHelper.null2void(map.get("VIEW_TYPE"));
        int readIndex = StringHelper.null2zero(map.get("SKIP_COL_NO"), 0) - 2;
        if(readIndex < 0) readIndex = 0;
        int resultCnt = 0;
        
        try {
            FileReaderSupporter reader = new FileReaderSupporter(new ExcelSSReader());
            uploadFileFullPath = this.properties.get("application.path") + StringHelper.null2void(saveFileMap.get("DOWNLOAD_URL"));
            List<Map<String, Object>> fileContents = null;
            
            // 시트목록을 구한다.
            List<Map<String, Object>> sheetList = reader.getSheet(null, fileName);
            
            if(sheetList == null || sheetList.size() == 0) {
            	throw new RuntimeException(this.getMessage("WRKSH,MSG_NOT_FOUND_01"));
            } else {
            	if(sheetName.isEmpty()) {
            		sheetName = StringHelper.null2void(sheetList.get(0).get("CODE"));
            	}
            	
            	returnMap.put("Sheet", JsonUtil.getViewJson(sheetList));
            }
            
            fileContents = reader.getContents(fileName, readIndex, sheetName);
            
            if (fileContents == null || fileContents.size() <= 0) {
                throw new Exception("file contents is null or size zero");
            }
            
            // 그리드에 보여줄 해더정보를 구한다.
            List<Map<String, Object>> returnHeaderList = reader.getFileHeaderList(fileContents.get(0), view, true);
            returnMap.put("Header", returnHeaderList);
            
            String reload = StringHelper.null2string(map.get("RELOAD_FLAG"), "Y");
            
            // 이전 정보의 갱신여부를 확인한 후 처리한다.
            if(reload.equals("Y")) {
	            List<Map<String, Object>> checkList = new ArrayList<Map<String, Object>>();
	            Map<String, Object> checkData = null;
	            Map<String, Object> fileContent = null;
	            String companyCd = StringHelper.null2void(map.get("COMPANY_CD"));
	            String userId = StringHelper.null2void(map.get("USER_ID"));
	            String ifCode = StringHelper.null2void(map.get("IF_CD"));
	            String yyyymm = StringHelper.null2void(map.get("YYYYMM"));
	            
	            for (int i = 1; i < fileContents.size(); i++) {
	            	readIndex = i + 1;
	                fileContent = fileContents.get(i);
	                
	                for(int h = 1; h < returnHeaderList.size(); h++) {
	                	checkData = new LinkedHashMap<String, Object>();
	                	
	                	String colNum = ((h < 10)? "0" : "") + h;
	                	String cellNum = StringHelper.null2void(returnHeaderList.get(h).get("cell"));
	                	String transCol = "ATTRIBUTE" + colNum;
	                	String value = StringHelper.null2void(fileContent.get(cellNum + readIndex));
	                	
	                	checkData.put("COMPANY_CD", companyCd);
	                	checkData.put("IF_CD", ifCode);
	                	checkData.put("USER_ID", userId);
	                	checkData.put("ROW_NO", readIndex);
	                	checkData.put("CELL_ID", cellNum);
	                	checkData.put("COLUMN_ID", transCol);
	                	checkData.put("COLUMN_NAME", returnHeaderList.get(h).get("title"));
	                	checkData.put("COLUMN_VALUE", ExcelFileSupporter.getJcoChangeValue(ifCode, transCol, value));
	                	checkData.put("CLOSING_MONTH", yyyymm);
		                
	                	checkList.add(checkData);
	                }
	            }
	            
	            // 판매유형을 품목정보의 자산유형으로 변경시킬 정보를 조회(조회결과가 없는 경우 매출의 판매유형이 그대로 적용됨)
	            Map sysMap = sm7001Dao.selectSysconfigOptionInfo(map);
	        	String itemTypeConf = StringHelper.null2string(sysMap.get("ITEM_TYPE_DATA"), "S");
	        	List itemList = new ArrayList();
	        	
	        	if(itemTypeConf.equals("I") && ifCode.equals("RPT_SD_008")) {
	            	itemList = mmA016Dao.selectItemTypeList(map);
	            }
	            
	        	List divList = mmA002Dao.selectMainList(map);
	            
	            // 데이터 체크결과를 구해 테이블에 저장
	            List checkDataList = ExcelFileSupporter.changeColumnNameToTrans(checkList, ifCode, lang, itemList, divList);
	            
	            int delCnt = mmA016Dao.deleteExcelData(map); // 기등록된 엑셀 데이터 삭제
	            int insCnt = mmA016Dao.insertExcelData(checkDataList); // 엑셀데이터 등록
	            
	            log.debug("delete count = " + delCnt + ", insert count = " + insCnt + ", total rows = " + checkDataList.size());
            }
        } catch (Exception e) {
            log.error(ExceptionUtils.getFullStackTrace(e));
            // 파일전송 중 오류가 발생했습니다.
            throw new RuntimeException(this.getMessage("MSG_FILE_TRANS_ERROR") + "(" + this.getMessage("MSG_NO_INSERT") + ")");
        } finally {
            File uplodedFile = new File(uploadFileFullPath);
            if (uplodedFile.exists()) {
                uplodedFile.delete();
            }
        }
        
        return returnMap;
    }
    
    /**
     * Excel 컬럼항목 및 유효성 체크 결과 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List selectExcelColmunList(Map map) throws Exception {
    	String lang = StringHelper.null2string(map.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE);
    	String ifCode = StringHelper.null2void(map.get("IF_CD"));
    	
    	List colList = mmA016Dao.selectExcelColmunList(map);
    	
        return ExcelFileSupporter.setTargetColumnName(colList, ifCode, lang);
    }
    
    /**
     * Excel 컬럼별 추출 결과 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List selectExcelValueList(Map map) throws Exception {
        return mmA016Dao.selectExcelValueList(map);
    }
    
    /**
     * 대상 테이블의 컬럼리스트 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List selectJcoColumnList(Map map) throws Exception {
    	String lang = StringHelper.null2string(map.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE);
    	String ifCode = StringHelper.null2void(map.get("IF_CD"));
    	
        return ExcelFileSupporter.getJcoColumns(ifCode, lang);
    }
    
    /**
     * 대상 테이블의 컬럼정보 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map selectJcoColumnInfo(Map map) throws Exception {
    	String lang = StringHelper.null2string(map.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE);
    	String ifCode = StringHelper.null2void(map.get("IF_CD"));
    	String colName = StringHelper.null2void(map.get("COLUMN_ID"));
    	
        return ExcelFileSupporter.getJcoColumnInfo(ifCode, colName, lang);
    }
    
    /**
     * 이관시킬 대상 컬럼ID 수정
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int updateTargetColumn(Map map) throws Exception {
    	String lang = StringHelper.null2string(map.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE);
    	String ifCode = StringHelper.null2void(map.get("IF_CD"));
    	
        int cnt = mmA016Dao.updateTargetColumn(map);
        
        if(cnt > 0) {
        	List checkList = mmA016Dao.selectExcelOriginDataList(map);
        	List checkDataList = ExcelFileSupporter.changeColumnNameToTrans(checkList, ifCode, lang); // 데이터 체크결과를 구해 테이블에 저장
            
        	mmA016Dao.deleteExcelData(map); // 기등록된 엑셀 데이터 삭제
            mmA016Dao.insertExcelData(checkDataList); // 엑셀데이터 등록
        }
        
        return cnt;
    }
    
    /**
     * Excel 원본 데이터 삭제
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int deleteSourceColumn(Map map) throws Exception {
        return mmA016Dao.deleteSourceColumn(map);
    }
    
    /**
     * Excel 소스 자동 맵핑
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map executeChangeSource(Map map) throws Exception {
    	return mmA016Dao.executeChangeSource(map);
    }
    
    /**
     * 엑셀데이터 등록
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int insertExcelData(Map map) throws Exception {
    	String lang = StringHelper.null2string(map.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE);
    	String ifCode = StringHelper.null2void(map.get("IF_CD"));
    	
    	List checkList = mmA016Dao.selectExcelOriginDataList(map);
    	List divList = mmA002Dao.selectMainList(map);
    	
    	List checkDataList = ExcelFileSupporter.changeColumnNameToTrans(checkList, ifCode, lang, null, divList); // 데이터 체크결과를 구해 테이블에 저장
        
    	mmA016Dao.deleteExcelData(map); // 기등록된 엑셀 데이터 삭제
        return mmA016Dao.insertExcelData(checkDataList); // 엑셀데이터 등록
    }
    
    /**
     * Excel 인터페이스 항목 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List selectExcelInterfaceList(Map map) throws Exception {
        return mmA016Dao.selectExcelInterfaceList(map);
    }
    
    /**
     * 검증 후 Excel 데이터의 통계
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map selectExcelDataStatus(Map map) throws Exception {
    	String lang = StringHelper.null2string(map.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE);
    	String ifCode = StringHelper.null2void(map.get("IF_CD"));
    	
    	Map returnMap = mmA016Dao.selectExcelDataStatus(map);
    	
    	if(returnMap == null) returnMap = new LinkedHashMap();
    	
    	JcoMapHelper jco = new JcoMapHelper(ifCode);
    	List columns = jco.getTableColumnName(0); // 단일 테이블에 대해서만 처리
    	
    	List datas = mmA016Dao.selectExcelHeaderList(map);
    	
    	Map checkMap = ExcelFileSupporter.getRequiredCheck(columns, datas, lang);
    	
    	if(checkMap != null) {
    		returnMap.putAll(checkMap);
    	}
    	
        return returnMap;
    }
    
    /**
     * 검증 후 Excel 데이터의 해더정보 
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List selectExcelHeaderList(Map map) throws Exception {
    	String lang = StringHelper.null2string(map.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE);
    	String ifCode = StringHelper.null2void(map.get("IF_CD"));
    	String eflag = StringHelper.null2string(map.get("ERROR_SHOW_YN"), "Y");
    	
    	List columns = mmA016Dao.selectExcelHeaderList(map);
    	
        return ExcelFileSupporter.getExcelHeaderList(columns, ifCode, eflag, lang);
    }
    
    /**
     * 엑셀업로드 완료 실행
     * 
     * @param map
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	public Map executeExcelBatch(Map map) throws Exception {
    	String language = StringHelper.null2string(map.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE);
    	String functionName = StringHelper.null2void(map.get("FUNCTION_NAME"));
    	String interfaceId = StringHelper.null2void(map.get("IF_CD"));
    	String closeDate = StringHelper.null2void(map.get("YYYYMM"));
    	String schedureCd = StringHelper.null2void(map.get("SCHEDULE_CD"));
    	
    	log.debug("close date = " + closeDate + ", date length = " + closeDate.length());
    	
    	if((schedureCd.equals("MONTHLY_BATCH") || schedureCd.equals("DAILY_BATCH")) && closeDate.length() > 5) {
    		String lastDay = DateHelper.getLastDay(closeDate.substring(0, 4), closeDate.substring(4, 6));
    		
    		map.put(Consistent.IF_PARAMETER_FROM_DATE, closeDate + "01");
	        map.put(Consistent.IF_PARAMETER_TO_DATE, closeDate + lastDay);
    	}
    	
    	BatchCaller mb = new BatchCaller();
    	Map resultMap = null;
    	
    	// 데이터 인터페이스(TR_xxx 테이블에 데이터가 저장됨)
    	resultMap = mb.execute(map, batch);
    	
    	if(StringHelper.null2void(resultMap.get("O_RETURN")).equals("E")) {
    		throw new FrameException(StringHelper.null2void(resultMap.get("O_RETURNMSG")));
    	}
    	
    	String seqId = StringHelper.null2void(mb.getBatchVo().getTransId());
    	
    	// 매출조정(내수/수출)을 엑셀로 업로드한 후 인터페이스 설정에 제품BOM(RPT_PP_005)을 사용하는 경우에는 BOM을 실시간으로 가져온다.(2020-12-11)
    	if(interfaceId.equals("RPT_SD_011") || interfaceId.equals("RPT_SD_012")) {
    		this.getRealProductBOM(map, seqId, "AS");
    	}
    	
    	// 배치 프로시져 수행
    	if(!functionName.isEmpty()) {
	    	map.put(Consistent.IF_PARENT_HISTORY_ID, seqId);
	    	map.put(Consistent.IF_BATCH_ITEM_TYPE, "P");
	    	map.put(Consistent.IF_BATCH_INTERFACE_CD, functionName);
	    	map.put("FUNCTION_NAME", functionName);
	    	map.put("PARAM_IF_CD", interfaceId);
			
			resultMap = mb.execute(map, batch);
    	}
    	
		if(resultMap == null) {
    		resultMap = new HashMap();
    		
    		resultMap.put("O_RETURN", "E");
    		resultMap.put("O_RETURNMSG", this.getMessage("MSG_FAILED_BODY", null, language));
    	}
    	
		if(StringHelper.null2void(resultMap.get("O_RETURN")).equals("E")) {
    		throw new FrameException(StringHelper.null2void(resultMap.get("O_RETURNMSG")));
    	}
		
    	return resultMap;
    }
    
    /**
     * 실시간으로 제품BOM을 가져오기 위한 인터페이스 수행
     * 
     * @param map 요청 파라메터 정보
     * @param seq 상위 인터페이스 식별ID
     * @param reqType 작업을 요청 타입(AS:매출조정, DS:일배치 매출, MS:월배치 매출 등)-다양한 구분으로 활용가능
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public Map getRealProductBOM(Map map, String seq, String reqType) throws Exception {
    	BatchCaller mb = new BatchCaller();
    	Map resultMap = null;
    	
    	// 실시간 제품BOM 배치정보 조회
    	Map paramMap = new HashMap();
    	
    	paramMap.put(Consistent.IF_JOB_COMPANY_CD, StringHelper.null2void(map.get(Consistent.IF_JOB_COMPANY_CD)));
    	paramMap.put(Consistent.IF_JOB_SCHEDULE_CD, "DAILY_BATCH");
    	paramMap.put("TARGET_IF_CD", "RPT_PP_005");
    	
    	List mappingList = batch.selectInterfaceMappingList(paramMap);
    	
    	if(mappingList != null && mappingList.size() == 1) {
    		Map infMap = (Map) mappingList.get(0);
    		
    		String interfaceId = StringHelper.null2void(infMap.get("IF_CD"));
    		String functionName = "PKG_CLOSING_MANUALLY"; // 수동실행으로 인식
        	String divisionCd = StringHelper.null2void(map.get("DIVISION_CD"));
        	String itemCd = StringHelper.null2void(map.get("PRODUCT_ITEM_CD"));
        	String language = StringHelper.null2string(map.get("DEFAULT_LANGUAGE"), StringHelper.null2string(map.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE));
        	String userId = StringHelper.null2string(map.get("USER_ID"), StringHelper.null2void(map.get("SESSION_USER_ID")));
        	String fromDate = StringHelper.null2void(map.get(Consistent.IF_PARAMETER_FROM_DATE));
        	String toDate = StringHelper.null2void(map.get(Consistent.IF_PARAMETER_TO_DATE));        	
    		
    		// 인터페이스를 위한 파라메터 생성
    		infMap.put("SESSION_USER_ID", userId);
    		infMap.put("DEFAULT_LANGUAGE", language);
    		infMap.put("FROM_DATE", fromDate);
    		infMap.put("TO_DATE", toDate);
    		if(!divisionCd.isEmpty()) infMap.put(Consistent.IF_PARAMETER_DIVISION_CD, divisionCd);
    		if(!itemCd.isEmpty()) infMap.put(Consistent.IF_PARAMETER_ITEM_CD, itemCd);
    		if(seq != null && !seq.isEmpty()) infMap.put("PARENT_HISTORY_ID", seq);
    		if(reqType != null && !reqType.isEmpty()) infMap.put("REQUEST_TYPE", reqType);
    		
        	// 데이터 인터페이스(TR_xxx 테이블에 데이터가 저장됨)
        	resultMap = mb.execute(infMap, batch);
	    	
        	// 배치 프로시져 수행
        	if(!functionName.isEmpty()) {
        		infMap.put(Consistent.IF_PARENT_HISTORY_ID, StringHelper.null2void(mb.getBatchVo().getTransId()));
        		infMap.put(Consistent.IF_BATCH_ITEM_TYPE, "P");
        		infMap.put(Consistent.IF_BATCH_INTERFACE_CD, functionName); // 프로시져 인터페이스는 프로시져명과 동일함
        		infMap.put("FUNCTION_NAME", functionName);
    	    	infMap.put("PARAM_IF_CD", interfaceId);
    			
    			resultMap = mb.execute(infMap, batch);
        	}
        	
    		if(resultMap == null) {
        		resultMap = new HashMap();
        		
        		resultMap.put("O_RETURN", "E");
        		resultMap.put("O_RETURNMSG", this.getMessage("MSG_FAILED_BODY", null, language));
        	}
        	
    		if(StringHelper.null2void(resultMap.get("O_RETURN")).equals("E")) {
        		throw new FrameException(StringHelper.null2void(resultMap.get("O_RETURNMSG")));
        	}
    	}
    	
    	return resultMap;
    }
    
}
