<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : ST-R002_02.jsp
  * @Description : 서비스 등록 및 수정
  * @Modification Information
  *
  * author YNI-Maker(이메일)
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "STR002_02_dailog_01"으로 하셔야 합니다.
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
            <form id="STR002_02_form_01" name="STR002_02_form_01" method="post">
	            <input type="hidden" id="flag" name="flag" value="${flag}"/>
	            <input type="hidden" id=COMPANY_CD name="COMPANY_CD" value="${COMPANY_CD}"/>
	            <input type="hidden" id="USER_ID" name="USER_ID" value="${_MEMBER.SESSION_USER_ID}"/>
	            <input type="hidden" id="gridData" name="gridData"/>
	            <div class="btn_R">
	                 <a href="javascript:STR002_02.control.insertServiceMst('insert');" id="STR002_02_btn_01" class="btnSave"><spring:message code="SAVE"/></a>
	                 <a href="javascript:STR002_02.control.deleteServiceMst('delete');" id="STR002_02_btn_02" class="btnDelete"><spring:message code="DEL"/></a>
	            </div>
	            <p class="h2"><spring:message code="CC_서비스 정보"/></p><span style="color:red;"> * 서비스ID는 영문, 숫자, 언더바(_)만 사용할 것</span>
                <p class="h2_btn">
                   <a href="javascript:STR002_02.excel.excelDownload_1();" class="btnExlDown" id="STR002_02_btn_btn_06" style="display:none;"><spring:message code="EXCEL, DOWN"/><%--엑셀다운로드--%></a>
                </p>
                <table class="dataT">
	                <caption>데이타 테이블</caption>
	                <colgroup>
	                    <col width="100px;" />
	                    <col width="170px;" />
	                    <col width="100px;" />
	                    <col width=";" />
	                    <col width="150px;" />
	                    <col width="110px;" />
	                    <col width="100px;" />
	                    <col width="90px;" />
	                </colgroup>
	                <tbody>
	                    <tr>
	                        <th scope="row" class="point2"><spring:message code="CC_서비스 ID"/></th>
	                        <td>
	                            <input type="text" name="SERVICE_ID" id="SERVICE_ID" value="${SERVICE_ID}" class="easyui-validatebox" required="true" style="width:150px;"/>
	                        </td>
	                        <th scope="row" class="point2"><spring:message code="CC_서비스명"/></th>
	                        <td>
	                            <input type="text" name="SERVICE_NAME" id="SERVICE_NAME" value="${SERVICE_NAME}" class="easyui-validatebox" required="true" style="width:98%;"/>
	                        </td>
	                        <th scope="row" class="point2"><spring:message code="CC_인터페이스 타입"/></th>
	                        <td>
	                            <input type="text" name="SERVICE_TYPE" id="SERVICE_TYPE" value="${SERVICE_TYPE}" style="width:100px;"/>
	                        </td>
	                        <th scope="row"><spring:message code="CC_사용여부"/></th>
	                        <td>
	                            <input type="text" name="USE_YN" id="USE_YN" value="${USE_YN}" style="width:80px;"/>
	                        </td>
	                    </tr>
	                    <tr>
	                        <th scope="row"><spring:message code="CC_설명"/></th>
	                        <td colspan = "7">
	                            <textarea name="SERVICE_DESC" id="SERVICE_DESC" class="easyui-validatebox" validType="['maxlength[2000]']" style="width:98%;height:70px">${SERVICE_DESC}</textarea>
	                        </td>
	                    </tr>
	                </tbody>
                </table>
            </form>
        </div>
        <div data-options="region:'center',border:false">
            <div class="easyui-layout" data-options="fit:true">
                <div data-options="region:'north',border:false" class="h2_etc">
                    <p class="h2"><spring:message code="CC_서비스 계획"/></p>
                    <p class="h2_btn">
                        <a href="javascript:STR002_02.ui.insertRow();" class="btnMoreInfo"><spring:message code="ADDRW"/></a>
                        <a href="javascript:STR002_02.ui.deleteRow();" class="btnMinusInfo"><spring:message code="DELRW"/></a>
                    </p>
                </div>
	            <div data-options="region:'center',border:false">
	                <table id="STR002_02_grid_01"></table>
	            </div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/RS/ST/ST-R002_02.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>