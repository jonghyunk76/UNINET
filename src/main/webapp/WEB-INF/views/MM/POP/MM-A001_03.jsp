<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : MM-A001_03.jsp
  * @Description : 협력사 포탈 메인화면
  * @Modification Information
  *
  * author YNI-Maker
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMA001_03_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>      
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <div class="easyui-layout" data-options="fit:true" style="height:100%;">
        <div data-options="region:'north',border:false" style="padding-top:3px;">
            <div>
                <form id="MMA001_03_form_06" name="MMA001_03_form_06" method="POST">
	                <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${_MEMBER.SESSION_COMPANY_CD}" />
	                <input type="hidden" id="VENDOR_CD" name="VENDOR_CD"  value="${_MEMBER.SESSION_VENDOR_CD}" />
	                <input type="hidden" id="REMOTE_SERVICE_YN" name="REMOTE_SERVICE_YN" value="${REMOTE_SERVICE_YN}"/>
	                <table class="gridT" style="margin-bottom:0px;">
	                    <caption><spring:message code="TXT_DATA_SEARCH_CONDITION"/><%--데이타 검색조건 설정--%></caption>
	                    <colgroup>
	                        <col width="150px" />
	                        <col width="180px" />
	                        <col width="150px" />
                            <col width="80px" />
	                        <col width="130px" />
	                        <col width=";" />
	                    </colgroup>
	                    <tbody>
	                        <tr>
	                            <th scope="row"><spring:message code="TXT_LATST_DEAL_DATE"/><%--거래일자--%></th>
	                            <td>
	                                <input id="FROM_DATE" name="FROM_DATE" style="width:95px"/> ~ <spring:message code="TODAY"/>
	                                <input type="hidden" id="TO_DATE" name="TO_DATE"/>
	                            </td>
	                            <th scope="row"><spring:message code="CNFDO, SUNMT,YEAR"/><%--확인서 포괄년도--%></th>
	                            <td>
	                                <input type="text" name="CO_COVER_YEAR" id="CO_COVER_YEAR" style="width:60px;"/>
	                            </td>
	                            <th scope="row"><spring:message code="FTA, GROUP"/><%--FTA그룹코드--%></th>
	                            <td>
	                                <input type="text" name="FTA_GROUP_CD" id="FTA_GROUP_CD" style="width:100px;"/>
	                                <span style="padding-left:15px;">
                                        <a href="javascript:MMA001_03.control.selectVendorProcessInfo();" class="easyui-linkbutton pop_sch"><spring:message code="SRCH"/><%--조회--%></a>
                                    </span>
	                            </td>
	                        </tr>
	                    </tbody>
	                </table>
	            </form>
            </div>
        </div>
        <div data-options="region:'center',border:false,split:false" style="padding-top:9px;">
            <div class="easyui-layout" data-options="fit:true">
                <div data-options="region:'north',border:false,split:true" style="height:340px;">
		            <div class="easyui-layout" data-options="fit:true">
		                <div data-options="region:'center',border:false" class="width50">
		                    <div class="easyui-layout" data-options="fit:true">
		                        <div data-options="region:'north',border:false" class="h2_etc">
		                            <p class="h2"><spring:message code="CRTOR"/> <spring:message code="TXT_RESLT_STAT"/></p>
		                        </div>
		                        <div data-options="region:'center',border:false">
	                                <table class="dataT" style="margin-bottom:0px;height:100%;">
	                                    <colgroup>
				                            <col width="120px" />
				                            <col width=";" />
				                            <col width=";" />
				                            <col width=";" />
				                            <col width=";" />
				                        </colgroup>
				                        <tbody>
				                            <tr height="35">
                                                <th scope="row" style="text-align:center;font-weight:bold;"><spring:message code="CLSE"/></th>
                                                <th scope="row" style="text-align:center;font-weight:bold;"><spring:message code="ALL"/></th>
                                                <th scope="row" style="text-align:center;font-weight:bold;"><spring:message code="DPEND"/></th>
                                                <th scope="row" style="text-align:center;font-weight:bold;"><spring:message code="CMPTE"/></th>
                                                <th scope="row" style="text-align:center;font-weight:bold;"><spring:message code="CMPTE_RATE"/></th>
                                            </tr>
                                          <c:if test="${SESSION_FTA_NATION eq 'VN'}">
                                            <tr>
                                                <th scope="row" style="text-align:center;"><spring:message code="DOMDE"/> <spring:message code="LTIT"/></th>
                                                <td style="text-align:center;padding:0px;"><label id="MMA001_03_lebal_13" style="font-size:12Pt;font-weight:bold;">0</label></td>
                                                <td style="text-align:center;padding:0px;"><label id="MMA001_03_lebal_14" style="font-size:12Pt;font-weight:bold;color:red;">0</label></td>
                                                <td style="text-align:center;padding:0px;"><label id="MMA001_03_lebal_15" style="font-size:12Pt;font-weight:bold;color:blue;">0</label></td>
                                                <td style="text-align:center;padding:0px;"><label id="MMA001_03_lebal_16" style="font-size:12Pt;font-weight:bold;">0</label></td>
                                            </tr>
                                            <tr>
                                                <th scope="row" style="text-align:center;"><spring:message code="IMP_LTIT"/></th>
                                                <td style="text-align:center;padding:0px;"><label id="MMA001_03_lebal_05" style="font-size:12Pt;font-weight:bold;">0</label></td>
                                                <td style="text-align:center;padding:0px;"><label id="MMA001_03_lebal_06" style="font-size:12Pt;font-weight:bold;color:red;">0</label></td>
                                                <td style="text-align:center;padding:0px;"><label id="MMA001_03_lebal_07" style="font-size:12Pt;font-weight:bold;color:blue;">0</label></td>
                                                <td style="text-align:center;padding:0px;"><label id="MMA001_03_lebal_08" style="font-size:12Pt;font-weight:bold;">0</label></td>
                                            </tr>
                                            <tr>
                                                <th scope="row" style="text-align:center;"><spring:message code="MOD_REQ"/></th>
                                                <td style="text-align:center;padding:0px;"><label id="MMA001_03_lebal_09" style="font-size:12Pt;font-weight:bold;">0</label></td>
                                                <td style="text-align:center;padding:0px;"><label id="MMA001_03_lebal_10" style="font-size:12Pt;font-weight:bold;color:red;">0</label></td>
                                                <td style="text-align:center;padding:0px;"><label id="MMA001_03_lebal_11" style="font-size:12Pt;font-weight:bold;color:blue;">0</label></td>
                                                <td style="text-align:center;padding:0px;"><label id="MMA001_03_lebal_12" style="font-size:12Pt;font-weight:bold;">0</label></td>
                                            </tr>
                                          </c:if>
                                          <c:if test="${SESSION_FTA_NATION ne 'VN'}">
				                            <tr>
				                                <th scope="row" style="text-align:center;"><spring:message code="VGB101_01"/></th>
				                                <td style="text-align:center;padding:0px;"><label id="MMA001_03_lebal_01" style="font-size:12Pt;font-weight:bold;">0</label></td>
				                                <td style="text-align:center;padding:0px;"><label id="MMA001_03_lebal_02" style="font-size:12Pt;font-weight:bold;color:red;">0</label></td>
				                                <td style="text-align:center;padding:0px;"><label id="MMA001_03_lebal_03" style="font-size:12Pt;font-weight:bold;">-</label></td>
				                                <td style="text-align:center;padding:0px;"><label id="MMA001_03_lebal_04" style="font-size:12Pt;font-weight:bold;">-</label></td>
				                            </tr>
				                            <tr>
                                                <th scope="row" style="text-align:center;"><spring:message code="VGB102_01"/></th>
                                                <td style="text-align:center;padding:0px;"><label id="MMA001_03_lebal_05" style="font-size:12Pt;font-weight:bold;">0</label></td>
                                                <td style="text-align:center;padding:0px;"><label id="MMA001_03_lebal_06" style="font-size:12Pt;font-weight:bold;color:red;">0</label></td>
                                                <td style="text-align:center;padding:0px;"><label id="MMA001_03_lebal_07" style="font-size:12Pt;font-weight:bold;color:blue;">0</label></td>
                                                <td style="text-align:center;padding:0px;"><label id="MMA001_03_lebal_08" style="font-size:12Pt;font-weight:bold;">0</label></td>
                                            </tr>
                                            <tr>
                                                <th scope="row" style="text-align:center;"><spring:message code="MOD_REQ"/></th>
                                                <td style="text-align:center;padding:0px;"><label id="MMA001_03_lebal_09" style="font-size:12Pt;font-weight:bold;">0</label></td>
                                                <td style="text-align:center;padding:0px;"><label id="MMA001_03_lebal_10" style="font-size:12Pt;font-weight:bold;color:red;">0</label></td>
                                                <td style="text-align:center;padding:0px;"><label id="MMA001_03_lebal_11" style="font-size:12Pt;font-weight:bold;color:blue;">0</label></td>
                                                <td style="text-align:center;padding:0px;"><label id="MMA001_03_lebal_12" style="font-size:12Pt;font-weight:bold;">0</label></td>
                                            </tr>
                                            <tr>
                                                <th scope="row" style="text-align:center;"><spring:message code="DPEND_REQ"/></th>
                                                <td style="text-align:center;padding:0px;"><label id="MMA001_03_lebal_13" style="font-size:12Pt;font-weight:bold;">0</label></td>
                                                <td style="text-align:center;padding:0px;"><label id="MMA001_03_lebal_14" style="font-size:12Pt;font-weight:bold;color:red;">0</label></td>
                                                <td style="text-align:center;padding:0px;"><label id="MMA001_03_lebal_15" style="font-size:12Pt;font-weight:bold;color:blue;">0</label></td>
                                                <td style="text-align:center;padding:0px;"><label id="MMA001_03_lebal_16" style="font-size:12Pt;font-weight:bold;">0</label></td>
                                            </tr>
                                            <tr id="MMA001_03_tr_01" style="display:none;">
                                                <th scope="row" style="text-align:center;"><spring:message code="GENER_ORG"/></th>
                                                <td style="text-align:center;padding:0px;"><label id="MMA001_03_lebal_17" style="font-size:12Pt;font-weight:bold;">0</label></td>
                                                <td style="text-align:center;padding:0px;"><label id="MMA001_03_lebal_18" style="font-size:12Pt;font-weight:bold;color:red;">0</label></td>
                                                <td style="text-align:center;padding:0px;"><label id="MMA001_03_lebal_19" style="font-size:12Pt;font-weight:bold;color:blue;">0</label></td>
                                                <td style="text-align:center;padding:0px;"><label id="MMA001_03_lebal_20" style="font-size:12Pt;font-weight:bold;">0</label></td>
                                            </tr>
                                            <tr id="MMA001_03_tr_02" style="display:none;">
                                                <th scope="row" style="text-align:center;"><spring:message code="TOLPC_CNFDO"/></th>
                                                <td style="text-align:center;padding:0px;"><label id="MMA001_03_lebal_21" style="font-size:12Pt;font-weight:bold;">0</label></td>
                                                <td style="text-align:center;padding:0px;"><label id="MMA001_03_lebal_22" style="font-size:12Pt;font-weight:bold;color:red;">0</label></td>
                                                <td style="text-align:center;padding:0px;"><label id="MMA001_03_lebal_23" style="font-size:12Pt;font-weight:bold;color:blue;">0</label></td>
                                                <td style="text-align:center;padding:0px;"><label id="MMA001_03_lebal_24" style="font-size:12Pt;font-weight:bold;">0</label></td>
                                            </tr>
                                          </c:if>
				                        </tbody>
	                                </table>
		                        </div>
		                    </div>
		                </div>
		                <div data-options="region:'east',border:false,split:true" class="width50">
		                    <div class="easyui-layout" data-options="fit:true">
	                            <div data-options="region:'north',border:false" class="h2_etc">
	                                <p class="h2"><spring:message code="CRTOR_SUNMT_RECOD"/></p>
	                                <p class="h2_btn">
                                        <a href="javascript:MMA001_03.dialog.openDialog_2();" class="btnSelectClick"><spring:message code="DETIL,INFMT"/></a>
                                    </p>
	                            </div>
	                            <div data-options="region:'center',border:false">
	                                <form id="MMA001_03_form_01" name="MMA001_03_form_01">
                                        <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${_MEMBER.SESSION_COMPANY_CD}" />
                                        <input type="hidden" id="VENDOR_CD" name="VENDOR_CD" value="${_MEMBER.SESSION_VENDOR_CD}" />
                                        <input type="hidden" id="USER_ID" name="USER_ID" value="${_MEMBER.SESSION_USER_ID}" />
                                        <input type="hidden" id="GLOBAL_CD" name="GLOBAL_CD" value="KR"/>
                                        <input type="hidden" id="FROM_DATE" name="FROM_DATE"/>
                                        <input type="hidden" id="TO_DATE" name="TO_DATE"/>
                                        <input type="hidden" id="CO_DETAIL_YN" name="CO_DETAIL_YN" value="N"/>
                                    </form>
                                    <table id="MMA001_03_grid_02"></table>
	                            </div>
		                    </div>
		                </div>
		            </div>
		        </div>
		        <div data-options="region:'center',border:false">
		            <div class="easyui-layout" data-options="fit:true">
		                <div data-options="region:'center',border:false" class="width50">
		                    <div class="easyui-layout" data-options="fit:true">
		                        <div data-options="region:'north',border:false" class="h2_etc">
		                            <p class="h2"><spring:message code="CSTMR, REQ, LIST"/></p>
		                            <p class="h2_btn">
		                                <a href="javascript:MMA001_03.ui.pageMove();" class="btnPageForward"><spring:message code="RETMU"/></a>
		                            </p>
		                        </div>
		                        <div data-options="region:'center',border:false">
		                            <form id="MMA001_03_form_03" name="MMA001_03_form_03">
		                                <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${_MEMBER.SESSION_COMPANY_CD}" />
		                                <input type="hidden" id="SYS_CODE" name="SYS_CODE"/>
		                            </form>
		                            <form id="MMA001_03_form_04" name="MMA001_03_form_04">
		                                <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${_MEMBER.SESSION_COMPANY_CD}" />
		                                <input type="hidden" id="AUTH_GROUP_ID" name="AUTH_GROUP_ID" VALUE="supplier"/>
		                                <input type="hidden" id="MENU_ID" name="MENU_ID"/>
		                            </form>
		                            <form id="MMA001_03_form_05" name="MMA001_03_form_05">
		                                <input type="hidden" id="GEN_ORIGIN_REQ_YN" name="GEN_ORIGIN_REQ_YN"/>
		                                <input type="hidden" id="GEN_ORIGIN_MENU_YN" name="GEN_ORIGIN_MENU_YN"/>
		                                <input type="hidden" id="VENDOR_INFO_REQUIRED_YN" name="VENDOR_INFO_REQUIRED_YN" value="${VENDOR_INFO_REQUIRED_YN}"/>
		                                <input type="hidden" id="VENDOR_WRITE_YN" name="VENDOR_WRITE_YN" value="${VENDOR_WRITE_YN}"/>
		                            </form>
		                            <form id="MMA001_03_form_07" name="MMA001_03_form_07">
                                        <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${_MEMBER.SESSION_COMPANY_CD}" />
                                        <input type="hidden" id="AUTH_GROUP_ID" name="AUTH_GROUP_ID" VALUE="supplier"/>
                                        <input type="hidden" id="MENU_ID" name="MENU_ID"/>
                                    </form>
		                            <table id="MMA001_03_grid_03"></table>
		                        </div>
		                    </div>
		                </div>
		                <div data-options="region:'east',border:false,split:true" class="width50">
		                    <div class="easyui-layout" data-options="fit:true">
		                        <div data-options="region:'north',border:false" class="h2_etc">
		                            <p class="h2"><spring:message code="NOTIC"/></p>
		                            <p class="h2_btn">
		                                <a href="javascript:MMA001_03.dialog.openDialog_1();" class="btnSelectClick"><spring:message code="DETIL,INFMT"/></a>
		                            </p>
		                        </div>
		                        <div data-options="region:'center',border:false">
		                            <table id="MMA001_03_grid_01"></table>
		                        </div>
		                    </div>
		                </div>
		                <div id="MMA001_03_div_01" data-options="region:'south',border:false,split:false" style="display:none;padding-top:2px;">
		                    <span id="supplierKoreaSiteLink" style="display:none;border:0px solid #000;">
		                        <span id="MMA001_03_span_03" style="display:none;padding-right:0px"><a href="http://mes.inzi.co.kr" onclick="window.open(this.href);return false;" onkeypress="this.onclick;"><img src="/images/ci/c_banner.png" /></a></span>
		                        <span style="padding-right:0px"><a href="http://www.customs.go.kr" onclick="window.open(this.href);return false;" onkeypress="this.onclick;"><img src="/images/main/ns_ba03.png" /></a></span>
		                        <span style="padding-right:0px"><a href="http://www.customs.go.kr/kcshome/site/index.do?layoutSiteId=cvnci" onclick="window.open(this.href);return false;" onkeypress="this.onclick;"><img src="/images/main/kcshome_ba10.png" /></a></span>
		                        <span style="padding-right:0px;display:none;"><a href="http://www.motie.go.kr" onclick="window.open(this.href);return false;" onkeypress="this.onclick;"><img src="/images/main/ns_ba01.png" /></a></span>
		                        <span style="padding-right:0px;display:none;"><a href="http://www.mosf.go.kr" onclick="window.open(this.href);return false;" onkeypress="this.onclick;"><img src="/images/main/ns_ba02.png" /></a></span>
		                        <span style="padding-right:0px"><a href="https://unipass.customs.go.kr/clip" onclick="window.open(this.href);return false;" onkeypress="this.onclick;"><img src="/images/main/ns_ba04.png" /></a></span>
		                        <span style="padding-right:0px"><a href="http://okfta.kita.net" onclick="window.open(this.href);return false;" onkeypress="this.onclick;"><img src="/images/main/ns_ba05.png" /></a></span>
		                        <span style="padding-right:0px;display:none;"><a href="http://www.ftapass.or.kr" onclick="window.open(this.href);return false;" onkeypress="this.onclick;"><img src="/images/main/ns_ba06.png" /></a></span>
		                        <span style="padding-right:0px"><a href="http://yesfta.customs.go.kr" onclick="window.open(this.href);return false;" onkeypress="this.onclick;"><img src="/images/main/ns_ba07.png" /></a></span>
		                        <a href="http://www.fta.go.kr" onclick="window.open(this.href);return false;" onkeypress="this.onclick;"><img src="/images/main/ns_ba08.png" /></a>
		                        <span style="padding-right:0px"><a href="https://www.wto.org/" onclick="window.open(this.href);return false;" onkeypress="this.onclick;"><img src="/images/main/wto_ba09.png" /></a></span>
		                    </span>
	                        <span id="MMA001_03_span_04" style="display:none;padding-right:0px"><a href="${REMOTE_SERVICE_URL}" onclick="window.open(this.href);return false;" onkeypress="this.onclick;"><img id="MMA001_03_img_01" src="#" /></a></span>
		                </div>
		            </div>
		        </div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-A001_03.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>