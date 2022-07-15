<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : MM-B007_03.jsp
  * @Description : 수출 란입력 화면
  * @Modification Information
  *
  * author YNI-Maker(이메일)
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMB007_03_dailog_01"으로 하셔야 합니다.
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
            <p class="h2"><spring:message code="CC_란입력 목록"/></p>
            <p class="h2_btn">
                <a href="javascript:MMB007_03.control.updateExportLneInputInfo();" class="btnSave"><spring:message code="SAVE"/></a>
                <a href="javascript:MMB007_03.dialog.closeThisDialog();" class="btnPopClose"><spring:message code="CLOSE"/></a>
            </p>
        </div>
        <div data-options="region:'center',border:false" style="padding-top:3px;">
            <div class="easyui-layout" data-options="fit:true">
                <div data-options="region:'north',border:false" class="h2_etc">
                    <form id="MMB007_03_form_01" method="post">
                        <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
                        <input type="hidden" id="UPDATE_BY" name="UPDATE_BY" value="${_MEMBER.SESSION_USER_ID}"/>
                        <input type="hidden" id="EXP_BCNC_SETUP_SN" name="EXP_BCNC_SETUP_SN" value="${EXP_BCNC_SETUP_SN}"/>
                        <input type="hidden" id="STT_ODR" name="STT_ODR" value="${STT_ODR}"/>
                        <input type="hidden" id="CC_TYPE" name="CC_TYPE" value="${CC_TYPE}"/>
                        <input type="hidden" id="gridData" name="gridData"/>
                        <input type="hidden" id="PRPDVS_STDR" name="PRPDVS_STDR"/>
                        <input type="hidden" id="AVG_NETWGHT" name="AVG_NETWGHT"/>
                        <input type="hidden" id="DCLRT_QY" name="DCLRT_QY"/>
                        <input type="hidden" id="DCLRT_PKNG_CO" name="DCLRT_PKNG_CO"/>
                        <input type="hidden" id="DCLRT_AMT" name="DCLRT_AMT"/>
                        <table class="gridT" style="margin-bottom:0px;">
                            <colgroup>
                                <col width="100px;" />
                                <col width="480px;" />
                                <col width="100px;" />
                                <col width="" />
                            </colgroup>
                            <tbody>
                                <tr>
                                    <th scope="row"><spring:message code="CC_거래품명"/></th>
                                    <td>
                                        <label><input type="radio" id="DEAL_PRDNM_STDR" name="DEAL_PRDNM_STDR" onclick="javascript:MMB007_03.control.updateExportLneDealName();" value="1" checked="checked"/><spring:message code="CC_첫번째행 규격1"/></label>
                                        <label><input type="radio" id="DEAL_PRDNM_STDR" name="DEAL_PRDNM_STDR" onclick="javascript:MMB007_03.control.updateExportLneDealName();" value="2"/><spring:message code="CC_첫번째행 규격2"/></label>
                                        <label><input type="radio" id="DEAL_PRDNM_STDR" name="DEAL_PRDNM_STDR" onclick="javascript:MMB007_03.control.updateExportLneDealName();" value="3"/><spring:message code="CC_첫번째행 규격3"/></label>
                                        <a href="javascript:MMB007_03.dialog.openDialog_1();" class="btnNewWindow" style="display:none;background-color:#fff;height:17px;"><spring:message code="CC_업체별 이력 불러오기"/></a>
                                    </td>
                                    <th scope="row"><spring:message code="CC_순중량 안분"/></th>
                                    <td>
                                        <label><spring:message code="CC_순중량"/> : </label><input type="text" id="TOT_NETWGHT" name="TOT_NETWGHT" class="easyui-numberbox" style="width:80px"/><label> KG</label>
                                        <span>
                                         <a href="javascript:MMB007_03.control.updateExportLneDealNetwght('Q');" class="btnToolCalculator"><spring:message code="CC_수량기준 안분"/></a>
                                         <a href="javascript:MMB007_03.control.updateExportLneDealNetwght('P');" id="MMB007_03_href_01" class="btnToolCalculator" ><spring:message code="CC_C/T기준 안분"/></a>
                                         <a href="javascript:MMB007_03.control.updateExportLneDealNetwght('A');" class="btnToolCalculator"><spring:message code="CC_금액기준 안분"/></a>
                                     </span>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </form>
                </div>
                <div data-options="region:'center',border:false">
                    <table id="MMB007_03_grid_01"> </table>
                </div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-B007_03.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>