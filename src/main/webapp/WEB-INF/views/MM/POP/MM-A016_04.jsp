<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : MM-A016_04.jsp
  * @Description : 엑셀업로드 완료
  * @Modification Information
  *
  * author carlos
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMA016_04_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>      
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'north',border:false">
                      <spring:message code="MSG_COMPLTE_NORMAL"/><!-- 필수항목과 검증결과가 모두 정상적인 경우에만 완료할 수 있습니다. -->
        </div>
        <div data-options="region:'center',border:false" style="padding-top:12px;">
            <div class="easyui-layout" data-options="fit:true">
                <div data-options="region:'north',border:false">
                    <div class="easyui-layout" data-options="fit:true">
                        <div data-options="region:'north',border:false" style="overflow:hidden;">
                           <p class="h2"><spring:message code="DTA, VFCTN,RSULT"/><%-- 데이터 검증결과 --%></p>
                        </div>
                        <div data-options="region:'center',border:false">
                            <form id="MMA016_04_form_01" name="MMA016_04_form_01" method="post">
                                <input type="hidden" id="COMPANY_CD" name="COMPANY_CD"/>
                                <input type="hidden" id="IF_CD" name="IF_CD"/>
                                <input type="hidden" id="IF_NAME" name="IF_NAME"/>
                                <input type="hidden" id="USER_ID" name="USER_ID"/>
                                <input type="hidden" id="STEP_INDEX" name="STEP_INDEX" value="2"/>
                                <input type="hidden" id="ERROR_CODE" name="ERROR_CODE"/>
	                            <table class="dataT" style="margin-bottom:0;">
	                                <caption><spring:message code="DTA, VFCTN,RSULT"/><%--데이터 검증결과--%></caption>
	                                <colgroup>
	                                    <col width="150px" />
	                                    <col width=";"/>
	                                    <col width="120px" />
	                                    <col width="120px" />
	                                    <col width="100px;;"/>
	                                    <col width="150px;"/>
                                        <col width="80px;;"/>
	                                </colgroup>
	                                <tbody>
	                                    <tr>
	                                        <th scope="row" rowspan="3"><spring:message code="DTA, ROW,NUMB"/><%--이름--%></th>
	                                        <td rowspan="3">
	                                            <input type="text" name="TOT_ROW_CNT" id="TOT_ROW_CNT" readonly class="input_readOnly" style="font-size:30px;text-align:center;font-weight:bold;width:98%;height:60px;"/>
	                                        </td>
	                                        <th scope="row" rowspan="3"><spring:message code="VFCTN,RSULT"/><%--검증결과--%></th>
	                                        <th scope="row"><spring:message code="SUCES"/><%--성공수--%></th>
	                                        <td>
	                                            <input type="text" name="SUCC_ROW_CNT" id="SUCC_ROW_CNT" readonly class="input_readOnly" style="text-align:right;font-weight:bold;color:blue;width:97%;"/>
	                                        </td>
	                                        <th scope="row" rowspan="3"><spring:message code="TXT_REQUIRED_MISS_NUM"/><%--필수항목 오류--%></th>
                                            <td rowspan="3">
                                                <input type="text" name="REQUIRED_NOINPUT_CNT" id="REQUIRED_NOINPUT_CNT" readonly class="input_readOnly" style="font-size:30px;text-align:center;font-weight:bold;color:red;width:98%;"/>
                                            </td>
	                                    </tr>
	                                    <tr>
	                                        <th scope="row"><spring:message code="FAIL"/><%--실패수--%></th>
	                                        <td>
	                                            <input type="text" name="FAIL_ROW_CNT" id="FAIL_ROW_CNT" readonly class="input_readOnly" style="text-align:right;font-weight:bold;color:red;width:97%;"/>
	                                        </td>
	                                    </tr>
	                                    <tr>
	                                        <th scope="row"><spring:message code="UNDTM"/><%--미확인수--%></th>
	                                        <td>
	                                            <input type="text" name="UND_ROW_CNT" id="UND_ROW_CNT" readonly class="input_readOnly" style="text-align:right;font-weight:bold;color:orange;width:97%;"/>
	                                        </td>
	                                    </tr>
	                                    <tr>
                                            <th scope="row"><spring:message code="DSCPT"/><%--설명--%></th>
                                            <td colspan="6">
                                                <input type="text" name="REQUIRED_NOINPUT_MSG" id="REQUIRED_NOINPUT_MSG" readonly class="input_readOnly" style="width:97%;"/>
                                            </td>
                                        </tr>
	                                </tbody>
	                            </table>
	                        </form>
                        </div>
                    </div>
                </div>
                <div data-options="region:'center',border:false" style="padding-top:6px;">
                    <div class="easyui-layout" data-options="fit:true">
                        <div data-options="region:'north',border:false" class="h2_etc" style="overflow:hidden;">
                            <p class="h2"><spring:message code="DTA"/><%-- 상태 --%></p>
                            <p class="h2_btn">
		                        <a href="javascript:MMA016_04.excel.excelDownload_1();" class="btnExlDown"><spring:message code="EXCEL, DOWN"/><%--엑셀다운로드--%></a>
		                    </p>
                        </div>
                        <div data-options="region:'center',border:false">
                            <div class="easyui-layout" data-options="fit:true">
                                <div data-options="region:'north',border:false">
                                    <form id="MMA016_04_form_02" name="MMA016_04_form_02" method="post">
	                                    <table class="gridT" style="margin-bottom:0;">
	                                        <colgroup>
	                                            <col width="150px" />
                                                <col width=";"/>
	                                        </colgroup>
	                                        <tbody>
	                                            <tr>
	                                                <th scope="row"><spring:message code="VFCTN,RSULT"/><%--검증결과--%></th>
	                                                <td>
	                                                    <input name="ERROR_CODE" id="ERROR_CODE" style="width:100px;"/>
	                                                </td>
	                                            </tr>
	                                        </tbody>
	                                    </table>
	                                </form>
                                </div>
                                <div data-options="region:'center',border:false" style="overflow:hidden;">
                                    <table id="MMA016_04_grid_01"></table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-A016_04.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body> 
</html>