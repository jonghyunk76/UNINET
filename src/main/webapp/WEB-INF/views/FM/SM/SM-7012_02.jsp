<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : SM-7012_02.jsp
  * @Description : 게시물 상세 및 등록
  * @Modification Information
  *
  * author YNI-Maker(이메일)
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "SM7012_02_dialog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>      
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center',border:false" style="overflow:hidden;">
            <div class="btn_R">
                <a href="javascript:SM7012_02.control.insertReplyInfo('reply');" id="SM7012_02_btn_01" class="btnSave" style="display:none;"><%--답변--%><spring:message code="REPLY"/></a>
                <a href="javascript:SM7012_02.control.updateQnAInfo();" id="SM7012_02_btn_02" class="btnSave"><%--저장--%><spring:message code="SAVE"/></a>
                <a href="javascript:SM7012_02.control.deleteQnAInfo();" id="SM7012_02_btn_03" class="btnDelete" style="display:none;"><spring:message code="DEL"/></a>
                <a href="javascript:dialog.handle.close(dialog.getObject('SM7012_02_dialog_01'));" class="btnPopClose"><spring:message code="CLOSE"/></a>
            </div>
            <form id="SM7012_02_form_01" name="SM7012_02_form_01" enctype="multipart/form-data" method="POST">
                <input type="hidden" id="flag" name="flag" value="${flag}"/>
                <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
                <input type="hidden" id="SESSION_COMPANY_CODE" name="SESSION_COMPANY_CODE" value="${_MEMBER.SESSION_COMPANY_CD}"/>
                <input type="hidden" id="SESSION_USER_ID" name="SESSION_USER_ID" value="${_MEMBER.SESSION_USER_ID}"/>
                <input type="hidden" id="USER_ID" name="USER_ID" value="${USER_ID}"/>
                <input type="hidden" id="BOARD_TYPE" name="BOARD_TYPE" value="Q"/>
                <input type="hidden" id="FILE_SIZE" name="FILE_SIZE"/>
                <input type="hidden" id="BOARD_ID" name="BOARD_ID" value="${BOARD_ID}"/>
                <input type="hidden" id="PARENT_BOARD_ID" name="PARENT_BOARD_ID" value="${PARENT_BOARD_ID}"/>
                <input type="hidden" id="BOARD_FILE_ID" name="BOARD_FILE_ID" value="${BOARD_FILE_ID}"/>
                <input type="hidden" id="FILE_COUNT" name="FILE_COUNT" value="${FILE_COUNT}"/>
                <input type="hidden" id="CREATE_BY" name="CREATE_BY" value="${CREATE_BY}"/>
                <input type="hidden" id="FILE_IN_FLAG" name="FILE_IN_FLAG" value="${FILE_IN_FLAG}"/>
                <p class="h2"><spring:message code="POST, CONTS"/><%--게시내용--%></p>
                <table class="dataT">
                    <caption>데이타 테이블</caption>
                    <colgroup>
                        <col width="120px" />
                        <col width=";" />
                        <col width="130px" />
                        <col width="150px;" />
                        <col width="120px" />
                        <col width="80px;" />
                    </colgroup>
                    <tbody>
                        <tr>
                            <th scope="row" class="point2"><spring:message code="TITLE"/><%--제목--%></th>
                            <td colspan="3">
                                <input type="text" id="SUBJECT" name="SUBJECT" value="${SUBJECT}" class="easyui-validatebox" required="true" style="width:98%;"/>
                            </td>
                            <th scope="row"><spring:message code="TXT_BBS_NO"/><%--게시번호--%></th>
                            <td>
                                <input type="text" id="BOARD_NO" name ="BOARD_NO" value="${BOARD_NO}" class="input_readOnly" readOnly style="width:96%;"/>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row"><spring:message code="WRTER"/><%-- 작성자 --%></th>
                            <td>
                                <input type="text" id="USER_NAME" name="USER_NAME" value="${USER_NAME}" class="input_readOnly" readOnly style="width:98%"/>
                            </td>
                            <th scope="row"><spring:message code="TXT_CREATE_DAY"/><%-- 등록일 --%></th>
                            <td colspan="3">
                                <input type="text" id="CREATE_DATE" name="CREATE_DATE" value="${CREATE_DATE}" class="input_readOnly" readOnly style="width:98%"/>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row"><spring:message code="CONTS"/><%--내용--%></th>
                            <td colspan="5">
                                <textarea name="CONTENT" id="CONTENT" style="width:99%;height:280px;">${CONTENT}</textarea>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row" rowspan="2">
                                <div class="flt_l"><spring:message code='FILE'/>(${FILE_COUNT})</div>
                            </th>
                            <td colspan="5" style="height:24px">
                                <div id="SM7012_02_files_div" class="file_box">
                                    <span id="SM7012_02_files_1" class="file_list" onclick="SM7012_02.tooltip.showTooltip();" style="width:23px;"></span>
                                    <input id="file_aliase" name="file_aliase" class="file_text easyui-filebox" style="width:480px;" readonly="readonly"/>
                                    <span class="file_wrap">
                                        <input type="file" id="FILEUP" name="FILEUP" class="file_add" onchange="javascript:SM7012_02.tooltip.setFiles(this);" multiple/>
                                    </span>
                                </div>
                            </td>
                        </tr>
                        <tr height="90px">
                            <td colspan="5">
                                <table id="SM7012_02_grid_01"></table>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
        <div style="display:none;">
            <form id="SM7012_02_form_02"  method="POST">
                <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
                <input type="hidden" id="BOARD_ID" name="BOARD_ID" value="${BOARD_ID}"/>
            </form>
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/FM/SM/SM-7012_02.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>