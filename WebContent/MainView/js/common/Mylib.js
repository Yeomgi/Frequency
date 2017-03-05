/**
 * Title : Personal Library
 * Author : 염형준
 *  Date : 2017-01 ~
 */


/*페이지 출력 테스트*/
function pagePrint(str) {
    if( str===null || str===undefined ) { document.write(""); }
    else{ document.write(str);}
}
function pagePrintln(str) {
    if( str===null || str===undefined ) { document.write("<br>"); }
    else{ document.write(str+"<br>");}
}

/*동적 .css, .js 할당 ( tag내 title속성 필요 )*/
function setCSS(title) {
    var linklist = document.getElementsByTagName('link');
    var i;
    for(i=0; i<linklist.length; i++) {
        if (linklist[i].getAttribute('title'))
            linklist[i].disabled = true;
    }
    for(i=0; i<linklist.length; i++) {
        if (linklist[i].getAttribute('title') && linklist[i].getAttribute('title') == title )
            linklist[i].disabled = false;
    }
}
function setJS(title) {
    var scriptlist = document.getElementsByTagName('script');
    var i;
    for(i=0; i<scriptlist.length; i++) {
        if (scriptlist[i].getAttribute('title'))
            scriptlist[i].disabled = true;
    }
    for(i=0; i<scriptlist.length; i++) {
        if (scriptlist[i].getAttribute('title') && scriptlist[i].getAttribute('title') == title )
            scriptlist[i].disabled = false;
    }
}

/*Ajax*/
function getAjax(url,value,successFx,errorFx) {
    var XML = new XMLHttpRequest();
    XML.onreadystatechange = function () {
        if( this.readyState==4 && this.status==200 ){
            successFx(this.responseText);
        }
        else if( this.readyState==4 && this.status!=200 ){
            errorFx(this.readyState, this.status);
        }
    };
    url = url +'?'+ value;
    XML.open('get',url,true);
    XML.send();
}
function postAjax(url,value,contentType,successFx,errorFx) {
    var XML = new XMLHttpRequest();
    XML.onreadystatechange = function () {
        if( this.readyState==4 && this.status==200 ){
            successFx(this.responseText);
        }
        else if( this.readyState==4 && this.status!=200 ){
            errorFx(this.readyState, this.status);
        }
    };
    XML.open('post',url,true);
    XML.setRequestHeader('Content-Type',contentType);
    XML.send(value);
}

/*input 태그 focus*/
function focusForm(inputTag) {
    //css 효과
    inputTag.style.transition = "box-shadow 0.3s";
    inputTag.style.boxShadow = "0px 0px 15px #007aff";
    //포커스이동
    inputTag.focus();
    // 클릭 or 키 입력 시 css제거와 해당 입력함수제거
    inputTag.onmousedown = function () {
        inputTag.style.boxShadow = "none";
        inputTag.onmousedown = null;
        inputTag.onkeydown = null;
    };
    inputTag.onkeydown = function () {
        inputTag.style.boxShadow = "none";
        inputTag.onkeydown = null;
        inputTag.onmousedown = null;
    };
}

/*페이지 로딩시 함수적용*/
// 모든 컨텐츠 (images, script, css, etc) 로드 후 함수실행
function addOnload(fx) {
    window.onload = fx;
}
// DOM 트리만 로딩 되어도 함수실행행
function addDOMContentLoaded(fx) {
    window.addEventListener(
        "DOMContentLoaded",
        fx
    );
}

/*일시정지 함수*/
function sleep(ms) {
    var now = new Date();
    var stop = now.getTime()+ms;
    while (true){
        now = new Date().getTime();
        if(now.getTime()>stop)
            return;
    }
}


