<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : MM-A001_07.jsp
  * @Description : 중계서버 메인화면
  * @Modification Information
  *
  * author YNI-Maker(이메일)
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMA001_07_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>      
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'west',border:false,split:true" style="width:380px;">
            <div class="easyui-layout" data-options="fit:true">
                <div data-options="region:'north',border:false,split:true" style="height:55px">
                    <div class="easyui-layout" data-options="fit:true">
                        <div data-options="region:'north',border:false" class="h2_etc">
                            <p class="h2"><spring:message code="CC_License"/></p>
                        </div>
                        <div data-options="region:'center',border:false">
                           <table class="gridT" style="margin-bottom:0px;">
                               <colgroup>
                                   <col style="width:80px;"/>
                                   <col style="width:;"/>
                               </colgroup>
                               <tr>
                                   <th>ID</th>
                                   <td>
                                       <input type="text" name="LICENSE_NO" id="LICENSE_NO" value="${SESSION_TOMS_FTA_CERT_KEY}" class="input_readOnly" readonly style="width:98%;"/>
                                   </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
                <div data-options="region:'center',border:false">
                    <div class="easyui-layout" data-options="fit:true">
		                <div data-options="region:'north',border:false" class="h2_etc">
		                    <p class="h2"><spring:message code="CC_Server 연결상태"/></p>
		                    <p class="h2_btn">
                                <a href="javascript:MMA001_07.control.selectServerList();" class="btnRefresh"><spring:message code="REFSH"/></a><%--새로고침--%>
                            </p>
		                </div>
		                <div data-options="region:'center',border:false">
		                   <table id="MMA001_07_grid_01"> </table>
		                </div>
		            </div>
                </div>
                <div data-options="region:'south',border:false,split:true" style="height:166px;">
                    <div class="easyui-layout" data-options="fit:true">
                        <div data-options="region:'north',border:false" class="h2_etc">
                            <p class="h2"><spring:message code="CC_시스템 설정상태"/></p>
                        </div>
                        <div data-options="region:'center',border:false">
                           <table class="gridT" style="margin-bottom:0px;">
                               <colgroup>
                                   <col style="width:80px;"/>
                                   <col style="width:;"/>
                               </colgroup>
                               <tr>
                                   <th>표시 항목</th>
                                   <td>
                                       <label><input type="checkbox" id="LOG_VIEW_1" name="LOG_VIEW_1" onchange="javascript:#;" value="Y" checked size="12" /><spring:message code="CC_Realtime log"/></label>
                                       <label><input type="checkbox" id="LOG_VIEW_2" name="LOG_VIEW_2" onchange="javascript:#;" value="Y" checked size="12" /><spring:message code="CC_System log"/></label>
                                   </td>
                                </tr>
                                <tr>
                                    <th colspan="2">설정 상태</th>
                                </tr>
                                <tr>
                                    <td colspan="2">
                                        <textarea style="width:98%;height:70px;resize:none;">
Used Auto log - ON
                                        </textarea>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div data-options="region:'center',border:false">
            <div class="easyui-layout" data-options="fit:true">
                <div data-options="region:'center',border:false,split:true">
		            <div class="easyui-layout" data-options="fit:true">
		                <div data-options="region:'center',border:false" class="width60">
		                    <div class="easyui-layout" data-options="fit:true">
		                        <div data-options="region:'north',border:false" class="h2_etc">
		                            <p class="h2"><spring:message code="CC_Schedule status"/></p>
		                        </div>
		                        <div data-options="region:'center',border:false">
			                        <form id="MMA001_07_form_01" name="MMA001_07_form_01" method="post">
					                    <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
					                    <input type="hidden" id="DEFAULT_LANGUAGE" name="DEFAULT_LANGUAGE" value="${_MEMBER.SESSION_DEFAULT_LANGUAGE}"/>
					                    <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${_MEMBER.SESSION_COMPANY_CD}"/>
					                </form>
		                           <table id="MMA001_07_grid_02"> </table>
		                        </div>
		                    </div>
		                </div>
		                <div data-options="region:'east',border:false,split:true" class="width40">
		                    <div class="easyui-layout" data-options="fit:true">
		                        <div data-options="region:'north',border:false" class="h2_etc">
		                            <p class="h2"><spring:message code="CC_Realtime Log"/></p>
		                        </div>
		                        <div data-options="region:'center',border:false" style="overflow:hidden;">
		                           <textarea id="MMA001_07_ta_01" readonly style="height:99%;width:99%;background:#000;color:#f1f1f1;resize:none;"></textarea>
		                        </div>
		                        <!-- div data-options="region:'south',border:false" style="text-align:center;">
		                            <a href="#" class="btn" style="width:48%;font-size:11pt;padding:2px;">Remove Log</a>
		                            <a href="#" class="btn" style="width:48%;font-size:11pt;padding:2px;">Save log</a>
		                        </div -->
		                    </div>
		                </div>
		                <div data-options="region:'south',border:false,split:true" style="height:190px;">
		                    <div class="easyui-layout" data-options="fit:true">
		                        <div data-options="region:'north',border:false" class="h2_etc">
		                            <p class="h2"><spring:message code="CC_System Access Log"/></p>
		                        </div>
		                        <div data-options="region:'center',border:false">
		                           <textarea id="MMA001_07_ta_02" readonly style="height:96%;width:99.5%;background:#f5f5f5;color:#000;resize:none;"></textarea>
		                        </div>
		                    </div>
		                </div>
		            </div>
		        </div>
		        <div data-options="region:'south',border:false" style="height:382px;padding-top:16px;">
		            <div class="easyui-layout" data-options="fit:true">
                        <div data-options="region:'center',border:false">
                            <div class="easyui-layout" data-options="fit:true" class="sec_area">
		                        <div data-options="region:'north',border:false" class="sec_tit" style="height:20px;overflow:hidden;">
		                            <ul class="tab_area">
		                                <li id="MMA001_07_divMove01" class="on"><a><spring:message code="CC_송수신 건수"/></a></li>
		                            </ul>
		                        </div>
		                        <div data-options="region:'center',border:false"  class="width80">
		                            <div id="MMA001_07_chart_01" style="width:100%; height:153px;background-color:#efefef;"></div>
		                        </div>
		                    </div>
                        </div>
                        <div data-options="region:'south',border:false,split:true" style="height:190px;">
                            <div class="easyui-layout" data-options="fit:true" class="sec_area">
                                <div data-options="region:'north',border:false" class="sec_tit" style="height:20px;overflow:hidden;">
                                    <ul class="tab_area">
                                        <li id="MMA001_07_divMove02" class="on"><a href="javascript:MMA001_07.ui.divMove('2');"><spring:message code="CC_데이터 처리량"/></a></li>
                                    </ul>
                                </div>
                                <div data-options="region:'center',border:false"  class="width80">
                                    <div id="MMA001_07_chart_02" style="width:100%; height:153px;background-color:#efefef;"></div>
                                </div>
                            </div>
                        </div>
                    </div>
		        </div>
		    </div>
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-A001_07.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>