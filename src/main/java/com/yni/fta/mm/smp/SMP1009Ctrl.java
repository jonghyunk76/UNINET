package com.yni.fta.mm.smp;

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
import kr.yni.frame.resources.MessageResource;
import kr.yni.frame.util.DataMapHelper;
import kr.yni.frame.util.MessageResourceHelper;
import kr.yni.frame.util.StringHelper;
import kr.yni.frame.web.action.WebAction;

/**
 * FTA협정등록
 * 
 * @author YNI-maker
 *
 */
@Controller
public class SMP1009Ctrl extends YniAbstractController {

    @Resource(name="smp1009")
    private SMP1009 smp1009;
    
    /**
     * 샘플 리스트 조회 화면으로 이동
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws EframeException
     */
    @RequestMapping(value="/mm/smp/smp1009_01")
    public ModelAndView smp1009_01(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/SMP/SMP1009_01", dataMap);
    }
    
    /**
     * 샘플 상세 조회 및 관리 화면으로 이동
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws EframeException
     */
    @RequestMapping(value="/mm/smp/smp1009_02")
    public ModelAndView smp1009_02(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/SMP/SMP1009_02", dataMap);
    }
    
    
    /**
     * 메시지 리스트 조회
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/mm/smp/smp1009/selectMessgeList")
    public ModelAndView selectMessgeList(HttpServletRequest req, DataMap dataMap) throws Exception {
        List result = null;
        String message = null;
        
        try {
        	result = smp1009.selectMessgeList(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnDataSet(result, message);
    }
    
    /**
     * 메시지 상세내역 조회
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/mm/smp/smp1009/selectMessgeDetail")
    public ModelAndView selectMessgeDetail(HttpServletRequest req, DataMap dataMap) throws Exception {
        Map result = null;
        String message = null;
        
        try {
            result = smp1009.selectMessgeDetail(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnMap(result, message);
    }
    
    /**
     * 
     * 메시지 신규 등록
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/mm/smp/smp1009/insertMessageInfo")
    public ModelAndView insertMessageInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        
        try {
            int resultCnt = smp1009.insertMessageInfo(DataMapHelper.getMap(dataMap));
            
            if(resultCnt < 0) {
            	success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
    /**
     * 
     * 메시지 수정
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/mm/smp/smp1009/updateMessageInfo")
    public ModelAndView updateMessageInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
    	boolean success = true;
        String message = null;
        
        try {
            int resultCnt = smp1009.updateMessageInfo(DataMapHelper.getMap(dataMap));
            
            if(resultCnt < 0) {
            	success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
    /**
     * 
     * 메시지 삭제
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/mm/smp/smp1009/deleteMessgeDetail")
    public ModelAndView deleteMessgeDetail(HttpServletRequest req, DataMap dataMap) throws Exception {
    	boolean success = true;
        String message = null;
        
        try {
            int resultCnt = smp1009.deleteMessgeDetail(DataMapHelper.getMap(dataMap));
            
            if(resultCnt < 0) {
            	success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
    /**
     * 
     * 시스템에 메시지 파일생성
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/mm/smp/smp1009/saveMessageSystem")
    public ModelAndView saveMessageSystem(HttpServletRequest req, DataMap dataMap) throws Exception {
    	boolean success = true;
        String message = null;
        String[] categoryList = {"KR", "KO", "EN", "ES", "VN"};
        
        try {
            Map map = DataMapHelper.getMap(dataMap);
            
        	map.remove("NATION_CODE"); 
        	map.remove("schKeyWord");
        	map.remove("SCH_FROM_DATE");
        	
        	List list = smp1009.selectMessgeList(map);
            
        	if(list.size() > 0) {
        		for(int i = 0; i < list.size(); i++) {
        			Map msgMap = (Map) list.get(i);
        			String msg = StringHelper.null2void(msgMap.get("MESSAGE"));

        			msgMap.put("MESSAGE", msg.replace(System.getProperty("line.separator"), "<br>"));
        		}
        		
	            MessageResourceHelper.setMessageCategory(categoryList); // 생성할 언어 설정
	            
        		MessageResourceHelper.generatePropertiesMessageFile(list);
	            MessageResourceHelper.generateJsMessageFile(list);
	            
	            MessageResource.clearMessageResource();
        	}
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
}
