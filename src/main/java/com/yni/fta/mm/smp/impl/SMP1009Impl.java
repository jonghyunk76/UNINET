package com.yni.fta.mm.smp.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.yni.fta.common.tools.FileReaderSupporter;
import com.yni.fta.mm.smp.SMP1009;

import kr.yni.frame.service.YniAbstractService;
import kr.yni.frame.util.DataMapHelper;
import kr.yni.frame.util.FileUtil;
import kr.yni.frame.util.StringHelper;
import kr.yni.frame.web.upload.FormFile;

/**
 * FTA Rule > FTA협정등록
 * 
 * @author YNI-maker
 *
 */
@Service("smp1009")
public class SMP1009Impl extends YniAbstractService implements SMP1009 {
    
    @Resource(name="smp1009Dao")
    private SMP1009Dao smp1009Dao;

	public List selectMessgeList(Map map) throws Exception {
		return smp1009Dao.selectMessgeList(map);
	}

	public Map selectMessgeDetail(Map map) throws Exception {
		return smp1009Dao.selectMessgeDetail(map);
	}

	public int insertMessageInfo(Map map) throws Exception {
		return smp1009Dao.insertMessageInfo(map);
	}

	public int updateMessageInfo(Map map) throws Exception {
		return smp1009Dao.updateMessageInfo(map);
	}
	
	public int deleteMessgeDetail(Map map) throws Exception {
		return smp1009Dao.deleteMessgeDetail(map);
	}
    
}
