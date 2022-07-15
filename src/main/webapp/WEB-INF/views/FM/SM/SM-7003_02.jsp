<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
/**
 * @Class Name : SM-7003_02.jsp
 * @Description : 사용자 정보 관리
 * @Modification Information
 *
 * author carlos
 *
 * <notice>
 * 본 화면을 다리얼로그로 사용할 경우 명칭을 "SM7003_02_dailog_01"으로 하셔야 합니다.
 */
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body class="h2_etc">
	<div class="easyui-layout" data-options="fit:true">
		<form id="SM7003_02_form_01" name="SM7003_02_form_01" method="post">
			<input type="hidden" id="flag" name="flag" value="${flag}" />
			<input type="hidden" id="callPid" name="callPid" value="${callPid}" />
			<input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}" />
			<input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}" />
			<input type="hidden" id="CREATE_BY" name="CREATE_BY" value="${SESSION_USER_ID}" />
			<input type="hidden" id="PASSWORD_ORG" name="PASSWORD_ORG" value="${PASSWORD_ORG}" />
			<input type="hidden" id="TOP_SYS_ID" name="TOP_SYS_ID" value="" />
			<input type="hidden" id="P_USER_ID" name="P_USER_ID" value="" />
			<input type="hidden" id="gridData" name="gridData" value="" />
			<input type="hidden" id="gridData1" name="gridData1" value="" />
			<input type="hidden" id="USER_ENTR_SN" name="USER_ENTR_SN" value="" />
			<input type="hidden" id="USER_ENTR_BCNC_SN" name="USER_ENTR_BCNC_SN" value="" />
			<input type="hidden" id="EMAIL_AUTH_INTG_YN" name="EMAIL_AUTH_INTG_YN" value="${EMAIL_AUTH_INTG_YN}" />
			<div data-options="region:'north',border:false" style="height: 295px;">
				<div class="btn_R">
					<span id="SM7003_02_span_01"><a href="javascript:SM7003_02.control.updateUserInfo();" id="SM7003_02_btn_01" class="btnSave"><spring:message code="SAVE" /></a></span> 
					<span id="SM7003_02_span_02"><a href="javascript:SM7003_02.control.deleteUserInfo();" id="SM7003_02_btn_02" class="btnDelete"><spring:message code="DEL" /></a></span>
				</div>
				<p class="h2">
					<spring:message code="USER,INFMT" />
					<%--사용자정보--%>
				</p>
				<table class="dataT">
					<caption>
						<spring:message code="DTA, TB" />
						<%--데이타 테이블--%>
					</caption>
					<colgroup>
						<col width="150px" />
						<col width="" />
						<col width="170px" />
						<col width="" />
						<col width="150px" />
						<col width="" />
					</colgroup>
					<tbody>
						<tr>
							<th scope="row" class="point2">
								<spring:message code="USER,ID" />
								<%--사용자--%>
							</th>
							<td>
								<input type="text" name="USER_ID" id="USER_ID" value="${USER_ID}" class="easyui-validatebox" required="true" validType="['maxlength[20]']" style="width: 98%" title="<spring:message code="USER,ID"/>" />
							</td>
							<th scope="row" class="point2">
								<spring:message code="USER,NAME" />
								<%--사용자명--%>
							</th>
							<td>
								<input type="text" name="NAME" id="NAME" value="${NAME}" class="easyui-validatebox" required="true" validType="['maxlength[100]']" style="width: 98%" title="<spring:message code="USER,NAME"/>" />
							</td>
							<th scope="row">
								<spring:message code="USER,NAME,(,ENGLS,)" />
								<%--사용자명(영문)--%>
							</th>
							<td>
								<input type="text" name="NAME_ENG" id="NAME_ENG" value="${NAME_ENG}" class="easyui-validatebox" required="true" validType="['maxlength[100]']" style="width: 98%" title="<spring:message code="USER,NAME,(,ENGLS,)"/>" />
							</td>
						</tr>
						<tr>
							<th scope="row" class="point2">
								<spring:message code="POSIT" />
								<%--직급--%>
							</th>
							<td>
								<input type="text" name="POSITION" id="POSITION" value="${POSITION}" class="easyui-validatebox" required="true" validType="['maxlength[50]']" style="width: 98%" title="<spring:message code="POSIT"/>" />
							</td>
							<th scope="row" class="point2">
								<spring:message code="PWD" />
								<%--비밀번호--%>
							</th>
							<td>
								<input type="password" name="PASSWORD" id="PASSWORD" autocomplete="off" value="${PASSWORD}" class="easyui-validatebox" required="true" validType="['maxlength[128]']" style="width: 98%" title="<spring:message code="PWD"/>" />
							</td>
							<th scope="row" class="point2">
								<spring:message code="TXT_PASSWORD_CFM" />
								<%--비밀번호확인--%>
							</th>
							<td>
								<input type="password" name="PASSWORD_CFM" id="PASSWORD_CFM" autocomplete="off" value="${PASSWORD_CFM}" class="easyui-validatebox" required="true" validType="['maxlength[128]']" style="width: 98%" title="<spring:message code="TXT_PASSWORD_CFM"/>" />
							</td>
						</tr>
						<tr>
							<th scope="row">
								<spring:message code="TXT_EMP_NO" />
								<%--사원번호--%>
							</th>
							<td>
								<input type="text" name="EMP_NO" id="EMP_NO" value="${EMP_NO}" class="easyui-validatebox" validType="['maxlength[20]']" style="width: 98%" title="<spring:message code="TXT_EMP_NO"/>" />
							</td>
							<th scope="row">
								<spring:message code="OFTEL" />
								<%--사무실전화번호--%>
							</th>
							<td>
								<input type="text" name="OFFICE_PHONE_NO" id="OFFICE_PHONE_NO" value="${OFFICE_PHONE_NO}" class="easyui-validatebox" validType="['telnum','maxlength[50]']" style="width: 98%" title="<spring:message code="OFTEL"/>" />
							</td>
							<th scope="row">
								<spring:message code="FAX" />
								<%--팩스번호--%>
							</th>
							<td>
								<input type="text" name="FAX_NO" id="FAX_NO" value="${FAX_NO}" class="easyui-validatebox" validType="['telnum','maxlength[20]']" style="width: 98%" title="<spring:message code="FAX"/>" />
							</td>
						</tr>
						<tr>
							<th scope="row">
								<spring:message code="CELPH" />
								<%--핸드폰번호--%>
							</th>
							<td>
								<input type="text" name="CELL_PHONE_NO" id="CELL_PHONE_NO" value="${CELL_PHONE_NO}" class="easyui-validatebox" validType="['celnum','maxlength[20]']" style="width: 98%" title="<spring:message code="CELPH"/>" />
							</td>
							<th scope="row">
								<spring:message code="IN_CHARGE_TYPE" />
								<%--담당자 유형--%>
							</th>
							<td>
								<input type="text" name="MANAGER_YN" id="MANAGER_YN" value="${MANAGER_YN}" style="width: 150px" />
							</td>
							<th scope="row">
								<spring:message code="TXT_LANGUAGE" />
								<%--사용언어--%>
							</th>
							<td>
								<input type="text" id="DEFAULT_LANGUAGE" name="DEFAULT_LANGUAGE" value="${DEFAULT_LANGUAGE}" />
							</td>
						</tr>
						<tr>
							<th scope="row" class="point2">
								<spring:message code="TXT_APPLY_START_DATE" />
							</th>
							<td>
								<input type="text" name="START_DATE" id="START_DATE" value="${START_DATE}" style="width: 95px" />
							</td>
							<th scope="row" class="point2">
								<spring:message code="TXT_APPLY_END_DATE" />
							</th>
							<td>
								<input type="text" name="END_DATE" id="END_DATE" value="${END_DATE}" style="width: 95px" />
							</td>
							<th scope="row" class="point2">
								<spring:message code="USE,ORNOT" />
								<%--상태--%>
							</th>
							<td>
								<input type="text" name="STATUS" id="STATUS" value="${STATUS}" style="width: 100px" />
							</td>
						</tr>
						<tr>
							<th scope="row">
								<spring:message code="COMENT" />
							</th>
							<td colspan="5">
								<input type="text" name="END_DESC" id="END_DESC" value="${END_DESC}" style="width: 99%" />
							</td>
						</tr>
					</tbody>
				</table>
				<p class="h2">
					<spring:message code="MAIL, INFMT" />(SMTP) <span id="SM7003_02_label_01"></span>
				</p>
				<table class="dataT" style="margin-bottom:0px;">
					<caption>
						<spring:message code="DTA, TB" />
					</caption>
					<colgroup>
						<col width="150px" />
						<col width="" />
						<col width="170px" />
						<col width="" />
						<col width="150px" />
						<col width="" />
					</colgroup>
					<tbody>
						<tr>
							<th scope="row">
								<spring:message code="TXT_EMAIL" />
								<%--이메일--%>
							</th>
							<td>
								<input type="text" name="EMAIL" id="EMAIL" value="${EMAIL}" class="easyui-validatebox" validType="['email','maxlength[50]']" style="width: 98%" title="<spring:message code="TXT_EMAIL"/>" />
							</td>
							<th scope="row">
								<spring:message code="MAIL, CNFIT, ID" />
								<%--메일 인증 ID--%>
							</th>
							<td>
								<input type="text" name="MAIL_ID" id="MAIL_ID" value="${MAIL_ID}" class="easyui-validatebox" style="width: 98%" />
							</td>
							<th scope="row">
								<spring:message code="PWD" />
								<%--비밀번호--%>
							</th>
							<td>
								<input type="password" name="MAIL_PASSWORD" id="MAIL_PASSWORD" autocomplete="off" class="easyui-validatebox" style="width: 98%" />
							</td>
						</tr>
						<tr id="SM7003_02_tr_01" style="display: none;">
							<th scope="row">
								<spring:message code="AUTO,TXT_SEND_MAIL,APYDT" />
							</th>
							<td>
								<input type="text" name="MAIL_SENDER_YN" id="MAIL_SENDER_YN" value="${MAIL_SENDER_YN}" style="width: 100px" />
							</td>
							<th scope="row">Kakao(Javascript Key)</th>
                            <td colspan="3">
                                <input type="text" name="KAKAO_SCRIPT_KEY" id="KAKAO_SCRIPT_KEY" value="${KAKAO_SCRIPT_KEY}" style="width:98%"/>
                            </td>
						</tr>
					</tbody>
				</table>
			</div>
			<!-- 통관 OPENAPI 인증키 설정 -->
			<div data-options="region:'center',border:false" id="SM7003_02_CC_01" style="padding: 0; padding-top: 5px; display: none">
				<div class="easyui-layout" data-options="fit:true">
					<div data-options="region:'north',border:false" style="height: 50px;">
						<p class="h2">
							<spring:message code="CC_통관 정보" />
						</p>
						<table class="dataT" style="margin-bottom: 0px;">
							<caption>
								<spring:message code="DTA, TB" />
								<%--데이타 테이블--%>
							</caption>
							<colgroup>
								<col width="150px" />
								<col width="" />
								<col width="170px" />
								<col width="" />
								<col width="150px" />
								<col width="" />
							</colgroup>
							<tbody>
								<tr id="SM7003_02_tr_01">
									<th scope="row">
										<spring:message code="CC_신고인코드" />
									</th>
									<td colspan="5">
										<input type="text" name="APLC_CD" id="APLC_CD" value="${APLC_CD}" style="width: 60px" />
										<span id="SM7003_02_btn_03"><a href="javascript:SM7003_02.dialog.openDialog_14()"><img src="/images/sample/btn_sch.gif" /></a></span>
										<input type="text" id="APLC_NM" name="APLC_NM" value="${APLC_NM}" class="search_readOnly easyui-validatebox" readonly style="width:200px;"/>										
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div data-options="region:'center',border:false" style="padding-top: 3px;">
						<div class="easyui-layout" data-options="fit:true">
							<div data-options="region:'center',border:false" style="padding-top: 3px;">
								<div class="easyui-layout" data-options="fit:true">
									<div data-options="region:'north',border:false" class="h2_etc">
										<p class="h2">
											<spring:message code="CC_통관 OPENAPI 인증키 설정" />
											<font color="red"><spring:message code="CC_(인증키 미 등록 시 유니패스 연계 오류 발생 함)" /></font>
										</p>
									</div>
									<div data-options="region:'center',border:false">
										<div class="easyui-layout" data-options="fit:true">
											<div data-options="region:'center',border:false" class="h2_etc">
												<table id="SM7003_02_grid_01"></table>									
											</div>
										</div>								
									</div>								
								</div>
							</div>
							<div data-options="region:'east',border:false,split:true" style="width: 400px;padding-top: 3px;">
								<div class="easyui-layout" data-options="fit:true">
									<div data-options="region:'north',border:false" class="h2_etc">
										<p class="h2">
											<spring:message code="CC_통관 거래처" />
										</p>
					                    <p class="h2_btn">
											<a href="javascript:SM7003_02.ui.insertRow('2');" class="btnMoreInfo"><spring:message code="ADDRW" /></a> 
											<a href="javascript:SM7003_02.control.deleteUserEntrBcnc();" class="btnMinusInfo"><spring:message code="DELRW" /></a>
					                    </p>										
									</div>
									<div data-options="region:'center',border:false">
										<div class="easyui-layout" data-options="fit:true">
											<div data-options="region:'center',border:false" class="h2_etc">
												<table id="SM7003_02_grid_02"></table>									
											</div>
										</div>								
									</div>									
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
	
	<script type="text/javascript" src="/js/views/FM/SM/SM-7003_02.js"></script>
	<script type="text/javascript" src="/js/frame/common/yni.window.js"></script>
</body>
</html>