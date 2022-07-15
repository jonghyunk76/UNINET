<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : SM-7009_02.jsp
  * @Description : 메뉴 상세정보
  * @Modification Information
  *
  * author sbj1000
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "SM7009_02_dailog_01"으로 하셔야 합니다.
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
        <div id="SM7009_02_div_01" class="btn_R">
	        <a href="javascript:SM7009_02.control.updateMenuInfo();" id="SM7009_02_btn_01" class="btnSave"><%--저장--%><spring:message code="SAVE"/></a>
        </div>
        <form id="SM7009_02_form_01" name="SM7009_02_form_01" method="post">
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
           	<input type="hidden" id="MENU_TYPE" name ="MENU_TYPE" value="${MENU_TYPE}"/>
            <p class="h2"><spring:message code="TXT_BASICINFO_MENU"/><%--메뉴정보--%></p>
		    <table class="dataT">
		        <caption>데이타 테이블</caption>
		        <colgroup>
		            <col width="130px"/>
		            <col width=";"/>
		            <col width="130px"/>
		            <col width=";"/>
		        </colgroup>
		        <tbody>
		            <tr>
		                <th scope="row" class="point2"><spring:message code="TOP, SYS, ID"/><%--상위 시스템 ID--%></th>
                        <td>
                            <input type="text" id="HGRNK_SYS_ID" name="HGRNK_SYS_ID" style="width:200px;" title="<spring:message code="TOP, SYS, ID"/>"/>
                        </td>
		                <th scope="row" class="point2"><spring:message code="SYS, ID"/><%--시스템 ID--%></th>
                        <td>
                            <input type="text" id="SYS_ID" name="SYS_ID" value="${SYS_ID}" style="width:200px;" title="<spring:message code="SYS, ID"/>"/>
                        </td>
		            </tr>
		            <tr>
		                <th scope="row" class="point2"><spring:message code="TOP, MENU, NAME"/><%--상위 메뉴--%></th>
                        <td>
                            <input type="text" name="HGRNK_MENU_ID" id="HGRNK_MENU_ID" value="${HGRNK_MENU_ID}" style="width:200px;" title="<spring:message code="TOP, MENU, NAME"/>"/>
                        </td>
		                <th scope="row" class="point2"><spring:message code="MENU, ID"/><%--메뉴 ID--%></th>
		                <td>
	                        <input type="text" id="MENU_ID" name="MENU_ID" value="${MENU_ID}" class="easyui-validatebox" style="width:98%"  required="true" title="<spring:message code="MENU, ID"/>"/>
		                </td>
		            </tr>
		            <tr>
		                <th scope="row" class="point2"><spring:message code="TXT_MENU_NAME"/><%--메뉴명--%></th>
                        <td colspan="3">
                            <input type="text" id="MENU_NM" name ="MENU_NM" value="${MENU_NM}" class="easyui-validatebox" style="width:98%" required="true" title="<spring:message code="TXT_MENU_NAME"/>"/>
                        </td>
                    </tr>
                    <tr>
		                <th scope="row" class="point2"><spring:message code="TXT_MENU_LEVEL"/><%--메뉴 레벨--%></th>
		                <td>
	                        <input type="text" id="MENU_LVL_NO" name="MENU_LVL_NO" value="${MENU_LVL_NO}" style="width:98%" title="<spring:message code="TXT_MENU_LEVEL"/>"/>
		                </td>
		                <th scope="row" class="point2"><spring:message code="TXT_SORT_SEQ"/><%--정렬 순번--%></th>
		                <td >
		                    <input type="text" id="MENU_SORT_SER" name ="MENU_SORT_SER" value="${MENU_SORT_SER}" class="easyui-validatebox" style="width:98%" required="true" validType="['number','maxlength[20]']"
		                    	   title="<spring:message code="TXT_SORT_SEQ"/>"/>
		                </td>
		            </tr>
		            <tr>
		                <th scope="row" class="point2"><spring:message code="TXT_URL"/><%--URL--%></th>
		                <td colspan="3">
	                        <input type="text" id="DIRECT_URL" name="DIRECT_URL" value="${DIRECT_URL}" class="easyui-validatebox" style="width:99%" required="true" title="<spring:message code="TXT_URL"/>"/>
		                </td>
		            </tr>
		            <tr>
		                <th scope="row"><spring:message code="INF, ID"/><%--인터페이스 ID--%></th>
		                <td>
		                    <input type="text" id="PRGM_ID" name ="PRGM_ID" value="${PRGM_ID}" class="easyui-validatebox" style="width:98%" title="<spring:message code="INF, ID"/>"/>
		                </td>
		                <th scope="row" class="point2"><spring:message code="TXT_USE_YN"/><%--사용 유무--%></th>
		                <td>
	                        <input type="text" id="USE_YN" name="USE_YN" value="${USE_YN}" style="width:98%" title="<spring:message code="TXT_USE_YN"/>"/>
		                </td>
		            </tr>
		            <tr>
		                <th scope="row"><spring:message code="TXT_MENU_DESC"/><%--메뉴 설명--%></th>
		                <td colspan="3">
	                        <textarea id="MENU_EPLN" name="MENU_EPLN" value="${MENU_EPLN}" class="easyui-validatebox" cols="38" rows="4" style="width:99%" title="<spring:message code="TXT_MENU_DESC"/>"></textarea>
		                </td>
		            </tr>
		        </tbody>
		    </table>
		</form>
	</div>
    <script type="text/javascript" src="/js/views/FM/SM/SM-7009_02.js"></script>
	<script type="text/javascript" src="/js/frame/common/yni.window.js"></script>
</div>
</body> 
</html>