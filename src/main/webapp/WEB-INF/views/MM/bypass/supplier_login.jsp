<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<%   
	response.setHeader("Cache-Control","no-store");   
	response.setHeader("Pragma","no-cache");   
	response.setDateHeader("Expires",0);   
	if (request.getProtocol().equals("HTTP/1.1")) response.setHeader("Cache-Control", "no-cache"); 
%> 
<html lang="ko">
<head>
    <meta charset=utf-8>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="apple-mobile-web-app-title" content="">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=yes">
    <meta http-equiv="Expires" content="-1">
	<meta http-equiv="Pragma" content="no-cache">
	<meta http-equiv="Cache-Control" content="No-Cache">
    <title>TOMS(Total Origin Management Solution)</title>
    <link href="/images/ci/TOMSlogo.png" rel="shortcut icon" type="image/x-icon" sizes="32x32"/>
    <!--[if lt IE 9]>
        <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="/css/import.css">
    <link rel="stylesheet" type="text/css" href="/css/yni-ui.css" /> 
    <!--[if IE 7]>
        <link rel="stylesheet" type="text/css" href="../css/ie7.css">
    <![endif]-->
    <script type="text/javascript" src="/js/frame/plugins/jquery-1.6.min.js"></script>
    <script type="text/javascript" src="/js/frame/message/message_${chageLanguage}.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.properties.js"></script>
    <script type="text/javascript" src="/js/frame/common/prototype.js"></script>
    <script type="text/javascript" src="/js/frame/common/utils.js"></script>
    <script type="text/javascript" src="/js/frame/plugins/yniui/jquery.yni-ui.min.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.validator.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.form.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.grid.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.dialog.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.combo.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.calendar.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.tooltip.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.tab.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.tree.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.ctrl.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.themes.js"></script>
</head>
<body>
    <div id="wrap">
        <div id="login_wrap">
            <div class="login_con">
                <ul>
                    <li>
	                    <div class="login_area">
	                        <ul>
	                            <li>
	                                <div><img src="/images/ci/TOMSlogo-01.png"/></div>
	                                <span id="MM0000_01_div_01"></span>
	                            </li>
	                        </ul>
	                    </div>
	                </li>
	            </ul>
	        </div>
	        <div class="login_con1">
	            <ul>
                    <li>
	                    <div class="login_info">
	                        <div id="MM0000_01_div_03" style="display:none;">
	                            <div style="padding:10px 70px 10px 70px;border:1px solid #c3c3c3;">
		                            <form id="MM0000_01_form_01" name="MM0000_01_form_01" method="post" target="_top">
		                                <input type="hidden" id="CERTIFY_TYPE" name="CERTIFY_TYPE" value="external"/>
		                                <input type="hidden" id="MENU_ID" name="MENU_ID" value="MM-0000"/>
		                                <input type="hidden" id="REQUEST_TYPE" name="REQUEST_TYPE" value="0"/>
		                                <input type="hidden" id="APPLICATION_SERVICE_TYPE" name="APPLICATION_SERVICE_TYPE" value="${APPLICATION_SERVICE_TYPE}"/>
		                                <input type="hidden" id="SUPPLIER_TYPE" name="SUPPLIER_TYPE" value="${SUPPLIER_TYPE}"/>
		                                <table>
	                                        <colgroup>
	                                            <col width="100px;" />
	                                            <col width="230px;" />
	                                            <col width=";"/>
	                                        </colgroup>
	                                        <tbody>
	                                            <tr style="height:40px;">
	                                                <th><span id="MM0000_01_div_02"></span></th>
	                                                <td>
	                                                    <input name="REQUEST_COMPANY_CD" id="REQUEST_COMPANY_CD" style="width:210px;height:28px;margin-left:5px;"/>
	                                                </td>
	                                                <td>
	                                                    <div id="MM0000_01_div_05" style="display:none"><spring:message code="TXT_USER_WORK_COMPANY_SELECT"/></div>
	                                                    <div id="MM0000_01_div_06" style="display:none"><spring:message code="TXT_TRADE_CUSTOMER_SELECT"/></div>
	                                                </td>
	                                            </tr>
	                                            <tr style="height:40px;">
	                                                <th><label id="language"><spring:message code="LANG"/></label></th>
	                                                <td>
	                                                    <input id="selectedLanguage" name="selectedLanguage" style="width:210px;height:28px;"/>
	                                                </td>
	                                                <td><spring:message code="TXT_SCREEN_LANGUAGE_SEL"/></td>
	                                            </tr>
	                                            <tr style="height:40px;">
	                                                <th class="point2"><label><spring:message code="ID"/></label></th>
	                                                <td>
	                                                    <input type="text" name="REQUEST_USER_ID" id="REQUEST_USER_ID" class="easyui-validatebox input_login" required="true" search="MM0000_01.control.login" style="width:204px;border:1px solid #c3c3c3;"/>
	                                                </td>
	                                                <td><spring:message code="TXT_USER_ID_INPUT"/></td>
	                                            </tr>
	                                            <tr style="height:40px;">
	                                                <th class="point2"><label><spring:message code="PWD"/></label></th>
	                                                <td>
	                                                    <input type="password" name="REQUEST_PASSWORD" id="REQUEST_PASSWORD" autocomplete="off" class="easyui-validatebox input_login" required="true" search="MM0000_01.control.login" style="width:204px;border:1px solid #c3c3c3;"/>
	                                                </td>
	                                                <td><spring:message code="TXT_USER_PASS_INPUT"/></td>
	                                            </tr>
	                                            <tr style="height:30px;">
	                                                <td colspan="3" style="text-align:center;">
		                                                <input type="checkbox" name="chk_info" id="chk_info" value="0"> <spring:message code="TXT_LOGIN_INFO_MAIN"/>
	                                                </td>
	                                            </tr>
	                                            <tr style="height:50px;">
	                                                <td colspan="3" style="text-align:center;">
	                                                    <span><a href="javascript:MM0000_01.control.login();" class="btnLoginBlue"><spring:message code="LOGIN"/></a></span>
	                                                    <span id="MM0000_01_span_01" style="display:none;padding-right:5px;"><a href="javascript:MM0000_01.ui.prevPageMove();" class="btnLoginGrey"><spring:message code="TO_FIRST"/></a></span>
	                                                </td>
	                                            </tr>
	                                        </tbody>
	                                    </table>
		                            </form>
		                            <form id="MM0000_01_form_03" name="MM0000_01_form_03" method="post">
		                                <input type="hidden" name="REQUEST_USER_ID" id="REQUEST_USER_ID"/>
		                                <input type="hidden" name="REQUEST_COMPANY_CD" id="REQUEST_COMPANY_CD"/>
		                            </form>
		                        </div>
	                            <div style="padding-top:5px;font-size:14px;text-align:center;">
	                                <div id="MM0000_01_div_07" style="display:none">* <spring:message code="TXT_FIREST_ID_PASSWORD_EMAIL"/></div>
	                                <div id="MM0000_01_div_08" style="display:none">* <spring:message code="TXT_FIRST_LOGIN_SUPPLIER_PWD"/></div>
	                                <p> <spring:message code="TXT_VENDOR_USER_NAME"/> : <span id="MM0000_01_span_04"></span>, <spring:message code="THPIC_EMAIL"/> : <span id="MM0000_01_span_02"></span>, <spring:message code="THPIC_TELNM"/> : <span id="MM0000_01_span_03"></span>
	                            </div>
	                        </div>
	                        <div id="MM0000_01_div_04" style="display:none;padding:0 0 0 15px;">
	                            <form id="MM0000_01_form_02" name="MM0000_01_form_02" method="post">
	                                <input type="hidden" id="REQ_TYPE" name="REQ_TYPE" value="EXISTS"/>
	                                <input type="hidden" id="COMPANY_CD" name="COMPANY_CD"/>
	                                <input style="display:none;width:0px;">
	                                <table>
				                        <tbody>
				                            <tr>
                                                <td style="height:100px;text-align:center;font-size:27px;font-family:'맑은고딕',malgun gothic;">
                                                    <span><spring:message code="TXT_BUSINESS_COMPANY_INPUT_CONNECT"/></span>
                                                </td>
                                            </tr>
				                            <tr style="height:55px;">
				                                <td style="text-align:center;">
				                                    <input type="text" name="BUSINESS_NO" id="BUSINESS_NO" class="easyui-validatebox input_login" required="true" style="width:400px;border:1px solid #c3c3c3;"/>
				                                </td>
				                            </tr>
                                            <tr>
				                                <td style="text-align:center;">
				                                   <a href="javascript:MM0000_01.control.selectFTAMgmtSysCompany();" class="btnLoginBlue"><spring:message code="FTA_ORIGIN_MAN_SYSTEM"/></a>
				                                   <a href="javascript:MM0000_01.control.selectSupplierSysCompany();" class="btnLoginBlue"><spring:message code="FTA_ORIGIN_SUBMIT_SYSTEM"/></a>
				                                </td>
				                            </tr>
				                            <tr>
                                                <td style="padding:15px 40px 0px 40px;font-size:16px;">
                                                    <ul>
                                                        <li><spring:message code="FTA_DETER_INSSUE_SYSTEM"/></li>
                                                        <li><spring:message code="ORIGIN_SUBMIT_UPLOAD_SYSTEM"/></li>
                                                        <p style="border:none; border:1px solid gray;border-bottom:0px;"></p>
                                                        <!-- li>전국 산업단지 클라우드 서비스 가입을 원하는 업체입니까? <a href="http://www.k-cloud.or.kr" target="_blank" class="btnUserAdd"><font style="color:blue;font-weight:bold;">"2017년 산업단지 클라우드"</font>서비스 가입</a></li -->
                                                        <li><spring:message code="TOMS_FTA_NEW_APPLY"/> <a href="javascript:MM0000_01.dialog.openDialog_1();" class="btnUserAdd"><spring:message code="JOIN_APPLICAT_WRITE"/></a></li>
                                                        <li><spring:message code="TOMS_USE_INQUIRE_QUEST"/> <a href="javascript:MM0000_01.dialog.openDialog_2();" class="btnSendEmail"><spring:message code="ONLINE_INQUIRE"/></a></li>
                                                    </ul>
                                                </td>
                                            </tr>
				                        </tbody>
				                    </table>
	                            </form>
	                        </div>
	                    </div>
	                </li>
	            </ul>
            </div>
            <div class="login_con2">
                <div class="cp_area">
                    <span style="font-size: 14px;color: #575757;"><spring:message code="CALL_CENTER"/> : 1588-1037 | <spring:message code="TXT_EMAIL"/> : 강은석,silverstone@daeucna.com  | <spring:message code="FAX"/> : 070-8282-0022</span>
                    <div style="padding-top:3px;font-size:12px;color: #888;">Copyrightⓒ 2016 DAEU C&A, All RIGHTS RESERVED.</div>
                </div>
            </div>
            <div id="login_win" class="easyui-window" title="<spring:message code="NOTIC"/>" data-options="modal:false,closed:true,resizable:false,minimizable:false,maximizable:false,collapsible:false" style="width:412px; height:591px;left:20px;top:20px;padding:0;">
                <div class="easyui-layout" data-options="fit:true">
                    <div data-options="region:'center', border:false" style="padding:0px;">
                        <a href="http://www.tomsfta.com" target="_blank"><img src="/images/main/domain_alter.png" style="width:100%;height:532px;"/></a>
                        <span style="font-size:8pt;"><a href="javascript:MM0000_01.control.popupClose();" class="btn_black">[ <spring:message code="TXT_TODAY_NO_SEE"/> ]</a></span>
                        <span style="float:right;color:#005699;font-size:8pt;">* <spring:message code="NOTICE_CLICK_NEW_ADDRESS"/></span>
                    </div>
                </div>
            </div>
        </div>
    </div><!-- //wrap -->
    
    <script type="text/javascript" src="/js/views/MM/bypass/MM-0000_01.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>
    
</body>
</html>
