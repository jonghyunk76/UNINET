<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : MM-A031_04.jsp
  * @Description : 문의 및 요청 등록 화면
  * @Modification Information
  *
  * author YNI-Maker(이메일)
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMA031_04_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>      
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
        <div class="easyui-layout" data-options="region:'north',border:false" style="padding:5px 0px 5px 0px;color:#3e3e3e;background-color:#fff;">
           <table>
               <tr>
                   <td style="width:140px;height:110px;text-align:center;"><img src="/images/icon/inquiry.png"/></td>
                   <td>
                       <h2 style="font-size:23px;font-family:'맑은 고딕', 'arial', 'Dotum', Gulim, '돋움', '굴림', 'malgun gothic', 'arial', 'sans-serif';"><spring:message code="TXT_INQUIRY_MSG1"/></h2>
                       <p style="font-size:15px;padding-top:10px;font-family:'맑은 고딕', 'arial', 'Dotum', Gulim, '돋움', '굴림', 'malgun gothic', 'arial', 'sans-serif';"><spring:message code="TXT_INQUIRY_MSG2"/><br>
                           <spring:message code="TXT_INQUIRY_MSG3"/>
                       </p>
                   </td>
               </tr>
           </table>
        </div>
        <div data-options="region:'center',border:false" style="background-color:#fff;">
            <form id="MMA031_04_form_01" name="MMA031_04_form_01" enctype="multipart/form-data" method="post">
                <input type="hidden" name="flag" id="flag" value="${flag}"/>
                <input type="hidden" name="INQUIRY_NO" id="INQUIRY_NO" value="${INQUIRY_NO}"/>
                <input type="hidden" name="INQUIRY_TYPE" id="INQUIRY_TYPE" value="${INQUIRY_TYPE}"/>
                <input type="hidden" name="INQUIRY_ID" id="INQUIRY_ID" value="${INQUIRY_ID}"/>
                <input type="hidden" name="APPLICATION_SERVICE_TYPE" id="APPLICATION_SERVICE_TYPE" value="${APPLICATION_SERVICE_TYPE}"/>
                <input type="hidden" name="CERTIFY_TYPE" id="CERTIFY_TYPE" value="${CERTIFY_TYPE}"/>
                <input type="hidden" name="FTA_NATION" id="FTA_NATION" value="${FTA_NATION}"/>
                <input type="hidden" name="LICENSE_KEY" id="LICENSE_KEY" value="${LICENSE_KEY}"/>
                <input type="hidden" name="MEMBER_ID" id="MEMBER_ID" value="${MEMBER_ID}"/>
                <input type="hidden" name="STATUS" id="STATUS" value="${STATUS}"/>
                <div style="padding:0 5px 0 5px;">
	                <table class="dataT">
	                    <colgroup>
	                        <col width="140px" />
	                        <col width=";" />
	                        <col width="140px;" />
	                        <col width=";" />
	                    </colgroup>
	                    <tbody>
	                        <tr>
	                            <th class="point2">
	                                <spring:message code="INQER,NAME"/> 
	                                <a id="MMA031_04_tool_01" href="javascript:void(0)" style="display:none;">
	                                   <img src="/images/sample/btn_sch.gif" alt="<spring:message code='SELET'/>"/>
	                                </a>
	                            </th>
	                            <td>
	                                <input id="INQUIRY_NAME" name="INQUIRY_NAME" class="easyui-validatebox" required="true" value="${INQUIRY_NAME}" style="width:98%;height:20px;"/>
	                            </td>
	                            <th><spring:message code="TXT_DUTY_POS"/></th>
	                            <td>
	                                <input id="INQUIRY_POSITION" name ="INQUIRY_POSITION" value="${INQUIRY_POSITION}" style="width:98%;height:20px;"/>
	                            </td>
	                        </tr>
	                        <tr>
                                <th class="point2"><spring:message code="TXT_EMAIL"/></th>
                                <td>
                                    <input id="INQUIRY_EMAIL" name ="INQUIRY_EMAIL" class="easyui-validatebox" required="true" value="${INQUIRY_EMAIL}" style="width:98%;height:20px;" />
                                </td>
                                <th class="point2"><spring:message code="CNTACT"/></th>
                                <td>
                                    <input id="INQUIRY_TEL" name ="INQUIRY_TEL" class="easyui-validatebox" required="true" value="${INQUIRY_TEL}" style="width:98%;height:20px;"/>
                                </td>
                            </tr>
                            <tr>
                                <th><spring:message code="CLINT"/>(<spring:message code="NAME"/>)</th>
                                <td colspan="3">
                                    <input type="text" name="CUSTOMER_NAME" id="CUSTOMER_NAME" value="${CUSTOMER_NAME}" style="width:99%;height:20px;">
                                </td>
                            </tr>
	                        <tr>
	                            <th class="point2"><spring:message code="CNSLT_METHD"/></th>
	                            <td colspan="3">
	                                <label><input type="radio" id="INQUIRY_RESPONSE" name ="INQUIRY_RESPONSE" value="1" <c:if test="${INQUIRY_RESPONSE == '1' || INQUIRY_RESPONSE == null}">checked</c:if>/><spring:message code="TXT_EMAIL, CNSLT"/></label>
	                                <label><input type="radio" id="INQUIRY_RESPONSE" name ="INQUIRY_RESPONSE" value="2" <c:if test="${INQUIRY_RESPONSE == '2'}">checked</c:if>/><spring:message code="TELPH,CNSLT"/></label>
	                                <p style="padding:5px 0 0 3px;font-size:8.5pt;color:blue;"><spring:message code="TXT_INQUIRY_MSG4"/></p>
	                            </td>
	                        </tr>
	                        <tr>
                                <th class="point2"><spring:message code="INQUI,SEPAT"/></th>
                                <td colspan="3">
                                    <input name="INQUIRY_PARENT_TYPE" id="INQUIRY_PARENT_TYPE" value="${INQUIRY_PARENT_TYPE}" style="width:400px;height:20px;">
                                    <input name="INQUIRY_SUB_TYPE" id="INQUIRY_SUB_TYPE" value="${INQUIRY_SUB_TYPE}" style="width:200px;height:20px;display:none;">
                                </td>
                            </tr>
	                        <tr>
	                            <th class="point2"><spring:message code="TITLE"/></th>
	                            <td colspan="3">
	                                <input id="INQUIRY_TITLE" name="INQUIRY_TITLE" class="easyui-validatebox" required="true" value="${INQUIRY_TITLE}" validType="['maxlength[200]']" style="width:99%;height:20px;"/>
	                            </td>
	                        </tr>
	                        <tr>
	                            <th class="point2"><spring:message code="INQUI,CONTS"/></th>
	                            <td colspan="3">
	                                <textarea name="INQUIRY_CONTENTS" id="INQUIRY_CONTENTS" class="easyui-validatebox" required="true" validType="['maxlength[1800]']" style="height:192px;width:99%;">${INQUIRY_CONTENTS}</textarea>
	                            </td>
	                        </tr>
	                        <tr>
                                <th rowspan="2"><spring:message code="TXT_ATTACH_FILE"/></th>
                                <td colspan="3">
                                    <div id="MMA031_04_files_div" class="file_box">
	                                    <span id="MMA031_04_files_1" class="file_list" onclick="MMA031_04.tooltip.showTooltip();" style="width:23px;"></span>
	                                    <input id="file_aliase" name="file_aliase" class="file_text easyui-filebox" style="width:480px;" readonly="readonly"/>
	                                    <span class="file_wrap">
	                                        <input type="file" id="FILEUP" name="FILEUP" class="file_add" onchange="javascript:MMA031_04.tooltip.setFiles(this);" multiple/>
	                                    </span>
	                                </div>
                                </td>
                            </tr>
                            <tr height="80px">
	                            <td colspan="3">
	                                <table id="MMA031_04_grid_01"></table>
	                            </td>
	                        </tr>
	                    </tbody>
	                </table>
	            </div>
                <div style="text-align:center;">
                    <span id="MMA031_04_span_01"><a href="javascript:MMA031_04.control.insertInquiryEmailRecord();" class="btnLoginBlue" style="font-family:'맑은 고딕', 'arial', 'Dotum', Gulim, '돋움', '굴림', 'malgun gothic', 'arial', 'sans-serif';"><spring:message code="SAVE"/></a></span>
                    <a href="javascript:MMA031_04.dialog.close();" class="btnLoginGrey" style="font-family:'맑은 고딕', 'arial', 'Dotum', Gulim, '돋움', '굴림', 'malgun gothic', 'arial', 'sans-serif';"><spring:message code="CLOSE"/></a>
                </div>
            </form>
            <form id="MMA031_04_form_02" name="MMA031_04_form_02" method="post">
                <input type="hidden" name="FILE_SEQ" id="FILE_SEQ"/>
                <input type="hidden" name="INQUIRY_NO" id="INQUIRY_NO" value="${INQUIRY_NO}"/>
            </form>
        </div>
        <div style="display:none;">
            <div class="easyui-layout" data-options="fit:true" id="MMA031_04_ttcont_01" style="height:300px;width:230px;">
                <div data-options="region:'center',border:false">
                    <table id="MMA031_04_grid_03"></table>
                </div>
                
            </div>
        </div>
	</div>
	
	<script type="text/javascript" src="/js/views/MM/POP/MM-A031_04.js"></script>
	<script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>