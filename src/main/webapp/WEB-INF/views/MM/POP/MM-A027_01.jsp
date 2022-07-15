<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : MM-A027_01.jsp
  * @Description : 인터페이스 이력 데이터 조회
  * @Modification Information
  *
  * author YNI-Maker
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMA027_01_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>      
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body class="h2_etc">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'north',border:false" class="h2_etc">
            <p class="h2"><spring:message code="DTA, DETIL"/> (${IF_NAME})</p>
            <p class="h2_btn">
                <a href="javascript:MMA027_01.excel.excelDownload_1();" class="btnExlDown"><spring:message code="EXCEL, DOWN"/><%--엑셀다운로드--%></a>
            </p>
        </div>
        <div data-options="region:'center',border:false">
            <form id="MMA027_01_form_01" method="POST">
	            <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
	            <input type="hidden" id="IF_NAME" name="IF_NAME" value="${IF_NAME}"/>
	            <input type="hidden" id="IF_CD" name="IF_CD" value="${IF_CODE}"/>
	            <input type="hidden" id="INTERFACE_HISTORY_ID" name="INTERFACE_HISTORY_ID" value="${INTERFACE_HISTORY_ID}"/>
	            <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
            </form>
            <table id="MMA027_01_grid_01"> </table>
        </div>
	</div>
	
    <script type="text/javascript" src="/js/views/MM/POP/MM-A027_01.js"></script>
	<script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body> 
</html>