package com.yni.fta.common.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.Blob;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import com.yni.fta.common.handler.HubDataHandler;

import java.util.ArrayList;
import java.util.Base64;

import kr.yni.frame.Constants;
import kr.yni.frame.config.Configurator;
import kr.yni.frame.config.ConfiguratorFactory;
import kr.yni.frame.resources.MessageResource;
import kr.yni.frame.util.StringHelper;

/**
 * FTA Hub(바츠) XML-Parsing & Create 
 * 
 * @author YNI-Maker
 *
 */
public class HubSupporter {
    
	private static Log log = LogFactory.getLog(HubSupporter.class);
	
    private ArrayList<Map<String, Object>> dataList;
    
    private HubDataHandler handler;
    
    public HubSupporter() { }
    
    public HubSupporter(String fileName) {
        try {
            handler = new HubDataHandler(fileName);
            
            dataList = handler.getData();
            
            log.debug("data size = " + dataList.size());
        } catch(Exception e) {
            if(log.isErrorEnabled()) log.error("Exception(method-HubSupporter) >> " + e.getMessage());
        }
    }
    
    /**
     * 문서 타입코드을 리턴
     * 
     * @return
     */
    public String getDocumentType() {
        return StringHelper.null2void(handler.getDocumentType());
    }
    
    /**
     * 인터페이스 ID를 리턴
     * 
     * @return
     */
    public String getInterfaceId() {
        String interfaceId = null;
        String docType = StringHelper.null2void(handler.getDocumentType());
        
        if(docType.contains(":RequestVerificationOfOrigin:")) { // 요청서
            interfaceId = "RPT_FH_001";
        } else if(docType.contains(":VerificationOfOrigin:")) { // 제출서
            interfaceId = "RPT_FH_004";
        } else if(docType.contains(":CommonResponse:")) { // 결과서
            interfaceId = "RPT_FH_007";
        } else if(docType.contains(":CopyDocumentVerificationOfOrigin:")) { // 복사본
            interfaceId = "RPT_FH_008";
        } else if(docType.contains(":RequestReturn:")) { // 반송서
            interfaceId = "RPT_FH_009";
        }
        
        return interfaceId;
    }
    
    /**
     * XML에서 경로 및 인덱스에 해당하는 값을 구한다.
     * 
     * @param path XML full 경로
     * @param idx  찾고자 하는 index번호
     * @return index가 -1인 경우 XML경로와 매칭되는 첫번째 값을 리턴함
     * @throws Exception
     */
    public String getValue(String path, int idx) throws Exception {
        if(path == null) {
            if(log.isWarnEnabled()) log.warn("COMMON-XML parsing error : not found xml path.");
            return null;
        }
        
        String reValue = "";
        
        for(int i = 0; i < this.dataList.size(); i++) {
            Map map = this.dataList.get(i);
            String ePath = StringHelper.null2void(map.get("PATH"));
            int index = StringHelper.null2zero(map.get("INDEX")); 
            
            if(ePath.equals(path)) {
                if(index == idx || idx == -1) {
                    reValue = StringHelper.null2void(map.get("VALUE"));
                    
                    break;
                }
            }
        }
        
        //if(log.isInfoEnabled()) log.info("COMMON - " + path + " = " + reValue);
        
        return reValue;
    }
    
    /**
     * XML에서 경로 및 인덱스에 해당하는 값을 구한다.
     * 
     * @param path XML full 경로
     * @param type 찾고자 하는 정보 타입(ITEM:자재)
     * @param idx  찾고자 하는 index번호
     * @return index가 -1인 경우 XML경로와 매칭되는 첫번째 값을 리턴함
     * @throws Exception
     */
    public String getValue(String path, String type, int idx) throws Exception {
        if(path == null) {
            if(log.isWarnEnabled()) log.warn("ITEM-XML parsing error : not found xml path.");
            return null;
        }
        
        String reValue = "";
        
        for(int i = 0; i < this.dataList.size(); i++) {
            Map map = this.dataList.get(i);
            String ePath = StringHelper.null2void(map.get("PATH"));
            int index = StringHelper.null2zero(map.get(type+"_INDEX"));
            
            if(ePath.equals(path)) {
                if(index == idx) {
                    reValue = StringHelper.null2void(map.get("VALUE"));
                    
                    break;
                }
            }
        }
        
        //if(log.isDebugEnabled()) log.debug("ITEM - " + path + " = " + reValue);
        
        return reValue;
    }
    
    /**
     * XML에서 경로 및 인덱스에 해당하는 값을 구한다.
     * 
     * @param path XML full 경로
     * @param type_1 찾고자 하는 정보 타입(ITEM:자재)
     * @param idx_1  찾고자 하는 index번호
     * @param type_2 찾고자 하는 정보 타입(FTA:협정)
     * @param idx_2  찾고자 하는 index번호
     * @return index가 -1인 경우 XML경로와 매칭되는 첫번째 값을 리턴함
     * @throws Exception
     */
    public String getValue(String path, String type_1, int idx_1, String type_2, int idx_2) throws Exception {
        if(path == null) {
            if(log.isWarnEnabled()) log.warn("AGREEMENT-XML parsing error : not found xml path.");
            return null;
        }
        
        String reValue = "";
        
        for(int i = 0; i < this.dataList.size(); i++) {
            Map map = this.dataList.get(i);
            String ePath = StringHelper.null2void(map.get("PATH"));
            int index1 = StringHelper.null2zero(map.get(type_1+"_INDEX"));
            int index2 = StringHelper.null2zero(map.get(type_2+"_INDEX"));
            
            if(ePath.equals(path)) {
                if(index1 == idx_1 && index2 == idx_2) {
                    reValue = StringHelper.null2void(map.get("VALUE"));
                    
                    break;
                }
            }
        }
        
        //if(log.isDebugEnabled()) log.debug("AGREEMENT - " + path + " = " + reValue);
        
        return reValue;
    }
    
    /**
     * 등록된 자재수를 리턴한다.
     * 
     * @param path XML 경로(Consistent.REQ_ITEM_ITEM_SEQ)
     * @return
     * @throws Exception
     */
    public int getItemCount(String path) throws Exception {
        if(path == null) {
            if(log.isWarnEnabled()) log.warn("XML parsing error : not found xml path.");
            return 0;
        }
        
        int rowCount = 0;
        
        // 등록된 자재의 Max 일련번호를 구한다.
        for(int i = 0; i < dataList.size(); i++) {
            Map map = dataList.get(i);
            String ePath = StringHelper.null2void(map.get("PATH"));
            
            if(ePath.equals(path)) {
                rowCount++;
            }
        }
        
        //if(log.isDebugEnabled()) log.debug("Item count = " + rowCount);
        
        return rowCount;
    }
    
    /**
     * 개별 자재내에 등록된 협정 등록수를 리턴한다.
     * 
     * @param path XML 경로(Consistent.REQ_AGRET_FTA_CD)
     * @param type 찾고자 하는 정보 타입(ITEM:자재)
     * @param idx  찾고자 하는 index번호
     * @return
     * @throws Exception
     */
    public int getAgreementCount(String path, String type, int idx) throws Exception {
        if(path == null) {
            if(log.isWarnEnabled()) log.warn("XML parsing error : not found xml path.");
            return 0;
        }
        
        int rowCount = 0;
        
        // 등록된 협정의 갯수를 구한다.
        for(int i = 0; i < dataList.size(); i++) {
            Map map = dataList.get(i);
            String ePath = StringHelper.null2void(map.get("PATH"));
            int index = StringHelper.null2zero(map.get(type+"_INDEX"));
            
            if(index == idx) {
                if(ePath.equals(path)) {
                    rowCount++;
                }
            }
        }
        
        if(log.isDebugEnabled()) log.debug("Item index = " + idx + ", Agreement count = " + rowCount);
        
        return rowCount;
    }
    
    /**
     * 원산지 확인서 송신용 XML 생성
     * 
     * @param coIssueMst
     * @param coIssueOriginList
     * @throws Exception
     */
    @SuppressWarnings({"rawtypes"})
    public String createVerificationOfOrigin2XML(Map coIssueMst, List coIssueOriginList) throws Exception {
    	Configurator configurator = ConfiguratorFactory.getInstance().getConfigurator();
    	String db = StringHelper.null2void(configurator.getString("application.db.type"));
    	String vendor_cd = StringHelper.null2void(coIssueMst.get("VENDOR_CD"));
        String outputFolder = this.getConfigFilePath("S", null, false, vendor_cd);//("xtrus.data.outbound");
        byte[] data = null;
        
        // Name space 정의
        Namespace rsm = Namespace.getNamespace("rsm", "urn:un:unece:uncefact:data:draft:VerificationOfOrigin:1.0");
        Namespace ram = Namespace.getNamespace("ram","urn:un:unece:uncefact:data:draft:ReusableAggregateBusinessInformationEntity:1.0");
        Namespace ccts = Namespace.getNamespace("ccts","urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2");
        Namespace ds = Namespace.getNamespace("ds","http://www.w3.org/2000/09/xmldsig#");
        Namespace qdt = Namespace.getNamespace("qdt","urn:un:unece:uncefact:data:standard:QualifiedDataType:8");
        Namespace udt = Namespace.getNamespace("udt","urn:un:unece:uncefact:data:standard:UnqualifiedDataType:9");
        Namespace xsi = Namespace.getNamespace("xsi","http://www.w3.org/2001/XMLSchema-instance");
        
        // root element 만들기
        Element FTABusinessDocument = new Element("FTABusinessDocument", rsm);
        
        //Name 스페이스 설정
        FTABusinessDocument.addNamespaceDeclaration(ccts);
        FTABusinessDocument.addNamespaceDeclaration(ds);
        FTABusinessDocument.addNamespaceDeclaration(qdt);
        FTABusinessDocument.addNamespaceDeclaration(ram);
        FTABusinessDocument.addNamespaceDeclaration(udt);
        FTABusinessDocument.addNamespaceDeclaration(xsi);
        FTABusinessDocument.setAttribute("schemaLocation", "urn:un:unece:uncefact:data:draft:VerificationOfOrigin:1.0 ../schema/VerificationOfOrigin.xsd", xsi);

        // 헤더 생성
        Element ICHD = new Element("InterchangeHeader", rsm);
        ICHD.addContent(new Element("CreationDateTime", rsm).setText(StringHelper.null2void(coIssueMst.get("DOC_CREATE_DATE"))));//문서 생성시간
        ICHD.addContent(new Element("ReferenceID", ram).setText(StringHelper.null2void(coIssueMst.get("REFERENCE_ID"))));//문서식별번호 - 문서 생성시 마다 자동생성 UUID
        
        Element SNPY = new Element("SenderParty", ram);//송신자 정보
        SNPY.addContent(new Element("IdentificationID", ram).setText(StringHelper.null2void(coIssueMst.get("SENDER_ID"))));//###송신식별자
        SNPY.addContent(new Element("AgencyID", ram).setText(StringHelper.null2void(coIssueMst.get("SENDER_QUAL"))));//송신 식별자 관리기관 코드        
        ICHD.addContent(SNPY);
        
        Element RCPY = new Element("ReceiverParty", ram);//수신자정보
        RCPY.addContent(new Element("IdentificationID", ram).setText(StringHelper.null2void(coIssueMst.get("RECEIVER_ID"))));//###수신자  식별자
        RCPY.addContent(new Element("AgencyID", ram).setText(StringHelper.null2void(coIssueMst.get("RECEIVER_QUAL"))));//수식 식별자 관리기관 코드
        ICHD.addContent(RCPY);
        
        Element APSD = new Element("ApplicationStandard", ram);//적용표준정보
        APSD.addContent(new Element("ControllingAgencyID", ram).setText("NIPA"));//문서 표준 관리 기관 코드
        APSD.addContent(new Element("ApplicationVersionID", ram).setText("3.0"));//표준 버젼
        APSD.addContent(new Element("ElementVersionID", ram).setText("1.0"));//문서버젼
        APSD.addContent(new Element("DocumentName", ram).setText(StringHelper.null2void(coIssueMst.get("DOCUMENT_NAME"))));//문서명
        ICHD.addContent(APSD);
        
        FTABusinessDocument.addContent(ICHD);
        
        // 확인서 제출서 정보
        Element VOON = new Element("VerificationOfOrigin", rsm);
        
        Element DOCI = new Element("DocumentIdentity", rsm);//문서 관리정보
        DOCI.addContent(new Element("ID", ram).setText(StringHelper.null2void(coIssueMst.get("DOCUMENT_ID")))); //###//문서 관리번호
        DOCI.addContent(new Element("TypeCode", ram).setText(StringHelper.null2void(coIssueMst.get("DOCUMENT_TYPE_CD")))); //###//문서 코드
        DOCI.addContent(new Element("Name", ram).setText(StringHelper.null2void(coIssueMst.get("DOCUMENT_NAME"))));//문서 명
        DOCI.addContent(new Element("ProcessingTypeCode", ram).setText(StringHelper.null2void(coIssueMst.get("DOCUMENT_PROCESSING")))); //###//문서 전송 구분 코드
        VOON.addContent(DOCI);
        
        Element VOOD = new Element("VerificationOfOriginDocument", rsm);//원산지 확인서 발급 정보
        VOOD.addContent(new Element("IssueID", ram).setText(StringHelper.null2void(coIssueMst.get("CO_CERTIFY_NO")))); //###//발급번호
        VOOD.addContent(new Element("IssueDateTime", ram).setText(StringHelper.null2void(coIssueMst.get("ISSUE_DATE")))); //###//작성일자        
        VOON.addContent(VOOD);
        
        Element IVPY = new Element("InvoicerParty", rsm);//공급자 정보
        IVPY.addContent(new Element("ID", ram).setText(vendor_cd)); //###//공급자 업체코드
        
        Element SIOZ = new Element("SpecifiedInvoicerOrganization", ram);//공급기관 정보
        SIOZ.addContent(new Element("ID", ram).setText(StringHelper.null2void(coIssueMst.get("VENDOR_BUSINESS_NO")))); //###//사업자번호
        SIOZ.addContent(new Element("Name", ram).setText(StringHelper.null2void(coIssueMst.get("VENDOR_NAME")))); //###//상호
        IVPY.addContent(SIOZ);
        
        
        Element RPPN = new Element("RepresentativePerson", ram);//대표자 정보
        RPPN.addContent(new Element("Name", ram).setText(StringHelper.null2void(coIssueMst.get("VENDOR_OFFICER_NAME")))); //###//대표자명
        IVPY.addContent(RPPN);
        
        Element SPAD = new Element("SpecifiedPostalAddress", ram);//주소정보
        SPAD.addContent(new Element("LineOne", ram).setText(StringHelper.null2void(coIssueMst.get("VENDOR_ADDRESS_ONE")))); //###//공급자  주소1
        SPAD.addContent(new Element("LineTwo", ram).setText(StringHelper.null2void(coIssueMst.get("VENDOR_ADDRESS_TWO")))); //###//공급자  주소2
        IVPY.addContent(SPAD);
        
        Element DFCT = new Element("DefinedContact", ram);//연락처 정보
        
        Element TLCT = new Element("TelephoneCommunication", ram);//전화번호정보
        TLCT.addContent(new Element("CompleteNumber", ram).setText(StringHelper.null2void(coIssueMst.get("VENDOR_IN_CHARGE_TEL_NO"))));//###전화번호        
        DFCT.addContent(TLCT);
        
        Element FXCT = new Element("FaxCommunication", ram);//FAX번호정보
        FXCT.addContent(new Element("CompleteNumber", ram).setText(StringHelper.null2void(coIssueMst.get("VENDOR_IN_CHARGE_FAX_NO"))));//###FAX번호정보        
        DFCT.addContent(FXCT);
        
        Element EUCT = new Element("EmailURICommunication", ram);//email
        EUCT.addContent(new Element("URIID", ram).setText(StringHelper.null2void(coIssueMst.get("VENDOR_IN_CHARGE_EMAIL"))));//###email        
        DFCT.addContent(EUCT);        
        
        IVPY.addContent(DFCT);
        VOON.addContent(IVPY);
        
        Element IVPT = new Element("InvoiceeParty", rsm);//공급처 정보
        IVPT.addContent(new Element("ID", ram).setText(StringHelper.null2void(coIssueMst.get("CUSTOMER_CD"))));//###공급처 업체코드
        
        Element SPOZ = new Element("SpecifiedInvoiceeOrganization", ram);//공급처 회사정보
        SPOZ.addContent(new Element("ID", ram).setText(StringHelper.null2void(coIssueMst.get("CUSTOMER_BUSINESS_NO"))));//###사업자번호
        SPOZ.addContent(new Element("Name", ram).setText(StringHelper.null2void(coIssueMst.get("CUSTOMER_NAME"))));//상호
        IVPT.addContent(SPOZ);
        
        Element RPTP = new Element("RepresentativePerson", ram);//대표자 정보
        RPTP.addContent(new Element("Name", ram).setText(StringHelper.null2void(coIssueMst.get("CUSTOMER_OFFICER_NAME"))));//###대표자명
        IVPT.addContent(RPTP);
        
        Element SADD = new Element("SpecifiedPostalAddress", ram);//주소정보
        SADD.addContent(new Element("LineOne", ram).setText(StringHelper.null2void(coIssueMst.get("CUSTOMER_ADDRESS_ONE"))));//###공급처  주소1
        SADD.addContent(new Element("LineTwo", ram).setText(StringHelper.null2void(coIssueMst.get("CUSTOMER_ADDRESS_TWO"))));//###공급처  주소2   
        IVPT.addContent(SADD);

        
        Element DFCN = new Element("DefinedContact", ram);//연락처 정보
        
        Element TLCN = new Element("TelephoneCommunication", ram);//전화번호정보 
        TLCN.addContent(new Element("CompleteNumber", ram).setText(StringHelper.null2void(coIssueMst.get("CUSTOMER_IN_CHARGE_TEL_NO"))));//###전화번호 
        DFCN.addContent(TLCN);

        
        Element FXCN = new Element("FaxCommunication", ram);//FAX번호정보 
        FXCN.addContent(new Element("CompleteNumber", ram).setText(StringHelper.null2void(coIssueMst.get("CUSTOMER_IN_CHARGE_FAX_NO"))));//###FAX번호 
        DFCN.addContent(FXCN);
         
        Element EUCN = new Element("EmailURICommunication", ram);//email
        EUCN.addContent(new Element("URIID", ram).setText(StringHelper.null2void(coIssueMst.get("CUSTOMER_IN_CHARGE_EMAIL"))));
        DFCN.addContent(EUCN);
        
        IVPT.addContent(DFCN);        
        VOON.addContent(IVPT);
        
        Element SGPY = new Element("SignerParty", rsm);
        
        Element SSOZ = new Element("SpecifiedSignerOrganization", ram);//서명권자 회사정보
        SSOZ.addContent(new Element("Name", ram).setText(StringHelper.null2void(coIssueMst.get("SING_COMPANY_NAME"))));//상호
        SGPY.addContent(SSOZ);
        
        Element CHPS = new Element("ChargePerson", ram);//서명자 정보
        CHPS.addContent(new Element("Name", ram).setText(StringHelper.null2void(coIssueMst.get("SIGNATURE_NAME"))));//작성자
        CHPS.addContent(new Element("Title", ram).setText(StringHelper.null2void(coIssueMst.get("SIGNATURE_POSITION"))));//직위
        
        if (db.equals("MSSQL") || db.equals("POSTGRESQL")) {
            data = (byte[]) coIssueMst.get("SIGNATURE_IMAGE");
        } else {
            Blob blob = (Blob) coIssueMst.get("SIGNATURE_IMAGE");
            data = blob.getBytes(1, (int) blob.length());
        }
        
        CHPS.addContent(new Element("SignatureBinaryDescription", ram).setText(
                StringHelper.null2void(Base64.getEncoder().encodeToString(data)).replaceAll("\n", "")));//서명 싸인 (base64)
        SGPY.addContent(CHPS);
        
        Element SFPA = new Element("SpecifiedPostalAddress", ram);//주소정보
        SFPA.addContent(new Element("LineOne", ram).setText(StringHelper.null2void(coIssueMst.get("SIGNATURE_ADDRESS_ONE"))));//서명권자 주소
        SFPA.addContent(new Element("LineTwo", ram).setText(StringHelper.null2void(coIssueMst.get("SIGNATURE_ADDRESS_TWO"))));
        SGPY.addContent(SFPA);
        
        VOON.addContent(SGPY);
        
        // 확인서 제출서의 원산지 정보
        for (int i=0; i< coIssueOriginList.size(); i++){
            Map reMap = (Map)coIssueOriginList.get(i);
            
            Element VOIM = new Element("VerificationOfOriginTradeLineItem", rsm);//공급 물품 정보
            VOIM.addContent(new Element("ID", ram).setText(StringHelper.null2void(reMap.get("ITEM_CD"))));//품목ID
            VOIM.addContent(new Element("SequenceNumeric", ram).setText(StringHelper.null2void(reMap.get("ITEM_SEQ"))));//연번
            VOIM.addContent(new Element("HSCodeID", ram).setText(StringHelper.null2void(reMap.get("HS_CODE"))));//HS번호
            VOIM.addContent(new Element("Name", ram).setText(StringHelper.null2void(reMap.get("ITEM_NAME"))));//품명
            VOIM.addContent(new Element("Description", ram).setText(StringHelper.null2void(reMap.get("STANDARD"))));//규격
            VOIM.addContent(new Element("SourcingInfoCode", ram).setText(StringHelper.null2string(reMap.get("SOURCING_CD"), "0")));//생산자 구분코드(1, 2, 3, 0)
            VOIM.addContent(new Element("SourcingInfoName", ram).setText(StringHelper.null2string(reMap.get("SOURCING_NAME"), "기타")));//생산자 구분코드 명칭(1:국내공장제조, 2:해외상품-해외공장생산, 3:해외상품-해외임가공, 0:기타 사유)
            
            Element CODR = new Element("CountryOfOriginDecisionExaminationResult", ram);//추가정보
            CODR.addContent(new Element("AgreementTypeCode", ram).setText(StringHelper.null2void(reMap.get("FTA_CD"))));//적용대상 협정
            CODR.addContent(new Element("ApprovalCode", ram).setText(StringHelper.null2void(reMap.get("ORIGIN_YN"))));//원산지기준 충족여부 Y: 충족, N: 미충족
            CODR.addContent(new Element("DecisionStandardStatement", ram).setText(StringHelper.null2void(reMap.get("RULE_CONTENTS"))));//원산지결정기준
            
            Element OCON = new Element("OriginCountry", ram);
            OCON.addContent(new Element("ID", ram).setText(StringHelper.null2void(reMap.get("FTA_NATION_CD"))));//원산지 코드
            OCON.addContent(new Element("Name", ram).setText(StringHelper.null2void(reMap.get("FTA_NATION_NAME"))));
            CODR.addContent(OCON);
            
            
            Element COCP = new Element("CountryOfOriginConfirmationPeriod", ram);//원산지 포괄확인 기간
            COCP.addContent(new Element("StartDateTime", ram).setText(StringHelper.null2void(reMap.get("APPLY_DATE"))));//시작일
            COCP.addContent(new Element("EndDateTime", ram).setText(StringHelper.null2void(reMap.get("END_DATE"))));//종료일자
            CODR.addContent(COCP);
            
            VOIM.addContent(CODR);
            
            VOON.addContent(VOIM);
        }
                
        Element RFDC = new Element("ReferenceDocument", rsm);//참조문서 정보   
        RFDC.addContent(new Element("ID", ram).setText(StringHelper.null2void(coIssueMst.get("REFERENCE_DOCUMENT_ID"))));//참조문서 번호 //미결요청서 번호, 기타  번호
        RFDC.addContent(new Element("Name", ram).setText(StringHelper.null2void(coIssueMst.get("REFERENCE_DOCUMENT_NAME"))));//참조문서명
        VOON.addContent(RFDC);           
        
        FTABusinessDocument.addContent(VOON);
        
        Document myDoc = new Document(FTABusinessDocument);
        
        XMLOutputter outputter = new XMLOutputter();
        FileOutputStream fos = null;
        OutputStreamWriter writer = null;
        
        StringBuffer buf = new StringBuffer();
        
        try {
            outputter.setFormat(Format.getPrettyFormat());
            
            buf.append(StringHelper.null2void(coIssueMst.get("SENDER_ID")));
            buf.append("-");
            buf.append(StringHelper.null2void(coIssueMst.get("RECEIVER_ID")));
            buf.append("-");
            buf.append(StringHelper.null2void(coIssueMst.get("DOCUMENT_TYPE_CD")));
            buf.append("-");
            buf.append(StringHelper.null2void(coIssueMst.get("DOC_CREATE_DATE")));
            buf.append("-");
            buf.append(StringHelper.null2void(coIssueMst.get("REFERENCE_ID")));
            buf.append(".xml");
           
            File dir = new File(outputFolder);

            if(!dir.isDirectory()){
                  dir.mkdirs();
            }
           
            fos = new FileOutputStream(outputFolder + "/" + buf.toString());
            writer = new OutputStreamWriter(fos, Constants.APPLICATION_FILE_ENCODING);
           
            outputter.output(myDoc, writer);
           
            if(log.isDebugEnabled()) {
                log.debug("Generate file info(path = " + outputFolder + ", name = " + buf.toString() + ")");
            }
        } catch(Exception e) {
            throw e;
        } finally {
            if(writer != null) writer.close();
            if(fos != null) fos.close();
        }
        
        return buf.toString();
    }
    
    /**
     * 원산지 확인서 송신용 XML 생성
     * 
     * @param coIssueMst
     * @param coIssueOriginList
     * @throws Exception
     */
    @SuppressWarnings({"rawtypes"})
    public String createRequestReturn2XML(Map requestReturn) throws Exception {
    	Configurator configurator = ConfiguratorFactory.getInstance().getConfigurator();
    	String db = StringHelper.null2void(configurator.getString("application.db.type"));
    	String vendor_cd = StringHelper.null2void(requestReturn.get("VENDOR_CD"));
        String outputFolder = this.getConfigFilePath("S", null, false, vendor_cd);//("xtrus.data.outbound");
        
        // Name space 정의
        Namespace rsm = Namespace.getNamespace("rsm", "urn:un:unece:uncefact:data:draft:RequestReturn:1.0");
        Namespace xsi = Namespace.getNamespace("xsi","http://www.w3.org/2001/XMLSchema-instance");
        Namespace ds = Namespace.getNamespace("ds","http://www.w3.org/2000/09/xmldsig#");
        Namespace ram = Namespace.getNamespace("ram","urn:un:unece:uncefact:data:draft:ReusableAggregateBusinessInformationEntity:1.0");
        
        // root element 만들기
        Element FTABusinessDocument = new Element("FTABusinessDocument", rsm);
        
        // Name 스페이스 설정
        FTABusinessDocument.addNamespaceDeclaration(ds);
        FTABusinessDocument.addNamespaceDeclaration(xsi);
        FTABusinessDocument.addNamespaceDeclaration(ram);
        FTABusinessDocument.setAttribute("schemaLocation", "urn:un:unece:uncefact:data:draft:RequestReturn:1.0 RequestReturn.xsd", xsi);
        
        // 송신자/수신자 정보
        Element ICHD = new Element("InterchangeHeader", rsm);
        ICHD.addContent(new Element("ReferenceID", ram).setText(StringHelper.null2void(requestReturn.get("REFERENCE_ID"))));//문서식별번호 - 문서 생성시 마다 자동생성 UUID
        
        Element SNPY = new Element("SenderParty", ram);//송신자 정보
        SNPY.addContent(new Element("IdentificationID", ram).setText(StringHelper.null2void(requestReturn.get("SENDER_ID"))));//###송신식별자
        SNPY.addContent(new Element("AgencyID", ram).setText(StringHelper.null2void(requestReturn.get("SENDER_QUAL"))));//송신 식별자 관리기관 코드        
        ICHD.addContent(SNPY);
        
        Element RCPY = new Element("ReceiverParty", ram);//수신자정보
        RCPY.addContent(new Element("IdentificationID", ram).setText(StringHelper.null2void(requestReturn.get("RECEIVER_ID"))));//###수신자  식별자
        RCPY.addContent(new Element("AgencyID", ram).setText(StringHelper.null2void(requestReturn.get("RECEIVER_QUAL"))));//수식 식별자 관리기관 코드
        ICHD.addContent(RCPY);
        
        Element APSD = new Element("ApplicationStandard", ram);//적용표준정보
        APSD.addContent(new Element("ControllingAgencyID", ram).setText("NIPA"));//문서 표준 관리 기관 코드
        APSD.addContent(new Element("ApplicationVersionID", ram).setText("3.0"));//표준 버젼
        APSD.addContent(new Element("ElementVersionID", ram).setText("1.0"));//문서버젼
        APSD.addContent(new Element("DocumentName", ram).setText(StringHelper.null2void(requestReturn.get("DOCUMENT_NAME"))));//문서명
        ICHD.addContent(APSD);
        
        FTABusinessDocument.addContent(ICHD);
        
        // 반송서 정보
        Element RERE = new Element("RequestReturn", rsm);
        
        Element DOCI = new Element("DocumentIdentity", rsm);//문서 관리정보
        DOCI.addContent(new Element("ID", ram).setText(StringHelper.null2void(requestReturn.get("DOCUMENT_ID")))); //###//문서 관리번호
        DOCI.addContent(new Element("TypeCode", ram).setText(StringHelper.null2void(requestReturn.get("DOCUMENT_TYPE_CD")))); //###//문서 코드
        DOCI.addContent(new Element("Name", ram).setText(StringHelper.null2void(requestReturn.get("DOCUMENT_NAME"))));//문서 명
        DOCI.addContent(new Element("ProcessingTypeCode", ram).setText(StringHelper.null2void(requestReturn.get("DOCUMENT_PROCESSING")))); //###//문서 전송 구분 코드
        RERE.addContent(DOCI);
        
        RERE.addContent(new Element("RequestDateTime", rsm).setText(StringHelper.null2void(requestReturn.get("ISSUE_DATE")))); // 요청서 내 요청일자
        
        Element TTTLI = new Element("RequestReturnTradeLineItem", rsm);//문서 관리정보
        TTTLI.addContent(new Element("ID", ram).setText(StringHelper.null2void(requestReturn.get("ITEM_CD")))); //###//품목
        TTTLI.addContent(new Element("SequenceNumeric", ram).setText(StringHelper.null2void(requestReturn.get("ITEM_SEQ")))); //###//품목SEQ
        TTTLI.addContent(new Element("Name", ram).setText(StringHelper.null2void(requestReturn.get("ITEM_NAME"))));//품목명
        TTTLI.addContent(new Element("ReturnCode", ram).setText(StringHelper.null2void(requestReturn.get("RETURN_CD")))); //###//반송코드
        TTTLI.addContent(new Element("Description", ram).setText(StringHelper.null2void(requestReturn.get("RETURN_DESC")))); //###//반송사유
        TTTLI.addContent(new Element("AgreementTypeCode", ram).setText(StringHelper.null2void(requestReturn.get("FTA_CD")))); //###//협정코드
        RERE.addContent(TTTLI);
        
        Element RFDC = new Element("ReferenceDocument", rsm);//참조문서 정보   
        RFDC.addContent(new Element("ID", ram).setText(StringHelper.null2void(requestReturn.get("REFERENCE_DOCUMENT_ID"))));//참조문서 번호 //미결요청서 번호, 기타  번호
        RFDC.addContent(new Element("Name", ram).setText(StringHelper.null2void(requestReturn.get("REFERENCE_DOCUMENT_NAME"))));//참조문서명
        RERE.addContent(RFDC);           
        
        FTABusinessDocument.addContent(RERE);
        
        Document myDoc = new Document(FTABusinessDocument);
        
        XMLOutputter outputter = new XMLOutputter();
        FileOutputStream fos = null;
        OutputStreamWriter writer = null;
        
        StringBuffer buf = new StringBuffer();
        
        try {
            outputter.setFormat(Format.getPrettyFormat());
            
            buf.append(StringHelper.null2void(requestReturn.get("SENDER_ID")));
            buf.append("-");
            buf.append(StringHelper.null2void(requestReturn.get("RECEIVER_ID")));
            buf.append("-");
            buf.append(StringHelper.null2void(requestReturn.get("DOCUMENT_TYPE_CD")));
            buf.append("-");
            buf.append(StringHelper.null2void(requestReturn.get("DOC_CREATE_DATE")));
            buf.append("-");
            buf.append(StringHelper.null2void(requestReturn.get("REFERENCE_ID")));
            buf.append(".xml");
           
            File dir = new File(outputFolder);

            if(!dir.isDirectory()){
                  dir.mkdirs();
            }
           
            fos = new FileOutputStream(outputFolder + "/" + buf.toString());
            writer = new OutputStreamWriter(fos, Constants.APPLICATION_FILE_ENCODING);
           
            outputter.output(myDoc, writer);
           
            if(log.isDebugEnabled()) {
                log.debug("Generate file info(path = " + outputFolder + ", name = " + buf.toString() + ")");
            }
        } catch(Exception e) {
            throw e;
        } finally {
            if(writer != null) writer.close();
            if(fos != null) fos.close();
        }
        
        return buf.toString();
    }
    
    /**
     * 요청서 및 제출서에 대한 연관성이 있는 인터페이스 항목을 체크하여 실행여부를 리턴
     * 
     * @param fileCode
     * @param batchCode
     * @param itemType
     * @return
     */
    public boolean isRunning(String fileCode, String batchCode, String itemType) {
        boolean rst = false;
       
        if(batchCode.equals(fileCode) || "P".equals(itemType)) { // 프로시져인 경우에는 무조건 실행
            rst = true;
        } else {
             if("RPT_FH_001".equals(fileCode)) { // 요청서
                 if(batchCode.equals("RPT_FH_002")) {
                     rst = true;
                 } else if(batchCode.equals("RPT_FH_003")) {
                     rst = true;
                 }
             } else if("RPT_FH_004".equals(fileCode)) { // 제출서
                 if(batchCode.equals("RPT_FH_005")) {
                     rst = true;
                 } else if(batchCode.equals("RPT_FH_006")) {
                     rst = true;
                 }
             }
        }
       
        return rst;
    }
    
    /**
     * 인터페이스명을 리턴
     * 
     * @return
     */
    public static String getInterfaceName(String interfaceId, Locale locale) {
        String interfaceName = null;
        MessageResource resource = MessageResource.getMessageInstance();
        
        if(interfaceId == null) return interfaceName;
        
        if(interfaceId.equals("RPT_FH_001")) { // 요청서
            interfaceName = resource.getMessage("LETRQ", null, locale);
        } else if(interfaceId.equals("RPT_FH_004")) { // 제출서
            interfaceName = resource.getMessage("PREDOC", null, locale);
        } else if(interfaceId.equals("RPT_FH_007")) { // 결과서
            interfaceName = resource.getMessage("TXT_RESULT_CERTIFY", null, locale);
        } else if(interfaceId.equals("RPT_FH_008")) { // 복사본
            interfaceName = resource.getMessage("CNFDO, MESCT", null, locale);
        } else if(interfaceId.equals("RPT_FH_009")) { // 반송서
            interfaceName = resource.getMessage("RETNRDOC", null, locale);
        }
        
        return interfaceName;
    }
    
    /**
     * 환경설정(app_resource.properties)에 설정된 FTA HUB 파일 경로를 리턴 
     * 
     * @param flag 송/수신 구분(S:송신, R:수신)
     * @return 송신 또는 수신 폴더 
     */
    public static String getConfigFilePath(String flag) throws Exception{
    	return getConfigFilePath(flag, null, false, "");
    }
    
    /**
     * 환경설정(app_resource.properties)에 설정된 FTA HUB 파일 경로를 리턴 
     * 
     * @param flag 송/수신 구분(S:송신, R:수신)
     * @param docType 문서구분코드
     * @return  송신 또는 수신 후 배치처리가 완료된 폴더
     */
    public static String getConfigFilePath(String flag, String docType) throws Exception{
    	return getConfigFilePath(flag, docType, true, "");
    }
    
    /**
     * 환경설정(app_resource.properties)에 설정된 FTA HUB 파일 경로를 리턴 
     * 
     * @param flag 송/수신 구분(S:송신, R:수신)
     * @param docType 문서구분코드
     * @param hubID 바츠 연계 ID(회사대표ID 4자리)
     * @return  송신 또는 수신 후 배치처리가 완료된 폴더
     */
    public static String getConfigFilePath(String flag, String docType, String hubID) throws Exception{
    	return getConfigFilePath(flag, docType, true, hubID);
    }
    
    /**
     * 환경설정(app_resource.properties)에 설정된 FTA HUB 파일 경로를 리턴 
     * @param flag 송/수신 구분(S:송신, R:수신)
     * @param docType 문서구분코드
     * @param status 처리상태(false:처리전, true:처리 완료)
     * @param hubID 바츠 연계 ID(회사대표ID 4자리)
     * @return
     */
    public static String getConfigFilePath(String flag, String docType, boolean status, String hubID) throws Exception{
    	Configurator configurator = ConfiguratorFactory.getInstance().getConfigurator();
    	String path = "";
    	
    	if(docType == null || docType.isEmpty()) status = false;
    		
    	if("R".equals(flag)) { // 수신
    		if(status) {
	    		if("RPT_FH_001".equals(docType) || "F103".equals(docType)) { // 요청서
	    			path = StringHelper.null2void(configurator.getString("xtrus.data.finish.inbound.pending"));
	    		} else if("RPT_FH_004".equals(docType) || "F100".equals(docType)) { // 제출서(포괄 원산지 확인서)
	    			path = StringHelper.null2void(configurator.getString("xtrus.data.finish.inbound.origin"));
	    		} else if("RPT_FH_004".equals(docType) || "F101".equals(docType)) { // 제출서(원산지 증명서)
	    			path = StringHelper.null2void(configurator.getString("xtrus.data.finish.inbound.origin"));
	    		} else if("RPT_FH_007".equals(docType) || "F104".equals(docType)) { // 결과서
	    			path = StringHelper.null2void(configurator.getString("xtrus.data.finish.inbound.trresult"));
	    		} else if("RPT_FH_008".equals(docType) || "F105".equals(docType)) { // 복사본
	    			path = StringHelper.null2void(configurator.getString("xtrus.data.finish.inbound.copy"));
	    		} else if("RPT_FH_009".equals(docType) || "F108".equals(docType)) { // 반송서
	    			path = StringHelper.null2void(configurator.getString("xtrus.data.finish.inbound.return"));
	    		}
    		} else {
    			path = StringHelper.null2void(configurator.getString("xtrus.data.inbound"));
    		}
    	} if("S".equals(flag)) { // 송신
    		if(status) {
    			path = StringHelper.null2void(configurator.getString("xtrus.data.outbound.temp"));
    		} else {
    			path = StringHelper.null2void(configurator.getString("xtrus.data.outbound"));
    		}
    	}
    	
    	if(!path.isEmpty() && path.indexOf("{0}") > -1) {
    		path = path.replace("{0}", hubID);
    		
    		if(log.isInfoEnabled()) log.info("Mutil HUB Path = " + path);
    	} else {
    		if(log.isInfoEnabled()) log.info("Single HUB Path = " + path);
    	}
    	
    	return path;
    }
   
}