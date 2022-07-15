<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : MM-A031_02.jsp
  * @Description : 메일발송 메시지 수정
  * @Modification Information
  *
  * author YNI-Maker(이메일)
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMA031_02_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>      
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'north',border:false" style="overflow:hidden;">
            <div class="btn_R">
                <a href="javascript:MMA031_02.control.selectRowValue();" class="btnPopTick"><spring:message code="SELET"/></a>
            </div>
        </div>
        <div data-options="region:'center',border:false">
	        <div class="easyui-layout" data-options="fit:true">
	            <div data-options="region:'west',border:false,split:true" style="width:400px;padding-top:2px;">
	                <div class="easyui-layout" data-options="fit:true">
                       <div data-options="region:'north',border:false" class="h2_etc">
                           <p class="h2"><spring:message code="MAIL,CONTS, LIST"/></p>
                       </div>
                       <div data-options="region:'center',border:false">
                           <form id="MMA031_02_form_01" name="MMA031_02_form_01" method="post">
                               <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
                               <input type="hidden" name="COMPANY_CD" id="COMPANY_CD" value="${_MEMBER.SESSION_COMPANY_CD}"/>
                               <input type="hidden" name="MESSAGE_TYPE" id="MESSAGE_TYPE" value="D"/>
                           </form>
                           <table id="MMA031_02_grid_01"></table>
                       </div>
                    </div>
	            </div>
	            <div data-options="region:'center',border:false" style="padding-top:2px;">
                    <div class="easyui-layout" data-options="fit:true">
                        <div data-options="region:'north',border:false" class="h2_etc">
                            <p class="h2"><spring:message code="PREVE"/> (<spring:message code="SAMPL"/>)</p>
                        </div>
                        <div data-options="region:'center',border:false" style="background-color:#fff;padding:3px;border: 1px solid #b7b7b7;">
                            <div id="MMA031_02_html_01"></div>
                        </div>
                     </div>
			    </div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-A031_02.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>