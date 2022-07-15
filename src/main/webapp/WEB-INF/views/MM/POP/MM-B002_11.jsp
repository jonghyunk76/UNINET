<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	/**
	 * @Class Name : MMB002_11
	 * @Description : 오픈API
	 * @Modification Information
	 *
	 * author carlos
	 * 
	 * <notice>
	 */
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'north',border:false,split:true" style="height: 230px;">
			<div class="easyui-layout" data-options="fit:true">
				<div data-options="region:'north',border:false" class="h2_etc">
					<form id="MMB002_11_form_02" name="MMB002_11_form_02" method="post">
						<p class="h2">
							<spring:message code="CC_오픈API 항목 송신 파라미터" />
						</p>
						<p class="h2_btn">
							<input type="checkbox" id="TARGET_INSERT_AT" name="TARGET_INSERT_AT" />
							<spring:message code="CC_가져오기" />
							<a href="javascript:MMB002_11.control.selectOpenAPICallSelectList();" class="easyui-linkbutton pop_sch" id="MMB002_11_searchBtn"><spring:message code="SRCH" /></a>
						</p>
					</form>
				</div>
				<div data-options="region:'center',border:false">
					<form id="MMB002_11_form_01" name="MMB002_11_form_01" method="POST">
						<input type="hidden" id="SESSION_COMPANY_CD" name="SESSION_COMPANY_CD" value="${_MEMBER.SESSION_COMPANY_CD}" /> 
						<input type="hidden" id="SESSION_USER_ID" name="SESSION_USER_ID" value="${_MEMBER.SESSION_USER_ID}" /> 
						<input type="hidden" id="DEFAULT_LANGUAGE" name="DEFAULT_LANGUAGE" value="${_MEMBER.SESSION_DEFAULT_LANGUAGE}" /> 
						<input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${_MEMBER.SESSION_COMPANY_CD}" /> 
						<input type="hidden" id="OAPI_IEM_CD" name="OAPI_IEM_CD" value="${OAPI_IEM_CD}" /> 
						<input type="hidden" id="OAPI_IEM_REC_LIST_SN_ARRAY" name="OAPI_IEM_REC_LIST_SN_ARRAY" />
						<input type="hidden" id="TRG_TABLE_YN" name="TRG_TABLE_YN" />
						<input type="hidden" id="TARGET_INSERT_AT" name="TARGET_INSERT_AT" /> 
						<input type="hidden" id="P_PARA_KEY_01" name="P_PARA_KEY_01" value="${P_PARA_KEY_01}"/> 
						<input type="hidden" id="P_PARA_VAL_01" name="P_PARA_VAL_01" value="${P_PARA_VAL_01}"/> 
						<input type="hidden" id="P_PARA_KEY_02" name="P_PARA_KEY_02" value="${P_PARA_KEY_02}"/> 
						<input type="hidden" id="P_PARA_VAL_02" name="P_PARA_VAL_02" value="${P_PARA_VAL_02}"/> 
						<input type="hidden" id="P_PARA_KEY_03" name="P_PARA_KEY_03" value="${P_PARA_KEY_03}"/> 
						<input type="hidden" id="P_PARA_VAL_03" name="P_PARA_VAL_03" value="${P_PARA_VAL_03}"/> 
						<input type="hidden" id="P_PARA_KEY_04" name="P_PARA_KEY_04" value="${P_PARA_KEY_04}"/> 
						<input type="hidden" id="P_PARA_VAL_04" name="P_PARA_VAL_04" value="${P_PARA_VAL_04}"/> 
						<input type="hidden" id="P_PARA_KEY_05" name="P_PARA_KEY_05" value="${P_PARA_KEY_05}"/> 
						<input type="hidden" id="P_PARA_VAL_05" name="P_PARA_VAL_05" value="${P_PARA_VAL_05}"/> 
						<input type="hidden" id="P_PARA_KEY_06" name="P_PARA_KEY_06" /> 
						<input type="hidden" id="P_PARA_VAL_06" name="P_PARA_VAL_06" /> 
						<input type="hidden" id="P_PARA_KEY_07" name="P_PARA_KEY_07" /> 
						<input type="hidden" id="P_PARA_VAL_07" name="P_PARA_VAL_07" /> 
						<input type="hidden" id="P_PARA_KEY_08" name="P_PARA_KEY_08" /> 
						<input type="hidden" id="P_PARA_VAL_08" name="P_PARA_VAL_08" /> 
						<input type="hidden" id="P_PARA_KEY_09" name="P_PARA_KEY_09" /> 
						<input type="hidden" id="P_PARA_VAL_09" name="P_PARA_VAL_09" /> 
						<input type="hidden" id="P_PARA_KEY_10" name="P_PARA_KEY_10" /> 
						<input type="hidden" id="P_PARA_VAL_10" name="P_PARA_VAL_10" />
					</form>
					<table id="MMB002_11_grid_02"></table>
				</div>
			</div>
		</div>
		<div data-options="region:'center',border:false">
			<div id="MMB002_11_tabs_01" class="easyui-tabs" data-options="fit:true,showHeader:false,plain:true">
				<div title="1">
					<div class="easyui-layout" data-options="fit:true">
						<div data-options="region:'center',border:false" style="height: auto">
							<form id="MMB002_11_form_03" name="MMB002_11_form_03" method="post">
								<input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"> 
								<input type="hidden" id="OAPI_IEM_REC_LIST_SN" name="OAPI_IEM_REC_LIST_SN" value="${OAPI_IEM_REC_LIST_SN}">
							</form>
							<table id="MMB002_11_grid_03"></table>
						</div>
					</div>
				</div>
				<div title="2">
					<div class="easyui-layout" data-options="fit:true">
						<div data-options="region:'north',border:false,split:true" style="height: 180px;">
							<form id="MMB002_11_form_04" name="MMB002_11_form_04" method="post">
								<input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}" /> 
								<input type="hidden" id="OAPI_IEM_REC_LIST_SN" name="OAPI_IEM_REC_LIST_SN" value="${OAPI_IEM_REC_LIST_SN}" />
							</form>
							<table id="MMB002_11_grid_04"></table>
						</div>
						<div data-options="region:'center',border:false">
							<form id="MMB002_11_form_05" name="MMB002_11_form_04" method="post">
								<input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}" /> <input type="hidden" id="OAPI_IEM_REC_LIST_SN" name="OAPI_IEM_REC_LIST_SN" value="${OAPI_IEM_REC_LIST_SN}" />
							</form>
							<table id="MMB002_11_grid_05"></table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--//wrap -->
	<script type="text/javascript" src="/js/views/MM/POP/MM-B002_11.js"></script>
	<script type="text/javascript" src="/js/frame/common/yni.window.js"></script>
</body>
</html>
