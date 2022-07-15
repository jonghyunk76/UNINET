package com.yni.fta.common.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import kr.yni.frame.Constants;
import kr.yni.frame.config.Configurator;
import kr.yni.frame.config.ConfiguratorFactory;
import kr.yni.frame.util.StringHelper;

/**
 * FTA PASS 연계를 도와주는 클래스
 * 
 * @author ador2
 *
 */
public class FTAPassSupporter {
	
	private static Log log = LogFactory.getLog(FTAPassSupporter.class);
	
	/**
     * 원산지 확인서 요청서 XML 생성
     * 
     * @param master
     * @param coIssueOriginList
     * @return XML파일명
     * @throws Exception
     */
    @SuppressWarnings({"rawtypes"})
    public static String createCOVerificationRequest2XML(Map master, List itemList) throws Exception {
    	Configurator configurator = ConfiguratorFactory.getInstance().getConfigurator();
    	String db = StringHelper.null2void(configurator.getString("application.db.type"));
    	String vendor_cd = StringHelper.null2void(master.get("VENDOR_CD"));
        String outputFolder = Constants.APPLICATION_REAL_PATH + "/upload/Temp/";
        byte[] data = null;
        
        // Name space 정의
        Namespace rsm = Namespace.getNamespace("rsm", "urn:un:unece:uncefact:data:draft:COVerificationRequest:1.0");
        Namespace ram = Namespace.getNamespace("ram","urn:un:unece:uncefact:data:draft:ReusableAggregateBusinessInformationEntity:1.0");
        Namespace ccts = Namespace.getNamespace("ccts","urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2");
        Namespace ds = Namespace.getNamespace("ds","http://www.w3.org/2000/09/xmldsig#");
        Namespace qdt = Namespace.getNamespace("qdt","urn:un:unece:uncefact:data:standard:QualifiedDataType:8");
        Namespace udt = Namespace.getNamespace("udt","urn:un:unece:uncefact:data:standard:UnqualifiedDataType:9");
        Namespace xsi = Namespace.getNamespace("xsi","http://www.w3.org/2001/XMLSchema-instance");
        
        // root element 만들기
        Element COVerificationRequest = new Element("COVerificationRequest");
        
        //Name 스페이스 설정
        COVerificationRequest.addNamespaceDeclaration(ccts);
        COVerificationRequest.addNamespaceDeclaration(ds);
        COVerificationRequest.addNamespaceDeclaration(qdt);
        COVerificationRequest.addNamespaceDeclaration(ram);
        COVerificationRequest.addNamespaceDeclaration(udt);
        COVerificationRequest.addNamespaceDeclaration(xsi);
        COVerificationRequest.setAttribute("schemaLocation", "urn:un:unece:uncefact:data:draft:COVerificationRequest:1.0 ../schema/COVerificationRequest_1p0.xsd", xsi);
    
        // 관리정보
        Element EXDO = new Element("ExchangedDocument", rsm);
        EXDO.addContent(new Element("ID", ram).setText(StringHelper.null2void(master.get("CO_DOC_NO")))); // 확인요청서 문서번호
        EXDO.addContent(new Element("ExtraTypeCode", ram).setText("F103")); // 전자문서 추가유형 코드(F103 : 원산지확인요청서)
        EXDO.addContent(new Element("IssueDateTime", ram).setText(StringHelper.null2void(master.get("ISSUE_DATE")))); // 전자문서 발행일시(YYYYMMDDHHMISS형식, 전자서명 생성 일시)
        
        // 송신자 정보
        Element SBP = new Element("SenderBusinessParty", ram); // 송신자 정보
        SBP.addContent(new Element("ID", ram).setText(StringHelper.null2void(master.get("SENDER_DOCBOX_NO")))); // 송신자 가입자 아이디
        SBP.addContent(new Element("CodeQualifierID", ram).setText(StringHelper.null2void(master.get("SENDER_PASS_ID")))); // 송신자 가입자 Agency 아이디
        SBP.addContent(new Element("Name", ram).setText(StringHelper.null2void(master.get("SENDER_COMPANY_NAME")))); // 송신자 상호
        
        Element SDRC = new Element("DefinedResponsibleContact", ram); // 송신자 정보 > 담당자 정보
        SDRC.addContent(new Element("JobTitle", ram).setText(StringHelper.null2void(master.get("SENDER_OFFICER_POSITION")))); // 송신자 담당자 직위
        //SDRC.addContent(new Element("DepartmentName", ram).setText(StringHelper.null2void(master.get("SENDER_OFFICER_DEPT")))); // 송신자 담당자 부서
        SDRC.addContent(new Element("PersonName", ram).setText(StringHelper.null2void(master.get("SENDER_OFFICER_NAME")))); // 송신자 담당자 이름
        //SDRC.addContent(new Element("SignDescription", ram).setText(StringHelper.null2void(master.get("SENDER_SIGN_INFO")))); // 송신자 서명 정보
        SBP.addContent(SDRC);
        
        Element STNC = new Element("TelephoneNumberCommunication", ram); // 송신자 정보 > 전화번호
        STNC.addContent(new Element("LocalNumber", ram).setText(StringHelper.null2void(master.get("SENDER_TEL_NO")))); // 송신자 전화번호
        SBP.addContent(STNC);
        
        Element SFNC = new Element("FaxNumberCommunication", ram); // 송신자 정보 > 팩스번호
        SFNC.addContent(new Element("LocalNumber", ram).setText(StringHelper.null2void(master.get("SENDER_FAX_NO")))); // 송신자 팩스번호
        SBP.addContent(SFNC);
        
        Element SUEC = new Element("URIEmailCommunication", ram); // 송신자 정보 > 이메일
        SUEC.addContent(new Element("URIID", ram).setText(StringHelper.null2void(master.get("SENDER_EMAIL")))); // 송신자 이메일주소
        SBP.addContent(SUEC);
        
        Element SOBA = new Element("OfficeBusinessAddress", ram); // 송신자 정보 > 주소
        SOBA.addContent(new Element("LineOne", ram).setText(StringHelper.null2void(master.get("SENDER_ADDRESS")))); // 송신자 주소
        SOBA.addContent(new Element("CountryID", ram).setText(StringHelper.null2void(master.get("SENDER_NATION_CD")))); // 송신자 국가코드
        SBP.addContent(SOBA);
        
        EXDO.addContent(SBP);
        
        // 수신자 정보
        Element RBP = new Element("RecipientBusinessParty", ram); // 수신자 정보
        RBP.addContent(new Element("ID", ram).setText(StringHelper.null2void(master.get("RECEIVER_DOCBOX_NO")))); // 수신자 가입자 아이디
        RBP.addContent(new Element("CodeQualifierID", ram).setText(StringHelper.null2void(master.get("RECEIVER_PASS_ID")))); // 수신자 가입자 Agency 아이디
        RBP.addContent(new Element("Name", ram).setText(StringHelper.null2void(master.get("RECEIVER_COMPANY_NAME")))); // 수신자 상호
        
        Element RDRC = new Element("DefinedResponsibleContact", ram); // 수신자 정보 > 담당자 정보
        RDRC.addContent(new Element("JobTitle", ram).setText(StringHelper.null2void(master.get("RECEIVER_OFFICER_POSITION")))); // 수신자 담당자 직위
        //RDRC.addContent(new Element("DepartmentName", ram).setText(StringHelper.null2void(master.get("RECEIVER_OFFICER_DEPT")))); // 수신자 담당자 부서
        RDRC.addContent(new Element("PersonName", ram).setText(StringHelper.null2void(master.get("RECEIVER_OFFICER_NAME")))); // 수신자 담당자 이름
        //RDRC.addContent(new Element("SignDescription", ram).setText(StringHelper.null2void(master.get("RECEIVER_SIGN_INFO")))); // 수신자 서명 정보
        RBP.addContent(RDRC);
        
        Element RTNC = new Element("TelephoneNumberCommunication", ram); // 수신자 정보 > 전화번호
        RTNC.addContent(new Element("LocalNumber", ram).setText(StringHelper.null2void(master.get("RECEIVER_TEL_NO")))); // 수신자 전화번호
        RBP.addContent(RTNC);
        
        Element RFNC = new Element("FaxNumberCommunication", ram); // 수신자 정보 > 팩스번호
        RFNC.addContent(new Element("LocalNumber", ram).setText(StringHelper.null2void(master.get("RECEIVER_FAX_NO")))); // 수신자 팩스번호
        RBP.addContent(RFNC);
        
        Element RUEC = new Element("URIEmailCommunication", ram); // 수신자 정보 > 이메일
        RUEC.addContent(new Element("URIID", ram).setText(StringHelper.null2void(master.get("RECEIVER_EMAIL")))); // 수신자 이메일주소
        RBP.addContent(RUEC);
        
        Element ROBA = new Element("OfficeBusinessAddress", ram); // 수신자 정보 > 주소
        ROBA.addContent(new Element("LineOne", ram).setText(StringHelper.null2void(master.get("RECEIVER_ADDRESS")))); // 수신자 주소
        ROBA.addContent(new Element("CountryID", ram).setText(StringHelper.null2void(master.get("RECEIVER_NATION_CD")))); // 수신자 국가코드
        RBP.addContent(ROBA);
        
        EXDO.addContent(RBP);
        
        COVerificationRequest.addContent(EXDO);
        
        // 확인요청서 기본정보
        Element VRD = new Element("VerificationRequestDocument", rsm);
        VRD.addContent(new Element("ID", ram).setText(StringHelper.null2void(master.get("CO_DOC_NO")))); // 원산지확인요청서 문서번호
        VRD.addContent(new Element("IssueDateTime", ram).setText(StringHelper.null2void(master.get("SENDER_WRITE_DATE")))); // 작성일자(YYYYMMDD)
        
        // 신청자 정보
        Element IAP = new Element("IssuerApplicantParty", ram); // 신청자 정보
        IAP.addContent(new Element("ID", ram).setText(StringHelper.null2void(master.get("SENDER_BUSINESS_NO")).replace("-", ""))); // 신청인 사업자번호
        IAP.addContent(new Element("MutualAssignedID", ram).setText(StringHelper.null2void(master.get("SENDER_COMPANY_CD")))); // 신청인 업체코드
        IAP.addContent(new Element("Name", ram).setText(StringHelper.null2void(master.get("SENDER_COMPANY_NAME")))); // 신청자 상호
        
        Element ISOP = new Element("SpecifiedOwnerPerson", ram); // 신청자 정보 > 대표자
        ISOP.addContent(new Element("Name", ram).setText(StringHelper.null2void(master.get("SENDER_PRESIDENT_NAME")))); // 대표자 성명
        IAP.addContent(ISOP);
        
        Element ITNC = new Element("TelephoneNumberCommunication", ram); // 신청자 정보 > 전화번호
        ITNC.addContent(new Element("LocalNumber", ram).setText(StringHelper.null2void(master.get("SENDER_TEL_NO")))); // 신청자 전화번호
        IAP.addContent(ITNC);
        
        Element IFNC = new Element("FaxNumberCommunication", ram); // 신청자 정보 > 팩스번호
        IFNC.addContent(new Element("LocalNumber", ram).setText(StringHelper.null2void(master.get("SENDER_FAX_NO")))); // 신청자 팩스번호
        IAP.addContent(IFNC);
        
        Element IUEC = new Element("URIEmailCommunication", ram); // 신청자 정보 > 이메일
        IUEC.addContent(new Element("URIID", ram).setText(StringHelper.null2void(master.get("SENDER_EMAIL")))); // 신청자 이메일주소
        IAP.addContent(IUEC);
        
        Element IOBA = new Element("OfficeBusinessAddress", ram); // 신청자 정보 > 주소
        IOBA.addContent(new Element("LineOne", ram).setText(StringHelper.null2void(master.get("SENDER_ADDRESS")))); // 신청자 주소
        IOBA.addContent(new Element("CountryID", ram).setText(StringHelper.null2void(master.get("SENDER_NATION_CD")))); // 신청자 국가코드
        IAP.addContent(IOBA);
        
        VRD.addContent(IAP);
        
        COVerificationRequest.addContent(VRD);
        
        // 거래 상대방 정보
        Element CTD = new Element("CollaboratedTradeDelivery", rsm);
        
        // 공급자 정보
        Element CGPP = new Element("GoodsSourcingProducerParty", ram); // 공급자 정보
        CGPP.addContent(new Element("ID", ram).setText(StringHelper.null2void(master.get("VENDOR_BUSINESS_NO")).replace("-", ""))); // 공급자 사업자번호
        CGPP.addContent(new Element("MutualAssignedID", ram).setText(StringHelper.null2void(master.get("VENDOR_CD")))); // 공급자 업체코드
        CGPP.addContent(new Element("Name", ram).setText(StringHelper.null2void(master.get("VENDOR_NAME")))); // 공급자 상호
        
        Element CSOP = new Element("SpecifiedOwnerPerson", ram); // 공급자 정보 > 대표자
        CSOP.addContent(new Element("Name", ram).setText(StringHelper.null2void(master.get("VENDOR_PRESIDENT_NAME")))); // 대표자 성명
        CGPP.addContent(CSOP);
        
        Element CTNC = new Element("TelephoneNumberCommunication", ram); // 공급자 정보 > 전화번호
        CTNC.addContent(new Element("LocalNumber", ram).setText(StringHelper.null2void(master.get("VENDOR_TEL")))); // 공급자 전화번호
        CGPP.addContent(CTNC);
        
        Element CFNC = new Element("FaxNumberCommunication", ram); // 공급자 정보 > 팩스번호
        CFNC.addContent(new Element("LocalNumber", ram).setText(StringHelper.null2void(master.get("VENDOR_FAX")))); // 공급자 팩스번호
        CGPP.addContent(CFNC);
        
        Element CUEC = new Element("URIEmailCommunication", ram); // 공급자 정보 > 이메일
        CUEC.addContent(new Element("URIID", ram).setText(StringHelper.null2void(master.get("VENDOR_EMAIL")))); // 공급자 이메일주소
        CGPP.addContent(CUEC);
        
        Element COBA = new Element("OfficeBusinessAddress", ram); // 공급자 정보 > 주소
        COBA.addContent(new Element("LineOne", ram).setText(StringHelper.null2void(master.get("VENDOR_ADDRESS")))); // 공급자 주소
        CGPP.addContent(COBA);
        
        CTD.addContent(CGPP);
        
        COVerificationRequest.addContent(CTD);
        
        for(int i = 0; i < itemList.size(); i++) {
        	Map itemMap = (Map) itemList.get(i);
        	
	        // 공급    물품명세
	        Element ITD = new Element("CustomsConsignmentItem", rsm);
	        ITD.addContent(new Element("ID", ram).setText(StringHelper.null2void(itemMap.get("ITEM_CD")))); // 내부적으로 관리하는 품목 ID
	        ITD.addContent(new Element("SequenceNumeric", ram).setText(StringHelper.null2void(itemMap.get("SORT_NO")))); // 연번
	        ITD.addContent(new Element("HSTypeCode", ram).setText(StringHelper.null2void(itemMap.get("HS_CODE")))); // 6단위 HS No.
	        ITD.addContent(new Element("FTATypeCode", ram).setText(StringHelper.null2void(itemMap.get("PASS_FTA_CD")))); // 적용대상협정(PASS기준1자리 코드)
	        ITD.addContent(new Element("ProductInformation", ram).setText(StringHelper.null2void(itemMap.get("SPEC")))); // 규격
	        ITD.addContent(new Element("Information", ram).setText(StringHelper.null2void(itemMap.get("ITEM_NAME")))); // 품명
	        
	        Element IRPS = new Element("RegulatoryProcessService", ram); // 원산지 포괄기간
	        Element IECP = new Element("EffectiveConfirmationPeriod", ram);
	        IECP.addContent(new Element("StartDateTime", ram).setText(StringHelper.null2void(itemMap.get("APPLY_DATE")))); // 확인기간 시작일자(YYYYMMDD)
	        IECP.addContent(new Element("EndDateTime", ram).setText(StringHelper.null2void(itemMap.get("END_DATE")))); // 확인기간 종료일자(YYYYMMDD)
	        IRPS.addContent(IECP);
	        
	        ITD.addContent(IRPS);
	        
	        COVerificationRequest.addContent(ITD);
        }
        
        Document myDoc = new Document(COVerificationRequest);
        
        XMLOutputter outputter = new XMLOutputter();
        FileOutputStream fos = null;
        OutputStreamWriter writer = null;
        String newFileName = null;
        
        try {
            outputter.setFormat(Format.getPrettyFormat());
            
            newFileName = UUID.randomUUID().toString() + ".xml";
           
            File dir = new File(outputFolder);

            if(!dir.isDirectory()){
                  dir.mkdirs();
            }
           
            fos = new FileOutputStream(outputFolder + "/" + newFileName);
            writer = new OutputStreamWriter(fos, Constants.APPLICATION_FILE_ENCODING);
           
            outputter.output(myDoc, writer);
           
            if(log.isDebugEnabled()) {
                log.debug("Generate file info(path = " + outputFolder + ", name = " + newFileName + ")");
            }
        } catch(Exception e) {
            throw e;
        } finally {
            if(writer != null) writer.close();
            if(fos != null) fos.close();
        }
        
        return newFileName;
    }
	
}
