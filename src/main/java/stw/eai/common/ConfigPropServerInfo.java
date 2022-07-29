package stw.eai.common;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigPropServerInfo {

	/**
	 * ȯ�漳�� Properties���� Ư�� property ���� �����´�.<br>
	 * hostmode ������ ���� �� ����Ѵ�.
	 * @param category Property�� �з�
	 * @param attr Property�� �Ӽ�
	 * @return Property ��
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
			is = new FileInputStream("C:\\Project\\01. ����\\F_HTW CRM ��û�ڷ�\\6. Interface\\�������̽� ���� ����\\�ҽ�\\STW EAI\\STW EAI\\bin\\STW.properties");
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