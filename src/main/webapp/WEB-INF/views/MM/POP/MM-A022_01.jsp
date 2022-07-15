<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : MM-A022_01.jsp
  * @Description : 자료업로드
  * @Modification Information
  *
  * author carlos
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMA022_01_dailog_01"으로 하셔야 합니다.
  *
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
                <form id="MMA022_01_form_01" enctype="multipart/form-data" method="POST">
                    <input type="hidden" id="TARGET_PID"   name="TARGET_PID"   value="${TARGET_PID}"/>
                    <input type="hidden" id="COMPANY_CD"   name="COMPANY_CD"   value="${COMPANY_CD}"/>
                    <input type="hidden" id="DIVISION_CD"  name="DIVISION_CD"  value="${DIVISION_CD}"/>
                    <input type="hidden" id="VENDOR_CD"    name="VENDOR_CD"    value="${VENDOR_CD}"/>
                    <input type="hidden" id="USER_ID"      name="USER_ID"      value="${USER_ID}"/>
                    <input type="hidden" id="CO_DOC_NO"    name="CO_DOC_NO"    value="${CO_DOC_NO}"/>
                    <input type="hidden" id="gridData"     name="gridData"     value='${gridData}'/>
                    <input type="hidden" id="GLOBAL_CD"    name="GLOBAL_CD"    value="${GLOBAL_CD}"/>
                    <input type="hidden" id="FTA_GROUP_CD" name="FTA_GROUP_CD" value="${FTA_GROUP_CD}"/>
                    <input type="hidden" id="TABLE_NAME"   name="TABLE_NAME"   value="${TABLE_NAME}"/>
                    <input type="hidden" id="ISSUE_DATE"   name="ISSUE_DATE"   value="${ISSUE_DATE}"/>
                    <input type="hidden" id="APPLY_DATE"   name="APPLY_DATE"   value="${APPLY_DATE}"/>
                    <input type="hidden" id="END_DATE"     name="END_DATE"     value="${END_DATE}"/>
                    <input type="hidden" id="beforeTP"  name="beforeTP"  value="${beforeTP}"/>
                    <input type="hidden" id="NON_ORGIN_RESN_YN"     name="NON_ORGIN_RESN_YN"     value="${NON_ORGIN_RESN_YN}"/> <%--역외사유 사용여부 --%>
                    <input type="hidden" id="NON_ORGIN_RESN_REQ_YN" name="NON_ORGIN_RESN_REQ_YN" value="${NON_ORGIN_RESN_REQ_YN}"/> <%--역외사유 설명 --%>
                    <table style="width:100%;margin-top:-4px;">
                        <colgroup>
                            <col width="120px;" />
                            <col width=";" />   
                            <col width="130px;"/>
                        </colgroup>
                        <tbody>
                            <tr>
                                <th scope="row" class="point"><spring:message code="TXT_ATTACH_FILE"/><%--첨부파일--%></th>
                                <td><input type="file" id="uploadExcelFile" name="uploadExcelFile" style="width:600px;"/></td>
                                <td style="padding-left:10px;">
                                    <p class="h2_btn">
                                        <a href="javascript:MMA022_01.control.saveExcelSample();" class="btnFileImport" id="MMA022_01_btn_01"><spring:message code="BTN_EXCEL_UPLOAD"/><%--조회--%></a>
                                    </p>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
        <!-- //검색조건 -->
        <div data-options="region:'center',border:false" style="padding-top:12px;">
            <div class="easyui-layout" data-options="fit:true">
                <div data-options="region:'north',border:false" class="h2_etc">
                    <p class="h2"><spring:message code="TXT_UPLOAD_LIST"/><%--업로드내역--%></p>
                    <p class="h2_btn" style="padding:0 10px 0 0;">
                        <a href="javascript:MMA022_01.control.saveItemList();" id="MMA022_01_btn_02" class="btnMainSave"><spring:message code="APYDT"/></a>
                    </p>
                </div>
                <div data-options="region:'center',border:false">
                   <table id="MMA022_01_grid_01"> </table>
                </div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-A022_01.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>