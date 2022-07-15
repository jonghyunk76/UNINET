<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : MM-A015_02.jsp
  * @Description : 기관발급용 표준코드 선택 팝업
  * @Modification Information
  *
  * author RHN
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMA015_02_01_dailog_01"으로 하셔야 합니다.
  *
  *
  **************************** 팝업 호출시 필수 파라미터 ID명
  * 부모창ID               : TARGET_PID
  * 회사코드               : COMPANY_CD
  * 공통코드T 카테고리코드 : CATEGORY_CD
  *
  **************************** 팝업 리턴값
  * 코드         : CODE
  * 코드명(한글) : CODE_NAME
  * 코드명(영문) : CODE_NAME_ENG
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
    		    <form id="MMA015_02_form_01" method="POST">
                    <input type="hidden" id="TARGET_PID"  name="TARGET_PID"  value="${TARGET_PID}"/>
                    <input type="hidden" id="CATEGORY_CD" name="CATEGORY_CD" value="${CATEGORY_CD}"/>
                    <input type="hidden" id="COMPANY_CD"  name="COMPANY_CD"  value="${COMPANY_CD}"/>
    				<table>
    					<caption>데이타 검색조건 설정</caption>
    					<colgroup>
    						<col width="80px" />
    						<col width="100px" />
    						<col width="80px" />
    						<col width="150px" />
    						<col width="80px" />
                            <col width="170px" />
                            <col width=";"/>
    					</colgroup>
    					<tbody>
    						<tr>
    							<th scope="row"><spring:message code="CODE, ID"/><%--코드 ID--%></th>
    							<td><input type="text" name="CODE" id="CODE" search="MMA015_02.control.selectKcsStandardCodeList" style="width:90px"/></td>
    							<th scope="row"><spring:message code="CODE,NAME"/><%--코드 명--%></th>
    							<td><input type="text" name="CODE_NAME" id="CODE_NAME" search="MMA015_02.control.selectKcsStandardCodeList" style="width:140px"/></td>
    							<th scope="row"><spring:message code="COTRY,NAME"/><%--국가 명--%></th>
                                <td><input type="text" name="NATION_NAME" id="NATION_NAME" search="MMA015_02.control.selectKcsStandardCodeList" style="width:140px"/></td>
                                <td class="txt_R">
    	                           <a href="javascript:MMA015_02.control.selectKcsStandardCodeList();" class="easyui-linkbutton pop_sch" id="MMA015_02_searchBtn"><spring:message code="SRCH"/><%--조회--%></a>
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
	                    <a href="javascript:MMA015_02.control.selectRowValue();" class="btnPopTick"><spring:message code="SELET"/><%--선택--%></a>
	                </p>
	            </div>
	            <div data-options="region:'center',border:false">
	               <table id="MMA015_02_grid_01"> </table>
	            </div>
	        </div>
        </div>
	</div>
	
	<script type="text/javascript" src="/js/views/MM/POP/MM-A015_02.js"></script>
	<script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body> 
</html>