/**
 * Title : Frequency 그룹채팅방 js
 * Author : 염형준
 * Date : 2017-04-10
 */


// 채팅에 사용될 전역변수
var chat = false;           // 채팅 여부 변수
var joinMessage = true;     // 채팅 시작 메세지 출력용 변수
var elements;               // Element겍체를 저장하는 객체
var functionObject;         // 사용함수를 저장하는 객체
var memberlistObject;       // 접속자목록를 관리하는 객체
var myIP;                   // 클라이언트의 IP 변수
var myNickName;             // 클라이언트의 NickNmae 변수

// ( 모든 컨텐츠 로드후 함수 시작 )
addOnload(function () {

    /*큰 div별 Element 객체 저장*/
    elements = {
        chatBox : document.getElementById("chatBox"),
        message : document.getElementById("message"),
        memberlist : document.getElementById("memberlist2"),
        exitchat : document.getElementById("exitchat")
    };

    /*접속자 목록 관리 객체*/
    memberlistObject = {


        chatControlList : {},
        // 접속자 리스트 상태 열거객체
        /*
         * 채팅차단 : -1
         * 기본 (out) : 0
         * 마우스 over : 1
         * 모달 오픈 : 2
         */
        listStatus : {
            block : -1,
            out : 0,
            over : 1,
            open : 2
        },
        members : document.getElementsByClassName("member"),
        nickname : document.getElementById('nickname'),
        block : document.getElementById('block'),
        letterMessage : document.getElementById("letterMessage"),
        send : document.getElementById('send'),
        modal : document.getElementById('letterWindow'),
        span : document.getElementsByClassName("close")[0],

        // 접속자리스트에 background-color 추가 및 관리
        Handle_ChatControlList : function (data) {

            // 멤버 이름 임시 변수
            var tempName;

            // json 객체로 변경한다
            var json = JSON.parse(data);

            // 빈문자열로 변경
            data = "";


            for(key in json){
                
                // 임시변숭 닉네임 할당
                tempName = json[key][1];

                // 새로 추가된 멤버일경우 out으로 기본설정한다
                if( memberlistObject.chatControlList[tempName]===undefined )
                    memberlistObject.chatControlList[tempName] = memberlistObject.listStatus.out;

                // 설정되 있는 상태에 따라 class를 추가하는 작업을 한다.
                data += memberlistObject.changeBackgroundColorClass(json[key][0],memberlistObject.chatControlList[tempName])
                    +json[key][1]
                    +json[key][2];

            }
            
            return data;
        },

        // 목록 백그라운드 CSS 변경작업
        changeBackgroundColorClass : function (tagStr, status) {

            // 삽입할 태그 문자열
            var addClass;

            // 마우스 올림여부, 모달오픈중, 차단으로 삽입할 클래스를 결정한다
            if(status===memberlistObject.listStatus.out)
                addClass = "<div class=\"member out\"";
            else if(status===memberlistObject.listStatus.over || status===memberlistObject.listStatus.open)
                addClass = "<div class=\"member over\"";
            else if(status===memberlistObject.listStatus.block)
                addClass = "<div class=\"member block\"";

            /// 삽입하여 반환
            return tagStr.replace("<div class=\"member\"",addClass);

        },

        // 모달창이 열릴때 작업
        openWindow : function () {

            // 클릭한 태그의 이름을 가져와 닉네임에 넣는다
            memberlistObject.nickname.innerHTML = this.innerHTML;
            var name = memberlistObject.nickname.innerHTML;

            if( myNickName===name ){
                alert("본인의 닉네임 입니다.");
                return;
            }

            // 모달창 보이기
            memberlistObject.modal.style.display = "block";

            // 차단이 아닐 경우 open 상태로 변경
            if( memberlistObject.chatControlList[name]!==memberlistObject.listStatus.block)
                memberlistObject.chatControlList[name] = memberlistObject.listStatus.open;

            // 차단여부에 따라 버튼 이름을 변경
            if(memberlistObject.chatControlList[name]!==memberlistObject.listStatus.block)
                memberlistObject.block.value = '차단';
            else
                memberlistObject.block.value = '헤제';

        },

        // 모달창 닫힐때 작업
        closeWindow : function () {
            var name = memberlistObject.nickname.innerHTML;
            if(memberlistObject.chatControlList[name]===memberlistObject.listStatus.open)
                memberlistObject.chatControlList[name] = memberlistObject.listStatus.out;
            memberlistObject.letterMessage.value = "";
            memberlistObject.modal.style.display = "none";
        },

        // 목록 low의 onmouseover 함수
         lowOverCSS : function () {
            var name = this.innerHTML;
            if(memberlistObject.chatControlList[name]===memberlistObject.listStatus.out)
                memberlistObject.chatControlList[name] = memberlistObject.listStatus.over;
        },

        // 목록 low의 onmouseout 함수
        lowOutCSS : function () {
            var name = this.innerHTML;
            if(memberlistObject.chatControlList[name]===memberlistObject.listStatus.over)
                memberlistObject.chatControlList[name] = memberlistObject.listStatus.out;
        },

        // 차단리스트에 등록/제거
        addorRemoveBlockList : function () {

            var name = memberlistObject.nickname.innerHTML;

            if(memberlistObject.chatControlList[name]!==memberlistObject.listStatus.block){
                memberlistObject.chatControlList[name] = memberlistObject.listStatus.block;
                memberlistObject.block.value = '해제';
                alert(name+"을(를) 채팅차단 했습니다");
            }
            else{
                memberlistObject.chatControlList[name] = memberlistObject.listStatus.open;
                memberlistObject.block.value = '차단';
                alert(name+"의 채팅차단을 해제했습니다");
            }

        },

        // 쪽지보내기
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


        // 접속자 목록에 이벤트 등록
        registlowFX : function () {
            memberlistObject.members = document.getElementsByClassName("member");
            for(var i=0; i<memberlistObject.members.length; i++){
                memberlistObject.members[i].onclick = memberlistObject.openWindow;
                memberlistObject.members[i].onmouseover = memberlistObject.lowOverCSS;
                memberlistObject.members[i].onmouseout = memberlistObject.lowOutCSS;
            }
        },

        // 일반 이벤트 등록
        registFx : function () {

            // 차단/헤제 버튼에 함수등록
            memberlistObject.block.onclick = memberlistObject.addorRemoveBlockList;
            // 쪽지 버튼 함수등록
            memberlistObject.send.onclick = memberlistObject.sendletter;
            // 아이콘과 윈도우에
            // 모달창 종료 함수 등록
            memberlistObject.span.onclick = memberlistObject.closeWindow;
            window.onclick = function(event) {
                if (event.target == memberlistObject.modal)
                    memberlistObject.closeWindow();
            };

        }

    };

    /*채팅 함수 정의*/
    functionObject = {

        // 페이지의 직접접근을 막기위한 채팅진행방 확인 처리
        checkJoin : function () {
            getAjax(
                "chatRoomCheckJoinUser.ajax",
                null,
                function (data) {
                    data = window.parseInt(data);
                    switch (data){
                        // 로그인을 하지않았거나 채팅방에 접속해있던 경우가 아닐떄(command로 직접접근시)
                        case 1: case -2:
                        alert("그룹채팅 목록을 통해 접속해 주십시오. 목록으로 전환됩니다");
                        functionObject.stopChat();
                        location.href = "groupchat.do";
                        break;
                        default :
                            functionObject.startchat();
                    }
                },
                function (readyState, status) {
                    if (readyState == 4 && status != 500){
                        alert("현재 서버문제로 채팅을 진행하실수 없습니다. 메인페이지로 돌아갑니다.");
                        location.href = 'main.do';
                    }
                }
            );
        },

        // 메시지 입력 함수
        inputMessage : function (event) {
            // onkeyup시 엔터키를 누르고 값이 존재하며 chat이 시작되었을경우 서버로 전송
            if (event.keyCode === 13 && elements.message.value != "" && chat) {
                postAjax(
                    'groupWrite.ajax',
                    elements.message.value,
                    "Application/x-www-form-urlencoded"
                );
                elements.message.value = '';
            }
        },

        // 채팅 종료버튼 함수
        exitchat : function () {
            if (confirm("진행중이신 채팅이 종료되고 목록으로 이동합니다. 진행하시겠습니까?")) {
                functionObject.stopChat();
                location.href = "groupchat.do";
            }
        },

        // 채팅 시작 함수
        startchat : function () {

            chat = true;

            // 서버로부터 아이피를 가져온다
            functionObject.setMyInfo();

            // 자가 실행 함수로 0.1초 단위로 서버에서 값을 가져온다
            !function getChat() {
                postAjax(
                    'groupRead.ajax',
                    null,
                    "Application/x-www-form-urlencoded",
                    function (data) {
                        // 채팅시작시 메세지 입력
                        if (joinMessage) {
                            elements.chatBox.innerHTML = '';
                            elements.chatBox.innerHTML += '<div id="mesage">채팅을 시작합니다.<div>';
                            joinMessage = false;
                        }
                        else if (data != "") {
                            var json = JSON.parse(data);
                            var text;
                            for (key in json) {
                                // 자신, 시스템, 다른사람 에따라 메세지를 분류 하여 출력 
                                if ( myIP == json[key][0] )
                                    text = '<div class="mychat">' + json[key][1] +' : '+ json[key][2] + '</div>';
                                else if( 'system' == json[key][1] )
                                    text = '<div id="mesage">'+ json[key][2] + '</div>';
                                else if( memberlistObject.chatControlList[json[key][1]]!==memberlistObject.listStatus.block )
                                    text = '<div class="youchat">' + json[key][1] +' : '+ json[key][2] + '</div>';

                                if(text!=undefined)
                                    elements.chatBox.innerHTML += text;
                            }
                            // 자동으로 스크롤을 아래로 내려준다
                            elements.chatBox.scrollTop = elements.chatBox.scrollHeight;
                        }
                    },
                    function (readyState, status) {
                        if (readyState == 4 && status != 500 && elements.chatBox.innerHTML.search(/서버 문제로 채팅을 진행할수 없습니다/gm) == -1) {
                            elements.chatBox.innerHTML += '<div id="mesage">---서버 문제로 채팅을 진행할수 없습니다 ---</div>';
                            functionObject.valueInit();
                            functionObject.stopChat();
                        }
                    }
                );
                // 접속자 목록 갱신
                postAjax(
                    "chatRoomGetMemberList.ajax",
                    null,
                    "Application/x-www-form-urlencoded",
                    function (data) {
                        elements.memberlist.innerHTML = memberlistObject.Handle_ChatControlList(data);
                        memberlistObject.registlowFX();
                    }
                );
                setTimeout(
                    function () {
                        getChat();
                    },
                    100
                );
            }();
        },

        // 채팅 종료 함수
        stopChat : function () {
            functionObject.valueInit();
            postAjax(
                'groupExit.ajax',
                null,
                "Application/x-www-form-urlencoded"
            );
        },

        // 전역변수 초기화
        valueInit : function () {
            chat = false;
            joinMessage = true;
        },

        // IP 획득 함수
        setMyInfo : function () {
            getAjax(
                "getIP.ajax",
                null,
                function (data) {
                    myIP = data;
                }
            )
            getAjax(
                "getNickName.ajax",
                null,
                function (data) {
                    myNickName = data;
                }
            );
        },

        // 이벤트 함수 등록
        registFx : function () {
            elements.message.onkeyup = functionObject.inputMessage;
            elements.exitchat.onclick = functionObject.exitchat;
        }

    };

    //종료버튼을 누르지않고 페이지전환,종료,뒤로가기,새로고침을 했을시 채팅종료를 위한 처리
    addBeforeUnload(
        function (){
            functionObject.stopChat();
        }
    );

    memberlistObject.registFx();
    functionObject.registFx();
    functionObject.checkJoin();

});
