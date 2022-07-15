<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : MM-A019_01.jsp
  * @Description : 멕시코 확인서(증명서) 상세 조회 및 제출
  * @Modification Information
  *
  * author carlos
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMA019_01_dailog_01"으로 하셔야 합니다.
  *
  *
  **************************** 팝업 호출시 필수 파라미터 ID명
  * 선택한그리드데이터 : gridData
  * 호출한 flag        : flag
  * 부모창ID           : TARGET_PID
  * 회사코드           : COMPANY_CD
  * 사업부코드         : DIVISION_CD
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
	<c:if test="${flag == 'view'}"><div data-options="region:'north',border:false" style="overflow:hidden;"></c:if>
	<c:if test="${flag != 'view'}"><div data-options="region:'north',border:false" style="overflow:hidden;height:26px;"></c:if>
	    <div class="btn_R">
            <a href="javascript:MMA019_01.control.saveMasterDetailInfo();" id="MMA019_01_btn_01" class="btnPopIssue"><spring:message code="SUNMT"/><%--제출--%></a>
            <a href="javascript:MMA019_01.control.saveOriginModifyInfo();" id="MMA019_01_btn_03" class="btnSave" style="display:none;"><spring:message code="MOD"/><%--수정--%></a>
            <a href="javascript:MMA017_01.control.insertEvcdFile();" id="MMA019_01_btn_02" class="btnSave" style="display:none;"><spring:message code="EVID,TXT_FILE_ADD"/><%--증빙파일첨부--%></a>
            <a href="javascript:MMA019_01.dialog.closeDialog_top();" id="MMA019_01_btn_04" class="btnPopClose" style="display:none;"><spring:message code="CLOSE"/><%--닫기--%></a>
        </div>
    </div>
    <div data-options="region:'center',border:false">
        <div class="easyui-layout" data-options="fit:true">
            <div data-options="region:'north',border:false">
                <div class="h2_etc">
                    <p class="h2"><spring:message code="CNFDO, TXT_BASICINFO"/><%--확인서 기본정보--%></p><p id="MMA019_01_p_01" style="color:red;display:none;">(* <spring:message code="MSG_END_DATE_NEXT_ISSUE"/>)</p>
                </div>
            </div>
            <div data-options="region:'center',border:false">
                <div class="easyui-layout" data-options="fit:true">
                    <div data-options="region:'north',border:false" style="overflow:hidden;">
	                    <form id="MMA019_01_form_01" name="MMA019_01_form_01" enctype="multipart/form-data" method="POST">
	                        <input type="hidden" id="gridData"              name="gridData"              value='${gridData}'/>
	                        <input type="hidden" id="flag"                  name="flag"                  value="${flag}"/>
	                        <input type="hidden" id="TARGET_PID"            name="TARGET_PID"            value="${TARGET_PID}"/>
	                        <input type="hidden" id="COMPANY_CD"            name="COMPANY_CD"            value="${COMPANY_CD}"/>
	                        <input type="hidden" id="DIVISION_CD"           name="DIVISION_CD"           value="${DIVISION_CD}"/>
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
	                        <input type="hidden" id="CUSTOMER_HS_CODE_YN"   name="CUSTOMER_HS_CODE_YN"   value="N"/>
	                        <input type="hidden" id="HUB_VENDOR_YN"         name="HUB_VENDOR_YN"         value="N"/>
	                        <input type="hidden" id="CO_ISSUE_STATUS"       value="${CO_ISSUE_STATUS}"/>
	                        <input type="hidden" id="REQ_TYPE"              name="REQ_TYPE"              value="${REQ_TYPE}"/>
                            <table class="dataT">
                                <caption><spring:message code="DTA, TB"/><%--데이타 테이블--%></caption>
                                <colgroup>
                                    <col width="115px;" />
                                    <col width="90px;" />
                                    <col width="210px;" />
                                    <col width="120px;" />
                                    <col width=";" />
                                    <col width="120px;" />
                                    <col width=";" />
                                </colgroup>
                                <tbody>
                                    <tr>
                                        <th scope="row" class="point2" colspan="2">
                                            <div style="float:left;"><spring:message code="TXT_SEARCH_COO_CERTIFY_NO"/></div>
                                            <span id="MMA019_01_form_01_chkAuto" style="float:right;">
                                                <input type="checkbox" name="chkAuto" id="chkAuto" onclick="javascript:MMA019_01.ui.onChangeChkAuto();"  checked/> <spring:message code="TXT_AUTO_GENERATION"/>
                                            </span>
                                        </th>
                                        <td>
                                            <input type="text" name="CO_DOC_NO" id="CO_DOC_NO" value="${CO_DOC_NO}" class="easyui-validatebox" required="true" validType="['maxlength[50]']" style="width:98%"/>
                                        </td>
                                        <th scope="row" class="point2">
                                            <spring:message code="PATNE"/><%--거래처--%>
                                        </th>
                                        <td>
                                            <input type="text" name="VENDOR_NAME" id="VENDOR_NAME" value="${VENDOR_NAME}"
                                                   class="input_readOnly" title="<spring:message code="TXT_DEAL_VENDOR"/>" readonly style="width:98%"/>
                                        </td>
                                        <th scope="row">
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
                                            <spring:message code="TXT_COVER_DATE"/><%--포괄기간--%>
                                        </th>
                                        <td colspan="3">
                                            <span id="MMA019_01_div_01" style="display:none;"><input type="text" name="APPLY_DATE" id="APPLY_DATE" value="${APPLY_DATE}" style="width:95px;"
                                                   data-options="validType:{comparedate:['MMA019_01_form_01','DATE','END_DATE']}" /></span>
                                            ~ <input type="text" name="END_DATE" id="END_DATE" value="${END_DATE}" style="width:95px;"/>
                                            <span id="MMA019_01_span_01" style="display:none;"> 
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
                                        <th scope="row" class="point2">
                                            <spring:message code="FUNM"/><%--성명--%>
                                        </th>
                                        <td>
                                            <input type="text" name="VENDOR_CHARGE_NAME" id="VENDOR_CHARGE_NAME" value="${VENDOR_CHARGE_NAME}"
                                                   class="easyui-validatebox" required="true" validType="['maxlength[100]']" style="width:98%"
                                                   title="<spring:message code="FUNM"/>" />
                                        </td>
                                        <th scope="row" class="point2">
                                            <spring:message code="CNTACT"/><%--연락처--%>
                                        </th>
                                        <td>
                                            <input type="text" name="VENDOR_CHARGE_PHONE_NO" id="VENDOR_CHARGE_PHONE_NO" value="${VENDOR_CHARGE_PHONE_NO}"
                                                   class="easyui-validatebox" required="true" validType="['telnum','maxlength[20]']" style="width:98%"
                                                   title="<spring:message code="CNTACT"/>" />
                                        </td>
                                        <th scope="row">
                                            <spring:message code="TXT_EMAIL"/><%--이메일--%>
                                        </th>
                                        <td>
                                            <input type="text" name="VENDOR_CHARGE_EMAIL" id="VENDOR_CHARGE_EMAIL" value="${VENDOR_CHARGE_EMAIL}"
                                                   class="easyui-validatebox" validType="['maxlength[30]','email']" style="width:98%"
                                                   title="<spring:message code="TXT_EMAIL"/>" />
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
                                                   class="easyui-validatebox" required="true" validType="['maxlength[50]','telnum']" style="width:98%"
                                                   title="<spring:message code="TELNM"/>" />
                                        </td>
                                        <th scope="row">
                                            <spring:message code="FAX"/><%--팩스번호--%>
                                        </th>
                                        <td>
                                            <input type="text" name="PRODUCER_FAX_NO" id="PRODUCER_FAX_NO" value="${PRODUCER_FAX_NO}"
                                                   class="easyui-validatebox" validType="['maxlength[50]','telnum']" style="width:98%"
                                                   title="<spring:message code="FAX"/>" />
                                        </td>
                                        <th scope="row">
                                            <spring:message code="TXT_EMAIL"/><%--이메일--%>
                                        </th>
                                        <td>
                                            <input type="text" name="PRODUCER_EMAIL" id="PRODUCER_EMAIL" value="${PRODUCER_EMAIL}"
                                                   class="easyui-validatebox" validType="['maxlength[50]','email']" style="width:98%"
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
                                            <span id="MMA019_01_form_02_chkAuto" style="float:right;">
                                                <input type="checkbox" name="chkFile" id="chkFile" onclick="javascript:MMA019_01.ui.onChangeFilePassChk();"  checked/> <spring:message code="AUTO,ATACH"/>
                                            </span>
                                        </th>
                                        <td colspan="5" style="height:22px;">
                                            <span id="MMA019_01_form_01_PRF_FILE">
                                                <input type="file" name="PRF_FILE" id="PRF_FILE" class="easyui-validatebox" required="true" onchange="javascript:MMA019_01.file.setFiles(this);" style="width:80%;"/>
                                            </span>
                                            <span id="MMA019_01_form_01_PRF_FILE_DW" style="display:none;">
                                                <a href="javascript:MMA019_01.control.selectProofFile();" id="MMA019_01_form_01_PRF_FILE_DOWN">
                                                    <span><img src="/images/icon/btn_file.gif" boder="0"/></span> <span id="MMA019_01_form_01_PRF_FILE_NAME_SPAN"></span>
                                                </a>
                                            </span>
                                        </td>
                                    </tr>
                                    <tr id="MMA019_01_tr_01" style="display:none;">
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
                    <div data-options="region:'center',border:false" style="padding-top:3px;">
                        <div class="easyui-layout" data-options="fit:true">
                            <div data-options="region:'north',border:false">
                                <div class="h2_etc">
                                    <p class="h2"><spring:message code="TXT_ITEMBY_ORG_INFO"/><%--품목별 원산지 정보--%></p>
                                    <p class="h2_btn">
                                        <a href="javascript:MMA019_01.dialog.openDialog_1('update');" id="MMA019_01_btn_32" class="btnEdit"><spring:message code="MSG_ROW,MOD"/><%--수정--%></a>
                                        <a href="javascript:MMA019_01.control.selectItemListQuest();" id="MMA019_01_btn_36" class="btnRefresh"><spring:message code="INIT"/></a>
                                        <a href="javascript:MMA019_01.dialog.openDialog_2();" id="MMA019_01_btn_34" class="btnImport"><spring:message code="DATA, UPLOD"/><%--자료업로드--%></a>
                                        <a href="javascript:MMA019_01.excel.excelDownload_1();" id="MMA019_01_btn_35" class="btnExport"><spring:message code="FORM, DOWN"/><%--SAMPLE--%></a>
                                        <a href="javascript:MMA019_01.excel.excelDownload_2();" id="MMA019_01_btn_40" class="btnExlDown" style="display:none;"><spring:message code="EXCEL, DOWN"/><%--엑셀다운로드--%></a>
                                    </p>
                                </div>
                            </div>
                            <div data-options="region:'center',border:false">
                               <div class="easyui-layout" data-options="fit:true">
                                    <div data-options="region:'north',border:false">
                                        <form id="MMA019_01_form_03" name="MMA019_01_form_03" method="POST">
                                            <table class="gridT" style="margin-bottom:0px;">
                                                <caption><spring:message code="DTA, TB"/><%--데이타 테이블--%></caption>
                                                <colgroup>
                                                    <col width="140px"/>
                                                    <col width=""/>
                                                </colgroup>
                                                <tbody>
                                                    <tr id="MMA019_01_tr_02">
                                                        <td scope="row"><spring:message code="TXT_MEET_YN"/></td>
                                                        <td>
                                                            <input type="text" name="FTA_CD" id="FTA_CD" style="width:130px"/>
                                                            <a href="javascript:MMA019_01.control.ftaApplyOfOrigin('Y');" id="MMA019_01_btn_15" class="btnApply"><spring:message code="TXT_COO_ALL_Y"/></a>
                                                            <a href="javascript:MMA019_01.control.ftaApplyOfOrigin('N');" id="MMA019_01_btn_16" class="btnReject"><spring:message code="TXT_COO_ALL_N"/></a>
                                                        </td>
                                                    </tr>
                                                 </tbody>
                                             </table>
                                         </form>
                                    </div>
                                    <div data-options="region:'center',border:false">
                                        <table id="MMA019_01_grid_01"></table>
                                    </div>
                                    <span id="MMA019_01_grid_02_SPAN"><table id="MMA019_01_grid_02"></table></span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="MMA019_01_main_win" class="easyui-window" title="Processing" data-options="modal:true,closed:true,closable:false,resizable:false,minimizable:false,maximizable:false,collapsible:false" style="width:400px; height:120px; padding:0;">
                    <div class="easyui-layout" data-options="fit:true">
                        <div data-options="region:'center', border:false" style="padding:10px;background:#5583a5;color:#fff;">
                            <spring:message code="MSG_LOADING_WAIT"/>
                        </div>
                        <div data-options="region:'south', border:false" style="height:45px;padding:10px;background:#5583a5;color:#fff;">
                            <div id="MMA019_01_de_progress" class="easyui-progressbar" style="width:360px;">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-A019_01.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>