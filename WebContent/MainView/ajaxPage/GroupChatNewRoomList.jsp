<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach items="${requestScope.roomslist}" var="list" varStatus="status">
    <div class="room" title="${list.value[0]}">
        <p class="p1">No.${status.count}</p>
        <div class="p2">"${list.value[0]}"</div>
        <p class="p3"><img src="resource/image/sub/loginboxicon.png" width="30px"> &nbsp;${list.value[1]}</p>
    </div>
</c:forEach>