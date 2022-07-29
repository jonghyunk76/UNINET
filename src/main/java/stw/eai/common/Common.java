package stw.eai.common;

import java.util.ArrayList;

import stw.eai.dhl.xml.datatypes.ObjectFactory;
import stw.eai.dhl.xml.datatypes.Request;
import stw.eai.dhl.xml.datatypes.ServiceHeader;

import com.siebel.data.SiebelPropertySet;

public class Common {
	public static String SERVER;
	
	//mySingle
	public final static String DEV_SERVER_ADDR	= "http://wsstage.samsung.net/wsgwsoaphttp1/soaphttpengine/WSGWBus";
	public final static String PRD_SERVER_ADDR	= "http://ws.samsung.net/wsgwsoaphttp1/soaphttpengine/WSGWBus";
	public final static String CONN_ID_ML		= "D11ML0051";
	public final static String CONN_PW_ML		= "D11ML0051298170";
	public final static String CONN_ID_AP		= "D11AP0043";
	public final static String CONN_PW_AP		= "D11AP0043246350";
	
	public static String MYSINGLE_SERVER_ADDR()
	{
		if("Local".equals(Common.SERVER))
		{
			return DEV_SERVER_ADDR;
		}
		else if("SSCRMD01".equals(Common.SERVER))
		{
			return DEV_SERVER_ADDR;
		}
		else if("SSCRMQ01".equals(Common.SERVER))
		{
			return PRD_SERVER_ADDR;
		}
		else if("SSCRMP01".equals(Common.SERVER))
		{
			return PRD_SERVER_ADDR;
		}
		else
		{
			return PRD_SERVER_ADDR;
		}
	}
	
	public static SiebelPropertySet	getChild(SiebelPropertySet psInput,String childName)
	{
		int i = 0;
		SiebelPropertySet temp;
		
		while (i < psInput.getChildCount())
		{
			temp = psInput.getChild(i);
			if(childName.equals(temp.getType()))
			{
				return temp;
			}
			i++;
		}
		
		return null;
	}
	
	public static ArrayList<SiebelPropertySet> getChilds(SiebelPropertySet psInput,String childName)
	{
		int i = 0;
		ArrayList<SiebelPropertySet> arPS = new ArrayList<SiebelPropertySet>();
		SiebelPropertySet temp;
		
		while (i < psInput.getChildCount())
		{
			temp = psInput.getChild(i);
			if(childName.equals(temp.getType()))
			{
				arPS.add(temp);
			}
			i++;
		}
		temp = null;
		
		return arPS;
	}
	
	public static String NVL(String value)
	{
		if("".equals(value)) return null;
		else return value;
	}
	
	
	//DHL
	
//	public final static String DEFAULT_PATH				= "D:\\SBA811\\ses\\siebsrvr\\XMLP\\DATA\\DHL";
	public final static String DEFAULT_PATH				= "C:\\XML\\DHL";
	public final static String XML_FILE_PATH			= DEFAULT_PATH + "\\TransformXMLtoPDF\\ResponseXMLS\\";  //bundle.getString("XML_FILE_PATH"); //
	public final static String PROCESSED_XML_FILE_PATH	= DEFAULT_PATH + "\\TransformXMLtoPDF\\ProcessedXMLS\\"; //bundle.getString("PROCESSED_XML_FILE_PATH");
	public final static String RESPONSE_PATH			= DEFAULT_PATH + "\\TransformXMLtoPDF\\PDFReports\\";    //bundle.getString("RESPONSE_PATH");
	public final static String REQUEST_PATH				= DEFAULT_PATH + "\\TransformXMLtoPDF\\RequestXML\\";
	public final static String JASPER_PATH				= DEFAULT_PATH + "\\JASPER\\";
	public final static String XML_CONTEXT_PATH			= "stw.eai.dhl.xml.datatypes";
	public final static String TEST_SERVLET_URL			= "http://xmlpitest-ea.dhl.com/XMLShippingServlet";
	public final static String SERVLET_URL				= "http://xmlpi-ea.dhl.com/XMLShippingServlet";
//	public final static String SERVLET_URL				= "http://xmlpiqa.dhl.com/XMLShippingServlet";
	public final static String DEV_SITE_ID				= "DServiceVal"; // REAL : "samsungte"    TEST => "DServiceVal"
	public final static String DEV_SITE_PW				= "testServVal"; // REAL : "YXj3GvBqu7"   TEST => "testServVal"
	public final static String PRD_SITE_ID				= "samsungte";
	public final static String PRD_SITE_PW				= "YXj3GvBqu7";
	
	public static Request getDHLRequest(){
		ObjectFactory factory = new ObjectFactory();
		Request request = factory.createRequest();
		ServiceHeader serviceHeader = factory.createServiceHeader();
		serviceHeader.setSiteID		(Common.getSiteId());
		serviceHeader.setPassword	(Common.getSitePw());
		request.setServiceHeader	(serviceHeader);
		return request;
	}
	
	public static String getServletURL()
	{
		if("Local".equals(Common.SERVER))
		{
			return TEST_SERVLET_URL;
		}
		else if("SSCRMD01".equals(Common.SERVER))
		{
			return TEST_SERVLET_URL;
		}
		else if("SSCRMQ01".equals(Common.SERVER) || "SSCRMP01".equals(Common.SERVER))
		{
			return SERVLET_URL;
		}
		else
		{
			return SERVLET_URL;
		}
	}
	
	public static String getSiteId()
	{
		if("Local".equals(Common.SERVER))
		{
			return DEV_SITE_ID;
		}
		else if("SSCRMD01".equals(Common.SERVER))
		{
			return DEV_SITE_ID;
		}
		else if("SSCRMQ01".equals(Common.SERVER) || "SSCRMP01".equals(Common.SERVER))
		{
			return PRD_SITE_ID;
		}
		else
		{
			return PRD_SITE_ID;
		}
	}
	
	public static String getSitePw()
	{
		if("Local".equals(Common.SERVER))
		{
			return DEV_SITE_PW;
		}
		else if("SSCRMD01".equals(Common.SERVER))
		{
			return DEV_SITE_PW;
		}
		else if("SSCRMQ01".equals(Common.SERVER) || "SSCRMP01".equals(Common.SERVER))
		{
			return PRD_SITE_PW;
		}
		else
		{
			return PRD_SITE_PW;
		}
	}
	
	public static String getProxySet()
	{
		if("Local".equals(Common.SERVER))
		{
			return "";
//			return "true";
		}
		else
		{
			return "";
//			return "true";
		}
	}
	
	public static String getProxyHost()
	{
		if("Local".equals(Common.SERVER))
		{
			return "";
//			return "55.200.250.10";
		}
		else
		{
			return "";
//			return "55.200.250.10";
		}
	}
	
	public static String getProxyPort()
	{
		if("Local".equals(Common.SERVER))
		{
			return "";
//			return "8080";
		}
		else
		{
			return "";
//			return "8080";
		}
	}
}
