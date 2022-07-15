<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : MM-A034_02.jsp
  * @Description : 데이터 그리드 사용자 설정
  * @Modification Information
  *
  * author YNI-Maker(이메일)
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMA034_02_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>      
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'north',border:false" style="height:27px;background:#e8e8e8;border-bottom: 1px solid #aaa;">
            <form id="MMA034_02_form_01" name="MMA034_02_form_01" method="POST">
                <input type="hidden" id="GRID_ID" name="GRID_ID" value="${GRID_ID}"/>
                <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${_MEMBER.SESSION_COMPANY_CD}"/>
                <input type="hidden" id="USER_ID" name="USER_ID" value="${_MEMBER.SESSION_USER_ID}"/>
                <input type="hidden" id="PID" name="PID" value="${PID}"/>
                <input type="hidden" id="SET_YN" name="SET_YN" value="N"/>
                <input type="hidden" id="gridData1" name="gridData1"/>
                <input type="hidden" id="gridData2" name="gridData2"/>
                <div id="MMA034_02_div_01" style="display:none;float:left;padding-top:3px;">
                    <table>
                        <caption><spring:message code="DTA, TB"/><%--데이타 테이블--%></caption>
                        <colgroup>
                            <col width=";" />
                            <col width="210px" />
                        </colgroup>
                        <tbody>
                            <tr>
                                <td scope="row" style="text-align:right;padding-right:3px;font-size:13pt;font-weight:bold;">
                                    <spring:message code="CLINT"/> :<!-- 거래처 -->
                                </td>
                                <td>
                                    <input type="text" name="USER_ATTRIBUTE1" id="USER_ATTRIBUTE1" style="width:200px;"/>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div style="float:right;padding-top:3px;">
                    <table>
                        <caption><spring:message code="DTA, TB"/><%--데이타 테이블--%></caption>
                        <colgroup>
                            <col width="160px" />
                            <col width="60px" />
                        </colgroup>
                        <tbody>
                            <tr>
                                <td style="text-align:right;padding-right:3px;font-size:13pt;font-weight:bold;">
                                    <spring:message code="HEADER_COLNUM"/> :<!-- 해더 행번호 -->
                                </td>
                                <td>
                                    <input id="HEADER_NO" name="HEADER_NO" style="width:50px;"/>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </form>
        </div>
        <div data-options="region:'center',border:false" style="padding-top:9px;">
            <div id="MMA034_02_tabs_01" class="easyui-tabs" data-options="fit:true,showHeader:false,plain:true" style="height:100%;">
                <div title="1">
                    <div class="easyui-layout" data-options="fit:true">
                        <div data-options="region:'center',border:false" class="width60">
	                        <div class="easyui-layout" data-options="fit:true">
			                    <div data-options="region:'north',border:false">
			                        <div class="h2_etc">
			                           <p class="h2"><spring:message code="DISPLAY_COLUMN"/></p>
			                           <p class="h2_btn">
			                               <a href="javascript:MMA034_02.ui.moveRow(0);" class="btnArrowTop"><spring:message code="TOP"/></a>
			                               <a href="javascript:MMA034_02.ui.moveRow(-1);" class="btnArrowUp"><spring:message code="UP"/></a>
			                               <a href="javascript:MMA034_02.ui.moveRow(1);" class="btnArrowDown"><spring:message code="DOW"/></a>
			                               <a href="javascript:MMA034_02.ui.moveRow(9);" class="btnArrowBottom"><spring:message code="BOTTOM"/></a>
			                           </p>
			                       </div>
			                    </div>
			                    <div data-options="region:'center',border:false" style="padding-top:0px;">
		                            <table id="MMA034_02_grid_01"></table>
			                    </div>
			                </div>
	                    </div>
	                    <div data-options="region:'east',border:false" class="width40">
	                         <div class="easyui-layout" data-options="fit:true">
	                            <div data-options="region:'west',border:false" style="width:30px;">
	                                <p style="box-sizing:border-box;text-align:center;padding:130px 0 0 0;">
	                                    <a href="javascript:MMA034_02.ui.hiddenColunm();"><img src="/images/sample/btn_arrR.gif"/></a><br><br>
	                                    <a href="javascript:MMA034_02.ui.showColunm();"><img src="/images/sample/btn_arrL.gif"/></a>
	                                </p>
	                            </div>
	                            <div data-options="region:'center',border:false">
	                                <div class="easyui-layout" data-options="fit:true">
		                                <div data-options="region:'north',border:false">
		                                    <div class="h2_etc">
		                                       <p class="h2"><spring:message code="HIEN_COLUMN"/></p>
		                                       <p class="h2_btn">
		                                           <a href="javascript:MMA034_02.control.deleteUserDatagridInfo();" class="btnRefresh"><spring:message code="INIT"/></a>
		                                       </p>
		                                   </div>
		                                </div>
		                                <div data-options="region:'center',border:false" style="padding-top:0px;">
		                                    <form id="MMA034_02_form_02" name="MMA034_02_form_02" method="POST">
		                                        <input type="hidden" id="GRID_ID" name="GRID_ID" value="${GRID_ID}"/>
								                <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${_MEMBER.SESSION_COMPANY_CD}"/>
								                <input type="hidden" id="USER_ID" name="USER_ID" value="${_MEMBER.SESSION_USER_ID}"/>
								                <input type="hidden" id="PID" name="PID" value="${PID}"/>
		                                    </form>
		                                    <table id="MMA034_02_grid_02"></table>
		                                </div>
		                            </div>
	                            </div>
	                        </div>
	                    </div>
                    </div>
                </div>
                <div title="2">
                </div>
            </div>
        </div>
        <div data-options="region:'south',border:false" style="height:31px;padding-top:4px;text-align:right;">
            <a href="javascript:MMA034_02.control.insertUserDatagridInfo();" class="btnEnableProc"><spring:message code="APYDT"/></a>
            <a href="javascript:dialog.handle.close(dialog.getObject('MMA034_02_dailog_01'));" class="btnDisableProc"><spring:message code="CLOSE"/></a>
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-A034_02.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>