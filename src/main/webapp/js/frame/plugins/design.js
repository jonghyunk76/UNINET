$(document).ready(function(){
    
    //MENU SIZE FIX////////////////////////MENUSIZE FIX////////////////////////MENUSIZE FIX///////////////////
    var adjustValue = 10;
    var menuHeightValue = jQuery("#contents_layout").height()-jQuery("#footer").height()-jQuery(".lnb_tit").height()-adjustValue;
    jQuery("#leftmenu1").css({"height":menuHeightValue});
    jQuery("#leftmenu2").css({"height":menuHeightValue});
    jQuery("#leftmenu3").css({"height":menuHeightValue});
    jQuery("#leftmenu4").css({"height":menuHeightValue});
    jQuery("#leftmenu5").css({"height":menuHeightValue});
    jQuery("#leftmenu7").css({"height":menuHeightValue});
    jQuery("#leftmenu8").css({"height":menuHeightValue});
    
    // EasyUI adjustment
    jQuery(".layout-panel-east").css({"left":0});
    //MENU SIZE FIX////////////////////////MENUSIZE FIX////////////////////////MENUSIZE FIX///////////////////

    
    //LEFTMENU ACTION////////////////////LEFTMENU ACTION////////////////////LEFTMENU ACTION///////////////////

    
    // CONTENTS ADJUST
    var $wrap = $('#wrap'); // Main > top 영역
    var $footer = $('#footer'); // Main > bottom 영역
    var $leftMenu = $('#menuOuter'); // 메뉴 영역
    var $container = $('#container'); // 좌측 대메뉴 영역
    var $aside = $('.aside'); // 좌측 대메뉴 영역 하위1
    var $aside_bar = $('.aside .aside_bar'); // 좌측 대메뉴 영역 하위2
    var $contents = $('.contents');
    var $lnb = $('.aside .lnb'); // 검색+즐겨찾기 메뉴
    var $lnbBtn = $('.aside_bar .btn_open img'); // 메뉴 영역 숨기기/보이기
    var contentsW = $('.contents').width();
    var winW = $(window).width();
    var bodyW = $('body').width();
    var lnbStats = true;
    
    if(TOP_SYS_ID == "RS") lnbStats = false;
        
    // Main > top 넓이 조절
    $(window).load(function() {            
        var winW = $(window).width();
        $contents.css({'width':winW - $lnb.width() - $aside_bar.width() +'px'});    
        $wrap.css({'width':$('body').width() +'px'});
    });
    
    //MENU OBJECTS ADJUST
    $(window).resize(function(){
        var rtime;
        var timeout = false;
        var delta = 200;
        var winW = $(window).width();
        var resizeEventCatcher = function(){
            if (new Date() - rtime < delta) {
                setTimeout(resizeEventCatcher, delta);
            } else {
                timeout = false;
                eventExcutor();
            }  
        }
        var eventExcutor = function(){
            var adjustValue = 10;
            var menuHeightValue = jQuery("#contents_layout").height()-jQuery("#footer").height()-jQuery(".lnb_tit").height()-adjustValue;
            
            jQuery("#leftmenu1").css({"height":menuHeightValue});
            jQuery("#leftmenu2").css({"height":menuHeightValue});
        }
        $contents.css({'width':winW - $lnb.width() - $aside_bar.width() +'px'});
        $wrap.css({'width':$('body').width() +'px'});
        jQuery(".layout-panel-east").css({"left":0});
        rtime = new Date();
        if (timeout === false) {
            timeout = true;
            setTimeout(resizeEventCatcher, delta);
        }
    });
    // ASIDE EVENT
    $lnbBtn.click(function (e) {
        e.preventDefault();
        
        if (lnbStats) { // 숨기기
        	var winW = $(window).width();
        	lnbStats = false;
        	
        	$lnbBtn.attr("src","../images/icon/aside_open.gif");
        	$('#MM0001_01_div_01').panel('panel').animate({left:($lnb.width()*-1)},500);
            $("#MM0001_01_div_02").panel('resize',{
            	width: winW - $aside_bar.width() + 2,
            	left:0
            });
        } else { // 보이기
        	var winW = $(window).width();
        	lnbStats = true;
        	
        	$lnbBtn.attr("src","../images/icon/aside_close.gif");
        	$('#MM0001_01_div_01').panel('panel').animate({left:0},100);
            $("#MM0001_01_div_02").panel('resize',{
            	width: winW - $lnb.width() - ($aside_bar.width()-2),
            	left:$lnb.width()
            });
        }
        
        // 그리드내 모든 멀티 필터창을 닫는다.(2020.07.08)
		$(".icon-multi_filter").each(function(){
			$(this).tooltip('hide');
        });
        
        // 시스템 구분이 RS(중계서버)인 경우에 그래프를 재로딩한다.
        try {
            if(TOP_SYS_ID == "RS") {
                MMA001_07.control.selectConnCountOfChart();
                MMA001_07.control.selectConnTrafficOfChart();
            }
        } catch(e) {
            ;
        }
    });
    
    // 대메뉴 추가시 이벤트 추가필요(2020.04.25)
    $('#tab1').click(function (e) {showMainMenuIn();});
    $('#tab2').click(function (e) {showMainMenuIn();});
    $('#tab3').click(function (e) {showMainMenuIn();});
    $('#tab4').click(function (e) {showMainMenuIn();});
    $('#tab5').click(function (e) {showMainMenuIn();});
    $('#tab7').click(function (e) {showMainMenuIn();});
    $('#tab8').click(function (e) {showMainMenuIn();});
    
    $('#MM0001_01_div_01').mouseleave(function(){
    	if(!lnbStats) {
        	var winW = $(window).width();
        	
        	$('#MM0001_01_div_01').panel('panel').animate({left:($lnb.width()*-1)},500);
        }
    });
    
    function showMainMenuIn() {
    	if(!lnbStats) {  
            $('#MM0001_01_div_01').panel('panel').animate({left:0},500);
        }
    };
    
    // SELECT BOX
    var select = $("select#company");    
    
    select.change(function(){
        var select_name = $(this).children("option:selected").text();
        $(this).siblings("label").text(select_name);
    });
    //LEFTMENU ACTION////////////////////LEFTMENU ACTION////////////////////LEFTMENU ACTION///////////////////
});

/**
 * 메뉴 생성
 * @param lang 다국어 국가코드
 * @param userId 로그인 사용자(협력사 포탈인 경우 supplier로 지정됨)
 * @param companyCd 로그인 회사코드
 * @returns
 */
function menuCall(lang, userId, companyCd){
	
    /**
     * 이벤트 처리를 위한 설정
     * 
     * 시스템 ID구분(sysId)
     * - FM : FTA
     * - PC : 원가계산
     * - SM : 환경설정
     * 별도 제품
     * - CC : 통관 > 수출입신고
     * - CU : 통관 > 부과서비스
     * - CR : 통관 > 리포트
     */
    var RegisterMenuManeuveringEvent = function(){
        //hide 0 child menu
        jQuery(".menu_area>li").each(function(){
            var size = jQuery(this).children('ul').children('li').length;
            if(size < 1){
                jQuery(this).hide();
            }
        });
        
        // MENU ON MARK
        jQuery(".menu_area ul.depth li").click(function(){
            jQuery(".menu_area>li").each(function(){
                jQuery(this).removeClass('on');
            });
            
            jQuery(".menu_area>li>ul>li").each(function(){
                if(jQuery(this).hasClass('on')){
                    jQuery(this).removeClass('on');
                };
            });
            
            jQuery(this).parents('li').addClass("on");
            jQuery(this).addClass("on");
        });
        
        // MENU TOGGLE
        jQuery(".menu_area > li > a").click(function(){
            jQuery(this).parents(".menu_area").each(function(){
                jQuery(this).find("li").removeClass("on");
            });
            
            jQuery(this).parent().addClass("on");
            jQuery(this).siblings().slideToggle();
        });
        
        //ALL MENU EXPAND
        jQuery("#expandMenuBtn").click(function(){ // 하위 메뉴 펼치기 버튼 클릭 시 수행
            jQuery(".menu_area .depth").slideDown(0);
        });
        
        //ALL MENU COLLAPSE
        jQuery("#collapseMenuBtn").click(function(){ // 하위 메뉴 접기 버튼 클릭 시 수행
            jQuery(".menu_area .depth").slideUp(0);
        });
    }
    
    var topMenuSize = 8; // 대메뉴 총개수
    
    /**
     * FTA 1레벨 메뉴 데이터 필터
     * 
     * @param item = 메뉴 데이터
     */
    var topFmItemFilter = function(item){
        return (item.hgrnkMenuId == item.menuId && item.sysId == "FM");
    }
    
    /**
     * FTA 1레벨 이하 메뉴 데이터 필터
     * 
     * @param item = 메뉴 데이터
     */
    var nomalFmItemFilter = function(item){
        return (item.hgrnkMenuId != item.menuId && item.sysId == "FM");
    }
    
    /**
     * 통관 1레벨 메뉴 데이터 필터
     * 
     * @param item = 메뉴 데이터
     */
    var topCcItemFilter = function(item){
        return (item.hgrnkMenuId == item.menuId && item.sysId == "CC");
    }
    
    /**
     * 통관 1레벨 이하 메뉴 데이터 필터
     * 
     * @param item = 메뉴 데이터
     */
    var nomalCcItemFilter = function(item){
        return (item.hgrnkMenuId != item.menuId && item.sysId == "CC");
    }
    
    /**
     * 통관>부가서비스 1레벨 메뉴 데이터 필터
     * 
     * @param item = 메뉴 데이터
     */
    var topCuItemFilter = function(item){
        return (item.hgrnkMenuId == item.menuId && item.sysId == "CU");
    }
    
    /**
     * 통관>부가서비스 1레벨 이하 메뉴 데이터 필터
     * 
     * @param item = 메뉴 데이터
     */
    var nomalCuItemFilter = function(item){
        return (item.hgrnkMenuId != item.menuId && item.sysId == "CU");
    }
    
    /**
     * 통관>리포트 1레벨 메뉴 데이터 필터
     * 
     * @param item = 메뉴 데이터
     */
    var topCrItemFilter = function(item){
        return (item.hgrnkMenuId == item.menuId && item.sysId == "CR");
    }
    
    /**
     * 통관>리포트 1레벨 이하 메뉴 데이터 필터
     * 
     * @param item = 메뉴 데이터
     */
    var nomalCrItemFilter = function(item){
        return (item.hgrnkMenuId != item.menuId && item.sysId == "CR");
    }    
    
    /**
     * 원가계산 1레벨 메뉴 데이터 필터
     * 
     * @param item = 메뉴 데이터
     */
    var topClItemFilter = function(item){
        return (item.hgrnkMenuId == item.menuId && item.sysId == "PC");
    }
    
    /**
     * 원가계산 1레벨 이하 메뉴 데이터 필터
     * 
     * @param item = 메뉴 데이터
     */
    var nomalClItemFilter = function(item){
        return (item.hgrnkMenuId != item.menuId && item.sysId == "PC");
    }
    
    /**
     * 중계서버 1레벨 메뉴 데이터 필터
     * 
     * @param item = 메뉴 데이터
     */
    var topRsItemFilter = function(item){
        return (item.hgrnkMenuId == item.menuId && item.sysId == "RS");
    }
    
    /**
     * 중계서버 1레벨 이하 메뉴 데이터 필터
     * 
     * @param item = 메뉴 데이터
     */
    var nomalRsItemFilter = function(item){
        return (item.hgrnkMenuId != item.menuId && item.sysId == "RS");
    }
    
    /**
     * 환경설정 1레벨 메뉴 데이터 필터
     * 
     * @param item = 메뉴 데이터
     */
    var topSmItemFilter = function(item){
        return (item.hgrnkMenuId == item.menuId && item.sysId == "SM");
    }
    
    /**
     * 환경설정 1레벨 이하 메뉴 데이터 필터
     * 
     * @param item = 메뉴 데이터
     */
    var nomalSmItemFilter = function(item){
        return (item.hgrnkMenuId != item.menuId && item.sysId == "SM");
    }
    
    /**
     * FTA 협력사 포탈 1레벨 메뉴 데이터 필터
     * 
     * @param item = 메뉴 데이터
     */
    var topSpItemFilter = function(item){
        return (item.hgrnkMenuId == item.menuId && item.sysId == "SP");
    }
    
    /**
     * FTA 협력사 포탈 1레벨 이하 메뉴 데이터 필터
     * 
     * @param item = 메뉴 데이터
     */
    var nomalSpItemFilter = function(item){
        return (item.hgrnkMenuId != item.menuId && item.sysId == "SP");
    }
    
    /**
     * 1레벨 메뉴 UI 생성
     * 
     * @param topItems = 1레벨 메뉴 데이터
     * @param typeNumber = 메뉴 번호
     */
    var injectTopMenuItem = function(topItems, typeNumber){
        // 메뉴탭에 속하는 메뉴정보가 있는 경우에만 메뉴를 표시하도록 변경(2018.04.19)
        if(typeNumber == "1" && topItems.length > 0) { // FTA
        	form.util.setVisible("tab1", true);
        }
        
        // 메뉴탭에 속하는 메뉴정보가 있는 경우에만 메뉴를 표시하도록 변경(2018.04.19)
        if(typeNumber == "2" && topItems.length > 0) { // 통관
        	form.util.setVisible("tab2", true);
        }
        
        if(typeNumber == "3" && topItems.length > 0) { // 통관 부가서비스
        	form.util.setVisible("tab3", true);
        }
        
        if(typeNumber == "4" && topItems.length > 0) { // 원가계산
        	form.util.setVisible("tab4", true);
        }
        
        if(typeNumber == "5" && topItems.length > 0) { // 환경설정
        	form.util.setVisible("tab5", true);
        }
        
        if(typeNumber == "6" && topItems.length > 0) { // FTA 협력사 포탈
        	form.util.setVisible("tab6", true);
        }
        
        if(typeNumber == "7" && topItems.length > 0) { // 통관 리포트
        	form.util.setVisible("tab7", true);
        }
        
        if(typeNumber == "8" && topItems.length > 0) { // 중계서버 리포트
            form.util.setVisible("tab8", true);
        }
        
        $("#menu_area" + typeNumber).show();
        
        for(var i = 0; i < topItems.length; i++) {
            var liObject = jQuery("<li></li>");
            var aHrefObject = jQuery("<a></a>");
            
            var title = decodeURI(topItems[i].menuName);
            var text = "";
            
            if(title.length > 23) {
                text = title.substring(0, 24) +"...";
            } else {
                text = title;
            }
            
            aHrefObject.prop("title", title);
            aHrefObject.text(text);
            
            liObject.prop("id",topItems[i].menuId);
            liObject.append(aHrefObject);
            
            jQuery("#leftmenu" + typeNumber + " .menu_area").append(liObject);
        }
        
        topItems=null;
    }
    
    /**
     * 1레벨 이하 메뉴 UI 생성
     * 
     * @param nomalItems = 1레벨이하 메뉴 데이터
     */
    var injectNomalMenuItem = function(nomalItems, typeNumber){
        for(var i = 0; i < nomalItems.length; i++){
            var obj = jQuery("#" + nomalItems[i].hgrnkMenuId + " .depth");
            
            if(obj.length===0){
                var ulObject = jQuery("<ul></ul>");
            
                ulObject.addClass('depth');
                jQuery("#" + nomalItems[i].hgrnkMenuId).append(ulObject);
            }
            
            var liObject     = jQuery("<li></li>");
            var aHrefObject = jQuery("<a></a>");
            
            var title = decodeURI(nomalItems[i].menuName);
            var text = "";
            
            if(title.length > 24) {
                text = title.substring(0, 25) +"...";
            } else {
                text = title;
            }
            
            aHrefObject.prop("title", title);
            aHrefObject.text(text);
            aHrefObject.prop("href","javascript:MM0001_01.control.go_page(\"/"+nomalItems[i].DirectUrl+"\",\""+nomalItems[i].menuIfMethod+"\",\""+nomalItems[i].menuId+"\",\""+nomalItems[i].menuName+"\",\""+nomalItems[i].sysId+"\");"); //RegisterMenuActionEvent
            
            liObject.prop("id", nomalItems[i].menuId);
            liObject.append(aHrefObject);
            
            jQuery("#" + nomalItems[i].hgrnkMenuId + " .depth").append(liObject);
        }

        nomalItems=null;
    }
    
    /**
     * 메뉴 데이터 조회 요청
     */
    var request = jQuery.ajax({
    	url: "/mm/leftMenu",
    	method : "post",
    	data : {selectedLanguage:lang, user:userId, company:companyCd},
    	dataType: "json",
    	success:function(data){
    		// 메뉴 로딩 후 메인화면이 보이도록 수정(2021-08-24)
    		for(var i = 0; i < data.result.length; i++) {
    			if(data.result[i].sysId != "SM") {
    				TOP_SYS_ID = data.result[i].sysId; // 첫 메뉴가 어떤 업무인지에 따라 메인화면을 변경함
    				break;
    			}
    		}
    		
    		for(var i = 0; i < data.result.length; i++) {
    			var menu_auth = new Object();
    			
    			menu_auth.MENU_ID = data.result[i].menuId;
    			menu_auth.SEL_AUTH = data.result[i].selAuth;
    			menu_auth.REG_AUTH = data.result[i].regAuth;
    			menu_auth.UPD_AUTH = data.result[i].updAuth;
    			menu_auth.DEL_AUTH = data.result[i].delAuth;
    			menu_auth.EXC_AUTH = data.result[i].excAuth;
    			menu_auth.FLE_AUTH = data.result[i].fleAuth;
    			
    			APPLICATION_MUNE_AUTH.push(menu_auth);
    			
//    			console.log("menu id = " + data.result[i].menuId + " > "+
//    					"menu name = " + data.result[i].menuName + " > "+
//    					"SEL_AUTH = " + data.result[i]["selAuth"]+ ", "+
//    					"REG_AUTH = " + data.result[i]["regAuth"]+ ", "+
//    					"UPD_AUTH = " + data.result[i]["updAuth"]+ ", "+
//    					"DEL_AUTH = " + data.result[i]["delAuth"]+ ", "+
//    					"EXC_AUTH = " + data.result[i]["excAuth"]+ ", "+
//    					"FLE_AUTH = " + data.result[i]["fleAuth"]);
    		}
    		
    		MM0001_01.control.go_home();
        }
    });
    
    /**
     * request 성공 시 호출
     * 
     * @param data = 메뉴 데이터
     */
    request.done(function(data) { 
    	jQuery("#leftmenu1 .menu_area").empty(); // 메인 메뉴 UI 초기화
        jQuery("#leftmenu2 .menu_area").empty(); // 문서함 UI 초기화
        jQuery("#leftmenu3 .menu_area").empty(); // 환경 설정 메뉴 UI 초기화
        
    	var menuItems = data.result; // 조회된 전체 메뉴 데이터
        // 1.FTA
    	var topFmItems = menuItems.filter(topFmItemFilter); // 상위 1레벨의 메뉴 데이터 추출(topItemFilter함수에 지정된 "hgrnkMenuId"인 데이터)
        var nomalFmItems = menuItems.filter(nomalFmItemFilter);  // 상위 1레벨이 아닌 메뉴 데이터 추출(nomalItemFilter함수에 지정된 "hgrnkMenuId"가 아닌 데이터)
        // 2.통관(CC)
        var topCcItems = menuItems.filter(topCcItemFilter); // 상위 1레벨의 메뉴 데이터 추출(topItemFilter함수에 지정된 "hgrnkMenuId"인 데이터)
        var nomalCcItems = menuItems.filter(nomalCcItemFilter);  // 상위 1레벨이 아닌 메뉴 데이터 추출(nomalItemFilter함수에 지정된 "hgrnkMenuId"가 아닌 데이터)
        // 3.통관 부가서비스(CU)
        var topCuItems = menuItems.filter(topCuItemFilter); // 상위 1레벨의 메뉴 데이터 추출(topItemFilter함수에 지정된 "hgrnkMenuId"인 데이터)
        var nomalCuItems = menuItems.filter(nomalCuItemFilter);  // 상위 1레벨이 아닌 메뉴 데이터 추출(nomalItemFilter함수에 지정된 "hgrnkMenuId"가 아닌 데이터)
        // 4.원가계산(CL)
        var topClItems = menuItems.filter(topClItemFilter); // 상위 1레벨의 메뉴 데이터 추출(topItemFilter함수에 지정된 "hgrnkMenuId"인 데이터)
        var nomalClItems = menuItems.filter(nomalClItemFilter);  // 상위 1레벨이 아닌 메뉴 데이터 추출(nomalItemFilter함수에 지정된 "hgrnkMenuId"가 아닌 데이터)
        // 5.환경설정(SM)
        var topSmItems = menuItems.filter(topSmItemFilter); // 상위 1레벨의 메뉴 데이터 추출(topItemFilter함수에 지정된 "hgrnkMenuId"인 데이터)
        var nomalSmItems = menuItems.filter(nomalSmItemFilter);  // 상위 1레벨이 아닌 메뉴 데이터 추출(nomalItemFilter함수에 지정된 "hgrnkMenuId"가 아닌 데이터)
        // 6.FTA 협력사 포탈(SP)
        var topSpItems = menuItems.filter(topSpItemFilter); // 상위 1레벨의 메뉴 데이터 추출(topItemFilter함수에 지정된 "hgrnkMenuId"인 데이터)
        var nomalSpItems = menuItems.filter(nomalSpItemFilter);  // 상위 1레벨이 아닌 메뉴 데이터 추출(nomalItemFilter함수에 지정된 "hgrnkMenuId"가 아닌 데이터)
        // 7.통관리포트(CR)
        var topCrItems = menuItems.filter(topCrItemFilter); // 상위 1레벨의 메뉴 데이터 추출(topItemFilter함수에 지정된 "hgrnkMenuId"인 데이터)
        var nomalCrItems = menuItems.filter(nomalCrItemFilter);  // 상위 1레벨이 아닌 메뉴 데이터 추출(nomalItemFilter함수에 지정된 "hgrnkMenuId"가 아닌 데이터)
        // 8.중계서버(RS)
        var topRsItems = menuItems.filter(topRsItemFilter); // 상위 1레벨의 메뉴 데이터 추출(topItemFilter함수에 지정된 "hgrnkMenuId"인 데이터)
        var nomalRsItems = menuItems.filter(nomalRsItemFilter);  // 상위 1레벨이 아닌 메뉴 데이터 추출(nomalItemFilter함수에 지정된 "hgrnkMenuId"가 아닌 데이터)
        
        data.result = null;
        menuItems = null;
        var fristChk = true;
        
        $("#MM0001_01_span_01").append(resource.getMessage("MAIN, MENU"));
    	
        // FTA
        if(topFmItems.length > 0) {
	        // 1레벨 메뉴 생성
	        injectTopMenuItem(topFmItems, "1");
	        // 1레벨 이하 메뉴 생성
	        injectNomalMenuItem(nomalFmItems, "1");
	        // 메뉴 표시
	        if(fristChk) tabChange2('leftmenu', 1, 'tab', topMenuSize);
	        fristChk = false;
        }
        
        // 통관
        if(topCcItems.length > 0) {
	        // 1레벨 메뉴 생성
	        injectTopMenuItem(topCcItems, "2");
	        // 1레벨 이하 메뉴 생성
	        injectNomalMenuItem(nomalCcItems, "2");
	        // 메뉴 표시
	        if(fristChk) tabChange2('leftmenu', 2, 'tab', topMenuSize);
	        fristChk = false;
        }
        
        // 통관 부가서비스
        if(topCuItems.length > 0) {
	        // 1레벨 메뉴 생성
	        injectTopMenuItem(topCuItems, "3");
	        // 1레벨 이하 메뉴 생성
	        injectNomalMenuItem(nomalCuItems, "3");
	        // 메뉴 표시
	        if(fristChk) tabChange2('leftmenu', 3, 'tab', topMenuSize);
	        fristChk = false;
        }
        
        // 원가계산
        if(topClItems.length > 0) {
	        // 1레벨 메뉴 생성
	        injectTopMenuItem(topClItems, "4");
	        // 1레벨 이하 메뉴 생성
	        injectNomalMenuItem(nomalClItems, "4");
	        // 메뉴 표시
	        if(fristChk) tabChange2('leftmenu', 4, 'tab', topMenuSize);
	        fristChk = false;
        }
        
        // 중계서버 리포트
        if(topRsItems.length > 0) {
            // 1레벨 메뉴 생성
            injectTopMenuItem(topRsItems, "8");
            // 1레벨 이하 메뉴 생성
            injectNomalMenuItem(nomalRsItems, "8");
            // 메뉴 표시
            if(fristChk) tabChange2('leftmenu', 8, 'tab', topMenuSize);
            fristChk = false;
        }
        
        // FTA 협력사 포탈
        if(topSpItems.length > 0) {
            // 1레벨 메뉴 생성
            injectTopMenuItem(topSpItems, "6");
            // 1레벨 이하 메뉴 생성
            injectNomalMenuItem(nomalSpItems, "6");
            // 메뉴 표시
            if(fristChk) tabChange2('leftmenu', 6, 'tab', topMenuSize);
            fristChk = false;
        }
        
        // 통관 리포트
        if(topCrItems.length > 0) {
            // 1레벨 메뉴 생성
            injectTopMenuItem(topCrItems, "7");
            // 1레벨 이하 메뉴 생성
            injectNomalMenuItem(nomalCrItems, "7");
            // 메뉴 표시
            if(fristChk) tabChange2('leftmenu', 7, 'tab', topMenuSize);
            fristChk = false;
        }
        
        // 환경설정
        if(topSmItems.length > 0) {
	        // 1레벨 메뉴 생성
	        injectTopMenuItem(topSmItems, "5");
	        // 1레벨 이하 메뉴 생성
	        injectNomalMenuItem(nomalSmItems, "5");
	        // 메뉴 표시
	        if(fristChk) tabChange2('leftmenu', 5, 'tab', topMenuSize);
	        fristChk = false;
        }
        
        // 메뉴 이벤트 처리
        RegisterMenuManeuveringEvent();
        
        // 메뉴 접기 이벤트 실행
        if(topFmItems.length > 0 && (topFmItems[0].sysId == "FM")) {
        	jQuery("#collapseMenuBtn").click();
        } else {
        	jQuery("#expandMenuBtn").click();
        }
        
    });
    
    /**
     * request 실패 시 호출
     * 
     * @param 메뉴 데이터
     */
    request.fail(function( jqXHR, textStatus ) { // request 실패 시 호출
    	window.location.href = '/mm/authority/logoffProcess';
    });
}

// TAB CHANGE
/**
 * 좌측 대메뉴에 대한 UI변경
 * @param obj 메뉴ID
 * @param num 메뉴번호
 * @param hId 탭ID
 * @param total 전체 탭갯수
 * @returns
 */
function tabChange2(obj, num, hId, total) {
    for (i=1; i<=total; i++){
        var hIds = form.getObject(hId+i);
        
        if (i == num) {
            form.util.setVisible(obj+i, true);
            form.util.addClass(hId+i, "on");
        } else {
            form.util.setVisible(obj+i, false);
            form.util.removeClass(hId+i, "on");
        }
    }
}
