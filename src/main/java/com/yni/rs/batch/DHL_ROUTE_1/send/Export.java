package com.yni.rs.batch.DHL_ROUTE_1.send;

import java.util.Map;

import com.yni.fta.common.parameter.YniAbstractBatch;
import com.yni.rs.batch.ExportPackage;

public class Export extends YniAbstractBatch implements ExportPackage {

	@Override
	public Map getParameter(Map map) throws Exception {
		log.debug("Send Export : " + map);
		
		return null;
	}

}
