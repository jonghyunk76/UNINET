<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : MM-B006_01.jsp
  * @Description : 인보이스/패킹리스트 메인화면
  * @Modification Information
  *
  * author YNI-Maker(이메일)
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMB006_01_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'north',border:false" class="sec_tit" style="overflow:hidden;">
            <ul class="tab_area">
                <li id="MMB006_01_divMove01" style="display:none"><a href="javascript:MMB006_01.ui.divMove('1');"><b><spring:message code="CC_공통정보"/></b></a></li>
                <li id="MMB006_01_divMove02" style="display:none"><a href="javascript:MMB006_01.ui.divMove('2');"><b><span id="MMB006_01_span_01"><spring:message code="CC_PPL"/></span></b></a></li>
            </ul>
        </div>
        <div data-options="region:'center',border:false">
            <form id="MMB006_01_form_01" method="POST">
                <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
                <input type="hidden" id="USER_ID" name="USER_ID" value="${USER_ID}"/>
                <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
                <input type="hidden" id="TAB_NM" name="TAB_NM" value="${TAB_NM}"/>
                <input type="hidden" id="BCNC_CD" name="BCNC_CD" value="${BCNC_CD}"/>
                <input type="hidden" id="BCNC_NM" name="BCNC_NM" value="${BCNC_NM}"/>
                <input type="hidden" id="IMP_EXP_SE_CD" name="IMP_EXP_SE_CD" value="${IMP_EXP_SE_CD}"/><!-- 수입/수출 구분코드(수입:I, 수출:E) -->
                <input type="hidden" id="DATA_SE" name="DATA_SE" value="${DATA_SE}"/><!-- 데이터 구분(인보이스:I, 패킹리스트:P) -->
                <input type="hidden" id="DOC_STLE_SE" name="DOC_STLE_SE" value="${DOC_STLE_SE}"/>
                <input type="hidden" id="IMP_BCNC_SETUP_SN" name="IMP_BCNC_SETUP_SN" value="${IMP_BCNC_SETUP_SN}"/>
                <input type="hidden" id="INVE_NO" name="INVE_NO" value="${INVE_NO}"/>
                <input type="hidden" id="CNR_NO" name="CNR_NO" value="${CNR_NO}"/>
            </form>
            <div id="MMB006_01_tabs_01" class="easyui-tabs" data-options="fit:true,showHeader:false,plain:false,tabPosition:'bottom'" style="height:auto;"></div>
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-B006_01.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>