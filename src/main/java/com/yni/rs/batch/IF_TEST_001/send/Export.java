package com.yni.rs.batch.IF_TEST_001.send;

import java.util.Map;

import com.yni.fta.common.parameter.YniAbstractBatch;
import com.yni.rs.batch.ExportPackage;

public class Export extends YniAbstractBatch implements ExportPackage {

	@Override
	public Map getParameter(Map map) throws Exception {
		log.debug("IF_TEST_001 Send Export : " + map);
		
		return null;
	}

}
