<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : SM7099_01.jsp
  * @Description : 팝업테스트
  * @Modification Information
  *
  * author carlos
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "SM7099_01_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body class="h2_etc">
    <div class="easyui-layout" data-options="fit:true">
        <form id="SM7099_01_form_01" name="SM7099_01_form_01" method="POST">
            <input type="hidden" id="flag"               name="flag" value="${flag}"/>
            <input type="hidden" id="TARGET_PID"         name="TARGET_PID" value="${TARGET_PID}"/>
            <input type="hidden" id="SESSION_COMPANY_CD" name="SESSION_COMPANY_CD" value="${_MEMBER.SESSION_COMPANY_CD}"/>
            <input type="hidden" id="SESSION_USER_ID"    name="SESSION_USER_ID" value="${_MEMBER.SESSION_USER_ID}"/>
            <input type="hidden" id="DEFAULT_LANGUAGE"   name="DEFAULT_LANGUAGE" value="${_MEMBER.DEFAULT_LANGUAGE}"/>
            <input type="hidden" id="COMPANY_CD"         name="COMPANY_CD" value="${_MEMBER.SESSION_COMPANY_CD}"/>
            <input type="hidden" id="CREATE_BY"          name="CREATE_BY" value="${_MEMBER.SESSION_USER_ID}"/>
            <input type="hidden" id="CREATE_BY"          name="UPDATE_BY" value="${_MEMBER.SESSION_USER_ID}"/>
            
            <p class="h2"><spring:message code="팝업 테스트"/></p>
            <table class="dataT">
                <caption><spring:message code="DTA, TB"/><%--데이타 테이블--%></caption>
                <colgroup>
                    <col width="150px" />
                    <col width=";" />
                    <col width="150px" />
                    <col width=";" />
                </colgroup>
                <tbody>
                    <tr>
                        <th scope="row">
                            <spring:message code="NALADISA, CODE"/>
                        </th>
                        <td>
                            <input type="text" id="NALADISA_CODE" name="NALADISA_CODE" size="10" search="SM7099_01.dialog.openDialog_1"/>
                            <a href="javascript:SM7099_01.dialog.openDialog_1();"><img src="/images/sample/btn_sch.gif" alt="<spring:message code="SERCH"/>"/></a>
                            <input type="text" id="NALADISA_CODE_NAME" name="NALADISA_CODE_NAME" size="30" />
                        </td>
                        <th scope="row">
                            <spring:message code="공통 코드"/>
                        </th>
                        <td>
                            <input type="text" id="BASIC_CODE" name="BASIC_CODE" size="10" search="SM7099_01.dialog.openDialog_2"/>
                            <a href="javascript:SM7099_01.dialog.openDialog_2();"><img src="/images/sample/btn_sch.gif" alt="<spring:message code="SERCH"/>"/></a>
                            <input type="text" id="BASIC_CODE_NAME" name="BASIC_CODE_NAME" size="30" />
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">
                            <spring:message code="증명서 변경요청"/>
                        </th>
                        <td>
                            <input type="text" id="CERTY_CODE" name="CERTY_CODE" size="10" search="SM7099_01.dialog.openDialog_3"/>
                            <a href="javascript:SM7099_01.dialog.openDialog_3();"><img src="/images/sample/btn_sch.gif" alt="<spring:message code="SERCH"/>"/></a>
                            <input type="text" id="CERTY_CODE_NAME" name="CERTY_CODE_NAME" size="30" />
                        </td>
                        <th scope="row">
                            <spring:message code="매출원장관리 상세"/>
                        </th>
                        <td>
                            <input type="text" id="CODE4" name="CODE4" size="10" search="SM7099_01.dialog.openDialog_4"/>
                            <a href="javascript:SM7099_01.dialog.openDialog_4();"><img src="/images/sample/btn_sch.gif" alt="<spring:message code="SERCH"/>"/></a>
                            <input type="text" id="NAME4" name="NAME4" size="30" />
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">
                            <spring:message code="제품군 선택"/>
                        </th>
                        <td>
                            <input type="text" id="CODE5" name="CODE5" size="10" search="SM7099_01.dialog.openDialog_5"/>
                            <a href="javascript:SM7099_01.dialog.openDialog_5();"><img src="/images/sample/btn_sch.gif" alt="<spring:message code="SERCH"/>"/></a>
                            <input type="text" id="NAME5" name="NAME5" size="30" />
                        </td>
                        <th scope="row">
                            <spring:message code="NALADISA, CODE"/>
                        </th>
                        <td>
                            <input type="text" id="CODE6" name="CODE6" size="10" search="SM7099_01.dialog.openDialog_6"/>
                            <a href="javascript:SM7099_01.dialog.openDialog_6();"><img src="/images/sample/btn_sch.gif" alt="<spring:message code="SERCH"/>"/></a>
                            <input type="text" id="NAME6" name="NAME6" size="30" />
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">
                            <spring:message code="한국 확인서 엑셀 업로드"/>
                        </th>
                        <td>
                            <input type="text" id="CODE7" name="CODE7" size="10" search="SM7099_01.dialog.openDialog_7"/>
                            <a href="javascript:SM7099_01.dialog.openDialog_7();"><img src="/images/sample/btn_sch.gif" alt="<spring:message code="SERCH"/>"/></a>
                            <input type="text" id="NAME7" name="NAME7" size="30" />
                        </td>
                        <th scope="row">
                            <spring:message code="한국 증명서 등록"/>
                        </th>
                        <td>
                            <input type="text" id="CODE8" name="CODE8" size="10" search="SM7099_01.dialog.openDialog_8"/>
                            <a href="javascript:SM7099_01.dialog.openDialog_8();"><img src="/images/sample/btn_sch.gif" alt="<spring:message code="SERCH"/>"/></a>
                            <input type="text" id="NAME8" name="NAME8" size="30" />
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">
                            <spring:message code="테스트 팝업7"/>
                        </th>
                        <td>
                            <input type="text" name="OFFICER_NAME" id="OFFICER_NAME" value="${OFFICER_NAME}" 
                                   class="easyui-validatebox" size="10" style="width:99%;"
                                   title="<spring:message code="${OFFICER_NAME}"/>" />
                        </td>
                        <th scope="row">
                            <spring:message code="테스트 팝업8"/>
                        </th>
                        <td>
                            <input type="text" name="OFFICER_PHONE_NO" id="OFFICER_PHONE_NO" value="${OFFICER_PHONE_NO}" 
                                   class="easyui-validatebox" size="10" style="width:99%;"
                                   title="<spring:message code="${OFFICER_PHONE_NO}"/>" />
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">
                            <spring:message code="테스트 팝업7"/>
                        </th>
                        <td>
                            <input type="text" name="OFFICER_NAME" id="OFFICER_NAME" value="${OFFICER_NAME}" 
                                   class="easyui-validatebox" size="10" style="width:99%;"
                                   title="<spring:message code="${OFFICER_NAME}"/>" />
                        </td>
                        <th scope="row">
                            <spring:message code="테스트 팝업8"/>
                        </th>
                        <td>
                            <input type="text" name="OFFICER_PHONE_NO" id="OFFICER_PHONE_NO" value="${OFFICER_PHONE_NO}" 
                                   class="easyui-validatebox" size="10" style="width:99%;"
                                   title="<spring:message code="${OFFICER_PHONE_NO}"/>" />
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">
                            <spring:message code="테스트 팝업7"/>
                        </th>
                        <td>
                            <input type="text" name="OFFICER_NAME" id="OFFICER_NAME" value="${OFFICER_NAME}" 
                                   class="easyui-validatebox" size="10" style="width:99%;"
                                   title="<spring:message code="${OFFICER_NAME}"/>" />
                        </td>
                        <th scope="row">
                            <spring:message code="테스트 팝업8"/>
                        </th>
                        <td>
                            <input type="text" name="OFFICER_PHONE_NO" id="OFFICER_PHONE_NO" value="${OFFICER_PHONE_NO}" 
                                   class="easyui-validatebox" size="10" style="width:99%;"
                                   title="<spring:message code="${OFFICER_PHONE_NO}"/>" />
                        </td>
                    </tr>
                </tbody>
            </table>
        </form>
        
    </div>
    
    <script type="text/javascript" src="/js/views/MM/smp/SMP7010_01.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>