<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : MM-B002_09.jsp
  * @Description : 브랜드 관리
  * @Modification Information
  *
  * author YNI-Maker(이메일)
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMB002_09_dailog_01"으로 하셔야 합니다.
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
	            <form id="MMB002_09_form_01" method="POST">
	                <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
	                <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
	                <input type="hidden" id="USER_ID" name="USER_ID" value="${_MEMBER.SESSION_USER_ID}"/>
	                <input type="hidden" id="gridData" name="gridData"/>
	                <table>
	                    <caption>데이타 검색조건 설정</caption>
	                    <colgroup>
	                        <col width="80px" />
	                        <col width="470px" />
	                        <col width=";"/>
	                    </colgroup>
	                    <tbody>
	                        <tr>
	                            <th scope="row"><spring:message code="SHCDT"/><%--조건검색--%></th>
                                <td>
                                    <input type="text" id="schKeyField" name="schKeyField" style="width:130px"/>
                                    <input type="text" id="schKeyWord" name="schKeyWord" class="easyui-validatebox" search="MMB002_09.control.selectCommonBrandList" style="width:150px;"/>
                                    <input type="text" id="schKeyLike" name="schKeyLike" style="width:80px"/>
                                </td>
	                            <td class="txt_R">
	                               <a href="javascript:MMB002_09.control.selectCommonBrandList();" class="easyui-linkbutton pop_sch" id="MMB002_09_searchBtn"><spring:message code="SRCH"/></a>
	                            </td>
	                        </tr>
	                    </tbody>
	                </table>
	            </form>
	        </div>
        </div>
        <!-- //검색조건 -->
        <div data-options="region:'center',border:false" style="padding-top:6px;">
            <div class="easyui-layout" data-options="fit:true">
                <div data-options="region:'north',border:false" class="h2_etc">
                    <p class="h2"><spring:message code="CC_브랜드 리스트"/></p>
                    <p class="h2_btn">
                        <a href="javascript:MMB002_09.control.updateCommonBrandInfo();" class="btnMainSave"><spring:message code="SAVE"/></a>
                        <a href="javascript:MMB002_09.control.deleteCommonBrandInfo();" class="btnMainDelete"><spring:message code="CC_일괄삭제"/></a>
                    </p>
                </div>
                <div data-options="region:'center',border:false" style="padding-top:3px;">
                    <div class="easyui-layout" data-options="fit:true">
                        <div data-options="region:'north',border:false">
                            <table class="gridT" style="margin-bottom:0px;">
		                        <tbody>
		                            <th>
		                                <a href="javascript:MMB002_09.ui.insertRow();" class="btnMoreInfo"><spring:message code="ADDRW"/></a>
                                        <a href="javascript:MMB002_09.ui.deleteRow();" class="btnMinusInfo"><spring:message code="DELRW"/></a>
		                            </th>
		                        </tbody>
		                    </table>
                        </div>
                        <div data-options="region:'center',border:false" style="padding-top:0px;">
		                   <table id="MMB002_09_grid_01"> </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-B002_09.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>