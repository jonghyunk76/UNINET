/**
 * @author ally
 * This Class ReDefine DHLClient(DHL Offered Test Class)
 * Modify Listing
 * 1. DHLClient Constructor => public String getShipValidResponseXML() throws Exception
 * 2. private static void fileWriter(...) => private static String fileWriter(...) throws Exception
 */
package stw.eai.dhl.redefine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DHLClientReDef {
	
	/**
	 * Private method to write the response from the input stream to a file in local directory.
	 * @param     strResponse  The string format of the response XML message
	 * @return    responseFile AbsolutePath
	 **/
	private static String fileWriter(String strResponse , String responseMessagePath, boolean isUTF8Support) throws Exception{

		DateFormat today = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss_SSS");
		String responseFileName = checkForRootElement(strResponse, isUTF8Support)+"_"+today.format(new java.util.Date());

		String ufn = responseMessagePath + responseFileName;
		File resFile = new File(ufn+".xml");
		System.out.println("ufn::::::::::::" + ufn);

		int i=0;
		try {
			//create file and if it already exits
			//if file exist add counter to it
			while(!resFile.createNewFile()){
				resFile = new File(ufn + "_" + (++i) +".xml");
			}

			Writer out = null;
			if(isUTF8Support) {
				out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(resFile), "UTF8"));
			} else {
				out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(resFile)));
			}

			try {
				out.write(strResponse);
			} finally {
				out.close();
			}

//			System.out.println("Response received and saved successfully at :" + responseMessagePath +"\n");
//			System.out.println("The file name is :" + resFile.getName());
		}catch(Exception e){
//			System.err.println(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return resFile.getAbsolutePath();
	}// end of  fileWriter method

	/**
	 * Returns the value of the root element of the response XML message send by DHL Server
	 * @param     strResponse  The string format of the response XML message
	 * @return      name of the root element of type string
	 * @throws Exception 
	 **/
	private static String checkForRootElement(String strResponse, boolean isUTF8Support) throws Exception{
		Element element = null;
		try{
			byte [] byteArray = null;
			if(isUTF8Support) {
				byteArray = strResponse.trim().getBytes("UTF-8");
			} else {
				byteArray = strResponse.trim().getBytes();
			}

			ByteArrayInputStream baip = new ByteArrayInputStream( byteArray);
			DocumentBuilderFactory factory       = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = factory.newDocumentBuilder();
			Document doc = documentBuilder.parse(baip); //Parsing the inputstream
			element = doc.getDocumentElement(); //getting the root element           

		}
		catch(Exception e){
			throw new Exception("Exception in Reponse checkForRootElement "+e.getMessage());
		}
		String rootElement = element.getTagName();
		//Check if root element has res: as prefix

		if(rootElement.startsWith("res:")||rootElement.startsWith("req:")||rootElement.startsWith("err:")||rootElement.startsWith("edlres:")||rootElement.startsWith("ilres:")){

			int index = rootElement.indexOf(":");

			rootElement = rootElement.substring(index+1);
		}
		return rootElement; // returning the value of the root element
	} //end of checkForRootElement method

	/**
	 * Change DHLClient Constructor to getShipValidResponseXML Method
	 * Create ShipmentValidateResponse.xml 
	 * @param requestMessagePath  The path of the request XML message to be send to server
	 * @param httpURL The http URL to connect ot the server (e.g. http://<ip address>:<port>/application name/Servlet name)
	 * @param responseMessagePath The path where the response XML message is to be stored
	 * @return ShipmentValidateResponse.xml AbsolutePath
	 * @throws Exception
	 */
	public String getShipValidResponseXML(String requestMessagePath, String httpURL, String responseMessagePath) throws Exception{
		String reposneFilePath = null;
		try{
			//Preparing file inputstream from a file    
			String clientRequestXML = null;

			InputStream fis = new FileInputStream(requestMessagePath);

			InputStreamReader	reader = new 	InputStreamReader(fis, "UTF-8")  ;

			int ilength = fis.available();

			char[] c = new char[ilength];

			int i = reader.read(c);
			clientRequestXML = new String(c);

			boolean isUTF8Support = utfEnable(clientRequestXML); 

			if(!isUTF8Support) {
				reader.close();
				fis.close();
				InputStream utfFis = new FileInputStream(requestMessagePath);
				InputStreamReader utfReader = new 	InputStreamReader(utfFis);
				ilength = utfFis.available();
				c = new char[ilength];
				i = utfReader.read(c);
				clientRequestXML = new String(c);
				utfReader.close();
				utfFis.close();
			} 

			//Getting size of the stream
			/* Preparing the URL and opening connection to the server*/
			URL servletURL = null;
			if(isUTF8Support) {
				String query = "isUTF8Support=true";
				servletURL = new URL(httpURL + "?" + query);
			} else {
				servletURL = new URL(httpURL);
			}

			HttpURLConnection servletConnection = null;
			servletConnection = (HttpURLConnection)servletURL.openConnection();

			servletConnection.setDoOutput(true);  // to allow us to write to the URL
			servletConnection.setDoInput(true);
			servletConnection.setUseCaches(false); 
			servletConnection.setRequestMethod("POST");
			servletConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			if(isUTF8Support) {
				servletConnection.setRequestProperty("Accept-Charset", "UTF-8");
			} 
			String len = Integer.toString(clientRequestXML.getBytes().length);
			servletConnection.setRequestProperty("Content-Length", len);						

			servletConnection.connect();

			OutputStreamWriter wr = null;
			if(isUTF8Support) {
				wr = new OutputStreamWriter(servletConnection.getOutputStream(), "UTF8");
			} else {
				wr = new OutputStreamWriter(servletConnection.getOutputStream());
			}

			wr.write(clientRequestXML);
			wr.flush();
			wr.close();

			/*Code for getting and processing response from DHL's servlet*/
			InputStreamReader isr = null;
			if(isUTF8Support) {
				isr = new InputStreamReader(servletConnection.getInputStream(),"UTF8");
			} else {
				isr = new InputStreamReader(servletConnection.getInputStream());
			}

			BufferedReader rd = new BufferedReader(isr);
			StringBuilder result = new StringBuilder();
			String line = "";

			while ((line = rd.readLine()) != null) {
				result.append(line).append("\n");
			}
			
			//Calling filewriter to write the response to a file
			reposneFilePath = fileWriter(result.toString() , responseMessagePath, isUTF8Support);
		}catch(MalformedURLException mfURLex){
			//System.out.println("MalformedURLException "+mfURLex.getMessage());
			throw new Exception("Exception in Reponse Check URL" + mfURLex.getMessage());
		}catch(IOException e){
			e.printStackTrace();
			System.out.println("Exception in Reponse IOException "+e.getMessage());
			throw new Exception("Exception in Reponse IOException "+e.getMessage());
		}

		return reposneFilePath;
	}

	private boolean utfEnable(String clientRequestXML) {
		boolean isUTF8Support = false;
		List<String> utf8EnableList = new ArrayList<String>();
		utf8EnableList.add("ShipmentValidateRequest");
		utf8EnableList.add("ShipmentValidateRequestAP");
		utf8EnableList.add("ShipmentValidateRequestEA");
		utf8EnableList.add("ShipmentRequest");
		utf8EnableList.add("KnownTrackingRequest");

		String rootElement = getRootElement(clientRequestXML);

		if(utf8EnableList.contains(rootElement)) {
			isUTF8Support = true;
		}
		return isUTF8Support;
	}

	private String getRootElement( String message) {

		try	{
			String rootElement = null;

			StringTokenizer st = new StringTokenizer(message.trim(),"<>" ,true);

			String value = null;
			int index  = 0;
			while (st.hasMoreTokens())	{
				value = st.nextToken();

				if ( value.equals("<") ){
					rootElement = st.nextToken();

					if (!rootElement.startsWith("?") && !rootElement.startsWith("!")) {
						index = rootElement.indexOf(" ");
						if (index != -1) {
							rootElement = rootElement.substring(0, index);
						}
						index = rootElement.indexOf(":");
						if (index != -1){			
							rootElement = rootElement.substring(index + 1);
						}
						return rootElement;
					}
				}
			}
		}
		catch (Exception e)	{
			e.printStackTrace();
		}
		return "fail";
	}
}
