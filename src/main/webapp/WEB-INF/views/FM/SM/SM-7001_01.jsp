<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : SM-7001_01.jsp
  * @Description : 회사정보 조회 및 수정
  * @Modification Information
  *
  * author carlos
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "SM7001_01_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body class="h2_etc">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'north',border:false,split:true">
            <div class="easyui-layout" data-options="fit:true">
                <div data-options="region:'north',border:false" class="h2_etc">
	                <p class="h2"><spring:message code="CONPY,INFMT"/><%--회사정보--%></p>
	                <p class="h2_btn">
	                    <a href="javascript:SM7001_01.control.updateCompanyInfo();" class="btnMainSave"><spring:message code="SAVE"/></a>
	                </p>
		        </div>
		        <div data-options="region:'center',border:false" style="padding-bottom:10px;overflow:hidden;">
		            <form id="SM7001_01_form_01" name="SM7001_01_form_01" enctype="multipart/form-data" method="post">
		                <input type="hidden" id="flag" name="flag" value="update"/>
		                <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
		                <input type="hidden" id="SESSION_COMPANY_CD" name="SESSION_COMPANY_CD" value="${_MEMBER.SESSION_COMPANY_CD}"/>
		                <input type="hidden" id="SESSION_USER_ID" name="SESSION_USER_ID" value="${_MEMBER.SESSION_USER_ID}"/>
		                <input type="hidden" id="DEFAULT_LANGUAGE" name="DEFAULT_LANGUAGE" value="${_MEMBER.SESSION_DEFAULT_LANGUAGE}"/>
		                <input type="hidden" id="CREATE_BY" name="CREATE_BY" value="${_MEMBER.SESSION_USER_ID}"/>
		                <input type="hidden" id="PARENT_COMPANY_CD" name="PARENT_COMPANY_CD" value="${_MEMBER.SESSION_PARENT_COMPANY_CD}"/>
		                <input type="hidden" id="AUTH_GROUP" name="AUTH_GROUP" value="${_MEMBER.SESSION_AUTH_GROUP}"/>
		                <table class="dataT" style="margin-bottom:0px;">
		                    <caption><spring:message code="DTA, TB"/><%--데이타 테이블--%></caption>
		                    <colgroup>
		                        <col width="200px" />
		                        <col width=";" />
		                        <col width="200px" />
		                        <col width=";" />
		                        <col width="200px" />
		                        <col width=";" />
		                    </colgroup>
		                    <tbody>
		                        <tr>
		                            <th scope="row">
		                                <spring:message code="TXT_COMPANY_CODE"/><%--회사명--%>
		                            </th>
		                            <td>
		                                <input type="text" name="COMPANY_CD" id="COMPANY_CD" class="input_readOnly" value="${_MEMBER.SESSION_COMPANY_CD}" readonly style="width:98%;"/>
		                            </td>
		                            <th scope="row">
		                                <spring:message code="TXT_COMPANY_NAME"/><%--회사명--%>
		                            </th>
		                            <td>
		                                <input type="text" name="COMPANY_NAME" id="COMPANY_NAME" value="${COMPANY_NAME}" style="width:98%;"/>
		                            </td>
		                            <th scope="row">
		                                <spring:message code="TXT_COM_NAME_ENG"/><%--영문회사명--%>
		                            </th>
		                            <td>
		                                <input type="text" name="COMPANY_NAME_ENG" id="COMPANY_NAME_ENG" value="${COMPANY_NAME_ENG}" 
		                                class="easyui-validatebox" style="width:98%;"/>
		                            </td>
		                        </tr>
		                        <tr>
		                            <th scope="row">
		                                <spring:message code="TXT_BUSINESSNO"/><%--사업자번호--%>
		                            </th>
		                            <td>
		                                <input type="text" name="BUSINESS_NO" id="BUSINESS_NO" value="${BUSINESS_NO}" 
		                                class="easyui-validatebox" style="width:98%;"/>
		                            </td>
		                            <th scope="row">
		                                <spring:message code="TXT_PRESIDENT_NAME"/><%--대표자명--%>
		                            </th>
		                            <td>
		                                <input type="text" name="PRESIDENT_NAME" id="PRESIDENT_NAME" value="${PRESIDENT_NAME}" 
		                                class="easyui-validatebox" style="width:98%;"/>
		                            </td>
		                            <th scope="row">
		                            <spring:message code="TXT_PRESIDENT_NAME_ENG"/><%--영문 대표자명--%>
		                            </th>
		                            <td>
		                                <input type="text" name="PRESIDENT_NAME_ENG" id="PRESIDENT_NAME_ENG" value="${PRESIDENT_NAME_ENG}" 
		                                class="easyui-validatebox" style="width:98%;"/>
		                            </td>
		                            </tr>
		                            <tr>
		                            <th scope="row">
		                                <spring:message code="CONPY, TELNM"/><%--회사 전화번호--%>
		                            </th>
		                            <td>
		                                <input type="text" name="COM_PHONE_NO" id="COM_PHONE_NO" value="${COM_PHONE_NO}" 
		                                class="easyui-validatebox" style="width:98%;"/>
		                            </td>
		                            <th scope="row">
		                                <spring:message code="CONPY, FAX"/><%--회사 FAXNO--%>
		                            </th>
		                            <td>
		                                <input type="text" name="COM_FAX_NO" id="COM_FAX_NO" value="${COM_FAX_NO}" 
		                                class="easyui-validatebox" style="width:98%;"/>
		                            </td>
		                            <th scope="row">
		                                <spring:message code="CONPY, EMAIL"/><%--회사 E-MAIL--%>
		                            </th>
		                            <td>
		                                <input type="text" name="COM_EMAIL" id="COM_EMAIL" value="${COM_EMAIL}" 
		                                class="easyui-validatebox" style="width:98%;"/>
		                            </td>
		                        </tr>
		                        <tr>
		                            <th scope="row">
		                                <spring:message code="THPIC"/><%--담당자--%>
		                            </th>
		                            <td>
		                                <input type="text" name="OFFICER_NAME" id="OFFICER_NAME" value="${OFFICER_NAME}" 
		                                class="easyui-validatebox" style="width:98%;"/>
		                            </td>
		                            <th scope="row">
		                                <spring:message code="THPIC, TELNM"/><%--담당자 전화번호--%>
		                            </th>
		                            <td>
		                                <input type="text" name="OFFICER_PHONE_NO" id="OFFICER_PHONE_NO" value="${OFFICER_PHONE_NO}" 
		                                class="easyui-validatebox" style="width:98%;"/>
		                            </td>
		                            <th scope="row">
		                                <spring:message code="THPIC, EMAIL"/><%--담당자 E-MAIL--%>
		                            </th>
		                            <td>
		                                <input type="text" name="OFFICER_EMAIL" id="OFFICER_EMAIL" value="${OFFICER_EMAIL}" 
		                                class="easyui-validatebox" style="width:98%;"/>
		                            </td>
		                        </tr>
		                        <tr>
		                            <th scope="row">
		                                <spring:message code="ADDRS"/><%--회사주소--%>
		                            </th>
		                            <td colspan="3">
		                                <input type="text" name="ADDRESS1" id="ADDRESS1" value="${ADDRESS1}" class="easyui-validatebox" style="width:99%;"/>
		                            </td>
		                            <th scope="row"><spring:message code="MGT, SITE"/></th>
                                    <td>
                                        <input type="text" name="MANAGER_SITE_URL" id="MANAGER_SITE_URL" value="${MANAGER_SITE_URL}" style="width:98%;"/>
                                    </td>
		                        </tr>
		                        <tr>
		                            <th scope="row">
		                                <spring:message code="ADDRS"/> (<spring:message code="ENGLS"/>)<%--회사주소--%>
		                            </th>
		                            <td colspan="3">
		                                <input type="text" name="ADDRESS2" id="ADDRESS2" value="${ADDRESS2}" class="easyui-validatebox" style="width:99%;"/>
		                            </td>
		                            <th scope="row"><spring:message code="PATNE, SITE"/></th>
                                    <td>
                                        <input type="text" name="SUPPLIER_SITE_URL" id="SUPPLIER_SITE_URL" value="${SUPPLIER_SITE_URL}" style="width:98%;"/>
                                    </td>
		                        </tr>
		                        <tr>
		                            <th scope="row">
		                                 <spring:message code="ORG, CNFIT, EXPER, ORNOT"/><%--원산지 인증 수출자 여부--%>
		                            </th>
		                            <td>
		                                <input type="text" name="CO_CERTIFIED_EXPORTER_YN" id="CO_CERTIFIED_EXPORTER_YN" value="${CO_CERTIFIED_EXPORTER_YN}" style="width:90px;" title="<spring:message code="ORG, CNFIT, EXPER, ORNOT"/>"/>
		                                <input type="text" name="CO_CERTIFIED_EXPORTER_NO" id="CO_CERTIFIED_EXPORTER_NO" readonly class="input_readOnly" style="width:60%;"/>
		                            </td>
		                            <th scope="row">
		                                FTA PASS(<spring:message code="CONPY,/,INBOX,CODE"/>)
		                            </th>
		                            <td>
		                                <input type="text" name="PASS_COMPANY_CD" id="PASS_COMPANY_CD" style="width:46%"/>
		                                <input type="text" name="PASS_DOCBOX_NO" id="PASS_DOCBOX_NO" style="width:48%"/>
		                            </td>
		                            <th scope="row">
		                                <spring:message code="LINSE, MSSNM"/><%--라이센스 번호--%>
		                            </th>
		                            <td>
		                                <input type="text" name="LICENSE_KEY" id="LICENSE_KEY" readonly class="input_readOnly" value="${LICENSE_KEY}"  style="width:98%"/>
		                            </td>
		                        </tr>
		                        <tr>
		                            <th scope="row" rowspan="2">Logo
		                            </th>
                                    <td>
				                        <input type="file" name="CI_IMAGE" id="CI_IMAGE" style="width:430px;"/>
		                            </td>
		                            <th scope="row" rowspan="2">Stamp
		                            </th>
                                    <td>
                                        <input type="file" name="STAMP_IMAGE" id="STAMP_IMAGE" style="width:430px;"/>
                                    </td>
                                    <td colspan="2" rowspan="2">
                                        <span>Logo : <img src="" id="SM7001_01_img_01" name="SM7001_01_img_01" style="width:100px;height:50px;border:solid 0.5px #ababab;border-radius:7px;"/></span>
                                        <span style="padding-left:5px;">Stamp : <img src="" id="SM7001_01_img_02" name="SM7001_01_img_02" style="width:100px;height:50px;border:solid 0.5px #ababab;border-radius:7px;"/></span>
                                    </td>
		                        </tr>
		                        <tr>
                                    <td>
                                        <input type="text" name="CI_IMAGE_NAME" id="CI_IMAGE_NAME" class="input_readOnly" readonly style="width:90%"/>
                                    </td>
                                    <td>
                                        <input type="text" name="STAMP_IMAGE_NAME" id="STAMP_IMAGE_NAME" class="input_readOnly" readonly style="width:90%"/>
                                    </td>
                                </tr>
		                    </tbody>
		                </table>
		            </form>
		        </div>
		    </div>
        </div>
        <div data-options="region:'center',border:false">
            <div class="easyui-layout" data-options="fit:true">
                <div data-options="region:'north',border:false" class="sec_tit" style="overflow:hidden;">
                    <ul class="tab_area">
                        <li id="SM7001_01_tabMove01" class="on"><a href="javascript:SM7001_01.ui.divTabMove('1');"><spring:message code="RELATE, CONPY"/></a></li><!--관련회사-->
                        <li id="SM7001_01_tabMove02" style="display:none;"><a href="javascript:SM7001_01.ui.divTabMove('2');"><spring:message code="SYS, OPT"/></a></li><!--시스템옵션-->
                        <li id="SM7001_01_tabMove03" style="display:none;"><a href="javascript:SM7001_01.ui.divTabMove('3');">HUB <spring:message code="SETTING"/></a></li><!--HUB설정-->
                    </ul>
                </div>
                <div data-options="region:'center',border:false">
                    <div id="SM7001_01_tabs_01" class="easyui-tabs" data-options="fit:true,showHeader:false,plain:true">
				        <div title="1">
				            <div class="easyui-layout" data-options="fit:true">
				                <div data-options="region:'north',border:false" class="h2_etc">
	                                <p class="h2"><spring:message code="RELATE, CONPY, LIST"/></p>
                                    <p id="SM7001_01_div_10" class="h2_btn" style="display:none;">
	                                    <a href="javascript:SM7001_01.dialog.openDialog_4();" class="btnNewRegiste"><spring:message code="REGER"/><%--등록--%></a>
	                                    <a href="javascript:SM7001_01.control.deleteCompnayGroupCd();" class="btnMainDelete"><spring:message code="DEL"/><%--삭제--%></a>
                                    </p>
	                            </div>
	                            <div data-options="region:'center',border:false">
	                                <form id="SM7001_01_form_04" name="SM7001_01_form_04" method="post">
	                                    <input type="hidden" id="COMPANY_CD" name="COMPANY_CD"/>
	                                    <input type="hidden" id="PARENT_COMPANY_CD" name="PARENT_COMPANY_CD"/>
	                                </form>
	                                <table id="SM7001_01_grid_04" ></table>
	                            </div>
				            </div>
				        </div>
				        <div title="2">
				            <div class="easyui-layout" data-options="fit:true">
				                <div id="SM7001_01_div_11" data-options="region:'center',border:false" style="display:none;">
                                    <div class="easyui-layout" data-options="fit:true">
			                            <div data-options="region:'north',border:false" class="h2_etc">
		                                    <p class="h2"><spring:message code="FTA, SYS, OPT"/>
		                                    <p class="h2_btn">
		                                        <a href="javascript:SM7001_01.control.updateSysconfigOptionInfo();" class="btnMainSave"><spring:message code="SAVE"/></a>
		                                    </p>
		                                </div>
		                                <div data-options="region:'center',border:false">
			                                <form id="SM7001_01_form_02" name="SM7001_01_form_02" method="post">
			                                    <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${_MEMBER.SESSION_COMPANY_CD}"/>
			                                    <table class="dataT">
			                                        <colgroup>
			                                            <col width="200px" />
			                                            <col width=";" />
			                                            <col width="200px" />
			                                            <col width=";" />
			                                        </colgroup>
			                                        <tbody>
			                                            <tr>
                                                               <th scope="row"><spring:message code="LINSE, MSSNM"/><%--라이센스 번호--%></th>
                                                               <td>
                                                                   <input type="text" name="TOMS_FTA_CERT_KEY" id="TOMS_FTA_CERT_KEY" value="${TOMS_FTA_CERT_KEY}"  style="width:98%"/>
                                                               </td>
                                                               <th scope="row">Cloud <spring:message code="ADDRS"/>(URL)</th>
                                                               <td>
                                                                   <input type="text" name="CLOUD_SERVER_URL" id="CLOUD_SERVER_URL" value="${CLOUD_SERVER_URL}" style="width:98%;"/>
                                                               </td>
                                                        </tr>
                                                        <tr>  
                                                               <th scope="row"><spring:message code="CC_엑셀 최대표시 Row수"/></th>
                                                               <td>
                                                                   <input type="text" name="EXCEL_MAX_ROWNUM" id="EXCEL_MAX_ROWNUM" class="easyui-numberbox" value="${EXCEL_MAX_ROWNUM}"  style="width:120px"/>
                                                                   <label>* 초과 시 CSV파일로 다운로드됨</label>
                                                               </td>
                                                               <th scope="row"><spring:message code="CC_CSV 최대표시 Row수"/></th>
                                                               <td>
                                                                   <input type="text" name="CSV_MAX_ROWNUM" id="CSV_MAX_ROWNUM" class="easyui-numberbox" value="${CSV_MAX_ROWNUM}"  style="width:120px"/>
                                                                   <label>* 초과 시 오류로 처리됨</label>
                                                               </td>
                                                           </tr>
			                                        </tbody>
			                                    </table>
			                                    <p class="h2_sub"><spring:message code="CC_원산지 판정"/></p>
                                                    <table class="gridT">
                                                         <colgroup>
                                                             <col width="200px" />
                                                             <col width=";" />
                                                             <col width="200px" />
                                                             <col width=";" />
                                                             <col width="200px" />
                                                             <col width=";" />
                                                         </colgroup>
                                                         <tbody>
                                                             <tr>
                                                                 <th scope="row">
                                                                     <spring:message code="CC_원산지 판정방법"/><%--원산지 판정방법 --%>
                                                                 </th>
                                                                 <td>
                                                                     <input type="text" name="ORIGIN_TRACE_MTH" id="ORIGIN_TRACE_MTH" value="${ORIGIN_TRACE_MTH}" style="width:160px;"/>
                                                                 </td>
                                                                 <th scope="row">
                                                                     <spring:message code="IVASM"/><%--재평가방법--%>
                                                                 </th>
                                                                 <td>
                                                                     <input type="text" name="EVALU_TYPE" id="EVALU_TYPE" value="${EVALU_TYPE}" style="width:127px;"/>
                                                                 </td>
                                                                 <th scope="row">
                                                                     <spring:message code="CC_원산지 지위 결정방법"/><%--원산지 지위 결정방법 --%>
                                                                 </th>
                                                                 <td>
                                                                     <input type="text" name="ORIGIN_DECSN_TYPE" id="ORIGIN_DECSN_TYPE" value="${ORIGIN_DECSN_TYPE}" style="width:127px;"/>
                                                                 </td>
                                                                 
                                                             </tr>
                                                             <tr>
                                                                 <th scope="row">
                                                                     <spring:message code="IVTUP"/>(<spring:message code="MONTH"/>)<%--재고회전기간(개월)--%>
                                                                 </th>
                                                                 <td>
                                                                     <span><spring:message code="MAX,MONTH"/> : </span>
                                                                     <input type="text" name="AGING" id="AGING" value="${AGING}" class="easyui-validatebox" required="true" validType="['number']" style="width:50px;"/>
                                                                     / <span><spring:message code="BUFER,MONTH"/> : </span>
                                                                     <input type="text" name="AGING_BUFFER" id="AGING_BUFFER" value="${AGING_BUFFER}" class="easyui-validatebox" required="true" validType="['number']" style="width:50px;"/>
                                                                 </td>
                                                                 <th scope="row">
                                                                     <spring:message code="TXT_LEDGER_TRACKING,STNDS"/><%--수불부 추적기준--%>
                                                                 </th>
                                                                 <td>
                                                                     <input type="text" name="PROD_YYYYMM" id="PROD_YYYYMM" value="${PROD_YYYYMM}" style="width:127px;"/>
                                                                 </td>
                                                                 <th scope="row">
                                                                     <spring:message code="CC_PE 판정범위"/><%-- PE 판정범위 --%>
                                                                 </th>
                                                                 <td>
                                                                     <input type="text" name="PE_RUN_TARGET" id="PE_RUN_TARGET" value="${PE_RUN_TARGET}" style="width:127px;"/>
                                                                 </td>
                                                             </tr>
                                                             <tr>
                                                                 <th scope="row">
                                                                      <spring:message code="TXT_IM_APPLY_YN,ORNOT"/><%--중간재 적용여부--%>
                                                                 </th>
                                                                 <td>
                                                                     <input id="IM_ITEM_YN" name="IM_ITEM_YN" value="${IM_ITEM_YN}" style="width:80px;"/>
                                                                 </td>
                                                                 <th scope="row">
                                                                     <spring:message code="RSDUAL, APYDT,ORNOT"/><%--부산물 적용여부 --%>
                                                                 </th>
                                                                 <td>
                                                                     <input type="text" name="RESIDUAL_CO_YN" id="RESIDUAL_CO_YN" value="${RESIDUAL_CO_YN}" style="width:80px;"/>
                                                                 </td>
                                                                 <th scope="row">
                                                                     <spring:message code="RVC, DTSTA,ORNOT"/><%--RVC 판정여부 --%>
                                                                 </th>
                                                                 <td>
                                                                     <input type="text" name="RVC_DETERMIN_YN" id="RVC_DETERMIN_YN" value="${RVC_DETERMIN_YN}" style="width:80px;"/>
                                                                 </td>
                                                             </tr>
                                                             <tr>
                                                                 <th scope="row">
                                                                     <spring:message code="DEMIN, DTSTA,ORNOT"/>(<spring:message code="AMUNT"/>)<%--미소기준(금액) --%>
                                                                 </th>
                                                                 <td>
                                                                     <input type="text" name="AMOUNT_DETERMIN_YN" id="AMOUNT_DETERMIN_YN" value="${AMOUNT_DETERMIN_YN}" style="width:80px;"/>
                                                                 </td>
                                                                 <th scope="row">
                                                                     <spring:message code="DEMIN, DTSTA,ORNOT"/>(<spring:message code="QTYUS"/>)<%--미소기준(소요량) --%>
                                                                 </th>
                                                                 <td>
                                                                     <input type="text" name="USAGE_DETERMIN_YN" id="USAGE_DETERMIN_YN" value="${USAGE_DETERMIN_YN}" style="width:80px;"/>
                                                                 </td>
                                                                 <th scope="row">
                                                                     <spring:message code="DEMIN, DTSTA,ORNOT"/>(<spring:message code="WEG"/>)<%--미소기준(중량) --%>
                                                                 </th>
                                                                 <td>
                                                                     <input type="text" name="WEIGHT_DETERMIN_YN" id="WEIGHT_DETERMIN_YN" value="${WEIGHT_DETERMIN_YN}" style="width:80px;"/>
                                                                 </td>
                                                             </tr>
                                                             <tr>
                                                                 <th scope="row">
                                                                     SP <spring:message code="TXT_MEET_YN"/><%--가공공정기준 --%>
                                                                 </th>
                                                                 <td>
                                                                     <input type="text" name="SP_YN" id="SP_YN" value="${SP_YN}" style="width:80px;"/>
                                                                 </td>
                                                                 <th scope="row">
                                                                     WO <spring:message code="TXT_MEET_YN"/><%--완전생산기준 --%>
                                                                 </th>
                                                                 <td>
                                                                     <input type="text" name="WO_YN" id="WO_YN" value="${WO_YN}" style="width:80px;"/>
                                                                 </td>
                                                                 <th scope="row">
                                                                     CR <spring:message code="TXT_MEET_YN"/><%-- 화학반응기준 --%>
                                                                 </th>
                                                                 <td>
                                                                     <input type="text" name="CR_YN" id="CR_YN" value="${CR_YN}" style="width:80px;"/>
                                                                 </td>
                                                             </tr>
                                                         </tbody>
                                                     </table>
			                                      <p class="h2_sub"><spring:message code="CC_마감 설정"/></p>
                                                     <table class="gridT">
                                                         <colgroup>
                                                             <col width="200px" />
                                                             <col width=";" />
                                                             <col width="200px" />
                                                             <col width=";" />
                                                             <col width="200px" />
                                                             <col width=";" />
                                                         </colgroup>
                                                         <tbody>
                                                             <tr>
                                                                 <th scope="row">
                                                                     <spring:message code="FNISH, ADJST,DAY_QTY"/>
                                                                 </th>
                                                                 <td>
                                                                     <spring:message code="DAY,FNISH"/> : <input type="text" id="BATCH_DAILY_NUM" name="BATCH_DAILY_NUM" value="${BATCH_DAILY_NUM}" style="width:30px;"/> /
                                                                     <spring:message code="MONTH,FNISH"/> : <input type="text" id="BATCH_MONTHLY_NUM" name="BATCH_MONTHLY_NUM" value="${BATCH_MONTHLY_NUM}" style="width:30px;"/>
                                                                 </td>
                                                                 <th scope="row">
                                                                      <spring:message code="DAY,DPOST, FNISH,SCOPE"/>
                                                                 </th>
                                                                 <td>
                                                                     <input type="text" name="DAILY_SAELS_DE" id="DAILY_SAELS_DE" value="${DAILY_SAELS_DE}" style="width:127px;"/>
                                                                 </td>
                                                                 <th scope="row">
                                                                     <spring:message code="OPER, SEPAT"/><%--운영 구분 --%>
                                                                 </th>
                                                                 <td>
                                                                     <input type="text" name="RUN_TYPE" id="RUN_TYPE" value="${RUN_TYPE}" style="width:127px;"/>
                                                                 </td>
                                                             </tr>
                                                             <tr>
                                                                 <th scope="row">
                                                                      <spring:message code="ACUNT, FNISH"/><%--회계마감--%>
                                                                 </th>
                                                                 <td>
                                                                     <input id="CO_CLOSING_UNIT" name="CO_CLOSING_UNIT" value="${CO_CLOSING_UNIT}"/>
                                                                 </td>
                                                                 <th scope="row">
                                                                      <spring:message code="CC_이력 데이터 삭제주기(-개월)"/><%--이력 데이터 삭제주기(-개월)--%>
                                                                 </th>
                                                                 <td colspan="3">
                                                                     <input id="IF_DEL_MONTH" name="IF_DEL_MONTH" value="${IF_DEL_MONTH}" style="width:90px;"/>
                                                                 </td>
                                                             </tr>
                                                         </tbody>
                                                     </table>
			                                      <p class="h2_sub"><spring:message code="CC_BOM 설정"/></p>
			                                      <table class="gridT">
                                                         <colgroup>
                                                             <col width="200px" />
                                                             <col width=";" />
                                                             <col width="200px" />
                                                             <col width=";" />
                                                             <col width="200px" />
                                                             <col width=";" />
                                                         </colgroup>
                                                         <tbody>
			                                                 <tr>
			                                                     <th scope="row">
                                                                     <spring:message code="TXT_BOM_YN, TYP"/>
                                                                 </th>
                                                                 <td>
                                                                     <input type="text" name="BOM_TYPE" id="BOM_TYPE" value="${BOM_TYPE}" style="width:90px;"/>
                                                                     <input type="text" name="BOM_APPLY_ORDER" id="BOM_APPLY_ORDER" value="${BOM_APPLY_ORDER}" style="width:137px;"/>
                                                                 </td>
			                                                     <th scope="row">
                                                                     <spring:message code="TXT_CLOSING_PRE, BOM, DEL"/>
                                                                 </th>
                                                                 <td>
                                                                     <input type="text" name="BOM_MM_DELETE" id="BOM_MM_DELETE" value="${BOM_MM_DELETE}" style="width:127px;"/>
                                                                 </td>
                                                                 <th scope="row">
                                                                     <spring:message code="TXT_CLOSING_AFTER, BOM, TXT_ASSY_INFO, DEL"/>
                                                                 </th>
                                                                 <td>
                                                                     <input type="text" name="BOM_ASSY_DEL_YN" id="BOM_ASSY_DEL_YN" value="${BOM_ASSY_DEL_YN}" style="width:127px;"/>
                                                                 </td>
			                                                 </tr>
			                                                 <tr>
                                                                 <th scope="row">
                                                                      <spring:message code="QTYUS, TXT_FORMULA"/><%--소요량 계산식--%>
                                                                 </th>
                                                                 <td>
                                                                     <input name="BOM_REQ_SUM_YN" id="BOM_REQ_SUM_YN" value="${BOM_REQ_SUM_YN}" style="width:127px;"/>
                                                                 </td>
                                                                 <th scope="row">
                                                                      <spring:message code="BOM,UNIPR,CNVSN"/>
                                                                 </th>
                                                                 <td>
                                                                     <input type="text" name="BOM_PRICE_ADJUST_TYPE" id="BOM_PRICE_ADJUST_TYPE" value="${BOM_PRICE_ADJUST_TYPE}" style="width:127px;"/>
                                                                 </td>
                                                                 <th scope="row">
                                                                      <spring:message code="CC_월기타출고 단가 적용여부"/><%--월기타출고 단가 적용여부--%>
                                                                 </th>
                                                                 <td>
                                                                     <input name="INV_EXT_ISSUE_APY_YN" id="INV_EXT_ISSUE_APY_YN" value="${INV_EXT_ISSUE_APY_YN}" style="width:127px;"/>
                                                                 </td>
                                                             </tr>
                                                             <tr>
                                                                 <th scope="row">
                                                                      <spring:message code="CC_투입일자 관리여부"/><%--투입일자 관리여부--%>
                                                                 </th>
                                                                 <td>
                                                                     <input name="BOM_DIRC_CTRL_YN" id="BOM_DIRC_CTRL_YN" value="${BOM_DIRC_CTRL_YN}" style="width:127px;"/>
                                                                 </td>
                                                             </tr>
			                                             </tbody>
                                                     </table>
                                                     <p class="h2_sub"><spring:message code="CC_개별법 설정"/></p>
                                                     <table class="gridT">
                                                         <colgroup>
                                                             <col width="200px" />
                                                             <col width=";" />
                                                             <col width="200px" />
                                                             <col width=";" />
                                                             <col width="200px" />
                                                             <col width=";" />
                                                         </colgroup>
                                                         <tbody>
                                                             <tr>
                                                                 <th scope="row">
                                                                      <spring:message code="CC_단가 산출방법"/><%--단가 계산법--%>
                                                                 </th>
                                                                 <td>
                                                                     <input id="INV_IN_PRICE_TYP" name="INV_IN_PRICE_TYP" value="${INV_IN_PRICE_TYP}" style="width:80px;"/>
                                                                     / <input id="INV_IN_PRICE_MTH" name="INV_IN_PRICE_MTH" value="${INV_IN_PRICE_MTH}" style="width:80px;"/>
                                                                 </td>
                                                                 <th scope="row">
                                                                      <spring:message code="CC_자재 생산출고 자료"/><%--원자재 투입출고 자료--%>
                                                                 </th>
                                                                 <td>
                                                                     <input id="INV_BOM_ISSUE_YN" name="INV_BOM_ISSUE_YN" value="${INV_BOM_ISSUE_YN}" style="width:127px;"/>
                                                                 </td>
                                                                 <th scope="row">
                                                                      <spring:message code="CC_전월 재고조정 적용"/><%--재고조정수량 적용여부--%>
                                                                 </th>
                                                                 <td>
                                                                     <input id="INV_INQTY_ADJUST_YN" name="INV_INQTY_ADJUST_YN" value="${INV_INQTY_ADJUST_YN}" style="width:127px;"/>
                                                                 </td>
                                                             </tr>
                                                             <tr>
                                                                 <th scope="row">
                                                                      <spring:message code="CC_수입 신고구분"/><%-- 수입신고구분 --%>
                                                                 </th>
                                                                 <td>
                                                                     <input id="INV_IMP_ADJUST_TYPE" name="INV_IMP_ADJUST_TYPE" value="${INV_IMP_ADJUST_TYPE}" style="width:127px;"/>
                                                                 </td>
                                                                 <th scope="row">
                                                                      <spring:message code="CC_구매 C/O 연결기준"/><%--구매 C/O 신고구분--%>
                                                                 </th>
                                                                 <td>
                                                                     <input id="INV_PO_DECLARE_TYPE" name="INV_PO_DECLARE_TYPE" value="${INV_PO_DECLARE_TYPE}" style="width:127px;"/>
                                                                 </td>
                                                                 <th scope="row">
                                                                      <spring:message code="CC_수출 C/O 신고구분"/><%--수출 C/O 신고구분--%>
                                                                 </th>
                                                                 <td>
                                                                     <input id="INV_SD_DECLARE_TYPE" name="INV_SD_DECLARE_TYPE" value="${INV_SD_DECLARE_TYPE}" style="width:127px;"/>
                                                                 </td>
                                                             </tr>
                                                         </tbody>
                                                     </table>
                                                     <p class="h2_sub"><spring:message code="CC_거래설정"/></p>
                                                     <table class="gridT">
                                                         <colgroup>
                                                             <col width="200px" />
                                                             <col width=";" />
                                                             <col width="200px" />
                                                             <col width=";" />
                                                             <col width="200px" />
                                                             <col width=";" />
                                                         </colgroup>
                                                         <tbody>
			                                              <tr>
			                                                <th scope="row">
			                                                    <spring:message code="TERM, FNISH"/><%-- 인도조건 마감 --%>
			                                                </th>
			                                                <td>
			                                                    <input type="text" name="TERM_TYPE" id="TERM_TYPE" value="${TERM_TYPE}" style="width:127px;"/>
			                                                </td>
			                                                <th scope="row">
			                                                    <spring:message code="DOM, TERM"/><%-- 로컬 인도조건 --%>
			                                                </th>
			                                                <td>
			                                                    <input type="text" name="DOMESTIC_TERM" id="DOMESTIC_TERM" value="${DOMESTIC_TERM}" style="width:127px;"/>
			                                                </td>
			                                                <th scope="row">
			                                                    <spring:message code="EXP, TERM"/><%-- 수출 인도조건 --%>
			                                                </th>
			                                                <td>
			                                                    <input type="text" name="EXPORT_TERM" id="EXPORT_TERM" value="${EXPORT_TERM}" style="width:127px;"/>
			                                                </td>
			                                              </tr>
			                                              <tr>
			                                                  <th scope="row">
			                                                      <spring:message code="CNVSN, CURCY, TYP"/><%--환산통화 타입 --%>
			                                                  </th>
			                                                  <td colspan="5">
			                                                      <input type="text" name="EXCHANGE_CURRENCY_TYPE" id="EXCHANGE_CURRENCY_TYPE"  value="${EXCHANGE_CURRENCY_TYPE}" style="width:127px;"/>
			                                                  </td>
			                                              </tr>
			                                              <tr>
		                                                      <th scope="row">
                                                                     <spring:message code="DEAL, CURCY"/><%-- 거래 통화 --%>
                                                                 </th>
                                                                 <td>
                                                                     <input type="text" name="LOCAL_CURRENCY" id="LOCAL_CURRENCY"  value="${LOCAL_CURRENCY}" style="width:127px;"/>
                                                                 </td>
                                                                 <th scope="row">
                                                                     <spring:message code="CNVSN, CURCY"/><%-- 환산 통화 --%>
                                                                 </th>
                                                                 <td>    
                                                                     <input type="text" name="EXCHANGE_CURRENCY" id="EXCHANGE_CURRENCY"  value="${EXCHANGE_CURRENCY}" style="width:127px;"/>
                                                                 </td>
                                                                 <th scope="row">
                                                                     ERP <spring:message code="MGT"/> <spring:message code="CURCY"/><%--ERP 관리 통화 --%>
                                                                 </th>
                                                                 <td>
                                                                     <input type="text" name="STD_EXCRATE_CURRENCY" id="STD_EXCRATE_CURRENCY"  value="${STD_EXCRATE_CURRENCY}" style="width:127px;"/>
                                                                 </td>
                                                             </tr>
                                                         </tbody>
                                                     </table>
                                                     <p class="h2_sub"><spring:message code="CC_협력사 수취정보 설정"/></p>
                                                     <table class="gridT">
                                                         <colgroup>
                                                             <col width="200px" />
                                                             <col width=";" />
                                                             <col width="200px" />
                                                             <col width=";" />
                                                             <col width="200px" />
                                                             <col width=";" />
                                                         </colgroup>
                                                         <tbody>
                                                             <tr>
			                                                  <th scope="row">
			                                                      <spring:message code="CNFDO, RECTG"/><%--협력사 확인서 수취대상--%>
			                                                  </th>
			                                                  <td>
			                                                      <input type="text" name="VENDOR_CO_TARGET" id="VENDOR_CO_TARGET" value="${VENDOR_CO_TARGET}" style="width:127px;"/>
			                                                  </td>
			                                                  <th scope="row">
			                                                      <spring:message code="RECTG, COMTS"/><%--중점관리 추가내용--%>
			                                                  </th>
			                                                  <td colspan="3">
			                                                      <span>1.<spring:message code="TXT_MATERIAL_RATE"/> : <input type="text" name="CRTCT_MAT_RATE" id="CRTCT_MAT_RATE" value="${PROD_REPT_RATE}" style="width:30px;"/>%</span> | 
			                                                      <span>2.<input type="checkbox" id="CRTCT_GOOD_YN" name="CRTCT_GOOD_YN" value="${CRTCT_GOOD_YN}"/> <spring:message code="GOOD"/></span> |
			                                                      <span>3.<input type="checkbox" id="CRTCT_IMP_YN" name="CRTCT_IMP_YN" value="${CRTCT_IMP_YN}"/> <spring:message code="IMP,LTIT"/></span>
			                                                      <span>4.<input type="checkbox" id="CRTCT_HS_YN" name="CRTCT_HS_YN" value="${CRTCT_HS_YN}"/> <spring:message code="TH,NON,CHNGE,LTIT"/></span>
			                                                  </td>
			                                              </tr>
			                                              <tr>
                                                                 <th scope="row">
                                                                      <spring:message code="GENER, ORG, XLS_REQUIRED_ERROR"/><%-- 일반 원산지 필수체크 --%>
                                                                 </th>
                                                                 <td>
                                                                     <input name="GEN_ORIGIN_REQ_YN" id="GEN_ORIGIN_REQ_YN" value="${GEN_ORIGIN_REQ_YN}" style="width:127px;"/>
                                                                 </td>
                                                                 <th scope="row">
                                                                      <spring:message code="NOORG,REASN, APYDT"/><%--역외사유 적용--%>
                                                                 </th>
                                                                 <td>
                                                                     <input name="NON_ORGIN_RESN_YN" id="NON_ORGIN_RESN_YN" value="${NON_ORGIN_RESN_YN}" style="width:127px;"/>
                                                                 </td>
                                                                 <th scope="row">
                                                                     <spring:message code="NOORG,REASN, XLS_REQUIRED_ERROR"/><%-- 역외사유 필수체크 --%>
                                                                 </th>
                                                                 <td>
                                                                     <input type="text" name="NON_ORGIN_RESN_REQ_YN" id="NON_ORGIN_RESN_REQ_YN" value="${NON_ORGIN_RESN_REQ_YN}" style="width:127px;"/>
                                                                 </td>
                                                             </tr>
                                                             <tr>
                                                                 <th scope="row">
                                                                      <spring:message code="TXT_RVC_RATE"/>(<spring:message code="EFTIV_CHECK"/>)<%--부가가치비율(유효성 체크)--%>
                                                                 </th>
                                                                 <td>
                                                                     <input name="RVC_REQUIRED_YN" id="RVC_REQUIRED_YN" value="${RVC_REQUIRED_YN}" style="width:127px;"/>
                                                                 </td>
                                                                 <th scope="row">
                                                                      <spring:message code="TXT_AGRET_SLCT, ORNOT"/>(<spring:message code="PATNE"/> C/O)<%--협력사 확인서 제출 시 협정 선택가능 여부(Y:선택불가, N:선택가능)--%>
                                                                 </th>
                                                                 <td>
                                                                     <input name="VENODR_FTA_FIX_YN" id="VENODR_FTA_FIX_YN" value="${VENODR_FTA_FIX_YN}" style="width:127px;"/>
                                                                 </td>
                                                                 <th scope="row">
                                                                      <spring:message code="EVALU,SCOPE"/>
                                                                 </th>
                                                                 <td>
                                                                     <input type="text" name="EVALUATION_SCOPE" id="EVALUATION_SCOPE" value="${EVALUATION_SCOPE}" style="width:127px;"/>
                                                                 </td>
                                                             </tr>
                                                             <tr>
                                                                 <th scope="row">
                                                                      <spring:message code="PATNE,ID, SET"/><%-- 협력사ID 설정 --%>
                                                                 </th>
                                                                 <td>
                                                                     <input name="VENDOR_ID_TYPE" id="VENDOR_ID_TYPE" value="${VENDOR_ID_TYPE}" style="width:127px;"/>
                                                                     <input type="text" name="VENDOR_ID_SUFFIX" id="VENDOR_ID_SUFFIX" value="${VENDOR_ID_SUB_FIX}" placeholder="Suffix name" style="width:100px;"/>
                                                                 </td>
                                                                 <th scope="row">
                                                                      <spring:message code="PATNE,PWD, SET"/><%--협력사 비밀번호 설정--%>
                                                                 </th>
                                                                 <td>
                                                                     <input name="VENDOR_PW_TYPE" id="VENDOR_PW_TYPE" value="${VENDOR_PW_TYPE}" style="width:127px;"/>
                                                                     <input type="text" name="VENDOR_PW_SUFFIX" id="VENDOR_PW_SUFFIX" value="${VENDOR_PW_SUFFIX}" placeholder="Suffix name" style="width:100px;"/>
                                                                 </td>
                                                                 <th scope="row">
                                                                      <spring:message code="PATNE, INFMT, TXT_REQUIRED_YN"/>
                                                                 </th>
                                                                 <td>
                                                                     <input type="text" name="VENDOR_INFO_REQUIRED_YN" id="VENDOR_INFO_REQUIRED_YN" value="${VENDOR_INFO_REQUIRED_YN}" style="width:127px;"/>
                                                                 </td>
                                                             </tr>
		                                              </tbody>
                                                     </table>
                                                     <p class="h2_sub"><spring:message code="CC_고객사 관리 및 발급 설정"/></p>
                                                     <table class="gridT">
                                                         <colgroup>
                                                             <col width="200px" />
                                                             <col width=";" />
                                                             <col width="200px" />
                                                             <col width=";" />
                                                             <col width="200px" />
                                                             <col width=";" />
                                                         </colgroup>
                                                         <tbody>
		                                                  <tr>
                                                                 <th scope="row">
                                                                      <spring:message code="CSTMR, MGT,SCOPE"/><%--고객사 관리범위--%>
                                                                 </th>
                                                                 <td>
                                                                     <input type="text" name="CUSTOMER_CO_TARGET" id="CUSTOMER_CO_TARGET" value="${CUSTOMER_CO_TARGET}" style="width:127px;"/>
                                                                 </td>
                                                                 <th scope="row">
                                                                      <spring:message code="CRTCT, COMTS"/><%--중점관리 추가내용--%>
                                                                 </th>
                                                                 <td>
                                                                     <span>1.<input type="checkbox" id="CRTCT_SUBITEM_YN" name="CRTCT_SUBITEM_YN" value="${CRTCT_SUBITEM_YN}"/> <spring:message code="TOLPC,LTIT"/></span> |
                                                                     <span>2.<input type="checkbox" id="CRTCT_EXPORT_YN" name="CRTCT_EXPORT_YN" value="${CRTCT_EXPORT_YN}"/> <spring:message code="EXP,LTIT"/></span>
                                                                 </td>
                                                                 <th scope="row">
                                                                      <spring:message code="TXT_PKRIN_TARGET_YN"/>(<spring:message code="EXP_FNISH"/>)
                                                                 </th>
                                                                 <td>
                                                                     <input type="text" name="PKRIN_EXP_TARGET_YN" id="PKRIN_EXP_TARGET_YN" value="${PKRIN_EXP_TARGET_YN}" style="width:127px;"/>
                                                                 </td>
                                                             </tr>
                                                             <tr>
                                                                 <th scope="row">
                                                                      <spring:message code="ORG, CERIS,ORGNZT"/><%--원산지 발급조직(C:회사, D:사업부)--%>
                                                                 </th>
                                                                 <td>
                                                                     <input name="CERT_EXPORTER_SCOPE" id="CERT_EXPORTER_SCOPE" value="${CERT_EXPORTER_SCOPE}" style="width:127px;"/>
                                                                 </td>
                                                                 <th scope="row">
                                                                      <spring:message code="ISORG, APYDT"/><%--기관발급 적용--%>
                                                                 </th>
                                                                 <td>
                                                                     <input name="ORGAN_ISSUE_YN" id="ORGAN_ISSUE_YN" value="${ORGAN_ISSUE_YN}" style="width:127px;"/>
                                                                 </td>
                                                                 <th scope="row">
                                                                      <spring:message code="CFDMF, MATAL,SPECF"/>
                                                                 </th>
                                                                 <td>
                                                                     <input type="text" name="BFO_DOC_JO_YN" id="BFO_DOC_JO_YN" value="${BFO_DOC_JO_YN}" style="width:127px;"/>
                                                                 </td>
                                                             </tr>
                                                             <tr>
                                                                 <th scope="row">
                                                                      <spring:message code="CC_법인간  C/O 발급조건"/>
                                                                 </th>
                                                                 <td>
                                                                     <input id="OVER_COPER_CO_TRANS" name="OVER_COPER_CO_TRANS" value="${OVER_COPER_CO_TRANS}" style="width:127px;"/>
                                                                 </td>
                                                             </tr>
                                                         </tbody>
                                                     </table>
                                                     <p class="h2_sub"><spring:message code="CC_매출자료 설정"/></p>
                                                     <table class="gridT">
                                                         <colgroup>
                                                             <col width="200px" />
                                                             <col width=";" />
                                                             <col width="200px" />
                                                             <col width=";" />
                                                             <col width="200px" />
                                                             <col width=";" />
                                                         </colgroup>
                                                         <tbody>
                                                             <tr>
                                                                 <th scope="row">
                                                                     <spring:message code="SALE,TYPE, MAPIG"/>(<spring:message code="BTN_EXCEL_UPLOAD"/>)<%-- 품목타입(엑셀 맵핑데이터) --%>
                                                                 </th>
                                                                 <td>
                                                                     <input type="text" name="ITEM_TYPE_DATA" id="ITEM_TYPE_DATA" value="${SALES_ITEM_TYPE}" style="width:127px;"/>
                                                                 </td>
                                                                 <th scope="row">
                                                                     <spring:message code="CSTMR, LTIT,UNIPR, APYDT"/><%-- 고객사 품목단가 적용 --%>
                                                                 </th>
                                                                 <td>
                                                                     <input type="text" name="CUSTOMER_PRICE_APLY_YN" id="CUSTOMER_PRICE_APLY_YN" value="${CUSTOMER_PRICE_APLY_YN}" style="width:127px;"/>
                                                                 </td>
                                                             </tr>
                                                             <tr>
                                                                 <th scope="row">
                                                                     <spring:message code="CC_0단가 판정여부"/><%-- 판매 0단가 포함여부 --%>
                                                                 </th>
                                                                 <td>
                                                                     <input type="text" name="SALES_NO_PRICE_INC_YN" id="SALES_NO_PRICE_INC_YN" value="${SALES_ITEM_TYPE}" style="width:127px;"/>
                                                                 </td>
                                                                 <th scope="row">
                                                                      <spring:message code="CC_매출조정(-수량) 포함여부"/>
                                                                 </th>
                                                                 <td>
                                                                     <input type="text" name="SALES_IN_ZERO_QTY_YN" id="SALES_IN_ZERO_QTY_YN" value="${SALES_IN_ZERO_QTY_YN}" style="width:127px;"/>
                                                                 </td>
                                                             </tr>
                                                         </tbody>
                                                     </table>
                                                     <p class="h2_sub"><spring:message code="CC_메일발송 설정"/></p>
                                                     <table class="gridT">
                                                         <colgroup>
                                                             <col width="200px" />
                                                             <col width=";" />
                                                             <col width="200px" />
                                                             <col width=";" />
                                                             <col width="200px" />
                                                             <col width=";" />
                                                         </colgroup>
                                                         <tbody>
			                                              <tr>
			                                                  <th scope="row"><spring:message code="SUBCO, MAIL,SENT,ORNOT"/></th>
                                                                 <td>
                                                                     <input type="text" name="SUB_MAIL_SEND_YN" id="SUB_MAIL_SEND_YN" value="${SUB_MAIL_SEND_YN}" style="width:127px;"/>
                                                                 </td>
                                                                 <th scope="row">FTA PASS <spring:message code="TXT_SEND_MAIL"/></th>
                                                                 <td>
                                                                     <input type="text" name="PASS_MAIL_SEND_YN" id="PASS_MAIL_SEND_YN" value="${PASS_MAIL_SEND_YN}" style="width:127px;"/>
                                                                 </td>
                                                                 <th scope="row"><spring:message code="CC_메일계정 통합관리 여부"/></th>
                                                                 <td>
                                                                     <input type="text" name="EMAIL_AUTH_INTG_YN" id="EMAIL_AUTH_INTG_YN" value="${EMAIL_AUTH_INTG_YN}" style="width:127px;"/>
                                                                 </td>
			                                              </tr>
			                                          </tbody>
                                                     </table>
                                                     <p class="h2_sub"><spring:message code="CC_타 시스템 연계"/></p>
                                                     <table class="gridT">
                                                         <colgroup>
                                                             <col width="200px" />
                                                             <col width=";" />
                                                             <col width="200px" />
                                                             <col width=";" />
                                                             <col width="200px" />
                                                             <col width=";" />
                                                         </colgroup>
                                                         <tbody>
                                                             <tr>
                                                                 <th scope="row">
                                                                      <spring:message code="SS_ORG, SEND,CONTS"/>
                                                                 </th>
                                                                 <td colspan="3">
                                                                     <label>매출선택: </label><input type="text" name="SEND_ORGIN_TARGET" id="SEND_ORGIN_TARGET" value="${SEND_ORGIN_TARGET}" style="width:127px;"/> /
                                                                     <label> 직수출 전송대상: </label><input type="text" name="SEND_CO_TIMING" id="SEND_CO_TIMING" value="${SEND_CO_TIMING}" style="width:127px;"/>
                                                                     <span style="color:blue;">(내수 매출은 원산지 확인서 발급 후 에만 전송됩니다.)</span>
                                                                 </td>
                                                                 <th scope="row">
                                                                      FTA HUB <spring:message code="LINKG,ORGNZT"/>
                                                                 </th>
                                                                 <td>
                                                                     <input name="VAATZ_HUB_UNIT" id="VAATZ_HUB_UNIT" value="${VAATZ_HUB_UNIT}" style="width:127px;"/>
                                                                 </td>
                                                             </tr>
                                                             <tr>
                                                                 <th scope="row">
                                                                      <spring:message code="CC_Remote Service URL"/>
                                                                 </th>
                                                                 <td colspan="3">
                                                                     <input name="REMOTE_SERVICE_URL" id="REMOTE_SERVICE_URL" value="${REMOTE_SERVICE_URL}" style="width:99%;"/>
                                                                 </td>
                                                                 <th scope="row">
                                                                      <spring:message code="CC_Remote Service(Y/N)"/>
                                                                 </th>
                                                                 <td>
                                                                     <input name="REMOTE_SERVICE_YN" id="REMOTE_SERVICE_YN" value="${REMOTE_SERVICE_YN}" style="width:127px;"/>
                                                                 </td>
                                                             </tr>
			                                        </tbody>
			                                    </table> 
			                                </form>  
			                            </div>
			                        </div>
			                    </div>
			                </div>
			            </div>
			            <div title="3">
			                <div class="easyui-layout" data-options="fit:true">
			                    <div id="SM7001_01_div_12"  data-options="region:'center',border:false" style="display:none;">
	                                <div class="easyui-layout" data-options="fit:true">
	                                    <div data-options="region:'north',border:false" style="overflow:hidden;" class="h2_etc">
	                                        <p class="h2"><spring:message code="FTA, HUB, OPT"/><%--FTA HUB 옵션 --%></p>
	                                    </div>
	                                    <div data-options="region:'center',border:false">
	                                        <div class="easyui-layout" data-options="fit:true">
	                                            <div data-options="region:'north',border:false" class="sec_tit" style="overflow:hidden;">
	                                                <ul class="tab_area">
	                                                    <li id="SM7001_01_divMove01" class="on"><a href="javascript:SM7001_01.ui.divMove('1');"><spring:message code="CNFIT, ID"/></a></li>
	                                                    <li id="SM7001_01_divMove02"><a href="javascript:SM7001_01.ui.divMove('2');"><spring:message code="SALES, SI2007_01"/></a></li>
	                                                    <li id="SM7001_01_divMove03"><a href="javascript:SM7001_01.ui.divMove('3');"><spring:message code="TXT_SIGN_USER"/></a></li>
	                                                </ul>
	                                            </div>
	                                            <div data-options="region:'center',border:false">
	                                                <div id="SM7001_01_tabs_02" class="easyui-tabs" data-options="fit:true,showHeader:false,plain:true">
		                                                <div title="1">
			                                                <div id="SM7001_01_div_01" class="easyui-layout" data-options="fit:true">
			                                                    <div data-options="region:'west',border:false,split:true" style="width:300px;">
			                                                        <div class="easyui-layout" data-options="fit:true">
                                                                              <div data-options="region:'north',border:false">
					                                                        <div class="h2_etc">
					                                                            <p class="h2"><spring:message code="CNFIT,INFMT"/></p>
					                                                            <p class="h2_btn">
					                                                                <a href="javascript:SM7001_01.control.updateCompanyCertIDInfo();" class="btnMainSave"><spring:message code="SAVE"/></a>
					                                                            </p>
					                                                        </div>
					                                                    </div>
				                                                        <div data-options="region:'center',border:false">
				                                                            <div class="easyui-layout" data-options="fit:true">
				                                                                <div data-options="region:'north',border:false">
							                                                        <form id="SM7001_01_form_03" name="SM7001_01_form_03" method="post">
							                                                            <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${_MEMBER.SESSION_COMPANY_CD}"/>
							                                                            <input type="hidden" id="USER_ID" name="USER_ID" value="${_MEMBER.SESSION_USER_ID}"/>
							                                                            <input type="hidden" id="gridData" name="gridData"/>
							                                                            <table class="dataT" style="margin-bottom:0px;">
							                                                                <caption><spring:message code="DTA, TB"/><!-- 데이타 테이블 --></caption>
							                                                                <colgroup>
							                                                                    <col width="150px" />
							                                                                    <col width=";" />
							                                                                </colgroup>
							                                                                <tbody>
							                                                                    <tr>
							                                                                        <th scope="row">Vaatz HUB ID(<spring:message code="BYCPY"/>)</th>
							                                                                        <td>
							                                                                           <input type="text" name="HUB_CERT_ID" id="HUB_CERT_ID" style="width:96%;"/>
							                                                                        </td>
							                                                                    </tr>
							                                                                    <tr>
		                                                                                            <th scope="row">TOMS HUB ID</th>
		                                                                                            <td>
		                                                                                                <input type="text" name="TOMS_HUB_ID" id="TOMS_HUB_ID" style="width:96%;"/>
		                                                                                            </td>
		                                                                                        </tr>
		                                                                                        <tr>
                                                                                                          <th scope="row" colspan="2" style="height:22px;">Vaatz HUB ID(<spring:message code="BYDIV"/>)</th>
                                                                                                      </tr>
							                                                                </tbody>
							                                                            </table>
							                                                        </form>
							                                                    </div>
							                                                    <div data-options="region:'center',border:false">
							                                                        <table id="SM7001_01_grid_05" ></table>
							                                                    </div>
							                                                </div>
							                                            </div>
							                                        </div>
			                                                    </div>
			                                                    <div data-options="region:'center',border:false">
			                                                        <div class="easyui-layout" data-options="fit:true">
			                                                            <div data-options="region:'north',border:false">
			                                                                <div class="h2_etc">
			                                                                    <p class="h2"><spring:message code="CNFDO, CERIS, CSTMR"/><%--CNFDO 발급 고객사--%></p>
			                                                                    <p class="h2_btn">
			                                                                        <a href="javascript:SM7001_01.dialog.openDialog_1('insert');" class="btnNewRegiste"><spring:message code="REGER"/><%--등록--%></a>
			                                                                        <a href="javascript:SM7001_01.dialog.openDialog_1('update');" class="btnEdit"><spring:message code="MOD"/><%--수정--%></a>
			                                                                    </p>
			                                                                </div>
			                                                            </div> 
			                                                            <div data-options="region:'center',border:false">
			                                                                <table id="SM7001_01_grid_01" ></table>
			                                                            </div>
			                                                        </div>
			                                                    </div>
			                                                </div>
			                                            </div>
			                                            <div title="2">
			                                                <div id="SM7001_01_div_02" class="easyui-layout" data-options="fit:true">
			                                                    <div data-options="region:'north',border:false">
			                                                        <div class="h2_etc">
			                                                            <p class="h2"><spring:message code="SALES, SI2007_01"/><%--거래 고객사--%></p>
			                                                            <p class="h2_btn">
			                                                                <a href="javascript:SM7001_01.dialog.openDialog_2('insert');" class="btnNewRegiste"><spring:message code="REGER"/><%--등록--%></a>
			                                                                <a href="javascript:SM7001_01.dialog.openDialog_2('update');" class="btnEdit"><spring:message code="MOD"/><%--수정--%></a>
			                                                            </p>
			                                                        </div>
			                                                    </div>
			                                                    <div data-options="region:'center',border:false"> 
			                                                       <table id="SM7001_01_grid_02"></table>
			                                                    </div>
			                                                </div>
			                                            </div>
			                                            <div title="3">
			                                                <div id="SM7001_01_div_03" class="easyui-layout" data-options="fit:true">
			                                                    <div data-options="region:'north',border:false">
			                                                        <div class="h2_etc">
			                                                            <p class="h2"><spring:message code="TXT_SIGN_USER"/><%--서명권자--%></p>
			                                                            <p class="h2_btn">
			                                                                <a href="javascript:SM7001_01.dialog.openDialog_3('insert');" class="btnNewRegiste"><spring:message code="REGER"/><%--등록--%></a>
			                                                                <a href="javascript:SM7001_01.dialog.openDialog_3('update');" class="btnEdit"><spring:message code="MOD"/><%--수정--%></a>
			                                                            </p>
			                                                        </div>
			                                                    </div>
			                                                    <div data-options="region:'center',border:false" >
			                                                       <table id="SM7001_01_grid_03"></table>
			                                                    </div>
			                                                </div>
			                                            </div>
			                                        </div>
	                                            </div>
	                                        </div>
	                                    </div>
	                                </div>
			                    </div>
	                        </div>
				        </div>
				    </div>
			    </div>
			</div>
	    </div>
	</div>

    <script type="text/javascript" src="/js/views/FM/SM/SM-7001_01.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>