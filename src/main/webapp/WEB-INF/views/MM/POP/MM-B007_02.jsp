<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : MM-B007_02.jsp
  * @Description : 란입력 품명지정 화면(업체이력을 조회해서 적용하도록 함)
  * @Modification Information
  *
  * author YNI-Maker(이메일)
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMB007_02_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>      
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <div class="easyui-layout" data-options="fit:true">
        <div class="easyui-layout" data-options="fit:true">
            <div data-options="region:'north',border:false" class="h2_etc">
                <p class="h2"><spring:message code="CC_업체 신고이력"/></p>
                <p class="h2_btn">
                    <a href="javascript:MMB007_02.control.applySelectData();" class="btnApply"><spring:message code="SELET"/></a>
                </p>
            </div>
            <div data-options="region:'center',border:false">
               <form id="MMB007_02_form_01" method="post">
                   <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
                   <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
                   <input type="hidden" id="HS_CD" name="HS_CD" value="${HS_CD}"/>
                   <input type="hidden" id="BCNC_CD" name="BCNC_CD" value="${BCNC_CD}"/>
                   <input type="hidden" id="SEARCH_TYPE" name="SEARCH_TYPE" value="${SEARCH_TYPE}"/>
               </form>
               <table id="MMB007_02_grid_01"> </table>
            </div>
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-B007_02.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>