<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : ST-R002_01.jsp
  * @Description : 서비스 목록 조회
  * @Modification Information
  *
  * author YNI-Maker(이메일)
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "STR002_01_dailog_01"으로 하셔야 합니다.
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
                <form id="STR002_01_form_01" method="POST">
                    <input type="hidden" id="flag" name="flag" value="${flag}"/>
                    <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
                    <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${_MEMBER.SESSION_COMPANY_CD}"/>
                    <input type="hidden" id="USER_ID" name="USER_ID" value="${_MEMBER.SESSION_USER_ID}"/>
                    <table>
                        <caption>데이타 검색조건 설정</caption>
                        <colgroup>
                            <col width="120px" />
                            <col width="120px" />
                            <col width="120px" />
                            <col width="120px" />
                            <col width=";"/>
                        </colgroup>
                        <tbody>
                            <tr>
                                <th scope="row"><spring:message code="CC_서비스명"/></th>
                                <td colspan="3">
                                    <input type="text" id="schKeyWord" name="schKeyWord" class="easyui-validatebox"/>
                                    <input type="text" id="schKeyLike" name="schKeyLike" style="width:120px;"/>
                                </td>
                                <td class="txt_R">
                                   <a href="javascript:STR002_01.control.selectMainList();" class="easyui-linkbutton pop_sch"><spring:message code="SRCH"/></a>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
        <!-- //검색조건 -->
        <div data-options="region:'center',border:false" style="height:100%;padding-top:12px;">
            <div class="easyui-layout" data-options="fit:true">
                <div data-options="region:'north',border:false" class="h2_etc">
                    <p class="h2"><spring:message code="CC_서비스 목록"/></p>
                    <p class="h2_btn">
                        <a href="javascript:STR002_01.dialog.openDialog_0('insert');" class="btnNewRegiste"><spring:message code="REGER"/></a>
                        <a href="javascript:STR002_01.dialog.openDialog_0('update');" class="btnEdit"><spring:message code="MOD"/></a>
                        <a href="javascript:STR002_01.excel.excelDownload_1();" class="btnExlDown"><spring:message code="EXCEL, DOWN"/></a>
                    </p>
                </div>
                <div data-options="region:'center',border:false">
                   <table id="STR002_01_grid_01"> </table>
                </div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/RS/ST/ST-R002_01.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>