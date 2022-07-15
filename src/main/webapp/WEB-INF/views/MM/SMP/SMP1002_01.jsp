<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : SMP1002_01
  * @Description : 샘플-표준코드 관리
  * @Modification Information
  *
  * author carlos
  * 
  * <notice>
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
            <form id="SM1002_01_form_01" name="SM1002_01_form_01" method="POST">
            	<input type="hidden" name="SYSID" id="SYSID" value="${SYSID}">
                <table>
                    <caption>데이타 검색조건 설정</caption>
		            <colgroup>
		                <col width="80px;" />
		                <col width="420px;" />
		                <col width=";" />
		            </colgroup>
		            <tbody>
		                <tr>
		                    <th scope="row">기준일자</th>
		                    <td>
		                        <input type="text" id="schKeyField" name="schKeyField" style="width:130px;"/> 
                                <input type="text" id="schKeyWord" name="schKeyWord" class="easyui-validatebox" search="SM1002_01.control.selectMainList" style="width:150px;"/>
                                <input type="text" id="schKeyLike" name="schKeyLike" style="width:90px;"/>
		                    </td>
		                    <td class="txt_R">
                                <a href="javascript:SM1002_01.control.selectMainList();" class="easyui-linkbutton pop_sch" id="SM1005_01_searchBtn">조회</a>
                            </td>
		                </tr>
		            </tbody>
                </table>
            </form>
        </div>
    </div>
    <!-- //검색조건 -->
    <div data-options="region:'center',border:false" style="padding-top:12px;">
        <div class="easyui-layout" data-options="fit:true">
            <div data-options="region:'west',border:false,split:true" class="sec_tit" style="width:480px;">
                <div class="easyui-layout" data-options="fit:true">
	                <div data-options="region:'north',border:false" class="h2_etc">
	                   <p class="h2"><spring:message code="CATEG, LIST"/></p>
	                </div>
	                <div data-options="region:'center',border:false">
	                   <table id="SM1002_01_grid_01"></table>
	                </div>
	            </div>
            </div>
            <div data-options="region:'center',border:false">
                <div class="easyui-layout" data-options="fit:true">
                    <div data-options="region:'north',border:false" style="overflow:hidden;">
		                <div class="h2_etc">
			                <p class="h2"><spring:message code="CATEG, MGT"/></p>
			                <p class="h2_btn">
			                    <a href="javascript:SM1002_01.event.resetUI();" class="btnRefresh" id="SM1002_01_btn_01" style="display:none;"><spring:message code="BTN_NEW_REGISTER"/></a>
			                    <a href="javascript:SM1002_01.control.updateCategoryInfo();" class="btnMainSave" id="SM1002_01_btn_02" style="display:none;"><spring:message code="SAVE"/></a>
			                    <a href="javascript:SM1002_01.control.deleteCategoryInfo();" class="btnMainDelete" id="SM1002_01_btn_03" style="display:none;"><spring:message code="DEL"/></a>
			                </p>
			            </div>
			            <form id="SM1002_01_form_02" name="SM1002_01_form_02" method="POST">
			                <input type="hidden" id="flag" name="flag"/>
				            <table class="dataT">
				                <caption>Category Reg.</caption>
				                <colgroup>
				                    <col width="150px" />
				                    <col width="" />
				                </colgroup>
				                <tbody>
				                    <tr>
				                        <th scope="row" class="point2"><spring:message code="CATEG, CODE"/></th>
				                        <td>
				                            <input type="text" id="CATEGORY_CD" name="CATEGORY_CD" class="easyui-validatebox input_readOnly" readonly required="true" style="width:98%;" />
				                            <!-- a herf="#"><span class="info"><spring:message code="CFMDU"/></span></a  -->
				                        </td>
				                    </tr>
				                    <tr>
				                        <th scope="row" class="point2"><spring:message code="CATEG, NAME"/></th>
				                        <td><input type="text" id="CATEGORY_NAME" name="CATEGORY_NAME" class="easyui-validatebox input_readOnly" readonly required="true" style="width:98%;" /></td>
				                    </tr>
				                    <tr>
				                        <th scope="row"><spring:message code="CATEG, ENGLS"/></th>
				                        <td><input type="text" id="CATEGORY_NAME_ENG" name="CATEGORY_NAME_ENG" class="input_readOnly" readonly style="width:98%;"  /></td>
				                    </tr>
				                    <tr>
				                        <th scope="row"><spring:message code="USER, MGT,ORNOT"/></th>
				                        <td><input id="COMPANY_YN" name="COMPANY_YN"  style="width:97px;"/></td>
				                    </tr>
				                </tbody>
				            </table>
				        </form>
			        </div>
			        <div data-options="region:'center',border:false">
                        <div class="easyui-layout" data-options="fit:true">
		                    <div data-options="region:'north',border:false" class="h2_etc">
	                            <p class="h2"><spring:message code="CODE, LIST"/></p>
	                            <p class="h2_btn">
			                        <a href="javascript:SM1002_01.dialog.openDialog_1('insert');" class="btnNewRegiste"><spring:message code="REGER"/></a>
	                                <a href="javascript:SM1002_01.dialog.openDialog_1('update');" class="btnEdit"><spring:message code="MOD"/></a>
			                    </p>
		                    </div>
		                    <div data-options="region:'center',border:false">
		                       <table id="SM1002_01_grid_02"></table>
		                    </div>
				        </div>
                    </div>
                    <div data-options="region:'south',border:false,split:true" style="height:180px;">
                        <div class="easyui-layout" data-options="fit:true">
                            <div data-options="region:'north',border:false" class="h2_etc">
                                <p class="h2"><spring:message code="LANG, LIST"/></p>
                                <p class="h2_btn">
                                    <a href="javascript:SM1002_01.event.resetLangUI();" class="btnRefresh"><spring:message code="BTN_NEW_REGISTER"/></a>
                                    <a href="javascript:SM1002_01.control.updateLanguageInfo();" class="btnMainSave"><spring:message code="SAVE"/></a>
                                    <a href="javascript:SM1002_01.control.deleteLanguageInfo();" class="btnMainDelete"><spring:message code="DEL"/></a>
                                </p>
                            </div>
                            <div data-options="region:'center',border:false">
                                <div class="easyui-layout" data-options="fit:true">
                                    <div data-options="region:'center',border:false">
                                        <table id="SM1002_01_grid_03"></table>
                                    </div>
                                    <div data-options="region:'east',border:false,split:true" class="sec_tit" style="width:500px;">
                                        <form id="SM1002_01_form_03" name="SM1002_01_form_03" method="POST">
				                            <input type="hidden" id="flag" name="flag"/>
				                            <input type="hidden" id="COMPANY_CD" name="COMPANY_CD"/>
				                            <input type="hidden" id="CATEGORY_CD" name="CATEGORY_CD"/>
				                            <input type="hidden" id="CODE" name="CODE"/>
				                            <input type="hidden" id="LANG_ID" name="LANG_ID" value="1"/>
				                            <table class="dataT">
				                                <caption>Category Reg.</caption>
				                                <colgroup>
				                                    <col width="100px" />
				                                    <col width="" />
				                                </colgroup>
				                                <tbody>
				                                    <tr>
				                                        <th scope="row" class="point2"><spring:message code="LANG"/></th>
				                                        <td>
				                                            <input type="text" id="LANGUAGE_CAT" name="LANGUAGE_CAT" style="width:100px;"/>
				                                        </td>
				                                    </tr>
				                                    <tr>
				                                        <th scope="row" class="point2"><spring:message code="MSMGT"/></th>
				                                        <td><input type="text" id="WORD" name="WORD" value="" class="easyui-validatebox" required="true" style="width:98%;" /></td>
				                                    </tr>
				                                    <tr>
				                                        <th scope="row"><spring:message code="DSCPT"/></th>
				                                        <td>
				                                            <textarea type="textarea" id="MEANNING" name="MEANNING" rows="4" style="width:95%;"></textarea>
				                                        </td>
				                                    </tr>
				                                </tbody>
				                            </table>
				                        </form>
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

<script type="text/javascript" src="/js/views/MM/smp/SMP1002_01.js"></script>
<script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body> 
</html>
