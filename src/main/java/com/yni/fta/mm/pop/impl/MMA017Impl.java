package com.yni.fta.mm.pop.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

//import com.yni.fta.common.tools.IreportSupporter;
//import com.yni.fta.common.tools.JasperReportsPrintView;
import com.yni.fta.mm.pop.MMA017;

import kr.yni.frame.Constants;
import kr.yni.frame.config.Configurator;
import kr.yni.frame.config.ConfiguratorFactory;
import kr.yni.frame.service.YniAbstractService;
import kr.yni.frame.util.DataMapHelper;
import kr.yni.frame.util.DateHelper;
import kr.yni.frame.util.FileUtil;
import kr.yni.frame.util.JsonUtil;
import kr.yni.frame.util.StringHelper;
import kr.yni.frame.web.upload.FormFile;

/**
 * 공통 > 한국 확인서 상세 조회 및 제출
 * 
 * @author hanaRyu
 */
@Service("mmA017")
public class MMA017Impl extends YniAbstractService implements MMA017 {
    
    @Resource(name = "mmA017Dao")
    private MMA017Dao mmA017Dao;

    /**
     * 확인서 기본정보 및 생산자 조회
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     */
    public Map selectBasicInfoCaseInsert(Map map) throws Exception {
        return mmA017Dao.selectBasicInfoCaseInsert(map);
    }

    /**
     * 품목별 원산지 리스트 조회
     * 
     * @param map Map
     * @return List
     * @throws Exception
     */
    public List executeItemListCaseInsert(Map map) throws Exception {
        List list = JsonUtil.getList(StringHelper.null2void(map.get("gridData")));
        
        // map.put("gridData", list);
        for(int i = 0; i < list.size(); i++) {
            Map pmap = (Map)list.get(i);
            
            pmap.put("ROW_SEQ", (i+1));
            pmap.put("COMPANY_CD", StringHelper.null2void(map.get("COMPANY_CD")));
            pmap.put("VENDOR_CD", StringHelper.null2void(map.get("VENDOR_CD")));
            pmap.put("USER_ID", StringHelper.null2void(map.get("USER_ID")));
        }
        
        // 임시테이블 저장
        mmA017Dao.deleteExcelReqItemSample(map);
        mmA017Dao.insertExcelReqItemSample(list);
        
        if(StringHelper.null2void(map.get("REGISTED_BY")).equals("4")) {
        	return mmA017Dao.selectCoItemMappCaseInsert(map);
        } else {        	
        	return mmA017Dao.selectItemListCaseInsert(map);
        }
    }
    
    /**
     * 확인서 기본정보 및 생산자,서명권자 수정정보 조회
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     */
    public Map selectBasicInfoCaseUpdate(Map map) throws Exception {
        return mmA017Dao.selectBasicInfoCaseUpdate(map);
    }
    
    
    /**
     * 품목별 원산지 내역 수정정보 조회
     * 
     * @param map Map
     * @return List
     * @throws Exception
     */
    public List selectItemListCaseUpdate(Map map) throws Exception {
        List list = JsonUtil.getList(StringHelper.null2void(map.get("gridData")));
        
        map.put("gridData", list);
        
        return mmA017Dao.selectItemListCaseUpdate(map);
    }
    
    /**
     * 확인서 기본정보 및 생산자 갱신정보 조회
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     */
    public Map selectBasicInfoCaseRenew(Map map) throws Exception {
    	List list = JsonUtil.getList(StringHelper.null2void(map.get("gridData")));
        
        map.put("gridData", list);
        
        return mmA017Dao.selectBasicInfoCaseRenew(map);
    }
    
    /**
     * 품목별 원산지 리스트 갱신정보 조회
     * 
     * @param map Map
     * @return List
     * @throws Exception
     */
    public List executeItemListCaseRenew(Map map) throws Exception {
        List list = JsonUtil.getList(StringHelper.null2void(map.get("gridData")));
        
        //map.put("gridData", list);
        for(int i = 0; i < list.size(); i++) {
            Map pmap = (Map)list.get(i);
            
            pmap.put("ROW_SEQ", (i+1));
            pmap.put("COMPANY_CD", StringHelper.null2void(map.get("COMPANY_CD")));
            pmap.put("VENDOR_CD", StringHelper.null2void(map.get("VENDOR_CD")));
            pmap.put("USER_ID", StringHelper.null2void(map.get("USER_ID")));
        }
        
        // 임시테이블 저장
        mmA017Dao.deleteExcelReqItemSample(map);
        mmA017Dao.insertExcelReqItemSample(list);
        
        return mmA017Dao.selectItemListCaseRenew(map);
    }
    
    /**
     * 품목별 원산지 리스트 미결정보 조회
     * 
     * @param map Map
     * @return List
     * @throws Exception
     */
    public List executeItemListCasePeding(Map map) throws Exception {
        List list = JsonUtil.getList(StringHelper.null2void(map.get("gridData")));
        
     // map.put("gridData", list);
        for(int i = 0; i < list.size(); i++) {
            Map pmap = (Map)list.get(i);
            
            pmap.put("ROW_SEQ", (i+1));
            pmap.put("COMPANY_CD", StringHelper.null2void(map.get("COMPANY_CD")));
            pmap.put("VENDOR_CD", StringHelper.null2void(map.get("VENDOR_CD")));
            pmap.put("USER_ID", StringHelper.null2void(map.get("USER_ID")));
        }
        
        // 임시테이블 저장
        mmA017Dao.deleteExcelReqItemSample(map);
        mmA017Dao.insertExcelReqItemSample(list);
        
        return mmA017Dao.selectItemListCasePeding(map);
    }
    
    /**
     * 증빙파일 다운로드
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     * */
    public Map selectProofFile(Map map) throws Exception {
        return mmA017Dao.selectProofFile(map);
    }
    
    /**
     * 한국 확인서 기본정보 신규 등록<br>
     *  1.포괄기간 중복여부 체크
     *  2.원산지 증명번호 채번<br>
     *    - 사용자가 직접 입력할 경우 기등록 증명번호인지 체크함<br>
     *  3.확인서 기본정보 및 생산자,서명권자 등록<br>
     *  4.품목 원산지 정보 전체 삭제<br>
     *  5.품목 원산지 정보 저장<br>
     *  6.원산지 정보 증빙파일 저장<br>
     *  7.협력사 자재의 원산지 확인서 등록 상태 변경(N:신규, U:기등록)<br>
     *  8.원산지 확인서 제출상태 수정<br>
     * 
     * @param map Map
     * @return int
     * @throws Exception
     */
    public int insertRcvCoInfo(Map map) throws Exception {
    	int rtVal = 0;
        Map rtMap = null;
        
        // 증빙파일이 미첨부된 상태에서 보고서 파일을 찾을 수 없다면, 오류를 발생한다.
        if(map.get("PRF_FILE") == null || !map.containsKey("PRF_FILE")) {
        	// 보고서 파일(VENDOR_COVER_ver.1.2)이 있는지 점검한다.
        	Configurator configurator = ConfiguratorFactory.getInstance().getConfigurator();
    		String dbType = StringHelper.null2void(configurator.getString("application.db.type"));
    		String root = StringHelper.null2void(configurator.getString("report.root.path"));
    		String jasperPath = "";
    		
    		if(dbType.equals("MSSQL")) {
    			jasperPath = root + "mssql/out/VENDOR_COVER_ver.1.2.jasper";
    		} else if(dbType.equals("POSTGRESQL")) {
    			jasperPath = root + "postgresql/out/VENDOR_COVER_ver.1.2.jasper";			
    		} else if(dbType.equals("DB2")) {
    			jasperPath = root + "db2/out/VENDOR_COVER_ver.1.2.jasper";
    		} else {
    			jasperPath = root + "oracle/out/VENDOR_COVER_ver.1.2.jasper";
    		}
    		
    		// jasper파일이 존재하는지 체크하고 존재하면 Report객체를 얻는다.
    		File compiledFile = new File(jasperPath);
    		
    		if(!compiledFile.exists()) {
    			throw new RuntimeException(this.getMessage("MSG_FILE_ADD"));
    		}
        }
        
        String coDocNo = StringHelper.null2void(map.get("CO_DOC_NO"));
        String flag = StringHelper.null2void(map.get("flag"));
        String ftaGroup = StringHelper.null2void(map.get("FTA_GROUP_CD")); // MR,MX,MER,VN등
        
        List list = JsonUtil.getList(StringHelper.null2void(map.get("gridData")));
        
        for(int i = 0; i < list.size(); i++) {
            Map pmap = (Map)list.get(i);
            
            pmap.put("ROW_SEQ", (i+1));
            pmap.put("COMPANY_CD", StringHelper.null2void(map.get("COMPANY_CD")));
            pmap.put("DIVISION_CD", StringHelper.null2void(map.get("DIVISION_CD")));
            pmap.put("VENDOR_CD", StringHelper.null2void(map.get("VENDOR_CD")));
            pmap.put("USER_ID", StringHelper.null2void(map.get("USER_ID")));
            pmap.put("APPLY_DATE", StringHelper.null2void(map.get("APPLY_DATE")));
            pmap.put("END_DATE", StringHelper.null2void(map.get("END_DATE")));
            pmap.put("FTA_CO_YN", StringHelper.null2string(pmap.get("FTA_CO_YN"), "N"));
        }
        
        map.remove("gridData"); // 불필요한 정보 삭제
        
        // 임시테이블에 이전 작업중인 내역을 삭제하고 리스트 정보를 저장함.
        mmA017Dao.deleteExcelDOSample(map);
        mmA017Dao.insertExcelDOSample(list);
        
        // 포괄기간 중복여부 체크
        Map coDate = mmA017Dao.selectCoDateCheck(map);
        
        if(StringHelper.null2zero(coDate.get("CO_DATE_CNT")) > 0) {
            throw new RuntimeException(this.getMessage("MSG_INVALID_EXT_COO"));
        }
        
        // 확인서 증명번호 채번
        if(coDocNo.isEmpty()) {
            rtMap = mmA017Dao.selectMaxCoDocNo(map);
            
            map.put("CO_DOC_NO", StringHelper.null2void(rtMap.get("CO_DOC_NO")));
        } else {
        	if(!"update".equals(flag)) {
	        	rtMap = mmA017Dao.selectcInterfaceDupCheck(map);
	            
	            if(rtMap != null) {
	                int nDupCnt = StringHelper.null2zero(rtMap.get("DUPLICATE"));
	                
	                if(nDupCnt > 0) {
	                	// 존재하는 확인서 증명번호입니다.
	                    throw new Exception(getMessage("MSG_EXISTS_IF_CODOCNO"));
	                }
	            }
        	}
        }
        
        // 수정시에는 등록전 이력을 기록한다.(YNI-Maker, 2016.09.20)
        if("update".equals(flag)) {
        	mmA017Dao.insertItemListHistory(map);
        	mmA017Dao.insertItemListHistoryDetail(map);
        	mmA017Dao.insertItemListHistoryFile(map);
        }
        
        // 품목의 원산지 정보를 등록한다.(구매하는 사업장에 맞게 등록하기 위해  품목을 우선 등록하도록 수정했음 - YNI-Maker, 2017-1-29)
        for(int i = 0; i < list.size(); i++) {
            Map pmap = (Map)list.get(i);
            
            pmap.put("CO_DOC_NO", StringHelper.null2void(map.get("CO_DOC_NO")));
        }
        
        // 멕시코 버전에서 적용되는 내용임(나중에는 임시테이블로 변경할 것)
        if(ftaGroup.equals("MX")||ftaGroup.equals("MER")) map.put("gridData", list);
        
        // 품목 원산지 정보 등록(원산지 확인서 상세정보)
        mmA017Dao.deleteItemListAll(map);
        mmA017Dao.insertItemList(map);
        
        // 확인서 기본정보 및 생산자,서명권자 저장(원산지 확인서 마스터 정보)
        if("update".equals(flag)) {
        	rtVal = mmA017Dao.updateBasicInfo(map);
        } else {
        	rtVal = mmA017Dao.insertBasicInfo(map);
        }
        
        return rtVal;
    }
    
    /**
     * 증빙파일을 등록한다.
     * @param map
     * @return
     * @throws Exception
     */
    public int insertRcvCoFileInfo(Map map) throws Exception {
    	String flag = StringHelper.null2void(map.get("flag"));
    	String regBy = StringHelper.null2void(map.get("REGISTED_BY"));
    	String ftaGroup = StringHelper.null2void(map.get("FTA_GROUP_CD")); // MR,MX,MER,VN등
    	
        int rtVal = -1;
    	
//    	try {
//	        // 원산지 정보 증빙파일 저장
//	        if(map.get("PRF_FILE") != null && map.containsKey("PRF_FILE")) {
//	            List<FormFile> formFileList = DataMapHelper.getFormFile(map);
//	            
//	            if(formFileList == null || formFileList.size() <= 0) {
//	                throw new RuntimeException(this.getMessage("MSG_FILE_NOT_FOUND")); /*파일이 존재하지 않습니다.*/
//	            }
//	    
//	            FormFile formFile = formFileList.get(0);
//	            
//	            // 첨부파일체크
//	            this.checkFormFile(formFile);
//	            
//	            if (formFile == null) {
//	                throw new RuntimeException(this.getMessage("MSG_FILE_NOT_FOUND")); /*파일이 존재하지 않습니다.*/
//	            }
//	            
//	            map.put("PRF_FILE_TYPE", "B");
//	            map.put("PRF_FILE_NAME", formFile.getFileName());
//	            map.put("PRF_FILE_ORG_NAME", formFile.getOriginalFilename());
//	            map.put("PRF_FILE_DATA", formFile.getFileData());
//	            map.put("EV_DOC_TYPE", "01");
//	        } else if(!ftaGroup.equals("VN")){ // 파일 없는 경우 자동 생성함(2017.11.23, YNI-Maker), 2021.08.17 - 베트남 FTA는 제외
//	        	IreportSupporter operator = new IreportSupporter();
//	            JasperReportsPrintView view = new JasperReportsPrintView();
//	            String certifyNo = StringHelper.null2void(map.get("CO_DOC_NO"));
//	            
//	            // 파라메터 생성
//	            ModelMap parameter = new ModelMap();
//	            
//	            parameter.put("REPORT_PRINT_TYPE", "F"); // 보고서 출력 형태(E:Empty, F:file, P:page)
//	            parameter.put("DOC_FILE_NAME", "VENDOR_COVER_ver.1.2"); // 레포트 파일명 : 여러 파일을 동시에 보여줄 경우 구분자(^)로 파일명을 만들 것
//	            parameter.put("REPORT_TYPE", "pdf"); // 레포트 타입
//	            // 레포트 공통 파라메터
//	            parameter.put("P_COMPANY_CODE", StringHelper.null2void(map.get("COMPANY_CD")));
//	            parameter.put("P_COO_CERTIFY_NO", certifyNo);
//	            parameter.put("P_VENDOR_CODE", StringHelper.null2void(map.get("VENDOR_CD")));
//	            
//	            view = operator.getJasperPrintOutputStream(parameter);
//	            
//	            map.put("PRF_FILE_TYPE", "B");
//	            map.put("PRF_FILE_NAME", certifyNo + "_" + DateHelper.getCurrentDateAsString() + ".pdf");
//	            map.put("PRF_FILE_ORG_NAME", certifyNo + "_" + DateHelper.getCurrentDateAsString() + ".pdf");
//	            map.put("PRF_FILE_DATA", view.getIReportFileStream());
//	            map.put("EV_DOC_TYPE", "01");
//	        }
//	        
//	        if(!StringHelper.null2void(map.get("EV_DOC_TYPE")).isEmpty()) {
//		        mmA017Dao.deleteProofFileInfo(map);
//		        mmA017Dao.insertProofFileInfoCaseInsert(map);
//		        mmA017Dao.updateProofFileInfo(map);
//	        }
//	        
//	        if("pending".equals(flag)) {
//	        	// 미결요청 처리완료 상태로 변경
//	        	mmA017Dao.updateRequestCoIssueStatus(map);
//	        	// 원산지 확인서 제출상태 수정
//	        	rtVal = mmA017Dao.updateDocSubmit(map);
//	        } else {
//		        // 협력사 자재의 원산지 확인서 등록 상태 변경(N:신규, U:기등록)
//		        mmA017Dao.updateVendorItemStatus(map);
//		        // 원산지 확인서 제출상태 수정
//		        rtVal = mmA017Dao.updateDocSubmit(map);
//		        // 원산지 확인서 수정 이력 등록
//		        if("update".equals(flag)) {
//		        	mmA017Dao.updateDocRequestStatus(map); // 수정요청 완료로 변경
//		        	mmA017Dao.updateVendorItemResync(map); // 수정 후 협력사 확인서와 납품 품목의 싱크를 맞춘다.
//		        }
//		        
//		        // 베트남 개별법인 경우에는 개별 원산지 신고 요청상태를 완료로 업데이트한다.
//		        if(ftaGroup.equals("VN")) {
//		        	List list = JsonUtil.getList(StringHelper.null2void(map.get("batchData")));
//		        	
//		        	if(list != null && list.size() > 0) {
//		        		for(int i = 0; i < list.size(); i++) {
//		        			Map gmap = (Map) list.get(i);
//		        			
//		        			gmap.put("CO_DOC_NO", StringHelper.null2void(map.get("CO_DOC_NO")));
//		        			gmap.put("USER_ID", StringHelper.null2void(map.get("USER_ID")));
//		        			gmap.put("COMPANY_CD", StringHelper.null2void(map.get("COMPANY_CD")));
//		        			gmap.put("VENDOR_CD", StringHelper.null2void(map.get("VENDOR_CD")));
//		        			gmap.put("INVOICE_NO", StringHelper.null2void(map.get("INVOICE_NO")));
//		        		}
//		        		
//		        		mmA017Dao.updateVendorCoMgmt(list); // 수입 원산지 패킹리스트 신고시 적용
//		        	} else {
//		        		mmA017Dao.updateVendorCoMgmt(map); // 내수 원산지 개별 신고시 적용
//		        	}
//		        }
//	        }
//	        
//	        if(regBy.equals("4")) { // 담당자가 철판코일 확인서를 맵핑해서 등록하는 경우, 증빙번호를 맵핑정보에 업데이트한다.
//	        	mmA017Dao.updateCoItemMapping(map);
//	        }
//    	} catch(Exception e) {
//    		rtVal = -1;
//    		if(log.isErrorEnabled()) log.error("[" + this.getMessage("MSG_SUBMIT_FAIL") + "]"+e);
//    	} finally {
//	        // 속도를 위해 파일은 삭제함.
//	        map.remove("SIGN_CARD");
//    	}
        
        return rtVal;
    }
    
    /**
     * 확인서 기본정보 및 생산자,서명권자 삭제
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     * */
    public int deleteRcvMstInfo(Map map) throws Exception {
        return mmA017Dao.deleteRcvMstInfo(map);
    }
    
    /**
     * 품목별 원산지 정보 삭제 전체
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     * */
    public int deleteItemListAll(Map map) throws Exception {
        return mmA017Dao.deleteItemListAll(map);
    }
    
    /**
     * 파일의 유효성 체크
     * 
     * @param formFile FormFile
     */
    private void checkFormFile(FormFile formFile) throws Exception {
    	if (formFile == null) {
            throw new RuntimeException(this.getMessage("MSG_FILE_NOT_FOUND")); /*파일이 존재하지 않습니다.*/
        }
    	
        String originalExtension = StringHelper.null2void(formFile.getOriginalExtension()).toLowerCase();
        
        int fileSize = formFile.getFileSize();
        
        if(!(originalExtension.equals(".pdf") || originalExtension.equals(".xls") || originalExtension.equals(".xlsx") || 
        		originalExtension.equals(".doc") || originalExtension.equals(".docx") || originalExtension.equals(".zip"))) {
            throw new RuntimeException(this.getMessage("MSG_EXT_PDF_XLS_DOC")); /*확장자는 pdf, xls, doc만 가능합니다.*/
        }
        
        if(fileSize > Constants.FILE_MAX_UPLOAD_SIZE) {
            throw new RuntimeException(this.getMessage("MSG_FILE_SIZE_OVER")); /*파일 용량이 초과하였습니다.*/
        }
    }
    
    /**
     * 원산지 수정을 위해 기존 포괄만료기간을 업데이트함
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     * */
    public int updateModifyEndDate(Map map) throws Exception {
        return mmA017Dao.updateModifyEndDate(map);
    }
    
    /**
     * 서명카드 이미지파일 불러오기
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     * */
    public Map selectSignatureImage(Map map) throws Exception {
        return mmA017Dao.selectSignatureImage(map);
    }
    
    /**
     * 증명파일 업데이트 실행
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int updateProofFileInfo(Map map) throws Exception {
        return mmA017Dao.updateProofFileInfo(map);
    }
    
    /**
     * 원산지 확인서 증빙파일 등록
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int insertEvCoFiles(Map map) throws Exception {
    	List<FormFile> formFileList = DataMapHelper.getFormFile(map);
        
        if(formFileList == null || formFileList.size() <= 0) {
            throw new RuntimeException(this.getMessage("MSG_FILE_NOT_FOUND")); /*파일이 존재하지 않습니다.*/
        }

        FormFile formFile = formFileList.get(0);
        
        // 첨부파일체크
        this.checkFormFile(formFile);
        
        if (formFile == null) {
            throw new RuntimeException(this.getMessage("MSG_FILE_NOT_FOUND")); /*파일이 존재하지 않습니다.*/
        }
        
        map.put("PRF_FILE_TYPE", "B");
        map.put("PRF_FILE_NAME", formFile.getFileName());
        map.put("PRF_FILE_ORG_NAME", formFile.getOriginalFilename());
        map.put("PRF_FILE_DATA", formFile.getFileData());
    	
        return mmA017Dao.insertEvCoFiles(map);
    }
    
    /**
     * 증명파일 삭제
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int deleteProofFileInfo(Map map) throws Exception {
        return mmA017Dao.deleteProofFileInfo(map);
    }
    
    /**
     * 확인서 증명서/확인서 개별 업데이트(관리자 권한으로만 가능)
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     * */
    public String updateMgmtEvenFile(Map map) throws Exception {
    	List<FormFile> formFileList = DataMapHelper.getFormFile(map);
        
        if(formFileList == null || formFileList.size() <= 0) {
            throw new RuntimeException(this.getMessage("MSG_FILE_NOT_FOUND")); /*파일이 존재하지 않습니다.*/
        }

        FormFile formFile = formFileList.get(0);
        
        // 첨부파일체크
        this.checkFormFile(formFile);
        
        if (formFile == null) {
            throw new RuntimeException(this.getMessage("MSG_FILE_NOT_FOUND")); /*파일이 존재하지 않습니다.*/
        }
        
        map.put("PRF_FILE_TYPE", "B");
        map.put("PRF_FILE_NAME", formFile.getFileName());
        map.put("PRF_FILE_ORG_NAME", formFile.getOriginalFilename());
        map.put("PRF_FILE_DATA", formFile.getFileData());
        map.put("EV_DOC_TYPE", "01");
        
        mmA017Dao.deleteProofFileInfo(map);
        mmA017Dao.insertProofFileInfoCaseInsert(map);
        mmA017Dao.updateProofFileInfo(map);
    	
        return formFile.getOriginalFilename();
    }
    
}
