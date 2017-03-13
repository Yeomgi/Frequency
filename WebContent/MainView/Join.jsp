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
    <script src="js/common/Mylib.js"></script>
    <script src="js/Join.js"></script>
</head>
<body>
<%--
    1. 아이디, 비밀번호, 비밀번호확인, 닉네임, 이메일 input태그
        1-1. input태그 근처에 유효성 고정 메세지 필요
            ID : 영문으로 시작하고 영문+숫자로 구성된 최소 6자리
            PW : 영문+숫자+특문 포함 최소 8자리
            NICKNAME : 최소 4자리
    2. 아이디, 비밀번호, 닉네임 유효성 적합여부 실시간 메세지변환 div태그 & 비밀번호 동일여부 실시간 메세지변환 div태그
    3. 아이디, 닉네임 중복체크 버튼 (사용가능하다면 input태그 readonly 속성추가 / backgroundColor = "#EEE" 추가 )
    4. 가입 버튼 클릭시 휴효성 검사후 Joindone으로 이동
        4-1. 가입 유효성 검사에 걸리는 항목시 태그에 파란 테두리 생기는 함수적용 (CSS 수정가능)
--%>
<form method="post">

    <input type="text" name="id" onkeyup="isRightPatturn(this)">
    <button type="button" onclick="isExistValue(this.form,'id',this)"><%--ID중복검사버튼이름--%></button>
    <div id="idMessage"></div>

    <input type="password" name="pw" onkeyup="isRightPatturn(this)">
    <div id="pwMessage"></div>

    <input type="password" name="pwre" onkeyup="isEqualPw(this.form)">
    <div id="pwreMessage"></div>

    <input type="text" name="nickname" onkeyup="isRightPatturn(this)">
    <button type="button" onclick="isExistValue(this.form,'nickname',this)"><%--별명중복검사버튼이름--%></button>
    <div id="nicknameMessage"></div>

    <input type="text" name="email">

    <input type="button" value="<%--가입 버튼이름--%>" onclick="checkFormValue(this.form)">

</form>
</body>
</html>
