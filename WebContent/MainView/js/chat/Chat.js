/**
 * Title :
 * Author : 염형준
 * Date : 2017-02-28
 */

var chat = false;
addDOMContentLoaded(
    function () {
        var message = document.getElementById("message");
        var textBox = document.getElementById("textBox");
        message.onkeydown = function (event) {
            if(event.keyCode===13 && message.value!='' && chat){
                postAjax(
                    'write.ChatTest',
                    message.value,
                    "Application/x-www-form-urlencoded"
                );
                message.value = '';
            }
        };
    }
);
function startchat() {
    chat = true;
    var message = document.getElementById("message");
    var textBox = document.getElementById("textBox");
    !function getOtherChat() {
        if(chat){
            postAjax(
                'read.ChatTest',
                null,
                "Application/x-www-form-urlencoded",
                function (data) {
                    if(data!=""){
                        var json = JSON.parse(data);
                        for(key in json){
                            var text = '<div>'+json[key][0]+' : '+json[key][1]+'</div>';
                            textBox.innerHTML += text;
                        }
                    }
                    textBox.scrollTop = textBox.scrollHeight;
                },
                function (readyState, status) {
                    if( readyState==4 && status!=500 && textBox.innerHTML.search(/서버 문제로 채팅을 진행할수 없습니다/gm)==-1)
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
function stopChat() {
    chat = false;
    postAjax(
        'stop.ChatTest',
        null,
        "Application/x-www-form-urlencoded"
    );
}
function clearBox() {
    var textBox = document.getElementById("textBox");
    textBox.innerHTML = '';
}
