package com.yni.fta.common.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kr.yni.frame.Constants;
import kr.yni.frame.util.JsonUtil;
import kr.yni.frame.util.StringHelper;

public class PythonSupporter {
	
	private static Log log = LogFactory.getLog(PythonSupporter.class);
	
//	private static PythonInterpreter pi;
	
	public static String excuteScript(Map map) throws Exception {
		StringBuffer rst = new StringBuffer();
		
		try {
			String func = StringHelper.null2void(map.get("PY_FUNC"));
			String url = StringHelper.changeCode4Message(StringHelper.null2void(map.get("CRAWL_URL")));
			List plist = JsonUtil.getList(StringHelper.null2string(map.get("PY_PARAM"), "[]"));
			
			log.debug("parameter size = " + plist.size());
			
			String pyfile = Constants.APPLICATION_REAL_PATH+File.separator+"py"+File.separator+StringHelper.null2void(map.get("RUN_FILE_NAME")+".py");
			String debugStr = "";
			String[] cmd = new String[plist.size() + 3];
			
			cmd[0] = "python";
			cmd[1] = pyfile;
			cmd[2] = url;
			
			debugStr += url +" ";
			
			// 파라메터 생성
			if(plist.size() > 0) {
				for(int i = 0; i < plist.size(); i++) {
					Map pmap = (Map) plist.get(i);
					
					String type = StringHelper.null2void(pmap.get("TYPE"));
					String val = StringHelper.null2void(pmap.get("VALUE"));
					
					log.debug("Type = " + type + ", Value = " + val);
					
					cmd[i+3] = val;
					debugStr += val +" ";
				}
			}
			
			if(log.isDebugEnabled()) log.debug("command : python " + pyfile + " " + debugStr);
			
			Process p = Runtime.getRuntime().exec(cmd);
			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream(), Constants.APPLICATION_CONTEXT_CHARSET));
			String s = null;
			
			while((s = in.readLine()) != null) {
				rst.append(s);
//				log.debug(s);
			}
		} catch(Exception e) {
			rst.append(e.getMessage());
		}
		
		return rst.toString();
	}
	
	/*
	public static PyObject execute(Map map) throws Exception {
		System.setProperty("python.import.site", "false");
		System.setProperty("python.cachedir.skip", "true");
		
		String func = StringHelper.null2void(map.get("PY_FUNC"));
		List paramList = JsonUtil.getList(StringHelper.null2string(map.get("PY_PARAM"), "[]")); // [{"TYPE":"INT", "VALUE":"10"}, {"TYPE":"INT", "VALUE":"20"}]
		
		String pyfile = Constants.APPLICATION_REAL_PATH+File.separator+"py"+File.separator+StringHelper.null2void(map.get("RUN_FILE_NAME")+".py");
		
		if(log.isDebugEnabled()) log.debug("Execute file name = "+pyfile + ", parameter size = " + paramList.size() + ", function = " + func);
		
		if(!new File(pyfile).exists()) {
			throw new Exception("Sorry... not found python file.");
		}
		
		if(!new File(pyfile).exists()) {
			throw new Exception("not found function name.");
		}
		
		List plist = new LinkedList();
		
		// 파라메터 생성
		if(paramList.size() > 0) {
			for(int i = 0; i < paramList.size(); i++) {
				Map pmap = (Map) paramList.get(i);
				
				String type = StringHelper.null2void(pmap.get("TYPE"));
				String val = StringHelper.null2void(pmap.get("VALUE"));
				
				log.debug("Type = " + type + ", Value = " + val);
				
				if(!type.isEmpty() && type.toUpperCase().equals("INT")) {
					plist.add(new PyInteger(Integer.parseInt(val)));
				} else {
					plist.add(new PyString(val));
				}
			}
		}
		
		log.debug("parameter size = " + plist.size());
		
		pi = new PythonInterpreter();
		
//		pi.exec("import sys\nsys.path\n['', 'C:/DEVTOOLS/Python3.1.0/Lib', '__classpath__', '__pyclasspath__/','C:/DEVTOOLS/Python3.1.0/Lib/site-packages']");
		pi.exec("import sys\nsys.path.append('C:/DEVTOOLS/Python3.1.0/Lib')");
		
//		pi.exec("import sys\nsys.path.append('pathToModules if they are not there by default')\nimport yourModule");
//		pi.exec("from bs4 import BeautifulSoup");
//		pi.exec("import urllib.request as urlopen");
		pi.exec("import overseas_tariff");
		
		log.debug("import complited...");
		
		pi.execfile(pyfile);
		
		PyFunction pf = (PyFunction) pi.get(func, PyFunction.class);
		PyObject[] params = new PyObject[plist.size()];
		
		for(int i = 0; i < plist.size(); i++) {
			params[i] = (PyObject) plist.get(i);
		}
		
		PyObject pobj = null;
		
		if(plist.size() == 0) {
			pobj = pf.__call__();
		} else {
			pobj = pf.__call__(params);
		}
		
		log.debug("Python result = " + pobj.toString());
		
		return pobj;
	}
	*/
	
}
