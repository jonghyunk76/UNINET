var dataset = {
	gridArray :[],
	arrayPush : function(obj){
		this.gridArray.push(obj);
	},
	arrayPop : function(){
		var obj = this.gridArray.pop();
		return obj;
	},
	arrayRemove : function(id){
		for(var i = 0; i < this.gridArray.length; i++) {
			if(this.gridArray[i] == id) {
				this.gridArray.splice(i, i);
			}
		}
	},
	arrayFilter : function(id) {
		var objt = this.gridArray.filter(function(obj){
			if(obj == id) return obj;
		});
		
		if(objt.length > 0) return objt[0];
		return null;
	},
	isArrayObject : function(id) {
		var objt = this.gridArray.filter(function(obj){
			if(obj == id) return obj;
		});
		
		if(objt.length > 0) return true;
		return false;
	},
	config:function(){},
	searchHeader : [
    	["KEY_NUMBER", resource.getMessage("KEYWD, CODE"),  26, "center", "center", true, false, null, null, null, 0, 0],
       	["KEY_WORD", resource.getMessage("KEYWD, CODE"),  218, "left", "left", true, false, null, null, null, 0, 0]
    ]
};

var comboVo = {
		comboArray :[],
		arrayPush : function(obj){
			this.comboArray.push(obj);
		},
		arrayPop : function(){
			var obj = this.comboArray.pop();
			return obj;
		},
		/**
		 * 지정된 ID에 해당하는 값을 배열에서 삭제함
		 * id : combobox 데이터 호출 시 파마레터로 지정된 값을 의미함
		 */
		arrayRemove : function(params){
			var id = JSON.stringify(params);
			
			for(var i = 0; i < this.comboArray.length; i++) {
				if(JSON.stringify(this.comboArray[i].id) == id) {
					this.comboArray.splice(i, 1);
				}
			}
		},
		arrayFilter : function(params) {
			var id = JSON.stringify(params);
			
			var objt = this.comboArray.filter(function(obj){
				if(JSON.stringify(obj.id) == id) return obj;
			});
			
			if(objt.length > 0) return objt[0].data;
			return null;
		},
		isArrayObject : function(params) {
			var id = JSON.stringify(params);
			
			var objt = this.comboArray.filter(function(obj){
				if(JSON.stringify(obj.id) == id) return obj;
			});
			
			if(objt.length > 0) return true;
			return false;
		},
		config:function(){}
	};

var dialogSet = {
	dialogArray :[],
	arrayPush : function(obj){
		this.dialogArray.push(obj);
	},
	arrayPop : function(){
		var obj = this.dialogArray.pop();
		return obj;
	},
	arrayRemove : function(id){
		for(var i = 0; i < this.dialogArray.length; i++) {
			if(this.dialogArray[i] == id) {
				this.dialogArray.splice(i, 1);
				break;
			}
		}
	},
	arrayFilter : function(id) {
		var objt = this.dialogArray.filter(function(obj){
			if(obj == id) return obj;
		});
		
		if(objt.length > 0) return objt[0];
		return null;
	},
	isArrayObject : function(id) {
		var objt = this.dialogArray.filter(function(obj){
			if(obj == id) return obj;
		});
		
		if(objt.length > 0) return true;
		return false;
	},
	arrayLast : function() {
		var sliceArray = this.dialogArray.slice();
		return sliceArray.pop();
	}
};

var gridGroupSet = {
	gridGroupArray :[],
	arrayPush : function(obj){
		this.gridGroupArray.push(obj);
	},
	arrayPop : function(){
		var obj = this.gridGroupArray.pop();
		return obj;
	},
	arrayRemove : function(id){
		for(var i = 0; i < this.gridGroupArray.length; i++) {
			if(this.gridGroupArray[i] == id) {
				this.gridGroupArray.splice(i, 1);
				break;
			}
		}
	},
	arrayFilter : function(id) {
		var objt = this.gridGroupArray.filter(function(obj){
			if(obj == id) return obj;
		});
		
		if(objt.length > 0) return objt[0];
		return null;
	},
	isArrayObject : function(id) {
		var objt = this.gridGroupArray.filter(function(obj){
			if(obj == id) return obj;
		});
		
		if(objt.length > 0) return true;
		return false;
	},
	arrayLast : function() {
		var sliceArray = this.gridGroupArray.slice();
		return sliceArray.pop();
	}
};

var gridObject = function(){

	/**
	 * datagrid 속성을 관리하고 요청 ID에 해당하는 객체를 리턴
	 * @param gid = grid ID
	 */
	this.getObject = function(gid) {
		var exsitsInit = dataset.isArrayObject(gid + ".init");
		var exsitsEvent = dataset.isArrayObject(gid + ".event");
		
		grid.id = gid;
		
		if(!exsitsInit) {
			grid.init.config = new Object(gid+".init");
			dataset.arrayPush(grid.init.config);
		}
		
		if(!exsitsEvent) {
			grid.event.config = new Object(gid+".event");
			dataset.arrayPush(grid.event.config);
		}
		
		return $("#" + gid);
	};
	
	/**
	 * 데이터 그리드 그룹뷰에 대한 현재 상태를 구하는 함수
	 * 
	 * @param gid = grid ID
	 * @param sta = 상태값
	 */
	this.setGroupConfig = function(gid, sta) {
		var conf = dataset.arrayFilter(gid+".init");
		
		var exsitsState = gridGroupSet.isArrayObject(gid+".group"+sta.index);
		
		if(!exsitsState) { // 이전에 설정된 값이 없으면 신규로 생성
			var staobj = new Object(gid+".group"+sta.index);
			staobj.group = sta;
			gridGroupSet.arrayPush(staobj);
		} else { // 이전에 설정된 값이 있으면 상태값 업데이트
			var staobj = gridGroupSet.arrayFilter(gid+".group"+sta.index);
			staobj.group = sta;
		}
		
		conf.groupView = gridGroupSet.gridGroupArray;
//		console.log("config groupView >> " + conf.groupView.length + ", exists = " + exsitsState + ", info = " + sta.index + ", " + sta.state);
	};
	
	/**
	 * datagrid 속성를 구하는 함수
	 * 
	 * @param gid = grid ID
	 */
    this.getConfig = function(gid) {
    	if(gid == null) gid = grid.id;
    	else gid = grid.handle.getGridID(gid);
    	
    	var conf = {};
    	var conf1 = dataset.arrayFilter(gid+".init");
    	var conf2 = dataset.arrayFilter(gid+".event");
    	
    	$.extend(conf, conf1, conf2);
    	
    	return conf;
    };
	
    /**
	 * datagrid 속성의 초기설정값을 구하는 함수
	 * 
	 * @param gid = grid ID
	 */
    this.getInitConfig = function(gid) {
    	if(gid == null) gid = grid.id;
    	else gid = grid.handle.getGridID(gid);
    	
    	return dataset.arrayFilter(gid+".init");
    };
    
    /**
	 * datagrid 속성의 이벤트 설정값을 구하는 함수
	 * 
	 * @param gid = grid ID
	 */
    this.getEventConfig = function(gid) {
    	if(gid == null) gid = grid.id;
    	else gid = grid.handle.getGridID(gid);
    	
    	return dataset.arrayFilter(gid+".event");
    };
    
	// Grid Header Type
    this.HeaderColumnTypes = {field:0, title:1, width:2, align:3, halign:4, sortable:5, hidden:6, editor:7, checkbox:8, formatter:9, rowspan:10, colspan:11, frozen:12, styler:13};
    
    // Sort Type
    this.alignType = {left: "left", center: "center", right: "right"};
    
    // Column default Value
    this.StateColName = "IUD";
    this.StateColWidth = "0";
    
    // default Header Value
    this.StateColArray = [this.StateColName, this.StateColName, this.StateColWidth, this.alignType.left, "true"];
    
    // max tabs number
    this.MAX_TAB_NUM = 10;
    
};

var formObject = function(){
	
	/**
	 * form 속성을 관리하고 요청 ID에 해당하는 객체를 리턴
	 * 
	 * @param fid = form ID:String
	 * @param eId = form 구성 id
	 */
	this.getObject = function(fid, eId) {
		var exsitsInit = dataset.isArrayObject(fid + ".init");
		var exsitsEvent = dataset.isArrayObject(fid + ".event");
		
		form.id = fid;
		
		if(!exsitsInit) {
			form.init.config = new Object(fid+".init");
			dataset.arrayPush(form.init.config);
		}
		
		if(!exsitsEvent) {
			form.event.config = new Object(fid+".event");
			dataset.arrayPush(form.event.config);
		}
		
		if(oUtil.isNull(eId)) return $("#" + fid);
        else return $("#" + fid).find("#" + eId);
	};
	
	/**
	 * form 속성를 구하는 함수
	 * 
	 * @param fid = form ID
	 */
    this.getConfig = function(fid) {
    	if(fid == null) fid = form.id;
    	
    	var conf = {};
    	var conf1 = dataset.arrayFilter(fid+".init");
    	var conf2 = dataset.arrayFilter(fid+".event");
    	
    	$.extend(conf, conf1, conf2);
    	
    	return conf;
    };
    
	/**
	 * form 속성의 초기설정값을 구하는 함수
	 * 
	 * @param fid = form ID
	 */
    this.getInitConfig = function(fid) {
    	if(fid == null) fid = form.id;
    	
    	return dataset.arrayFilter(fid+".init");
    };
    
    /**
     * submit 수행
     * 
     * @param obj = form object
     */
    this.submit = function(obj) {
    	var fid = (typeof obj == "object") ? obj.attr("id") : obj;
    	var initConf = form.getInitConfig(fid);
    	
    	this.convertDate(fid);
    	
    	if(oUtil.isNull(initConf.validationFlag)) initConf.validationFlag = true;
    	if(oUtil.isNull(initConf.progressFlag)) initConf.progressFlag = true;
    	if(oUtil.isNull(initConf.jsonReturnFlag)) initConf.jsonReturnFlag = true;
	    if(oUtil.isNull(initConf.debugFlag)) initConf.debugFlag = false;
	    if(oUtil.isNull(initConf.returnType)) initConf.returnType = "none";
	    //if(oUtil.isNull(initConf.dataType)) initConf.dataType = "xml";
	    if(oUtil.isNull(initConf.loadData)) initConf.loadData = false;
	    
	    // 엑셀요청 parameter 제거
	    initConf.excelFlag = false;
	    this.excelElementHandle(fid, "headers", "remove");
    	this.excelElementHandle(fid, "filename", "remove");
    	this.excelElementHandle(fid, "sheetname", "remove");
    	this.excelElementHandle(fid, "websocket", "remove");
    	
	    form.event.setOnSubmit(obj);
	    form.event.setSuccess(obj);
	    form.event.setOnLoadSuccess(obj);
	    form.event.setOnChange(obj);
	    
	    obj.form('submit', form.getConfig(fid));
    };
    
    /**
     * excel download을 수행하기 위한 submit 수행
     * 
     * @param gobj = grid object
     * @param fobj = form object
     * @param headers = header object
     * @param filename = file name:string
     * @param sheetname = sheet name:string
     */
    this.excelSubmit = function(gobj, fobj, filename, sheetname) {
    	var fid = (typeof fobj == "object") ? fobj.attr("id") : obj;
    	var initConf = form.getInitConfig(fid);
    	
    	this.convertDate(fid);
    	
    	initConf.excelFlag = true;
    	this.excelElementHandle(fid, "headers", "add");
    	this.excelElementHandle(fid, "filename", "add");
    	this.excelElementHandle(fid, "sheetname", "add");
    	this.excelElementHandle(fid, "websocket", "add");
    	
    	$.messager.progress({
        	msg: "Excel downloading...",
        	text : "Loading data..."
        });
    	// 1분동안 응답이 없으면 자동으로 창을 닫는다.
//    	setTimeout(function(){
//            $.messager.progress('close');
//        }, 60000);
        
    	var prop = grid.getInitConfig(gobj);
        var headers = grid.util.convertJson2Header(prop.columns, prop.frozenColumns);
        
    	initConf.headers = headers;
        initConf.filename = resource.changeCode4FileName(filename);
        initConf.sheetname = sheetname;
        initConf.progressFlag = false;
        initConf.validationFlag = false;
        
        form.event.setOnSubmit(fobj);
	    form.event.setSuccess(fobj);
	    form.event.setOnLoadSuccess(fobj);
	    
	    fobj.form('submit', form.getConfig(fid));
    };
    
    this.excelElementHandle = function(fid, eid, flag) {
    	var fobj = form.getObject(fid);
        var eobj = form.getObject(fid, eid);
        
    	if(eobj.length > 0 && flag == "remove") {
    		eobj.remove();
    		return;
        }
    	
        if(eobj.length <= 0 && flag == "add") {
        	var input = "<input type=\"hidden\" id=\"" + eid + "\" name=\"" + eid + "\"/>";
        	
        	fobj.append(input);
        }
    };
    
    this.convertDate = function(formId) {
    	var fm = document.getElementById(formId);
        var fmlen = fm.length;
        var jsonVal = {};
        
        for (var i = 0; i < fmlen; i++) {
        	var noTag = fm.elements[i];
            var key = noTag.id;
            var type = noTag.type;
            
            var obj = form.getObject(formId, key);
            
            if (oUtil.isNull(obj) || oUtil.isNull(key)) {
                continue;
            } else {
            	var exp = false;
            	try {
                    obj.datebox("options");
                } catch(e) {
                	exp = true;
                }
                
                if(!exp) {
                	value = calendar.handle.getDate(formId, key);
                	obj.datebox('setValue', value);
                }
            }
        }
    };
    
};

var dialogObject = function(){
	
	/**
	 * dialog 속성을 관리하고 요청 ID에 해당하는 객체를 리턴
	 * 
	 * @param did = dialog ID:String
	 */
	this.getObject = function(did, eId) {
		var exsitsInit = dataset.isArrayObject(did + ".init");
		var exsitsEvent = dataset.isArrayObject(did + ".event");
		
		dialog.id = did;
		
		if(!exsitsInit) {
			dialog.init.config = new Object(did+".init");
			dataset.arrayPush(dialogMethod.init.config);
			
			this.createPopupDiv(did);
		}
		
		if(!exsitsEvent) {
			dialog.event.config = new Object(did+".event");
			dataset.arrayPush(dialogMethod.event.config);
		}
		
		return $("#" + did);
	};
	
	/**
	 * dialog 속성를 구하는 함수
	 * 
	 * @param did = dialog ID:String
	 */
    this.getConfig = function(did) {
    	if(did == null) did = dialog.id;
    	
    	var conf = {};
    	var conf1 = dataset.arrayFilter(did+".init");
    	var conf2 = dataset.arrayFilter(did+".event");
    	
    	$.extend(conf, conf1, conf2);
    	
    	return conf;
    };
    
	/**
	 * dialog 속성의 초기설정값을 구하는 함수
	 * 
	 * @param did = dialog ID:String
	 */
    this.getInitConfig = function(did) {
    	if(did == null) did = dialog.id;
    	
    	return dataset.arrayFilter(did+".init");
    };
    
    /**
     * dialog open
     * 
     * @param obj = dialog object
     * @parma hid = 도움말 ID(프로그램명)
     */
    this.open = function(obj) {
    	var did = (typeof obj == "object") ? obj.attr("id") : obj;
    	var dialogObj = dialog.getObject(did);
    	var initConf = dialog.getInitConfig(did);
    	
    	try {
    		var emptitle;
    		
    		if((initConf.title).startsWith("[c]")) { // [c] 이후의 문자열을 타이틀과 경로를 동시에 지정
    			emptitle = replaceAll(initConf.title , "[c]", "");
    		} else {
    			emptitle = "";
    		}
    		
    		if((initConf.title).startsWith("[f]")) { // [f] 이후의 문자열을 타이틀로 지정 
    			initConf.title = replaceAll(initConf.title , "[f]", "");
    			initConf.helpable = false;
    		} else {
	    		if(did == "MMA031_01_dailog_01") {
	    			initConf.title = resource.getMessage("INQUI,/,NOTIS");
			    	initConf.helpable = false;
	    		} else if(did == "MMA034_02_dailog_01"){
	    			initConf.title = "GRID " + resource.getMessage("SETTING");
			    	initConf.helpable = false;
	    		} else if(did == "MMA034_03_dailog_01"){
	    			initConf.title = "GRID " + resource.getMessage("MGT");
			    	initConf.helpable = false;
	    		} else {
			    	var tabObj = tab.getObject("MM0001_01_tabs_01");
					var tabAtr = tab.handle.getSelected(tabObj);
					
			    	initConf.title = tabAtr.panel("options").title;
			    	initConf.helpable = true;
	    		}
	    		
	    		if(!oUtil.isNull(emptitle)) {
	    			initConf.title = initConf.title + " ▶ " + emptitle;
	    		}
    		}
    	} catch(e) {
    		initConf.title = " ";
	    	initConf.helpable = false;
    	}
    	
    	if(oUtil.isNull(initConf.closable)) initConf.closable = true;
	    if(oUtil.isNull(initConf.resizable)) initConf.resizable = false;
	    if(oUtil.isNull(initConf.method)) initConf.method = "post";
	    if(oUtil.isNull(initConf.modal)) initConf.modal = true;
	    if(oUtil.isNull(initConf.arrowEvent))initConf.arrowEvent = false;
	    if(initConf.arrowEvent) {
	    	initConf.title = initConf.title + "<span style='color:#ffff00;'> (▲ : "+resource.getMessage("BEFRE, SERCH")+" / ▼ : "+resource.getMessage("NEXT, SERCH")+")</span>";
	    }
	    
	    var width = initConf.width;
	    var height = initConf.height;
	    
	    if(!oUtil.isNull(did.getWidth())) width = did.getWidth();
	    if(!oUtil.isNull(did.getHeight())) height = did.getHeight();
	    
	    // 폰트변경에 따라 높이를 재 조정함
	    height = height + 2;
	    
	    var lyH = $(window).height(); 
		var lyW = $(window).width();
		var ly = $('#'+did).attr("alt");
		var barW = $('.'+ly).width();
		var barH = $('.'+ly).height();
		
	    // 높이와 넓이 지정
	    if (oUtil.isNull(width)) width = 400;
	    if (oUtil.isNull(height)) height = 500;
	    if (width > (lyW - barW)) width = (lyW - barW) * 0.97;
	    if (height > (lyH - barH)) height = (lyH - barH) * 0.97;
	    
	    initConf.width = width;
	    initConf.height = height;
	    if(oUtil.isNull(initConf.maximizable)) initConf.maximizable = true;
	    
	    // 파라메터 추가(사용자 이용내역 기록용)
//	    var paramObj = initConf.queryParams;
//	    
//	    if(!oUtil.isNull(paramObj)) {
//	    	paramObj.COMPANY_CD = SESSION.COMPANY_CD;
//	    	paramObj.USER_ID = SESSION.USER_ID;
//	    	paramObj.MENU_ID = did.substring(0, PROGRAM_ID_NUMBER);
//	    	paramObj.REQUEST_TYPE = "2";
//	    }
	    
	    dialog.event.setOnLoad(dialogObj);
	    dialog.event.setOnClose(dialogObj);
	    dialog.event.setOnMove(dialogObj, width, height);
	    
	    // dialog가 있는지 확인서 가장 최근에 open된dialog를 기록한다. 
		if(!dialogSet.isArrayObject(did)) dialogSet.arrayPush(did);
	    
	    var conf = dialog.getConfig(did);
	    
	    dialogObj.dialog(conf);
    };
    
    this.createPopupDiv = function(tagId) {
        var div = '<div id="' + tagId + '" name="' + tagId + '"></div>';
        var tagCurrObj = "#" + tagId;
        
        $(tagCurrObj).remove();
        $('body').append(div);
    };
    
};

var comboObject = function(){
	
	/**
	 * form내 콤보박스 속성을 관리하고 요청 ID에 해당하는 객체를 리턴
	 * 
	 * @param fid = form ID:String:필수
	 * @param eId = combobox id:필수
	 */
	this.getObject = function(fid, eid) {
		var cid = fid + "." + eid;
		var exsitsInit = dataset.isArrayObject(cid + ".init");
		var exsitsEvent = dataset.isArrayObject(cid + ".event");
		
		if(!exsitsInit) {
			combo.init.config = new Object(cid +".init");
			dataset.arrayPush(combo.init.config);
		}
		
		if(!exsitsEvent) {
			combo.event.config = new Object(cid +".event");
			dataset.arrayPush(combo.event.config);
		}
		
		combo.id = cid;
		
		return $("#" + fid).find("#" + eid);
	};
	
	/**
	 * form내 콤보박스 속성를 구하는 함수
	 * 
	 * @param fid = form ID
	 * @param eId = combobox id
	 */
    this.getConfig = function(fid, eid) {
    	var cid = fid + "." + eid;
    	
    	var conf = {};
    	var conf1 = dataset.arrayFilter(cid +".init");
    	var conf2 = dataset.arrayFilter(cid +".event");
    	
    	$.extend(conf, conf1, conf2);
    	
    	return conf;
    };
    
	/**
	 * form내 콤보박스 속성의 초기설정값을 구하는 함수
	 * 
	 * @param fid = form ID
	 * @param eId = combobox id
	 */
    this.getInitConfig = function(fid, eid) {
    	var cid = fid + "." + eid;
    	
    	return dataset.arrayFilter(cid +".init");
    };
    
    /**
	 * form내 콤보박스 속성의 이벤트 설정값을 구하는 함수
	 * 
	 * @param fid = form ID
	 * @param eId = calendar id
	 */
    this.getEventConfig = function(fid, eid) {
    	var cid = fid + "." + eid;
    	
    	return dataset.arrayFilter(cid +".event");
    };
    
    /**
     * form내 콤보박스을 생성
     * 
     * @param obj = combobox object
     * @param fid = form id(선택)
     */
    this.create = function(obj, fid) {
    	var fid = (typeof obj == "object") ? obj.parents("#"+obj.attr("id") + ",form").attr("id") : fid;
    	var eid = (typeof obj == "object") ? obj.attr("id") : obj;
    	
    	var comboObj = combo.getObject(fid, eid);
    	var initConf = combo.getInitConfig(fid, eid);
    	
    	// 이미 호출된 정보인 경우에는 기존에 캐시된 데이터를 적용하도록 수정(2020.06.25)
    	if(initConf.url == "/mm/cbox/selectStandardCode") {
        	var exsitsVo = comboVo.isArrayObject(initConf.queryParams);
        	
        	if(exsitsVo) {
        		initConf.url = "";
        		initConf.data = comboVo.arrayFilter(initConf.queryParams);
        	}
    	}
    	
    	if(initConf.type == "year") {
    		var toYear = calendar.util.toYear2String();
    		var year = parseInt(oUtil.isNull(initConf.startYear)? toYear: initConf.startYear);
    		var toCount = parseInt(calendar.util.between2Month("2013-01-01", calendar.util.toDate2String("-")) / 12) + 1;
    		var count = parseInt(oUtil.isNull(initConf.yearCount)? toCount : initConf.yearCount);
    		var dataArray = new Array();
    		
    		for(var i = 0; i < count; i++) {
    			dataArray[i] = {CODE:year, NAME:year};
    			year++;
    		}
    		
    		if(oUtil.isNull(initConf.initValue)) {
    			initConf.initValue = toYear;
    		} else if(initConf.initValue == "0000") {
    			dataArray[0] = {CODE:" ", NAME:resource.getMessage("SELET")};
    			initConf.initValue = " ";
    		} else if(initConf.initValue == "9999") {
    			dataArray[0] = {CODE:" ", NAME:resource.getMessage("ALL")};
    			initConf.initValue = " ";
    		}
    		
    		initConf.url = "";
    		initConf.data = dataArray;
    		if(count > 8) initConf.panelHeight = 160;
    		initConf.valueField = "CODE";
    		initConf.textField = "NAME";
    	} else if(initConf.type == "month") {
    		var dataArray = new Array();
    		var toMonth = calendar.util.toMonth2Date();
    		
    		for(var i = 1; i < 13; i++) {
    			dataArray[i-1] = {CODE:i, NAME:i};
    		}
    		
    		if(oUtil.isNull(initConf.initValue)) {
    			initConf.initValue = toMonth;
    		}
    		initConf.url = "";
    		initConf.data = dataArray;
    		initConf.valueField = "CODE";
    		initConf.textField = "NAME";
    	} else if(initConf.type == "day") {
    		var dataArray = new Array();
    		var todate = new Date();
    		var toDay = todate.getDate();
    		
    		for(var i = 1; i < 32; i++) {
    			dataArray[i-1] = {CODE:i, NAME:i};
    		}
    		
    		if(oUtil.isNull(initConf.initValue)) {
    			initConf.initValue = toDay;
    		}
    		initConf.url = "";
    		initConf.data = dataArray;
    		initConf.valueField = "CODE";
    		initConf.textField = "NAME";
    	}
    	
    	if(oUtil.isNull(initConf.editable)) initConf.editable = false;
	    if(oUtil.isNull(initConf.multiple)) initConf.multiple = false;
	    if(oUtil.isNull(initConf.method)) initConf.method = "post";
	    if(oUtil.isNull(initConf.panelHeight)) initConf.panelHeight = "auto";
	    if(oUtil.isNull(initConf.panelWidth)) initConf.panelWidth = null;
	    if(oUtil.isNull(initConf.required)) initConf.required = false;
	    if(oUtil.isNull(initConf.highlight)) initConf.highlight = 16;
	    if(oUtil.isNull(initConf.comboType)) initConf.comboType = "combobox";
	    if(initConf.required) {
		    if(oUtil.isNull(comboObj.attr("validType"))) {
		    	comboObj.attr("validType", "\"notEmpty['"+fid+"','"+eid+"']\"");
		    	initConf.required = false;
			}
	    }
	    
	    combo.event.setOnLoadSuccess(comboObj);
	    combo.event.setOnChange(comboObj);
	    combo.event.setOnShowPanel(comboObj);
	    combo.event.setOnHidePanel(comboObj);
	    
	    if(initConf.comboType == "combotree") {
	    	comboObj.combotree(combo.getConfig(fid, eid));
    	} else {
    		comboObj.combobox(combo.getConfig(fid, eid));
    	}
    };
    
};

var calendarObject = function(){
	
	/**
	 * form내 달력 속성을 관리하고 요청 ID에 해당하는 객체를 리턴
	 * 
	 * @param fid = form ID:String:필수
	 * @param eId = calendar id:필수
	 */
	this.getObject = function(fid, eid) {
		var cid = fid + "." + eid;
		var exsitsInit = dataset.isArrayObject(cid + ".init");
		var exsitsEvent = dataset.isArrayObject(cid + ".event");
		
		calendar.id = cid;
		
		if(!exsitsInit) {
			calendar.init.config = new Object(cid +".init");
			dataset.arrayPush(calendar.init.config);
		}
		
		if(!exsitsEvent) {
			calendar.event.config = new Object(cid +".event");
			dataset.arrayPush(calendar.event.config);
		}
		
		return $("#" + fid).find("#" + eid);
	};
	
	/**
	 * form내 달력 속성를 구하는 함수
	 * 
	 * @param fid = form ID
	 * @param eId = calendar id
	 */
    this.getConfig = function(fid, eid) {
    	var cid = fid + "." + eid;
    	
    	var conf = {};
    	var conf1 = dataset.arrayFilter(cid +".init");
    	var conf2 = dataset.arrayFilter(cid +".event");
    	
    	$.extend(conf, conf1, conf2);
    	
    	return conf;
    };
    
	/**
	 * form내 달력 속성의 초기설정값을 구하는 함수
	 * 
	 * @param fid = form ID
	 * @param eId = calendar id
	 */
    this.getInitConfig = function(fid, eid) {
    	var cid = fid + "." + eid;
    	
    	return dataset.arrayFilter(cid +".init");
    };
    
    /**
	 * form내 달력 속성의 이벤트 설정값을 구하는 함수
	 * 
	 * @param fid = form ID
	 * @param eId = calendar id
	 */
    this.getEventConfig = function(fid, eid) {
    	var cid = fid + "." + eid;
    	
    	return dataset.arrayFilter(cid +".event");
    };
    
    /**
     * form내 달력을 생성
     * 
     * @param obj = calendar object
     * @param fid = form id(선택)
     */
    this.create = function(obj, fid) {
    	var fid = (typeof obj == "object") ? obj.parents("#"+obj.attr("id") + ",form").attr("id") : fid;
    	var eid = (typeof obj == "object") ? obj.attr("id") : obj;
    	
    	// 달력 오브젝트 생성
    	var calendarObj = calendar.getObject(fid, eid);
    	var initConf = calendar.getInitConfig(fid, eid);
    	
    	if(oUtil.isNull(initConf.editable)) initConf.editable = true;
    	if(oUtil.isNull(initConf.seperator)) initConf.seperator = SEPERATOR_OF_DATE;
    	if(oUtil.isNull(initConf.nation)) initConf.nation = NATION_DATE_VIEW;
    	if(oUtil.isNull(initConf.required)) initConf.required = true;
    	if(oUtil.isNull(initConf.dateType)) initConf.dateType = "date";
    	if(oUtil.isNull(initConf.readonly)) initConf.readonly = false;
	    calendar.init.setParser(fid, eid);
    	calendar.init.setFormatter(fid, eid);
    	// 기본 이벤트 설정
    	calendar.event.setOnSelect(fid, eid);
    	
    	if(initConf.dateType == "month") {
    		if(oUtil.isNull(calendarObj.attr("validType"))) {
    			calendarObj.attr("validType", "'month'");
    		}
    		
    		if(initConf.nation == "MX" || initConf.nation == "US") {
    			calendar.init.setHighlight(0);
    		} else {
    			calendar.init.setHighlight(1);
    		}
    		
    		// 월달력에 데이터가 있으면 초기값으로 적용함
    		if(oUtil.isNull(initConf.value)) {
    			var fdate = form.handle.getValue(fid, eid);
    			
    			if(!oUtil.isNull(fdate)) {
    				initConf.value = calendar.util.getMonth2String(fdate, "");
    			} else {
    				initConf.value = calendar.util.toMonth2String();
    			}
    		}
    		
    		calendarObj.datetimespinner(calendar.getConfig(fid, eid), initConf.readonly);
    		
    		if(!oUtil.isNull(initConf.value)) {
	    		var fdate = calendar.handle.getDate(fid, eid);
	    		
	    		form.handle.setValue(fid, replaceAll(eid, "_CAL", ""), fdate);
    		}
    	} else {
    		if(oUtil.isNull(calendarObj.attr("validType"))) {
    			calendarObj.attr("validType", "'date'");
    		}
    		
    		var idate = form.handle.getValue(fid, eid);
    		
		    if(initConf.initDate == "none" && oUtil.isNull(idate)) {
		    	initConf.value = "";
		    	calendarObj.datebox(calendar.getConfig(fid, eid));
		    	
		    	return;
		    } else {
		    	if(initConf.initDate == "none") initConf.initDate = "";
		    	
		    	calendarObj.datebox(calendar.getConfig(fid, eid));
		    }
		    
		    if(oUtil.isNull(initConf.initDate)) {
		    	if(oUtil.isNull(idate)) {
		    		idate = calendar.util.toDate2String();
		    	} else {
		    		idate = calendar.util.getDate2String(idate, "-");
		    	}
		    	
		    	calendar.handle.setDate(fid, eid, idate);
		    } else {
		    	calendar.handle.setDate(fid, eid, initConf.initDate);
		    }
    	}
    };
    
};

var tooltipObject = function(){
	
	/**
	 * ToolTip 속성을 관리하고 요청 ID에 해당하는 객체를 리턴
	 * 
	 * @param tid = tooltip ID:String:필수
	 */
	this.getObject = function(tid) {
		var exsitsInit = dataset.isArrayObject(tid + ".init");
		var exsitsEvent = dataset.isArrayObject(tid + ".event");
		
		tooltip.id = tid;
		
		if(!exsitsInit) {
			calendar.init.config = new Object(tid +".init");
			dataset.arrayPush(calendar.init.config);
		}
		
		if(!exsitsEvent) {
			calendar.event.config = new Object(tid +".event");
			dataset.arrayPush(calendar.event.config);
		}
		
		return $("#" + tid);
	};
	
	/**
	 * ToolTip 속성를 구하는 함수
	 * 
	 * @param tid = tooltip ID:String:필수
	 */
    this.getConfig = function(tid) {
    	var conf = {};
    	var conf1 = dataset.arrayFilter(tid +".init");
    	var conf2 = dataset.arrayFilter(tid +".event");
    	
    	$.extend(conf, conf1, conf2);
    	
    	return conf;
    };
    
	/**
	 * ToolTip 속성의 초기설정값을 구하는 함수
	 * 
	 * @param tid = tooltip ID:String:필수
	 */
    this.getInitConfig = function(tid) {
    	return dataset.arrayFilter(tid +".init");
    };
    
    /**
	 * ToolTip 속성의 이벤트 설정값을 구하는 함수
	 * 
	 * @param tid = tooltip ID:String:필수
	 */
    this.getEventConfig = function(tid) {
    	return dataset.arrayFilter(tid +".event");
    };
    
    /**
     * ToolTip 생성
     * 
     * @param obj = tooltip Object
     */
    this.create = function(obj) {
    	var tid = (typeof obj == "object") ? obj.attr("id") : obj;
    	
    	var tooltipObj = tooltip.getObject(tid);
    	var initConf = tooltip.getInitConfig(tid);
    	
    	if(oUtil.isNull(initConf.content)) initConf.content = $('<div></div>');
    	if(oUtil.isNull(initConf.showEvent)) initConf.showEvent = "click";
    	// 내용 설정정보
    	if(oUtil.isNull(initConf.width)) initConf.width = 200;
    	if(oUtil.isNull(initConf.border)) initConf.border = false;
    	if(oUtil.isNull(initConf.title)) initConf.title = "";
    	if(oUtil.isNull(initConf.pid)) initConf.pid = "MMA001_02";
    	if(oUtil.isNull(initConf.showCallbackFunction)) initConf.showCallbackFunction = "";
    	
    	if(!oUtil.isNull(initConf.url)) {
    		tooltip.event.onUpdate(initConf);
    	} else {
    		tooltip.event.onContent(initConf);
    	}
    	tooltip.event.onShow(tooltipObj);
    	
    	tooltipObj.tooltip(tooltip.getConfig(tid));
    };
    
};

var tabObject = function(){
	
	/**
	 * tab 속성을 관리하고 요청 ID에 해당하는 객체를 리턴
	 * 
	 * @param tid = tab ID:String:필수
	 */
	this.getObject = function(tid) {
		var exsitsInit = dataset.isArrayObject(tid + ".init");
		var exsitsEvent = dataset.isArrayObject(tid + ".event");
		
		tab.id = tid;
		
		if(!exsitsInit) {
			calendar.init.config = new Object(tid +".init");
			dataset.arrayPush(calendar.init.config);
		}
		
		if(!exsitsEvent) {
			calendar.event.config = new Object(tid +".event");
			dataset.arrayPush(calendar.event.config);
		}
		
		return $("#" + tid);
	};
	
	/**
	 * tab 속성를 구하는 함수
	 * 
	 * @param tid = tab ID:String:필수
	 */
    this.getConfig = function(tid) {
    	var conf = {};
    	var conf1 = dataset.arrayFilter(tid +".init");
    	var conf2 = dataset.arrayFilter(tid +".event");
    	
    	$.extend(conf, conf1, conf2);
    	
    	return conf;
    };
    
	/**
	 * tab 속성의 초기설정값을 구하는 함수
	 * 
	 * @param tid = tab ID:String:필수
	 */
    this.getInitConfig = function(tid) {
    	return dataset.arrayFilter(tid +".init");
    };
    
    /**
	 * tab 속성의 이벤트 설정값을 구하는 함수
	 * 
	 * @param tid = tab ID:String:필수
	 */
    this.getEventConfig = function(tid) {
    	return dataset.arrayFilter(tid +".event");
    };
    
    /**
     * tab 생성
     * 
     * @param obj = tab Object
     */
    this.create = function(obj) {
    	var tid = (typeof obj == "object") ? obj.attr("id") : obj;
    	
    	var tabObj = tab.getObject(tid);
    	var initConf = tab.getInitConfig(tid);
    	
    	if(oUtil.isNull(initConf.fit)) initConf.fit = true;
    	if(oUtil.isNull(initConf.border)) initConf.border = false;
    	if(oUtil.isNull(initConf.plain)) initConf.plain = true;
    	
    	tab.event.setOnLoad(tabObj);
    	tab.event.setOnSelect(tabObj);
    	tab.event.setOnAdd(tabObj);
    	tab.event.setOnUpdate(tabObj);
    	tab.event.setOnContextMenu(tabObj);
    	
    	tabObj.tabs(tab.getConfig(tid));
    };
    
};

var treeObject = function(){
	
	/**
	 * tree 속성을 관리하고 요청 ID에 해당하는 객체를 리턴
	 * 
	 * @param tid = tree ID:String:필수
	 */
	this.getObject = function(tid) {
		var exsitsInit = dataset.isArrayObject(tid + ".init");
		var exsitsEvent = dataset.isArrayObject(tid + ".event");
		
		tree.id = tid;
		
		if(!exsitsInit) {
			calendar.init.config = new Object(tid +".init");
			dataset.arrayPush(calendar.init.config);
		}
		
		if(!exsitsEvent) {
			calendar.event.config = new Object(tid +".event");
			dataset.arrayPush(calendar.event.config);
		}
		
		return $("#" + tid);
	};
	
	/**
	 * tree 속성를 구하는 함수
	 * 
	 * @param tid = tree ID:String:필수
	 */
    this.getConfig = function(tid) {
    	var conf = {};
    	var conf1 = dataset.arrayFilter(tid +".init");
    	var conf2 = dataset.arrayFilter(tid +".event");
    	
    	$.extend(conf, conf1, conf2);
    	
    	return conf;
    };
    
	/**
	 * tree 속성의 초기설정값을 구하는 함수
	 * 
	 * @param tid = tree ID:String:필수
	 */
    this.getInitConfig = function(tid) {
    	return dataset.arrayFilter(tid +".init");
    };
    
    /**
	 * tree 속성의 이벤트 설정값을 구하는 함수
	 * 
	 * @param tid = tree ID:String:필수
	 */
    this.getEventConfig = function(tid) {
    	return dataset.arrayFilter(tid +".event");
    };
    
    /**
     * tree 생성
     * 
     * @param obj = tree Object
     */
    this.create = function(obj) {
    	var tid = (typeof obj == "object") ? obj.attr("id") : obj;
    	var treeObj = tree.getObject(tid);
    	var initConf = tree.getInitConfig(tid);
    	
    	if(oUtil.isNull(initConf.lines)) initConf.lines = true;

    	tree.event.setOnClick(treeObj);
    	tree.event.setOnLoadSuccess(treeObj);
    	
    	treeObj.tree(tree.getConfig(tid));
    };
    
};

gridObject.prototype = gridMethod; // gridMethod를 부모로 하는 gridObject 생성하여 반환함
var grid = new gridObject();

formObject.prototype = formMethod; 
var form = new formObject();

dialogObject.prototype = dialogMethod; 
var dialog = new dialogObject();

comboObject.prototype = comboMethod; 
var combo = new comboObject();

calendarObject.prototype = calendarMethod; 
var calendar = new calendarObject();

tooltipObject.prototype = tooltipMethod; 
var tooltip = new tooltipObject();

tabObject.prototype = tabMethod; 
var tab = new tabObject();

treeObject.prototype = treeMethod; 
var tree = new treeObject();