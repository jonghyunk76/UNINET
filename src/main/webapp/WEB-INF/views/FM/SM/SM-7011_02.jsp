<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : SM-7011_02.jsp
  * @Description : 공지사항 상세정보 조회
  * @Modification Information
  *
  * author sbj1000
  *
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "SM7011_02_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body class="h2_etc">
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'north',border:false" class="sec_tit" style="overflow:hidden;">
        <div id="SM7011_02_div_01" class="btn_R">
            <a href="javascript:SM7011_02.control.updateNoticeInfo();" id="SM7011_02_btn_01" class="btnSave"><%--저장--%><spring:message code="SAVE"/></a>
            <a href="javascript:SM7011_02.control.deleteNoticeInfo();" id="SM7011_02_btn_02" class="btnDelete" style="display:none;"><spring:message code="DEL"/></a>
            <a href="javascript:dialog.handle.close(dialog.getObject('SM7011_02_dailog_01'));" id="SM7011_02_btn_03" class="btnPopClose" style="display:none;"><spring:message code="CLOSE"/></a>
        </div>
    </div>
    <div data-options="region:'center',border:false">
        <form id="SM7011_02_form_01" name="SM7011_02_form_01" enctype="multipart/form-data" method="POST">
            <input type="hidden" id="flag" name="flag" value="${flag}"/>
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
            <input type="hidden" id="NOTICE_FILE_NAME" name="NOTICE_FILE_NAME" value="${NOTICE_FILE_NAME}"/>
            <input type="hidden" id="NOTICE_NO" name ="NOTICE_NO" value="${NOTICE_NO}">
            <p class="h2"><spring:message code="NOTIC"/><%--공지사항--%></p>
            <table class="dataT" style="margin-bottom:0px;">
                <caption>데이타 테이블</caption>
                <colgroup>
                    <col width="120px" />
                    <col width=";" />
                    <col width="120px" />
                    <col width="110px;" />
                    <col width="120px" />
                    <col width="110px;" />
                </colgroup>
                <tbody>
                    <tr>
                        <th scope="row" class="point2"><spring:message code="TITLE"/><%--제목--%></th>
                        <td colspan="5">
                            <input type="text" id="SUBJECT" name="SUBJECT" value="${SUBJECT}"
                                   class="easyui-validatebox" required="true" style="width:99%;"
                                   title="<spring:message code="TITLE"/>" />
                        </td>
                    </tr>
                    <tr id="SM7011_02_tr_01">
                        <th scope="row"><spring:message code="TXT_BBS_TYPE"/><%--게시유형--%></th>
                        <td>
                            <input type="text" id="BBS_TYPE" name ="BBS_TYPE" value="${BBS_TYPE}" class="easyui-validatebox" style="width:180px;"/>
                        </td>
                        <th scope="row" class="point2"><spring:message code="TXT_START_DATE"/><%--게시시작일--%></th>
                        <td>
                            <input type="text" id="START_DATE" name="START_DATE" value="${START_DATE}" class="easyui-validatebox" style="width:95px;"/>
                        </td>
                        <th scope="row" class="point2"><spring:message code="TXT_BBS_END_DATE"/><%--게시종료일--%></th>
                        <td >
                            <input type="text" id="END_DATE" name ="END_DATE" value="${END_DATE}" class="easyui-validatebox" style="width:95px;"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row"><spring:message code="CONTS"/><%--내용--%></th>
                        <td colspan="5">
                            <textarea name="CONTENT1" id="CONTENT1" value="${CONTENT1}" class="easyui-validatebox" style="height:280px;width:99%;"></textarea>
                        </td>
                    </tr>
                    <tr id="SM7011_02_tr_02">
                        <th scope="row"><spring:message code="FILE"/><%--파일 업로드--%></th>
                        <td colspan="5">
	                        <input type="file" name="NOTICE_FILE" id="NOTICE_FILE" title="<spring:message code="FILE"/>"/>
                        </td>
                    </tr>
                    <tr id="SM7011_02_tr_03" style="display:none;">
                        <th scope="row" style="height:18px"><spring:message code="ATACH, FILE"/><%--파일명--%></th>
                        <td colspan="5">
                            <img src="\images\icon\btn_file.gif" id ="img_btn" style="display:none;">
                            <a href="/fm/sm/sm7011_02/selectNoticeFile/${_MEMBER.SESSION_COMPANY_CD}&${NOTICE_NO}">
                            <span id = "SM7011_02_span_01"></span></a>
                        </td>
                    </tr>
                </tbody>
            </table>
        </form>
    </div>
    <script type="text/javascript" src="/js/views/FM/SM/SM-7011_02.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>
</div>
</body>
</html>