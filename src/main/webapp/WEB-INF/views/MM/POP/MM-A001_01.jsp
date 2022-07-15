<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : MM-A001_01.jsp
  * @Description : FTA관리 메인화면
  * @Modification Information
  *
  * author YNI-Maker
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMA001_01_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>      
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'north',border:false,split:true" style="height:251px;padding:0 1px 0px 0;">
            <div class="easyui-layout" data-options="fit:true">
                <div data-options="region:'north',border:false" class="h2_etc">
                   <p class="h2"><spring:message code="FTA, MGT,PSTCN"/></p>
                </div>
                <div data-options="region:'center',border:false">
                    <div class="easyui-panel" data-options="fit:true,border:false">
                        <form id="MMA001_01_form_01" name="MMA001_01_form_01" method="POST">
                            <input type="hidden" id="REQUEST_STATE" name="REQUEST_STATE"/>
                            <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${_MEMBER.SESSION_COMPANY_CD}"/>
                            <input type="hidden" id="SCHEDULE_CD" name="SCHEDULE_CD" value="MONTHLY_BATCH"/>
                            <input type="hidden" id="START_YYYYMM" name="START_YYYYMM"/>
                            <input type="hidden" id="YYYYMM" name="YYYYMM" value="${_MEMBER.SESSION_WORK_YYYY_MM}"/>
                            <input type="hidden" id="AUTHTARIFF" name="AUTHTARIFF" value="${AUTHTARIFF}"/>
                            <input type="hidden" id="PARENT_HISTORY_ID" name="PARENT_HISTORY_ID"/>
                            <input type="hidden" id="CUSTOMER_CO_TARGET" value="${_MEMBER.SESSION_CUSTOMER_CO_TARGET}"/>
                            <input type="hidden" id="REMOTE_SERVICE_YN" name="REMOTE_SERVICE_YN" value="${REMOTE_SERVICE_YN}"/>
                            <div id="MMA001_01_invest_view" class="search_table">
                                <table width="100%" height="220px" cellpadding="0" cellspacing="3" border="0">
                                    <colgroup>
                                        <col style="width:20%;"/>
                                        <col style="width:20%;"/>
                                        <col style="width:20%;"/>
                                        <col style="width:20%;"/>
                                        <col style="width:;"/>
                                    </colgroup>
                                    <tr>
                                        <td class="search_table_tit">
                                            <table width="100%" cellpadding="0" cellspacing="0" border="0">
                                                <tr>
                                                    <td><div class="flt_l">1.<spring:message code="FNISH, STAUS"/></div></td>
                                                    <td width="7px"><div class="flt_r"><a href="javascript:MMA001_01.control.initPageData('STEP1');"><img src="/images/icon/reload.png"/></a></div></td>
                                                </tr>
                                            </table>
                                        </td>
                                        <td class="search_table_tit">
                                           <table width="100%" cellpadding="0" cellspacing="0" border="0">
                                                <tr>
                                                    <td><div class="flt_l">2.ERP <spring:message code="INF"/></div></td>
                                                    <td width="7px"><div class="flt_r"><a href="javascript:MMA001_01.control.initPageData('STEP2')"><img src="/images/icon/reload.png"/></a></div></td>
                                                </tr>
                                            </table>
                                        </td>
                                        <td class="search_table_tit">
                                            <table width="100%" cellpadding="0" cellspacing="0" border="0">
                                                <tr>
                                                    <td><div class="flt_l">3.<spring:message code="PATNE, CNFDO"/></div></td>
                                                    <td width="7px"><div class="flt_r"><a href="javascript:MMA001_01.control.initPageData('STEP3')"><img src="/images/icon/reload.png"/></a></div></td>
                                                </tr>
                                            </table>
                                        </td>
                                        <td class="search_table_tit">
                                            <table width="100%" cellpadding="0" cellspacing="0" border="0">
                                                <tr>
                                                    <td><div class="flt_l">4.<spring:message code="TXT_COO_DETERMIN"/></div></td>
                                                    <td width="7px"><div class="flt_r"><a href="javascript:MMA001_01.control.initPageData('STEP4')"><img src="/images/icon/reload.png"/></a></div></td>
                                                </tr>
                                            </table>
                                        </td>
                                        <td class="search_table_tit">
                                            <table width="100%" cellpadding="0" cellspacing="0" border="0">
                                                <tr>
                                                    <td><div class="flt_l">5.<spring:message code="RPT, CERIS"/></div></td>
                                                    <td width="7px"><div class="flt_r"><a href="javascript:MMA001_01.control.initPageData('STEP5')"><img src="/images/icon/reload.png"/></a></div></td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="conditions_detail" style="padding:1px" valign="top">
                                            <a href="#" id="MMA001_01_tooltip_1" class="easyui-linkbutton" data-options="plain:true" style="width:96%;text-align:left;"><div id="MMA001_01_CLOSING_YYYYMM"></div></a><br>
                                            <a href="#" class="easyui-linkbutton" data-options="plain:true,disabled:true" style="color:#000;width:96%;height:20px;text-align:left;"><div id="MMA001_01_CLOSING_COMPANY"></div></a><br>
                                            <a href="#" class="easyui-linkbutton" data-options="plain:true,disabled:true" style="color:#000;width:96%;height:20px;text-align:left;"><div id="MMA001_01_CLOSING_STATUS"></div></a><br>
                                            <a href="#" class="easyui-linkbutton" data-options="plain:true,disabled:true" style="color:#000;width:96%;height:20px;text-align:left;"><div id="MMA001_01_CLOSING_DATE"></div></a><br>
                                        </td>
                                        <td class="conditions_detail" style="padding:1px" valign="top">
                                            <a href="javascript:MMA001_01.redirectMenu('CM-J1200');" class="easyui-linkbutton" data-options="plain:true" style="width:96%;text-align:left;"><div id="MMA001_01_IF_ITEM"></div></a><br>
                                            <a href="#" class="easyui-linkbutton" data-options="plain:true,disabled:true" style="color:#000;width:96%;height:20px;text-align:left;"><div id="MMA001_01_IF_ITEM_PO"></div></a><br>
                                            <a href="#" class="easyui-linkbutton" data-options="plain:true,disabled:true" style="color:#000;width:96%;height:20px;text-align:left;"><div id="MMA001_01_IF_ITEM_INV"></div></a><br>
                                            <a href="#" class="easyui-linkbutton" data-options="plain:true,disabled:true" style="color:#000;width:96%;height:20px;text-align:left;"><div id="MMA001_01_IF_ITEM_BOM"></div></a><br>
                                            <a href="#" class="easyui-linkbutton" data-options="plain:true,disabled:true" style="color:#000;width:96%;height:20px;text-align:left;"><div id="MMA001_01_IF_ITEM_SALES"></div></a><br>
                                        </td>
                                        <td class="conditions_detail" style="padding:1px" valign="top">
                                            <a href="javascript:MMA001_01.redirectMenu('CM-J1301');" class="easyui-linkbutton" data-options="plain:true" style="width:96%;text-align:left;"><div id="MMA001_01_CO_TARGET"></div></a><br>
                                            <a href="#" class="easyui-linkbutton" data-options="plain:true,disabled:true" style="color:#000;width:96%;height:20px;text-align:left;"><div id="MMA001_01_CO_RECEPT_TOT"></div></a><br>
                                            <a href="#" class="easyui-linkbutton" data-options="plain:true,disabled:true" style="color:#000;width:96%;height:20px;text-align:left;"><div id="MMA001_01_CO_RECEPT_CNT"></div></a><br>
                                            <a href="#" class="easyui-linkbutton" data-options="plain:true,disabled:true" style="color:#000;width:96%;height:20px;text-align:left;"><div id="MMA001_01_CO_RECEPT_RATE"></div></a><br>
                                            <a href="javascript:MMA001_01.redirectMenu('CM-J1301');" class="easyui-linkbutton" data-options="plain:true" style="width:96%;text-align:left;"><div id="MMA001_01_CN_TARGET"></div></a><br>
                                            <a href="#" class="easyui-linkbutton" data-options="plain:true,disabled:true" style="color:#000;width:96%;height:20px;text-align:left;"><div id="MMA001_01_CN_RECEPT_TOT"></div></a><br>
                                            <a href="#" class="easyui-linkbutton" data-options="plain:true,disabled:true" style="color:#000;width:96%;height:20px;text-align:left;"><div id="MMA001_01_CN_RECEPT_CNT"></div></a><br>
                                            <a href="#" class="easyui-linkbutton" data-options="plain:true,disabled:true" style="color:#000;width:96%;height:20px;text-align:left;"><div id="MMA001_01_CN_RECEPT_RATE"></div></a><br>
                                        </td>
                                        <td class="conditions_detail" style="padding:1px" valign="top">
                                            <a href="javascript:MMA001_01.redirectMenu('CM-J1301');" class="easyui-linkbutton" data-options="plain:true" style="width:96%;text-align:left;"><div id="MMA001_01_DE_STATUS"></div></a><br>
                                            <a href="#" class="easyui-linkbutton" data-options="plain:true,disabled:true" style="color:#000;width:96%;height:20px;text-align:left;"><div id="MMA001_01_DE_TOT"></div></a><br>
                                            <a href="#" class="easyui-linkbutton" data-options="plain:true,disabled:true" style="color:#000;width:96%;height:20px;text-align:left;"><div id="MMA001_01_DE_SUCC_CNT"></div></a><br>
                                            <a href="#" class="easyui-linkbutton" data-options="plain:true,disabled:true" style="color:#000;width:96%;height:20px;text-align:left;"><div id="MMA001_01_DE_FAIL_CNT"></div></a><br>
                                            <a href="#" class="easyui-linkbutton" data-options="plain:true,disabled:true" style="color:#000;width:96%;height:20px;text-align:left;"><div id="MMA001_01_DE_NOVER_CNT"></div></a><br>
                                            <a href="javascript:MMA001_01.redirectMenu('CM-J1301');" class="easyui-linkbutton" data-options="plain:true" style="width:96%;text-align:left;"><div id="MMA001_01_DE_RST"></div></a><br>
                                            <a href="#" class="easyui-linkbutton" data-options="plain:true,disabled:true" style="color:#000;width:96%;height:20px;text-align:left;"><div id="MMA001_01_DE_ORG_RATE"></div></a><br>
                                            <a href="#" class="easyui-linkbutton" data-options="plain:true,disabled:true" style="color:#000;width:96%;height:20px;text-align:left;"><div id="MMA001_01_DE_NOORG_RATE"></div></a><br>
                                        </td>
                                        <td class="conditions_detail" style="padding:1px" valign="top">
                                            <a href="javascript:MMA001_01.redirectMenu('CM-J1301');" class="easyui-linkbutton" data-options="plain:true" style="width:96%;text-align:left;"><div id="MMA001_01_REPORT_DMT"></div></a><br>
                                            <a href="#" class="easyui-linkbutton" data-options="plain:true,disabled:true" style="color:#000;width:96%;height:20px;text-align:left;"><div id="MMA001_01_REPORT_DMT_TOT"></div></a><br>
                                            <a href="#" class="easyui-linkbutton" data-options="plain:true,disabled:true" style="color:#000;width:96%;height:20px;text-align:left;"><div id="MMA001_01_REPORT_DMT_ISU"></div></a><br>
                                            <a href="#" class="easyui-linkbutton" data-options="plain:true,disabled:true" style="color:#000;width:96%;height:20px;text-align:left;"><div id="MMA001_01_REPORT_DMT_RATE"></div></a><br>
                                            <a href="javascript:MMA001_01.redirectMenu('CM-J1301');" class="easyui-linkbutton" data-options="plain:true" style="width:96%;text-align:left;"><div id="MMA001_01_REPORT_EXP"></div></a><br>
                                            <a href="#" class="easyui-linkbutton" data-options="plain:true,disabled:true" style="color:#000;width:96%;height:20px;text-align:left;"><div id="MMA001_01_REPORT_EXP_TOT"></div></a><br>
                                            <a href="#" class="easyui-linkbutton" data-options="plain:true,disabled:true" style="color:#000;width:96%;height:20px;text-align:left;"><div id="MMA001_01_REPORT_EXP_ISU"></div></a><br>
                                            <a href="#" class="easyui-linkbutton" data-options="plain:true,disabled:true" style="color:#000;width:96%;height:20px;text-align:left;"><div id="MMA001_01_REPORT_EXP_RATE"></div></a><br>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div data-options="region:'center',border:false">
		    <div id="MMA001_01_div_03" class="easyui-layout" style="width:100%;height:630px;background-color:#fff;">
		        <div class="easyui-layout" data-options="fit:true">
		            <div data-options="region:'west',border:false,split:true" class="width50">
                        <div class="easyui-layout" data-options="fit:true">
                            <div data-options="region:'north',border:false" style="height:210px;padding-bottom:12px;">
                                <div class="easyui-layout" data-options="fit:true">
		                            <div data-options="region:'north',border:false" class="h2_etc">
		                                <p class="h2"><spring:message code="REOOR"/> <span id="MMA001_01_span_01" style="display:none;">- <spring:message code="CSTMR, CRTCT"/></span></p>
		                                <p class="h2_btn">
		                                    <a href="javascript:MMA001_01.control.selectDeterminateCount();" class="btnRefresh"><spring:message code="REFSH"/></a><%--새로고침--%>
		                                </p>
		                            </div>
		                            <div data-options="region:'center',border:true" style="padding:3px;">
								        <div id="MMA001_01_chart_01" style="overflow:hidden;width:100%; height:100%;background-color:#fff;"></div>
		                            </div>
		                        </div>
		                    </div>
		                    <div data-options="region:'center',border:false">
		                        <div class="easyui-layout" data-options="fit:true">
		                            <div data-options="region:'north',border:false" class="h2_etc">
                                        <p class="h2"><spring:message code="TXT_COO_DETERMIN, RSULT"/> <span id="MMA001_01_span_02" style="display:none;">- <spring:message code="CSTMR, CRTCT"/></span></p>
                                        <p class="h2_btn">
                                            <a href="javascript:MMA001_01.ui.pageDeterMove();" class="btnPageForward"><spring:message code="RETMU"/></a>
                                            <a href="javascript:MMA001_01.control.updateDwMonthFcrStatus();" class="btnSelectClick"><spring:message code="CC_월마감 집계"/></a>
                                            <a href="javascript:MMA001_01.excel.excelDownload_1();" class="btnExlDown"><spring:message code="EXCEL, DOWN"/><%--엑셀다운로드--%></a>
                                        </p>
                                    </div>
                                    <div data-options="region:'center',border:false">
                                        <form id="MMA001_01_form_04" name="MMA001_01_form_04" method="POST">
                                            <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${_MEMBER.SESSION_COMPANY_CD}"/>
                                            <input type="hidden" id="YYYYMM" name="YYYYMM"/>
                                        </form>
                                        <table id="MMA001_01_grid_02" name="MMA001_01_grid_02"></table>
                                    </div>
		                        </div>
		                    </div>
                        </div>
                    </div>
                    <div id="MMA001_01_div_04" data-options="region:'center',border:false" class="width50">
                        <div class="easyui-layout" data-options="fit:true">
                            <div data-options="region:'north',border:false" style="height:210px;padding-bottom:12px;">
		                        <div class="easyui-layout" data-options="fit:true">
		                           <div data-options="region:'north',border:false" class="h2_etc">
		                               <p class="h2"><spring:message code="SFSCO"/></p>
		                               <p class="h2_btn">
		                                   <a href="javascript:MMA001_01.control.selectVendorCoCount();" class="btnRefresh"><spring:message code="REFSH"/></a><%--새로고침--%>
		                                   <a href="javascript:MMA001_01.excel.excelDownload_2();" class="btnExlDown"><spring:message code="EXCEL, DOWN"/><%--엑셀다운로드--%></a>
		                               </p>
		                           </div>
		                           <div data-options="region:'center',border:true" style="padding:3px;">
		                               <form id="MMA001_01_form_02" name="MMA001_01_form_02" method="POST">
		                                   <input type="hidden" id="COMPANY_CD" name="COMPANY_CD"/>
		                                   <input type="hidden" id="START_YYYYMM" name="START_YYYYMM"/>
		                                   <input type="hidden" id="YYYYMM" name="YYYYMM"/>
		                               </form>
		                               <form id="MMA001_01_form_03" name="MMA001_01_form_03" method="POST">
		                                   <input type="hidden" id="COMPANY_CD" name="COMPANY_CD"/>
		                                   <input type="hidden" id="START_YYYYMM" name="START_YYYYMM"/>
		                                   <input type="hidden" id="YYYYMM" name="YYYYMM"/>
		                                   <input type="hidden" id="TARIFF_STATUS_TYPE" name="TARIFF_STATUS_TYPE" value="C"/>
		                               </form>
		                               <div id="MMA001_01_chart_02" style="overflow:hidden;width:100%; height:100%;background-color:#fff;"></div>
		                           </div>
		                        </div>
		                    </div>
		                    <div data-options="region:'center',border:false" class="width50">
                                <div class="easyui-layout" data-options="fit:true">
                                    <c:if test="${AUTHTARIFF eq 'Y'}">
                                        <div id="MMA001_01_div_02" data-options="region:'north',border:false" style="height:210px;">
                                            <div class="easyui-layout" data-options="fit:true">
                                                <div data-options="region:'north',border:false" class="h2_etc">
                                                    <p class="h2"><spring:message code="FTA, CSTDU, SVAMT, TRASI"/></p>
                                                    <p class="h2_btn">
                                                        <a href="javascript:MMA001_01.control.selectExportTariffInfo();" class="btnRefresh"><spring:message code="REFSH"/></a><%--새로고침--%>
                                                        <a href="javascript:MMA001_01.dialog.openDialog_2();" class="btnStateGraph"><spring:message code="DETIL,INFMT"/></a>
                                                    </p>
                                                </div>
                                                <div data-options="region:'center',border:true" style="padding:3px;">
                                                    <div id="MMA001_01_chart_03" style="overflow:hidden;width:100%;height:100%;background-color:#fff;"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:if>
                                    <div data-options="region:'center',border:false">
                                        <div class="easyui-layout" data-options="fit:true">
                                            <div data-options="region:'north',border:false" class="h2_etc">
                                                <p class="h2"><spring:message code="NOTIC"/></p>
                                                <p class="h2_btn">
                                                    <a href="javascript:MMA001_01.dialog.openDialog_1('view');" class="btnSelectClick"><spring:message code="DETIL,INFMT"/></a>
                                                </p>
                                            </div>
                                            <div data-options="region:'center',border:false">
                                                <table id="MMA001_01_grid_01"></table>
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
		<div id="MMA001_01_div_01" data-options="region:'south',border:false,split:false" style="display:none;padding-top:2px;">
            <span id="divKoreaSiteLink" style="border:0px solid #000;display:none;">
                <span id="MMA001_01_span_03" style="display:none;padding-right:0px"><a href="http://inzimes.inzi.co.kr" onclick="window.open(this.href);return false;" onkeypress="this.onclick;"><img src="/images/ci/c_banner.png" /></a></span>
                <span id="MMA001_01_span_04" style="display:none;padding-right:0px"><a href="http://172.50.2.24/FRM_INIT.aspx?p_userid=${_MEMBER.SESSION_USER_ID}&p_sysgubun=0&p_companycd=${_MEMBER.SESSION_COMPANY_CD}&v_xusernm=${_MEMBER.SESSION_USER_NAME}&v_cust_cd=" onclick="window.open(this.href);return false;" onkeypress="this.onclick;"><img src="/images/ci/b_banner.png" /></a></span>
                <span id="MMA001_01_span_05" style="display:none;padding-right:0px"><a href="http://172.28.126.67:8088" onclick="window.open(this.href);return false;" onkeypress="this.onclick;"><img src="/images/ci/ad_banner.png" /></a></span>
                <span id="MMA001_01_span_06" style="display:none;padding-right:0px"><a href="http://172.18.10.36/origin/main/login.do?no_password=Y&ssoUserId=${_MEMBER.SESSION_USER_ID}" onclick="window.open(this.href);return false;" onkeypress="this.onclick;"><img src="/images/ci/phc_banner.png" /></a></span>
                <span id="MMA001_01_span_07" style="display:none;padding-right:0px"><a href="http://172.18.10.36/supplier" onclick="window.open(this.href);return false;" onkeypress="this.onclick;"><img src="/images/ci/phc_supplier_banner.png" /></a></span>
                <span style="padding-right:0px"><a href="https://www.customs.go.kr" onclick="window.open(this.href);return false;" onkeypress="this.onclick;"><img src="/images/main/ns_ba03.png" /></a></span>
                <span style="padding-right:0px"><a href="https://www.customs.go.kr/cvnci/main.do" onclick="window.open(this.href);return false;" onkeypress="this.onclick;"><img src="/images/main/kcshome_ba10.png" /></a></span>
                <span style="padding-right:0px;display:none;"><a href="http://www.motie.go.kr" onclick="window.open(this.href);return false;" onkeypress="this.onclick;"><img src="/images/main/ns_ba01.png" /></a></span>
                <span style="padding-right:0px;display:none;"><a href="http://www.mosf.go.kr" onclick="window.open(this.href);return false;" onkeypress="this.onclick;"><img src="/images/main/ns_ba02.png" /></a></span>
                <span style="padding-right:0px"><a href="https://unipass.customs.go.kr/clip" onclick="window.open(this.href);return false;" onkeypress="this.onclick;"><img src="/images/main/ns_ba04.png" /></a></span>
                <span style="padding-right:0px"><a href="http://okfta.kita.net" onclick="window.open(this.href);return false;" onkeypress="this.onclick;"><img src="/images/main/ns_ba05.png" /></a></span>
                <span style="padding-right:0px;display:none;"><a href="http://www.ftapass.or.kr" onclick="window.open(this.href);return false;" onkeypress="this.onclick;"><img src="/images/main/ns_ba06.png" /></a></span>
                <span style="padding-right:0px"><a href="https://www.customs.go.kr/ftaportalkor/main.do" onclick="window.open(this.href);return false;" onkeypress="this.onclick;"><img src="/images/main/ns_ba07.png" /></a></span>
                <span style="padding-right:0px"><a href="http://www.fta.go.kr" onclick="window.open(this.href);return false;" onkeypress="this.onclick;"><img src="/images/main/ns_ba08.png" /></a></span>
                <span style="padding-right:0px"><a href="https://www.wto.org/" onclick="window.open(this.href);return false;" onkeypress="this.onclick;"><img src="/images/main/wto_ba09.png" /></a></span>
            </span>
            <span id="divVietnamSiteLink" style="border:0px solid #000;display:none;">
                <span style="padding-right:0px"><a href="https://www.customs.gov.vn/home.aspx?language=en-US" onclick="window.open(this.href);return false;" onkeypress="this.onclick;"><img src="/images/main/vietnam_customs_icon.png" /></a></span>
                <span style="padding-right:0px"><a href="http://www.ecosys.gov.vn/Homepage/HomePage.aspx" onclick="window.open(this.href);return false;" onkeypress="this.onclick;"><img src="/images/main/ecosys_icon.png" /></a></span>
            </span>
            <span id="MMA001_01_span_08" style="display:none;padding-right:0px"><a href="${REMOTE_SERVICE_URL}" onclick="window.open(this.href);return false;" onkeypress="this.onclick;"><img id="MMA001_01_img_01" src="#" /></a></span>
        </div>
        <div style="display:none;"><table id="MMA001_01_grid_03"></table></div>
    </div>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-A001_01.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>