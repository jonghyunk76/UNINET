<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : MM-B002_16.jsp
  * @Description : 통관 첨부파일 등록
  * @Modification Information
  *
  * author carlos
  *
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMB002_16_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body class="h2_etc">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center',border:false">
        	<div class="btn_R">
                <a href="javascript:MMB002_16.control.mergeEntryFile();" id="MMB002_16_btn_01" class="btnSave"><spring:message code="SAVE"/></a>
                <a href="javascript:MMB002_16.control.deleteCustomerSite();" id="MMB002_16_btn_02" class="btnDelete" style="display:none;"><spring:message code="DEL"/></a>
                <a href="javascript:dialog.handle.close(dialog.getObject('MMB002_16_dailog_01'));" id="MMB002_16_btn_00" class="btnPopClose"><spring:message code="CLOSE"/></a>
            </div>
            <form id="MMB002_16_form_01" name="MMB002_16_form_01" enctype="multipart/form-data" method="post">
            	<input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
            	<input type="hidden" id="IMP_DCLRT_SN" name="IMP_DCLRT_SN" value="${IMP_DCLRT_SN}"/>
            	<input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
            	<input type="hidden" id="ENTR_ATCHF_MASTR_SN" name="ENTR_ATCHF_MASTR_SN" value="${ENTR_ATCHF_MASTR_SN}"/>
            	<input type="hidden" id="ENTR_TY" name="ENTR_TY" value="${ENTR_TY}"/>
            	<input type="hidden" id="NEW_YN" name="NEW_YN" value="${NEW_YN}"/>
            	<input type="hidden" id="flag" name="flag" value="${flag}"/>
                <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
                <input type="hidden" id="USER_ID" name="USER_ID" value="${USER_ID}"/>
                <input type="hidden" id="gridData" name="gridData">
                <input type="hidden" id="gridCount" name="gridCount">
                <!-- File -->
                <input type="hidden" id="ENTR_ATCHF_DETAIL_SN" name="ENTR_ATCHF_DETAIL_SN"/>
                <input type="hidden" id="FILE_FIELD_NAME" name="FILE_FIELD_NAME" value="ATCHF"/>
                <input type="hidden" id="FILE_NAME" name="FILE_NAME"/>
                                                                    
                <table id="MMB002_16_grid_01" class="dataT"></table>

            </form>
        </div>
    </div>

    <script type="text/javascript" src="/js/views/MM/POP/MM-B002_16.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>