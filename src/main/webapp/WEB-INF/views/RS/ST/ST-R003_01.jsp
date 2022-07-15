<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : ST-R003_01.jsp
  * @Description : 서비스 이력 조회
  * @Modification Information
  *
  * author YNI-Maker(이메일)
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "STR003_01_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>      
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <div class="easyui-layout" data-options="fit:true">
        <!-- 검색조건 -->
        <div data-options="region:'north',border:false" class="sch_box2">
            <div class="sch_boxIn2">
                <form id="STR003_01_form_01" method="POST">
                    <input type="hidden" id="SERVICE_HISTORY_ID" name="SERVICE_HISTORY_ID"/>
                    <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${_MEMBER.SESSION_COMPANY_CD}"/>
                    <table>
                        <caption>데이타 검색조건 설정</caption>
                        <colgroup>
                            <col width="130px" />
                            <col width="220px" />
                            <col width="110px" />
                            <col width="130px" />
                            <col width="120px"/>
                            <col width="400px"/>
                            <col width=";"/>
                        </colgroup>
                        <tbody>
                            <tr>
                                <th scope="row" class="point"><spring:message code="EXCUS,DATE"/></th>
                                <td>
                                    <input type="hidden" id="SEH_FORM_DATE" name="SEH_FORM_DATE" style="width:95px;"/> ~
                                    <input type="hidden" id="SEH_TO_DATE" name="SEH_TO_DATE" style="width:95px;"/>
                                </td>
                                <th scope="row" class="point"><spring:message code="TXT_RESLT_STAT"/></th>
                                <td>
                                    <input type="hidden" id="TRANS_STATUS" name="TRANS_STATUS" style="width:95px;"/>
                                </td>
                                <th scope="row"><spring:message code="SHCDT"/><%--조회조건--%></th>
                                <td>
                                    <input type="text" id="schKeyField" name="schKeyField" style="width:110px;"/> 
                                    <input type="text" id="schKeyWord" name="schKeyWord" search="SM7018_01.control.selectUseLogStatusList" style="width:130px;"/>
                                    <input type="text" id="schKeyLike" name="schKeyLike"style="width:80px;"/>
                                </td>
                                <td class="txt_R">
                                    <a href="javascript:STR003_01.control.selectServiceHistoryList();" class="easyui-linkbutton pop_sch"><spring:message code="SRCH"/></a>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
        <!-- //검색조건 -->
        <div data-options="region:'center',border:false" style="padding-top:12px;">
            <div class="easyui-layout" data-options="fit:true">
                <div data-options="region:'north',border:false,split:true"style="height:350px;">
                    <div class="easyui-layout" data-options="fit:true">
                        <div data-options="region:'north',border:false" class="h2_etc">
		                    <p class="h2"><spring:message code="CC_서비스 이력"/></p>
		                    <p class="h2_btn">
		                        <a href="javascript:STR003_01.control.executeRelayReBatch();" class="btnMainTransfer"><spring:message code="CC_재실행"/></a>
		                        <a href="javascript:STR003_01.excel.excelDownload_1();" class="btnExlDown"><spring:message code="EXCEL, DOWN"/></a>
		                    </p>
	                    </div>
	                    <div data-options="region:'center',border:false">
	                        <form id="STR003_01_form_03" method="POST">
                                <input type="hidden" id="COMPANY_CD" name="COMPANY_CD"/>
                                <input type="hidden" id="SERVICE_HISTORY_ID" name="SERVICE_HISTORY_ID"/>
                                <input type="hidden" id="SERVICE_ID" name="SERVICE_ID"/>
                                <input type="hidden" id="REQUEST_PARAM" name="REQUEST_PARAM"/>
                            </form>
		                    <table id="STR003_01_grid_01"> </table>
		                </div>
	                </div>
                </div>
	            <div data-options="region:'center',border:false" style="height:100%;">
	                <div class="easyui-layout" data-options="fit:true">
	                    <div data-options="region:'north',border:false" class="h2_etc">
	                        <p class="h2"><spring:message code="CC_서비스 계획 실행이력"/><%--인터페이스 이력--%></p>
	                        <p class="h2_btn">
	                            <a href="javascript:STR003_01.dialog.openDialog_1();" class="btnSelectClick"><spring:message code="DTA, DETIL"/><%--데이터 상세--%></a>
	                            <a href="javascript:STR003_01.excel.excelDownload_2();" class="btnExlDown"><spring:message code="EXCEL, DOWN"/></a>
	                        </p>
	                    </div>
		                <div data-options="region:'center',border:false">
		                    <form id="STR003_01_form_02" method="POST">
			                    <input type="hidden" id="SERVICE_HISTORY_ID" name="SERVICE_HISTORY_ID"/>
			                    <input type="hidden" id="COMPANY_CD" name="COMPANY_CD"/>
			                </form>
                            <table id="STR003_01_grid_02"> </table>
		                </div>
	                </div>
	            </div>
            </div>
        </div>
        
    </div>
    
    <script type="text/javascript" src="/js/views/RS/ST/ST-R003_01.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>