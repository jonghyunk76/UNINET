/******************************************************************************************
 * 작성자 : 유동기
 * Program Id : SM-7012_01
 * 작업일자 : 2017.07.12
 * 
 ******************************************************************************************/

function onLoadPage() {
    SM7012_01.config.applyFtaNation();
    SM7012_01.init.initComponent();
}

var SM7012_01 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
            SM7012_01.combobox.initCombo_1();
            SM7012_01.combobox.initCombo_2();
            
            SM7012_01.datagrid.initGrid_1();
            
            var tabObj = tab.getObject("MM0001_01_tabs_01");
            var selTab = tab.handle.getSelected(tabObj);
            var title = selTab.panel('options').title;
            
            if(oUtil.isNull(title)) {
            	title = resource.getMessage("POST,LIST");
            } else {
            	title = title+" "+resource.getMessage("LIST");
            }
            
            $("#SM7012_01_span_01").html(title);
        }
    }, 
    // 달력 생성
    calendar : {}, 
    // 콤보박스 생성
    combobox : {
        dataSearchType : [{CODE : "SUBJECT", NAME : resource.getMessage("TITLE")}, {CODE : "USER_NAME", NAME : resource.getMessage("WRTER")}],
        initCombo_1 : function() {
            var objSearchType = combo.getObject("SM7012_01_form_01", "schKeyField");
            
            combo.init.setData(this.dataSearchType);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(objSearchType);
        },
        initCombo_2 : function() {
            var objSearchKeyLike = combo.getObject("SM7012_01_form_01", "schKeyLike");
            
            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"LIKE"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");
            
            combo.create(objSearchKeyLike);
        }
    },
    // 챠트 생성
    chart : {},
    // 툴팁 생성
    tooltip : {},
    // 데이터 그리드 생성
    datagrid : {
    	header_1 : [//Field ID |  Field Name |  Width Size |  Align |  halign |  Sort |  hidden |  editor type |  checkbox |  format |  rowspan |  colspan |  frozen |  styler
            ["SUBJECT"              ,resource.getMessage("TITLE")           ,550    ,"left"    ,"center"    ,true       ,false    ,null         ,null     ,{format:"function", pid:"SM7012_01", name:"showNewIcon"}    ,0        ,0],
            ["FILE_COUNT"           ,resource.getMessage("TXT_TOTAL_FLIES") ,100    ,"right"   ,"center"    ,true       ,false    ,null         ,null     ,null    ,0        ,0],
            ["USER_NAME"            ,resource.getMessage("WRTER")           ,130    ,"center"  ,"center"    ,true       ,false    ,null         ,null     ,null    ,0        ,0],
            ["CREATE_DATE"          ,resource.getMessage("TXT_CREATE_DAY")  ,140    ,"center"  ,"center"    ,true       ,false    ,null         ,null     ,        ,0        ,0],
            ["READ_COUNT"           ,resource.getMessage("TXT_READ_COUNT")  ,80     ,"center"  ,"center"    ,true       ,true     ,null         ,null     ,null    ,0        ,0]
        ],
        initGrid_1 : function() {
            var params = form.handle.getElementsParam("SM7012_01_form_01");
            var dg_1 = grid.getObject("SM7012_01_grid_01");
            
            grid.init.setURL("/fm/sm/sm7013_01/selectQnAList");
            grid.init.setQueryParams(params);
            grid.init.setPage(true);
            grid.init.setEmptyMessage(false);
            grid.init.setFitColumns(false);
            
            grid.event.setOnDblClickRow(dg_1);
            
            Theme.defaultGrid(dg_1, this.header_1);
        }
    },
    // 이벤트 처리
    event : {
    	onDblClick_SM7012_01_grid_01 : function(rowData) {
            SM7012_01.dialog.openDialog_1('update');
        }
    },
    // 업무구현
    control : {
    	selectQnAList : function() {
    		var params = form.handle.getElementsParam("SM7012_01_form_01");
    		var dg_1 = grid.getObject("SM7012_01_grid_01");
    		
    		grid.handle.sendRedirect(dg_1, "/fm/sm/sm7013_01/selectQnAList", params);
    	}
    },
    // 다이얼로그 구현
    dialog : {
    	openDialog_1 : function(flag) {
            var dg_1 = grid.getObject("SM7012_01_grid_01");
            
            if (flag == "update" && !grid.handle.isSelected(dg_1)) {
                alert(resource.getMessage("MSG_NO_SELECT"));
                return;
            }
            
            var params = new Object();
            
            if(flag == "insert") {
                params.FILE_COUNT = 0;
            } else if(flag == "update") {
                params = grid.handle.getSelectedRowData(dg_1);
            }
            params.COMPANY_CD = form.handle.getValue("SM7012_01_form_01", "COMPANY_CD");
            params.USER_ID = form.handle.getValue("SM7012_01_form_01", "USER_ID");
            params.USER_NAME = form.handle.getValue("SM7012_01_form_01", "USER_NAME");
            params.flag = flag;
            
            var dl_1= dialog.getObject("SM7012_02_dialog_01");
            
            dialog.init.setTitle(resource.getMessage("SM7012_02"));
            dialog.init.setWidth(800); 
            dialog.init.setHeight(552);
            dialog.init.setURL("/fm/sm/sm7012_02");
            
            dialog.init.setQueryParams(params);
            
            dialog.open(dl_1);
        }
    },
    // 화면 UI 변경
    ui : {
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
    file : {}, 
    // 엑셀다운로드 구현
    excel : {},
    // 화면 초기 설정
    config : {
        applyFtaNation : function() {}
    }
};
