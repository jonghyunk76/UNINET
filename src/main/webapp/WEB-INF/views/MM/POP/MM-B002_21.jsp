<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : MM-B002_21.jsp
  * @Description : 운수기관 조회
  * @Modification Information
  *
  * author YNI-Maker(이메일)
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMB002_21_dailog_01"으로 하셔야 합니다.
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
	            <form id="MMB002_21_form_01" method="POST">
	                <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
	                <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
	                <input type="hidden" id="FIELD_ID" name="FIELD_ID" value="${FIELD_ID}"/>
	                <input type="hidden" id="ORDER_BY_TYPE" name="ORDER_BY_TYPE"/>
	                <table>
	                    <caption>데이타 검색조건 설정</caption>
                        <colgroup>
                        	<col width="100px" />
                        	<col width="120px" />
                            <col width="100px" />
                            <col width="420px" />
                            <col width=";" />
                        </colgroup>
	                    <tbody>
                            <tr>
			                    <th scope="row"><spring:message code="CC_운수기관구분"/></th>
	                            <td>
	                                <input type="text" id="TRAT_INSTT_SE" name="TRAT_INSTT_SE" style="width:100px;"/>
	                            </td>                             
                                <th scope="row"><spring:message code="SHCDT"/><%--조건검색--%></th>
                                <td>
                                    <input type="text" id="schKeyField" name="schKeyField" style="width:110px"/>
                                    <input type="text" id="schKeyWord" name="schKeyWord" class="easyui-validatebox" search="MMB002_21.control.selectAgencyList" style="width:150px;"/>
                                    <input type="text" id="schKeyLike" name="schKeyLike" style="width:80px"/>
                                </td>
                                <td align="right">
                                    <a href="javascript:MMB002_21.control.selectAgencyList();" class="easyui-linkbutton pop_sch" id="MMB002_21_searchBtn"><spring:message code="SRCH"/></a>
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
                    <p class="h2"><spring:message code="CC_운수기관"/> <spring:message code="LIST"/></p>
                    <p class="h2_btn">
                        <a href="javascript:MMB002_21.control.selectRowValue();" class="btnPopTick"><spring:message code="SELET"/></a>
                    </p>
                </div>
                <div data-options="region:'center',border:false" style="padding-top:3px;">
                    <div class="easyui-layout" data-options="fit:true">
                        <div data-options="region:'center',border:false">
		                    <table id="MMB002_21_grid_01"> </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-B002_21.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>