<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : DE-0000_01.jsp
  * @Description : JSP 샘플파일
  * @Modification Information
  *
  * author YNI-Maker(이메일)
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "DE0000_01_dailog_01"으로 하셔야 합니다.
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
                <form id="DE0000_01_form_01" name="DE0000_01_form_01" method="POST">
                    <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${_MEMBER.SESSION_COMPANY_CD}"/>
                    <input type="hidden" id="gridData" name="gridData"/>
                    <input type="hidden" id="filename" name="filename"/>
                    <input type="hidden" id="sheetname" name="sheetname"/>
                    <input type="hidden" id="schCheckbox" name="schCheckbox" value="Y"/>
                    <input type="hidden" id="CUSTOMER_CO_TARGET" value="${_MEMBER.SESSION_CUSTOMER_CO_TARGET}"/>
                    <input type="hidden" id="EXPORT_FLAG" name ="EXPORT_FLAG" value="D"/>
                    <input type="hidden" id="OYB_INCLUD_YN" name="OYB_INCLUD_YN"/>
                    <table>
                        <caption>데이타 검색조건 설정</caption>
                        <colgroup>
	                        <col width="120px" />
	                        <col width="400px" />
	                        <col width=";"/>
                        </colgroup>
                        <tbody>
                            <tr>
                                <th scope="row" class="point"><spring:message code="TXT_CLOS_MON"/></th>
                                <td>
                                    <input type="hidden" id="SALES_FROM_DATE" name="SALES_FROM_DATE"/>
                                    <input type="hidden" id="SALES_TO_DATE" name="SALES_TO_DATE"/>
                                    <input type="text" id="SALES_FROM_DATE_CAL" name="SALES_FROM_DATE_CAL" class="easyui-validatebox" validType="'month'" required="true" style="width:75px"
                                           onblur="javascript:calendar.handle.setDateSync('DE0000_01_form_01', 'SALES_FROM_DATE_CAL', 'SALES_FROM_DATE');"/> ~ 
                                    <input type="text" id="SALES_TO_DATE_CAL" name="SALES_TO_DATE_CAL" class="easyui-validatebox" validType="'month'" required="true" style="width:75px"
                                           onblur="javascript:calendar.handle.setDateSync('DE0000_01_form_01', 'SALES_TO_DATE_CAL', 'SALES_TO_DATE');"/>
                                    <input type="checkbox" id="determinDetailChck" name="determinDetailChck" value="Y" checked size="12" /><spring:message code="TXT_COO_DETERMIN,RSULT, TXT_HIDE"/>
                                </td>
                                <td align="right">
                                   <a href="javascript:DE0000_01.control.selectMainList();" class="easyui-linkbutton pop_sch"><spring:message code="SRCH"/><%--조회--%></a>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
        <div data-options="region:'center',border:false" style="padding-top:12px;">
            <div class="easyui-layout" data-options="fit:true">
			    <div data-options="region:'north',border:false" class="sec_tit">
		            <div class="h2_etc">
		               <p class="h2">Datagrid Demo1</p>
		               <p class="h2_btn">
		                   <a href="javascript:DE0000_01.excel.excelDownload_1();" class="btnExlDown"><spring:message code="EXCEL, DOWN"/><%--엑셀다운로드--%></a>
		               </p>
		           </div>
		        </div>
		        <div data-options="region:'center',border:false" style="padding-top:0px;">
			        <table id="DE0000_01_dg_01"></table>
			    </div>
			</div>
		</div>
		<div data-options="region:'south',border:false,split:true" style="height:300px;">
            <div class="easyui-layout" data-options="fit:true">
                <div data-options="region:'north',border:false" class="sec_tit">
                    <div class="h2_etc">
                       <p class="h2">Datagrid Demo2</p>
                       <p class="h2_btn">
                           <a href="javascript:DE0000_01.excel.excelDownload_2();" class="btnExlDown"><spring:message code="EXCEL, DOWN"/><%--엑셀다운로드--%></a>
                       </p>
                   </div>
                </div>
                <div data-options="region:'center',border:false" style="padding-top:0px;">
                    <table id="DE0000_01_dg_02"></table>
                </div>
            </div>
        </div>
	</div>
	
	<script type="text/javascript" src="/js/views/MM/DEMO/DE-0000_01.js"></script>
	<script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>