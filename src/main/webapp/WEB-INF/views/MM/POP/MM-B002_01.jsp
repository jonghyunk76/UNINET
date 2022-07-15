<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : MM-B002_01.jsp
  * @Description : 통관계획 설정
  * @Modification Information
  *
  * author YNI-Maker(이메일)
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMB002_01_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>      
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center',border:false" class="width50" style="padding-top:6px;">
            <div class="easyui-layout" data-options="fit:true">
                <div data-options="region:'north',border:false" class="h2_etc">
                    <p class="h2"><spring:message code="CC_코드 리스트"/></p>
                </div>
                <div data-options="region:'center',border:false" style="padding-top:3px;">
                   <table id="MMB002_01_grid_01"> </table>
                </div>
            </div>
        </div>
        <div data-options="region:'east',border:false" class="width50" style="padding-top:6px;">
            <div class="easyui-layout" data-options="fit:true">
                <div data-options="region:'west',border:false" style="width:30px;">
                    <p style="box-sizing:border-box;text-align:center;padding:130px 0 0 0;">
                        <a href="javascript:MMB002_01.ui.hiddenColunm();"><img src="/images/sample/btn_arrR.gif"/></a><br><br>
                        <a href="javascript:MMB002_01.ui.showColunm();"><img src="/images/sample/btn_arrL.gif"/></a>
                    </p>
                </div>
                <div data-options="region:'center',border:false">
                    <div class="easyui-layout" data-options="fit:true">
		                <div data-options="region:'north',border:false" class="h2_etc">
		                    <p class="h2"><spring:message code="CC_통관계획 설정"/></p>
		                    <p class="h2_btn">
		                        <a href="javascript:MMB002_01.ui.moveRow(-1);" class="btnArrowUp"><spring:message code="UP"/></a>
                                <a href="javascript:MMB002_01.ui.moveRow(1);" class="btnArrowDown"><spring:message code="DOW"/></a>
		                        <a href="javascript:MMB002_01.event.applyEntrPlan();" class="btnPopTick"><spring:message code="SETTING"/></a>
		                    </p>
		                </div>
		                <div data-options="region:'center',border:false">
		                   <div class="easyui-layout" data-options="fit:true">
                                <div data-options="region:'north',border:false" class="h2_etc">
                                    <form id="MMB002_01_form_01" name="MMB002_01_form_01" method="post">
                                        <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
                                        <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
                                        <table class="gridT" style="margin-bottom:0px;">
                                            <colgroup>
                                                <col width="100px" />
                                                <col width="" />
                                            </colgroup>
                                            <tbody>
                                                <tr>
                                                    <th scope="row"><spring:message code="CC_설정값"/></th>
                                                    <td>
                                                        <input type="text" name="ENTR_PLAN" id="ENTR_PLAN" class="input_readOnly" readonly style="width:98%;"/>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </form>
                                </div>
                                <div data-options="region:'center',border:false">
		                            <table id="MMB002_01_grid_02"> </table>
                                </div>
                            </div>
		                </div>
		            </div>
		        </div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-B002_01.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>