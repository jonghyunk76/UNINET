package stw.eai.dhl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import stw.eai.common.Common;
import stw.eai.dhl.siebel.STWDhlClient;
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
import stw.eai.dhl.xml.request.ShipmentValidateRequestEA;

import com.siebel.data.SiebelPropertySet;
import com.siebel.eai.SiebelBusinessServiceException;

public class ShipValidGeneratorTestInvoke {
	public ShipValidGeneratorTestInvoke() {

	}
	
	public static void requestShipValid() throws SiebelBusinessServiceException {

		ShipValidGeneratorTestInvoke v=  new ShipValidGeneratorTestInvoke();

		//		ShipmentValidateRequestEA ship = v.createShipmentValidateRequestEA();
		//			try {
		//				ShipValidGeneratorResult result = ShipValidGenerator.work(ship);
		//				System.out.println("AWBNumber = " + result.getAwbNumber());
		//				System.out.println("Description = " + result.getResultDescription());
		//			} catch (Exception e) {
		//				// TODO Auto-generated catch block
		//				e.printStackTrace();
		//			}

		SiebelPropertySet psInput			= new SiebelPropertySet();
		SiebelPropertySet psOutput			= new SiebelPropertySet();
		SiebelPropertySet psAddressLine		= new SiebelPropertySet();
		SiebelPropertySet psContact			= new SiebelPropertySet();
		
		psInput.setProperty("Server", "SSCRMQ01");

		SiebelPropertySet psSiebelMessage	= new SiebelPropertySet();
		psSiebelMessage.setType("SiebelMessage");
		psInput.addChild(psSiebelMessage);

		SiebelPropertySet psShipRequest		= new SiebelPropertySet();
		psShipRequest.setType("ShipmentRequest");
		psSiebelMessage.addChild(psShipRequest);

		SiebelPropertySet psBilling = new SiebelPropertySet();
		psBilling.setType("Billing");
		psBilling.setProperty("ShipperAccountNumber", "955574052");
		psBilling.setProperty("BillingAccountNumber", "955574052");
		psBilling.setProperty("ShippingPaymentType", "R");
		psBilling.setProperty("DutyAccountNumber", "");
		psBilling.setProperty("DutyPaymentType", "");
		psShipRequest.addChild(psBilling);

		SiebelPropertySet psShipper = new SiebelPropertySet();
		psShipper.setType("Shipper");
		psShipper.setProperty("ShipperID", "955574052");
		psShipper.setProperty("CompanyName", "ADIMerton");
		psShipper.setProperty("RegisteredAccount", "955574052");
		psShipper.setProperty("PostalCode", "SW19 2QA");
		psShipper.setProperty("CountryCode", "GB");
		psShipper.setProperty("CountryName", "UNITED KINGDOM");
		psShipper.setProperty("City", "London");
		psShipper.setProperty("Division", "");
		
		psAddressLine	= new SiebelPropertySet();
		psAddressLine.setType("AddressLine_0");
		psAddressLine.setValue("Uint 9 Chelsea Fields 278 Western");
		psShipper.addChild(psAddressLine);
		
		psAddressLine	= new SiebelPropertySet();
		psAddressLine.setType("AddressLine_0");
		psAddressLine.setValue("Merton");
		psShipper.addChild(psAddressLine);
		
		psContact		= new SiebelPropertySet();
		psContact.setType("Contact_0");
		psContact.setProperty("PersonName", "Russell Kench");
		psContact.setProperty("PhoneNumber", "0208 646 7171");
		psContact.setProperty("Telex", "");
		psContact.setProperty("PhoneExtension", "");
		psContact.setProperty("FaxNumber", "");
		psContact.setProperty("Email", "");
		psShipper.addChild(psContact);
		
		psShipRequest.addChild(psShipper);

		SiebelPropertySet psConsignee = new SiebelPropertySet();
		psConsignee.setType("Consignee");
		psConsignee.setProperty("CompanyName", "STE SC");
		psConsignee.setProperty("CountryCode", "NL");
		psConsignee.setProperty("CountryName", "NETHERLANDS");
		psConsignee.setProperty("PostalCode", "5986 PK");
		psConsignee.setProperty("City", "Beringe");
		psConsignee.setProperty("Division", "");
		
		psAddressLine	= new SiebelPropertySet();
		psAddressLine.setType("AddressLine");
		psAddressLine.setValue("Schoorgras 10 Ind. Beringe");
		psConsignee.addChild(psAddressLine);
		
		psAddressLine	= new SiebelPropertySet();
		psAddressLine.setType("AddressLine");
		psAddressLine.setValue("terrein Helden nr. 2175");
		psConsignee.addChild(psAddressLine);
		
		psContact		= new SiebelPropertySet();
		psContact.setType("Contact");
		psContact.setProperty("PersonName", "Mine Karim");
		psContact.setProperty("PhoneNumber", "");
		psContact.setProperty("Telex", "");
		psContact.setProperty("PhoneExtension", "");
		psContact.setProperty("FaxNumber", "");
		psContact.setProperty("Email", "");
		psConsignee.addChild(psContact);
		
		psShipRequest.addChild(psConsignee);

		SiebelPropertySet psReference = new SiebelPropertySet();
		psReference.setType("Reference");
		psReference.setProperty("ReferenceID", "1402128-IN");
		psReference.setProperty("ReferenceType", "");
		psShipRequest.addChild(psReference);

		SiebelPropertySet psShipmentDetails = new SiebelPropertySet();
		psShipmentDetails.setType("ShipmentDetails");
		psShipmentDetails.setProperty("DimensionUnit", "C");
		psShipmentDetails.setProperty("DoorTo", "DD");
		psShipmentDetails.setProperty("CurrencyCode", "EUR");
		psShipmentDetails.setProperty("PackageType", "CP");
		psShipmentDetails.setProperty("WeightUnit", "K");
		psShipmentDetails.setProperty("GlobalProductCode", "U");
		psShipmentDetails.setProperty("LocalProductCode", "U");
		psShipmentDetails.setProperty("Weight", "1");
		psShipmentDetails.setProperty("Date", v.getTomorrowDate()+""); //"yyyy-MM-dd"
//		psShipmentDetails.setProperty("Date", "2014-03-15");
		psShipmentDetails.setProperty("Contents", "legacy asp_shp_cont");
		psShipmentDetails.setProperty("DeclaredValue", "0.00");
		psShipmentDetails.setProperty("InsuredAmount", "");

		psShipRequest.addChild(psShipmentDetails);

		SiebelPropertySet psPieces = new SiebelPropertySet();
		psPieces.setType("Pieces");
		//psPieces.setProperty("NumberOfPieces", "2");
		SiebelPropertySet psPiece1 = new SiebelPropertySet();
		psPiece1.setType("Piece");
		psPiece1.setProperty("PieceID", "1");
		psPiece1.setProperty("PackageType", "PA");
		psPiece1.setProperty("Weight", "1");
		psPiece1.setProperty("DimWeight", "1");
		psPiece1.setProperty("Width", "1");
		psPiece1.setProperty("Height", "1");
		psPiece1.setProperty("Depth", "1");
		psPieces.addChild(psPiece1);
		SiebelPropertySet psPiece2 = new SiebelPropertySet();
		psPiece2.setType("Piece");
		psPiece2.setProperty("PieceID", "2");
		psPiece2.setProperty("PackageType", "PA");
		psPiece2.setProperty("Weight", "5");
		psPiece2.setProperty("DimWeight", "4");
		psPiece2.setProperty("Width", "3");
		psPiece2.setProperty("Height", "2");
		psPiece2.setProperty("Depth", "1");
		psPieces.addChild(psPiece2);
		psShipmentDetails.addChild(psPieces);

		STWDhlClient sendRouting = new STWDhlClient();
		System.out.println(psInput.toString());
		sendRouting.doInvokeMethod("ShipValid", psInput, psOutput);

		System.out.println("outputs");
		System.out.println(psOutput.toString());
	}

	public static void main_(String[] args) throws SiebelBusinessServiceException {

		ShipValidGeneratorTestInvoke v=  new ShipValidGeneratorTestInvoke();

		//		ShipmentValidateRequestEA ship = v.createShipmentValidateRequestEA();
		//			try {
		//				ShipValidGeneratorResult result = ShipValidGenerator.work(ship);
		//				System.out.println("AWBNumber = " + result.getAwbNumber());
		//				System.out.println("Description = " + result.getResultDescription());
		//			} catch (Exception e) {
		//				// TODO Auto-generated catch block
		//				e.printStackTrace();
		//			}

		SiebelPropertySet psInput			= new SiebelPropertySet();
		SiebelPropertySet psOutput			= new SiebelPropertySet();
		SiebelPropertySet psAddressLine		= new SiebelPropertySet();
		
		psInput.setProperty("Server", "SSCRMD01");

		SiebelPropertySet psSiebelMessage	= new SiebelPropertySet();
		psSiebelMessage.setType("SiebelMessage");
		psInput.addChild(psSiebelMessage);

		SiebelPropertySet psShipRequest		= new SiebelPropertySet();
		psShipRequest.setType("ShipmentRequest");
		psSiebelMessage.addChild(psShipRequest);

		SiebelPropertySet psBilling = new SiebelPropertySet();
		psBilling.setType("Billing");
		psBilling.setProperty("ShipperAccountNumber", "955574052");
		psBilling.setProperty("BillingAccountNumber", "955574052");
		psBilling.setProperty("ShippingPaymentType", "R");
		psShipRequest.addChild(psBilling);

		SiebelPropertySet psShipper = new SiebelPropertySet();
		psShipper.setType("Shipper");
		psShipper.setProperty("ShipperID", "955574052");
		psShipper.setProperty("CompanyName", "ADIMerton");
		psShipper.setProperty("RegisteredAccount", "955574052");
		psShipper.setProperty("AddressLine1", "Uint 9 Chelsea Fields 278 Western");
		psShipper.setProperty("AddressLine2", "Merton");
		psShipper.setProperty("AddressLine3", "");
		psShipper.setProperty("PostalCode", "SW19 2QA");
		psShipper.setProperty("CountryCode", "GB");
		psShipper.setProperty("CountryName", "UNITED KINGDOM");
		psShipper.setProperty("City", "London");
		psShipper.setProperty("Division", "");
		psShipper.setProperty("PersonName", "Russell Kench");
		psShipper.setProperty("PhoneNumber", "0208 646 7171");
		psShipRequest.addChild(psShipper);

		SiebelPropertySet psConsignee = new SiebelPropertySet();
		psConsignee.setType("Consignee");
		psConsignee.setProperty("CompanyName", "STE SC");
		psConsignee.setProperty("AddressLine1", "Schoorgras 10 Ind. Beringe");
		psConsignee.setProperty("AddressLine2", "terrein Helden nr. 2175");
		psConsignee.setProperty("AddressLine3", "");
		psConsignee.setProperty("CountryCode", "NL");
		psConsignee.setProperty("CountryName", "NETHERLANDS");
		psConsignee.setProperty("PostalCode", "5986 PK");
		psConsignee.setProperty("City", "Beringe");
		psConsignee.setProperty("Division", "");
		psConsignee.setProperty("PersonName", "Mine Karim");
		psConsignee.setProperty("PhoneNumber", "");
		psShipRequest.addChild(psConsignee);

		SiebelPropertySet psReference = new SiebelPropertySet();
		psReference.setType("Reference");
		psReference.setProperty("ReferenceID", "1402128-IN");
		psShipRequest.addChild(psReference);

		SiebelPropertySet psShipmentDetails = new SiebelPropertySet();
		psShipmentDetails.setType("ShipmentDetails");
		psShipmentDetails.setProperty("DimensionUnit", "C");
		psShipmentDetails.setProperty("DoorTo", "DD");
		psShipmentDetails.setProperty("CurrencyCode", "EUR");
		psShipmentDetails.setProperty("PackageType", "CP");
		psShipmentDetails.setProperty("WeightUnit", "K");
		psShipmentDetails.setProperty("GlobalProductCode", "U");
		psShipmentDetails.setProperty("LocalProductCode", "U");
		psShipmentDetails.setProperty("Weight", "1");
		psShipmentDetails.setProperty("Date", v.getTomorrowDate()+""); //"yyyy-MM-dd"
		psShipmentDetails.setProperty("Contents", "legacy asp_shp_cont");
		psShipmentDetails.setProperty("DeclaredValue", "0.00");

		psShipRequest.addChild(psShipmentDetails);

		SiebelPropertySet psPieces = new SiebelPropertySet();
		psPieces.setType("Pieces");
		//psPieces.setProperty("NumberOfPieces", "2");
		SiebelPropertySet psPiece1 = new SiebelPropertySet();
		psPiece1.setType("Piece");
		psPiece1.setProperty("PieceID", "1");
		psPiece1.setProperty("PackageType", "PA");
		psPiece1.setProperty("Weight", "1");
		psPiece1.setProperty("DimWeight", "1");
		psPiece1.setProperty("Width", "1");
		psPiece1.setProperty("Height", "1");
		psPiece1.setProperty("Depth", "1");
		psPieces.addChild(psPiece1);
		SiebelPropertySet psPiece2 = new SiebelPropertySet();
		psPiece2.setType("Piece");
		psPiece2.setProperty("PieceID", "2");
		psPiece2.setProperty("PackageType", "PA");
		psPiece2.setProperty("Weight", "5");
		psPiece2.setProperty("DimWeight", "4");
		psPiece2.setProperty("Width", "3");
		psPiece2.setProperty("Height", "2");
		psPiece2.setProperty("Depth", "1");
		psPieces.addChild(psPiece2);
		psShipmentDetails.addChild(psPieces);

		System.setProperty("proxySet", "true");
		System.setProperty("proxyHost", "55.200.250.10");
		System.setProperty("proxyPort", "8080");
		STWDhlClient sendRouting = new STWDhlClient();
		sendRouting.doInvokeMethod("ShipValid", psInput, psOutput);

		System.out.println("outputs");
		System.out.println(psOutput.toString());
	}
	
	public String getTomorrowDate(){
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		GregorianCalendar gCalendar = new GregorianCalendar();
		gCalendar.setTime(dt);
		gCalendar.add(GregorianCalendar.DATE, 1); //������¥ +1

		return sdf.format(gCalendar.getTime());
	}


	public ShipmentValidateRequestEA createShipmentValidateRequestEA(){
		ObjectFactory factory = new ObjectFactory();

		/* Billing b Define Start */
		Billing b = factory.createBilling();
		b.setShipperAccountNumber((long)955574052); // legacy asp_shp_acc
		b.setBillingAccountNumber((long)955574052); // legacy asp_shp_acc
		b.setShippingPaymentType(ShipmentPaymentType.R); // legacy "Inbound": return PaymentType.R;
		/* Billing b Define ENd */ 

		/* Shipper sp Define Start */
		Shipper sp =  factory.createShipper();
		sp.setShipperID("955574052"); // legacy asp_shp_acc
		sp.setCompanyName("ADIMerton"); // legacy asp_shp_com
		sp.setRegisteredAccount((long)955574052); // legacy asp_shp_acc
		sp.getAddressLine().add("Uint 9 Chelsea Fields 278 Western"); //legacy asp_shp_add1
		sp.getAddressLine().add("Merton"); //legacy asp_shp_add2
		sp.getAddressLine().add(""); //legacy asp_shp_add3
		sp.setPostalCode("SW19 2QA"); //legacy asp_shp_zip
		sp.setCountryCode("GB"); //legacy asp_shp_cc
		sp.setCountryName("UNITED KINGDOM"); //legacy asp_shp_cn
		sp.setCity("London"); //legacy asp_shp_city
		sp.setDivision(""); //legacy asp_shp_div
		Contact shipCon = factory.createContact();
		shipCon.setPersonName("Russell Kench");  //legacy asp_shp_name
		shipCon.setPhoneNumber("0208 646 7171"); //legacy asp_shp_phn
		sp.setContact(shipCon);
		/* Shipper sp Define ENd */

		/* Consignee c Define Start */
		Consignee c = factory.createConsignee();
		c.setCompanyName("STE SC"); //legacy asp_con_com
		c.getAddressLine().add("Schoorgras 10 Ind. Beringe"); //legacy asp_con_add1
		c.getAddressLine().add("terrein Helden nr. 2175"); //legacy asp_con_add2
		c.getAddressLine().add(""); //legacy asp_con_add3
		c.setCountryCode("NL"); //legacy asp_con_cc
		c.setCountryName("NETHERLANDS"); //legacy asp_con_cn
		c.setPostalCode("5986 PK"); //legacy asp_con_zip
		c.setCity("Beringe"); //legacy asp_con_city
		c.setDivision(""); //legacy asp_con_div
		Contact conCon = factory.createContact();
		conCon.setPersonName("Mine Karim");  //legacy asp_con_name
		conCon.setPhoneNumber(""); //legacy asp_con_phn
		c.setContact(conCon);
		/* Consignee c Define ENd */ 

		/* Dutiable d Define Start */
		Dutiable d = factory.createDutiable();
		d.setDeclaredValue("0.00"); //legacy asp_shp_dv
		d.setDeclaredCurrency("EUR"); //legacy "EUR"
		/* Dutiable  Define ENd */ 

		/* Reference ref Define Start */
		Reference ref = factory.createReference();
		ref.setReferenceID("RMA No : " + "1402128-IN"); //legacy "RMA No :" + RMA
		/* Reference  Define ENd */ 

		/* ShipmentDetails d Define Start */
		ShipmentDetails sd = factory.createShipmentDetails();
		sd.setDoorTo(DoorTo.DD);//legacy DoorTo.DD
		sd.setCurrencyCode("EUR"); //legacy currenycode
		sd.setPackageType(PackageType.CP); //legacy PackageType
		sd.setDimensionUnit(DimensionalUnit.C); //legacy DimensionalUnit.C
		sd.setWeightUnit(WeightUnit.K); //legacy WeightUnit.K
		sd.setGlobalProductCode("U"); //legacy asp_shp_gpc.Text.Substring(0,1);
		sd.setLocalProductCode("U"); //legacy asp_shp_lpc.Text.Substring(0,1);
		sd.setWeight(new BigDecimal("1")); //legacy decimal.Parse(asp_shp_sum_weight.Text);
		sd.setDate(this.getTomorrowDate()); //legacy DateTime.Now.AddDays(1);
		sd.setContents("legacy asp_shp_cont"); // legacy asp_shp_cont
		//*** Pieces�����ʹ�  DB���� �ҷ����� �ʴ� �������� ȭ�鿡�� legacy �� SsnXMLPieces���� DB �����ÿ��� ����ϰ� ����.***//
		Piece p1 = factory.createPiece();
		p1.setPieceID("1"); //legacy asp_p_ID.Text = (1 + SsnXMLPieces.Count).ToString();
		p1.setPackageType(PackageType.PA); //legacy pt1 = ServiceObjects().getPackageType1(piece_type.SelectedValue.Substring(0, 2));
		p1.setWeight(new BigDecimal("1")); //legacy asp_p_weight
		p1.setDimWeight(new BigDecimal("1")); //legacy asp_p_weight
		p1.setWidth(new BigInteger("1")); //legacy asp_p_width
		p1.setHeight(new BigInteger("1")); //legacy asp_p_height
		p1.setDepth(new BigInteger("1")); //legacy asp_p_depth

		Piece p2 = factory.createPiece();
		p2.setPieceID("2"); //legacy asp_p_ID.Text = (1 + SsnXMLPieces.Count).ToString();
		p2.setPackageType(PackageType.PA); //legacy pt1 = ServiceObjects().getPackageType1(piece_type.SelectedValue.Substring(0, 2));
		p2.setWeight(new BigDecimal("5")); //legacy asp_p_weight
		p2.setDimWeight(new BigDecimal("4")); //legacy asp_p_weight
		p2.setWidth(new BigInteger("3")); //legacy asp_p_width
		p2.setHeight(new BigInteger("2")); //legacy asp_p_height
		p2.setDepth(new BigInteger("1")); //legacy asp_p_depth

		Pieces ps = factory.createPieces();
		ps.getPiece().add(p1);
		ps.getPiece().add(p2);
		sd.setNumberOfPieces(new BigInteger("2")); //legacy SsnXMLPieces.Count.ToString()
		sd.setPieces(ps);
		/* ShipmentDetails  Define ENd */ 

		ShipmentValidateRequestEA shipmentValidate = factory.createShipmentValidateRequestEA();
		shipmentValidate.setRequest(Common.getDHLRequest());
		shipmentValidate.setLanguageCode("en");
		shipmentValidate.setPiecesEnabled(PiecesEnabled.Y);
		shipmentValidate.setNewShipper(YesNo.N);
		shipmentValidate.getReference().add(ref) ;

		shipmentValidate.setBilling(b);
		shipmentValidate.setShipper(sp);
		shipmentValidate.setConsignee(c);
		shipmentValidate.setShipmentDetails(sd);
		shipmentValidate.setDutiable(d);

		return shipmentValidate;
	}
}
