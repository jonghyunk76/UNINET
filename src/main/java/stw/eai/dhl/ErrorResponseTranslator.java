package stw.eai.dhl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import stw.eai.common.Common;
import stw.eai.dhl.xml.datatypes.Condition;
import stw.eai.dhl.xml.datatypes.ErrorResponse;
import stw.eai.dhl.xml.datatypes.Status;
import stw.eai.dhl.xml.response.ErrorResponseMsg;
import stw.eai.dhl.xml.response.ShipValidErrorResponseMsg;

public class ErrorResponseTranslator {

	public static String output (String input) {
		StringReader errprResponseXML = new StringReader(input.toString());
		return ErrorResponseTranslator.output(errprResponseXML);
	}
	
	public static String output (File input) {
		String outputMsg = "";
		try {
			Reader errprResponseXML = new java.io.FileReader(input);		
			outputMsg = ErrorResponseTranslator.output(errprResponseXML);
		} catch (FileNotFoundException e) {
			outputMsg = "Error In Pasing ErrorResponse - FileNotFoundException";
		}
		return outputMsg;
	}
	
	public static String output(Reader errprResponseXML){
		String outputMsg = "";
		try {
			Unmarshaller unmarshaller = JAXBContext.newInstance(Common.XML_CONTEXT_PATH).createUnmarshaller();
			ErrorResponseMsg errResponseRT = (ErrorResponseMsg) unmarshaller.unmarshal(errprResponseXML);
			ErrorResponse errResponse = errResponseRT.getResponse();
			Status errResponseStatus = errResponse.getStatus();
			List<Condition> slist = errResponseStatus.getCondition();
			outputMsg = errResponseStatus.getActionStatus() + " : ";
			for(int i=0; i<slist.size();i++){
				Condition condition = (Condition)slist.get(i);
				outputMsg = outputMsg + condition.getConditionCode() + "(Error Code) | " + condition.getConditionData() + " \n" ;
			}			
		} catch (Exception e) {
			outputMsg = "Error In Pasing ErrorResponse";
		}
		return outputMsg;
	}
}
