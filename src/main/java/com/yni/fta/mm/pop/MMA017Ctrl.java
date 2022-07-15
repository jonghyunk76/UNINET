package com.yni.fta.mm.pop;

import java.io.File;
import java.sql.Blob;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yni.fta.fm.sm.SM7001;
import com.yni.fta.mm.cbox.ComboBox;

import kr.yni.frame.Constants;
import kr.yni.frame.collection.DataMap;
import kr.yni.frame.controller.YniAbstractController;
import kr.yni.frame.util.DataMapHelper;
import kr.yni.frame.util.FileUtil;
import kr.yni.frame.util.StringHelper;
import kr.yni.frame.util.SystemHelper;
import kr.yni.frame.web.action.WebAction;

/**
 * 공통 > 한국 확인서 상세 조회 및 제출
 * 
 * @author hanaRyu
 */
@Controller
public class MMA017Ctrl extends YniAbstractController {
    
    private static final Logger logger = LoggerFactory.getLogger(MMA017Ctrl.class);

	@Resource(name="mmA017")
	private MMA017 mmA017;

	@Resource(name = "sm7001")
    private SM7001 sm7001;
	
	@Resource(name="comboBox")
	private ComboBox comboBox;
	
    /**
     * 한국 확인서 리스트 조회 화면으로 이동
     * 
     * @param dataMap DataMap
     * @return ModelAndView
     * @throws Exception
     */
    @RequestMapping(value = "/mm/pop/mmA017_01")
    public ModelAndView mmA017_01Move(DataMap dataMap) throws Exception {
    	Map sysMap = sm7001.selectSysconfigOptionInfo(DataMapHelper.getMap(dataMap));
    	
    	dataMap.put("VENODR_FTA_FIX_YN", StringHelper.null2string(sysMap.get("VENODR_FTA_FIX_YN"), "Y"));
    	dataMap.put("NON_ORGIN_RESN_YN", StringHelper.null2string(sysMap.get("NON_ORGIN_RESN_YN"), "N"));
    	dataMap.put("NON_ORGIN_RESN_REQ_YN", StringHelper.null2string(sysMap.get("NON_ORGIN_RESN_REQ_YN"), "N"));
    	dataMap.put("RVC_REQUIRED_YN", StringHelper.null2string(sysMap.get("RVC_REQUIRED_YN"), "Y"));
    	
        return WebAction.forwarding("/POP/MM-A017_01", dataMap);
    }
    
    /**
     * 확인서 기본정보 및 생산자 조회
     * 
     * @param req HttpServletRequest
     * @param dataMap DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/mm/pop/mmA017_01/selectBasicInfoCaseInsert")
    public ModelAndView selectBasicInfoCaseInsert(HttpServletRequest req, DataMap dataMap) throws Exception {
        Map result = null;
        String message = null;
        
        try {
            result = mmA017.selectBasicInfoCaseInsert(DataMapHelper.getMap(dataMap));
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
    @RequestMapping("/mm/pop/mmA017_01/selectItemListCaseInsert")
    public ModelAndView selectItemListCaseInsert(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = new LinkedList();
        String message = null;
        String nOrigin = null;
        String qOrigin = null;
        String header_res = null;
        String filename = null;
        
        try {
        	Map map = DataMapHelper.getMap(dataMap);
        	
        	Map sysMap = sm7001.selectSysconfigOptionInfo(map);
        	
        	if(sysMap != null) {
	        	nOrigin = StringHelper.null2string(sysMap.get("NON_ORGIN_RESN_YN"), "N");
	        	qOrigin = StringHelper.null2string(sysMap.get("NON_ORGIN_RESN_REQ_YN"), "N");
        	} else {
        		nOrigin = StringHelper.null2string(map.get("NON_ORGIN_RESN_YN"), "N");
	        	qOrigin = StringHelper.null2string(map.get("NON_ORGIN_RESN_REQ_YN"), "N");
        	}
        	
        	map.put("NON_ORGIN_RESN_YN", nOrigin);
        	
        	List itemInsert = mmA017.executeItemListCaseInsert(map);
        	String createUser = StringHelper.null2void(map.get("REGISTED_BY"));
        	header_res = StringHelper.null2void(map.get("HEADER_RES"));
        	filename = StringHelper.null2void(map.get("filename"));
        	Locale locale = SystemHelper.getLocale(StringHelper.null2string(map.get("SESSION_DEFAULT_LANGUAGE"), "KR"));
        	
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
        	
        	if(nOrigin.equals("Y") && !header_res.isEmpty()) {
        		resultList.add(0, itemInsert);
        		
    			Map pmap = new HashMap();
    			
    			pmap.put("COMPANY_CD", StringHelper.null2void(map.get("COMPANY_CD")));
    			pmap.put("CATEGORY_CD", "NONORIGIN_REASON");
    			pmap.put("SESSION_DEFAULT_LANGUAGE", StringHelper.null2void(map.get("SESSION_DEFAULT_LANGUAGE")));
    			pmap.put("headers", header_res);
        		pmap.put("sheetname", this.messageSource.getMessage("(,REDER,),NOMEET, REASN", null, locale));
        		
    			List coMethodList = comboBox.selectStandardCode(pmap);
    			
    			resultList.add(1, coMethodList);
    		} else {
    			if(header_res.isEmpty()) {
    				resultList = itemInsert;
    			} else {
    				resultList.add(0, itemInsert);
    			}
    		}
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        if(!header_res.isEmpty()) {
        	return WebAction.returnExcelView(resultList, filename, message);
        } else {
        	return WebAction.returnDataSet(resultList, message);
        }
    }
    
    /**
     * 확인서 기본정보 및 생산자,서명권자 수정정보 조회
     * 
     * @param req HttpServletRequest
     * @param dataMap DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/mm/pop/mmA017_01/selectBasicInfoCaseUpdate")
    public ModelAndView selectBasicInfoCaseUpdate(HttpServletRequest req, DataMap dataMap) throws Exception {
        Map result = null;
        String message = null;
        
        try {
            result = mmA017.selectBasicInfoCaseUpdate(DataMapHelper.getMap(dataMap));
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
    @RequestMapping("/mm/pop/mmA017_01/selectItemListCaseUpdate")
    public ModelAndView selectItemListCaseUpdate(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
            resultList = mmA017.selectItemListCaseUpdate(DataMapHelper.getMap(dataMap));
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
    @RequestMapping("/mm/pop/mmA017_01/selectBasicInfoCaseRenew")
    public ModelAndView selectBasicInfoCaseRenew(HttpServletRequest req, DataMap dataMap) throws Exception {
        Map result = null;
        String message = null;
        
        try {
            result = mmA017.selectBasicInfoCaseRenew(DataMapHelper.getMap(dataMap));
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
    @SuppressWarnings("unchecked")
	@RequestMapping("/mm/pop/mmA017_01/selectItemListCaseRenew")
    public ModelAndView selectItemListCaseRenew(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = new LinkedList();
        String message = null;
        String nOrigin = null;
        String qOrigin = null;
        String header_res = null;
        String filename = null;
        
        try {
        	Map map = DataMapHelper.getMap(dataMap);
        	
        	Map sysMap = sm7001.selectSysconfigOptionInfo(map);
        	
        	if(sysMap != null) {
	        	nOrigin = StringHelper.null2string(sysMap.get("NON_ORGIN_RESN_YN"), "N");
	        	qOrigin = StringHelper.null2string(sysMap.get("NON_ORGIN_RESN_REQ_YN"), "N");
        	} else {
        		nOrigin = StringHelper.null2string(map.get("NON_ORGIN_RESN_YN"), "N");
	        	qOrigin = StringHelper.null2string(map.get("NON_ORGIN_RESN_REQ_YN"), "N");
        	}
        	
        	map.put("NON_ORGIN_RESN_YN", nOrigin);
        	
        	List itemInsert = mmA017.executeItemListCaseRenew(map);
        	String createUser = StringHelper.null2void(map.get("REGISTED_BY"));
        	header_res = StringHelper.null2void(map.get("HEADER_RES"));
        	filename = StringHelper.null2void(map.get("filename"));
        	Locale locale = SystemHelper.getLocale(StringHelper.null2string(map.get("SESSION_DEFAULT_LANGUAGE"), "KR"));
        	
        	if(nOrigin.equals("Y") && !header_res.isEmpty()) {
        		resultList.add(0, itemInsert);
        		
    			Map pmap = new HashMap();
    			
    			pmap.put("COMPANY_CD", StringHelper.null2void(map.get("COMPANY_CD")));
    			pmap.put("CATEGORY_CD", "NONORIGIN_REASON");
    			pmap.put("SESSION_DEFAULT_LANGUAGE", StringHelper.null2void(map.get("SESSION_DEFAULT_LANGUAGE")));
    			pmap.put("headers", header_res);
        		pmap.put("sheetname", this.messageSource.getMessage("(,REDER,),NOMEET, REASN", null, locale));
        		
    			List coMethodList = comboBox.selectStandardCode(pmap);
    			
    			resultList.add(1, coMethodList);
    		} else {
    			if(header_res.isEmpty()) {
    				resultList = itemInsert;
    			} else {
    				resultList.add(0, itemInsert);
    			}
    		}
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        if(!header_res.isEmpty()) {
        	return WebAction.returnExcelView(resultList, filename, message);
        } else {
        	return WebAction.returnDataSet(resultList, message);
        }
    }
    
    /**
     * 품목별 원산지 리스트 갱신정보 조회
     * 
     * @param req HttpServletRequest
     * @param dataMap DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/mm/pop/mmA017_01/selectItemListCasePeding")
    public ModelAndView selectItemListCasePeding(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
        	Map map = DataMapHelper.getMap(dataMap);
        	
        	resultList = mmA017.executeItemListCasePeding(map);
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
    @RequestMapping("/mm/pop/mmA017_01/selectProofFile")
    public ModelAndView selectProofFile(HttpServletResponse res, HttpServletRequest req, DataMap dataMap) throws Exception  {
    	String message = null;
    	String orgName = null;
    	byte[] data = null;
    	
    	try {
    		Map map = DataMapHelper.getMap(dataMap);
    		Locale locale = SystemHelper.getLocale(StringHelper.null2string(map.get("SESSION_DEFAULT_LANGUAGE"), "KR"));
    		
	    	Map result = mmA017.selectProofFile(map);
	        
	        if (result != null) {
	        	orgName = StringHelper.null2void(result.get("ORIGIN_FILE_NAME"));
	        	
                String db = StringHelper.null2void(this.properties.get("application.db.type"));
                String fileType = StringHelper.null2void(result.get("FILE_TYPE")); // A:서버저장파일, B:DB바이너리 파일)
                String filePath = StringHelper.null2void(result.get("FILE_PATH"));
				
                if(fileType.equals("A")) {
					File file = new File(filePath);
					
					if(file.exists()) {
						data = FileUtil.getBytesFromFile(file);
					} else {
						message = this.messageSource.getMessage("MSG_FILE_NOT_FOUND", null, locale);
					}
				} else if(fileType.equals("B")) {
	                if(db.equals("MSSQL") || db.equals("POSTGRESQL")) {
	                    data = (byte[]) result.get("FILE_BIN");
	                } else {
	                	Blob blob = (Blob) result.get("FILE_BIN");
	                    data = blob.getBytes(1, (int) blob.length());
	                }
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
    @RequestMapping("/mm/pop/mmA017_01/insertRcvCoInfo")
    public ModelAndView insertRcvCoInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        Map resultMap = new HashMap();
        String message = null;
        int rst = 0;
        
        try {
        	Map map = DataMapHelper.getMap(dataMap);
        	map.put("batchData", StringHelper.null2void(map.get("gridData")));
        	
        	// 원산지 정보 등록
        	mmA017.insertRcvCoInfo(map);
        	// 확인서 증빙파일 등록
        	rst = mmA017.insertRcvCoFileInfo(map);
            
            String coNo = StringHelper.null2void(map.get("CO_DOC_NO"));
            
            if(!coNo.isEmpty()) {
            	resultMap.put("CO_DOC_NO", coNo);
            	resultMap.put("PRF_FILE_NAME", StringHelper.null2void(map.get("PRF_FILE_ORG_NAME")));
            }
            //log.debug("Data = " + resultMap.toString());
            if(rst < 0) {
            	String flag = StringHelper.null2void(map.get("flag"));
            	
            	if("update".equals(flag)) { // 수정요청인 경우            		
            		message = this.getMessage("MSG_UNSPECIFIED_ERROR_MSG_REQUEST");
            		resultMap.put("RESULT_FLAG", "-1");
            	} else {
            		// 등록한 원산지 확인서 정보를 삭제한다.
            		mmA017.deleteItemListAll(map);
            		mmA017.deleteRcvMstInfo(map);
            		
            		resultMap.put("RESULT_FLAG", "-1");
            	}
            } else {
            	resultMap.put("RESULT_FLAG", "0");
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
    @RequestMapping("/mm/pop/mmA017_01/updateModifyEndDate")
    public ModelAndView updateModifyEndDate(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        
        try {
        	Map map = DataMapHelper.getMap(dataMap);
            
        	int cnt = mmA017.updateModifyEndDate(map);
            
        	if(cnt < 0) {
        		success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
    /**
     * 원산지 확인서 증빙파일 등록
     * 
     * @param req HttpServletRequest
     * @param dataMap DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/mm/pop/mmA017_01/insertEvCoFiles")
    public ModelAndView insertEvCoFiles(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        
        try {
        	Map map = DataMapHelper.getMap(dataMap);
            
        	int cnt = mmA017.insertEvCoFiles(map);
            
        	if(cnt < 0) {
        		success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
    /**
     * 원산지 확인서 증빙파일 삭제
     * 
     * @param req HttpServletRequest
     * @param dataMap DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/mm/pop/mmA017_01/deleteProofFileInfo")
    public ModelAndView deleteProofFileInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        
        try {
        	Map map = DataMapHelper.getMap(dataMap);
            
        	int cnt = mmA017.deleteProofFileInfo(map);
            
        	if(cnt < 0) {
        		success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
    /**
     * 확인서 증명서/확인서 개별 업데이트(관리자 권한으로만 가능)
     * 
     * @param req HttpServletRequest
     * @param dataMap DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/mm/pop/mmA017_01/updateMgmtEvenFile")
    public ModelAndView updateMgmtEvenFile(HttpServletRequest req, DataMap dataMap) throws Exception {
        Map result = new LinkedHashMap();
        String message = null;
        
        try {
            String fname = mmA017.updateMgmtEvenFile(DataMapHelper.getMap(dataMap));
            
            result.put("PRF_FILE_NAME", fname);
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnMap(result, message);
    }
    
}
