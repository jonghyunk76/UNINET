<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : MM-A033_01.jsp
  * @Description : 고객사 품목 조회
  * @Modification Information
  *
  * author carlos
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMA033_01_dailog_01"으로 하셔야 합니다.
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
                <form id="MMA033_01_form_01" name="MMA033_01_form_01" method="post">
                    <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
                    <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
                    <input type="hidden" id="USER_ID" name="USER_ID" value="${_MEMBER.SESSION_USER_ID}"/>
                    <input type="hidden" id="PARENT_COMPANY_CD" name="PARENT_COMPANY_CD" value="${_MEMBER.SESSION_PARENT_COMPANY_CD}"/>
                    <table>
                        <caption><spring:message code="TXT_DATA_SEARCH_CONDITION"/><%--데이타 검색조건 설정--%></caption>
                        <colgroup>
                            <col width="110px" />
                            <col width="100px" />
                            <col width="130px" />
                            <col width="470px" />
                            <col width=";"/>
                        </colgroup>
                        <tbody>
                            <tr>
                                <th scope="row"><spring:message code="SALE, TYPE"/></th>
	                            <td>
	                                <input type="text" name="CUSTOMER_SALES_TYPE" id="CUSTOMER_SALES_TYPE" value="${CUSTOMER_SALES_TYPE}" style="width:90px;"/>
	                            </td>
                                <th scope="row"><spring:message code="SHCDT"/><%--조건검색--%></th>
                                <td>
                                    <input type="text" id="schKeyField" name="schKeyField" style="width:100px"/> 
                                    <input type="text" id="schKeyWord" name="schKeyWord" class="easyui-validatebox" search="MMA033_01.control.selectMainList" style="width:150px;paddig:0;"/>
                                    <input type="text" id="schKeyLike" name="schKeyLike" style="width:80px"/>
                                </td>
                                <td class="txt_R">
                                   <a href="javascript:MMA033_01.control.selectMainList();" class="easyui-linkbutton pop_sch" id="MMA033_01_searchBtn"><spring:message code="SRCH"/><%--조회--%></a>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
        <!-- //검색조건 -->
        <div data-options="region:'center',border:false" style="height:100%;padding-top:6px;">
            <div class="easyui-layout" data-options="fit:true">
                <div data-options="region:'north',border:false" class="h2_etc">
                    <p class="h2"><spring:message code="CSTMR, LTIT, LIST"/></p>
                    <p class="h2_btn">
                        <a href="javascript:MMA033_01.control.selectRowValue();" class="btnPopTick"><spring:message code="SELET"/><%--선택--%></a>
                    </p>
                </div>
                <div data-options="region:'center',border:false">
                   <table id="MMA033_01_grid_01"> </table>
                </div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-A033_01.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body> 
</html>