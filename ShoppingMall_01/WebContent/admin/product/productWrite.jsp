<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ include file="/admin/header.jsp"%>
<%@ include file="/admin/sub_menu.jsp"%>

<article>
	<h1>상품등록</h1>
	<form name="frm" method="post" enctype="multipart/form-data">
		<table id="list">
			<tr>
			<th>상품분류</th>
			<td colspan="5">
				<select name="kind">
					<c:forEach items="${kindList}" var="kind" varStatus="status">
						<option value="${status.count}">${kind}></option>
					</c:forEach>
				</select>
			<tr>
				<th>상품명</th>
				<td width="345" colspan="5">
					<input type="text" name="name" size="50" maxlength="100">
				</td>
			</tr>
			<tr>
				<th>원가A</th>
				<td width="70">
					<input type="text" name="price1" size="11" onkeyup='NumFomat(this)'>
				</td>
				<th>판매가B</th>
				<td width="70">
					<input type="text" name="price2" size="11" onblur="go_ab()" onkeyup='NumFomat(this)'>
				</td>
				<th>A-B</th>
				<td width="70">
					<input type="text" name="price3" size="11" readonly onkeyup='NumFomat(this)'>
				</td>
			</tr>
			<tr>
				<th>색상</th>
				<td width="70"><input type="text" name="color" size="11"></td>
				<th>사이즈</th>
				<td colspan="5">
				<select name="productsize">
				<c:forEach items="${sizeList}" var="productsize" varStatus="status">
					<option value="${status.count}">${productsize}</option>
				</c:forEach>
				</select>
				</td>
			</tr>
			<tr>
				<th>물량</th>
				<td width="70">
					<input type="text" name="suply" size="11">
				</td>
			</tr>
			<tr>
				<th>상세설명</th>
				<td colspan="5">
					<textarea name="content" rows="8" cols="70"></textarea>
				</td>
			</tr>
			<tr>
				<th>상품이미지</th>
				<td width="345" colspan="5">
					<input type="file" name="image">
				</td>
			</tr>
			<tr>
				<th>상품 상세이미지2</th>
				<td width="345" colspan="5">
					<input type="file" name="image2">
				</td>
			</tr>
			<tr>
				<th>상품 상세이미지3</th>
				<td width="345" colspan="5">
					<input type="file" name="image3">
				</td>
			</tr>
			<tr>
				<th>상품 상세이미지4</th>
				<td width="345" colspan="5">
					<input type="file" name="image4">
				</td>
			</tr>
			<tr>
				<th>상품 상세이미지5</th>
				<td width="345" colspan="5">
					<input type="file" name="image5">
				</td>
			</tr>
		</table>
		<input class="btn" type="button" value="저장" onclick="go_save()">
		<input class="btn" type="button" value="취소" onclick="go_mov()">
	</form>
</article>

<%@ include file="/admin/footer.jsp"%>
</body>
</html>