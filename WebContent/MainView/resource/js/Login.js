/**
 * Title : Frequency 로그인 js
 * Author : 염형준
 * Date : 2017-03-20
 */
function login(form) {

    // 값의 공백여부 체크
    if( form.id.value=="" ){
        focusForm(form.id);
        return;
    }
    if( form.pw.value=="" ){
        focusForm(form.pw);
        return;
    }

    // json 객체로 만든다
    var json = {
      id : form.id.value,
      pw : form.pw.value
    };

    // 전송하여 확인
    postAjax(
        "logincheck.ajax",
        JSON.stringify(json),
        "Application/json",
        function (data){
            data = window.Number(data);
            switch (data){
                case -1:
                    alert("이미 로그인중인 유저입니다.");
                    break;
                case 0:
                    alert("잘못된 정보를 입력하셨습니다.");
                    break;
                case 1:
                    form.action = "logindone.do";
                    form.submit();
                    break;
            }
        },
        function (readyState, status) {
            if (readyState == 4 && status != 500 && elements.chatBox.innerHTML.search(/서버 문제로 채팅을 진행할수 없습니다/gm) == -1) {
                alert("서버문제로 로그인을 할수 없습니다. 나중에 나시 시도하여 주십시오");
            }

        }
    );

}
 
