/******************************************************************************************
 * 작성자 : 이재욱
 * Program Id : MM-A004_01
 * 작업일자 : 2016.03.28
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMA004_01.init.initComponent();
}

var MMA004_01 = {
	    
		// 초기값 설정
	    init : {
	    	initComponent : function() {
	    		var pid = form.handle.getValue("MMA004_01_form_01", "TARGET_PID");
	    		
	    		MMA004_01.calendar.initCalendar_1();
	    		MMA004_01.calendar.initCalendar_2();
	    		
	    		MMA004_01.combobox.initCombo_1();
	    		if(pid == "MMA304_01") {
	    			MMA004_01.combobox.initCombo_2();
	    		}
	    		
	    		MMA004_01.datagrid.initGrid_1();
	    		MMA004_01.control.selectInvoiceInfo();
	    	}
	    },
	    calendar : {
			initCalendar_1 : function() {
				var ddate = form.handle.getValue("MMA004_01_form_01", "SHIPPING_DATE");
				var obj_01 = calendar.getObject("MMA004_01_form_01", "SHIPPING_DATE");
				
				if(!oUtil.isNull(date)) {
					var date = calendar.util.getDate2String(ddate);
					calendar.init.setInitDate(date);
				} else {
					calendar.init.setInitDate("none");
				}
				calendar.init.setRequired(true);
        		
				calendar.create(obj_01);
			},
			initCalendar_2 : function() {
				var ddate = form.handle.getValue("MMA004_01_form_01", "EXPORT_DECLARE_DATE");
				var obj_02 = calendar.getObject("MMA004_01_form_01", "EXPORT_DECLARE_DATE");
				
				if(!oUtil.isNull(date)) {
					var date = calendar.util.getDate2String(ddate);
					calendar.init.setInitDate(date);
				} else {
					calendar.init.setInitDate("none");
				}
				calendar.init.setRequired(false);
				
				calendar.create(obj_02);
			},
	    }, 
	    combobox : {
	    	initCombo_1 : function() {
	            var obj = combo.getObject("MMA004_01_form_01", "VEHICLE_TYPE");
	            
	            combo.init.setURL("/mm/cbox/selectStandardCode");
	            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"OTT", MESSAGE_CODE:"TXT_NO_SELECT"});
	            combo.init.setValueField("CODE");
	            combo.init.setNameField("NAME");
	            
	            combo.create(obj);
	        },
	        initCombo_2 : function() {
	        	var data = MMA304_01.util.getInvoiceList("json");
	        	var obj = combo.getObject("MMA004_01_form_01", "INVOICE_NO");
	            
	        	combo.init.setData(eval(data));
	            combo.init.setValueField("CODE");
	            combo.init.setNameField("NAME");
	            combo.init.setCallFunction("changeInvoiceCombo");
	            combo.init.setRequired(true);
	            
	            combo.create(obj);
	        }
	    },
	    datagrid : {
	    	header1_1 : [
	            ["INV_NO", resource.getMessage("PL_INVOICE_NO") + " : <span id='MMA004_01_gh_01'>" + form.handle.getValue("MMA004_01_form_01", "INVOICE_NO")+"</span>", 100, "center", "center", true, false, null, null, null, 0, 7]
	    	],
	        header1_2 : [
	            ["CUSTOMER_ITEM_NAME",     	resource.getMessage("PL_PNAME")    			, 280, "left"  , "center", true, false, null, null, null],
	            ["CUSTOMER_ITEM_CD"	,       resource.getMessage("PL_PNUMBER") 			, 220, "center", "center", true, false,  null, null, null],
				["SALES_QTY",       		resource.getMessage("PL_QTY")          		, 130, "right", "center", true, false, null, null, {format:"quantity"}],
	            ["SE_UNIT_PRICE",		    resource.getMessage("PL_PRICE", [form.handle.getValue("MMA004_01_form_01", "CURRENCY")]), 130, "right", "center", true, false, null, null, {format:"amount"}],
			    ["SE_AMOUNT",        	    resource.getMessage("PL_AMOUNT", [form.handle.getValue("MMA004_01_form_01", "CURRENCY")]) , 130, "right", "center", true, false, null, null, {format:"amount"}],
	            ["HS_CODE",      			resource.getMessage("TXT_STAWN")      		, 120, "center", "center", true, false, null, null, null],
	            ["ISSUE_ORIGIN_YN", 		resource.getMessage("PL_ORIGIN")  			, 120, "center", "center", true, false, null, null, null]
	        ],
	        initGrid_1 : function() {
	        	var params = form.handle.getElementsParam("MMA004_01_form_01");
	        	var dg_1 = grid.getObject("MMA004_01_grid_01");
	        	
	        	grid.init.setQueryParams(params);
	        	grid.init.setURL("/mm/pop/mmA204_01/selectDeterminateList");
	        	grid.init.setPage(true);
                grid.init.setShowConfigPage(true);
                grid.init.setPageLayout(["resizeColumn"]);
	        	grid.init.setFitColumns(true);
	            
	        	Theme.defaultGrid(dg_1, this.header1_1, this.header1_2);
	        }
	    },
	    event : {
	    	selectAfterUI : function(data) {
	    		form.handle.loadData("MMA004_01_form_01", data);
	    		
	    		MMA004_01.ui.updateChangeUI();
	    	},
	    	updateAfterUI : function(data) {
	    		var pid = form.handle.getValue("MMA004_01_form_01", "TARGET_PID");
	    		var allData = form.handle.getElementsParam("MMA004_01_form_01");
	    		
	    		if(pid == "MMA304_01") {
	    			form.handle.loadData("MMA304_01_form_01", allData);
	    		} else if(pid == "MMA204_01") {
	    			form.handle.loadData("MMA204_01_form_01", allData);
	    		}
	    	},
	    	changeInvoiceCombo : function(data) {
	    		$("#MMA004_01_gh_01").html(data);
	    		
	    		MMA004_01.control.selectInvoiceInfo();
	    		MMA004_01.control.selectDeterminateList();
	    	}
	    },
	    control : {// 업무구현
	    	selectInvoiceInfo : function() {
	    		form.init.setURL("/mm/pop/mmA004_02/selectInvoiceInfo");
	    		var obj = form.getObject("MMA004_01_form_01");
	    		
	    		form.init.setCallBackFunction("selectAfterUI");
	    		form.init.setValidationFlag(false);
	    		form.init.setProgressFlag(false);
	    		
	    		form.submit(obj);
	    	},
	    	selectDeterminateList : function() {
	     		var params = form.handle.getElementsParam("MMA004_01_form_01");
	     		var dg_1 = grid.getObject("MMA004_01_grid_01");
	     		
	   			grid.handle.sendRedirect(dg_1, "/mm/pop/mmA204_01/selectDeterminateList", params);
	     	},
	    	updateInvoiceInfo : function() {
	    		if(!form.handle.isValidate("MMA004_01_form_01")) return;
	    		
	    		$.messager.confirm(CNFIR, resource.getMessage("MSG_CONFIRM_SAVE"), function(flag) {
	                if(flag) {
	                	var obj = form.getObject("MMA004_01_form_01");
	    	    		
    	    			form.init.setURL("/mm/pop/mmA004_02/updateInvoiceInfo");

    	    			form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
	    	    		form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
	    	    		form.init.setCallBackFunction("updateAfterUI");
	    	    		
	    	    		form.submit(obj);
	                }
	            });
	    	}
	    },
	    dialog : {
	    	openDialog_1 : function() {
	    		var dl_1 = dialog.getObject("MMA008_01_dailog_01");
	    		
	    		dialog.init.setWidth(600);
	    		dialog.init.setHeight(400);
	    		dialog.init.setURL("/mm/pop/mmA008_01");
	    		dialog.init.setQueryParams({TARGET_PID:"MMA004_01", COMPANY_CD:SESSION.COMPANY_CD});
	    		dialog.init.setTitle(resource.getMessage("MMA008_01"));
	    		dialog.open(dl_1);
	    	},
	    	callByMMA008_01 : function(datas) {
	    		var dl_1 = dialog.getObject("MMA008_01_dailog_01");
	    		
	    		form.handle.setValue("MMA004_01_form_01", "FINAL_DESTINATION", datas.CODE);
	    		form.handle.setValue("MMA004_01_form_01", "FINAL_DESTINATION_NAME", datas.CODE_NAME);
	    		
	    		// 창을 닫는다.
	    		dialog.handle.close(dl_1);
	    	},
	    	openDialog_2 : function(type) {
	    		this.portType = type;
	    		
	      		var dg_1 = dialog.getObject("MMA015_02_01_dailog_01");
	      		
	      		dialog.init.setWidth(800);
	      		dialog.init.setHeight(450);
	      		dialog.init.setURL("/mm/pop/mmA015_02");
	      		dialog.init.setQueryParams({TARGET_PID:"MMA004_01", COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"OPOS"});
	      		
	    		dialog.open(dg_1);
	    	},
	    	callByMMA015_02 : function(datas) {
	    		var dg_1 = dialog.getObject("MMA015_02_01_dailog_01");
	    		
	    		if(this.portType == "P1") {
		    		form.handle.setValue("MMA004_01_form_01", "LOADING_PORT", datas.CODE);
		    		form.handle.setValue("MMA004_01_form_01", "LOADING_PORT_NAME", datas.CODE_NAME_ENG);
	    		} else if(this.portType == "P2") {
		    		form.handle.setValue("MMA004_01_form_01", "DISCHARGE_PORT", datas.CODE);
		    		form.handle.setValue("MMA004_01_form_01", "DISCHARGE_PORT_NAME", datas.CODE_NAME_ENG);
	    		} else if(this.portType == "P3") {
		    		form.handle.setValue("MMA004_01_form_01", "FINAL_DEST_PORT", datas.CODE);
		    		form.handle.setValue("MMA004_01_form_01", "FINAL_DEST_PORT_NAME", datas.CODE_NAME_ENG);
	    		}
	    		
	    		dialog.handle.close(dg_1);
	    	}
	    },
	    ui : {
	    	updateChangeUI : function() {
	        	//읽기만 가능하도록 변경
	        	form.handle.readonly("MMA004_01_form_01", "INVOICE_NO", true);
	        	form.handle.addClass("MMA004_01_form_01", "INVOICE_NO", "input_readOnly");
	    	}
	    },
	    file : {},
	    excel : {
	    	excelDownload_1 : function() {
	        	 var fobj = form.getObject("MMA004_01_form_01");
	        	 var dg_1 = grid.getObject("MMA004_01_grid_01");
	        	 
	        	 form.init.setURL("/mm/pop/mmA204_01/selectDeterminateList.fle");
	             
	             form.excelSubmit(dg_1, fobj, "Invoice no("+form.handle.getValue("MMA004_01_form_01", "INVOICE_NO")+")", "Packing List");
	         }
	    }
	    
	};