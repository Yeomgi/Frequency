<%--
  Title : Frequency 회원가입
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
    <script src="resource/js/common/PageMoving.js"></script>
    <script src="resource/js/Join.js"></script>
</head>
<body>
    <jsp:include page="include/header.jsp"/>
    <form method="post">
        <h2>JOIN Member-</h2>
        <fieldset>
            <legend>ID&PW</legend>
            <label>아이디</label>
            <input type="text" name="id" onkeyup="isRightPatturn(this)">
            <button class="abtn" type="button" onclick="isExistValue(this.form,'id',this)">Check</button>
            <div id="idMessage"></div>
            <label>비밀번호</label>
            <input type="password" name="pw" onkeyup="isRightPatturn(this)">
            <div id="pwMessage"></div>
            <label>비밀번호 확인</label>
            <input type="password" name="pwre" onkeyup="isEqualPw(this.form)">
            <div id="pwreMessage"></div>
        </fieldset>
        <fieldset>
            <legend>Info</legend>
            <label>닉네임</label>
            <input type="text" name="nickname" onkeyup="isRightPatturn(this)">
            <button class="abtn" type="button" onclick="isExistValue(this.form,'nickname',this)">Check</button>
            <div id="nicknameMessage"></div>
            <label>이메일</label>
            <input type="text" name="email">
        </fieldset>
        <input id="bbtn" type="button" name="join" value="함께하기" onclick="checkFormValue(this.form)">
    </form>
</body>
</html>
