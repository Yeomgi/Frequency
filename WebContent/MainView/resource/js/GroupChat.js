/**
 * Title : Frequency 그룹채팅리스트 js
 * Author : 염형준
 * Date : 2017-04-10
 */


addDOMContentLoaded(function () {

    // 방 목록 관련 객체
    var roomlist = {

        list : document.getElementById("roomlist"),
        rooms : document.getElementsByClassName("room"),
        refreshbutton : document.getElementById("refresh"),

        // 방 목록 새로고침
        refreshlist : function () {
            getAjax(
                "chatRoomListRefresh.ajax",
                null,
                function (data) {
                    roomlist.list.innerHTML = data;
                    roomlist.registFx();
                }
            );
        },

        // 방 입장
        joinRoom : function () {
            var roomname = this.title;
            getAjax(
                "chatRoomCheckJoinUser.ajax",
                'roomname='+roomname,
                function (data) {
                    data = window.parseInt(data);
                    switch (data){
                        // 비로그인 경우
                        case -3:
                            if( confirm("로그인이 필요합니다. 로그인 페이지로 이동하시겠습니까?") )
                                location.href = "login.do";
                            break;
                        // 밴처리된 유저일 경우
                        case -2:
                            alert("해당아이디는 현재 채팅과 게시판이용에 일부 제한이 있습니다.");
                            break;
                        // 이미 채팅을 하고 있을 경우
                        case -1:
                            alert("이미 채팅을 진행중이신 방이 있습니다. 한번에 하나의 채팅만 하실 수 있습니다.");
                            break;
                        // 방이 존재하지 않을경우
                        case 0:
                            alert("방이 존재하지 않습니다 리스트를 갱신해 주십시오.");
                            break;
                        // 방입장
                        case 1:
                            var uri = "groupchatRoomJoin.do?roomname="+roomname;
                            location.href = uri;
                            break;
                    }
                }
            );
        },

        /*CSS 처리*/
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
        /*CSS 처리*/

        // 방 목록 div에 이벤트 등록
        registFx : function () {
            roomlist.refreshbutton.onclick = roomlist.refreshlist;
            for(var i=0; i<roomlist.rooms.length ; i++){
                roomlist.rooms[i].onmouseover = roomlist.changeover;
                roomlist.rooms[i].onmouseout = roomlist.changeout;
                roomlist.rooms[i].onclick = roomlist.joinRoom;
            }
        }
    };

    // 방생성 관리 객체
    var createRoom = {

        createform : document.getElementById("createform"),
        roomname : this.createform.roomname,
        createbutton : this.createform.create,

        // 방생성
        create : function (){

            // 방이름을 가져옴
            var name = createRoom.roomname.value;

            // 방이름이 공백인지 체크
            if( name=="" ){
                focusForm(createRoom.roomname);
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
                        case -3:
                            if( confirm("로그인이 필요합니다. 로그인 페이지로 이동하시겠습니까?") )
                                location.href = "login.do";
                            break;
                        // 밴처리된 유저일 경우
                        case -2:
                            alert("해당아이디는 현재 채팅과 게시판이용에 일부 제한이 있습니다.");
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

        // 버튼에 이벤트 등록
        registFx : function () {
            createRoom.createbutton.onclick = createRoom.create;
        }
    };

    var searchRoom = {

        keywordtag : document.getElementById("keyword"),
        searchbutton : document.getElementById("search"),

        // 검색
        search : function (){

            // 검색어를 가져옴
            var keyword = searchRoom.keywordtag.value;

            // 검색어가 공백인지 체크
            if( keyword=="" ){
                focusForm(searchRoom.keywordtag);
                return;
            }

            // 전송할 value를 만듬
            keyword = "keyword="+keyword;

            // 검색어로 구성된 리스트를 삽입
            getAjax(
                "chatRoomListSearch.ajax",
                keyword,
                function (data) {
                    roomlist.list.innerHTML = data;
                    roomlist.registFx();
                }
            );

            // 리스트에 대해 함수를 재동록

        },

        // 이벤트 등록
        registFx : function () {
            searchRoom.searchbutton.onclick = searchRoom.search;
            searchRoom.keywordtag.onkeyup = function (event) {
                // 검색어 입력창에서 엔터키 입력시 검색
                if(event.keyCode==13)
                    searchRoom.search();
            }
        }
    };

    // 이벤트 등록함수 실행
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

 
 
