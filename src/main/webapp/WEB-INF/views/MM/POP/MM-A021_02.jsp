<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : MM-A021_02.jsp
  * @Description : 협력사 확인서 원산지 이력 상세조회
  * @Modification Information
  *
  * author RHN
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMA021_02_dialog_01"으로 하셔야 합니다.
  *
  */
%>
<!DOCTYPE html>
<html>
<head>      
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'north',border:false" class="h2_etc">
            <p class="h2"><spring:message code="VGB000_01, RECOD"/></p>
            <p class="h2_btn">
                <a href="javascript:MMA021_02.excel.excelDownload_1();" class="btnExlDown"><spring:message code="BTN_EXCEL"/><%--엑셀다운로드--%></a>
            </p>
        </div>
        <div data-options="region:'center',border:false">
            <form id="MMA021_02_form_01" method="POST">
                <input type="hidden" id="CO_DOC_NO" name="CO_DOC_NO" value="${CO_DOC_NO}"/>
		        <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
		        <!-- input type="hidden" id="DIVISION_CD" name="DIVISION_CD" value="${DIVISION_CD}"/ -->
		        <input type="hidden" id="VENDOR_CD" name="VENDOR_CD" value="${VENDOR_CD}"/>
		        <input type="hidden" id="MODIFY_REQ" name="MODIFY_REQ" value="${MODIFY_REQ}"/>
		    </form>
            <table id="MMA021_02_grid_01"> </table>
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-A021_02.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>