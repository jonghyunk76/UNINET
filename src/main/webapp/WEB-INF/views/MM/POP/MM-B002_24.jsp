<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	/**
	* @Class Name : MM-B002_24.jsp
	* @Description : 검사검역/요건승인 조회
	* @Modification Information
	*
	* author YNI-Maker(이메일)
	* 
	* <notice>
	* 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMB002_24_dailog_01"으로 하셔야 합니다.
	*/
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'north',border:false" style="height:30px;">
            <div class="btn_R">
                <a href="javascript:dialog.handle.close(dialog.getObject('MMB002_24_dailog_01'));" class="btnPopClose"><spring:message code="CLOSE"/></a>
            </div>
        </div>	
		<div data-options="region:'center',border:false" style="padding-top: 4px;">
			<div class="easyui-layout" data-options="fit:true">
				<div data-options="region:'center',border:false">
					<div class="easyui-layout" data-options="fit:true">
						<div data-options="region:'north',border:false" class="sec_tit" style="height: 30px; overflow: hidden; padding-top: 8px;">
							<ul class="tab_area">
								<li id="MMB002_24_divMove01" class="on"><a href="javascript:MMB002_24.ui.divMove('1');"><b><spring:message code="CC_검사검역" /></b></a></li>
								<li id="MMB002_24_divMove02"><a href="javascript:MMB002_24.ui.divMove('2');"><b><spring:message code="CC_요건승인" /></b></a></li>
							</ul>
						</div>
						<div data-options="region:'center',border:false">
							<div id="MMB002_24_tabs_01" class="easyui-tabs" data-options="fit:true,showHeader:false,plain:true">
								<div title="1">
									<div class="easyui-layout" data-options="fit:true">
										<div data-options="region:'north',border:false" class="sch_box2">
											<div class="sch_boxIn2">
												<form id="MMB002_24_form_01" method="POST">
													<input type="hidden" id="USER_ID" name="USER_ID" value="${_MEMBER.SESSION_USER_ID}" />
													<input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${_MEMBER.SESSION_COMPANY_CD}" />
													<input type="hidden" id="OAPI_IEM_CD" name="OAPI_IEM_CD" value="API004" />
													<input type="hidden" id="TARGET_INSERT_AT" name="TARGET_INSERT_AT" value="Y" />
													<input type="hidden" id="P_PARA_KEY_01" name="P_PARA_KEY_01" />
													<input type="hidden" id="P_PARA_VAL_01" name="P_PARA_VAL_01" />
													<input type="hidden" id="P_PARA_KEY_02" name="P_PARA_KEY_02" />
													<input type="hidden" id="P_PARA_VAL_02" name="P_PARA_VAL_02" />
													<input type="hidden" id="P_PARA_KEY_03" name="P_PARA_KEY_03" />
													<input type="hidden" id="P_PARA_VAL_03" name="P_PARA_VAL_03" />
													<table>
														<caption>
															<spring:message code="TXT_DATA_SEARCH_CONDITION" />
															<%--데이타 검색조건 설정--%>
														</caption>
														<colgroup>
															<col width="100px" />
															<col width="200px" />
															<col width="100px" />
															<col width="150px" />
															<col width=";" />
														</colgroup>
														<tbody>
															<tr>
																<th scope="row">
																	<spring:message code="CC_검사검역기관" />
																</th>
																<td>
																	<input type="text" id="INSCQUANITT" name="INSCQUANITT" style="width: 180px" />
																</td>
																<th scope="row">
																	<spring:message code="CC_통지번호/차수" />
																</th>
																<td>
																	<input type="text" id="NTFCNO" name="NTFCNO" class="easyui-validatebox" required="true" value="" style="width: 70px;" />
																	/
																	<input type="text" id="DGCNT" name="DGCNT" class="easyui-validatebox" required="true" value="" style="width: 20px;" />
																</td>
																<td align="right">
																	<a href="javascript:MMB002_24.control.selectOpenAPICallSelectList('API004');" class="easyui-linkbutton pop_sch" id="MMB002_24_searchBtn"><spring:message code="SRCH" /></a>
																</td>
															</tr>
														</tbody>
													</table>
												</form>
											</div>
										</div>
										<div data-options="region:'center',border:false">
											<div class="easyui-layout" data-options="fit:true">
												<div data-options="region:'north',border:false,split:true" class="h2_etc" style="height: 200px;">
													<form id="MMB002_24_form_02" method="POST">
														<input type="hidden" id="USER_ID" name="USER_ID" value="${_MEMBER.SESSION_USER_ID}" />
														<input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${_MEMBER.SESSION_COMPANY_CD}" />
														<input type="hidden" id="OAPI_IEM_REC_LIST_SN" name="OAPI_IEM_REC_LIST_SN" value="${OAPI_IEM_REC_LIST_SN}" />
														<input type="hidden" id="OAPI_IEM_CD" name="OAPI_IEM_CD" value="API004" />
													</form>
													<table id="MMB002_24_grid_01"></table>
												</div>
												<div data-options="region:'center',border:false">
													<form id="MMB002_24_form_03" method="POST">
														<input type="hidden" id="USER_ID" name="USER_ID" value="${_MEMBER.SESSION_USER_ID}" />
														<input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${_MEMBER.SESSION_COMPANY_CD}" />
														<input type="hidden" id="OAPI_IEM_REC_LIST_SN" name="OAPI_IEM_REC_LIST_SN" value="${OAPI_IEM_REC_LIST_SN}" />
														<input type="hidden" id="OAPI_IEM_CD" name="OAPI_IEM_CD" value="API004" />
													</form>
													<table id="MMB002_24_grid_02"></table>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div title="2">
									<div class="easyui-layout" data-options="fit:true">
										<div data-options="region:'north',border:false" class="sch_box2">
											<div class="sch_boxIn2">
												<form id="MMB002_24_form_04" method="POST">
													<input type="hidden" id="USER_ID" name="USER_ID" value="${_MEMBER.SESSION_USER_ID}" />
													<input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${_MEMBER.SESSION_COMPANY_CD}" />
													<input type="hidden" id="OAPI_IEM_CD" name="OAPI_IEM_CD" value="API003" />
													<input type="hidden" id="TARGET_INSERT_AT" name="TARGET_INSERT_AT" value="Y" />
													<input type="hidden" id="P_PARA_KEY_01" name="P_PARA_KEY_01" />
													<input type="hidden" id="P_PARA_VAL_01" name="P_PARA_VAL_01" />
													<input type="hidden" id="P_PARA_KEY_02" name="P_PARA_KEY_02" />
													<input type="hidden" id="P_PARA_VAL_02" name="P_PARA_VAL_02" />
													<table>
														<caption>
															<spring:message code="TXT_DATA_SEARCH_CONDITION" />
															<%--데이타 검색조건 설정--%>
														</caption>
														<colgroup>
															<col width="100px" />
															<col width="100px" />
															<col width="100px" />
															<col width="150px" />
															<col width=";" />
														</colgroup>
														<tbody>
															<tr>
																<th scope="row">
																	<spring:message code="CC_수입수출" />
																</th>
																<td>
																	<input type="text" id="IMEXTPCD" name="IMEXTPCD" style="width: 60px" />
																</td>
																<th scope="row">
																	<spring:message code="CC_요건승인번호" />
																</th>
																<td>
																	<input type="text" id="REQAPRENO" name="REQAPRENO" class="easyui-validatebox" required="true" value="" style="width: 110px;" />
																</td>
																<td align="right">
																	<a href="javascript:MMB002_24.control.selectOpenAPICallSelectList('API003');" class="easyui-linkbutton pop_sch" id="MMB002_24_searchBtn"><spring:message code="SRCH" /></a>
																</td>
															</tr>
														</tbody>
													</table>
												</form>
											</div>
										</div>
										<div data-options="region:'center',border:false">
											<div class="easyui-layout" data-options="fit:true">
												<div data-options="region:'north',border:false,split:true" class="h2_etc" style="height: 200px;">
													<form id="MMB002_24_form_05" method="POST">
														<input type="hidden" id="USER_ID" name="USER_ID" value="${_MEMBER.SESSION_USER_ID}" />
														<input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${_MEMBER.SESSION_COMPANY_CD}" />
														<input type="hidden" id="OAPI_IEM_REC_LIST_SN" name="OAPI_IEM_REC_LIST_SN" value="${OAPI_IEM_REC_LIST_SN}" />
														<input type="hidden" id="OAPI_IEM_CD" name="OAPI_IEM_CD" value="API004" />
													</form>
													<table id="MMB002_24_grid_03"></table>
												</div>
												<div data-options="region:'center',border:false">
													<form id="MMB002_24_form_06" method="POST">
														<input type="hidden" id="USER_ID" name="USER_ID" value="${_MEMBER.SESSION_USER_ID}" />
														<input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${_MEMBER.SESSION_COMPANY_CD}" />
														<input type="hidden" id="OAPI_IEM_REC_LIST_SN" name="OAPI_IEM_REC_LIST_SN" value="${OAPI_IEM_REC_LIST_SN}" />
														<input type="hidden" id="OAPI_IEM_CD" name="OAPI_IEM_CD" value="API004" />
													</form>
													<table id="MMB002_24_grid_04"></table>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="/js/views/MM/POP/MM-B002_24.js"></script>
	<script type="text/javascript" src="/js/frame/common/yni.window.js"></script>
</body>
</html>