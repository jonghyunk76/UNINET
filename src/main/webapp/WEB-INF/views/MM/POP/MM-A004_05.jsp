<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : MM-A004_05.jsp
  * @Description : 개별 보고서 발급
  * @Modification Information
  *
  * author 이재욱
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMA004_05_dailog_01"으로 하셔야 합니다.
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
            <form id="MMA004_05_form_01" name="MMA004_05_form_01" method="POST" target="MMA004_05_frame_1">
                <input type="hidden" id="PRODUCT_LINE_CD" name="PRODUCT_LINE_CD" value="${PRODUCT_LINE_CD}"/>
                <input type="hidden" id="REPORT_PRINT_TYPE" name="REPORT_PRINT_TYPE" value="P"/>
                <input type="hidden" id="REPORT_TYPE" name="REPORT_TYPE" value="pdf"/>
                <input type="hidden" id="VENDOR_CD" name="VENDOR_CD" value="${VENDOR_CD}"/>
                <input type="hidden" id="START_DATE" name="START_DATE" value="${FROM_DATE}"/>
                <input type="hidden" id="END_DATE" name="END_DATE" value="${TO_DATE}"/>
                <input type="hidden" id="DOC_FILE_NAME" name="DOC_FILE_NAME" value="${DOC_FILE_NAME}"/>
                <input type="hidden" id="file" name="file" value="${FILE_NAME}"/>
                <input type="hidden" id="SOCKET_ID" name="SOCKET_ID"/>
                <table class="dataT">
                    <tbody>
                        <tr>
                            <td>
                                <span style="float:right;">
                                    <a href="javascript:MMA004_05.control.executeIssueDoc('pdf');" style="padding-right:3px;"><img src="/images/icon/file_pdf.png" width="20px" height="20px" title="PDF"/></a>
                                    <a href="javascript:MMA004_05.control.executeIssueDoc('xls');" style="padding-right:3px;"><img src="/images/icon/file_xls.png" width="20px" height="20px" title="Excel"/></a>
                                    <a href="javascript:MMA004_05.control.executeIssueDoc('doc');" style="padding-right:3px;"><img src="/images/icon/file_doc.png" width="20px" height="20px" title="Word"/></a>
                                </span>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
        <div data-options="region:'center',border:false" style="overflow:hidden">
            <div class="easyui-layout" data-options="fit:true" style="overflow:hidden">
                <iframe name="MMA004_05_frame_1" id="MMA004_05_frame_1" style="width:100%;height:100%;background-color:#fff;border:1px solid #c7cbce;"></iframe>
            </div>
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-A004_05.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body> 
</html>