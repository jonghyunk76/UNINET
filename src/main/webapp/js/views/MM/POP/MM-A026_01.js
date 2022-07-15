/******************************************************************************************
 * 작성자 : carlos
 * Program Id : MM-A026_01
 * 작업일자 : 2016.03.28
 * 
 ******************************************************************************************/

function onLoadPage() {
    MMA026_01.init.initComponent();
}

var MMA026_01 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {
            MMA026_01.ui.viewChangeUI();
        }
    }, 
    // 달력 그리드 생성
    calendar : {
        initCalendar_1 : function() {
            //console.log("call calendar.initCalendar_1() ");
        }
    }, 
    // 콤보박스 그리드 생성
    combobox : {
        initCombo_1 : function() {
            //console.log("call combobox.initCombo_1() ");
        }
    },
    // 데이터 그리드 생성
    datagrid : {
        header1 : [] ,
        initGrid_1 : function() {
            //console.log("call datagrid.initGrid_1()");
        }
    },
    // 이벤트 처리
    event : {
    },
    // 업무구현
    control : {
        
    },
    // 다이얼로그 구현
    dialog : {
    },
    // 화면 UI 변경
    ui : {
        viewChangeUI : function() {
            var dl_1 = dialog.getObject("MMA026_01_dailog_01");
            var oImageInfo = $("#imageInfo");
            
            oImageInfo.load(function () {
                var nImageWidth = $("#imageInfo").width();
                var nImageHeight = $("#imageInfo").height();

                dialog.handle.resize("MMA026_01_dailog_01", nImageWidth, nImageHeight);
                MMA026_01.ui.resetCenterLayout();
            });
        },
        // 20160503::carlos::추가함
        resetCenterLayout: function() {
            var dl_1 = dialog.getObject("MMA026_01_dailog_01");
            
            var nDlWidth = dl_1.width();
            var nDlHeight = dl_1.height(); 
            
            var nMoveLeft = ($(window).scrollLeft() + ($(window).width() - nDlWidth) / 2);
            var nMoveTop = ($(window).scrollTop() + ($(window).height() - nDlHeight) / 2);
            
            if (nMoveLeft < 0) nMoveLeft = 0;
            if (nMoveTop < 0)  nMoveTop = 0;
            
            dl_1.dialog("move", {left:nMoveLeft, top:nMoveTop});
        }
    },
    // 파일 input 엘리먼트 구현
    file : {
        
    },
    // 엑셀다운로드 구현
    excel : {
        
    }
    
};
