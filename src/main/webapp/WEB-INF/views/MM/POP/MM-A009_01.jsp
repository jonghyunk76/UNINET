<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : MM-A009_01.jsp
  * @Description : 사용자 조회 및 선택
  * @Modification Information
  *
  * author YNI-Maker(이메일)
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMA009_01_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>      
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
	    <!-- 화면 디자인 영역 구현 -->
	    <!-- 검색조건 -->
	    <div data-options="region:'north',border:false" class="sch_box2">
		    <div class="sch_boxIn2">
		        <form id="MMA009_01_form_01" method="POST">
		            <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
		            <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
		            <table>
		                <caption>데이타 검색조건 설정</caption>
		                <colgroup>
		                    <col width="100px" />
	                        <col width="120px" />
	                        <col width="100px" />
	                        <col width="120px" />
	                        <col width=";"/>
		                </colgroup>
		                <tbody>
		                    <tr>
		                        <th scope="row"><spring:message code="USER, ID"/></th>
		                        <td><input type="text" name="USER_ID" value="${USER_ID}" size="10"  class="easyui-validatebox" search="MMA009_01.control.selectMainList" id="USER_ID" /></td>
		                        <th scope="row"><spring:message code="USER, NAME"/></th>
		                        <td><input type="text" name="NAME" value="" size="15" class="easyui-validatebox"  search="MMA009_01.control.selectMainList" id="NAME" /></td>
		                        <td class="txt_R">
		                           <a href="javascript:MMA009_01.control.selectMainList();" class="easyui-linkbutton pop_sch" id="MMA009_01_searchBtn"><spring:message code="SRCH"/></a>
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
	                <p class="h2"><spring:message code="USER, SELET"/></p>
	                <p class="h2_btn">
	                    <a href="javascript:MMA009_01.control.selectRowValue();" class="btnPopTick"><spring:message code="SELET"/></a>
	                </p>
	            </div>
	            <div data-options="region:'center',border:false">
	               <table id="MMA009_01_grid_01"> </table>
	            </div>
	        </div>
        </div>
	    <!-- //검색조건 -->
	</div>
	
	<script type="text/javascript" src="/js/views/MM/POP/MM-A009_01.js"></script>
	<script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body> 
</html>