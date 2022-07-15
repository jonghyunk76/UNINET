package com.yni.fta.mm.pop;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.yni.frame.Constants;
import kr.yni.frame.collection.DataMap;
import kr.yni.frame.controller.YniAbstractController;
import kr.yni.frame.util.DataMapHelper;
import kr.yni.frame.util.StringHelper;
import kr.yni.frame.web.action.WebAction;

/**
 * 공통 > 표준통관예정 자료 업로드 컨트롤 클래스
 * 
 * @author YNI-maker
 *
 */
@Controller
public class MMB008Ctrl extends YniAbstractController {

	@Resource(name="MMB008")
	private MMB008 MMB008;
	
	/**
	 * 표준통관예정 자료 등록 메인화면으로 이동
	 * 
	 * @param dataMap 요청 파라메터
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/pop/MMB008_01")
	public ModelAndView MMB008_01Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding("/POP/MM-B008_01", dataMap);
	}
	
	/**
	 * 표준통관예정 자료 리스트 조회
	 * 
	 * @param req HttpServletRequest
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB008_01/selectStandardEntrUploList")
	public ModelAndView selectStandardEntrUploList(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = MMB008.selectStandardEntrUploList(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}
	
	/**
     * 표준통관예정 자료 리스트 일괄 수정
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exceptio
     */
	@RequestMapping("/mm/pop/MMB008_01/updateStandardEntrUplo")
    public ModelAndView updateStandardEntrUplo(HttpServletRequest req, DataMap dataMap) throws Exception {
        int resultCnt = 0;
        boolean success = true;
        String message = null;
        
        try {
            resultCnt = MMB008.updateStandardEntrUplo(DataMapHelper.getMap(dataMap));
            
            if (resultCnt < 0) {
                success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
	
	/**
     * 표준통관예정 자료 리스트 일괄 삭제
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exceptio
     */
	@RequestMapping("/mm/pop/MMB008_01/deleteStandardEntrUplo")
    public ModelAndView deleteStandardEntrUplo(HttpServletRequest req, DataMap dataMap) throws Exception {
        int resultCnt = 0;
        boolean success = true;
        String message = null;
        
        try {
            resultCnt = MMB008.deleteStandardEntrUplo(DataMapHelper.getMap(dataMap));
            
            if (resultCnt < 0) {
                success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
	/**
     * Excel Upload & Save
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exceptio
     */
    @RequestMapping(value = "/mm/pop/MMB008_01/saveExcelSample", method = RequestMethod.POST)
    public ModelAndView saveExcelSample(HttpServletRequest req, DataMap dataMap) throws Exception {
        int resultCnt = 0;
        boolean success = true;
        String message = null;
        
        try {
            resultCnt = MMB008.saveExcelSample(DataMapHelper.getMap(dataMap));
            
            if (resultCnt < 0) {
                success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
    /**
     * 표통과 수입업로드 자료 매칭 수행
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exceptio
     */
	@RequestMapping("/mm/pop/MMB008_01/updateStandardEntrUploadNo")
    public ModelAndView updateStandardEntrUploadNo(HttpServletRequest req, DataMap dataMap) throws Exception {
        int resultCnt = 0;
        boolean success = true;
        String message = null;
        
        try {
            resultCnt = MMB008.updateStandardEntrUploadNo(DataMapHelper.getMap(dataMap));
            
            if (resultCnt < 0) {
                success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
}