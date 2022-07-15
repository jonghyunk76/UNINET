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
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "SM1009_02_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>      
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body class="h2_etc">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center',border:false">
	        <div id="SM1009_02_div_01" class="btn_R">
		        <a href="javascript:SM1009_02.control.updateMainInfo();" id="SM1009_02_btn_01" class="btnSave"><spring:message code="SAVE"/></a>
		        <a href="javascript:SM1009_02.control.deleteMainInfo();" id="SM1009_02_btn_02" class="btnDelete"><spring:message code="DEL"/></a>
	        </div>
	        <form id="SM1009_02_form_01" name="SM1009_02_form_01" method="post">
	            <input type="hidden" id="flag" name="flag" value="${flag}"/>
			    <table class="dataT" style="margin-bottom:0px;">
			        <caption>데이타 테이블</caption>
			        <colgroup>
			            <col width="120px;" />
			            <col width="230px;" />
			            <col width="120px;" />
			            <col width=";" />
			        </colgroup>
			        <tbody>
			            <tr>
			                <th scope="row" class="point2"><spring:message code="LANG"/></th>
			                <td colspan="3">
		                        <input type="text" name="NATION_CODE" id="NATION_CODE" value="${NATION_CODE}" style="width:120px;"/>
			                </td>
			            </tr>
			            <tr>
			                <th scope="row" class="point2"><spring:message code="MSMGT, CODE"/></th>
			                <td colspan="3">
			                    <input type="text" name="MESSAGE_CODE" id="MESSAGE_CODE"  class="easyui-validatebox" required="true" value="${MESSAGE_CODE}" style="width:250px;"/>
			                </td>
			            </tr>
			            <tr>
			                <th scope="row" class="point2"><spring:message code="MSMGT"/></th>
			                <td colspan="3">
		                        <textarea type="text" name="MESSAGE" id="MESSAGE" class="easyui-validatebox" required="true" style="height:100px;width:99%;"></textarea>
		                    </td>
			            </tr>
			            <tr>
                            <th scope="row"><spring:message code="DSCPT"/></th>
                            <td colspan="3"><textarea id="COMMENTS" name="COMMENTS" rows="4" class="input_red" style="width:99%;" ></textarea></td>
                        </tr>
			            <tr>
			                <th scope="row"><spring:message code="APYDT, DATE"/></th>
			                <td>
				                <input type="text" id="APPLY_START_DATE" name="APPLY_START_DATE" style="width:95px;"/> ~
			                    <input type="text" id="APPLY_END_DATE" name="APPLY_END_DATE" style="width:95px;"/>
			                </td>
			                <th scope="row" class="point"><spring:message code="USE,ORNOT"/></th>
                            <td>
                                <input type="text" name="USE_YN" id="USE_YN" size="10"/>
                            </td>
			            </tr>
			        </tbody>
			    </table>  
            </form>
        </div>
	</div>
	
    <script type="text/javascript" src="/js/views/MM/smp/SMP1009_02.js"></script>
	<script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body> 
</html>