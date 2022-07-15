<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : MM-B002_26.jsp
  * @Description : 법령부호별 서류명 조회
  * @Modification Information
  *
  * author YNI-Maker(이메일)
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMB002_26_dailog_01"으로 하셔야 합니다.
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
            <p class="h2"><spring:message code="CODE_LIST"/></p>
            <p class="h2_btn">
                <a href="javascript:MMB002_26.control.selectRowValue();" class="btnPopTick"><spring:message code="SELET"/></a>
            </p>
        </div>
        <div data-options="region:'center',border:false" style="padding-top:3px;">
            <form id="MMB002_26_form_01" method="POST">
                <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
                <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
                <input type="hidden" id="LAOR_CD" name="LAOR_CD" value="${LAOR_CD}"/>
                <input type="hidden" id="IMP_EXP_SE" name="IMP_EXP_SE" value="${IMP_EXP_SE}"/><!-- 1:수출, 2:수입 -->
            </form>
            <table id="MMB002_26_grid_01"> </table>
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-B002_26.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>