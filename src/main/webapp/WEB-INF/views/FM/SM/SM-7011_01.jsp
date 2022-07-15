<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : SM-7011_01.jsp
  * @Description : 공지사항 조회
  * @Modification Information
  *
  * author sbj1000
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "SM7011_01_dailog_01"으로 하셔야 합니다.
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
	            <form id="SM7011_01_form_01" method="POST">
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
	                    <caption>데이타 검색조건 설정</caption>
	                    <colgroup>
	                        <col width="120px" />
	                        <col width="110px" />
	                        <col width="120px" />
	                        <col width=";" />
	                    </colgroup>
	                    <tbody>
	                        <tr>
	                            <th scope="row"><spring:message code="POST, ORNOT"/></th>
                                <td>
                                    <input type="text" id="USE_YN" name="USE_YN" style="width:100px;"/>
                                </td>
	                            <th scope="row"><spring:message code="TITLE"/></th>
	                            <td>
	                            	<input type="text" id="SUBJECT" name="SUBJECT" value="${SUBJECT}" class="easyui-validatebox" size="50" search="SM7011_01.control.selectNoticeList" title="<spring:message code="SUBJECT"/>" />
	                            </td>
	                            <td class="txt_R">
	                            	<a href="javascript:SM7011_01.control.selectNoticeList();" class="easyui-linkbutton pop_sch" id="SM7011_01_searchBtn">
	                            		<spring:message code="SRCH"/>
	                            	</a>
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
	            	<p class="h2"><spring:message code="SM7011_01"/><%--공지사항--%></p>
					<p class="h2_btn">
                    	<a href="javascript:SM7011_01.dialog.openDialog_1('insert');" class="btnNewRegiste"><spring:message code="REGER"/></a>
                      	<a href="javascript:SM7011_01.dialog.openDialog_1('update');" class="btnEdit"><spring:message code="MOD"/></a>
					</p>	            
		        </div>
	            <div data-options="region:'center',border:false">
	               <table id="SM7011_01_grid_01"> </table>
	            </div>
	        </div>
        </div>
        
	</div>
	
	<script type="text/javascript" src="/js/views/FM/SM/SM-7011_01.js"></script>
	<script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body> 
</html>