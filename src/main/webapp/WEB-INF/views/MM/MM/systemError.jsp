<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<c:redirect url="/mm/login.htm" />--%>
<html>
<body>
<script type="text/javascript">
	var retVal = confirm("사용자 인증 정보를 찾을 수 없습니다.<br>로그인 페이지로 이동하시겠습니까?");
	 
	if(retVal == true) {
	    location.href = '${pageContext.request.contextPath}/mm/login.htm'; 
	}
</script>
</body>
</html>