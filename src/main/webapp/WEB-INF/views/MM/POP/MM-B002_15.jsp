<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : MM-B002_15.jsp
  * @Description : 해외거래처 조회
  * @Modification Information
  *
  * author YNI-Maker(이메일)
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMB002_15_dailog_01"으로 하셔야 합니다.
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
	            <form id="MMB002_15_form_01" method="POST">
	                <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
	                <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
	                <input type="hidden" id="FIELD_ID" name="FIELD_ID" value="${FIELD_ID}"/>
	                <table>
	                    <caption>데이타 검색조건 설정</caption>
	                    <colgroup>
                            <col width="120px"/>
                            <col width="400px"/>
                            <col width=";"/>
	                    </colgroup>
                        <tbody>
                            <tr>
                                <th scope="row"><spring:message code="SRCH,CNDIT"/><%--조회조건--%></th>
                                <td>
                                    <input type="text" id="schKeyField" name="schKeyField" style="width:130px"/>
                                    <input type="hidden" id="multiData" name="multiData"/>
                                    <span id="MMB002_01_tool_01" class="grid" onclick="javascript:MMB002_01.tool.showTooltip();" style="width:19px;display:none;"></span>
                                    <input type="text" id="schKeyWord" name="schKeyWord" class="easyui-validatebox" search="MMB002_15.control.selectOverBcncList" style="width:150px;padding-left:20px;"/>
                                    <input type="text" id="schKeyLike" name="schKeyLike" style="width:80px"/>
                                </td>                              
                                <td class="txt_R">
                                   	<a href="javascript:MMB002_15.control.selectOverBcncList();" class="easyui-linkbutton pop_sch" id="MMB002_01_searchBtn"><spring:message code="SRCH"/><%--조회--%></a>
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
                    <p class="h2"><spring:message code="CC_해외공급자"/> <spring:message code="LIST"/></p>
                    <p class="h2_btn">
                        <a href="javascript:MMB002_15.control.selectRowValue('Y');" class="btnPopTick"><spring:message code="SELET"/></a>
                        <a href="javascript:MMB002_15.control.selectRowValue('N');" class="btnPopTick"><spring:message code="NOSEL"/></a>
                    </p>
                </div>
                <div data-options="region:'center',border:false" style="padding-top:3px;">
		            <div class="easyui-layout" data-options="fit:true">
                        <div data-options="region:'north',border:false">
                            <form id="MMB002_15_form_02" method="POST">
                                <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${_MEMBER.SESSION_COMPANY_CD}" />
                                <input type="hidden" id="USER_ID" name="USER_ID" value="${_MEMBER.SESSION_USER_ID}"/>
			                    <input type="hidden" id="TARGET_INSERT_AT" name="TARGET_INSERT_AT" value="Y"/>
			                    <input type="hidden" id="OAPI_IEM_CD" name="OAPI_IEM_CD" value="API011"/>
			                    <input type="hidden" id="P_PARA_KEY_03" name="P_PARA_KEY_01" value="cntySgn"/>
			                    <input type="hidden" id="P_PARA_KEY_03" name="P_PARA_KEY_02" value="conm"/>
			                    <input type="hidden" id="P_PARA_KEY_03" name="P_PARA_KEY_03" value="crkyCn"/>
			                    <input type="hidden" id="P_PARA_VAL_03" name="P_PARA_VAL_03" value="${PARA_VAL}"/>
			                    <table class="gridT" style="margin-bottom:0px;">
			                        <colgroup>
			                            <col width="120px"/>
			                            <col width="96px;"/>
			                            <col width="120px"/>
                                        <col width=";"/>
			                        </colgroup>
			                        <tbody>
			                            <tr>
			                                <th scope="row"><spring:message code="CC_국가코드(2자리)"/></th>
			                                <td>
			                                     <input type="text" id="P_PARA_VAL_01" name="P_PARA_VAL_01" class="easyui-validatebox" required="true" style="width:80px;text-transform: uppercase;"/>
			                                </td>
			                                <th scope="row"><spring:message code="CC_상호명"/></th>
                                            <td>
                                                 <input type="text" id="P_PARA_VAL_02" name="P_PARA_VAL_02" class="easyui-validatebox" required="true" style="width:200px;text-transform: uppercase;"/>
                                                 <span style="padding-left:10px;">
                                                     <a href="javascript:MMB002_15.control.executeCustomerToUinpass();" class="btnPopTransfer"><spring:message code="CC_관세청"/></a>
                                                 </span>
                                            </td>
			                            </tr>
			                        </tbody>
			                    </table>
                            </form>
                        </div>
                        <div data-options="region:'center',border:false">
                            <table id="MMB002_15_grid_01"> </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-B002_15.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>