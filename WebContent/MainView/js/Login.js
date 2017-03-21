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

    // 페이지 이동
    form.action = "logindone.do";
    form.submit();

}
 
