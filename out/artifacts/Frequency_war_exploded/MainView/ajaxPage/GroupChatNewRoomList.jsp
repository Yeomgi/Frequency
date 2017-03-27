<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach items="${requestScope.roomslist}" var="list" varStatus="status">
    <div class="room" title="${list.value[0]}">
        <div>${status.count}</div>
        <div>방이름 : ${list.value[0]}</div>
        <div>참여자 수 : ${list.value[1]}</div>
    </div>
</c:forEach>