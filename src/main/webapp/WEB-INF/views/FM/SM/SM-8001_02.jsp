<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : SM-8001_02.jsp
  * @Description : 서버간 연계 Test
  * @Modification Information
  *
  * author YNI-Maker(이메일)
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "SM8001_02_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>      
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center',border:false">
            <div class="easyui-layout" data-options="fit:true">
                <div data-options="region:'north',border:false" class="h2_etc">
                    <p class="h2">Paypal Test</p>
                </div>
                <form id="SM8001_02_form_01" name="SM8001_02_form_01" method="post">
                    <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${_MEMBER.SESSION_COMPANY_CD}"/>
                    <table class="gridT" style="margin-bottom:0px">
                        <colgroup>
                            <col width="140px" />
                            <col width="400px;" />
                            <col width="140px" />
                            <col width=";" />
                        </colgroup>
                        <tr>
                            <th scope="row" class="point2"><label>URL</label></th>
                            <td>
                                <input type="text" id="URL" value="/fm/sm/SM8001_02/executePaypalTest" style="width:96%;"/>
                            </td>
                            <th scope="row" class="point2"><label>Operation</label></th>
                            <td>
                                <input type="text" id="METHOD_NAME" name="METHOD_NAME" style="width:160px;"/>
                                <span style="padding-left:30px;"><a href="javascript:SM8001_02.control.executePaypalTest();" class="btnSendData">Send</a></span>
                            </td>
                        </tr>
                    </table>
               </form>
               <div data-options="region:'north',border:false" class="h2_etc">
                    <p class="h2">DHL Test</p>
                </div>
                <form id="SM8001_02_form_03" name="SM8001_02_form_03" method="post">
                    <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${_MEMBER.SESSION_COMPANY_CD}"/>
                    <table class="gridT" style="margin-bottom:0px">
                        <colgroup>
                            <col width="140px" />
                            <col width="400px;" />
                            <col width="140px" />
                            <col width=";" />
                        </colgroup>
                        <tr>
                            <th scope="row" class="point2"><label>URL</label></th>
                            <td>
                                <input type="text" id="URL" value="/fm/sm/SM8001_02/executeDhlTest" style="width:96%;"/>
                            </td>
                            <th scope="row" class="point2"><label>Operation</label></th>
                            <td>
                                <input type="text" id="METHOD_NAME" name="METHOD_NAME" style="width:160px;"/>
                                <span style="padding-left:30px;"><a href="javascript:SM8001_02.control.executeDhlTest();" class="btnSendData">Send</a></span>
                            </td>
                        </tr>
                    </table>
               </form>
               <div data-options="region:'north',border:false" class="h2_etc">
                    <p class="h2">Request</p>
                </div>
                <form id="SM8001_02_form_02" name="SM8001_02_form_02" method="post">
                    <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${_MEMBER.SESSION_COMPANY_CD}"/>
                    <table class="gridT" style="margin-bottom:0px">
                        <colgroup>
                            <col width="140px" />
                            <col width="140px;" />
                            <col width="140px" />
                            <col width=";" />
                        </colgroup>
                        <tr>
                            <th scope="row" class="point2"><label>URL</label></th>
                            <td colspan="3">
                                <input type="text" id="URL" value="/fm/sm/SM8001_02/executeRelayBatch" style="width:60%;"/>
                                <span style="padding-left:30px;"><a href="javascript:SM8001_02.control.executeRelayBatch();" class="btnSendData">Send</a></span>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row" class="point2"><label>Service ID</label></th>
                            <td>
                                <input type="text" name="SERVICE_ID" id="SERVICE_ID" style="width:120px;"/>
                            </td>
                            <th scope="row"><label>Server ID</label></th>
                            <td>
                                <input type="text" name="SERVER_ID" id="SERVER_ID" style="width:120px;"/>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row" class="point2"><label>Parameter</label></th>
                            <td colspan="3">
                                <textarea id="REQUEST_PARAM" name="REQUEST_PARAM" style="width:99.5%;height:100px">{"RequestType": "D","Address1": "Minervum 7118","Address2":"4817ZN Breda","CountryCode": "NL","CountryName": "NETHERLANDS","PostalCode": "4817 ZN","City": "Breda","OriginCountryCode": "NL"}</textarea>
                            </td>
                        </tr>
                    </table>
               </form>
            </div>
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/FM/SM/SM-8001_02.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>