<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : MM-B002_03.jsp
  * @Description : 거래처 조회
  * @Modification Information
  *
  * Atom
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMB002_27_dailog_01"으로 하셔야 합니다.
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
	            <form name="MMB002_27_form_01" id="MMB002_27_form_01" method="POST">
	                <input type="hidden" id="USER_ID" name="USER_ID" value="${_MEMBER.SESSION_USER_ID}"/>
	                <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
	                <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
	                <input type="hidden" id="FIELD_ID" name="FIELD_ID" value="${FIELD_ID}"/>
	                <input type="hidden" id="IMP_EXP_TYPE" name="IMP_EXP_TYPE" value="${IMP_EXP_TYPE}"/>
	                <table>
	                    <caption>데이타 검색조건 설정</caption>
	                    <colgroup>
	                        <col width="80px" />
	                        <col width="400px" />
	                        <col width="80px" />
	                        <col width="170px" />
	                        <col width=";"/>
	                    </colgroup>
	                    <tbody>
	                        <tr>
	                            <th scope="row"><spring:message code="SHCDT"/></th>
	                            <td>
	                                <input type="text" id="schKeyField" name="schKeyField" style="width:80px"/>
                                    <input type="hidden" id="multiData" name="multiData"/>
                                    <span id="MMB002_27_tool_01" class="grid" onclick="javascript:MMB002_27.tool.showTooltip();" style="width:19px;display:none;"></span>
                                    <input type="text" id="schKeyWord" name="schKeyWord" class="easyui-validatebox" search="MMB002_27.control.selectClientList" style="width:130px;padding-left:20px;"/>
                                    <input type="text" id="schKeyLike" name="schKeyLike" style="width:80px"/>	                                
	                            </td>
                                <th scope="row"><spring:message code="CC_설정값"/></th>
                                <td>
                                    <input type="text" id="SETUP_VAL" name="SETUP_VAL" search="MMB002_27.control.selectClientList" style="width:100px"/>
                                </td>
	                            <td class="txt_R">
	                               <a href="javascript:MMB002_27.control.selectClientList();" class="easyui-linkbutton pop_sch"><spring:message code="SRCH"/></a>
	                            </td>
	                        </tr>
	                    </tbody>
	                </table>
	            </form>
	        </div>
        </div>
        <!-- //검색조건 -->
        <div data-options="region:'center',border:false" style="padding-top:6px;">
            <div class="easyui-layout" data-options="fit:true">
                <div data-options="region:'north',border:false" class="h2_etc">
                    <p class="h2"><spring:message code="CC_기본값설정 목록"/></p>
                    <p class="h2_btn">
                        <%-- <a href="javascript:MMB002_27.control.selectRowValue();" class="btnPopTick"><spring:message code="SELET"/></a> --%>
                    </p>
                </div>
                <div data-options="region:'center',border:false" style="padding-top:3px;">
                    <div class="easyui-layout" data-options="fit:true">
                        <div data-options="region:'north',border:false" class="h2_etc">
                            <form id="MMB002_27_form_02" method="post">
				                <input type="hidden" id="USER_ID" name="USER_ID" value="${_MEMBER.SESSION_USER_ID}"/>
				                <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
				                <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>                            
                            	<input type="hidden" id="TABLE_NAME" name="TABLE_NAME" value="${TABLE_NAME}"/>
                            	<input type="hidden" id="gridData" name="gridData"/>                         	
                                <table class="gridT" style="margin-bottom:0px;">
                                    <colgroup>
                                    	<col width="60px" />
				                        <col width="210px" />
				                        <col width="60px" />
				                        <col width="" />
                                    </colgroup>
                                    <tbody>
	                                    <tr>
	                                        <th scope="row"><spring:message code="CC_항목"/></th>
	                                        <td>
	                                            <input type="text" name="MOD_BATCH_ITEM" id="MOD_BATCH_ITEM" style="width:195px;"/>
	                                        </td>
	                                        <th scope="row"><spring:message code="CC_입력값"/></th>
											<td>
                                               <input type="text" name="MOD_BATCH_VALUE" id="MOD_BATCH_VALUE" class="easyui-validatebox" required="true" style="width:200px;"/>&nbsp;
                                               <a href="javascript:MMB002_27.control.updateBcncSetupBatchModify();" class="btnCheckEdit">일괄수정</a>
                                           	</td>	                                        
	                                    </tr>
                                    </tbody>
                                </table>
                            </form>
                        </div>
                        <div data-options="region:'center',border:false">
		                    <table id="MMB002_27_grid_01"> </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-B002_27.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>