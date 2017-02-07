<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%@include file="../include/header.jsp"%>
<link href="http://localhost:80/ShoppingMall_01/member/css/member.css" rel="stylesheet">
<section>
	<h1 id="title">Be come a Member-</h1>
	<div id="joinboxwrap">
		<form id="join" method="post" action="FlasicServlet?command=join" name="formm">
			<table id="table">
				<tr>
					<td>ID</td>
					<td><input type="text" id="intext" name="id" placeholder="영문,숫자포함 10자리까지" ></td>
					<td><input type="button" id="ckbtn"  value="중복체크" onclick="idcheck()"></td>
				</tr>
				<tr>
					<td>PASSWARD</td>
					<td><input type="password" id="intext" name="pw" placeholder="문자,숫자조합 8~12자리"></td>
				</tr>
				<tr>
					<td>CONFIRM</td>
					<td><input type="password" id="intext" name="rpw" placeholder="다시한번 입력해주세요."></td>
				</tr>
				<tr>
					<td>NAME</td>
					<td><input type="text" id="intext" name="name"></td>
				</tr>
				<tr>
					<td>BIRTH</td>
					<td><input type="text" id="intext" name="birth" placeholder="ex)19901122"></td>
				</tr>
				<tr>
					<td>PHONE</td>
					<td><input type="text" id="intext" name="pnum" placeholder="ex)01011112222"></td>
				</tr>
				<tr>
					<td>EMAIL</td>
					<td><input type="email" id="intext" name="email"></td>
				</tr>
				<tr>
					<td>ADDRESS</td>
					<td><input type="text" id="intext" name="addr"></td>
				</tr>
			</table>
			<br>
			<input type="button" value="JOIN" onclick="join()" id="okbtn" >&nbsp;&nbsp;<input type="button" value="돌아가기" onclick="" id="backbtn">
	</div>
	</form>
</section>
<%@include file="../include/footer.jsp"%>