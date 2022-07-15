<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : MM-A004_02.jsp
  * @Description : 보고서 발급 툴팁창
  * @Modification Information
  *
  * author 이재욱
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMA004_02_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'west',border:true" style="width:270px;" id="MMA004_02_out_list">
            <div class="easyui-layout" data-options="fit:true">
                <div data-options="region:'north',border:false" style="padding:1px;">
                    <form id="MMA004_02_form_01" name="MMA004_02_form_01" method="post">
                        <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
                        <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
                        <input type="hidden" id="DIVISION_CD" name="DIVISION_CD" value="${DIVISION_CD}"/>
                        <input type="hidden" id="FTA_CD" name="FTA_CD" value="${FTA_CD}"/>
                        <input type="hidden" id="FTA_GROUP_CD" name="FTA_GROUP_CD" value="${FTA_GROUP_CD}"/>
                        <input type="hidden" id="EXPORT_FLAG" name="EXPORT_FLAG" value="${EXPORT_FLAG}"/>
                        <input type="hidden" id="REPORT_PRINT_TYPE" name="REPORT_PRINT_TYPE" value="P"/>
                        <input type="hidden" id="REPORT_TYPE" name="REPORT_TYPE" value="pdf"/>
                        <input type="hidden" id="DOC_FILE_NAME" name="DOC_FILE_NAME" value="${DOC_FILE_NAME}"/>
                        <input type="hidden" id="file" name="file" value="${FILE_NAME}"/>
                        <input type="hidden" id="P_DIVISION_CD" name="P_DIVISION_CD" value="${P_DIVISION_CD}"/>
                        <input type="hidden" id="P_START_DATE" name="P_START_DATE" value="${ASSIGN_FROM_DATE}"/>
                        <input type="hidden" id="P_END_DATE" name="P_END_DATE" value="${ASSIGN_TO_DATE}"/>
                        <input type="hidden" id="P_VENDOR_CD" name="P_VENDOR_CD" value="${VENDOR_CD}"/>
                        <input type="hidden" id="BOM_TYPE" name="BOM_TYPE" value="${BOM_TYPE}"/>
                        <table class="dataT">
                            <caption>데이타 테이블</caption>
                            <colgroup>
                                <col width="110px" />
                                <col width="" />
                            </colgroup>
                            <tbody>
                                <tr>
                                    <th scope="row" class="point2"><spring:message code="TXT_SEARCH_COO_CERTIFY_NO"/></th>
                                    <td>
                                        <input type="text" id="CO_DOC_NO" name=CO_DOC_NO value="${CO_DOC_NO}" style="width:98%;" readonly class="input_readOnly"/>
                                    </td>
                                </tr>
                                <tr>
                                    <th scope="row"><spring:message code="CERIS,TYPE"/></th>
                                    <td>
                                        <input type="text" id="CO_ISSUE_TYPE_NAME" name="CO_ISSUE_TYPE_NAME" value="${CO_ISSUE_TYPE_NAME}" style="width:98%;" readonly class="input_readOnly"/>
                                    </td>
                                </tr>
                                <tr>
                                    <th scope="row"><spring:message code="TXT_ISSUE_DATE"/></th>
                                    <td>
                                        <input type="text" id="ISSUE_DATE" name="ISSUE_DATE" value="${ISSUE_DATE}" style="width:90px;" readonly class="input_readOnly"/>
                                        <span id="MMA004_02_span_01" style="display:none;"><a href="javascript:MMA004_02.control.updateCoIssueDate();" class="btnBasicUpdate"><spring:message code="MOD"/></a></span>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </form>
                </div>
                <div data-options="region:'center',border:false">
                    <div class="easyui-layout" data-options="fit:true">
                        <div data-options="region:'north',border:false" style="padding:0 3px 0 3px;">
                            <div class="h2_etc">
                                <p class="h2"><spring:message code="ORG, DOC, LIST"/></p>
                                <p class="h2_btn">
                                    <a href="javascript:MMA004_02.control.executeIssueCoDoc();" class="btnFile"><spring:message code="OUTP"/></a>
                                </p>
                            </div>
                        </div>
                        <div data-options="region:'center',border:false">
                            <table id="MMA004_02_grid_01"></table>
                        </div>
                    </div>
                </div>
                <div data-options="region:'south',border:false,split:true" style="height:360px;">
                    <div class="easyui-layout" data-options="fit:true">
                        <div data-options="region:'north',border:false" style="padding:0 3px 0 3px;">
                            <div class="h2_etc">
                                <p class="h2"><spring:message code="INVES,DATA, LIST"/></p>
                                <p class="h2_btn">
                                    <a href="javascript:MMA004_02.control.executeIssueEvidenceDoc();" class="btnFile"><spring:message code="OUTP"/></a>
                                </p>
                            </div>
                        </div>
                        <div data-options="region:'center',border:false">
                            <table id="MMA004_02_grid_02"></table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div data-options="region:'center',border:false" style="padding:0 0 0 3px;">
            <div class="easyui-panel" id="MMA004_02_panel_01" data-options="border:false,fit:true"></div>
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-A004_02.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body> 
</html>