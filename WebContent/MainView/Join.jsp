<%--
  Title : Frequency 회원가입
  Author : 홍녕우, 염형준
  Date : 2017-03-02
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--
    1. 아이디, 비밀번호, 비밀번호확인, 닉네임, 이메일 input
    2. 아이디, 닉네임 중복체크 버튼 (Ajax 처리후 Alert)
    3. 가입 버튼 클릭후 Joindone으로 이동
--%>
<form method="post">
    <input type="text" name="id">
    <input type="text" name="pw">
    <input type="text" name="pwre">
    <input type="text" name="nickname">
    <input type="text" name="email">
    <input type="button" name="submit" value="<%--가입 버튼이름--%>">
</form>
</body>
</html>
