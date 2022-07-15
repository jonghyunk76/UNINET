<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%
 /**
  * @Class Name : MM-A028_01.jsp
  * @Description : 파일뷰어 팝업
  * @Modification Information
  *
  * author carlos
  *
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMA028_01_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body class="h2_etc">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'north',border:false">
            <div class="easyui-layout" data-options="fit:true">
                 <div data-options="region:'north',border:false" class="h2_etc">
                     <p class="h2"><spring:message code="FILE, NAME"/> : ${FILE_NAME}</p>
                 </div>
                 <div data-options="region:'center',border:false">
                     <form id="MMA028_01_form_01" name="MMA028_01_form_1" method="post" action="/mm/pop/mmA028_01/selectViewFile">
                         <input type="hidden" id="FILE_PATH" name="FILE_PATH" value="${FILE_PATH}"/>
                         <input type="hidden" id="FILE_NAME" name="FILE_NAME" value="${FILE_NAME}"/>
                     </form>
                 </div>
             </div>
        </div>
        <div data-options="region:'center',border:false" style="padding:2px 2px 2px 0;">
            <div class="easyui-layout" data-options="fit:true" style="overflow:hidden">
                <iframe name="MMA028_01_frame_1" id="MMA028_01_frame_1" style="width:100%;height:100%;background-color:#fff;border:1px solid #c7cbce;">
                </iframe>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="/js/views/MM/POP/MM-A028_01.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>
</body>
</html>