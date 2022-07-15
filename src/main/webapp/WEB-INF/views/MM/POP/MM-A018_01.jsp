<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : MM-A018_01.jsp
  * @Description : 한국 외부원산지품목 정보
  * @Modification Information
  *
  * author carlos
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMA018_01_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body class="h2_etc">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'north',border:false" style="height:28px;overflow:hidden;">
            <div id="MMA018_01_div_01" class="btn_R">
                <a href="javascript:MMA018_01.control.saveDetailInfo();" id="MMA018_01_btn_01" class="btnSave"><spring:message code="APYDT"/><%--적용--%></a>
            </div>
         </div>
         <div data-options="region:'center',border:false">
            <form id="MMA018_01_form_01" name="MMA018_01_form_01" method="POST">
                <input type="hidden" id="flag"         name="flag"         value="${flag}"/>
                <input type="hidden" id="TARGET_PID"   name="TARGET_PID"   value="${TARGET_PID}"/>
                <input type="hidden" id="COMPANY_CD"   name="COMPANY_CD"   value="${COMPANY_CD}"/>
                <input type="hidden" id="DIVISION_CD"  name="DIVISION_CD"  value="${DIVISION_CD}"/>
                <input type="hidden" id="VENDOR_CD"    name="VENDOR_CD"    value="${VENDOR_CD}"/>
                <input type="hidden" id="USER_ID"      name="USER_ID"      value="${USER_ID}"/>
                <input type="hidden" id="ISSUE_DATE"   name="ISSUE_DATE"   value="${ISSUE_DATE}"/>
                <input type="hidden" id="APPLY_DATE"   name="APPLY_DATE"   value="${APPLY_DATE}"/>
                <input type="hidden" id="END_DATE"     name="END_DATE"     value="${END_DATE}"/>
                <input type="hidden" id="CUSTOMER_ITEM_CD"     name="CUSTOMER_ITEM_CD"     value="${CUSTOMER_ITEM_CD}"/>
                <input type="hidden" id="CUSTOMER_ITEM_NAME"     name="CUSTOMER_ITEM_NAME"     value="${CUSTOMER_ITEM_NAME}"/>
                <input type="hidden" id="FTA_ASSOCIATION_CD" name="FTA_ASSOCIATION_CD" value="${FTA_ASSOCIATION_CD}"/>
                <input type="hidden" id="ORIGIN_YN"     name="ORIGIN_YN"     value="${ORIGIN_YN}"/>
                <input type="hidden" name="FTA_GROUP_CD" id="FTA_GROUP_CD" value="${FTA_GROUP_CD}"/>
                <table class="dataT" style="margin-bottom:0px;">
                    <colgroup>
                        <col width="150px" />
                        <col width="" />
                        <col width="150px" />
                        <col width="" />
                    </colgroup>
                    <tbody>
                        <tr>
                            <th scope="row" class="point2">
                                <spring:message code="LTIT, CODE"/><%--품목코드--%>
                            </th>
                            <td colspan="3">
                                <input type="text" name="ITEM_CD" id="ITEM_CD" value="${ITEM_CD}" class="easyui-validatebox readOnly" required="true" size="15" readonly/> /
                                <input type="text" name="ITEM_NAME" id="ITEM_NAME" value="${ITEM_NAME}" class="easyui-validatebox" size="40" />
                                <span id="MMA018_01_form_01_ITEM"><a href="javascript:MMA018_01.dialog.openDialog1();"><img src="/images/sample/btn_sch.gif" alt="<spring:message code='SRCH'/><%--조회--%>" /></a></span>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row" class="point2">
                                <spring:message code="TXT_FTA"/><%--FTA 협정--%>
                            </th>
                            <td>
                                <input type="text" name="FTA_CD" id="FTA_CD" value="${FTA_CD}" class="easyui-validatebox" size="20"/>
                            </td>
                            <th scope="row" class="point2">
                                <spring:message code="TXT_HS_CODE"/><%--HS코드--%>
                            </th>
                            <td>
                                <input type="text" name="HS_CODE" id="HS_CODE" value="${HS_CODE}" class="easyui-validatebox" required="true" validType="['minlength[6]','maxlength[10]','number']" size="20" 
                                    onkeyup="javascript:MMA018_01.control.selectRuleContents();" />
                            </td>
                        </tr>
                        <tr>
                            <th scope="row" class="point2">
                                <spring:message code="TXT_RULE_CODE"/><%--결정기준--%>
                            </th>
                            <td>
                                <input name="RULE_CONTENTS" id="RULE_CONTENTS" value="${RULE_CONTENTS}" size="20" />
                            </td>
                            <th scope="row" class="point2">
                                <spring:message code="TXT_MEET_YN"/><%--충족여부--%>
                            </th>
                            <td>
                                <input type="text" name="FTA_CO_YN" id="FTA_CO_YN" value="${FTA_CO_YN}" class="easyui-validatebox" size="20" />
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">
                                <spring:message code="TXT_DOMESTIC_RATE"/><%--역내비율--%>
                            </th>
                            <td>
                                <input type="text" name="RVC_RATE" id="RVC_RATE" value="${RVC_RATE}" class="easyui-validatebox" validType="['floatformatexts[17,5]','number']" size="20"/>
                            </td>
                            <th scope="row" class="point2">
                                <spring:message code="ORG_COTRY"/><%--원산지 국가--%>
                            </th>
                            <td>
                                <input type="text" name="ORIGIN_NATION_CD" id="ORIGIN_NATION_CD" value="${ORIGIN_NATION_CD}" class="easyui-validatebox" size="20"/>
                            </td>
                        </tr>
                        <tr id="MMA018_01_tr_01">
                            <c:if test="${FTA_GROUP_CD eq 'ML'}">
                                <th scope="row">
                                    <spring:message code="TXT_NALADISA_2002"/><%--NALADISA 2002--%>
                                </th>
                                <td>
                                    <input type="text" name="NALADISA_CODE" id="NALADISA_CODE" value="${NALADISA_CODE}" class="easyui-validatebox" validType="['maxlength[10]']" size="20" onkeyup="javascript:MMA018_01.control.selectRuleContents('nlds', this.value);"/>
                                </td>
                            </c:if>
                            <c:if test="${FTA_GROUP_CD eq 'MN'}">
                                <th scope="row">
                                    <spring:message code="TXT_TRACE_VALUE"/><%--TRACE_VALUE--%>
                                </th>
                                <td>
                                    <input type="text" name="TRACE_VALUE" id="TRACE_VALUE" value="${TRACE_VALUE}" class="easyui-numberbox" required="true" validType="['floatformatexts[17,5]']" size="15" />
                                    <input name="CURRENCY" id="CURRENCY" value="${CURRENCY}" size="13" />
                                </td>
                            </c:if>
                            <th scope="row" class="point2">
                                <spring:message code="TXT_SALES_UNIT_PRICE"/><%--판가--%>
                            </th>
                            <td>
                                <input type="text" name="SALES_UNIT_PRICE" id="SALES_UNIT_PRICE" value="${SALES_UNIT_PRICE}" class="easyui-numberbox" required="true" validType="['floatformatexts[17,5]']" size="15"/>
                                <input name="SALES_CURRENCY" id="SALES_CURRENCY" value="${SALES_CURRENCY}" size="13" />
                            </td>
                        </tr>
                        
                    </tbody>
                </table>
            </form>
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-A018_01.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>