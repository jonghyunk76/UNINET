package com.yni.fta.fm.sm;

import java.sql.Blob;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.yni.frame.Constants;
import kr.yni.frame.collection.DataMap;
import kr.yni.frame.controller.YniAbstractController;
import kr.yni.frame.util.DataMapHelper;
import kr.yni.frame.util.StringHelper;
import kr.yni.frame.web.action.WebAction;

/**
 * 시스템관리 > 회사정보설정   
 * 
 * @author carlos
 *
 */
@Controller
public class SM7001Ctrl extends YniAbstractController {

    @Resource(name = "sm7001")
    private SM7001 sm7001;

    /**
     * 회사정보 리스트 조회 화면으로 이동
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/fm/sm/sm7001_01")
    public ModelAndView sm7001_01Move(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/SM/SM-7001_01", dataMap);
    }

    /**
     * C/O 발급 고객사 관리 화면으로 이동
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/fm/sm/sm7001_02")
    public ModelAndView sm7001_02Move(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/SM/SM-7001_02", dataMap);
    }
    
    /**
     * 거래 고객사 관리 화면으로 이동
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/fm/sm/sm7001_03")
    public ModelAndView sm7001_03Move(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/SM/SM-7001_03", dataMap);
    }
    
    /**
     * 서명권자 관리 화면으로 이동
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/fm/sm/sm7001_04")
    public ModelAndView sm7001_04Move(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/SM/SM-7001_04", dataMap);
    }
    
    /**
     * 회사정보 리스트 조회
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/fm/sm/sm7001_01/selectCompanyList")
    public ModelAndView selectCompanyList(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;

        try {
            resultList = sm7001.selectCompanyList(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }

        return WebAction.returnDataSet(resultList, message);
    }
    
    /**
     * 
     * 회사정보 중복건수 조회
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/fm/sm/sm7001_01/selectCompanyDupCheck")
    public ModelAndView selectCompanyDupCheck(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;

        int nDupCnt = 0;
        try {
            
            Map dupInfo = sm7001.selectCompanyDupCheck(DataMapHelper.getMap(dataMap));
            
            if (dupInfo != null) {
                nDupCnt = StringHelper.null2zero(dupInfo.get("DUPLICATE"));
            }
            
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, "리턴건수:"+nDupCnt);
    }
    
    /**
     * 회사정보 정보 조회
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/fm/sm/sm7001_01/selectCompanyInfo")
    public ModelAndView selectCompanyInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        Map result = null;
        String message = null;
        
        try {
            result = sm7001.selectCompanyInfo(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnMap(result, message);
    }
    
    
    /**
     * 
     * 회사정보 신규 등록
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/fm/sm/sm7001_01/insertCompanyInfo")
    public ModelAndView insertCompanyInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        
        try {
            int resultCnt = sm7001.insertCompanyInfo(DataMapHelper.getMap(dataMap));
            
            if (resultCnt < 0) {
                success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
    /**
     * 
     * 회사정보 수정
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/fm/sm/sm7001_01/updateCompanyInfo")
    public ModelAndView updateCompanyInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        
        try {
            int resultCnt = sm7001.updateCompanyInfo(DataMapHelper.getMap(dataMap));
            
            if (resultCnt < 0) {
                success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
    /**
     * 
     * 회사정보 삭제
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/fm/sm/sm7001_01/deleteCompanyInfo")
    public ModelAndView deleteCompanyInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        
        try {
            int resultCnt = sm7001.deleteCompanyInfo(DataMapHelper.getMap(dataMap));
            
            if (resultCnt < 0) {
                success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
    /**
     * 회사정보 그룹코드 수정
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/fm/sm/sm7001_01/updateCompnayGroupCd")
    public ModelAndView updateCompnayGroupCd(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        
        try {
            int resultCnt = sm7001.updateCompnayGroupCd(DataMapHelper.getMap(dataMap));
            
            if (resultCnt < 0) {
                success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
    /**
     * 회사정보 그룹 리스트 조회
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/fm/sm/sm7001_01/selectCompnayGroupList")
    public ModelAndView selectCompnayGroupList(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;

        try {
            resultList = sm7001.selectCompnayGroupList(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }

        return WebAction.returnDataSet(resultList, message);
    }
    
    /**
     * 
     * C/O 발급 고객사 관리
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/fm/sm/sm7001_02/updateHubCertCustomerInfo")
    public ModelAndView updateHubCertCustomerInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        
        try {
            int resultCnt = sm7001.updateHubCertCustomerInfo(DataMapHelper.getMap(dataMap));
            
            if (resultCnt < 0) {
                success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
    /**
     * 
     * C/O 발급 고객사 삭제
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/fm/sm/sm7001_02/deleteHubCertCustomerInfo")
    public ModelAndView deleteHubCertCustomerInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        
        try {
            int resultCnt = sm7001.deleteHubCertCustomerInfo(DataMapHelper.getMap(dataMap));
            
            if (resultCnt < 0) {
                success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
    /**
     * 
     * 거래 고객사 관리
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/fm/sm/sm7001_03/updateHubSalesCustomerInfo")
    public ModelAndView updateHubSalesCustomerInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        
        try {
            int resultCnt = sm7001.updateHubSalesCustomerInfo(DataMapHelper.getMap(dataMap));
            
            if (resultCnt < 0) {
                success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
    /**
     * 
     * 거래 고객사 삭제
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/fm/sm/sm7001_03/deleteHubSalesCustomerInfo")
    public ModelAndView deleteHubSalesCustomerInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        
        try {
            int resultCnt = sm7001.deleteHubSalesCustomerInfo(DataMapHelper.getMap(dataMap));
            
            if (resultCnt < 0) {
                success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
    /**
     * 
     * 서명권자 관리
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/fm/sm/sm7001_04/updateHubSignatureInfo")
    public ModelAndView updateHubSignatureInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        
        try {
            int resultCnt = sm7001.updateHubSignatureInfo(DataMapHelper.getMap(dataMap));
            
            if (resultCnt < 0) {
                success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
    /**
     * 
     * 서명권자 삭제
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/fm/sm/sm7001_04/deleteHubSignatureInfo")
    public ModelAndView deleteHubSignatureInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        
        try {
            int resultCnt = sm7001.deleteHubSignatureInfo(DataMapHelper.getMap(dataMap));
            
            if (resultCnt < 0) {
                success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
    /**
     * FTA 시스템 옵션 조회
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/fm/sm/sm7001_01/selectSysconfigOptionInfo")
    public ModelAndView selectSysconfigOptionInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        Map result = null;
        String message = null;
        
        try {
            result = sm7001.selectSysconfigOptionInfo(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnMap(result, message);
    }
    
    /**
     * 
     * FTA 시스템 옵션 수정
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/fm/sm/sm7001_01/updateSysconfigOptionInfo")
    public ModelAndView updateSysconfigOptionInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        
        try {
        	Map map = DataMapHelper.getMap(dataMap);
        	
            int resultCnt = sm7001.updateSysconfigOptionInfo(map);
            
            if (resultCnt < 0) {
                success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
    /**
     * 
     * 인증 ID 수정
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/fm/sm/sm7001_01/updateCompanyCertIDInfo")
    public ModelAndView updateCompanyCertIDInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        
        try {
            int resultCnt = sm7001.updateCompanyCertIDInfo(DataMapHelper.getMap(dataMap));
            
            if (resultCnt < 0) {
                success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
    /**
     * C/O 발급 고객사 리스트 조회
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/fm/sm/SM7001_01/selectHubCertCustomerList")
    public ModelAndView selectHubCertCustomerList(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;

        try {
            resultList = sm7001.selectHubCertCustomerList(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }

        return WebAction.returnDataSet(resultList, message);
    }
    
    /**
     * C/O 발급 고객사 리스트 조회
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/fm/sm/SM7001_01/selectHubCertCustomerDtail")
    public ModelAndView selectHubCertCustomerDtail(HttpServletRequest req, DataMap dataMap) throws Exception {
        Map resultMap = null;
        String message = null;

        try {
            List resultList = sm7001.selectHubCertCustomerList(DataMapHelper.getMap(dataMap));
            
            if(resultList.size() > 0) {
            	resultMap = (Map) resultList.get(0);
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }

        return WebAction.returnMap(resultMap, message);
    }
    
    /**
     * 거래 고객사 리스트 조회
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/fm/sm/SM7001_01/selectHubSalesCustomerList")
    public ModelAndView selectHubSalesCustomerList(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;

        try {
            resultList = sm7001.selectHubSalesCustomerList(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }

        return WebAction.returnDataSet(resultList, message);
    }
    
    /**
     * 서명권자 리스트 조회
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/fm/sm/SM7001_01/selectHubSignatureList")
    public ModelAndView selectHubSignatureList(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;

        try {
            resultList = sm7001.selectHubSignatureList(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }

        return WebAction.returnDataSet(resultList, message);
    }
    
    /**
     * 
     * 인증 ID 유효성 체크
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/fm/sm/SM7001_01/selectHubCertID")
    public ModelAndView selectHubCertID(HttpServletRequest req, DataMap dataMap) throws Exception {
        Map result = null;
        String message = null;

        try {
            result = sm7001.selectHubCertID(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnMap(result, message);
    }
    
    /**
     * 회사 CI 및 직인 파일 조회
     * 
     * @param imgType = 1:Logo, 2:Stamp
     * @param req HttpServletRequest
     * @param dataMap DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @ResponseBody
    @RequestMapping("/fm/sm/SM7001_01/selectStempCIImg/{imgType}")
    public byte[] selectStempCIImg(@PathVariable("imgType") String imgType, HttpServletRequest req, DataMap dataMap) throws Exception {
        byte[] data = null;
        String message = null;

        try {
            Map map = DataMapHelper.getMap(dataMap);
            Map result = sm7001.selectStempCIImg(map);

            if (result != null) {
                String db = StringHelper.null2void(this.properties.get("application.db.type"));

                log.debug("Database Type = " + db + ", Image type = " + imgType); 

                if(db.equals("MSSQL") || db.equals("POSTGRESQL")) {
                	if(imgType.equals("1")) {
                		data = (byte[]) result.get("CI_IMAGE");
                	} else if(imgType.equals("2")) {
                		data = (byte[]) result.get("STAMP_IMAGE");
                	}
                } else {
                	if(imgType.equals("1")) {
                		Blob blob = (Blob) result.get("CI_IMAGE");
                		if(blob != null) data = blob.getBytes(1, (int) blob.length());
                	} else if(imgType.equals("2")) {
                		Blob blob = (Blob) result.get("STAMP_IMAGE");
                        if(blob != null) data = blob.getBytes(1, (int) blob.length());
                	}
                	
                	
                }
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }

        return data;
    }
    
}
