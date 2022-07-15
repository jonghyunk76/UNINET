/******************************************************************************************
 * 작성자 : carlos
 * Program Id : MM-A006_01
 * 작업일자 : 2016.03.28
 * 
 ******************************************************************************************/

function onLoadPage() {
	MMA006_01.config.applyFtaNation();
    MMA006_01.init.initComponent();
}

var MMA006_01 = {
    init : {
        initComponent : function() {
            MMA006_01.combobox.initCombo_1();
            MMA006_01.combobox.initCombo_2();
            
            MMA006_01.datagrid.initGrid_1();
        }
    }, // 초기값 설정
    calendar : {
        initCalendar_1 : function() {
        }
    }, // 달력 그리드 생성
    combobox : {
    	data_1 : [
	         {CODE:"VENDOR_NAME", NAME:resource.getMessage("PATNE, NAME")}
	        ,{CODE:"VENDOR_CD", NAME:resource.getMessage("PATNE, CODE")}
	        ,{CODE:"BUSINESS_NO", NAME:resource.getMessage("BZRGN")}
	    ],
	    initCombo_1 : function() {
            var obj = combo.getObject("MMA006_01_form_01", "schKeyField");

            combo.init.setData(this.data_1);
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");

            combo.create(obj);
        },
        initCombo_2 : function() {
            var obj = combo.getObject("MMA006_01_form_01", "schKeyLike");

            combo.init.setURL("/mm/cbox/selectStandardCode");
            combo.init.setQueryParams({COMPANY_CD:SESSION.COMPANY_CD, CATEGORY_CD:"LIKE"});
            combo.init.setValueField("CODE");
            combo.init.setNameField("NAME");

            combo.create(obj);
        }
    }, // 콤보박스 그리드 생성
    datagrid : { // 데이터 그리드 생성
        header : [
          ["VENDOR_CD"  , resource.getMessage("TXT_PARTNER_CD") /*협력사코드*/ ,100, "center", "center", true, false, null, null, null, 0, 0],
          ["VENDOR_NAME", resource.getMessage("TXT_PARTNER_NM") /*협력사명*/  ,150, "left"  , "center", true, false, null, null, null, 0, 0],
          ["BUSINESS_NO"        ,resource.getMessage("BZRGN")              ,100    ,"center"      ,"center"    ,true       ,false     ,null         ,null     ,null    ,0        ,0],
	      ["ADDRESS"            ,resource.getMessage("ADDRS")              ,300    ,"left"      ,"center"    ,true       ,true     ,null         ,null     ,null    ,0        ,0],
	      ["ADDRESS_ENG"        ,resource.getMessage("ADDRS")              ,300    ,"left"      ,"center"    ,true       ,true     ,null         ,null     ,null    ,0        ,0]
       ],
       initGrid_1 : function() {
            var dg_1 = grid.getObject("MMA006_01_grid_01");
            
            grid.init.setPage(true);
            grid.init.setFitColumns(true);
            grid.event.setOnDblClickRow(dg_1);
            
            // 기본 데마를 적용한 그리드를 생성한다.
            Theme.defaultGrid(dg_1, this.header);
        }
    },
    event : { // 이벤트 처리
        onDblClick_MMA006_01_grid_01 : function(rowData) {
            var pid = form.handle.getValue("MMA006_01_form_01", "TARGET_PID");
            
            if (!oUtil.isNull(pid)) {
                var pro_id = eval("window." + pid + ".dialog");
                
                if (typeof(pro_id["callByMMA006_01"]) == "function") {
                    pro_id["callByMMA006_01"](rowData);
                }
            }
        },
        callBackNoData : function(data) {}
    },
    control : {// 업무구현
        selectMainList : function() {
            // 검색창의 validation 체크결과를 비교한다.
            if (!form.handle.isValidate("MMA006_01_form_01")) {
                return;
            }
            
            var dg_1 = grid.getObject("MMA006_01_grid_01");
            var params = form.handle.getElementsParam("MMA006_01_form_01");
            
            grid.handle.sendRedirect(dg_1, "/mm/pop/mmA006_01/selectMainList", params);
        },
        selectRowValue : function() {
            var dg_1 = grid.getObject("MMA006_01_grid_01");
            var rowData = grid.handle.getSelectedRowData(dg_1);
            
            if (!grid.handle.isSelected(dg_1)) {
                alert(resource.getMessage("MSG_SELECT_GRID_FIRST"));
                return;
            }
            
            MMA006_01.event.onDblClick_MMA006_01_grid_01(rowData);
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
      		var columns1 = MMA006_01.datagrid.header;
      		
  			if(SESSION.FTA_NATION == "MX") {
  				grid.util.changeHeaderHidden(columns1 , "ADDRESS", false);
  				grid.util.changeHeaderHidden(columns1 , "ADDRESS_ENG", true);
  			} else {
  				grid.util.changeHeaderHidden(columns1 , "ADDRESS", true);
  				grid.util.changeHeaderHidden(columns1 , "ADDRESS_ENG", false);
  			} 
  		}
     }
    
};
