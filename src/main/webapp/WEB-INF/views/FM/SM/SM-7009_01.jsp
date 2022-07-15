<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : SM-7009_01.jsp
  * @Description : 메뉴 조회
  * @Modification Information
  *
  * author sbj1000
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "SM7009_01_dailog_01"으로 하셔야 합니다.
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
    		    <form id="SM7009_01_form_01" method="POST">
                    <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
                    <input type="hidden" id="SESSION_COMPANY_CD" name="SESSION_COMPANY_CD" value="${_MEMBER.SESSION_COMPANY_CD}"/>
           			<input type="hidden" id="SESSION_USER_ID" name="SESSION_USER_ID" value="${_MEMBER.SESSION_USER_ID}"/>
           			<input type="hidden" id="SESSION_DEFAULT_LANGUAGE" name="SESSION_DEFAULT_LANGUAGE" value="${_MEMBER.SESSION_DEFAULT_LANGUAGE}"/>
           			<input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${_MEMBER.SESSION_COMPANY_CD}"/>
           			<input type="hidden" id="I_BUKRS" name="I_BUKRS" value="${_MEMBER.SESSION_COMPANY_CD}"/>
           			<input type="hidden" id="USER_ID" name="USER_ID" value="testUser"/>
           			<input type="hidden" id="FILE_TYPE" name="FILE_TYPE" value="I"/>
           			<input type="hidden" id="ITEM_TYPE" name="ITEM_TYPE" value=""/>
           			<input type="hidden" id="IF_CODE" name ="IF_CODE" value=""/>
           			<input type="hidden" id="VENDOR_CD" name="VENDOR_CD" value=""/>
    				<table>
    					<caption>Menu Search</caption>
    					<colgroup>
    						<col width="100px" />
    						<col width="110px" />
    						<col width="130px" />
    						<col width="180px" />
    						<col width="100px" />
                            <col width="90px" />
    						<col width="130px" />
    						<col width="410px" />
    						<col width=";"/>
    					</colgroup>
    					<tbody>
    						<tr>
    							<th scope="row"><spring:message code="SYS, ID"/></th>
    							<td>
    							    <input type="text" id="SYS_ID" name="SYS_ID" style="width:100px"/>
    							</td>
    							<th scope="row"><spring:message code="TOP, MENU, NAME"/></th>
    							<td>
    							    <input type="text" id="HGRNK_MENU_ID" name="HGRNK_MENU_ID" style="width:170px"/>
    							</td>
    							<th scope="row"><spring:message code="TXT_USE_YN"/></th>
                                <td>
                                    <input type="text" id="USE_YN" name="USE_YN" style="width:80px"/>
                                </td>
    							<th scope="row"><spring:message code="CNDIT,SERCH"/></th>
	                            <td>
	                                <input type="text" id="schKeyField" name="schKeyField" style="width:100px"/> 
	                                <input type="text" id="schKeyWord" name="schKeyWord" class="easyui-validatebox" search="SM7009_01.control.selectMenuList" style="width:170px;paddig:0;"/>
	                                <input type="text" id="schKeyLike" name="schKeyLike" style="width:80px"/>
	                            </td>
    							<td class="txt_R">
    	                           <a href="javascript:SM7009_01.control.selectMenuList();" class="easyui-linkbutton pop_sch" id="SM7009_01_searchBtn"><spring:message code="SRCH"/><%--조회--%></a>
    	                        </td>
    						</tr>
    					</tbody>
    				</table>
    			</form>
    		</div>
		</div>
        <!-- //검색조건 -->
        <div data-options="region:'center',border:false" style="padding-top:12px;">
        	<div class="easyui-layout" data-options="fit:true">
	            <div data-options="region:'center',border:false">
	                <div class="easyui-layout" data-options="fit:true">
                        <div data-options="region:'north',border:false" class="h2_etc">
                            <p class="h2"><spring:message code="SM7009_01"/></p>
        	                <p class="h2_btn">
        	                    <a href="javascript:SM7009_01.dialog.openDialog_1('insert');" class="btnNewRegiste"><spring:message code="REGER"/><%--등록--%></a>
                                <a href="javascript:SM7009_01.dialog.openDialog_1('update');" class="btnEdit"><spring:message code="MOD"/><%--수정--%></a>
        	                </p>
        	            </div>
        	            <div data-options="region:'center',border:false">
        	               <table id="SM7009_01_grid_01"> </table>
        	            </div>
                     </div>
                </div>
	            <div data-options="region:'east',border:false,split:true" style="width:485px;">
                	<div class="easyui-layout" data-options="fit:true">
                        <div data-options="region:'north',border:false" class="h2_etc">
        	                <p class="h2"><spring:message code="MENU, LANG"/></p>
        	                <p class="h2_btn">
        	                    <a href="javascript:SM7009_01.dialog.openDialog_2('insert');" class="btnNewRegiste"><spring:message code="REGER"/><%--등록--%></a>
                                <a href="javascript:SM7009_01.dialog.openDialog_2('update');" class="btnEdit"><spring:message code="MOD"/><%--수정--%></a>
        	                </p>
        	            </div>
        	            <div data-options="region:'center',border:false">
        	               <table id="SM7009_01_grid_02"></table>
        	            </div>
                   </div>
           	    </div>

	        </div>
        </div>
	</div>
	
	<script type="text/javascript" src="/js/views/FM/SM/SM-7009_01.js"></script>
	<script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body> 
</html>