<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset=utf-8>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="apple-mobile-web-app-title" content="">
	<meta name="description" content="">
	<meta name="keywords" content="">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=yes">
	<title></title>
	<!--[if lt IE 9]>
		<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->
	<link rel="stylesheet" type="text/css" href="/css/main/import.css">
	<!-- css 충돌 -->
	<link rel="stylesheet" type="text/css" href="/css/yni-ui.css" /> 
	<!--[if IE 7]>
		<link rel="stylesheet" type="text/css" href="../css/ie7.css">
	<![endif]-->
    <script type="text/javascript" src="/js/frame/plugins/jquery-1.6.min.js"></script>
	<script type="text/javascript" src="/js/frame/message/message_ENG.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.properties.js"></script>
    <script type="text/javascript" src="/js/frame/common/prototype.js"></script>
    <script type="text/javascript" src="/js/frame/common/utils.js"></script>
    <script type="text/javascript" src="/js/frame/plugins/yniui/jquery.yni-ui.min.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.validator.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.form.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.grid.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.dialog.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.combo.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.calendar.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.ctrl.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.themes.js"></script>
    <script type="text/javascript" src="/js/frame/common/yni.window.js"></script>
</head>
<body>
<!-- /mm/leftMenu -->
<%-- <c:import url="/mm/leftMenu?selectedLanguage=ko"></c:import> --%>
<% /* session progress */ %>
<c:set var="userName" value=""></c:set>
<c:set var="companyCode" value=""></c:set>



<c:if test="${_MEMBER.SESSION_USER_NAME ne ''}">
	<c:set var="userName" value="${_MEMBER.SESSION_USER_ID}"></c:set>
	<c:set var="companyCode" value="${sessionScope._MEMBER.SESSION_COMPANY_CD}"></c:set>
</c:if>
<div id="log"></div>
<div id="menuOuter">
	<!-- 메인메뉴 -->
	<div id="menuWrap">
	<p class="lnb_tit">
      	<span>Main Menu</span>
		<em>
           <a id="expandMenuBtn" href="#"><img src="../images/icon/btn_down.png" alt="아래로" /></a>
           <a id="collapseMenuBtn" href="#"><img src="../images/icon/btn_up.png" alt="위로" /></a>
		</em>
    </p>
     <div id="leftmenu1">
         
        <ul class="menu_area" >
         
        	<li><a href="#a1" id="leftSubMenu_1">Standalone Menu</a>
			<li>
            	<a href="#a2">Sampling</a>						            	
                <ul class="depth2">
	            	<li><a href="javascript:go_page('/mm/smp/smp1001');">SMP1001 다양한 기능예제</a></li>
					<li><a href="javascript:go_page('/mm/smp/smp1002/smp1002_01');">SMP1002 표준코드</a></li>
					<li><a href="javascript:go_page('/mm/smp/smp1003');">SMP1003</a></li>
					<li><a href="javascript:go_page('/mm/smp/smp1004');">SMP1004</a></li>
					<li><a href="javascript:dialogOpen('SM1005_01_dailog_01', '/mm/smp/smp1005');">SMP1005 공통 팝업</a></li>
					<li><a href="javascript:go_page('/mm/smp/smp1006');">SMP1006</a></li>
					<li><a href="javascript:go_page('/mm/smp/smp1007');">SMP1007</a></li>
					<li><a href="javascript:go_page('/mm/smp/smp1008');">SMP1008</a></li>
					<li><a href="javascript:go_page('/mm/smp/smp1009_01');">SMP1009 리스트</a></li>
					<li><a href="javascript:dialogNewOpen('SM1009_01_dailog_01', '/mm/smp/smp1009_01');">[By popup]</a></li>
				</ul>
			</li>
            <li>
            	<a id="leftSubMenu_2" href="#a2">[공통팝업-MMA000]</a>
				<ul class="depth2">
					<li><a href="javascript:go_page('/mm/pop/mmA002_01');">MM-A002 사업부 조회</a></li>
					<li><a href="javascript:dialogOpen('MMA002_dialog_01', '/mm/pop/mmA002_01');" title="MM-A002"> 사업부 조회-Dialog</a></li>
					<li><a href="javascript:dialogOpen('MMA003_dialog_01', '/mm/pop/mmA003_01');" title="MM-A003"> 레포트 발급(내수)-Dialog</a></li>
					<li><a href="javascript:dialogOpen('MMA004_dialog_01', '/mm/pop/mmA004_01');" title="MM-A004"> 레포트 발급(수출-자율)-Dialog</a> </li>
					<li><a href="javascript:dialogOpen('MMA005_dialog_01', '/mm/pop/mmA005_01');" title="MM-A005"> 품목 조회-Dialog</a></li>
					<li><a href="javascript:dialogOpen('MMA006_dialog_01', '/mm/pop/mmA006_01');" title="MM-A006"> 협력사 조회-Dialog</a></li>
					<li><a href="javascript:dialogOpen('MMA007_01_dailog_01', '/mm/pop/mmA007_01');" title="MM-A007"> 판매업체 조회-Dialog</a> </li>
					<li><a href="javascript:dialogOpen('MMA008_01_dailog_01', '/mm/pop/mmA008_01');" title="MM-A008"> 국가조회-Dialog</a></li>
					<li><a href="javascript:dialogOpen('MMA009_01_dailog_01', '/mm/pop/mmA009_01');" title="MM-A009"> 사용자 조회-Dialog</a> </li>
					<li><a href="javascript:dialogOpen('MMA010_dialog_01', '/mm/pop/mmA010_01');" title="MM-A010"> 서명권자 조회-Dialog</a></li>
					<li><a href="javascript:dialogOpen('MMA011_dialog_01', '/mm/pop/mmA011_01');" title="MM-A011"> HS 코드 조회-Dialog</a> </li>
					<li><a href="javascript:dialogOpen('MMA012_dialog_01', '/mm/pop/mmA012_01');" title="MM-A012"> NALADISA 코드 조회-Dialog</a> </li>
					<li><a href="javascript:dialogOpen('MMA013_dialog_01', '/mm/pop/mmA013_01');" title="MM-A013"> FTA 협정조회-Dialog</a> </li>
					<li><a href="javascript:dialogOpen('MMA014_dialog_01', '/mm/pop/mmA014_01');" title="MM-A014"> 원산지 판정결과 조회-Dialog</a> </li>
					<li><a href="javascript:dialogOpen('MMA015_dialog_01', '/mm/pop/mmA015_01');" title="MM-A015"> 표준코드 조회-Dialog</a></li>
					<li><a href="javascript:dialogOpen('MMA016_dialog_01', '/mm/pop/mmA016_01');" title="MM-A016"> 엑셀 업로드-Dialog</a></li>
					<li><a href="javascript:dialogOpen('MMA017_dialog_01', '/mm/pop/mmA017_01');" title="MM-A017 Key_In_1-Dialog"> 한국 증명서 등록</a></li>
					<li><a href="javascript:dialogOpen('MMA018_dialog_01', '/mm/pop/mmA018_01');" title="MM-A017 Key_In_2-Dialog"> 한국 증명서 등록 </a></li>
					<li><a href="javascript:dialogOpen('MMA019_dialog_01', '/mm/pop/mmA019_01');" title="MM-A019 Key_In_1-Dialog"> 멕시코 증명서 등록 </a></li>
					<li><a href="javascript:dialogOpen('MMA020_dialog_01', '/mm/pop/mmA020_01');" title="MM-A019 Key_In_2-Dialog"> 멕시코 증명서 등록</a></li>
					<li><a href="javascript:dialogOpen('MMA021_dialog_01', '/mm/pop/mmA021_01');" title="MM-A021 Dialog"> 증명서 변경요청</a></li>
					<li><a href="javascript:dialogOpen('MMA022_dialog_01', '/mm/pop/mmA022_01');" title="MM-A022 Excel_Load-Dialog"> 한국 증명서 등록 </a></li>
					<li><a href="javascript:dialogOpen('MMA023_dialog_01', '/mm/pop/mmA023_01');" title="MM-A023 Excel_Load-Dialog"> 멕시코 증명서 등록</a></li>
                </ul>
			</li>
			<li>
            	<a id="leftSubMenu_2" href="#a2">[FM/FR]</a>
				<ul class="depth2">
					<li><a href="javascript:go_page('/fm/fr/fr1001_01');" title="FR1001_01"> FTA 협정 리스트 조회</a></li>
					<li><a href="javascript:go_page('/fm/fr/fr1002_01');" title="FR1002_01"> FTA 협정별 국가 등록 수 조회</a></li>
					<li><a href="javascript:go_page('/fm/fr/fr1003_01');" title="FR1003_01"> PSR 조회</a></li>
					<li><a href="javascript:go_page('/fm/fr/fr1004_01');" title="FR1004_01"> FTA 옵션 조회 및 관리</a></li>
					<li><a href="javascript:go_page('/fm/fr/fr1005_01');" title="FR1005_01"> HS코드 조회</a></li>
					<li><a href="javascript:go_page('/fm/fr/fr1006_01');" title="FR1006_01"> 협정별 세율 리스트 조회</a></li> 
					
                </ul>
			</li>
			<li>
            	<a id="leftSubMenu_2" href="#a2">[FM/DI]</a>
				<ul class="depth2">
					<li><a href="javascript:go_page('/fm/di/di3001_01');"title="DI3001_01"> 구매원장 리스트 조회</a></li>
					<li><a href="javascript:go_page('/fm/di/di3003_01');"title="DI3003_01"> 수출건별 Invoice 리스트 조회</a></li>
					<li><a href="javascript:go_page('/fm/di/di3004_01');"title="DI3004_01"> 수불부 리스트 조회</a></li>
					<li><a href="javascript:go_page('/fm/di/di3005_01');">DI3005_01 외부 원산지 증명서 조회</a></li>
                </ul>
			</li>
			<li>
            	<a id="leftSubMenu_2" href="#a2">[FM/SI]</a>
				<ul class="depth2">
					<li><a href="javascript:go_page('/fm/si/si2001_01');"title="SI2001_01"> 제품 원재료관리</a></li>
					<li><a href="javascript:go_page('/fm/si/si2002_01');"title="SI2002_01"> 품목명세서관리</a> </li>
					<li><a href="javascript:go_page('/fm/si/si2002_01');"title="SI2013_01"> 자재명세 리스트 조회(생산BOM)</a> </li>
					<li><a href="javascript:go_page('/fm/si/si2002_01');"title="SI2014_01"> 자재명세 리스트 조회(표준BOM)</a> </li>
					<li><a href="javascript:go_page('/fm/si/si2003_01');"title="SI2003_01"> 고객사 품목관리</a></li>
					<li><a href="javascript:go_page('/fm/si/si2004_01');"title="SI2004_01"> 표준원가조회</a> </li>
					<li><a href="javascript:go_page('/fm/si/si2005_01');"title="SI2005_01"> 생산공정 관리</a></li>
					<li><a href="javascript:go_page('/fm/si/si2006_01');"title="SI2006_01"> 협력사 및 구매품목</a> </li>
					<li><a href="javascript:go_page('/fm/si/si2007_01');"title="SI2007_01"> 판매처및모델관리</a> </li>
					<li><a href="javascript:go_page('/fm/si/si2008_01');"title="SI2008_01"> 제조경비 리스트 조회</a> </li>
					<li><a href="javascript:go_page('/fm/si/si2009_01');"title="SI2009_01"> 내륙운송비 리스트 조회</a> </li>
					<li><a href="javascript:go_page('/fm/si/si2010_01');"title="SI2010_01"> 수출물류경비 리스트 조회</a> </li>
					<li><a id="clickMe">click me</a></li> 
                </ul>
			</li>
			<li>
            	<a id="leftSubMenu_2" href="#a2">[FM/OR]</a>
				<ul class="depth2">
					<li><a href="javascript:go_page('/fm/or/or5001_01');"title="OR5001_01">수출매출리스트조회</a></li>
					<li><a href="javascript:go_page('/fm/or/or5002_01');"title="OR5002_01">내수및로컬수출 매출 리스트 조회</a></li>
					<li><a href="javascript:go_page('/fm/or/or5004_01');"title="OR5004_01">수정발급</a></li>
                </ul>
			</li>
			<li>
            	<a id="leftSubMenu_2" href="#a2">[FM/OD]</a>
				<ul class="depth2">
					<li><a href="javascript:go_page('/fm/od/od4004_01');"title="OD4004_01">수출판매가치조정환산율</a></li>
					<li><a href="javascript:dialogOpen('OD4005_03_dailog_01', '/fm/od/od4005_03');" title="OD4005_03">거래자정보Panel</a></li>
					<li><a href="javascript:dialogOpen('OD4005_04_dailog_01', '/fm/od/od4005_04');" title="OD4005_04">원산지 판정결과 조회Panel</a></li>
					<li><a href="javascript:dialogOpen('OD4005_05_dailog_01', '/fm/od/od4005_05');" title="OD4005_05">생산정보 조회Panel</a></li>
					<li><a href="javascript:dialogOpen('OD4005_06_dailog_01', '/fm/od/od4005_06');" title="OD4005_06">품목단가 정보 조회Panel</a></li>
                </ul>
			</li>
			<li>
            	<a id="leftSubMenu_2" href="#a2">[FM/SM]</a>
				<ul class="depth2">
					<li> <a href="javascript:go_page('/fm/sm/sm7004_01');"title="SM7004_01"> 스케쥴 리스트 조회</a> </li>
					<li> <a href="javascript:go_page('/fm/sm/sm7005_01');"title="SM7005_01"> 인터페이스 항목 리스트 조회</a></li>
					<li> <a href="javascript:go_page('/fm/sm/sm7006_01');"title="SM7006_01"> 인터페이스 수동 실행 및 이력 조회</a></li>
					원산지 판정결과 조회
                </ul>
			</li>
		</ul>
        </div><!-- leftmenu1 -->
     </div><!-- menuWrap -->
</div><!-- menuOuter -->
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false" style="height:54px;overflow:hidden;">
	    <div id="wrap" class="sch_box">
			<div id="header">
				<p class="lang">
					<a href="#"><img src="../images/icon/top_ko.gif" alt="한국어" /></a>
					<a href="#"><img src="../images/icon/top_china.gif" alt="중국어" /></a>
					<a href="#"><img src="../images/icon/top_usa.gif" alt="영어" /></a>
				</p>
				<div class="top_area">
					<p>
						<label for="company">회사명</label><!-- 2016.04.05 -->
						<select name="" id="company"><!-- 2016.04.05 -->
							<option selected="selected">인밸류비즈</option>
							<option>회사명1</option>
							<option>회사명2</option>
							<option>회사명3</option>
						</select>
					</p>
					<ul>
						<li><a href="#">${companyCode} 그룹웨어</a></li>
						<li><a href="#">[FTA TFT] ${userName} 님</a></li>
						<li class="last"><a href="#">로그아웃</a></li>
					</ul>
				</div>
			</div><!-- //header -->
		</div>
	</div>
	<!-- body -->
	<div id="contents_structure" data-options="region:'center',border:false">
	    <div class="easyui-layout" data-options="fit:true">
	       <div data-options="region:'west',border:false" style="width:30px;overflow:hidden;">
                <div id="container" class="con_wrap">
                    <div class="aside">
                        <div class="aside_bar">
                            <p class="btn_open"><img height="36px" src="../images/icon/aside_close.gif" alt="<spring:message code="TXT_BUTTON"/>"></p>
                            <ul class="left_tabs">
	                            <li id="tab1" class="on"><a href="#" class="leftmenu" onclick="tabChange2('leftmenu',1,'tab',2); return false;">메<br />인<br />메<br />뉴</a></li>
	                            <li id="tab2"><a href="#" class="bmk" onclick="tabChange2('leftmenu',2,'tab',2); return false;">즐<br />겨<br />찾<br />기</a></li>
	                        </ul>
	                    </div>
	                </div>
	            </div>
	        </div>
			<div data-options="region:'center',border:false"> <!-- 컨텐츠 영역-->
			    <div id="contents_layout" class="easyui-layout" data-options="fit:true">
                    <div data-options="region:'west',border:false,minWidth:'0'" style="background-color:#fff;width:243px;overflow:hidden;">
		                <div class="aside">
						    <div class="lnb">
				                <p class="sch_area">
				                    <input type="text" name="" value="" id="sch_m" style="width:85%;height:18px;border:1px solid #dfdfdf;"/><label for="sch_m">
				                    <a href="#"><img src="../images/icon/btn_sch2.gif" alt="검색" /></a></label>
				                </p>
				                <!-- 즐겨찾기 -->
				                <div id="leftmenu2" style="display:none;">
				                    <p class="lnb_tit2">
				                        <span>즐겨찾기 추가</span>
				                        <em>
				                            <a href="#"><img src="../images/icon/btn_down.png" alt="아래로" /></a>
				                            <a href="#"><img src="../images/icon/btn_up.png" alt="위로" /></a>
				                        </em>
				                    </p>
				                    <ul class="bmk_area">
				                        <li class="on"><a href="#">메뉴 stc_Lvl01</a></li><!-- 선택된 경우 class="on"  추가 -->
				                        <li><a href="#">메뉴 stc_Lvl01</a></li>
				                        <li><a href="#">메뉴 stc_Lvl01</a></li>
				                    </ul>
				                </div>
				            </div>
			            </div>
			        </div><!-- data-options -->
					<div data-options="region:'center',border:false" style="padding:10px 5px 0 5px;">        
			            <div class="easyui-panel" id="MAIN_MENU" data-options="border:false,fit:true"></div>
			        </div>
			    </div>
			</div>
		</div>
	</div>
	<div id="footer" data-options="region:'south',border:false" style="height:30px;padding:0;">
		<p><a href="#"><img src="../images/icon/foot_iome.gif" alt="home" /></a></p>
		<ul>
			<li><a href="#"><img src="../images/icon/foot_arr.gif" alt="이전" /></a></li>
			<li><a href="#"><img src="../images/icon/foot_arr2.gif" alt="다음" /></a></li>
			<li><a href="#"><img src="../images/icon/foot_btn1.gif" alt="위로" /></a></li>
			<li><a href="#"><img src="../images/icon/foot_btn2.gif" alt="새창" /></a></li>
			<li><a href="#"><img src="../images/icon/foot_btn3.gif" alt="세로면분할" /></a></li>
			<li><a href="#"><img src="../images/icon/foot_btn4.gif" alt="가로면분할" /></a></li>
			<li><a href="#"><img src="../images/icon/foot_btn5.gif" alt="" /></a></li>
			<li><a href="#"><img src="../images/icon/foot_btn6.gif" alt="" /></a></li>
		</ul>
		<div data-options="region:'east',border:false" style="background: repeat-y; width: 5px;">
	        <div id="right_top"></div>
	        <div id="main_win" class="easyui-window" title="Warning" data-options="modal:true,closed:true,resizable:false,minimizable:false,maximizable:false,collapsible:false" style="width:550px; height:300px; padding:0;">
	            <div class="easyui-layout" data-options="fit:true">
	                <div data-options="region:'center', border:false" style="padding:10px;background:#5583a5;color:#fff;">
	                    <div id="main_win_message"></div>
	                </div>
	                <div data-options="region:'south',border:false" style="text-align: center; padding: 5px 0 0;">
	                    <a class="easyui-linkbutton" href="javascript:void(0)" onclick="javascript:$('#main_win').window('close');" style="width: 33px">Ok</a>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
	<!-- //footer -->
</div>
<script type="text/javascript">

	function go_page(url) {
	    $('#MAIN_MENU').panel({href:url});
	}
	
	function dialogOpen(name, url) {
	    var dg_1 = dialog.getObject(name);
	    
	    dialog.init.setTitle("Sample Dialog");
	    dialog.init.setWidth(600);
	    dialog.init.setHeight(400);
	    dialog.init.setURL(url);
	
	    // dialog창 열기
	    dialog.open(dg_1);
	};
	
	function dialogNewOpen(name, url) {
	    var dg_1 = dialog.getObject(name);
	    
	    dialog.init.setTitle("Message 검색");
	    dialog.init.setWidth(900);
	    dialog.init.setHeight(500);
	    dialog.init.setURL(url);
	    dialog.init.setResizable(true);
	    dialog.init.setModal(false);
	    
	    // dialog창 열기
	    dialog.open(dg_1);
	};
	
	var SESSION = {
	    COMPANY_CD 			:"${_MEMBER.SESSION_COMPANY_CD}"
	    ,USER_ID            :"${_MEMBER.SESSION_USER_ID}"
	    ,DIVISION_CD        :"${_MEMBER.SESSION_DIVISION_CD}"
	    ,COMPANY_NAME       :"${_MEMBER.SESSION_COMPANY_NAME}"
	    ,USER_NAME          :"${_MEMBER.SESSION_USER_NAME}"
	    ,USER_AUTH          :"${_MEMBER.SESSION_USER_AUTH}"
	    ,DEFAULT_LANGUAGE   :"${_MEMBER.SESSION_DEFAULT_LANGUAGE}"
	    ,WORK_YYYY_MM 	    :"${_MEMBER.SESSION_WORK_YYYY_MM}" 	
	};
	
	
</script>
<script type="text/javascript" src="/js/frame/common/yni.window.js"></script>
<script type="text/javascript" src="/js/frame/plugins/design.js"></script>
</body> 
</html>
