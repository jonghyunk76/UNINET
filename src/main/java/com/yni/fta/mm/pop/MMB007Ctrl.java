package com.yni.fta.mm.pop;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.yni.frame.Constants;
import kr.yni.frame.collection.DataMap;
import kr.yni.frame.controller.YniAbstractController;
import kr.yni.frame.util.DataMapHelper;
import kr.yni.frame.util.StringHelper;
import kr.yni.frame.web.action.WebAction;

/**
 * 공통 > 란입력 컨트롤 클래스
 * 
 * @author YNI-maker
 *
 */
@Controller
public class MMB007Ctrl extends YniAbstractController {

	@Resource(name="MMB007")
	private MMB007 MMB007;
	
	/**
	 * 수입 란입력 메인화면으로 이동
	 * 
	 * @param dataMap 요청 파라메터
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/pop/MMB007_01")
	public ModelAndView MMB007_01Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding("/POP/MM-B007_01", dataMap);
	}
	
	/**
	 * 품목지정 화면으로 이동
	 * 
	 * @param dataMap 요청 파라메터
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/pop/MMB007_02")
	public ModelAndView MMB007_02Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding("/POP/MM-B007_02", dataMap);
	}
	
	/**
	 * 수출 란입력 메인화면으로 이동
	 * 
	 * @param dataMap 요청 파라메터
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/pop/MMB007_03")
	public ModelAndView MMB007_03Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding("/POP/MM-B007_03", dataMap);
	}
	
	/**
	 * 수입 란입력 리스트 조회
	 * 
	 * @param req HttpServletRequest
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB007_01/selectImportLneInputList")
	public ModelAndView selectImportLneInputList(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = MMB007.selectImportLneInputList(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}
	
	/**
	 * 수입 란입력 협력사 품목등록 이력 정보 리스트
	 * 
	 * @param req HttpServletRequest
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB007_01/selectImportPrdnmHistList")
	public ModelAndView selectImportPrdnmHistList(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = MMB007.selectImportPrdnmHistList(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}
	
	/**
     * 수입 란입력 정보 업데이트
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/mm/pop/MMB007_01/updateImportLneInputInfo")
    public ModelAndView updateImportLneInputInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
    	boolean success = true;
        String message = null;
        
        try {
        	Map map = DataMapHelper.getMap(dataMap);
            int resultCnt = MMB007.updateImportLneInputInfo(map);
            
            if (resultCnt < 0) {
            	success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
    /**
     * 수입 란입력 > 거래품명 일괄 업데이트
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/mm/pop/MMB007_01/updateImportLneDealName")
    public ModelAndView updateImportLneDealName(HttpServletRequest req, DataMap dataMap) throws Exception {
    	boolean success = true;
        String message = null;
        
        try {
        	Map map = DataMapHelper.getMap(dataMap);
            int resultCnt = MMB007.updateImportLneDealName(map);
            
            if (resultCnt < 0) {
            	success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
    /**
     * 수입 란입력 > 순중량 안분 일괄 업데이트
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/mm/pop/MMB007_01/updateImportLneDealNetwght")
    public ModelAndView updateImportLneDealNetwght(HttpServletRequest req, DataMap dataMap) throws Exception {
    	boolean success = true;
        String message = null;
        
        try {
        	Map map = DataMapHelper.getMap(dataMap);
            int resultCnt = MMB007.updateImportLneDealNetwght(map);
            
            if (resultCnt < 0) {
            	success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
	
    /**
	 * 수출 란입력 리스트 조회
	 * 
	 * @param req HttpServletRequest
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB007_03/selectExportLneInputList")
	public ModelAndView selectExportLneInputList(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = MMB007.selectExportLneInputList(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}
	
	/**
	 * 수출 란입력 협력사 품목등록 이력 정보 리스트
	 * 
	 * @param req HttpServletRequest
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB007_03/selectExportPrdnmHistList")
	public ModelAndView selectExportPrdnmHistList(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = MMB007.selectExportPrdnmHistList(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}
	
	/**
     * 수출 란입력 정보 업데이트
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/mm/pop/MMB007_03/updateExportLneInputInfo")
    public ModelAndView updateExportLneInputInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
    	boolean success = true;
        String message = null;
        
        try {
        	Map map = DataMapHelper.getMap(dataMap);
            int resultCnt = MMB007.updateExportLneInputInfo(map);
            
            if (resultCnt < 0) {
            	success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
    /**
     * 수출 란입력 > 거래품명 일괄 업데이트
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/mm/pop/MMB007_03/updateExportLneDealName")
    public ModelAndView updateExportLneDealName(HttpServletRequest req, DataMap dataMap) throws Exception {
    	boolean success = true;
        String message = null;
        
        try {
        	Map map = DataMapHelper.getMap(dataMap);
            int resultCnt = MMB007.updateExportLneDealName(map);
            
            if (resultCnt < 0) {
            	success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
    /**
     * 수출 란입력 > 순중량 안분 일괄 업데이트
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/mm/pop/MMB007_03/updateExportLneDealNetwght")
    public ModelAndView updateExportLneDealNetwght(HttpServletRequest req, DataMap dataMap) throws Exception {
    	boolean success = true;
        String message = null;
        
        try {
        	Map map = DataMapHelper.getMap(dataMap);
            int resultCnt = MMB007.updateExportLneDealNetwght(map);
            
            if (resultCnt < 0) {
            	success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
}