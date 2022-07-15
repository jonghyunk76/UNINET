<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : SMP1002_02.jsp
  * @Description : 샘플- 표준코드 등록
  * @Modification Information
  *
  * author carlos
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "SM1002_02_dailog_01"으로 하셔야 합니다.
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
	        <div id="SM1002_02_div_01" class="btn_R">
		        <a href="javascript:SM1002_02.control.updateMainInfo();" id="SM1002_02_btn_01" class="btnSave"><spring:message code="SAVE"/></a>
		        <a href="javascript:SM1002_02.control.deleteMainInfo();" id="SM1002_02_btn_02" class="btnDelete"><spring:message code="DEL"/></a>
	        </div>
	        <form id="SM1002_02_form_01" name="SM1002_02_form_01" method="post">
	            <input type="hidden" id="flag" name="flag" value="${flag}"/>
	            <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
			    <table class="dataT" style="margin-bottom:0px">
			        <caption>데이타 테이블</caption>
			        <colgroup>
			            <col width="120px" />
			            <col width=";" />
			            <col width="120px" />
			            <col width=";" />
			        </colgroup>
			        <tbody>
			            <tr>
			                <th scope="row" class="point"><spring:message code="CATEG, CODE"/></th>
			                <td>
		                        <input type="text" name="CATEGORY_CD" id="CATEGORY_CD" value="${CATEGORY_CD}" class="easyui-validatebox input_readOnly" required="true" style="width:98%" readonly/>
			                </td>
			                <th scope="row" class="point"><spring:message code="CATEG, NAME"/></th>
			                <td>
			                    <input type="text" name="CATEGORY_NAME" id="CATEGORY_NAME" value="${CATEGORY_NAME}" style="width:98%" readonly class="input_readOnly"/>
			                </td>
			            </tr>
			            <tr>
			                <th scope="row" class="point2"><spring:message code="CODE"/></th>
			                <td>
		                        <input type="text" name="CODE" id="CODE" value="${CODE}" class="easyui-validatebox" required="true" validType="'maxlength[30]'" style="width:98%"/>
		                    </td>
		                    <th scope="row" class="point2"><spring:message code="TXT_SORT_NO"/></th>
                            <td>
                                <input type="text" name="SORT_NO" id="SORT_NO" class="easyui-numberbox easyui-validatebox" required="true" validType="['number','maxlength[10]']" style="width:100px;"/>
                            </td>
			            </tr>
			            <tr>
                            <th scope="row" class="point2"><spring:message code="CODE, NAME"/></th>
                            <td colspan="3">
                                <input type="text" name="CODE_NAME" id="CODE_NAME" class="easyui-validatebox" required="true" validType="'maxlength[120]'" style="width:99%"/>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row" class="point2"><spring:message code="CODE, ENGLS,NAME"/></th>
                            <td colspan="3">
                                <input type="text" name="CODE_NAME_ENG" id="CODE_NAME_ENG" class="easyui-validatebox" required="true" validType="'maxlength[120]'" style="width:99%"/>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row"><spring:message code="SET"/>(<spring:message code="STR"/>)1</th>
                            <td>
                                <input type="text" name="TXT_VALUE1" id="TXT_VALUE1" class="easyui-validatebox" validType="'maxlength[100]'" width="98%"/>
                            </td>
                            <th scope="row"><spring:message code="SET"/>(<spring:message code="STR"/>)2</th>
                            <td>
                                <input type="text" name="TXT_VALUE2" id="TXT_VALUE2" class="easyui-validatebox" validType="'maxlength[100]'" width="98%"/>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row"><spring:message code="SET"/>(<spring:message code="STR"/>)3</th>
                            <td>
                                <input type="text" name="TXT_VALUE3" id="TXT_VALUE3" class="easyui-validatebox" validType="'maxlength[100]'" width="98%"/>
                            </td>
                            <th scope="row"><spring:message code="SET"/>(<spring:message code="STR"/>)4</th>
                            <td>
                                <input type="text" name="TXT_VALUE4" id="TXT_VALUE4" class="easyui-validatebox" validType="'maxlength[100]'" width="98%"/>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row"><spring:message code="SET"/>(<spring:message code="STR"/>)5</th>
                            <td>
                                <input type="text" name="TXT_VALUE5" id="TXT_VALUE5" class="easyui-validatebox" validType="'maxlength[100]'" width="98%"/>
                            </td>
                            <th scope="row"></th>
                            <td>
                                
                            </td>
                        </tr>                        
                        <tr>
                            <th scope="row"><spring:message code="SET"/>(<spring:message code="NUM"/>)1</th>
                            <td>
                                <input type="text" name="NUM_VALUE1" id="NUM_VALUE1" class="easyui-numberbox easyui-validatebox" validType="['floatformat[10,4]']" width="98%"/>
                            </td>
                            <th scope="row"><spring:message code="SET"/>(<spring:message code="NUM"/>)2</th>
                            <td>
                                <input type="text" name="NUM_VALUE2" id="NUM_VALUE2" class="easyui-numberbox easyui-validatebox" validType="['floatformat[10,4]']" width="98%"/>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row"><spring:message code="SET"/>(<spring:message code="NUM"/>)3</th>
                            <td>
                                <input type="text" name="NUM_VALUE3" id="NUM_VALUE3" class="easyui-numberbox easyui-validatebox" validType="['floatformat[10,4]']" width="98%"/>
                            </td>
                            <th scope="row"><spring:message code="SET"/>(<spring:message code="NUM"/>)4</th>
                            <td>
                                <input type="text" name="NUM_VALUE4" id="NUM_VALUE4" class="easyui-numberbox easyui-validatebox" validType="['floatformat[10,4]']" width="98%"/>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row"><spring:message code="SET"/>(<spring:message code="NUM"/>)5</th>
                            <td>
                                <input type="text" name="NUM_VALUE5" id="NUM_VALUE5" class="easyui-numberbox easyui-validatebox" validType="['floatformat[10,4]']" width="98%"/>
                            </td>
                            <th scope="row"></th>
                            <td>
                            </td>
                        </tr>                                                
			        </tbody>
			    </table>  
            </form>
        </div>
	</div>
	
    <script type="text/javascript" src="/js/views/MM/smp/SMP1002_02.js"></script>
	<script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body> 
</html>