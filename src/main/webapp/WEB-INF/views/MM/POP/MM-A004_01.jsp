<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : MM-A004_01.jsp
  * @Description : 수출매출리스트 인보이스 수정
  * @Modification Information
  *
  * author 이재욱
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMA004_01_dailog_d_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>      
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body class="h2_etc">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'north',border:false">
            <div class="easyui-layout" data-options="fit:true">
                <div data-options="region:'north',border:false" style="height:25px;overflow:hidden;">
			        <div id="MMA004_01_div_01" class="btn_R">
				        <a href="javascript:MMA004_01.control.updateInvoiceInfo();" id="MMA004_01_btn_01" class="btnSave"><spring:message code="SAVE"/></a>
			        </div>
			    </div>
				<div data-options="region:'center',border:false">
			        <div class="easyui-layout" data-options="fit:true">
		                <div data-options="region:'north',border:false">
		                    <div class="h2_etc">
		                        <p class="h2"><spring:message code="INVIC, INFMT"/></p>
		                    </div>
		                </div>
		                <div data-options="region:'center',border:false">
					        <form id="MMA004_01_form_01" name="MMA004_01_form_01" method="post">
					            <input type="hidden" id="flag" name="flag" value="${flag}"/>
					            <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
					            <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
					            <input type="hidden" id="DIVISION_CD" name="DIVISION_CD" value="${DIVISION_CD}"/>
					            <input type="hidden" id="INVOICE_DATE" name="INVOICE_DATE" value="${INVOICE_DATE}"/>
					            <input type="hidden" id="CURRENCY" name="CURRENCY" value="${CURRENCY}"/>
					            <input type="hidden" id="FTA_CD" name="FTA_CD" value="${FTA_CD}"/>
							    <table class="dataT" style="margin-bottom:0px;">
							        <caption>데이타 테이블</caption>
							        <colgroup>
							            <col width="180px;" />
							            <col width=";" />
							            <col width="180px;" />
							            <col width=";" />
							        </colgroup>
							        <tbody>
						            <tr>
						                <th scope="row" class="point2"><spring:message code="TXT_INVOICE_NO"/></th>
						                <td>
					                        <input type="text" name="INVOICE_NO" value="${INVOICE_NO}" id="INVOICE_NO" class="easyui-validatebox input_readOnly" readonly style="width:250px;"/>
						                </td>
						                <th scope="row"><spring:message code="TXT_EXPORT_ACCEPT_DATE"/></th>
		                                <td>
		                                    <input type="text" name="EXPORT_DECLARE_DATE" value="${EXPORT_DECLARE_DATE}" id="EXPORT_DECLARE_DATE" style="width:95px;"/>
		                                </td>
						            </tr>
						            <tr>
                                        <th scope="row"><spring:message code="TXT_EXPORT_DECLARE_NO"/></th>
                                        <td>
                                            <input type="text" name="EXPORT_DECLARE_NO" value="${EXPORT_DECLARE_NO}" id="EXPORT_DECLARE_NO" style="width:98%;"/>
                                        </td>
                                        <th scope="row"><spring:message code="EDCMN"/></th>
                                        <td>
                                            <input type="text" name="EXPORT_DECLARE_SEQ" value="${EXPORT_DECLARE_SEQ}" id="EXPORT_DECLARE_SEQ"  class="easyui-validatebox" style="width:98%;"/>
                                        </td>
                                    </tr>
						            <tr>
                                        <th scope="row"><spring:message code="TXT_VEHICLE_TYPE"/></th>
                                        <td>
                                            <input type="text" name="VEHICLE_TYPE" value="${VEHICLE_TYPE}" id="VEHICLE_TYPE" style="width:250px;"/>
                                        </td>
                                        <th scope="row"><spring:message code="TXT_VEHICLE_NAME"/></th>
                                        <td>
                                            <input type="text" name="VEHICLE_NAME" value="${VEHICLE_NAME}" id="VEHICLE_NAME" style="width:98%;"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th scope="row" class="point2"><spring:message code="TXT_SHIPPING_DATE"/></th>
                                        <td>
                                            <input type="text" name="SHIPPING_DATE" value="${SHIPPING_DATE}" id="SHIPPING_DATE" style="width:95px;"/>
                                        </td>
						                <th scope="row"><spring:message code="TXT_SHIP_PORT"/></th>
						                <td>
						                    <input id="LOADING_PORT" name="LOADING_PORT" class="easyui-validatebox" validType="['maxlength[5]']" style="width:50px"/>
                                            <input id="LOADING_PORT_NAME" name="LOADING_PORT_NAME" class="easyui-validatebox" validType="['maxlength[100]']" style="width:170px"/>
                                            <a href="javascript:MMA004_01.dialog.openDialog_2('P1');"><img src="/images/sample/btn_sch.gif" alt="검색" /></a>
						                </td>
						            </tr>
		                            <tr>
						                <th scope="row" class="point2"><spring:message code="TXT_ARRIVAL_NATION_NAME"/></th>
		                                <td>
		                                    <input type="text" name="FINAL_DESTINATION" id="FINAL_DESTINATION" class="easyui-validatebox" required="true" validType="['maxlength[3]']" style="width:50px"/>
		                                    <input type="text" name="FINAL_DESTINATION_NAME" id="FINAL_DESTINATION_NAME" class="easyui-validatebox" validType="['maxlength[30]']" style="width:170px"/>
		                                    <a href="javascript:MMA004_01.dialog.openDialog_1();"><img src="/images/sample/btn_sch.gif" alt="<spring:message code='SRCH '/>"/></a>
		                                </td>
		                                <th scope="row"><spring:message code='ARVPT' /></td>                         
                                        <td>
                                            <input id="FINAL_DEST_PORT" name="FINAL_DEST_PORT" class="easyui-validatebox" validType="['maxlength[5]']" style="width:50px;"/>
                                            <input id="FINAL_DEST_PORT_NAME" name="FINAL_DEST_PORT_NAME" class="easyui-validatebox" validType="['maxlength[100]']" style="width:170px;"/>
                                            <a href="javascript:MMA004_01.dialog.openDialog_2('P3');"><img src="/images/sample/btn_sch.gif" alt="검색" /></a>
                                        </td>
						            </tr>
						            <tr>
                                        <th scope="row"><spring:message code="TXT_TOTAL_WEIGHT, /, UNIT"/></th>
                                        <td>
                                            <input type="text" name="TOTAL_WEIGHT" id="TOTAL_WEIGHT" class="easyui-validatebox easyui-numberbox" validType="['maxlength[23]']" style="width:130px"/> /
                                            <input type="text" name="WEIGHT_UNIT" id="WEIGHT_UNIT"  class="easyui-validatebox" validType="['maxlength[3]']" style="width:50px"/>
                                        </td>
                                        <th scope="row"><spring:message code="TOTAL,QTY, /, UNIT"/></th>
                                        <td>
                                            <input type="text" name="TOTAL_QTY" id="TOTAL_QTY" class="easyui-validatebox easyui-numberbox" validType="['maxlength[23]']" style="width:130px"/> /
                                            <input type="text" name="QTY_UNIT" id="QTY_UNIT"  class="easyui-validatebox" validType="['maxlength[3]']" style="width:50px"/>
                                        </td>
                                    </tr>
							        </tbody>
							    </table>
							</form>
						</div>
					</div>
			   </div>
		   </div>
       </div>
	   <div data-options="region:'center',border:false" style="padding-top:9px;">
	       <div class="easyui-layout" data-options="fit:true">
               <div data-options="region:'north',border:false">
                   <div class="h2_etc">
                       <p class="h2"><spring:message code='PACKING_LIST' />(Packing List)</p>
                       <p class="h2_btn">
                           <a href="javascript:MMA004_01.excel.excelDownload_1();" class="btnExlDown"><spring:message code="BTN_EXCEL"/></a>
                       </p>
                   </div>
               </div>
               <div data-options="region:'center',border:false">
                   <table id="MMA004_01_grid_01"></table>
               </div>
           </div>
	   </div>
	</div>
	
    <script type="text/javascript" src="/js/views/MM/POP/MM-A004_01.js"></script>
	<script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body> 
</html>