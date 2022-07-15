<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : MM-A016_01.jsp
  * @Description : 엑셀업로드 메인
  * @Modification Information
  *
  * author carlos
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMA016_01_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>      
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'west',border:false" style="width:240px;padding-right:3px;">
            <div class="easyui-layout" data-options="fit:true">
                <div data-options="region:'center',border:false">
                    <div class="easyui-layout" data-options="fit:true">
                        <div data-options="region:'north',border:false" class="h2_etc" style="overflow:hidden;">
                            <p class="h2"><spring:message code="DTA, LIST"/><%-- 데이터 목록 --%></p>
                            <p class="h2_btn">
                                <a href="javascript:MMA016_01.control.excelSampleDownload();" class="btnExport"><spring:message code="SAMPL, DOWN"/><%--샘플 다운로드--%></a>
                            </p>
                        </div>
                        <div data-options="region:'center',border:false">
                            <form id="MMA016_01_form_02" name="MMA016_01_form_02" method="post">
                                <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${_MEMBER.SESSION_COMPANY_CD}"/>
                                <input type="hidden" id="IF_CD" name="IF_CD" value="${IF_CD}"/>
                                <input type="hidden" id="DATA_TYPE" name="DATA_TYPE" value="${DATA_TYPE}"/>
                                <input type="hidden" id="SCHEDULE_CD" name="SCHEDULE_CD" value="${SCHEDULE_CD}"/>
                                <input type="hidden" id="ITEM_TYPE" name="ITEM_TYPE" value="T"/>
                                <input type="hidden" id="IF_METHOD" name="IF_METHOD" value="E"/>
                                <input type="hidden" id="FUNCTION_NAME" name="FUNCTION_NAME" value="${FUNCTION_NAME}"/>
                                <input type="hidden" id="YYYYMM" name="YYYYMM" value="${YYYYMM}"/>
                            </form>
                            <form id="MMA016_01_form_01" name="MMA016_01_form_01" method="post">
                                <input type="hidden" id="TARGET_PID"  name="TARGET_PID"  value="${TARGET_PID}"/>
                                <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${_MEMBER.SESSION_COMPANY_CD}"/>
                                <input type="hidden" id="IF_CD" name="IF_CD"/>
                                <input type="hidden" id="IF_NAME" name="IF_NAME"/>
                                <input type="hidden" id="USER_ID" name="USER_ID" value="${_MEMBER.SESSION_USER_ID}"/>
                                <input type="hidden" id="IF_DATA_TYPE" name="IF_DATA_TYPE" value="${IF_DATA_TYPE}"/>
                                <input type="hidden" id="FILE_NAME" name="FILE_NAME"/>
                            </form>
                            <form id="MMA016_01_form_03" name="MMA016_01_form_03" method="post">
                                <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${_MEMBER.SESSION_COMPANY_CD}"/>
                                <input type="hidden" id="IF_CD" name="IF_CD" value="${IF_CD}"/>
                                <input type="hidden" id="USER_ID" name="USER_ID" value="${_MEMBER.SESSION_USER_ID}"/>
                                <input type="hidden" id="YYYYMM" name="YYYYMM"/>
                            </form>
                           <table id="MMA016_01_grid_01" style="background-color:#000"></table>
                        </div>
                   </div>
                </div>
                <div data-options="region:'south',border:false" style="padding-top:6px;">
                    <div class="easyui-layout" data-options="fit:true">
                        <div data-options="region:'north',border:false" style="overflow:hidden;">
                            <p class="h2"><spring:message code="TREAT,STAGE"/><%-- 처리단계 --%></p>
                        </div>
                        <div data-options="region:'center',border:false" style="padding:1px;">
                            <div class="processBox">
	                            <span id="MMA016_01_span_01" class="excel_process_sel">1.<spring:message code="DTA, PREVE"/></span><br>
			                    <span id="MMA016_01_span_02" class="excel_process_none">2.<spring:message code="DTA, VFCTN"/></span><br>
			                    <span id="MMA016_01_span_03" class="excel_process_none">
			                        3.<spring:message code="CMPTE"/>
			                        <div id="MMA016_01_div_01" style="padding-left:10px;font-weight:normal;"></div>
			                    </span>
		                    </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div data-options="region:'center',border:false">
            <div id="MMA016_01_tabs_01" class="easyui-tabs" data-options="fit:true,showHeader:false,plain:true" style="height:auto;">
                <div title="Step0"></div>
            </div>
        </div>
        <div data-options="region:'south',border:false" style="height:40px;">
            <div class="btn_R">
                <a href="javascript:MMA016_01.ui.tabsBack();" id="MMA016_01_btn_01" class="btnDisableProc"><spring:message code="<, BACK"/></a>
                <a href="javascript:MMA016_01.ui.tabsNext();" id="MMA016_01_btn_02" class="btnDisableProc"><spring:message code="NEXT, >"/></a>
                <a href="javascript:MMA016_01.control.executeExcelBatch();" id="MMA016_01_btn_03" class="btnDisableProc"><spring:message code="CMPTE"/></a>
                <a href="javascript:MMA016_01.control.cancelUpload();" id="MMA016_01_btn_04" class="btnEnableProc"><spring:message code="CLOSE"/></a>
            </div>
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-A016_01.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body> 
</html>