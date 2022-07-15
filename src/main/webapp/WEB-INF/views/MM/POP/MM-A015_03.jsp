<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : MM-A015_03.jsp
  * @Description : 발행협정코드 표준코드 리스트 조회 및 선택
  * @Modification Information
  *
  * author RHN
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMA015_03_01_dailog_01"으로 하셔야 합니다.
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
		<div data-options="region:'north',border:false">
		</div>
        <div data-options="region:'center',border:false">
            <div class="easyui-layout" data-options="fit:true">
	            <div data-options="region:'north',border:false" class="h2_etc">
	                <p class="h2_btn">
	                    <a href="javascript:MMA015_03.control.selectRowValue();" class="btnPopTick"><spring:message code="SELET"/><%--선택--%></a>
	                </p>
	            </div>
	            <div data-options="region:'center',border:false">
	                <form id="MMA015_03_form_01" method="POST">
	                    <input type="hidden" id="TARGET_PID"  name="TARGET_PID"  value="${TARGET_PID}"/>
	                    <input type="hidden" id="CATEGORY_CD" name="CATEGORY_CD" value="${CATEGORY_CD}"/>
	                    <input type="hidden" id="COMPANY_CD"  name="COMPANY_CD"  value="${COMPANY_CD}"/>
	                    <input type="hidden" id="schKeyField" name="schKeyField" value="${schKeyField}"/> 
	                    <input type="hidden" id="schKeyWord" name="schKeyWord" value="${schKeyWord}"/>
	                    <input type="hidden" id="schKeyLike" name="schKeyLike" value="${schKeyLike}"/>
	                </form>
	                <table id="MMA015_03_grid_01"> </table>
	            </div>
	        </div>
        </div>
	</div>
	
	<script type="text/javascript" src="/js/views/MM/POP/MM-A015_03.js"></script>
	<script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body> 
</html>