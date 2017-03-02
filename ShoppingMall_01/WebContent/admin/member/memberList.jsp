<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ include file="/admin/header.jsp" %>
<%@ include file="/admin/sub_menu.jsp" %>
<script type="text/javascript">
function go_serch() {
	document.frm.action="FlasicServlet?command=admin_member_list";
	document.frm.submit();
}
</script>

<article>
<h1>회원리스트</h1>
<table style="float: right;">
<tr>
<td>
회원이름
<input type="text" name="key">
<input class="btn" type="button" value="검색" onclick="go_serch()">
</td>
</tr>
</table>
<br>
<table id="orderList">
	<tr>
		<th>아이디(디자이너)</th> <th>이름</th> <th>이메일</th> <th>주소</th> <th>전화</th> 
	</tr>
	<c:forEach items="${memberList}" var="memberDTO">
	<tr>
		<td>${memberDTO.id}
		<c:choose>
			<c:when test='${memberDTO.designer=="n"}'>
				<input type="checkbox" name="designer" disabled="disabled">
			</c:when>
			<c:otherwise>
				<input type="checkbox" name="designer" checked="checked" disabled="disabled">
			</c:otherwise>
		</c:choose>
		</td>
		<td>${memberDTO.name}</td>
		<td>${memberDTO.email}</td>
		<td>${memberDTO.addr}</td>
		<td>${memberDTO.pnum}</td>
	</tr>
	</c:forEach>	
</table>
</article>
<%@ include file="/admin/footer.jsp" %>
</body>
</html>