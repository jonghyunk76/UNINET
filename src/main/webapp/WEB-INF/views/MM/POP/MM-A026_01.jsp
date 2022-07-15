<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="kr.yni.frame.util.DateHelper" %>
<%
 /**
  * @Class Name : MM-A026_01.jsp
  * @Description : 공통 이미지정보 조회
  * @Modification Information
  *
  * author carlos
  *
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMA026_01_dailog_01"으로 하셔야 합니다.
  */
%>
<%-- 20160510::carlos::CACHE-KEY --%>
<c:set var="nowDateTime" value="<%=DateHelper.getCurrentDateTimeAsString()%>" />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body class="h2_etc">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center',border:false" style="overflow:hidden;">
            <img src="/mm/pop/mmA026_01/selectImageInfo?reqType=${reqType}&imageKey=${imageKey}&ck=${nowDateTime}" 
                 id="imageInfo" alt="image info" title="image info" />
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-A026_01.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>
</body>
</html>