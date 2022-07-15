/******************************************************************************************
 * 작성자 : 유동기
 * Program Id : SM-7012_02
 * 작업일자 : 2017.07.12
 * 
 ******************************************************************************************/

function onLoadPage() {
    SM7012_02.config.applyFtaNation();
    SM7012_02.init.initComponent();
}

var SM7012_02 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
            var flag = form.handle.getValue("SM7012_02_form_01", "flag");
            
            SM7012_02.datagrid.initGrid_1();
            
            if(flag != "insert"){
                SM7012_02.ui.actionChangeUI();
            }
            
            SM7012_02.tooltip.makeTooltip();
        }
    }, 
    // 달력 생성
    calendar : {}, 
    // 콤보박스 생성
    combobox : {},
    // 챠트 생성
    chart : {},
    // 툴팁 생성
    tooltip : {
        makeTooltip : function() {
            form.util.setFileTooltip("SM7012_02_files_1", "SM7012_02_grid_02", null, 500, 100);
        },
        showTooltip : function() {
            form.util.showTooltip("SM7012_02_files_1");
        },
        hideTooltip : function() {
            form.util.hideTooltip("SM7012_02_files_1");
        },
        setFiles : function(pfile) {
        	this.showTooltip();
        	
        	setTimeout( function() { // 크롭에서 문제가 있어 0.5정도 후에 보이도록 수정(2020-05-14)
	            var file = pfile.files;
	            var fileArray = new Array();
	            var tts = 0;
	            
	            for(var i = 0; i < file.length; i++) {
	                var params = {};
	                
	                params.datas = file[i];
	                params.name = file[i].name;
	                params.size = file[i].size;
	                tts += file[i].size;
	                
	                fileArray[i] = params;
	            }
	
	            var msg = "";
	            if(!oUtil.isNull(SESSION.MAX_UPLOAD_SIZE) && tts > parseInt(SESSION.MAX_UPLOAD_SIZE)) {
	                msg = "Warring : " + resource.getMessage("MSG_FILE_SIZE_OVER") + "(" + resource.getMessage("TOTAL, SIZE") + " : " + 
	                      Math.floor(tts/1000000) + "MB / " + resource.getMessage("MAX, SIZE") + " : "+Math.floor(parseInt("${FILE_MAX_UPLOAD_SIZE}")/1000000)+"MB)";
	            } else {
	                msg = resource.getMessage("FILE, NUMBR, : ") + fileArray.length + ", " + resource.getMessage("TOTAL, SIZE, : ") + (tts/1000000) + "MB";
	            }
	            
	            form.handle.setValue("SM7012_02_form_01", "file_aliase", msg);
	            form.handle.setValue("SM7012_02_form_01", "FILE_SIZE", tts);
	            
	            SM7012_02.datagrid.initGrid_2(fileArray);
        	}, 500);
        }
    },
    // 데이터 그리드 생성
    datagrid : {
        header1 : [
               ["ORIGIN_FILE_NAME"      ,resource.getMessage("FILE, LIST")             ,300    ,"left"     ,"center"   ,false    ,false    ,null        ,null    ,{format: 'function', pid:'SM7012_02', name:'fileDownloadLink'}    ,0        ,0 ],
               ["FILE_SEQ"              ,resource.getMessage("DEL")                    ,80     ,"center"   ,"center"   ,true     ,false    ,null        ,null    ,{format: 'function', pid:'SM7012_02', name:'formatDelButton'}    ,0        ,0]
         ],
         header2 : [
               ["datas"                 ,resource.getMessage("DATA")                   ,160    ,"left"     ,"center"   ,false    ,true     ,null        ,null    ,null    ,0        ,0 ],
               ["name"                  ,resource.getMessage("FILE, NAME")             ,300    ,"left"     ,"center"   ,false    ,false    ,null        ,null    ,null    ,0        ,0 ],
               ["size"                  ,resource.getMessage("FILE, SIZE, (byte)")     ,100    ,"right"    ,"center"   ,false    ,false    ,null        ,null    ,{format:"int"}    ,0        ,0 ],
               ["status"                ,resource.getMessage("STATE")                  ,160    ,"left"     ,"center"   ,false    ,true     ,null        ,null    ,null    ,0        ,0 ]
         ],
         initGrid_1 : function() {
        	 var flag = form.handle.getValue("SM7012_02_form_01", "flag");
        	 var params = form.handle.getElementsParam("SM7012_02_form_02");
             var dg_1 = grid.getObject("SM7012_02_grid_01");
             
             if(flag == "update") {
	             grid.init.setQueryParams(params);
	             grid.init.setURL("/fm/sm/sm7013_02/selectBoardFileList");
             } else {
            	 grid.init.setURL("#");
             }
             grid.init.setPage(false);
             grid.init.setShowHeader(false);
             grid.init.setRownumbers(true);
             grid.init.setFitColumns(true);
             grid.init.setEmptyMessage(false);
             
             Theme.defaultGrid(dg_1, this.header1);
         },
         initGrid_2 : function(fileArray) {
             var dg_1 = grid.getObject("SM7012_02_grid_02");
             
             grid.init.setPage(false);
             grid.init.setData(fileArray);
             grid.init.setFit(true);
             grid.init.setFitColumns(true);
             grid.init.setEmptyMessage(false);
             grid.init.setFilter(false);
             
             Theme.defaultGrid(dg_1, this.header2);
         }
    },
    // 이벤트 처리
    event : {
        updateAfterUI : function(datas) {
        	form.handle.setValue("SM7012_02_form_01", "flag", "update");
        	form.handle.setValue("SM7012_02_form_01", "FILE_IN_FLAG", datas.dataMap.map.file_aliase);
        	
        	if(!oUtil.isNull(datas.BOARD_ID)) {
        		form.handle.setValue("SM7012_02_form_01", "BOARD_ID", datas.BOARD_ID);
        		form.handle.setValue("SM7012_02_form_02", "BOARD_ID", datas.BOARD_ID);
        		form.handle.setValue("SM7012_02_form_01", "BOARD_NO", datas.BOARD_NO);
        		form.handle.setValue("SM7012_02_form_01", "CREATE_DATE", datas.CREATE_DATE);
        		form.handle.setValue("SM7012_02_form_01", "READ_COUNT", datas.READ_COUNT);
            }
        	
        	SM7012_02.control.selectQnAFile();
        	SM7012_01.control.selectQnAList();
        	SM7012_02.ui.actionChangeUI();
        },
        deleteAfterUI : function() {
            var dl_1 = dialog.getObject("SM7012_02_dialog_01");
            
            dialog.handle.close(dl_1);
            
            SM7012_01.control.selectQnAList();
        },
        readCountSetUI : function(data) {
        	form.handle.setValue("SM7012_02_form_01", "READ_COUNT", data.READ_COUNT);
        },
        readCountUI : function() {
        	SM7012_02.control.selectReadCount();
        },
        searchQnAFile : function() {
        	var fileCount = form.handle.getValue("SM7012_02_form_01", "FILE_COUNT");
        	var fileInFlag = form.handle.getValue("SM7012_02_form_01", "FILE_IN_FLAG");
        	var dg_1 = grid.getObject("SM7012_02_grid_02");
        	
        	if((!oUtil.isNull(fileCount) && fileCount > 0) || !oUtil.isNull(fileInFlag)) {
        		form.handle.setValue("SM7012_02_form_01", "file_aliase","");
        		
        		try {grid.handle.clearAll(dg_1);} catch (e) {}
        		
        		SM7012_02.control.selectQnAFile();
        	}
        }
    },
    // 업무구현
    control : {
        selectQnAFile : function() {
            var dg_1 = grid.getObject("SM7012_02_grid_01");
            var params = form.handle.getElementsParam("SM7012_02_form_02");
            
            grid.handle.sendRedirect(dg_1, "/fm/sm/sm7013_02/selectBoardFileList" ,params);
        },
        updateQnAInfo : function() {
            if(!form.handle.isValidate("SM7012_02_form_01")) return;
            
            var confMsg = "";
            if(form.handle.getValue("SM7012_02_form_01", "flag") == "update") {
            	confMsg = resource.getMessage("MSG_CONFIRM_UPDATE");
            } else {
            	confMsg = resource.getMessage("MSG_CONFIRM_SAVE");
            }

            $.messager.confirm(resource.getMessage("CNFIR"), confMsg, function(flag) {
                if(flag) {
                    var obj = form.getObject("SM7012_02_form_01");

                    if(form.handle.getValue("SM7012_02_form_01", "flag") == "update") {
                        form.init.setURL("/fm/sm/sm7013_02/updateQnAInfo");
                    } else {
                        form.init.setURL("/fm/sm/sm7013_02/insertQnAInfo");
                    }
                    form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
                    form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
                    form.init.setCallBackFunction("updateAfterUI");
                    form.submit(obj);
                }
            });
        },
        deleteQnAInfo : function() {
            $.messager.confirm(resource.getMessage("CNFIR"), resource.getMessage("MSG_CONFIRM_DELETE"), function(flag) {
                if(flag) {
                    var obj = form.getObject("SM7012_02_form_01");
                    
                    form.handle.setValue("SM7012_02_form_01", "file_aliase","");
                    
                    form.init.setURL("/fm/sm/sm7013_02/deleteQnAInfo");
                    form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
                    form.init.setFailMessage(resource.getMessage("MSG_DELETE_FAILED"));
                    form.init.setCallBackFunction("deleteAfterUI");
                    
                    form.submit(obj);
                }
            });
        },
        selectReadCount : function() {
            var obj = form.getObject("SM7012_02_form_02");
            
            form.init.setURL("/fm/sm/sm7013_02/selectReadCount");
            form.init.setCallBackFunction("readCountSetUI");
            form.init.setProgressFlag(false);
            form.submit(obj);
        },
        updateReadCount : function() {
            var obj = form.getObject("SM7012_02_form_02");
            
            form.init.setURL("/fm/sm/sm7013_02/updateReadCount");
            form.init.setProgressFlag(false);
            form.init.setCallBackFunction("readCountUI");
            
            form.submit(obj);
        },
        downloadConfirmFile : function() {
            var dg_1 = grid.getObject("SM7012_02_grid_01");
            var rowData = grid.handle.getSelectedRowData(dg_1);
            
            form.handle.setValue("SM7012_02_form_01", "BOARD_FILE_ID", rowData.FILE_SEQ);
            
            var obj = form.getObject("SM7012_02_form_01");
            
            form.init.setURL("/fm/sm/sm7013_02/selectBoardFile");
            form.init.setProgressFlag(false);
            
            form.submit(obj);
        },
        fncDeleteFile : function() {
            var dg_1 = grid.getObject("SM7012_02_grid_01");
            var rowData = grid.handle.getSelectedRowData(dg_1);
            
            form.handle.setValue("SM7012_02_form_01", "BOARD_FILE_ID", rowData.FILE_SEQ);
            
            var obj = form.getObject("SM7012_02_form_01");
            
            form.init.setURL("/fm/sm/sm7013_02/deleteBoardFile");
            form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
            form.init.setFailMessage(resource.getMessage("MSG_DELETE_FAILED"));
            form.init.setCallBackFunction("searchQnAFile");
            
            form.submit(obj);
        },
        insertReplyInfo : function(flag) {
        	var boardId = form.handle.getValue("SM7012_02_form_01", "BOARD_ID");
        	var content = form.handle.getValue("SM7012_02_form_01", "CONTENT");
        	var params = new Object();
        	
        	params.PARENT_BOARD_ID = boardId;
        	params.CONTENT = content;
        	params.flag = flag;
        	SM7012_01.dialog.openDialog_1(params);
        	
        	form.handle.reset("SM7012_02_form_01");
        }
    },
    // 다이얼로그 구현
    dialog : {},
    // 화면 UI 변경
    ui : {
        fileDownloadLink : function(value, row, index) {
            return "<a href=\"javascript:SM7012_02.control.downloadConfirmFile();\" title=\"" + value + "\" class=\"easyui-tooltip\">"+
                   "<img src=\"/images/icon/external_link.png\" boder=\"0\"/> " + value + "</a>";
        },
        formatDelButton : function(value, row, index) {
        	var createBy = form.handle.getValue("SM7012_02_form_01","CREATE_BY");
            var userId = form.handle.getValue("SM7012_02_form_01","SESSION_USER_ID");

            if(!oUtil.isNull(value) && createBy ==  userId) {
                return "<a href='javascript:SM7012_02.control.fncDeleteFile();' class='btn'>"+resource.getMessage("DEL")+"</a>";
            } else {
                return "";
            }
        },
        actionChangeUI : function() {
            var flag = form.handle.getValue("SM7012_02_form_01", "flag");
            var createBy = form.handle.getValue("SM7012_02_form_01","CREATE_BY");
            var userId = form.handle.getValue("SM7012_02_form_01","USER_ID");
            
            if(flag == "insert") {
                form.util.setVisible("SM7012_02_btn_div_1", true);
            } else if(flag == "update") {
                if(userId != createBy){
	                form.handle.closeAll("SM7012_02_form_01");
	                
	                form.util.setVisible("SM7012_02_btn_02", false);
	                form.util.setVisible("SM7012_02_btn_div_1", false);
	                form.util.setVisible("SM7012_02_files_div", false);
	                form.util.setVisible("SM7012_02_include_div", false);
                } else {
	                form.util.setVisible("SM7012_02_btn_03", true);
                }
            }
        }
    }, 
    // 파일 input 엘리먼트 구현
    file : {}, 
    // 엑셀다운로드 구현
    excel : {},
    // 화면 초기 설정
    config : {
        applyFtaNation : function() {}
    }
};
