/******************************************************************************************
 * 작성자 : 김종현
 * Program Id : SM-8003_01
 * 작업일자 : 2021.12.15
 * 
 ******************************************************************************************/

function onLoadPage() {
    SM8003_01.config.applyFtaNation();
    SM8003_01.init.initComponent();
}

var SM8003_01 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
            SM8003_01.datagrid.initGrid_1(null);
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
    datagrid : {
    	header_1 : [
            ["HS_CD"           ,resource.getMessage("HS_CODE")  ,100     ,"center"  ,"center"    ,true       ,false     ,null         ,null     ,null    ,0        ,0],
            ["HS_DESC"           ,resource.getMessage("TXT_PRODUCT_SPEC")  ,200     ,"left"  ,"center"    ,true       ,false     ,null         ,null     ,null    ,0        ,0],
            ["NATION_CD"       ,resource.getMessage("COTRY_CODE")  ,100     ,"center"  ,"center"    ,true       ,false     ,null         ,null     ,null    ,0        ,0],
            ["RATE_SE_DESC"           ,resource.getMessage("TRAFF_TYPE")+resource.getMessage("DSCPT")  ,160     ,"left"  ,"center"    ,true       ,false     ,null         ,null     ,null    ,0        ,0],
            ["TARRATE_SE_NM"   ,resource.getMessage("TRAFF_TYPE")  ,100     ,"center"  ,"center"    ,true       ,false     ,null         ,null     ,null    ,0        ,0],
            ["RATE_DESC"           ,resource.getMessage("STNDS_TRAFF")+resource.getMessage("DSCPT")  ,130     ,"left"  ,"center"    ,true       ,false     ,null         ,null     ,null    ,0        ,0],
            ["TARRATE"         ,resource.getMessage("STNDS_TRAFF")  ,100     ,"center"  ,"center"    ,true       ,false     ,null         ,null     ,null    ,0        ,0]
        ],
        initGrid_1 : function(data) {
            var dg_1 = grid.getObject("SM8003_01_grid_01");
            
            grid.init.setData(data);
            grid.init.setPage(false);
            grid.init.setEmptyMessage(false);
            grid.init.setFitColumns(false);
            
            Theme.defaultGrid(dg_1, this.header_1);
        }
    },
    // 이벤트 처리
    event : {
        executePythonFile : function(data) {
            form.handle.loadData("SM8003_01_form_02", data);
            
            SM8003_01.datagrid.initGrid_1(data.rows);
        }
    },
    // 업무구현
    control : {
    	executePythonFile : function() {
            form.handle.setValue("SM8003_01_form_02", "RESULT_DATA", "");
            
    		var obj = form.getObject("SM8003_01_form_01");
                        
            form.init.setURL("/fm/sm/SM8003_01/executePythonFile");
            // form.init.setSucceseMessage(resource.getMessage("MSG_SUCCESS_BODY"));
            form.init.setFailMessage(resource.getMessage("MSG_SAVE_FAILURE"));
            form.init.setCallBackFunction("executePythonFile");
            
            form.submit(obj);
    	}
    },
    // 다이얼로그 구현
    dialog : {},
    // 화면 UI 변경
    ui : {}, 
    // 파일 input 엘리먼트 구현
    file : {}, 
    // 엑셀다운로드 구현
    excel : {},
    // 화면 초기 설정
    config : {
        applyFtaNation : function() {}
    }
};
