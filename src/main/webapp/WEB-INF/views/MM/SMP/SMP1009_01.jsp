<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : SMP1009_01.jsp
  * @Description : 엑셀업로드 테스트
  * @Modification Information
  *
  * author carlos
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "SMP1009_01_dailog"으로 하셔야 합니다.
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
		<!-- 검색조건 -->
	    <div class="sch_boxIn2">    
	        <form id="SM1009_01_form_01" name="SM1009_01_form_01" method="POST">
		        <table>
		            <caption>데이타 검색조건 설정</caption>
		            <colgroup>
		                <col width="100px;" />
		                <col width="230px;" />
		                <col width="100px;" />
                        <col width="130px;" />
		                <col width="130px;" />
		                <col width="450px;" />
		                <col width=";" />
		            </colgroup>
		            <tbody>
		                <tr>
		                    <th scope="row" class="point"><spring:message code="REGER, DATE"/></th>
		                    <td>
		                        <input type="text" id="SCH_FROM_DATE" name="SCH_FROM_DATE" style="width:95px"
		                           class="easyui-validatebox" data-options="validType:{comparedate:['SM1009_01_form_01', 'DATE', 'SCH_TO_DATE']}"/> ~ 
		                        <input type="text" id="SCH_TO_DATE" name="SCH_TO_DATE" style="width:95px"/>
		                    </td>
                            <th scope="row"><spring:message code="LANG"/></th>
                            <td>
                                <input id="NATION_CODE" name="NATION_CODE" style="width:120px;"/>
                            </td>
		                    <th scope="row"><spring:message code="CNDIT,SERCH"/></th>
                            <td>
                                <input type="text" id="schKeyField" name="schKeyField" style="width:110px"/> 
                                <input type="text" id="schKeyWord" name="schKeyWord" class="easyui-validatebox" search="SM1009_01.control.selectMainList" style="width:200px;paddig:0;"/>
                                <input type="text" id="schKeyLike" name="schKeyLike" style="width:80px"/>
                            </td>
                            <td align="right">
                               <a href="javascript:SM1009_01.control.selectMainList();" class="easyui-linkbutton pop_sch" id="SM1009_01_searchBtn"><spring:message code="SRCH"/></a>
                            </td>
		                </tr>
		            </tbody>
		        </table>
		    </form>
	    </div>
	</div>
    <!-- //검색조건 -->
    <div data-options="region:'center',border:false" style="padding-top:12px;">
        <div class="easyui-layout" data-options="fit:true" class="sec_area">
            <div data-options="region:'north',border:false" class="sec_tit">
	            <div class="h2_etc">
                   <p class="h2"><spring:message code="MSMGT, LIST"/></p>
                   <p class="h2_btn">
                       <a href="javascript:SM1009_01.dialog.openDialog_2('insert');" class="btnNewRegiste"><spring:message code="REGER"/></a>
                       <a href="javascript:SM1009_01.dialog.openDialog_2('update');" class="btnEdit"><spring:message code="MOD"/></a>
                       <a href="javascript:SM1009_01.control.saveMessageSystem();" class="btnServer"><spring:message code="APYDT_SYS"/></a>
                       <a href="javascript:SM1009_01.excel.excelDownload_1();" class="btnExlDown"><spring:message code="EXCEL, DOWN"/></a>
                   </p>
               </div>
	        </div><!-- //sec_tit -->
			<div data-options="region:'center',border:false" style="padding-top:0px;">
			    <table id="SM1009_01_grid_01"></table>
			</div>
		</div><!-- //sec_area -->
	</div>
</div>
	
    <script type="text/javascript" src="/js/views/MM/smp/SMP1009_01.js"></script>
	<script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body> 
</html>