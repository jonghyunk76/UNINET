<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%
 /**
  * @Class Name : MM-A029_01.jsp
  * @Description : 도움말
  * @Modification Information
  *
  * author carlos
  *
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMA029_01_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html lang="${LANG}">
<head>
    <meta charset=utf-8>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="apple-mobile-web-app-title" content="">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=yes">
    <title>TOMS Help</title>
    <!--[if lt IE 9]>
        <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="/css/main/import.css">
    <!-- css 충돌 -->
    <link rel="stylesheet" type="text/css" href="/css/yni-ui.css" />
    <!--[if IE 7]>
        <link rel="stylesheet" type="text/css" href="../css/ie7.css">
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="/css/jqplot/jquery-ui.min.css" />
    <script type="text/javascript" src="/js/frame/plugins/jquery-1.6.min.js"></script>
    <script type="text/javascript" src="/js/frame/message/message_${LANG}.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.properties.js"></script>
    <script type="text/javascript" src="/js/frame/common/prototype.js"></script>
    <script type="text/javascript" src="/js/frame/common/pop_utils.js"></script>
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
</head>
<body>
    <div class="easyui-layout" data-options="fit:true">
        <!-- div data-options="region:'north',border:false" style="padding-top:5px;overflow:hidden;">
            <ul class="tab_area" style="padding-left:3px;">
                <li id="MMA029_01_divMove01" class="on"><a href="javascript:MMA029_01.ui.divMove('1');"><spring:message code="CONTS"/></a></li>
                <li id="MMA029_01_divMove02"><a href="javascript:MMA029_01.ui.divMove('2');"><spring:message code="SRCH"/></a></li>
            </ul>
        </div-->
        <div data-options="region:'center',border:false" style="padding:3px 4px 2px 4px;">
	        <div id="MMA029_01_tabs_01" class="easyui-tabs" data-options="fit:true,showHeader:true,plain:true" style="height:auto;">
	            <div title="&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code='SRCH'/>&nbsp;&nbsp;&nbsp;&nbsp;" style="border: 1px solid #95b8e7;">
                    <div class="easyui-layout" data-options="fit:true">
                        <div data-options="region:'north',border:false" style="width:auto;">
                            <div class="easyui-layout" data-options="fit:true">
                                <div data-options="region:'north',border:false" class="h2_etc" style="background:#fff;padding:6px 6px 0px 6px;"> 
                                    <p class="h2"><spring:message code="TXT_SEARCH_CONDITION"/></p>
                                </div>
                                <div data-options="region:'center',border:false" style="background:#fff;padding:0px 6px 3px 6px;">
                                    <form id="MMA029_01_form_02" name="MMA029_01_form_02" method="POST">
                                        <input type="hidden" id="schKeyLike" name="schKeyLike" value="C"/>
                                        <input type="hidden" id="SESSION_COMPANY_CD" name="SESSION_COMPANY_CD" value="TOMS"/>
                                        <input type="hidden" id="SESSION_CERTIFY_TYPE" name="SESSION_CERTIFY_TYPE" value="${CERTIFY_TYPE}"/>
                                        <input type="hidden" id="SESSION_USER_ID" name="SESSION_USER_ID" value="fta"/>
                                        <input type="hidden" id="SESSION_DEFAULT_LANGUAGE" name="SESSION_DEFAULT_LANGUAGE" value="${LANG}"/>
                                        <input style="display:none;"/>
                                        <table style="width:100%;">
                                            <colgroup>
                                                <col width=";" />
                                                <col width="90px;"/>
                                            </colgroup>
                                            <tbody>
                                                <tr>
                                                    <td>
                                                        <input type="text" name="schKeyWord" id="schKeyWord" class="easyui-validatebox" required="true" style="width:100%;padding-left:5px;" search="MMA029_01.control.selectKeyContents" />
                                                    </td>
                                                    <td style="text-align:center;">
                                                        <a href="javascript:MMA029_01.control.selectKeyContents();" class="easyui-linkbutton pop_sch"><spring:message code="SRCH"/></a>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div data-options="region:'center',border:false" style="overflow:hidden;background:#fff;">
                            <div class="easyui-layout" data-options="fit:true">
                                <div data-options="region:'north',border:false" class="h2_etc" style="background:#fff;padding:3px 6px 0px 6px;"> 
                                    <p class="h2"><spring:message code="CONTS"/></p>
                                </div>
                                <div data-options="region:'center',border:false" style="background:#fff;padding:0px 6px 6px 6px;">
                                    <div id="MMA029_01_div_01"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
	            <div title="&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code='CONTS'/>&nbsp;&nbsp;&nbsp;&nbsp;" style="border: 1px solid #95b8e7;">
	                <div class="easyui-layout" data-options="fit:true">
	                    <div data-options="region:'west',border:false,split:true" style="width:350px;background:#fff;padding:3px;">
	                        <div class="easyui-layout" data-options="fit:true">
		                        <div data-options="region:'north',border:false" class="h2_etc" style="background:#fff;"> 
		                            <p class="h2"><spring:message code="LIST"/></p>
		                        </div>
		                        <div data-options="region:'center',border:false" style="background:#fff;border:1px solid #b7d2ff;">
		                            <form id="MMA029_01_form_01" name="MMA029_01_form_01" method="POST">
		                                <input type="hidden" id="SESSION_COMPANY_CD" name="SESSION_COMPANY_CD" value="TOMS"/>
		                                <input type="hidden" id="SESSION_CERTIFY_TYPE" name="SESSION_CERTIFY_TYPE" value="${CERTIFY_TYPE}"/>
		                                <input type="hidden" id="SESSION_USER_ID" name="SESSION_USER_ID" value="fta"/>
		                                <input type="hidden" id="SESSION_DEFAULT_LANGUAGE" name="SESSION_DEFAULT_LANGUAGE" value="${LANG}"/>
		                            </form>
                                    <ul id="MMA029_01_ul_01" style="padding:0;color:#3f8dc7;"></ul>
		                        </div>
		                    </div>
	                    </div>
	                    <div data-options="region:'center',border:false" style="overflow:hidden;">
                            <iframe id="TOMS_HELP" src="/html/help/MM-0000_01.html" style="width:100%;height:100%;"></iframe>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
    </div>
    <script type="text/javascript">
        $(this).keydown(function (e) {
        	var code = e.which ? e.which : e.keyCode;

	    	if(code == 27) {
	    		window.close();
	        }
	    	
	    	if(code == 112) {
	    		return false;
	    	}
	    });
    </script>
    <script type="text/javascript" src="/js/views/MM/POP/MM-A029_01.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>
</body>
</html>