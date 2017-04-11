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
    <link href="include/css/headerFooter.css" rel="stylesheet">
    <link href="resource/css/grouproom.css" rel="stylesheet">
    <script src="resource/js/common/Mylib.js"></script>
    <script src="resource/js/common/PageMoving.js"></script>
    <script src="resource/js/GroupChatRoom.js"></script>
</head>
<body>
    <jsp:include page="include/header.jsp"/>
    <form action="">
        <div id="letterWindow" class="modal">
            <div class="modalcontent">
                <span class="close">&times;</span>
                쪽지보내기/차단<br>
                <div id="nickname">"USER"<%--닉네임삽입--%></div>
                <textarea id="letterMessage" rows="20" cols="30"></textarea><br>
                <input id="send" type="button" value="전송">
                <input id="block" type="button" value="차단">
            </div>
        </div>
    </form>
    <div id="img"><img src="resource/image/sub/chatback4.png"> </div>
    <div id="group">
        <div id="roomName">${requestScope.roomname}</div>
        <div id="chatBox" class="lr"></div>
        <div class="clear"></div>
        <div id="memberlist" class="lr">
            <div id="memberTitle">MemberList</div>
            <div id="memberlist2"></div>
        </div>
        <div id="messageBox" class="lr"><input id="message" type="text"></div>
        <div id="exit" class="lr"><button id="exitchat"></button></div>
        <div class="clear"></div>
    </div>
</body>
</html>
