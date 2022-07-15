<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : MM-A013_01.jsp
  * @Description : FTA 협정 조회
  * @Modification Information
  *
  * author carlos
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMA013_01_dailog_01"으로 하셔야 합니다.
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
                <form id="MMA013_01_form_01" method="POST">
                    <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
                    <input type="hidden" id="SESSION_COMPANY_CD" name="SESSION_COMPANY_CD" value="${SESSION_COMPANY_CD}"/>
                    <input type="hidden" id="SESSION_USER_ID" name="SESSION_USER_ID" value="${SESSION_USER_ID}"/>
                    <input type="hidden" id="DEFAULT_LANGUAGE" name="DEFAULT_LANGUAGE" value="${DEFAULT_LANGUAGE}"/>
                    <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${SESSION_COMPANY_CD}"/>
                    <table>
                        <caption><spring:message code="TXT_DATA_SEARCH_CONDITION"/><%--데이타 검색조건 설정--%></caption>
                        <colgroup>
                            <col width="120px"/>
                            <col width="470px"/>
                            <col width=";"/>
                        </colgroup>
                        <tbody>
                            <tr>
                                <th scope="row" class="point"><spring:message code="SRCH,CNDIT"/><%--조회조건--%></th>
                                <td>
                                    <input type="text" id="schKeyField" name="schKeyField" size="18"/> 
                                    <input type="text" id="schKeyWord" name="schKeyWord" value="${schKeyWord}" class="easyui-validatebox" 
                                           search="MMA013_01.control.selectScheduleList" size="15"/>
                                    <input type="text" id="schKeyLike" name="schKeyLike" size="12"/>
                                </td>
                                <td class="txt_R">
                                    <a href="javascript:MMA013_01.control.selectMainList();" 
                                       class="easyui-linkbutton pop_sch" id="MMA013_01_searchBtn"><spring:message code="SRCH"/><%--조회--%></a>
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
                    <p class="h2"><spring:message code="TXT_FTA_LIST"/><%--FTA 협정 내역--%></p>
                    <p class="h2_btn">
                        <a href="javascript:MMA013_01.control.selectRowValue();" class="btnPopTick"><spring:message code="SELET"/><%--선택--%></a>
                    </p>
                </div>
                <div data-options="region:'center',border:false">
                   <table id="MMA013_01_grid_01"> </table>
                </div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-A013_01.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body> 
</html>