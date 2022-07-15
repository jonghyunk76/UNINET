<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : SM-7012_01.jsp
  * @Description : 게시판 리스트
  * @Modification Information
  *
  * author YNI-Maker(이메일)
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "SM7012_01_dialog_01"으로 하셔야 합니다.
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
                <form id="SM7012_01_form_01" name="SM7012_01_form_01" method="POST">
                    <input type="hidden" id="DEFAULT_LANGUAGE" name="DEFAULT_LANGUAGE" value="${_MEMBER.SESSION_DEFAULT_LANGUAGE}"/>
                    <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${_MEMBER.SESSION_COMPANY_CD}"/>
                    <input type="hidden" id="USER_ID" name="SESSION_USER_ID" value="${_MEMBER.SESSION_USER_ID}"/>
                    <input type="hidden" id="USER_NAME" name="USER_NAME" value="${_MEMBER.SESSION_USER_NAME}"/>
                    <input type="hidden" id="BOARD_TYPE" name="BOARD_TYPE" value="Q"/>
                    <table>
                        <caption>데이타 검색조건 설정</caption>
                        <colgroup>
                            <col width="120px"/>
                            <col width="420px"/>
                            <col width=";"/>
                        </colgroup>
                        <tbody>
                            <tr>
                                <th scope="row"><spring:message code="SHCDT"/><%--조건검색--%></th>
                                <td>
                                    <input type="text" id="schKeyField" name="schKeyField" style="width:110px;"/> 
                                    <input type="text" id="schKeyWord" name="schKeyWord" search="SM7012_01.control.selectQnAList" style="width:130px;paddig:0;"/>
                                    <input type="text" id="schKeyLike" name="schKeyLike" style="width:80px;"/>
                                </td>
                                <td class="txt_R">
                                    <a href="javascript:SM7012_01.control.selectQnAList();" class="easyui-linkbutton pop_sch" id="SM7012_01_searchBtn">
                                        <spring:message code="SRCH"/>
                                    </a>
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
                    <p id="SM7012_01_span_01" class="h2"></p>
                    <p class="h2_btn">
                        <a href="javascript:SM7012_01.dialog.openDialog_1('insert');" class="btnNewRegiste"><spring:message code="REGER"/></a>
                        <a href="javascript:SM7012_01.dialog.openDialog_1('update');" class="btnEdit"><spring:message code="MOD"/></a>
                    </p>
                </div>
                <div data-options="region:'center',border:false">
                   <table id="SM7012_01_grid_01"> </table>
                </div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/FM/SM/SM-7012_01.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>