function fnCall(method, url, params){
    $.ajax({
        type : method,           // 타입 (get, post, put 등등)
        url : url,           // 요청할 서버url
        async : false,            // 비동기화 여부 (default : true)
        headers : {              // Http header
        // "Content-Type" : "application/json",
        "Content-Type" : "application/x-www-form-urlencoded; charset=UTF-8"
        // "X-HTTP-Method-Override" : "POST"
        },
        dataType : 'json',       // 데이터 타입 (html, xml, json, text 등등)
        data : params ,
        // data : JSON.stringify({  // 보낼 데이터 (Object , String, Array)
        //     params
        // }),
        success : function(result) { // 결과 성공 콜백함수
            console.log(result);
            return result ;
        },
        error : function(request, status, error) { // 결과 에러 콜백함수
            console.log(error)
            return error;
        }
    });
}