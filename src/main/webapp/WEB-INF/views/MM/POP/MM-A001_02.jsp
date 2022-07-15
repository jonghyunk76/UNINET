<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : MM-A001_02.jsp
  * @Description : 날짜설정 Tooltip
  * @Modification Information
  *
  * author YNI-Maker
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMA001_02_tooltip_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <div style="padding:5px;">
        <div style="padding:5px 10px;">
            <form id="MMA001_02_form_02" name="MMA001_02_form_02" method="post">
                <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
                <div class="btn_C">
		            <input id="YYYY" name="YYYY" style="width:60px"><spring:message code="YEARS"/>
		            <input id="MM" name="MM"  style="width:40px"><spring:message code="MONTH"/>
	            </div>
            </form>
        </div>
        <div style="padding:5px 10px;text-align:center">
            <a href="javascript:MMA001_02.control.setCallFuntion();" id="MMA001_02_cfm_btn" class="easyui-linkbutton"><spring:message code="CNFIR"/></a>
            <a href="javascript:void(0)" id="MMA001_02_cls_btn" class="easyui-linkbutton"><spring:message code="CLOSE"/></a>
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-A001_02.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>