<%--
  Title : Frequency 메인
  Author : 홍녕우, 염형준
  Date : 2017-03-02
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Frequency</title>
    <link href="css/Main.css" rel="stylesheet">
    <script src="js/Main.js" ></script>
    <script>

        /*<페이지 이동함수>*/
        function movePage(tag) {

            // 등록한 타이틀을 받아온다
            var title = tag.title;

            // 해당하는 타이틀의 페이지로 이동
            switch ( title ){
                case 'groupchat' :
                case 'freeboard' :
                case 'randomchat' :
                case 'notice' :
                case 'login' :
                case 'join' :
                    location.href = 'join.do';
                case 'findinfo' :
            }

        }
    </script>
</head>
<body>
    <header>
        <p>"만남은 인간의 본능인 것이다"</p>
    </header>
    <section>
        <div id="logo" class="metroFloat"><a><img id="logoimg" src="resource/image/main/mainLOGO.png" onmouseover="logoOver()" onmouseout="logoOut()"></span></div>
        <div id="abox" class="metroFloat">
            <span title="groupchat" onclick="movePage(this)"><input class="bbtn" type="button"></span>
            <span title="freeboard" onclick="movePage(this)"><input class="abtn" type="button"></span>
        </div>
        <div id="bbox" class="metroFloat">
            <span title="randomchat" onclick="movePage(this)"><input class="abtn" type="button"></span>
            <span title="notice" onclick="movePage(this)"><input class="bbtn" type="button"></span>
        </div>
        <div id="cbox" class="metroFloat">
            <span title="login" onclick="movePage(this)"><input class="cbtn" type="button" value="login"></span>
            <span title="join" onclick="movePage(this)"><input class="cbtn" type="button" value="join"></span>
            <span title="findinfo" onclick="movePage(this)"><input class="cbtn" type="button" value="find"></span>
        </div>
    </section>
    <div class="clear"></div>
    <footer>
        <div id="foot">
            <ul>
                <li><img src="resource/image/main/phone-book.png" width="40px;">82+0255647895</li>
                <li><img src="resource/image/main/mail.png" width="40px;">Frequency@gmail.com</li>
                <li><img src="resource/image/main/placeholder.png" width="40px;">서울특별시 강남구 서초동 프리빌딩 410호</li>
            </ul>
            <div class="clear"></div>
            <br>
            All contents Copyright 2013 frequency Inc. all rights reserved<br>
            Contact mail : frequency@frequency.com Tel: +82 64 123 4315
            Fax +82 64 123 4321
        </div>
    </footer>
</body>
</html>
