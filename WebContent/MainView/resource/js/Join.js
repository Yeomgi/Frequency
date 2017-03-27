/**
 * Title : Frequency 회원가입 js
 * Author : 염형준
 * Date : 2017-03-03
 */


var rightPatternId = false;
var rightPatternPw = false;
var rightPatternNick = false;

// form 태크 값 체크
function checkFormValue(form) {

    if( form.id.value=="" ){
        focusForm(form.id);
        return;
    }
    if( form.pw.value=="" ){
        focusForm(form.pw);
        return;
    }
    if( form.pw.value!=form.pwre.value ){
        focusForm(form.pwre);
        return;
    }
    if( form.nickname.value=="" ){
        focusForm(form.nickname);
        return;
    }
    if( form.email.value=="" ){
        focusForm(form.email);
        return;
    }
    if( form.id.getAttribute('readonly')===null ){
        alert("아이디 중복 검사를 해주세요");
        return;
    }
    if( form.nickname.getAttribute('readonly')===null ){
        alert("별명 중복 검사를 해주세요");
        return;
    }
    if( !rightPatternPw ){
        alert("비밀번호가 형식에 맞지 않습니다.");
        focusForm(form.pw);
        return;
    }

    form.action = "joindone.do";
    form.submit();

}

// Pw일치여부 검사
function isEqualPw(form) {
    var pwreMessage = document.getElementById("pwreMessage");
    if( form.pw.value==form.pwre.value )
        pwreMessage.innerHTML = "일치 합니다";
    else
        pwreMessage.innerHTML = "일치 하지 않습니다";
}

// 아이디, 별명 중복체크
function isExistValue(form, name, tag) {

    var inputTag;

    switch ( name ) {
        case "id":
            inputTag = form.id;
            break;
        case "nickname":
            inputTag = form.nickname;
            break;
    }

    var type = 'type='+inputTag.name;
    var value = 'value='+inputTag.value;
    value = type+'&'+value;

    //  값의 종류와 옳은 패턴의 값인지 검사
    if( (inputTag.name=="id" && rightPatternId) || (inputTag.name=="nickname" && rightPatternNick) ){
        getAjax(
            "joinExist.ajax",
            value,
            function (data) {
                // 중복여부를 받아와 사용자에게 출력
                if( data==true ){
                    if( confirm( "입력하신 '"+inputTag.value+"'를 사용하실수 있습니다. 사용하시겠습니까?" ) ){
                        inputTag.style.backgroundColor = "#EEE";
                        inputTag.setAttribute("readonly","readonly");
                        tag.onclick = null;
                    }
                }
                else if( data==false ){
                    alert(inputTag.name+"이(가) 이미 존재합니다.");
                }
            }
        );
    }
    else{
        alert(inputTag.name+"가 형식에 맞지 않습니다.");
    }

}

// ID,PW,NICKNAME 유효성 검사
function isRightPatturn(tag) {

    var pattern;
    var type = tag.name;
    var value = tag.value;
    var message;

    // 값의 종류에따라 유효성 검사 후 (T/F) 반환
    switch ( type ) {
        case "id":
            pattern = isRightIdPattern(value);
            message = document.getElementById("idMessage");
            break;
        case "pw":
            pattern = isRightPwPattern(value);
            message = document.getElementById("pwMessage");
            break;
        case "nickname":
            pattern = isRightNicknamePattern(value);
            message = document.getElementById("nicknameMessage");
            break;
    }

    // 반환된 값에 따라 페이지에 출력
    if( pattern===0 ){
        message.innerHTML = "형식에 맞습니다";
        changeBoolean(type,true);
    }
    else if ( pattern===-1 ){
        message.innerHTML = "형식에 맞지 않습니다";
        changeBoolean(type,false);
    }

    // 중복체크에 쓰일 boolean변수 설정 함수
    function changeBoolean(type, boolean) {
        switch ( type ) {
            case "id":
                rightPatternId = boolean;
                break;
            case "pw":
                rightPatternPw = boolean;
                break;
            case "nickname":
                rightPatternNick = boolean;
                break;
        }
    }

    // 영문으로 시작하고 영문+숫자로 구성된 최소 6자리 ID 형식체크 함수
    function isRightIdPattern(value){
        return value.search( /^(?=^[a-zA-Z])(?=.*\d)[0-9a-zA-Z]{6,}$/gm );
    }
    // 영문+숫자+특문 포함 최소 8자리 PW 형식체크 함수
    function isRightPwPattern(value){
        return value.search( /^(?=.*\d)(?=.*[a-zA-Z])(?=.*[~!@#$%^&*()\\-\_=+|\[\]\{\};:'",.<>/?]).{8,}$/gm );
    }
    // 최소 4자리 별명 형식체크 함수
    function isRightNicknamePattern(value){
        return value.search( /^.{4,}$/gm );
    }

}

 
 
