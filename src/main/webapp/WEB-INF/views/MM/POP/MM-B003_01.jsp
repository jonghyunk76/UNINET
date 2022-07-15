<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : MM-B003_01.jsp
  * @Description : 가격신고서 작성 화면
  * @Modification Information
  *
  * author YNI-Maker(이메일)
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMB003_01_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>      
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'north',border:false" class="h2_etc">
            <form id="MMB003_01_form_02" name="MMB003_01_form_02" method="post">
                <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
                <input type="hidden" id="IMP_DCLRT_SN" name="IMP_DCLRT_SN" value="${IMP_DCLRT_SN}"/> <!-- 수입신고서일련번호 -->
                <input type="hidden" id="IMP_BCNC_SETUP_SN" name="IMP_BCNC_SETUP_SN" value="${IMP_BCNC_SETUP_SN}"/><!-- 거래처 설정정보 -->
                <input type="hidden" id="STT_ODR" name="STT_ODR" value="${STT_ODR}"/>
            </form>
            <form id="MMB003_01_form_03" name="MMB003_01_form_03" method="post">
                <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
                <input type="hidden" id="IMP_DCLRT_SN" name="IMP_DCLRT_SN" value="${IMP_DCLRT_SN}"/> <!-- 수입신고서일련번호 -->
            </form>
            <div class="btn_R">
                <a href="javascript:MMB003_01.control.updateImpPrnoInfo();" class="btnSave"><spring:message code="SAVE"/></a><!-- 가격신고서 작성 -->
                <a href="javascript:MMB003_01.control.deleteImpPrnoInfo();" class="btnDelete" style="display:none;"><spring:message code="DEL"/></a>
                <a href="javascript:dialog.handle.close(dialog.getObject('MMB003_01_dailog_01'));" class="btnPopClose"><spring:message code="CLOSE"/></a>
            </div>
        </div>
        <div data-options="region:'center',border:false">
            <div class="easyui-layout" data-options="fit:true">
                <form id="MMB003_01_form_01" name="MMB003_01_form_01" method="post">
                    <div data-options="region:'north',border:false">
                        <input type="hidden" id="flag" name="flag"/>
                        <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
                        <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
                        <input type="hidden" id="USER_ID" name="USER_ID" value="${_MEMBER.SESSION_USER_ID}"/>
                        <input type="hidden" id="IMP_PRNO_SN" name="IMP_PRNO_SN"/> <!-- 가격신고서일련번호 -->
                        <input type="hidden" id="IMP_BCNC_SETUP_SN" name="IMP_BCNC_SETUP_SN"/>
                        <input type="hidden" id="STT_ODR" name="STT_ODR"/>
                        <input type="hidden" id="IMP_DCLRT_SN" name="IMP_DCLRT_SN"/> <!-- 수입신고서일련번호 -->
                        <input type="hidden" id="DOC_STLE_SE" name="DOC_STLE_SE" value="GOVCBR934"/> <!-- 가격신고서 -->
                        <input type="hidden" id="PRVS_PC_STT_RESN_SL" name="PRVS_PC_STT_RESN_SL"/>
                        <input type="hidden" id="M26_BFO_PRPOS" name="M26_BFO_PRPOS"/>
                        <input type="hidden" id="M26_BFO_PC_CALC_BASIS" name="M26_BFO_PC_CALC_BASIS"/>
                        <input type="hidden" id="STT_CSMH" name="STT_CSMH"/>
                        <input type="hidden" id="STT_KWA" name="STT_KWA"/>
                        <input type="hidden" name="YYYY" id="YYYY" value="${YYYY}"/>
                        <table class="dataT" style="margin-bottom:0px;">
                            <colgroup>
                                <col width="100px" />
                                <col width="" />
                                <col width="100px" />
                                <col width="" />
                                <col width="100px" />
                                <col width="" />
                                <col width="100px" />
                                <col width="" />
                            </colgroup>
                            <tbody>
                                <tr>
                                    <th scope="row"><spring:message code="CC_전송구분"/></th>
                                    <td>
                                        <input name="TRNS_SE" id="TRNS_SE" style="width:138px;"/>
                                    </td>
                                    <th scope="row"><spring:message code="CC_결정방법코드"/></th>
                                    <td>
                                        <input name="M26_BFO_TATA_MTH" id="M26_BFO_TATA_MTH" class="readOnly" readonly style="width:95%;"/>
                                    </td>
                                    <th scope="row"><spring:message code="CC_서식구분"/></th>
                                    <td colspan="3">
                                        <input name="PRNO_FOT_SE" id="PRNO_FOT_SE" style="width:230px;"/>
                                    </td>
                                </tr>
                                <tr>
                                    <th scope="row"><spring:message code="CC_접수일자"/></th>
                                    <td>
                                        <input name="RCE_DE" id="RCE_DE" style="width:95px;"/>
                                    </td>
                                    <th scope="row"><spring:message code="CC_전송결과"/></th>
                                    <td>
                                        <input name="TRNS_RELT" id="TRNS_RELT" style="width:95%;"/>
                                    </td>
                                    <th scope="row"><spring:message code="CC_수신결과"/></th>
                                    <td colspan="3">
                                        <input name="REC_RELT" id="REC_RELT" style="width:97%;"/>
                                    </td>
                                </tr>
                                <tr>
                                    <th scope="row"><spring:message code="CC_가격신고서(A)"/></th>
                                    <td colspan="7">
                                        <div style="font-weight:bold;">
                                            <label style="color:red">과세가격 결정방법을 선택하세요.</label>
                                            <label> A타입서식 : 제1방법, B타입서식 : 제2방법~6방법</label>
                                        </div>
                                        <div>
                                            <input name="PRNO_FOT_MTH_CD" id="PRNO_FOT_MTH_CD" style="width:800px;"/>
                                        </div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div data-options="region:'center',border:false" style="padding-top:9px;">
                        <div class="easyui-layout" data-options="fit:true">
	                        <div data-options="region:'north',border:false" class="h2_etc">
	                            <p class="h2"><spring:message code="CC_가격신고서 내용"/></p>
	                            <p class="h2_btn">
			                        <a href="javascript:MMB003_01.ui.allToggleOpen();" class="btnFolderOpen"><spring:message code="CC_모두 펴기"/></a>
			                        <a href="javascript:MMB003_01.ui.allToggleClose();" class="btnFolderClose"><spring:message code="CC_모두 접기"/></a>
			                    </p>
	                        </div>
	                        <div data-options="region:'center',border:false" style="border:1px solid #c7cbce;">
		                        <table class="surveyT" style="margin-bottom:0px;">
		                            <colgroup>
		                                <col width="100px" />
		                                <col width="" />
		                                <col width="100px" />
		                                <col width="" />
		                                <col width="130px" />
		                                <col width="" />
		                                <col width="180px" />
		                                <col width="110px" />
		                            </colgroup>
		                            <tbody>
		                                <tr>
		                                    <th scope="row" colspan="4">
		                                        <label>1.납세의무자 상호 및 사업자등록번호(가격신고자)</label>
		                                        <div>
		                                            <input type="text" name="PLFTP_MTLTY" id="PLFTP_MTLTY" class="easyui-validatebox" required="true" style="width:200px;"/>
		                                            <a href="javascript:MMB003_01.dialog.openDialog_1()"><img src="/images/sample/btn_sch.gif"/></a>
		                                            <input type="text" name="PLFTP_IDNU" id="PLFTP_IDNU" class="easyui-validatebox" required="true" style="width:100px;"/>
		                                            <input name="PLFTP_IDNU_SE_SL" id="PLFTP_IDNU_SE_SL" style="width:120px;"/>
		                                        </div>
		                                        <div>
		                                            <label>* 수입신고번호</label>
		                                            <input type="hidden" id="STT_NO" name="STT_NO"/> <!-- 아래 입력된 수입신고번호를 조합해서 하나로 만들 것:관세사부호(5)+년도(2)+일련번호(7)+구분(1) -->
		                                            <input type="text" id="APLC_CBOA_APPN"  name="APLC_CBOA_APPN" class="easyui-validatebox search_readOnly" readonly validType="['minlength[5]','maxlength[5]']" style="width:60px;text-align:center;"/>
		                                            <input type="text" id="SN_YY" name="SN_YY" class="easyui-validatebox search_readOnly" readonly validType="['minlength[2]','maxlength[2]']" style="width:30px;text-align:center;"/>
		                                            <input type="text" id="IMPDEC_SN"  name="IMPDEC_SN"class="easyui-validatebox search_readOnly" readonly validType="['minlength[6]','maxlength[6]']" style="width:80px;text-align:center;"/>
		                                            <input type="text" id="IMPDEC_TYPE"  name="IMPDEC_TYPE"class="easyui-validatebox search_readOnly" readonly validType="['minlength[1]','maxlength[1]']" style="width:20px;text-align:center;"/>
		                                        </div>
		                                    </th>
		                                    <th scope="row" colspan="4">
		                                        <label>2.판매자(수입신고서상 공급자와 다른 경우에만 기재)</label>
		                                        <div>
		                                            <input type="text" name="SELER_COMPANY_NM" id="SELER_COMPANY_NM" placeholder="회사명 기재" style="width:99%;"/>
		                                            <input type="text" name="SELER_ADRES" id="SELER_ADRES" placeholder="주소 기재" style="width:99%;"/>
		                                            <input type="text" name="SELER_RPRS" id="SELER_RPRS" placeholder="대표자 성명 기재" style="width:99%;"/>
		                                        </div>
		                                    </th>
		                                </tr>
		                                <tr>
		                                    <th scope="row" colspan="4">
		                                        <a href="javascript:MMB003_01.ui.toggleMove('3');">
		                                            <label>3.구매자(납세의무자와 다른 경우에만 기재)</label> 
		                                        <label id="MMB003_01_label_03" style="color:blue">▼</label></a>
		                                        <div id="MMB003_01_div_03" style="display:none;">
		                                            <input type="text" name="PURSR_COMPANY_NM" id="PURSR_COMPANY_NM" placeholder="회사명 기재" style="width:99%;"/>
		                                            <input type="text" name="PURSR_ADRES" id="PURSR_ADRES" placeholder="주소 기재" style="width:99%;"/>
		                                            <input type="text" name="PURSR_RPRS" id="PURSR_RPRS" placeholder="대표자 성명과 전화번호 기재" style="width:99%;"/>
		                                        </div>
		                                    </th>
		                                    <th scope="row" colspan="4" rowspan="4">
		                                        <label>* 세관기재란(심사담당 직원의 특이사항 기재)</label>
		                                        <div>
		                                            <textarea name="CSMH_STA_LNE" id="CSMH_STA_LNE" style="height:130px;width:99%;"></textarea>
		                                        </div>
		                                    </th>
		                                </tr>
		                                <tr>
		                                    <th scope="row" colspan="4">
		                                        <label>4.송품장번호와 발행일</label>
		                                        <div>
		                                            <input type="text" name="INVC_NO" id="INVC_NO" style="width:300px;"/>
		                                            <input name="INVC_ISU_DE" id="INVC_ISU_DE" style="width:95px;"/>
		                                        </div>
		                                    </th>
		                                </tr>
		                                <tr>
		                                    <th scope="row" colspan="4">
		                                        <label>5.계약번호와 계약일</label>
		                                        <div>
		                                            <input type="text" name="CNR_NO" id="CNR_NO" style="width:300px;"/>
		                                            <input name="CNR_DE" id="CNR_DE" style="width:95px;"/>
		                                        </div>
		                                    </th>
		                                </tr>
		                                <tr>
		                                    <th scope="row" colspan="4">
		                                        <label>6.구매주문서(Purchase Order)번호와 주문일</label>
		                                        <div>
		                                            <input type="text" name="PURC_ORR_NO" id="PURC_ORR_NO" style="width:300px;"/>
		                                            <input name="PURC_ORR_DE" id="PURC_ORR_DE" style="width:95px;"/>
		                                        </div>
		                                    </th>
		                                </tr>
		                                <tr id="MMB003_01_typeA_01" style="display:none;">
		                                    <td colspan="8" style="padding:0px;">
		                                        <table style="width: 100%;">
				                                    <colgroup>
				                                        <col width="100px" />
				                                        <col width="" />
				                                        <col width="100px" />
				                                        <col width="" />
				                                        <col width="130px" />
				                                        <col width="" />
				                                        <col width="180px" />
				                                        <col width="110px" />
				                                    </colgroup>
				                                    <tbody>
				                                        <tr>
				                                            <th scope="row" colspan="6">
				                                                <label>7.(a)구매자와 판매자는 관세법 시행령 제32조 제1항중 특수관계에 해당합니까?</label><br>
				                                                <label style="padding-left:30px;">(해당하지 않으면, (b),(c),(d),(e)는 기재하지 마세요)</label>
				                                            </th>
				                                            <th scope="row">
				                                                <span style="float:right">
				                                                <label>(a) </label><input type="text" name="M1_AFO_07_A_NO" id="M1_AFO_07_A_NO" style="width:120px;"/>
				                                                </span>
				                                            </th>
				                                            <th scope="row"><label> </label></th>
				                                        </tr>
				                                        <tr>
				                                            <th scope="row" colspan="6">
				                                                <a href="javascript:MMB003_01.ui.toggleMove('7b');">
				                                                <label style="padding-left:13px;">(b)질문7 (a)에서 특수관계에 해당한다면 관세법 시행령 제23조 제1항중 어느 특수관계에 해당합니까?</label>
				                                                <label id="MMB003_01_label_07b" style="color:blue">▼</label></a>
				                                                <div id="MMB003_01_div_07b" style="display:none;padding-left:30px;">
				                                                    <ol>
				                                                        <li>① 구매자와 판매자가 상호 사업상의 임원 또는 관리자인 경우</li>
				                                                        <li>② 구매자와 판매자가 상호 법률상의 동업자인 경우</li>
				                                                        <li>③ 구매자와 판매자가 고용관계에 있는 경우</li>
				                                                        <li>④ 특정인이 구매자 및 판매자의 의결권 있는 주식을 직접 또는 간접으로 5퍼센트 이상 소유하거나 관리하는 경우</li>
				                                                        <li>⑤ 구매자 및 판매자 중 일방이 상대방에 대하여 법적으로 또는 사실상으로 지시나 통제를 할 수 있는 위치에 있는 등 일방이 상대방을 직접 또는 간접으로 지배를 받는 경우</li>
				                                                        <li>⑥ 구매자 및 판매자가 동일한 제3자에 의하여 직접 또는 간접으로 지배를 받는 경우</li>
				                                                        <li>⑦ 구매자 및 판매자가 동일한 제3자를 직접 또는 간접으로 공동지배하는 경우</li>
				                                                        <li>⑧ 구매자와 판매자가 국세기본법 시행령 제1조의2제1항 각 호의 어느 하나에 해당하는 친족관계에 있는 경우</li>
				                                                    </ol>
				                                                </div>
				                                            </th>
				                                            <th scope="row">
				                                                <span style="float:right">
				                                                <label>(b) </label><input type="text" name="M1_AFO_07_B_NO" id="M1_AFO_07_B_NO" style="width:120px;"/>
				                                                </span>
				                                            </th>
				                                            <th scope="row"><label> [① ~ ⑧ 택1]</label></th>
				                                        </tr>
				                                        <tr>
				                                            <th scope="row" colspan="6">
				                                                <label style="padding-left:13px;">(c)특수관계가 수입물품의 가격 결정에 영향을 미쳤습니까?</label>
				                                            </th>
				                                            <th scope="row">
				                                                <span style="float:right">
				                                                <label>(c) </label><input type="text" name="M1_AFO_07_C_AT" id="M1_AFO_07_C_AT" style="width:120px;"/>
				                                                </span>
				                                            </th>
				                                            <th scope="row">
				                                                <label> </label>
				                                            </th>
				                                        </tr>
				                                        <tr>
				                                            <th scope="row" colspan="6">
				                                                <label style="padding-left:13px;">(d)거래가격이 관세법시행규칙 제5조의 비교가격에 근접합니까?(선택적 기재)</label>
				                                            </th>
				                                            <th scope="row">
				                                                <span style="float:right">
				                                                <label>(d) </label><input type="text" name="M1_AFO_07_D_AT" id="M1_AFO_07_D_AT" style="width:120px;"/>
				                                                </span>
				                                            </th>
				                                            <th scope="row"><label> </label></th>
				                                        </tr>
				                                        <tr>
				                                            <th scope="row" colspan="6">
				                                                <a href="javascript:MMB003_01.ui.toggleMove('7e');">
				                                                <label style="padding-left:13px;">(e)특수관계자간 거래시 수입물품의 가격결정방법은 어느 것입니까?</label>
				                                                <label id="MMB003_01_label_07e" style="color:blue">▼</label></a>
				                                                <div id="MMB003_01_div_07e" style="display:none;padding-left:30px;">
				                                                    <ol>
				                                                        <li>① 비교가능 제3자가격법</li>
				                                                        <li>② 재판매가격법</li>
				                                                        <li>③ 원가가산법</li>
				                                                        <li>④ 이익분할법</li>
				                                                        <li>⑤ 거래순이익률법(영업이익률)</li>
				                                                        <li>⑥ 거래순이익률법(총원가가산율)</li>
				                                                        <li>⑦ 거래순이익률법(Berry Ratio)</li>
				                                                        <li>⑧  기타(<span><input type="text" id="M1_AFO_07_E_ETC" name="M1_AFO_07_E_ETC" style="width:80%;"/></span>)</li>
				                                                    </ol>
				                                                </div>
				                                            </th>
				                                            <th scope="row">
				                                                <span style="float:right">
				                                                <label>(e) </label><input type="text" name="M1_AFO_07_E_NO" id="M1_AFO_07_E_NO" style="width:120px;"/>
				                                                </span>
				                                            </th>
				                                            <th scope="row"><label> [① ~ ⑧ 택1]</label></th>
				                                        </tr>
				                                        <tr>
				                                            <th scope="row" colspan="6">
				                                                <a href="javascript:MMB003_01.ui.toggleMove('8a');">
				                                                <label>8.(a)수입물품의 처분 또는 사용에 있어서 다음 각호 이외의 제한이 있는가?</label>
				                                                <label id="MMB003_01_label_08a" style="color:blue">▼</label></a>
				                                                <div id="MMB003_01_div_08a" style="display:none;padding-left:30px;">
				                                                    <ol>
				                                                        <li>- 수입국의 법령에 의한 강제 또는 의무 이행</li>
				                                                        <li>- 상품판매 지역의 제한</li>
				                                                        <li>- 상품가격에 실직적으로 영향을 미치지 아니하는 제한</li>
				                                                    </ol>
				                                                </div>
				                                            </th>
				                                            <th scope="row">
				                                                <span style="float:right">
				                                                <label>(a) </label><input type="text" name="M1_AFO_08_A_AT" id="M1_AFO_08_A_AT" style="width:120px;"/>
				                                                </span>
				                                            </th>
				                                            <th scope="row"><label> </label></th>
				                                        </tr>
				                                        <tr>
				                                            <th scope="row" colspan="6">
				                                                <label style="padding-left:13px;">(b)상품가격 이외에 판매 또는 가격과 관련한 조건 또는 사정이 있습니까?</label><br>
				                                                <label style="padding-left:30px;">(만일 위 질문의 답변에 "예"라면 상세한 정보를 별도 제출하시오)</label>
				                                            </th>
				                                            <th scope="row">
				                                                <span style="float:right">
				                                                <label>(b) </label><input type="text" name="M1_AFO_08_B_AT" id="M1_AFO_08_B_AT" style="width:120px;"/>
				                                                </span>
				                                            </th>
				                                            <th scope="row"><label> </label></th>
				                                        </tr>
				                                        <tr>
				                                            <th scope="row" colspan="6">
				                                                <label>9.(a)수입물품의 거래조건으로 직접 또는 간접 지급되었으나 실제지급금액에 포함되지 아니한 로열티나 권리사용료가 있습니까?</label><br>
				                                            </th>
				                                            <th scope="row">
				                                                <span style="float:right">
				                                                <label>(a) </label><input type="text" name="M1_AFO_09_A_AT" id="M1_AFO_09_A_AT" style="width:120px;"/>
				                                                </span>
				                                            </th>
				                                            <th scope="row"><label> </label></th>
				                                        </tr>
				                                        <tr>
				                                            <th scope="row" colspan="6">
				                                                <label style="padding-left:13px;">(b)수입물품의 사용 또는 재판매 수익의 일부가 직접 또는 간접으로 판매자에게 귀속됩니까?</label><br>
				                                                <label style="padding-left:30px;">(만일 위 질문의 답변에"예"라면 상세한 조건들과 영향의 환산 금액을 질문15번,16번에 기재하시오)</label>
				                                            </th>
				                                            <th scope="row">
				                                                <span style="float:right">
				                                                <label>(b) </label><input type="text" name="M1_AFO_09_B_AT" id="M1_AFO_09_B_AT" style="width:120px;"/>
				                                                </span>
				                                            </th>
				                                            <th scope="row"><label> </label></th>
				                                        </tr>
				                                    </tbody>
				                                </table>
		                                    </td>
		                                </tr>
		                                <tr id="MMB003_01_typeB_01" style="display:none;">
		                                    <td colspan="8" style="padding:0px;">
		                                        <table style="width: 100%;">
                                                    <colgroup>
                                                        <col width="100px" />
                                                        <col width="" />
                                                        <col width="100px" />
                                                        <col width="" />
                                                        <col width="130px" />
                                                        <col width="" />
                                                        <col width="180px" />
                                                        <col width="110px" />
                                                    </colgroup>
                                                    <tbody>
                                                        <tr>
                                                            <th scope="row" colspan="8">
                                                                <label>7.수입물품의 관세평가방법</label>
                                                            </th>
                                                        </tr>
                                                        <tr>
                                                            <th scope="row" colspan="8">
                                                                <label style="padding-left:13px;color:blue">* 상단의 가격신고서 항목에서 과세가격 결정방법을 선택하십시요.</label>
                                                            </th>
                                                        </tr>
                                                        <tr>
                                                            <th scope="row" colspan="8">
                                                                <label>8.제4(B)방법 적용 시에만 해당 : 잠정 90일 이내 결정되는 관세의 과세가격 추정치를 기재하시오.</label>
                                                            </th>
                                                        </tr>
                                                        <tr>
                                                            <th scope="row" colspan="8" style="padding-left:16px;">
                                                                <input type="text" id="M26_BFO_TAXT_PC_ESTI" name="M26_BFO_TAXT_PC_ESTI" class="easyui-validatebox" validType="['maxlength[18]']" style="width:99%;"/>
                                                            </th>
                                                        </tr>
                                                        <tr>
                                                            <th scope="row" colspan="8">
                                                                <label>9.신고하는 관세의 과세가격을 뒷받침하는 증거서류, 또는 제4(B)방법 적용 시는 수입 후 90일 이내 제출할 증명자료를 기술하시오.(예시 : 세관심사 시 과세가격 산출에 사용되는 자료 등)</label>
                                                            </th>
                                                        </tr>
                                                        <tr>
                                                            <th scope="row" colspan="8" style="padding-left:25px;">
                                                                <label>(a) </label><input type="text" id="M26_BFO_PROOF_DTA_01" name="M26_BFO_PROOF_DTA_01" class="easyui-validatebox" validType="['maxlength[50]']" style="width:96%;"/><br>
                                                                <label>(b) </label><input type="text" id="M26_BFO_PROOF_DTA_02" name="M26_BFO_PROOF_DTA_02" class="easyui-validatebox" validType="['maxlength[50]']" style="width:96%;"/>
                                                            </th>
                                                        </tr>
                                                    </tbody>
                                                </table>
		                                    </td>
		                                </tr>
                                        <tr>
                                            <th scope="row" colspan="8">
                                                <a href="javascript:MMB003_01.ui.toggleMove('10');">
                                                <label>10.잠정가격신고의 경우</label>
                                                <label id="MMB003_01_label_010" style="color:blue">▼</label></a>
                                                <span style="padding-left:30px;">
                                                <label><input type="checkbox" name="" id="" value="Y"/>잠정가산금액 로열티 반영</label>
                                                </span>
                                                <div id="MMB003_01_div_010" style="display:none;">
                                                    <table class="innerT" style="width: 100%;">
					                                    <colgroup>
					                                        <col width="60px;" />
					                                        <col width="120px;" />
					                                        <col width=";" />
                                                            <col width="180px;" />
                                                            <col width=";" />
                                                            <col width="60px;" />
                                                            <col width="120px;" />
                                                            <col width=";" />
					                                    </colgroup>
					                                    <tbody>
		                                                    <tr>
					                                            <th scope="row" colspan="2">(a)잠정가격신고번호</th>
					                                            <th scope="row" colspan="2">
					                                                <input type="hidden" id="PRVS_PC_STT_NO" name="PRVS_PC_STT_NO"/><!-- 아래 4가지 정보를 조합해서 1개의 잠정가격신고번호를 만들것:세관(3) + 과 (2) + 년도 (2)  -->
					                                                <input type="text" id="PRVS_PC_STT_CSMH" name="PRVS_PC_STT_CSMH" class="easyui-validatebox" validType="['minlength[3]','maxlength[3]']" style="width:40px;text-align:center;"/>
					                                                <input type="text" id="PRVS_PC_STT_KWA" name="PRVS_PC_STT_KWA" class="easyui-validatebox" validType="['minlength[2]','maxlength[2]']" style="width:30px;text-align:center;"/>
					                                                <input type="text" id="STT_YYYY" name="STT_YYYY" class="easyui-validatebox" validType="['minlength[2]','maxlength[2]']" style="width:30px;text-align:center;"/>
					                                            </th>
					                                            <th scope="row" colspan="3">(b)잠정가산율</th>
                                                                <th scope="row" colspan="2">
                                                                    <input type="text" name="PRVS_ADDI_RT" id="PRVS_ADDI_RT" class="easyui-numberbox easyui-validatebox" validType="['floatformat[5,2]']"  style="width:100px;"/> <label>%</label>
                                                                </th>
					                                        </tr>
					                                        <tr>
                                                                <th scope="row" colspan="2">(c)잠정가산되어야 할 금액</th>
                                                                <th scope="row" colspan="2">
                                                                    <input type="text" name="PRVS_ADDI_AMT" id="PRVS_ADDI_AMT" class="easyui-numberbox easyui-validatebox" validType="['integerformat[12]']" style="width:200px;"/> <label>원</label>
                                                                </th>
                                                                <th scope="row" colspan="3">(d)가격확정예정시기(분할확정시기)</th>
                                                                <th scope="row" colspan="2">
                                                                    <input type="text" name="PC_DCSN_PREA_ERA_PARN_ERA" id="PC_DCSN_PREA_ERA_PARN_ERA" style="width:95px;"/>
                                                                </th>
                                                            </tr>
                                                            <tr>
                                                                <th scope="row" colspan="2">(e)관련수입거래 계약시간</th>
                                                                <th scope="row" colspan="2">
                                                                    <input type="text" name="RELA_IMP_DEAL_CNR_END_DE" id="RELA_IMP_DEAL_CNR_END_DE" style="width:95px;"/>
                                                                </th>
                                                                <th scope="row" colspan="5">(f)잠정가격신고 사유(체크:Y/미체크:N)</th><!-- 아래 17가지 체크박스에서 체크된 값에 대해 cc_imp_prvd_resn의 부호과 데이터를 입력하고 cc_imp_prno.PRVS_PC_STT_RESN_SL 컬럼에 부호를 등록할 것 -->
                                                            </tr>
					                                        <tr>
                                                                <th scope="row"></th>
                                                                <th scope="row">수수료</th>
                                                                <th scope="row">
                                                                    <input type="checkbox" name="PRVS_PC_STT_RESN_SL_1" id="PRVS_PC_STT_RESN_SL_1" value="1" style="width:30px;"/>
                                                                </th>
                                                                <th scope="row">중계료</th>
                                                                <th scope="row" colspan="2">
                                                                    <input type="checkbox" name="PRVS_PC_STT_RESN_SL_2" id="PRVS_PC_STT_RESN_SL_2" value="2" style="width:30px;"/>
                                                                </th>
                                                                <th scope="row">용기비용</th>
                                                                <th scope="row">
                                                                    <input type="checkbox" name="PRVS_PC_STT_RESN_SL_3" id="PRVS_PC_STT_RESN_SL_3" value="3" style="width:30px;"/>
                                                                </th>
                                                            </tr>
                                                            <tr>
                                                                <th scope="row"></th>
                                                                <th scope="row">포장노무비</th>
                                                                <th scope="row">
                                                                    <input type="checkbox" name="PRVS_PC_STT_RESN_SL_4" id="PRVS_PC_STT_RESN_SL_4" value="4" style="width:30px;"/>
                                                                </th>
                                                                <th scope="row">포장자재비</th>
                                                                <th scope="row" colspan="2">
                                                                    <input type="checkbox" name="PRVS_PC_STT_RESN_SL_5" id="PRVS_PC_STT_RESN_SL_5" value="5" style="width:30px;"/>
                                                                </th>
                                                                <th scope="row">생산지원비용</th>
                                                                <th scope="row">
                                                                    <input type="checkbox" name="PRVS_PC_STT_RESN_SL_6" id="PRVS_PC_STT_RESN_SL_6" value="6" style="width:30px;"/>
                                                                </th>
                                                            </tr>
                                                            <tr>
                                                                <th scope="row"></th>
                                                                <th scope="row">권리사용료</th>
                                                                <th scope="row">
                                                                    <input type="checkbox" name="PRVS_PC_STT_RESN_SL_7" id="PRVS_PC_STT_RESN_SL_7" value="7" style="width:30px;"/>
                                                                </th>
                                                                <th scope="row">사후귀속이익</th>
                                                                <th scope="row" colspan="2">
                                                                    <input type="checkbox" name="PRVS_PC_STT_RESN_SL_8" id="PRVS_PC_STT_RESN_SL_8" value="8" style="width:30px;"/>
                                                                </th>
                                                                <th scope="row">보험료</th>
                                                                <th scope="row">
                                                                    <input type="checkbox" name="PRVS_PC_STT_RESN_SL_9" id="PRVS_PC_STT_RESN_SL_9" value="9" style="width:30px;"/>
                                                                </th>
                                                            </tr>
                                                            <tr>
                                                                <th scope="row"></th>
                                                                <th scope="row">운임</th>
                                                                <th scope="row">
                                                                    <input type="checkbox" name="PRVS_PC_STT_RESN_SL_10" id="PRVS_PC_STT_RESN_SL_10" value="10" style="width:30px;"/>
                                                                </th>
                                                                <th scope="row">운송관리비용</th>
                                                                <th scope="row" colspan="2">
                                                                    <input type="checkbox" name="PRVS_PC_STT_RESN_SL_11" id="PRVS_PC_STT_RESN_SL_11" value="11" style="width:30px;"/>
                                                                </th>
                                                                <th scope="row">실제지급금액</th>
                                                                <th scope="row">
                                                                    <input type="checkbox" name="PRVS_PC_STT_RESN_SL_12" id="PRVS_PC_STT_RESN_SL_12" value="12" style="width:30px;"/>
                                                                </th>
                                                            </tr>
                                                            <tr>
                                                                <th scope="row"></th>
                                                                <th scope="row" colspan="6">원유,곡물,광석 등 1차산품으로서 수입신고일 현재 가격이 정해지지 않은 경우</th>
                                                                <th scope="row">
                                                                    <input type="checkbox" name="PRVS_PC_STT_RESN_SL_13" id="PRVS_PC_STT_RESN_SL_13" value="13" style="width:30px;"/>
                                                                </th>
                                                            </tr>
                                                            <tr>
                                                                <th scope="row"></th>
                                                                <th scope="row" colspan="6">특수관계자간 거래가격 결정방법 사전심사(ACVA) 신청.승인 업체인 경우</th>
                                                                <th scope="row">
                                                                    <input type="checkbox" name="PRVS_PC_STT_RESN_SL_14" id="PRVS_PC_STT_RESN_SL_14" value="14" style="width:30px;"/>
                                                                </th>
                                                            </tr>
                                                            <!-- tr>
                                                                <th scope="row"></th>
                                                                <th scope="row" colspan="6">
                                                                    <label>특수관계자간 거래 중 법 제30조 제1항 본문에 따른 수입물품의 거래가격이 수입신고 수리 이후에</label><br>
                                                                    <label>"국제조세 조정에 관한 법률" 제5조에 따른 정상가격으로 조정될 것으로 예상되는 경우</label>
                                                                </th>
                                                                <th scope="row">
                                                                    <input type="checkbox" name="" id="" value="" style="width:30px;"/>
                                                                </th>
                                                            </tr-->
                                                            <tr>
                                                                <th scope="row"></th>
                                                                <th scope="row" colspan="6">
                                                                    <label>국내판매가격에 기초로 한 과세가격 결정(제4방법)으로 가격결정에 장시간 소요되는 경우</label>
                                                                </th>
                                                                <th scope="row">
                                                                    <input type="checkbox" name="PRVS_PC_STT_RESN_SL_15" id="PRVS_PC_STT_RESN_SL_15" value="15" style="width:30px;"/>
                                                                </th>
                                                            </tr>
                                                            <tr>
                                                                <th scope="row"></th>
                                                                <th scope="row" colspan="6">
                                                                    <label>턴키방식 플랜트 등 물품의 최초 발주 이후 상담기간 후 인도 완료되는 경우</label>
                                                                </th>
                                                                <th scope="row">
                                                                    <input type="checkbox" name="PRVS_PC_STT_RESN_SL_16" id="PRVS_PC_STT_RESN_SL_16" value="16" style="width:30px;"/>
                                                                </th>
                                                            </tr>
                                                            <tr>
                                                                <th scope="row"></th>
                                                                <th scope="row">기타</th>
                                                                <th scope="row">
                                                                    <input type="checkbox" name="PRVS_PC_STT_RESN_SL_19" id="PRVS_PC_STT_RESN_SL_19" value="19" style="width:30px;"/>
                                                                </th>
                                                                <th scope="row">기타사유</th>
                                                                <th scope="row" colspan="4">
                                                                    <input type="text" name="PRVS_PC_STT_RESN_ETC_CN" id="PRVS_PC_STT_RESN_ETC_CN" style="width:80%;"/>
                                                                </th>
                                                            </tr>
                                                            <tr>
                                                                <th scope="row"></th>
                                                                <th scope="row" colspan="7">
                                                                    <label>* 기타틑 수입 이전에 최종가격 산출공식이 확정되고, 산출공식은 수입이후 발생 변수에 근거하며,</label><br>
                                                                    <label>그 변수는 거래당사자가 통제할 수 없는 경우에 한함(수입물품 과세가격 결정에 관한 고시 제49조1항3호 참고)</label>
                                                                </th>
                                                            </tr>
					                                    </tbody>
					                                </table>
                                                </div>
                                            </th>
                                        </tr>
                                        <tr id="MMB003_01_typeB_02" style="display:none;">
                                            <td colspan="8" style="padding:0px;">
                                                <table style="width: 100%;">
                                                    <colgroup>
                                                        <col width="100px" />
                                                        <col width="" />
                                                        <col width="100px" />
                                                        <col width="" />
                                                        <col width="130px" />
                                                        <col width="" />
                                                        <col width="180px" />
                                                        <col width="110px" />
                                                    </colgroup>
                                                    <tbody>
                                                        <tr>
                                                            <th scope="row" colspan="8" style="padding-bottom:4px;">
                                                                <label>11.(a) 외환거래가 수반되지 않는 경우 수입물품의 용도</label>
                                                            </th>
                                                        </tr>
                                                        <tr><!-- 아래 6가지 체크박스에서 체크된 값에 대해 CC_IMP_THNG_PRPOS의 부호과 데이터를 입력하고 cc_imp_prno.M26_BFO_PRPOS 컬럼에 부로을 등록할 것 -->
                                                            <th scope="row" colspan="8" style="padding-left:50px;padding-top:0px;">
                                                                <label>견본품<input type="checkbox" id="M26_BFO_PRPOS_1" name="M26_BFO_PRPOS_1" value="1"/> </label>
                                                                <label style="padding-left:15px;">광고용<input type="checkbox" id="M26_BFO_PRPOS_2" name="M26_BFO_PRPOS_2" value="2"/> </label>
                                                                <label style="padding-left:15px;">하자보수용품<input type="checkbox" id="M26_BFO_PRPOS_3" name="M26_BFO_PRPOS_3" value="3"/> </label>
                                                                <label style="padding-left:15px;">대체품<input type="checkbox" id="M26_BFO_PRPOS_4" name="M26_BFO_PRPOS_4" value="4"/> </label>
                                                                <label style="padding-left:15px;">선물 또는 무상기증<input type="checkbox" id="M26_BFO_PRPOS_5" name="M26_BFO_PRPOS_5" value="5"/> </label>
                                                                <label style="padding-left:15px;">생산제조용<input type="checkbox" id="M26_BFO_PRPOS_6" name="M26_BFO_PRPOS_6" value="6"/> </label>
                                                                <label style="padding-left:15px;">기타사유<input type="checkbox" id="M26_BFO_PRPOS_7" name="M26_BFO_PRPOS_7" value="7"/> </label>
                                                                (<input type="text" id="M26_BFO_PRPOS_ETC_RESN" name="M26_BFO_PRPOS_ETC_RESN" class="easyui-validatebox" validType="['maxlength[50]']" style="width:280px;"/>)
                                                            </th>
                                                        </tr>
                                                        <tr>
                                                            <th scope="row" colspan="8" style="padding-left:25px;padding-bottom:4px;padding-top:2px;">
                                                                <label>(b) 외환거래가 수반되지 않는 수입물품의 가격 산정 근거</label>
                                                            </th>
                                                        </tr>
                                                        <tr><!-- 아래 6가지 체크박스에서 체크된 값에 대해 CC_IMP_THNG_PC_BASIS의 부호과 데이터를 입력하고 cc_imp_prno.M26_BFO_PC_CALC_BASIS 컬럼에 부로을 등록할 것 -->
                                                            <th scope="row" colspan="8" style="padding-left:50px;padding-top:0px;">
                                                                <label>유상거래 실적가격<input type="checkbox" id="M26_BFO_PC_CALC_BASIS_1" name="M26_BFO_PC_CALC_BASIS_1" value="1"/> </label>
                                                                <label style="padding-left:15px;">Price List<input type="checkbox" id="M26_BFO_PC_CALC_BASIS_2" name="M26_BFO_PC_CALC_BASIS_2" value="2"/> </label>
                                                                <label style="padding-left:15px;">제조원가<input type="checkbox" id="M26_BFO_PC_CALC_BASIS_3" name="M26_BFO_PC_CALC_BASIS_3" value="3"/> </label>
                                                                <label style="padding-left:15px;">송품장<input type="checkbox" id="M26_BFO_PC_CALC_BASIS_4" name="M26_BFO_PC_CALC_BASIS_4" value="4"/> </label>
                                                                <label style="padding-left:15px;">기타사유<input type="checkbox" id="M26_BFO_PC_CALC_BASIS_5" name="M26_BFO_PC_CALC_BASIS_5" value="5"/> </label>
                                                                (<input type="text" id="M26_BFO_PC_CALC_ETC_RESN" name="M26_BFO_PC_CALC_ETC_RESN" class="easyui-validatebox" validType="['maxlength[50]']" style="width:280px;"/>)
                                                            </th>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th scope="row" colspan="8"><span id="MMB003_01_span_01"></span>가격신고서 작성 책임자(대표이사, 재무이사, 구매관리자 등) 연락처</th>
                                        </tr>
                                        <tr>
                                            <th scope="row" colspan="2" style="padding-left:25px;padding-bottom:2px;">(a)부서 및 직위</th>
                                            <th scope="row" colspan="2" style="padding-bottom:2px;">
                                                <input type="text" name="WRI_RSPN_DEPT_OFPO" id="WRI_RSPN_DEPT_OFPO" class="easyui-validatebox" required="true" style="width:225px;"/>
                                            </th>
                                            <th scope="row" style="padding-left:25px;padding-bottom:2px;">(b)성명</th>
                                            <th scope="row" colspan="3" style="padding-bottom:2px;">
                                                <input type="text" name="WRI_RSPN_NM" id="WRI_RSPN_NM" class="easyui-validatebox" required="true" style="width:225px;"/>
                                            </th>
                                        </tr>
                                        <tr>
                                            <th scope="row" colspan="2" style="padding-left:25px;padding-top:0px;">(c)전화번호</th>
                                            <th scope="row" colspan="6" style="padding-top:0px;">
                                                <input type="text" name="WRI_RSPN_TELNO" id="WRI_RSPN_TELNO" class="easyui-validatebox" required="true" style="width:225px;"/>
                                            </th>
                                        </tr>
                                        <tr>
                                            <th scope="row" colspan="8"><span id="MMB003_01_span_02"></span>가격신고서 작성 실무자 연락처 연락처</th>
                                        </tr>
                                        <tr>
                                            <th scope="row" colspan="2" style="padding-left:25px;padding-bottom:2px;">(a)부서 및 직위</th>
                                            <th scope="row" colspan="2" style="padding-bottom:2px;">
                                                <input type="text" name="WRI_HAOW_DEPT_OFPO" id="WRI_HAOW_DEPT_OFPO" class="easyui-validatebox" required="true" style="width:225px;"/>
                                            </th>
                                            <th scope="row" style="padding-left:25px;padding-bottom:2px;">(b)성명</th>
                                            <th scope="row" colspan="3" style="padding-bottom:2px;">
                                                <input type="text" name="WRI_HAOW_NM" id="WRI_HAOW_NM" class="easyui-validatebox" required="true" style="width:225px;"/>
                                            </th>
                                        </tr>
                                        <tr>
                                            <th scope="row" colspan="2" style="padding-left:25px;padding-top:0px;">(c)작성일자</th>
                                            <th scope="row" colspan="2" style="padding-top:0px;">
                                                <input type="text" name="WRI_DE" id="WRI_DE" style="width:95px;"/>
                                            </th>
                                            <th scope="row" style="padding-left:25px;padding-top:0px;">(d)전화번호</th>
                                            <th scope="row" colspan="3" style="padding-top:0px;">
                                                <input type="text" name="WRI_HAOW_TELNO" id="WRI_HAOW_TELNO" class="easyui-validatebox" required="true" style="width:225px;"/>
                                            </th>
                                        </tr>
                                        <tr id="MMB003_01_typeA_02" style="display:none;">
                                            <td colspan="8" style="padding:0px;">
                                                <table class="middle" style="width: 100%;">
                                                    <colgroup>
                                                        <col width="120px" />
                                                        <col width="" />
                                                        <col width="290px" />
                                                    </colgroup>
                                                    <tbody>
                                                        <tr>
				                                            <td class="line" colspan="3"></td>
				                                        </tr>
				                                        <tr>
				                                            <th scope="row" rowspan="3">A.산출근거</th>
				                                            <th scope="row">
				                                                <label>13.(a)송품장 화폐단위로 표시된 거래가격(관세평가목적상 실제 지급하거나, 지급할 가격)</label><br>
				                                                <label style="padding-left:40px;">( 화폐단위/환율 : </label>
				                                                    <input type="text" name="M1_AFO_CALC_BASIS_CURR_CD" id="M1_AFO_CALC_BASIS_CURR_CD" style="width:60px;"/>
				                                                    <input type="text" name="M1_AFO_CALC_BASIS_EHGT" id="M1_AFO_CALC_BASIS_EHGT" surveyno="A_A14" class="easyui-numberbox easyui-validatebox" validType="['floatformat[5,4]']" style="width:150px;"/>
				                                                <label> )</label>
				                                            </th>
				                                            <th scope="row" style="vertical-align: top;">
				                                                <div style="padding-left:2px;padding-top:2px;"><label>(a) </label><input type="text" name="M1_AFO_CALC_BASIS_PC" id="M1_AFO_CALC_BASIS_PC" surveyno="A_A14" class="easyui-numberbox  easyui-validatebox" validType="['floatformat[12,2]']" style="width:84%;"/></div>
				                                            </th>
				                                        </tr>
				                                        <tr>
				                                            <th scope="row">
				                                                <label style="padding-left:20px;">(b)직접,간접지급금액,할인(조건,사정해당),채무상계,변제금액 등(원화)</label><br>
				                                            </th>
				                                            <th scope="row">
				                                                <div style="padding-left:2px;padding-top:2px;"><label>(b) </label><input type="text" name="M1_AFO_CALC_BASIS_INDRT_AMT" surveyno="A_B14" id="M1_AFO_CALC_BASIS_INDRT_AMT" class="easyui-numberbox easyui-validatebox" validType="['integerformat[12]']" style="width:84%;"/></div>
				                                            </th>
				                                        </tr>
				                                        <tr>
				                                            <th scope="row">
				                                                <label>14.국내화폐로 환산한 총액(A)</label><br>
				                                            </th>
				                                            <th scope="row">
				                                                <span style="padding-left:21px;"><input type="text" name="M1_AFO_CVER_TAMT" id="M1_AFO_CVER_TAMT" class="easyui-numberbox easyui-validatebox" validType="['integerformat[12]']" style="width:84%;"/></span>
				                                            </th>
				                                        </tr>
				                                        <tr>
				                                            <td class="line" colspan="3"></td>
				                                        </tr>
				                                        <tr>
                                                            <th scope="row" rowspan="9">B.가산금액</th>
                                                            <th scope="row" colspan="2" style="padding:6px 0px 0px 6px;">
                                                                <label>15.구매자 부담금액</label>
                                                            </th>
                                                        </tr>
				                                        <tr>
				                                            <th scope="row" style="padding-top:0px">
			                                                    <div style="padding:6px 2px 4px 20px;">(a) 구매수수료를 제외한 수수료(커미션)</div>
			                                                    <div style="padding:4px 2px 4px 20px;">(b) 중개료</div>
			                                                    <div style="padding:4px 2px 4px 20px;">(c) 용기 및 포장비용</div>
				                                            </th>
				                                            <th scope="row" style="vertical-align: bottom;padding-top:0px;">
				                                                <div style="padding-left:2px;padding-top:2px;"><label>(a) </label><input type="text" surveyno="A_B20" name="M1_AFO_ADDI_PURSR_CT" id="M1_AFO_ADDI_PURSR_CT" class="easyui-numberbox easyui-validatebox" validType="['integerformat[12]']" style="width:84%;"/></div>
				                                                <div style="padding-left:2px;padding-top:2px;"><label>(b) </label><input type="text" surveyno="A_B20" name="M1_AFO_ADDI_BRKC" id="M1_AFO_ADDI_BRKC" class="easyui-numberbox easyui-validatebox" validType="['integerformat[12]']" style="width:84%;"/></div>
				                                                <div style="padding-left:2px;padding-top:2px;"><label>(c) </label><input type="text" surveyno="A_B20" name="M1_AFO_ADDI_PKNG_CT" id="M1_AFO_ADDI_PKNG_CT" class="easyui-numberbox easyui-validatebox" validType="['integerformat[12]']" style="width:84%;"/></div>
				                                            </th>
				                                        </tr>
				                                        <tr>
                                                            <th scope="row" colspan="2" style="padding:6px 0px 0px 6px;">
                                                                <label>16.무료 또는 인하된 가격으로 구매자에 의해 제공된 재화와 용역으로서 수입물픔의 제조와 수출에 사용된:</label>
                                                            </th>
                                                        </tr>
				                                        <tr>
				                                            <th scope="row" style="padding-top:0px;">
			                                                    <div style="padding:6px 2px 4px 20px;">(a) 수입물품에 결합된 재료 또는 구성요소</div>
			                                                    <div style="padding:4px 2px 4px 20px;">(b) 수입물품의 생산에 사용되는 공구,금형,다이스 및 이와 유사한 물품</div>
			                                                    <div style="padding:4px 2px 4px 20px;">(c) 수입물품의 생산과정에서 소비되는 물품(비료,촉매등)</div>
			                                                    <div style="padding:4px 2px 4px 20px;">(d) 외국에서 수행된 것으로, 수입물품의 생산에 필요한 기술,설계,고안,공예 및 의자,스케치</div>
				                                            </th>
				                                            <th scope="row" style="vertical-align: bottom;padding-top:0px;">
				                                                <div style="padding-left:2px;padding-top:2px;"><label>(a) </label><input type="text" surveyno="A_B20" name="M1_AFO_ADDI_AMT_CT" id="M1_AFO_ADDI_AMT_CT" class="easyui-numberbox easyui-validatebox" validType="['integerformat[12]']" style="width:84%;"/></div>
				                                                <div style="padding-left:2px;padding-top:2px;"><label>(b) </label><input type="text" surveyno="A_B20" name="M1_AFO_ADDI_IMRTS_PRC_UNT" id="M1_AFO_ADDI_IMRTS_PRC_UNT" class="easyui-numberbox easyui-validatebox" validType="['integerformat[12]']" style="width:84%;"/></div>
				                                                <div style="padding-left:2px;padding-top:2px;"><label>(c) </label><input type="text" surveyno="A_B20" name="M1_AFO_ADDI_CNSMP_THNG" id="M1_AFO_ADDI_CNSMP_THNG" class="easyui-numberbox easyui-validatebox" validType="['integerformat[12]']" style="width:84%;"/></div>
				                                                <div style="padding-left:2px;padding-top:2px;"><label>(d) </label><input type="text" surveyno="A_B20" name="M1_AFO_ADDI_DEV_FAOR" id="M1_AFO_ADDI_DEV_FAOR" class="easyui-numberbox easyui-validatebox" validType="['integerformat[12]']" style="width:84%;"/></div>
				                                            </th>
				                                        </tr>
				                                        <tr>
				                                            <th scope="row">
				                                                <label>17.로열티 및 권리 사용 - 8(a)란 참조</label><br>
				                                            </th>
				                                            <th scope="row">
				                                                <span style="padding-left:21px;"><input type="text" surveyno="A_B20" name="M1_AFO_ADDI_ROYA" id="M1_AFO_ADDI_ROYA" class="easyui-numberbox easyui-validatebox" validType="['integerformat[12]']" style="width:84%;"/></span>
				                                            </th>
				                                        </tr>
				                                        <tr>
				                                            <th scope="row">
				                                                <label>18.판매자에게 귀속되는 수입후의 전매,처분 또는 사용에 따른 수익금액</label>
				                                            </th>
				                                            <th scope="row">
				                                                <span style="padding-left:21px;"><input type="text" surveyno="A_B20" name="M1_AFO_ADDI_ERNS" id="M1_AFO_ADDI_ERNS" class="easyui-numberbox easyui-validatebox" validType="['integerformat[12]']" style="width:84%;"/></span>
				                                            </th>
				                                        </tr>
				                                        <tr>
				                                            <th scope="row" style="padding:6px 0px 0px 6px;">
				                                                <label>19.수입항까지의 운송비용(소개)</label>
				                                            </th>
				                                            <th scope="row" style="padding:4px 10px 0px 6px;">
				                                                <span style="padding-left:21px;"><input type="text" surveyno="A_B20" name="M1_AFO_ADDI_CTRN_SSUM" id="M1_AFO_ADDI_CTRN_SSUM" class="easyui-numberbox easyui-validatebox" validType="['integerformat[12]']" style="width:84%;"/></span>
				                                            </th>
				                                        </tr>
				                                        <tr>
                                                            <th scope="row" style="padding-top:0px;">
                                                                <div style="padding:6px 2px 4px 20px;">(a) 운임,완복운임</div>
                                                                <div style="padding:4px 2px 4px 20px;">(b) 적하,양하,환적비용,기타 운송관련 비용 등</div>
                                                                <div style="padding:4px 2px 4px 20px;">(c) 보험료</div>
                                                            </th>
                                                            <th scope="row" style="vertical-align:bottom;padding-top:0px;">
                                                                <div style="padding-left:2px;padding-top:2px;"><label>(a) </label><input type="text" surveyno="A_B19" name="M1_AFO_ADDI_CYCHG" id="M1_AFO_ADDI_CYCHG" class="easyui-numberbox easyui-validatebox" validType="['integerformat[12]']" style="width:84%;"/></div>
                                                                <div style="padding-left:2px;padding-top:2px;"><label>(b) </label><input type="text" surveyno="A_B19" name="M1_AFO_ADDI_SHCO_CT" id="M1_AFO_ADDI_SHCO_CT" class="easyui-numberbox easyui-validatebox" validType="['integerformat[12]']" style="width:84%;"/></div>
                                                                <div style="padding-left:2px;padding-top:2px;"><label>(c) </label><input type="text" surveyno="A_B19" name="M1_AFO_ADDI_IRNCF" id="M1_AFO_ADDI_IRNCF" class="easyui-numberbox easyui-validatebox" validType="['integerformat[12]']" style="width:84%;"/></div>
                                                            </th>
                                                        </tr>
				                                        <tr>
				                                            <th scope="row">
				                                                <label>20.가산비용 총액</label>
				                                            </th>
				                                            <th scope="row">
				                                                <span style="padding-left:21px;"><input type="text" name="M1_AFO_ADDI_TAMT" id="M1_AFO_ADDI_TAMT" class="easyui-numberbox easyui-validatebox" validType="['integerformat[12]']" style="width:84%;"/></span>
				                                            </th>
				                                        </tr>
				                                        <tr>
				                                            <td class="line" colspan="3"></td>
				                                        </tr>
				                                        <tr>
				                                            <th scope="row" rowspan="5">C.공제금액</th>
				                                            <th scope="row">
				                                                <label>21.수입장소 도착 후 운송비용</label>
				                                            </th>
				                                            <th scope="row">
				                                                <span style="padding-left:21px;"><input type="text" surveyno="A_C25" name="M1_AFO_DDC_TRN_CT" id="M1_AFO_DDC_TRN_CT" class="easyui-numberbox easyui-validatebox" validType="['integerformat[12]']" style="width:84%;"/></span>
				                                            </th>
				                                        </tr>
				                                        <tr>
				                                            <th scope="row">
				                                                <label>22.수입 후 행해진 건설,설치,조립,유지보수 또는 당해 수입물픔에 대한 기술지원 금액</label>
				                                            </th>
				                                            <th scope="row">
				                                                <span style="padding-left:21px;"><input type="text" surveyno="A_C25" name="M1_AFO_DDC_TCHN_SPORT" id="M1_AFO_DDC_TCHN_SPORT" class="easyui-numberbox easyui-validatebox" validType="['integerformat[12]']" style="width:84%;"/></span>
				                                            </th>
				                                        </tr>
				                                        <tr>
				                                            <th scope="row">
				                                                <label>23.기타비용(계약과 관련없이 구매자 자신의 필요에 의해 사용된 검사비용.구매수수료,교육훈련비,연불이자 등)</label>
				                                            </th>
				                                            <th scope="row">
				                                                <span style="padding-left:21px;"><input type="text" surveyno="A_C25" name="M1_AFO_DDC_ETC_CT" id="M1_AFO_DDC_ETC_CT" class="easyui-numberbox easyui-validatebox" validType="['integerformat[12]']" style="width:84%;"/></span>
				                                            </th>
				                                        </tr>
				                                        <tr>
				                                            <th scope="row">
				                                                <label>24.현금할인,수량할인 등 인정하는 가격할이 금액(필요시 기재)</label><br>
				                                                <label style="padding-left:21px;">수출국에서 수출시 경감 또는 환급받아야 할 관세와 내국세 금액</label>
				                                            </th>
				                                            <th scope="row">
				                                                <span style="padding-left:21px;"><input type="text" surveyno="A_C25" name="M1_AFO_DDC_DSNT_AMT" id="M1_AFO_DDC_DSNT_AMT" class="easyui-numberbox easyui-validatebox" validType="['integerformat[12]']" style="width:84%;"/></span>
				                                            </th>
				                                        </tr>
				                                        <tr>
				                                            <th scope="row">
				                                                <label>25.공제금액 총액</label>
				                                            </th>
				                                            <th scope="row">
				                                                <span style="padding-left:21px;"><input type="text" name="M1_AFO_DDC_CT_TAMT" id="M1_AFO_DDC_CT_TAMT" class="easyui-numberbox easyui-validatebox" validType="['integerformat[12]']" style="width:84%;"/></span>
				                                            </th>
				                                        </tr>
                                                    </tbody>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr id="MMB003_01_typeB_03" style="display:none;">
                                            <td colspan="8" style="padding:0px;">
                                                <table class="middle" style="width: 100%;">
                                                    <colgroup>
                                                        <col width="120px" />
                                                        <col width="" />
                                                        <col width="290px" />
                                                    </colgroup>
                                                    <tbody>
                                                        <tr>
                                                            <td class="line" colspan="3"></td>
                                                        </tr>
                                                        <tr>
                                                            <th scope="row" rowspan="2">A.계산의 기초<br>(대체가격)</th>
                                                            <th scope="row">
                                                                <label>14.과세가격으로 인정된 동종/유사물품의 거래가격</label>
                                                            </th>
                                                            <th scope="row">
                                                                <span style="padding-left:21px;"><input type="text" name="M23_BFO_SIMI_PC" id="M23_BFO_SIMI_PC" surveyno="B_A14" class="easyui-numberbox easyui-validatebox" validType="['floatformat[12,2]']" style="width:84%;"/></span>
                                                            </th>
                                                        </tr>
                                                        <tr>
                                                            <th scope="row" style="padding-top:2px;">
                                                                <label style="padding-left:20px;">국내화폐로 환산한 총액(A)( 화폐단위/환율: </label>
                                                                    <input type="text" name="M23_BFO_SIMI_PC_CURR_CD" id="M23_BFO_SIMI_PC_CURR_CD" style="width:60px;"/> /
                                                                    <input type="text" name="M23_BFO_ALRN_PC_EHGT" id="M23_BFO_ALRN_PC_EHGT" surveyno="B_A14" class="easyui-numberbox easyui-validatebox" validType="['floatformat[5,4]']"  style="width:150px;"/>
                                                                <label> )</label>
                                                            </th>
                                                            <th scope="row" style="padding-top:2px;">
                                                                <span style="padding-left:21px;"><input type="text" name="M23_BFO_ALRN_WON_CVER_PC" id="M23_BFO_ALRN_WON_CVER_PC" class="easyui-numberbox easyui-validatebox" validType="['integerformat[12]']" style="width:84%;"/><!-- 거개가격*환율 --></span>
                                                            </th>
                                                        </tr>
                                                        <tr>
                                                            <td class="line" colspan="3"></td>
                                                        </tr>
                                                        <tr>
                                                            <th scope="row" rowspan="6">B.공제 조정</th>
                                                            <th scope="row" style="padding-bottom:0px;">
                                                                <label>15.(a)수량할인 조정</label>
                                                            </th>
                                                            <th scope="row" style="vertical-align: top;padding-bottom:2px;">
                                                                <span style="padding-left:21px;"><input type="text" surveyno="B_B16" name="M23_BFO_DDC_MDAT_QY_DSNT" id="M23_BFO_DDC_MDAT_QY_DSNT" class="easyui-numberbox easyui-validatebox" validType="['integerformat[12]']" style="width:84%;"/></span>
                                                            </th>
                                                        </tr>
                                                        <tr>
                                                            <th scope="row" style="padding: 0px 10px 0px 6px;">
                                                                <label style="padding-left:20px;">(b)상업적 단계 조정</label>
                                                            </th>
                                                            <th scope="row" style="vertical-align: top;padding: 0px 10px 2px 6px;">
                                                                <span style="padding-left:21px;"><input type="text" surveyno="B_B16" name="M23_BFO_DDC_MDAT_AMT" id="M23_BFO_DDC_MDAT_AMT" class="easyui-numberbox easyui-validatebox" validType="['integerformat[12]']" style="width:84%;"/></span>
                                                            </th>
                                                        </tr>
                                                        <tr>
                                                            <th scope="row" style="padding: 0px 10px 0px 6px;">
                                                                <label style="padding-left:20px;">(c)운송비용의 차이</label>
                                                            </th>
                                                            <th scope="row" style="vertical-align: top;padding: 0px 10px 2px 6px;">
                                                                <span style="padding-left:21px;"><input type="text" surveyno="B_B16" name="M23_BFO_DDC_MDAT_TRN_CT" id="M23_BFO_DDC_MDAT_TRN_CT" class="easyui-numberbox easyui-validatebox" validType="['integerformat[12]']" style="width:84%;"/></span>
                                                            </th>
                                                        </tr>
                                                        <tr>
                                                            <th scope="row" style="padding: 0px 10px 2px 6px;">
                                                                <label style="padding-left:20px;">(d)선적항까지의 비용의 차이</label>
                                                            </th>
                                                            <th scope="row" style="vertical-align: top;padding: 0px 10px 2px 6px;">
                                                                <span style="padding-left:21px;"><input type="text" surveyno="B_B16" name="M23_BFO_DDC_MDAT_SHNG_PRT_CT" id="M23_BFO_DDC_MDAT_SHNG_PRT_CT" class="easyui-numberbox easyui-validatebox" validType="['integerformat[12]']" style="width:84%;"/></span>
                                                            </th>
                                                        </tr>
                                                        <tr>
                                                            <th scope="row" style="padding: 0px 10px 5px 6px;">
                                                                <label style="padding-left:20px;">(e)보험료의 차이</label>
                                                            </th>
                                                            <th scope="row" style="vertical-align: top;padding: 0px 10px 5px 6px;">
                                                                <span style="padding-left:21px;"><input type="text" surveyno="B_B16" name="M23_BFO_DDC_MDAT_IRNCF" id="M23_BFO_DDC_MDAT_IRNCF" class="easyui-numberbox easyui-validatebox" validType="['integerformat[12]']" style="width:84%;"/></span>
                                                            </th>
                                                        </tr>
                                                        <tr>
                                                            <th scope="row">16.B의 소계</th>
                                                            <th scope="row" style="vertical-align: top;">
                                                                <span style="padding-left:21px;"><input type="text" name="M23_BFO_DDC_MDAT_SSUM" id="M23_BFO_DDC_MDAT_SSUM" class="easyui-numberbox easyui-validatebox" validType="['integerformat[12]']" style="width:84%;"/></span>
                                                            </th>
                                                        </tr>
                                                        <tr>
                                                            <td class="line" colspan="3"></td>
                                                        </tr>
                                                        <tr>
                                                            <th scope="row" rowspan="6">C.가산 조정</th>
                                                            <th scope="row" style="padding-bottom:0px;">
                                                                <label>17.(a)수량할인 조정</label>
                                                            </th>
                                                            <th scope="row" style="vertical-align: top;padding-bottom:2px;">
                                                                <span style="padding-left:21px;"><input type="text" surveyno="B_C18" name="M23_BFO_ADDI_MDAT_QY_DSNT" id="M23_BFO_ADDI_MDAT_QY_DSNT" class="easyui-numberbox easyui-validatebox" validType="['integerformat[12]']" style="width:84%;"/></span>
                                                            </th>
                                                        </tr>
                                                        <tr>
                                                            <th scope="row" style="padding: 0px 10px 0px 6px;">
                                                                <label style="padding-left:20px;">(b)상업적 단계 조정</label>
                                                            </th>
                                                            <th scope="row" style="vertical-align: top;padding: 0px 10px 2px 6px;">
                                                                <span style="padding-left:21px;"><input type="text" surveyno="B_C18" name="M23_BFO_ADDI_MDAT_AMT_AMT" id="M23_BFO_ADDI_MDAT_AMT_AMT" class="easyui-numberbox easyui-validatebox" validType="['integerformat[12]']" style="width:84%;"/></span>
                                                            </th>
                                                        </tr>
                                                        <tr>
                                                            <th scope="row" style="padding: 0px 10px 0px 6px;">
                                                                <label style="padding-left:20px;">(c)운송비용의 차이</label>
                                                            </th>
                                                            <th scope="row" style="vertical-align: top;padding: 0px 10px 2px 6px;">
                                                                <span style="padding-left:21px;"><input type="text" surveyno="B_C18" name="M23_BFO_ADDI_MDAT_TRN_CT" id="M23_BFO_ADDI_MDAT_TRN_CT" class="easyui-numberbox easyui-validatebox" validType="['integerformat[12]']" style="width:84%;"/></span>
                                                            </th>
                                                        </tr>
                                                        <tr>
                                                            <th scope="row" style="padding: 0px 10px 2px 6px;">
                                                                <label style="padding-left:20px;">(d)선적항까지의 비용의 차이</label>
                                                            </th>
                                                            <th scope="row" style="vertical-align: top;padding: 0px 10px 2px 6px;">
                                                                <span style="padding-left:21px;"><input type="text" surveyno="B_C18" name="M23_BFO_ADDI_MDAT_SHNG_CT" id="M23_BFO_ADDI_MDAT_SHNG_CT" class="easyui-numberbox easyui-validatebox" validType="['integerformat[12]']" style="width:84%;"/></span>
                                                            </th>
                                                        </tr>
                                                        <tr>
                                                            <th scope="row" style="padding: 0px 10px 5px 6px;">
                                                                <label style="padding-left:20px;">(e)보험료의 차이</label>
                                                            </th>
                                                            <th scope="row" style="vertical-align: top;padding: 0px 10px 5px 6px;">
                                                                <span style="padding-left:21px;"><input type="text" surveyno="B_C18" name="M23_BFO_ADDI_MDAT_IRNCF" id="M23_BFO_ADDI_MDAT_IRNCF" class="easyui-numberbox easyui-validatebox" validType="['integerformat[12]']" style="width:84%;"/></span>
                                                            </th>
                                                        </tr>
                                                        <tr>
                                                            <th scope="row">18.C의 소계</th>
                                                            <th scope="row" style="vertical-align: top;">
                                                                <span style="padding-left:21px;"><input type="text" name="M23_BFO_ADDI_MDAT_SSUM" id="M23_BFO_ADDI_MDAT_SSUM" class="easyui-numberbox easyui-validatebox" validType="['integerformat[12]']" style="width:84%;"/></span>
                                                            </th>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr id="MMB003_01_typeB_04" style="display:none;">
                                            <td colspan="8" style="padding:0px;">
                                                <table class="middle" style="width: 100%;">
                                                    <colgroup>
                                                        <col width="120px" />
                                                        <col width="" />
                                                        <col width="290px" />
                                                    </colgroup>
                                                    <tbody>
                                                        <tr>
                                                            <td class="line" colspan="3"></td>
                                                        </tr>
                                                        
                                                        <tr>
                                                            <th scope="row" rowspan="2">D.계산의 기초</th>
                                                            <th scope="row">
                                                                <label>20.당해물품 또는 동종/유사물품을 국내 비특수관계자에게 가장 많은 수량으로 판매한 단위가격</label>
                                                            </th>
                                                            <th scope="row">
                                                                <span style="padding-left:21px;"><input type="text" name="M4_BFO_UNIT_PC" id="M4_BFO_UNIT_PC" surveyno="B_D20" class="easyui-numberbox easyui-validatebox" validType="['floatformat[12,2]']" style="width:84%;"/></span>
                                                            </th>
                                                        </tr>
                                                        <tr>
                                                            <th scope="row" style="padding-top:2px;">
                                                                <label style="padding-left:20px;">국내화폐로 환산한 총액(D)( 화폐단위/환율: </label>
                                                                    <input type="text" name="M4_BFO_CURR_UNIT" id="M4_BFO_CURR_UNIT" style="width:60px;"/> /
                                                                    <input type="text" name="M4_BFO_EHGT" id="M4_BFO_EHGT" surveyno="B_D20" class="easyui-numberbox easyui-validatebox" validType="['floatformat[5,4]']" style="width:150px;"/>
                                                                <label> )</label>
                                                            </th>
                                                            <th scope="row" style="padding-top:2px;">
                                                                <span style="padding-left:21px;"><input type="text" name="M4_BFO_WON_CVER_PC" id="M4_BFO_WON_CVER_PC" class="easyui-numberbox easyui-validatebox" validType="['integerformat[12]']" style="width:84%;"/><!-- 거개가격*환율 --></span>
                                                            </th>
                                                        </tr>
                                                        <tr>
                                                            <td class="line" colspan="3"></td>
                                                        </tr>
                                                        <tr>
                                                            <th scope="row" rowspan="10">E.공제비용:<br>
                                                                <input type="text" id="M4_BFO_DDC_CSMH_REF_NO" name="M4_BFO_DDC_CSMH_REF_NO" placeholder="세관참조번호 기재" style="width:99%;"/><br>
                                                                <br><span style="color:blue;font-weight: normal;">* D항목에 포함된 금액만 해당</span>
                                                            </th>
                                                            <th scope="row" style="padding-bottom:0px;">
                                                                <label>21.(a)수탁판매수수료(위탁판매 수입에 한함)</label>
                                                            </th>
                                                            <th scope="row" style="vertical-align: top;padding-bottom:2px;">
                                                                <span style="padding-left:21px;"><input type="text" surveyno="B_E22" name="M4_BFO_DDC_TRU_SLE_FEE" id="M4_BFO_DDC_TRU_SLE_FEE" class="easyui-numberbox easyui-validatebox" validType="['integerformat[12]']" style="width:84%;"/></span>
                                                            </th>
                                                        </tr>
                                                        <tr>
                                                            <th scope="row" style="padding: 0px 10px 0px 6px;">
                                                                <label style="padding-left:20px;">(b)이윤 및 일반경비</label>
                                                            </th>
                                                            <th scope="row" style="vertical-align: top;padding: 0px 10px 2px 6px;">
                                                                <span style="padding-left:21px;"><input type="text" surveyno="B_E22" name="M4_BFO_DDC_GNRL_EXPE" id="M4_BFO_DDC_GNRL_EXPE" class="easyui-numberbox easyui-validatebox" validType="['integerformat[12]']" style="width:84%;"/></span>
                                                            </th>
                                                        </tr>
                                                        <tr>
                                                            <th scope="row" style="padding: 0px 10px 0px 6px;">
                                                                <label for="M4_BFO_IMP_DDC_CT_RATE_IND1" style="padding-left:45px;">* 비율표시 : 동종동류비율(기준(한도)비율)</label><input type="radio" id="M4_BFO_IMP_DDC_CT_RATE_IND1" name="M4_BFO_IMP_DDC_CT_RATE_IND" value="1"/>
                                                                <label for="M4_BFO_IMP_DDC_CT_RATE_IND2" style="padding-left:20px;">, 납세자제시비율(인정비율)</label><input type="radio" id="M4_BFO_IMP_DDC_CT_RATE_IND2" name="M4_BFO_IMP_DDC_CT_RATE_IND" value="2"/>
                                                            </th>
                                                            <th scope="row" style="vertical-align: top;padding: 0px 10px 2px 6px;">
                                                                <span style="padding-left:21px;"><input type="text" name="M4_BFO_IMP_DDC_CT_RATE" id="M4_BFO_IMP_DDC_CT_RATE" class="easyui-numberbox easyui-validatebox" validType="['floatformat[3,2]']" style="width:84%;"/>%</span>
                                                            </th>
                                                        </tr>
                                                        <tr>
                                                            <th scope="row" style="padding: 0px 10px 0px 6px;">
                                                                <label style="padding-left:20px;">(c)운송비용(수입항-보관창고)</label>
                                                            </th>
                                                            <th scope="row" style="vertical-align: top;padding: 0px 10px 2px 6px;">
                                                                <span style="padding-left:21px;"><input type="text" surveyno="B_E22" name="M4_BFO_DDC_TRN_CT" id="M4_BFO_DDC_TRN_CT" class="easyui-numberbox easyui-validatebox" validType="['integerformat[12]']" style="width:84%;"/></span>
                                                            </th>
                                                        </tr>
                                                        <tr>
                                                            <th scope="row" style="padding: 0px 10px 2px 6px;">
                                                                <label style="padding-left:20px;">(d)보험료(수입항-보관창고)</label>
                                                            </th>
                                                            <th scope="row" style="vertical-align: top;padding: 0px 10px 2px 6px;">
                                                                <span style="padding-left:21px;"><input type="text" surveyno="B_E22" name="M4_BFO_DDC_IRNCF" id="M4_BFO_DDC_IRNCF" class="easyui-numberbox easyui-validatebox" validType="['integerformat[12]']" style="width:84%;"/></span>
                                                            </th>
                                                        </tr>
                                                        <tr>
                                                            <th scope="row" style="padding: 0px 10px 5px 6px;">
                                                                <label style="padding-left:20px;">(e)적하비용(수입항-보관창고)</label>
                                                            </th>
                                                            <th scope="row" style="vertical-align: top;padding: 0px 10px 5px 6px;">
                                                                <span style="padding-left:21px;"><input type="text" surveyno="B_E22" name="M4_BFO_DDC_SHCO_CT" id="M4_BFO_DDC_SHCO_CT" class="easyui-numberbox easyui-validatebox" validType="['integerformat[12]']" style="width:84%;"/></span>
                                                            </th>
                                                        </tr>
                                                        <tr>
                                                            <th scope="row" style="padding: 0px 10px 5px 6px;">
                                                                <label style="padding-left:20px;">(f)기타 운송비용(상풍계정)</label>
                                                            </th>
                                                            <th scope="row" style="vertical-align: top;padding: 0px 10px 5px 6px;">
                                                                <span style="padding-left:21px;"><input type="text" surveyno="B_E22" name="M4_BFO_DDC_ETC_TRN_CT" id="M4_BFO_DDC_ETC_TRN_CT" class="easyui-numberbox easyui-validatebox" validType="['integerformat[12]']" style="width:84%;"/></span>
                                                            </th>
                                                        </tr>
                                                        <tr>
                                                            <th scope="row" style="padding: 0px 10px 5px 6px;">
                                                                <label style="padding-left:20px;">(g)추가가공시 비용(제조원가+부가가치)</label>
                                                            </th>
                                                            <th scope="row" style="vertical-align: top;padding: 0px 10px 5px 6px;">
                                                                <span style="padding-left:21px;"><input type="text" surveyno="B_E22" name="M4_BFO_DDC_ADIT_CT" id="M4_BFO_DDC_ADIT_CT" class="easyui-numberbox easyui-validatebox" validType="['integerformat[12]']" style="width:84%;"/></span>
                                                            </th>
                                                        </tr>
                                                        <tr>
                                                            <th scope="row" style="padding: 0px 10px 5px 6px;">
                                                                <label style="padding-left:20px;">(h)국내판매 및 수입과 관련된 세금과 공과금</label>
                                                            </th>
                                                            <th scope="row" style="vertical-align: top;padding: 0px 10px 5px 6px;">
                                                                <span style="padding-left:21px;"><input type="text" surveyno="B_E22" name="M4_BFO_DDC_CT_TAX" id="M4_BFO_DDC_CT_TAX" class="easyui-numberbox easyui-validatebox" validType="['integerformat[12]']" style="width:84%;"/></span>
                                                            </th>
                                                        </tr>
                                                        <tr>
                                                            <th scope="row">22.E의 소계</th>
                                                            <th scope="row" style="vertical-align: top;">
                                                                <span style="padding-left:21px;"><input type="text" name="M4_BFO_DDC_CT_TAMT" id="M4_BFO_DDC_CT_TAMT" class="easyui-numberbox easyui-validatebox" validType="['integerformat[12]']" style="width:84%;"/></span>
                                                            </th>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr id="MMB003_01_typeB_05" style="display:none;">
                                            <td colspan="8" style="padding:0px;">
                                                <table class="middle" style="width: 100%;">
                                                    <colgroup>
                                                        <col width="120px" />
                                                        <col width="" />
                                                        <col width="290px" />
                                                    </colgroup>
                                                    <tbody>
                                                        <tr>
                                                            <td class="line" colspan="3"></td>
                                                        </tr>
                                                        <tr>
                                                            <th scope="row">F.계산의 기초</th>
                                                            <th scope="row">
                                                                <label>24.산정가격/수입물품의 가격(국내통화로 환산된 가격)으로서 세관의 결정 등 합의된 사항에 따라 계산되는 금액을 기재</label><br>
                                                            </th>
                                                            <th scope="row" style="vertical-align: top;">
                                                                <span style="padding-left:21px;"><input type="text" surveyno="B_F24" name="M56_BFO_CALC_BSIS" id="M56_BFO_CALC_BSIS" class="easyui-numberbox easyui-validatebox" validType="['integerformat[12]']" style="width:84%;"/></span>
                                                            </th>
                                                        </tr>
                                                        <tr>
                                                            <td class="line" colspan="3"></td>
                                                        </tr>
                                                        <tr>
                                                            <th scope="row" rowspan="4">G.가산금액<br>
                                                                <br><span style="color:blue;font-weight: normal;">* F란에 포함된지 않은 금액으로 내국통화로 환산</span>
                                                            </th>
                                                            <th scope="row" style="padding-bottom:2px;">
                                                                <label>25.(a)수입항까지의 운송비용</label>
                                                            </th>
                                                            <th scope="row" style="vertical-align: top;padding-bottom:2px;">
                                                                <span style="padding-left:21px;"><input type="text" surveyno="B_G26" name="M56_BFO_ADDI_AMT_CYCHG" id="M56_BFO_ADDI_AMT_CYCHG" class="easyui-numberbox easyui-validatebox" validType="['integerformat[12]']" style="width:84%;"/></span>
                                                            </th>
                                                        </tr>
                                                        <tr>
                                                            <th scope="row" style="padding: 0px 10px 0px 6px;">
                                                                <label style="padding-left:20px;">(b)선적항에서의 적하비용</label>
                                                            </th>
                                                            <th scope="row" style="vertical-align: top;padding: 0px 10px 2px 6px;">
                                                                <span style="padding-left:21px;"><input type="text" surveyno="B_G26" name="M56_BFO_ADDI_AMT_SHCO_CT" id="M56_BFO_ADDI_AMT_SHCO_CT" class="easyui-numberbox easyui-validatebox" validType="['integerformat[12]']" style="width:84%;"/></span>
                                                            </th>
                                                        </tr>
                                                        <tr>
                                                            <th scope="row" style="padding: 0px 10px 5px 6px;">
                                                                <label style="padding-left:20px;">(c)보험료 및 기타비용</label>
                                                            </th>
                                                            <th scope="row" style="vertical-align: top;padding: 0px 10px 5px 6px;">
                                                                <span style="padding-left:21px;"><input type="text" surveyno="B_G26" name="M56_BFO_ADDI_AMT_IRNCF" id="M56_BFO_ADDI_AMT_IRNCF" class="easyui-numberbox easyui-validatebox" validType="['integerformat[12]']" style="width:84%;"/></span>
                                                            </th>
                                                        </tr>
                                                        <tr>
                                                            <th scope="row">26.G의 소계</th>
                                                            <th scope="row" style="vertical-align: top;">
                                                                <span style="padding-left:21px;"><input type="text" name="M56_BFO_ADDI_AMT_SSUM" id="M56_BFO_ADDI_AMT_SSUM" class="easyui-numberbox easyui-validatebox" validType="['integerformat[12]']" style="width:84%;"/></span>
                                                            </th>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="line" colspan="8"></td>
                                        </tr>
                                        <tr>
                                            <th scope="row" colspan="6"><span id="MMB003_01_span_03"> </span></th>
                                            <th scope="row" colspan="2">
                                                <span style="padding-left:21px;"><input type="text" name="STT_PAY_TAXT_PC" id="STT_PAY_TAXT_PC" class="easyui-numberbox easyui-validatebox" required="true" validType="['integerformat[12]']" style="width:84%;"/></span>
                                            </th>
                                        </tr>
		                            </tbody>
		                        </table>
		                    </div>
		                </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-B003_01.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>