package com.yni.fta.mm.batch;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import kr.yni.frame.controller.YniAbstractController;

/**
 * 인터페이스 배치을 위한 Controller클래스
 * 
 * @author YNI-maker
 *
 */
@Controller
public class BatchCtrl extends YniAbstractController {
	
	@Resource(name="batchService")
	private BatchService batchService;
	
}
