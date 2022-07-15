<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
<meta http-equiv="Expires" content="0">
<meta http-equiv="Pragma" content="no-cache">

<script type="text/javascript">
	window.onload = function() {
		var lang = navigator.language || navigator.userLanguage;
		lang = lang.toUpperCase().substring(0, 2);
		
		document.getElementById('TOMSFTA_from_1').chageLanguage.value = lang; 
		document.getElementById('TOMSFTA_from_1').submit();
		
		return true;
	};
</script>
<body>
    <form id="TOMSFTA_from_1" target="_top" method="POST" action="${pageContext.request.contextPath}/mm/login.htm" onsubmit="javascript:window.location.reload();">
	    <input type="hidden" id="chageLanguage" name="chageLanguage"/>
	</form>
</body>
</html>