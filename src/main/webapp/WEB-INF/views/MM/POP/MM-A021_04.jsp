<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : MM-A021_04.jsp
  * @Description : 증빙파일 첨부(개별법)
  * @Modification Information
  *
  * author carlos
  *
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMA021_04_dailog_01"으로 하셔야 합니다.
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
                <a href="javascript:MMA021_04.control.saveEvDocFile();" id="MMA021_04_btn_01" class="btnFileImport"><spring:message code="UPLOD"/></a>
                <a href="javascript:MMA021_04.dialog.closeDialog();" class="btnPopClose"><spring:message code="CLOSE"/></a>
            </p>
        </div>
		<div data-options="region:'center', border:false">
		    <form id="MMA021_04_form_01" name="MMA021_04_form_01" enctype="multipart/form-data" method="POST">
	           <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${_MEMBER.SESSION_COMPANY_CD}" />
	           <input type="hidden" id="VENDOR_CD" name="VENDOR_CD" value="${_MEMBER.SESSION_VENDOR_CD}" />
	           <input type="hidden" id="IN_VENDOR_CD" name="IN_VENDOR_CD" value="${VENDOR_CD}" />
	           <input type="hidden" id="USER_ID" name="USER_ID" value="${_MEMBER.SESSION_USER_ID}" />
	           <input type="hidden" id="CO_DOC_NO" name="CO_DOC_NO" value="${CO_DOC_NO}"/>
	           <input type="hidden" id="REGISTED_BY" name="REGISTED_BY" value="1"/>
	           <table class="gridT" style="margin-bottom:0px;">
	               <colgroup>
	                  <col width="150px" />
	                  <col width="" />
	               </colgroup>
	               <tbody>
	                  <tr>
	                      <th scope="row" class="point2"><spring:message code="FILE, TYPE"/><%--파일 타입--%></th>
	                      <td>
	                          <input name="EV_DOC_TYPE" id="EV_DOC_TYPE" style="width:300px;"/>
	                      </td>
	                  </tr>
	                  <tr>
	                      <th scope="row"><spring:message code="LTIT"/><%--품목--%></th>
                          <td>
                              <input name="ITEM_CD" id="ITEM_CD" value="${ITEM_CD}" style="width:300px;"/>
                          </td>
                      </tr>
	                  <tr style="height:26px;">
	                      <th scope="row" class="point2"><spring:message code="TXT_ATTACH_FILE"/><%--첨부파일--%></th>
	                      <td>
	                         <input type="file" name="PRF_FILE" id="PRF_FILE" class="easyui-validatebox" required="true" onchange="javascript:MMA021_04.file.setFiles(this);" style="width:99%;height:22px;"/>
	                      </td>
	                  </tr>
	                  <tr>
	                      <th scope="row"><spring:message code="DSCPT"/><%--설명--%></th>
	                      <td>
	                         <textarea name="FILE_DESC" id="FILE_DESC" style="height:40px;width:99%;"></textarea>
	                      </td>
	                  </tr>
	               </tbody>
	           </table>
	       </form>
	   </div>
	</div>

    <script type="text/javascript" src="/js/views/MM/POP/MM-A021_04.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>
</body>
</html>