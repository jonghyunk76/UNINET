package com.yni.fta.mm.pop;

import java.sql.Blob;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.yni.frame.Constants;
import kr.yni.frame.collection.DataMap;
import kr.yni.frame.controller.YniAbstractController;
import kr.yni.frame.util.DataMapHelper;
import kr.yni.frame.util.ExcelUtil;
import kr.yni.frame.util.StringHelper;
import kr.yni.frame.web.action.WebAction;

/**
 * 공통 > 멕시코 확인서 상세 조회 및 제출
 * 
 * @author hanaRyu
 */
@Controller
public class MMA019Ctrl extends YniAbstractController {
    
    private static final Logger logger = LoggerFactory.getLogger(MMA019Ctrl.class);

	@Resource(name="mmA019")
	private MMA019 mmA019;
	
    /**
     * 확인서 리스트 조회 화면으로 이동
     * 
     * @param dataMap DataMap
     * @return ModelAndView
     * @throws Exception
     */
    @RequestMapping(value = "/mm/pop/mmA019_01")
    public ModelAndView mmA019_01Move(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/POP/MM-A019_01", dataMap);
    }

    /**
     * 확인서 기본정보 및 생산자 조회
     * 
     * @param req HttpServletRequest
     * @param dataMap DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/mm/pop/mmA019_01/selectBasicInfoCaseInsert")
    public ModelAndView selectBasicInfoCaseInsert(HttpServletRequest req, DataMap dataMap) throws Exception {
        Map result = null;
        String message = null;
        
        try {
            result = mmA019.selectBasicInfoCaseInsert(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnMap(result, message);
    }
    
    /**
     * 품목별 원산지 리스트 조회
     * 
     * @param req HttpServletRequest
     * @param dataMap DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/mm/pop/mmA019_01/selectItemListCaseInsert")
    public ModelAndView selectItemListCaseInsert(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
        	Map map = DataMapHelper.getMap(dataMap);
        	String header = StringHelper.null2void(map.get("headers"));
        	String fileName = StringHelper.null2string(map.get("filename"), this.getMessage("ORG,REGER"));
        	String sheetName = StringHelper.null2string(map.get("sheetname"), "Sheet");
        	String createUser = StringHelper.null2void(map.get("REGISTED_BY"));
        	
        	resultList = mmA019.executeItemListCaseInsert(map);
        	
        	if(createUser.equals("2")) { // 등록하는 사용자가 내부 담당자인 경우
        		for(int i = 0; i < resultList.size(); i++) {
        			Map itemMap = (Map) resultList.get(i);
        			
        			String psrs = StringHelper.null2void(itemMap.get("RULE_CODES"));
        			String ruleCont = StringHelper.null2void(itemMap.get("RULE_CONTENTS"));
        			
        			if(ruleCont.isEmpty()) {
	        			if(!psrs.isEmpty()) {
	        				String[] psr = psrs.split(",");
	        				
	        				if(psr.length > 0) {
	        					itemMap.put("RULE_CONTENTS", psr[0].trim());
	        				}
	        			}
        			}
        		}
        	}
        	
        	if(!header.isEmpty()) {
        		// 엑셀 해더 변수 선언
        		if(StringHelper.null2void(map.get("FTA_GROUP_CD")).equals("MN")) {
	        		String excelHead[] = {
							"Status", "Part NO.", "Part Desc", "Agreement", "Agr. Description", "Group", "HS Code",
							"NALADISA2002", "* PSR", "* Originating", "* Non-Oringating", "Declaration Date", "Valid From",
							"Valid To", "Originating Ratio (%)", "* Trace Value", "Currency", "* Sales Price", "Currency", "Reference(type of Origin criteria)"
	        		    }; // 엑셀 1행 해드값
	
	        		ExcelUtil.setHeaderInfo(excelHead, resultList, fileName, sheetName);  // Header, DB 조회 List, 엑셀 파일명, 엑셀 시트
        		} else if(StringHelper.null2void(map.get("FTA_GROUP_CD")).equals("ML")) {
        			String excelHead[] = {
							"Status", "Part NO.", "Part Desc", "Agreement", "Agr. Description", "Group", "HS Code",
							"NALADISA2002", "* PSR", "* Originating", "* Non-Oringating", "Declaration Date", "Valid From",
							"Valid To", "Originating Ratio (%)", "Trace Value", "Currency", "* Sales Price", "Currency", "Reference(type of Origin criteria)"
	        		    }; // 엑셀 1행 해드값
	
	        		ExcelUtil.setHeaderInfo(excelHead, resultList, fileName, sheetName);  // Header, DB 조회 List, 엑셀 파일명, 엑셀 시트
        		}
        	}
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnDataSet(resultList, message);
    }
    
    /**
     * 확인서 기본정보 및 생산자,서명권자 수정정보 조회
     * 
     * @param req HttpServletRequest
     * @param dataMap DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/mm/pop/mmA019_01/selectBasicInfoCaseUpdate")
    public ModelAndView selectBasicInfoCaseUpdate(HttpServletRequest req, DataMap dataMap) throws Exception {
        Map result = null;
        String message = null;
        
        try {
            result = mmA019.selectBasicInfoCaseUpdate(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnMap(result, message);
    }
    
    /**
     * 품목별 원산지 내역 수정정보 조회
     * 
     * @param req HttpServletRequest
     * @param dataMap DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/mm/pop/mmA019_01/selectItemListCaseUpdate")
    public ModelAndView selectItemListCaseUpdate(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
            resultList = mmA019.selectItemListCaseUpdate(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnDataSet(resultList, message);
    }
    
    /**
     * 확인서 기본정보 및 생산자 갱신정보 조회
     * 
     * @param req HttpServletRequest
     * @param dataMap DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/mm/pop/mmA019_01/selectBasicInfoCaseRenew")
    public ModelAndView selectBasicInfoCaseRenew(HttpServletRequest req, DataMap dataMap) throws Exception {
        Map result = null;
        String message = null;
        
        try {
            result = mmA019.selectBasicInfoCaseRenew(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnMap(result, message);
    }
    
    /**
     * 품목별 원산지 리스트 갱신정보 조회
     * 
     * @param req HttpServletRequest
     * @param dataMap DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/mm/pop/mmA019_01/selectItemListCaseRenew")
    public ModelAndView selectItemListCaseRenew(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
        	Map map = DataMapHelper.getMap(dataMap);
        	String header = StringHelper.null2void(map.get("headers"));
        	String fileName = StringHelper.null2string(map.get("filename"), this.getMessage("ORG,REGER"));
        	String sheetName = StringHelper.null2string(map.get("sheetname"), "Sheet");
        	
        	resultList = mmA019.executeItemListCaseRenew(map);
        	
        	if(!header.isEmpty()) {
        		if(StringHelper.null2void(map.get("FTA_GROUP_CD")).equals("MN")) {
	        		String excelHead[] = {
							"Status", "Part NO.", "Part Desc", "Agreement", "Agr. Description", "Group", "HS Code",
							"NALADISA2002", "* PSR", "* Originating", "* Non-Oringating", "Declaration Date", "Valid From",
							"Valid To", "Originating Ratio (%)", "* Trace Value", "Currency", "* Sales Price", "Currency", "Reference(type of Origin criteria)"
	        		    }; // 엑셀 1행 해드값
	
	        		ExcelUtil.setHeaderInfo(excelHead, resultList, fileName, sheetName);  // Header, DB 조회 List, 엑셀 파일명, 엑셀 시트
        		} else if(StringHelper.null2void(map.get("FTA_GROUP_CD")).equals("ML")) {
        			String excelHead[] = {
							"Status", "Part NO.", "Part Desc", "Agreement", "Agr. Description", "Group", "HS Code",
							"NALADISA2002", "* PSR", "* Originating", "* Non-Oringating", "Declaration Date", "Valid From",
							"Valid To", "Originating Ratio (%)", "Trace Value", "Currency", "* Sales Price", "Currency", "Reference(type of Origin criteria)"
	        		    }; // 엑셀 1행 해드값
	
	        		ExcelUtil.setHeaderInfo(excelHead, resultList, fileName, sheetName);  // Header, DB 조회 List, 엑셀 파일명, 엑셀 시트
        		}
        	}
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnDataSet(resultList, message);
    }
    
    /**
     * 품목별 원산지 내역 미결정보 조회
     * 
     * @param req HttpServletRequest
     * @param dataMap DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/mm/pop/mmA019_01/selectItemListCasePeding")
    public ModelAndView selectItemListCasePeding(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
            resultList = mmA019.executeItemListCasePeding(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnDataSet(resultList, message);
    }
    
    /**
     * 증빙파일 불러오기
     * 
     * @param res HttpServletResponse
     * @param req HttpServletRequest
     * @param dataMap DataMap
     * @exception Exception
     * */
    @SuppressWarnings("rawtypes")
    @RequestMapping("/mm/pop/mmA019_01/selectProofFile")
    public ModelAndView selectProofFile(HttpServletResponse res, HttpServletRequest req, DataMap dataMap) throws Exception  {
    	String message = null;
    	String orgName = null;
    	byte[] data = null;
    	
    	try {
	    	Map result = mmA019.selectProofFile(DataMapHelper.getMap(dataMap));
	        
	        if (result != null) {
	        	orgName = (String)result.get("ORIGIN_FILE_NAME");
	        	
                String db = StringHelper.null2void(this.properties.get("application.db.type"));

                if(db.equals("MSSQL") || db.equals("POSTGRESQL")) {
                    data = (byte[]) result.get("FILE_BIN");
                } else {
                	Blob blob = (Blob) result.get("FILE_BIN");
                    data = blob.getBytes(1, (int) blob.length());
                }
            }
    	} catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnFileView(data, orgName, message);
    }
    
    /**
     * 원산지 확인서 정보 신규 등록
     * 
     * @param req HttpServletRequest
     * @param dataMap DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/mm/pop/mmA019_01/insertRcvCoInfo")
    public ModelAndView insertRcvCoInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        Map resultMap = new HashMap();
        String message = null;
        int rst = 0;
        
        try {
        	Map map = DataMapHelper.getMap(dataMap);
            
        	// 원산지 정보 등록
        	mmA019.insertRcvCoInfo(map);
        	// 증빙파일을 등록한다.
        	rst = mmA019.insertRcvCoFileInfo(map);
            
            String coNo = StringHelper.null2void(map.get("CO_DOC_NO"));
            
            if(!coNo.isEmpty()) {
            	resultMap.put("CO_DOC_NO", coNo);
            	resultMap.put("PRF_FILE_NAME", StringHelper.null2void(map.get("PRF_FILE_ORG_NAME")));
            }
            
            if(rst < 0) {
            	message = this.getMessage("MSG_UNSPECIFIED_ERROR_MSG_REQUEST");
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnMap(resultMap, message);
    }
    
    /**
     * 원산지 수정을 위해 기존 포괄만료기간을 업데이트함
     * 
     * @param req HttpServletRequest
     * @param dataMap DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/mm/pop/mmA019_01/updateModifyEndDate")
    public ModelAndView updateModifyEndDate(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        
        try {
        	Map map = DataMapHelper.getMap(dataMap);
            
        	int cnt = mmA019.updateModifyEndDate(map);
            
        	if(cnt < 0) {
        		success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
}
