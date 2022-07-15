<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : MM-A025_01.jsp
  * @Description : 수출매출리스트조회 상세
  * @Modification Information
  *
  * author 이재욱
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMA025_01_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>      
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body class="h2_etc">
    <div class="easyui-layout" data-options="fit:true">
        <div id="MMA025_01_div_01" class="btn_R">
	        <a href="javascript:MMA025_01.control.selectRowValue();" id="MMA025_01_btn_01" class="pop_btn"><spring:message code="선택완료"/></a>
        </div>
        <form id="MMA025_01_form_01" name="MMA025_01_form_01" method="post">
            <input type="hidden" id="flag" name="flag" value="${flag}"/>
            <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
            <input type="hidden" id="DIVISION_CD" name="DIVISION_CD" value="${DIVISION_CD}"/>
            <input type="hidden" id="CO_DOC_NO" name="CO_DOC_NO" value="${CO_DOC_NO}"/>
            <input type="hidden" id="gridData"    name="gridData"    value='${gridData}'/>
            <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
		</form>
        <!-- //그리드 -->
        <div data-options="region:'center',border:false" style="height:100%;padding-top:6px;">
        	<div class="easyui-layout" data-options="fit:true">
	            <div data-options="region:'north',border:false" class="h2_etc" style="height:215px;">
	                <div class="easyui-layout" data-options="fit:true">
        	            <div data-options="region:'center',border:false">
        	               <table id="MMA025_01_grid_01"> </table>
        	            </div>
                     </div>
                </div>
	        </div>
		</div>
	</div>
	
    <script type="text/javascript" src="/js/views/MM/POP/MM-A025_01.js"></script>
	<script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body> 
</html>