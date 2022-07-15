<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : MM-A304_01.jsp
  * @Description : 레포트 발급(베트남-기관발급)
  * @Modification Information
  *
  * author YNI-Maker(이메일)
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMA304_01_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>      
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center',border:false">
	        <div class="easyui-layout" data-options="fit:true">
	            <div data-options="region:'north',border:false" style="height:25px;overflow:hidden;">
	                <div class="btn_R">
	                    <a href="javascript:MMA304_01.control.issueReport();" id="MMA304_01_btn_12" class="btnPopIssue"><spring:message code="CERIS"/></a>
	                    <a href="javascript:MMA304_01.dialog.openDialog_1();" id="MMA304_01_btn_11" class="btnPopInvoice"><spring:message code="INVIC, MOD"/></a>
	                    <a href="javascript:MMA304_01.control.updateOriginResultInfo();" id="MMA304_01_btn_13" class="btnPopPrint" style="display:none;"><spring:message code="OUTP"/></a>
	                    <a href="javascript:dialog.handle.close(dialog.getObject('MMA304_01_dailog_01'));" id="MMA304_01_btn_15" class="btnPopClose" style="display:none;"><spring:message code="CLOSE"/><%--닫기--%></a>
	                </div>
	            </div>
	            <div data-options="region:'center',border:false">
	                <div class="easyui-layout" data-options="fit:true">
	                    <div data-options="region:'north',border:false,split:true">
	                        <div class="easyui-layout" data-options="fit:true">
	                            <form id="MMA304_01_form_01" name="MMA304_01_form_01" method="POST">
	                                <input type="hidden" id="flag" name="flag" value="${flag}"/>
	                                <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
	                                <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
	                                <input type="hidden" id="DIVISION_CD" name="DIVISION_CD" value="${DIVISION_CD}"/>
	                                <input type="hidden" id="CUSTOMER_CD" name="CUSTOMER_CD" value="${CUSTOMER_CD}"/>
	                                <input type="hidden" id="INVOICE_DATE" name="INVOICE_DATE" value="${INVOICE_DATE}"/>
	                                <input type="hidden" id="FTA_CD" name="FTA_CD" value="${FTA_CD}"/>
	                                <input type="hidden" id="EXPORT_FLAG" name="EXPORT_FLAG" value="${EXPORT_FLAG}"/>
	                                <input type="hidden" id="CO_ISSUE_TYPE" name="CO_ISSUE_TYPE" value="${CO_ISSUE_TYPE}"/>
	                                <input type="hidden" id="SALES_MGMT_NO" name="SALES_MGMT_NO" value="${SALES_MGMT_NO}"/>
	                                <input type="hidden" id="gridData" name="gridData"/> <!-- 포괄확인서 대상 리스트 -->
	                                <input type="hidden" id="gridRows" name="gridRows"/> <!-- 보고서 발급 대상 리스트 -->
	                                <input type="hidden" id="gridPrds" name="gridPrds"/> <!-- 생산자 리스트 -->
	                                <input type="hidden" id="FTA_GROUP_CD" name="FTA_GROUP_CD" value="${FTA_GROUP_CD}"/>
	                                <input type="hidden" id="REQUESTER" name="REQUESTER"/>
	                                <input type="hidden" id="REQUEST_DATE" name="REQUEST_DATE"/>
	                                <input type="hidden" id="REASON_CONTENTS" name="REASON_CONTENTS"/>
	                                <input type="hidden" id="CURRENCY" name="CURRENCY"/>
	                                <input type="hidden" id="SEARCH_TYPE" name="SEARCH_TYPE" value="${SEARCH_TYPE}"/>
	                                <input type="hidden" id="ORGAN_ISSUE_YN" name="ORGAN_ISSUE_YN" value="${ORGAN_ISSUE_YN}"/>
	                                <input type="hidden" id="CO_DOC_SEQ" name="CO_DOC_SEQ"/>
	                                <input type="hidden" id="APPLY_DATE" name="APPLY_DATE" value="${INVOICE_DATE}"/>
	                                <input type="hidden" id="END_DATE" name="END_DATE" value="${INVOICE_DATE}"/>
	                                <input type="hidden" id="CUSTOMER_NAME" name="CUSTOMER_NAME" value="${CUSTOMER_NAME}"/>
	                                <input type="hidden" id="EXPORT_DECLARE_NO" name="EXPORT_DECLARE_NO" value="${EXPORT_DECLARE_NO}"/>
	                                <input type="hidden" id="ORIGIN_RESULT_1" name="ORIGIN_RESULT_1"/>
	                                <input type="hidden" id="ORIGIN_RESULT_2" name="ORIGIN_RESULT_2"/>
	                                <input type="hidden" id="ORIGIN_RESULT_3" name="ORIGIN_RESULT_3"/>
	                                <input type="hidden" id="ORIGIN_RESULT_4" name="ORIGIN_RESULT_4"/>
	                                <input type="hidden" id="ORIGIN_RESULT_5" name="ORIGIN_RESULT_5"/>
	                                <input type="hidden" id="ORIGIN_RESULT_6" name="ORIGIN_RESULT_6"/>
	                                <input type="hidden" id="ORIGIN_RESULT_7" name="ORIGIN_RESULT_7"/>
	                                <input type="hidden" id="ORIGIN_RESULT_8" name="ORIGIN_RESULT_8"/>
	                                <input type="hidden" id="ORIGIN_RESULT_9" name="ORIGIN_RESULT_9"/>
	                                <div data-options="region:'north',border:false,split:true" style="height:193px;">
	                                    <div class="easyui-layout" data-options="fit:true">
			                                <div data-options="region:'north',border:false" class="h2_etc">
		                                        <p class="h2"><spring:message code="CNFDO, TXT_BASICINFO"/><%--확인서 기본정보--%></p>
			                                </div>
			                                <div data-options="region:'center',border:false">
			                                    <table class="dataT" style="margin-bottom:0px;" >
			                                        <caption><spring:message code="DTA, TB"/><%--데이타 테이블--%></caption>
			                                        <colgroup>
			                                            <col width="100px" />
			                                            <col width="140px" />
			                                            <col width="190px" />
			                                            <col width="150px" />
			                                            <col width="150px" />
			                                            <col width="140px" />
			                                            <col width=";" />
			                                        </colgroup>
			                                        <tbody>
			                                            <tr>
			                                                <th scope="row" colspan="2" class="point2">
			                                                    <div style="float:left;"><spring:message code="TXT_SEARCH_COO_CERTIFY_NO"/></div>
			                                                    <span id="MMA304_01_form_01_chkAuto" style="float:right;">
			                                                        <input type="checkbox" name="chkAuto" id="chkAuto" onclick="javascript:MMA304_01.ui.changeAutoCheck();" value="Y" checked/> Auto
			                                                    </span>
			                                                </th>
			                                                <td>
			                                                    <input type="text" name="CO_DOC_NO" id="CO_DOC_NO" value="${CO_DOC_NO}" class="easyui-validatebox" validType="['maxlength[100]']" style="width:97%;" title="<spring:message code="CNFDO, TXT_SEARCH_COO_CERTIFY_NO"/>"/>
			                                                </td>
			                                                <th scope="row" class="point2">
			                                                    <spring:message code="FTA, AGRET"/><%--거래처--%>
			                                                </th>
			                                                <td>
			                                                    <input type="text" name="FTA_NAME" id="FTA_NAME" value="${FTA_NAME}" readonly class="input_readOnly" style="width:97%;" title="<spring:message code="FTA, AGRET"/>" />
			                                                </td>
			                                                <th scope="row" class="point2">
			                                                    <spring:message code="TXT_DOC_NAME"/><%--문서명--%>
			                                                </th>
			                                                <td>
			                                                    <input type="text" name="DOC_NAME_ENG" id="DOC_NAME_ENG" value="${DOC_NAME_ENG}" style="width:97%;" readonly class="input_readOnly"/>
			                                                </td>
			                                            </tr>
			                                            <tr>
			                                                <th scope="row" rowspan="2">
                                                                <spring:message code="INVIC_INFMT"/> <%-- 인보이스 정보 --%>
                                                            </th>
			                                                <th scope="row">
			                                                    <spring:message code="TXT_INVOICE_NO"/><%--Invoice 번호--%>
			                                                </th>
			                                                <td>
			                                                    <input type="text" name="INVOICE_NO" value="${INVOICE_NO}" id="INVOICE_NO" readonly class="easyui-validatebox input_readOnly"  style="width:97%;"/>
			                                                </td>
			                                                <th scope="row">
			                                                    <spring:message code="TXT_COO_REGION_NATION"/><%--원산지 국가--%>
			                                                </th>
			                                                <td>
			                                                    <input type="text" name="ORIGIN_NATION_NM" value="${ORIGIN_NATION_NM}" id="ORIGIN_NATION_NM" readonly class="easyui-validatebox input_readOnly" required="true" style="width:97%;"/>
			                                                </td>
			                                                <th scope="row" class="point2">
			                                                    <spring:message code="ARIVL,COTRY"/><%--목적지 국가--%>
			                                                </th>
			                                                <td>
			                                                    <input type="text" name="FINAL_DESTINATION_NAME" value="${FINAL_DESTINATION_NAME}" id="FINAL_DESTINATION_NAME" readonly class="easyui-validatebox input_readOnly" required="true" style="width:97%;"/>
			                                                </td>
			                                            </tr>
			                                            <tr>
		                                                    <th scope="row">
		                                                        <spring:message code="TXT_VEHICLE_NAME"/><%--선편명--%>
		                                                    </th>
		                                                    <td>
		                                                        <input type="text" name="VEHICLE_NAME" value="${VEHICLE_NAME}" id="VEHICLE_NAME" readonly class="easyui-validatebox input_readOnly" required="true" style="width:97%;"/>
		                                                    </td>
		                                                    <th scope="row">
		                                                        <spring:message code="TXT_SHIP_PORT_NAME"/><%--선적항명--%>
		                                                    </th>
		                                                    <td>
		                                                        <input type="text" name="LOADING_PORT_NAME" value="${LOADING_PORT_NAME}" id="LOADING_PORT_NAME" readonly class="easyui-validatebox input_readOnly" required="true" style="width:97%;"/>
		                                                    </td>
		                                                    <th scope="row" class="point2">
		                                                        <spring:message code="TXT_SHIPPING_DATE"/><%--선적일자--%>
		                                                    </th>
		                                                    <td>
		                                                        <input name="SHIPPING_DATE" value="${SHIPPING_DATE}" id="SHIPPING_DATE" style="width:95px;"/>
		                                                    </td>
		                                                </tr>
			                                            <tr>
			                                                <th scope="row" rowspan="2">
			                                                    <spring:message code="DCRAN_INFMT"/> <%-- 신고인 정보 --%>
			                                                </th>
			                                                <th scope="row" class="point2">
			                                                    <spring:message code="EXPNM"/><%-- 상호 --%>
			                                                </th>
			                                                <td>
			                                                    <input type="text" id="COMPANY_NAME" name="COMPANY_NAME" value="${COMPANY_NAME}" class="easyui-validatebox" required="true" style="width:97%"/>
			                                                </td>
			                                                <th scope="row" class="point2">
			                                                    <spring:message code="COTRY_NAME"/> <%-- 제조국가 --%>
			                                                </th>
			                                                <td>
			                                                    <input type="text" id="MAKER_NATION_NM" name="MAKER_NATION_NM" value="${MAKER_NATION_NM}" class="easyui-validatebox" required="true" style="width:97%;"/>
			                                                </td>
			                                                <th scope="row" class="point2" rowspan="2">
			                                                    <spring:message code="TXT_SIGN_FILE"/>
			                                                </th>
			                                                <td valign="bottom" rowspan="2">
			                                                    <img src="" id="MMA304_01_form_01_SIGN_CARD" name="MMA304_01_form_01_SIGN_CARD" style="width:100px;height:45px;"/>
			                                                </td>
			                                            </tr>
			                                            <tr>
			                                                <th scope="row" class="point2">
			                                                    <spring:message code="TXT_DECLARE_DATE"/><%--신고일자--%>
			                                                </th>
			                                                <td>
			                                                    <input id="DECLARE_DATE" name="DECLARE_DATE" value="${DECLARE_DATE}" style="width:95px"/>
			                                                </td>
			                                                <th scope="row" class="point2">
			                                                    <spring:message code="CITY"/> <%--신고장소--%>
			                                                </th>
			                                                <td>
			                                                    <input type="text" id="CITY" name="CITY" value="${CITY}" class="easyui-validatebox" required="true" style="width:97%;"/>
			                                                </td>
			                                            </tr>
			                                            <tr>
		                                                    <th scope="row">
                                                                <spring:message code="CERIS,REASN"/><%--발급사유--%>
                                                                <span id="MMA304_01_span_01" style="display:none;"><a href="javascript:MMA304_01.dialog.openDialog_3('view');"><img src="/images/icon/external_link.png" boder="0"/></a></span>
                                                            </th>
                                                            <td colspan="2">
                                                                <input name="REASON_TITLE" id="REASON_TITLE" style="width:97%"/>
                                                            </td>
		                                                    <th scope="row" class="point2">
		                                                        <spring:message code="CERIS_DATE"/><%--발행일자--%>
		                                                    </th>
		                                                    <td>
		                                                        <input name="ISSUE_DATE" id="ISSUE_DATE" value="${ISSUE_DATE}" style="width:95px;"/>
		                                                    </td>
			                                                <th scope="row">
			                                                    <spring:message code="CNFIT_EXPER_MSSNM"/>
			                                                </th>
			                                                <td>
			                                                    <input type="text" id="CERTIFICATION_NO" name="CERTIFICATION_NO" value="${CERTIFICATION_NO}" readonly class="input_readOnly"  style="width:98%;"/>
			                                                </td>
			                                            </tr>
			                                        <tbody>
			                                    </table>
			                                </div>
			                            </div>
	                                </div>
	                                <div data-options="region:'center',border:false">
                                        <div class="easyui-layout" data-options="fit:true" style="height:112px;">
			                                <div data-options="region:'west',border:false" class="width50">
			                                    <div class="easyui-layout" data-options="fit:true">
		                                            <div data-options="region:'north',border:false" class="h2_etc">
			                                           <p class="h2"><spring:message code="EXPER"/></p>
		                                            </div>
		                                            <div data-options="region:'center',border:false">
					                                    <table class="dataT" style="margin-bottom:0px;">
					                                        <caption><spring:message code="TXT_EXPER_INFO"/></caption>
					                                        <colgroup>
					                                            <col width="120px" />
					                                            <col width="" />
					                                            <col width="100px" />
					                                            <col width="" />
					                                        </colgroup>
					                                        <tbody>
					                                            <tr>
					                                                <th scope="row" class="point2"><spring:message code="EXPNM"/></th>
					                                                <td colspan="3"><input type="text" name="PRODUCER_NAME" id="PRODUCER_NAME" value="${PRODUCER_NAME}" class="easyui-validatebox" required="true" style="width:99%"/> </td>
					                                            </tr>
					                                            <tr>
					                                                <th scope="row"><spring:message code="RSTNM"/></th>
					                                                <td><input type="text" name="PRODUCER_PRESIDENT_NAME" id="PRODUCER_PRESIDENT_NAME" value="${PRODUCER_PRESIDENT_NAME}" class="easyui-validatebox" required="true" validType="['maxlength[100]']" style="width:97%"/> </td>
					                                                <th scope="row" class="point2"><spring:message code="COTRY_NAME"/></th>
					                                                <td><input type="text" name="PRODUCER_NATION_NM" id="PRODUCER_NATION_NM" value="${PRODUCER_NATION_NM}" style="width:97%"/> </td>
					                                            </tr>
					                                            <tr>
					                                                <th scope="row" class="point2"><spring:message code="ADDRS"/></th>
					                                                <td colspan="3">
					                                                   <input type="text" name="PRODUCER_ADDRESS" id="PRODUCER_ADDRESS" value="${PRODUCER_ADDRESS}" class="easyui-validatebox" validType="['maxlength[300]']" required="true" style="width:99%"/>
					                                                </td>
					                                            </tr>
					                                        </tbody>
					                                    </table>
					                                </div>
					                            </div>
			                                </div>
			                                <div data-options="region:'center',border:false" style="padding-left:5px;" class="width50">
			                                    <div class="easyui-layout" data-options="fit:true">
                                                    <div data-options="region:'north',border:false" class="h2_etc">
                                                       <p class="h2"><spring:message code="IMPER"/></p>
                                                    </div>
                                                    <div data-options="region:'center',border:false">
					                                    <table class="dataT" style="margin-bottom:0px;">
					                                        <caption>수입자 정보</caption>
					                                        <colgroup>
					                                            <col width="120px" />
					                                            <col width="" />
					                                            <col width="100px" />
					                                            <col width="" />
					                                        </colgroup>
					                                        <tbody>
					                                            <tr>
					                                                <th scope="row" class="point2"><spring:message code="EXPNM"/></th>
					                                                <td colspan="3"><input type="text" name="EXPORTER_NAME" id="EXPORTER_NAME" value="${EXPORTER_NAME}" class="easyui-validatebox" required="true" validType="['maxlength[100]']" style="width:99%"/> </td>
					                                            </tr>
					                                            <tr>
					                                                <th scope="row"><spring:message code="RSTNM"/></th>
					                                                <td><input type="text" name="EXPORTER_PRESIDENT_NAME" id="EXPORTER_PRESIDENT_NAME" value="${EXPORTER_PRESIDENT_NAME}" class="easyui-validatebox" required="true" validType="['maxlength[100]']" style="width:97%"/> </td>
					                                                <th scope="row" class="point2"><spring:message code="COTRY_NAME"/></th>
				                                                    <td><input type="text" name="IMPORT_NATION_NM" id="IMPORT_NATION_NM" value="${IMPORT_NATION_NM}" style="width:97%"/> </td>
					                                            </tr>
					                                            <tr>
					                                                <th scope="row" class="point2"><spring:message code="ADDRS"/></th>
					                                                <td colspan="3"><input type="text" name="EXPORTER_ADDRESS" id="EXPORTER_ADDRESS" value="${EXPORTER_ADDRESS}" class="easyui-validatebox" validType="['maxlength[300]']" required="true" style="width:99%"/> </td>
					                                            </tr>
					                                        </tbody>
					                                    </table>
					                                </div>
					                            </div>
			                                </div>
			                            </div>
			                        </div>
	                            </form>
	                        </div>
	                    </div>
	                    <div data-options="region:'center',border:false">
	                        <div class="easyui-layout" data-options="fit:true">
	                            <div data-options="region:'north',border:false">
	                                <div class="h2_etc">
	                                    <form id="MMA304_01_form_02" name="MMA304_01_form_02" method="POST">
	                                        <p class="h2"><spring:message code="EXRPT_LTIT"/><%--수출신고품목--%></p>
	                                        <p class="h2_btn">
	                                            <a href="javascript:MMA304_01.dialog.openDialog_5();" class="btnList"><spring:message code="EXRPT, LTIT"/></a>
	                                            <span id="MMA304_01_span_02"><a href="javascript:MMA304_01.dialog.openDialog_4();" class="btnSelectClick"><spring:message code="DTSTA,RECOD"/></a></span>
	                                            <a href="javascript:MMA304_01.excel.excelDownload_1();" class="btnExlDown"><spring:message code="BTN_EXCEL"/></a>
	                                        </p>
	                                        <p class="h2_btn" id="MMA304_01_div_01" style="padding-right:10px;">
	                                            <input type="checkbox" id="cmCheckbox" name="cmCheckbox" onchange="javascript:MMA304_01.control.selectMainList();" value="N" size="12" /><spring:message code="TXT_USE_POSI_SRCH"/>
	                                        </p>
	                                    </form>
	                                </div>
	                            </div>
	                            <div data-options="region:'center',border:false">
	                                <div class="easyui-layout" data-options="fit:true">
                                      <c:if test="${FTA_CD ne 'PVNEE'}">
                                        <div id="MMA304_01_layout_01" data-options="region:'north',border:false" style="height:auto;background-color:#fff">
                                            <form id="MMA304_01_form_03" name="MMA304_01_form_03" method="POST">
                                                <table class="gridT" style="margin-bottom:0px;">
                                                    <caption><spring:message code="DTA, TB"/><%--데이타 테이블--%></caption>
                                                    <colgroup>
                                                        <col width="150px" />
                                                        <col width="" />
                                                    </colgroup>
                                                    <tbody>
                                                        <tr>
                                                            <th scope="row">
                                                                <spring:message code="ORG_DECSN_CNDIT"/>
                                                            </th>
                                                            <td>
                                                                <label id="MMA304_01_label_01" style="display:none;"><input type="checkbox" id="ORIGIN_RESULT_1" name="ORIGIN_RESULT_1" value="Y"/>The Thrid Country invoicing</label>
                                                                <label id="MMA304_01_label_02" style="display:none;"><input type="checkbox" id="ORIGIN_RESULT_2" name="ORIGIN_RESULT_2" value="Y"/>Back to back C/O</label>
                                                                <label id="MMA304_01_label_03" style="display:none;"><input type="checkbox" id="ORIGIN_RESULT_3" name="ORIGIN_RESULT_3" value="Y"/>Exhibitions</label>
                                                                <label id="MMA304_01_label_04" style="display:none;"><input type="checkbox" id="ORIGIN_RESULT_4" name="ORIGIN_RESULT_4" value="Y"/>Issued Retroactively</label>
                                                                <label id="MMA304_01_label_05" style="display:none;"><input type="checkbox" id="ORIGIN_RESULT_5" name="ORIGIN_RESULT_5" value="Y"/>Accumulation</label>
                                                                <label id="MMA304_01_label_06" style="display:none;"><input type="checkbox" id="ORIGIN_RESULT_6" name="ORIGIN_RESULT_6" value="Y"/>Partial Cumulation</label>
                                                                <label id="MMA304_01_label_07" style="display:none;"><input type="checkbox" id="ORIGIN_RESULT_7" name="ORIGIN_RESULT_7" value="Y"/>De minimis</label>
                                                                <label id="MMA304_01_label_08" style="display:none;"><input type="checkbox" id="ORIGIN_RESULT_8" name="ORIGIN_RESULT_8" value="Y"/>Movement Certification</label>
                                                                <label id="MMA304_01_label_09" style="display:none;"><input type="checkbox" id="ORIGIN_RESULT_9" name="ORIGIN_RESULT_9" value="Y"/>Cumulation</label>
                                                            </td>
                                                        </tr>
                                                     </tbody>
                                                 </table>
                                             </form>
                                        </div>
                                      </c:if>
                                        <div id="MMA304_01_layout_02" data-options="region:'center',border:false">
			                                <table id="MMA304_01_grid_01"></table>
                                        </div>
                                    </div>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
    </div>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-A304_01.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>