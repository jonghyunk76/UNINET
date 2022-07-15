<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : MM-A012_01.jsp
  * @Description : NALADISA CODE 선택 팝업
  * @Modification Information
  *
  * author HanaRyu
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMA012_01_dailog_01"으로 하셔야 합니다.
  *
  *
  **************************** 팝업 호출시 필수 파라미터 ID명
  * 부모창ID : TARGET_PID
  *
  **************************** 팝업 리턴값
  * HS코드       : HS_CODE
  * NALADISA코드 : NALADISA_CODE
  * NALADISA명   : NALADISA_CODE_NAME
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
		<!-- 검색조건 -->
		<div data-options="region:'north',border:false" class="sch_box2">
    		<div class="sch_boxIn2">
    		    <form id="MMA012_01_form_01" method="POST">
                    <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>

    				<table>
    					<caption>데이타 검색조건 설정</caption>
    					<colgroup>
    						<col width="250px" />
    						<col width="250px" />
    						<col width="300px" />
    						<col width="250px" />
    						<col width=";"/>
    					</colgroup>
    					<tbody>
    						<tr>
    							<th scope="row"><spring:message code="NALADISA, CODE"/><%--NALADISA 코드--%></th>
    							<td><input type="text" name="CODE" id="CODE" value="${CODE}" class="easyui-validatebox" search="MMA012_01.control.selectMainList" size="7" /></td>
    							<th scope="row"><spring:message code="NALADISA, NAME"/><%--NALADISA 명--%></th>
    							<td><input type="text" name="CODE_NAME" id="CODE_NAME" value="${CODE_NAME}" class="easyui-validatebox" search="MMA012_01.control.selectMainList" size="15" /></td>
    							<td class="txt_R">
    	                           <a href="javascript:MMA012_01.control.selectMainList();" class="easyui-linkbutton pop_sch" id="MMA012_01_searchBtn"><spring:message code="SRCH"/><%--조회--%></a>
    	                        </td>
    						</tr>
    					</tbody>
    				</table>
    			</form>
    		</div>
		</div>
        <!-- //검색조건 -->
        <div data-options="region:'center',border:false" style="height:100%;padding-top:6px;">
            <div class="easyui-layout" data-options="fit:true">
	            <div data-options="region:'north',border:false" class="h2_etc">
	                <p class="h2"><spring:message code="NALADISA, HISTY"/><%--NALADISA 내역--%></p>
	                <p class="h2_btn">
	                    <a href="javascript:MMA012_01.control.selectRowValue();" class="btnPopTick"><spring:message code="SELET"/><%--선택--%></a>
	                </p>
	            </div>
	            <div data-options="region:'center',border:false">
	               <table id="MMA012_01_grid_01"> </table>
	            </div>
	        </div>
        </div>
	</div>
	
	<script type="text/javascript" src="/js/views/MM/POP/MM-A012_01.js"></script>
	<script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body> 
</html>