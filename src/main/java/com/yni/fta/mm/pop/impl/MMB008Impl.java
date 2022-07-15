package com.yni.fta.mm.pop.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.fta.common.tools.FileReaderSupporter;
import com.yni.fta.mm.pop.MMB008;

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
 * 공통 > 표준통관예정 자료 업로드 구현 클래스
 * 
 * @author YNI-maker
 *
 */
@Service("MMB008")
public class MMB008Impl extends YniAbstractService implements MMB008 {
	
	@Resource(name="MMB008Dao")
	private MMB008Dao MMB008Dao;
	
	/**
	 * 표준통관 업로드 데이터 리스트 조회
	 * 
	 * @param map 검색 조건
	 */
	public List selectStandardEntrUploList(Map map) throws Exception {
		return MMB008Dao.selectStandardEntrUploList(map);
	}
	
	/**
	 * 표준통관 업로드 데이터 일괄 수정
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateStandardEntrUplo(Map map) throws Exception {
		String user = StringHelper.null2void(map.get("USER_ID"));
		List list = JsonUtil.getList(StringHelper.null2string(map.get("gridData"), "[]"));
		List updList = new LinkedList();
		List insList = new LinkedList();
		
		if(list != null) {
			for(int i = 0; i < list.size(); i++) {
				Map mdata = (Map) list.get(i);
				
				mdata.put("USER_ID", user);
				
				if(StringHelper.null2void(mdata.get("SN")).isEmpty()) {
					insList.add(mdata);
				} else {
					updList.add(mdata);
				}
			}
			
			log.debug("insert count = " + insList.size() + " / update count = " + updList.size());
		}
		
		int cnt = MMB008Dao.updateStandardEntrUplo(updList, insList);
		
		// 표통과 수입업로드 자료 매칭 수행
        this.updateStandardEntrUploadNo(map);
		
		return cnt;
	}
	
	/**
	 * 표준통관 업로드 데이터 일괄 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteStandardEntrUplo(Map map) throws Exception {
		List list = JsonUtil.getList(StringHelper.null2string(map.get("gridData"), "[]"));
		
		int cnt = MMB008Dao.deleteStandardEntrUplo(list);
		
		// 표통과 수입업로드 자료 매칭 수행
        this.updateStandardEntrUploadNo(map);
		
		return cnt;
	}
	
	/**
     * Excel Upload & Save
     * 
     * @param map Map
     * @return int
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public int saveExcelSample(Map map) throws Exception {
    	int resultCnt = 0;
    	List<FormFile> formFileList = DataMapHelper.getFormFile(map);
        
        if(formFileList == null || formFileList.size() <= 0) {
            throw new RuntimeException(this.getMessage("MSG_FILE_NOT_FOUND")); /*파일이 존재하지 않습니다.*/
        }
        
        log.debug("Form file size = " + formFileList.size());
        
        for(int f = 0; f < formFileList.size(); f++) {
	        FormFile formFile = formFileList.get(f);
	        
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
	
	        List fileContents = null; // 파일내용
	        String sheetName = null; // 시트번호
	        
	        try {
	        	// 시트목록을 구한다.
	            List<Map<String, Object>> sheetList = reader.getSheet(null, fileName);
	            
	            if(sheetList == null || sheetList.size() == 0) {
	            	throw new RuntimeException(this.getMessage("WRKSH,MSG_NOT_FOUND_01"));
	            } else {
	            	sheetName = StringHelper.null2void(sheetList.get(0).get("CODE"));
	            }
	            
	            fileContents = reader.getContents(fileName, 0, sheetName, true);
	        } catch(Exception e) {
	        	if(log.isErrorEnabled()) log.error(e);
	            throw new RuntimeException(this.getMessage("MSG_FILE_NOT_FOUND")); /*파일이 존재하지 않습니다.*/
	        }
	
	        if (fileContents == null || fileContents.size() <= 0) {
	            // 파일전송 중 오류가 발생했습니다.
	            throw new RuntimeException(this.getMessage("MSG_FILE_TRANS_ERROR") + "(" + this.getMessage("MSG_NO_INSERT") + ")");
	        }
	
	        List<Map<String, Object>> insertList = new LinkedList<Map<String, Object>>();
	        Map<String, Object> fileContent = null;
	        
	        int keyIdx = 0;
	        int nFileContentsCnt = fileContents.size();
	        String issueNo = "";
	        String issueDate = "";
	        boolean skipNo = false;
	        
	        for(int ii = 1; ii < nFileContentsCnt; ii++) {
	        	// G열에 값이 "표준통관예정보고서"이면 다음행은 skip한다.(2행 merge된 양식을 고려해서 적용함)
	        	if(skipNo) {
	        		skipNo = false;
	        		continue;
	        	}
	        	
	            fileContent = (Map) fileContents.get(ii);
	            
	            String constrA = "";
	            String constrB = "";
	            String constrC = "";
	            String constrM = "";
	            String constrK = ""; // 표통 양식 개정으로 발급일자 위치 변경됨(2022-06-21)
	            String constrG = "";
	            int height = StringHelper.null2zero(fileContent.get("ROW_HEIGHT"))/20;
	            
	            Set set = fileContent.entrySet();
	            Iterator iterator = set.iterator();
	
	            while(iterator.hasNext()){
		            Map.Entry entry = (Map.Entry)iterator.next();
		            String key = (String)entry.getKey();
		            String data = StringHelper.null2void(entry.getValue()).replaceAll(System.getProperty("line.separator"), " ").trim();
		            
		            if(key.startsWith("A")) constrA = data;
		            if(key.startsWith("B")) constrB = data;
		            if(key.startsWith("C")) constrC = data;
		            if(key.startsWith("M")) constrM = data;
		            if(key.startsWith("K")) constrK = data;
		            if(key.startsWith("G")) constrG = data;
	            }
	            
	            if(StringHelper.replace(constrA, " ", "").toUpperCase().startsWith("(14)발급번호")) { // 발급번호 구하기
	            	// 발급번호
	            	issueNo = StringHelper.replace(constrC, " ", "");
	            	// 발급일자(M -> K로 위치 변경, 2022-06-21)
	            	if(!constrM.isEmpty()) issueDate = StringHelper.replace(constrM, "-", "");
	            	else issueDate = StringHelper.replace(constrK, "-", "");
	            }
	            
	            if(!this.checkRemoveData(constrA, constrB, constrC, constrG) && height > 14) { // 셀 높이가 특정 이하인 경우에는 무시하도록 하고 있음
	            	insertList.add(fileContent);
//	            	log.debug(" insert Data["+constrA+","+constrB+","+constrC+"] > " + fileContent);
	            }
	            
	            if(constrG.equals("표준통관예정보고서")) {
	            	skipNo = true;
	            }
	        }
	        
	        int vnum = 0;
	        List<Map<String, Object>> vlist = new LinkedList<Map<String, Object>>();
	        Map cmap = new LinkedHashMap();
	        Map vmap = null;
	        
	        // 표준통관 요건서류에서 데이터 추출
	        for(int i = 1; i < insertList.size(); i++) {
	        	Map imap = (Map) insertList.get(i);
	        	
	        	String constrA = "";
	            String constrB = "";
	            String constrD = "";
	            String constrJ = "";
	            String constrK = "";
	            
	        	Set set = imap.entrySet();
	            Iterator iterator = set.iterator();
	            
	            while(iterator.hasNext()){
		            Map.Entry entry = (Map.Entry)iterator.next();
		            String key = (String)entry.getKey();
		            String data = StringHelper.null2void(entry.getValue()).replaceAll(System.getProperty("line.separator"), " ").trim();
		            
		            if(key.startsWith("A")) constrA = data;
		            if(key.startsWith("B")) constrB = data;
		            if(key.startsWith("D")) constrD = data;
		            if(key.startsWith("J")) constrJ = data;
		            if(key.startsWith("K")) constrK = data; // constrJ에 값이 없는 경우, 사용할 값(실제, 양식에 따라 J 또는 H셀로 값이 저장되어 있음)
	            }
	            
	            if(constrJ == null || constrJ.isEmpty()) {
	            	constrJ = constrK;
	            }
	            
//	            log.debug("put Data("+vnum+")["+constrA+","+constrB+","+constrD+","+constrJ+"] > " + imap);
	            
	        	if(!constrA.isEmpty() && DataHelper.isNumber(constrA)) { // 첫 A셀의 값이 숫자라면 값의 시작으로 인식함
	        		vnum = 1;
	        		vmap = new LinkedHashMap();
	        		
	        		vmap.put("COMPANY_CD", StringHelper.null2void(map.get("COMPANY_CD"))); // 회사코드
	        		vmap.put("IMP_BCNC_SETUP_SN", StringHelper.null2void(map.get("IMP_BCNC_SETUP_SN"))); // 수입신고 설정 일련번호
	        		vmap.put("USER_ID", StringHelper.null2void(map.get("USER_ID"))); // 로그인ID
	        		vmap.put("ISSU_NO", issueNo); // 발급번호
        			vmap.put("ISSU_DE", issueDate); // 발급일자
        			
        			cmap.put("COMPANY_CD", StringHelper.null2void(map.get("COMPANY_CD"))); // 회사코드
	        		cmap.put("IMP_BCNC_SETUP_SN", StringHelper.null2void(map.get("IMP_BCNC_SETUP_SN"))); // 수입신고 설정 일련번호
	        		cmap.put("ISSU_NO", issueNo); // 발급번호
	        	}
	        	
	        	if(vnum > 0) {
	        		log.debug("put Data("+vnum+")["+constrA+","+constrB+","+constrD+" >>>> "+constrJ+"] > " + imap);
	        		
	        		if(vnum == 1) {
	        			vmap.put("SN", constrA);// 일련번호
	        			vmap.put("HS_SL", StringHelper.replace(constrB, "-", "")); // 세번부호
	    				vmap.put("MTRIL_CD", constrD); // 품목코드(원데이터)
	    				vmap.put("ITEM_CD", this.getItemCode(map, constrD)); // 품목코드(추출)
	    				vmap.put("STARD_01", constrJ); // 거래품명(규격1로 지정할 것)
	    				
	    				// 품목식별번호 자동분류(2022-06-17)
	    				if(!constrA.isEmpty()) {
	    					int nom = Integer.parseInt(constrA);
	    					String nos = "FR2" + String.format("%03d", nom);
	    					
	    					vmap.put("PRDLST_IDNT_SL", nos); // 품목식별번호
	    				}
	        		} else if(vnum == 2) {
	        			if(!constrA.isEmpty()) vmap.put("PRDLST_IDNT_SL", constrA); // 품목식별번호(서식변경으로 삭제, 2022-06-17)
	        			vmap.put("STARD_02", constrB); // 모델규격(형명)2
	    				vmap.put("IRDNT_01", constrJ); // 성분함량
	        		} else if(vnum == 3) {
	        			String[] qy = constrB.split("\\(");
	    				String[] amt = constrJ.split("\\(");
	    				
	    				if(qy.length > 0) vmap.put("QY", StringHelper.replace(qy[0].trim(), ",", "")); // 수량
	    				if(qy.length > 1) vmap.put("QY_UNIT", StringHelper.replace(qy[1].trim(), ")", "")); // 수량단위
	    				vmap.put("INP_UNTPC", StringHelper.replace(constrD, ",", "")); // 단가
	    				if(amt.length > 0) vmap.put("AMT", StringHelper.replace(amt[0].trim(), ",", "")); // 금액
	    				if(amt.length > 1) vmap.put("AMT_UNIT", StringHelper.replace(amt[1].trim(), ")", "")); // 금액
	        		} else if(vnum == 4) {
	        			String[] prod = constrB.split("/");
	    				
	    				if(prod.length > 0) vmap.put("MNFACR_NO", prod[0].trim()); // 제조번호
	    				if(prod.length > 1) vmap.put("MNFACR_DE", prod[1].trim()); // 제조일
	        		} else if(vnum == 5) {
	        			String[] nadd = constrJ.split("/");
	    				
	    				vmap.put("OUT_MNFACR_ENPS", constrB); // (위탁)제조원
	    				if(nadd.length > 0) vmap.put("OUT_MNFACR_NATION_CD", nadd[0].trim()); // (위탁)제조국
	    				if(nadd.length > 1) vmap.put("OUT_MNFACR_ADRES", nadd[1].trim()); // (위탁)주소
	        		} else if(vnum == 6) {
	        			String[] nadd = constrJ.split("/");
	        			
	        			vmap.put("IN_MNFACR_ENPS", constrB); // 계약(수탁)제조원
	    				if(nadd.length > 0) vmap.put("IN_MNFACR_NATION_CD", nadd[0].trim()); // 계약(수탁)제조국
	    				if(nadd.length > 1) vmap.put("IN_MNFACR_ADRES", nadd[1].trim()); // 계약(수탁)제조원 주소
	        		} else if(vnum == 7) {
	        			String[] bse = constrB.split("/");
	    				
	    				if(bse.length > 0) vmap.put("BSE_01", bse[0].trim()); // 기원동물
	    				if(bse.length > 1) vmap.put("BSE_02", bse[1].trim()); // 추출부위
	    				if(bse.length > 2) vmap.put("BSE_03", bse[2].trim()); // Batch No
	    				if(bse.length > 3) vmap.put("BSE_04", bse[3].trim()); // 원산지
	        		} else if(vnum == 8) {
	        			if(!constrB.isEmpty()) vmap.put("PRDLST_IDNT_SL", constrB); // 품목식별번호(서식변경으로 수정, 2022-06-17)
	        		}
	        		
	        		vnum++;
        		}
        			        	
        		if(vnum > 8) {
        			log.debug("filter Data("+vnum+") > " + vmap);
        			vlist.add(vmap);
        			vnum = 0;
        		}
	        }
	        
	        MMB008Dao.deleteStandardEntrUplo(cmap); // 중복된 데이터 삭제
	        MMB008Dao.insertStandardEntrUplo(vlist); // 표준통관 정보 등록
	        
	        // 표통과 수입업로드 자료 매칭 수행
	        this.updateStandardEntrUploadNo(map);
	        
            //업로드를 위해 만든 excel temp파일 삭제
	        String uploadFileFullPath = this.properties.get("application.path") + StringHelper.null2void(saveFileMap.get("DOWNLOAD_URL"));
	        File uplodedFile = new File(uploadFileFullPath);
	
	        if(uplodedFile.exists()) {
	            uplodedFile.delete();
	        }
        }
        
        return resultCnt;
    }
    
    /**
     * 표통과 수입업로드 자료 매칭 수행
     * 
     * @param map
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public int updateStandardEntrUploadNo(Map map) throws Exception {
    	int rst = 0;
    	String initYn = StringHelper.null2string(map.get("UPLOAD_NO_INIT_YN"), "N"); 
    			
    	MMB008Dao.updateStandardEntrDupCnt(map); // 중복되는 데이터 개수 업데이트(2022-04-14)
        
        // 중복된 데이터 개수의 최대값를 구하고, 개수만큼 수입자료업로드 번호를 연결한다.(2022-04-14)
        int maxCnt = StringHelper.null2zero(MMB008Dao.selectStandardEntrDupCnt(map));
        List plist = new LinkedList();
        
        if(log.isDebugEnabled()) log.debug("Max count = " + maxCnt);
        
        for(int p = 1; p <= maxCnt; p++) {
        	Map pmap = new HashMap();
        	
        	pmap.put("COMPANY_CD", StringHelper.null2void(map.get("COMPANY_CD"))); // 회사코드
        	pmap.put("IMP_BCNC_SETUP_SN", StringHelper.null2void(map.get("IMP_BCNC_SETUP_SN"))); // 수입신고 설정 일련번호
        	pmap.put("DUP_CO", Integer.toString(p));
        	pmap.put("STEP_NO", "1");
        	
        	plist.add(pmap);
        }
        
        if(plist.size() > 0) {
        	// 재매칭을 요청하는 경우 연결정보를 초기화 시키도 처음부터 다시 매칭한다.
        	if(initYn.equals("Y")) MMB008Dao.updateInitStandardEntrUploadNo(map);
        	
        	rst = MMB008Dao.updateStandardEntrUploadNo(plist);
        }
        
        return rst;
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
        
        String contentType = StringHelper.null2void(formFile.getContentType());
        String originalExtension = StringHelper.null2void(formFile.getOriginalExtension()).toLowerCase();
        int fileSize = formFile.getFileSize();

        if (!(originalExtension.equals(".xlsx") || originalExtension.equals(".xls"))) {
            throw new RuntimeException(this.getMessage("MSG_EXTENSION_XLSX")); /*확장자는 .xlsx만 가능합니다.*/
        }
        
        if (Constants.FILE_MAX_UPLOAD_SIZE < fileSize) {
            throw new RuntimeException(this.getMessage("MSG_FILE_SIZE_OVER")); /*파일 용량이 초과하였습니다.*/
        }
    }
    
    private boolean checkRemoveData(String a, String b, String c, String g) throws Exception {
    	boolean chk = false;
    	String[] ratarget = {"표준통관예정보고서"
    			,"(1)신청번호"
    			,"(3)수입자"
    			,"(4)수입화주"
    			,"(5)수출자"
    			,"(6)결제금액"
    			,"(7)국내도착항"
    			,"(8)품질검사기관"
    			,"(9)최초(또는국가)검정기관"
    			,"(10)관할지방"
    			,"(12)품목내역"
    			,"NO"
    			,"품목식별부호"
    			,"(13)발급조건"
    			,"(14)발급번호"
    			,"(16)유효기간"
    			,"PAGE"
    			,"신청번호"
    			,"총수량"};
    	String[] rbtarget = {"세번부호"
    			,"모델규격(형명)"
    			,"수량(단위)"
    			,"제조번호/제조일자"
    			,"(위탁)제조원"
    			,"계약(수탁)제조원"
    			,"BSE기원동물"};
    	String[] rctarget = {"상호"
    			,"수입업허가번호"
    			,"주소"
    			,"상호"
    			,"주소"
    			,"상호"
    			,"주소"};
    	String[] rgtarget = {"표준통관예정보고서"};
    	
    	a = StringHelper.replace(a, " ", "");
    	b = StringHelper.replace(b, " ", "");
    	c = StringHelper.replace(c, " ", "");
    	
    	for(int i = 0; i < ratarget.length; i++) {
    		if(a.toUpperCase().startsWith(ratarget[i])) {
    			return true;
    		}
    	}
    	
    	for(int i = 0; i < rbtarget.length; i++) {
    		if(b.toUpperCase().startsWith(rbtarget[i])) {
    			return true;
    		}
    	}
    	
    	for(int i = 0; i < rctarget.length; i++) {
    		if(c.toUpperCase().startsWith(rctarget[i])) {
    			return true;
    		}
    	}
    	
    	for(int i = 0; i < rgtarget.length; i++) {
    		if(g.toUpperCase().startsWith(rgtarget[i])) {
    			return true;
    		}
    	}
    	
    	return chk;
    }
    
    /**
     * 문자열에서 특정 위치의 품목코드를 추출한다.
     * 
     * @param map 요청 파라메터
     * @param cd  문자열
     * @return
     * @throws Exception
     */
    private String getItemCode(Map map, String cd) throws Exception {
    	String rst = "";
    	String sse = StringHelper.null2void(map.get("MTRIL_PRCSS_DRC")); // 방향(F:앞, B:뒤)
    	int sfl = StringHelper.null2zero(map.get("MTRIL_PRCSS_BEGIN_LC")); // 시작위치
    	int stl = StringHelper.null2zero(map.get("MTRIL_PRCSS_END_LC")); // 종료위치
    	String idc1 = StringHelper.null2void(map.get("MTRIL_DEL_CHRCTR_01")); // 제외할 문자1
    	String idc2 = StringHelper.null2void(map.get("MTRIL_DEL_CHRCTR_02")); // 제외할 문자2
    	String idc3 = StringHelper.null2void(map.get("MTRIL_DEL_CHRCTR_03")); // 제외할 문자3
    	
    	// 문자열 자르기
    	if((stl+sfl) > 0) {
    		sfl = sfl - 1;
    		
    		if(sse.equals("B")) { // 뒤
    			sfl = cd.length() - sfl;
    			stl = cd.length() - stl;
    			
    			if(sfl < 0) sfl = cd.length();
    		    if(stl < 0) stl = 0;
    			
    			rst = StringHelper.toSubString(cd, stl, sfl);
    		} else { // 앞
    			if(sfl < 0) sfl = 0;
    		    if(stl < 0 || stl > cd.length()) stl = cd.length();
    		    
    			rst = StringHelper.toSubString(cd, sfl, stl);
    		}
    	} else {
    		rst = cd;
    	}
    	
    	// 문자열 치환
    	if(!idc1.isEmpty()) rst = StringHelper.replace(rst, idc1, "");
    	if(!idc2.isEmpty()) rst = StringHelper.replace(rst, idc2, "");
    	if(!idc3.isEmpty()) rst = StringHelper.replace(rst, idc3, "");
    	
    	return rst;
    }
}
