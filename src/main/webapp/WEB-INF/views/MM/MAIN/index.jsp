<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.net.InetAddress" %>
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
<html lang="${_MEMBER.SESSION_DEFAULT_LANGUAGE}">
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
    <link rel="stylesheet" type="text/css" href="/css/main/reset_${_MEMBER.SESSION_DEFAULT_LANGUAGE}.css">
    <link rel="stylesheet" type="text/css" href="/css/main/style.css">
    <!-- css 충돌 -->
    <link rel="stylesheet" type="text/css" href="/css/main/yni-ui.css" />
    <!--[if IE 7]>
        <link rel="stylesheet" type="text/css" href="../css/ie7.css">
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="/css/jqplot/jquery.jqplot.min.css" />
    <link rel="stylesheet" type="text/css" href="/css/jqplot/examples.min.css" />
    <link type="text/css" rel="stylesheet" href="/css/jqplot/syntax/shCoreDefault.min.css" />
    <link type="text/css" rel="stylesheet" href="/css/jqplot/syntax/shThemejqPlot.min.css" />
    
    <link rel="stylesheet" type="text/css" href="/css/jqplot/jquery-ui.min.css" />
    <script type="text/javascript" src="/js/frame/plugins/jquery-1.6.min.js"></script>
    <script type="text/javascript" src="/js/frame/plugins/jquery.number.js"></script>
    <!-- script type="text/javascript" src="/js/frame/plugins/file/bluebird.min.js"></script -->
    <!-- script type="text/javascript" src="/js/frame/plugins/file/html2canvas.min.js"></script>
    <script type="text/javascript" src="/js/frame/plugins/file/jspdf.min.js"></script -->
    <script type="text/javascript" src="/js/frame/plugins/file/jquery.table2excel.js"></script>
    <script type="text/javascript" src="/js/frame/plugins/github/jquery.inputmask.bundle.js"></script>
    <script type="text/javascript" src="/js/frame/message/message_${_MEMBER.SESSION_DEFAULT_LANGUAGE}.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.properties.js"></script>
    <script type="text/javascript" src="/js/frame/common/prototype.js"></script>
    <script type="text/javascript" src="/js/frame/common/utils.js"></script>
    <script type="text/javascript" src="/js/frame/plugins/yniui/jquery.yni-ui.min.js"></script>
    <script type="text/javascript" src="/js/frame/plugins/yniui/datagrid-bufferview.js"></script>
    <script type="text/javascript" src="/js/frame/plugins/yniui/datagrid-export.js"></script>
    <script type="text/javascript" src="/js/frame/plugins/yniui/datagrid-filter.js"></script>
    <script type="text/javascript" src="/js/frame/plugins/yniui/datagrid-groupview.js"></script>
    <script type="text/javascript" src="/js/frame/plugins/yniui/pdfmake.min.js"></script>
    <script type="text/javascript" src="/js/frame/plugins/yniui/vfs_fonts.js"></script>
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
    <script type="text/javascript" src="/js/frame/plugins/design.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.chart.js"></script>
    <script type="text/javascript" src="/js/frame/common/surveyHelper.js"></script>
    <!-- chart -->
    <script type="text/javascript" src="/js/frame/plugins/jqplot/jquery.jqplot.min.js"></script>
    <script type="text/javascript" src="/js/frame/plugins/jqplot/syntax/shCore.min.js"></script>
    <script type="text/javascript" src="/js/frame/plugins/jqplot/syntax/shBrushJScript.min.js"></script>
    <script type="text/javascript" src="/js/frame/plugins/jqplot/syntax/shBrushXml.min.js"></script>
    <script type="text/javascript" src="/js/frame/plugins/jqplot/jqplot.barRenderer.min.js"></script>
    <script type="text/javascript" src="/js/frame/plugins/jqplot/jqplot.canvasAxisLabelRenderer.min.js"></script>
    <script type="text/javascript" src="/js/frame/plugins/jqplot/jqplot.canvasAxisTickRenderer.min.js"></script>
    <script type="text/javascript" src="/js/frame/plugins/jqplot/jqplot.canvasTextRenderer.min.js"></script>
    <script type="text/javascript" src="/js/frame/plugins/jqplot/jqplot.categoryAxisRenderer.min.js"></script>
    <script type="text/javascript" src="/js/frame/plugins/jqplot/jqplot.cursor.min.js"></script>
    <script type="text/javascript" src="/js/frame/plugins/jqplot/jqplot.dateAxisRenderer.min.js"></script>
    <script type="text/javascript" src="/js/frame/plugins/jqplot/jqplot.donutRenderer.min.js"></script>
    <script type="text/javascript" src="/js/frame/plugins/jqplot/jqplot.highlighter.min.js"></script>
    <script type="text/javascript" src="/js/frame/plugins/jqplot/jqplot.json2.min.js"></script>
    <script type="text/javascript" src="/js/frame/plugins/jqplot/jqplot.pieRenderer.min.js"></script>
    <script type="text/javascript" src="/js/frame/plugins/jqplot/jqplot.pointLabels.min.js"></script>
    <script type="text/javascript" src="/js/frame/plugins/jqplot/example.min.js"></script>
    <!-- SNS -->
    <script type="text/javascript" src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
    <!-- mathjax -->
    <script type="text/x-mathjax-config">
        MathJax.Hub.Config({
            extensions: ["TeX/cancel.js"], // this allows use of the "cancel" macro
            tex2jax: {
                inlineMath: [ ['$','$'] ],   
                displayMath: [ ['$$','$$'] ],
                processEscapes: true         // this allows me to use a literal dollar sign, \$, outside of math mode
            }
        });
    </script>
    <script type="text/javascript" src="https://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-MML-AM_CHTML"></script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
	    <div data-options="region:'north',border:false" style="height:54px;overflow:hidden;">
	        <div id="wrap" class="sch_box">
	            <div id="header">
	                <form id="MM0001_01_form_1" name="MM0001_01_form_1" method="post">
	                    <input type="hidden" id="selectedLanguage" name="selectedLanguage" value="${_MEMBER.SESSION_DEFAULT_LANGUAGE}"/>
	                    <input type="hidden" id="USER_ID" name="USER_ID" value="${_MEMBER.SESSION_USER_ID}"/>
	                    <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${sessionScope._MEMBER.SESSION_COMPANY_CD}"/>
	                    <input type="hidden" id="CERTIFY_TYPE" name="CERTIFY_TYPE" value="${_MEMBER.SESSION_CERTIFY_TYPE}"/>
	                    <input type="hidden" id="SR_IP" name="SR_IP" value="<%=request.getServerName()%>"/>
	                    <input type="hidden" id="SR_PORT" name="SR_PORT" value="<%=request.getServerPort()%>"/>
	                    <input type="hidden" id="SUPPLIER_TYPE" name="SUPPLIER_TYPE" value="${_MEMBER.SUPPLIER_TYPE}"/>
	                    <input type="hidden" id="BUSINESS_NO" name="BUSINESS_NO" value="${_MEMBER.BUSINESS_NO}"/>
	                    <input type="hidden" id="HELP_SITE" name="HELP_SITE" value="${_MEMBER.SESSION_TOMS_CLOUD_SITE}"/>
	                    <input type="hidden" id="CURRENT_PID" name="CURRENT_PID" value="MM-0000_01"/>
	                    <p class="lang">
	                        <a href="javascript:MM0001_01.control.changeLanguage('KR')"><img src="../images/flags/kr.png" alt="한국어" class="flag_icon"/></a>
	                        <a href="javascript:MM0001_01.control.changeLanguage('EN')" id="MM0001_01_href_1" style="display:none;"><img src="../images/flags/us.png" alt="English" class="flag_icon"/></a>
	                        <a href="javascript:MM0001_01.control.changeLanguage('ES')" id="MM0001_01_href_2" style="display:none;"><img src="../images/flags/mx.png" alt="español" class="flag_icon"/></a>
	                        <a href="javascript:MM0001_01.control.changeLanguage('VN')" id="MM0001_01_href_3" style="display:none;"><img src="../images/flags/vn.png" alt="Việt nam" class="flag_icon"/></a>
	                    </p>
	                    <div class="top_area">
	                        <p id="MM0001_01_pan_01">
	                            <label for="company">${_MEMBER.SESSION_COMPANY_NAME}</label>
	                            <select name="selectedCompnay" id="selectedCompnay" onchange="javascript:MM0001_01.control.changeCompany()"></select>
	                        </p>
	                        <p id="MM0001_01_pan_02" style="display:none;">
	                            <a href="javascript:MM0001_01.dialog.openDialog_3();" style="width:220px;">${_MEMBER.SESSION_COMPANY_NAME}</a>
	                        </p>
	                        <ul>
	                            <li id="MM0001_01_li_1" class="first-child" style="display:none;"><a href="javascript:MM0001_01.dialog.dialogOpen_1();" title="Help">&nbsp;Help(F1)</a></li>
	                            <li id="MM0001_01_li_2" class="quest" style="display:none;padding:0 18px 0 18px;"><span id="MM0001_01_span_03" class="number_alert">0</span><span style="float:right"><a href="javascript:MM0001_01.dialog.dialogOpen_4();" title="TOMS <spring:message code="UPDAT"/>/<spring:message code="INQUI"/>">&nbsp;TOMS <spring:message code="UPDAT"/>/<spring:message code="INQUI"/></a></span></li>
	                            <li><a href="javascript:MM0001_01.dialog.dialogOpen_2();">&nbsp;<span id="MM0001_01_span_02"></span> (${_MEMBER.SESSION_USER_NAME})</a></li>
	                            <li class="last"><a href="javascript:MM0001_01.control.go_logout()" title="Logout">Logout</a></li>
	                        </ul>
	                    </div>
	                </form>
	            </div><!-- //header -->
	        </div>
	    </div>
	    <!-- body -->
	    <div id="contents_structure" data-options="region:'center',border:false">
	        <div class="easyui-layout" data-options="fit:true">
	           <div data-options="region:'west',border:false" style="width:30px;overflow:hidden;">
	                <div id="container" class="con_wrap">
	                    <div class="aside">
	                        <div class="aside_bar">
	                            <p class="btn_open"><img height="36px" src="../images/icon/aside_close.gif" alt="<spring:message code="TXT_BUTTON"/>"></p>
	                            <ul class="left_tabs">
	                                <li id="tab1" style="display:none;"><a href="#" class="leftmenu" onclick="tabChange2('leftmenu',1,'tab',8); return false;"><span class="vertical-text" style="line-height:22px;font-family:-webkit-pictograph;font-size:10pt;"><b>F<br>T<br>A</b></span></a></li>
	                                <li id="tab2" style="display:none;"><a href="#" class="leftmenu" onclick="tabChange2('leftmenu',2,'tab',8); return false;"><span class="vertical-text">수<br>출<br>입<br>신<br>고</span></a></li>
                                    <li id="tab3" style="display:none;"><a href="#" class="leftmenu" onclick="tabChange2('leftmenu',3,'tab',8); return false;"><span class="vertical-text">커<br>스<br>터<br>마<br>이<br>징</span></a></li><!-- 통관 -->
                                    <li id="tab7" style="display:none;"><a href="#" class="leftmenu" onclick="tabChange2('leftmenu',7,'tab',8); return false;"><span class="vertical-text">리<br>포<br>트</span></a></li>
                                    <li id="tab4" style="display:none;"><a href="#" class="leftmenu" onclick="tabChange2('leftmenu',4,'tab',8); return false;"><span class="vertical-text">원<br>가<br>계<br>산</span></a></li>
                                    <li id="tab8" style="display:none;"><a href="#" class="leftmenu" onclick="tabChange2('leftmenu',8,'tab',8); return false;"><span class="vertical-text">중<br>계<br>서<br>버</span></a></li>
	                                <li id="tab5" style="display:none;"><a href="#" class="set" onclick="tabChange2('leftmenu',5,'tab',8); return false;"><span class="vertical-text" style="font-family:-webkit-pictograph;font-size:10pt;"><b>S<br>E<br>T<br>U<br>P</b></span></a></li>
	                                <li id="tab6" style="display:none;"><a href="#" class="leftmenu" onclick="tabChange2('leftmenu',6,'tab',8); return false;"><span class="vertical-text" style="line-height:22px;font-family:-webkit-pictograph;font-size:10pt;"><b>F<br>T<br>A</b></span></a></li>
	                            </ul>
	                        </div>
	                    </div>
	                </div>
	            </div>
	            <div data-options="region:'center',border:false"> <!-- 컨텐츠 영역-->
	                <div id="contents_layout" class="easyui-layout" data-options="fit:true">
	                    <div id="MM0001_01_div_01" data-options="region:'west',border:false,animate:true" style="background-color:#fff;width:243px;overflow:hidden;">
	                        <div id="log"></div>
						    <div id="menuOuter">
						        <!-- 메인메뉴 -->
						        <div id="menuWrap">
						            <p class="lnb_tit">
						                <a href="javascript:MM0001_01.control.go_home();"><span id="MM0001_01_span_01"></span></a>
						                <em>
						                   <a id="expandMenuBtn" href="#"><img src="../images/icon/btn_down.png" alt="아래로" /></a>
						                   <a id="collapseMenuBtn" href="#"><img src="../images/icon/btn_up.png" alt="위로" /></a>
						                </em>
						            </p>
						            <div id="leftmenu1">
						                <ul class="menu_area" id="menu_area1"></ul>
						            </div>
						            <div id="leftmenu2" style="display:none;">
						                <ul class="menu_area" id="menu_area2"></ul>
						            </div>
						            <div id="leftmenu3" style="display:none;">
						                <ul class="menu_area" id="menu_area3"></ul>
						            </div>
						            <div id="leftmenu4" style="display:none;">
                                        <ul class="menu_area" id="menu_area4"></ul>
                                    </div>
                                    <div id="leftmenu5" style="display:none;">
                                        <ul class="menu_area" id="menu_area5"></ul>
                                    </div>
                                    <div id="leftmenu6" style="display:none;">
                                        <ul class="menu_area" id="menu_area6"></ul>
                                    </div>
                                    <div id="leftmenu7" style="display:none;">
                                        <ul class="menu_area" id="menu_area7"></ul>
                                    </div>
                                    <div id="leftmenu8" style="display:none;">
                                        <ul class="menu_area" id="menu_area8"></ul>
                                    </div>
						         </div><!-- menuWrap -->
						    </div><!-- menuOuter -->
	                        <div class="aside">
	                            <div class="lnb">
	                                <p class="sch_area">
	                                    <input type="text" name="" value="" id="sch_m" style="width:85%;height:18px;border:1px solid #dfdfdf;"/><label for="sch_m">
	                                    <a href="#"><img src="../images/icon/btn_sch2.gif" alt="검색" /></a></label>
	                                </p>
	                                <!-- 즐겨찾기 -->
	                                <div id="leftmenu2" style="display:none;">
	                                    <p class="lnb_tit2">
	                                        <span>Configuration</span>
	                                        <em>
	                                            <a href="#"><img src="../images/icon/btn_down.png" alt="아래로" /></a>
	                                            <a href="#"><img src="../images/icon/btn_up.png" alt="위로" /></a>
	                                        </em>
	                                    </p>
	                                    <ul class="bmk_area" style="display:online;">
	                                        <li class="on"><a href="#">메뉴 stc_Lvl01</a></li><!-- 선택된 경우 class="on"  추가 -->
	                                        <li><a href="#">메뉴 stc_Lvl01</a></li>
	                                        <li><a href="#">메뉴 stc_Lvl01</a></li>
	                                    </ul>
	                                </div>
	                            </div>
	                        </div>
	                    </div><!-- data-options -->
	                    <div id="MM0001_01_div_02" data-options="region:'center',border:false" style="padding:8px 5px 0px;">
	                        <div id="MM0001_01_tabs_01" class="easyui-tabs" data-options="fit:true,showHeader:true,plain:false,tabPosition:'bottom'" style="height:auto;"></div>
	                        <div id="main_tab_menu" class="easyui-menu" data-options="inline:false" style="width:120px;display:none;">
				                <div onclick="javascript:MM0001_01.tabs.closeAll()"><spring:message code="CLALL"/></div>
				                <div onclick="javascript:MM0001_01.tabs.closeToLeft()"><spring:message code="CLLFT"/></div>
				                <div onclick="javascript:MM0001_01.tabs.closeToRight()"><spring:message code="CLRIT"/></div>
				                <div onclick="javascript:MM0001_01.tabs.closeOthers()"><spring:message code="CLOTH"/></div>
				                <div class="menu-sep"></div>
				                <div onclick="javascript:MM0001_01.tabs.close()"><spring:message code="CLOSE"/></div>
				            </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	    <div id="footer" data-options="region:'south',border:false" style="height:30px;padding:0;">
	        <p>
	            <a href="javascript:MM0001_01.control.go_home();"><img src="../images/icon/foot_iome.gif" alt="home" /></a>
	            <span id="MM0001_01_span_04" style="font-color:#eee"></span>
	        </p>
	        <ul>
	            <li style="padding:6px 5px 0px 0px;color:#6f6d6d;font-size:8pt;">
	                Copyrightⓒ 2016 DAEU C&A, All Rights Reserved. | Version 2.7.3, 2022/05/09
	            </li>
	            <!-- li><a href="#"><img src="../images/icon/foot_arr.gif" alt="이전" /></a></li>
	            <li><a href="#"><img src="../images/icon/foot_arr2.gif" alt="다음" /></a></li>
	            <li><a href="#"><img src="../images/icon/foot_btn1.gif" alt="위로" /></a></li>
	            <li><a href="#"><img src="../images/icon/foot_btn2.gif" alt="새창" /></a></li>
	            <li><a href="#"><img src="../images/icon/foot_btn3.gif" alt="세로면분할" /></a></li>
	            <li><a href="#"><img src="../images/icon/foot_btn4.gif" alt="가로면분할" /></a></li>
	            <li><a href="#"><img src="../images/icon/foot_btn5.gif" alt="" /></a></li>
	            <li><a href="#"><img src="../images/icon/foot_btn6.gif" alt="" /></a></li -->
	        </ul>
	        <div data-options="region:'east',border:false" style="width:5px;"> <%--background: url(/images/frameset/frame_right.jpg) repeat-y;--%>
	            <div id="right_top"></div>
	            <div id="main_win" class="easyui-window" title="Warning" data-options="modal:true,closed:true,resizable:false,minimizable:false,maximizable:false,collapsible:false" style="width:550px; height:300px; padding:0;">
	                <div class="easyui-layout" data-options="fit:true">
	                    <div data-options="region:'center', border:false" style="padding:10px;background:#5583a5;color:#fff;">
	                        <div id="main_win_message"></div>
	                    </div>
	                    <div data-options="region:'south',border:false" style="text-align: center; padding: 5px 0 5px 0;">
	                        <a class="easyui-linkbutton" href="javascript:void(0)" onclick="javascript:$('#main_win').window('close');">Ok</a>
	                    </div>
	                </div>
	            </div>
	            <div id="main_win_1" class="easyui-window" title="Notice" data-options="modal:true,closed:true,resizable:false,minimizable:false,maximizable:false,collapsible:false" style="width:650px; height:500px; padding:0;">
	                <div class="easyui-layout" data-options="fit:true">
	                    <div data-options="region:'center', border:false" style="padding:10px;background:#5583a5;color:#fff;">
	                        <div id="main_win_message_1"></div>
	                    </div>
	                    <div data-options="region:'south',border:false" style="text-align: center; padding: 5px 0 5px 0;">
	                        <a class="easyui-linkbutton" href="javascript:void(0)" onclick="javascript:$('#main_win_1').window('close');">Ok</a>
	                    </div>
	                </div>
	            </div>
	            <div id="main_win_2" class="easyui-window" title="TOMS <spring:message code="UPDAT"/>/<spring:message code="INQUI"/>" data-options="modal:true,closed:true,resizable:false,minimizable:false,maximizable:true,collapsible:false" style="width:1200px;height:800px;padding:0;">
                    <div class="easyui-layout" data-options="fit:true">
                        <form id="MM0001_01_form_2" name="MM0001_01_form_2" method="post">
	                        <input type="hidden" id="CERTIFY_TYPE" name="CERTIFY_TYPE" value="${_MEMBER.SESSION_CERTIFY_TYPE}"/>
                        </form>
                        <form id="MM0001_01_form_3" name="MM0001_01_form_3" method="post">
                            <input type="hidden" name="COMPANY_CD" id="COMPANY_CD" value="KJ100"> <!-- 오픈시에는 "KJ100"으로 변경할 것 -->
                            <input type="hidden" name="INQUIRY_NAME" id="INQUIRY_NAME">
                            <input type="hidden" name="INQUIRY_ID" id="INQUIRY_ID">
                            <input type="hidden" name="INQUIRY_POSITION" id="INQUIRY_POSITION">
                            <input type="hidden" name="INQUIRY_TEL" id="INQUIRY_TEL">
                            <input type="hidden" name="INQUIRY_EMAIL" id="INQUIRY_EMAIL">
                            <input type="hidden" name="CUSTOMER_NAME" id="CUSTOMER_NAME">
                            <input type="hidden" name="INQUIRY_TYPE" id="INQUIRY_TYPE">
                            <input type="hidden" name="APPLICATION_SERVICE_TYPE" id="APPLICATION_SERVICE_TYPE" value="${_MEMBER.APPLICATION_SERVICE_TYPE}">
                            <input type="hidden" name="CERTIFY_TYPE" id="CERTIFY_TYPE">
                            <input type="hidden" name="FTA_NATION" id="FTA_NATION">
                            <input type="hidden" name="LICENSE_KEY" id="LICENSE_KEY">
                            <input type="hidden" name="MEMBER_ID" id="MEMBER_ID">
                            <input type="hidden" id="URL" name="URL" value="${_MEMBER.SESSION_TOMS_CLOUD_SITE}/ch/link/inbound/selectNoticeCount"/>
                            <input type="hidden" id="LANGUAGE" name="LANGUAGE" value="${_MEMBER.SESSION_DEFAULT_LANGUAGE}"/>
                        </form>
                        <form id="MMA031_03_form_00" name="MMA031_03_form_00" target="MM0001_01_iframe_1" method="post">
						    <input type="hidden" name="INQUIRY_NAME" id="INQUIRY_NAME">
						    <input type="hidden" name="INQUIRY_ID" id="INQUIRY_ID">
						    <input type="hidden" name="INQUIRY_POSITION" id="INQUIRY_POSITION">
						    <input type="hidden" name="INQUIRY_TEL" id="INQUIRY_TEL">
						    <input type="hidden" name="INQUIRY_EMAIL" id="INQUIRY_EMAIL">
						    <input type="hidden" name="CUSTOMER_NAME" id="CUSTOMER_NAME">
						    <input type="hidden" name="INQUIRY_TYPE" id="INQUIRY_TYPE">
						    <input type="hidden" name="APPLICATION_SERVICE_TYPE" id="APPLICATION_SERVICE_TYPE" value="${_MEMBER.APPLICATION_SERVICE_TYPE}">
						    <input type="hidden" name="CERTIFY_TYPE" id="CERTIFY_TYPE">
						    <input type="hidden" name="FTA_NATION" id="FTA_NATION">
						    <input type="hidden" name="LICENSE_KEY" id="LICENSE_KEY">
						    <input type="hidden" name="MEMBER_ID" id="MEMBER_ID">
						    <input type="hidden" id="LANGUAGE" name="LANGUAGE" value="${_MEMBER.SESSION_DEFAULT_LANGUAGE}"/>
						</form>
                        <iframe id="MM0001_01_iframe_1" name="MM0001_01_iframe_1" style="width:100%;height:100%;border:1px solid #c7cbce;"></iframe>
                    </div>
                </div>
	        </div>
	    </div>
	    <!-- //footer -->
	</div>
    
    <script type="text/javascript">
        SESSION = {
             COMPANY_CD         : "${_MEMBER.SESSION_COMPANY_CD}"
            ,USER_ID            : "${_MEMBER.SESSION_USER_ID}"
            ,DIVISION_CD        : "${_MEMBER.SESSION_DIVISION_CD}"
            ,COMPANY_NAME       : "${_MEMBER.SESSION_COMPANY_NAME}"
            ,USER_NAME          : "${_MEMBER.SESSION_USER_NAME}"
            ,USER_AUTH          : "${_MEMBER.SESSION_USER_AUTH}"
            ,DEFAULT_LANGUAGE   : "${_MEMBER.SESSION_DEFAULT_LANGUAGE}"
            ,WORK_YYYY_MM       : "${_MEMBER.SESSION_WORK_YYYY_MM}" // DB에 지정된 일자
            ,CERTIFY_TYPE       : "${_MEMBER.SESSION_CERTIFY_TYPE}"
            // 협력사 포탈에서 로그인했을 경우에만 생성되는 정보임.
            ,VENDOR_NAME        : "${_MEMBER.SESSION_VENDOR_NAME}"
            ,VENDOR_CD          : "${_MEMBER.SESSION_VENDOR_CD}"
            ,FTA_NATION         : "${_MEMBER.SESSION_FTA_NATION}"
            ,MAX_UPLOAD_SIZE    : "${_MEMBER.SESSION_MAX_UPLOAD_SIZE}"
            ,LOCAL_CURRENCY     : "${_MEMBER.SESSION_LOCAL_CURRENCY}"
            ,EXCHANGE_CURRENCY  : "${_MEMBER.SESSION_EXCHANGE_CURRENCY}"
            ,EXCHANGE_CURRENCY_TYPE  : "${_MEMBER.SESSION_EXCHANGE_CURRENCY_TYPE}"
            ,EXCHANGE_RATE      : "${_MEMBER.SESSION_EXCHANGE_RATE}"
            ,VENDOR_CO_TARGET   : "${_MEMBER.SESSION_VENDOR_CO_TARGET}"
            ,APPLICATION_SERVICE_TYPE : "${_MEMBER.APPLICATION_SERVICE_TYPE}"
            ,CONTEXTPATH : "${pageContext.request.contextPath}"
            ,TRANSACTION_ID : oUtil.getUID()
            ,AMOUNT_POINT_NM : "${_MEMBER.SESSION_AMOUNT_POINT_NM}"
            ,KAKAO_SCRIPT_KEY : "${_MEMBER.SESSION_KAKAO_SCRIPT_KEY}"
        };

        // 공통 설정으로 적용
        if(!oUtil.isNull(SESSION.DEFAULT_LANGUAGE)) {
            DEFAULT_LANGUAGE = SESSION.DEFAULT_LANGUAGE;
            NATION_DATE_VIEW = SESSION.FTA_NATION;
        }
    </script>
    <script type="text/javascript" src="/js/views/MM/MAIN/MM-0001_01.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>
    
</body> 
</html>
