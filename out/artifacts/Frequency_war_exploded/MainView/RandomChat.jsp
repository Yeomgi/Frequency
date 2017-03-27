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
    <link href="include/css/headerFooter.css" rel="stylesheet">
    <link href="resource/css/random.css" rel="stylesheet">
    <script src="resource/js/common/Mylib.js"></script>
    <script src="resource/js/common/PageMoving.js"></script>
    <script src="resource/js/RandomChat.js"></script>
</head>
<body>
    <jsp:include page="include/header.jsp"/>
    <div id="clear"></div>
    <div id="random">
        <img id="liveimg" src="resource/image/sub/liveoff.png" >
        <div id="chatBox"></div>
        <div id="input">
            <input id="message" type="text" >
            <div id="send">시작</div>
        </div>
        <br>
    </div>
</body>
</body>
</html>
