<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : SM-7529_01.jsp
  * @Description : datagrid db 설정 데모버전
  * @Modification Information
  *
  * author YNI-Maker(이메일)
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "SM7529_01_dailog_01"으로 하셔야 합니다.
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
                <form id="SM7529_01_form_02" name="SM7529_01_form_02" method="post">
                    <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${_MEMBER.SESSION_COMPANY_CD}"/>
                    <table>
                        <caption><spring:message code="TXT_DATA_SEARCH_CONDITION"/><%--데이타 검색조건 설정--%></caption>
                        <colgroup>
                            <col width="130px" />
                            <col width="300px" /> 
                            <col width=";"/>
                        </colgroup>
                        <tbody>
                            <tr>
                                <th scope="row"><spring:message code="CC_Session Key"/><%--조건검색--%></th>
                                <td>
                                    <input type="text" id="SESSION_KEY" name="SESSION_KEY" search="SM7529_01.control.selectWebSocketList" style="width:96%"/>
                                    <span style="display:none;"><input type="text" id="COMPANY_CD" name="COMPANY_CD" value="${_MEMBER.SESSION_COMPANY_CD}" style="width:0px;"/></span>
                                </td>
                                <td class="txt_R">
                                   <a href="javascript:SM7529_01.control.selectWebSocketList();" class="easyui-linkbutton pop_sch"><spring:message code="SRCH"/><%--조회--%></a>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
        <div data-options="region:'center',border:false" style="height:100%;padding-top:12px;">
            <div class="easyui-layout" data-options="fit:true">
                <div data-options="region:'north',border:false" class="h2_etc">
                    <p class="h2"><spring:message code="EXP, INVIC, LIST"/><%--수출 인보이스--%></p>
                    <p class="h2_btn">
                        <a href="javascript:SM7529_01.control.removeUserWebSocket();" class="btnCheckEdit"><spring:message code="CC_Socket 닫기"/></a>
                        <a href="javascript:SM7529_01.control.removeAllWebSocket();" class="btnCleaning"><spring:message code="CC_전체 초기화"/></a>
                     </p>
                </div>
                <div data-options="region:'center',border:false">
                   <form id="SM7529_01_form_01" method="post">
                       <input type="hidden" id="gridData" name="gridData"/>
                   </form>
                   <table id="SM7529_01_grid_01"> </table>
                </div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/FM/SM/SM-7529_01.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>