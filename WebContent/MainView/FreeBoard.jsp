<%--
  Title : Frequency 자유게시판 리스트
  Author : 홍녕우, 염형준
  Date : 2017-04-12
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Frequency</title>
    <script src="resource/js/common/Mylib.js"></script>
    <script src="resource/js/common/PageMoving.js"></script>
    <script>

        // 전역객체
        var listObject;             // 목록 객체
        var pageNavigationObject;   // 페이지내비게이션 객체
        var buttonObject;           // 기능 객체
        var letterObject;           // 쪽지 객체

        // DOM Tree 로딩직후 실행
        addDOMContentLoaded(function () {

            listObject = {

                low : document.getElementsByClassName("lowtitle"),

                // 게시글 조회
                moveContent : function () {

                },
                // 이벤트 등록
                registFx : function () {
                    for(var i=0; i<this.low.length; i++)
                        this.low[i].onclick = this.moveContent;
                }

            };

            pageNavigationObject = {

                first : document.getElementById("nav_first"),
                last : document.getElementById("nav_last"),
                previous : document.getElementById("nav_previousTen"),
                next : document.getElementById("nav_nextTen"),
                thispage : document.getElementById("nav_thispage"),
                pages : document.getElementsByClassName("nav_page"),

                // 내비게이션 이벤트
                moveBoardPage : function () {

                    var uri;
                    var joint;
                    var value;
                    var button = this.id;
                    var pageNum = pageNavigationObject.thispage.innerHTML;

                    // 검색페이지인지에 따라 변수를 설정
                    if( location.href.search(/(keyword=.+?(?=(?:&|$)))/m)!=-1 ){
                        uri = "freeboard.do?"+RegExp.$1;
                        joint = "&";
                    }
                    else{
                        uri = "freeboard.do";
                        joint = "?";
                    }

                    // 내비 버튼에 따라 uri 구성
                    switch (button) {
                        case "nav_first":
                            joint = "";
                            value = "";
                            break;
                        case "nav_last":
                            value = "page=last";
                            break;
                        case "nav_previousTen":
                            pageNum = window.Number(pageNum)-10;
                            value = "page="+pageNum;
                            break;
                        case "nav_nextTen":
                            pageNum = window.Number(pageNum)+10;
                            value = "page="+pageNum;
                            break;
                        case "nav_thispage":
                            value = "page="+pageNum;
                            break;
                        default :
                            pageNum = this.innerHTML;
                            value = "page="+pageNum;
                    }

                    uri+=joint+value;

                    // 이동
                    location.href = uri;

                },

                // 이벤트 등록
                registFx : function () {
                    if(this.first!==null) this.first.onclick = this.moveBoardPage;
                    if(this.last!==null) this.last.onclick = this.moveBoardPage;
                    if(this.previous!==null) this.previous.onclick = this.moveBoardPage;
                    if(this.next!==null) this.next.onclick = this.moveBoardPage;
                    for(var i=0; i<this.pages.length; i++)
                        if(this.pages[i]!==null)
                            this.pages[i].onclick = this.moveBoardPage;
                    if(this.thispage!==null) this.thispage.onclick = this.moveBoardPage;
                }

            };

            buttonObject = {

                write : document.getElementById("write"),
                search : document.getElementById("search"),
                keyword : document.getElementById("keyword"),

                // 새글쓰기
                newWrite : function () {

                },
                // 검색
                searchList : function () {

                    // 검색어를 가져옴
                    var keyword = buttonObject.keyword.value;

                    // 검색어가 공백일경우 미검색 상태로 새로고침
                    if( keyword==="" )
                        location.href = "freeboard.do";
                    else
                        location.href = "freeboard.do?keyword="+keyword;

                },
                // 이벤트 등록
                registFx : function () {
                    this.write.onclick = this.newWrite;
                    this.search.onclick = this.searchList;
                    this.keyword.onkeyup = function (event){
                        if(event.keyCode===13)
                            buttonObject.searchList();
                    };
                }

            };

            letterObject = {

                lowNick : document.getElementsByClassName("lowNickname"),
                letterNick : document.getElementById("messageNickname"),
                conent : document.getElementById("letterMessage"),
                send : document.getElementById("send"),
                modal : document.getElementById('letterWindow'),
                close : document.getElementsByClassName("close")[0],

                // 모달창이 열릴때 작업
                openWindow : function () {

                    // boolean 변수
                    var login = false;      // 로그인여부
                    var nick = false;       // 본인에게 쪽지 했는지 여부

                    // 클릭한 태그의 이름을 가져와 닉네임에 넣는다
                    letterObject.letterNick.innerHTML = this.innerHTML;
                    var name = letterObject.letterNick;

                    // 로그인 여부검사
                    getAjax(
                        "isLogin.ajax",
                        null,
                        function (data) {
                            data = window.parseInt(data);
                            if( data===1 )
                                login=true;
                            else{
                                if(confirm("로그인이 필요합니다. 로그인 페이지로 이동하시겠습니까 ?"))
                                    location.href = "login.do";
                            }
                        }
                    );
                    if( !login ) return;

                    // 본인인 인지 여부검사
                    getAjax(
                        "getNickName.ajax",
                        null,
                        function (data) {
                            if( data!==name )
                                nick=true;
                            else
                                alert("자신에게 쪽지를 보낼수 없습니다.");
                        }
                    );
                    if( !nick ) return;

                    // 모달창 보이기
                    letterObject.modal.style.display = "block";

                },

                // 모달창 닫힐때 작업
                closeWindow : function () {
                    letterObject.letterMessage.value = "";
                    letterObject.modal.style.display = "none";
                },

                // 쪽지전송
                sendletter : function () {

                    // 보낼 메세지를 가져옴
                    var message = memberlistObject.letterMessage.value;

                    // 공백인지 확인
                    if(message==''){
                        focusForm(memberlistObject.letterMessage);
                        return;
                    }

                    // json 객체로만듬
                    var json = {
                        towhom : memberlistObject.nickname.innerHTML,
                        content : message
                    };

                    postAjax(
                        "sendMessage.ajax",
                        JSON.stringify(json),
                        "Application/json",
                        function (data) {
                            data = window.parseInt(data);
                            switch (data){
                                case 1:
                                    alert("메세지를 전송했습니다");
                                    break;
                                case 0:
                                    alert("메세지를 전송에 실패했습니다.");
                                    break;
                            }
                            memberlistObject.closeWindow();
                        },
                        function (readyState, status) {
                            if (readyState == 4 && status != 500 )
                                alert("현재 서버문제로 쪽지를 보낼수 없습니다.");
                        }
                    );

                },
                // 이벤트 등록
                registFx : function () {
                    for(var i=0; i<this.lowNick.length; i++)
                        this.lowNick[i].onclick = this.openWindow;
                    this.send.onclick = this.sendletter;
                    this.close.onclick = this.closeWindow;
                    window.onclick = function(event) {
                        if (event.target == this.modal)
                            this.closeWindow();
                    };
                }
            };

            listObject.registFx();
            pageNavigationObject.registFx();
            buttonObject.registFx();
            letterObject.registFx();

        });

    </script>
    <style>
        ul{
            list-style-type: none;
        }
        li{
            float: left;
            margin: 0 5px;
            background-color: #dd4e42;
            width: 20px;
            height: 20px;
        }
    </style>

</head>
<%--
    --<RequestScope Attribute>--
    freeboardList : 게시판 목록 정보 ArrayList
    fisrtpage : 첫 페이지
    thispage : 현재 페이지
    lastpage : 마지막 페이지
    keyword : 검색어
    --<RequestScope Attribute>--
--%>
<c:set var="fristpage" value="${1}" scope="request"/>
<body>

    <%--게시판 목록--%>
    <table>
        <c:forEach items="${freeboardList}" var="list">
            <tr>
                <td>${list.idx}</div></td>
                <td><span class="lowtitle" title="${list.idx}">${list.title}</span></td>
                <td>${list.hit}</td>
                <td>${list.up}</td>
                <td><span class="lowNickname">${list.nickname}</td>
                <td>${list.writetime}</td>
            </tr>
        </c:forEach>
    </table>

    <ul>
    <%--<페이지 네비게이션>--%>

        <%--좌 버튼--%>
        <c:if test="${thispage-11>0}">
            <li ><span id="nav_first"> << </span></li>
        </c:if>
        <c:if test="${thispage-10>0}">
            <li><span id="nav_previousTen"> < </span></li>
        </c:if>

        <%--
            <페이지표시>
            1. 하단페이지는 10개 배치를 기준으로 한다
            2. 현재페이지의 좌+5 / 우+4 만큼 의 더한페이지를 표시한다
            2-1. 좌+5 조건이 맞지 않을 경우 좌+(가능한수) / 우+(가능한수) 하여 총합 10이 넘지 않도록 하여 배치
            2-2. 우+4 조건이 맞지 않을 경우 좌+5 / 우+(가능한수) 하여 배치
            3-1. begin : 좌+5 조건을 검사 하고 기입
            3-2. end : 전체페이지 10개미만과 우+4 조건을 검사후 좌+5조건을 검사 하고 기입
        --%>
        <c:forEach
                var="i"
                begin="${ ( thispage>5 ) ? thispage-5 : 1 }"
                step="1"
                end="${ (lastpage<10 || thispage+4>lastpage) ? lastpage : (thispage>5) ? thispage+4 : 10 }"
        >
            <c:choose>
                <c:when test="${ i==thispage }">
                    <li><strong><span id="nav_thispage" class="nav_page">${i}</span></strong></li>
                </c:when>
                <c:otherwise>
                    <li><span class="nav_page">${i}</span></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <%--우 버튼--%>
        <c:if test="${thispage+10<lastpage}">
            <li><span id="nav_nextTen"> > </span></li>
        </c:if>
        <c:if test="${thispage+11<lastpage}">
            <li><span id="nav_last"> >> </span></li>
        </c:if>

    </ul>

    <input id="write" type="button" value="<%--글쓰기 버튼--%>">

    <input id="keyword" type="text" value="${ (keyword!=null) ? keyword : "" }" >
    <input id="search" type="button"  value="<%--검색버튼--%>">

    <div id="letterWindow" class="modal">
        <div class="modalcontent">
            <span class="close">&times;</span>
            쪽지보내기/차단<br>
            <div id="messageNickname"></div>
            <textarea id="letterMessage" rows="20" cols="30"></textarea><br>
            <input id="send" type="button" value="전송">
        </div>
    </div>

</body>
</html>
