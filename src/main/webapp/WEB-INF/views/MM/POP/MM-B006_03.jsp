<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : MM-B006_03.jsp
  * @Description : 패킹리스트 업로드
  * @Modification Information
  *
  * author YNI-Maker(이메일)
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMB006_03_dailog_01"으로 하셔야 합니다.
  * [기능]
  * 1. 추출할 열범위 지정 기능(데이터 시작행에 1-99)
  * 2. 지정된 행에 필수여부를 지정하여, 데이터 시작행부터~마지막까지 지정한 후 데이터가 존재하는 열만 추출하는 기능 추가
  */
%>
<!DOCTYPE html>
<html>
<head>      
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'west',border:false,split:true" style="width:569px;">
            <div class="easyui-layout" data-options="fit:true">
                <div data-options="region:'north',border:false" class="h2_etc">
                    <p class="h2">1.<spring:message code="CC_엑셀 맵핑 설정"/></p>
                    <p class="h2_btn">
                        <a href="javascript:MMB006_03.control.initPageControl();" class="btnNewRegiste"><spring:message code="NEW_REGER"/></a>
                        <a href="javascript:MMB006_03.dialog.openDialog_2();" class="btnList" title="타업체 설정값 불러오기"><spring:message code="CC_불러오기"/></a>
                        <a href="javascript:MMB006_03.control.insertInvUpSetupInfo();" class="btnMainSave"><spring:message code="SAVE"/></a>
                        <a href="javascript:MMB006_03.control.deleteInvUpSetupInfo();" class="btnMainDelete"><spring:message code="DEL"/></a>
                    </p>
                </div>
                <div data-options="region:'center',border:false">
                   <div class="easyui-layout" data-options="fit:true">
                        <div data-options="region:'north',border:false" class="h2_etc">
                            <form id="MMB006_03_form_01" name="MMB006_03_form_01" method="post">
                                <input type="hidden" id="flag" name="flag" value="${flag}"/>
                                <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
                                <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
                                <input type="hidden" id="BCNC_CD" name="BCNC_CD" value="${BCNC_CD}"/>
                                <input type="hidden" name="CATEGORY_CD" id="CATEGORY_CD"/>
                                <input type="hidden" id="IMP_EXP_SE_CD" name="IMP_EXP_SE_CD" value="${IMP_EXP_SE_CD}"/><!-- 수입/수출 구분코드(수입:I, 수출:E) -->
                                <input type="hidden" id="DATA_SE" name="DATA_SE" value="${DATA_SE}"/><!-- 데이터 구분(인보이스:I, 패킹리스트:P) -->
                                <input type="hidden" name="FORM_SETUP_SN" id="FORM_SETUP_SN" value="${FORM_SETUP_SN}"/>
                                <input type="hidden" id="USER_ID" name="USER_ID" value="${USER_ID}"/>
                                <input type="hidden" id="gridData" name="gridData"/>
                                <table class="gridT" style="margin-bottom:0px;">
                                    <colgroup>
                                        <col width="100px" />
                                        <col width="" />
                                        <col width="100px" />
                                        <col width="" />
                                    </colgroup>
                                    <tbody>
                                        <tr>
                                            <th scope="row"><spring:message code="CC_업체명"/></th>
                                            <td colspan="3">
                                                <input type="text" id="BCNC_NM" name="BCNC_NM" value="${BCNC_NM}" class="easyui-validatebox input_readOnly" required="true" readonly style="width:98%;"/>
                                            </td>
                                        </tr>
                                        <tr style="height:27px;">
                                            <th scope="row"><spring:message code="CC_양식 설정명"/></th>
                                            <td colspan="3">
                                                <input type="text" name="FORM_SETUP_NM" id="FORM_SETUP_NM" value="${FORM_SETUP_NM}" class="easyui-validatebox" required="true" style="width:87%;"/>
                                                <span><a id="MMB006_03_href_01" href="javascript:void(0)" class="db_reg_list"></a></span>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th scope="row"><spring:message code="CC_시트번호"/></th>
                                            <td>
                                                <input type="text" name="SHEET_NO" id="SHEET_NO" value="${SHEET_NO}" class="easyui-validatebox" required="true" style="width:90%;"/>
                                            </td>
                                            <th scope="row"><spring:message code="CC_데이터 시작행"/></th>
                                            <td>
                                                <input type="text" name="BEGIN_ROW" id="BEGIN_ROW" value="${BEGIN_ROW}" class="easyui-validatebox" required="true" style="width:90%;"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th scope="row">
                                                <spring:message code="CC_합산항목"/>
                                                <span style="float:right;"><label><input type="checkbox" id="ALL_SSUM_AT" onclick="javascript:MMB006_03.event.allCheck();"/></label></span>
                                            </th>
                                            <td colspan="3">
                                                <label><input type="checkbox" id="QY_SSUM_AT" name="QY_SSUM_AT" value="Y"/>수량</label>
                                                <label><input type="checkbox" id="AMT_SSUM_AT" name="AMT_SSUM_AT" value="Y"/>금액</label>
                                                <label><input type="checkbox" id="NETWGHT_SSUM_AT" name="NETWGHT_SSUM_AT" value="Y"/>순중량</label>
                                                <label><input type="checkbox" id="TOT_WT_SSUM_AT" name="TOT_WT_SSUM_AT" value="Y"/>총중량</label>
                                                <label><input type="checkbox" id="PKNG_CO_SSUM_AT" name="PKNG_CO_SSUM_AT" value="Y"/>포장개수</label>
                                                <label><input type="checkbox" id="ADAMT_SSUM_AT" name="ADAMT_SSUM_AT" value="Y"/>가산금</label>
                                                <label><input type="checkbox" id="DDC_AMT_SSUM_AT" name="DDC_AMT_SSUM_AT" value="Y"/>공제금</label>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </form>
                        </div>
                        <div data-options="region:'center',border:false">
                            <table id="MMB006_03_grid_01"> </table>
                        </div>
                        <div data-options="region:'south',border:false" style="height:250px;padding:5px 0px 0px 5px;background-color:#efefef;">
                            <p>[맵핑정보 설정방법]</p>
                            <p style="padding-top:6px;">1. 시트번호: 파일 업로드 시 읽을 시트번호를 지정함</p>
                            <p style="padding-left:15px;"> - 여러 시트를 읽을 경우 쉼표로 구분해서 지정(예시:1,2,3)</p>
                            <p style="padding-top:6px;">2. 합산: 합산할 항목을 지정 시 <font color="blue">자재코드별</font>(필수항목)로 숫자를 합산함</p>
                            <p style="padding-left:15px;"> - 합산항목이 하나도 지정하지 않을 경우에는 추출된 값이 그대로 표시됨</p>
                            <p style="padding-left:15px;"> - 합산항목을 일부 지정 시 미지정된 항목은 자재코드별 첫 입력값이 표시됨</p>
                            <p style="padding-top:4px;">3. 항목: 고정된 행으로 시스템 관리자에 의해 추가할 수 있음</p>
                            <p style="padding-top:4px;">4. 열번호: 2번 엑셀 데이터의 A부터 AZ까지 1개 이상 입력이 가능함</p>
                            <p style="padding-left:15px;">옵션1) 문자열합치기: 더하기(+)로 구분하여 열번호에 입력(예시:A+B)</p>
                            <p style="padding-left:15px;">옵션2) 계산식: =로 시작하는 계산식을 포함한 열번호 입력(예시:=A+B*D)</p>
                            <p style="padding-left:15px;">옵션3) 함수식: func:로 시작하는 스크립트 코드 입력(열번호 지정시 앞에 $로 지정)</p>
                            <p style="padding-left:30px;"> - 문자열자르기 예시 : func:$A.substring(0,6)</p>
                            <p style="padding-left:30px;"> - 조건식 예시 : func:($A=="123")?"S":"P"</p>
                            <p style="padding-left:30px;"> - 문자열변경 예시 : func:$A.replace("ABC", "AAA")</p>
                            <p style="padding-top:4px;">5. 셀타입: 특정 문자열에서 필요한 타입의 데이터만 추출함</p>
                            <p style="padding-left:15px;">- 원본데이터(공백): 문자열 원문이 그대로 추출됨</p>
                            <p style="padding-left:15px;">- 문자열: 숫자를 제외한 모든 문자를 추출함</p>
                            <p style="padding-left:15px;">- 숫자: 소수점을 포함한 숫자를 추출함</p>
                            <p style="padding-left:15px;">- 날짜: 숫자형태의 날짜로 변환한 값를 추출함</p>
                            <p style="padding-left:15px;">- 통화코드: 문자열에서 통화코드를 추출함</p>
                            <p style="padding-left:15px;">- 국가코드: 문자열에서 국가명을 찾아 코드로 변환한 값을 추출함</p>
                            <p style="padding-left:15px;">- 인도조건: 문자열에서 인도조건을 추출함</p>
                            <p style="padding-left:15px;">- 숫자만: 문자열에서 숫자만 추출함</p>
                            <p style="padding-top:4px;">6. 필수여부: 필수로 지정된 열의 값이 모두 있는 행만 추출함</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div data-options="region:'center',border:false">
            <div class="easyui-layout" data-options="fit:true">
                <div data-options="region:'center',border:false">
                    <div class="easyui-layout" data-options="fit:true">
		                <div data-options="region:'north',border:false" class="h2_etc">
		                    <p class="h2">2.<spring:message code="CC_엑셀 데이터"/></p><font>* 셀을 더블클릭하면 셀 위치가 자동으로 입력됩니다. </font>
		                    <p class="h2_btn">
		                        <a href="javascript:MMB006_03.control.pasteColumn();" class="btnTextPaste"><spring:message code="클립보드 붙여넣기"/></a>
		                        <a href="javascript:MMB006_03.control.allClearColumn();" class="btnMainDelete"><spring:message code="전체 삭제"/></a>
		                    </p>
		                </div>
		                <div data-options="region:'center',border:false">
		                    <div class="easyui-layout" data-options="fit:true">
		                        <div data-options="region:'north',border:false" class="h2_etc">
		                            <form id="MMB006_03_form_02" name="MMB006_03_form_02" enctype="multipart/form-data" method="post">
		                                <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
		                                <input type="hidden" id="SHEET_NO" name="SHEET_NO"/>
		                                <input type="hidden" id="gridData" name="gridData"/>
		                                <table class="gridT" style="margin-bottom:0px;">
		                                    <colgroup>
		                                        <col width="100px" />
		                                        <col width="" />
		                                    </colgroup>
		                                    <tbody>
		                                        <tr>
		                                            <th scope="row"><spring:message code="BTN_EXCEL_UPLOAD"/></th>
		                                            <td colspan="3">
		                                                <input type="file" name="FILEUP" id="FILEUP" class="easyui-validatebox" required="true" style="width:87%;height:22px;"/>
		                                                <span style="float:right;padding-top:2px;">
		                                                    <a href="javascript:MMB006_03.control.executeExcelUploadFile();" class="btnPopFileImport"><spring:message code="CC_파일 불러오기"/></a>
		                                                </span>
		                                            </td>
		                                        </tr>
		                                    </tbody>
		                                </table>
		                            </form>
		                        </div>
		                        <div data-options="region:'center',border:false">
		                            <table id="MMB006_03_grid_02"> </table>
		                        </div>
		                    </div>
		                </div>
		            </div>
                </div>
                <div data-options="region:'south',border:false,split:true" style="height:350px;">
		            <div class="easyui-layout" data-options="fit:true">
		                <div data-options="region:'north',border:false" class="h2_etc">
		                    <p id="MMB006_03_span_01" class="h2">3.<spring:message code="CC_PPL 정보"/></p>
		                    <p class="h2_btn">
		                        <a href="javascript:MMB006_03.control.filterInvoiceData();" class="btnFilter"><spring:message code="CC_데이터추출"/></a>
		                        <a href="javascript:MMB006_03.control.insertPlUploadHist();" class="btnMainSave"><spring:message code="SAVE"/></a>
		                    </p>
		                </div>
		                <div data-options="region:'center',border:false">
		                    <form id="MMB006_03_form_04" name="MMB006_03_form_04" method="post">
                                <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
                                <input type="hidden" id="USER_ID" name="USER_ID" value="${USER_ID}"/>
                                <input type="hidden" id="IMP_BCNC_SETUP_SN" name="IMP_BCNC_SETUP_SN" value="${IMP_BCNC_SETUP_SN}"/>
                                <input type="hidden" id="DOC_STLE_SE" name="DOC_STLE_SE" value="${DOC_STLE_SE}"/>
                                <input type="hidden" id="gridData" name="gridData"/>
                                <input type="hidden" name="INVE_NO" id="INVE_NO" value="${INVE_NO}"/>
                                <input type="hidden" name="TAB_NM" id="TAB_NM"/>
                            </form>
		                    <table id="MMB006_03_grid_03"> </table>
		                    </div>
		                </div>
		            </div>
		        </div>
            </div>
        </div>
        <div style="display:none;">
            <form id="MMB006_03_form_00" name="MMB006_03_form_00" method="post">
                <textarea name="PASTE_TEXT1" id="PASTE_TEXT1"></textarea>
            </form>
        </div>
        <div style="display:none">
            <div id="MMB006_03_contents_01" style="width:250px;height:400px;border-right:1px solid #d4d4d4;border-bottom:1px solid #d4d4d4;">
                <div class="easyui-layout" data-options="fit:true">
                    <div data-options="region:'north',border:false" class="h2_etc" style="height:20px;background-color:#fff">
                        <p class="h2_sub"><spring:message code="CC_설정 리스트"/></p>
                    </div>
                    <div data-options="region:'center',border:false">
                        <form id="MMB006_03_form_03" name="MMB006_03_form_03" method="post">
                            <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
                            <input type="hidden" id="BCNC_CD" name="BCNC_CD" value="${BCNC_CD}"/>
                            <input type="hidden" id="IMP_EXP_SE_CD" name="IMP_EXP_SE_CD" value="${IMP_EXP_SE_CD}"/>
                            <input type="hidden" id="DATA_SE" name="DATA_SE" value="${DATA_SE}"/>
                        </form>
                        <table id="MMB006_03_grid_04"> </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-B006_03.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>