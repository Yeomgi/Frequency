<%--
  Title : Frequency 그룹채팅
  Author : 홍녕우, 염형준
  Date : 2017-03-07
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Frequency</title>
</head>
<%--

--%>
<body>

    <c:forEach>
        <div><%--방 번호--%></div>
        <div><%--방 이름--%></div>
        <div><%--방장닉네임--%></div>
        <div><%--방참여 인원--%></div>
    </c:forEach>
    <button><%--방개설버튼--%></button>

</body>
</html>
