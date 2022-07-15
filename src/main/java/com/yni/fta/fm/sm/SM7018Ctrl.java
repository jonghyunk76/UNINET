package com.yni.fta.fm.sm;

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
 * 운영관리 > 마감현황 및 관리
 * 
 * @author YNI-maker
 *
 */
@Controller
public class SM7018Ctrl extends YniAbstractController {

	@Resource(name="sm7018")
	private SM7018 sm7018;
	
	/**
	 * 사용자 로그 화면으로 이동
	 * 
	 * @param dataMap 요청 파라메터
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/fm/sm/sm7018_01")
	public ModelAndView sm7018_01Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding("/SM/SM-7018_01", dataMap);
	}
	
	/**
	 * 사용자 로그 목록 조회
	 * 
	 * @param req
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
    @RequestMapping("/fm/sm/sm7018_01/selectUseLogList")
    public ModelAndView selectUseLogList(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;

        try {
            resultList = sm7018.selectUseLogList(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }

        return WebAction.returnDataSet(resultList, message);
    }
    
    /**
	 * 사용자 로그 통계정보 조회
	 * 
	 * @param req
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
    @RequestMapping("/fm/sm/sm7018_01/selectUseLogStatusList")
    public ModelAndView selectUseLogStatusList(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;

        try {
            resultList = sm7018.selectUseLogStatusList(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }

        return WebAction.returnDataSet(resultList, message);
    }
    
}