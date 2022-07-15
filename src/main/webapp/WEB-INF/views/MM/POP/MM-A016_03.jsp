<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : MM-A016_03.jsp
  * @Description : 엑셀업로드 열정의
  * @Modification Information
  *
  * author carlos
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMA016_03_dailog_01"으로 하셔야 합니다.
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
                      <spring:message code="MSG_MATCH_COLUM"/><!-- 데이터를 매핑할 소스 데이터 열 목록에 대해 오른쪽의 대상 테이블 열을 선택하십시오. -->
        </div>
        <div data-options="region:'center',border:false" style="padding-top:12px;">
            <div class="easyui-layout" data-options="fit:true">
                <div data-options="region:'west',border:false,split:true" class="width40">
                    <div class="easyui-layout" data-options="fit:true">
                        <div data-options="region:'north',border:false" class="h2_etc" style="overflow:hidden;">
                           <p class="h2"><spring:message code="SOURC, DTA, ROW"/><%-- 소스 데이터 열--%></p>
                           <p class="h2_btn">
                                <a href="javascript:MMA016_03.control.executeChangeSource();" class="btnSynchroniz"><spring:message code="SOURC,MAPIG"/><%-- 소스 맵핑 --%></a>
                                <a href="javascript:MMA016_03.control.deleteSourceColumn();" class="btnMainDelete"><spring:message code="DEL"/><%-- 삭제 --%></a>
                            </p>
                        </div>
                        <div data-options="region:'center',border:false">
                            <table id="MMA016_03_grid_01"></table>
                        </div>
                        <div data-options="region:'south',border:false" style="height:130px;padding-top:6px;">
                            <div class="easyui-layout" data-options="fit:true">
                                <div data-options="region:'north',border:false" style="overflow:hidden;">
                                   <p class="h2"><spring:message code="STAUS"/><%-- 상태 --%></p>
                                </div>
                                <div data-options="region:'center',border:true" style="padding:2px;background-color:#fff;">
                                    <div id="MMA016_03_div_01"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div data-options="region:'center',border:false" class="width60">
                    <div class="easyui-layout" data-options="fit:true">
                        <div data-options="region:'north',border:false" class="h2_etc" style="overflow:hidden;">
                            <p class="h2"><spring:message code="TARGT, TB, ROW"/><%-- 대상 테이블 열--%></p>
                            <p class="h2_btn">
                                <a href="javascript:MMA016_03.control.updateTargetColumn();" class="btnMainSave"><spring:message code="APYDT"/><%-- 적용 --%></a>
                            </p>
                        </div>
                        <div data-options="region:'center',border:false">
                            <div class="easyui-layout" data-options="fit:true">
                                <div data-options="region:'north',border:false">
                                    <form id="MMA016_03_form_01" name="MMA016_03_form_01" method="post">
                                        <input type="hidden" id="COMPANY_CD" name="COMPANY_CD"/>
	                                    <input type="hidden" id="IF_CD" name="IF_CD"/>
	                                    <input type="hidden" id="USER_ID" name="USER_ID"/>
	                                    <input type="hidden" id="CELL_ID" name="CELL_ID"/>
	                                    <input type="hidden" id="YYYYMM" name="YYYYMM"/>
	                                    <table class="dataT" style="margin-bottom:0;">
	                                        <caption><spring:message code="Target table column"/><%--대상 테이블 열--%></caption>
	                                        <colgroup>
	                                            <col width="120px" />
	                                            <col width=";"/>
	                                            <col width="120px" />
                                                <col width=";"/>
	                                        </colgroup>
	                                        <tbody>
	                                            <tr>
	                                                <th scope="row"><spring:message code="TXT_COLUMN,NAME"/><%--이름--%></th>
	                                                <td colspan="3">
	                                                    <input type="text" name="COLUMN_ID" id="COLUMN_ID" style="width:250px;"/>
	                                                </td>
	                                            </tr>
	                                            <tr>
	                                                <th scope="row"><spring:message code="DTA, TYPE"/><%--데이터 유형--%></th>
	                                                <td colspan="3">
	                                                    <input type="text" name="COLUMN_TYPE_NAME" id="COLUMN_TYPE_NAME" readonly class="input_readOnly" style="width:98%;"/>
	                                                </td>
	                                            </tr>
                                                <tr>
	                                                <th scope="row"><spring:message code="DTA, LENG"/><%--데이터 길이--%></th>
	                                                <td>
	                                                    <input type="text" name="COLUMN_LENG" id="COLUMN_LENG" readonly class="input_readOnly" style="width:98%;"/>
	                                                </td>
                                                    <th scope="row"><spring:message code="POINT"/><%--소수점--%></th>
                                                    <td>
                                                        <input type="text" name="COLUMN_DECIMALS" id="COLUMN_DECIMALS" readonly class="input_readOnly" style="width:98%;"/>
                                                    </td>
                                                </tr>
	                                            
	                                            <tr>
	                                                <th scope="row"><spring:message code="TXT_REQUIRED_YN"/><%--필수여부--%></th>
	                                                <td colspan="3">
	                                                    <input type="text" name="COLUMN_REQUIRED_NAME" id="COLUMN_REQUIRED_NAME" readonly class="input_readOnly" style="width:98%;"/>
	                                                </td>
	                                            </tr>
	                                            <tr>
	                                                <th scope="row"><spring:message code="BASIS, DTA"/><%--기본 데이터--%></th>
	                                                <td colspan="3">
	                                                    <input type="text" name="COLUMN_DEFAULTVAL" id="COLUMN_DEFAULTVAL" readonly class="input_readOnly" style="width:98%;"/>
	                                                </td>
	                                            </tr>
	                                            <tr>
	                                                <th scope="row"><spring:message code="DTA, FOMAT"/><%--데이타 포맷--%></th>
	                                                <td colspan="3">
	                                                    <input type="text" name="COLUMN_FORMAT" id="COLUMN_FORMAT" readonly class="input_readOnly" style="width:98%;"/>
	                                                </td>
	                                            </tr>
	                                        </tbody>
	                                    </table>
	                                </form>
                                </div>
                                <div data-options="region:'center',border:false" style="padding-top:6px;">
                                    <div class="easyui-layout" data-options="fit:true">
                                        <div data-options="region:'north',border:false" class="h2_etc" style="overflow:hidden;">
                                            <p class="h2"><spring:message code="DTA"/><%-- 상태 --%></p>
                                            <form id="MMA016_03_form_02" name="MMA016_03_form_02" method="post">
	                                            <p style="text-align:right;padding-right:5px;">
						                            <input type="checkbox" id="errorCheck" name="errorCheck" onchange="javascript:MMA016_03.control.selectExcelValueList();" size="12" /><spring:message code="MSG_ERROR_VALIDATION"/>
						                        </p>
					                        </form>
                                        </div>
                                        <div data-options="region:'center',border:false" style="overflow:hidden;">
                                            <table id="MMA016_03_grid_02"></table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-A016_03.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body> 
</html>