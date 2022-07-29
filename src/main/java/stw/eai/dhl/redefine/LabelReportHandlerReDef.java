/**
 * @author ally
 * This Class ReDefine com.dhl.sop.label.LabelReportHandler
 * Modify Listing
 * 1. private static void generate(...) => public String generate(...)
 * 2. ArchiveLabelReportController => ArchiveLabelReportControllerReDef
 */
package stw.eai.dhl.redefine;

import java.io.File;
import java.util.ArrayList;

import com.dhl.ShipmentValidateResponse;
import com.dhl.sop.label.PdfMerger;

public class LabelReportHandlerReDef {

	public String generate(String xmlPath, String pdfPath) throws Exception{
    	ShipmentValidateResponse shipmentValidateResponse = LabelReportControllerReDef.generateSOPLabel(xmlPath,pdfPath);
    	String awbNumber = shipmentValidateResponse.getAirwayBillNumber();
    	
	    if(awbNumber != null && !"".equals(awbNumber.trim()) ) {
	    	
	    	//BEGIN :: Pavan Kumar (TechMahindra) | 25-JUL-2013 | XMLPI 4.7 | Archive document image output option
	    	//Updated below from ArchiveLabel to ArchiveDocument and GlobalLabel to TransportLabel
//	    	System.out.println("SOP compliant label generated :" + pdfPath+"TransportLabel_"+awbNumber+".pdf");
//	    	System.out.println("Generating Archive document");
	    	ArchiveLabelReportControllerReDef.generateArchiveLabel(xmlPath,pdfPath, shipmentValidateResponse);
//	    	System.out.println("Archive document generated :" + pdfPath+"ArchiveDocument_"+awbNumber+".pdf");
	        
	        ArrayList<String> argumnets= new ArrayList<String>(); 
	    
	        argumnets.add(pdfPath+"TransportLabel_"+awbNumber+".pdf");
	        argumnets.add(pdfPath+"ArchiveDocument_"+awbNumber+".pdf");
	        argumnets.add(pdfPath+awbNumber+".pdf");
	        PdfMerger.getConcatenatedPdf(argumnets);
	        
	     // Edited by Victor Low K.C. in 12 July 2012. Create 2 new ArrayList 
	    	ArrayList<String> archive= new ArrayList<String>(); 
	    	ArrayList<String> global= new ArrayList<String>();
		    
	    	// Add in 1st String is the path of generated archive label in pdf format, 2nd String to rename the file.
	    	archive.add(pdfPath+"ArchiveDocument_"+awbNumber+".pdf");
	        archive.add(pdfPath+"Archive_Document_"+awbNumber+".pdf");
	        
	    	// Add in 1st String is the path of generated global label in pdf format, 2nd String to rename the file.
	        global.add(pdfPath+"TransportLabel_"+awbNumber+".pdf");
	        global.add(pdfPath+"Transport_Label_"+awbNumber+".pdf");
	        
	        PdfMerger.getConcatenatedPdf(archive);
	        PdfMerger.getConcatenatedPdf(global);
	        // Editing ends here
	        
	        String globalLabelFile = pdfPath+"TransportLabel_"+awbNumber+".pdf";
	        String archiveLabelFile = pdfPath+"ArchiveDocument_"+awbNumber+".pdf";
	        
	        // A File object to represent the filename
	        File globalFile = new File(globalLabelFile);
	        File archiveFile = new File(archiveLabelFile);
	
	        // Attempt to delete it
	        //mary 23th May 2012 - commented to remove the original PDFs
	        globalFile.delete();
	        archiveFile.delete();
	        
	      //END :: Pavan Kumar (TechMahindra) | 25-JUL-2013 | XMLPI 4.7 | Archive document image output option
	        
    	} else {
    		//Updated below from GlobalLabel to TransportLabel :: Pavan Kumar (TechMahindra) | 25-JUL-2013 | XMLPI 4.7 | Archive document image output option
    		String transportLabelFile = pdfPath+"TransportLabel_"+awbNumber+".pdf";
    		File transportFile = new File(transportLabelFile);
    		transportFile.delete();
    		throw new Exception("Awbnumber is empty");
    	} 
	    
	    return awbNumber;
	}
	
}
