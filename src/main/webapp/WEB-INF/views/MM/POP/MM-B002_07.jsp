<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : MM-B002_07.jsp
  * @Description : 해외공급자 조회
  * @Modification Information
  *
  * author YNI-Maker(이메일)
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMB002_07_dailog_01"으로 하셔야 합니다.
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
        <div data-options="region:'north',border:false" class="h2_etc">
            <ul class="tab_area">
                <li id="MMB002_07_divMove01" class="on"><a href="javascript:MMB002_07.ui.divMove('1');"><b><spring:message code="CC_화주거래처"/></b></a></li>
                <li id="MMB002_07_divMove02"><a href="javascript:MMB002_07.ui.divMove('2');"><b><spring:message code="CC_사용거래처"/></b></a></li>
                <li id="MMB002_07_divMove03"><a href="javascript:MMB002_07.ui.divMove('3');"><b><spring:message code="CC_해외공급자"/></b></a></li>
                <p style="text-align:right">
                    <a href="javascript:;" class="btnSelectClick"><spring:message code="CC_관세청 조회"/></a>   
                    <a href="javascript:;" class="btnExlDown"><spring:message code="CC_관세청 다운"/></a>
                </p>
            </ul>
        </div>
        <!-- //검색조건 -->
        <div data-options="region:'center',border:false">
            <div class="easyui-layout" data-options="fit:true">
                <div data-options="region:'north',border:false" class="sch_box2">
                    <div class="sch_boxIn2">
		                <form id="MMB002_07_form_01" method="POST">
		                    <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
		                    <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
		                    <input type="hidden" id="FIELD_ID" name="FIELD_ID" value="${FIELD_ID}"/>
		                    <input type="hidden" id="OWR_CD" name="OWR_CD"/>
		                    <input type="hidden" id="CLIENT_TYPE" name="CLIENT_TYPE"/>
		                    <input type="hidden" id="SEARCH_FLAG" name="SEARCH_FLAG"/>
		                    <table>
		                        <caption>데이타 검색조건 설정</caption>
		                        <colgroup>
		                            <col width="80px" />
		                            <col width="150px" />
		                            <col width="80px" />
		                            <col width="210px" />
		                            <col width=";"/>
		                        </colgroup>
		                        <tbody>
		                            <tr>
		                                <th scope="row"><spring:message code="CODE"/></th>
		                                <td>
		                                    <input type="text" id="BCNC_CD" name="BCNC_CD" search="MMB002_07.control.selectClientList" style="width:130px"/>
		                                </td>
		                                <th scope="row"><spring:message code="EXPNM"/></th>
		                                <td>
		                                    <input type="text" id="BCNC_MTLTY" name="BCNC_MTLTY" search="MMB002_07.control.selectClientList" style="width:180px"/>
		                                </td>
		                                <td class="txt_R">
		                                   <a href="javascript:MMB002_07.control.selectClientList();" class="easyui-linkbutton pop_sch" id="MMB002_07_searchBtn"><spring:message code="SRCH"/></a>
		                                </td>
		                            </tr>
		                        </tbody>
		                    </table>
		                </form>
		            </div>
                </div>
                <div data-options="region:'center',border:false"  style="padding-top:6px;">
                    <div class="easyui-layout" data-options="fit:true">
                        <div data-options="region:'north',border:false" class="h2_etc">
                            <p class="h2"><spring:message code="CC_거래처"/> <spring:message code="LIST"/></p>
		                    <p class="h2_btn">
		                        <a href="javascript:;" class="btnMainDelete"><spring:message code="CC_삭제"/></a>
	                            <form id="MMB002_07_form_02" method="post">
	                                <table class="gridT" style="margin-bottom:0px;">
	                                    <colgroup>
	                                        <col width="100px" />
	                                        <col width="220px" />
	                                        <col width="" />
	                                    </colgroup>
	                                    <tbody>
	                                        <tr>
	                                            <th scope="row"><spring:message code="CC_화주선택"/></th>
				                                <td>
				                                    <input type="text" id="OWR_CD" name="OWR_CD" style="width:30%;"/>
			                                        <input type="text" id="OWR_NM" name="OWR_NM" class="search_readOnly" readonly style="width:53%;"/>
			                                        <a href="javascript:IC1002_01.dialog.openDialog_3('IMPR_ECTMRK')"><img src="/images/sample/btn_sch.gif"/></a>
				                                </td>
	                                            <th scope="row">
	                                                <label><input type="radio" id="CLIENT_TYPE" name="CLIENT_TYPE" onchange="javascript:MMB002_07.control.selectClientList()" value="1" checked="checked"/><spring:message code="CC_납세자"/></label>
	                                                <label><input type="radio" id="CLIENT_TYPE" name="CLIENT_TYPE" onchange="javascript:MMB002_07.control.selectClientList()" value="2"/><spring:message code="CC_수입자"/></label>
	                                            </th>
	                                        </tr>
	                                    </tbody>
	                                </table>
	                            </form>
		                    </p>
                        </div>
                        <div data-options="region:'center',border:false">
		                    <table id="MMB002_07_grid_01"> </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-B002_07.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>