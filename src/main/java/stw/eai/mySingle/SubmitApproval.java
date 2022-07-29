package stw.eai.mySingle;

import java.io.File;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.xml.rpc.ServiceException;

import mySingle.service.APSubmitServiceLocator;
import samsung.esb.approval.service.APSubmitService;
import samsung.esb.approval.vo.AttachmentWSVO;
import samsung.esb.approval.vo.RouteWSVO;
import samsung.esb.approval.vo.StartProcessWSVO;
import samsung.esb.common.vo.ESBAuthVO;
import samsung.esb.common.vo.ESBFaultVO;
import stw.eai.common.Common;

import com.siebel.data.SiebelPropertySet;
import com.siebel.eai.SiebelBusinessServiceException;

public class SubmitApproval implements Client {

	@Override
	public void request(SiebelPropertySet inputs, SiebelPropertySet outputs) throws SiebelBusinessServiceException {
		APSubmitServiceLocator	serviceLocator	= null;
		APSubmitService			clientStub		= null;

		ESBAuthVO			esbAuthVO			= null;
		StartProcessWSVO	startProcessWSVO	= null;
		RouteWSVO[]			routeVOs			= null;
		RouteWSVO			routeVO				= null;
		AttachmentWSVO[]	attachmentVOs		= null;
		AttachmentWSVO		attachmentVO		= null;

		File				file				= null;
		FileDataSource		fds					= null;
		DataHandler			dh					= null;

		SiebelPropertySet	siebelMessage		= null;
		SiebelPropertySet	psSubmitApproval	= null;
		SiebelPropertySet	psSubmitApprovalResp= null;
		SiebelPropertySet	psStartProcessWSVO	= null;

		ArrayList<SiebelPropertySet>	psRouteVOs	= null;
		ArrayList<SiebelPropertySet>	psAttchVOs	= null;

		String error = "";

		outputs.setProperty("Error", "");
		outputs.setProperty("Result", "");
		try {
			// Inputs
			siebelMessage		= Common.getChild	(inputs, 			"SiebelMessage");
			psSubmitApproval	= Common.getChild	(siebelMessage, 	"submitApproval");
			psStartProcessWSVO	= Common.getChild	(psSubmitApproval,	"startProcessWSVO");
			psRouteVOs			= Common.getChilds	(psStartProcessWSVO,"RouteVOs");
			psAttchVOs			= Common.getChilds	(psStartProcessWSVO,"AttachmentVOs");
			// == VO 생성 ==

			// esbAuthVO
			esbAuthVO			= new ESBAuthVO(Common.CONN_ID_AP,Common.CONN_PW_AP);

			// startProcessWSVO
			startProcessWSVO	= new StartProcessWSVO();
			startProcessWSVO.setBody			(psStartProcessWSVO.getProperty("Body"));
			startProcessWSVO.setBodyType		(psStartProcessWSVO.getProperty("BodyType"));
			startProcessWSVO.setCreateDate		(psStartProcessWSVO.getProperty("CreateDate"));
			startProcessWSVO.setDrmOptionInfo	(psStartProcessWSVO.getProperty("DrmOptionInfo"));
			startProcessWSVO.setLocaleInfo		(psStartProcessWSVO.getProperty("LocaleInfo"));
			startProcessWSVO.setSystemID		(psStartProcessWSVO.getProperty("SystemID"));
			startProcessWSVO.setMisID			(psStartProcessWSVO.getProperty("MisID"));
			startProcessWSVO.setNotiMail		(psStartProcessWSVO.getProperty("NotiMail"));
			startProcessWSVO.setPriority		(psStartProcessWSVO.getProperty("Priority"));
			startProcessWSVO.setSecurity		(psStartProcessWSVO.getProperty("Security"));
			startProcessWSVO.setTimeZone		(psStartProcessWSVO.getProperty("TimeZone"));
			startProcessWSVO.setTitle			(psStartProcessWSVO.getProperty("Title"));
			startProcessWSVO.setSendDMS			("Y".equals(psStartProcessWSVO.getProperty("SendDMS")));

			// routeVOs
			routeVOs	= new RouteWSVO[psRouteVOs.size()];
			for(int i = 0 ; i < routeVOs.length ; i++)
			{
				routeVO = new RouteWSVO();
				routeVO.setActionType	(psRouteVOs.get(i).getProperty("ActionType"));
				routeVO.setActivity		(psRouteVOs.get(i).getProperty("Activity"));
				routeVO.setAlarmType	(psRouteVOs.get(i).getProperty("AlarmType"));
				routeVO.setApproved		(psRouteVOs.get(i).getProperty("Approved"));
				routeVO.setArbitrary	(psRouteVOs.get(i).getProperty("Arbitrary"));
				routeVO.setArrived		(psRouteVOs.get(i).getProperty("Arrived"));
				routeVO.setBodyModify	(psRouteVOs.get(i).getProperty("BodyModify"));
				routeVO.setCompCode		(psRouteVOs.get(i).getProperty("CompCode"));
				routeVO.setCompName		(psRouteVOs.get(i).getProperty("CompName"));
				routeVO.setDeptCode		(psRouteVOs.get(i).getProperty("DeptCode"));
				routeVO.setDeptName		(psRouteVOs.get(i).getProperty("DeptName"));
				String duration			= psRouteVOs.get(i).getProperty("Duration");
				routeVO.setDuration		("".equals(duration)?"0":duration);
				routeVO.setDuty			(psRouteVOs.get(i).getProperty("Duty"));
				routeVO.setDutyCode		(psRouteVOs.get(i).getProperty("DutyCode"));
				routeVO.setGroupCode	(psRouteVOs.get(i).getProperty("GroupCode"));
				routeVO.setGroupName	(psRouteVOs.get(i).getProperty("GroupName"));
				routeVO.setMailAddress	(psRouteVOs.get(i).getProperty("MailAddress"));
				routeVO.setOpinion		(psRouteVOs.get(i).getProperty("Opinion"));
				routeVO.setReserved		(psRouteVOs.get(i).getProperty("Reserved"));
				routeVO.setRouteModify	(psRouteVOs.get(i).getProperty("RouteModify"));
				routeVO.setSequence		(psRouteVOs.get(i).getProperty("Sequence"));
				routeVO.setUserID		(psRouteVOs.get(i).getProperty("UserID"));
				routeVO.setUserName		(psRouteVOs.get(i).getProperty("UserName"));
				routeVO.setDelegated	(psRouteVOs.get(i).getProperty("Delegated"));
				routeVO.setSocialID		(psRouteVOs.get(i).getProperty("SocialID"));

				routeVOs[i] = routeVO;
			}
			startProcessWSVO.setRouteVOs(routeVOs);

			// attachmentVOs
			attachmentVOs	= new AttachmentWSVO[psAttchVOs.size()];
			for(int i = 0 ; i < attachmentVOs.length ; i++)
			{
				attachmentVO = new AttachmentWSVO();

				file	= new File(psAttchVOs.get(i).getProperty("file"));
				fds		= new FileDataSource(file);
				dh		= new DataHandler(fds);

				attachmentVO.setFile(dh);
				attachmentVO.setFileName(psAttchVOs.get(i).getProperty("FileName"));
				attachmentVO.setSequence(Integer.toString(i));

				attachmentVOs[i] = attachmentVO;
			}
			startProcessWSVO.setAttachmentVOs(attachmentVOs);

			// Port 개방
			System.out.println("== Request ==");

			serviceLocator	= new APSubmitServiceLocator();

			String result = "";

			clientStub		= serviceLocator.getAPSubmitService_InboundPort();
			result = clientStub.submitApproval(esbAuthVO, startProcessWSVO);


			outputs.setProperty("Error", error);
			outputs.setProperty("Result", result);
			System.out.println(result);

			siebelMessage			= new SiebelPropertySet();
			psSubmitApprovalResp	= new SiebelPropertySet();

			siebelMessage.setType("SiebelMessage");

			psSubmitApprovalResp.setType("submitApprovalResponse");
			psSubmitApprovalResp.setProperty("result",result);
			siebelMessage.addChild(psSubmitApprovalResp);

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
			startProcessWSVO	= null;
			routeVOs			= null;
			routeVO				= null;
			attachmentVOs		= null;
			attachmentVO		= null;

			file				= null;
			fds					= null;
			dh					= null;

			siebelMessage		= null;
			psSubmitApproval	= null;
			psStartProcessWSVO	= null;

			psRouteVOs			= null;
			psAttchVOs			= null;
		}
	}

}
