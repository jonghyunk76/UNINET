<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%
 /**
  * @Class Name : MM-A028_04.jsp
  * @Description : 협력사 설문지 작성 및 제출
  * @Modification Information
  *
  * author carlos
  *
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMA028_04_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body class="h2_etc">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'north',border:false" style="height:30px;overflow:hidden;">
	        <div class="btn_R">
	            <a href="javascript:MMA028_04.control.insertEvaluSurveyInfo('2');" id="MMA028_04_btn_01" class="btnSave" style="display:none;"><spring:message code="TXT_TEM_SAVE"/><%--임시저장--%></a>
	            <a href="javascript:MMA028_04.control.insertEvaluSurveyInfo('3');" id="MMA028_04_btn_02" class="btnPopIssue" style="display:none;"><spring:message code="SUNMT"/><%--제출--%></a>
	            <a href="javascript:MMA028_04.control.htmlConvertExcel();" id="MMA028_04_btn_04" class="btnPopPrint" style="display:none;"><spring:message code="BTN_EXCEL"/></a>
	            <a href="javascript:dialog.handle.close(dialog.getObject('MMA028_04_dailog_01'));" id="MMA028_04_btn_03" class="btnPopClose" style="display:none;"><spring:message code="CLOSE"/><%--닫기--%></a>
	        </div>
	    </div>
        <div data-options="region:'center',border:true" style="width:100%;height:820px;background-color:#fff">
	        <form id="MMA028_04_form_01" name="MMA028_04_form_01" method="post">
	           <input type="hidden" id="flag" name="flag" value="${flag}"/>
	           <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
	           <input type="hidden" id="PARENT_COMPANY_CD" name="PARENT_COMPANY_CD" value="${PARENT_COMPANY_CD}"/>
	           <input type="hidden" id="EVALU_CD" name="EVALU_CD" value="${EVALU_CD}"/>
	           <input type="hidden" id="BUSINESS_NO" name="BUSINESS_NO" value="${BUSINESS_NO}"/>
	           <input type="hidden" id="EVALU_STATUS" name="EVALU_STATUS"/>
	           <input type="hidden" id="SURVEY_FORMAT_MST_ID" name="SURVEY_FORMAT_MST_ID" value="${SURVEY_FORMAT_MST_ID}"/>
	           <input type="hidden" id="USER_ID" name="USER_ID" value="${_MEMBER.SESSION_USER_ID}"/>
	           <input type="hidden" id="USER_NAME" name="USER_NAME" value="${_MEMBER.SESSION_USER_NAME}"/>
	           <input type="hidden" id="SURVEY_NAME" name="SURVEY_NAME" value="${SURVEY_NAME}"/>
	           <input type="hidden" id="VENDOR_NAME" name="VENDOR_NAME" value="${VENDOR_NAME}"/>
	           <div id="MMA028_04_div_01" style="padding-right:2px;"></div>
	        </form>
	    </div>
    </div>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-A028_04.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>
</body>
</html>