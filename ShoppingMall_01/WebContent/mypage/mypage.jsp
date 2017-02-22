<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@include file="../include/header.jsp" %>
<%@include file="sub_menu.jsp" %>

	<article>
		<h1>회원정보</h1>
			<form name="frm" method="post">
				<table id="userinfo">
					<tr>
						<th width="140">이름</th>
						<td width="200">${mDTO.name}</td>
						<th>아이디</th>
						<td width="200">${mDTO.id}</td>
					</tr>
					<tr>
						<th width="140">디자이너</th>
						<td width="200">
						<span id="memberType">
						<c:choose>
						<c:when test='${mDTO.designer=="n"}'>일반회원</c:when>
						<c:otherwise>디자이너</c:otherwise>
						</c:choose>
						</span>
						</td>
						<th width="140">디자이너 신청하기</th>
						<td>
							<input class="mpbtn" type="button" value="신청" onclick="dg_check()" >
						</td>						
					</tr>
					<tr>
						<th width="140">생년월일</th>
						<td width="200">${mDTO.birth}</td>
					</tr>
					<tr>
						<th width="140">전화번호</th>
						<td width="200">${mDTO.pnum}
					</tr>
					<tr>
						<th width="140">이메일</th>
						<td>${mDTO.email}</td>
					</tr>
					<tr>
						<th width="140">주소</th>
						<td>${mDTO.addr}</td>
					</tr>
				</table>
				<input class="mpbtn" type="button" value="수정" onclick="">
			</form>
	</article>
<%@include file="../include/footer.jsp" %>