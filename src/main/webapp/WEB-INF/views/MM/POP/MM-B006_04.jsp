<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : MM-B006_04.jsp
  * @Description : 타업체 설정값 조회
  * @Modification Information
  *
  * author YNI-Maker(이메일)
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMB006_04_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'north',border:false" class="sch_box2">
            <div class="sch_boxIn2">
                <form id="MMB006_04_form_01" method="POST">
                    <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
                    <input type="hidden" id="USER_ID" name="USER_ID" value="${_MEMBER.SESSION_USER_ID}"/>
                    <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${_MEMBER.SESSION_COMPANY_CD}"/>
                    <input type="hidden" id="IMP_EXP_SE_CD" name="IMP_EXP_SE_CD" value="${IMP_EXP_SE_CD}"/><!-- 수입/수출 구분코드(수입:I, 수출:E) -->
                    <input type="hidden" id="DATA_SE" name="DATA_SE" value="${DATA_SE}"/><!-- 데이터 구분(인보이스:I, 패킹리스트:P) -->
                    <input type="hidden" id="CATEGORY_CD" name="CATEGORY_CD" value="${CATEGORY_CD}"/>
                    <table>
                        <caption><spring:message code="TXT_DATA_SEARCH_CONDITION"/><%--데이타 검색조건 설정--%></caption>
                        <colgroup>
                            <col width="100px" />
                            <col width="150px" />
                            <col width="100px" />
                            <col width="200px" />
                            <col width="100px" />
                            <col width="230px" />
                            <col width=";" />
                        </colgroup>
                        <tbody>
                            <tr>
                                <th scope="row"><spring:message code="CC_거래처 코드"/></th>
                                <td>
                                    <input type="text" id="BCNC_CD" name="BCNC_CD" search="MMB006_04.control.searchMainList" style="width:90px;"/>
                                    <span><a href="javascript:MMB006_04.dialog.openDialog_1();"><img src="/images/sample/btn_sch.gif"/></a></span>
                                </td>
                                <th scope="row"><spring:message code="CC_거래처명"/></th>
                                <td>
                                    <input type="text" id="BCNC_NM" name="BCNC_NM" search="MMB006_04.control.searchMainList" style="width:180px;"/>
                                </td>
                                <th scope="row"><spring:message code="CC_양식 설정명"/></th>
                                <td>
                                    <input type="text" id="FORM_SETUP_NM" name="FORM_SETUP_NM" search="MMB006_04.control.searchMainList" style="width:180px;"/>
                                </td>
                                <td align="right">
                                    <a href="javascript:MMB006_04.control.searchMainList();" class="easyui-linkbutton pop_sch">검색</a>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
        <div data-options="region:'west',border:false,split:true" style="width:477px;padding-top:12px;">
            <div class="easyui-layout" data-options="fit:true">
                <div data-options="region:'north',border:false" class="h2_etc">
                    <p class="h2"><spring:message code="CC_거래처"/></p>
                    <p class="h2_btn">
                        <a href="javascript:MMB006_04.excel.excelDownload_1();" class="btnExlDown"><spring:message code="BTN_EXCEL"/><%--엑셀다운로드--%></a>
                    </p>
                </div>
                <div data-options="region:'center',border:false">
                    <table id="MMB006_04_grid_01"> </table>
                </div>
            </div>
        </div>
        <div data-options="region:'center',border:false" style="padding-top:12px;">
            <div class="easyui-layout" data-options="fit:true">
                <div data-options="region:'north',border:false" class="h2_etc">
                    <p class="h2"><spring:message code="CC_설정값"/></p>
                    <p class="h2_btn">
                        <a href="javascript:MMB006_04.control.applySettingInfo();" class="btnApply"><spring:message code="CC_적용"/><%--적용--%></a>
                        <a href="javascript:MMB006_04.excel.excelDownload_2();" class="btnExlDown"><spring:message code="BTN_EXCEL"/><%--엑셀다운로드--%></a>
                    </p>
                </div>
                <div data-options="region:'center',border:false">
                    <form id="MMB006_04_form_02" method="POST">
	                    <input type="hidden" id="USER_ID" name="USER_ID" value="${_MEMBER.SESSION_USER_ID}"/>
	                    <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${_MEMBER.SESSION_COMPANY_CD}"/>
	                    <input type="hidden" id="IMP_EXP_SE_CD" name="IMP_EXP_SE_CD" value="${IMP_EXP_SE_CD}"/><!-- 수입/수출 구분코드(수입:I, 수출:E) -->
	                    <input type="hidden" id="DATA_SE" name="DATA_SE" value="${DATA_SE}"/><!-- 데이터 구분(인보이스:I, 패킹리스트:P) -->
	                    <input type="hidden" id="CATEGORY_CD" name="CATEGORY_CD" value="${CATEGORY_CD}"/>
	                    <input type="hidden" id="ORG_BCNC_CD" name="ORG_BCNC_CD" value="${ORG_BCNC_CD}"/><!-- 설정할 원본 거래처 코드 -->
	                    <input type="hidden" id="FORM_SETUP_SN" name="FORM_SETUP_SN"/>
	                    <input type="hidden" id="BCNC_CD" name="BCNC_CD"/>
                    </form>
                    <table id="MMB006_04_grid_02"> </table>
                </div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-B006_04.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>