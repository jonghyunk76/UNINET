<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : SM-7009_03.jsp
  * @Description : 메뉴 상세정보
  * @Modification Information
  *
  * author sbj1000
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "SM7009_03_dailog_01"으로 하셔야 합니다.
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
        <div id="SM7009_03_div_01" class="btn_R">
	        <a href="javascript:SM7009_03.control.updateMenuLanguageInfo();" id="SM7009_03_btn_01" class="btnSave"><%--저장--%><spring:message code="SAVE"/></a>
        </div>
        <form id="SM7009_03_form_01" name="SM7009_03_form_01" method="post">
            <input type="hidden" id="flag" name="flag" value="${flag}"/>
            <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
            <input type="hidden" id="SESSION_COMPANY_CD" name="SESSION_COMPANY_CD" value="${_MEMBER.SESSION_COMPANY_CD}"/>
           	<input type="hidden" id="SESSION_USER_ID" name="SESSION_USER_ID" value="${_MEMBER.SESSION_USER_ID}"/>
           	<input type="hidden" id="SESSION_DEFAULT_LANGUAGE" name="SESSION_DEFAULT_LANGUAGE" value="${_MEMBER.SESSION_DEFAULT_LANGUAGE}"/>
           	<input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${_MEMBER.SESSION_COMPANY_CD}"/>
           	<input type="hidden" id="I_BUKRS" name="I_BUKRS" value="${_MEMBER.SESSION_COMPANY_CD}"/>
           	<input type="hidden" id="USER_ID" name="USER_ID" value="testUser"/>
           	<input type="hidden" id="FILE_TYPE" name="FILE_TYPE" value="I"/>
           	<input type="hidden" id="ITEM_TYPE" name="ITEM_TYPE" value=""/>
           	<input type="hidden" id="IF_CODE" name ="IF_CODE" value=""/>
           	<input type="hidden" id="VENDOR_CD" name="VENDOR_CD" value=""/>
            <p class="h2"><spring:message code="TXT_BASICINFO_MENU"/><%--메뉴정보--%></p>
		    <table class="dataT">
		        <caption>데이타 테이블</caption>
		        <colgroup>
		            <col width="100px" />
		            <col width="250px" />		            
		        </colgroup>
		        <tbody>
		            <tr>
		                <th scope="row" class="point2"><spring:message code="MENU, ID"/><%--메뉴 ID--%></th>
		                <td>
	                        <input type="text" id="MENU_ID" name="MENU_ID" value="${MENU_ID}" 
	                        	   class="easyui-validatebox" size="20"  required="true" readOnly
	                        	   title="<spring:message code="MENU, ID"/>" />
		                </td>
		            </tr>
		            <tr>
		                <th scope="row" class="point2"><spring:message code="LANG"/><%--언어--%></th>
		                <td >
		                    <input type="text" id="LANGUAGE_CAT" name ="LANGUAGE_CAT" value="${LANGUAGE_CAT}"
		                    	   class="easyui-validatebox" size="20"
		                    	   title="<spring:message code="LANG"/>" />
		                </td>
		            </tr>
		            <tr>
		                <th scope="row" class="point2"><spring:message code="LANG, ID"/><%--언어 ID--%></th>
		                <td>
	                        <input type="text" name="LANG_ID" id="LANG_ID" value="${LANG_ID}" 
	                        	   class="easyui-validatebox" size="20" validType="['number','maxlength[20]']"
	                        	   title="<spring:message code="LANG, ID"/>" />
	                    </td>
		            </tr>
		            <tr>
		                <th scope="row" class="point2"><spring:message code="TXT_MENU_NAME"/><%--메뉴명--%></th>
		                <td>
	                        <input type="text" id="WORD" name="WORD" value="${WORD}"
	                        	   class="easyui-validatebox" size="40" required="true"
	                        	   title="<spring:message code="TXT_MENU_NAME"/>" />
		                </td>
		            </tr>
		            <tr>
		                <th scope="row" class="point2"><spring:message code="TXT_MENU_DESC"/><%--메뉴 설명--%></th>
		                <td>
	                        <textarea id="MEANNING" name="MEANNING" value="${MEANNING}"
	                        	   class="easyui-validatebox" cols="38" rows="4"
	                        	   title="<spring:message code="TXT_MENU_DESC"/>"></textarea>
		                </td>
		            </tr>
		        </tbody>
		    </table>
		</form>
	</div>
    <script type="text/javascript" src="/js/views/FM/SM/SM-7009_03.js"></script>
	<script type="text/javascript" src="/js/frame/common/yni.window.js"></script>
</div>
</body> 
</html>