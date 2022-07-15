<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : MM-B008_01.jsp
  * @Description : 표준통관 자료 등록 화면
  * @Modification Information
  *
  * author YNI-Maker(이메일)
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMB008_01_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'north',border:false" class="h2_etc">
            <form id="MMB008_01_form_01" enctype="multipart/form-data" method="POST">
                <input type="hidden" id="flag" name="flag" value="${flag}"/>
                <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
                <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
                <input type="hidden" id="IMP_BCNC_SETUP_SN" name="IMP_BCNC_SETUP_SN" value="${IMP_BCNC_SETUP_SN}"/>
                <input type="hidden" id="USER_ID" name="USER_ID" value="${_MEMBER.SESSION_USER_ID}"/>
                <table class="gridT" style="margin-bottom:0px;">
                    <colgroup>
                        <col width="120px;" />
                        <col width=";" /> 
                    </colgroup>
                    <tbody>
                        <tr>
                            <th scope="row" class="point"><spring:message code="TXT_ATTACH_FILE"/><%--첨부파일--%></th>
                            <td>
                                <input type="file" id="uploadExcelFile" name="uploadExcelFile" style="width:600px;" multiple/>
                                <p class="h2_btn">
                                    <a href="javascript:MMB008_01.control.saveExcelSample();" class="btnFileImport" id="MMB008_01_btn_01"><spring:message code="BTN_EXCEL_UPLOAD"/><%--조회--%></a>
                                </p>
                            </td>
                        </tr>
                        <tr>
                            <th><spring:message code="CC_품목코드 규칙"/></th>
                            <td>
                                <label><spring:message code="CC_코드위치"/>: </label>
                                <input id="MTRIL_PRCSS_DRC" name="MTRIL_PRCSS_DRC" value="${MTRIL_PRCSS_DRC}" style="width:40px;"/>
                                <input type="text" id="MTRIL_PRCSS_BEGIN_LC" name="MTRIL_PRCSS_BEGIN_LC" value="${MTRIL_PRCSS_BEGIN_LC}" style="width:40px;"/> ~
                                <input type="text" id="MTRIL_PRCSS_END_LC" name="MTRIL_PRCSS_END_LC" value="${MTRIL_PRCSS_END_LC}" style="width:40px;"/>
                                <label>, <spring:message code="CC_삭제할 문자"/>: </label>
                                <input type="text" id="MTRIL_DEL_CHRCTR_01" name="MTRIL_DEL_CHRCTR_01" value="${MTRIL_DEL_CHRCTR_01}" style="width:30px;"/> /
                                <input type="text" id="MTRIL_DEL_CHRCTR_02" name="MTRIL_DEL_CHRCTR_02" value="${MTRIL_DEL_CHRCTR_02}" style="width:30px;"/> /
                                <input type="text" id="MTRIL_DEL_CHRCTR_03" name="MTRIL_DEL_CHRCTR_03" value="${MTRIL_DEL_CHRCTR_03}" style="width:30px;"/>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
        <div data-options="region:'center',border:false" style="padding-top:12px;">
            <div class="easyui-layout" data-options="fit:true">
                <div data-options="region:'north',border:false" class="h2_etc">
                    <p class="h2">
                        <spring:message code="TXT_UPLOAD_LIST"/><font color="blue"><span id="MMB008_01_span_01"></span></font>
                    </p>
                    <p class="h2_btn">
                        <a href="javascript:MMB008_01.ui.insertRow('1');" id="MMB008_01_btn_03" class="btnMoreInfo"><spring:message code="ADDRW"/></a>
                        <a href="javascript:MMB008_01.ui.deleteRow('1');" id="MMB008_01_btn_04" class="btnMinusInfo"><spring:message code="DELRW"/></a>
                        <a href="javascript:MMB008_01.control.updateStandardEntrUploadNo();" class="btnSynchroniz"><spring:message code="CC_수입자료 연결 재실행"/></a>
                        <a href="javascript:MMB008_01.control.updatePlUploadHist();" class="btnMainSave" id="MMB008_01_btn_05"><spring:message code="SAVE"/></a>
                        <a href="javascript:MMB008_01.control.deleteStandardEntrUplo();" class="btnMainDelete" id="MMB008_01_btn_02"><spring:message code="DEL"/></a>
                    </p>
                </div>
                <div data-options="region:'center',border:false">
                    <form id="MMB008_01_form_02" method="POST">
		                <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
		                <input type="hidden" id="IMP_BCNC_SETUP_SN" name="IMP_BCNC_SETUP_SN" value="${IMP_BCNC_SETUP_SN}"/>
		                <input type="hidden" id="UPLOAD_NO_INIT_YN" name="UPLOAD_NO_INIT_YN" value="Y"/>
		            </form>
		            <form id="MMB008_01_form_03" method="POST">
                        <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
                        <input type="hidden" id="gridData" name="gridData"/>
                        <input type="hidden" id="USER_ID" name="USER_ID" value="${_MEMBER.SESSION_USER_ID}"/>
                    </form>
                    <table id="MMB008_01_grid_01"> </table>
                </div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-B008_01.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>