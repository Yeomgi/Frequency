<%--
  Title : Frequency 로그인
  Author : 홍녕우, 염형준
  Date : 2017-03-02
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Frequency</title>
    <link href="include/css/headerFooter.css" rel="stylesheet">
    <link href="resource/css/login.css" rel="stylesheet">
    <script src="resource/js/common/Mylib.js"></script>
    <script src="resource/js/common/PageMoving.js"></script>
    <script src="resource/js/Login.js"></script>
</head>
<body>
    <jsp:include page="include/header.jsp"/>
    <form method="post">
        <fieldset>
            <legend>LOGIN</legend>
            <label>아이디</label>
            <input type="text" name="id"><br>
            <label>비밀번호</label>
            <input type="password" name="pw"><br>
            <input class="abtn" type="button" value="로그인" onclick="login(this.form)">
            <input class="abtn" type="button" value="회원가입" title="join" onclick="movePage(this)">
            <input class="abtn" type="button" value="아이디찾기">
        </fieldset>
    </form>
</html>
