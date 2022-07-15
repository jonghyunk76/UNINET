<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : MM-A024_01.jsp
  * @Description : 제품군 조회 및 선택
  * @Modification Information
  *
  * author RHN
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMA024_01_dialog_01"으로 하셔야 합니다.
  *
  *
  **************************** 팝업 호출시 필수 파라미터 ID명
  * 부모창ID : TARGET_PID
  * 회사코드 : COMPANY_CD
  *
  **************************** 팝업 리턴값
  * 품목코드 : PRODUCT_LINE_CD
  * 품목명   : CONTENT1
  *
  */
%>
<!DOCTYPE html>
<html>
<head>      
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <div class="easyui-layout" data-options="fit:true">
        <form id="MMA024_01_form_01" method="POST">
            <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
            <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
        </form>
        
        <!-- //검색조건 -->
        <div data-options="region:'center',border:false" style="height:100%;padding-top:6px;">
            <div class="easyui-layout" data-options="fit:true">
                <div data-options="region:'north',border:false" class="h2_etc">
                    <p class="h2_btn">
                        <a href="javascript:MMA024_01.control.selectRowValue();" class="btnPopTick"><spring:message code="SELET"/><%--선택--%></a>
                    </p>
                </div>
                <div data-options="region:'center',border:false">
                   <table id="MMA024_01_grid_01"> </table>
                </div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-A024_01.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>