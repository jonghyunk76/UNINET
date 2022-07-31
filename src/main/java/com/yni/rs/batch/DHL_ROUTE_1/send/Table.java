package com.yni.rs.batch.DHL_ROUTE_1.send;

import java.util.List;
import java.util.Map;

import com.yni.fta.common.parameter.YniAbstractBatch;
import com.yni.rs.batch.TablePackage;

public class Table extends YniAbstractBatch implements TablePackage {

	@Override
	public Object getTable(Map map) throws Exception {
		log.debug("Send Table : " + map);
		
		return null;
	}

}
