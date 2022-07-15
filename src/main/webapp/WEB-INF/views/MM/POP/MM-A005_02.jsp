<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : MM-A005_02.jsp
  * @Description : BOM 투입자재 조회 및 선택
  * @Modification Information
  *
  * author 이재욱
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMA005_02_dailog_01"으로 하셔야 합니다.
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
        <div data-options="region:'north',border:false" class="sch_box">
            <div class="sch_boxIn">
                <form id="MMA005_02_form_01" method="POST">
                    <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
                    <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
                    <input type="hidden" name="DIVISION_CD" id="DIVISION_CD" value="${DIVISION_CD}"/>
                    <input type="hidden" name="VENDOR_ITEM_STATE" id="VENDOR_ITEM_STATE" value="${VENDOR_ITEM_STATE}"/>
                    <input type="hidden" name="VENDOR_CD" id="VENDOR_CD" value="${VENDOR_CD}"/>
                    <table>
                        <caption>데이타 검색조건 설정</caption>
                        <colgroup>
                            <col width="140x" /> 
                            <col width="190px"/>
                            <col width="100x" /> 
                            <col width="170px"/>
                            <col width=";"/>
                        </colgroup>
                        <tbody>
                            <tr>
                                <th scope="row" class="point"><spring:message code="TXT_CLOS_MON"/></th>
                                <td>
                                    <input type="hidden" id="SCH_FROM_DATE" name="SCH_FROM_DATE"/>
                                    <input type="hidden" id="SCH_TO_DATE" name="SCH_TO_DATE"/>
                                    <input type="text" id="SCH_FROM_DATE_CAL" name="SCH_FROM_DATE_CAL" class="easyui-validatebox" validType="'month'" required="true" style="width:75px"
                                           onblur="javascript:calendar.handle.setDateSync('MMA005_02_form_01', 'SCH_FROM_DATE_CAL', 'SCH_FROM_DATE');"/> ~ 
                                    <input type="text" id="SCH_TO_DATE_CAL" name="SCH_TO_DATE_CAL" class="easyui-validatebox" validType="'month'" required="true" style="width:75px"
                                           onblur="javascript:calendar.handle.setDateSync('MMA005_02_form_01', 'SCH_TO_DATE_CAL', 'SCH_TO_DATE');"/>
                                </td>
                                <th scope="row" class="point"><spring:message code="PRDT_CODE"/></th>
                                <td>
                                    <input type="text" id="PRODUCT_ITEM_CD" name="PRODUCT_ITEM_CD" value="${PRODUCT_ITEM_CD}" search="MMA005_02.control.selectMainList" class="easyui-validatebox" required="true" style="width:120px;paddig:0;"/>
                                </td>
                                <td class="txt_R" rowspan="2">
                                    <a href="javascript:MMA005_02.control.selectMainList();" class="easyui-linkbutton pop_sch"><spring:message code="SRCH"/></a>
                                </td>
                            </tr>
                            <tr>
                                <th scope="row"><spring:message code="SHCDT"/><%--조건검색--%></th>
                                <td colspan="3">
                                    <input type="text" id="schKeyField" name="schKeyField" style="width:110px"/> 
                                    <input type="text" id="schKeyWord" name="schKeyWord" search="MMA005_02.control.selectMainList" style="width:130px;paddig:0;"/>
                                    <input type="text" id="schKeyLike" name="schKeyLike" style="width:80px"/>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
        <!-- //검색조건 -->
        <div data-options="region:'center',border:false" style="height:100%;padding-top:6px;">
            <div class="easyui-layout" data-options="fit:true">
                <div data-options="region:'north',border:false" class="h2_etc">
                    <p class="h2_btn">
                        <a href="javascript:MMA005_02.control.selectRowValue();" class="btnPopTick"><spring:message code="SELET"/></a>
                    </p>
                </div>
                <div data-options="region:'center',border:false">
                   <table id="MMA005_02_grid_01"></table>
                </div>
            </div>
        </div>
        
    </div>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-A005_02.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body> 
</html>