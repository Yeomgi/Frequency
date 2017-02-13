<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>아이디 중복체크</title>
<style type="text/css">
body {
	background-color: white;
	font-family: Verdana;
}

#wrap {
	margin: 0 20px;
}

h1 {
	font-family: "Times New Roman", Times, serif;
	font-size: 45px;
	color: #CCC;
	font-weight: normal;
}

input[type=button], input[type=submit] {
	border: none;
	float: right;
	width: 60px;
	height: 23px;
	text-align: center;
}
input[type=button], input[type=submit]:hover {
	background-color: orange;
	cursor: pointer;
}
</style>
<script type="text/javascript">
function idok(){
	  opener.formm.id.value="${id}"; 
	  opener.formm.reid.value="${id}";
	  self.close();
	}
</script>
</head>
<body>
	<div id="wrap">
		<h1>Check ID</h1>
		<form method=post name=formm style="margin-right: 0"
			action="FlasicServlet?command=idcheck">
			Use ID <input type=text name="id" value=""> <input
				type=submit value="Check" class="submit"><br>
			<div style="margin-top: 20px">
				<c:if test="${message == 1}">
					<script type="text/javascript">
          opener.document.formm.id.value="";
        </script>
        ${id}는 이미 사용중인 아이디입니다.
      </c:if>
				<c:if test="${message==-1}">
        ${id}는 사용 가능한 ID입니다.
        <input type="button" value="사용" class="cancel" onclick="idok()">
				</c:if>
			</div>
		</form>
	</div>
</body>
</html>