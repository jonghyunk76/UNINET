package stw.eai.dhl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import stw.eai.common.Common;
import stw.eai.dhl.xml.request.RoutingRequestEA;

public class RoutingProcessor {

	public static Object processing(RoutingRequestEA routing) throws JAXBException, IOException  {

		/*1. convert JAVA to Request_XML*/
		JAXBContext jc = JAXBContext.newInstance(Common.XML_CONTEXT_PATH);
		Marshaller marshaller = jc.createMarshaller();
		marshaller.setProperty("jaxb.encoding", "UTF-8");
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, new Boolean(true));
		StringWriter sw = new StringWriter();
		marshaller.marshal(routing, sw);
		String requestXML = sw.toString();

		/*2. Connect to Servlet*/
		//			Setup Proxy 
		//          JVM Option -Dhttp.proxyHost=55.200.250.10 -Dhttp.proxyPort=8080
		//			OR
		System.setProperty("proxySet"	,Common.getProxySet());
		System.setProperty("proxyHost"	,Common.getProxyHost());
		System.setProperty("proxyPort"	,Common.getProxyPort());
		URL servletURL = new URL(Common.getServletURL() + "?" + "isUTF8Support=true");
		HttpURLConnection servletConnection = null;
		servletConnection = (HttpURLConnection)servletURL.openConnection();
		servletConnection.setDoOutput(true);  // to allow us to write to the URL
		servletConnection.setDoInput(true);
		servletConnection.setUseCaches(false); 
		servletConnection.setRequestMethod("POST");
		servletConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
		servletConnection.setRequestProperty("Accept-Charset", "UTF-8");
		String len = Integer.toString(requestXML.length());
		servletConnection.setRequestProperty("Content-Length", len);						
		servletConnection.connect();
		OutputStreamWriter wr = null;
		wr = new OutputStreamWriter(servletConnection.getOutputStream(), "UTF8");
		wr.write(requestXML);		
		wr.flush();
		wr.close();

		/*3. getting and processing response from DHL's Servlet*/
		InputStreamReader isr = new InputStreamReader(servletConnection.getInputStream(),"UTF8");
		BufferedReader rd = new BufferedReader(isr);
		StringBuilder result = new StringBuilder();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line).append("\n");
		}

		/*4. convert Response_XML to JAVA*/
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		StringReader responseXML = null;
		responseXML = new StringReader(result.toString());
//		System.out.println("RoutingProcessor : " + result.toString());
		Object unmarshal = unmarshaller.unmarshal(responseXML);
		
		return unmarshal;
	}

}
