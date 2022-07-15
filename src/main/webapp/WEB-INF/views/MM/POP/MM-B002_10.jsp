<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : MM-B002_10.jsp
  * @Description : 표준 품명
  * @Modification Information
  *
  * author YNI-Maker(이메일)
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMB002_10_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>      
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'west',border:false,split:true" style="width:498px;">
            <div class="easyui-layout" data-options="fit:true">
                <div data-options="region:'north',border:false" class="h2_etc">
                    <p class="h2"><spring:message code="CC_세번코드 목록"/></p>
                    <p class="h2_btn">
                        <a href="javascript:MMB002_10.control.selectStardHscodeList();" class="btnRefresh"><spring:message code="RENEW"/></a>
                    </p>
                </div>
                <div data-options="region:'center',border:false" style="padding-top:3px;">
                   <table id="MMB002_10_grid_01"> </table>
                </div>
            </div>
        </div>
        <div data-options="region:'center',border:false">
            <div class="easyui-layout" data-options="fit:true">
                <div data-options="region:'north',border:false" class="h2_etc">
                    <form id="MMB002_10_form_02" method="POST">
                    <p class="h2">
                        <spring:message code="CC_표준품명 코드 목록"/>
                    </p>
                    <p class="h2_btn">
                        <label><input type="checkbox" id="ONLY_STD_CODE_YN" name="ONLY_STD_CODE_YN" checked value="Y"/><spring:message code="CC_표준품명코드만 적용"/> </label>
                        <a href="javascript:MMB002_10.control.selectStardProductcodeRow();" class="btnApply"><spring:message code="APYDT"/></a>
                    </p>
                    </form>
                </div>
                <div data-options="region:'center',border:false" style="padding-top:3px;">
                    <form id="MMB002_10_form_01" method="POST">
	                    <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
	                    <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
	                    <input type="hidden" id="HS_CD" name="HS_CD" value="${HS_CD}"/>
	                    <input type="hidden" id="STT_DE" name="STT_DE" value="${STT_DE}"/>
	                    <input type="hidden" id="YYYY" name="YYYY" value="${YYYY}"/>
                    </form>
                    <table id="MMB002_10_grid_02"> </table>
                </div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-B002_10.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>