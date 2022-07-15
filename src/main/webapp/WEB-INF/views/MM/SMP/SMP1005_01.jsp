<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : SMP1005.jsp
  * @Description : 공통팝업 Sample
  * @Modification Information
  *
  * author YNI-Maker(이메일)
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "SM1005_01_dailog_01"으로 하셔야 합니다.
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
	            <form id="SM1005_01_form_01" method="POST">
	                <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
	                <table>
	                    <caption>데이타 검색조건 설정</caption>
	                    <colgroup>
	                        <col width="80px" />
	                        <col width="100px" />
	                        <col width="80px" />
	                        <col width="120px" />
	                        <col width=";"/>
	                    </colgroup>
	                    <tbody>
	                        <tr>
	                            <th scope="row" class="point"><spring:message code="CUNTR, CODE"/></th>
	                            <td><input type="text" name="CODE" value="" class="easyui-validatebox" required="true" search="SM1005_01.control.selectMainList" size="10" id="CODE"/></td>
	                            <th scope="row"><spring:message code="CUNTR, NAME"/></th>
	                            <td><input type="text" name="CODE_NAME" value="" class="easyui-validatebox" search="SM1005_01.control.selectMainList" size="15" id="CODE_NAME"/></td>
	                            <td class="txt_R">
	                               <a href="javascript:SM1005_01.control.selectMainList();" class="easyui-linkbutton pop_sch" id="SM1005_01_searchBtn">조회</a>
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
	                <p class="h2">공통팝업 Sample</p>
	                <p class="h2_btn">
	                    <a href="javascript:SM1005_01.control.selectRowValue();" class="btn">선택</a>
	                </p>
	            </div>
	            <div data-options="region:'center',border:false">
	               <table id="SM1005_01_grid_01"> </table>
	            </div>
	        </div>
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/MM/smp/SMP1005_01.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>