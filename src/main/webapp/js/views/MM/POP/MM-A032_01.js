/******************************************************************************************
 * 작성자 : carlos
 * Program Id : MM-A032_01
 * 작업일자 : 2016.03.28
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMA032_01.config.applyFtaNation();
    MMA032_01.init.initComponent();
}

var MMA032_01 = {
    init : {
        initComponent : function() {
            MMA032_01.combobox.initCombo_1();
            MMA032_01.combobox.initCombo_2();
            
            MMA032_01.datagrid.initGrid_1();
        }
    }, // 초기값 설정
    calendar : {
        initCalendar_1 : function() {
        }
    }, // 달력 그리드 생성
    combobox : {
    	data_1 : [
	        {CODE:"COMPANY_NAME", NAME:resource.getMessage("CONPY, NAME")},
	        {CODE:"COMPANY_CD", NAME:resource.getMessage("CONPY, CODE")},
	        {CODE:"BUSINESS_NO", NAME:resource.getMessage("BZRGN")}
	    ],
	    initCombo_1 : function() {
            var obj = combo.getObject("MMA032_01_form_01", "schKeyField");

            combo.init.setData(this.data_1);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");

            combo.create(obj);
        },
        initCombo_2 : function() {
            var obj = combo.getObject("MMA032_01_form_01", "schKeyLike");

            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"LIKE"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");

            combo.create(obj);
        }
    },
    datagrid : {
        header : [
        	["COMPANY_CD"            ,resource.getMessage("CONPY, CODE")        ,100    ,"center"    ,"center",    true,       false, null, null, null, 0, 0],
            ["COMPANY_NAME"          ,resource.getMessage("CONPY,NAME")         ,140    ,"left"      ,"center",    true,       false, null, null, null, 0, 0],
            ["ADDRESS1"              ,resource.getMessage("ADDRS")              ,260    ,"left"      ,"center"    ,true       ,false     ,null         ,null     ,null    ,0        ,0],
            ["OFFICER_PHONE_NO"      ,resource.getMessage("TELNM")              ,100    ,"center"    ,"center",    true,       false, null, null, null, 0, 0],
	        ["PARENT_COMPANY_NAME"   ,resource.getMessage("RPSNT, CONPY")       ,140    ,"left"      ,"center"    ,true       ,false     ,null         ,null     ,null    ,0        ,0],
	        ["MGMT_COMPANY_NAME"     ,resource.getMessage("MGT, ORGNZT")         ,140    ,"left"      ,"center"    ,true       ,false, null, null, null, 0, 0]
        ],
        initGrid_1 : function() {
        	var params = form.handle.getElementsParam("MMA032_01_form_01");
        	var dg_1 = grid.getObject("MMA032_01_grid_01");
            
        	grid.init.setQueryParams(params);
        	grid.init.setURL("/mm/pop/mmA032_01/selectCompanyList");
            grid.init.setPage(true);
            grid.init.setFitColumns(true);
            grid.event.setOnDblClickRow(dg_1);
            
            // 기본 데마를 적용한 그리드를 생성한다.
            Theme.defaultGrid(dg_1, this.header);
        }
    },
    event : { // 이벤트 처리
        onDblClick_MMA032_01_grid_01 : function(rowData) {
            var pid = form.handle.getValue("MMA032_01_form_01", "TARGET_PID");
            
            if (!oUtil.isNull(pid)) {
                var pro_id = eval("window." + pid + ".dialog");
                
                if (typeof(pro_id["callByMMA032_01"]) == "function") {
                    pro_id["callByMMA032_01"](rowData);
                }
            }
        },
        callBackNoData : function(data) {}
    },
    control : {// 업무구현
        selectMainList : function() {
            var dg_1 = grid.getObject("MMA032_01_grid_01");
            var params = form.handle.getElementsParam("MMA032_01_form_01");
            
            grid.handle.sendRedirect(dg_1, "/mm/pop/mmA032_01/selectCompanyList", params);
        },
        selectRowValue : function() {
            var dg_1 = grid.getObject("MMA032_01_grid_01");
            var rowData = grid.handle.getSelectedRowData(dg_1);
            
            if (!grid.handle.isSelected(dg_1)) {
                alert(resource.getMessage("MSG_SELECT_GRID_FIRST"));
                return;
            }
            
            MMA032_01.event.onDblClick_MMA032_01_grid_01(rowData);
        }
    },
    dialog : {
        // 다이얼로그 구현
    },
    file : {
        // 파일 input 엘리먼트 구현
    },
    excel : {
        // 엑셀다운로드 구현
    },
    config : {
      	applyFtaNation : function() {
      		if(form.handle.getValue("MMA032_01_form_01", "USER_ID") == "fta") {
      			form.handle.setValue("MMA032_01_form_01", "PARENT_COMPANY_CD", "");
      		}
      	}
     }
    
};
