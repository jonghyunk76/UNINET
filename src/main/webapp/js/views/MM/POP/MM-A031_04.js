/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : MM-A031_04
 * 작업일자 : 2020.01.29
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMA031_04.config.applyFtaNation();
    MMA031_04.init.initComponent();
}

var MMA031_04 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
        	try {
        		if(SESSION.COMPANY_CD == "KJ100" || SESSION.COMPANY_CD == "TOMS") {
        			form.util.setVisible("MMA031_04_tool_01", true);
        			
        			MMA031_04.tooltip.initTooltip_1();
        			
        			setTimeout(function () {
                		MMA031_04.datagrid.initGrid_3();
                	}, 1000);
        		}
        	} catch(e) { }
        	
        	MMA031_04.tooltip.makeTooltip();
        	MMA031_04.datagrid.initGrid_1();
        	
        	var flag = form.handle.getValue("MMA031_04_form_01", "flag");
        	var status = form.handle.getValue("MMA031_04_form_01", "STATUS");
        	
        	if(flag == "view" || status == "3") {
        		MMA031_04.ui.changeViewUI();
        	} else if(flag == "update") {
        		MMA031_04.ui.changeUpdateUI();
        	}
        	
        	MMA031_04.combobox.initCombo_1();
        }
    }, 
    // 달력 생성
    calendar : {}, 
    // 콤보박스 생성
    combobox : {
    	initCombo_1 : function() {
    		var inType = form.handle.getValue("MMA031_04_form_01", "INQUIRY_TYPE");
            var obj = combo.getObject("MMA031_04_form_01", "INQUIRY_PARENT_TYPE");

            combo.init.setURL("/mm/cbox/selectStandardCode");
            if(inType == "3") {
            	combo.init.setQueryParams({COMPANY_CD:'TOMS', CATEGORY_CD:"INQUIRY_PARENT_TYPE", MESSAGE_CODE:"SELET", IGNORE_CODE:"'3','4','6'"});
            } else {
            	combo.init.setQueryParams({COMPANY_CD:'TOMS', CATEGORY_CD:"INQUIRY_PARENT_TYPE", MESSAGE_CODE:"SELET"});
            }
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            combo.init.setRequired(true);
            
            combo.create(obj);
        },
        initCombo_2 : function() {
            var obj = combo.getObject("MMA031_04_form_01", "INQUIRY_SUB_TYPE");

            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:'TOMS', CATEGORY_CD:"INQUIRY_SUB_TYPE", MESSAGE_CODE:"SELET"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(obj);
        }
    },
    // 챠트 생성
    chart : {},
    // 데이터 그리드 생성
    datagrid : {
    	header1 : [
            ["ORIGIN_FILE_NAME"      ,resource.getMessage("FILE, LIST")             ,300    ,"left"     ,"center"   ,false    ,false    ,null        ,null    ,{format: 'function', pid:'MMA031_04', name:'fileDownloadLink'}    ,0        ,0 ],
            ["FILE_SEQ"              ,resource.getMessage("DEL")                    ,80     ,"center"   ,"center"   ,true     ,false    ,null        ,null    ,{format: 'function', pid:'MMA031_04', name:'formatDelButton'}    ,0        ,0]
        ],
        header2 : [
            ["datas"                 ,resource.getMessage("DATA")                   ,160    ,"left"     ,"center"   ,false    ,true     ,null        ,null    ,null    ,0        ,0 ],
            ["name"                  ,resource.getMessage("FILE, NAME")             ,300    ,"left"     ,"center"   ,false    ,false    ,null        ,null    ,null    ,0        ,0 ],
            ["size"                  ,resource.getMessage("FILE, SIZE, (byte)")     ,100    ,"right"    ,"center"   ,false    ,false    ,null        ,null    ,{format:"int"}    ,0        ,0 ],
            ["status"                ,resource.getMessage("STATE")                  ,160    ,"left"     ,"center"   ,false    ,true     ,null        ,null    ,null    ,0        ,0 ]
        ],
        header3 : [
            ["INQUIRY_NAME"      ,resource.getMessage("FRSNM")             ,200    ,"left"     ,"center"   ,true    ,false    ,null        ,null    ,null   ,0        ,0 ]
        ],
        initGrid_1 : function() {
        	var flag = form.handle.getValue("MMA031_04_form_01", "flag");
        	var params = form.handle.getElementsParam("MMA031_04_form_01");
            var dg_1 = grid.getObject("MMA031_04_grid_01");
            
            if(params == "insert") {
            	grid.init.setURL("#");
            } else {            	
            	grid.init.setURL("/mm/noses/mmA031_01/selectInquiryFileList");
            }
            grid.init.setQueryParams(params);
		    grid.init.setPage(false);
		    grid.init.setShowHeader(false);
		    grid.init.setRownumbers(true);
		    grid.init.setFitColumns(true);
		    grid.init.setEmptyMessage(false);
		  
		    Theme.defaultGrid(dg_1, this.header1);
        },
        initGrid_2 : function(fileArray) {
            var dg_1 = grid.getObject("MMA031_04_grid_02");
          
            grid.init.setPage(false);
            grid.init.setData(fileArray);
            grid.init.setFit(true);
            grid.init.setFitColumns(true);
            grid.init.setEmptyMessage(false);
          
            Theme.defaultGrid(dg_1, this.header2);
        },
        initGrid_3 : function() {
//        	var params = form.handle.getElementsParam("MMA031_04_form_01");
            var dg_1 = grid.getObject("MMA031_04_grid_03");
            
//            grid.init.setURL("/fm/sm/sm7014_02/selectInquiryUserList");
//            grid.init.setQueryParams(params);
            grid.init.setPage(false);
            grid.init.setFit(true);
            grid.init.setFitColumns(false);
            grid.init.setShowHeader(true);
            grid.init.setRownumbers(false);
            grid.init.setEmptyMessage(false);
            
            grid.event.setOnDblClickRow(dg_1);
            
            Theme.defaultGrid(dg_1, this.header3);
        }
    },
    // 이벤트 처리
    event : {
    	insertInquiryEmailRecord : function(data) {
    		if(!oUtil.isNull(data.INQUIRY_NO)) {
	    		form.handle.setValue("MMA031_04_form_01", "INQUIRY_NO", data.INQUIRY_NO);
	    		form.handle.setValue("MMA031_04_form_02", "INQUIRY_NO", data.INQUIRY_NO);
    		}
    		
    		form.handle.setValue("MMA031_04_form_01", "flag", "update");
    		form.handle.setValue("MMA031_04_form_01", "STATUS", "1");
    		form.handle.setValue("MMA031_04_form_01", "FILEUP", "");
    		form.handle.setValue("MMA031_04_form_01", "file_aliase", "");
    		
    		var dg_1 = grid.getObject("MMA031_04_grid_02");
    		grid.handle.clearAll(dg_1);
    		
    		MMA031_04.control.selectInquiryFileList();
    		MMA031_03.control.selectMainList();
    	},
    	deleteInquiryFileRecord : function() {
    		MMA031_04.control.selectInquiryFileList();
    		MMA031_03.control.selectMainList();
    	},
    	onDblClick_MMA031_04_grid_03 : function(rowData) {
            form.handle.loadData("MMA031_04_form_01", rowData);
        },
        selectInquiryUserList : function() {
        	MMA031_04.control.selectInquiryUserList();
        }
    },
    // 업무구현
    control : {
    	insertInquiryEmailRecord : function() {
    		var flag = form.handle.getValue("MMA031_04_form_01", "flag");
    		var obj = form.getObject("MMA031_04_form_01");
    		
    		if(flag == "update") {
    			form.init.setURL("/mm/noses/mmA031_01/updateInquiryEmailRecord");
    		} else if(flag == "insert") {
    			form.init.setURL("/mm/noses/mmA031_01/insertInquiryEmailRecord");
    		}
    		form.init.setProgressFlag(true);
    		form.init.setSucceseMessage("<h3>"+resource.getMessage("TXT_INQUIRY_MSG5")+"</h3><br>"+resource.getMessage("TXT_INQUIRY_MSG6"));
            form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
            form.init.setCallBackFunction("insertInquiryEmailRecord");
            
            form.submit(obj);
    	},
    	selectInquiryFileList : function() {
            var dg_1 = grid.getObject("MMA031_04_grid_01");
            var params = form.handle.getElementsParam("MMA031_04_form_01");
            
            grid.handle.sendRedirect(dg_1, "/mm/noses/mmA031_01/selectInquiryFileList", params);
        },
        selectInquiryUserList : function() {
            var dg_1 = grid.getObject("MMA031_04_grid_03");
            var params = form.handle.getElementsParam("MMA031_04_form_01");
            
            grid.handle.sendRedirect(dg_1, "/fm/sm/sm7014_02/selectInquiryUserList", params);
        },
        fncDeleteFile : function() {
        	$.messager.confirm(CNFIR, resource.getMessage("MSG_CONFIRM_DELETE"), function(flag) {
        		if (flag) {
		            var dg_1 = grid.getObject("MMA031_04_grid_01");
		            var rowData = grid.handle.getSelectedRowData(dg_1);
		            
		            form.handle.setValue("MMA031_04_form_02", "FILE_SEQ", rowData.FILE_SEQ);
		            
		            var obj = form.getObject("MMA031_04_form_02");
		            
		            form.init.setURL("/mm/noses/mmA031_01/deleteInquiryFileRecord");
		            form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
		            form.init.setFailMessage(resource.getMessage("MSG_DELETE_FAILED"));
		            form.init.setCallBackFunction("deleteInquiryFileRecord");
		            
		            form.submit(obj);
        		}
        	});
        },
        downloadConfirmFile : function(fileSeq) {
        	form.handle.setValue("MMA031_04_form_02", "FILE_SEQ", fileSeq);
            
            var obj = form.getObject("MMA031_04_form_02");
            
            form.init.setURL("/mm/noses/mmA031_01/selectInquiryRealFile");
            form.init.setProgressFlag(false);
            
            form.submit(obj);
        }
    },
    // 다이얼로그 구현
    dialog : {
    	close : function() {
    		var obj = dialog.getObject("MMA031_04_dailog_01");
			
    		dialog.handle.close(obj);
    	}
    },
    // 화면 UI 변경
    ui : {
    	changeUpdateUI : function() {},
    	changeViewUI : function() {
    		var flag = form.handle.getValue("MMA031_04_form_01", "flag");
    		
    		form.handle.closeAll("MMA031_04_form_01", true);
    		form.util.setVisible("MMA031_04_span_01", false);
    		
    		if(flag != "insert") {
    			MMA031_04.control.selectInquiryFileList();
    		}
    	},
    	fileDownloadLink : function(value, row, index) {
            return "<a href=\"javascript:MMA031_04.control.downloadConfirmFile('"+row.FILE_SEQ+"');\" title=\"" + value + "\" class=\"easyui-tooltip\">"+
                   "<img src=\"/images/icon/external_link.png\" boder=\"0\"/> " + value + "</a>";
        },
        formatDelButton : function(value, row, index) {
        	return "<a href='javascript:MMA031_04.control.fncDeleteFile();' class='btn'>"+resource.getMessage("DEL")+"</a>";
        }
    }, 
    // 파일 input 엘리먼트 구현
    file : {},
    // 툴팁 생성
    tooltip : {
        makeTooltip : function() {
            // tooltip명, datagrid명, 툴팁 열기전 호출할 function 명, 넓이, 높이
            form.util.setFileTooltip("MMA031_04_files_1", "MMA031_04_grid_02", null, 500, 100);
        },
        showTooltip : function() {
            form.util.showTooltip("MMA031_04_files_1");
        },
        hideTooltip : function() {
            form.util.hideTooltip("MMA031_04_files_1");
        },
        setFiles : function(pfile) {
        	this.showTooltip();
        	
        	setTimeout( function() { // 크롭에서 문제가 있어 0.5정도 후에 보이도록 수정(2020-05-14)
	            var file = pfile.files;
	            var fileArray = new Array();
	            var tts = 0;
	            var max_size = 10000000; // 10M 
	            
	            for(var i = 0; i < file.length; i++) {
	                var params = {};
	                
	                params.datas = file[i];
	                params.name = file[i].name;
	                params.size = file[i].size;
	                tts += file[i].size;
	                
	                fileArray[i] = params;
	            }
	
	            var msg = "";
	            if(!oUtil.isNull(max_size) && tts > parseInt(max_size)) {
	                msg = "Warring : " + resource.getMessage("MSG_FILE_SIZE_OVER") + "(" + resource.getMessage("TOTAL, SIZE") + " : " + 
	                      Math.floor(tts/1000000) + "MB / " + resource.getMessage("MAX, SIZE") + " : "+Math.floor(max_size/1000000)+"MB)";
	            } else {
	                msg = resource.getMessage("FILE, NUMBR, : ") + fileArray.length + ", " + resource.getMessage("TOTAL, SIZE, : ") + (tts/1000000) + "MB";
	            }
	            
	            form.handle.setValue("MMA031_04_form_01", "file_aliase", msg);
	            form.handle.setValue("MMA031_04_form_01", "FILE_SIZE", tts);
	            
	            MMA031_04.datagrid.initGrid_2(fileArray);
        	}, 500);
        },
        initTooltip_1 : function() {
            var obj = tooltip.getObject("MMA031_04_tool_01");
            
            tooltip.init.setContentID("MMA031_04_ttcont_01");
            tooltip.init.setWidth(300);
            tooltip.init.setPID("MMA031_04");
            tooltip.init.setHideEvent("none");
            tooltip.init.setShowCallbackFunction("selectInquiryUserList");
            
            tooltip.create(obj);
        }
    },
    // 엑셀다운로드 구현
    excel : {},
    // 화면 초기 설정
    config : {
    	applyFtaNation : function() {}
    }
};
