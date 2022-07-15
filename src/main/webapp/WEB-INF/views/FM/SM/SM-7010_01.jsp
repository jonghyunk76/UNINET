<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : SM-7010_01.jsp
  * @Description : 권한관리 테스트
  * @Modification Information
  *
  * author carlos
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "SM7010_01_dailog_01"으로 하셔야 합니다.
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
                    <p class="h2"><spring:message code="SM7010_01"/><%--권한 그룹 관리--%></p>
                    <p class="h2_btn">
                        <a href="javascript:SM7010_01.event.resetUI();" class="btnRefresh"><spring:message code="BTN_NEW_REGISTER"/><%--신규등록--%></a>
                        <a href="javascript:SM7010_01.control.updateAuthGroupInfo();" class="btnMainSave"><spring:message code="SAVE"/><%--저장--%></a>
                    </p>
                </div>
                <form id="SM7010_01_form_01" name="SM7010_01_form_01" method="POST">
                    <input type="hidden" id="flag"       name="flag"       value="insert"/>
                    <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${_MEMBER.SESSION_COMPANY_CD}"/>
                    
                    <table class="dataT">
                        <caption>Auth Group Reg.</caption>
                        <colgroup>
                            <col width="150px" />
                            <col width="" />
                        </colgroup>
                        <tbody>
                            <tr>
                                <th scope="row" class="point2"><spring:message code="GROUP, CODE"/><%--그룹 코드--%></th>
                                <td>
                                    <input type="text" id="AUTH_GROUP_ID" name="AUTH_GROUP_ID" value="" class="easyui-validatebox" required="true" validType="['maxlength[40]']" size="20" /> <!-- 데이타 입력 후 class="data_in" 추가 -->
                                </td>
                            </tr>
                            <tr>
                                <th scope="row" class="point2"><spring:message code="GROUP, NAME"/><%--그룹 명--%></th>
                                <td><input type="text" id="AUTH_GROUP_NAME" name="AUTH_GROUP_NAME" value="" class="easyui-validatebox" required="true" validType="['maxlength[400]']" style="width:98%;" /></td>
                            </tr>
                            <tr>
                                <th scope="row"><spring:message code="GROUP, ENGLS"/><%--그룹 영문--%></th>
                                <td><input type="text" id="AUTH_GROUP_NAME_ENG" name="AUTH_GROUP_NAME_ENG" value="" style="width:98%;" /></td>
                            </tr>
                            <tr>
                                <th scope="row"><spring:message code="USE, ORNOT"/><%--사용 여부--%></th>
                                <td><input type="text" id="USE_YN" name="USE_YN" value="" size="12" /></td>
                            </tr>
                            <tr>
                                <th scope="row"><spring:message code="COMTS"/><%--추가내용--%></th>
                                <td><textarea rows="3" id="COMMENTS" name="COMMENTS" cols="30" style="width:97%;" validType="['maxlength[4000]']"></textarea></td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </div>
            <div data-options="region:'center',border:false" style="padding-top:0px;">
                <table id="SM7010_01_grid_01"></table>
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
		                   <p class="h2"><spring:message code="USER, AUTHO, APPOT"/><%--사용자 권한--%></p>
		                   <p class="h2_btn">
		                       <a href="javascript:SM7010_01.dialog.openDialog_1();" class="btnUserAdd"><spring:message code="ADDTL"/><%--추가--%></a>
		                       <a href="javascript:SM7010_01.control.deleteAuthUserInfo();" class="btnMainDelete"><spring:message code="DEL"/><%--삭제--%></a>
		                   </p>
		               </div>
		            </div><!-- //sec_tit -->
		            <div data-options="region:'center',border:false" style="padding-top:0px;">
		                <table id="SM7010_01_grid_02"></table>
		            </div>
		        </div><!-- //sec_area -->
            </div>
            <div data-options="region:'center',border:false">
                <!-- 메뉴 권한 부여 -->
                <div class="easyui-layout" data-options="fit:true">
                    <div data-options="region:'west',border:false" style="width:350px;">
                        <form id="SM7010_01_form_02" name="SM7010_01_form_02" method="POST">
                            <input type="hidden" id="COMPANY_CD"    name="COMPANY_CD"/>
                            <input type="hidden" id="AUTH_GROUP_ID" name="AUTH_GROUP_ID"/>
                            <input type="hidden" id="USER_ID"       name="USER_ID"/>
                        </form>
                        <table id="SM7010_01_grid_03"></table>
                    </div>
                    <div data-options="region:'center',border:false">
                         <div class="easyui-layout" data-options="fit:true">
                            <div data-options="region:'west',border:false" style="width:30px;">		                    
			                    <p style="box-sizing:border-box;text-align:center;padding:80px 0 0 0;">
						            <a href="javascript:SM7010_01.control.insertAuthMenuInfo();"><img src="/images/sample/btn_arrR.gif" alt="오른쪽으로 옮기기" /></a><br><br>
						            <a href="javascript:SM7010_01.control.deleteAuthMenuInfo();"><img src="/images/sample/btn_arrL.gif" alt="왼쪽으로 옮기기" /></a>
						        </p>
						    </div>
						    <div data-options="region:'center',border:false">				                
					            <div class="easyui-layout" data-options="fit:true">    
					                <div class="h2_etc" data-options="region:'north',border:false">
					                    <p class="h2"><spring:message code="MSG_MENU_INVESTED_LIST"/><%--권한 그룹 관리--%></p>
					                    <p class="h2_btn">				                        
					                        <a href="javascript:SM7010_01.control.updateAuthMenuInfo();" class="btnMainSave"><spring:message code="SAVE"/><%--저장--%></a>
					                        <a href="javascript:SM7010_01.control.selectAuthMenuList();" class="btnRefresh"><spring:message code="REFSH"/></a>
					                    </p>
					                </div>
					                <div data-options="region:'center',border:false">						        
								        <form id="SM7010_01_form_03" name="SM7010_01_form_03" method="POST">
								            <input type="hidden" id="gridData"      name="gridData"/>
		                                    <input type="hidden" id="COMPANY_CD"    name="COMPANY_CD"/>
		                                    <input type="hidden" id="AUTH_GROUP_ID" name="AUTH_GROUP_ID"/>
								        </form>
				                        <table id="SM7010_01_grid_04"></table>
				                    </div>   
			                    </div>    
		                    </div>
						</div>
				    </div>
                </div>
            </div>
        </div>
    </div>
</div><!--//wrap -->

    <script type="text/javascript" src="/js/views/FM/SM/SM-7010_01.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>
    
</body>	
</html>
