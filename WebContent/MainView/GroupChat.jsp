<%--
  Title : Frequency 그룹채팅
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
    <link href="resource/css/group.css" rel="stylesheet">
    <script src="resource/js/common/Mylib.js"></script>
    <script src="resource/js/common/PageMoving.js"></script>
    <script src="resource/js/GroupChat.js"></script>
</head>
<body>
    <jsp:include page="include/header.jsp"/>
    <div id="clear"></div>
    <div id="group">
        <div id="menu">
            <div id="sbnt"><input id="keyword" type="text" placeholder="검색어"><input id="search" type="button" value="검색"></div>
            <div id="bdiv">방만들기</div>
            <div id="refresh">새로고침</div>
            <!--Modal -->
            <form id="createform" method="get" onsubmit="return false">
                <div id="roommodal" class="modal">
                    <!--ModalContent  -->
                    <div class="modalcontent">
                        <span class="close">&times;</span>
                        <div id="content">방이름 <input type="text" name="roomname"><input type="button" name="create" value="생성"></div>
                    </div>
                </div>
            </form>
        </div>
        <div class="clear"></div>
        <div id="roomlist">
            <c:forEach items="${requestScope.roomslist}" var="list" varStatus="status">
                <div class="room" title="${list.value[0]}">
                    <p class="p1">No.${status.count}</p>
                    <div class="p2">"${list.value[0]}"</div>
                    <p class="p3"><img src="resource/image/sub/loginboxicon.png" width="30px"> &nbsp;${list.value[1]}</p>
                </div>
            </c:forEach>
        </div>
        <div class="clear"></div>
    </div>
</body>
</html>
