<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : MM-A031_05.jsp
  * @Description : 유지보수 요청서 출력
  * @Modification Information
  *
  * author YNI-Maker(이메일)
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMA031_05_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>      
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'north',border:false" style="height:30px;overflow:hidden;">
            <div class="btn_R">
                <a href="javascript:MMA031_05.control.htmlConvertExcel();" class="btnPopPrint" style="display:none;"><spring:message code="DOWN"/></a>
                <a href="javascript:dialog.handle.close(dialog.getObject('MMA031_05_dailog_01'));" class="btnPopClose"><spring:message code="CLOSE"/><%--닫기--%></a>
            </div>
        </div>
        <div data-options="region:'center',border:false" style="background-color:#fff;">
            <form id="MMA031_05_form_01" name="MMA031_05_form_01" enctype="multipart/form-data" method="post">
                <input type="hidden" name="INQUIRY_NO" id="INQUIRY_NO" value="${INQUIRY_NO}"/>
                <div style="padding:10px 5px 10px 5px;">
                    <p style="font-size:21pt;font-weight:bold;text-align:center;">문의 조치 결과</p>
                </div>
                <div style="padding:0 5px 0 5px;">
                    <table class="gridT" style="margin-bottom:0px;">
                        <colgroup>
                            <col width="137px" />
                            <col width=";" />
                            <col width="137px;" />
                            <col width=";" />
                            <col width="137px;" />
                            <col width=";" />
                        </colgroup>
                        <tbody>
                            <tr>
                                <th><spring:message code="INQUI, TXT_COMPANY_NAME"/></th>
                                <td colspan="2">
                                    <input id="COMPANY_NAME" name="COMPANY_NAME" value="${COMPANY_NAME}" readonly class="input_readOnly" style="width:98%;height:20px;"/>
                                </td>
                                <th><spring:message code="RCPTN,NAME"/></th>
                                <td colspan="2">
                                    <input id="RECEIPT_NAME" name ="RECEIPT_NAME" value="${RECEIPT_NAME}" readonly class="input_readOnly" style="width:98%;height:20px;"/>
                                </td>
                            </tr>
                            <tr>
                                <th><spring:message code="INQER, CNTACT"/></th>
                                <td colspan="2">
                                    <input id="INQUIRY_TEL" name="INQUIRY_TEL" value="${INQUIRY_TEL}" readonly class="input_readOnly" style="width:98%;height:20px;"/>
                                </td>
                                <th><spring:message code="RCPTN, CNTACT"/></th>
                                <td colspan="2">
                                    <input id="RECEIPT_PHONE" name ="RECEIPT_PHONE" value="${RECEIPT_PHONE}" readonly class="input_readOnly" style="width:98%;height:20px;"/>
                                </td>
                            </tr>
                            <tr>
                                <th><spring:message code="INQER, TXT_EMAIL"/></th>
                                <td colspan="2">
                                    <input id="INQUIRY_EMAIL" name="INQUIRY_EMAIL" value="${INQUIRY_EMAIL}" readonly class="input_readOnly" style="width:98%;height:20px;"/>
                                </td>
                                <th><spring:message code="RCPTN, TXT_EMAIL"/></th>
                                <td colspan="2">
                                    <input id="RECEIPT_EMAIL" name ="RECEIPT_EMAIL" value="${RECEIPT_EMAIL}" readonly class="input_readOnly" style="width:98%;height:20px;"/>
                                </td>
                            </tr>
                            <tr>
                                <th><spring:message code="INQUI,SEPAT"/></th>
                                <td colspan="5">
                                    <input id="INQUIRY_TYPE_NAME" name="INQUIRY_TYPE_NAME" value="${INQUIRY_TYPE_NAME}" readonly class="input_readOnly" style="width:99%;height:20px;"/>
                                </td>
                            </tr>
                            <tr>
                                <th><spring:message code="INQER"/></th>
                                <td colspan="2">
                                    <input id="INQUIRY_NAME" name="INQUIRY_NAME" value="${INQUIRY_NAME}" readonly class="input_readOnly" style="width:98%;height:20px;"/>
                                </td>
                                <th><spring:message code="조치자"/></th>
                                <td colspan="2">
                                    <input id="ANSWER_NAME" name ="ANSWER_NAME" value="${ANSWER_NAME}" readonly class="input_readOnly" style="width:98%;height:20px;"/>
                                </td>
                            </tr>
                            <tr>
                                <th colspan="2" style="height:24px;"><spring:message code="REQ,DATE"/></th>
                                <th colspan="2" style="height:24px;"><spring:message code="RCPT,DATE"/></th>
                                <th colspan="2" style="height:24px;"><spring:message code="TREAT,DATE"/></th>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <input id="INQUIRY_DATE" name="INQUIRY_DATE" value="${INQUIRY_DATE}" readonly class="input_readOnly" style="width:98%;height:20px;text-align:center;"/>
                                </td>
                                <td colspan="2">
                                    <input id="RECEIPT_DATE" name="RECEIPT_DATE" value="${RECEIPT_DATE}" readonly class="input_readOnly" style="width:98%;height:20px;text-align:center;"/>
                                </td>
                                <td colspan="2">
                                    <input id="SEND_DATE" name="SEND_DATE" value="${SEND_DATE}" readonly class="input_readOnly" style="width:98%;height:20px;text-align:center;"/>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div style="padding:3px 5px 0 5px;">
                    <table class="gridT" style="margin-bottom:0px;">
                        <colgroup>
                            <col width="" />
                            <col width="" />
                        </colgroup>
                            <tr>
                                <th colspan="2" style="height:24px;"><spring:message code="INQUI,CONTS"/></th>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <textarea name="INQUIRY_CONTENTS" id="INQUIRY_CONTENTS" readonly class="input_readOnly" style="height:192px;width:99%;">${INQUIRY_CONTENTS}</textarea>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div style="padding:3px 5px 0 5px;">
                    <table class="gridT" style="margin-bottom:0px;">
                        <colgroup>
                            <col width="" />
                            <col width="" />
                        </colgroup>
                            <tr>
                                <th style="height:24px;"><spring:message code="CNSLT,CONTS"/></th>
                                <th style="height:24px;"><spring:message code="ANSWR,CONTS"/></th>
                            </tr>
                            <tr>
                                <td>
                                    <textarea name="SURVEY_RESULT" id="SURVEY_RESULT" readonly class="input_readOnly" style="height:192px;width:99%;">${SURVEY_RESULT}</textarea>
                                </td>
                                <td>
                                    <textarea name="SEND_CONTENTS" id="SEND_CONTENTS" readonly class="input_readOnly" style="height:192px;width:99%;">${SEND_CONTENTS}</textarea>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div style="padding:3px 5px 0 5px;display:none;">
                    <table class="gridT" style="margin-bottom:0px;">
                        <tr>
                            <td>
                                <p style="font-size:11pt;text-align:center;">위와 같이 지원했음을 확인합니다.</p>
                            </td>
                        </tr>
                    </table>
                </div>
                <div style="padding:10px 5px 10px 5px;display:none;">
                    <div>
                        <p style="font-size:12pt;font-weight:bold;text-align:center;">(주)대유씨엔에이</p>
                    </div>
                </div>
            </form>
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-A031_05.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>