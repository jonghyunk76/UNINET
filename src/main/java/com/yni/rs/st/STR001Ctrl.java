package com.yni.rs.st;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yni.fta.common.tools.SocketSupporter;

import kr.yni.frame.Constants;
import kr.yni.frame.collection.DataMap;
import kr.yni.frame.controller.YniAbstractController;
import kr.yni.frame.util.DataMapHelper;
import kr.yni.frame.util.StringHelper;
import kr.yni.frame.web.action.WebAction;

/**
 * 서버관리 > 서버목록
 * 
 * @author YNI-maker
 */
@Controller
public class STR001Ctrl extends YniAbstractController {

	@Resource(name="stR001")
	private STR001 stR001;

	/**
	 * 서버목록 조회 화면으로 이동
	 * 
	 * @param request
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/rs/st/stR001_01")
	public ModelAndView stR001_01Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding( "/ST/ST-R001_01", dataMap);
	}

	/**
	 * 서버등록 및 수정 화면으로 이동
	 * 
	 * @param request
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/rs/st/stR001_02")
	public ModelAndView stR001_02Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding( "/ST/ST-R001_02", dataMap);
	}
	
	/**
	 * 서버선택 화면으로 이동
	 * 
	 * @param request
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/rs/st/stR001_03")
	public ModelAndView stR001_03Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding( "/ST/ST-R001_03", dataMap);
	}
	
	/**
	 * 서버목록 조회
	 * 
	 * @param req
	 * @param dataMap
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/rs/st/stR001_01/selectServerList")
	public ModelAndView selectServerList(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;
		
		try {
			resultList = stR001.selectServerList(DataMapHelper.getMap(dataMap));
			
			// 서버별 연결상태를 체크한다.
			if(resultList != null) {
				SocketSupporter ss = new SocketSupporter();
				
				for(int i = 0; i < resultList.size(); i++) {
					Map resultMap = (Map) resultList.get(i);
					
					String useyn = StringHelper.null2string(resultMap.get("USE_YN"), "Y");
					String ip = StringHelper.null2void(resultMap.get("SERVER_IP"));
					int port = StringHelper.null2zero(resultMap.get("SERVER_PORT"));
					
					if(useyn.equals("Y") && !ip.isEmpty()) {
						if(ss.availablePort(ip, port)) {
							resultMap.put("CURRENT_STAT", "R");
							resultMap.put("CURRENT_STAT_NAME", "기동");
						} else {
							resultMap.put("CURRENT_STAT", "E");
							resultMap.put("CURRENT_STAT_NAME", "장애");
						}
					} else {
						resultMap.put("CURRENT_STAT", "S");
						resultMap.put("CURRENT_STAT_NAME", "중지");
					}
				}
			}
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}
	
	/**
     * 서버 등록
     * 
     * @param req
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping("/rs/st/stR001_02/insertServerMst")
    public ModelAndView insertServerListMst(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        
        try {
            int resultCnt = stR001.insertServerMst(DataMapHelper.getMap(dataMap));
            
            if (resultCnt < 0) {
                success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
    /**
     * 서버 수정
     * 
     * @param req
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping("/rs/st/stR001_02/updateServerMst")
    public ModelAndView updateServerMst(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        
        try {
            int resultCnt = stR001.updateServerMst(DataMapHelper.getMap(dataMap));
            
            if (resultCnt < 0) {
                success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
    /**
     * 서버 삭제
     * 
     * @param req
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping("/rs/st/stR001_02/deleteServerMst")
    public ModelAndView deleteServerMst(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        
        try {
            int resultCnt = stR001.deleteServerMst(DataMapHelper.getMap(dataMap));
            
            if (resultCnt < 0) {
                success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }

}
