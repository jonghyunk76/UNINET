$(this).ready(function() { // window 로드전에 실행됨
    if (typeof (window["onLoadPage"]) == "function") {
    	$(":input").each(function(){
    		$(this).attr("title", $(this).val());
    		
    		$(this).keyup(function(event) {
        		var busVal = $(this).val();
        		var read = $(this).attr("readonly");
        		
        		if(!(read || $(this).hasClass("readOnly") || $(this).hasClass("search_readOnly") || $(this).hasClass("input_readOnly"))) {
	        		if(event.which === 38 && event.ctrlKey) {
	        			$(this).val(busVal.toUpperCase());
	        		} else if(event.which === 40 && event.ctrlKey) {
	        			$(this).val(busVal.toLowerCase());
	        		}
        		}
    		});
    	});
    	
        // 모든 페이지에 필수로 적용될 함수지정
        window["onLoadPage"](); // 화면 초기화
    }
});