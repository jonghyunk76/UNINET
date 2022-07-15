/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : OR-5009_02
 * 작업일자 : 2020.09.23
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMB002_16.config.applyFtaNation();
    MMB002_16.init.initComponent();
}

var MMB002_16 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
        	MMB002_16.control.selectEntryFileList();
        }
    }, 
    // 달력 생성
    calendar : {}, 
    // 콤보박스 생성
    combobox : {},
    // 챠트 생성
    chart : {},
    // 툴팁 생성
    tooltip : {},
    // 데이터 그리드 생성
    datagrid : {},
    // 이벤트 처리
    event : {
    	selectAfterUI : function(data) {
            form.handle.loadData("MMB002_16_form_01", data);
            MMB002_16.ui.showFileContents(data.rows);
        },
    	updateAfterUI : function(data) {
    		form.handle.setValue("MMB002_16_form_01", "ENTR_ATCHF_MASTR_SN", data.ENTR_ATCHF_MASTR_SN);
    		// 첨부파일 재 조회
			setTimeout(function () {
				MMB002_16.control.selectEntryFileList();
            },
            100);    		
    		
			// 수입신고서 리스트 재 조회
            var pid = form.handle.getValue("MMB002_16_form_01", "TARGET_PID");
            var newYN = form.handle.getValue("MMB002_16_form_01", "NEW_YN");
            console.log('TARGET_PID : ' + TARGET_PID);
            
            if (!oUtil.isNull(pid)) {
                var pro_id = eval("window." + pid + ".dialog");                                
                var params = form.handle.getElementsParam("MMB002_16_form_01");
                if (newYN == "N") {
                	params.NEW_YN = "Y"; // 신규 등록인 경우
                }
         
                if (typeof(pro_id["callByMMB002_16"]) == "function") {
                    pro_id["callByMMB002_16"](params);
                }
            }
    	},
    	deleteEntryFileDetailInfo : function(data) {
    		// 첨부파일 재 조회
			setTimeout(function () {
				MMB002_16.control.selectEntryFileList();
            },
            100);
			
			// 수입신고서 리스트 재 조회
            var pid = form.handle.getValue("MMB002_16_form_01", "TARGET_PID");
            console.log('TARGET_PID : ' + TARGET_PID);
            
            if (!oUtil.isNull(pid)) {
                var pro_id = eval("window." + pid + ".dialog");
                var params = form.handle.getElementsParam("MMB002_16_form_01");
                
                if (typeof(pro_id["callByMMB002_16"]) == "function") {
                    pro_id["callByMMB002_16"](params);
                }
            }			
    	}
    },
    // 업무구현
    control : {
    	selectEntryFileList : function() {
            var obj = form.getObject("MMB002_16_form_01");

            form.init.setURL("/mm/pop/MMB002_16/selectEntryFileList");
            form.init.setCallBackFunction("selectAfterUI");
            form.init.setProgressFlag(false);
            
            form.submit(obj);
        },
    	// 통관 첨부파일 등록/수정
    	mergeEntryFile : function() {
    		var formName = "MMB002_16_form_01";
            if (!form.handle.isValidate(formName)) return;		// input 유효성 체크
            
            var confMsg = resource.getMessage("MSG_CONFIRM_UPDATE");
            
            $.messager.confirm(CNFIR, confMsg, function(flag) {							// 계속 실행 하시겠습니까? 물어보는 얼랏 창
                
                if (flag) {
                    var obj = form.getObject(formName);

                    // gridData
                    var jsonFileArray = new Array();
                    // 파일첨부 가능 개수
                    var gridCount = form.handle.getValue(formName, "gridCount");

         			for(var i = 1; i <= gridCount; i++) {
         				var jsonFile = new Object();
         				var ENTR_ATCHF_MASTR_SN = "ENTR_ATCHF_MASTR_SN";
         				var ENTR_ATCHF_DETAIL_SN = "ENTR_ATCHF_DETAIL_SN"+i;
         				var ATCHF = "ATCHF"+i;
         				var FILE_TY = "FILE_TY"+i;
						var ATCHF_TY = "ATCHF_TY"+i;         				

         				jsonFile.ENTR_ATCHF_MASTR_SN = form.handle.getValue(formName, ENTR_ATCHF_MASTR_SN);
         				jsonFile.ENTR_ATCHF_DETAIL_SN = form.handle.getValue(formName, ENTR_ATCHF_DETAIL_SN);
         				jsonFile.ATCHF = form.handle.getValue(formName, ATCHF);
         				jsonFile.FILE_TY = form.handle.getValue(formName, FILE_TY);
						jsonFile.ATCHF_TY = form.handle.getValue(formName, ATCHF_TY);
         				
         				if (!oUtil.isNull(jsonFile.ATCHF)) {
         					jsonFileArray.push(jsonFile);
         				}
            		}
         			
         			
         			form.handle.setValue(formName, "gridData", grid.util.convertJson2Rows(jsonFileArray));
         			console.log('FileUpload : ' + form.handle.getValue(formName, "gridData"));
                    form.init.setURL("/mm/pop/MMB002_16/mergeEntryFile");
                    form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
                    form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
                    form.init.setCallBackFunction("updateAfterUI");
                    
                    form.submit(obj);
                }
            });
        },
    	// 신고서 첨부파일 다운로드
        selectEntryFileDownload : function(ENTR_ATCHF_DETAIL_SN, FILE_NM) {
            form.handle.setValue("MMB002_16_form_01", "ENTR_ATCHF_DETAIL_SN", ENTR_ATCHF_DETAIL_SN);
            form.handle.setValue("MMB002_16_form_01", "FILE_NAME", FILE_NM);
            
            var obj = form.getObject("MMB002_16_form_01");
            
            form.init.setURL("/mm/pop/MMB002_16/selectEntryFileDownload");
            form.init.setProgressFlag(false);
            
            form.submit(obj);
        },
        // 신고서 첨부파일 삭제
        deleteEntryFileDetailInfo : function(sn) {
        	$.messager.confirm(CNFIR, resource.getMessage("MSG_CONFIRM_DELETE"), function(flag) {
                if(flag) {
                	form.handle.setValue("MMB002_16_form_01", "ENTR_ATCHF_DETAIL_SN", sn);
                	var obj = form.getObject("MMB002_16_form_01");
                    
                    form.init.setURL("/mm/pop/MMB002_16/deleteEntryFileDetailInfo");
                    form.init.setSucceseMessage(resource.getMessage("MSG_DELETE_SUCCESS"));
                    form.init.setFailMessage(resource.getMessage("MSG_DELETE_FAILED"));
                    form.init.setCallBackFunction("deleteEntryFileDetailInfo");
                    
                    form.submit(obj);
                }
            });
        }
    },
    // 다이얼로그 구현
    dialog : {
    },
    // 화면 UI 변경
    ui : {
    	showFileContents : function(datas) {
    		console.log(datas);
    		if(oUtil.isNull(datas)) return;
    		
    		var contents = new StringBuffer();
    		contents.append("<colgroup>						");
    		contents.append("<col style='width:80px;' />	");
    		contents.append("<col style='width:100px;' />	");
    		contents.append("<col style='width:;' />		");
    		contents.append("<col style='width:150px;' />	");
    		contents.append("<col style='width:;' />		");
    		contents.append("</colgroup>    				");

    		for(var i = 1 ; i <= datas.length; i++) {
    			var data = datas[i-1];
				var entrAtchfDetailSn = '';
				var fileNm  = '';
				
				if(!oUtil.isNull(data.ENTR_ATCHF_DETAIL_SN)) {
					entrAtchfDetailSn = data.ENTR_ATCHF_DETAIL_SN;
					fileNm = data.FILE_NM;
				}
				
                contents.append("<tr>                                                                                                                             ");    
                contents.append("    <th scope='row' rowspan='2'>                                                                                                 ");
                contents.append("        <div class='flt_l'>"+data.FILE_TY_NM+"</div>                                                      							");
                contents.append("    </th>                                                                                                                        ");
                contents.append("    <th scope='row'>"+TXT_FILE_ADD+"</th>                                                                    ");
                contents.append("    <td colspan='3'>                                                                                                             ");
                contents.append("        <input type='file' name='ATCHF"+i+"' id='ATCHF"+i+"' title='FILE' style='width:98%'/>             ");
                contents.append("        <input type='hidden' name='ENTR_ATCHF_DETAIL_SN"+i+"' id='ENTR_ATCHF_DETAIL_SN"+i+"' value='"+data.ENTR_ATCHF_DETAIL_SN+"' />             ");
                contents.append("        <input type='hidden' name='FILE_TY"+i+"' id='FILE_TY"+i+"' value='"+data.FILE_TY+"' />             ");
                contents.append("        <input type='hidden' name='ATCHF_TY"+i+"' id='ATCHF_TY"+i+"' value='ATCHF"+i+"' />             ");
                contents.append("    </td>                                                                                                                        ");
                contents.append("</tr>                                                                                                                            ");
                contents.append("<tr>                                                                                                                             ");
                contents.append("    <th scope='row'>"+TXT_ATTACH_FILE+"</th>                                                                ");
                contents.append("    <td colspan='3' style='height:19px;'>                                                                                        ");
				if(!oUtil.isNull(data.ENTR_ATCHF_DETAIL_SN)) {
					contents.append("        <a href='javascript:MMB002_16.control.selectEntryFileDownload(\""+entrAtchfDetailSn+"\",\""+fileNm+"\");'><img src='/images/icon/disk_download.png' boder='0'/> "+fileNm+"</a><span style='float:right;'><a href='javascript:MMB002_16.control.deleteEntryFileDetailInfo(\""+entrAtchfDetailSn+"\");' class='btn'>"+DEL+"</a></span>");
				}
                contents.append("    </td>                                                                                                                        ");
                contents.append("</tr>                                    ");
    		}
    		
    		form.handle.setValue("MMB002_16_form_01", "gridCount", datas.length);
    		
    		$("#MMB002_16_grid_01").html(contents.toString());
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
