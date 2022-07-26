<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : SM-8001_01.jsp
  * @Description : Salesforce 인터페이스(RESTFull) Test
  * @Modification Information
  *
  * author YNI-Maker(이메일)
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "SM8001_01_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>      
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'north',border:false">
            <div class="easyui-layout" data-options="fit:true">
                <div data-options="region:'north',border:false" class="h2_etc">
                    <p class="h2">Acess Token Info.</p>
                    <p class="h2_btn">
                        <a href="javascript:SM8001_01.control.selectAccessToken();" class="btnSendData">Send</a>
                     </p>
                </div>
                <div data-options="region:'center',border:false">
		            <form id="SM8001_01_form_01" name="SM8001_01_form_01" method="post">
		                <table class="dataT" style="margin-bottom:0px">
		                    <colgroup>
		                        <col width="140px" />
		                        <col width=";" />
		                        <col width="140px" />
		                        <col width=";" />
		                    </colgroup>
		                    <tbody>                             
		                        <tr>
		                            <th scope="row" class="point2">
		                                <label>Token Name</label>
		                            </th>
		                            <td>
		                                <input type="text" name="TOKEN_NAME" id="TOKEN_NAME" value="SCK_FSB" class="easyui-validatebox" required="true" style="width:98%;"/>
		                            </td>
		                            <th scope="row" class="point2">
		                                <label>Access Token URL</label>
		                            </th>
		                            <td>
		                                <input type="text" name="TOKEN_URL" id="TOKEN_URL" value="https://test.salesforce.com/services/oauth2/token" class="easyui-validatebox" required="true" style="width:98%;"/>
		                            </td>
		                        </tr>
		                        <tr>
		                            <th scope="row" class="point2">
		                                <label>User Name</label>
		                            </th>
		                            <td>
		                                <input type="text" name="USER_ID" id="USER_ID" value="if_user@hanwha.com.sandbox2" class="easyui-validatebox" required="true" style="width:98%;"/>
		                            </td>
		                            <th scope="row" class="point2">
		                                <label>Password</label>
		                            </th>
		                            <td>
		                                <input type="text" name="USER_PW" id="USER_PW" value="gksghkxpzmdnls1234!" class="easyui-validatebox" required="true" style="width:98%;"/>
		                            </td>
		                        </tr>
		                        <tr>
		                            <th scope="row" class="point2">
		                                <label>Client ID</label>
		                            </th>
		                            <td>
		                                <input type="text" name="CLIENT_ID" id="CLIENT_ID" value="3MVG9rnryk9FxFMXeB1wnPvbBeE5jLTwPdN65oN7iPshLB97e_cbv9fAN1r.q736SN.NguQ45Cw5zvJjrOQQm" class="easyui-validatebox" required="true" style="width:98%;"/>
		                            </td>
		                            <th scope="row" class="point2">
		                                <label>Client Secret</label>
		                            </th>
		                            <td>
		                                <input type="text" name="CLIENT_SECRET" id="CLIENT_SECRET" value="DC704AFE25B311915270FE58142371F9831AC270D64D4972381B95DBAEE543C1" class="easyui-validatebox" required="true" style="width:98%;"/>
		                            </td>
		                        </tr>
		                    </tbody>
		                </table>
		            </form>
		        </div>
		    </div>
        </div>
        <div data-options="region:'center',border:false" style="padding-top:12px;">
            <div class="easyui-layout" data-options="fit:true">
                <div data-options="region:'north',border:false" class="h2_etc">
                    <p class="h2">Request</p>
                    <p class="h2_btn">
                        <a href="javascript:SM8001_01.control.executeInterface();" class="btnSendData">Send</a>
                     </p>
                </div>
                <div data-options="region:'center',border:false">
                    <div class="easyui-layout" data-options="fit:true">
		                <div data-options="region:'north',border:false">
		                   <form id="SM8001_01_form_02" name="SM8001_01_form_02" method="post">
		                        <table class="gridT" style="margin-bottom:0px">
		                            <colgroup>
		                                <col width="140px" />
		                                <col width=";" />
		                                <col width="140px" />
		                                <col width=";" />
		                            </colgroup>
		                            <tr>
		                                <th scope="row" class="point2">
		                                    <label>Access Token</label>
		                                </th>
		                                <td colspan="3">
		                                    <input type="text" name="ACCESS_TOKEN" id="ACCESS_TOKEN" class="easyui-validatebox" required="true" style="width:98%;"/>
		                                </td>
		                            </tr>
		                            <tr>
                                        <th scope="row" class="point2">
                                            <label>Request URL</label>
                                        </th>
                                        <td colspan="3">
                                            <input type="text" name="REQ_URL" id="REQ_URL" value="https://sckorea--fullsb.my.salesforce.com/services/apexrest/SCK/interface" class="easyui-validatebox" required="true" style="width:98%;"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th scope="row" class="point2">
                                            <label>Request parameter</label>
                                        </th>
                                        <td colspan="3">
                                            <input type="text" name="REQ_PARAMS" id="REQ_PARAMS" value='{"Interface_id": "IF-WS-ACTIVITY-VIEW-001","iRecordSeq": "a1S0p000000MjIdEAK","queryDebug":"false"}' style="width:98%;"/>
                                        </td>
                                    </tr>
		                        </table>
		                   </form>
		               </div>
		               <div data-options="region:'center',border:false" style="padding-top:12px;">
                           <div class="easyui-layout" data-options="fit:true">
				               <div data-options="region:'north',border:false" class="h2_etc">
				                   <p class="h2">Response Data</p>
				               </div>
				               <div data-options="region:'center',border:false">
	                               <textarea id="SM8001_01_ta_01" style="width:99.5%;height:99%"></textarea>
	                           </div>
	                       </div>
                       </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/FM/SM/SM-8001_01.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>