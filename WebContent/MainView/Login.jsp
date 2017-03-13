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
    <script src="js/common/Mylib.js"></script>
    <script>
        function login(form) {

            // 값의 공백여부 체크
            if( form.id.value=="" ){
                focusForm(form.id);
                return;
            }
            if( form.pw.value=="" ){
                focusForm(form.pw);
                return;
            }

            // 페이지 이동
            form.action = "logindone.do";
            form.submit();

        }
    </script>
</head>
<body>
<%--
    1. 아이디, 비밀번호 input
    2. 로그인버튼추가후 클릭시 메인페이지 이동
--%>
<form method="post">
    <input type="text" name="id">
    <input type="password" name="pw">
    <input type="button" value="<%--로그인 버튼이름--%>" onclick="login(this.form)">
</form>
</body>
</html>
