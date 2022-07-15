package com.yni.fta.mm.pop;

import java.util.HashMap;
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
 * 공통 > 인보이스,인보이스 항목 업로드 컨트롤 클래스
 * 
 * @author YNI-maker
 *
 */
@Controller
public class MMB006Ctrl extends YniAbstractController {

	@Resource(name="MMB006")
	private MMB006 MMB006;
	
	@Resource(name="MMB008")
	private MMB008 MMB008;
	
	/**
	 * 인보이스/인보이스 항목 메인화면으로 이동
	 * 
	 * @param dataMap 요청 파라메터
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/pop/MMB006_01")
	public ModelAndView MMB006_01Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding("/POP/MM-B006_01", dataMap);
	}
	
	/**
	 * 인보이스 업로드 화면으로 이동
	 * 
	 * @param dataMap 요청 파라메터
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/pop/MMB006_02")
	public ModelAndView MMB006_02Move(DataMap dataMap) throws Exception {
		try {
			Map map = DataMapHelper.getMap(dataMap);
			Map vmap = MMB006.selectMaxInvUpSetupNo(map);
			
			if(vmap != null && vmap.size() > 0) {
				if(!StringHelper.null2void(vmap.get("FORM_SETUP_SN")).isEmpty()) {
				    map.put("FORM_SETUP_SN", vmap.get("FORM_SETUP_SN"));
				    map.put("FORM_SETUP_NM", vmap.get("FORM_SETUP_NM"));
				    map.put("SHEET_NO", vmap.get("SHEET_NO"));
				    map.put("BEGIN_ROW", vmap.get("BEGIN_ROW"));
				}
			}
		} catch(Exception e) {
			if(log.isErrorEnabled()) log.error(e);
		}
		
		return WebAction.forwarding("/POP/MM-B006_02", dataMap);
	}
	
	/**
	 * 인보이스 항목 업로드 화면으로 이동
	 * 
	 * @param dataMap 요청 파라메터
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/pop/MMB006_03")
	public ModelAndView MMB006_03Move(DataMap dataMap) throws Exception {
		try {
			Map map = DataMapHelper.getMap(dataMap);
			Map vmap = MMB006.selectMaxInvUpSetupNo(map);
			
			if(!StringHelper.null2void(vmap.get("FORM_SETUP_SN")).isEmpty()) {
			    map.put("FORM_SETUP_SN", vmap.get("FORM_SETUP_SN"));
			    map.put("FORM_SETUP_NM", vmap.get("FORM_SETUP_NM"));
			    map.put("SHEET_NO", vmap.get("SHEET_NO"));
			    map.put("BEGIN_ROW", vmap.get("BEGIN_ROW"));
			}
		} catch(Exception e) {
			if(log.isErrorEnabled()) log.error(e);
		}
		
		return WebAction.forwarding("/POP/MM-B006_03", dataMap);
	}
	
	/**
	 * 타업체 설정값 조회
	 * 
	 * @param dataMap 요청 파라메터
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/pop/MMB006_04")
	public ModelAndView MMB006_04Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding("/POP/MM-B006_04", dataMap);
	}
	
	/**
	 * 인보이스 업로드 설정정보 조회
	 * 
	 * @param req HttpServletRequest
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB006_01/selectInvUpSetupInfo")
	public ModelAndView selectInvUpSetupInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
		Map resultMap = null;
		String message = null;

		try {
			resultMap = MMB006.selectInvUpSetupInfo(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnMap(resultMap, message);
	}
	
	/**
	 * 인보이스 업로드 설정 리스트 등록
	 * 
	 * @param req HttpServletRequest
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB006_01/selectInvUpSetupList")
	public ModelAndView selectInvUpSetupList(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = MMB006.selectInvUpSetupList(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}
	
	/**
	 * 인보이스 업로드 설정정보 등록
	 * 
	 * @param req HttpServletRequest
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB006_01/insertInvUpSetupInfo")
	public ModelAndView insertInvUpSetupInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
		Map resultMap = new HashMap();
		String message = null;

		try {
			Map map = DataMapHelper.getMap(dataMap);
			
			String nostr = MMB006.insertInvUpSetupInfo(map);
			
			resultMap.put("FORM_SETUP_SN", nostr);
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnMap(resultMap, message);
	}
	
	/**
	 * 인보이스 업로드 설정정보 수정
	 * 
	 * @param req HttpServletRequest
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB006_01/updateInvUpSetupInfo")
	public ModelAndView updateInvUpSetupInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
		boolean result = true;
		Map resultMap = new HashMap();
		String message = null;

		try {
			Map map = DataMapHelper.getMap(dataMap);
			
			int cnt = MMB006.updateInvUpSetupInfo(map);
			
			if(cnt < 0) {
				result = false;
			}
			
			resultMap.put("FORM_SETUP_SN", map.get("FORM_SETUP_SN"));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}
		
		if(result) {
			return WebAction.returnMap(resultMap, message);
		} else {
			return WebAction.resultJsonMsg(result, message);
		}
	}
	
	/**
	 * 인보이스 업로드 설정정보 삭제
	 * 
	 * @param req HttpServletRequest
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB006_01/deleteInvUpSetupInfo")
	public ModelAndView deleteInvUpSetupInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
		boolean result = true;
		String message = null;

		try {
			Map map = DataMapHelper.getMap(dataMap);
			
			int cnt = MMB006.deleteInvUpSetupInfo(map);
			
			if(cnt < 0) {
				result = false;
			}
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.resultJsonMsg(result, message);
	}
	
	/**
	 * 인보이스 항목 기본정보  조회
	 * 
	 * @param req HttpServletRequest
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB006_01/selectBaseInvUpList")
	public ModelAndView selectBaseInvUpList(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = MMB006.selectBaseInvUpList(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}
	
	/**
	 * 인보이스 항목 업로드 설정정보 조회
	 * 
	 * @param req HttpServletRequest
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB006_01/selectInvUpMasterInfo")
	public ModelAndView selectInvUpMasterInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = MMB006.selectInvUpMasterInfo(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}
	
	/**
	 * 인보이스 항목 업로드 설정정보 등록
	 * 
	 * @param req HttpServletRequest
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB006_01/insertInvUpMasterInfo")
	public ModelAndView insertInvUpMasterInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
		boolean result = true;
		String message = null;

		try {
			int cnt = MMB006.insertInvUpMasterInfo(DataMapHelper.getMap(dataMap));
			
			if(cnt < 0) {
				result = false;
			}
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.resultJsonMsg(result, message);
	}
	
	/**
	 * 인보이스 항목 업로드 설정정보 수정
	 * 
	 * @param req HttpServletRequest
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB006_01/updateInvUpMasterInfo")
	public ModelAndView updateInvUpMasterInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
		boolean result = true;
		String message = null;

		try {
			int cnt = MMB006.updateInvUpMasterInfo(DataMapHelper.getMap(dataMap));
			
			if(cnt < 0) {
				result = false;
			}
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.resultJsonMsg(result, message);
	}
	
	/**
	 * 인보이스 항목 업로드 설정정보 삭제
	 * 
	 * @param req HttpServletRequest
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB006_01/deleteInvUpMasterInfo")
	public ModelAndView deleteInvUpMasterInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
		boolean result = true;
		String message = null;

		try {
			int cnt = MMB006.deleteInvUpMasterInfo(DataMapHelper.getMap(dataMap));
			
			if(cnt < 0) {
				result = false;
			}
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.resultJsonMsg(result, message);
	}
	
	/**
	 * 인보이스/패킹리스트 업로드 이력 리스트 조회
	 * 
	 * @param req HttpServletRequest
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB006_01/selectUploadHistList")
	public ModelAndView selectUploadHistList(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = MMB006.selectUploadHistList(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}
	
	/**
	 * 인보이스 마스터 정보 업로드 이력 등록
	 * 
	 * @param req HttpServletRequest
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB006_01/insertInvUploadHist")
	public ModelAndView insertInvUploadHist(HttpServletRequest req, DataMap dataMap) throws Exception {
		boolean result = true;
		String message = null;

		try {
			int cnt = MMB006.insertInvUploadHist(DataMapHelper.getMap(dataMap));
			
			if(cnt < 0) {
				result = false;
			}
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.resultJsonMsg(result, message);
	}
	
	/**
	 * 인보이스 마스터 정보 업로드 이력 삭제
	 * 
	 * @param req HttpServletRequest
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB006_01/deleteInvUploadHist")
	public ModelAndView deleteInvUploadHist(HttpServletRequest req, DataMap dataMap) throws Exception {
		boolean result = true;
		String message = null;

		try {
			int cnt = MMB006.deleteInvUploadHist(DataMapHelper.getMap(dataMap));
			
			if(cnt < 0) {
				result = false;
			}
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.resultJsonMsg(result, message);
	}
	
	/**
	 * 패킹리스트 업로드 이력 등록
	 * 
	 * @param req HttpServletRequest
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB006_01/insertPlUploadHist")
	public ModelAndView insertPlUploadHist(HttpServletRequest req, DataMap dataMap) throws Exception {
		boolean result = true;
		String message = null;

		try {
			Map map = DataMapHelper.getMap(dataMap);
			int cnt = MMB006.insertPlUploadHist(map);
			
			if(cnt < 0) {
				result = false;
			} else {
				// 표통과 수입자료 매칭
				map.put("UPLOAD_NO_INIT_YN", "Y");
				MMB008.updateStandardEntrUploadNo(map);
			}
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.resultJsonMsg(result, message);
	}
	
	/**
	 * 패킹리스트 업로드 이력 수정
	 * 
	 * @param req HttpServletRequest
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB006_01/updatePlUploadHist")
	public ModelAndView updatePlUploadHist(HttpServletRequest req, DataMap dataMap) throws Exception {
		boolean result = true;
		String message = null;

		try {
			Map map = DataMapHelper.getMap(dataMap);
			int cnt = MMB006.updatePlUploadHist(map);
			
			if(cnt < 0) {
				result = false;
			} else {
				// 표통과 수입자료 매칭
				map.put("UPLOAD_NO_INIT_YN", "Y");
				MMB008.updateStandardEntrUploadNo(map);
			}
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.resultJsonMsg(result, message);
	}
	
	/**
	 * 패킹리스트 업로드 이력 삭제
	 * 
	 * @param req HttpServletRequest
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB006_01/deletePlUploadHist")
	public ModelAndView deletePlUploadHist(HttpServletRequest req, DataMap dataMap) throws Exception {
		boolean result = true;
		String message = null;

		try {
			Map map = DataMapHelper.getMap(dataMap);
			int cnt = MMB006.deletePlUploadHist(map);
			
			if(cnt < 0) {
				result = false;
			} else {
				// 표통과 수입자료 매칭
				map.put("UPLOAD_NO_INIT_YN", "Y");
				MMB008.updateStandardEntrUploadNo(map);
			}
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.resultJsonMsg(result, message);
	}
	
	/**
	 * 패킹리스트 업로드 이력 일괄 삭제
	 * 
	 * @param req HttpServletRequest
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB006_01/deleteBatchUploadHist")
	public ModelAndView deleteBatchUploadHist(HttpServletRequest req, DataMap dataMap) throws Exception {
		boolean result = true;
		String message = null;

		try {
			Map map = DataMapHelper.getMap(dataMap);
			int cnt = MMB006.deleteBatchUploadHist(map);
			
			if(cnt < 0) {
				result = false;
			} else {
				// 표통과 수입자료 매칭
				map.put("UPLOAD_NO_INIT_YN", "Y");
				MMB008.updateStandardEntrUploadNo(map);
			}
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.resultJsonMsg(result, message);
	}
	
	/**
	 * excel 업로드한 파일내역 조회
	 * 
	 * @param req HttpServletRequest
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB006_01/executeExcelUploadFile")
	public ModelAndView executeExcelUploadFile(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = MMB006.executeExcelUploadFile(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}
	
}