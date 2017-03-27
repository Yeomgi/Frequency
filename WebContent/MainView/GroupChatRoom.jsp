<%--
  Title : Frequency 그룹채팅방
  Author : 홍녕우, 염형준
  Date : 2017-03-07
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Frequency</title>
    <script src="resource/js/common/Mylib.js"></script>
    <script src="resource/js/common/PageMoving.js"></script>
    <script>

        // 채팅에 사용될 전역변수
        var joinMessage = true;     // 채팅 시작 메세지 출력용 변수
        var elements;               // Element겍체를 저장하는 객체
        var functionObject;         // 사용함수를 저장하는 객체
        var myIP;                   // 클라이언트의 IP를 서버로부터 얻기위한 변수

        // ( DOM Tree가 로딩된 직후 타이밍에 정의 )
        addDOMContentLoaded(function () {

            /*Element 객체 저장*/
            elements = {
                chatBox : document.getElementById("chatBox"),
                message : document.getElementById("message"),
                memberlist : document.getElementById("memberlist"),
                exitchat : document.getElementById("exitchat")
            };

            /*함수선언*/
            functionObject = {

                // 메시지 입력 함수
                inputMessage : function (event) {
                    // onkeyup시 엔터키를 누르고 값이 존재하며 chat이 시작되었을경우 서버로 전송
                    if (event.keyCode === 13 && message.value != "" && chat) {
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
                    }
                },

                // 채팅 시작 함수
                startchat : function () {

                    // 서버로부터 아이피를 가져온다
                    functionObject.setMyIP();

                    // 자가 실행 함수로 0.1초 단위로 서버에서 값을 가져온다
                    !function getChat() {
                        postAjax(
                            'groupRead.ajax',
                            null,
                            "Application/x-www-form-urlencoded",
                            function (data) {
                                if (joinMessage) {
                                    elements.chatBox.innerHTML = '';
                                    elements.chatBox.innerHTML += '<div id="mesage">채팅을 시작합니다.<div>';
                                    joinMessage = false;
                                }
                                else if (data != "") {
                                    var json = JSON.parse(data);
                                    var text;
                                    for (key in json) {
                                        if ( myIP == json[key][0] )
                                            text = '<div class="mychat">' + json[key][0] +' : '+ json[key][1] + '</div>';
                                        else
                                            text = '<div class="youchat">' + json[key][0] +' : '+ json[key][1] + '</div>';
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
                    location.hostname = "groupchat.do";
                },

                // 전역변수 초기화
                valueInit : function () {
                    joinMessage = true;
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
                },

                // 이벤트 함수 등록
                registFx : function () {
                    elements.message.onkeyup = functionObject.inputMessage;
                    elements.exitchat.onclick = functionObject.exitchat;
                }

            };

            // 종료버튼을 누르지않고 페이지전환,종료,뒤로가기,새로고침을 했을시 채팅종료를 위한 처리
            addBeforeUnload(
                function (){
                    functionObject.exitchat();
                }
            );


            functionObject.registFx();


        });


    </script>
</head>
<body>

    <%--채팅창,참여자목록,메세지입력창,채팅종료버튼(목록페이지로 이동)--%>
    <div id="chatBox"></div>
    <div id="memberlist">
        <c:forEach>

        </c:forEach>
    </div>
    <input id="message" type="text">
    <button id="exitchat">나가기<%--나가기 버튼이름--%></button>

</body>
</html>
