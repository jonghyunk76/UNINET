<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : MM-B002_13.jsp
  * @Description : 화물운송주선업자 조회
  * @Modification Information
  *
  * author YNI-Maker(이메일)
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMB002_13_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>      
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <div class="easyui-layout" data-options="fit:true">
        <!-- 검색조건 -->
        <div data-options="region:'north',border:false" class="sch_box2">
            <div class="sch_boxIn2">
	            <form id="MMB002_13_form_01" method="POST">
	                <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
	                <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
	                <input type="hidden" id="FIELD_ID" name="FIELD_ID" value="${FIELD_ID}"/>
	                <table>
	                    <caption>데이타 검색조건 설정</caption>
	                    <colgroup>
	                        <col width="150px" />
	                        <col width="120px" />
	                        <col width="80px" />
                            <col width="170px" />
	                        <col width=";"/>
	                    </colgroup>
	                    <tbody>
	                        <tr>
	                            <th scope="row"><spring:message code="CC_화물운송주선업자부호"/></th>
	                            <td>
	                                <input type="text" id="FRGHT_TRN_AGENC_BSMN_SL" name="FRGHT_TRN_AGENC_BSMN_SL" search="MMB002_13.control.selectFreightTransportAgencyList" style="width:100px"/>
	                            </td>
	                            <th scope="row"><spring:message code="CC_상호국문명"/></th>
	                            <td>
	                                <input type="text" id="MTLTY_KOR_NM" name="MTLTY_KOR_NM" search="MMB002_13.control.selectFreightTransportAgencyList" style="width:150px"/>
	                            </td>
	                            <td class="txt_R">
	                               <a href="javascript:MMB002_13.control.selectFreightTransportAgencyList();" class="easyui-linkbutton pop_sch" id="MMB002_13_searchBtn"><spring:message code="SRCH"/></a>
	                            </td>
	                        </tr>
	                    </tbody>
	                </table>
	            </form>
	        </div>
        </div>
        <!-- //검색조건 -->
        <div data-options="region:'center',border:false" style="padding-top:6px;">
            <div class="easyui-layout" data-options="fit:true">
                <div data-options="region:'north',border:false" class="h2_etc">
                    <p class="h2"><spring:message code="CC_화물운송주선업자"/> <spring:message code="LIST"/></p>
                    <p class="h2_btn">
                        <a href="javascript:MMB002_13.control.selectRowValue();" class="btnPopTick"><spring:message code="SELET"/></a>
                    </p>
                </div>
                <div data-options="region:'center',border:false" style="padding-top:3px;">
		            <table id="MMB002_13_grid_01"> </table>
                </div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-B002_13.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>