<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : MM-A203_01.jsp
  * @Description : 포괄 확인서 발급
  * @Modification Information
  *
  * author carlos
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMA203_01_dailog_01"으로 하셔야 합니다.
  *
  *** 부모창에서 넘기는 파라매터 ID
  *** 확인서 기본정보
  * 확인서 증명번호 : CO_DOC_NO
  * 거래처          : VENDOR_CD, VENDOR_NAME 
  * 원산지 증명유형 : CO_DOC_TYPE
  * 증명서 발행일   : PUBLISH_DATE
  * 포괄기간        : ORIGINAL_APPLY_DATE, ORIGINAL_END_DTAE
  * 성명            : VENDOR_CHARGE_NAME
  * 연락처          : VENDOR_CHARGE_PHONE_NO
  * 이메일          : VENDOR_CHARGE_EMAIL
  * 서명파일        : SIGN_CARD
  * 첨부파일        : FILE_PATH, FILE_NAME
  *** 생산자 정보
  * 업체명          : PRODUCER_NAME
  * 사업자등록번호  : PRODUCER_BUSINESS_NO
  * 대표자명        : PRODUCER_PRESIDENT_NAME
  * 전화번호        : PRODUCER_PHONE_NO
  * 팩스번호        : PRODUCER_FAX_NO
  * 이메일          : PRODUCER_EMAIL
  * 주소            : PRODUCER_ADDRESS
  
  */
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'north',border:false">
        <div class="easyui-layout" data-options="fit:true">
            <div data-options="region:'north',border:false" style="height:25px;overflow:hidden;">
	          <div class="btn_R">
	              <a href="javascript:MMA203_01.dialog.openDialog_1();" id="MMA203_01_btn_11" class="btnPopInvoice" style="display:none;"><spring:message code="INVIC,MOD"/></a>
	              <a href="javascript:MMA203_01.control.issueReport();" id="MMA203_01_btn_12" class="btnPopIssue"><spring:message code="CERIS"/></a>
	              <a href="javascript:MMA203_01.control.updateSendToCustomer();" id="MMA203_01_btn_14" class="btnSave" style="display:none;"><spring:message code="MSG_APPLY_CO_SUBMIT, MOD"/></a>
                  <a href="javascript:MMA203_01.dialog.openDialog_2();" id="MMA203_01_btn_13" class="btnPopPrint" style="display:none;"><spring:message code="OUTP"/></a>
	          </div>
	        </div>
	        <div data-options="region:'center',border:false" style="overflow:hidden;">
                <form id="MMA203_01_form_01" name="MMA203_01_form_01" method="POST">
                    <input type="hidden" id="flag" name="flag" value="${flag}"/>
                    <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
                    <input type="hidden" id="YYYYMM" name="YYYYMM" value="${YYYYMM}"/>
                    <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
                    <input type="hidden" id="DIVISION_CD" name="DIVISION_CD" value="${DIVISION_CD}"/>
                    <input type="hidden" id="CUSTOMER_CD" name="CUSTOMER_CD" value="${CUSTOMER_CD}"/>
                    <input type="hidden" id="INVOICE_DATE" name="INVOICE_DATE" value="${INVOICE_DATE}"/>
                    <input type="hidden" id="FTA_CD" name="FTA_CD" value="${FTA_CD}"/>
                    <input type="hidden" id="EXPORT_FLAG" name="EXPORT_FLAG" value="${EXPORT_FLAG}"/>
                    <input type="hidden" id="CO_ISSUE_TYPE" name="CO_ISSUE_TYPE" value="${CO_ISSUE_TYPE}"/>
                    <input type="hidden" id="SALES_MGMT_NO" name="SALES_MGMT_NO" value="${SALES_MGMT_NO}"/>
                    <input type="hidden" id="REQUESTER" name="REQUESTER"/>
                    <input type="hidden" id="REQUEST_DATE" name="REQUEST_DATE"/>
                    <input type="hidden" id="REASON_CONTENTS" name="REASON_CONTENTS"/>
                    <input type="hidden" id="CURRENCY" name="CURRENCY"/>
                    <input type="hidden" id="CLIENT_SEND_YN" name="CLIENT_SEND_YN" value="${CLIENT_SEND_YN}"/>
                    <input type="hidden" id="gridRows" name="gridRows"/> <!-- 보고서 발급 대상 리스트 -->
                    <input type="hidden" id="APPLY_FTA_CD" name="APPLY_FTA_CD" value="${APPLY_FTA_CD}"/>
                    <div style="float:top;">
                        <p class="h2"><spring:message code="CNFDO, TXT_BASICINFO"/><%--확인서 기본정보--%></p>
                        <table class="dataT">
                            <caption><spring:message code="DTA, TB"/><%--데이타 테이블--%></caption>
                            <colgroup>
                                <col width="100px" />
                                <col width="100px" />
                                <col width="190px" />
                                <col width="120px" />
                                <col width="150px" />
                                <col width="140px" />
                                <col width=";" />
                            </colgroup>
                            <tbody>
                                <tr>
                                    <th scope="row" class="point2" colspan="2">
                                        <div style="float:left;"><spring:message code="TXT_SEARCH_COO_CERTIFY_NO"/></div>
                                        <span id="MMA203_01_form_01_chkAuto" style="float:right;">
                                            <input type="checkbox" name="chkAuto" id="chkAuto" onclick="javascript:MMA203_01.ui.changeAutoCheck();" value="Y" checked/> Auto
                                        </span>
                                    </th>
                                    <td>
                                        <input type="text" name="CO_DOC_NO" id="CO_DOC_NO" value="${CO_DOC_NO}" class="easyui-validatebox" validType="['maxlength[100]']" style="width:98%;" title="<spring:message code="CNFDO, TXT_SEARCH_COO_CERTIFY_NO"/>"/>
                                    </td>
                                    <th scope="row">
                                        <spring:message code="CSTMR"/><%--거래처--%>
                                    </th>
                                    <td>
                                        <input type="text" name="CUSTOMER_NAME" id="CUSTOMER_NAME" value="${CUSTOMER_NAME}" readonly class="input_readOnly" style="width:98%;" title="<spring:message code="TXT_DEAL_VENDOR"/>" />
                                    </td>
                                    <th scope="row">
                                        <spring:message code="CERIS,TYPE"/><%--원산지 증명유형--%>
                                    </th>
                                    <td>
                                        <input type="text" name="CO_DOC_TYPE" id="CO_DOC_TYPE" value="${CO_DOC_TYPE}" style="display:none;"/>
                                        <input type="text" name="CO_DOC_TYPE_NAME" id="CO_DOC_TYPE_NAME" value="${CO_DOC_TYPE_NAME}" style="width:38%;" readonly class="input_readOnly"/>/
                                        <input type="text" name="CO_ISSUE_TYPE_NAME" id="CO_ISSUE_TYPE_NAME" value="${CO_ISSUE_TYPE_NAME}" style="width:48%;" readonly class="input_readOnly"/>
                                    </td>
                                </tr>
                                <tr id="MMA203_01_form_01_export_view">
                                    <th scope="row" colspan="2">
                                        <spring:message code="TXT_INVOICE_NO"/><%--Invoice 번호--%>
                                    </th>
                                    <td>
                                        <input type="text" name="INVOICE_NO" value="${INVOICE_NO}" id="INVOICE_NO" readonly class="easyui-validatebox input_readOnly"  style="width:98%;"/>
                                    </td>
                                    <th scope="row">
                                        <spring:message code="TXT_SHIPPING_DATE"/><%--출항일--%>
                                    </th>
                                    <td>
                                        <input type="text" name="SHIPPING_DATE" value="${SHIPPING_DATE}" id="SHIPPING_DATE" readonly class="easyui-validatebox input_readOnly"  style="width:95px;"/>
                                    </td>
                                    <th scope="row">
                                        <spring:message code="TXT_FLDTCTR"/><%--최종도착국가--%>
                                    </th>
                                    <td>
                                        <input type="text" name="FINAL_DESTINATION_NAME" value="${FINAL_DESTINATION_NAME}" id="FINAL_DESTINATION_NAME" readonly class="easyui-validatebox input_readOnly"  style="width:98%;"/>
                                    </td>
                                </tr>
                                <tr>
                                    <th scope="row" class="point2" colspan="2">
                                        <spring:message code="CERIS,DATE"/><%--증명서 발행일--%>
                                    </th>
                                    <td>
                                        <input name="ISSUE_DATE" id="ISSUE_DATE" value="${ISSUE_DATE}" style="width:95px;"/>
                                    </td>
                                    <th scope="row" class="point2">
                                        <spring:message code="AGRET, GROUP"/><%--증명서 발행일--%>
                                    </th>
                                    <td>
                                        <input type="hidden" id="FTA_GROUP_CD" name="FTA_GROUP_CD" value="${FTA_GROUP_CD}"/>
                                        <input type="text" id="FTA_GROUP_CD_NAME" name="FTA_GROUP_CD_NAME" value="${FTA_GROUP_CD_NAME}" readonly class="input_readOnly" style="width:98%;"/>
                                    </td>
                                    <th scope="row" class="point2" id="MMA203_01_form_01_period_text">
                                        <spring:message code="TXT_COVER_DATE"/><%--포괄기간--%>
                                    </th>
                                    <td id="MMA203_01_form_01_period_date">
                                        <input name="APPLY_DATE" id="APPLY_DATE" value="${APPLY_DATE}" style="width:95px;"/>~
                                        <input name="END_DATE" id="END_DATE" value="${END_DATE}" style="width:95px;"/>
                                    </td>
                                </tr>
                                <tr>
                                    <th scope="row" rowspan="2">
                                        <spring:message code="TXT_THPIC_SIGN"/>
                                    </th>
                                    <th scope="row" class="point2">
                                        <spring:message code="AUSIN"/>
                                    </th>
                                    <td>
                                        <input id="SIGNATURE_SEQ" name="SIGNATURE_SEQ" value="${SIGNATURE_SEQ}" class="easyui-validatebox" style="width:180px;" title="<spring:message code="FUNM"/>" />
                                    </td>
                                    <th scope="row">
                                        <spring:message code="POSIT"/>
                                    </th>
                                    <td>
                                        <input type="text" id="SIGN_POSITION" name="SIGN_POSITION" value="${SIGN_POSITION}" readonly class="input_readOnly"  style="width:98%;"/>
                                    </td>
                                    <th scope="row" class="point2" rowspan="2">
                                        <spring:message code="TXT_SIGN_FILE"/>
                                    </th>
                                    <td valign="bottom" rowspan="2">
                                        <img src="" id="MMA203_01_form_01_SIGN_CARD" name="MMA203_01_form_01_SIGN_CARD" style="width:100px;height:45px;"/>
                                    </td>
                                </tr>
                                <tr>
                                    <th>
                                        <spring:message code="CNTACT"/><%--연락처--%>
                                    </th>
                                    <td>
                                        <input type="text" id="SIGN_PHONE_NO" name="SIGN_PHONE_NO" value="${SIGN_PHONE_NO}" readonly class="input_readOnly" style="width:98%"/>
                                    </td>
                                    <th scope="row">
                                        <spring:message code="TXT_COMPANY_END_DATE"/>
                                    </th>
                                    <td>
                                        <input type="text" id="SIGN_END_DATE" name="SIGN_END_DATE" value="${SIGN_END_DATE}" readonly class="input_readOnly"  style="width:98%;"/>
                                    </td>
                                </tr>
                                <tr>
                                    <th scope="row" colspan="2">
                                        <spring:message code="CERIS,REASN"/><%--발급사유--%>
                                    </th>
                                    <td colspan="5">
                                        <span id="MMA203_01_span_01" style="display:none;"><a href="javascript:MMA203_01.dialog.openDialog_3('view');"><img src="/images/icon/external_link.png" boder="0"/></a></span>
                                        <input name="REASON_TITLE" id="REASON_TITLE" style="width:97%"/>
                                    </td>
                                </tr>
                                <tr>
                                    <th colspan="2"><spring:message code="MSG_APPLY_CO_SUBMIT"/></th>
                                    <td colspan="5">
                                        <input type="checkbox" id="CO_SHARE_YN" name="CO_SHARE_YN" value="${CO_SHARE_YN}" onclick="javascript:MMA203_01.ui.changeCoShareCheck();"/> <spring:message code="APPLY"/> (<spring:message code="MSG_AUTO_CO_ISSUE"/>)
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div style="float:top;">
                        <p class="h2"><spring:message code="EXPER"/></p>
                        <table class="dataT">
                            <caption><spring:message code="TXT_EXPER_INFO"/></caption>
                            <colgroup>
                                <col width="140px" />
                                <col width="" />
                                <col width="140px" />
                                <col width="" />
                                <col width="140px" />
                                <col width="" />
                            </colgroup>
                            <tbody>
                                <tr>
                                    <th scope="row" class="point2"><spring:message code="EXPNM"/></th>
                                    <td><input type="text" name="EXPORTER_NAME" id="EXPORTER_NAME" value="${EXPORTER_NAME}" class="easyui-validatebox" required="true" validType="['maxlength[100]']"  title="<spring:message code="EXPNM"/>" style="width:98%"/> </td>
                                    <th scope="row" class="point2"><spring:message code="RSTNM"/></th>
                                    <td><input type="text" name="EXPORTER_PRESIDENT_NAME" id="EXPORTER_PRESIDENT_NAME" value="${EXPORTER_PRESIDENT_NAME}" class="easyui-validatebox" required="true" validType="['maxlength[100]']"  title="<spring:message code="RSTNM"/>" style="width:98%"/> </td>
                                    <th scope="row" class="point2"><spring:message code="BZRGN"/></th>
                                    <td><input type="text" name="EXPORTER_BUSINESS_NO" id="EXPORTER_BUSINESS_NO" value="${EXPORTER_BUSINESS_NO}" class="easyui-validatebox" required="true" validType="['maxlength[50]']"  title="<spring:message code="BZRGN"/>" style="width:98%"/> </td>
                                </tr>
                                <tr>
                                    <th scope="row" class="point2"><spring:message code="TELNM"/></th>
                                    <td><input type="text" name="EXPORTER_PHONE_NO" id="EXPORTER_PHONE_NO" value="${EXPORTER_PHONE_NO}" class="easyui-validatebox" required="true" validType="['maxlength[20]']"  title="<spring:message code="TELNM"/>" style="width:98%"/> </td>
                                    <th scope="row" class="point2"><spring:message code="FAX"/></th>
                                    <td><input type="text" name="EXPORTER_FAX_NO" id="EXPORTER_FAX_NO" value="${EXPORTER_FAX_NO}" class="easyui-validatebox" required="true" validType="['maxlength[20]']"  title="<spring:message code="FAX"/>" style="width:98%"/> </td>
                                    <th scope="row" class="point2"><spring:message code="TXT_EMAIL"/></th>
                                    <td><input type="text" name="EXPORTER_EMAIL" id="EXPORTER_EMAIL" value="${EXPORTER_EMAIL}" class="easyui-validatebox" required="true" validType="'email'" title="<spring:message code="TXT_EMAIL"/>" style="width:98%"/> </td>
                                </tr>
                                <tr>
                                    <th scope="row" class="point2"><spring:message code="ADDRS"/></th>
                                    <td colspan="5"><input type="text" name="EXPORTER_ADDRESS" id="EXPORTER_ADDRESS" value="${EXPORTER_ADDRESS}" class="easyui-validatebox" validType="['maxlength[300]']" title="<spring:message code="ADDRS"/>" style="width:99.5%"/> </td>
                                </tr>
                            </tbody>
                        </table> 
                    </div>
                    <div style="float:top;">
                        <p class="h2"><spring:message code="PRDER"/></p>
                        <table class="dataT" style="margin-bottom:0px;">
                            <caption>생산자 정보</caption>
                            <colgroup>
                                <col width="140px" />
                                <col width="" />
                                <col width="140px" />
                                <col width="" />
                            </colgroup>
                            <tbody>
                                <tr>
                                    <th scope="row" class="point2"><spring:message code="EXPNM"/></th>
                                    <td><input type="text" name="PRODUCER_NAME" id="PRODUCER_NAME" value="${PRODUCER_NAME}" class="easyui-validatebox" required="true" title="<spring:message code="EXPNM"/>" style="width:98%"/> </td>
                                    <th scope="row"><spring:message code="RSTNM"/></th>
                                    <td><input type="text" name="PRODUCER_PRESIDENT_NAME" id="PRODUCER_PRESIDENT_NAME" value="${PRODUCER_PRESIDENT_NAME}" title="<spring:message code="RSTNM"/>" style="width:98%"/> </td>
                                    <th scope="row"><spring:message code="BZRGN"/></th>
                                    <td><input type="text" name="PRODUCER_BUSINESS_NO" id="PRODUCER_BUSINESS_NO" value="${PRODUCER_BUSINESS_NO}" title="<spring:message code="BZRGN"/>" style="width:98%"/> </td>
                                </tr>
                                <tr>
                                    <th scope="row"><spring:message code="TELNM"/></th>
                                    <td><input type="text" name="PRODUCER_PHONE_NO" id="PRODUCER_PHONE_NO" value="${PRODUCER_PHONE_NO}" title="<spring:message code="TELNM"/>" style="width:98%"/> </td>
                                    <th scope="row"><spring:message code="FAX"/></th>
                                    <td><input type="text" name="PRODUCER_FAX_NO" id="PRODUCER_FAX_NO" value="${PRODUCER_FAX_NO}" title="<spring:message code="FAX"/>" style="width:98%"/> </td>
                                    <th scope="row"><spring:message code="TXT_EMAIL"/></th>
                                    <td><input type="text" name="PRODUCER_EMAIL" id="PRODUCER_EMAIL" value="${PRODUCER_EMAIL}" title="<spring:message code="TXT_EMAIL"/>" style="width:98%"/> </td>
                                </tr>
                                <tr>
                                    <th scope="row"><spring:message code="ADDRS"/></th>
                                    <td colspan="5"><input type="text" name="PRODUCER_ADDRESS" id="PRODUCER_ADDRESS" value="${PRODUCER_ADDRESS}" title="<spring:message code="ADDRS"/>" style="width:99.5%"/> </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div data-options="region:'center',border:false" style="padding-top:12px;">
        <div class="easyui-layout" data-options="fit:true">
            <div data-options="region:'north',border:false">
                <div class="h2_etc">
                    <p class="h2"><spring:message code="TXT_ITEMBY_ORG_INFO"/><%--품목별 원산지 정보--%></p>
                    <p class="h2_btn">
                        <a href="javascript:MMA203_01.dialog.openDialog_5();" class="btnEdit"><spring:message code="NOMEET, REASN, REGER"/></a>
                        <span id="MMA203_01_span_02" style="display:none;"><a href="javascript:MMA203_01.dialog.openDialog_4();" class="btnSelectClick"><spring:message code="DTSTA,RECOD"/></a></span>
                        <a href="javascript:MMA203_01.excel.excelDownload_1();" class="btnExlDown"><spring:message code="BTN_EXCEL"/></a>
                    </p>
                </div>
            </div>
            <div data-options="region:'center',border:false">
                <form id="MMA203_01_form_02" name="MMA203_01_form_02" method="POST">
                    <input type="hidden" id="FTA_GROUP_CD" name="FTA_GROUP_CD" value="${FTA_GROUP_CD}"/>
                    <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
                    <input type="hidden" id="APPLY_FTA_CD" name="APPLY_FTA_CD" value="${APPLY_FTA_CD}"/>
                    <input type="hidden" id="EXPORT_FLAG" name="EXPORT_FLAG" value="${EXPORT_FLAG}"/>
                    <input type="hidden" id="gridData" name="gridData"/> <!-- 포괄확인서 대상 리스트 -->
                </form>
                <table id="MMA203_01_grid_01"></table>
            </div>
        </div>
    </div>
</div>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-A203_01.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>