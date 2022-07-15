/******************************************************************************************
 * 작성자 : sbj1000
 * Program Id : SM-7011_01
 * 작업일자 : 2016.05.11
 *
 ******************************************************************************************/

function onLoadPage() {
    SM7011_01.init.initComponent();
}

var SM7011_01 = {

    init : {
        initComponent : function() {
            SM7011_01.combobox.initCombo_1();
            SM7011_01.datagrid.initGrid_1();
        }
    }, // 초기값 설정
    calendar : {}, // 달력 그리드 생성
    combobox : {
        initCombo_1 : function() {
            var obj_01 = combo.getObject("SM7011_01_form_01", "USE_YN");
            
            combo.init.setURL("/mm/cbox/selectStandardCode");
        	combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"YN", ALL:"Y"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");

            combo.create(obj_01);
        }
    }, // 콤보박스 그리드 생성
    datagrid : { // 데이터 그리드 생성
        header_1 : [//Field ID |  Field Name |  Width Size |  Align |  halign |  Sort |  hidden |  editor type |  checkbox |  format |  rowspan |  colspan |  frozen |  styler
                    ["SUBJECT"              ,resource.getMessage("TITLE")           ,500    ,"left"    ,"center"    ,true       ,false    ,null         ,null     ,{format:"function", pid:"SM7011_01", name:"showNewIcon"}    ,0        ,0],
                    ["BBS_TYPE_NAME"        ,resource.getMessage("TXT_BBS_TYPE")    ,100    ,"center"  ,"center"    ,true       ,false    ,null         ,null     ,null    ,0        ,0],
                    ["START_DATE"           ,resource.getMessage("TXT_START_DATE")  ,100    ,"left"    ,"center"    ,true       ,false    ,null         ,null     ,{format:'date'}    ,0        ,0],
                    ["END_DATE"             ,resource.getMessage("TXT_BBS_END_DATE"),100    ,"left"    ,"center"    ,true       ,false    ,null         ,null     ,{format:'date'}    ,0        ,0],
                    ["CREATE_DATE"          ,resource.getMessage("TXT_CREATE_DAY")  ,150    ,"center"  ,"center"    ,true       ,false    ,null         ,null     ,null    ,0        ,0]
        ],
        initGrid_1 : function() {
        	var params = form.handle.getElementsParam("SM7011_01_form_01");
        	var dg_1 = grid.getObject("SM7011_01_grid_01");
            
            grid.init.setPage(true);
            grid.init.setURL("/fm/sm/sm7011_01/selectNoticeList");
            grid.init.setQueryParams(params);
            grid.init.setEmptyMessage(false);
            
            grid.event.setOnDblClickRow(dg_1);
            
            Theme.defaultGrid(dg_1, this.header_1);
        }
    },
    event : { // 이벤트 처리
        onDblClick_SM7011_01_grid_01 : function(rowData) {
            SM7011_01.dialog.openDialog_1('update');
        }
    },
    control : {// 업무구현.
        selectNoticeList : function() {
        	var params = form.handle.getElementsParam("SM7011_01_form_01");
            var dg_1 = grid.getObject("SM7011_01_grid_01");

            grid.handle.sendRedirect(dg_1, "/fm/sm/sm7011_01/selectNoticeList", params);
        }
    },
    dialog : { /******** dialog 구현 *********/
        openDialog_1 : function(flag) {
            var dg_1 = grid.getObject("SM7011_01_grid_01");
            var dl_1 = dialog.getObject("SM7011_02_dailog_01");

            if(flag != "insert" && !grid.handle.isSelected(dg_1)) {
                alert(resource.getMessage("MSG_NO_SELECT"));
                return;
            }

            // 팝업으로 넘겨줄 파라메터 생성
            var params = {};
            var params1 = {};
            var params2 = {};

            if(flag != "insert") params2 = grid.handle.getSelectedRowData(dg_1);

            $.extend(params, params1, params2);

            params.flag = flag;
            params.COMPANY_CD = form.handle.getValue("SM7011_01_form_01", "COMPANY_CD");
            params.TARGET_PID = "SM7011_01";
            
            // 팝업 셋팅
            dialog.init.setTitle(resource.getMessage("SM7011_02"));
            dialog.init.setWidth(800);
            dialog.init.setHeight(495);
            dialog.init.setURL("/fm/sm/sm7011_02");
            dialog.init.setQueryParams(params);

            dialog.open(dl_1);
        }
    },
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
    file : { },
    excel : { }
};
