/**
 * Title : Frequency 페이지이동 공통js
 * Author : 염형준
 * Date : 2017-03-03
 */

/*<페이지 이동함수>*/
function movePage(tag) {

    // 등록한 타이틀을 받아온다
    var title = tag.title;

    // command을 객체로 맵핑해둔다
    var command = {
        main : "main.do",
        groupchat : "",
        freeboard : "",
        randomchat : "randomchat.do",
        notice : "",
        login : "login.do",
        logout : "logout.do",
        join : "join.do",
        findinfo : "",
        mypage : ""
    };

    // 해당하는 타이틀의 페이지로 이동
    location.href = command[title];

}
 
 
