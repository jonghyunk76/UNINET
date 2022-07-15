<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : SM-7004_04.jsp
  * @Description : 배치수행이력 조회
  * @Modification Information
  *
  * author carlos
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "SM7004_04_dailog_01"으로 하셔야 합니다.
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
                <form id="SM7004_04_form_01" name="SM7004_04_form_01" method="post">
                    <input type="hidden" id="SESSION_COMPANY_CD" name="SESSION_COMPANY_CD" value="${_MEMBER.SESSION_COMPANY_CD}"/>
                    <input type="hidden" id="DEFAULT_LANGUAGE" name="DEFAULT_LANGUAGE" value="${_MEMBER.SESSION_DEFAULT_LANGUAGE}"/>
                    <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${_MEMBER.SESSION_COMPANY_CD}"/>
                    <input type="hidden" id="SCHEDULE_CD" name="SCHEDULE_CD" value="${SCHEDULE_CD}"/>
                    <input type="hidden" id="INTERFACE_SCHEDULE_ID" name="INTERFACE_SCHEDULE_ID" value="${INTERFACE_SCHEDULE_ID}"/>
                    <input type="hidden" id="IF_CD" name="IF_CD"/>
                    <table>
                        <caption><spring:message code="TXT_DATA_SEARCH_CONDITION"/><%--데이타 검색조건 설정--%></caption>
                        <colgroup>
                            <col width="130px"/>
                            <col width="220px"/>
                            <col width="100px"/>
                            <col width="110px"/>
                            <col width="150px"/>
                            <col width="160px"/>
                            <col width=";"/>
                        </colgroup>
                        <tbody>
                            <tr>
                                <th scope="row" class="point"><spring:message code="EXCUS,DATE"/><%--실행일자--%></th>
                                <td>
                                    <input type="text" name="SCH_FROM_DATE" id="SCH_FROM_DATE" validType="['date']" style="width:94px;"/>
                                    ~
                                    <input type="text" name="SCH_TO_DATE" id="SCH_TO_DATE" validType="['date']" style="width:94px;" />
                                </td>
                                <th scope="row"><spring:message code="TXT_RESULT_STATUS"/><%--처리결과--%></th>
                                <td>
                                    <input type="text" name="BATCH_STATUS" id="BATCH_STATUS" style="width:94px;"/> 
                                </td>
                                <td class="txt_R">
                                    <a href="javascript:SM7004_04.control.selectCloseList();" 
                                       class="easyui-linkbutton pop_sch" id="SM7004_04_searchBtn1"><spring:message code="SRCH"/><%--조회--%></a>
                                </td>
                            </tr>
                        </tbody>
                    </table>                   
                </form>         
            </div>
        </div>
        <div data-options="region:'center',border:false" style="height:100%;padding-top:9px;">
            <div class="easyui-layout" data-options="fit:true">
                <div data-options="region:'north',border:false" class="h2_etc">
                    <p class="h2"><spring:message code="TXT_BATCH, RECOD"/><%--배치이력--%></p>
                    <p class="h2_btn">
                        <a href="javascript:SM7004_04.dialog.openDialog_1();" class="btnSelectClick"><spring:message code="DTA, DETIL"/><%--데이터 상세--%></a>
                        <a href="javascript:SM7004_04.excel.excelDownload_1();" class="btnExlDown"><spring:message code="EXCEL, DOWN"/><%--엑셀다운로드--%></a>
                    </p>
                </div>
                <div data-options="region:'center',border:false">
                   <table id="SM7004_04_grid_01"></table>
                </div>
            </div>
        </div>
        <div data-options="region:'west',border:false,split:true" style="width:300px;padding-top:9px;">
            <div class="easyui-layout" data-options="fit:true">
                <div data-options="region:'north',border:false" class="h2_etc">
                    <p class="h2"><spring:message code="TXT_BATCH, LIST"/><%--배치목록--%></p>
                </div>
                <div data-options="region:'center',border:false">
                   <table id="SM7004_04_grid_02"></table>
                </div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/FM/SM/SM-7004_04.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body> 
</html>