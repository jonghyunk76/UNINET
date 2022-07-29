package stw.eai.utility;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import com.siebel.data.SiebelPropertySet;
import com.siebel.eai.SiebelBusinessService;
import com.siebel.eai.SiebelBusinessServiceException;


public class STWUtil extends SiebelBusinessService {

	@Override
	public void doInvokeMethod(String methodName, SiebelPropertySet inputs,	SiebelPropertySet outputs) throws SiebelBusinessServiceException
	{
		if("FileToBin".equals(methodName))
		{
			fileToBin(inputs,outputs);
		}
	}
	
	private void fileToBin(SiebelPropertySet inputs, SiebelPropertySet outputs)
	{
		String				filePath	= inputs.getProperty("FilePath");
		int					filesize;
		
		FileInputStream		stream		= null;
		StringBuffer		sb			= null;
		BufferedInputStream	bis			= null;
		
		outputs.setProperty("Result", "");
		try {
			filesize= Integer.parseInt(inputs.getProperty("FileSize"))+1;
			sb		= new StringBuffer();
			
			stream	= new FileInputStream(filePath);
			bis		= new  BufferedInputStream(stream);
			
			int data = 0;
			int count	= 0;
			while((data = bis.read()) != -1)
			{
				count ++;
				if(count < filesize)
				{
					sb.append(Integer.toHexString(data));
				}
				else
				{
					throw(new Exception("File Size is Over"));
				}
			}
			
			bis		.close();
			stream	.close();
			outputs.setValue(sb.toString());
			outputs.setProperty("Result", "Success");
		} catch (Exception e) {
			outputs.setProperty("Result", e.getMessage());
		}
		finally
		{
			try {
				bis		.close();
				stream	.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stream	= null;
			sb		= null;
			bis		= null;
		}
	}
}
