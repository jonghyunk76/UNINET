package com.yni.fta.mm.menu;


import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.yni.frame.Constants;
import kr.yni.frame.collection.DataMap;
import kr.yni.frame.controller.YniAbstractController;
import kr.yni.frame.util.DataMapHelper;
import kr.yni.frame.util.StringHelper;
import kr.yni.frame.web.action.WebAction;

/**
 * 메인화면 사용자 권한별 메뉴 Controller클래스
 * 
 * @author YNI-maker
 *
 */
@Controller
public class LeftMenuCtrl extends YniAbstractController {
	
	@Resource(name="leftMenu")
	private LeftMenu leftMenu;
	
	/* logger */
	private Log logger = LogFactory.getLog(LeftMenuCtrl.class);
	
	/**
     * main page menu
     * @param req
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/mm/leftMenu",method = RequestMethod.GET )
    public ResponseEntity<String> main(@RequestParam(value="selectedLanguage",required=true) String selectedLanguage
							    	,@RequestParam(value="user",required=true)  String user
							    	,@RequestParam(value="company",required=true) String company
							    	,HttpServletRequest req) throws Exception {
    	Items<MenuItem> items=leftMenu.getMenuItems(selectedLanguage,user, company);
    	
    	return jsonConverter(items.toString(),"utf-8");
    }
    
    static private ResponseEntity<String> jsonConverter(String str,String charType){
    	HttpHeaders responseHeaders = new HttpHeaders();
    	
        responseHeaders.add("Content-Type", "text/html; charset="+charType);
        
    	return new ResponseEntity<String>(str, responseHeaders, HttpStatus.CREATED);
    }
    
    /**
	 * 메뉴에 대한 사용권한 조회
	 * 
	 * @param paramTwo
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/selectMenuAuthInfo")
    public ModelAndView selectMenuAuthInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        Map result = null;
        String message = null;
        try {
            result = leftMenu.selectMenuAuthInfo(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnMap(result, message);
    }
    
}
