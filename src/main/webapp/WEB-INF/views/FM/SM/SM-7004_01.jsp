<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : SM-7004_01.jsp
  * @Description : 스케쥴 리스트 조회
  * @Modification Information
  *
  * author carlos
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "SM7004_01_dailog_01"으로 하셔야 합니다.
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
                <form id="SM7004_01_form_01" name="SM7004_01_form_01" method="post">
                    <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
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
                                    <input type="text" id="schKeyWord" name="schKeyWord" class="easyui-validatebox" search="SM7004_01.control.selectScheduleList" style="width:150px;"/>
                                    <input type="text" id="schKeyLike" name="schKeyLike" style="width:90px;"/>
                                </td>
                                <td class="txt_R">
                                    <a href="javascript:SM7004_01.control.selectScheduleList();" class="easyui-linkbutton pop_sch"><spring:message code="SRCH"/><%--조회--%></a>
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
                       <p class="h2"><spring:message code="SM7004_01"/><%--스케쥴 목록--%></p>
                       <p class="h2_btn">
                           <a href="javascript:SM7004_01.dialog.openDialog('view');" class="btnSelectClick" style="display:none;"><spring:message code="DETIL,VIWER"/><%--상세보기--%></a>
                           <a href="javascript:SM7004_01.dialog.openDialog('insert');" class="btnNewRegiste"><spring:message code="REGER"/><%--등록--%></a>
                           <a href="javascript:SM7004_01.dialog.openDialog('update');" class="btnEdit"><spring:message code="MOD"/><%--수정--%></a>
                           <a href="javascript:SM7004_01.control.initScheduleStatus();" class="btnRefresh"><spring:message code="INIT"/><%--초기화--%></a>
                           <a href="javascript:SM7004_01.dialog.openDialog_1();" class="btnSelectClick"><spring:message code="RECOD,SRCH"/><%--이력조회--%></a>
                           <a href="javascript:SM7004_01.excel.excelDownload_1();" class="btnExlDown"><spring:message code="EXCEL, DOWN"/><%--엑셀다운로드--%></a>
                       </p>
                   </div>
    	        </div>
                <!-- //sec_tit -->
    			<div data-options="region:'center',border:false" style="padding-top:0px;">
    			   <form id="SM7004_01_form_02" name="SM7004_01_form_02" method="POST">
    			       <input type="hidden" id="COMPANY_CD" name="COMPANY_CD"/>
    			       <input type="hidden" id="INTERFACE_SCHEDULE_ID" name="INTERFACE_SCHEDULE_ID"/>
    			       <input type="hidden" id="SCHEDULE_CD" name="SCHEDULE_CD"/>
    			       <input type="hidden" id="STATUS" name="STATUS" value="0"/>
    			       <input type="hidden" id="BATCH_YYYYMM_YN" name="BATCH_YYYYMM_YN" value="N"/>
    			   </form>
    			   <table id="SM7004_01_grid_01"></table>
    			</div>
    		</div>
            <!-- //sec_area -->
    	</div>
    	<div data-options="region:'east',border:false,split:true" style="width:395px;padding-top:12px;">
            <!-- sec_area -->
            <div class="easyui-layout" data-options="fit:true" class="sec_area">
                <!-- sec_tit -->
                <div data-options="region:'north',border:false" class="sec_tit">
                    <div class="h2_etc">
                       <p class="h2"><spring:message code="TXT_BATCH_PLAN"/><%--배치 스케쥴 계획--%></p>
                       <p class="h2_btn">
                           <a href="javascript:SM7004_01.control.selectQuartzJobList();" class="btnRefresh"><spring:message code="REFSH"/><%--새로고침--%></a>
                           <a href="javascript:SM7004_01.control.executeStopScheduler();" class="btnStop"><spring:message code="STOP"/><%--정지--%></a>
                       </p>
                   </div>
                </div>
                <!-- //sec_tit -->
                <div data-options="region:'center',border:false" style="padding-top:0px;">
                   <table id="SM7004_01_grid_02"></table>
                </div>
            </div>
            <!-- //sec_area -->
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/FM/SM/SM-7004_01.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body> 
</html>