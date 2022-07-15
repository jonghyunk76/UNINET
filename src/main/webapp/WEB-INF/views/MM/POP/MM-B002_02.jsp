<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : MM-B002_02.jsp
  * @Description : 세관/과 팝업 조회
  * @Modification Information
  *
  * author YNI-Maker(이메일)
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMB002_02_dailog_01"으로 하셔야 합니다.
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
	            <form id="MMB002_02_form_01" name="MMB002_02_form_01" method="post">
	                <input type="hidden" id="TARGET_PID" name="TARGET_PID" value="${TARGET_PID}"/>
	                <input type="hidden" id="COMPANY_CD" name="COMPANY_CD" value="${COMPANY_CD}"/>
	                <input type="hidden" id="CATEGORY_CD" name="CATEGORY_CD" value="CT"/>
	                <table>
	                    <caption>데이타 검색조건 설정</caption>
	                    <colgroup>
                            <col width="120px" />
                            <col width="150px" />	                    
	                        <col width="120px" />
	                        <col width="400px" />
	                        <col width=";"/>
	                    </colgroup>
	                    <tbody>
	                        <tr>
								<th scope="row"><spring:message code="CC_수입/수출"/><%--조회조건--%></th>	                        
                                <td>
                                    <input type="text" id="IMP_EXP_SE_CD" name="IMP_EXP_SE_CD" style="width:80px"/>
                                </td>	                        
                                <th scope="row"><spring:message code="SRCH,CNDIT"/><%--조회조건--%></th>
                                <td>                                	
                                    <input type="text" id="schKeyField" name="schKeyField" style="width:100px"/>
                                    <input type="text" id="schKeyWord" name="schKeyWord" class="easyui-validatebox" search="MMB002_02.control.selectKcsCodeList" style="width:150px;"/>
                                    <input type="text" id="schKeyLike" name="schKeyLike" style="width:80px"/>
                                </td>
                                <td align="txt_R">
                                    <a href="javascript:MMB002_02.control.selectKcsCodeList();" class="easyui-linkbutton pop_sch" id="MMB002_02_searchBtn"><spring:message code="SRCH"/><%--조회--%></a>
                                </td>	                            
	                        </tr>
	                    </tbody>
	                </table>
	            </form>
	        </div>
        </div>
        
	    <div data-options="region:'center',border:false" style="padding-top:12px;">
	        <div class="easyui-layout" data-options="fit:true">
	            <div data-options="region:'west',border:false,split:true" class="sec_tit" style="width:400px;">
	                <div class="easyui-layout" data-options="fit:true">
		                <div data-options="region:'north',border:false" class="h2_etc">
		                   <p class="h2"><spring:message code="CC_세관"/></p>
		                </div>
		                <div data-options="region:'center',border:false">
		                   <table id="MMB002_02_grid_01"></table>
		                </div>
					</div>
				</div>

				<div data-options="region:'center',border:false">
					<div class="easyui-layout" data-options="fit:true">
						<div data-options="region:'north',border:false,split:true" class="sec_tit" style="height: 250px;">
							<div class="easyui-layout" data-options="fit:true">
								<div data-options="region:'north',border:false" class="h2_etc">
									<form id="MMB002_02_form_02" method="POST">
										<input type="hidden" id="gridData" name="gridData"/>
									</form>								
									<p class="h2">
										<spring:message code="CC_과" />
									</p>
		                    		<p class="h2_btn">
                                   		<a href="javascript:MMB002_02.control.deleteCsmhKwaMapping();" class="btnMinusInfo"><spring:message code="CC_과 삭제"/></a>
                                   		<a href="javascript:MMB002_02.control.selectRowValue();" class="btnPopTick"><spring:message code="SELET"/></a>
                                   	</p>									
								</div>
								<div data-options="region:'center',border:false">
									<table id="MMB002_02_grid_02"></table>
								</div>
							</div>
						</div>
						<div data-options="region:'center',border:false,split:true" class="sec_tit">	
							<div class="easyui-layout" data-options="fit:true">
								<div data-options="region:'center',border:false">
									<form id="MMB002_02_form_03" method="POST">
										<input type="hidden" id="IMP_EXP_SE_CD" name="IMP_EXP_SE_CD"/>
										<input type="hidden" id="CSMH_CD" name="CSMH_CD"/>
										<input type="hidden" id="gridData" name="gridData"/>
									</form>
									<div class="easyui-layout" data-options="fit:true">							
										<div data-options="region:'north',border:false" class="h2_etc">
											<p class="h2">
												<spring:message code="CC_과 전체" />
											</p>
				                    		<p class="h2_btn">
                                        		<a href="javascript:MMB002_02.control.insertCsmhKwaMapping();" class="btnMoreInfo"><spring:message code="CC_과 추가"/></a>
                                        	</p>											
										</div>
										<div data-options="region:'center',border:false">
											<table id="MMB002_02_grid_03"></table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
	</div>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-B002_02.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>