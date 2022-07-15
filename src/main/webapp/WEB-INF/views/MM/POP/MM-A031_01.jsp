<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : MM-A031_01.jsp
  * @Description : 메일 문의하는 화면
  * @Modification Information
  *
  * author YNI-Maker(이메일)
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMA031_01_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>      
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
        <div class="easyui-layout" data-options="region:'north',border:false" style="padding:5px 0px 5px 0px;color:#3e3e3e;">
           <table>
               <tr>
                   <td style="width:140px;height:110px;text-align:center;"><img src="/images/icon/inquiry.png"/></td>
                   <td>
                       <h2 style="font-size:23px;"><spring:message code="ONLINE_INQUIRE"/></h2>
                       <p style="font-size:15px;padding-top:10px;"><spring:message code="WHAT_HELP_YOU"/><br>
                           <spring:message code="TOMS_APPLICATION_INQUIRE_SEND"/>
                       </p>
                   </td>
               </tr>
           </table>
        </div>
        <div data-options="region:'center',border:false">
            <form id="MMA031_01_form_01" name="MMA031_01_form_01" method="post">
                <input type="hidden" id="INQUIRY_TYPE" name="INQUIRY_TYPE" value="1"/>
                <div style="padding:0 5px 0 5px;">
	                <table class="dataT">
	                    <colgroup>
	                        <col width="140px" />
	                        <col width=";" />
	                        <col width="140px;" />
	                        <col width=";" />
	                    </colgroup>
	                    <tbody>
	                        <tr>
	                            <th class="point2"><spring:message code="INQER_NAME"/></th>
	                            <td>
	                                <input id="INQUIRY_NAME" name="INQUIRY_NAME" class="easyui-validatebox" required="true" value="${INQUIRY_NAME}" style="width:98%;height:24px;"/>
	                            </td>
	                            <th><spring:message code="TXT_DUTY_POS"/></th>
	                            <td>
	                                <input id="INQUIRY_POSITION" name ="INQUIRY_POSITION" value="${INQUIRY_POSITION}" style="width:98%;height:24px;"/>
	                            </td>
	                        </tr>
	                        <tr>
                                <th class="point2"><spring:message code="TXT_EMAIL"/></th>
                                <td>
                                    <input id="INQUIRY_EMAIL" name ="INQUIRY_EMAIL" class="easyui-validatebox" required="true" value="${INQUIRY_EMAIL}" style="width:98%;height:24px;" />
                                </td>
                                <th class="point2"><spring:message code="CNTACT"/></th>
                                <td>
                                    <input id="INQUIRY_TEL" name ="INQUIRY_TEL" class="easyui-validatebox" required="true" value="${INQUIRY_TEL}" style="width:98%;height:24px;"/>
                                </td>
                            </tr>
	                        <tr>
	                            <th class="point2"><spring:message code="CNSLT_METHD_TYPE"/></th>
	                            <td colspan="3">
	                                <label><input type="radio" id="INQUIRY_RESPONSE" name ="INQUIRY_RESPONSE" value="1" checked/><spring:message code="EMAIL_CON"/></label>
	                                <label><input type="radio" id="INQUIRY_RESPONSE" name ="INQUIRY_RESPONSE" value="2"/><spring:message code="TEL_CON"/></label>
	                                <p><spring:message code="TXT_INQUIRY_MSG4"/></p>
	                            </td>
	                        </tr>
	                        <tr>
	                            <th class="point2"><spring:message code="TITLE"/></th>
	                            <td colspan="3">
	                                <input id="INQUIRY_TITLE" name="INQUIRY_TITLE" class="easyui-validatebox" required="true" value="${INQUIRY_TITLE}" style="width:99%;height:24px;"/>
	                            </td>
	                        </tr>
	                        <tr>
	                            <th class="point2"><spring:message code="INQUIRY_CONTENTS"/></th>
	                            <td colspan="3">
	                                <textarea name="INQUIRY_CONTENTS" id="INQUIRY_CONTENTS" class="easyui-validatebox" required="true" style="height:280px;width:99%;">${INQUIRY_CONTENTS}</textarea>
	                            </td>
	                        </tr>
	                    </tbody>
	                </table>
	            </div>
                <div style="text-align:center;">
                    <span id="MMA031_01_span_01"><a href="javascript:MMA031_01.control.insertInquiryEmailRecord();" class="btnLoginBlue"><spring:message code="TXT_SEND"/></a></span>
                    <a href="javascript:MMA031_01.dialog.close();" class="btnLoginGrey"><spring:message code="CLOSE"/></a>
                </div>
            </form>
        </div>
	</div>
	
	<script type="text/javascript" src="/js/views/MM/POP/MM-A031_01.js"></script>
	<script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>