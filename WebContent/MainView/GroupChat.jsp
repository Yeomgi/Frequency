<%--
  Title : Frequency 그룹채팅
  Author : 홍녕우, 염형준
  Date : 2017-03-07
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Frequency</title>
    <link href="include/css/headerFooter.css" rel="stylesheet">
    <link href="css/group.css" rel="stylesheet">
    <script src="resource/js/common/Mylib.js"></script>
    <script src="resource/js/common/PageMoving.js"></script>
    <script>
        addDOMContentLoaded(function () {

            var roomlist = {

                list : document.getElementById("roomlist"),
                rooms : document.getElementsByClassName("room"),
                refreshbutton : document.getElementById("refresh"),

                refreshlist : function () {
                    getAjax(
                        "chatRoomListRefresh.ajax",
                        null,
                        function (data) {
                            roomlist.list.innerHTML = data;
                        }
                    );
                },
                joinRoom : function () {
                    getAjax(
                        "chatRoomIsChatingUser.ajax",
                        null,
                        function (data) {
                            data = window.parseInt(data);
                            switch (data){
                                // 비로그인 경우
                                case -2:
                                    if( confirm("로그인이 필요합니다. 로그인 페이지로 이동하시겠습니까?") ){
                                        location.href = "login.do";
                                    }
                                    break;
                                // 이미 채팅을 하고 있을 경우
                                case -1:
                                    alert("이미 채팅을 진행중이신 방이 있습니다. 한번에 하나의 채팅만 하실수 있습니다.");
                                    break;
                                // 방입장
                                case 1:
                                    var uri = "groupchatRoomJoin.do?roomname="+this.title;
                                    location.href = uri;
                                    break;
                            }
                        }
                    );
                },
                changeover : function () {
                    for(var i=0; i<roomlist.rooms.length ; i++){
                        if(this != roomlist.rooms[i]){
                            roomlist.rooms[i].style.backgroundColor = "RGBA(0,0,0,0.7)" ;
                        }
                    }

                },
                changeout : function () {
                    for(var i=0; i<roomlist.rooms.length ; i++){
                        roomlist.rooms[i].style.backgroundColor = "RGBA(255,255,255,0.9)";
                    }
                },
                registFx : function () {
                    for(var i=0; i<roomlist.rooms.length ; i++){
                        roomlist.rooms[i].onmouseover = roomlist.changeover;
                    }
                    for(var i=0; i<roomlist.rooms.length ; i++){
                        roomlist.rooms[i].onmouseout = roomlist.changeout;
                    }
                    for(var i=0; i<roomlist.rooms.length ; i++){
                        roomlist.rooms[i].onclick = roomlist.joinRoom;
                    }
                    roomlist.refreshbutton.onclick = roomlist.refreshlist;
                }
            };

            var createRoom = {

                createform : document.getElementById("createform"),
                roomname : this.createform.roomname,
                createbutton : this.createform.create,

                create : function (){

                    // 방이름을 가져옴
                    var name = createRoom.roomname.value;

                    // 방이름이 공백인지 체크
                    if( name=="" ){
                        focusForm(roomname);
                        return;
                    }

                    // 전송할 value를 만듬
                    name = "roomname="+name;

                    // 로그인여부 / 방이름 중복 검사 / 방생성
                    getAjax(
                        "chatRoomNameExist.ajax",
                        name,
                        function (data) {
                            data = window.Number(data);
                            switch(data) {
                                // 비로그인 경우
                                case -2:
                                    if( confirm("로그인이 필요합니다. 로그인 페이지로 이동하시겠습니까?") ){
                                        location.href = "login.do";
                                    }
                                    break;
                                // 이미 채팅을 하고 있을 경우
                                case -1:
                                    alert("이미 채팅을 진행중이신 방이 있습니다. 한번에 하나의 채팅만 하실수 있습니다.");
                                    break;
                                // 이미 동일한 방이름이 있을경우
                                case 0:
                                    alert("동일한 방이름이 존재합니다.");
                                    break;
                                // 방 생성이 가능한 경우
                                case 1:
                                    uri = "groupchatRoomCreate.do?"+name;
                                    location.href = uri;
                                    break;
                            }
                        }
                    );
                },
                registFx : function () {
                    createRoom.createbutton.onclick = createRoom.create;
                }
            };

            var searchRoom = {

                keywordtag : document.getElementById("keyword"),
                searchbutton : document.getElementById("search"),

                search : function (){

                    // 검색어를 가져옴
                    var keyword = searchRoom.keywordtag.value;

                    // 검색어가 공백인지 체크
                    if( keyword=="" ){
                        focusForm(keyword);
                        return;
                    }

                    // 전송할 value를 만듬
                    keyword = "keyword="+keyword;

                    // 로그인여부 / 방이름 중복 검사 / 방생성
                    getAjax(
                        "chatRoomListSearch.ajax",
                        keyword,
                        function (data) {
                            roomlist.list.innerHTML = data;
                        }
                    );

                },
                registFx : function () {
                    searchRoom.searchbutton.onclick = searchRoom.search;
                    searchRoom.keywordtag.onkeyup = function (event) {
                        // 검색어 입력창에서 엔터키 입력시 검색
                        if(event.keyCode==13)
                            searchRoom.search();
                    }
                }
            };

            roomlist.registFx();
            createRoom.registFx();
            searchRoom.registFx();

            /*모달 윈도우처리*/
            var modal = document.getElementById('roommodal');
            var bdiv = document.getElementById('bdiv');
            var span = document.getElementsByClassName("close")[0];

            bdiv.onclick = function() {
                modal.style.display = "block";
            }
            span.onclick = function() {
                modal.style.display = "none";
            }
            window.onclick = function(event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                }
            }
            /*모달 윈도우처리*/


        });
    </script>
</head>
<body>
    <jsp:include page="include/header.jsp"/>
    <div id="clear"></div>
    <div id="group">
        <div id="menu">
            <div id="sbnt"><input id="keyword" type="text" placeholder="검색어"><input id="search" type="button" value="검색"></div>
            <div id="bdiv">방만들기</div>
            <div id="refresh">새로고침</div>
            <!--Modal -->
            <form id="createform" method="get" onsubmit="return false">
                <div id="roommodal" class="modal">
                    <!--ModalContent  -->
                    <div class="modalcontent">
                        <span class="close">&times;</span>
                        <div id="content">방이름 <input type="text"  name="roomname"><input type="button" name="create"></div>
                    </div>
                </div>
            </form>
        </div>
        <div class="clear"></div>
        <div id="roomlist">
            <c:forEach items="${requestScope.roomslist}" var="list" varStatus="status">
                <div class="room" title="${list.value[0]}">
                    <p class="p1">No.${status.count}</p>
                    <div class="p2">"${list.value[0]}"</div>
                    <p class="p3"><img src="resource/image/sub/loginboxicon.png" width="30px"> &nbsp;${list.value[1]}</p>
                </div>
            </c:forEach>
        </div>
        <div class="clear"></div>
    </div>
</body>
</html>
