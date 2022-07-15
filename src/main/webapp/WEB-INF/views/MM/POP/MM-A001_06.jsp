<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @Class Name : MM-A001_06.jsp
  * @Description : 원가계산 메인화면
  * @Modification Information
  *
  * author YNI-Maker(이메일)
  * 
  * <notice>
  * 본 화면을 다리얼로그로 사용할 경우 명칭을 "MMA001_06_dailog_01"으로 하셔야 합니다.
  */
%>
<!DOCTYPE html>
<html>
<head>      
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style> 
		.box_00{margin:2px;float:left;width:calc(50% - 4px);height:600px;text-align:center; 
		background-size:cover;
		background-image: linear-gradient(to right, #535353 0%, #FFEC8C 100%),url('/images/main/main_customs.jpg');} 
		.box_00 h1{line-height:600px;color:#fff;} 
		.blend{background-blend-mode: multiply;} 
	</style>
</head>
<body>
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center',border:false" style="background-blend-mode: overlay;background-image: linear-gradient(to bottom, rgb(28 79 120) 100%, rgb(225 235 237) 0%), url(/images/main/main_customs.jpg);background-size:cover;margin:2px;float:left;width:calc(50% - 4px);height:600px;text-align:center; "><!-- style="padding-left:20px;background: url(/images/main/main_customs.jpg) no-repeat 0 0;background-repeat: round;background-size: cover;"> -->
            <table style="height:100%;width:100%;">
                <tr>
                    <td style="text-align:center;">
                         <font style="
			                    vertical-align: middle;
			                    font-size: 100pt;
			                    font-family: fantasy;
			                    text-shadow: 5px 5px #aba8a8;
			                ">원가계산</font><br>
			                <font style="
                                vertical-align: middle;
                                font-size: 30pt;
                                font-family: auto;
                            "></font>
                    </td>
                </tr>
            </table>
            <!-- h1 style="font-size:40pt;">메인화면</h1>
            <h2>
	            <ol>
			        <li style="padding-top:5px;">1. 재수출 이행기간 도래 안내</li>
			        <li style="padding-top:5px;">2. 확정가격신고 이행기간 안내</li>
			        <li style="padding-top:5px;">3. 수출 미선적 내용 안내</li>
			        <li style="padding-top:5px;">4. FTA협정관세 적용 신청기한 만료 안내</li>
			        <li style="padding-top:5px;">5. 첨부서류 사후제출이행 안내</li>
			        <li style="padding-top:5px;">6. 수입신고 수리물품 반출 안내</li>
			        <li style="padding-top:5px;">7. 신고지연 가산세 부과대상 사전 안내</li>
			        <li style="padding-top:5px;">8. 분석회보서 통지</li>
			        <li style="padding-top:5px;">9. 월별납부 고지서 발행일 안내 </li>
			        <li style="padding-top:5px;">10. 납부기한 도래 안내</li>
			    </ol>
		    </h2-->
        </div>
    </div>
    
    <script type="text/javascript" src="/js/views/MM/POP/MM-A001_06.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>

</body>
</html>