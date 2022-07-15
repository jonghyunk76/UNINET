<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : MM-B002_17.jsp
  * @Description : 그리드 필드 조회
  * @Modification Information
  *
  * author YNI-Maker(이메일)
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMB002_17_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>      
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'north',border:false" class="sch_box">
            <div class="sch_boxIn">
                <form id="MMB002_17_form_01" method="POST">
                    <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
	                <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
	                <input type="hidden" id="GRID_ID" name="GRID_ID" value="${GRID_ID}"/>
                    <table>
                        <caption>데이타 검색조건 설정</caption>
                        <colgroup>
                            <col width="120px" />
                            <col width="230px;" />
                            <col width=";"/>
                        </colgroup>
                        <tbody>
                            <tr>
                                <th scope="row"><spring:message code="자료명"/></th>
                                <td>
                                    <input type="text" id="STT_DTA_KEYWORD" name="STT_DTA_KEYWORD" class="easyui-validatebox" search="MMB002_17.control.selectDatagridColumnList" style="width:200px;paddig:0;"/>
                                </td>
                                <td class="txt_R" rowspan="2">
                                   <a href="javascript:MMB002_17.control.selectDatagridColumnList();" class="easyui-linkbutton pop_sch"><spring:message code="SRCH"/><%--조회--%></a>
                                </td>
                            </tr>
                            <tr>
                                <th scope="row"><spring:message code="항목명"/></th>
                                <td>
                                    <input type="text" id="IEM_KEYWORD" name="IEM_KEYWORD" class="easyui-validatebox" search="MMB002_17.control.selectDatagridColumnList" style="width:200px;paddig:0;"/>
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
                        <a href="javascript:MMB002_17.control.applySelectData();" class="btnApply"><spring:message code="SELET"/></a>
                    </p>
                </div>
                <div data-options="region:'center',border:false">
                   <table id="MMB002_17_grid_01"> </table>
                </div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-B002_17.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>