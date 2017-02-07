<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@include file="../include/header.jsp" %>
<link href="http://localhost:80/ShoppingMall_01/member/css/member.css" rel="stylesheet">
<div class="clear"></div>
	<section>
		<div id="loginboxwrap">
		<h2 id="lotitle">Login !!</h2>
			<div id="input_login">
				ID&nbsp;&nbsp;&nbsp;<input type="text" placeholder="아이디를 입력하세요">
			</div>
			<br>
			<div id="input_login">
				PW&nbsp;&nbsp;<input type="text" placeholder="패스워드를 입력하세요">
			</div>
			<br>
			<form action="" method="post">
				<div id="loginbtn">
					<input type="submit" value="LOGIN">
					<input type="button" value="JOIN MEMBER" onclick="">
				</div>
		</form>
		<br>
		<div id="findbtn">
			<input type="button" value="아이디 및 비밀번호 찾기" onclick="">
		</div>
		<br>
		<div id="kakaobtn">
			<input type="button" value="KAKAO TALK" onclick="">
		</div>
		<br>
		<div id="facebtn">
			<input type="button" value="facebook" onclick="">
		</div>
		</div>
	</section>
<%@include file="../include/footer.jsp" %>