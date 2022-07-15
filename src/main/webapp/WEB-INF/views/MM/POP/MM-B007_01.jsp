<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : MM-B007_01.jsp
  * @Description : 수입 란입력 화면
  * @Modification Information
  *
  * author YNI-Maker(이메일)
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMB007_01_dailog_01"으로 하셔야 합니다.
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
            <p class="h2">
                <spring:message code="CC_란입력 목록"/>
            </p>
            <label> (특정 셀 선택하고 적용할 행을 체크한 후 <a class="btnTextSync"></a>을 클릭하면 체크한 행의 셀값이 덮어쓰기 됩니다.)</label>
            <p class="h2_btn">
                <a href="javascript:MMB007_01.control.updateImportLneInputInfo();" class="btnSave"><spring:message code="SAVE"/></a>
                <a href="javascript:MMB007_01.dialog.closeThisDialog();" class="btnPopClose"><spring:message code="CLOSE"/></a>
            </p>
        </div>
        <div data-options="region:'center',border:false" style="padding-top:3px;">
            <div class="easyui-layout" data-options="fit:true">
                <div data-options="region:'north',border:false" class="h2_etc">
                    <form id="MMB007_01_form_01" method="post">
                        <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
                        <input type="hidden" id="UPDATE_BY" name="UPDATE_BY" value="${_MEMBER.SESSION_USER_ID}"/>
                        <input type="hidden" id="IMP_BCNC_SETUP_SN" name="IMP_BCNC_SETUP_SN" value="${IMP_BCNC_SETUP_SN}"/>
                        <input type="hidden" id="STT_ODR" name="STT_ODR" value="${STT_ODR}"/>
                        <input type="hidden" id="CC_TYPE" name="CC_TYPE" value="${CC_TYPE}"/>
                        <input type="hidden" id="gridData" name="gridData"/>
                        <input type="hidden" id="PRPDVS_STDR" name="PRPDVS_STDR"/>
                        <input type="hidden" id="AVG_NETWGHT" name="AVG_NETWGHT"/>
                        <input type="hidden" id="DCLRT_QY" name="DCLRT_QY"/>
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
                                        <label><input type="radio" id="DEAL_PRDNM_STDR" name="DEAL_PRDNM_STDR" onclick="javascript:MMB007_01.control.updateImportLneDealName();" value="1" checked="checked"/><spring:message code="CC_첫번째행 규격1"/></label>
                                        <label><input type="radio" id="DEAL_PRDNM_STDR" name="DEAL_PRDNM_STDR" onclick="javascript:MMB007_01.control.updateImportLneDealName();" value="2"/><spring:message code="CC_첫번째행 규격2"/></label>
                                        <label><input type="radio" id="DEAL_PRDNM_STDR" name="DEAL_PRDNM_STDR" onclick="javascript:MMB007_01.control.updateImportLneDealName();" value="3"/><spring:message code="CC_첫번째행 규격3"/></label>
                                        <a href="javascript:MMB007_01.dialog.openDialog_2();" class="btnNewWindow" style="display:none;background-color:#fff;height:17px;""><spring:message code="CC_업체별 이력 불러오기"/></a>
                                    </td>
                                    <th scope="row"><spring:message code="CC_순중량 안분"/></th>
                                    <td>
                                        <label><spring:message code="CC_순중량"/> : </label><input type="text" id="TOT_NETWGHT" name="TOT_NETWGHT" class="easyui-numberbox" style="width:80px"/><label> KG</label>
                                        <span>
                                         <a href="javascript:javascript:MMB007_01.control.updateImportLneDealNetwght('L');" class="btnToolCalculator"><spring:message code="CC_란기준 안분"/></a>
                                         <a href="javascript:MMB007_01.control.updateImportLneDealNetwght('Q');" class="btnToolCalculator"><spring:message code="CC_수량기준 안분"/></a>
                                         <a href="javascript:MMB007_01.control.updateImportLneDealNetwght('A');" class="btnToolCalculator"><spring:message code="CC_금액기준 안분"/></a>
                                     </span>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </form>
                </div>
                <div data-options="region:'center',border:false">
                    <table id="MMB007_01_grid_01"> </table>
                </div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-B007_01.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>