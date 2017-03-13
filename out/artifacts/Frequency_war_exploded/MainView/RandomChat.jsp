<%--
  Title : Frequency 랜덤채팅
  Author : 홍녕우, 염형준
  Date : 2017-03-07
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Frequency</title>
    <script>

        // 채팅에 사용될 전역변수
        var chat = false;
        var elements = {
            textBox : document.getElementById("chatBox"),
            message : document.getElementById("message")
        };

        // 메시지 입력 함수
        function inputMessage(event) {
            if (event.keyCode === 13 && message.value != "" && chat) {
                postAjax(
                    'randomWrite.ajax',
                    elements[message].value,
                    "Application/x-www-form-urlencoded"
                );
                elements[message].value = '';
            }
        }

        //채팅 시작 함수
        function startchat() {

            var joinMessage = true;
            elements[textBox].innerHTML += '<div>상대를 찾는중입니다...<div>';

            chat = true;
            // 자가 실행 함수로 0.1초 단위로 서버에서 값을 가져온다
            !function getOtherChat() {
                if(chat){
                    postAjax(
                        'randomRead.ajax',
                        null,
                        "Application/x-www-form-urlencoded",
                        function (data) {
                            if(joinMessage){
                                elements[textBox].innerHTML = '';
                                elements[textBox].innerHTML += '<div>채팅을 시작합니다.<div>';
                                joinMessage = false;
                            }
                            if(data==-1){
                                elements[textBox].innerHTML += '<div>상대방이 채팅을 종료하였습니다.div>';
                                stopChat();
                                return;
                            }
                            if(data!=""){
                                var json = JSON.parse(data);
                                for(key in json){
                                    var text = '<div>'+json[key][0]+' : '+json[key][1]+'</div>';
                                    elements[textBox].innerHTML += text;
                                }
                            }
                            // 자동으로 스크롤을 아래로 내려준다
                            elements[textBox].scrollTop = elements[textBox].scrollHeight;
                        },
                        function (readyState, status) {
                            if( readyState==4 && status!=500 && elements[textBox].innerHTML.search(/서버 문제로 채팅을 진행할수 없습니다/gm)==-1)
                                textBox.innerHTML += "<div>---서버 문제로 채팅을 진행할수 없습니다 ---</div>";
                        }
                    );
                    setTimeout(
                        function () {
                            getOtherChat();
                        },
                        100
                    );
                }
            }();
        }

        // 채팅 종료 함수
        function stopChat() {
            chat = false;
            postAjax(
                'randomExit.ajax',
                null,
                "Application/x-www-form-urlencoded"
            );
        }

    </script>
</head>
<%--
    1. chatBox의 크기는 가로를 넉넉히(채팅 내용을 고려해서) 세로는 디자인 고려해서 가능
    2. message는 텍스트크기 위아래로 여유있게(5~10px) <- 디자인상 수정가능
--%>
<body>

    <div id="chatBox"></div>
    <input id="message" type="text" onkeyup="inputMessage()">
    <div onclick="startchat()"><%--시작버튼이름--%></div>
    <div onclick="stopChat()" ><%--종료버튼이름--%></div>

</body>
</html>
