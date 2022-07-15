<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : ST-R001_02.jsp
  * @Description : 서버 등록 및 수정
  * @Modification Information
  *
  * author YNI-Maker(이메일)
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "STR001_02_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>      
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'north',border:false" class="h2_etc">
            <div class="btn_R">
		         <a href="javascript:STR001_02.control.insertServerListMst();" id="STR001_02_btn_01" class="btnSave"><spring:message code="SAVE"/></a>
		         <a href="javascript:STR001_02.control.deleteServerListMst();" id="STR001_02_btn_02" class="btnDelete"><spring:message code="DEL"/></a>
            </div>
        </div>
        <div data-options="region:'center',border:false" style="height:100%;">
            <p class="h2"><spring:message code="CC_서버 등록"/><%--수출 인보이스--%></p>
            <form id="STR001_02_form_01" name="STR001_02_form_01" method="post">
            <input type="hidden" id="flag" name="flag" value="${flag}"/>
            <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
            <input type="hidden" id="USER_ID" name="USER_ID" value="${_MEMBER.SESSION_USER_ID}"/>
            <input type="hidden" id="ATTRIBUTE02" name="ATTRIBUTE02" value="${ATTRIBUTE02}"/>
            <input type="hidden" id="ATTRIBUTE07" name="ATTRIBUTE07" value="${ATTRIBUTE07}"/>
            <input type="hidden" id="ATTRIBUTE08" name="ATTRIBUTE08" value="${ATTRIBUTE08}"/>
            <input type="hidden" id="ATTRIBUTE09" name="ATTRIBUTE09" value="${ATTRIBUTE09}"/>
            <input type="hidden" id="ATTRIBUTE10" name="ATTRIBUTE10" value="${ATTRIBUTE10}"/>
            <table class="dataT" style="margin-bottom:0px;">
                <caption>데이타 테이블</caption>
                <colgroup>
                    <col width="150px;" />
                    <col width=";" />
                    <col width="150px;" />
                    <col width=";" />
                </colgroup>
                <tbody>
                    <tr>
                        <th scope="row" class="point2"><spring:message code="CC_서버ID"/></th>
                        <td>
	                        <input type="text" name="SERVER_ID" id="SERVER_ID" value="${SERVER_ID}" class="easyui-validatebox" required="true" style="width:98%;"/>
	                    </td>
                        <th scope="row" class="point2"><spring:message code="CC_서버이름"/></th>
                        <td>
	                        <input type="text" name="SERVER_NAME" id="SERVER_NAME" value="${SERVER_NAME}" class="easyui-validatebox" required="true" style="width:98%;"/>
	                    </td>
                    </tr>
                    <tr>
                        <th scope="row" class="point2"><spring:message code="CC_서버유형"/></th>
                        <td>
	                        <input type="text" name="ATTRIBUTE01" id="ATTRIBUTE01" value="${ATTRIBUTE01}" style="width:98%;"/>
	                    </td>
	                    <th scope="row" class="point2"><spring:message code="CC_통신방법"/></th>
                        <td>
                            <input type="text" name="ATTRIBUTE06" id="ATTRIBUTE06" value="${ATTRIBUTE06}" style="width:120px;"/>
                        </td>
	                </tr>
	                <tr>
                        <th scope="row" class="point2"><spring:message code="CC_서버 IP/Port"/></th>
                        <td colspan="3">
                            <input type="text" name="SERVER_IP" id="SERVER_IP" value="${SERVER_IP}" class="easyui-validatebox" required="true" style="width:200px;"/> /
                            <input type="text" name="SERVER_PORT" id="SERVER_PORT" value="${SERVER_PORT}" class="easyui-validatebox" required="true" style="width:60px;"/>
                            <label><input type="checkbox" id="SECURE_CONNECT_YN" value="Y"> <spring:message code="CC_보안연결(https://)"/></label>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row"><spring:message code="CC_Proxy IP/Port"/></th>
                        <td colspan="3">
                            <input type="text" name="ATTRIBUTE03" id="ATTRIBUTE03" value="${ATTRIBUTE03}" style="width:200px;"/> /
                            <input type="text" name="ATTRIBUTE04" id="ATTRIBUTE04" value="${ATTRIBUTE04}" style="width:60px;"/>
                        </td>
                    </tr>
	                <tr>
	                    <th scope="row"><spring:message code="CC_Datasource 명칭"/></th>
                        <td>
                            <input type="text" name="ATTRIBUTE05" id="ATTRIBUTE05" value="${ATTRIBUTE05}" style="width:98%;"/>
                        </td>
                        <th scope="row" class="point2"><spring:message code="CC_사용여부"/></th>
                        <td>
	                        <input type="text" name="USE_YN" id="USE_YN" value="${USE_YN}" style="width:98%;"/>
	                    </td>
                    </tr>
                    <tr>
                        <th scope="row"><spring:message code="CC_서버설명"/></th>
                        <td colspan = "3">
                            <textarea name="SERVER_DESC" id="SERVER_DESC" class="easyui-validatebox" validType="['maxlength[100]']" style="width:98%;height:70px">${SERVER_DESC}</textarea>
                        </td>
                    </tr>
                </tbody>
            </table>
            </form>
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/RS/ST/ST-R001_02.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>