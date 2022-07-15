<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : MM-A016_02.jsp
  * @Description : 엑셀업로드 데이터 미리보기
  * @Modification Information
  *
  * author carlos
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMA016_02_dailog_01"으로 하셔야 합니다.
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
                      <spring:message code="TXT_UPLOAD_FILE"/><%-- 파일 선택 후 업로드하시면 다음 단계로 이동할 수 있습니다. --%>
        </div>
        <div data-options="region:'center',border:false" style="padding-top:12px;">
            <div class="easyui-layout" data-options="fit:true">
		        <div data-options="region:'north',border:false">
		            <div class="easyui-layout" data-options="fit:true">
		                <div data-options="region:'north',border:false" style="overflow:hidden;">
		                    <p class="h2"><spring:message code="FILE, UPLOD"/><%-- 파일 업로드 --%></p>
		                </div>
		                <div data-options="region:'center',border:false">
		                    <table>
		                        <colgroup>
		                            <col width=";" />
		                            <col width="80px;"/>
		                        </colgroup>
		                        <tr>
		                            <td>
		                                <form id="MMA016_02_form_02" name="MMA016_02_form_02" enctype="multipart/form-data" method="post">
		                                    <input type="hidden" id="SKIP_COL_NO" name="SKIP_COL_NO"/>
		                                    <input type="hidden" id="SHEET_NAME" name="SHEET_NAME"/>
		                                    <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
		                                    <input type="hidden" id="IF_CD" name="IF_CD" value="${IF_CD}"/>
		                                    <input type="hidden" id="USER_ID" name="USER_ID" value="${USER_ID}"/>
		                                    <input type="hidden" id="VIEW_TYPE" name="VIEW_TYPE"/>
		                                    <input type="hidden" id="RELOAD_FLAG" name="RELOAD_FLAG" value="Y"/>
		                                    <table class="gridT" style="margin-bottom:0;">
			                                    <caption><spring:message code="FILE, SELET"/><%--파일선택--%></caption>
			                                    <colgroup>
			                                        <col width="170px" />
			                                        <col width=";"/>
			                                    </colgroup>
			                                    <tbody>
			                                        <tr id="MMA016_02_tr_01" style="display:none;">
                                                        <th scope="row"><spring:message code="FNISH, DTE"/><%--마감날짜--%></th>
                                                        <td>
                                                            <input type="hidden" id="YYYYMM" name="YYYYMM"/>
						                                    <input type="text" id="YYYYMM_CAL" name="YYYYMM_CAL" class="easyui-validatebox" style="width:100px;"
						                                           onblur="javascript:calendar.handle.setDateSync('MMA016_02_form_02', 'YYYYMM_CAL', 'YYYYMM');"/>
                                                        </td>
                                                    </tr>
			                                        <tr>
			                                            <th scope="row"><spring:message code="FILE, SELET"/><%--첨부파일--%></th>
			                                            <td>
			                                                <input type="file" name="uploadExcelFile" id="uploadExcelFile" style="width:570px;"/>
			                                            </td>
			                                        </tr>
			                                    </tbody>
			                                </table>
		                                </form>
		                            </td>
		                            <td style="text-align:center;vertical-align:middle;">
		                                <a href="javascript:MMA016_02.control.executeExcelUpload();" class="btnFileImport"><spring:message code='UPLOD'/></a>
		                            </td>
		                        </tr>
		                    </table>
		                </div>
		            </div>
		        </div>
		        <div data-options="region:'center',border:false" style="padding-top:6px;">
		            <div class="easyui-layout" data-options="fit:true">
		                <div data-options="region:'north',border:false">
		                    <div class="easyui-layout" data-options="fit:true">
		                       <div data-options="region:'north',border:false" style="overflow:hidden;">
		                           <p class="h2"><spring:message code="FILE, TXT_FORMAT"/><%-- 파일형식 --%></p>
		                       </div>
		                       <div data-options="region:'center',border:false">
		                           <form id="MMA016_02_form_01" name="MMA016_02_form_01" method="post">
			                           <table class="gridT" style="margin-bottom:0;">
			                               <caption><spring:message code="FILE, TXT_FORMAT"/><%--파일선택--%></caption>
			                               <colgroup>
			                                   <col width="170px" />
			                                   <col width=";"/>
			                                   <col width="170px" />
			                                   <col width="150px;"/>
			                               </colgroup>
			                               <tbody>
			                                   <tr>
			                                       <th scope="row"><spring:message code="TXT_REG_DATA"/><%--등록할 데이터--%></th>
			                                       <td>
			                                           <input type="text" id="IF_NAME" name="IF_NAME" value="${IF_NAME}" readonly class="input_readOnly" style="width:99%;"/>
			                                       </td>
			                                       <th scope="row"><spring:message code="TXT_SHOW_HEADERS"/><%--해더 표시방법--%></th>
		                                           <td>
		                                               <input id="VIEW_TYPE" name="VIEW_TYPE" value="value" style="width:140px;"/>
		                                           </td>
			                                   </tr>
			                                   <tr>
			                                       <th scope="row"><spring:message code="WRKSH"/><%--워크시트 --%></th>
			                                       <td>
			                                           <input id="SHEET_NAME" name="SHEET_NAME" style="width:250px;"/>
			                                       </td>
			                                       <th scope="row"><spring:message code="TXT_READ_START_NUM"/><%--데이터 시작 행번호 --%></th>
			                                       <td>
			                                           <input type="text" id="SKIP_COL_NO" name="SKIP_COL_NO" value="2" readonly class="readOnly" style="width:98%;"/>
			                                       </td>
			                                   </tr>
			                               </tbody>
			                           </table>
			                       </form>
		                       </div>
		                   </div>
		                </div>
		                <div data-options="region:'center',border:false" style="padding-top:6px;">
		                   <div class="easyui-layout" data-options="fit:true">
		                       <div id="MMA016_02_div_01" data-options="region:'north',border:false" style="overflow:hidden;">
		                           <p class="h2"><spring:message code="FILE, CONTS"/><%-- 파일내용 --%></p>
		                       </div>
		                       <div data-options="region:'center',border:false">
		                           <table id="MMA016_02_grid_01"> </table>
		                       </div>
		                   </div>
		                </div>
		            </div>
		        </div>
		    </div>
		</div>
    </div>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-A016_02.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body> 
</html>