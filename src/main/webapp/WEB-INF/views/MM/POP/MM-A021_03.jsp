<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : MM-A021_03.jsp
  * @Description : 증빙파일 첨부(포괄법)
  * @Modification Information
  *
  * author carlos
  *
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMA021_03_dailog_01"으로 하셔야 합니다.
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
	    <form id="MMA021_03_form_01" name="MMA021_03_form_01" enctype="multipart/form-data" method="POST">
	        <input type="hidden" id="TARGET_PID"  name="TARGET_PID"  value="${TARGET_PID}"/>
	        <input type="hidden" id="COMPANY_CD"  name="COMPANY_CD"  value="${COMPANY_CD}"/>
	        <input type="hidden" id="DIVISION_CD" name="DIVISION_CD" value="${DIVISION_CD}"/>
	        <input type="hidden" id="VENDOR_CD"   name="VENDOR_CD"   value="${VENDOR_CD}"/>
	        <input type="hidden" id="CO_DOC_NO"   name="CO_DOC_NO"   value="${CO_DOC_NO}"/>
	        <input type="hidden" id="USER_ID"     name="USER_ID"     value="${USER_ID}"/>
	        <input type="hidden" id="REGISTED_BY" name="REGISTED_BY" value="${REGISTED_BY}"/>
	        <div data-options="region:'north',border:false" style="overflow:hidden;">
	            <p class="btn_R">
	                <a href="javascript:MMA021_03.control.saveEvDocFile();" id="MMA021_03_btn_01" class="btnFileImport"><spring:message code="UPLOD"/></a>
	                <a href="javascript:MMA021_03.dialog.closeDialog();" class="btnPopClose"><spring:message code="CLOSE"/></a>
	            </p>
	        </div>
	        <div data-options="region:'center',border:false">
                <div class="easyui-layout" data-options="fit:true">
                    <div data-options="region:'north',border:false" class="h2_etc" style="padding-top:6px;">
                           <div class="easyui-layout" data-options="fit:true">
                               <div data-options="region:'north',border:false" class="h2_etc">
                                   <p class="h2"><spring:message code="TXT_EVDC_FILE"/></p> <%--증빙파일--%>
                               </div>
                               <div data-options="region:'center',border:false">
	                               <table class="gridT" style="margin-bottom:0px;">
		                               <colgroup>
		                                   <col width="150px" />
                                           <col width="165px" />
		                                   <col width="150px" />
		                                   <col width="" />
		                               </colgroup>
		                               <tbody>
		                                   <tr>
	                                           <th scope="row"><spring:message code="FILE, TYPE"/><%--파일 타입--%></th>
	                                           <td>
	                                               <input name="EV_DOC_TYPE" id="EV_DOC_TYPE" style="width:160px;"/>
	                                           </td>
	                                           <th scope="row"><spring:message code="LTIT"/><%--품목--%></th>
                                               <td>
                                                   <input name="ITEM_CD" id="ITEM_CD" style="width:300px;"/>
                                               </td>
	                                       </tr>
	                                       <tr style="height:25px;">
	                                           <th scope="row"><spring:message code="TXT_ATTACH_FILE"/><%--첨부파일--%></th>
	                                           <td colspan="3">
	                                              <input type="file" name="PRF_FILE" id="PRF_FILE" class="easyui-validatebox" required="true" onchange="javascript:MMA021_03.file.setFiles(this);" style="width:99%;height:22px;"/>
	                                           </td>
	                                       </tr>
	                                       <tr>
	                                           <th scope="row"><spring:message code="DSCPT"/><%--설명--%></th>
	                                           <td colspan="3">
	                                              <textarea name="FILE_DESC" id="FILE_DESC" style="height:40px;width:99%;"></textarea>
	                                           </td>
	                                       </tr>
	                                   </tbody>
	                               </table>
                               </div>
                          </div>
                       </div>
                       <div data-options="region:'center',border:false" style="padding-top:6px;">
                        <div class="easyui-layout" data-options="fit:true">
                            <div data-options="region:'north',border:false" class="h2_etc">
                                <p class="h2"><spring:message code="WRITE,METHD"/></p> <%--작성방법--%>
                                <p class="h2_btn">
                                    <a href="javascript:MMA021_03.control.excelSampleDownload();" class="btnExport"><spring:message code="SAMPL, DOWN"/><%--샘플 다운로드--%></a>
                                </p>
                            </div>
                            <div data-options="region:'center',border:true" style="padding:5px 0px 0px 5px;background-color:#efefef;">
								<p><spring:message code='TXT_SUPPLIER_UP_01' /></p>
								<br>
								<p>1. <spring:message code='TXT_SUPPLIER_UP_02' /></p>
								<p style="padding-left:15px;">: <spring:message code='TXT_SUPPLIER_UP_03' /></p>
								<p>2. <spring:message code='FILE_TYPE' /></p>
								<p style="padding-left:15px;">1) <spring:message code='TXT_SUPPLIER_UP_04' />
								<p style="padding-left:30px;">: <spring:message code='TXT_SUPPLIER_UP_05' /></p>
								<p style="padding-left:15px;">2) <spring:message code='TXT_SUPPLIER_UP_06' />
								<p style="padding-left:45px;">- <spring:message code='TXT_SUPPLIER_UP_09' /></p>
								<p style="padding-left:45px;">- <spring:message code='TXT_SUPPLIER_UP_10' /></p>
								<p style="padding-left:45px;">- <spring:message code='TXT_SUPPLIER_UP_11' /></p>
								<p style="padding-left:45px;">- <spring:message code='TXT_SUPPLIER_UP_11' /></p>
								<p style="padding-left:45px;">- <spring:message code='TXT_SUPPLIER_UP_12' /></p>
								<p style="padding-left:45px;">- <spring:message code='TXT_SUPPLIER_UP_13' /></p>
								<p>3. <spring:message code='TXT_ATTACH_FILE' /></p>
								<p style="padding-left:15px;">: <spring:message code='TXT_SUPPLIER_UP_14' /></p>
								<p>4. <spring:message code='DSCPT' /></p>
								<p style="padding-left:15px;">: <spring:message code='TXT_SUPPLIER_UP_15' /></p>
								<p>5. <spring:message code='TXT_SUPPLIER_UP_16' /></p>
                            </div>
                         </div>
                    </div>
                </div>
	        </div>
	    </form>
	    <form id="MMA021_03_form_02" name="MMA021_03_form_02" method="post">
            <input type="hidden" id="FILE_NAME" name="FILE_NAME" value="MM-A021_확인서_증빙파일_KR.xls"/>
        </form>
	</div>

    <script type="text/javascript" src="/js/views/MM/POP/MM-A021_03.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>
</body>
</html>