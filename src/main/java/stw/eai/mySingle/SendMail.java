package stw.eai.mySingle;

import java.io.File;
import java.rmi.RemoteException;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.xml.rpc.ServiceException;

import mySingle.service.MLSendServiceLocator;

import samsung.esb.common.vo.ESBAuthVO;
import samsung.esb.common.vo.ESBFaultVO;
import samsung.esb.mail.service.MLSendService;
import samsung.esb.mail.vo.AttachEtyCSVO;
import samsung.esb.mail.vo.HeaderHelperCSVO;
import samsung.esb.mail.vo.RecipientEtyCSVO;
import samsung.esb.mail.vo.ResourceCSVO;
import stw.eai.common.Common;

import com.siebel.data.SiebelPropertySet;
import com.siebel.eai.SiebelBusinessServiceException;

public class SendMail implements Client {
	
	@Override
	public void request(SiebelPropertySet inputs,SiebelPropertySet outputs) throws SiebelBusinessServiceException{
		MLSendServiceLocator serviceLocator		= null;
		MLSendService clientStub				= null;

		ESBAuthVO			esbAuthVO			= null;
		HeaderHelperCSVO	headerHelperCSVO	= null;
		RecipientEtyCSVO[]	recipientEtyCSVO	= null;
		RecipientEtyCSVO	recipientVO			= null;
		AttachEtyCSVO[]		attachEtyCSVO		= null;
		AttachEtyCSVO		attachEtyVO			= null;
		ResourceCSVO		resourceCSVO		= null;

		File			file	= null;
		FileDataSource	fds		= null;
		DataHandler		dh		= null;

//		SiebelPropertySet RequestMsg	= null;
//		SiebelPropertySet EAIMasterMsg	= null;
		SiebelPropertySet sendMISMail	= null;
		SiebelPropertySet headerHelper	= null;
		SiebelPropertySet attachEty		= null;
		SiebelPropertySet recipientEty	= null;
		SiebelPropertySet resource		= null;

		SiebelPropertySet siebelMessage	= null;
		SiebelPropertySet sendMailResp	= null;
//		SiebelPropertySet psResult		= null;
		
		String error = "";
		
		outputs.setProperty("Error", "");

		try
		{
			siebelMessage	= Common.getChild(inputs, "SiebelMessage");
			sendMISMail		= Common.getChild(siebelMessage, "sendMISMail");

			headerHelper	= Common.getChild(sendMISMail,	"HeaderHelperCSVO");
			attachEty		= Common.getChild(sendMISMail,	"AttachEtyCSVO");
			recipientEty	= Common.getChild(sendMISMail,	"RecipientEtyCSVO");
			resource		= Common.getChild(sendMISMail,	"ResourceCSVO");
			
			// Port ����
			serviceLocator	= new MLSendServiceLocator();
			clientStub		= serviceLocator.getMLSendService_InboundPort();
			
			// == VO ���� ==

			// esbAuthVO
			esbAuthVO			= new ESBAuthVO(Common.CONN_ID_ML,Common.CONN_PW_ML);

			// headerHelperCSVO
			headerHelperCSVO	= new HeaderHelperCSVO();
			headerHelperCSVO.setSenderMailAddr		(headerHelper.getProperty("senderMailAddr"));
			headerHelperCSVO.setMsgType				(headerHelper.getProperty("msgType"));
			headerHelperCSVO.setBHtmlContentCheck	(headerHelper.getProperty("bHtmlContentCheck").equals("Y"));
			headerHelperCSVO.setIFileCount			(attachEty.getChildCount());
			headerHelperCSVO.setIsDst				(headerHelper.getProperty("isDst").equals("Y"));
			headerHelperCSVO.setTimeZone			(headerHelper.getProperty("timeZone"));
			headerHelperCSVO.setSubject				(headerHelper.getProperty("subject"));

			// recipientVO
			recipientEtyCSVO	= new RecipientEtyCSVO[recipientEty.getChildCount()];

			for(int i = 0 ; i < recipientEty.getChildCount() ; i++)
			{
				recipientVO	= new RecipientEtyCSVO();
				recipientVO.setISeqID	(i);
				recipientVO.setRecType	(recipientEty.getChild(i).getProperty( "recType"));
				recipientVO.setRecAddr	(recipientEty.getChild(i).getProperty( "recAddr"));

				recipientEtyCSVO[i] = recipientVO;
			}

			// attachEtyCSVO
			attachEtyCSVO		= new AttachEtyCSVO[attachEty.getChildCount()];

			for(int i = 0 ; i < attachEty.getChildCount() ; i++)
			{
				file	= new File(attachEty.getChild(i).getProperty( "file"));
				fds		= new FileDataSource(file);
				dh		= new DataHandler(fds);

				attachEtyVO	= new AttachEtyCSVO();
				attachEtyVO.setFile(dh);
				attachEtyVO.setFileName(attachEty.getChild(i).getProperty( "fileName"));
				attachEtyVO.setISeqID(i);

				attachEtyCSVO[i] = attachEtyVO;
			}

			// resourceCSVO
			resourceCSVO		= new ResourceCSVO();

			resourceCSVO.setEmail	(resource.getProperty(	"email"));
			resourceCSVO.setEncoding(resource.getProperty(	"encoding"));
			resourceCSVO.setLocale	(resource.getProperty(	"locale"));
			resourceCSVO.setPassword(resource.getProperty(	"password"));

			// == VO ���� �Ϸ� ==
			System.out.println("SendMail : "+ Common.MYSINGLE_SERVER_ADDR());
			// Send request
			String result = clientStub.sendMISMail(	esbAuthVO,										
					sendMISMail.getProperty("BodyStr"), // ����     "<![CDATA["+""+"]]>"
					headerHelperCSVO,
					recipientEtyCSVO,
					attachEtyCSVO,
					resourceCSVO);
			System.out.println(result);

			siebelMessage	= new SiebelPropertySet();
			sendMailResp	= new SiebelPropertySet();
			//psResult		= new SiebelPropertySet();

			siebelMessage	.setType("SiebelMessage");
			//			siebelMessage	.setProperty("xmlns:ns",		"http://samsung/esb/mail/service");
			//			siebelMessage	.setProperty("MessageId",		"");
			//			siebelMessage	.setProperty("MessageType",		"Integration Object");
			//			siebelMessage	.setProperty("IntObjectName",	"sendMISMailResponse");
			//			siebelMessage	.setProperty("IntObjectFormat",	"Siebel Hierarchical");

			sendMailResp	.setType("sendMISMailResponse");
			sendMailResp	.setProperty("result", result);
			//psResult		.setType("result");

			//psResult.setValue(result);

			//sendMailResp	.addChild(psResult);
			siebelMessage	.addChild(sendMailResp);
			
			outputs.addChild(siebelMessage);
		} catch (ServiceException e) {
			error += "ServiceException \n" + e.getMessage();
			outputs.setProperty("Error", error);
			throw new SiebelBusinessServiceException(e, e.getMessage(), e.getMessage());
		} catch (ESBFaultVO e) {
			e.printStackTrace();
			error += "ESBFaultVO : [" + e.getFaultCode1() + "] "+ e.getFaultMessage();
			outputs.setProperty("Error", error);
			System.out.println(error);
			throw new SiebelBusinessServiceException(e, e.getFaultCode1(), e.getFaultMessage());
		} catch (RemoteException e) {
			error += "RemoteException \n" + e.getMessage();
			outputs.setProperty("Error", error);
			throw new SiebelBusinessServiceException(e, e.getMessage(), e.getMessage());
		} catch	(Exception e){
			error += "Exception \n" + e.getMessage();
			outputs.setProperty("Error", error);
			throw new SiebelBusinessServiceException(e, e.getMessage(), e.getMessage());
		}
		finally
		{
			serviceLocator		= null;
			clientStub			= null;

			esbAuthVO			= null;
			headerHelperCSVO	= null;
			recipientEtyCSVO	= null;
			recipientVO			= null;
			attachEtyCSVO		= null;
			attachEtyVO			= null;
			resourceCSVO		= null;

			file	= null;
			fds		= null;
			dh		= null;

//			RequestMsg		= null;
//			EAIMasterMsg	= null;
			sendMISMail		= null;
			headerHelper	= null;
			attachEty		= null;
			recipientEty	= null;
			resource		= null;

			siebelMessage	= null;
			sendMailResp	= null;
//			psResult		= null;
		}
	}
}