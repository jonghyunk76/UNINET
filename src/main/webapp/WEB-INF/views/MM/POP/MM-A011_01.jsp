<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : MM-A011_01.jsp
  * @Description : HS CODE 조회 및 선택
  * @Modification Information
  *
  * author 이재욱
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMA011_01_dailog_01"으로 하셔야 합니다.
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
    		    <form id="MMA011_01_form_01" method="POST">
                    <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
		            <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${_MEMBER.SESSION_COMPANY_CD}"/>
		            <input type="hidden" id="USER_ID" name="USER_ID" value="${_MEMBER.SESSION_USER_ID}"/>
    				<table>
    					<caption>데이타 검색조건 설정</caption>
    					<colgroup>
	                        <col width="80px" />
	                        <col width="100px" />
	                        <col width="100px" />
	                        <col width="120px" />
	                        <col width=";"/>
    					</colgroup>
    					<tbody>
    						<tr>
    							<th scope="row" class="point"><spring:message code="TXT_HS_CODE"/></th>
    							<td><input type="text" name="HS_CODE" value="${HS_CODE}" class="easyui-validatebox"   search="MMA011_01.control.selectMainList" size="12" id="HS_CODE" />
    							<th scope="row" class="point"><spring:message code="TXT_HS_CODE_NAME"/></th>
    							<td><input type="text" name="HS_CODE_NAME" value="${HS_CODE_NAME}" class="easyui-validatebox" search="MMA011_01.control.selectMainList" size="15" id="HS_CODE_NAME" />
	                            <td class="txt_R">
	                               <a href="javascript:MMA011_01.control.selectMainList();" class="easyui-linkbutton pop_sch" id="MMA011_01_searchBtn">조회</a>
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
	                <p class="h2_btn">
	                    <a href="javascript:MMA011_01.control.selectRowValue();" class="btnPopTick"><spring:message code="SELET"/></a>
	                </p>
	            </div>
	            <div data-options="region:'center',border:false">
	               <table id="MMA011_01_grid_01"> </table>
	            </div>
	        </div>
        </div>
	</div>
	
	<script type="text/javascript" src="/js/views/MM/POP/MM-A011_01.js"></script>
	<script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body> 
</html>