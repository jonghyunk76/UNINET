package com.yni.rs.st;

import java.util.List;

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
 * 서버관리 > 서비스 설정
 * 
 * @author YNI-maker
 */
@Controller
public class STR002Ctrl extends YniAbstractController {

	@Resource(name="stR002")
	private STR002 stR002;

	/**
	 * 서비스 목록 조회 화면으로 이동
	 * 
	 * @param request
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/rs/st/stR002_01")
	public ModelAndView stR002_01Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding( "/ST/ST-R002_01", dataMap);
	}

	/**
	 * 서비스 등록 및 수정 화면으로 이동
	 * 
	 * @param request
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/rs/st/stR002_02")
	public ModelAndView stR002_02Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding( "/ST/ST-R002_02", dataMap);
	}
	
	/**
	 * 서비스 조회 및 선택 화면으로 이동
	 * 
	 * @param request
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/rs/st/stR002_03")
	public ModelAndView stR002_03Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding( "/ST/ST-R002_03", dataMap);
	}
	
	/**
	 * 서비스 목록 조회
	 * 
	 * @param req
	 * @param dataMap
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/rs/st/stR002_01/selectServiceList")
	public ModelAndView selectServiceList(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = stR002.selectServiceList(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}
	
	/**
     * 서비스 등록
     * 
     * @param req
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping("/rs/st/stR002_02/insertServiceMst")
    public ModelAndView insertServiceMst(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        
        try {
            int resultCnt = stR002.insertServiceMst(DataMapHelper.getMap(dataMap));
            
            if (resultCnt < 0) {
                success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }

    /**
     * 서비스 수정
     * 
     * @param req
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping("/rs/st/stR002_02/updateServiceMst")
    public ModelAndView updateServiceMst(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        
        try {
            int resultCnt = stR002.updateServiceMst(DataMapHelper.getMap(dataMap));
            
            if (resultCnt < 0) {
                success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
    /**
     * 서비스 삭제
     * 
     * @param req
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping("/rs/st/stR002_02/deleteServiceMst")
    public ModelAndView deleteServiceMst(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        
        try {
            int resultCnt = stR002.deleteServiceMst(DataMapHelper.getMap(dataMap));
            
            if (resultCnt < 0) {
                success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
    /**
	 * 서비스 실행계획 목록 조회
	 * 
	 * @param req
	 * @param dataMap
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/rs/st/stR002_02/selectServicePlanList")
	public ModelAndView selectServicePlanList(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = stR002.selectServicePlanList(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}
    
	/**
	 * 서비스 마스터 리스트 조회
	 * 
	 * @param req
	 * @param dataMap
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/rs/st/stR002_03/selectServiceMstList")
	public ModelAndView selectServiceMstList(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = stR002.selectServiceMstList(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}
	
}
