<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : MM-A021_01.jsp
  * @Description : 협력사 확인서 수정요청
  * @Modification Information
  *
  * author carlos
  *
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMA021_01_dailog_01"으로 하셔야 합니다.
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
        <div data-options="region:'north',border:false" style="overflow:hidden;">
            <p class="btn_R">
                <a href="javascript:MMA021_01.control.saveReqReason();" id="MMA021_01_btn_01" class=btnSave style="display:none"><spring:message code="MOD,REQ"/></a>
                <a href="javascript:MMA021_01.control.cancelReqReason();" id="MMA021_01_btn_03" class="btnDelete" style="display:none"><spring:message code="REQ, CANCL"/></a>
                <a href="javascript:MMA021_01.dialog.openDialog();" id="MMA021_01_btn_02" class="btnPopIssue" style="display:none"><spring:message code="CNFDO, MOD"/></a>
                <a href="javascript:MMA021_01.dialog.closeDialog();" class="btnPopClose"><spring:message code="CLOSE"/></a>
            </p>
        </div>
        <div data-options="region:'center',border:false">
            <div class="easyui-layout" data-options="fit:true">
                <!-- //검색조건 -->
                <div data-options="region:'center',border:false" style="height:100%;">
                    <div class="easyui-layout" data-options="fit:true">
                        <div data-options="region:'center',border:false" style="padding-top:6px;">
                            <div class="easyui-layout" data-options="fit:true">
                                <div data-options="region:'north',border:false" class="h2_etc">
                                    <p class="h2"><spring:message code="TXT_MOD_REQ_HSTR"/></p> <%--수정요청 이력--%>
                                </div>
                                <div data-options="region:'center',border:false" style="padding-top:0px;">
                                   <table id="MMA021_01_grid_01"></table>
                                </div>
                             </div>
                        </div>
                        <div data-options="region:'north',border:false" class="h2_etc" style="height:85px;">
                            <div class="easyui-layout" data-options="fit:true">
                                <div data-options="region:'north',border:false" class="h2_etc">
                                    <p class="h2"><spring:message code="TXT_REQUEST_REASN"/></p> <%--요청사유--%>
                                </div>
                                <div data-options="region:'center',border:false">
                                   <form id="MMA021_01_form_01" name="MMA021_01_form_01" method="POST">
							            <input type="hidden" id="TARGET_PID"  name="TARGET_PID"  value="${TARGET_PID}"/>
							            <input type="hidden" id="COMPANY_CD"  name="COMPANY_CD"  value="${COMPANY_CD}"/>
							            <input type="hidden" id="DIVISION_CD" name="DIVISION_CD" value="${DIVISION_CD}"/>
							            <input type="hidden" id="VENDOR_CD"   name="VENDOR_CD"   value="${VENDOR_CD}"/>
							            <input type="hidden" id="CO_DOC_NO"   name="CO_DOC_NO"   value="${CO_DOC_NO}"/>
							            <input type="hidden" id="USER_ID"     name="USER_ID"     value="${USER_ID}"/>
							            <input type="hidden" id="REGISTED_BY" name="REGISTED_BY" value="${REGISTED_BY}"/>
							            <input type="hidden" id="FTA_GROUP_CD" name="FTA_GROUP_CD" value="${FTA_GROUP_CD}"/>
							            <input type="hidden" id="MODIFY_REQUEST_STATUS" name="MODIFY_REQUEST_STATUS"/>
							            <input type="hidden" id="GLOBAL_CD" name="GLOBAL_CD" value="${_MEMBER.SESSION_FTA_NATION}"/> <%--국가코드-고정값:MX(멕시코)--%>
							            <input type="hidden" id="CO_DOC_TYPE" name="CO_DOC_TYPE" value="${CO_DOC_TYPE}"/>
							            <input type="hidden" id="ISSUE_DATE" name="ISSUE_DATE" value="${ISSUE_DATE}"/>
							            <input type="hidden" id="APPLY_DATE" name="APPLY_DATE" value="${APPLY_DATE}"/>
							            <input type="hidden" id="END_DATE" name="END_DATE" value="${END_DATE}"/>
                                         <textarea rows="3" id="MODIFY_REQUEST_REASON" name="MODIFY_REQUEST_REASON" class="easyui-validatebox" required="true" style="height:60px;width:99%;" value="${MODIFY_REQUEST_REASON}" validType="['maxlength[4000]']"></textarea>
                                    </form>
                                </div>
                           </div>
                        </div>
                        <div data-options="region:'south',border:false" class="h2_etc" style="padding-top:6px;height:37px;overflow:hidden;">
                            <form id="MMA021_01_form_02" name="MMA021_01_form_02" method="POST">
	                            <table class="dataT">
			                        <caption>요청사유 view</caption>
			                        <colgroup>
				                        <col width="150px" />
				                        <col width=";" />
			                        </colgroup>
			                        <tbody>
			                            <tr>
			                                <th scope="row"><spring:message code="TXT_REQUEST_REASN"/><%--요청사유--%></th>
			                                <td>
	                                            <input type="text" id="MODIFY_REQUEST_REASON_VIEW" name="MODIFY_REQUEST_REASON_VIEW" readonly class="readOnly" style="width:98%"/>
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
	</div>

    <script type="text/javascript" src="/js/views/MM/POP/MM-A021_01.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>
</body>
</html>