/******************************************************************************************
 * 작성자 : carlos
 * Program Id : MM-A031_03
 * 작업일자 : 2020.01.29
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMA031_03.config.applyFtaNation();
    MMA031_03.init.initComponent();
}

SESSION = {TRANSACTION_ID : oUtil.getUID()};

var MMA031_03 = {
    init : {
        initComponent : function() {
        	MMA031_03.calendar.initCalendar_1();
        	MMA031_03.calendar.initCalendar_2();
        	
        	MMA031_03.combobox.initCombo_1();
        	MMA031_03.combobox.initCombo_2();
        	MMA031_03.combobox.initCombo_3();
        	
            MMA031_03.datagrid.initGrid_1();
            MMA031_03.datagrid.initGrid_2();
            MMA031_03.datagrid.initGrid_3();
        }
    }, // 초기값 설정
    calendar : {
    	initCalendar_1 : function() {
        	var yyyymm = calendarMethod.util.toMonth2String();
            var obj_01 = calendar.getObject("MMA031_03_form_01", "SCH_FROM_DATE");
            
            calendar.init.setInitDate(calendar.util.getFirstDay2String(yyyymm, "-"));
            
            calendar.create(obj_01);
        },
        initCalendar_2 : function() {
        	var yyyymm = calendarMethod.util.toMonth2String();
            var obj_01 = calendar.getObject("MMA031_03_form_01", "SCH_TO_DATE");
            
            calendar.init.setInitDate(calendar.util.getLastDay2String(yyyymm, "-"));
            
            calendar.create(obj_01);
        }
    }, // 달력 그리드 생성
    combobox : {
    	data_1 : [
        	{CODE:"TC", NAME:resource.getMessage("TITLE")+"+"+resource.getMessage("CONTS")},
        	{CODE:"T", NAME:resource.getMessage("TITLE")},
        	{CODE:"C", NAME:resource.getMessage("CONTS")}
       	],
    	initCombo_1 : function() {
            var obj = combo.getObject("MMA031_03_form_01", "STATUS");

            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:'TOMS', CATEGORY_CD:"INQUIRY_STATUS", ALL:"Y"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");

            combo.create(obj);
        },
        initCombo_2 : function() {
            var obj = combo.getObject("MMA031_03_form_01", "schKeyLike");

            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:'TOMS', CATEGORY_CD:"LIKE"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(obj);
        },
        initCombo_3 : function() {
            var obj = combo.getObject("MMA031_03_form_01", "schKeyField");

            combo.init.setData(this.data_1);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(obj);
        }
    },
    datagrid : {
		header_1 : [
			["INQUIRY_TITLE"		,resource.getMessage("TITLE")			,350    ,"left"     ,"center"    ,true       ,false    	,null   ,null     ,null,  2, 0],
			["INQUIRY_DATE"			,resource.getMessage("INQUI,DATE")		,130    ,"center"   ,"center"    ,true       ,false    	,null   ,null     ,null,  2, 0],
			["INQUIRY_RESPONSE_NAME",resource.getMessage("CNSLT_METHD")		,100    ,"center"   ,"center"    ,true       ,false		,null   ,null     ,null,  2, 0],
			["STATUS_NAME"          ,resource.getMessage("TXT_RESLT_STAT")	,110    ,"center"   ,"center"    ,true       ,false    	,null   ,null     ,null,  2, 0],
			["RECEIPT_INFO"			,resource.getMessage("RCPT,INFMT")		,400    ,"center"   ,"center"    ,true       ,false    	,null   ,null     ,null,  0, 4],
			["ANSW_CONTENTS"	    ,resource.getMessage("ANSWR,INFMT")	    ,400    ,"center"   ,"center"    ,true       ,false  	,null   ,null     ,null,  0, 2]
	    ],
	    header_1_1 : [
			["RECEIPT_DATE"			,resource.getMessage("RCPT,DATE")		,130    ,"center"   ,"center"    ,true       ,false    	,null   ,null     ,null],
			["ANSWER_NAME"			,resource.getMessage("RCPTN")			,100    ,"center"   ,"center"    ,true       ,false    	,null   ,null     ,null],
			["ANSWER_PHONE"	        ,resource.getMessage("CNTACT")			,150    ,"center"   ,"center"    ,true       ,false    	,null   ,null     ,null],
			["ANSWER_EMAIL"		    ,resource.getMessage("TXT_EMAIL")		,150    ,"center"   ,"center"    ,true       ,false    	,null   ,null     ,null],
			["SEND_DATE"		    ,resource.getMessage("ANSWR,DATE")		,130    ,"center"   ,"center"    ,true       ,false    	,null   ,null     ,null],
			["SEND_CONTENTS"	    ,resource.getMessage("ANSWR,CONTS")	    ,500    ,"left"     ,"center"    ,true       ,false  	,null   ,null     ,null]
	    ],
	    header_2 : [
	    	["SUBJECT"              ,resource.getMessage("TITLE") 	        ,300    ,"left"     ,"center"    ,true       ,false  	,null   ,null     ,{format:"function", pid:"MMA031_03", name:"showNewIcon"}],
        	["CREATE_DATE"          ,resource.getMessage("TXT_CREATE_DAY")  ,100    ,"center"   ,"center"    ,true       ,false  	,null   ,null     ,null]
	    ],
	    header_3 : [
	    	["SUBJECT"              ,resource.getMessage("TITLE") 	        ,300    ,"left"     ,"center"    ,true       ,false  	,null   ,null     ,{format:"function", pid:"MMA031_03", name:"showNewIcon"}],
        	["CREATE_DATE"          ,resource.getMessage("TXT_CREATE_DAY")  ,100    ,"center"   ,"center"    ,true       ,false  	,null   ,null     ,null]
	    ],
        initGrid_1 : function() {
        	var params = form.handle.getElementsParam("MMA031_03_form_01");
        	var dg_1 = grid.getObject("MMA031_03_grid_01");
            
        	grid.init.setURL("/mm/noses/mmA031_01/selectInquiryEmailRecordList");
        	grid.init.setQueryParams(params);
            grid.init.setPage(true);
            grid.init.setFitColumns(false);
            grid.init.setFilter(false);
            grid.init.setEmptyMessage(false);
            
            grid.event.setOnDblClickRow(dg_1);
            
            Theme.defaultGrid(dg_1, this.header_1, this.header_1_1);
        },
        initGrid_2 : function() {
        	var params = form.handle.getElementsParam("MMA031_03_form_03");
        	
        	if(params.CERTIFY_TYPE == "internal") {
        		params.BBS_TYPE = '1';
        		form.handle.setValue("MMA031_03_form_03", "BBS_TYPE", '1');
        	} else if(params.CERTIFY_TYPE == "external") {
        		params.BBS_TYPE = '2';
        		form.handle.setValue("MMA031_03_form_03", "BBS_TYPE", '2');
        	}
        	
            var dg_1 = grid.getObject("MMA031_03_grid_02");
            
            grid.init.setQueryParams(params);
            grid.init.setURL("/mm/noses/mmA031_01/selectSystemNoticeList");
            grid.init.setPage(true);
            grid.init.setFitColumns(true);
            grid.init.setFilter(false);
            grid.init.setEmptyMessage(false);
            
            grid.event.setOnClickRow(dg_1);
            
            Theme.defaultGrid(dg_1, this.header_2);
        },
        initGrid_3 : function() {
        	var params = form.handle.getElementsParam("MMA031_03_form_04");
            var dg_1 = grid.getObject("MMA031_03_grid_03");
            
            grid.init.setQueryParams(params);
            grid.init.setURL("/mm/noses/mmA031_01/selectSystemNoticeList");
            grid.init.setPage(true);
            grid.init.setFitColumns(true);
            grid.init.setFilter(false);
            grid.init.setEmptyMessage(false);

            grid.event.setOnClickRow(dg_1);

            Theme.defaultGrid(dg_1, this.header_3);
        }
    },
    event : { // 이벤트 처리
        onDblClick_MMA031_03_grid_01 : function(rowData) {
        	if(rowData.STATUS == "3") {
        		MMA031_03.dialog.openDialog_1("view");
        	} else {
        		MMA031_03.dialog.openDialog_1("update");
        	}
        },
        onClick_MMA031_03_grid_02 : function(rowData) {
        	$("#MMA031_03_html_01").html(rowData.CONTENT1);
        },
        onClick_MMA031_03_grid_03 : function(rowData) {
        	$("#MMA031_03_html_01").html(rowData.CONTENT1);
        },
        deleteInquiryEmailRecord : function() {
        	MMA031_03.control.selectMainList();
        }
    },
    control : {// 업무구현
        selectMainList : function() {
            var dg_1 = grid.getObject("MMA031_03_grid_01");
            var params = form.handle.getElementsParam("MMA031_03_form_01");
            
            grid.handle.sendRedirect(dg_1, "/mm/noses/mmA031_01/selectInquiryEmailRecordList", params);
        },
        deleteInquiryEmailRecord : function() {
        	var dg_1 = grid.getObject("MMA031_03_grid_01");
        	
        	if (!grid.handle.isSelected(dg_1)) {
                alert(resource.getMessage("MSG_NO_SELECT"));
                return;
            }
        	
        	row = grid.handle.getSelectedRowData(dg_1);
        	
        	if(row.STATUS == "3") {
            	alert(resource.getMessage("MSG_REJECT_HANDLE"));
            	return;
            }
        	
        	$.messager.confirm(CNFIR, resource.getMessage("MSG_CONFIRM_DELETE"), function(flag) {
                if (flag) {
		        	form.handle.setValue("MMA031_03_form_02", "INQUIRY_NO", row.INQUIRY_NO);
		        	
		    		var obj = form.getObject("MMA031_03_form_02");
		    		
		    		form.init.setURL("/mm/noses/mmA031_01/deleteInquiryEmailRecord");
		    		form.init.setProgressFlag(true);
		    		form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
		            form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
		            form.init.setCallBackFunction("deleteInquiryEmailRecord");
		            
		            form.submit(obj);
                }
            });
    	},
    	selectSystemNoticeList : function() {
            var dg_1 = grid.getObject("MMA031_03_grid_02");
            var params = form.handle.getElementsParam("MMA031_03_form_03");
            
            grid.handle.sendRedirect(dg_1, "/mm/noses/mmA031_01/selectSystemNoticeList", params);
        },
        selectSystemUpdateList : function() {
            var dg_1 = grid.getObject("MMA031_03_grid_03");
            var params = form.handle.getElementsParam("MMA031_03_form_04");
            
            grid.handle.sendRedirect(dg_1, "/mm/noses/mmA031_01/selectSystemNoticeList", params);
        }
    },
    dialog : {
        openDialog_1 : function(flag) {
        	var dialogType = "1";
        	var dg_1 = grid.getObject("MMA031_03_grid_01");
        	var params = new Object();
            
            if (flag == "update" && !grid.handle.isSelected(dg_1)) {
                alert(resource.getMessage("MSG_NO_SELECT"));
                return;
            }
            
            // 파라메터 지정
            if(flag == "insert") {
                params = form.handle.getElementsParam("MMA031_03_form_01");
            } else if(flag == "update") {
                params = grid.handle.getSelectedRowData(dg_1);
                
                if(params.STATUS == "3") {
                	alert(resource.getMessage("MSG_REJECT_HANDLE"));
                	return;
                }
            } else if(flag == "view") {
                params = grid.handle.getSelectedRowData(dg_1);
                dialogType = "2";
            }
            params.flag = flag;
            
            if(dialogType == "1") {
            	this.openDdlDialog(params);
            } else {
            	this.openViewDialog(params);
            }
        },
        openDdlDialog : function(params) {
        	var dg_1 = dialog.getObject("MMA031_04_dailog_01");
            
            dialog.init.setURL("/mm/noses/mmA031_04");
    		dialog.init.setQueryParams(params);
            dialog.init.setWidth(850);
    		dialog.init.setHeight(765);
    	    
            dialog.open(dg_1);
        },
        openViewDialog : function(params) {
        	var dg_1 = dialog.getObject("MMA031_04_dailog_01");
            
            dialog.init.setURL("/mm/noses/mmA031_04");
    		dialog.init.setQueryParams(params);
            dialog.init.setWidth(850);
    		dialog.init.setHeight(765);
    	    
            dialog.open(dg_1);
        },
        openDialog_2 : function() {
        	var dg_1 = grid.getObject("MMA031_03_grid_01");
        	var params = new Object();
            
            if (!grid.handle.isSelected(dg_1)) {
                alert(resource.getMessage("MSG_NO_SELECT"));
                return;
            }
            
            params = grid.handle.getSelectedRowData(dg_1);
            
            if(params.STATUS != "3") {
            	alert(resource.getMessage("MSG_HANDLE_AFTER"));
            	return;
            }
            
            var dg_1 = dialog.getObject("MMA031_05_dailog_01");
            
            dialog.init.setURL("/mm/noses/mmA031_05");
    		dialog.init.setQueryParams(params);
            dialog.init.setWidth(850);
    		dialog.init.setHeight(765);
    	    
            dialog.open(dg_1);
        }
    },
    ui : {
    	divMove : function(idx) {
            var obj = tab.getObject("MMA031_03_tabs_01");
            var num = tab.handle.getTabIndex(obj);
            
            for(var i = 1; i < 4; i++) {
                if(idx == i) {
                    form.util.addClass("MMA031_03_divMove0"+i, "on");
                } else {
                    form.util.removeClass("MMA031_03_divMove0"+i, "on");
                }
            }
            
            if(idx == "1") {
            	var dg_2 = grid.getObject("MMA031_03_grid_02");
            	var dg_3 = grid.getObject("MMA031_03_grid_03");
            	
                if(!grid.handle.isSelected(dg_2)) {
                	MMA031_03.control.selectSystemNoticeList();
                }
                
                if(!grid.handle.isSelected(dg_3)) {
                	MMA031_03.control.selectSystemUpdateList();
                }
            }
            
            tab.handle.select(obj, idx - 1);
        },
        showNewIcon : function(val, row, idx) {
    		var src;
    		
    		if(oUtil.isNull(val)) {
    			src = "";
    		} else {
    			if(row.NEW_YN == "Y") {
    				src = "<span id=\"MM0001_01_span_03\" class=\"new_notice\">New</span> <span>"+val+"</span>";
    			} else {
    				src = val;
    			}
    		}
    		
    		return src;
    	}
    },
    // 파일 input 엘리먼트 구현
    file : {
    },
    // 엑셀다운로드 구현
    excel : {
    	excelDownload_1 : function() {
            var dg_1 = grid.getObject("MMA031_03_grid_01");
            var fobj = form.getObject("MMA031_03_form_01");
            
            form.init.setURL("/mm/noses/mmA031_01/selectInquiryEmailRecordList.fle");
            
            form.excelSubmit(dg_1, fobj, resource.getMessage("INQUI, LIST"), resource.getMessage("LIST"));
            
            setTimeout(function(){
                $.messager.progress('close');
            }, 2000);
        }
    },
    config : {
      	applyFtaNation : function() {}
     }
    
};
