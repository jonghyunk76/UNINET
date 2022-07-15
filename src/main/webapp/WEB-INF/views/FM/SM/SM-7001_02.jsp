<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : SM-7001_02.jsp
  * @Description : C/O 발급 고객사 관리
  * @Modification Information
  *
  * author YNI-Maker(이메일)
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "SM7001_02_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>      
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body class="h2_etc">
	<div class="easyui-layout" data-options="fit:true" width="800px">
	    <div data-options="region:'north',border:false" style="height:32px;">
	        <div class="btn_R">
                <a href="javascript:SM7001_02.control.selectHubCertID();" class="btnSave"><spring:message code="SAVE"/></a>
                <a href="javascript:SM7001_02.control.deleteHubCertCustomerInfo();" class="btnDelete"><spring:message code="DEL"/></a>
            </div>
        </div>
	    <div data-options="region:'center',border:false" style="overflow:hidden;">
	        <div class="easyui-layout" data-options="fit:true" class="sec_area">
                <!-- sec_tit -->
                <div data-options="region:'north',border:false" class="sec_tit">
                    <div class="h2_etc">
                       <p class="h2"><spring:message code="CNFDO, CERIS, CSTMR"/><%--* CNFDO 발급 고객사--%></p>
                   </div>
                </div>
                <div data-options="region:'center',border:false" style="padding-top:0px;overflow:hidden;">
		            <form id="SM7001_02_form_01" name="SM7001_02_form_01" method="post">
			            <input type="hidden" id="flag" name="flag" value="${flag}"/>
		                <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="SM7001_02"/>
		                <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
		                <input type="hidden" id="USER_ID" name="USER_ID" value="${_MEMBER.SESSION_USER_ID}"/>
		                <table class="dataT">
		                    <caption>데이타 검색조건 설정</caption>
		                    <colgroup>
		                        <col width="170px"/>
		                        <col width=";"/>
		                        <col width="170px"/>
		                        <col width=";"/>
		                        <col width="170px"/>
		                        <col width=";"/>
		                    </colgroup>
		                    <tbody>
		                        <tr>
		                            <th scope="row" class="point2"><spring:message code="TXT_CUSTOMER_CODE_HD"/><%--고객사코드--%></th>
		                            <td>
		                                <input type="text" id="CUSTOMER_CD" name="CUSTOMER_CD" readonly class="search_readOnly" value="${CUSTOMER_CD}" style="width:80%;"/>
		                                <span id="SM7001_02_span_01"><a href="javascript:SM7001_02.dialog.openDialog_1();"><img src="/images/sample/btn_sch.gif" alt=<spring:message code="SERCH"/>/></a></span>
		                            </td>
		                            <th scope="row" class="point2"><spring:message code="TXT_CUSTOMER_NAME_HD"/><%--고객사 명--%></th>
		                            <td>
		                                <input type="text" class="easyui-validatebox" required="true" id="CUSTOMER_NAME" name="CUSTOMER_NAME" value="${CUSTOMER_NAME}" style="width:98%"/>
		                            </td>
		                            <th scope="row"><spring:message code="TXT_CUSTOMER_NAME_ENG_HD"/><%--고객사 영문명--%></th>
		                            <td>
		                                <input type="text" id="CUSTOMER_NAME_ENG" name="CUSTOMER_NAME_ENG" value="${CUSTOMER_NAME_ENG}" style="width:98%"/>
		                            </td>
		                        </tr>
		                        <tr>
                                    <th scope="row" class="point2"><spring:message code="TXT_PRESIDENT_NAME"/><%--대표자명--%></th>
                                    <td>
                                        <input type="text" class="easyui-validatebox" required="true" id="PRESIDENT_NAME" name="PRESIDENT_NAME" value="${PRESIDENT_NAME}" style="width:98%"/>
                                    </td>
                                    <th scope="row"><spring:message code="TXT_PRESIDENT_NAME_ENG"/><%--대표자영문명--%></th>
                                    <td>
                                        <input type="text" id="PRESIDENT_NAME_ENG" name="PRESIDENT_NAME_ENG" value="${PRESIDENT_NAME_ENG}" style="width:98%"/>
                                    </td>
                                    <th scope="row" class="point2"><spring:message code="BZRGN"/><%--사업자 등록번호--%></th>
                                    <td>
                                        <input type="text" class="easyui-validatebox" required="true" id="BUSINESS_NO" name="BUSINESS_NO" value="${BUSINESS_NO}" style="width:98%"/>
                                    </td>
                                </tr>
                                <tr>
                                    <th scope="row" class="point2"><spring:message code="TELNM"/><%--전화번호--%></th>
                                    <td>
                                        <input type="text"class="easyui-validatebox" required="true" validType="['telnum']" id="PHONE_NO" name="PHONE_NO" value="${PHONE_NO}" style="width:98%"/>
                                    </td>
                                    <th scope="row" class="point2"><spring:message code="FAX"/><%--팩스번호--%></th>
                                    <td>
                                        <input type="text"class="easyui-validatebox" required="true" validType="['telnum']" id="FAX_NO" name="FAX_NO" value="${FAX_NO}" style="width:98%"/>
                                    </td>
                                    <th scope="row" class="point2"><spring:message code="TXT_EMAIL"/><%--이메일--%></th>
                                    <td>
                                        <input type="text"class="easyui-validatebox" required="true" id="CHARGE_EMAIL" name="CHARGE_EMAIL" value="${CHARGE_EMAIL}" style="width:98%"/>
                                    </td>
                                </tr>
                                <tr>
                                     <th scope="row" class="point2"><spring:message code="ADDRS,(,KORLN,)"/><%--주소(한글)--%></th>
                                     <td colspan="5">
                                        <input type="text"class="easyui-validatebox" required="true" id="ADDRESS" name="ADDRESS" value="${ADDRESS}" style="width:99%" />
                                    </td>
                                </tr>
                                <tr>
                                     <th scope="row"><spring:message code="ADDRS,(,ENGLS,)"/><%--주소(영문)--%></th>
                                     <td colspan="5">
                                        <input type="text" id="ADDRESS_ENG" name="ADDRESS_ENG" value="${ADDRESS_ENG}" style="width:99%" />
                                    </td>
                                </tr>
                                <tr>
                                    <th scope="row" class="point2"><spring:message code="CNFIT, ID"/><%--인증ID--%></th>
                                    <td colspan="5">
                                        <input type="text"class="easyui-validatebox" required="true" id="HUB_CERT_ID" name="HUB_CERT_ID" value="${HUB_CERT_ID}" style="width:156px"/>
                                    </td>
                                </tr>
		                    </tbody>
		                </table>
		            </form>
		        </div>
		    </div>
	    </div>
	</div>
	
	<script type="text/javascript" src="/js/views/FM/SM/SM-7001_02.js"></script>
	<script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>