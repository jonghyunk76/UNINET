package com.yni.fta.mm.pop;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yni.fta.common.tools.CertifySupporter;
import com.yni.fta.fm.sm.SM7001;
import com.yni.fta.mm.menu.LeftMenu;
//import com.yni.fta.sp.vs.VSC002;

import kr.yni.frame.Constants;
import kr.yni.frame.collection.DataMap;
import kr.yni.frame.controller.YniAbstractController;
import kr.yni.frame.util.DataMapHelper;
import kr.yni.frame.util.DateHelper;
import kr.yni.frame.util.StringHelper;
import kr.yni.frame.web.action.WebAction;

/**
 * FTA협정등록
 * 
 * @author YNI-maker
 *
 */
@Controller
public class MMA001Ctrl extends YniAbstractController {

	 
	@Resource(name="mmA001")
	private MMA001 mmA001;
	
	@Resource(name="leftMenu")
	private LeftMenu leftMenu;
	
	@Resource(name = "sm7001")
    private SM7001 sm7001;
	
//	@Resource(name = "vsC002")
//	private VSC002 vsC002;
	
	/**
     * FTA 내부 관리 메인 화면으로 이동
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/mm/pop/mmA001_01")
    public ModelAndView mmA001_01Move(DataMap dataMap) throws Exception {
    	Map map = DataMapHelper.getMap(dataMap);
    	Map paramMap = new HashMap();
    	
    	paramMap.putAll(dataMap.getMap());
    	paramMap.put("MENU_ID", "FR-1010");
    	
    	Map rstMap = leftMenu.selectMenuAuthInfo(paramMap);
    	
    	dataMap.put("AUTHTARIFF", StringHelper.null2string(rstMap.get("AUTHTARIFF"), "N"));
    	
    	Map sysMap = sm7001.selectSysconfigOptionInfo(map);
    	
    	dataMap.put("REMOTE_SERVICE_YN", StringHelper.null2string(sysMap.get("REMOTE_SERVICE_YN"), "N"));
    	dataMap.put("REMOTE_SERVICE_URL", StringHelper.null2string(sysMap.get("REMOTE_SERVICE_URL"), "#"));
    	
        return WebAction.forwarding("/POP/MM-A001_01", dataMap);
    }
	
    /**
     * 날짜설정 Tooltip
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/mm/pop/mmA001_02")
    public ModelAndView mmA001_02Move(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/POP/MM-A001_02", dataMap);
    }
    
    /**
     * 협력사 포탈 메인 화면으로 이동
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/mm/pop/mmA001_03")
    public ModelAndView mmA001_03Move(DataMap dataMap) throws Exception {
    	Map map = DataMapHelper.getMap(dataMap);
    	Map sysMap = sm7001.selectSysconfigOptionInfo(map);
    	
    	if(StringHelper.null2void(map.get("CONNECT_CLASS")).equals("M")) {
    		dataMap.put("VENDOR_INFO_REQUIRED_YN", "N");
    	} else {
	    	dataMap.put("VENDOR_INFO_REQUIRED_YN", StringHelper.null2string(sysMap.get("VENDOR_INFO_REQUIRED_YN"), "N"));
    	}
    	dataMap.put("REMOTE_SERVICE_YN", StringHelper.null2string(sysMap.get("REMOTE_SERVICE_YN"), "N"));
    	dataMap.put("REMOTE_SERVICE_URL", StringHelper.null2string(sysMap.get("REMOTE_SERVICE_URL"), "#"));
    	
    	map.put("VENDOR_CD", map.get("SESSION_VENDOR_CD"));
    	
//    	Map infMap = vsC002.selectVendorRequiredInfo(map);
//    	
//    	dataMap.put("VENDOR_WRITE_YN", StringHelper.null2string(infMap.get("VENDOR_WRITE_YN"), "Y"));
    	
        return WebAction.forwarding("/POP/MM-A001_03", dataMap);
    }
    
    /**
     * FTA 관세 절감액 추이 상세 조회 화면으로 이동
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/mm/pop/mmA001_04")
    public ModelAndView mmA001_04Move(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/POP/MM-A001_04", dataMap);
    }
    
    /**
     * 통관 메인화면으로 이동
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/mm/pop/mmA001_05")
    public ModelAndView mmA001_05Move(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/POP/MM-A001_05", dataMap);
    }
    
    /**
     * 원가계산 메인화면으로 이동
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/mm/pop/mmA001_06")
    public ModelAndView mmA001_06Move(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/POP/MM-A001_06", dataMap);
    }    
    
    /**
     * 중계서버 메인화면으로 이동
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/mm/pop/mmA001_07")
    public ModelAndView mmA001_07Move(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/POP/MM-A001_07", dataMap);
    }
    
    /**
     * FTA 원산지 관리 현황 조회
     * 
     * @param paramTwo
     *            - ModelMap model
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/mm/pop/selectClosingStatus")
    public ModelAndView selectClosingStatus(HttpServletRequest req, DataMap dataMap) throws Exception {
        Map resultMap = new HashMap();
        String message = null;

        try {
        	// 북마크 여부 조회
        	Map map = DataMapHelper.getMap(dataMap);
        	String requestState = StringHelper.null2void(map.get("REQUEST_STATE"));
        	String yyyymm = StringHelper.null2void(map.get("YYYYMM"));
        	
        	if(!requestState.isEmpty()) resultMap.put("REQUEST_STATE", requestState);
        	
        	if(requestState.isEmpty() || requestState.equals("STEP1")) {
        		Map stepMap = mmA001.selectClosingInfo(map);
        		if(stepMap != null && stepMap.size() > 0) resultMap.putAll(stepMap);
        		
        		if(!yyyymm.isEmpty()) {
        			// 요청한 마감월과 현재월 마감월이 같은 경우 항상 최근 정보가 적용될 수 있게 마감월 지정을 취소함
        			String todate = DateHelper.getYear() + DateHelper.getMonth();
        			
        			if(todate.equals(yyyymm)) {
        				log.debug("동일한 마감월을 지정하여 적용월을 취소합니다.");
        				map.put("WORK_YYYYMM", todate);
        			} else {
        				map.put("WORK_YYYYMM", yyyymm);
        				
        				// 세션정보의 작업월을 변경함
            			CertifySupporter.changeWorkClosingMonth(req, yyyymm);
        			}
        			
        			mmA001.updateWorkDate(map);
        		}
        	}
        	if(requestState.isEmpty() || requestState.equals("STEP2")) {
        		if(StringHelper.null2void(map.get("PARENT_HISTORY_ID")).isEmpty()) {
        			map.put("PARENT_HISTORY_ID", resultMap.get("PARENT_HISTORY_ID"));
        		}
        		
        		Map stepMap = mmA001.selectDataInterfaceCount(map);
        		if(stepMap != null && stepMap.size() > 0) resultMap.putAll(stepMap);
        	}        	
        	if(requestState.isEmpty() || requestState.equals("STEP3")) {
        		Map stepMap = null;
        		List vcoList = mmA001.selectVendorCoCount(map);
        		
        		if(vcoList != null && vcoList.size() > 0) {
        			stepMap = (Map) vcoList.get(0);
        		} else {
        			stepMap = new LinkedHashMap();
        		}
        		
        		if(stepMap != null && stepMap.size() > 0) resultMap.putAll(stepMap);
        	}
        	if(requestState.isEmpty() || requestState.equals("STEP4")) {
        		Map stepMap = null;
        		List deList = mmA001.selectDeterminateCount(map);
        		
        		if(deList != null && deList.size() > 0) {
        			stepMap = (Map) deList.get(0);
        		} else {
        			stepMap = new LinkedHashMap();
        		}
        		
        		if(stepMap != null && stepMap.size() > 0) resultMap.putAll(stepMap);
        	}
        	if(requestState.isEmpty() || requestState.equals("STEP5")) {
        		Map stepMap = mmA001.selectIssueReportCount(map);
        		if(stepMap != null && stepMap.size() > 0) resultMap.putAll(stepMap);
        	}
        } catch (Exception e) {
            message = getExceptionMessage(req, e, "MSG_UNSPECIFIED_ERROR");    
        }

        return WebAction.returnMap(resultMap, message);
    }
    
    /**
     * 월별 원산지 수취율 조회
     * 
     * @param req
     * @param dataMap
     * @return
     * @throws Exception
     */
	@RequestMapping("/mm/pop/selectVendorCoCount")
	public ModelAndView selectVendorCoCount(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = mmA001.selectVendorCoCount(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}
	
	/**
     * 월별 원산지 판정 추이
     * 
     * @param req
     * @param dataMap
     * @return
     * @throws Exception
     */
	@RequestMapping("/mm/pop/selectDeterminateCount")
	public ModelAndView selectDeterminateCount(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = mmA001.selectMonthlyDeterminateCount(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}
	
	/**
     * 협력사 원산지 제출 상태
     * 
     * @param req
     * @param dataMap
     * @return
     * @throws Exception
     */
	@RequestMapping("/mm/pop/selectSupplierIssueStatus")
	public ModelAndView selectSupplierIssueStatus(HttpServletRequest req, DataMap dataMap) throws Exception {
		Map resultMap = null;
		String message = null;

		try {
			resultMap = mmA001.selectSupplierIssueStatus(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnMap(resultMap, message);
	}
	
	/**
     * FTA 관세 절감액 추이
     * 
     * @param req
     * @param dataMap
     * @return
     * @throws Exception
     */
	@RequestMapping("/mm/pop/selectExportTariffInfo")
	public ModelAndView selectExportTariffInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = mmA001.selectExportTariffInfo(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}
	
	/**
     * 환경설정 값 구하기
     * 
     * @param req
     * @param dataMap
     * @return
     * @throws Exception
     */
	@RequestMapping("/mm/pop/selectSystemConfig")
	public ModelAndView selectSystemConfig(HttpServletRequest req, DataMap dataMap) throws Exception {
		Map resultMap = null;
		String message = null;

		try {
			resultMap = mmA001.selectSystemConfig(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnMap(resultMap, message);
	}
	
	/**
     * 메뉴정보 구하기
     * 
     * @param req
     * @param dataMap
     * @return
     * @throws Exception
     */
	@RequestMapping("/mm/pop/selectSystemMenuInfo")
	public ModelAndView selectSystemMenuInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
		Map resultMap = null;
		String message = null;

		try {
			resultMap = mmA001.selectSystemMenuInfo(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnMap(resultMap, message);
	}
	
	/**
     * 월별 원산지 판정결과 재집계
     * 
     * @param req
     * @param dataMap
     * @return
     * @throws Exception
     */
	@RequestMapping("/mm/pop/updateDwMonthFcrStatus")
	public ModelAndView updateDwMonthFcrStatus(HttpServletRequest req, DataMap dataMap) throws Exception {
		boolean success = true;
        String message = null;
        
        try {
        	Map map = DataMapHelper.getMap(dataMap);
            int resultCnt = mmA001.updateDwMonthFcrStatus(map);
            
            if (resultCnt < 0) {
            	success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
	}
	
}
