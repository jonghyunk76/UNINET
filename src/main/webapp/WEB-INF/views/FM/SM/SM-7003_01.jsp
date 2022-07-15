<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : SM-7003_01.jsp
  * @Description : 사용자 리스트 조회
  * @Modification Information
  *
  * author carlos
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "SM7003_01_dailog_01"으로 하셔야 합니다.
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
                <form id="SM7003_01_form_01" name="SM7003_01_form_01" method="post">
                    <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
                    <input type="hidden" id="SESSION_COMPANY_CD" name="SESSION_COMPANY_CD" value="${_MEMBER.SESSION_COMPANY_CD}"/>
                    <input type="hidden" id="SESSION_USER_ID" name="SESSION_USER_ID" value="${_MEMBER.SESSION_USER_ID}"/>
                    <input type="hidden" id="DEFAULT_LANGUAGE" name="DEFAULT_LANGUAGE" value="${_MEMBER.SESSION_DEFAULT_LANGUAGE}"/>
                    <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${_MEMBER.SESSION_COMPANY_CD}"/>
                    <table>
                        <caption><spring:message code="TXT_DATA_SEARCH_CONDITION"/><%--데이타 검색조건 설정--%></caption>
                        <colgroup>
                            <col width="120px"/>
                            <col width="420px"/>
                            <col width=";"/>
                        </colgroup>
                        <tbody>
                            <tr>
                                <th scope="row"><spring:message code="SRCH,CNDIT"/><%--조회조건--%></th>
                                <td>
                                    <input type="text" id="schKeyField" name="schKeyField" style="width:130px;"/> 
                                    <input type="text" id="schKeyWord" name="schKeyWord" class="easyui-validatebox" search="SM7003_01.control.selectUserList" style="width:150px;"/>
                                    <input type="text" id="schKeyLike" name="schKeyLike" style="width:90px;"/>
                                </td>
                                <td class="txt_R">
                                    <a href="javascript:SM7003_01.control.selectUserList();" 
                                       class="easyui-linkbutton pop_sch" id="SM7003_01_searchBtn"><spring:message code="SRCH"/><%--조회--%></a>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
        
        <!-- //검색조건 -->
        <div data-options="region:'center',border:false" style="padding-top:12px;">
            <!-- sec_area -->
            <div class="easyui-layout" data-options="fit:true" class="sec_area">
                <!-- sec_tit -->
                <div data-options="region:'north',border:false" class="sec_tit">
    	            <div class="h2_etc">
                       <p class="h2"><spring:message code="SM7003_01"/><%--사용자 리스트--%></p>
                       <p class="h2_btn">
                           <a href="javascript:SM7003_01.dialog.openDialog('view');" class="btnSelectClick"><spring:message code="DETIL,VIWER"/><%--상세보기--%></a>
                           <a href="javascript:SM7003_01.dialog.openDialog('insert');" class="btnNewRegiste"><spring:message code="REGER"/><%--등록--%></a>
                           <a href="javascript:SM7003_01.dialog.openDialog('update');" class="btnEdit"><spring:message code="MOD"/><%--수정--%></a>
                           <a href="javascript:SM7003_01.excel.excelDownload_1();" class="btnExlDown"><spring:message code="EXCEL, DOWN"/><%--엑셀다운로드--%></a>
                       </p>
                   </div>
    	        </div>
                <!-- //sec_tit -->
    			<div data-options="region:'center',border:false" style="padding-top:0px;">
    			    <table id="SM7003_01_grid_01"></table>
    			</div>
    		</div>
            <!-- //sec_area -->
    	</div>
    </div>
    
    <script type="text/javascript" src="/js/views/FM/SM/SM-7003_01.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body> 
</html>