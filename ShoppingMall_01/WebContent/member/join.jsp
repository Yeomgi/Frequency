<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%@include file="../include/header.jsp"%>
<link href="css/member.css" rel="stylesheet">
<script type="text/javascript" src="member/member.js"></script>
<section>
	<h1 id="title">Be come a Member-</h1>
	<div id="joinboxwrap">
		<form id="join" method="post" action="" name="formm">
			<table id="table">
				<tr>
					<td>ID</td>
					<td><input type="text" name="id" placeholder="영문,숫자포함 10자리까지" ></td>
				</tr>
				<tr>
					<td>PASSWARD</td>
					<td><input type="password" name="pw" placeholder="문자,숫자조합 8~12자리"></td>
				</tr>
				<tr>
					<td>CONFIRM</td>
					<td><input type="password" placeholder="다시한번 입력해주세요."></td>
				</tr>
				<tr>
					<td>NAME</td>
					<td><input type="text" name="name"></td>
				</tr>
				<tr>
					<td>BIRTH</td>
					<td><input type="text" name="birth" placeholder="ex)19901122"></td>
				</tr>
				<tr>
					<td>PHONE</td>
					<td><input type="text" name="pnum" placeholder="ex)01011112222"></td>
				</tr>
				<tr>
					<td>EMAIL</td>
					<td><input type="email" name="email"></td>
				</tr>
				<tr>
					<td>ADDRESS</td>
					<td><input type="text" name="addr"></td>
				</tr>
			</table>
			<br>
			<input type="submit" value="JOIN" id="okbtn" >&nbsp;&nbsp;<input type="button" value="돌아가기" onclick="" id="backbtn">
	</div>
	</form>
</section>
<%@include file="../include/footer.jsp"%>