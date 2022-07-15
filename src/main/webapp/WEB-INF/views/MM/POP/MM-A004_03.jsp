<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : MM-A004_03.jsp
  * @Description : 보고서 발급 사유등록(형상관리용 화면)
  * @Modification Information
  *
  * author YNI-Maker
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMA004_03_dailog_01"으로 하셔야 합니다.
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
	        <div id="MMA004_03_div_01" class="btn_R">
		        <span id="MMA004_03_span_01"><a href="javascript:MMA004_03.event.btnSaveClick();" id="MMA004_03_btn_01" class="btnSave"><spring:message code="SAVE"/></a></span>
		        <a href="javascript:MMA004_03.dialog.closeDialog();" class="btnPopClose"><spring:message code="CLOSE"/><%--닫기--%></a>
	        </div>
	        <div class="h2_etc">
                <p class="h2"><spring:message code="CERIS, REASN"/><%-- 발급사유입력 --%></p>
            </div>
	        <form id="MMA004_03_form_01" name="MMA004_03_form_01" method="post">
	            <input type="hidden" id="flag" name="flag" value="${flag}"/>
	            <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
	            <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
	            <input type="hidden" id="DIVISION_CD" name="DIVISION_CD" value="${DIVISION_CD}"/>
	            <input type="hidden" id="CO_DOC_NO" name="CO_DOC_NO" value="${CO_DOC_NO}"/>
			    <table class="dataT" style="margin-bottom:0px;">
			        <caption>데이타 테이블</caption>
			        <colgroup>
			            <col width="130px;" />
			            <col width=";" />
			            <col width="130px;" />
                        <col width="150px;" />
			        </colgroup>
			        <tbody>
			            <tr>
			                <th scope="row" class="point2"><spring:message code="TITLE"/></th>
                            <td colspan="3">
                                <input type="text" name="REASON_TITLE" id="REASON_TITLE" value="${REASON_TITLE}" class="easyui-validatebox" validType="['maxlength[200]']" required="true" style="width:99%;"/>
                            </td>
                        </tr>
			            <tr>
                            <th scope="row" class="point2"><spring:message code="REQ,CHARS"/></th>
                            <td>
                                <input type="text" name="REQUESTER" id="REQUESTER" value="${REQUESTER}" class="easyui-validatebox" validType="['maxlength[30]']" required="true" style="width:98%;"/>
                            </td>
                            <th><spring:message code="TXT_REQUEST_DATE2"/></th>
                            <td>
                                <input name="REQUEST_DATE" id="REQUEST_DATE" value="${REQUEST_DATE}" style="width:100px;"/>
                            </td>
                        </tr>
                        <tr id="MMA004_03_tr_01" style="display:none;">
                            <th scope="row"><spring:message code="MSG_APPLY_CO_SUBMIT"/></th>
                            <td colspan="3">
                                <input type="checkbox" id="CO_SHARE_YN" name="CO_SHARE_YN" value="${CO_SHARE_YN}" onclick="javascript:MMA004_03.ui.changeCoShareCheck();"/> <spring:message code="APPLY"/> (<spring:message code="MSG_AUTO_CO_ISSUE"/>)
                            </td>
                        </tr>
			            <tr>
                            <th scope="row" class="point2"><spring:message code="TXT_REQUEST_REASN"/></th>
                            <td colspan="3" style="height:344px;">
                                <textarea name="REASON_CONTENTS" id="REASON_CONTENTS" class="easyui-validatebox" validType="['maxlength[300]']" required="true" style="width:99%;height:100%;">${REASON_CONTENTS}</textarea>
                            </td>
                        </tr>
			        </tbody>
			    </table>
			</form>
	   </div>
	</div>
	
    <script type="text/javascript" src="/js/views/MM/POP/MM-A004_03.js"></script>
	<script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body> 
</html>