<%--
  Title : Frequency 회원가입완료
  Author : 홍녕우, 염형준
  Date : 2017-03-02
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Frequency</title>
    <link href="include/css/headerFooter.css" rel="stylesheet">
    <link href="resource/css/join.css" rel="stylesheet">
    <script src="resource/js/common/Mylib.js"></script>
    <script src="resource/js/common/PageMoving.js" ></script>
</head>
<body>
    <jsp:include page="include/header.jsp"/>
    <div id="doneform">
        <p><span style="font-size: 50px;">"
        ${requestScope.nickname} "</span>님 회원가입을 축하드립니다.<br>
        이곳에서 새로운 인연을 만들어 보십시오.
        </p><br>
        <input class="donebtn" title="main" type="button" value="메인으로" onclick="movePage(this)">>
        <input class="donebtn" title="mypage" type="button" value="마이페이지" onclick="movePage(this)">>
    </div>
</body>
</html>
