<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : SM-7004_02.jsp
  * @Description : 스케쥴 상세 조회 및 관리
  * @Modification Information
  *
  * author carlos
  *
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "SM7004_02_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body class="h2_etc">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'north',border:false,split:true" style="border:0px solid #000;overflow:hidden;">
            <div id="SM7004_02_div_01" class="btn_R">
                <a href="javascript:SM7004_02.control.updateScheduleInfo();" id="SM7004_02_btn_01" class="btnSave"><spring:message code="SAVE"/></a>
                <a href="javascript:SM7004_02.control.deleteScheduleInfo();" id="SM7004_02_btn_02" class="btnDelete"><spring:message code="DEL"/></a>
            </div>
            <form id="SM7004_02_form_01" name="SM7004_02_form_01" method="post">
                <input type="hidden" id="flag" name="flag" value="${flag}"/>
                <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
                <input type="hidden" id="SESSION_COMPANY_CD" name="SESSION_COMPANY_CD" value="${SESSION_COMPANY_CD}"/>
                <input type="hidden" id="SESSION_USER_ID" name="SESSION_USER_ID" value="${SESSION_USER_ID}"/>
                <input type="hidden" id="DEFAULT_LANGUAGE" name="DEFAULT_LANGUAGE" value="${DEFAULT_LANGUAGE}"/>
                <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
                <input type="hidden" id="CREATE_BY" name="CREATE_BY" value="${SESSION_USER_ID}"/>
                <input type="hidden" id="CREATE_BY" name="UPDATE_BY" value="${SESSION_USER_ID}"/>
                <input type="hidden" id="INTERFACE_SCHEDULE_ID" name="INTERFACE_SCHEDULE_ID" value="${INTERFACE_SCHEDULE_ID}"/>
                <input type="hidden" id="gridData" name="gridData"/>
                <input type="hidden" id="SYSTEM_ID" name="SYSTEM_ID"/>
                <p class="h2"><spring:message code="DETIL,INFMT"/><%--상세정보--%></p>
                <table class="dataT">
                    <caption><spring:message code="DTA, TB"/><%--데이타 테이블--%></caption>
                    <colgroup>
                        <col width="100px" />
                        <col width="80px" />
                        <col width="220px" />
                        <col width="100px" />
                        <col width="100px" />
                        <col width="" />
                    </colgroup>
                    <tbody>
                        <tr>
                            <th scope="row" class="point2" colspan="2">
                                <spring:message code="SCHE, CODE"/><%--스케줄 코드--%>
                            </th>
                            <td colspan="4">
                                <input type="text" name="SCHEDULE_CD" id="SCHEDULE_CD" value="${SCHEDULE_CD}" 
                                       class="easyui-validatebox" required="true" validType="['maxlength[30]']" style="width:99%"
                                       title="<spring:message code="SCHE, CODE"/>" />
                            </td>
                        </tr>
                        <tr>
                            <th scope="row" class="point2" colspan="2">
                                <spring:message code="SCHE, NAME"/><%--스케줄 명--%>
                            </th>
                            <td>
                                <input type="text" name="SCHEDULE_NAME" id="SCHEDULE_NAME" value="${SCHEDULE_NAME}" 
                                       class="easyui-validatebox" required="true" validType="['maxlength[100]']" style="width:98%"
                                       title="<spring:message code="SCHE, NAME"/>" />
                            </td>
                            <th scope="row" class="point2" colspan="2">
                                <spring:message code="PROGM, NAME"/><%--프로그램 명--%>
                            </th>
                            <td>
                                <input type="text" name="EXECUTION_PROGRAM" id="EXECUTION_PROGRAM" value="${EXECUTION_PROGRAM}" 
                                       class="easyui-validatebox" required="true" validType="['maxlength[100]']" style="width:98%"
                                       title="<spring:message code="PROGM, NAME"/>" />
                            </td>
                        </tr>
                        <tr>
                            <th scope="row" class="point2" colspan="2">
                                <spring:message code="TXT_APPLY_PERIOD"/><%--적용 기간--%>
                            </th>
                            <td>
                                <span id="SM7004_02_form_01_APPLY_FROM_DATE">
                                    <!-- 우측span은 calendar를 상세보기 시 이를 라벨로 보이게 함(formid+"_"+input id) -->
                                    <input type="text" name="APPLY_FROM_DATE" id="APPLY_FROM_DATE" value="${APPLY_FROM_DATE}" style="width:95px"/>
                                </span> ~
                                <span id="SM7004_02_form_01_APPLY_TO_DATE">
                                    <!-- 우측span은 calendar를 상세보기 시 이를 라벨로 보이게 함(formid+"_"+input id) -->
                                    <input type="text" name="APPLY_TO_DATE" id="APPLY_TO_DATE" value="${APPLY_TO_DATE}" style="width:95px"/>
                                </span>
                            </td>
                            <th scope="row" colspan="2">
                                <spring:message code="SYS, EXCUS,ORNOT"/><%--시스템 실행여부--%>
                            </th>
                            <td>
                                <input type="text" name="SYSTEM_BATCH_YN" id="SYSTEM_BATCH_YN" value="${SYSTEM_BATCH_YN}" size="15"/>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row" colspan="2">
                                <spring:message code="SCHE, DSCPT"/><%--스케줄 설명--%>
                            </th>
                            <td colspan="4">
                                <textarea name="SCHEDULE_DESC" id="SCHEDULE_DESC"
                                          class="input_red easyui-validatebox" validType="['maxlength[400]']" style="width:99%;height:68px;">${SCHEDULE_DESC}</textarea>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row" rowspan="4">
                                <spring:message code="MSG_BATCH_NON_START, CYCLE,<br>,INFMT"/><%--실행주기정보--%>
                            </th>
                            <th scope="row" class="th2">
                                <spring:message code="MONTH"/><%--월--%>
                            </th>
                            <td>
                                <input type="text" name="MONTH" id="MONTH" value="${MONTH}" 
                                       class="easyui-validatebox" validType="['maxlength[50]']" style="width:98%"
                                       title="<spring:message code="TXT_EXECUTE_CYCLE, MONTH"/>"/>
                            </td>
                            <th scope="row" rowspan="4">
                                <spring:message code="STNDD,CYCLE, INFMT"/><%--실행주기정보--%>
                            </th>
                            <th scope="row" class="th2">
                                <spring:message code="MONTH"/><%--월--%>
                            </th>
                            <td>
                                <input type="text" name="COM_MONTH" id="COM_MONTH" value="${COM_MONTH}" 
                                       class="easyui-validatebox" validType="['maxlength[50]']" style="width:80px;"
                                       title="<spring:message code="TXT_EXECUTE_CYCLE, MONTH"/>"/>
                                <spring:message code="MSG_CYCLE_MONTH_EX"/><%--ex) 1,2,3 (1월,2월,3월 실행)  ※ * (매월)--%>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row" class="th2">
                                <spring:message code="DAY"/><%--일--%>
                            </th>
                            <td>
                                <input type="text" name="DAY" id="DAY" value="${DAY}" 
                                       class="easyui-validatebox" validType="['maxlength[50]']" style="width:98%"
                                       title="<spring:message code="TXT_EXECUTE_CYCLE, DAY"/>"/>
                            </td>
                            <th scope="row" class="th2">
                                <spring:message code="DAY"/><%--일--%>
                            </th>
                            <td>
                                <input type="text" name="COM_DAY" id="COM_DAY" value="${COM_DAY}" 
                                       class="easyui-validatebox" validType="['maxlength[50]']" style="width:80px;"
                                       title="<spring:message code="TXT_EXECUTE_CYCLE, DAY"/>"/>
                                <spring:message code="MSG_CYCLE_DAY_EX"/><%--ex) 1,2,3 (1일,2일,3일 실행) ※ * (매일)--%>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row" class="th2">
                                <spring:message code="HOUR"/><%--시간--%>
                            </th>
                            <td>
                                <input type="text" name="HOUR" id="HOUR" value="${HOUR}" 
                                       class="easyui-validatebox" validType="['maxlength[50]']" style="width:98%"
                                       title="<spring:message code="TXT_EXECUTE_CYCLE, HOUR"/>"/>
                            </td>
                            <th scope="row" class="th2">
                                <spring:message code="HOUR"/><%--시간--%>
                            </th>
                            <td>
                                <input type="text" name="COM_HOUR" id="COM_HOUR" value="${COM_HOUR}" 
                                       class="easyui-validatebox" validType="['maxlength[50]']" style="width:80px;"
                                       title="<spring:message code="TXT_EXECUTE_CYCLE, HOUR"/>"/>
                                <spring:message code="MSG_CYCLE_HOUR_EX"/><%--ex) 1,2,3 (1시,2시,3시 실행) ※ * (매시간)--%>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row" class="th2">
                                <spring:message code="MINUT"/><%--분--%>
                            </th>
                            <td>
                                <input type="text" name="MINUTES" id="MINUTES" value="${MINUTES}" 
                                       class="easyui-validatebox" validType="['maxlength[50]']" style="width:98%"
                                       title="<spring:message code="TXT_EXECUTE_CYCLE, MINUT"/>"/>
                            </td>
                            <th scope="row" class="th2">
                                <spring:message code="MINUT"/><%--분--%>
                            </th>
                            <td>
                                <input type="text" name="COM_MINUTES" id="COM_MINUTES" value="${COM_MINUTES}" 
                                       class="easyui-validatebox" validType="['maxlength[50]']" style="width:80px;"
                                       title="<spring:message code="TXT_EXECUTE_CYCLE, MINUT"/>"/>
                                <spring:message code="MSG_CYCLE_MINUTES_EX"/><%--ex) 00 (00분에 실행) ※ * (매분)--%>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
        <div data-options="region:'center',border:false" style="height:100%;">
            <div class="easyui-layout" data-options="fit:true">
                <div data-options="region:'north',border:false" class="h2_etc">
                    <p class="h2"><spring:message code="TXT_BATCH_PLAN"/><%--배치 수행계획--%></p>
                    <p class="h2_btn">
                       <a href="javascript:SM7004_02.ui.insertRow('1');" id="SM7004_02_btn_05" class="btnMoreInfo" style="display:none;"><spring:message code="ADDRW"/></a>
                       <a href="javascript:SM7004_02.ui.deleteRow('1');" id="SM7004_02_btn_06" class="btnMinusInfo" style="display:none;"><spring:message code="DELRW"/></a>
                       <a href="javascript:SM7004_02.ui.moveRow('1', -1);" id="SM7004_02_btn_07" class="btnArrowUp" style="display:none;"><spring:message code="UP"/></a>
                       <a href="javascript:SM7004_02.ui.moveRow('1', 1);" id="SM7004_02_btn_08" class="btnArrowDown" style="display:none;"><spring:message code="DOW"/></a>
                       <a href="javascript:SM7004_02.dialog.openDialog('insert');" id="SM7004_02_btn_03" class="btnNewRegiste"><spring:message code="REGER"/><%--등록--%></a>
                       <a href="javascript:SM7004_02.dialog.openDialog('update');" id="SM7004_02_btn_04" class="btnEdit" style="display:none;"><spring:message code="MOD"/><%--수정--%></a>
                    </p>
                </div>
                <div data-options="region:'center',border:false">
                   <table id="SM7004_02_grid_01"></table>
                </div>
            </div>
        </div>
        
    </div>
    
    <script type="text/javascript" src="/js/views/FM/SM/SM-7004_02.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>