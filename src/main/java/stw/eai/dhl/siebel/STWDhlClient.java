package stw.eai.dhl.siebel;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

import stw.eai.common.Common;
import stw.eai.dhl.RoutingProcessor;
import stw.eai.dhl.ShipValidGenerator;
import stw.eai.dhl.ShipValidGeneratorResult;
import stw.eai.dhl.xml.datatypes.Billing;
import stw.eai.dhl.xml.datatypes.Consignee;
import stw.eai.dhl.xml.datatypes.Contact;
import stw.eai.dhl.xml.datatypes.DimensionalUnit;
import stw.eai.dhl.xml.datatypes.DoorTo;
import stw.eai.dhl.xml.datatypes.Dutiable;
import stw.eai.dhl.xml.datatypes.ObjectFactory;
import stw.eai.dhl.xml.datatypes.PackageType;
import stw.eai.dhl.xml.datatypes.Piece;
import stw.eai.dhl.xml.datatypes.Pieces;
import stw.eai.dhl.xml.datatypes.PiecesEnabled;
import stw.eai.dhl.xml.datatypes.Reference;
import stw.eai.dhl.xml.datatypes.ShipmentDetails;
import stw.eai.dhl.xml.datatypes.ShipmentPaymentType;
import stw.eai.dhl.xml.datatypes.Shipper;
import stw.eai.dhl.xml.datatypes.WeightUnit;
import stw.eai.dhl.xml.datatypes.YesNo;
import stw.eai.dhl.xml.request.RoutingRequestEA;
import stw.eai.dhl.xml.request.ShipmentValidateRequestEA;
import stw.eai.dhl.xml.response.ErrorResponseMsg;
import stw.eai.dhl.xml.response.RoutingResponse;

import com.dhl.datatypes.Condition;
import com.siebel.data.SiebelPropertySet;
import com.siebel.eai.SiebelBusinessService;
import com.siebel.eai.SiebelBusinessServiceException;

public class STWDhlClient extends SiebelBusinessService {
	@Override
	public void doInvokeMethod(String methodName, SiebelPropertySet inputs,	SiebelPropertySet outputs) throws SiebelBusinessServiceException {
		Common.SERVER = inputs.getProperty("Server");

		if("Routing".equals(methodName)) {
			Routing(inputs,outputs);
		}
		else if("ShipValid".equals(methodName)) {
			ShipValid(inputs,outputs);
		}
		else{
			outputs.setProperty("ErrorMsg", "MethodName Error");
		}
	}

	public void Routing(SiebelPropertySet inputs, SiebelPropertySet outputs) throws SiebelBusinessServiceException {
		SiebelPropertySet psRouting			= null;
		SiebelPropertySet psSiebelMessage	= null;
		SiebelPropertySet psResponse		= null;
		SiebelPropertySet psServiceHeader	= null;
		SiebelPropertySet psStatus			= null;
		SiebelPropertySet psCondition		= null;
		SiebelPropertySet psServiceArea		= null;

		ObjectFactory factory				= null;
		RoutingRequestEA routing			= null;

		try {
			psSiebelMessage	= Common.getChild(inputs			, "SiebelMessage"); 
			psRouting		= Common.getChild(psSiebelMessage	, "RouteRequest");

			factory = new ObjectFactory();
			routing = factory.createRoutingRequestEA();		
			routing.setRequest			(Common.getDHLRequest());
			routing.setRequestType		(psRouting.getProperty("RequestType")); //legacy RoutingRequestEARequestType.O
			routing.setAddress1			(psRouting.getProperty("Address1")); //legacy asp_con_add1
			routing.setAddress2			(psRouting.getProperty("Address2")); //legacy asp_con_add2
			routing.setAddress2			(psRouting.getProperty("Address3")); //legacy asp_con_add3
			routing.setPostalCode		(psRouting.getProperty("PostalCode")); //legacy asp_con_zip
			routing.setCity				(psRouting.getProperty("City")); //legacy asp_con_city
			//			routing.setDivision			(psRouting.getProperty("Division"));
			routing.setCountryCode		(psRouting.getProperty("CountryCode")); //legacy asp_con_cc
			routing.setCountryName		(psRouting.getProperty("CountryName")); //legacy asp_con_cn
			routing.setOriginCountryCode(psRouting.getProperty("OriginCountryCode")); //legacy asp_con_cc

			RoutingResponse responseMsg = null;
			ErrorResponseMsg errResponseMsg = null;
			Object response = RoutingProcessor.processing(routing);
			if(response instanceof RoutingResponse){
				responseMsg	= (RoutingResponse)response;
				
				psSiebelMessage	= new SiebelPropertySet();
				psSiebelMessage.setType("SiebelMessage");

				psRouting	= new SiebelPropertySet();
				psRouting.setType("RouteResponse");
				psRouting.setProperty("Result"				,	"SUCCESS");
				psRouting.setProperty("RegionCode"				,responseMsg.getRegionCode());
				psRouting.setProperty("GMTNegativeIndicator"	,responseMsg.getGMTNegativeIndicator());
				psRouting.setProperty("GMTOffset"				,responseMsg.getGMTOffset());
				psSiebelMessage.addChild(psRouting);

				psResponse		= new SiebelPropertySet();
				psResponse.setType("Response");
				psRouting.addChild(psResponse);

				psServiceHeader	= new SiebelPropertySet();
				psServiceHeader.setType("ServiceHeader");
				psServiceHeader.setProperty("MessageTime"		,responseMsg.getResponse().getServiceHeader().getMessageTime().toString());
				psServiceHeader.setProperty("SiteID"			,responseMsg.getResponse().getServiceHeader().getSiteID());
				psServiceHeader.setProperty("MessageReference"	,responseMsg.getResponse().getServiceHeader().getMessageReference());
				psResponse.addChild(psServiceHeader);
				
				psStatus		= new SiebelPropertySet();
				psStatus.setType("Status");
				psStatus.setProperty("ActionStatus", "");
				psResponse.addChild(psStatus);
				
				psCondition		= new SiebelPropertySet();
				psCondition.setType("Condition");
				psCondition.setProperty("ConditionCode", "");
				psCondition.setProperty("ConditionData", "");
				psStatus.addChild(psCondition);

				psServiceArea	= new SiebelPropertySet();
				psServiceArea.setType("ServiceArea");
				psServiceArea.setProperty("ServiceAreaCode"		,responseMsg.getServiceArea().getServiceAreaCode());
				psServiceArea.setProperty("Description"			,responseMsg.getServiceArea().getDescription());
				psRouting.addChild(psServiceArea);

				outputs.addChild(psSiebelMessage);
				outputs.setProperty("result", "SUCCESS");
				outputs.setProperty("Error"	,"");
			}
			else
			{
				errResponseMsg	= (ErrorResponseMsg)response;
				
				psSiebelMessage	= new SiebelPropertySet();
				psSiebelMessage.setType("SiebelMessage");

				psRouting	= new SiebelPropertySet();
				psRouting.setType("RouteResponse");
				psRouting.setProperty("Result"				,"FAIL");
				psSiebelMessage.addChild(psRouting);

				psResponse		= new SiebelPropertySet();
				psResponse.setType("Response");
				psRouting.addChild(psResponse);

				psServiceHeader	= new SiebelPropertySet();
				psServiceHeader.setType("ServiceHeader");
				psServiceHeader.setProperty("MessageTime"		,errResponseMsg.getResponse().getServiceHeader().getMessageTime().toString());
				psServiceHeader.setProperty("SiteID"			,errResponseMsg.getResponse().getServiceHeader().getSiteID());
				psServiceHeader.setProperty("MessageReference"	,""+errResponseMsg.getResponse().getServiceHeader().getMessageReference());
				psResponse.addChild(psServiceHeader);
				
				psStatus		= new SiebelPropertySet();
				psStatus.setType("Status");
				psStatus.setProperty("ActionStatus", errResponseMsg.getResponse().getStatus().getActionStatus());
				psResponse.addChild(psStatus);
				
				psCondition		= new SiebelPropertySet();
				psCondition.setType("Condition");
				stw.eai.dhl.xml.datatypes.Condition con = errResponseMsg.getResponse().getStatus().getCondition().get(0);
				psCondition.setProperty("ConditionCode", con.getConditionCode());
				psCondition.setProperty("ConditionData", con.getConditionData());
				psStatus.addChild(psCondition);

				psServiceArea	= new SiebelPropertySet();
				psServiceArea.setType("ServiceArea");
				psServiceArea.setProperty("ServiceAreaCode"		,"");
				psServiceArea.setProperty("Description"			,"");
				psRouting.addChild(psServiceArea);

				outputs.addChild(psSiebelMessage);
				
				outputs.setProperty("result", "FAIL");
				outputs.setProperty("Error"	, con.getConditionCode() + ":" + con.getConditionData());
				
			}			
		} catch (Exception e) {
			outputs.setProperty("result", "FAIL");
			outputs.setProperty("Error", e.getMessage());
			e.printStackTrace();
		} finally {
			psRouting		= null;
			psSiebelMessage	= null;
			psResponse		= null;
			psServiceHeader	= null;
			psStatus		= null;
			psCondition		= null;
			psServiceArea	= null;

			routing			= null;
			factory			= null;
		}
	}

	public void ShipValid(SiebelPropertySet inputs, SiebelPropertySet outputs) throws SiebelBusinessServiceException {
		SiebelPropertySet psSiebelMessage	= null;
		SiebelPropertySet psShipRequest		= null;
		SiebelPropertySet psBilling			= null;
		SiebelPropertySet psShipper			= null;
		SiebelPropertySet psConsignee		= null;
		SiebelPropertySet psReference		= null;
		SiebelPropertySet psShipmentDetails	= null;
		SiebelPropertySet psPieces			= null;
		SiebelPropertySet psContact			= null;
		
		ArrayList<SiebelPropertySet> psPiece		= null;
		ArrayList<SiebelPropertySet> psAddressLine	= null;
		
		SiebelPropertySet psResult			= null;
		
		ObjectFactory				factory			= null;
		ShipmentValidateRequestEA	shipmentValidate= null;
		
		try {

			psSiebelMessage		= Common.getChild	(inputs				,"SiebelMessage");
			psShipRequest		= Common.getChild	(psSiebelMessage	,"ShipmentRequest");
			psBilling			= Common.getChild	(psShipRequest		,"Billing");
			psConsignee			= Common.getChild	(psShipRequest		,"Consignee");
			psReference			= Common.getChild	(psShipRequest		,"Reference");
			psShipmentDetails	= Common.getChild	(psShipRequest		,"ShipmentDetails");
			psShipper			= Common.getChild	(psShipRequest		,"Shipper");

			factory = new ObjectFactory();
			shipmentValidate = factory.createShipmentValidateRequestEA();

			/* ShipmentRequest Define Start */
			shipmentValidate.setRequest(Common.getDHLRequest());
			shipmentValidate.setLanguageCode("en");
			shipmentValidate.setPiecesEnabled(PiecesEnabled.Y);
			shipmentValidate.setNewShipper(YesNo.N);
			/* ShipmentRequest Define Start */

			/* Billing billing Define Start */
			Billing billing = factory.createBilling();
			billing.setShipperAccountNumber(new Long(psBilling.getProperty("ShipperAccountNumber"))); // legacy asp_shp_acc
			billing.setBillingAccountNumber(new Long(psBilling.getProperty("BillingAccountNumber"))); // legacy asp_shp_acc
			billing.setShippingPaymentType(ShipmentPaymentType.valueOf(psBilling.getProperty("ShippingPaymentType"))); // legacy "Inbound": return PaymentType.R;
			
			shipmentValidate.setBilling(billing);
			/* Billing billing Define End */ 

			/* Consignee consignee Define Start */
			Consignee consignee = factory.createConsignee();
			consignee.setCompanyName	(Common.NVL(psConsignee.getProperty("CompanyName"))); //legacy asp_con_com
			consignee.setCity			(Common.NVL(psConsignee.getProperty("City"))); //legacy asp_con_city
			consignee.setDivision		(Common.NVL(psConsignee.getProperty("Division"))); //legacy asp_con_div
			consignee.setPostalCode		(Common.NVL(psConsignee.getProperty("PostalCode"))); //legacy asp_con_zip
			consignee.setCountryCode	(Common.NVL(psConsignee.getProperty("CountryCode"))); //legacy asp_con_cc
			consignee.setCountryName	(Common.NVL(psConsignee.getProperty("CountryName"))); //legacy asp_con_cn
			consignee.setFederalTaxId	(Common.NVL(psConsignee.getProperty("FederalTaxId")));

			psAddressLine	= Common.getChilds(psConsignee, "AddressLine");
			for(int i = 0 ; i < psAddressLine.size() ; i++)
			{
				consignee.getAddressLine().add(Common.NVL(psAddressLine.get(i).getValue())); //legacy asp_con_add
			}
			
			psContact	= Common.getChild(psConsignee, "Contact");
			if(psContact != null)
			{
				Contact conCon = factory.createContact();
				
				conCon.setPersonName	(psContact.getProperty("PersonName"));  //legacy asp_con_name
				conCon.setPhoneNumber	(psContact.getProperty("PhoneNumber")); //legacy asp_con_phn
				consignee.setContact(conCon);
			}
			
			shipmentValidate.setConsignee(consignee);
			/* Consignee consigneec Define End */ 
			
			/* Dutiable dutiable Define Start */
			Dutiable dutiable = factory.createDutiable();
			dutiable.setDeclaredValue	(Common.NVL(psShipmentDetails.getProperty("DeclaredValue"))); //legacy asp_shp_dv
			dutiable.setDeclaredCurrency(Common.NVL(psShipmentDetails.getProperty("CurrencyCode"))); //legacy "EUR"
			
			shipmentValidate.setDutiable(dutiable);
			/* Dutiable dutiable Define End */ 

			/* Reference ref Define Start */
			Reference ref = factory.createReference();
			ref.setReferenceID		(Common.NVL(psReference.getProperty("ReferenceID"))); //legacy "RMA No :" + RMA
			ref.setReferenceType	(Common.NVL(psReference.getProperty("ReferenceType")));
			
			shipmentValidate.getReference().add(ref);
			/* Reference ref Define End */ 

			/* ShipmentDetails shipmentDetails Define Start */
			ShipmentDetails shipmentDetails = factory.createShipmentDetails();
			shipmentDetails.setWeight			(new BigDecimal(psShipmentDetails.getProperty("Weight"))); //legacy decimal.Parse(asp_shp_sum_weight.Text);
			shipmentDetails.setWeightUnit		(WeightUnit.valueOf(psShipmentDetails.getProperty("WeightUnit"))); //legacy WeightUnit.K
			shipmentDetails.setGlobalProductCode(Common.NVL(psShipmentDetails.getProperty("GlobalProductCode"))); //legacy asp_shp_gpc.Text.Substring(0,1);
			shipmentDetails.setLocalProductCode	(Common.NVL(psShipmentDetails.getProperty("LocalProductCode"))); //legacy asp_shp_lpc.Text.Substring(0,1);
			shipmentDetails.setDate				(Common.NVL(psShipmentDetails.getProperty("Date"))); //legacy DateTime.Now.AddDays(1); "yyyy-MM-dd"
			shipmentDetails.setContents			(Common.NVL(psShipmentDetails.getProperty("Contents"))); // legacy asp_shp_cont
			shipmentDetails.setDoorTo			(DoorTo.valueOf(psShipmentDetails.getProperty("DoorTo")));//legacy DoorTo.DD
			shipmentDetails.setDimensionUnit	(DimensionalUnit.valueOf(psShipmentDetails.getProperty("DimensionUnit"))); //legacy DimensionalUnit.C
			shipmentDetails.setInsuredAmount	(Common.NVL(psShipmentDetails.getProperty("InsuredAmount")));
			shipmentDetails.setPackageType		(PackageType.valueOf(psShipmentDetails.getProperty("PackageType"))); //legacy PackageType
			shipmentDetails.setIsDutiable		(psShipmentDetails.getProperty("IsDutiable") == "Y"?YesNo.Y:YesNo.N);
			shipmentDetails.setCurrencyCode		(Common.NVL(psShipmentDetails.getProperty("CurrencyCode"))); //legacy currenycode
			shipmentDetails.setCustData			(Common.NVL(psShipmentDetails.getProperty("CustData")));

			psPieces			= Common.getChild	(psShipmentDetails	,"Pieces");
			if(psPieces != null)
			{
				if(psPieces.getChildCount() > 0)
				{
					psPiece				= Common.getChilds	(psPieces			,"Piece");
					//*** Pieces데이터는  DB에서 불러오지 않는 데이터임 화면에서 legacy 의 SsnXMLPieces으로 DB 생성시에만 사용하고 버림.***//
					Pieces pieces = factory.createPieces();
					for(int i=0; i<psPiece.size(); i++){
						Piece piece = factory.createPiece();
						piece.setPieceID(Common.NVL(psPiece.get(i).getProperty("PieceID"))); //legacy asp_p_ID.Text = (1 + SsnXMLPieces.Count).ToString();
						piece.setPackageType(PackageType.valueOf(psPiece.get(i).getProperty("PackageType"))); //legacy pt1 = ServiceObjects().getPackageType1(piece_type.SelectedValue.Substring(0, 2));
						piece.setWeight(new BigDecimal(psPiece.get(i).getProperty("Weight"))); //legacy asp_p_weight
						piece.setDimWeight(new BigDecimal(psPiece.get(i).getProperty("DimWeight"))); //legacy asp_p_weight
						piece.setWidth(new BigInteger(psPiece.get(i).getProperty("Width"))); //legacy asp_p_width
						piece.setHeight(new BigInteger(psPiece.get(i).getProperty("Height"))); //legacy asp_p_height
						piece.setDepth(new BigInteger(psPiece.get(i).getProperty("Depth"))); //legacy asp_p_depth
						piece.setPieceContents((i+1)+"");
						pieces.getPiece().add(piece);
					}
					
					shipmentDetails.setNumberOfPieces(new BigInteger(""+psPiece.size())); //legacy SsnXMLPieces.Count.ToString()
					shipmentDetails.setPieces(pieces);
				}
			}
			
			shipmentValidate.setShipmentDetails(shipmentDetails);
			/* ShipmentDetails shipmentDetails Define End */ 

			/* Shipper shipper Define Start */
			Shipper shipper =  factory.createShipper();
			shipper.setShipperID				(Common.NVL(psShipper.getProperty("ShipperID"))); // legacy asp_shp_acc
			shipper.setCompanyName				(Common.NVL(psShipper.getProperty("CompanyName"))); // legacy asp_shp_com
			shipper.setRegisteredAccount		(new Long(psShipper.getProperty("RegisteredAccount"))); // legacy asp_shp_acc
			shipper.setCity						(Common.NVL(psShipper.getProperty("City"))); //legacy asp_shp_city
			shipper.setDivision					(Common.NVL(psShipper.getProperty("Division"))); //legacy asp_shp_div
			shipper.setPostalCode				(Common.NVL(psShipper.getProperty("PostalCode"))); //legacy asp_shp_zip
			shipper.setOriginServiceAreaCode	(Common.NVL(psShipper.getProperty("OriginServiceAreaCode")));
			shipper.setOriginFacilityCode		(Common.NVL(psShipper.getProperty("OriginFacilityCode")));
			shipper.setCountryCode				(Common.NVL(psShipper.getProperty("CountryCode"))); //legacy asp_shp_cc
			shipper.setCountryName				(Common.NVL(psShipper.getProperty("CountryName"))); //legacy asp_shp_cn
			shipper.setFederalTaxId				(Common.NVL(psShipper.getProperty("FederalTaxId")));
			
			psAddressLine	= Common.getChilds(psShipper, "AddressLine_0");
			for(int i = 0 ; i < psAddressLine.size() ; i++)
			{
				shipper.getAddressLine().add(Common.NVL(psAddressLine.get(i).getValue())); //legacy asp_con_add
			}
			
			psContact	= Common.getChild(psShipper, "Contact_0");
			if(psContact != null)
			{
				Contact conCon = factory.createContact();
				
				conCon.setPersonName	(psContact.getProperty("PersonName"));  //legacy asp_con_name
				conCon.setPhoneNumber	(psContact.getProperty("PhoneNumber")); //legacy asp_con_phn
				shipper.setContact(conCon);
			}
			
			shipmentValidate.setShipper(shipper);
			/* Shipper shipper Define End */
			
			ShipValidGeneratorResult result = ShipValidGenerator.work(shipmentValidate);
			
			psSiebelMessage = new SiebelPropertySet();
			psSiebelMessage.setType("SiebelMessage");
			
			psResult = new SiebelPropertySet();
			psResult.setType("ShipmentResponse");
			psSiebelMessage.addChild(psResult);
			
			String awbNum	= result.getAwbNumber();
			psResult.setProperty("AWBNumber", awbNum);
			if("".equals(awbNum) || awbNum == null)
			{
				psResult.setProperty("Result"		,"FAIL");
				psResult.setProperty("PDFFilePath"	,"");
				psResult.setProperty("Description"	,result.getResultDescription());
				
				outputs.setProperty("result", "FAIL");
				outputs.setProperty("Error", result.getResultDescription());
			}
			else
			{
				psResult.setProperty("Result"		,"SUCCESS");
				psResult.setProperty("PDFFilePath", Common.RESPONSE_PATH + result.getAwbNumber() + ".pdf");
				psResult.setProperty("Description", result.getResultDescription());
				
				outputs.setProperty("result", "SUCCESS");
				outputs.setProperty("Error", "");
			}
			
			outputs.addChild(psSiebelMessage);
		} catch (Exception e) {
			outputs.setProperty("result", "FAIL");
			outputs.setProperty("Error", e.getMessage());
			e.printStackTrace();
		} finally {
			psSiebelMessage		= null;
			psShipRequest		= null;
			psBilling			= null;
			psShipper			= null;
			psConsignee			= null;
			psReference			= null;
			psShipmentDetails	= null;
			psPieces			= null;
			psContact			= null;
			
			psPiece				= null;
			psAddressLine		= null;
			
			psResult			= null;
			
			factory				= null;
			shipmentValidate	= null;
		}
	}
}
