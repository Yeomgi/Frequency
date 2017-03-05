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
</head>
<body>
    <header>
        <p>"만남은 인간의 본능인 것이다"</p>
    </header>
    <section>
        <div id="logo" class="metroFloat"><a><img id="logoimg" src="resource/image/main/mainLOGO.png" onmouseover="logoOver()" onmouseout="logoOut()"></a></div>
        <div id="abox" class="metroFloat">
            <input class="bbtn" type="button">
            <input class="abtn" type="button">
        </div>
        <div id="bbox" class="metroFloat">
            <input class="abtn" type="button">
            <input class="bbtn" type="button">
        </div>
        <div id="cbox" class="metroFloat">
            <input class="cbtn" type="button" value="login">
            <input class="cbtn" type="button" value="join">
            <input class="cbtn" type="button" value="find">
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
