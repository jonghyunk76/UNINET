<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : SMP1004_01.jsp
  * @Description : 권한관리 테스트
  * @Modification Information
  *
  * author carlos
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "SMP1004_01_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>      
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'west',border:false,split:true" style="width:400px;">
        <!-- 권한관리 -->
        <div class="easyui-layout" data-options="fit:true">
            <div data-options="region:'north',border:false" style="overflow:hidden;">
                <div class="h2_etc">
                    <p class="h2"><spring:message code="AUTHO, GROUP, MGT"/></p>
                    <p class="h2_btn">
                        <a href="javascript:SM1004_01.event.resetUI();" class="btn"><spring:message code="BTN_NEW_REGISTER"/></a>
                        <a href="javascript:SM1004_01.control.updateAuthGroupInfo();" class="btn"><spring:message code="SAVE"/></a>
                    </p>
                </div>
                <form id="SM1004_01_form_01" name="SM1004_01_form_01" method="POST">
                    <input type="hidden" id="flag" name="flag" value="insert"/>
                    <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${_MEMBER.SESSION_COMPANY_CD}"/>
                    <table class="dataT">
                        <caption>Auth Group Reg.</caption>
                        <colgroup>
                            <col width="150px" />
                            <col width="" />
                        </colgroup>
                        <tbody>
                            <tr>
                                <th scope="row" class="point2"><spring:message code="GROUP, CODE"/></th>
                                <td>
                                    <input type="text" id="AUTH_GROUP_ID" name="AUTH_GROUP_ID" value="" class="easyui-validatebox" required="true" size="20" /> <!-- 데이타 입력 후 class="data_in" 추가 -->
                                    <!-- a herf="#"><span class="info"><spring:message code="CFMDU"/></span></a  -->
                                </td>
                            </tr>
                            <tr>
                                <th scope="row" class="point2"><spring:message code="GROUP, NAME"/></th>
                                <td><input type="text" id="AUTH_GROUP_NAME" name="AUTH_GROUP_NAME" value="" class="easyui-validatebox" required="true" style="width:98%;" /></td>
                            </tr>
                            <tr>
                                <th scope="row"><spring:message code="GROUP, ENGLS"/></th>
                                <td><input type="text" id="AUTH_GROUP_NAME_ENG" name="AUTH_GROUP_NAME_ENG" value="" style="width:98%;"  /></td>
                            </tr>
                            <tr>
                                <th scope="row"><spring:message code="USE, ORNOT"/></th>
                                <td><input type="text" id="USE_YN" name="USE_YN" value="" size="8"  /></td>
                            </tr>
                            <tr>
                                <th scope="row"><spring:message code="COMTS"/></th>
                                <td><textarea rows="3" id="COMMENTS" name="COMMENTS"  cols="30" style="width:97%;" ></textarea></td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </div>
            <div data-options="region:'center',border:false" style="padding-top:0px;">
                <table id="SM1004_01_grid_01"></table>
            </div>
        </div>
    </div>
    <div data-options="region:'center',border:false">
        <div class="easyui-layout" data-options="fit:true">
            <div data-options="region:'north',border:false,split:true" style="height:250px;">
                <!-- 사용자 권한 부여 -->
                <div class="easyui-layout" data-options="fit:true">
		            <div data-options="region:'north',border:false">
		                <div class="h2_etc">
		                   <p class="h2"><spring:message code="USER, AUTHO"/></p>
		                   <p class="h2_btn">
		                       <a href="javascript:SM1004_01.dialog.openDialog_1();" class="btn"><spring:message code="ADDTL"/></a>
		                       <a href="javascript:SM1004_01.control.deleteAuthUserInfo();" class="btn"><spring:message code="DEL"/></a>
		                   </p>
		               </div>
		            </div><!-- //sec_tit -->
		            <div data-options="region:'center',border:false" style="padding-top:0px;">
		                <table id="SM1004_01_grid_02"></table>
		            </div>
		        </div><!-- //sec_area -->
            </div>
            <div data-options="region:'center',border:false">
                <!-- 메뉴 권한 부여 -->
                <div class="easyui-layout" data-options="fit:true">
                    <div data-options="region:'west',border:false" style="width:350px;">
                        <form id="SM1004_01_form_02" name="SM1004_01_form_02" method="POST">
                            <input type="hidden" id="COMPANY_CD" name="COMPANY_CD"/>
                            <input type="hidden" id="AUTH_GROUP_ID" name="AUTH_GROUP_ID"/>
                            <input type="hidden" id="USER_ID" name="USER_ID"/>
                        </form>
                        <table id="SM1004_01_grid_03"></table>
                    </div>
                    <div data-options="region:'center',border:false">
                         <div class="easyui-layout" data-options="fit:true">
                            <div data-options="region:'west',border:false" style="width:30px;">
			                    <p style="box-sizing:border-box;text-align:center;padding:80px 0 0 0;">
						            <a href="javascript:SM1004_01.control.insertAuthMenuInfo();"><img src="/images/sample/btn_arrR.gif" alt="오른쪽으로 옮기기" /></a><br><br>
						            <a href="javascript:SM1004_01.control.deleteAuthMenuInfo();"><img src="/images/sample/btn_arrL.gif" alt="왼쪽으로 옮기기" /></a>
						        </p>
						    </div>
						    <div data-options="region:'center',border:false">
						        <form id="SM1004_01_form_03" name="SM1004_01_form_03" method="POST">
						            <input type="hidden" id="gridData" name="gridData"/>
                                    <input type="hidden" id="COMPANY_CD" name="COMPANY_CD"/>
                                    <input type="hidden" id="AUTH_GROUP_ID" name="AUTH_GROUP_ID"/>
						        </form>
		                        <table id="SM1004_01_grid_04"></table>
		                    </div>
						</div>
				    </div>
                </div>
            </div>
        </div>
    </div>
</div><!--//wrap -->

    <script type="text/javascript" src="/js/views/MM/smp/SMP1004_01.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>
    
</body>	
</html>
