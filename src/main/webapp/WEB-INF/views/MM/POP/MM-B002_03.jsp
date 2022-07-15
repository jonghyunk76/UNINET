<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : MM-B002_03.jsp
  * @Description : 거래처 조회
  * @Modification Information
  *
  * author YNI-Maker(이메일)
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMB002_03_dailog_01"으로 하셔야 합니다.
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
	            <form name="MMB002_03_form_01" id="MMB002_03_form_01" method="POST">
	                <input type="hidden" id="USER_ID" name="USER_ID" value="${_MEMBER.SESSION_USER_ID}"/>
	                <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
	                <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
	                <input type="hidden" id="FIELD_ID" name="FIELD_ID" value="${FIELD_ID}"/>
	                <input type="hidden" id="ORDER_BY_TYPE" name="ORDER_BY_TYPE"/>
	                <table>
	                    <caption>데이타 검색조건 설정</caption>
	                    <colgroup>
	                        <col width="80px" />
	                        <col width="120px" />
	                        <col width="80px" />
	                        <col width="170px" />
	                        <col width="130px" />
                            <col width="150px" />
	                        <col width=";"/>
	                    </colgroup>
	                    <tbody>
	                        <tr>
	                            <th scope="row"><spring:message code="CODE"/></th>
	                            <td>
	                                <input type="text" id="BCNC_CD" name="BCNC_CD" search="MMB002_03.control.selectClientList" style="width:100px"/>
	                            </td>
	                            <th scope="row"><spring:message code="EXPNM"/></th>
	                            <td>
	                                <input type="text" id="BCNC_MTLTY" name="BCNC_MTLTY" search="MMB002_03.control.selectClientList" style="width:150px"/>
	                            </td>
	                            <th scope="row"><spring:message code="BZRGN"/></th>
                                <td>
                                    <input type="text" id="BIZRNO" name="BIZRNO" search="MMB002_03.control.selectClientList" style="width:120px"/>
                                </td>
	                            <td class="txt_R">
	                               <a href="javascript:MMB002_03.control.selectClientList();" class="easyui-linkbutton pop_sch" id="MMB002_03_searchBtn"><spring:message code="SRCH"/></a>
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
                    <p class="h2"><spring:message code="CLINT"/> <spring:message code="LIST"/></p>
                    <p class="h2_btn">
                        <a href="javascript:MMB002_03.control.selectRowValue();" class="btnPopTick"><spring:message code="SELET"/></a>
                    </p>
                </div>
                <div data-options="region:'center',border:false" style="padding-top:3px;">
                    <div class="easyui-layout" data-options="fit:true">
                        <div data-options="region:'north',border:false" class="h2_etc">
                            <form id="MMB002_03_form_02" method="post">
                                <table class="gridT" style="margin-bottom:0px;">
                                    <colgroup>
                                        <col width="" />
                                    </colgroup>
                                    <tbody>
                                        <tr>
                                            <th scope="row">
                                                <label><input type="radio" id="ORDER_BY_TYPE" name="ORDER_BY_TYPE" onchange="javascript:MMB002_03.control.selectClientList()" value="1" checked="checked"/><spring:message code="CC_거래처코드로 정렬"/></label>
                                                <label><input type="radio" id="ORDER_BY_TYPE" name="ORDER_BY_TYPE" onchange="javascript:MMB002_03.control.selectClientList()" value="2"/><spring:message code="CC_거래처명으로 정렬"/></label>
                                                <label><input type="radio" id="ORDER_BY_TYPE" name="ORDER_BY_TYPE" onchange="javascript:MMB002_03.control.selectClientList()" value="3"/><spring:message code="CC_최근등록순으로 정렬"/></label>
                                            </th>
                                        </tr>
                                    </tbody>
                                </table>
                            </form>
                        </div>
                        <div data-options="region:'center',border:false">
		                    <table id="MMB002_03_grid_01"> </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-B002_03.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>