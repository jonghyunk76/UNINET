<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : SM-7004_03.jsp
  * @Description : 스케쥴 인터페이스 항목 관리
  * @Modification Information
  *
  * author carlos
  *
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "SM7004_03_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body class="h2_etc">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center',border:false" style="overflow:hidden;">
        
            <div id="SM7004_03_div_01" class="btn_R">
                <a href="javascript:SM7004_03.control.updateScheduleMappingInfo();" id="SM7004_03_btn_01" class="btnSave"><spring:message code="SAVE"/></a>
                <a href="javascript:SM7004_03.control.deleteScheduleMappingInfo();" id="SM7004_03_btn_02" class="btnDelete"><spring:message code="DEL"/></a>
            </div>
            <form id="SM7004_03_form_01" name="SM7004_03_form_01" method="post">
                <input type="hidden" id="flag" name="flag" value="${flag}"/>
                <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
                <input type="hidden" id="SESSION_COMPANY_CD" name="SESSION_COMPANY_CD" value="${SESSION_COMPANY_CD}"/>
                <input type="hidden" id="SESSION_USER_ID" name="SESSION_USER_ID" value="${SESSION_USER_ID}"/>
                <input type="hidden" id="DEFAULT_LANGUAGE" name="DEFAULT_LANGUAGE" value="${DEFAULT_LANGUAGE}"/>
                <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
                <input type="hidden" id="CREATE_BY" name="CREATE_BY" value="${SESSION_USER_ID}"/>
                <input type="hidden" id="CREATE_BY" name="UPDATE_BY" value="${SESSION_USER_ID}"/>
                <input type="hidden" id="INTERFACE_SCHEDULE_MAPPING_ID" name="INTERFACE_SCHEDULE_MAPPING_ID" value="${INTERFACE_SCHEDULE_MAPPING_ID}"/>
                <input type="hidden" id="SCHEDULE_CD" name="SCHEDULE_CD" value="${SCHEDULE_CD}"/>
                <input type="hidden" id="SCHEDULE_SEQ" name="SCHEDULE_SEQ" value="${SCHEDULE_SEQ}"/>
                <p class="h2"><spring:message code="DETIL,INFMT"/><%--상세정보--%></p>
                <table class="dataT">
                    <caption><spring:message code="DTA, TB"/><%--데이타 테이블--%></caption>
                    <colgroup>
                        <col width="150px" />
                        <col width="" />
                    </colgroup>
                    <tbody>
                        <tr>
                            <th scope="row">
                                <spring:message code="SCHE, NAME"/><%--스케줄 명--%>
                            </th>
                            <td>
                                <input type="text" name="SCHEDULE_NAME" id="SCHEDULE_NAME" value="${SCHEDULE_NAME}" size="30"
                                       title="<spring:message code="SCHE, NAME"/>" />
                            </td>
                        </tr>
                    </tbody>
                </table>
                <p class="h2"><spring:message code="TXT_BATCH, ITEMS, REGER"/><%--배치 항목등록--%></p>
                <table class="dataT">
                    <caption><spring:message code="DTA, TB"/><%--데이타 테이블--%></caption>
                    <colgroup>
                        <col width="150px" />
                        <col width="" />
                        <col width="150px" />
                        <col width="" />
                    </colgroup>
                    <tbody>
                        <tr>
                            <th scope="row">
                                <spring:message code="INF, ITEMS"/><%--인터페이스 항목--%>
                            </th>
                            <td colspan="3">
                                <input type="text" name="IF_CD" id="IF_CD" value="${IF_CD}" 
                                       class="easyui-validatebox" validType="['maxlength[50]']" size="50"/>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row"><spring:message code="PARNT, INF, ITEMS"/><%--상위 인터페이스 항목--%></th>
                            <td colspan="3">
                                <input type="text" name="IF_PARENT_CD" id="IF_PARENT_CD" value="${IF_PARENT_CD}" 
                                       class="easyui-validatebox" validType="['maxlength[50]']" size="50"/>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row""><spring:message code="INF, METHD"/><%--인터페이스 방법--%></th>
                            <td colspan="3">
                                <input type="text" name="IF_METHOD" id="IF_METHOD" value="${IF_METHOD}" size="30"/>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row"><spring:message code="REQUD,ORNOT"/><%--필수여부--%></th>
                            <td>
                                <input type="text" name="REQUIRED_YN" id="REQUIRED_YN" value="${REQUIRED_YN}" size="15"/>
                            </td>
                            <th scope="row"><spring:message code="EFTIV,CHECK"/><%--유효성 체크--%></th>
                            <td>
                                <input type="text" name="VALID_CHECK_YN" id="VALID_CHECK_YN" value="${VALID_CHECK_YN}" size="15"/>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row"><spring:message code="EXCUS,SEQ"/><%--실행순서--%></th>
                            <td colspan="3">
                                <input type="text" name="SCHEDULE_SEQ_NEW" id="SCHEDULE_SEQ_NEW" value="${SCHEDULE_SEQ}" class="easyui-validatebox" required="true" size="15"/>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
        
    </div>
    
    <script type="text/javascript" src="/js/views/FM/SM/SM-7004_03.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>