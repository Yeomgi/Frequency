<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@include file="../include/header.jsp" %>
<%@include file="sub_menu.jsp" %>
	<article>
		<h1>Designer License</h1>
		<div id="dgwrap">
		<textarea rows="50" cols="100" readonly="readonly">
		이용약관
		${mDTO.id} 님은 디자이너가 되시겠습니까?
		</textarea>
		<br>
		<form name="frm" method="post">
		<input type="checkbox" name="designer" value="y"> 동의합니다.
		<br><br>
		<input class="mpbtn" type="button" value="동의" onclick="dg_ok('${mDTO.id}')">
		&nbsp;
		<input class="mpbtn" type="button" value="취소" onclick="dg_no()">
		</form>
		</div>
	</article>
<%@include file="../include/footer.jsp" %>