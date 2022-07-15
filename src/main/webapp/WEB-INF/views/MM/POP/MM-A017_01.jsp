<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : MM-A017_01.jsp
  * @Description : 한국 확인서(증명서) 상세 조회 및 제출
  * @Modification Information
  *
  * author carlos
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMA017_01_dailog_01"으로 하셔야 합니다.
  *
  *
  **************************** 팝업 호출시 필수 파라미터 ID명
  * 선택한그리드데이터 : gridData
  * 호출한 flag        : flag
  * 부모창ID           : TARGET_PID
  * 회사코드           : COMPANY_CD
  * 협력사코드         : VENDOR_CD
  * 로그인ID           : USER_ID
  * 원산지증명번호     : CO_DOC_NO
  */
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'north',border:false" style="height:30px;overflow:hidden;">
	    <div class="btn_R">
	        <a href="javascript:MMA017_01.control.saveMasterDetailInfo();" id="MMA017_01_btn_01" class="btnPopIssue"><spring:message code="SUNMT"/><%--제출--%></a>
	        <a href="javascript:MMA017_01.control.saveOriginModifyInfo();" id="MMA017_01_btn_03" class="btnSave" style="display:none;"><spring:message code="MOD"/><%--수정--%></a>
	        <a href="javascript:MMA017_01.dialog.openDialog_4();" id="MMA017_01_btn_02" class="btnPopFileImport" style="display:none;"><spring:message code="EVID,FILE,ATACH"/><%--증빙파일첨부--%></a>
            <a href="javascript:MMA017_01.dialog.closeDialog_top();" id="MMA017_01_btn_04" class="btnPopClose" style="display:none;"><spring:message code="CLOSE"/><%--닫기--%></a>
	    </div>
	</div>
    <div data-options="region:'center',border:false">
        <div class="easyui-layout" data-options="fit:true">
            <div data-options="region:'north',border:false">
                <div class="h2_etc">
                    <p class="h2"><spring:message code="CNFDO, TXT_BASICINFO"/><%--확인서 기본정보--%></p><p id="MMA017_01_p_01" style="color:red;display:none;">(* <spring:message code="MSG_END_DATE_NEXT_ISSUE"/>)</p>
                </div>
            </div>
            <div data-options="region:'center',border:false">
                <div class="easyui-layout" data-options="fit:true">
                    <div data-options="region:'north',border:false" style="overflow:hidden;">
                        <form id="MMA017_01_form_01" name="MMA017_01_form_01" enctype="multipart/form-data" method="POST">
	                        <input type="hidden" id="gridData"              name="gridData"              value='${gridData}'/>
	                        <input type="hidden" id="flag"                  name="flag"                  value="${flag}"/>
	                        <input type="hidden" id="TARGET_PID"            name="TARGET_PID"            value="${TARGET_PID}"/>
	                        <input type="hidden" id="COMPANY_CD"            name="COMPANY_CD"            value="${COMPANY_CD}"/>
	                        <input type="hidden" id="VENDOR_CD"             name="VENDOR_CD"             value="${VENDOR_CD}"/>
	                        <input type="hidden" id="MODIFY_REQ"            name="MODIFY_REQ"            value="${MODIFY_REQ}"/>
	                        <input type="hidden" id="CO_DOC_TYPE"           name="CO_DOC_TYPE"           value="${CO_DOC_TYPE}"/>
	                        <input type="hidden" id="USER_ID"               name="USER_ID"               value="${_MEMBER.SESSION_USER_ID}"/>
	                        <input type="hidden" id="GLOBAL_CD"             name="GLOBAL_CD"             value="${GLOBAL_CD}"/>
	                        <input type="hidden" id="PUBLISH_DATE"          name="PUBLISH_DATE"          value="${PUBLISH_DATE}"/> <%--증명서발행일--%>
	                        <input type="hidden" id="ORIGINAL_APPLY_DATE"   name="ORIGINAL_APPLY_DATE"   value="${ORIGINAL_APPLY_DATE}"/> <%--포괄시작일--%>
	                        <input type="hidden" id="ORIGINAL_END_DTAE"     name="ORIGINAL_END_DTAE"     value="${ORIGINAL_END_DTAE}"/> <%--포괄종료일--%>
	                        <input type="hidden" id="FTA_GROUP_CD"          name="FTA_GROUP_CD"          value="${FTA_GROUP_CD}"/>
	                        <input type="hidden" id="LAST_PURCHASE_DATE"    name="LAST_PURCHASE_DATE"    value="${LAST_PURCHASE_DATE}"/>
	                        <input type="hidden" id="END_FIX_DATE"          name="END_FIX_DATE"          value="${END_FIX_DATE}"/>
	                        <input type="hidden" id="PRINT_FLAG"            name="PRINT_FLAG"            value="${flag}"/>
	                        <input type="hidden" id="step1_basicInfoSave"   name="step1_basicInfoSave"   value="${step1_basicInfoSave}"/> <%--기본정보 저장여부--%>
	                        <input type="hidden" id="step2_itemInfoSave"    name="step2_itemInfoSave"    value="${step2_itemInfoSave}"/> <%--자재정보 저장여부--%>
	                        <input type="hidden" id="step3_printAfter"      name="step3_printAfter"      value="${step3_printAfter}"/> <%--프린트 실행여부--%>
	                        <input type="hidden" id="BF_CO_DOC_NO"          name="BF_CO_DOC_NO"          value="${BF_CO_DOC_NO}"/> <%--이전 증명서 번호--%>
	                        <input type="hidden" id="bf_issueDate"          name="bf_issueDate"          value="${bf_issueDate}"/> <%--이전 증명서 발행일--%>
	                        <input type="hidden" id="bf_applyDate"          name="bf_applyDate"          value="${bf_applyDate}"/> <%--이전 포괄시작일--%>
	                        <input type="hidden" id="bf_endDate"            name="bf_endDate"            value="${bf_endDate}"/> <%--이전 포괄정료일--%>
	                        <input type="hidden" id="SIGN_CARD_FILE_YN"     name="SIGN_CARD_FILE_YN"     value="${SIGN_CARD_FILE_YN}"/> <%--서명파일 존재여부--%>
	                        <input type="hidden" id="excel"                 name="excel"                 value="${excel}"/> <%--엑셀다운 여부--%>
	                        <input type="hidden" id="bf_flag"               name="bf_flag"               value="${bf_flag}"/>
	                        <input type="hidden" id="PRF_FILE_SEQ"          name="PRF_FILE_SEQ"          value="${PRF_FILE_SEQ}"/>
                            <input type="hidden" id="PRF_FILE_NAME"         name="PRF_FILE_NAME"         value="${PRF_FILE_NAME}"/>
	                        <input type="hidden" id="REGISTED_BY"           name="REGISTED_BY"           value="${REGISTED_BY}"/>
	                        <input type="hidden" id="TABLE_NAME"            name="TABLE_NAME"            value="#EXCEL_TEMP"/>
                            <input type="hidden" id="SING_REG_YN"           name="SING_REG_YN"           value="${SING_REG_YN}"/>
                            <input type="hidden" id="EV_FILE_ONLY_YN"       name="EV_FILE_ONLY_YN"       value="Y"/>
                            <input type="hidden" id="EV_FILE_EXISTS_YN"     name="EV_FILE_EXISTS_YN"     value="N"/>
                            <input type="hidden" id="CUSTOMER_HS_CODE_YN"   name="CUSTOMER_HS_CODE_YN"   value="N"/>
                            <input type="hidden" id="HUB_VENDOR_YN"         name="HUB_VENDOR_YN"         value="N"/>
                            <input type="hidden" id="CO_ISSUE_STATUS"       value="${CO_ISSUE_STATUS}"/>
                            <input type="hidden" id="VENODR_FTA_FIX_YN"     name="VENODR_FTA_FIX_YN"     value="${VENODR_FTA_FIX_YN}"/>
                            <input type="hidden" id="V_CO_DOC_NO"           name="V_CO_DOC_NO"           value="${CO_DOC_NO}"/>
                            <input type="hidden" id="APPLY_FTA_CD"          name="APPLY_FTA_CD"/>
                            <input type="hidden" id="NON_ORGIN_RESN_YN"     name="NON_ORGIN_RESN_YN"     value="${NON_ORGIN_RESN_YN}"/> <%--역외사유 사용여부 --%>
                            <input type="hidden" id="NON_ORGIN_RESN_REQ_YN" name="NON_ORGIN_RESN_REQ_YN" value="${NON_ORGIN_RESN_REQ_YN}"/> <%--역외사유 설명 --%>
                            <input type="hidden" id="REQ_TYPE"              name="REQ_TYPE"              value="${REQ_TYPE}"/>
                            <input type="hidden" id="RVC_REQUIRED_YN"       name="RVC_REQUIRED_YN"       value="${RVC_REQUIRED_YN}"/> <%-- 부가가치비율 필수여부(Y/N) --%>
                            <input type="hidden" id="HEADER_RES"            name="HEADER_RES" />
                            <input type="hidden" id="CO_COVER_YN"           name="CO_COVER_YN" /> <%-- 포괄기간 체크여부 --%>
                            <input type="hidden" id="DECLARE_NO"            name="DECLARE_NO"            value="${INVOICE_NO}"/> <%-- 신고번호 --%>
                            <input type="hidden" id="INVOICE_NO"            name="INVOICE_NO"            value="${INVOICE_NO}"/> <%-- 신고번호 --%>
                            <input type="hidden" id="TXN_TYPE"              name="TXN_TYPE"              value="${TXN_TYPE}"/> <%-- 내수/수입 --%>
                            <table class="dataT">
                                <caption><spring:message code="DTA, TB"/><%--데이타 테이블--%></caption>
                                <colgroup>
                                    <col width="115px;" />
                                    <col width="90px;" />
                                    <col width=";" />
                                    <col width="120px;" />
                                    <col width=";" />
                                    <col width="120px;" />
                                    <col width=";" />
                                </colgroup>
                                <tbody>
                                    <tr>
                                        <th scope="row" class="point2" colspan="2">
                                            <div style="float:left;"><spring:message code="TXT_SEARCH_COO_CERTIFY_NO"/></div>
                                            <div id="MMA017_01_form_01_chkAuto" style="float:right;">
                                                <input type="checkbox" name="chkAuto" id="chkAuto" onclick="javascript:MMA017_01.ui.onChangeChkAuto();"/> <spring:message code="TXT_AUTO_GENERATION"/>
                                            </div>
                                        </th>
                                        <td>
                                            <input type="text" name="CO_DOC_NO" id="CO_DOC_NO" class="easyui-validatebox" required="true" validType="['maxlength[50]']" style="width:98%"
                                                   title="<spring:message code="CNFDO, TXT_SEARCH_COO_CERTIFY_NO"/>" />
                                        </td>
                                        <th scope="row">
                                            <spring:message code="PATNE"/><%--거래처--%>
                                        </th>
                                        <td>
                                            <input type="text" name="VENDOR_NAME" id="VENDOR_NAME" value="${VENDOR_NAME}"
                                                   class="input_readOnly" title="<spring:message code="TXT_DEAL_VENDOR"/>" readonly style="width:98%"/>
                                        </td>
                                        <th scope="row" class="point2">
                                            <spring:message code="FTA, GROUP"/><%--협정그룹명--%>
                                        </th>
                                        <td>
                                            <input type="text" name="FTA_GROUP_NM" id="FTA_GROUP_NM" value="${FTA_GROUP_NM}" class="input_readOnly" readonly style="width:98%"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th scope="row" colspan="2" class="point2">
                                            <spring:message code="CERIS,DATE"/><%--증명서 발행일--%>
                                        </th>
                                        <td>
                                            <input type="text" name="ISSUE_DATE" id="ISSUE_DATE" value="${ISSUE_DATE}" style="width:95px;"/>
                                        </td>
                                        <th scope="row" class="point2">
                                            <span id="MMA017_01_div_03"><spring:message code="TXT_COVER_DATE"/></span><%--포괄기간--%>
                                        </th>
                                        <td colspan="3">
                                            <span id="MMA017_01_div_01" style="display:none;"><input type="text" name="APPLY_DATE" id="APPLY_DATE" value="${APPLY_DATE}" style="width:95px;"
                                                   data-options="validType:{comparedate:['MMA017_01_form_01','DATE','END_DATE']}" /></span>
                                            <span id="MMA017_01_div_02" style="display:none;">~ <input type="text" name="END_DATE" id="END_DATE" value="${END_DATE}" style="width:95px;" /></span>
                                            <span id="MMA017_01_span_02" style="display:none;"> 
                                                (<spring:message code="MOD,RSTS"/> :
                                                <input type="text" name="MOD_APPLY_DATE" id="MOD_APPLY_DATE" value="${MOD_APPLY_DATE}" style="width:95px;"/> ~
                                                <input type="text" name="MOD_END_DATE" id="MOD_END_DATE" value="${MOD_END_DATE}" style="width:95px;"/>)
                                            </span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th scope="row">
                                            <spring:message code="THPIC"/><%--담당자 정보--%>
                                        </th>
                                        <th scope="row" id="MMA017_01_th_02" class="point2">
                                            <spring:message code="FUNM"/><%--성명--%>
                                        </th>
                                        <td>
                                            <input type="text" name="VENDOR_CHARGE_NAME" id="VENDOR_CHARGE_NAME" value="${VENDOR_CHARGE_NAME}"
                                                   class="easyui-validatebox" required="true" validType="['maxlength[100]']" style="width:98%"/>
                                        </td>
                                        <th scope="row">
                                            <spring:message code="POSIT"/><%--직책--%>
                                        </th>
                                        <td>
                                            <input type="text" name="VENDOR_CHARGE_POSITION" id="VENDOR_CHARGE_POSITION" value="${VENDOR_CHARGE_POSITION}"
                                                   class="easyui-validatebox" required="true" validType="['maxlength[30]']" style="width:98%"/>
                                        </td>
                                        <th scope="row" id="MMA017_01_th_03" class="point2">
                                            <spring:message code="CNTACT"/><%--연락처--%>
                                        </th>
                                        <td>
                                            <input type="text" name="VENDOR_CHARGE_PHONE_NO" id="VENDOR_CHARGE_PHONE_NO" value="${VENDOR_CHARGE_PHONE_NO}"
                                                   class="easyui-validatebox" required="true" validType="['telnum','maxlength[20]']" style="width:98%"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th scope="row" rowspan="3">
                                            <spring:message code="TXT_PRDER_INFO"/><%--생산자 정보--%>
                                        </th>
                                        <th scope="row" class="point2">
                                            <spring:message code="COMPN,NAME"/><%--업체명--%>
                                        </th>
                                        <td>
                                            <input type="text" name="PRODUCER_NAME" id="PRODUCER_NAME" value="${PRODUCER_NAME}"
                                                   class="easyui-validatebox" required="true" validType="['maxlength[200]']" style="width:98%"
                                                   title="<spring:message code="COMPN,NAME"/>" />
                                        </td>
                                        <th scope="row" class="point2">
                                            <spring:message code="TXT_CO_REG_NUM"/><%--사업자등록번호--%>
                                        </th>
                                        <td>
                                            <input type="text" name="PRODUCER_BUSINESS_NO" id="PRODUCER_BUSINESS_NO" value="${PRODUCER_BUSINESS_NO}"
                                                   class="easyui-validatebox" required="true" validType="['maxlength[100]']" style="width:98%"
                                                   title="<spring:message code="TXT_CO_REG_NUM"/>" />
                                        </td>
                                        <th scope="row" class="point2">
                                            <spring:message code="TXT_PRESIDENT_NAME"/><%--대표자명--%>
                                        </th>
                                        <td>
                                            <input type="text" name="PRODUCER_PRESIDENT_NAME" id="PRODUCER_PRESIDENT_NAME" value="${PRODUCER_PRESIDENT_NAME}"
                                                   class="easyui-validatebox" required="true" validType="['maxlength[50]']" style="width:98%"
                                                   title="<spring:message code="TXT_PRESIDENT_NAME"/>" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <th scope="row" class="point2">
                                            <spring:message code="TELNM "/><%--전화번호--%>
                                        </th>
                                        <td>
                                            <input type="text" name="PRODUCER_PHONE_NO" id="PRODUCER_PHONE_NO" value="${PRODUCER_PHONE_NO}"
                                                   class="easyui-validatebox" required="true" validType="['maxlength[50]']" style="width:98%"
                                                   title="<spring:message code="TELNM"/>" />
                                        </td>
                                        <th scope="row">
                                            <spring:message code="FAX"/><%--팩스번호--%>
                                        </th>
                                        <td>
                                            <input type="text" name="PRODUCER_FAX_NO" id="PRODUCER_FAX_NO" value="${PRODUCER_FAX_NO}"
                                                   class="easyui-validatebox" validType="['maxlength[50]']" style="width:98%"
                                                   title="<spring:message code="FAX"/>" />
                                        </td>
                                        <th scope="row">
                                            <spring:message code="TXT_EMAIL"/><%--이메일--%>
                                        </th>
                                        <td>
                                            <input type="text" name="PRODUCER_EMAIL" id="PRODUCER_EMAIL" value="${PRODUCER_EMAIL}"
                                                   class="easyui-validatebox" validType="['maxlength[50]']" style="width:98%"
                                                   title="<spring:message code="TXT_EMAIL"/>" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <th scope="row" class="point2">
                                            <spring:message code="ADDRS"/><%--주소--%>
                                        </th>
                                        <td colspan="5">
                                            <input type="text" name="PRODUCER_ADDRESS" id="PRODUCER_ADDRESS" value="${PRODUCER_ADDRESS}"
                                                   class="easyui-validatebox" required="true" validType="['maxlength[200]']" style="width:99%"
                                                   title="<spring:message code="ADDRS"/>" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <th scope="row" class="point2" colspan="2">
                                            <div style="float:left;"><spring:message code="CNFDO, FILE"/></div>
                                            <div id="MMA017_01_form_02_chkAuto" style="float:right;">
                                                <input type="checkbox" name="chkFile" id="chkFile" onclick="javascript:MMA017_01.ui.onChangeFilePassChk();"/> <spring:message code="AUTO,ATACH"/>
                                            </div>
                                            <span id="MMA017_01_span_06" style="display:none;float:right;">
                                                <a href="javascript:MMA017_01.ui.updateEvenFileUI();"><img src="/images/icon/btn_fileP.gif" boder="0"/></a>
                                            </span>
                                        </th>
                                        <td colspan="5" style="height:22px;">
                                            <span id="MMA017_01_form_01_PRF_FILE">
                                                <input type="file" name="PRF_FILE" id="PRF_FILE" class="easyui-validatebox" required="true" onchange="javascript:MMA017_01.file.setFiles(this);" style="width:70%;height:20px;"/>
                                            </span>
                                            <span id="MMA017_01_span_07" style="display:none;">
                                                <a href="javascript:MMA017_01.control.updateMgmtEvenFile();" class="btn"><spring:message code="REGSR"/></a>
                                            </span>
                                            <span id="MMA017_01_form_01_PRF_FILE_DW" style="display:none;">
                                                <a href="javascript:MMA017_01.control.selectProofFile();" id="MMA017_01_form_01_PRF_FILE_DOWN" style="display:none;">
                                                    <span><img src="/images/icon/btn_file.gif" boder="0"/></span> <span id="MMA017_01_form_01_PRF_FILE_NAME_SPAN"></span>
                                                </a>
                                            </span>
                                        </td>
                                    </tr>
                                    <tr id="MMA017_01_tr_01" style="display:none;">
                                        <th scope="row" colspan="2">
                                            <div style="float:left;"><spring:message code="TXT_MOD_REQ_REASN"/></div>
                                        </th>
                                        <td colspan="5">
                                            <input type="text" name="MODIFY_REQUEST_REASON" id="MODIFY_REQUEST_REASON" value="${MODIFY_REQUEST_REASON}" class="input_readOnly" readonly style="width:80%;"/>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </form>
                    </div>
                    <div data-options="region:'center',border:false">
                        <div class="easyui-layout" data-options="fit:true">
		                    <div data-options="region:'west',border:false,split:true" class="width65" style="padding-top:3px;">
		                        <div class="easyui-layout" data-options="fit:true">
		                            <div data-options="region:'north',border:false">
		                                <div class="h2_etc">
		                                    <p class="h2">
		                                        <spring:message code="TXT_ITEMBY_ORG_INFO"/><%--품목별 원산지 정보--%>
		                                        (* <spring:message code="MSG_IS_REQUIRED_FILED"/>)
		                                    </p>
		                                    <p class="h2_btn">
		                                        <a href="javascript:MMA017_01.control.selectItemListQuest();" id="MMA017_01_btn_36" class="btnRefresh"><spring:message code="INIT"/></a>
		                                        <a href="javascript:MMA017_01.dialog.openDialog_2();" id="MMA017_01_btn_34" class="btnImport"><spring:message code="DATA, UPLOD"/><%--자료업로드--%></a>
		                                        <a href="javascript:MMA017_01.excel.excelDownload_1();" id="MMA017_01_btn_35" class="btnExport"><spring:message code="FORM, DOWN"/><%--SAMPLE--%></a>
		                                        <a href="javascript:MMA017_01.excel.excelDownload_2();" id="MMA017_01_btn_40" class="btnExlDown" style="display:none;"><spring:message code="EXCEL, DOWN"/><%--엑셀다운로드--%></a>
		                                    </p>
		                                </div>
		                            </div>
		                            <div data-options="region:'center',border:false">
		                                <div class="easyui-layout" data-options="fit:true">
		                                    <div id="MMA017_01_layout_01" data-options="region:'north',border:false" style="height:auto;background-color:#fff">
		                                        <form id="MMA017_01_form_03" name="MMA017_01_form_03" method="POST">
		                                            <table class="gridT" style="margin-bottom:0px;">
		                                                <caption><spring:message code="DTA, TB"/><%--데이타 테이블--%></caption>
		                                                <colgroup>
		                                                    <col width="140px"/>
		                                                    <col width=""/>
		                                                    <col width="140px"/>
                                                            <col width="250px"/>
		                                                </colgroup>
		                                                <tbody>
		                                                    <tr id="MMA017_01_tr_03" style="display:none;">
                                                                <td scope="row"><spring:message code="TXT_AGRET_SLCT"/></td>
                                                                <td colspan="3">
                                                                    <input id="APPLY_FTA_CD" name="APPLY_FTA_CD" style="width:400px;"/>
                                                                </td>
                                                            </tr>
		                                                    <tr id="MMA017_01_tr_02">
		                                                        <td scope="row"><spring:message code="ORG, APYAL"/></td>
		                                                        <td>
		                                                            <input type="text" name="FTA_CD" id="FTA_CD" style="width:130px"/>
		                                                            <a href="javascript:MMA017_01.control.ftaApplyOfOrigin('Y');" id="MMA017_01_btn_15" class="btnApply"><spring:message code="TXT_COO_ALL_Y"/></a>
		                                                            <a href="javascript:MMA017_01.control.ftaApplyOfOrigin('N');" id="MMA017_01_btn_16" class="btnReject"><spring:message code="TXT_COO_ALL_N"/></a>
		                                                        </td>
		                                                        <td scope="row">RCEP <spring:message code="TXT_COO_REGION_NATION"/></td>
                                                                <td>
                                                                    <input type="text" name="NATION_CD" id="NATION_CD" style="width:130px"/>
                                                                    <a href="javascript:MMA017_01.control.ftaApplyNationOfRcep('Y');" class="btnApply"><spring:message code="APYAL"/></a>
                                                                </td>
		                                                    </tr>
		                                                 </tbody>
		                                             </table>
		                                         </form>
		                                    </div>
		                                    <div id="MMA017_01_layout_02" data-options="region:'center',border:false">
		                                        <table id="MMA017_01_grid_01"></table>
		                                    </div>
		                                    <div style="display:none">
                                                <table id="MMA017_01_grid_04"></table>
                                                <table id="MMA017_01_grid_05"></table>
                                            </div>
		                                </div>
		                                <span id="MMA017_01_grid_02_SPAN"><table id="MMA017_01_grid_02"></table></span>
		                            </div>
		                        </div>
		                    </div>
		                    <div data-options="region:'center',border:false" style="padding-top:6px;">
		                        <div class="easyui-layout" data-options="fit:true">
		                            <div data-options="region:'north',border:false">
				                        <div class="easyui-layout" data-options="fit:true">
				                            <div data-options="region:'north',border:false" style="height:19px;">
				                                <div class="h2_etc">
				                                    <p class="h2"><spring:message code="MSG_ROW,INFMT"/><%--품목별 원산지 정보--%></p>
				                                    <p class="h2_btn">
				                                        <span id="MMA017_01_span_01"><a href="javascript:MMA017_01.control.applyRowData();" id="MMA017_01_btn_05" class="btn" style="padding:0 9px;text-align:center;height:16px;line-height:16px;border-radius:2px"><spring:message code="APYDT"/><%--수정--%></a></span>
				                                    </p>
				                                </div>
				                            </div>
				                            <div data-options="region:'center',border:false">
				                                <form id="MMA017_01_form_02" name="MMA017_01_form_02" method="POST">
				                                    <table class="dataT" style="margin-bottom:0px">
				                                        <caption><spring:message code="MSG_ROW, INFMT"/><%--데이타 테이블--%></caption>
				                                        <colgroup>
				                                            <col width="130px;" />
				                                            <col width=";" />
				                                        </colgroup>
				                                        <tbody>
				                                            <tr>
				                                                <th scope="row" class="point2">
				                                                    <spring:message code="LTIT, CODE"/>
				                                                </th>
				                                                <td>
				                                                    <input type="text" name="ITEM_CD" id="ITEM_CD" readonly class="input_readOnly" style="width:99%;"/> 
				                                                </td>
				                                            </tr>
				                                            <tr>
                                                                <th scope="row" class="point2">
                                                                    <spring:message code="LTIT,NAME"/>
                                                                </th>
                                                                <td>
                                                                    <input type="text" name="ITEM_NAME" id="ITEM_NAME" readonly class="input_readOnly" style="width:99%;"/>
                                                                </td>
                                                            </tr>
				                                            <tr>
				                                                <th scope="row" class="point2">
				                                                    <spring:message code="FTA,CODE, /, NAME"/>
				                                                </th>
				                                                <td>
				                                                    <input type="text" name="FTA_CD" id="FTA_CD" readonly class="input_readOnly" style="width:60px;"/> /
				                                                    <input type="text" name="FTA_NAME" id="FTA_NAME" readonly class="input_readOnly" style="width:120px;"/>
				                                                </td>
				                                            </tr>
				                                            <tr>
				                                                <th scope="row" class="point2">
				                                                    <spring:message code="HS,CODE"/>
				                                                </th>
				                                                <td>
				                                                    <input type="text" name="HS_CODE" id="HS_CODE" class="easyui-validatebox" validType="['minlength[6]','integer']" onkeyup="javascript:MMA017_01.control.selectRuleContents();" style="width:90px;"/>
				                                                </td>
				                                            </tr>
				                                            <tr>
				                                                <th scope="row" class="point2">
				                                                    <spring:message code="TXT_RULE_CODE"/>
				                                                </th>
				                                                <td>
				                                                    <input name="RULE_CONTENTS" id="RULE_CONTENTS" style="width:98%;"/>
				                                                </td>
				                                            </tr>
				                                            <tr>
				                                                <th id="MMA017_01_th_01" scope="row">
				                                                    <span id="MMA017_01_label_01"><spring:message code="RVC, RATE"/></span>
				                                                </th>
				                                                <td>
				                                                    <input type="text" name="RVC_RATE" id="RVC_RATE" class="easyui-validatebox" validType="['number']" style="width:50px;text-align:right;"/> %
				                                                </td>
				                                            </tr>
				                                            <tr>
				                                                <th scope="row" class="point2">
				                                                    <spring:message code="TXT_MEET_YN"/>
				                                                </th>
				                                                <td>
				                                                    <input name="FTA_CO_YN" id="FTA_CO_YN" style="width:100px;"/>
				                                                </td>
				                                            </tr>
				                                            <tr>
                                                                <th scope="row">
                                                                    <spring:message code="TXT_COO_REGION_NATION"/>
                                                                </th>
                                                                <td>
                                                                    <input type="text" name="ORIGIN_NATION_CD" id="ORIGIN_NATION_CD" value="${ORIGIN_NATION_CD}" search="MMA017_01.control.applyRowData" style="width:40px;text-transform:uppercase;"/>
									                                <input type="text" name="ORIGIN_NATION_NAME" id="ORIGIN_NATION_NAME" value="${ORIGIN_NATION_NAME}" readonly style="width:140px;"/>
									                                <span id="MMA017_01_span_05"><a href="javascript:MMA017_01.dialog.openDialog_5();"><img src="/images/sample/btn_sch.gif" alt="검색" /></a></span>
                                                                </td>
                                                            </tr>
				                                            <c:if test="${NON_ORGIN_RESN_YN eq 'Y'}">
				                                            <tr>
                                                                <th scope="row">
                                                                    <spring:message code="NOMEET, REASN"/><br>(<spring:message code="NOORG,REASN"/>)
                                                                </th>
                                                                <td>
                                                                    <input name="NONORIGIN_REASON_CD" id="NONORIGIN_REASON_CD" style="width:220px;"/>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <th scope="row">
                                                                    <spring:message code="NOMEET, REASN,DSCPT"/>
                                                                </th>
                                                                <td>
                                                                <c:if test="${NON_ORGIN_RESN_REQ_YN eq 'Y'}">
                                                                    <textarea name="NONORIGIN_REASON_DESC" id="NONORIGIN_REASON_DESC" placeholder="<spring:message code="TXT_NOT_MEET_REASON_INPUT"/>" style="width:98%;height:50px;"></textarea>
                                                                </c:if>
                                                                <c:if test="${NON_ORGIN_RESN_REQ_YN eq 'N'}">
                                                                    <textarea name="NONORIGIN_REASON_DESC" id="NONORIGIN_REASON_DESC" style="width:98%;height:50px;"></textarea>
                                                                </c:if>
                                                                </td>
                                                            </tr>
                                                            </c:if>
				                                        </tbody>
				                                    </table>
				                                </form>
				                            </div>
				                        </div>    
		                            </div>
		                            <div data-options="region:'center', border:false" style="padding-top:9px;">
		                                <div class="easyui-layout" data-options="fit:true">
		                                    <div class="easyui-layout" data-options="fit:true">
			                                    <div data-options="region:'north',border:false" class="h2_etc"  style="height:19px;">
		                                            <p class="h2"><spring:message code="TXT_EVDC_FILE, LIST"/></p>
		                                            <p class="h2_btn">
		                                                <span id="MMA017_01_span_03" style="display:none;"><a href="javascript:MMA017_01.file.evcdFileDownload();" class="btnExlDown"><spring:message code="FILE, DOWN"/></a></span>
			                                            <span id="MMA017_01_span_04" style="display:none;"><a href="javascript:MMA017_01.control.deleteEvcdFile();" class="btnMainDelete"><spring:message code="DEL"/></a></span>
		                                            </p>
			                                    </div>
			                                    <div data-options="region:'center', border:false">
			                                        <form id="MMA017_01_form_04" name="MMA017_01_form_04" method="POST">
			                                            <input type="hidden" id="COMPANY_CD" name="COMPANY_CD"/>
			                                            <input type="hidden" id="VENDOR_CD" name="VENDOR_CD"/>
			                                            <input type="hidden" id="CO_DOC_NO" name="CO_DOC_NO"/>
			                                            <input type="hidden" id="EV_DOC_TYPE" name="EV_DOC_TYPE"/>
			                                            <input type="hidden" id="FILE_SEQ" name="FILE_SEQ"/>
			                                        </form>
			                                        <table id="MMA017_01_grid_03"></table>
			                                    </div>
			                                </div>
			                            </div>
			                        </div>
			                    </div>
			                </div>
	                    </div>
	                </div>
	            </div>
	            <div id="MMA017_01_main_win" class="easyui-window" title="Processing" data-options="modal:true,closed:true,closable:false,resizable:false,minimizable:false,maximizable:false,collapsible:false" style="width:400px; height:120px; padding:0;">
	                <div class="easyui-layout" data-options="fit:true">
	                    <div data-options="region:'center', border:false" style="padding:10px;background:#5583a5;color:#fff;">
	                        <spring:message code="MSG_LOADING_WAIT"/>
	                    </div>
	                    <div data-options="region:'south', border:false" style="height:45px;padding:10px;background:#5583a5;color:#fff;">
	                        <div id="MMA017_01_de_progress" class="easyui-progressbar" style="width:360px;"></div>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
    </div>
</div>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-A017_01.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>