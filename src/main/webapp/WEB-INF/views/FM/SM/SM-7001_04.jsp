<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : SM-7001_04.jsp
  * @Description : 서명권자 관리
  * @Modification Information
  *
  * author YNI-Maker(이메일)
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "SM7001_04_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>      
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body class="h2_etc">
	<div class="easyui-layout" data-options="fit:true">
	   <div data-options="region:'north',border:false" style="height:32px;">
            <div class="btn_R">
                <a href="javascript:SM7001_04.control.updateHubSignatureInfo();" class="btnSave"><spring:message code="SAVE"/></a>
                <a href="javascript:SM7001_04.control.deleteHubSignatureInfo();" class="btnDelete"><spring:message code="DEL"/></a>
            </div>
	   </div>
	   <div data-options="region:'center',border:false" style="overflow:hidden;">
            <div class="easyui-layout" data-options="fit:true" class="sec_area">
                <!-- sec_tit -->
                <div data-options="region:'north',border:false" class="sec_tit">
                    <div class="h2_etc">
                       <p class="h2"><spring:message code="TXT_SIGN_USER"/><%--* 서명권자--%></p>
                   </div>
                </div>
                <div data-options="region:'center',border:false" style="padding-top:0px; overflow:hidden;">
                    <form id="SM7001_04_form_01" name="SM7001_04_form_01" method="post" enctype="multipart/form-data">
                        <input type="hidden" id="flag" name="flag" value="${flag}"/>
                        <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="SM7001_04"/>
                        <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
                        <input type="hidden" id="SIGNATURE_SEQ" name="SIGNATURE_SEQ" value="${SIGNATURE_SEQ}"/>
                        <input type="hidden" id="USER_ID" name="USER_ID" value="${USER_ID}"/>
                        <input type="hidden" id="HUB_CERT_YN" name="HUB_CERT_YN" value="Y"/>
                        <table class="dataT">
                            <caption>데이타 검색조건 설정</caption>
                            <colgroup>
                                <col width="170px"/>
                                <col width=";"/>
                                <col width="170px"/>
                                <col width=";"/>
                                <col width="120px"/>
                                <col width=";"/>
                            </colgroup>
                            <tbody>
                                <tr>
                                    <th scope="row" class="point2"><spring:message code="TXT_SIGN_USER_NAME"/><%--서명권자 명--%></th>
                                    <td>
                                        <input type="text" id="SIGNATURE_NAME" name="SIGNATURE_NAME" value="${SIGNATURE_NAME}" readonly class="search_readOnly" style="width:130px"/>
                                        <span id="SM7001_04_span_01"><a href="javascript:SM7001_04.dialog.openDialog_1();"><img src="/images/sample/btn_sch.gif" alt=<spring:message code="SERCH"/>/></a></span>
                                    </td>
                                    <th scope="row" class="point2"><spring:message code="TXT_SIGN_USER,TXT_NM_ENG"/><%--서명권자 영문명--%></th>
                                    <td>
                                        <input type="text" class="easyui-validatebox" required="true" id="SIGNATURE_NAME_ENG" name="SIGNATURE_NAME_ENG" value="${SIGNATURE_NAME_ENG}" style="width:98%"/>
                                    </td>
                                    <th scope="row" class="point2"><spring:message code="DIVIS"/><%--사업부--%></th>
                                    <td>
                                        <input type="text" id="DIVISION_CD" name="DIVISION_CD" value="${DIVISION_CD}" style="width:160px"/>
                                    </td>
                                </tr>
                                <tr>
                                    <th scope="row" class="point2"><spring:message code="POSIT"/><%--직급--%></th>
                                    <td>
                                        <input type="text" class="easyui-validatebox" required="true" id="POSITION" name="POSITION" value="${POSITION}" style="width:96%"/>
                                    </td>
                                    <th scope="row" class="point2"><spring:message code="TXT_APPLY_PERIOD"/><%--적용기간--%></th>
                                    <td colspan="3">
		                                <input name="START_DATE" id="START_DATE" value="${START_DATE}" style="width:120px"/> ~
		                                <input name="END_DATE" id="END_DATE" value="${END_DATE}" style="width:120px"/>
		                            </td>
                                </tr>
                                <tr>
                                    <th cope="row" class="point2"><spring:message code="POSIT,(,TXT_EN_LANG,)"/></th><%--직급 영문--%>
                                    <td>
                                        <input type="text" class="easyui-validatebox" required="true" id="POSITION_ENG" name="POSITION_ENG" value="${POSITION_ENG}" style="width:96%"/>
                                    </td>
                                    <th rowspan="3" cope="row" class="point2"><spring:message code="TXT_SIGN_FILE"/></th><%--서명파일--%>
                                    <td  colspan="3">
                                        <input type="file" class="easyui-validatebox" required="true" id="SIGNATURE_IMAGE" name="SIGNATURE_IMAGE" value="${SIGNATURE_IMAGE}" onchange="javascript:SM7001_04.file.setFiles(this);" style="width:96%"/>
                                    </td>
                                </tr>
                                <tr>    
                                    <th cope="row"><spring:message code="TELNM"/></th><%--전화번호--%>
                                    <td>
                                        <input type="text"id="PHONE_NO" name="PHONE_NO" value="${PHONE_NO}" style="width:96%"/>
                                    </td>
                                    <td colspan="3" rowspan="3">
                                        <img src="" id="SM7001_04_img_01" name="SM7001_04_img_01" style="width:100px;height:62px;"/>
                                    </td>
                                    
                                </tr>
                                <tr>
                                    <th cope="row"><spring:message code="TXT_EMAIL"/></th><%--이메일--%>
                                    <td>
                                        <input type="text"id="EMAIL" name="EMAIL" value="${EMAIL}" style="width:96%"/>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </form>
                </div>              
	        </div>
	   </div>
	</div>
	
	<script type="text/javascript" src="/js/views/FM/SM/SM-7001_04.js"></script>
	<script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>