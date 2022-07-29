package stw.eai.hwt.paypal.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HTWPayPalLog {
	
	private StringBuffer fileLog;	
	private long startTime;
	private long endTime;
	private boolean isLogWriteFlag = true;
	
	public void initLog(boolean logFlag){
		isLogWriteFlag	= logFlag;
		fileLog 		= new StringBuffer();
		startTime 		= System.currentTimeMillis();
		endTime 		= 0;
		
	}
	
	public void addLog(String logMessage){
		
		fileLog.append("["+getTimeFormat(System.currentTimeMillis(), true)+"] : ["+logMessage+"] \r\n");
		
	}

	public void writeLog(){

		if( !isLogWriteFlag ){
			return;
		}
		
		FileWriter fileWriter = null;
		BufferedWriter out = null;
		File folderFile = null;
		endTime = System.currentTimeMillis();
		
	    try {
	    	
	    	String folderPath = "C:\\PayPal\\Paypal_log";			
	    	String fileName = "paypallog_"+getTimeFormat(System.currentTimeMillis(),false)+".txt";
			folderFile = new File(folderPath);
			
			if(folderFile.exists() == false) {
				folderFile.setExecutable(false, true);
				folderFile.setReadable(true);
				folderFile.setWritable(false, true);	
				folderFile.mkdirs();
			}
			
			fileWriter = new FileWriter(folderPath+"\\"+fileName);
	        out = new BufferedWriter(fileWriter);
	        String s = fileLog.toString();
	        
	        out.write("******************************************");out.newLine();
	        out.write("* [Start Time] : " + getTimeFormat(startTime,true));out.newLine();	        
	        out.write("* [End   Time] : " + getTimeFormat(endTime,true));out.newLine();	        
	        out.write("******************************************");out.newLine();
	        out.write("* [Log Message]");out.newLine();
	        out.write("******************************************");out.newLine();
	        out.write(s);out.newLine();
	        out.write("******************************************");
	        //out.close();
	        
	      } catch (Exception e) {
	    	  // Nothing~~~~
	          //try{ if(out != null)			{ out.close(); } 		} catch (Exception e1){ out = null; }
	          //try{ if(fileWriter != null)	{ fileWriter.close(); } } catch (Exception e2){ fileWriter = null; }
	      } finally {
	    	  folderFile = null;	    	  
	          try{ if(out != null)			{ out.close(); } 		} catch (Exception e1){ out = null; }
	          try{ if(fileWriter != null)	{ fileWriter.close(); } } catch (Exception e2){ fileWriter = null; }
	      }
	}
	
	public String getTimeFormat(long time, boolean useFormatStr){		
		
		SimpleDateFormat format = null;
		if ( useFormatStr == true ){
			format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
		} else {
			format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		}
		return format.format(new Date(time));
		
	}
	
}