package com.yni.fta.mm.pop.impl;

import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

//import com.yni.fta.common.tools.IreportSupporter;
//import com.yni.fta.common.tools.JasperReportsPrintView;
import com.yni.fta.mm.pop.MMA021;

import kr.yni.frame.Constants;
import kr.yni.frame.service.YniAbstractService;
import kr.yni.frame.util.DateHelper;
import kr.yni.frame.util.FileUtil;
import kr.yni.frame.util.StringHelper;
import kr.yni.frame.util.SystemHelper;

/**
 * 공통 > 증명서 변경요청
 * 
 * @author YNI-maker
 *
 */
@Service("mmA021")
public class MMA021Impl extends YniAbstractService implements MMA021 {
	
	@Resource(name="mmA021Dao")
	private MMA021Dao mmA021Dao;
	
	/**
     * 증명서 변경요청 리스트 조회
     * 
     * @param map Map
     * @return List
     * @throws Exception
     */
	public List selectMainList(Map map) throws Exception {
		return mmA021Dao.selectMainList(map);
	}
	
	/**
	 * 증명서 변경요청 사유 저장
	 * 
	 * @param map Map
	 * @return int
	 * @throws Exception
	 */
	public int updateReqReason(Map map) throws Exception {
	    mmA021Dao.insertReqHistory(map);
	    return mmA021Dao.updateReqReason(map);
	}
	
	/**
     * 증명서 변경 원산지 상세내역
     * 
     * @param map Map
     * @return List
     * @throws Exception
     */
	public List selectRecieveCoDetail(Map map) throws Exception {
		return mmA021Dao.selectRecieveCoDetail(map);
	}
	
	/**
     * 증빙파일 목록 조회
     * 
     * @param map Map
     * @return List
     * @throws Exception
     */
	public List selectEvdcFileList(Map map) throws Exception {
		List coFileList = mmA021Dao.selectEvdcDownloadFileList(map);
		
		for(int i = 0; i < coFileList.size(); i++) {
			Map result = (Map) coFileList.get(i);
			
			result.put("FILE_BIN", null);
		}
		
		return coFileList;
	}
	
	/**
     * 증빙파일 다운로드
     * 
     * @param map Map
     * @return List
     * @throws Exception
     */
	public List selectEvdcDownloadFile(Map map) throws Exception {
		List coFileList = mmA021Dao.selectEvdcDownloadFileList(map);
		
//		for(int i = 0; i < coFileList.size(); i++) {
//			Map result = (Map) coFileList.get(i);
//			
//			String fileName = StringHelper.null2void(result.get("ORIGIN_FILE_NAME"));
//			String certifyNo = StringHelper.null2void(result.get("CO_DOC_NO"));
//			String fileType = StringHelper.null2void(result.get("FILE_TYPE")); // A:서버저장파일, B:DB바이너리 파일
//			String docTypeName = StringHelper.null2void(result.get("EV_DOC_TYPE_NAME"));
//			String docType = StringHelper.null2void(result.get("EV_DOC_TYPE"));
//			boolean dataFileCheck = false;
//			
//			// 파일명이 없는 경우 증빙파일이 미등록된 건으로 보고, 과거 등록 이력이나 현재 등록된 상태를 비교해서 확인해서 증빙파일을 자동으로 생성한다.
//			if(fileType.equals("A")) {
//				String filePath = StringHelper.null2void(result.get("FILE_PATH"));
//				
//				if(!filePath.isEmpty()) {
//					File file = new File(filePath);
//					
//					if(file.exists()) {
//						dataFileCheck = true;
//						
//						result.put("FILE_BIN", FileUtil.getBytesFromFile(file));
//						result.put("FILE_OUT_TYPE", "BYTE");
//					}
//				}
//			} else if(fileType.equals("B")) {
//				if(result.get("FILE_BIN") != null && result.containsKey("FILE_BIN")) {
//					result.put("FILE_OUT_TYPE", "COLUMN");
//					
//					dataFileCheck = true;
//				}
//			}
//			
//			if(!docType.equals("01")) dataFileCheck = true;
//			
//			if(dataFileCheck) {
//				String coFileName = "";
//				
//				if(fileName.isEmpty()) {
//					coFileName = docTypeName + "-" + certifyNo + "_" + DateHelper.getCurrentDateAsString() + ".pdf";
//				} else {
//					coFileName = docTypeName + "-" + fileName;
//				}
//				
//				result.put("ORIGIN_FILE_NAME", coFileName);
//		    }else {
//				IreportSupporter operator = new IreportSupporter();
//	            JasperReportsPrintView view = new JasperReportsPrintView();
//	            ModelMap parameter = new ModelMap();
//	            
//				try {
//		            // 파라메터 생성
//		            parameter.put("REPORT_PRINT_TYPE", "F"); // 보고서 출력 형태(E:Empty, F:file, P:page)
//		            parameter.put("REPORT_TYPE", "pdf"); // 레포트 타입
//		            parameter.put("DOC_FILE_NAME", "VENDOR_COVER_ver.1.2"); // 레포트 파일명 : 여러 파일을 동시에 보여줄 경우 구분자(^)로 파일명을 만들 것
//		            
//		            // 레포트 공통 파라메터
//		            parameter.put("P_COMPANY_CODE", StringHelper.null2void(result.get("COMPANY_CD")));
//		            parameter.put("P_COO_CERTIFY_NO", certifyNo);
//		            parameter.put("P_VENDOR_CODE", StringHelper.null2void(result.get("VENDOR_CD")));
//		            parameter.put("P_SERVER_URL", StringHelper.null2void(result.get("MODIFY_REQ"))); // 수정요청번호
//		            
//		            view = operator.getJasperPrintOutputStream(parameter);
//		            
//		            result.put("ORIGIN_FILE_NAME", docTypeName + "-" + certifyNo + "_" + DateHelper.getCurrentDateAsString() + ".pdf");
//		            result.put("FILE_BIN", view.getIReportFileStream());
//		            result.put("FILE_OUT_TYPE", "BYTE");
//				} catch(Exception e) {
//					continue;
//				}
//			} 
//		}
		
		return coFileList;
	}
	
}
