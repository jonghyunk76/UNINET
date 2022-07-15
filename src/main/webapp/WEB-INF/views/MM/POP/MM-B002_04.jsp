<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : MM-B002_04.jsp
  * @Description : 공통표준코드 조회
  * @Modification Information
  *
  * author YNI-Maker(이메일)
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMB002_04_dailog_01"으로 하셔야 합니다.
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
	            <form id="MMB002_04_form_01" method="POST">
	                <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
	                <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
	                <input type="hidden" id="CATEGORY_CD" name="CATEGORY_CD" value="${CATEGORY_CD}"/>
	                <input type="hidden" id="FIELD_ID" name="FIELD_ID" value="${FIELD_ID}"/>
	                <input type="hidden" id="TXT_VALUE1" name="TXT_VALUE1" value="${TXT_VALUE1}"/>
	                <input type="hidden" id="TXT_VALUE2" name="TXT_VALUE2" value="${TXT_VALUE2}"/>
	                <input type="hidden" id="TXT_VALUE3" name="TXT_VALUE3" value="${TXT_VALUE3}"/>
	                <input type="hidden" id="TXT_VALUE4" name="TXT_VALUE4" value="${TXT_VALUE4}"/>
	                <input type="hidden" id="TXT_VALUE5" name="TXT_VALUE5" value="${TXT_VALUE5}"/>
	                <table>
	                    <caption>데이타 검색조건 설정</caption>
	                    <colgroup>
	                        <col width="80px" />
	                        <col width="100px" />
	                        <col width="100px" />
                            <col width="150px" />
	                        <col width=";"/>
	                    </colgroup>
	                    <tbody>
	                        <tr>
	                            <th scope="row"><spring:message code="CODE"/></th>
	                            <td>
	                                <input type="text" id="CODE" name="CODE" search="MMB002_04.control.selectStandardCode" style="width:90px"/>
	                            </td>
	                            <th scope="row"><spring:message code="CC_코드 명"/></th>
	                            <td>
	                                <input type="text" id="CODE_NAME" name="CODE_NAME" search="MMB002_04.control.selectStandardCode" style="width:130px"/>
	                            </td>
	                            <td class="txt_R">
	                               <a href="javascript:MMB002_04.control.selectStandardCode();" class="easyui-linkbutton pop_sch" id="MMB002_04_searchBtn"><spring:message code="SRCH"/></a>
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
                    <p class="h2"><spring:message code="CODE_LIST"/></p>
                    <p class="h2_btn">
                        <a href="javascript:MMB002_04.control.selectRowValue('Y');" class="btnPopTick"><spring:message code="SELET"/></a>
                        <a href="javascript:MMB002_04.control.selectRowValue('N');" class="btnPopTick"><spring:message code="NOSEL"/></a>
                    </p>
                </div>
                <div data-options="region:'center',border:false" style="padding-top:3px;">
		            <table id="MMB002_04_grid_01"> </table>
                </div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-B002_04.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>