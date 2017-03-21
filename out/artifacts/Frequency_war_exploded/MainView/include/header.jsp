<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="wrap">
	<header>
		<div id="head">
			<span title="main" onclick="movePage(this)"><img id="Logo" src="resource/image/sub/subLOGO2.png" width="100"></span>
			<input id="btn1" type="button" title="randomchat" onclick="movePage(this)">
			<input id="btn2" type="button" title="" onclick="movePage(this)">
			<input id="btn3" type="button" title="" onclick="movePage(this)">
			<input id="btn4" type="button" title="" onclick="movePage(this)">
			<c:choose>
				<c:when test="${sessionScope.memberinfo==null}">
					<input id="btn5" type="button" title="login" onclick="movePage(this)">
					<input id="btn6" type="button" title="join" onclick="movePage(this)">
				</c:when>
				<c:otherwise>
					<input id="btn7" type="button" title="logout" onclick="movePage(this)">
					<input id="btn8" type="button" title="mypage" onclick="movePage(this)">
				</c:otherwise>
			</c:choose>
		</div>
	</header>
</div>