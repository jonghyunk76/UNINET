<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : MM-A005_01.jsp
  * @Description : 자재조회 및 선택
  * @Modification Information
  *
  * author 이재욱
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMA005_01_dailog_01"으로 하셔야 합니다.
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
                <form id="MMA005_01_form_01" method="POST">
                    <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
                    <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
                    <input type="hidden" name="DIVISION_CD" id="DIVISION_CD" value="${DIVISION_CD}"/>
                    <input type="hidden" name="VENDOR_ITEM_STATE" id="VENDOR_ITEM_STATE" value="${VENDOR_ITEM_STATE}"/>
                    <input type="hidden" name="VENDOR_CD" id="VENDOR_CD" value="${VENDOR_CD}"/>
                    <table>
                        <caption>데이타 검색조건 설정</caption>
                        <colgroup>
                            <col width="100x" />
                            <col width="150px" />
                            <col width="130px" />
                            <col width="450px" /> 
                            <col width=";"/>
                        </colgroup>
                        <tbody>
                            <tr>
                                <th scope="row"><spring:message code="TXT_ASSETS_TYPE"/></th>
                                <td>
                                    <input type="text" name="ITEM_TYPE" id="ITEM_TYPE" style="width:138px" search="MMA005_01.control.selectMainList" />
                                </td>
                                <th scope="row"><spring:message code="SHCDT"/><%--조건검색--%></th>
                                <td>
                                    <input type="text" id="schKeyField" name="schKeyField" style="width:110px"/> 
                                    <input type="text" id="schKeyWord" name="schKeyWord" search="MMA005_01.control.selectMainList" style="width:130px;paddig:0;"/>
                                    <input type="text" id="schKeyLike" name="schKeyLike" style="width:80px"/>
                                </td>
                                <td class="txt_R" rowspan="2">
                                    <a href="javascript:MMA005_01.control.selectMainList();" class="easyui-linkbutton pop_sch" id="MMA005_01_searchBtn"><spring:message code="SRCH"/></a>
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
                    <p class="h2_btn">
                        <a href="javascript:MMA005_01.control.selectRowValue();" class="btnPopTick"><spring:message code="SELET"/></a>
                    </p>
                </div>
                <div data-options="region:'center',border:false">
                   <table id="MMA005_01_grid_01"></table>
                </div>
            </div>
        </div>
        
    </div>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-A005_01.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body> 
</html>