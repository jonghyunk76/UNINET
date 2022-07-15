package com.yni.fta.mm.pop;

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
 * 공통 > 제품군 조회
 * 
 * @author YNI-maker
 *
 */
@Controller
public class MMA025Ctrl extends YniAbstractController {

    @Resource(name = "mmA025")
    private MMA025 mmA025;

    /**
     * FTA협정선택 화면으로 이동
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/mm/pop/mmA025_01")
    public ModelAndView mmA025_01Move(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/POP/MM-A025_01", dataMap);
    }

    /**
     * FTA협정선택 조회
     * 
     * @param paramTwo
     *            - ModelMap model
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/mm/pop/mmA025_01/selectMainList")
    public ModelAndView selectMainList(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;

        try {
            resultList = mmA025.selectMainList(DataMapHelper.getMap(dataMap));
        } catch(Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }

        return WebAction.returnDataSet(resultList, message);
    }

}
