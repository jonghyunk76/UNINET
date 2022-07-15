package com.yni.fta.mm.pop;

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
 * 공통 > 가입신청서 작성
 * 
 * @author YNI-Maker
 *
 */
@Controller
public class MMA030Ctrl extends YniAbstractController {
	
	@Resource(name="mma030")
	private MMA030 mma030;
	
    /**
     * 도움말 화면으로 이동
     * 
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/mm/noses/mmA030_01")
    public ModelAndView mmA028_01Move(DataMap dataMap) throws Exception {
    	return WebAction.forwarding("/POP/MM-A030_01", dataMap);
    }
    
    /**
	 * 신규가입정보 등록여부 체크
	 * 
	 * @param req
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/mm/noses/mmA030_01/selectJoinCompanyNo")
	public ModelAndView selectJoinCompanyNo(HttpServletRequest req, DataMap dataMap) throws Exception {
		Map resultMap = null;
		String message = null;

		try {
			resultMap = mma030.selectJoinCompanyNo(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnMap(resultMap, message);
	}
    
	/**
	 * 신규가입정보 조회
	 * 
	 * @param req
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/mm/noses/mmA030_01/selectJoinCompanyInfo")
	public ModelAndView selectJoinCompanyInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
		Map resultMap = null;
		String message = null;

		try {
			resultMap = mma030.selectJoinCompanyInfo(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnMap(resultMap, message);
	}
	
	/**
	 * 신규가입정보 등록
	 * 
	 * @param req
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/mm/noses/mmA030_01/insertJoinCompanyInfo")
	public ModelAndView insertJoinCompanyInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
		boolean rst = true;
		String message = null;

		try {
			int cnt = mma030.insertJoinCompanyInfo(DataMapHelper.getMap(dataMap));
			
			if(cnt < 0) {
				rst = false;
			}
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.resultJsonMsg(rst, message);
	}
	
	/**
	 * 신규가입정보 수정
	 * 
	 * @param req
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/mm/noses/mmA030_01/updateJoinCompanyInfo")
	public ModelAndView updateJoinCompanyInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
		boolean rst = true;
		String message = null;

		try {
			int cnt = mma030.updateJoinCompanyInfo(DataMapHelper.getMap(dataMap));
			
			if(cnt < 0) {
				rst = false;
			}
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.resultJsonMsg(rst, message);
	}
    
}
