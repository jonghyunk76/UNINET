<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : SM-7005_03.jsp
  * @Description : 인터페이스 항목 상세 조회 및 관리
  * @Modification Information
  *
  * author carlos
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "SM7005_03_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body class="h2_etc">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center',border:false" style="overflow:hidden;">
        
            <div id="SM7005_03_div_01" class="btn_R">
                <a href="javascript:SM7005_03.control.updateInterfaceItemMstInfo();" id="SM7005_03_btn_01" class="btnSave"><spring:message code="SAVE"/></a>
                <a href="javascript:SM7005_03.control.deleteInterfaceItemMstInfo();" id="SM7005_03_btn_02" class="btnDelete"><spring:message code="DEL"/></a>
            </div>
            <form id="SM7005_03_form_01" name="SM7005_03_form_01" method="post">
                <input type="hidden" id="flag" name="flag" value="${flag}"/>
                <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
                <input type="hidden" id="SESSION_COMPANY_CD" name="SESSION_COMPANY_CD" value="${SESSION_COMPANY_CD}"/>
                <input type="hidden" id="SESSION_USER_ID" name="SESSION_USER_ID" value="${SESSION_USER_ID}"/>
                <input type="hidden" id="DEFAULT_LANGUAGE" name="DEFAULT_LANGUAGE" value="${DEFAULT_LANGUAGE}"/>
                <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
                <input type="hidden" id="CREATE_BY" name="CREATE_BY" value="${SESSION_USER_ID}"/>
                <input type="hidden" id="CREATE_BY" name="UPDATE_BY" value="${SESSION_USER_ID}"/>
                <input type="hidden" id="INTERFACE_ITEM_DTL_ID" name="INTERFACE_ITEM_DTL_ID" value="${INTERFACE_ITEM_DTL_ID}"/>
                <input type="hidden" id="INTERFACE_ITEM_MST_ID" name="INTERFACE_ITEM_MST_ID" value="${INTERFACE_ITEM_MST_ID}"/>
                <input type="hidden" id="COL_SEQ" name="COL_SEQ" value="${COL_SEQ}"/>
                <p class="h2"><spring:message code="DETIL,INFMT"/><%--상세정보--%></p>
                <table class="dataT">
                    <caption><spring:message code="DTA, TB"/><%--데이타 테이블--%></caption>
                    <colgroup>
                        <col width="150px" />
                        <col width="220px" />
                        <col width="150px" />
                        <col width="220px" />
                    </colgroup>
                    <tbody>
                        <tr>
                            <th scope="row">
                                <spring:message code="INF, CODE"/><%--인터페이스 코드--%>
                            </th>
                            <td>
                                <input type="text" name="IF_CD" id="IF_CD" value="${IF_CD}" 
                                       class="easyui-validatebox" required="true" validType="['maxlength[50]']" size="30"
                                       title="<spring:message code="INF, CODE"/>" />
                            </td>
                            <th scope="row">
                                <spring:message code="INF, NAME"/><%--인터페이스 명--%>
                            </th>
                            <td>
                                <input type="text" name="IF_NAME" id="IF_NAME" value="${IF_NAME}" 
                                       class="easyui-validatebox" required="true" validType="['maxlength[200]']" size="30"
                                       title="<spring:message code="INF, NAME"/>" />
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">
                                <spring:message code="ORIGIN,TB"/><%--원천테이블--%>
                            </th>
                            <td>
                                <input type="text" name="SOURCE_TABLE" id="SOURCE_TABLE" value="${SOURCE_TABLE}" 
                                       class="easyui-validatebox" required="true" validType="['maxlength[30]']" size="30"
                                       title="<spring:message code="ORIGIN,TB"/>" />
                            </td>
                            <th scope="row">
                                <spring:message code="TARGT,TB"/><%--대상테이블--%>
                            </th>
                            <td>
                                <input type="text" name="TARGET_TABLE" id="TARGET_TABLE" value="${TARGET_TABLE}" 
                                       class="easyui-validatebox" required="true" validType="['maxlength[30]']" size="30"
                                       title="<spring:message code="TARGT,TB"/>" />
                            </td>
                        </tr>
                    </tbody>
                </table>
                
                <p class="h2"><spring:message code="INF, ITEMS, REGER"/><%--인터페이스 항목 등록--%></p>
                <table class="dataT">
                    <caption><spring:message code="DTA, TB"/><%--데이타 테이블--%></caption>
                    <colgroup>
                        <col width="150px" />
                        <col width="220px" />
                        <col width="150px" />
                        <col width="220px" />
                    </colgroup>
                    <tbody>
                        <tr>
                            <th scope="row" class="point2">
                                <spring:message code="ORIGIN,TB, TXT_COLUMN,NAME"/><%--원천테이블 컬럼명--%>
                            </th>
                            <td>
                                <input type="text" name="FROM_COL_ID" id="FROM_COL_ID" value="${FROM_COL_ID}" 
                                       class="easyui-validatebox" required="true" validType="['maxlength[30]']" size="30"
                                       title="<spring:message code="ORIGIN,TB, TXT_COLUMN,NAME"/>" />
                            </td>
                            <th scope="row" class="point2">
                                <spring:message code="TARGT,TB, TXT_COLUMN,NAME"/><%--대상테이블 컬럼명--%>
                            </th>
                            <td>
                                <input type="text" name="TO_COL_ID" id="TO_COL_ID" value="${TO_COL_ID}" 
                                       class="easyui-validatebox" required="true" validType="['maxlength[30]']" size="30"
                                       title="<spring:message code="TARGT,TB, TXT_COLUMN,NAME"/>" />
                            </td>
                        </tr>
                        <tr>
                            <th scope="row" class="point2">
                                <spring:message code="REQUD,ORNOT"/><%--필수여부--%>
                            </th>
                            <td>
                                <input type="text" name="REQUIRED_YN" id="REQUIRED_YN" value="${REQUIRED_YN}" size="15"
                                       title="<spring:message code="REQUD,ORNOT"/>" />
                            </td>
                            <th scope="row" class="point2">
                                <spring:message code="DTA, TYP"/><%--데이터 타입--%>
                            </th>
                            <td>
                                <input type="text" name="DATA_TYPE" id="DATA_TYPE" value="${DATA_TYPE}" size="15" 
                                       title="<spring:message code="DTA, TYP"/>"/>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row" class="point2">
                                <spring:message code="DTA, LENG"/><%--데이터 길이--%>
                            </th>
                            <td>
                                <input type="text" name="DATA_LENGTH" id="DATA_LENGTH" value="${DATA_LENGTH}" 
                                       class="easyui-validatebox" required="true" validType="'floatformatexts[5,1]'" size="20"
                                       title="<spring:message code="DTA, LENG"/>" />
                            </td>
                            <th scope="row">
                                <spring:message code="DTA, FOMAT"/><%--데이터 포맷--%>
                            </th>
                            <td>
                                <input type="text" name="DATA_FORMAT" id="DATA_FORMAT" value="${DATA_FORMAT}" 
                                       class="easyui-validatebox" validType="['maxlength[30]']" size="30"
                                       title="<spring:message code="DTA, FOMAT"/>" />
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        
        </div>
        
    </div>
    
    <script type="text/javascript" src="/js/views/FM/SM/SM-7005_03.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>