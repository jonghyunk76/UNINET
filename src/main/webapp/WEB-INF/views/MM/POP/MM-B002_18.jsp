<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : MM-B002_18.jsp
  * @Description : 해외 원산지 발행기관 조회
  * @Modification Information
  *
  * author YNI-Maker(이메일)
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMB002_18_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>      
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'north',border:false" class="sch_box2">
            <div class="sch_boxIn2">
                <form id="MMB002_18_form_01" method="POST">
                    <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
                    <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
                    <input type="hidden" id="FIELD_ID" name="FIELD_ID" value="${FIELD_ID}"/>
                    <table>
                        <caption>데이타 검색조건 설정</caption>
                        <colgroup>
                            <col width="120px"/>
                            <col width="400px"/>
                            <col width=";"/>
                        </colgroup>
                        <tbody>
                            <tr>
                                <th scope="row"><spring:message code="SRCH,CNDIT"/><%--조회조건--%></th>
                                <td>
                                    <input type="text" id="schKeyField" name="schKeyField" style="width:130px"/>
                                    <input type="hidden" id="multiData" name="multiData"/>
                                    <span id="MMB002_01_tool_01" class="grid" onclick="javascript:MMB002_01.tool.showTooltip();" style="width:19px;display:none;"></span>
                                    <input type="text" id="schKeyWord" name="schKeyWord" class="easyui-validatebox" search="MMB002_18.control.selectOverseaIssueOrganList" style="width:150px;padding-left:20px;"/>
                                    <input type="text" id="schKeyLike" name="schKeyLike" style="width:80px"/>
                                </td>                              
                                <td class="txt_R">
                                    <a href="javascript:MMB002_18.control.selectOverseaIssueOrganList();" class="easyui-linkbutton pop_sch" id="MMB002_01_searchBtn"><spring:message code="SRCH"/><%--조회--%></a>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
        <div data-options="region:'center',border:false" style="height:100%;padding-top:6px;">
            <div class="easyui-layout" data-options="fit:true">
                <div data-options="region:'north',border:false" class="h2_etc">
                    <p class="h2"><spring:message code="CC_필드 목록"/></p>
                    <p class="h2_btn">
                        <a href="javascript:MMB002_18.control.applySelectData();" class="btnApply"><spring:message code="SELET"/></a>
                    </p>
                </div>
                <div data-options="region:'center',border:false">
                   <table id="MMB002_18_grid_01"> </table>
                </div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-B002_18.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>