/******************************************************************************************
 * 작성자 : YNI-Maker
 * Program Id : DE-0000_01
 * 작업일자 : 2016.09.28
 * 
 ******************************************************************************************/

function onLoadPage() {
    MMA029_01.config.applyFtaNation();
    MMA029_01.init.initComponent();
}

var MMA029_01 = {
    
    // 초기값 설정
    init : {
        initComponent : function() {}
    }, 
    // 달력 생성
    calendar : {}, 
    // 콤보박스 생성
    combobox : {},
    // 챠트 생성
    chart : {},
    // 툴팁 생성
    tooltip : {},
    // 트리
    tree : {},
    // 데이터 그리드 생성
    datagrid : {},
    // 이벤트 처리
    event : {},
    // 업무구현
    control : {},
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
