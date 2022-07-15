<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : DE-0000_02.jsp
  * @Description : datagrid db 설정 데모버전
  * @Modification Information
  *
  * author YNI-Maker(이메일)
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "DE0000_02_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>      
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center',border:false" style="height:100%;padding-top:12px;">
            <div class="easyui-layout" data-options="fit:true">
                <div data-options="region:'north',border:false" class="h2_etc">
                    <p class="h2"><spring:message code="EXP, INVIC, LIST"/><%--수출 인보이스--%></p>
                    <p class="h2_btn">
                        <a href="javascript:DE0000_02.excel.excelDownload_1();" class="btnExlDown" id="DE0000_02_btn_btn_06" style="display:none;"><spring:message code="EXCEL, DOWN"/><%--엑셀다운로드--%></a>
                     </p>
                </div>
                <div data-options="region:'center',border:false">
                   <table id="DE0000_02_grid_01"> </table>
                </div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/MM/DEMO/DE-0000_02.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>