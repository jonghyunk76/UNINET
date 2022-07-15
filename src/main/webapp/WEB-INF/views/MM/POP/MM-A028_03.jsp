<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%
 /**
  * @Class Name : MM-A028_03.jsp
  * @Description : 설문지 설정 뷰어 
  * @Modification Information
  *
  * author carlos
  *
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMA028_03_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body class="h2_etc">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center',border:true" style="width:100%;height:820px;background-color:#fff">
	        <form id="MMA028_03_form_01" name="MMA028_03_form_01" method="post">
	           <input type="hidden" id="str" name="str" value=""/>
	           <input type="hidden" id="SURVEY_FORMAT_MST_ID" name="SURVEY_FORMAT_MST_ID" value="${SURVEY_FORMAT_MST_ID}"/>
	           <div id="MMA028_03_div_01" style="padding-right:2px;"></div>
	        </form>
	    </div>
    </div>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-A028_03.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>
</body>
</html>