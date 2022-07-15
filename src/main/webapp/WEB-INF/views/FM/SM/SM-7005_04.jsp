<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : SM-7005_04.jsp
  * @Description : 인터페이스 항목(프로시저) 상세 조회 및 관리
  * @Modification Information
  *
  * author carlos
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "SM7005_04_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body class="h2_etc">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center',border:false">
            <div id="SM7005_04_div_01" class="btn_R">
                <a href="javascript:SM7005_04.control.updateInterfaceItemMstInfo();" id="SM7005_04_btn_01" class="btnSave"><spring:message code="SAVE"/></a>
                <a href="javascript:SM7005_04.control.deleteInterfaceItemMstInfo();" id="SM7005_04_btn_02" class="btnDelete"><spring:message code="DEL"/></a>
            </div>
            <form id="SM7005_04_form_01" name="SM7005_04_form_01" method="post">
                <input type="hidden" id="flag" name="flag" value="${flag}"/>
                <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
                <input type="hidden" id="SESSION_COMPANY_CD" name="SESSION_COMPANY_CD" value="${SESSION_COMPANY_CD}"/>
                <input type="hidden" id="SESSION_USER_ID" name="SESSION_USER_ID" value="${SESSION_USER_ID}"/>
                <input type="hidden" id="DEFAULT_LANGUAGE" name="DEFAULT_LANGUAGE" value="${DEFAULT_LANGUAGE}"/>
                <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
                <input type="hidden" id="CREATE_BY" name="CREATE_BY" value="${SESSION_USER_ID}"/>
                <input type="hidden" id="CREATE_BY" name="UPDATE_BY" value="${SESSION_USER_ID}"/>
                <input type="hidden" id="INTERFACE_ITEM_MST_ID" name="INTERFACE_ITEM_MST_ID" value="${INTERFACE_ITEM_MST_ID}"/>
                <input type="hidden" id="ITEM_TYPE" name="ITEM_TYPE" value="P"/><%--T:TABLE, P:PROCEDURE--%>
                <input type="hidden" id="FILE_TYPE" name="FILE_TYPE" value="I"/>
                <p class="h2"><spring:message code="DETIL,INFMT"/><%--상세정보--%></p>
                <table class="dataT" style="margin-bottom:0px;">
                    <caption><spring:message code="DTA, TB"/><%--데이타 테이블--%></caption>
                    <colgroup>
                        <col width="150px" />
                        <col width="220px" />
                    </colgroup>
                    <tbody>
                        <tr>
                            <th scope="row" class="point2">
                                <spring:message code="INF, CODE,(,PROCE,)"/><%--인터페이스 코드(프로시저)--%>
                            </th>
                            <td>
                                <input type="text" name="IF_CD" id="IF_CD" value="${IF_CD}" 
                                       class="easyui-validatebox" required="true" validType="['maxlength[50]']" style="width:50%;"
                                       title="<spring:message code="INF, CODE"/>" />
                            </td>
                        </tr>
                        <tr>
                            <th scope="row" class="point2">
                                <spring:message code="INF, NAME"/><%--인터페이스 명--%>
                            </th>
                            <td>
                                <input type="text" name="IF_NAME" id="IF_NAME" value="${IF_NAME}" 
                                       class="easyui-validatebox" required="true" validType="['maxlength[200]']" style="width:98%;"
                                       title="<spring:message code="INF, NAME"/>" />
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">
                                <spring:message code="PROGM_NAME"/><%--프로그램명--%>
                            </th>
                            <td>
                                <input type="text" name="FILE_PATH" id="FILE_PATH" value="${FILE_PATH}" style="width:98%;"/>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row" class="point2">
                                <spring:message code="USE,ORNOT"/><%--사용여부--%>
                            </th>
                            <td>
                                <input type="text" name="USING_YN" id="USING_YN" value="${USING_YN}" style="width:100px;"/>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">
                                <spring:message code="PROCE, DSCPT"/><%--프로시저 설명--%>
                            </th>
                            <td>
                                <textarea name="REMARK" id="REMARK" class="input_red easyui-validatebox" validType="['maxlength[2000]']" style="width:98%;height:50px;">${REMARK}</textarea>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        
        </div>
        
    </div>
    
    <script type="text/javascript" src="/js/views/FM/SM/SM-7005_04.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>