<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : MM-B005_01.jsp
  * @Description : 잠정신고 화면
  * @Modification Information
  *
  * author YNI-Maker(이메일)
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMB005_01_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>      
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'north',border:false" style="height:30px;">
            <div class="btn_R">
                <a href="javascript:MMB005_01.control.updateImportBcncSetupDetail();" id="MMB005_01_btn_01" class="btnSave" style="display:none;"><spring:message code="SAVE"/></a>
                <a href="javascript:MMB005_01.control.deleteImportBcncSetupDetail();" id="MMB005_01_btn_02" class="btnDelete" style="display:none;"><spring:message code="DEL"/></a>
                <a href="javascript:dialog.handle.close(dialog.getObject('MMB005_01_dailog_01'));" class="btnPopClose"><spring:message code="CLOSE"/></a>
            </div>
        </div>
        <div data-options="region:'center',border:false">
            <div class="easyui-layout" data-options="fit:true">
		        <div data-options="region:'north',border:false" style="height:300px;"> <!-- 설정 -->
		            <div class="easyui-layout" data-options="fit:true">
		                <div data-options="region:'center',border:false">
		                    <div class="easyui-layout" data-options="fit:true">
		                        <div data-options="region:'north',border:false" class="h2_etc">
		                            <p class="h2"><spring:message code="CC_잠정신고 대상지정"/></p>
		                        </div>
		                        <div data-options="region:'center',border:false">
		                        </div>
		                    </div>
		                </div>
		                <div data-options="region:'east',border:false" style="width:300px;">
		                    <div class="easyui-layout" data-options="fit:true">
		                        <div data-options="region:'north',border:false" class="h2_etc">
		                            <p class="h2"><spring:message code="CC_잠정신고 결과값"/></p>
		                        </div>
		                        <div data-options="region:'center',border:false">
		                        </div>
		                    </div>
		                </div>
		            </div>
		        </div>
		        <div data-options="region:'center',border:false"> <!-- 업체별 설정값 -->
		            <div class="easyui-layout" data-options="fit:true">
		                <div data-options="region:'north',border:false" class="h2_etc">
		                    <p class="h2"><spring:message code="CC_업체별 대상설정 목록"/></p>
		                </div>
		                <div data-options="region:'center',border:false">
		                </div>
		            </div>
		        </div>
		    </div>
		</div>
    </div>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-B005_01.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>