<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : SM-7018_01.jsp
  * @Description : 사용자 로그
  * @Modification Information
  *
  * author YNI-Maker
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "SM7018_01_dailog_01"으로 하셔야 합니다.
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
                <form id="SM7018_01_form_01" name="SM7018_01_form_01" method="post">
                    <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
                    <table>
                        <caption><spring:message code="TXT_DATA_SEARCH_CONDITION"/><%--데이타 검색조건 설정--%></caption>
                        <colgroup>
                            <col width="130px" />
                            <col width="220px" />
                            <col width="110px" />
                            <col width="130px" />
                            <col width="120px"/>
                            <col width="350px"/>
                            <col width=";"/>
                        </colgroup>
                        <tbody>
                            <tr>
                                <th scope="row" class="point"><spring:message code="REQ,DATE"/></th>
                                <td>
                                    <input type="hidden" id="SEARCH_FROM_DATE" name="SEARCH_FROM_DATE" style="width:95px;"/> ~
                                    <input type="hidden" id="SEARCH_TO_DATE" name="SEARCH_TO_DATE" style="width:95px;"/>
                                </td>
                                <th scope="row" class="point2"><spring:message code="SITE"/></th>
                                <td>
                                    <input type="text" id="SITE_TYPE" name="SITE_TYPE" style="width:120px"/>
                                </td>
                                <th scope="row"><spring:message code="SHCDT"/><%--조회조건--%></th>
                                <td>
                                    <input type="text" id="schKeyField" name="schKeyField" style="width:110px;"/> 
                                    <input type="text" id="schKeyWord" name="schKeyWord" search="SM7018_01.control.selectUseLogStatusList" style="width:130px;"/>
                                    <input type="text" id="schKeyLike" name="schKeyLike"style="width:80px;"/>
                                </td>
                                <td class="txt_R">
                                    <a href="javascript:SM7018_01.control.selectUseLogStatusList();" class="easyui-linkbutton pop_sch"><spring:message code="SRCH"/></a>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
        <div data-options="region:'center',border:false" style="padding-top:12px;">
            <div class="easyui-layout" data-options="fit:true" class="sec_area">
                <!-- sec_tit -->
                <div data-options="region:'north',border:false" class="sec_tit">
    	            <div class="h2_etc">
                       <p class="h2"><spring:message code="USE,RECOD, LIST"/><%--사용이력 목록--%></p>
                       <p class="h2_btn">
                           <a href="javascript:SM7018_01.excel.excelDownload_1();" class="btnExlDown"><spring:message code="EXCEL, DOWN"/><%--엑셀다운로드--%></a>
                       </p>
                   </div>
    	        </div>
    			<div data-options="region:'center',border:false" style="padding-top:0px;">
    			    <table id="SM7018_01_grid_01"></table>
    			</div>
    		</div>
    	</div>
    	<div data-options="region:'east',border:false,split:true" style="width:500px;padding-top:12px;">
            <div class="easyui-layout" data-options="fit:true" class="sec_area">
                <!-- sec_tit -->
                <div data-options="region:'north',border:false" class="sec_tit">
                    <div class="h2_etc">
                       <p class="h2"><spring:message code="USE,PSTCN"/> (<spring:message code="BYUSR"/>)<%--사용현황(사용자별)--%></p>
                       <p class="h2_btn">
                           <a href="javascript:SM7018_01.excel.excelDownload_2();" class="btnExlDown"><spring:message code="EXCEL, DOWN"/><%--엑셀다운로드--%></a>
                       </p>
                   </div>
                </div>
                <div data-options="region:'center',border:false" style="padding-top:0px;">
                    <table id="SM7018_01_grid_02"></table>
                </div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/FM/SM/SM-7018_01.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body> 
</html>