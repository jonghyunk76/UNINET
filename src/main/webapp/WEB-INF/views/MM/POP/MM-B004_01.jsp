<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : MM-B004_01.jsp
  * @Description : 협정적용 신청서 화면
  * @Modification Information
  *
  * author YNI-Maker(이메일)
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMB004_01_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>      
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center',border:false">
            <div class="easyui-layout" data-options="fit:true">
                <div data-options="region:'north',border:false"  class="h2_etc">
                    <div class="btn_R">
		                <a href="javascript:MMB004_01.control.updateImportTotalCvtaApcPdoInfo();" class="btnSave"><spring:message code="SAVE"/></a>
		                <a href="javascript:dialog.handle.close(dialog.getObject('MMB004_01_dailog_01'));" class="btnPopClose"><spring:message code="CLOSE"/></a>
		            </div>
                </div>
                <div data-options="region:'center',border:false">
                    <div class="easyui-layout" data-options="fit:true">
				        <div data-options="region:'north',border:false" class="sec_tit" style="overflow:hidden;">
				            <form id="MMB004_01_form_05" method="post">
		                        <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
		                        <input type="hidden" id="IMP_CVTA_APC_RDO_LNE_SN" name="IMP_CVTA_APC_RDO_LNE_SN"/>
		                        <input type="hidden" id="IMP_CVTA_APC_RDO_SN" name="IMP_CVTA_APC_RDO_SN"/>
		                        <input type="hidden" id="DOC_STLE_SE" name="DOC_STLE_SE"/>
		                    </form>
		                    <form id="MMB004_01_form_01" method="post">
		                        <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
		                        <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
		                        <input type="hidden" id="IMP_BCNC_SETUP_SN" name="IMP_BCNC_SETUP_SN" value="${IMP_BCNC_SETUP_SN}"/><!-- 거래처 설정정보 -->
		                        <input type="hidden" id="STT_ODR" name="STT_ODR" value="${STT_ODR}"/>
		                        <input type="hidden" id="IMP_CVTA_APC_RDO_SN" name="IMP_CVTA_APC_RDO_SN"/>
		                    </form>
				            <ul class="tab_area">
				                <li id="MMB004_01_divMove01" class="on"><a href="javascript:MMB004_01.ui.divMove('1');"><b><spring:message code="CC_작성"/></b></a></li>
				                <li id="MMB004_01_divMove02"><a href="javascript:MMB004_01.ui.divMove('2');"><b><spring:message code="CC_란사항"/></b></a></li>
				                <li id="MMB004_01_divMove03" style="display:none;"><a href="javascript:MMB004_01.ui.divMove('3');"><b><spring:message code="CC_원산지규격"/></b></a></li>
				            </ul>
				        </div>
				        <div data-options="region:'center',border:false">
				            <div id="MMB004_01_tabs_01" class="easyui-tabs" data-options="fit:true,showHeader:false,plain:true">
		                        <div title="1">
		                            <div class="easyui-layout" data-options="fit:true">
                                        <!-- div data-options="region:'north', border:false">
                                            <div class="h2_etc">
                                                <p class="h2_btn">
                                                    <a href="javascript:MMB004_01.control.updateImportCvtaApcPdoInfo();" class="btnMainSave"><spring:message code="SAVE"/></a>
                                                </p>
                                            </div>
                                        </div -->
                                        <div data-options="region:'center',border:false">
				                            <form id="MMB004_01_form_02" name="MMB004_01_form_02" method="post">
						                        <input type="hidden" id="COMPANY_CD" name="COMPANY_CD"/>
						                        <input type="hidden" id="USER_ID" name="USER_ID" value="${_MEMBER.SESSION_USER_ID}"/>
						                        <input type="hidden" id="IMP_CVTA_APC_RDO_SN" name="IMP_CVTA_APC_RDO_SN"/> <!-- 협정적용신청서 일련번호 -->
						                        <input type="hidden" id="IMP_DCLRT_SN" name="IMP_DCLRT_SN"/>
								                <input type="hidden" id="IMP_BCNC_SETUP_SN" name="IMP_BCNC_SETUP_SN"/><!-- 거래처 설정정보 -->
								                <input type="hidden" id="STT_ODR" name="STT_ODR"/>
						                        <input type="hidden" id="DOC_STLE_SE" name="DOC_STLE_SE" value="GOVCBR5SC"/>
						                        <input type="hidden" id="IMP_BCNC_SETUP_SN" name="IMP_BCNC_SETUP_SN"/>
						                        <table class="gridT">
						                            <colgroup>
						                                <col width="120px" />
						                                <col width="110px" />
						                                <col width="" />
						                                <col width="80px" />
		                                                <col width="60px" />
		                                                <col width="80px" />
		                                                <col width="60px" />
		                                                <col width="100px" />
		                                                <col width="110px" />
		                                                <col width="100px" />
		                                                <col width="110px" />
						                            </colgroup>
						                            <tbody>
						                                <tr>
						                                    <th scope="row"><spring:message code="CC_수입신고번호"/></th>
						                                    <td colspan="2">
						                                        <input type="hidden" name="STT_NO" id="STT_NO"/>
			                                                    <input type="text" id="APLC_CBOA_APPN"  name="APLC_CBOA_APPN" class="easyui-validatebox search_readOnly" readonly validType="['minlength[5]','maxlength[5]']" style="width:60px;text-align:center;"/>
			                                                    <input type="text" id="SN_YY" name="SN_YY" class="easyui-validatebox search_readOnly" readonly validType="['minlength[2]','maxlength[2]']" style="width:30px;text-align:center;"/>
			                                                    <input type="text" id="IMPDEC_SN"  name="IMPDEC_SN" class="easyui-validatebox search_readOnly" readonly validType="['minlength[7]','maxlength[7]']" style="width:80px;text-align:center;"/>
			                                                    <input type="text" id="IMPDEC_TYPE"  name="IMPDEC_TYPE" class="easyui-validatebox search_readOnly" readonly validType="['minlength[1]','maxlength[1]']" style="width:20px;text-align:center;"/>
						                                    </td>
						                                    <th scope="row"><spring:message code="CC_전송"/></th>
		                                                    <td>
		                                                        <input type="text" name="TRNS_RELT" id="TRNS_RELT" readonly class="search_readOnly" style="width:88%;"/>
		                                                    </td>
		                                                    <th scope="row"><spring:message code="CC_수신"/></th>
		                                                    <td>
		                                                        <input type="text" name="REC_RELT" id="REC_RELT" readonly class="search_readOnly" style="width:88%;"/>
		                                                    </td>
		                                                    <th scope="row"><spring:message code="CC_신청일자"/></th>
		                                                    <td>
		                                                        <input name="REQST_DE" id="REQST_DE" style="width:95px;"/>
		                                                    </td>
		                                                    <th scope="row"><spring:message code="CC_전송구분"/></th>
		                                                    <td>
		                                                        <input name="TRNS_SE" id="TRNS_SE" style="width:100px;"/>
		                                                    </td>
						                                </tr>
						                                <tr>
						                                    <th scope="row"><spring:message code="CC_세관/과"/></th>
		                                                    <td colspan="2">
		                                                        <input type="text" id="CSMH_NM" name="CSMH_NM" readonly class="search_readOnly" style="width:45%;"/> /
		                                                        <input type="text" id="KWA_NM" name="KWA_NM" readonly class="search_readOnly" style="width:45%;"/>
		                                                    </td>
		                                                    <th scope="row" colspan="2"><spring:message code="CC_수입자 식별번호구분"/></th>
		                                                    <td colspan="2">
		                                                        <input name="IMPR_IDNU_SE_SL" id="IMPR_IDNU_SE_SL" style="width:132px;"/>
		                                                    </td>
		                                                    <th scope="row"><spring:message code="CC_식별번호"/></th>
		                                                    <td colspan="3">
		                                                        <input name="IMPR_IDNU" id="IMPR_IDNU" class="easyui-validatebox" required="true" style="width:160px;"/>
		                                                    </td>
		                                                </tr>
						                            </tbody>
						                        </table>
						                        <div class="h2_etc">
			                                        <p class="h2"><spring:message code="CC_수입자"/> <font color="blue">* 원산지 증명서상의 수입자/납세의무자</font></p>
			                                    </div>
			                                    <table class="gridT">
		                                            <colgroup>
		                                                <col width="120px" />
		                                                <col width=";" />
		                                                <col width="100px" />
		                                                <col width="150px;" />
		                                                <col width="120px" />
		                                                <col width="150px;" />
		                                            </colgroup>
		                                            <tbody>
		                                                <tr>
		                                                    <th scope="row" class="point2"><spring:message code="CC_상호"/></th>
		                                                    <td>
		                                                        <input type="text" name="IMPR_CD" id="IMPR_CD" class="easyui-validatebox search_readOnly" readonly required="true" style="width:130px;"/>
		                                                        <input type="text" name="IMPR_MTLTY" id="IMPR_MTLTY" class="easyui-validatebox search_readOnly" readonly required="true" style="width:60%;"/>
		                                                        <a href="javascript:MMB004_01.dialog.openDialog_2('I')" class="btnSearch">거래처</a>
		                                                    </td>
		                                                    <th scope="row"><spring:message code="CC_대표자"/></th>
		                                                    <td>
		                                                        <input type="text" name="IMPR_NM" id="IMPR_NM"style="width:98%;"/>
		                                                    </td>
		                                                    <th scope="row"><spring:message code="CC_사업자등록번호"/></th>
		                                                    <td>
		                                                        <input type="text" name="IMPR_BIZRNO" id="IMPR_BIZRNO" style="width:98%;"/>
		                                                    </td>
		                                                </tr>
		                                                <tr>
		                                                    <th scope="row"><spring:message code="CC_주소"/></th>
		                                                    <td>
		                                                        <input type="text" name="IMPR_BASS_ADRES" id="IMPR_BASS_ADRES" style="width:99%;"/>
		                                                    </td>
		                                                    <th scope="row"><spring:message code="CC_E-Mail"/></th>
		                                                    <td colspan="3">
		                                                        <input type="text" name="IMPR_EMAIL" id="IMPR_EMAIL" style="width:240px;"/>
		                                                    </td>
		                                                </tr>
		                                                <tr>
		                                                    <th scope="row"><spring:message code="CC_TEL/FAX"/></th>
		                                                    <td>
		                                                        <input type="text" name="IMPR_TELNO" id="IMPR_TELNO" style="width:180px;"/>
		                                                        <input type="text" name="IMPR_FAX" id="IMPR_FAX" style="width:180px;"/>
		                                                    </td>
		                                                    <th scope="row"><spring:message code="CC_통관고유부호"/></th>
		                                                    <td colspan="3">
		                                                        <input type="text" name="IMPR_ECTMRK" id="IMPR_ECTMRK" style="width:180px;"/>
		                                                    </td>
		                                                </tr>
		                                                <tr>
		                                                    <th scope="row"><spring:message code="CC_도로명코드"/></th>
		                                                    <td>
		                                                        <input type="text" name="IMPR_RN_CD" id="IMPR_RN_CD" style="width:99%;"/>
		                                                    </td>
		                                                    <th scope="row"><spring:message code="CC_건물관리번호"/></th>
		                                                    <td colspan="3">
		                                                        <input type="text" name="IMPR_NABN" id="IMPR_NABN" style="width:180px;"/>
		                                                    </td>
		                                                </tr>
		                                                <tr>
		                                                    <th scope="row"><spring:message code="CC_상세주소"/></th>
		                                                    <td colspan="3">
		                                                        <input type="text" name="IMPR_DETAIL_ADRES" id="IMPR_DETAIL_ADRES" style="width:99%;"/>
		                                                    </td>
		                                                    <th scope="row"><spring:message code="CC_우편번호"/></th>
		                                                    <td>
		                                                        <input type="text" name="IMPR_ZIP" id="IMPR_ZIP" style="width:98%;"/>
		                                                    </td>
		                                                </tr>
		                                            </tbody>
		                                        </table>
		                                        <div class="h2_etc">
		                                            <p class="h2"><spring:message code="CC_수출자"/> <font color="blue">* 원산지 증명서상의 수출자</font></p>
		                                        </div>
		                                        <table class="gridT">
		                                            <colgroup>
		                                                <col width="120px" />
		                                                <col width=";" />
		                                                <col width="100px" />
		                                                <col width="150px;" />
		                                                <col width="120px" />
		                                                <col width="150px;" />
		                                            </colgroup>
		                                            <tbody>
		                                                <tr>
		                                                    <th scope="row"><spring:message code="CC_상호"/></th>
		                                                    <td>
		                                                        <a href="javascript:MMB004_01.dialog.openDialog_3('E')" class="btnSearch">해외공급자</a>
		                                                        <a href="javascript:MMB004_01.dialog.openDialog_2('E')" class="btnSearch">거래처</a>
		                                                        <input type="text" name="EXPTER_CD" id="EXPTER_CD" class="easyui-validatebox" style="width:130px;"/>
		                                                        <input type="text" name="EXPTER_MTLTY" id="EXPTER_MTLTY" class="easyui-validatebox" style="width:43%;"/>
		                                                    </td>
		                                                    <th scope="row"><spring:message code="CC_대표자"/></th>
		                                                    <td colspan="3">
		                                                        <input type="text" name="EXPTER_NM" id="EXPTER_NM" style="width:98%;"/>
		                                                    </td>
		                                                </tr>
		                                                <tr>
		                                                    <th scope="row"><spring:message code="CC_주소"/></th>
		                                                    <td colspan="5">
		                                                        <input type="text" name="EXPTER_ADRES" id="EXPTER_ADRES" style="width:99%;"/>
		                                                    </td>
		                                                </tr>
		                                                <tr>
		                                                    <th scope="row"><spring:message code="CC_TEL/FAX"/></th>
		                                                    <td>
		                                                        <input type="text" name="EXPTER_TELNO" id="EXPTER_TELNO" style="width:180px;"/>
		                                                        <input type="text" name="EXPTER_FAX" id="EXPTER_FAX" style="width:180px;"/>
		                                                    </td>
		                                                    <th scope="row"><spring:message code="CC_국가코드"/></th>
		                                                    <td colspan="3">
		                                                        <input type="text" name="EXPTER_NATION_CD" id="EXPTER_NATION_CD" style="width:60px;"/>
		                                                        <input type="text" name="EXPTER_NATION_NM" id="EXPTER_NATION_NM" class="search_readOnly" readonly style="width:200px;"/>
		                                                        <a href="javascript:MMB004_01.dialog.openDialog_1('EXPTER_NATION_CD')"><img src="/images/sample/btn_sch.gif"/></a>
		                                                    </td>
		                                                </tr>
		                                            </tbody>
		                                        </table>
		                                        <div class="h2_etc">
		                                            <p class="h2"><spring:message code="CC_생산자"/> <font color="blue">* 수출자와 상이할 경우 기재</font></p>
		                                        </div>
		                                        <table class="gridT">
		                                            <colgroup>
		                                                <col width="120px" />
		                                                <col width=";" />
		                                                <col width="100px" />
		                                                <col width="150px;" />
		                                                <col width="120px" />
		                                                <col width="150px;" />
		                                            </colgroup>
		                                            <tbody>
		                                                <tr>
		                                                    <th scope="row"><spring:message code="CC_상호"/></th>
		                                                    <td>
		                                                        <a href="javascript:MMB004_01.dialog.openDialog_3('P')" class="btnSearch">해외공급자</a>
		                                                        <a href="javascript:MMB004_01.dialog.openDialog_2('P')" class="btnSearch">거래처</a>
		                                                        <input type="text" name="MKER_CD" id="MKER_CD" class="easyui-validatebox" style="width:130px;"/>
                                                                <input type="text" name="MKER_MTLTY" id="MKER_MTLTY" class="easyui-validatebox" style="width:43%;"/>
		                                                    </td>
		                                                    <th scope="row"><spring:message code="CC_대표자"/></th>
		                                                    <td colspan="3">
		                                                        <input type="text" name="MKER_NM" id="MKER_NM" style="width:98%;"/>
		                                                    </td>
		                                                </tr>
		                                                <tr>
		                                                    <th scope="row"><spring:message code="CC_주소"/></th>
		                                                    <td colspan="5">
		                                                        <input type="text" name="MKER_ADRES" id="MKER_ADRES" style="width:99%;"/>
		                                                    </td>
		                                                </tr>
		                                                <tr>
		                                                    <th scope="row"><spring:message code="CC_TEL/FAX"/></th>
		                                                    <td colspan="5">
		                                                        <input type="text" name="MKER_TELNO" id="MKER_TELNO" style="width:180px;"/>
		                                                        <input type="text" name="MKER_FAX" id="MKER_FAX" style="width:180px;"/>
		                                                    </td>
		                                                </tr>
		                                            </tbody>
		                                        </table>
		                                        <div class="h2_etc">
		                                            <p style="height:1px;"></p>
		                                        </div>
		                                        <table class="gridT" style="margin-bottom:0px;">
		                                            <colgroup>
		                                                <col width="120px" />
		                                                <col width=";" />
		                                            </colgroup>
		                                            <tbody>
		                                                <tr>
		                                                    <th scope="row"><spring:message code="CC_협정관세적용법령"/></th>
		                                                    <td>
		                                                        <input type="radio" name="CVTA_APC_LAOR_CD" id="CVTA_APC_LAOR_CD" value="1"/><label>제8조제1항(수입수리전)</label>
		                                                        <input type="radio" name="CVTA_APC_LAOR_CD" id="CVTA_APC_LAOR_CD" value="2"/><label>제9조제1항(수입신고수리후)</label>
		                                                        <input type="radio" name="CVTA_APC_LAOR_CD" id="CVTA_APC_LAOR_CD" value="3"/><label>제9조제2항(수리후 신고한 품목과 다른 품목분류를 적용시)</label>
		                                                    </td>
		                                                </tr>
		                                                <tr>
		                                                    <th scope="row"><spring:message code="CC_고지번호"/></th>
		                                                    <td>
		                                                        <input type="text" name="NTIC_NO" id="NTIC_NO" style="width:180px;"/>
		                                                        <label style="color:blue">제9조제2항 적용시 필수 기재. 협정관세적용법령, 고지번호는 2020.04.01부터 시행</label>
		                                                    </td>
		                                                </tr>
		                                            </tbody>
		                                        </table>
						                    </form>
						                </div>
						            </div>
		                        </div>
		                        <div title="2">
		                            <div class="easyui-layout" data-options="fit:true">
			                            <div data-options="region:'north', border:false">
		                                    <div class="h2_etc">
		                                        <p class="h2"><spring:message code="CC_란 자료"/></p>
		                                        <p class="h2_btn">
		                                            <a href="javascript:MMB004_01.control.updateImportCvtaApcPdoLneInfo();" class="btnMainSave" style="display:none;"><spring:message code="SAVE"/></a>
		                                            <a href="javascript:MMB004_01.control.deleteImportCvtaApcPdoLneInfo();" class="btnMainDelete"><spring:message code="DEL"/></a>
		                                            <a href="javascript:MMB004_01.control.insertRenewImpCvtaApcPdoLneInfo();" class="btnMainRestart"><spring:message code="CC_재생성"/></a>
		                                        </p>
		                                    </div>
		                                </div>
	                                    <div data-options="region:'center',border:false">
		                                    <div class="easyui-layout" data-options="fit:true">
		                                        <div data-options="region:'west', border:false,split:true" style="width:340px;">
		                                            <div class="easyui-layout" data-options="fit:true">
                                                        <div data-options="region:'north', border:false">
                                                            <form id="MMB004_01_form_07" name="MMB004_01_form_07" method="post">
		                                                        <input type="hidden" id="USER_ID" name="USER_ID" value="${_MEMBER.SESSION_USER_ID}"/>
		                                                        <input type="hidden" id="gridData" name="gridData"/>
	                                                            <table class="gridT" style="margin-bottom:0px;">
		                                                            <colgroup>
		                                                                <col width="120px" />
		                                                                <col width=";" />
		                                                            </colgroup>
		                                                            <tbody>
		                                                                <tr>
		                                                                    <th scope="row"><spring:message code="CC_항목"/></th>
		                                                                    <td colspan="3">
		                                                                        <input type="text" name="MOD_BATCH_ITEM" id="MOD_BATCH_ITEM" style="width:195px;"/>
		                                                                    </td>
		                                                                </tr>
		                                                                <tr>
		                                                                    <th scope="row"><spring:message code="CC_입력값"/></th>
	                                                                        <td colspan="3">
	                                                                            <input type="text" name="MOD_BATCH_VALUE" id="MOD_BATCH_VALUE" class="easyui-validatebox" required="true" style="width:113px;"/>
	                                                                            <a href="javascript:MMB004_01.control.updateBatchImportCvtaDclrtLne();" class="btnCheckEdit">일괄수정</a>
	                                                                        </td>
		                                                                </tr>
		                                                            </tbody>
	                                                            </table>
	                                                        </form>
                                                        </div>
	                                                    <div data-options="region:'center',border:false">
			                                                <table id="MMB004_01_grid_01"></table>
			                                            </div>
			                                        </div>
		                                        </div>
		                                        <div data-options="region:'center',border:false">
		                                            <div id="MMB004_01_div_02">
			                                        <form id="MMB004_01_form_03" name="MMB004_01_form_03" method="post">
	                                                    <input type="hidden" id="COMPANY_CD" name="COMPANY_CD"/>
	                                                    <input type="hidden" id="USER_ID" name="USER_ID" value="${_MEMBER.SESSION_USER_ID}"/>
	                                                    <input type="hidden" id="IMP_CVTA_APC_RDO_LNE_SN" name="IMP_CVTA_APC_RDO_LNE_SN"/>
			                                            <table class="gridT" style="margin-bottom:0px;">
				                                            <colgroup>
				                                                <col width="120px" />
				                                                <col width="100px;" />
				                                                <col width="120px" />
                                                                <col width=";" />
                                                                <col width="100px" />
                                                                <col width=";" />
                                                                <col width="80px" />
                                                                <col width=";" />
				                                            </colgroup>
				                                            <tbody>
				                                                <tr>
				                                                    <th scope="row"><spring:message code="CC_일련번호"/></th>
				                                                    <td>
				                                                        <input type="text" name="CVTA_REQST_SN" id="CVTA_REQST_SN" style="width:92%;"/>
				                                                    </td>
				                                                    <th scope="row"><spring:message code="CC_수입신고 란번호"/></th>
                                                                    <td colspan="5">
                                                                        <input type="text" name="IMP_DCLRT_LNE_NO" id="IMP_DCLRT_LNE_NO" style="width:60px;"/>
                                                                        <label style="display:none;"><input type="checkbox" name="GET_IMP_DCLRT_LNE" id="GET_IMP_DCLRT_LNE"/>수입신고서의 란내용 가져오기</label>
                                                                    </td>
				                                                </tr>
				                                                <tr>
				                                                    <th scope="row"><spring:message code="CC_원산지증빙서류"/></th>
				                                                    <td colspan="7">
				                                                        <input type="text" name="ORPL_PRUF_PAPE_SE" id="ORPL_PRUF_PAPE_SE" style="width:60px;"/>
				                                                        <label>[1:원산지증명서, 2:사전심사서, 3:동종물질품목의 원산지증명서 제출면제물품]</label>
				                                                    </td>
				                                                </tr>
				                                                <tr>
                                                                    <th scope="row" class="point2"><spring:message code="CC_원산지"/></th>
                                                                    <td>
                                                                        <input type="text" name="ORPL_NATION_CD" id="ORPL_NATION_CD" style="width:92%;"/>
                                                                    </td>
                                                                    <th scope="row"><spring:message code="CC_원산지기준"/></th>
                                                                    <td colspan="5">
                                                                        <input type="text" name="COTDOO" id="COTDOO" style="width:300px;"/>
                                                                        <img src="/images/icon/link_url.png" 
                                                                        title="자율발급원산지증명 물품으로 원산지 기준을 알 수 없는 경우:[G]제조공적(절단을 포함)을 수반한 직조/자수/도포공정을 거친 것:[H]항의 경우 자수/도포공정인 경우, 자수/도포되지 않은 비원산지 직물 가격이 제품 공장도가격의 40%을 초과하지 않아야 함"/>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <th scope="row"><spring:message code="CC_협정적용순중량"/></th>
                                                                    <td>
                                                                        <input type="text" name="AGM_APC_NETWGHT" id="AGM_APC_NETWGHT" class="easyui-numberbox" style="width:60px;"/> <label>KG</label>
                                                                    </td>
                                                                    <th scope="row"><spring:message code="CC_세번부호"/></th>
                                                                    <td>
                                                                        <input type="text" name="HS_CD" id="HS_CD" style="width:92%;"/>
                                                                    </td>
                                                                    <th scope="row"><spring:message code="CC_협정관세율"/></th>
                                                                    <td>
                                                                        <input type="text" name="CVTA_RT" id="CVTA_RT" style="width:92%;"/>
                                                                    </td>
                                                                    <th scope="row"><spring:message code="CC_세율구분"/></th>
                                                                    <td>
                                                                        <input type="text" name="CVTA_SE_CD" id="CVTA_SE_CD" style="width:40px;"/>
                                                                        <a href="javascript:MMB004_01.dialog.openDialog_1('CVTA_SE_CD');"><img src="/images/sample/btn_sch.gif"/></a>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <th scope="row"><spring:message code="CC_품명"/></th>
                                                                    <td colspan="7">
                                                                        <input type="text" name="PRDNM" id="PRDNM" style="width:99%;"/>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <th scope="row"><spring:message code="CC_모델,규격"/></th>
                                                                    <td colspan="7">
                                                                        <textarea name="MEL_STARD" id="MEL_STARD" style="height:39px;width:99%;"></textarea>
                                                                    </td>
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                        <div class="h2_etc">
                                                            <p style="height:9px;"></p>
                                                        </div>
                                                        <table class="gridT" style="margin-bottom:0px;">
                                                            <colgroup>
                                                                <col width="120px" />
                                                                <col width="100px;" />
                                                                <col width="120px" />
                                                                <col width=";" />
                                                                <col width="100px" />
                                                                <col width=";" />
                                                                <col width="80px" />
                                                                <col width=";" />
                                                            </colgroup>
                                                            <tbody>
                                                                <tr>
                                                                    <th scope="row"><spring:message code="CC_발급번호"/></th>
                                                                    <td colspan="3">
                                                                        <input type="text" name="ISSU_NO" id="ISSU_NO" style="width:98%;"/>
                                                                    </td>
                                                                    <th scope="row"><spring:message code="CC_발급일자"/></th>
                                                                    <td colspan="3">
                                                                        <input type="text" name="ISSU_DE" id="ISSU_DE" style="width:95px;"/>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <th scope="row" colspan="2"><spring:message code="CC_원산지증명서 발급주체"/></th>
                                                                    <td colspan="6">
                                                                        <input type="text" name="ORPL_ISSUER_SE" id="ORPL_ISSUER_SE" style="width:40px;"/>
                                                                        <label>[발급자구분 1:기간, 2:자율(수출자), 3:자율(생산자), 4:자율(수입자)]</label>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <th scope="row"><spring:message code="CC_기관명"/></th>
                                                                    <td colspan="3">
                                                                        <input type="text" name="INSTT_NM" id="INSTT_NM" style="width:80%;"/>
                                                                        <a href="javascript:MMB004_01.dialog.openDialog_4();"><img src="/images/sample/btn_sch.gif"/></a>
                                                                    </td>
                                                                    <th scope="row"><spring:message code="CC_종류"/></th>
                                                                    <td colspan="3">
                                                                        <input type="text" name="INSTT_SE_SL" id="INSTT_SE_SL" style="width:40px;"/>
                                                                        <label>[1:국가기관, 2:상공회의소, 9:비국가기관]</label>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <th scope="row"><spring:message code="CC_총순중량"/></th>
                                                                    <td colspan="2">
                                                                        <input type="text" name="TOT_NETWGHT" id="TOT_NETWGHT" class="easyui-numberbox" style="width:98%;"/>
                                                                    </td>
                                                                    <th colspan="5">
                                                                        <label>* 원산지증명서상의 순중량</label><br>
                                                                        <label>* 원산지증빙서류가 "1:원산지증명서"인 경우, 2020.03.05부터 한-칠레 FTA 물품에 대해 협정관세적용신청서의 총중량을 기재하지 않을 수 있음.</label>
                                                                    </th>
                                                                </tr>
                                                                <tr>
                                                                    <th scope="row"><spring:message code="CC_적출국"/></th>
                                                                    <td>
                                                                        <input type="text" name="EXTRAC_NATION_CD" id="EXTRAC_NATION_CD" style="width:60px;"/>
                                                                        <a href="javascript:MMB004_01.dialog.openDialog_1('EXTRAC_NATION_CD2')"><img src="/images/sample/btn_sch.gif"/></a>
                                                                    </td>
                                                                    <th scope="row"><spring:message code="CC_적출항명"/></th>
                                                                    <td>
                                                                        <input type="text" name="EXTRAC_PRT_NM" id="EXTRAC_PRT_NM" style="width:98%;"/>
                                                                    </td>
                                                                    <th scope="row"><spring:message code="CC_출항일"/></th>
                                                                    <td colspan="3">
                                                                        <input type="text" name="TKOFF_DE" id="TKOFF_DE" style="width:95px;"/>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <th scope="row"><spring:message code="CC_환적국"/></th>
                                                                    <td>
                                                                        <input type="text" name="TRSH_NATION_CD" id="TRSH_NATION_CD" style="width:60px;"/>
                                                                        <a href="javascript:MMB004_01.dialog.openDialog_1('TRSH_NATION_CD')"><img src="/images/sample/btn_sch.gif"/></a>
                                                                    </td>
                                                                    <th scope="row"><spring:message code="CC_환적항"/></th>
                                                                    <td>
                                                                        <input type="text" name="TRSH_PRT_NM" id="TRSH_PRT_NM" style="width:98%;"/>
                                                                    </td>
                                                                    <th scope="row"><spring:message code="CC_환적일"/></th>
                                                                    <td>
                                                                        <input type="text" name="TRSH_DE" id="TRSH_DE" style="width:95px;"/>
                                                                    </td>
                                                                    <th scope="row"><spring:message code="CC_환적여부"/></th>
                                                                    <td>
                                                                        <input type="text" name="TRSH_AT" id="TRSH_AT" style="width:40px;"/>
                                                                        &nbsp<label>[Y/N]</label>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <th scope="row"><spring:message code="CC_분할수입"/></th>
                                                                    <td colspan="3">
                                                                        <input type="text" name="PARN_IMP" id="PARN_IMP" style="width:80px;"/>
                                                                        <label>[Y:분할, N:전량]</label>
                                                                    </td>
                                                                    <th scope="row"><spring:message code="CC_분할차수"/></th>
                                                                    <td colspan="3">
                                                                        <input type="text" name="PARN_ODR" id="PARN_ODR" style="width:40px;"/>
                                                                    </td>
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                        <div class="h2_etc">
				                                            <p style="height:9px;"></p>
				                                        </div>
				                                        <table class="gridT" style="margin-bottom:0px;">
				                                            <colgroup>
				                                                <col width="170px" />
				                                                <col width=";" />
                                                                <col width="150px" />
                                                                <col width=";" />
				                                            </colgroup>
				                                            <tbody>
				                                                <tr>
				                                                    <th scope="row"><spring:message code="CC_연결원산지증명서 발급국가"/></th>
				                                                    <td>
				                                                        <input type="text" name="CNNC_CEOR_ISSU_NATION_CD" id="CNNC_CEOR_ISSU_NATION_CD" style="width:60px;"/>
                                                                        <a href="javascript:MMB004_01.dialog.openDialog_1('CNNC_CEOR_ISSU_NATION_CD')"><img src="/images/sample/btn_sch.gif"/></a>
				                                                    </td>
				                                                    <th scope="row"><spring:message code="CC_제3국 송품장 발급국가"/></th>
                                                                    <td>
                                                                        <input type="text" name="THCO_INVC_ISSU_NATION_CD" id="THCO_INVC_ISSU_NATION_CD" style="width:60px;"/>
                                                                        <a href="javascript:MMB004_01.dialog.openDialog_1('THCO_INVC_ISSU_NATION_CD')"><img src="/images/sample/btn_sch.gif"/></a>
                                                                    </td>
				                                                </tr>
				                                                <tr>
                                                                    <th scope="row"><spring:message code="CC_제3국 송품장 발급여부"/></th>
                                                                    <td>
                                                                        <input type="text" name="THCO_INVC_ISSU_AT" id="THCO_INVC_ISSU_AT" style="width:80px;"/>
                                                                        <label>[Y:발급, N:미발급]</label>
                                                                    </td>
				                                                    <th scope="row"><spring:message code="CC_원산지인증 수출자 번호"/></th>
				                                                    <td>
				                                                        <input type="text" name="CARE_NO" id="CARE_NO" style="width:180px;"/>
				                                                    </td>
				                                                </tr>
				                                            </tbody>
				                                        </table>
		                                            </form>
		                                            </div>
		                                            <div id="MMB004_01_div_03" style="display:none;">
		                                            <form id="MMB004_01_form_06" name="MMB004_01_form_06" method="post">
                                                        <input type="hidden" id="COMPANY_CD" name="COMPANY_CD"/>
                                                        <input type="hidden" id="USER_ID" name="USER_ID" value="${_MEMBER.SESSION_USER_ID}"/>
                                                        <input type="hidden" id="IMP_CVTA_APC_RDO_LNE_SN" name="IMP_CVTA_APC_RDO_LNE_SN"/>
                                                        <input type="hidden" id="IMP_DCLRT_LNE_NO" name="IMP_DCLRT_LNE_NO"/>
                                                        <input type="hidden" id="IMP_BCNC_SETUP_SN" name="IMP_BCNC_SETUP_SN" value="${IMP_BCNC_SETUP_SN}"/><!-- 거래처 설정정보 -->
                                                        <input type="hidden" id="STT_ODR" name="STT_ODR" value="${STT_ODR}"/>
                                                        <table class="gridT" style="margin-bottom:0px;">
                                                            <colgroup>
                                                                <col width="170px" />
                                                                <col width=";" />
                                                                <col width="150px" />
                                                                <col width=";" />
                                                            </colgroup>
                                                            <tbody>
                                                                <tr>
                                                                    <th scope="row"><spring:message code="CC_원산지"/></th>
                                                                    <td colspan="3">
                                                                        <input type="text" name="ORPL" id="ORPL" style="width:60px;"/>
                                                                        <a href="javascript:MMB004_01.dialog.openDialog_1('ORPL')"><img src="/images/sample/btn_sch.gif"/></a>
                                                                        <input type="text" name="ORPL_NM" id="ORPL_NM" style="width:180px;"/>
                                                                        <!-- input type="checkbox" name="FTA_AFTFAT_AT" id="FTA_AFTFAT_AT" value="Y"/><label for="UNITY_MODEL_YN"><spring:message code="CC_FTA사후관리 적용"/></label -->
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <th scope="row"><spring:message code="CC_원산지 표시결정"/></th>
                                                                    <td>
                                                                        <input type="text" name="ORPL_COTDOO" id="ORPL_COTDOO" style="width:250px;"/>
                                                                    </td>
                                                                    <th scope="row"><spring:message code="CC_원산지 표시유무"/></th>
                                                                    <td>
                                                                        <input type="text" name="ORPL_AT" id="ORPL_AT" style="width:250px;"/>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <th scope="row"><spring:message code="CC_원산지 방법"/></th>
                                                                    <td>
                                                                        <input type="text" name="ORPL_MTH" id="ORPL_MTH" style="width:250px;"/>
                                                                    </td>
                                                                    <th scope="row"><spring:message code="CC_원산지 면제"/></th>
                                                                    <td>
                                                                        <input type="text" name="ORPL_EXEPT" id="ORPL_EXEPT" style="width:250px;"/>
                                                                    </td>
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                        <div class="h2_etc">
                                                            <p style="height:9px;"></p>
                                                        </div>
                                                        <table class="gridT" style="margin-bottom:0px;">
                                                            <colgroup>
                                                                <col width="170px" />
                                                                <col width=";" />
                                                                <col width="150px" />
                                                                <col width=";" />
                                                            </colgroup>
                                                            <tbody>
                                                                <tr>
                                                                    <th scope="row"><spring:message code="CC_발급번호"/></th>
                                                                    <td>
                                                                        <input type="text" name="ISSU_NO" id="ISSU_NO" style="width:98%;"/>
                                                                    </td>
                                                                    <th scope="row"><spring:message code="CC_원산지기준"/></th>
                                                                    <td>
                                                                        <input type="text" name="COTDOO" id="COTDOO" style="width:250px;"/>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <th scope="row"><spring:message code="CC_발급국가"/></th>
                                                                    <td>
                                                                        <input type="text" name="CEOR_ISU_NATION_SL" id="CEOR_ISU_NATION_SL" style="width:60px;"/>
                                                                        <a href="javascript:MMB004_01.dialog.openDialog_1('CEOR_ISU_NATION_SL')"><img src="/images/sample/btn_sch.gif"/></a>
                                                                        <input type="text" name="CEOR_ISU_NATION_SL_NM" id="CEOR_ISU_NATION_SL_NM" style="width:153px;"/>
                                                                    </td>
                                                                    <th scope="row"><spring:message code="CC_발급담당자"/></th>
                                                                    <td>
                                                                        <input type="text" name="CEOR_ISSU_PINC_NM" id="CEOR_ISSU_PINC_NM" style="width:250px;"/>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <th scope="row"><spring:message code="CC_발급일자"/></th>
                                                                    <td>
                                                                        <input type="text" name="ISSU_DE" id="ISSU_DE" style="width:98%;"/>
                                                                    </td>
                                                                    <th scope="row"><spring:message code="CC_발급지역"/></th>
                                                                    <td>
                                                                        <input type="text" name="CEOR_ISSU_AREA_NM" id="CEOR_ISSU_AREA_NM" style="width:250px;"/>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <th scope="row"><spring:message code="CC_기관명"/></th>
                                                                    <td colspan="3">
                                                                        <input type="text" name="INSTT_NM" id="INSTT_NM" style="width:80%;"/>
                                                                        <a href="javascript:MMB004_01.dialog.openDialog_4();"><img src="/images/sample/btn_sch.gif"/></a>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <th scope="row"  rowspan="2"><spring:message code="CC_분할여부"/></th>
                                                                    <td rowspan="2">
                                                                        <input type="text" name="PARN_IMP" id="PARN_IMP" style="width:80px;"/><br>
                                                                        <label>* 원산지 증명서를 분할신고하는 경우 "Y" 기재</label>
                                                                    </td>
                                                                    <th scope="row"><spring:message code="CC_수량 전체/사용"/></th>
                                                                    <td>
                                                                        <input type="text" name="CEOR_ALL_QY" id="CEOR_ALL_QY" class="easyui-numberbox" style="width:110px;"/> /
                                                                        <input type="text" name="CEOR_USE_QY" id="CEOR_USE_QY" class="easyui-numberbox" style="width:110px;"/>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <th scope="row"><spring:message code="CC_중량 전체/사용"/></th>
                                                                    <td>
                                                                        <input type="text" name="CEOR_ALL_WT" id="CEOR_ALL_WT" class="easyui-numberbox" style="width:110px;"/> /
                                                                        <input type="text" name="CEOR_USE_WT" id="CEOR_USE_WT" class="easyui-numberbox" style="width:110px;"/>
                                                                    </td>
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                        <div class="h2_etc">
                                                            <p style="height:9px;"></p>
                                                        </div>
                                                        <table class="gridT" style="margin-bottom:0px;">
                                                            <colgroup>
                                                                <col width="170px" />
                                                                <col width=";" />
                                                                <col width="150px" />
                                                                <col width=";" />
                                                            </colgroup>
                                                            <tbody>
                                                                <tr>
                                                                    <th scope="row"><spring:message code="CC_제품/원료구분"/></th>
                                                                    <td>
                                                                        <input type="text" name="PRDUCT_MTRAL_SE" id="PRDUCT_MTRAL_SE" style="width:80px;"/>
                                                                        <label>* 원료과세인 경우 반드시 기재</label>
                                                                    </td>
                                                                    <th scope="row"><spring:message code="CC_제품란번호"/></th>
                                                                    <td>
                                                                        <input type="text" name="PRDUCT_LNE_NO" id="PRDUCT_LNE_NO" style="width:60px;"/>
                                                                        <label>* 원료(B)일 경우 필수 기재</label>
                                                                    </td>
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                    </form>
                                                    </div>
                                                </div>
	                                        </div>
	                                    </div>
	                                </div>
                                </div>
                                <div title="3">
                                    <div class="easyui-layout" data-options="fit:true">
                                        <div data-options="region:'north', border:false">
                                            <div class="h2_etc">
                                                <p class="h2"><spring:message code="CC_한중FTA 원산지규격 목록"/></p>
                                                <p class="h2_btn">
                                                    <a href="javascript:MMB004_01.control.updateImpCvtaApcPdoStardInfo();" class="btnMainSave" style="display:none;"><spring:message code="SAVE"/></a>
                                                    <a href="javascript:MMB004_01.event.updateImpCvtaApcPdoStardInfo();" class="btnSearch"><spring:message code="CC_재검색"/></a>
                                                    <a href="javascript:MMB004_01.control.deleteImpCvtaApcPdoStardInfo();" class="btnMainDelete"><spring:message code="DEL"/></a>
                                                    <a href="javascript:MMB004_01.control.insertRenewImpCvtaApcPdoStardInfo();" class="btnMainRestart"><spring:message code="CC_재생성"/></a>
                                                </p>
                                            </div>
                                        </div>
                                        <div data-options="region:'center',border:false">
                                            <div class="easyui-layout" data-options="fit:true">
                                                <form id="MMB004_01_form_04" name="MMB004_01_form_04" method="post">
                                                    <input type="hidden" id="COMPANY_CD" name="COMPANY_CD"/>
                                                    <input type="hidden" id="USER_ID" name="USER_ID" value="${_MEMBER.SESSION_USER_ID}"/>
                                                    <input type="hidden" id="IMP_CVTA_APC_RDO_STARD_SN" name="IMP_CVTA_APC_RDO_STARD_SN"/>
	                                                <div data-options="region:'west', border:false,split:true" style="width:672px;">
	                                                    <div class="easyui-layout" data-options="fit:true">
									                        <!-- div id="MMB004_01_div_01" data-options="region:'north',border:false">
								                                <table class="gridT" style="margin-bottom:0px;">
								                                    <colgroup>
								                                        <col width="100px" />
								                                        <col width="" />
								                                    </colgroup>
								                                    <tbody>
								                                        <tr>
								                                            <th scope="row"><spring:message code="BTN_EXCEL_UPLOAD"/></th>
								                                            <td>
								                                                <input type="file" name="FILEUP" id="FILEUP" style="width:75%;"/>
								                                                <a href="javascript:MMB004_01.event.applyEntrPlan();" class="btnFile"><spring:message code="CC_규격일괄등록"/></a>
								                                            </td>
								                                        </tr>
								                                        <tr>
								                                            <th colspan="2">
								                                                <input type="checkbox" name="REC_RELT" id="REC_RELT"/><label>원산지행번호 01고정</label>
								                                                <input type="checkbox" name="REC_RELT" id="REC_RELT"/><label>원산지행번호 일련번호 동기화</label>
								                                                <input type="checkbox" name="REC_RELT" id="REC_RELT"/><label>규격 수량 0제외</label><br>
								                                                <input type="checkbox" name="REC_RELT" id="REC_RELT"/><label>수입신고규격 소수점4자리 처리</label>
								                                                <label>(처리방식:</label>
								                                                <label><input type="radio" id="FTA_DOC_TYPE" name="FTA_DOC_TYPE" value="1" checked="checked"/><spring:message code="CC_올림"/></label>
								                                                <label><input type="radio" id="FTA_DOC_TYPE" name="FTA_DOC_TYPE" value="2"/><spring:message code="CC_반올림"/></label>
								                                                <label><input type="radio" id="FTA_DOC_TYPE" name="FTA_DOC_TYPE" value="3"/><spring:message code="CC_내림"/></label>
								                                                <label>)</label>
								                                            </th>
								                                        </tr>
								                                    </tbody>
								                                </table>
									                        </div --> <!-- 수입자료 업로드에서 관리되는 자료로 자동 생성되므로 삭제 처리됨 -->
									                        <div data-options="region:'center',border:false">
			                                                    <table id="MMB004_01_grid_02"></table>
									                        </div>
									                    </div>
	                                                </div>
	                                                <div data-options="region:'center',border:false">
                                                        <table class="gridT" style="margin-bottom:0px;">
                                                            <colgroup>
                                                                <col width="120px" />
                                                                <col width=";" />
                                                                <col width="120px" />
                                                                <col width="90px;" />
                                                            </colgroup>
                                                            <tbody>
                                                                <tr>
                                                                    <th scope="row"><spring:message code="CC_일련번호"/></th>
                                                                    <td colspan="3">
                                                                        <input type="text" name="REGIST_NO" id="REGIST_NO" style="width:70px;"/>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <th scope="row"><spring:message code="CC_수입란번호"/></th>
                                                                    <td>
                                                                        <input type="text" name="IMP_LNE_NO" id="IMP_LNE_NO" style="width:50px;"/>
                                                                    </td>
                                                                    <th scope="row"><spring:message code="CC_수입규격번호"/></th>
                                                                    <td>
                                                                        <input type="text" name="IMP_STARD_NO" id="IMP_STARD_NO" style="width:95%;"/>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <th scope="row"><spring:message code="CC_C/O 발행번호"/></th>
                                                                    <td>
                                                                        <input type="text" name="CEOR_ISU_NO" id="CEOR_ISU_NO" style="width:98%;"/>
                                                                    </td>
                                                                    <th scope="row"><spring:message code="CC_C/O 행번호"/></th>
                                                                    <td>
                                                                        <input type="text" name="CEOR_ROW_NO" id="CEOR_ROW_NO" style="width:95%;"/>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <th scope="row"><spring:message code="CC_사용량"/></th>
                                                                    <td>
                                                                        <input type="text" name="USE_QY" id="USE_QY" class="easyui-numberbox" style="width:100px;"/>
                                                                    </td>
                                                                    <th scope="row"><spring:message code="CC_사용단위"/></th>
                                                                    <td>
                                                                        <input type="text" name="USE_QY_UNIT" id="USE_QY_UNIT" style="width:95%;"/>
                                                                    </td>
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                        <div class="h2_etc">
                                                            <p style="height:1px;"></p>
                                                        </div>
                                                        <table class="gridT">
                                                            <colgroup>
                                                                <col width="120px" />
                                                                <col width=";" />
                                                            </colgroup>
                                                            <tbody>
                                                                <tr>
                                                                    <th scope="row"><spring:message code="CC_C/O 총사용량"/></th>
                                                                    <td scope="row">
                                                                        <input type="text" name="CEOR_TOT_QY" id="CEOR_TOT_QY" class="easyui-numberbox" style="width:100px;"/>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <th scope="row"><spring:message code="CC_수입신고 총사용량"/></th>
                                                                    <td scope="row">
                                                                        <input type="text" name="IMPDEC_TOT_QY" id="IMPDEC_TOT_QY" class="easyui-numberbox" style="width:100px;"/>
                                                                    </td>
                                                                </tr>
                                                            </tbody>
                                                        </table>
				                                        <div class="h2_etc">
				                                            <label style="color:red">** 신고서 규격이나 엑셀로 데이터를 올릴 경우 수량(사용량)이 소수점 4자리이면 반올림이 기본입니다. 입력 후 반드시 확인하십시오.</label><br>
				                                            <label>의도하신 값과 다를 경우, 사용량을 저장하시면 됩니다.</label>
				                                        </div>
	                                                </div>
		                                        </form>
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
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-B004_01.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>