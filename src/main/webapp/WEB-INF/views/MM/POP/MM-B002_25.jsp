<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : MM-B002_25.jsp
  * @Description : 수입화물진행정보 목록
  * @Modification Information
  *
  * author YNI-Maker(이메일)
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMB002_25_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>      
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center',border:false" style="padding-top:2px;">
            <div class="easyui-layout" data-options="fit:true">
                <div data-options="region:'north',border:false" class="h2_etc">
                    <p class="h2"><spring:message code="CC_수입화물진행정보 목록"/></p>
                    <p class="h2_btn">
                        <a href="javascript:MMB002_25.control.selectRowValue();" id="MMB002_25_btn_02" class="btnPopTick"><spring:message code="SELET"/></a>
                        <a href="javascript:dialog.handle.close(dialog.getObject('MMB002_25_dailog_01'));" id="MMB002_25_btn_03" class="btnPopClose"><spring:message code="CLOSE"/></a>
                    </p>
                </div>
                <div data-options="region:'center',border:false" style="padding-top:3px;">
                    <div class="easyui-layout" data-options="fit:true">
                        <div data-options="region:'north',border:false" class="h2_etc">
                            <form id="MMB002_25_form_01" method="post">
	                            <input type="hidden" id="SESSION_COMPANY_CD" name="SESSION_COMPANY_CD" value="${_MEMBER.SESSION_COMPANY_CD}" />
								<input type="hidden" id="SESSION_USER_ID" name="SESSION_USER_ID" value="${_MEMBER.SESSION_USER_ID}" />
								<input type="hidden" id="DEFAULT_LANGUAGE" name="DEFAULT_LANGUAGE" value="${_MEMBER.SESSION_DEFAULT_LANGUAGE}" />
								<input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${_MEMBER.SESSION_COMPANY_CD}" />
								<input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}" />
								<input type="hidden" id="gridData" name="gridData" value="${gridData}"/>
								<input type="hidden" id="gridDatas" name="gridDatas"/>								
                            </form>
                        </div>
                        <div data-options="region:'center',border:false">
		                    <table id="MMB002_25_grid_01"> </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-B002_25.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>