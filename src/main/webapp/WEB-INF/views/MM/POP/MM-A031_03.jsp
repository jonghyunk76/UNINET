<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : MM-A031_03.jsp
  * @Description : 문의 이력 조회
  * @Modification Information
  *
  * author carlos
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMA031_03_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>      
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset=utf-8>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="apple-mobile-web-app-title" content="">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=yes">
    <meta http-equiv="Expires" content="-1">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="No-Cache">
    <title>TOMS(Total Origin Management Solution)</title>
    <link href="/images/ci/TOMSlogo.png" rel="shortcut icon" type="image/x-icon" sizes="32x32"/>
    <!--[if lt IE 9]>
        <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="/css/main/import.css">
    <!-- css 충돌 -->
    <link rel="stylesheet" type="text/css" href="/css/main/yni-ui.css" />
    <!--[if IE 7]>
        <link rel="stylesheet" type="text/css" href="../css/ie7.css">
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="/css/jqplot/jquery.jqplot.min.css" />
    <link rel="stylesheet" type="text/css" href="/css/jqplot/jquery-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="/css/jqplot/shCoreDefault.min.css"/>
    <link rel="stylesheet" type="text/css" href="/css/jqplot/shThemejqPlot.min.css"/>
    <script type="text/javascript" src="/js/frame/plugins/jquery-1.6.min.js"></script>
    <script type="text/javascript" src="/js/frame/plugins/jquery.number.js"></script>
    <script type="text/javascript" src="/js/frame/plugins/file/bluebird.min.js"></script>
    <!-- script type="text/javascript" src="/js/frame/plugins/file/html2canvas.min.js"></script>
    <script type="text/javascript" src="/js/frame/plugins/file/jspdf.min.js"></script -->
    <script type="text/javascript" src="/js/frame/plugins/file/jquery.table2excel.js"></script>
    <script type="text/javascript" src="/js/frame/plugins/github/jquery.inputmask.bundle.js"></script>
    <script type="text/javascript" src="/js/frame/message/message_${LANGUAGE}.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.properties.js"></script>
    <script type="text/javascript" src="/js/frame/common/prototype.js"></script>
    <script type="text/javascript" src="/js/frame/common/utils.js"></script>
    <script type="text/javascript" src="/js/frame/plugins/yniui/jquery.yni-ui.min.js"></script>
    <script type="text/javascript" src="/js/frame/plugins/yniui/datagrid-filter.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.validator.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.form.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.grid.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.dialog.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.combo.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.calendar.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.tooltip.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.tab.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.tree.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.ctrl.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.themes.js"></script>
    <script type="text/javascript" src="/js/frame/plugins/design.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.chart.js"></script>
    <script type="text/javascript" src="/js/frame/common/surveyHelper.js"></script>
</head>
<body style="padding:2px 3px 3px 2px;">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'north',border:false" class="sec_tit" style="overflow:hidden;">
            <ul class="tab_area">
                <li id="MMA031_03_divMove01" class="on"><a href="javascript:MMA031_03.ui.divMove('1');"><b><spring:message code="UPDAT"/></b></a></li>
                <li id="MMA031_03_divMove02"><a href="javascript:MMA031_03.ui.divMove('2');"><b><spring:message code="INQUI"/></b></a></li>
            </ul>
        </div>
        <div data-options="region:'center',border:false" style="padding-top:3px;">
            <div id="MMA031_03_tabs_01" class="easyui-tabs" data-options="fit:true,showHeader:false,plain:true">
                <div title="1">
                    <div class="easyui-layout" data-options="fit:true">
                        <div data-options="region:'west',border:false,split:true" class="width50">
                            <div class="easyui-layout" data-options="fit:true">
                                <div data-options="region:'center',border:false">
                                    <div class="easyui-layout" data-options="fit:true">
                                        <div data-options="region:'north',border:false" class="h2_etc">
                                            <p class="h2"><spring:message code="NOTIC"/></p>
                                            <p class="h2_btn">
                                                <a href="javascript:MMA031_03.dialog.openDialog_2();" class="btnSelectClick"><spring:message code="DETIL,INFMT"/></a>
                                            </p>
                                        </div>
                                        <div data-options="region:'center',border:false">
                                           <form id="MMA031_03_form_03" name="MMA031_03_form_03" method="post">
                                               <input type="hidden" name="COMPANY_CD" id="COMPANY_CD" value="KJ100"> <!-- 오픈시에는 "KJ100"으로 변경할 것 -->
                                               <input type="hidden" name="INQUIRY_TYPE" id="INQUIRY_TYPE" value="${INQUIRY_TYPE}">
                                               <input type="hidden" name="CERTIFY_TYPE" id="CERTIFY_TYPE" value="${CERTIFY_TYPE}">
                                               <input type="hidden" name="BBS_TYPE" id="BBS_TYPE" value="A"> <!-- A:공통, 1:관리 사이트 -->
                                           </form>
                                           <table id="MMA031_03_grid_02"> </table>
                                        </div>
                                    </div>
                                </div>
                                <div data-options="region:'south',border:false,split:true" style="height:400px;">
                                    <div class="easyui-layout" data-options="fit:true">
                                        <div data-options="region:'north',border:false" class="h2_etc">
                                            <p class="h2"><spring:message code="UPDAT"/></p>
                                            <p class="h2_btn">
                                                <a href="javascript:MMA031_03.dialog.openDialog_2();" class="btnSelectClick"><spring:message code="DETIL,INFMT"/></a>
                                            </p>
                                        </div>
                                        <div data-options="region:'center',border:false">
                                           <form id="MMA031_03_form_04" name="MMA031_03_form_04" method="post">
                                               <input type="hidden" name="COMPANY_CD" id="COMPANY_CD" value="KJ100"> <!-- 오픈시에는 "KJ100"으로 변경할 것 -->
                                               <input type="hidden" name="INQUIRY_TYPE" id="INQUIRY_TYPE" value="${INQUIRY_TYPE}">
                                               <input type="hidden" name="CERTIFY_TYPE" id="CERTIFY_TYPE" value="${CERTIFY_TYPE}">
                                               <input type="hidden" name="BBS_TYPE" id="BBS_TYPE" value="U"> <!-- U:업데이트 -->
                                           </form>
                                           <table id="MMA031_03_grid_03"> </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div data-options="region:'center',border:false">
                            <div class="easyui-layout" data-options="fit:true">
                                <div data-options="region:'north',border:false" class="h2_etc" style="padding-bottom:4px;">
                                    <p class="h2"><spring:message code="DETIL,CONTS"/></p>
                                </div>
                                <div data-options="region:'center',border:true" style="background-color:#fff;padding:3px;">
                                    <div id="MMA031_03_html_01"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div title="1">
                    <div class="easyui-layout" data-options="fit:true">
                        <div data-options="region:'north',border:false" class="sch_box2">
                            <div class="sch_boxIn2">
                                <form id="MMA031_03_form_01" name="MMA031_03_form_01" method="post">
                                    <input type="hidden" name="COMPANY_CD" id="COMPANY_CD" value="KJ100"> <!-- 오픈시에는 "KJ100"으로 변경할 것 -->
                                    <input type="hidden" name="INQUIRY_NAME" id="INQUIRY_NAME" value="${INQUIRY_NAME}">
                                    <input type="hidden" name="INQUIRY_ID" id="INQUIRY_ID" value="${INQUIRY_ID}">
                                    <input type="hidden" name="INQUIRY_POSITION" id="INQUIRY_POSITION" value="${INQUIRY_POSITION}">
                                    <input type="hidden" name="INQUIRY_TYPE" id="INQUIRY_TYPE" value="${INQUIRY_TYPE}">
                                    <input type="hidden" name="INQUIRY_TEL" id="INQUIRY_TEL" value="${INQUIRY_TEL}">
                                    <input type="hidden" name="INQUIRY_EMAIL" id="INQUIRY_EMAIL" value="${INQUIRY_EMAIL}">
                                    <input type="hidden" name="CUSTOMER_NAME" id="CUSTOMER_NAME" value="${CUSTOMER_NAME}">
                                    <input type="hidden" name="APPLICATION_SERVICE_TYPE" id="APPLICATION_SERVICE_TYPE" value="${APPLICATION_SERVICE_TYPE}">
                                    <input type="hidden" name="CERTIFY_TYPE" id="CERTIFY_TYPE" value="${CERTIFY_TYPE}">
                                    <input type="hidden" name="FTA_NATION" id="FTA_NATION" value="${FTA_NATION}">
                                    <input type="hidden" name="LICENSE_KEY" id="LICENSE_KEY" value="${LICENSE_KEY}">
                                    <input type="hidden" name="MEMBER_ID" id="MEMBER_ID" value="${MEMBER_ID}">
                                    <input type="hidden" name="DEFAULT_LANGUAGE" id="DEFAULT_LANGUAGE" value="${LANGUAGE}">
                                    <table>
                                        <caption><spring:message code="TXT_DATA_SEARCH_CONDITION"/><%--데이타 검색조건 설정--%></caption>
                                        <colgroup>
                                            <col width="110px" />
                                            <col width="230px" />
                                            <col width="120px" />
                                            <col width="100px" />
                                            <col width="120px" />
                                            <col width=";"/>
                                        </colgroup>
                                        <tbody>
                                            <tr>
                                                <th scope="row"><spring:message code="INQUI,DATE"/></th>
                                                <td>
                                                    <input type="text" id="SCH_FROM_DATE" name="SCH_FROM_DATE" style="width:95px;"/> ~ 
                                                    <input type="text" id="SCH_TO_DATE" name="SCH_TO_DATE" style="width:95px;"/>
                                                </td>
                                                <th scope="row"><spring:message code="TXT_RESLT_STAT"/></th>
                                                <td>
                                                    <input type="text" name="STATUS" id="STATUS" style="width:90px;"/>
                                                </td>
                                                <th scope="row"><spring:message code="SHCDT"/><%--조건검색--%></th>
                                                <td>
                                                    <input type="text" id="schKeyField" name="schKeyField" style="width:120px"/> 
                                                    <input type="text" id="schKeyWord" name="schKeyWord" class="easyui-validatebox" search="MMA031_03.control.selectMainList" style="width:150px;paddig:0;"/>
                                                    <input type="text" id="schKeyLike" name="schKeyLike" style="width:80px"/>
                                                </td>
                                                <td class="txt_R" style="padding-left:10px;">
                                                   <a href="javascript:MMA031_03.control.selectMainList();" class="easyui-linkbutton pop_sch"><spring:message code="SRCH"/><%--조회--%></a>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </form>
                            </div>
                        </div>
                        <!-- //검색조건 -->
                        <div data-options="region:'center',border:false" style="height:100%;padding-top:6px;">
                            <div class="easyui-layout" data-options="fit:true">
                                <div data-options="region:'north',border:false" class="h2_etc">
                                    <p class="h2"><spring:message code="INQUI, LIST"/></p>
                                    <p class="h2_btn">
                                        <a href="javascript:MMA031_03.dialog.openDialog_1('insert');" class="btnNewRegiste"><spring:message code="INQUI,REGER"/></a>
                                        <a href="javascript:MMA031_03.dialog.openDialog_1('update');" class="btnEdit"><spring:message code="CONTS,MOD"/></a>
                                        <a href="javascript:MMA031_03.control.deleteInquiryEmailRecord();" class="btnMainDelete"><spring:message code="INQUI,CANCL"/></a>
                                        <a href="javascript:MMA031_03.dialog.openDialog_2();" class="btnSelectClick"><spring:message code="ANSWR,DETIL"/></a>
                                        <a href="javascript:MMA031_03.excel.excelDownload_1();" class="btnExlDown"><spring:message code="EXCEL, DOWN"/><%--엑셀다운로드--%></a>
                                    </p>
                                </div>
                                <div data-options="region:'center',border:false">
                                   <form id="MMA031_03_form_02" name="MMA031_03_form_02" method="post">
                                       <input type="hidden" name="INQUIRY_NO" id="INQUIRY_NO"/>
                                   </form>
                                   <table id="MMA031_03_grid_01"> </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript">
        DEFAULT_LANGUAGE = "${LANGUAGE}";
    </script>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-A031_03.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body> 
</html>