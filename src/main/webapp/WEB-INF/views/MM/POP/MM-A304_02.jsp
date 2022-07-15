<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : MM-A304_02.jsp
  * @Description : 레포트 발급(베트남 -내수)
  * @Modification Information
  *
  * author YNI-Maker(이메일)
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMA304_02_dailog_01"으로 하셔야 합니다.
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
	                  <a href="javascript:MMA304_02.control.issueReport();" id="MMA304_02_btn_12" class="btnPopIssue"><spring:message code="CERIS"/></a>
	                  <a href="javascript:MMA304_02.dialog.openDialog_2();" id="MMA304_02_btn_13" class="btnPopPrint" style="display:none;"><spring:message code="OUTP"/></a>
	                  <a href="javascript:dialog.handle.close(dialog.getObject('MMA304_02_dailog_01'));" id="MMA304_02_btn_15" class="btnPopClose" style="display:none;"><spring:message code="CLOSE"/><%--닫기--%></a>
	              </div>
	            </div>
	            <div data-options="region:'center',border:false" style="overflow:hidden;">
	                <form id="MMA304_02_form_01" name="MMA304_02_form_01" method="POST">
	                    <input type="hidden" id="flag" name="flag" value="${flag}"/>
	                    <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
	                    <input type="hidden" id="YYYYMM" name="YYYYMM" value="${YYYYMM}"/>
	                    <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
	                    <input type="hidden" id="DIVISION_CD" name="DIVISION_CD" value="${DIVISION_CD}"/>
	                    <input type="hidden" id="CUSTOMER_CD" name="CUSTOMER_CD" value="${CUSTOMER_CD}"/>
	                    <input type="hidden" id="INVOICE_NO" name="INVOICE_NO" value="${INVOICE_NO}"/>
	                    <input type="hidden" id="INVOICE_DATE" name="INVOICE_DATE" value="${INVOICE_DATE}"/>
	                    <input type="hidden" id="FTA_CD" name="FTA_CD" value="${FTA_CD}"/>
	                    <input type="hidden" id="EXPORT_FLAG" name="EXPORT_FLAG" value="D"/>
	                    <input type="hidden" id="VN_EXPORT_FLAG" name="VN_EXPORT_FLAG" value="E"/>
	                    <input type="hidden" id="SEARCH_TYPE" name="SEARCH_TYPE" value="ITEM"/>
	                    <input type="hidden" id="CO_ISSUE_TYPE" name="CO_ISSUE_TYPE" value="${CO_ISSUE_TYPE}"/>
	                    <input type="hidden" id="SALES_MGMT_NO" name="SALES_MGMT_NO" value="${SALES_MGMT_NO}"/>
	                    <input type="hidden" id="REQUESTER" name="REQUESTER"/>
	                    <input type="hidden" id="REQUEST_DATE" name="REQUEST_DATE"/>
	                    <input type="hidden" id="REASON_CONTENTS" name="REASON_CONTENTS"/>
	                    <input type="hidden" id="CURRENCY" name="CURRENCY"/>
	                    <input type="hidden" id="CLIENT_SEND_YN" name="CLIENT_SEND_YN" value="${CLIENT_SEND_YN}"/>
	                    <input type="hidden" id="gridData" name="gridData"/> <!-- 포괄확인서 대상 리스트 -->
	                    <input type="hidden" id="gridRows" name="gridRows"/> <!-- 보고서 발급 대상 리스트 -->
	                    <input type="hidden" id="APPLY_FTA_CD" name="APPLY_FTA_CD" value="${APPLY_FTA_CD}"/>
	                    <input type="hidden" id="APPLY_DATE" name="APPLY_DATE" value="${INVOICE_DATE}"/>
                        <input type="hidden" id="END_DATE" name="END_DATE" value="${INVOICE_DATE}"/>
                        <input type="hidden" id="FTA_GROUP_CD" name="FTA_GROUP_CD" value="${FTA_GROUP_CD}"/>
	                    <div style="float:top;">
	                        <p class="h2"><spring:message code="TXT_BASICINFO"/><%--기본정보--%></p>
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
	                                        <span id="MMA304_02_form_01_chkAuto" style="float:right;">
	                                            <input type="checkbox" name="chkAuto" id="chkAuto" onclick="javascript:MMA304_02.ui.changeAutoCheck();" value="Y" checked/> Auto
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
	                                    <th scope="row" class="point2" rowspan="2">
                                            <spring:message code="TXT_SIGN_FILE"/>
                                        </th>
                                        <td valign="bottom" rowspan="2">
                                            <img src="" id="MMA304_02_form_01_SIGN_CARD" name="MMA304_02_form_01_SIGN_CARD" style="width:100px;height:45px;"/>
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
	                                        <spring:message code="AGRET, GROUP"/><%--협정그룹--%>
	                                    </th>
	                                    <td>
	                                        <input type="text" id="FTA_GROUP_CD_NAME" name="FTA_GROUP_CD_NAME" value="${FTA_GROUP_CD_NAME}" readonly class="input_readOnly" style="width:98%;"/>
	                                    </td>
	                                </tr>
	                                <tr>
	                                    <th scope="row" colspan="2">
	                                        <spring:message code="CERIS,REASN"/><%--발급사유--%>
	                                    </th>
	                                    <td colspan="5">
	                                        <span id="MMA304_02_span_01" style="display:none;"><a href="javascript:MMA304_02.dialog.openDialog_3('view');"><img src="/images/icon/external_link.png" boder="0"/></a></span>
	                                        <input name="REASON_TITLE" id="REASON_TITLE" style="width:97%"/>
	                                    </td>
	                                </tr>
	                            </tbody>
	                        </table>
	                    </div>
	                    <div style="float:top;">
	                        <p class="h2"><spring:message code="CSTMR"/></p>
	                        <table class="dataT">
	                            <caption><spring:message code="CSTMR"/></caption>
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
	                                    <th scope="row"><spring:message code="EXPNM"/></th>
	                                    <td><input type="text" name="EXPORTER_NAME" id="EXPORTER_NAME" value="${EXPORTER_NAME}" class="easyui-validatebox" validType="['maxlength[100]']"  title="<spring:message code="EXPNM"/>" style="width:98%"/> </td>
	                                    <th scope="row"><spring:message code="RSTNM"/></th>
	                                    <td><input type="text" name="EXPORTER_PRESIDENT_NAME" id="EXPORTER_PRESIDENT_NAME" value="${EXPORTER_PRESIDENT_NAME}" class="easyui-validatebox" validType="['maxlength[100]']"  title="<spring:message code="RSTNM"/>" style="width:98%"/> </td>
	                                    <th scope="row"><spring:message code="BZRGN"/></th>
	                                    <td><input type="text" name="EXPORTER_BUSINESS_NO" id="EXPORTER_BUSINESS_NO" value="${EXPORTER_BUSINESS_NO}" class="easyui-validatebox" validType="['maxlength[50]']"  title="<spring:message code="BZRGN"/>" style="width:98%"/> </td>
	                                </tr>
	                                <tr>
	                                    <th scope="row"><spring:message code="TELNM"/></th>
	                                    <td><input type="text" name="EXPORTER_PHONE_NO" id="EXPORTER_PHONE_NO" value="${EXPORTER_PHONE_NO}" class="easyui-validatebox" validType="['maxlength[20]']"  title="<spring:message code="TELNM"/>" style="width:98%"/> </td>
	                                    <th scope="row"><spring:message code="FAX"/></th>
	                                    <td><input type="text" name="EXPORTER_FAX_NO" id="EXPORTER_FAX_NO" value="${EXPORTER_FAX_NO}" class="easyui-validatebox" validType="['maxlength[20]']"  title="<spring:message code="FAX"/>" style="width:98%"/> </td>
	                                    <th scope="row"><spring:message code="TXT_EMAIL"/></th>
	                                    <td><input type="text" name="EXPORTER_EMAIL" id="EXPORTER_EMAIL" value="${EXPORTER_EMAIL}" class="easyui-validatebox" validType="'email'" title="<spring:message code="TXT_EMAIL"/>" style="width:98%"/> </td>
	                                </tr>
	                                <tr>
	                                    <th scope="row"><spring:message code="ADDRS"/></th>
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
	                                    <th scope="row"><spring:message code="EXPNM"/></th>
	                                    <td><input type="text" name="PRODUCER_NAME" id="PRODUCER_NAME" value="${PRODUCER_NAME}" class="easyui-validatebox" title="<spring:message code="EXPNM"/>" style="width:98%"/> </td>
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
	                    <form id="MMA304_02_form_02" name="MMA304_02_form_02" method="POST">
		                    <p class="h2"><spring:message code="TXT_ITEMBY_ORG_INFO"/><%--품목별 원산지 정보--%></p>
		                    <p class="h2_btn">
		                        <span id="MMA304_02_span_02" style="display:none;"><a href="javascript:MMA304_02.dialog.openDialog_4();" class="btnSelectClick"><spring:message code="DTSTA,RECOD"/></a></span>
		                        <a href="javascript:MMA304_02.excel.excelDownload_1();" class="btnExlDown"><spring:message code="BTN_EXCEL"/></a>
		                    </p>
		                    <p class="h2_btn" id="MMA304_02_div_01" style="padding-right:10px;">
	                            <input type="checkbox" id="cmCheckbox" name="cmCheckbox" onchange="javascript:MMA304_02.control.selectMainList();" value="N" size="12" /><spring:message code="TXT_USE_POSI_SRCH"/>
	                        </p>
                        </form>
	                </div>
	            </div>
	            <div data-options="region:'center',border:false">
	                <table id="MMA304_02_grid_01"></table>
	            </div>
	        </div>
	    </div>
	</div>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-A304_02.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>