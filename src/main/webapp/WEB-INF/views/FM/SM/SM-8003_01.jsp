<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : SM-8003_01.jsp
  * @Description : Python 실행
  * @Modification Information
  *
  * author YNI-Maker(이메일)
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "SM8003_01_dialog_01"으로 하셔야 합니다.
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
        <div data-options="region:'north',border:false" class="sch_box3">
            <div class="sch_boxIn3">
                <form id="SM8003_01_form_01" name="SM8003_01_form_01" method="POST">
                    <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${_MEMBER.SESSION_COMPANY_CD}"/>
                    <input type="hidden" id="USER_ID" name="SESSION_USER_ID" value="${_MEMBER.SESSION_USER_ID}"/>
                    <table style="width:100%">
                        <caption>데이타 검색조건 설정</caption>
                        <colgroup>
                            <col width="120px;"/>
                            <col width=";"/>
                        </colgroup>
                        <tbody>
                            <tr>
                                <th scope="row"><spring:message code="TXT_FILE_NAME"/></th>
                                <td>
                                    <input type="text" id="RUN_FILE_NAME" name="RUN_FILE_NAME" value="TFC_KR_001" search="SM8003_01.control.executePythonFile" class="easyui-validatebox" required="true" style="width:170px;"/>
                                    <label> *파일 확장자를 제외한 명칭만 입력할 것. 단, 인터페이스 등록 시에는 파일명이 인터페이스 ID와 동일해야 함</label>
                                    <a href="javascript:SM8003_01.control.executePythonFile();" class="easyui-linkbutton pop_sch"><spring:message code="EXCUS"/></a>
                                </td>
                            </tr>
                            <tr>
                                <th scope="row"><spring:message code="URL"/></th>
                                <td>
                                    <input type="text" id="CRAWL_URL" name="CRAWL_URL" value="http://www.tradenavi.or.kr/CmsWeb/txrt/tHsCodeSearch.req?inttFtaNatnCd={0}&sKeyword={1}&sKeyword_nm=" search="SM8003_01.control.executePythonFile" class="easyui-validatebox" required="true" style="width:99%;"/>
                                </td>
                            </tr>
                            <tr>
                                <th scope="row"><spring:message code="파라메터"/></th>
                                <td>
                                    <input type="text" id="PY_PARAM" name="PY_PARAM" value='[{"TYPE":"STR", "VALUE":"US"}, {"TYPE":"STR", "VALUE":"7801"}]' placeholder='[{"TYPE":"INT", "VALUE":"10"}, {"TYPE":"STR", "VALUE":"20"}]' search="SM8003_01.control.executePythonFile" style="width:99%;"/>
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
                <form id="SM8003_01_form_02" name="SM8003_01_form_02" method="POST">
	                <div data-options="region:'north',border:false" class="h2_etc">
	                    <p id="SM8003_01_span_01" class="h2">실행결과</p>
	                </div>
	                <div data-options="region:'west',border:false,split:true" style="width:600px;">
	                    <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${_MEMBER.SESSION_COMPANY_CD}"/>
	                    <input type="hidden" id="USER_ID" name="SESSION_USER_ID" value="${_MEMBER.SESSION_USER_ID}"/>
	                    <textarea id="RESULT_DATA" name="RESULT_DATA" style="width:99%;height:99%"></textarea>
	                </div>
	                <div data-options="region:'center',border:false">
	                   <table id="SM8003_01_grid_01"> </table>
	                </div>
                </form>
            </div>
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/FM/SM/SM-8003_01.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>