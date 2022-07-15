<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : MM-A001_04.jsp
  * @Description : FTA 관세절감액 상세
  * @Modification Information
  *
  * author YNI-Maker
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMA001_04_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>      
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'north',border:false" class="sch_box2">
            <div class="sch_boxIn2">
                <form id="MMA001_04_form_01" name="MMA001_04_form_01" method="post">
                    <input type="hidden" id="flag" name="flag" value="${flag}"/>
                    <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
                    <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
                    <input type="hidden" id="START_YYYYMM" name="START_YYYYMM" value="${START_YYYYMM}"/>
                    <table>
                        <caption>데이타 테이블</caption>
                        <colgroup>
                            <col width="70px" />
                            <col width="140px" />
                            <col width="110px" />
                            <col width="200px" />
                            <col width="90px" />
                            <col width="200px" />
                            <col width="" />
                        </colgroup>
                        <tbody>
                            <tr>
                                <th scope="row"><spring:message code="DIVIS"/></th>
                                <td>
                                    <input type="text" id="DIVISION_CD" name="DIVISION_CD" style="width:130px;"/>
                                </td>
                                <th scope="row" class="point"><spring:message code="TXT_CLOS_MON"/></th>
                                <td>
                                    <input type="hidden" id="YYYYMM" name="YYYYMM"/>
                                    <input type="text" id="START_YYYYMM_CAL" name="START_YYYYMM_CAL" value="${START_YYYYMM}" class="easyui-validatebox" validType="'month'" required="true" style="width:75px;"
                                           onblur="javascript:calendar.handle.setDateSync('MMA001_04_form_01', 'START_YYYYMM_CAL', 'START_YYYYMM');"/> ~
                                    <input type="text" id="YYYYMM_CAL" name="YYYYMM_CAL" class="easyui-validatebox" validType="'month'" required="true" style="width:75px;"
                                           onblur="javascript:calendar.handle.setDateSync('MMA001_04_form_01', 'YYYYMM_CAL', 'YYYYMM');"/>
                                </td>
                                <th scope="row"><spring:message code="AGRET"/></th>
                                <td>
                                    <input type="text" id="FTA_CD" name="FTA_CD" style="width:140px;"/>
                                </td>
                                <td align="right">
                                    <a href="javascript:MMA001_04.control.selectExportTariffInfo();" class="easyui-linkbutton pop_sch" id="MMA001_04_searchBtn1"><spring:message code="SRCH"/><%--조회--%></a>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
        <div data-options="region:'center',border:false,split:false">
            <div class="easyui-layout" data-options="fit:true">
                <div data-options="region:'north',border:false,split:true" style="height:250px;padding-top:0px;">
		            <div class="easyui-layout" data-options="fit:true">
		                <div data-options="region:'west',border:false,split:true" class="width50" style="padding-top:12px;">
		                    <div class="easyui-layout" data-options="fit:true">
		                        <div data-options="region:'north',border:false" class="h2_etc">
		                            <p class="h2"><spring:message code="CONPY, SVAMT, TRASI"/></p>
		                            <p class="h2_btn">
		                                <a href="javascript:MMA001_04.excel.excelDownload_1();" class="btnExlDown"><spring:message code="DATA, DOWN"/></a>
		                            </p>
		                        </div>
		                        <div data-options="region:'center',border:true" style="padding:3px;">
		                            <table id="MMA001_04_chart_01" style="overflow:hidden;width:100%;height:100%;background-color:#fff;"></table>
		                        </div>
		                        <div data-options="region:'center',border:false" style="display:none;">
		                            <form id="MMA001_04_form_02" name="MMA001_04_form_02" method="post">
		                                <input type="hidden" id="COMPANY_CD" name="COMPANY_CD"/>
		                                <input type="hidden" id="DIVISION_CD" name="DIVISION_CD"/>
		                                <input type="hidden" id="START_YYYYMM" name="START_YYYYMM"/>
		                                <input type="hidden" id="YYYYMM" name="YYYYMM"/>
		                                <input type="hidden" id="FTA_CD" name="FTA_CD"/>
		                                <input type="hidden" id="TARIFF_STATUS_TYPE" name="TARIFF_STATUS_TYPE" value="C"/>
		                            </form>
		                            <table id="MMA001_04_grid_01"></table>
		                        </div>
		                    </div>
		                </div>
		                <div data-options="region:'center',border:false" style="padding-top:12px;">
		                    <div class="easyui-layout" data-options="fit:true">
                                <div data-options="region:'north',border:false" class="h2_etc">
                                    <p class="h2"><spring:message code="BYDIV, SVAMT, TRASI"/></p>
                                    <p class="h2_btn">
                                        <a href="javascript:MMA001_04.excel.excelDownload_3();" class="btnExlDown"><spring:message code="DATA, DOWN"/></a>
                                    </p>
                                </div>
                                <div data-options="region:'center',border:true" style="padding:3px;">
                                    <table id="MMA001_04_chart_03" style="overflow:hidden;width:100%;height:100%;background-color:#fff;"></table>
                                </div>
                                <div data-options="region:'center',border:false" style="padding-left:6px;display:none;">
                                    <form id="MMA001_04_form_04" name="MMA001_04_form_04" method="post">
                                        <input type="hidden" id="COMPANY_CD" name="COMPANY_CD"/>
                                        <input type="hidden" id="DIVISION_CD" name="DIVISION_CD"/>
                                        <input type="hidden" id="START_YYYYMM" name="START_YYYYMM"/>
                                        <input type="hidden" id="YYYYMM" name="YYYYMM"/>
                                        <input type="hidden" id="FTA_CD" name="FTA_CD"/>
                                        <input type="hidden" id="TARIFF_STATUS_TYPE" name="TARIFF_STATUS_TYPE" value="D"/>
                                    </form>
                                    <table id="MMA001_04_grid_03"></table>
                                </div>
                            </div>
			            </div>
			        </div>
			    </div>
			    <div data-options="region:'center',border:false,split:false">
		            <div class="easyui-layout" data-options="fit:true">
		                <div data-options="region:'north',border:false,split:true" style="height:250px;">
		                    <div class="easyui-layout" data-options="fit:true">
			                    <div data-options="region:'west',border:false,split:true" class="width50">
			                        <div class="easyui-layout" data-options="fit:true">
		                                <div data-options="region:'north',border:false" class="h2_etc">
		                                    <p class="h2"><spring:message code="CNTES, SVAMT, PSTCN"/></p>
		                                    <p class="h2_btn">
		                                        <a href="javascript:MMA001_04.excel.excelDownload_2();" class="btnExlDown"><spring:message code="DATA, DOWN"/></a>
		                                    </p>
		                                </div>
		                                <div data-options="region:'center',border:true" style="padding:3px;">
		                                    <table id="MMA001_04_chart_02" style="overflow:hidden;width:100%;height:100%;background-color:#fff;"></table>
		                                </div>
		                                <div data-options="region:'center',border:false" style="display:none;">
		                                    <form id="MMA001_04_form_03" name="MMA001_04_form_03" method="post">
		                                        <input type="hidden" id="COMPANY_CD" name="COMPANY_CD"/>
		                                        <input type="hidden" id="DIVISION_CD" name="DIVISION_CD"/>
		                                        <input type="hidden" id="START_YYYYMM" name="START_YYYYMM"/>
		                                        <input type="hidden" id="YYYYMM" name="YYYYMM"/>
		                                        <input type="hidden" id="FTA_CD" name="FTA_CD"/>
		                                        <input type="hidden" id="TARIFF_STATUS_TYPE" name="TARIFF_STATUS_TYPE" value="N"/>
		                                    </form>
		                                    <table id="MMA001_04_grid_02"></table>
		                                </div>
		                            </div>
                                </div>
			                    <div data-options="region:'center',border:false">
				                    <div class="easyui-layout" data-options="fit:true">
			                            <div data-options="region:'north',border:false" class="h2_etc">
			                                <p class="h2"><spring:message code="TXT_OVERSEA_CORP, SVAMT, PSTCN"/></p>
			                                <p class="h2_btn">
			                                    <a href="javascript:MMA001_04.excel.excelDownload_5();" class="btnExlDown"><spring:message code="DATA, DOWN"/></a>
			                                </p>
			                            </div>
			                            <div data-options="region:'center',border:true" style="padding:3px;">
			                                <table id="MMA001_04_chart_05" style="overflow:hidden;width:100%;height:100%;background-color:#fff;"></table>
			                            </div>
			                            <div data-options="region:'center',border:false" style="padding-left:6px;display:none;">
			                                <form id="MMA001_04_form_06" name="MMA001_04_form_06" method="post">
			                                    <input type="hidden" id="COMPANY_CD" name="COMPANY_CD"/>
			                                    <input type="hidden" id="DIVISION_CD" name="DIVISION_CD"/>
			                                    <input type="hidden" id="START_YYYYMM" name="START_YYYYMM"/>
			                                    <input type="hidden" id="YYYYMM" name="YYYYMM"/>
			                                    <input type="hidden" id="FTA_CD" name="FTA_CD"/>
			                                    <input type="hidden" id="TARIFF_STATUS_TYPE" name="TARIFF_STATUS_TYPE" value="E"/>
			                                </form>
			                                <table id="MMA001_04_grid_05"></table>
			                            </div>
					                </div>
					            </div>
							</div>
				        </div>
			            <div data-options="region:'center',border:false">
		                    <div class="easyui-layout" data-options="fit:true">
		                        <div data-options="region:'north',border:false" class="h2_etc">
		                            <p class="h2"><spring:message code="BYPRD, SVAMT, LIST"/></p>
		                            <p class="h2_btn">
		                                <a href="javascript:MMA001_04.excel.excelDownload_4();" class="btnExlDown"><spring:message code="DATA, DOWN"/></a>
		                            </p>
		                        </div>
		                        <div data-options="region:'center',border:false">
		                            <form id="MMA001_04_form_05" name="MMA001_04_form_05" method="post">
		                                <input type="hidden" id="COMPANY_CD" name="COMPANY_CD"/>
		                                <input type="hidden" id="DIVISION_CD" name="DIVISION_CD"/>
		                                <input type="hidden" id="START_YYYYMM" name="START_YYYYMM"/>
		                                <input type="hidden" id="YYYYMM" name="YYYYMM"/>
		                                <input type="hidden" id="FTA_CD" name="FTA_CD"/>
		                                <input type="hidden" id="TARIFF_STATUS_TYPE" name="TARIFF_STATUS_TYPE" value="I"/>
		                            </form>
		                            <table id="MMA001_04_grid_04"></table>
		                        </div>
		                    </div>
		                </div>
		            </div>
		        </div>
		    </div>
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-A001_04.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>