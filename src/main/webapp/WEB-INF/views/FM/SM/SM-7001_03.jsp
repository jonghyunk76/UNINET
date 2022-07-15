<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : SM-7001_03.jsp
  * @Description : 거래 고객사 관리
  * @Modification Information
  *
  * author YNI-Maker(이메일)
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "SM7001_03_dailog_01"으로 하셔야 합니다.
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
                <a href="javascript:SM7001_03.control.updateHubSalesCustomerInfo();" class="btnSave"><spring:message code="SAVE"/></a>
                <a href="javascript:SM7001_03.control.deleteHubSalesCustomerInfo();" class="btnDelete"><spring:message code="DEL"/></a>
            </div>
       </div>
	   <div data-options="region:'center',border:false" style="overflow:hidden;">
	        <div data-options="region:'north',border:false" class="sec_tit">
		        <div class="h2_etc">
		           <p class="h2"><spring:message code="CNFDO, CERIS, CSTMR"/><%--* CNFDO 발급 고객사--%></p>
		        </div>
	        </div>
	        <div data-options="region:'center',border:false" style="padding-top:0px;">
	           <form id="SM7001_03_form_01" name="SM7001_03_form_01" method="post">
	               <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
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
                                 <th scope="row" class="point2"><spring:message code="TXT_CUSTOMER_CODE_HD"/></th><%--고객사코드--%>
		                         <td>
		                             <input type="text" id="CUSTOMER_CD" name="CUSTOMER_CD" value="${CUSTOMER_CD}" style="width:157px;"/>
		                         </td>
                                 <th scope="row"><spring:message code="TXT_CUSTOMER_NAME_HD"/><%--고객사 명--%></th>
                                 <td>
                                     <input type="text" id="CUSTOMER_NAME" name="CUSTOMER_NAME" value="${CUSTOMER_NAME}" style="width:98%" readonly class="input_readOnly"/>
                                 </td>
                                 <th scope="row"><spring:message code="BZRGN"/><%--사업자 등록번호--%></th>
                                 <td>
                                     <input type="text" id="BUSINESS_NO" name="BUSINESS_NO" value="${BUSINESS_NO}" style="width:98%" readonly class="input_readOnly"/>
                                 </td>
                             </tr>
                             <tr>
	                             <th scope="row" ><spring:message code="TELNM"/><%--전화번호--%></th>
	                             <td>
	                                 <input type="text" id="PHONE_NO" name="PHONE_NO" value="${PHONE_NO}" style="width:98%" readonly class="input_readOnly"/>
	                             </td>
	                             <th scope="row"><spring:message code="ADDRS"/><%--주소--%></th>
	                             <td colspan="3">
	                                <input type="text" style="width:98%" id="ADDRESS" name="ADDRESS" value="${ADDRESS}" readonly class="input_readOnly"/>
	                             </td>   
                             </tr>
                            </tbody>
	               </table>
	           </form>
	           <p class="h2"><spring:message code="SALES,CSTMR"/><%--거래고객사--%></p>
	           <form id="SM7001_03_form_02" name="SM7001_03_form_02" method="post">
	               <input type="hidden" id="flag" name="flag" value="${flag}"/>
                   <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="SM7001_03"/>
                   <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
                   <input type="hidden" id="USER_ID" name="USER_ID" value="${_MEMBER.SESSION_USER_ID}"/>
                   <input type="hidden" id="CUSTOMER_CD" name="CUSTOMER_CD"/>
	               <table class="dataT">
	                    <caption><spring:message code="DTA, TB"/><%--데이타 테이블--%></caption>
		                    <colgroup>
		                        <col width="120px" />
		                        <col width=";" />
		                        <col width="120px" />
		                        <col width=";" />
		                        <col width="150px" />
		                        <col width=";" />
		                    </colgroup>
	                    <tbody>
	                        <tr>
	                           <th scope="row" class="point2"><spring:message code="TXT_CUSTOMER_CODE_HD"/><%--거래고객사 코드--%></th>
                                 <td>
                                     <input type="text" id="SALES_CUSTOMER_CD" name="SALES_CUSTOMER_CD" value="${SALES_CUSTOMER_CD}" readonly class="search_readOnly" style="width:130px;"/>
                                     <span id="SM7001_03_span_01"><a href="javascript:SM7001_03.dialog.openDialog_1();"><img src="/images/sample/btn_sch.gif" alt=<spring:message code="SERCH"/> /></a></span>
                                 </td>
                               <th scope="row" class="point2"><spring:message code="TXT_CUSTOMER_NAME_HD"/><%--거래고객사 명--%></th>
                                 <td>
                                     <input type="text" class="easyui-validatebox" required="true" id="SALES_CUSTOMER_NAME" name="SALES_CUSTOMER_NAME" value="${SALES_CUSTOMER_NAME}" style="width:98%"/>
                                 </td>
                               <th scope="row" class="point2"><spring:message code="TXT_SALES_DIVISION"/><%--판매 사업부--%></th>
                                 <td>
                                     <input type="text" id="DIVISION_CD" name="DIVISION_CD" value="${DIVISION_CD}" style="width:160px;"/>
                                 </td>
                            </tr>
                            <tr>     
                               <th scope="row"><spring:message code="COMENT"/><%--비고--%></th>
                                 <td colspan="5">
                                 <textarea type="textarea" name="REMARK" id="REMARK" style="width:99%;height:58px;">${REMARK}</textarea>
                                 </td>
	                        </tr>
	                    </tbody>
                   </table>
	           </form>
	        </div>   
	   </div>
	</div>
	<script type="text/javascript" src="/js/views/FM/SM/SM-7001_03.js"></script>
	<script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>