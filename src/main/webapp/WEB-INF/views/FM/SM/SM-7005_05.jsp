<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : SM-7005_05.jsp
  * @Description : 고객사별 엑셀양식 설정 및 테이블 생성
  * @Modification Information
  *
  * author YNI-Maker(이메일)
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "SM7005_05_dailog_01"으로 하셔야 합니다.
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
            <div class="btn_R">
                <a href="javascript:SM7005_05.control.updateInterfaceItemMstInfo();" id="SM7005_05_btn_01" class="btnSave"><spring:message code="SAVE"/></a>
                <a href="javascript:SM7005_05.control.deleteInterfaceItemMstInfo();" id="SM7005_05_btn_02" class="btnDelete"><spring:message code="DEL"/></a>
                <a href="javascript:dialog.handle.close(dialog.getObject('SM7005_05_dailog_01'));" class="btnPopClose"><spring:message code="CLOSE"/></a>
            </div>
        </div>
        <div data-options="region:'center',border:false">
            <div class="easyui-layout" data-options="fit:true">
                <div data-options="region:'north',border:false" class="h2_etc">
		            <div class="easyui-layout" data-options="fit:true">
		                <div data-options="region:'north',border:false" class="h2_etc">
		                    <p class="h2"><spring:message code="EXCEL_INFO"/></p>
		                </div>
		                <div data-options="region:'center',border:false">
		                    <form id="SM7005_05_form_01" method="POST">
                                <input type="hidden" id="flag" name="flag" value="${flag}"/>
				                <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
				                <input type="hidden" id="SESSION_COMPANY_CD" name="SESSION_COMPANY_CD" value="${SESSION_COMPANY_CD}"/>
				                <input type="hidden" id="SESSION_USER_ID" name="SESSION_USER_ID" value="${SESSION_USER_ID}"/>
				                <input type="hidden" id="DEFAULT_LANGUAGE" name="DEFAULT_LANGUAGE" value="${DEFAULT_LANGUAGE}"/>
				                <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
				                <input type="hidden" id="CREATE_BY" name="CREATE_BY" value="${SESSION_USER_ID}"/>
				                <input type="hidden" id="CREATE_BY" name="UPDATE_BY" value="${SESSION_USER_ID}"/>
				                <input type="hidden" id="INTERFACE_ITEM_MST_ID" name="INTERFACE_ITEM_MST_ID" value="${INTERFACE_ITEM_MST_ID}"/>
				                <input type="hidden" id="ITEM_TYPE" name="ITEM_TYPE" value="${ITEM_TYPE}"/><%--T:TABLE, P:PROCEDURE, C:URL, E:Excel--%>
				                <input type="hidden" id="gridData" name="gridData"/>
                                <table class="gridT" style="margin-bottom:0px;">
                                    <colgroup>
                                        <col width="130px" />
                                        <col width=";" />
                                        <col width="130px" />
                                        <col width=";" />
                                    </colgroup>
                                    <tbody>
                                        <tr>
                                            <th scope="row" class="point2"><spring:message code="INF, CODE"/></th>
                                            <td>
                                                <input type="text" id="IF_CD" name="IF_CD" class="easyui-validatebox" required="true" placeholder="회사코드_XX_999" style="width:98%;"/>
                                            </td>
                                            <th scope="row" class="point2"><spring:message code="INF, NAME"/></th>
                                            <td>
                                                <input type="text" id="IF_NAME" name="IF_NAME" class="easyui-validatebox" required="true" style="width:98%;"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th scope="row" class="point2"><spring:message code="DATA_TYPE"/></th>
                                            <td>
                                                <input type="text" id="FILE_TYPE" name="FILE_TYPE" style="width:120px;"/>
                                            </td>
                                            <th scope="row" class="point2"><spring:message code="DTA_TB"/></th>
                                            <td>
                                                <input type="text" id="SOURCE_TABLE" name="SOURCE_TABLE" class="easyui-validatebox" required="true" placeholder="<spring:message code="INF, CODE"/>" style="width:160px;"/>
                                                <label><input type="checkbox" id="CREATE_TBL_YN" name="CREATE_TBL_YN" value="Y"/><spring:message code="TBL_CREATE_RENEW"/></label>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th scope="row" class="point2"><spring:message code="TXT_READ_START_NUM"/></th>
                                            <td>
                                                <input type="text" id="FILE_NAME" name="FILE_NAME" class="easyui-validatebox" required="true" style="width:120px;"/>
                                            </td>
                                            <th scope="row" class="point2"><spring:message code="TXT_REQUIRED_YN"/></th>
                                            <td>
                                                <input id="FILE_STATUS" name="FILE_STATUS" style="width:100px;"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th scope="row"><spring:message code="DPOST_PFMC_ITEMS"/></th>
                                            <td>
                                                <input id="TARGET_TABLE" name="TARGET_TABLE" style="width:300px;"/>
                                            </td>
                                            <th scope="row" class="point2"><spring:message code="TXT_USING_YN"/></th>
                                            <td>
                                                <input type="text" id="USING_YN" name="USING_YN" style="width:100px;"/>
                                            </td>
                                            
                                        </tr>
                                        <tr>
                                            <th scope="row"><spring:message code="FILE_IDEN_NAME"/></th>
                                            <td colspan="3">
                                                <label style="color:blue;">* <spring:message code="TXT_FILE_IDEN_NAME"/></label>
                                                <input type="text" id="FILE_PATH" name="FILE_PATH" style="width:99%;"/>
                                            </td>
                                        </tr>
                                    </tbody>
                               </table>
                            </form>
		                </div>
		            </div>
		        </div>
		        <div data-options="region:'center',border:false" style="padding-top:9px;">
		            <div class="easyui-layout" data-options="fit:true">
                        <div data-options="region:'north',border:false" class="h2_etc">
                            <p class="h2"><spring:message code="TBL_COLUMN_EXCEL_FORM"/></p>
                            <p class="h2_btn">
		                        <a href="javascript:SM7005_05.ui.insertRow();" class="btnMoreInfo"><spring:message code="ADDRW"/></a>
                                <a href="javascript:SM7005_05.ui.deleteRow();" class="btnMinusInfo"><spring:message code="DELRW"/></a>
		                    </p>
                        </div>
                        <div data-options="region:'center',border:false">
                            <table id="SM7005_05_grid_01"></table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/FM/SM/SM-7005_05.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>