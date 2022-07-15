<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : MM-A034_03.jsp
  * @Description : 데이터 그리드 생성 및 관리
  * @Modification Information
  *
  * author YNI-Maker(이메일)
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMA034_03_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>      
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'north',border:false" style="height:32px;">
            <div id="MMA034_03_div_01" class="btn_R">
                <a href="javascript:MMA034_03.ui.viewApplyDatagrid();" class="btnPopPreview" style="display:none;"><spring:message code="CNFIR"/></a>
                <a href="javascript:MMA034_03.control.insertDatagridMstInfo();" class="btnSave"><spring:message code="SAVE"/></a>
                <a href="javascript:MMA034_03.control.deleteDatagridMstInfo();" class="btnDelete"><spring:message code="DEL"/></a>
                <a href="javascript:dialog.handle.close(dialog.getObject('MMA034_03_dailog_01'));" class="btnPopClose"><spring:message code="CLOSE"/></a>
            </div>
        </div>
        <div data-options="region:'center',border:false"  style="padding-top:2px;">
            <div class="easyui-layout" data-options="fit:true">
                <div data-options="region:'north',border:false">
                    <form id="MMA034_03_form_01" name="MMA034_03_form_01" method="post">
		                <input type="hidden" id="flag" name="flag" value="${flag}"/>
		                <input type="hidden" id="PID" name="PID" value="${PID}"/>
		                <input type="hidden" id="USER_ID" name="USER_ID" value="${_MEMBER.SESSION_USER_ID}"/>
		                <input type="hidden" name="HEADER_NO" id="HEADER_NO"/>
		                <input type="hidden" name="HEADER_ID_1" id="HEADER_ID_1"/>
		                <input type="hidden" name="HEADER_ID_2" id="HEADER_ID_2"/>
		                <input type="hidden" id="gridData1" name="gridData1"/>
		                <input type="hidden" id="gridData2" name="gridData2"/>
		                <input type="hidden" name="TOP_SYS_ID" id="TOP_SYS_ID"/>
		                <table class="dataT" style="margin-bottom:0px;">
		                    <caption><spring:message code="DTA, TB"/><%--데이타 테이블--%></caption>
		                    <colgroup>
		                        <col width="160px" />
		                        <col width="" />
		                        <col width="160px" />
		                        <col width="" />
		                    </colgroup>
		                    <tbody>
		                        <tr>
		                            <th scope="row" class="point2">Grid <spring:message code="ID"/></th>
		                            <td>
		                                <input type="text" name="GRID_ID" id="GRID_ID" value="${GRID_ID}" class="easyui-validatebox input_readOnly" required="true" readonly style="width:98%;"/>
		                            </td>
		                            <th scope="row" class="point2">Grid <spring:message code="NAME"/></th>
		                            <td>
		                                <input type="text" name="GRID_LANG_CODE" id="GRID_LANG_CODE" value="${GRID_LANG_CODE}" class="easyui-validatebox" required="true" style="width:98%"/>
		                            </td>
		                        </tr>
		                    </tbody>
		                </table>
		            </form>
                </div>
		        <div data-options="region:'west',border:false,split:true" class="width50" style="padding-top:9pt;">
		            <div class="easyui-layout" data-options="fit:true">
		                <div data-options="region:'north',border:false" class="h2_etc">
		                    <p class="h2"><spring:message code="HEADER_COLNUM"/>(1)</p>
		                    <p class="h2_btn">
		                        <a href="javascript:MMA034_03.control.pasteColumn_1();" class="btnTextPaste"><spring:message code="CLIB_PASTE"/></a>
		                        <a href="javascript:MMA034_03.control.copyColumn_1();" class="btnTextCopy"><spring:message code="CALL_SOURCE"/></a>
		                        <a href="javascript:MMA034_03.control.selectGridColumnSetList('1');" class="btnRefresh"><spring:message code="REFSH"/></a>
		                        <a href="javascript:MMA034_03.excel.excelDownload_1();" class="btnExlDown"><spring:message code="EXCEL, DOWN"/><%--엑셀다운로드--%></a>
		                    </p>
		                </div>
		                <div data-options="region:'center',border:false">
		                    <div class="easyui-layout" data-options="fit:true">
                                <div data-options="region:'north',border:false" class="h2_etc">
                                    <form id="MMA034_03_form_02" name="MMA034_03_form_02" method="post">
				                        <table class="gridT" style="margin-bottom:0px;">
				                            <caption><spring:message code="DTA, TB"/><%--데이타 테이블--%></caption>
				                            <colgroup>
				                                <col width="160px" />
				                                <col width="" />
				                            </colgroup>
				                            <tbody>
				                                <tr>
				                                    <th scope="row" class="point2">Header <spring:message code="ID"/></th>
				                                    <td>
				                                        <input type="text" name="HEADER_ID_1" id="HEADER_ID_1" class="input_readOnly" readonly style="width:98%;"/>
				                                    </td>
				                                </tr>
				                                <tr>
                                                    <th scope="row" colspan="2">
                                                        <a href="javascript:MMA034_03.ui.insertRow('1');" class="btnMoreInfo"><spring:message code="ADDRW"/></a>
                                                        <a href="javascript:MMA034_03.ui.deleteRow('1');" class="btnMinusInfo"><spring:message code="DELRW"/></a>
                                                        <a href="javascript:MMA034_03.ui.moveRow('1', -1);" class="btnArrowUp"><spring:message code="UP"/></a>
                                                        <a href="javascript:MMA034_03.ui.moveRow('1', 1);" class="btnArrowDown"><spring:message code="DOW"/></a>
                                                    </th>
                                                </tr>
				                            </tbody>
				                        </table>
				                    </form>
                                </div>
                                <div data-options="region:'center',border:false">
		                            <table id="MMA034_03_grid_01"> </table>
		                        </div>
		                    </div>
		                </div>
		            </div>
		        </div>
		        <div data-options="region:'center',border:false" class="width50" style="padding-top:9pt;">
		            <div class="easyui-layout" data-options="fit:true">
		                <div data-options="region:'north',border:false" class="h2_etc">
		                    <p class="h2"><spring:message code="HEADER_COLNUM"/>(2)</p>
                            <p class="h2_btn" id="MMA034_03_p_01" style="display:none;">
                                <a href="javascript:MMA034_03.control.pasteColumn_2();" class="btnTextPaste"><spring:message code="CLIB_PASTE"/></a>
                                <a href="javascript:MMA034_03.control.copyColumn_2();" class="btnTextCopy"><spring:message code="CALL_SOURCE"/></a>
                                <a href="javascript:MMA034_03.control.selectGridColumnSetList('2');" class="btnRefresh"><spring:message code="REFSH"/></a>
                                <a href="javascript:MMA034_03.excel.excelDownload_2();" class="btnExlDown"><spring:message code="EXCEL, DOWN"/><%--엑셀다운로드--%></a>
                            </p>
		                </div>
		                <div data-options="region:'center',border:false">
		                   <div class="easyui-layout" data-options="fit:true">
                                <div data-options="region:'north',border:false" class="h2_etc">
                                    <form id="MMA034_03_form_03" name="MMA034_03_form_03" method="post">
                                        <table class="gridT" style="margin-bottom:0px;">
                                            <caption><spring:message code="DTA, TB"/><%--데이타 테이블--%></caption>
                                            <colgroup>
                                                <col width="160px" />
                                                <col width="" />
                                            </colgroup>
                                            <tbody>
                                                <tr>
                                                    <th scope="row" class="point2">Header <spring:message code="ID"/></th>
                                                    <td>
                                                        <input type="text" name="HEADER_ID_2" id="HEADER_ID_2" class="input_readOnly" readonly style="width:98%"/>
                                                    </td>
                                                </tr>
                                                <tr id="MMA034_03_tr_01" style="display:none;">
                                                    <th scope="row" colspan="2">
                                                        <a href="javascript:MMA034_03.ui.insertRow('2');" class="btnMoreInfo"><spring:message code="ADDRW"/></a>
                                                        <a href="javascript:MMA034_03.ui.deleteRow('2');" class="btnMinusInfo"><spring:message code="DELRW"/></a>
                                                        <a href="javascript:MMA034_03.ui.moveRow('2', -1);" class="btnArrowUp"><spring:message code="UP"/></a>
                                                        <a href="javascript:MMA034_03.ui.moveRow('2', 1);" class="btnArrowDown"><spring:message code="DOW"/></a>
                                                    </th>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </form>
                                </div>
                                <div data-options="region:'center',border:false">
                                    <table id="MMA034_03_grid_02"> </table>
                                </div>
                            </div>
		                </div>
		            </div>
		        </div>
		    </div>
		</div>
		<div style="display:none;">
		    <form id="MMA034_03_form_00" name="MMA034_03_form_00" method="post">
		        <textarea name="PASTE_TEXT1" id="PASTE_TEXT1"></textarea>
		        <textarea name="PASTE_TEXT2" id="PASTE_TEXT2"></textarea>
		    </form>
		</div>
    </div>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-A034_03.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>