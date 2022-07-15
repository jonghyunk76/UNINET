<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%
 /**
  * @Class Name : MM-A029_02.jsp
  * @Description : 도움말
  * @Modification Information
  *
  * author carlos
  *
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMA029_02_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html lang="${_MEMBER.SESSION_DEFAULT_LANGUAGE}">
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
    <script type="text/javascript" src="/js/frame/message/message_${_MEMBER.SESSION_DEFAULT_LANGUAGE}.js"></script>
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
    <script type="text/javascript" src="/js/frame/common/yni.ctrl.js"></script>
</head>
<body>
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center',border:false" style="overflow:hidden;">
            <iframe id="TOMS_DIV_HELP" src="/html/help/${PID}.html" style="width:100%;height:100%;"></iframe>
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
    <script type="text/javascript" src="/js/views/MM/POP/MM-A029_02.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>
</body>
</html>