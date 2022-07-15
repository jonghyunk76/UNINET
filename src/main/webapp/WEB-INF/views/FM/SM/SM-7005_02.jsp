<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : SM-7005_02.jsp
  * @Description : 인터페이스 항목(테이블) 상세 조회 및 관리
  * @Modification Information
  *
  * author carlos
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "SM7005_02_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body class="h2_etc">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'north',border:false" style="border:0px solid #000;overflow:hidden;">
            <div id="SM7005_02_div_01" class="btn_R">
                <a href="javascript:SM7005_02.control.updateInterfaceItemMstInfo();" id="SM7005_02_btn_01" class="btnSave"><spring:message code="SAVE"/></a>
                <a href="javascript:SM7005_02.control.deleteInterfaceItemMstInfo();" id="SM7005_02_btn_02" class="btnDelete"><spring:message code="DEL"/></a>
            </div>
            <form id="SM7005_02_form_01" name="SM7005_02_form_01" method="post">
                <input type="hidden" id="flag" name="flag" value="${flag}"/>
                <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
                <input type="hidden" id="SESSION_COMPANY_CD" name="SESSION_COMPANY_CD" value="${SESSION_COMPANY_CD}"/>
                <input type="hidden" id="SESSION_USER_ID" name="SESSION_USER_ID" value="${SESSION_USER_ID}"/>
                <input type="hidden" id="DEFAULT_LANGUAGE" name="DEFAULT_LANGUAGE" value="${DEFAULT_LANGUAGE}"/>
                <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
                <input type="hidden" id="CREATE_BY" name="CREATE_BY" value="${SESSION_USER_ID}"/>
                <input type="hidden" id="CREATE_BY" name="UPDATE_BY" value="${SESSION_USER_ID}"/>
                <input type="hidden" id="INTERFACE_ITEM_MST_ID" name="INTERFACE_ITEM_MST_ID" value="${INTERFACE_ITEM_MST_ID}"/>
                <input type="hidden" id="ITEM_TYPE" name="ITEM_TYPE" value="${ITEM_TYPE}"/><%--T:TABLE, P:PROCEDURE, C:URL--%>
                <input type="hidden" id="FILE_STATUS" name="FILE_STATUS" value="2"/>
                <input type="hidden" id="gridData" name="gridData"/>
                <p class="h2"><spring:message code="DETIL,INFMT"/><%--상세정보--%></p>
                <table class="dataT">
                    <caption><spring:message code="DTA, TB"/><%--데이타 테이블--%></caption>
                    <colgroup>
                        <col width="180px" />
                        <col width=";" />
                        <col width="180px" />
                        <col width=";" />
                    </colgroup>
                    <tbody>
                        <tr>
                            <th scope="row" class="point2">
                                <spring:message code="INF, CODE"/><%--인터페이스 코드--%>
                            </th>
                            <td>
                                <input type="text" name="IF_CD" id="IF_CD" value="${IF_CD}" 
                                       class="easyui-validatebox" required="true" validType="['maxlength[50]']" style="width:98%;"
                                       title="<spring:message code="INF, CODE"/>" />
                            </td>
                            <th scope="row" class="point2">
                                <spring:message code="INF, NAME"/><%--INF, NAME--%>
                            </th>
                            <td>
                                <input type="text" name="IF_NAME" id="IF_NAME" value="${IF_NAME}" 
                                       class="easyui-validatebox" required="true" validType="['maxlength[200]']" style="width:98%;"
                                       title="<spring:message code="INF, NAME"/>" />
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">
                                <spring:message code="ORIGIN,TB"/><%--원천테이블--%>
                            </th>
                            <td>
                                <input type="text" name="SOURCE_TABLE" id="SOURCE_TABLE" value="${SOURCE_TABLE}" 
                                       class="easyui-validatebox" validType="['maxlength[30]']" style="width:98%;"
                                       title="<spring:message code="ORIGIN,TB"/>" />
                            </td>
                            <th scope="row">
                                <spring:message code="TARGT,TB"/><%--대상테이블--%>
                            </th>
                            <td>
                                <input type="text" name="TARGET_TABLE" id="TARGET_TABLE" value="${TARGET_TABLE}" 
                                       class="easyui-validatebox" validType="['maxlength[30]']" style="width:98%;"
                                       title="<spring:message code="TARGT,TB"/>" />
                            </td>
                        </tr>
                        <tr>
                            <th scope="row" class="point2">
                                <spring:message code="RECOD,TB"/><%--이력테이블--%>
                            </th>
                            <td>
                                <input type="text" name="HISTORY_TABLE" id="HISTORY_TABLE" value="${HISTORY_TABLE}" 
                                       class="easyui-validatebox" required="true" validType="['maxlength[30]']" style="width:98%;"
                                       title="<spring:message code="RECOD,TB"/>" />
                            </td>
                            <th scope="row" class="point2">
                                <spring:message code="DATA,TYPE"/><%--데이타 타입--%>
                            </th>
                            <td>
                                <input name="FILE_TYPE" id="FILE_TYPE" value="${FILE_TYPE}" style="width:100px;"/>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">
                                <spring:message code="SDREV, FILE,ID"/>(XML)<%--파일 ID--%>
                            </th>
                            <td>
                                <input type="text" name="FILE_NAME" id="FILE_NAME" value="${FILE_NAME}" style="width:98%;"/>
                            </td>
                            <th scope="row" class="point2">
                                <spring:message code="USE,ORNOT"/><%--사용여부--%>
                            </th>
                            <td>
                                <input name="USING_YN" id="USING_YN" value="${USING_YN}" style="width:100px;"/>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">
                                <spring:message code="SDREV, REQD,PATH"/>(URL)<%--요청경로--%>
                            </th>
                            <td colspan="3">
                                <input type="text" name="FILE_PATH" id="FILE_PATH" value="${FILE_PATH}" style="width:98%;"/>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">
                                <spring:message code="INF, DSCPT"/><%--인터페이스 설명--%>
                            </th>
                            <td colspan="3">
                                <textarea name="REMARK" id="REMARK" cols="10" rows="5" 
                                          class="input_red easyui-validatebox" validType="['maxlength[2000]']" style="width:99%">${REMARK}</textarea>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
            <div style="display:none">
	            <form id="SM7005_02_form_00" name="SM7005_02_form_00" method="post">
	                <textarea name="PASTE_TEXT1" id="PASTE_TEXT1" style="width:100%;height:100px;"></textarea>
	            </form>
	        </div>
        </div>
        <div data-options="region:'center',border:false" style="padding-top:6px;">
            <div class="easyui-layout" data-options="fit:true">
                <div data-options="region:'north',border:false" class="h2_etc">
                    <p class="h2"><spring:message code="INF, ITEMS, LIST"/><%--인터페이스 항목 리스트--%></p>
                    <p class="h2_btn">
                        <a href="javascript:SM7005_02.control.pasteColumn();" id="SM7005_02_btn_07" class="btnTextPaste"><spring:message code="CLIB_PASTE"/></a>
                        <a href="javascript:SM7005_02.dialog.openDialog('view');" id="SM7005_02_btn_05" class="btnSelectClick" style="display:none"><spring:message code="DETIL,VIWER"/><%--상세보기--%></a>
                        <a href="javascript:SM7005_02.dialog.openDialog('insert');" id="SM7005_02_btn_03" class="btnNewRegiste"><spring:message code="ADDTL"/><%--등록--%></a>
                        <a href="javascript:SM7005_02.dialog.openDialog('update');" id="SM7005_02_btn_04" class="btnEdit"><spring:message code="MOD"/><%--수정--%></a>
                        <a href="javascript:SM7005_02.control.insertInterfaceItemDtl();" id="SM7005_02_btn_08" class="btnMainSave" style="display:none"><spring:message code="SAVE"/><%--저장--%></a>
                        <a href="javascript:SM7005_02.excel.excelDownload_1();" id="SM7005_02_btn_06" class="btnExlDown"><spring:message code="EXCEL, DOWN"/><%--엑셀다운로드--%></a>   
                    </p>
                </div>
                <div data-options="region:'center',border:false">
                   <table id="SM7005_02_grid_01"></table>
                </div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/FM/SM/SM-7005_02.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>