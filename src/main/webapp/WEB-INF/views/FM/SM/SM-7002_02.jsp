<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : SM-7002_02.jsp
  * @Description : 사업부 정보 관리
  * @Modification Information
  *
  * author carlos
  *
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "SM7002_02_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body class="h2_etc">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'north',border:false" style="overflow:hidden;height:27px;">
            <div id="SM7002_02_div_01" class="btn_R">
                <a href="javascript:SM7002_02.control.updateDivisionInfo();" id="SM7002_02_btn_01" class="btnSave"><spring:message code="SAVE"/></a>
                <a href="javascript:SM7002_02.control.deleteDivisionInfo();" id="SM7002_02_btn_02" class="btnDelete"><spring:message code="DEL"/></a>
            </div>
        </div>
        <div data-options="region:'center',border:false">
            <form id="SM7002_02_form_01" name="SM7002_02_form_01" method="post">
                <input type="hidden" id="flag" name="flag" value="${flag}"/>
                <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
                <input type="hidden" id="SESSION_COMPANY_CD" name="SESSION_COMPANY_CD" value="${SESSION_COMPANY_CD}"/>
                <input type="hidden" id="SESSION_USER_ID" name="SESSION_USER_ID" value="${SESSION_USER_ID}"/>
                <input type="hidden" id="DEFAULT_LANGUAGE" name="DEFAULT_LANGUAGE" value="${DEFAULT_LANGUAGE}"/>
                <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
                <input type="hidden" id="CREATE_BY" name="CREATE_BY" value="${SESSION_USER_ID}"/>
                <input type="hidden" id="CREATE_BY" name="UPDATE_BY" value="${SESSION_USER_ID}"/>
                <input type="hidden" id="ORGAN_ISSUE_YN" name="ORGAN_ISSUE_YN" value="${ORGAN_ISSUE_YN}"/>
                <p class="h2">
                    <spring:message code="TXT_BIZ_DIV,INFMT"/><%--사업부정보--%>
                </p>
                <table class="dataT" style="margin-bottom:0px;">
                    <caption><spring:message code="DTA, TB"/><%--데이타 테이블--%></caption>
                    <colgroup>
                        <col width="170px" />
                        <col width=";" />
                        <col width="170px" />
                        <col width=";" />
                        <col width="170px" />
                        <col width=";" />
                    </colgroup>
                    <tbody>
                        <tr>
                            <th scope="row" class="point2">
                                <spring:message code="DIVIS,CODE"/><%--사업부코드--%>
                            </th>
                            <td>
                                <input type="text" name="DIVISION_CD" id="DIVISION_CD" value="${DIVISION_CD}" 
                                       class="easyui-validatebox" required="true" validType="['maxlength[20]']" style="width:98%" 
                                       title="<spring:message code="DIVIS,CODE"/>" />
                            </td>
                            <th scope="row" class="point2">
                                <spring:message code="DIVIS,NAME"/><%--사업부명--%>
                            </th>
                            <td>
                                <input type="text" name="DIVISION_NAME" id="DIVISION_NAME" value="${DIVISION_NAME}" 
                                       class="easyui-validatebox" required="true" validType="['maxlength[200]']" style="width:98%"
                                       title="<spring:message code="DIVIS,NAME"/>" />
                            </td>
                            <th scope="row" class="point2">
                                <spring:message code="DIVIS,NAME,(,ENGLS,)"/><%--사업부명(영문)--%>
                            </th>
                            <td>
                                <input type="text" name="DIVISION_NAME_ENG" id="DIVISION_NAME_ENG" value="${DIVISION_NAME_ENG}" 
                                       class="easyui-validatebox" required="true" validType="['maxlength[200]']" style="width:98%"
                                       title="<spring:message code="DIVIS,NAME,(,ENGLS,)"/>" />
                            </td>
                        </tr>
                        <tr>
                            <th scope="row" class="point2">
                                <spring:message code="TXT_BUSINESSNO"/><%--사업자등록번호--%>
                            </th>
                            <td>
                                <input type="text" name="BUSINESS_NO" id="BUSINESS_NO" value="${BUSINESS_NO}" 
                                       class="easyui-validatebox" required="true" validType="['maxlength[50]']" style="width:98%"
                                       title="<spring:message code="TXT_BUSINESSNO"/>" />
                            </td>
                            <th scope="row">
                                <spring:message code="DIVIS,TYP"/><%--사업부형태--%>
                            </th>
                            <td>
                                <input type="text" name="DIVISION_TYPE" id="DIVISION_TYPE" value="${DIVISION_TYPE}" style="width:98%"
                                       title="<spring:message code="DIVIS,TYP"/>"/>
                            </td>
                            <th scope="row" class="point2">
                                <spring:message code="STAUS"/><%--상태--%>
                            </th>
                            <td>
                                <input type="text" name="STATUS" id="STATUS" value="${STATUS}" style="width:98%"
                                       title="<spring:message code="STAUS"/>"/>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">
                                <spring:message code="THPIC,NAME"/><%--담당자명--%>
                            </th>
                            <td>
                                <input type="text" name="OFFICER" id="OFFICER" value="${OFFICER}" 
                                       class="easyui-validatebox" validType="['maxlength[100]']" style="width:98%"
                                       title="<spring:message code="THPIC,NAME"/>" />
                            </td>
                            <th scope="row">
                                <spring:message code="THPIC, TELNM"/><%--담당자전화번호--%>
                            </th>
                            <td>
                                <input type="text" name="OFFICER_PHONE_NO" id="OFFICER_PHONE_NO" value="${OFFICER_PHONE_NO}" 
                                       class="easyui-validatebox" validType="['telnum','maxlength[20]']" style="width:98%"
                                       title="<spring:message code="THPIC, TELNM"/>" />
                            </td>
                            <th scope="row">
                                <spring:message code="TXT_VENDOR_EMAIL"/><%--담당자 E-MAIL--%>
                            </th>
                            <td>
                                <input type="text" name="OFFICER_EMAIL" id="OFFICER_EMAIL" value="${OFFICER_EMAIL}" 
                                       class="easyui-validatebox" validType="['email','maxlength[30]']" style="width:98%"
                                       title="<spring:message code="TXT_VENDOR_EMAIL"/>" />
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">
                                <spring:message code="TELNM"/><%--전화번호--%>
                            </th>
                            <td>
                                <input type="text" name="PHONE_NO" id="PHONE_NO" value="${PHONE_NO}" 
                                       class="easyui-validatebox" validType="['telnum','maxlength[20]']" style="width:98%"
                                       title="<spring:message code="TELNM"/>" />
                            </td>
                            <th scope="row">
                                <spring:message code="FAX"/><%--팩스번호--%>
                            </th>
                            <td colspan="3">
                                <input type="text" name="FAX_NO" id="FAX_NO" value="${FAX_NO}" 
                                       class="easyui-validatebox" validType="['telnum','maxlength[20]']" style="width:98%"
                                       title="<spring:message code="FAX"/>" />
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">
                                <spring:message code="TXT_PRESIDENT_NAME"/><%--대표자명--%>
                            </th>
                            <td>
                                <input type="text" name="PRESIDENT_NAME" id="PRESIDENT_NAME" value="${PRESIDENT_NAME}" 
                                       class="easyui-validatebox" validType="['maxlength[100]']" style="width:98%"
                                       title="<spring:message code="TXT_PRESIDENT_NAME"/>" />
                            </td>
                            <th scope="row">
                                <spring:message code="TXT_PRESIDENT_NAME,(,ENGLS,)"/><%--대표자명(영문)--%>
                            </th>
                            <td>
                                <input type="text" name="PRESIDENT_NAME_ENG" id="PRESIDENT_NAME_ENG" value="${PRESIDENT_NAME_ENG}" 
                                       class="easyui-validatebox" validType="['maxlength[100]']" style="width:98%"
                                       title="<spring:message code="TXT_PRESIDENT_NAME,(,ENGLS,)"/>" />
                            </td>
                            <th scope="row">
                                <spring:message code="ORG,CNFIT, EXPER, CNFIT,MSSNM"/><%--인증수출자 인증번호--%>
                            </th>
                            <td>
                                <input type="text" name="CERTIFICATION_NO" id="CERTIFICATION_NO" value="${CERTIFICATION_NO}" 
                                       class="easyui-validatebox" validType="['maxlength[20]']" style="width:98%"
                                       title="<spring:message code="ORG,CNFIT, EXPER, CNFIT,MSSNM"/>" />
                            </td>
                        </tr>
                        <tr>
                            <th scope="row" class="point2">
                                <spring:message code="ADDRS"/><%--주소1--%>
                            </th>
                            <td colspan="3">
                                <input type="text" name="ADDRESS1" id="ADDRESS1" value="${ADDRESS1}" 
                                       class="easyui-validatebox" required="true" validType="['maxlength[300]']" style="width:99%"
                                       title="<spring:message code="ADDRS,1"/>" />
                            </td>
                            <th scope="row">
                                <spring:message code="CITY"/><%--주소1--%>
                            </th>
                            <td>
                                <input type="text" name="ADDRESS2" id="ADDRESS2" value="${ADDRESS2}" 
                                       class="easyui-validatebox" validType="['maxlength[300]']" style="width:99%"
                                       title="<spring:message code="CITY"/>" />
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">
                                <spring:message code="ADDRS"/>(<spring:message code="ENGLS"/>)<%--주소1(영문)--%>
                            </th>
                            <td colspan="3">
                                <input type="text" name="ADDRESS1_ENG" id="ADDRESS1_ENG" value="${ADDRESS1_ENG}" 
                                       class="easyui-validatebox" validType="['maxlength[300]']" style="width:99%"
                                       title="<spring:message code="ADDRS,1(,ENGLS,)"/>" />
                            </td>
                            <th scope="row">
                                <spring:message code="CITY"/>(<spring:message code="ENGLS"/>)<%--주소1(영문)--%>
                            </th>
                            <td>
                                <input type="text" name="ADDRESS2_ENG" id="ADDRESS2_ENG" value="${ADDRESS2_ENG}"
                                       class="easyui-validatebox" validType="['maxlength[300]']" style="width:99%"
                                       title="<spring:message code="CITY"/>" />
                            </td>
                        </tr>
                    </tbody>
                </table>
                <div id="SM7002_02_div_02" style="display:none;padding-top:12px;">
		            <div class="h2_etc">
		                <p class="h2"><spring:message code="ISORG, INFMT"/></p>
		            </div>
		            <table class="dataT" style="margin-bottom:0px;">
		                <caption><spring:message code="DTA, TB"/><%--데이타 테이블--%></caption>
		                <colgroup>
		                    <col width="170px" />
		                    <col width=";" />
		                    <col width="170px" />
		                    <col width=";" />
		                    <col width="170px" />
                            <col width=";" />
		                </colgroup>
		                <tbody>
		                    <tr>
		                        <th scope="row">
		                            <spring:message code="TXT_CUSTOMS_CODE"/>
		                        </th>
		                        <td>
		                            <input type="text" name="CUSTOMS_CD" id="CUSTOMS_CD" value="${CUSTOMS_CD}" style="width:150px;"/>
		                        </td>
		                        <th scope="row">
		                            <spring:message code="DCRAN, ASSGN,CODE"/>
		                        </th>
		                        <td>
		                            <input type="text" name="ORGAN_VENDOR_CD" id="ORGAN_VENDOR_CD" value="${ORGAN_VENDOR_CD}" style="width:98%"/>
		                        </td>
		                        <th scope="row">
		                            <spring:message code="INBOX,MSSNM"/>
		                        </th>
		                        <td>
		                            <input type="text" name="ORGAN_DOC_NO" id="ORGAN_DOC_NO" value="${ORGAN_DOC_NO}" style="width:98%"/>
		                        </td>
		                    </tr>
		                </tbody>
		            </table>
		        </div>
            </form>
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/FM/SM/SM-7002_02.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>