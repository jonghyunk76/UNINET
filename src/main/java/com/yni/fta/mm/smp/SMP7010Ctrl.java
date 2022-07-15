package com.yni.fta.mm.smp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.yni.frame.collection.DataMap;
import kr.yni.frame.controller.YniAbstractController;
import kr.yni.frame.web.action.WebAction;


@Controller
public class SMP7010Ctrl extends YniAbstractController {

    /**
     * 샘플 리스트 조회 화면으로 이동
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws EframeException
     */
    @RequestMapping(value="/mm/smp/smp7010_01")
    public ModelAndView smp1009_01(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/SMP/SMP7010_01", dataMap);
    }
}
