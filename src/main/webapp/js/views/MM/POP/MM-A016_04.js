/******************************************************************************************
 * 작성자 : carlos
 * Program Id : MMA016_04
 * 작업일자 : 2016.03.28
 * 
 ******************************************************************************************/

function onLoadPage() {
    MMA016_04.init.initComponent();
}

var MMA016_04 = {
    
    init : {
        initComponent : function() {
        	var data = form.handle.getElementsParam("MMA016_01_form_01");
        	form.handle.loadData("MMA016_04_form_01", data);
        	
        	MMA016_04.combobox.initCombo_1();
        	
        	MMA016_04.control.selectExcelDataStatus();
        	
        	MMA016_04.datagrid.initGrid_1();
        }
    },
    chart : {},
    calendar : {},
    combobox : {
    	data_1 : [
    		{CODE:"", NAME:resource.getMessage("SELET")},
    		{CODE:"S", NAME:resource.getMessage("SUCES")},
	        {CODE:"F", NAME:resource.getMessage("FAIL")}
		],
		initCombo_1 : function() {
	      var obj_1 = combo.getObject("MMA016_04_form_02", "ERROR_CODE");
	      
	      combo.init.setData(this.data_1);
	      combo.init.setValueField("CODE");
	      combo.init.setNameField("NAME");
	      combo.init.setCallFunction("onChangeErrorCode");
	      
	      combo.create(obj_1);
	  }
    }, // 콤보박스 그리드 생성
    datagrid : { // 데이터 그리드 생성
        initGrid_1 : function() {
        	var params = form.handle.getElementsParam("MMA016_04_form_01");
            var dg_1 = grid.getObject("MMA016_04_grid_01");
            
            grid.init.setPage(true);
            grid.init.setURL("/mm/pop/mmA016_02/selectExcelData");
            grid.init.setQueryParams(params);
            grid.init.setEmptyMessage(false);
            grid.init.setFilter(false); // 그리드  해더에 필터 적용
            
            Theme.defaultGrid(dg_1, {url:"/mm/pop/mmA016_04/selectExcelHeaderList", params:params});
        }
    },
    event : {
    	selectExcelDataStatusAfterUI : function(data) {
    		form.handle.loadData("MMA016_04_form_01", data);
    		
    		MMA016_04.ui.changeCompleteButtom();
    	},
    	onChangeErrorCode : function(data) {
    		combo.handle.setValue("MMA016_04_form_01", "ERROR_CODE", data);
    		
    		MMA016_04.control.selectExcelData();
    	}
    },
    control : {
    	selectExcelDataStatus : function() {
            var obj = form.getObject("MMA016_04_form_01");
            
            form.init.setURL("/mm/pop/mmA016_04/selectExcelDataStatus");
            form.init.setCallBackFunction("selectExcelDataStatusAfterUI");
            form.init.setValidationFlag(false);
            form.init.setProgressFlag(false);
            
            form.submit(obj);
        },
        selectExcelData : function() {
    		var params = form.handle.getElementsParam("MMA016_04_form_01");
            var dg_1 = grid.getObject("MMA016_04_grid_01");
            
            grid.handle.sendRedirect(dg_1, "/mm/pop/mmA016_02/selectExcelData", params);
        }
    },
    dialog : {},
    ui : {
    	changeCompleteButtom : function() {
    		var total = form.handle.getValue("MMA016_04_form_01", "TOT_ROW_CNT");
    		var success = form.handle.getValue("MMA016_04_form_01", "SUCC_ROW_CNT");
    		var noInput = form.handle.getValue("MMA016_04_form_01", "REQUIRED_NOINPUT_CNT");
    		
    		if(!oUtil.isNull(total)) {
	    		if(total > 0 && total == success && noInput == 0) {
	    			form.util.removeClass("MMA016_01_btn_03", "btnDisableProc");
	                form.util.addClass("MMA016_01_btn_03", "btnEnableProc");
	    		}
    		}
    	}
    },
    file : {},
    excel : {
    	excelDownload_1 : function() {
            var dg_1 = grid.getObject("MMA016_04_grid_01");
            var fobj = form.getObject("MMA016_04_form_01");
            var title = form.handle.getValue("MMA016_04_form_01", "IF_NAME");
            
            form.init.setURL("/mm/pop/mmA016_02/selectExcelData.fle");
            
            // parameter description : grid객체, form객체, 파일명, 시트명
            form.excelSubmit(dg_1, fobj, title, title);
        }
    }
};
