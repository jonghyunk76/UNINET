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
 * 공통 > 협정적용 신청서 컨트롤 클래스
 * 
 * @author YNI-maker
 *
 */
@Controller
public class MMB004Ctrl extends YniAbstractController {

	@Resource(name="MMB004")
	private MMB004 MMB004;
	
	/**
	 * 협정적용 신청서 메인화면으로 이동
	 * 
	 * @param dataMap 요청 파라메터
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/pop/MMB004_01")
	public ModelAndView MMB004_01Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding("/POP/MM-B004_01", dataMap);
	}
	
	/**
	 * 란자료 선택 화면으로 이동
	 * 
	 * @param dataMap 요청 파라메터
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/pop/MMB004_02")
	public ModelAndView MMB004_02Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding("/POP/MM-B004_02", dataMap);
	}
	
	/**
	 * 협정적용 신청서 정보 조회
	 * 
	 * @param req HttpServletRequest
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB004_01/selectImportCvtaApcPdoList")
	public ModelAndView selectImportCvtaApcPdoList(HttpServletRequest req, DataMap dataMap) throws Exception {
		Map resultMap = null;
		String message = null;

		try {
			resultMap = MMB004.selectImportCvtaApcPdoList(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnMap(resultMap, message);
	}
	
	/**
	 * 협정적용 신청서 수정
	 * 
	 * @param req HttpServletRequest
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB004_01/updateImportCvtaApcPdoInfo")
	public ModelAndView updateImportCvtaApcPdoInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
		boolean succes = true;
		String message = null;

		try {
			int cnt = MMB004.updateImportCvtaApcPdoInfo(DataMapHelper.getMap(dataMap));
			
			if(cnt < 0) {
				succes = false;
			}
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.resultJsonMsg(succes, message);
	}
	
	/**
	 * 협정적용 신청서 란 리스트 조회
	 * 
	 * @param req HttpServletRequest
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB004_01/selectImportCvtaDclrtLneList")
	public ModelAndView selectImportCvtaDclrtLneList(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = MMB004.selectImportCvtaDclrtLneList(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}
	
	/**
	 * 협정적용 신청서 란정보 일괄 수정
	 * 
	 * @param req HttpServletRequest
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB004_01/updateBatchImportCvtaDclrtLne")
	public ModelAndView updateBatchImportCvtaDclrtLne(HttpServletRequest req, DataMap dataMap) throws Exception {
		boolean succes = true;
		String message = null;

		try {
			int cnt = MMB004.updateBatchImportCvtaDclrtLne(DataMapHelper.getMap(dataMap));
			
			if(cnt < 0) {
				succes = false;
			}
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.resultJsonMsg(succes, message);
	}
	
	/**
	 * 협정적용 신청서 란 상세정보 조회
	 * 
	 * @param req HttpServletRequest
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB004_01/selectImportCvtaApcPdoLneList")
	public ModelAndView selectImportCvtaApcPdoLneList(HttpServletRequest req, DataMap dataMap) throws Exception {
		Map resultMap = null;
		String message = null;

		try {
			resultMap = MMB004.selectImportCvtaApcPdoLneList(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnMap(resultMap, message);
	}
	
	/**
	 * 협정적용 신청서 란정보 수정
	 * 
	 * @param req HttpServletRequest
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB004_01/updateImportCvtaApcPdoLneInfo")
	public ModelAndView updateImportCvtaApcPdoLneInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
		boolean succes = true;
		String message = null;

		try {
			int cnt = MMB004.updateImportCvtaApcPdoLneInfo(DataMapHelper.getMap(dataMap));
			
			if(cnt < 0) {
				succes = false;
			}
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.resultJsonMsg(succes, message);
	}
	
	/**
	 * 협정적용 신청서 란정보 삭제
	 * 
	 * @param req HttpServletRequest
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB004_01/deleteImportCvtaApcPdoLneInfo")
	public ModelAndView deleteImportCvtaApcPdoLneInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
		boolean succes = true;
		String message = null;

		try {
			int cnt = MMB004.deleteImportCvtaApcPdoLneInfo(DataMapHelper.getMap(dataMap));
			
			if(cnt < 0) {
				succes = false;
			}
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.resultJsonMsg(succes, message);
	}
	
	/**
	 * 협정적용 신청서 원산지 규격 리스트 조회
	 * 
	 * @param req HttpServletRequest
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB004_01/selectImpCvtaApcPdoStardList")
	public ModelAndView selectImpCvtaApcPdoStardList(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = MMB004.selectImpCvtaApcPdoStardList(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}
	
	/**
	 * 협정적용 신청서 원산지 규격 사용량 합계 조회
	 * 
	 * @param req HttpServletRequest
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB004_01/selectImpCvtaApcPdoStardTotInfo")
	public ModelAndView selectImpCvtaApcPdoStardTotInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
		Map resultMap = null;
		String message = null;

		try {
			resultMap = MMB004.selectImpCvtaApcPdoStardTotInfo(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnMap(resultMap, message);
	}
	
	/**
	 * 협정적용 신청서 원산지 규격 수정
	 * 
	 * @param req HttpServletRequest
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB004_01/updateImpCvtaApcPdoStardInfo")
	public ModelAndView updateImpCvtaApcPdoStardInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
		boolean succes = true;
		String message = null;

		try {
			int cnt = MMB004.updateImpCvtaApcPdoStardInfo(DataMapHelper.getMap(dataMap));
			
			if(cnt < 0) {
				succes = false;
			}
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.resultJsonMsg(succes, message);
	}
	
	/**
	 * 협정적용 신청서 원산지 규격 삭제
	 * 
	 * @param req HttpServletRequest
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB004_01/deleteImpCvtaApcPdoStardInfo")
	public ModelAndView deleteImpCvtaApcPdoStardInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
		boolean succes = true;
		String message = null;

		try {
			int cnt = MMB004.deleteImpCvtaApcPdoStardInfo(DataMapHelper.getMap(dataMap));
			
			if(cnt < 0) {
				succes = false;
			}
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.resultJsonMsg(succes, message);
	}
	
}