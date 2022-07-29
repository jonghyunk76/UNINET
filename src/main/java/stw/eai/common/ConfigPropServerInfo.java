package stw.eai.common;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigPropServerInfo {

	/**
	 * 환경설정 Properties에서 특정 property 값을 가져온다.<br>
	 * hostmode 구분이 없을 때 사용한다.
	 * @param category Property의 분류
	 * @param attr Property의 속성
	 * @return Property 값
	 * @see getHostmodeProperty
	 * @see getValue
	 */
	public String getProperty(String category, String attr) throws Exception {

		String key = String.format("%s.%s", category, attr);

		return getValue(key);
	}
	public Properties getProperties() throws Exception {
		Properties	properties;
		FileInputStream is = null;
		try{
//				is = ClassLoader.getSystemResourceAsStream("STW.properties");
//			is = new FileInputStream("D:\\SBA811\\ses\\siebsrvr\\CLASSES\\STW.properties");
			is = new FileInputStream("C:\\Project\\01. 문서\\F_HTW CRM 요청자료\\6. Interface\\인터페이스 공유 폴더\\소스\\STW EAI\\STW EAI\\bin\\STW.properties");
			properties = new Properties();
			properties.load(is);
		}
		catch(Exception e){
			throw e;
		}
		finally{
			is.close();
		}
		return properties;
	}

	public String getValue(String key) throws Exception {
		String value = getProperties().getProperty(key);

		if ((value != null) && (value.isEmpty())) {
			value = null;
		}

		return value;
	}
}