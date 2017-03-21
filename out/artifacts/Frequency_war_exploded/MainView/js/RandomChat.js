/**
 * Title : Frequency 랜덤채팅 js
 * Author : 염형준
 * Date : 2017-03-20
 */


// 채팅에 사용될 전역변수
var chat = false;           // 채팅중 유무 변수
var joinMessage = true;     // 채팅 시작 메세지 출력용
var startMessage = true;    // 채팅 매칭중 메세지 출력용
var elements;               // Element겍체를 저장하는 객체
var functionObject;         // 사용함수를 저장하는 객체
var myIP;                   // 클라이언트의 IP를 서버로부터 얻기위한 객체

// ( DOM Tree가 로딩된 직후 타이밍에 정의 )
addDOMContentLoaded(function () {

    /*Element 객체 저장*/
    elements = {
        chatBox : document.getElementById("chatBox"),
        message : document.getElementById("message"),
        button : document.getElementById("send")
    };

    /*함수 선언*/
    functionObject = {

        // 메시지 입력 함수
        inputMessage : function (event) {
            // onkeyup시 엔터키를 누르고 값이 존재하며 chat이 시작되었을경우 서버로 전송
            if (event.keyCode === 13 && message.value != "" && chat) {
                postAjax(
                    'randomWrite.ajax',
                    elements.message.value,
                    "Application/x-www-form-urlencoded"
                );
                elements.message.value = '';
            }
        },

        //채팅 시작 함수
        startchat : function () {
            
            // 서버로부터 아이피를 가져온다
            functionObject.setMyIP();
            // 시작버튼을 종료버튼으로 바꿈
            functionObject.changeButton('stop');
            
            // 채팅시작
            chat = true;
            // 시스템메세지와 이전 채팅메세지을 지운다
            elements.chatBox.innerHTML = '';

            // 자가 실행 함수로 0.1초 단위로 서버에서 값을 가져온다
            !function getChat() {
                if ( chat ) {
                    postAjax(
                        'randomRead.ajax',
                        null,
                        "Application/x-www-form-urlencoded",
                        function (data) {
                            if (data == -2) {
                                if (startMessage) {
                                    elements.chatBox.innerHTML += '<div id="mesage">상대를 찾는중입니다...<div>';
                                    startMessage = elements.chatBox.innerHTML.search(/상대를 찾는중입니다\.\.\./gm) == -1 ? true : false;
                                }
                                return;
                            }
                            else if (data == -1) {
                                elements.chatBox.innerHTML += '<div id="mesage">상대방이 채팅을 종료하였습니다.<div>';
                                functionObject.changeButton('start');
                                functionObject.valueInit();
                                return;
                            }
                            else if (joinMessage) {
                                elements.chatBox.innerHTML = '';
                                elements.chatBox.innerHTML += '<div id="mesage">채팅을 시작합니다.<div>';
                                joinMessage = false;
                            }
                            else if (data != "") {
                                var json = JSON.parse(data);
                                var text;
                                for (key in json) {
                                    if ( myIP == json[key][0] )
                                        text = '<div class="mychat">' + json[key][0] + ' : ' + json[key][1] + '</div>';
                                    else
                                        text = '<div class="youchat">' + json[key][0] + ' : ' + json[key][1] + '</div>';
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
                                changeButton('start');
                            }

                        }
                    );
                    setTimeout(
                        function () {
                            getChat();
                        },
                        100
                    );
                }
            }();
        },

        // 채팅 종료 함수
        stopChat : function () {
            if (chat) {
                functionObject.valueInit();
                functionObject.changeButton('start');
                elements.chatBox.innerHTML += '<div id="mesage">채팅이 종료 되었습니다.<div>';
                postAjax(
                    'randomExit.ajax',
                    null,
                    "Application/x-www-form-urlencoded"
                );
            }
        },

        // 전역변수 초기화
        valueInit : function () {
            chat = false;
            joinMessage = true;
            startMessage = true;
        },

        // 버튼 변환 함수
        changeButton : function (changeName) {
            switch (changeName) {
                case 'start' :
                    elements.button.onclick = functionObject.startchat;
                    elements.button.innerHTML = '시작';
                    break;
                case 'stop' :
                    elements.button.onclick = functionObject.stopChat;
                    elements.button.innerHTML = '종료 ';
                    break;
            }
        },
        
        // IP 획득 함수
        setMyIP : function () {
            getAjax(
                "getIP.ajax",
                null,
                function (data) {
                    myIP = data;
                }
            );
        }

    };

    // 이벤트함수를 등록
    elements.message.onkeyup = functionObject.inputMessage;
    elements.button.onclick = functionObject.startchat;

    // 종료버튼을 누르지않고 페이지전환,종료,뒤로가기,새로고침을 했을시 채팅종료를 위한 처리
    addbeforeunload(
        function (){
            functionObject.stopChat();
        }
    );

});

