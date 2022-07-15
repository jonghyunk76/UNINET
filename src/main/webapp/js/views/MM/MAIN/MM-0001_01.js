/******************************************************************************************
 * 작성자 : YNI-Maker 
 * Program Id : MM-0001_01
 * 작업일자 : 2016.05.08
******************************************************************************************/
var objPopup;

function onLoadPage() {
	MM0001_01.ws.start();
	MM0001_01.config.applyFtaNation();
	MM0001_01.init.initComponent();
}

var MM0001_01 = {
	
    init : {
    	initComponent : function() {
    		MM0001_01.control.userOfCompany();
    		
//    		MM0001_01.control.go_home(); // 메뉴로딩 후 화면이 보이도록 수정됨(2021-08-24)
    		
    		if(SESSION.CERTIFY_TYPE == "external") {
    			menuCall(SESSION.DEFAULT_LANGUAGE, "supplier", SESSION.COMPANY_CD);
    		} else {
    			menuCall(SESSION.DEFAULT_LANGUAGE, SESSION.USER_ID, SESSION.COMPANY_CD);
    		}
    		
			jQuery("#sch_m").keyup(function() {
				var searchValue = jQuery(this).val();
				
				jQuery(".menu_area>li").hide();
				jQuery(".menu_area>li>ul>li").hide();
				
				var subMenuObj = jQuery(".menu_area>li>ul>li");
				var menuArray=[];
				var i = 0;
				
				subMenuObj.each(function(){
					var item 	= {};
					item.text 	= jQuery(this).children("li>a").text();
					item.id		= this.id;
					menuArray[i]= item;
					i++;
				});
				
				var filteredMenuItems = menuArray.filter(function(item){
					return encodeURI(item.text).search(encodeURI(searchValue)) != -1;	
			    });
				
				for(var i = 0;i<filteredMenuItems.length;i++){
					jQuery("#"+filteredMenuItems[i].id).parents('li').show()
					jQuery("#"+filteredMenuItems[i].id).show();
				}
			});
        }
    }, // 초기값 설정
	calendar : {}, // 달력 그리드 생성
	combobox : {},
    datagrid : {},
    tabs : {
    	close : function() {
    		var obj = tab.getObject("MM0001_01_tabs_01");
    		var index = tab.handle.getTabIndex(obj);
    		
    		if(index != 0) {
    			tab.handle.close(obj, index);
    		}
    	},
    	closeOthers : function() {
    		var clary = new Array();
    		var obj = tab.getObject("MM0001_01_tabs_01");
    		var size = tab.handle.getLength(obj); // 탭갯수를 구한다.
    		var index = tab.handle.getTabIndex(obj); // 현재 활성화된 탭을 구한다.
    		
    		for(var i = 0; i < size; i++) {
    			if(i == 0) continue;
    			else {
    				if(index != i) {
    					var title = tab.handle.getTab(obj, i).panel('options').title;
    					
	    				clary.push(title);
	    			}
    			}
    		}
    		
    		for(var a = 0; a < clary.length; a++) {
    			tab.handle.close(obj, clary[a]);
    		}
    	},
    	closeToLeft : function() {
    		var clary = new Array();
    		var obj = tab.getObject("MM0001_01_tabs_01");
    		var size = tab.handle.getLength(obj);
    		var index = tab.handle.getTabIndex(obj);
    		
    		for(var i = 0; i < size; i++) {
    			if(i == 0) continue;
    			else {
    				if(index > i) {
    					var title = tab.handle.getTab(obj, i).panel('options').title;
    					
    					clary.push(title);
    				}
    			}
    		}
    		
    		for(var a = 0; a < clary.length; a++) {
    			tab.handle.close(obj, clary[a]);
    		}
    	},
    	closeToRight : function() {
    		var clary = new Array();
    		var obj = tab.getObject("MM0001_01_tabs_01");
    		var size = tab.handle.getLength(obj);
    		var index = tab.handle.getTabIndex(obj);
    		
    		for(var i = 0; i < size; i++) {
    			if(i == 0) continue;
    			else {
    				if(index < i) {
    					var title = tab.handle.getTab(obj, i).panel('options').title;
    					
    					clary.push(title);
    				}
    			}
    		}
    		
    		for(var a = 0; a < clary.length; a++) {
    			tab.handle.close(obj, clary[a]);
    		}
    	},
    	closeAll : function() {
    		var clary = new Array();
    		var obj = tab.getObject("MM0001_01_tabs_01");
    		var size = tab.handle.getLength(obj);
    		
    		for(var i = 0; i < size; i++) {
    			if(i == 0) continue;
    			else {
    				var title = tab.handle.getTab(obj, i).panel('options').title;
					
    				clary.push(title);
    			}
    		}
    		
    		for(var a = 0; a < clary.length; a++) {
    			tab.handle.close(obj, clary[a]);
    		}
    	}
    },
    ws : {
    	start : function() {
    		var ws_ip = form.handle.getValue("MM0001_01_form_1", "SR_IP");
    		var ws_port = form.handle.getValue("MM0001_01_form_1", "SR_PORT");
    		var ws_url;
    		if(ws_port == "443") ws_url = "wss://"+ws_ip+":"+ws_port+"/toms/kcs/webscoket";
			else ws_url = "ws://"+ws_ip+":"+ws_port+"/toms/kcs/webscoket";
			
    		var webSocket = new WebSocket(ws_url);

    		try {
	    		webSocket.onopen = function(message){
	    			if(SESSION.USER_ID == "fta") console.log("socket open(Session number:"+SESSION.TRANSACTION_ID+")...");
	    			webSocket.send(SESSION.TRANSACTION_ID);
	    		};
	    		webSocket.onclose = function(message){
	    			if(SESSION.USER_ID == "fta") console.log("socket close...");
	    			setTimeout(function(){
	    				if(SESSION.USER_ID == "fta") console.log("try restart...");
	        			
	    				MM0001_01.ws.start();
	    			}, 5000); // 닫히면 5초마다 재연결을 시도한다.
	    		};
	    		webSocket.onerror = function(message){
	    			if(SESSION.USER_ID == "fta") console.log("error...");
	    		};
	    		webSocket.onmessage = function(message){
	    			var data = message.data;
	    			var pid = data.substring(0, PROGRAM_ID_NUMBER);
	    			
	    			try {
		    			if (!oUtil.isNull(pid)) {
		    				var pro_id = eval("window." + pid + ".event");
		    		        if (!oUtil.isNull(pro_id)) {
		    		        	// 그리드 ID에 해당하는 <code>function</code>이 있으면 호출된다.
		    		            if (typeof(pro_id["onCalledWebSoket"]) == "function") {
		    		                pro_id["onCalledWebSoket"](data);
		    		            }
		    		        }
		    		    }
	    			} catch(e) {
	    				if(SESSION.USER_ID == "fta") console.debug("program id = " + pid);
	    			}
	    		};
    		} catch(e) {
    			if(SESSION.USER_ID == "fta") console.debug("web socket exception = " + e);
    		}
    	}
    },
    event : {
    	setUserOfCompany : function(datas) {
    		var data = datas.rows;
    		
    		if(data.length <= 10) {
    			for(var i = 0 ;i<data.length;i++){
    				var selectOption = jQuery('<option></option>');
    				
    				if(SESSION.COMPANY_CD == data[i].CODE) {
    					selectOption.attr('selected',data[i].CODE);
    				}
    				selectOption.text(data[i].NAME);
    				selectOption.attr('value',data[i].CODE);
    				
    				jQuery('.top_area > p > select').append(selectOption);
    			}
    		} else {
    			form.util.setVisible("MM0001_01_pan_01", false);
    			form.util.setVisible("MM0001_01_pan_02", true);
    		}
    	},
    	onCalledWebSoket : function() {
    		$.messager.progress('close');
        },
        selectInquireUserInfo : function(data) {
        	form.handle.loadData("MMA031_03_form_00", data);
        	form.handle.loadData("MM0001_01_form_3", data);
        	MM0001_01.control.selectNoticeCount();
        },
        selectNoticeCount : function(data) {
        	$("#MM0001_01_span_03").html(oUtil.isNull(data.B_NO) ? "0" : data.B_NO);
        }
    },
    control : {// 업무구현
    	go_page :function(url, method, id, title, type, addParam) {
    		var params;
            
            if(!oUtil.isNull(addParam)) params = addParam;
            else params = new Object();
            
    		var closeYn = true;
    		
    		if(id == "MM-0001" || id == "VG-0001") closeYn = false;
    		
    		params.menuIfMethod = method;
    		params.COMPANY_CD = SESSION.COMPANY_CD;
    		params.USER_ID = SESSION.USER_ID;
    		params.MENU_AUTH_YN = "Y";
    		params.MENU_ID = id;
    		// 로그기록을 남기지 않을 페이지를 지정한다. 
    		if(id == "SM-7018") {
    			params.REQUEST_TYPE = null;
    		} else {
    			params.REQUEST_TYPE = "1";
    		}
    		
    		var tabObj = tab.getObject("MM0001_01_tabs_01");
    		var newTabInfo = {id:id, title:title, href:url, queryParams:params, closable:closeYn, mtype:type};
            
            tab.handle.addTab(tabObj, newTabInfo, title);
    	},
    	go_home : function() {
    		if(TOP_SYS_ID == "CC") { // 수출입 통관
    			this.go_page("/mm/pop/mmA001_05", null, "MM-0001", resource.getMessage("MAIN"));
    		} else if(TOP_SYS_ID == "PC") { // 원가계산
    			this.go_page("/mm/pop/mmA001_06", null, "MM-0001", resource.getMessage("MAIN"));
    		} else if(TOP_SYS_ID == "RS") { // 중계서버
                this.go_page("/mm/pop/mmA001_07", null, "MM-0001", resource.getMessage("MAIN"));
            } else { // fta
    			if(SESSION.COMPANY_CD == "KJ100") {
    				this.go_page("/fm/sm/sm7012_01", null, "MM-0001", resource.getMessage("MAIN"));
    			} else {    				
    				if(SESSION.CERTIFY_TYPE == "external") {
    					this.go_page("/mm/pop/mmA001_03", null, "VG-0001", resource.getMessage("MAIN"));
    				} else {
    					this.go_page("/mm/pop/mmA001_01", null, "MM-0001", resource.getMessage("MAIN"));
    				}
    			}
    		}
            
    		return;
    	},
    	go_logout : function() {
    		$.messager.confirm("Info", resource.getMessage("MSG_SESSION_CUT"), function(flag) {
				if(flag) {
					if(form.handle.getValue("MM0001_01_form_1", "SUPPLIER_TYPE") == "ST") {
						window.location.href = '/mm/authority/logoffSupProcess';
					} else {
						window.location.href = '/mm/authority/logoffProcess';
					}
				}
			});
    	},
    	userOfCompany : function() {
    		var obj = form.getObject("MM0001_01_form_1");
    		
    		if(SESSION.CERTIFY_TYPE == "external") {
    			form.init.setURL("/mm/selectCompanyOfSupplier");
    		} else {
    			form.init.setURL("/mm/selectCompanyOfUser");
    		}
    		form.init.setCallBackFunction("setUserOfCompany");
    		form.init.setProgressFlag(false);
    		form.init.setValidationFlag(false);
    		
			form.submit(obj);
    	},
    	changeCompany : function() {
    		var obj = form.getObject("MM0001_01_form_1");

    		form.handle.setValue("MM0001_01_form_1", "COMPANY_CD", form.handle.getValue("MM0001_01_form_1", "selectedCompnay"));
    		MM0001_01_form_1.action="/mm/changeCompany";
			MM0001_01_form_1.submit();
    	},
    	changeLanguage : function(lang) {
    		form.handle.setValue("MM0001_01_form_1", "selectedLanguage", lang);
			
    		MM0001_01_form_1.action="/mm/contentsIndex";
			MM0001_01_form_1.submit();
    	},
    	selectInquireUserInfo : function() {
    		var obj = form.getObject("MM0001_01_form_2");
    		
    		form.init.setURL("/mm/selectInquireUserInfo");
    		form.init.setCallBackFunction("selectInquireUserInfo");
    		form.init.setProgressFlag(false);
    		form.init.setValidationFlag(false);
    		
			form.submit(obj);
    	},
    	selectNoticeCount : function() {
    		var obj = form.getObject("MM0001_01_form_3");
    		
    		form.init.setURL("/mm/noses/mmA031_01/selectNoticeCount");
    		form.init.setCallBackFunction("selectNoticeCount");
    		form.init.setProgressFlag(false);
    		form.init.setValidationFlag(false);
    		
			form.submit(obj);
    	}
    },
    dialog : {
    	dialogOpen_1 : function(pid) {
    		// tab에 대한 인덱스와 옵션을 구한다.
    		var tabObj = tab.getObject("MM0001_01_tabs_01");
    		var tabAtr = tab.handle.getSelected(tabObj);
    		var tabIdx = tab.handle.getSelectedIndex(tabObj);
    		var did = dialogSet.arrayLast(); // 마지막 팝업되어 있는 dialog ID
    		
    		if(!oUtil.isNull(did)) {
    			// 팝업 도움말 매칭
    			var fid = form.handle.getProgramID(did); // dialog 프로그램 ID
    			var tabId = tabAtr.panel("options").id; // 메뉴 화면의 프로그램 ID
    			var oid = changeMenual2Pid(tabId, "M");
    			var tid = "";
    			if(fid.substring(0,2) == "MM") {
    				tid = fid.substring(0,2) + "-" + fid.substring(2, PROGRAM_ID_NUMBER);
    			} else {
    			    tid = oid + fid.substring(PROGRAM_ID_NUMBER-3, PROGRAM_ID_NUMBER);
    			}
    			
    			var cid = changeMenual2Pid(tid, "P");
    			
    			if(tabId == "FR-1010" && fid == "FR1010_03") { // FTA 세율
    				pid = "FR-1010_03";
    			} else if(tabId == "DI-3014" && fid == "FR1010_02") { // 해외법인 매출실적
    				pid = "FR-1010_02";
    			} else if(tabId == "OR-5001" && fid == "OR5008_02") { // 수출용 증명서 발급
    				pid = "OR-5008_02";
    			} else if(tabId == "OR-5001" && fid == "DI3003_02") { // 수출용 증명서 발급
    				pid = "DI-3003_02";
    			} else if(tabId == "FR-1003") { // 원산지 결정기준
    				if(did == "FR1003_02_dialog_01") {
    					if(form.handle.getValue("FR1003_02_form_01", "GRID_ID") == "FR1003_01_grid_01") pid = "FR-1003_02"; // 일반기준
    					if(form.handle.getValue("FR1003_02_form_01", "GRID_ID") == "FR1003_01_grid_02") pid = "FR-1003_10"; // 예외기준
    				}
    		    } else if(tabId == "FR-1009") { // HS코드 변경관리
    				if(did == "FR1009_02_dailog_01") {
    					if(form.handle.getValue("FR1009_02_form_01", "flag") == "evcd") pid = "FR-1009_10";
    					else pid = "FR-1009_02";
    				}
    		    } else if(tid == "MM-A004_05") { // 제조공정도 출력 
    				if(oid == "SI-2005") pid = "SI-2005_10";
    				if(oid == "VG-B207") pid = "VG-B207_10";
    		    } else if(did.substring(0, PROGRAM_ID_NUMBER) == "MMA016_01") { // 엑셀업로드
    				if(oid == "SI-2006") {
    					if(form.handle.getValue("MMA016_01_form_02", "IF_CD") == "RPT_MM_005") pid = oid+"_00_1"; // 협력사 품목(나중에 추가 필요)
    					else pid = oid+"_00"; // 엑셀업로드 페이지
    				} else if(oid == "SI-2203") {
    					if(form.handle.getValue("MMA016_01_form_02", "IF_CD") == "RPT_SD_010") pid = oid+"_00_1"; // 협력사 품목(나중에 추가 필요)
    					else pid = oid+"_00"; // 엑셀업로드 페이지
    				} else {
    					pid = oid+"_00";
    				}
    			} else if(tid == "MM-A017_01") { // 협력사 원산지 확인서 매칭
    				if(form.handle.getValue("MMA017_01_form_01", "flag") == "view") {
    					pid = "MM-A017_01";
    				} else {
    					if(tabId == "DI-3010") {
    						var tabDiObj = tab.getObject("DI3010_01_tabs_01");
            	    		var tabDiIdx = tab.handle.getSelectedIndex(tabDiObj);
            	    		
            	    		if(tabDiIdx == "3") {
            	    			pid = "MM-A017_01_6";
            	    		} else {
            	    			pid = "DI-3010_10"; // 내부 협력사 확인서 관리내 확인서  신규 등록 페이지
            	    		}
    					}
    					else if(tabId == "DI-3005") pid = "DI-3005_10"; // 내부 협력사 증명서 신규 등록 페이지
    					else if(tabId == "DI-3011") pid = "DI-3011_10"; // 내부 협력사 미품목 확인서내 확인서 신규 등록 페이지
    					else if(tabId == "VG-B001") pid = "MM-A017_01_1"; // 협력사 포탈 > 신규등록
    					else if(tabId == "VG-B002") pid = "MM-A017_01_2"; // 협력사 포탈 > 갱신등록
    					else if(tabId == "VG-B003") pid = "MM-A017_01_3"; // 협력사 포탈 > 수정요청
    					else if(tabId == "VG-B004") pid = "MM-A017_01_4"; // 협력사 포탈 > 수정신고
    					else if(tabId == "VG-B005") pid = "MM-A017_01_5"; // 협력사 포탈 > 미결요청
    					else if(tabId == "VG-B006") pid = "MM-A017_01_6"; // 협력사 포탈 > 미등록 협정
    				}
    			} else if(tabId == "DI-3009" && fid == "SI2006_04") { // 협력사 메일링 > 메일주소 수정
    				pid = "DI-3209_10"; 
    			} else if(oid == "VG-0001" && fid == "SM7011_02") { // 협력사 메일링 > 메일주소 수정
    				pid = "VB-E001_02"; 
    			} else if(fid == "OD4205_02") { // 원산지 판정 > 판정결과
    				var tabDeObj = tab.getObject("OD4205_02_tabs_01");
    				
    				if(tabDeObj.length > 0) {
	    				var tabDeAtr = tab.handle.getSelected(tabDeObj);
	    	    		var tabDeId = tabDeAtr.panel("options").id; // 프로그램 ID
	    	    		
	    	    		pid = changeMenual2Pid(tabDeId, "P");
    				} else if(tabId == "OR-5002") {
    					pid = form.handle.getValue("OR5202_01_form_01", "DIALOG_PID");
    				}
    			} else {
    				pid = cid;
    			}
    			
    			if(oUtil.isNull(pid)) {
    				pid = "#"; // 나중에 오류 페이지로 링크    				
    			}
    		} else {
    			// 메인화면 도움말 매칭
        		if(oUtil.isNull(pid) && tabIdx > 0) {    			
        			var tid = tabAtr.panel("options").id;
        			pid = changeMenual2Pid(tid, "M");
        			
        			if(tid == "CS-6002") { // 일마감배치
        				pid = pid+"_02";
        			} else if(tid == "DI-3010") { // 협력사 확인서  > 확인서 관리
        				var tabDiObj = tab.getObject("DI3010_01_tabs_01");
        	    		var tabDiIdx = tab.handle.getSelectedIndex(tabDiObj);
        	    		
        	    		pid = pid+"_01_"+tabDiIdx;
        			} else if(tid == "OD-4001") { // 협력사 확인서  > 확인서 관리
        				var tabDiObj = tab.getObject("OD4201_01_tabs_01");
        	    		var tabDiIdx = tab.handle.getSelectedIndex(tabDiObj);
        	    		
        	    		pid = pid+"_01_"+tabDiIdx;
        			} else if(tid == "SI-2003") { // 기준정보 > 고객사 품목
        				var tabDiObj = tab.getObject("SI2203_01_tabs_01");
        	    		var tabDiIdx = tab.handle.getSelectedIndex(tabDiObj);
        	    		
        	    		pid = pid+"_01_"+tabDiIdx;
        			} else if(tid == "OR-5001") { // 원산지 서류 > 직수출 원산지 증명서
        				var tabDiObj = tab.getObject("OR5201_01_tabs_02");
        	    		var tabDiIdx = tab.handle.getSelectedIndex(tabDiObj);
        	    		
        	    		pid = pid+"_01_"+tabDiIdx;
        			} else if(tid == "DI-3002") { // 거래정보 > 매출원장
        				var tabDiObj = tab.getObject("DI3202_01_tabs_01");
        	    		var tabDiIdx = tab.handle.getSelectedIndex(tabDiObj);
        	    		
        	    		pid = pid+"_01_"+tabDiIdx;
        			} else {
	        			if(!oUtil.isNull(pid)) {
	        				if(tid == "DI-3005") pid = "DI-3205"; // 협력사 확인서 수취현황
	        				pid = pid+"_01";
	        			} else {
	        				pid = "#"; // 나중에 오류 페이지로 링크
	        			}
        			}
        		}
    		}
    		
    		var site = form.handle.getValue("MM0001_01_form_1", "HELP_SITE");
    		
    		// 인터넷이 연결된 상태에서는 TOMS 클라우드의 도움말을 적용하고, 없으면 로컬에 저장된 메뉴얼을 연결한다.(2019.11.23)
    		if(site.indexOf("localhost") > -1 || site.indexOf("172.0.0.1") > -1) {
    			site = "";
    		} else {
	    		if(!navigator.onLine || oUtil.isNull(site)) {
	    			site = "";
	    		}
    		}
    		
    		if(oUtil.isNull(pid)) {
    			window.open(site+"/mm/pop/mmA029_01?CERTIFY_TYPE="+SESSION.CERTIFY_TYPE+"&LANG="+SESSION.DEFAULT_LANGUAGE, "TOMS_Full_Help", "scrollbars=yes,toolbar=no,location=no,resizable=no,status=no,menubar=no,resizable=yes,width=1274,height=800,left=0,top=0");
    		} else {
    			objPopup = window.open(site+"/mm/pop/mmA029_02?CERTIFY_TYPE="+SESSION.CERTIFY_TYPE+"&LANG="+SESSION.DEFAULT_LANGUAGE+"&PID="+pid, "TOMS_Help", "scrollbars=yes,toolbar=no,location=no,resizable=no,status=no,menubar=no,resizable=yes,width=900,height=800,left=0,top=0");
    		}
    	},
    	dialogOpen_2 : function() {
    		if(SESSION.CERTIFY_TYPE == "external") {
    			return;
    		} else {
		        var dl_1 = dialog.getObject("SM7003_02_dailog_01");
		        
		        // 팝업으로 넘겨줄 파라메터 생성
		        var params = {};
		        var params1 = SESSION;
		        var params2 = {};
		        
		        params1.USER_ID = params1.USER_ID;
		        params1.flag = "update";
		        params1.callPid = "INDEX";
		        
		        $.extend(params, params1, params2);
		        
		        dialog.init.setWidth(1024);
		        dialog.init.setHeight(330);
		        dialog.init.setURL("/fm/sm/sm7003_02");
		        dialog.init.setResizable(true);
		        dialog.init.setQueryParams(params);
		        dialog.init.setTitle(resource.getMessage("SM7003_02"));
		        dialog.open(dl_1);
    		}
    	},
    	openDialog_3: function() {
    		var params = new Object();
    		var dg_1 = dialog.getObject("MMA032_01_dailog_01");
      		
    		params.COMPANY_CD = SESSION.COMPANY_CD;
    		params.TARGET_PID = "MM0001_01";
      		
      		dialog.init.setWidth(900);
      		dialog.init.setHeight(500);
      		dialog.init.setURL("/mm/pop/mmA032_01");
      		dialog.init.setQueryParams(params);
      		      		
    		dialog.open(dg_1);
    	},
    	callByMMA032_01 : function(datas) {
    		var dg_1 = dialog.getObject("MMA032_01_dailog_01");
    		dialog.handle.close(dg_1);
    		
    		var obj = form.getObject("MM0001_01_form_1");
    		
    		form.handle.setValue("MM0001_01_form_1", "COMPANY_CD", datas.COMPANY_CD);
    		MM0001_01_form_1.action="/mm/changeCompany";
			MM0001_01_form_1.submit();
    	},
    	dialogOpen_4 : function() {
    		var site = form.handle.getValue("MM0001_01_form_1", "HELP_SITE");
    		var url = site+"/mm/noses/mmA031_03";
    		var params = form.handle.getElementsParam("MMA031_03_form_00");
    		var paramUrl = makeStringParameter(params);
    		
    		$("#main_win_2").window("open");
    		if(oUtil.isNull($("#MM0001_01_iframe_1").attr("src"))) {
    			$("#MM0001_01_iframe_1").attr("src", url+"?"+paramUrl);
    		}
        }
    }, // 파일 input 엘리먼트 구현
    file : {}, // 엑셀다운로드 구현
    excel : {},
    config : {
    	applyFtaNation : function() {
    		if(SESSION.CERTIFY_TYPE == "external") {
    			$("#MM0001_01_span_02").html(SESSION.VENDOR_NAME);
    		} else {    			
    			$("#MM0001_01_span_02").html(SESSION.COMPANY_NAME);
    		}
    		
    		$("#MM0001_01_tabs_01").find(".tabs").bind('contextmenu',function(e){
                e.preventDefault();
                
                $('#main_tab_menu').menu('show', {
                    left: e.pageX,
                    top: e.pageY
                });
            });
    		
    		// 문의 및 요청 정보 조회
    		MM0001_01.control.selectInquireUserInfo();
    		
    		// 서비스 센터에서는 fta계정을 사용할 수 없도록 조치
    		if(SESSION.COMPANY_CD == "KJ100" && SESSION.USER_ID == "fta") {
    			$.messager.confirm(CNFIR, resource.getMessage("TXT_SERVICE_FTA_USER_NOT"), function(flag) {
                    if(flag) {
                    	window.location.href = '/mm/authority/logoffProcess';
                    } else {
                    	window.location.href = '/mm/authority/logoffProcess';
                    }
                });
    			
    		}
    		
    		// 카카오 초기화
    		if(!oUtil.isNull(SESSION.KAKAO_SCRIPT_KEY)) {
    			Kakao.init(SESSION.KAKAO_SCRIPT_KEY);
    			$("#MM0001_01_span_04").html("Kakao 연동중");
    		}
    		
            if(TOP_SYS_ID == "RS") {
                // 메뉴바 숨기기
                var $lnbBtn = $('.aside_bar .btn_open img'); // 메뉴 영역 숨기기/보이기
                var $aside_bar = $('.aside .aside_bar'); // 좌측 대메뉴 영역 하위2
                var $lnb = $('.aside .lnb'); // 검색+즐겨찾기 메뉴
                var winW = $(window).width();
                
                $lnbBtn.attr("src","../images/icon/aside_open.gif");
                $('#MM0001_01_div_01').panel('panel').animate({left:($lnb.width()*-1)}, 0);
                $("#MM0001_01_div_02").panel('resize',{
                    width: winW - $aside_bar.width() + 2,
                    left:0
                });
            } else if(TOP_SYS_ID != "CC") { // 통관시스템가 아닌 경우
    			if(SESSION.APPLICATION_SERVICE_TYPE == "CL") { // 글라우드에서는 모든 다국어를 표시할 것
    				form.util.setVisible("MM0001_01_href_2", true); // 스페인어
    				form.util.setVisible("MM0001_01_href_3", true); // 베트남어
    			} else {
    				if(SESSION.FTA_NATION == "MX") {
    					form.util.setVisible("MM0001_01_href_2", true); // 스페인어
    				} else if(SESSION.FTA_NATION == "VN") {
    					form.util.setVisible("MM0001_01_href_3", true); // 베트남어
    				}
    			}
    			
    			form.util.setVisible("MM0001_01_href_1", true); // 영어
    			form.util.setVisible("MM0001_01_li_1", true);
    			form.util.setVisible("MM0001_01_li_2", true);
    		}
		}
    }
	
};
