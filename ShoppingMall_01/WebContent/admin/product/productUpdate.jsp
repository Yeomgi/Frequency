<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ include file="/admin/header.jsp"%>
<%@ include file="/admin/sub_menu.jsp"%>
<article>
	<h1>상품수정</h1>
	<form name="frm" method="post" enctype="multipart/form-data">
	<input type="hidden" name="num" value="${pDTO.num}">
	<input type="hidden" name="code">
	<input type="hidden" name="flamakeImg" value="${pDTO.image}">
	<table id="list">
		<tr>
			<th>상품분류</th>
			<td colspan="5">
				<select name="kind">
				<c:forEach items="${kindList}" var="kind" varStatus="status">
					<c:choose>
						<c:when test="${pDTO.kind==status.count}">
							<option value="${status.count}" selected="selected">${kind}</option>
						</c:when>
						<c:otherwise>
							<option value="${status.count}">${kind}</option>
						</c:otherwise>				
					</c:choose>
				</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th>상품명</th>
			<td width="345" colspan="5">
				<input type="text" name="name" size="50" maxlength="100" value="${pDTO.name}">
			</td>
		</tr>
		<tr>
			<th>원가A</th>
			<td width="70">
				<input type="text" name="price1" size="11" onkeyup='NumFomat(this)' value="${pDTO.price1}">
			</td>
			<th>판매가B</th>
			<td width="70">
				<input type="text" name="price2" size="11" onblur="go_ab()" onkeyup='NumFomat(this)' value="${pDTO.price2}">
			</td>
			<th>A-B</th>
			<td width="70">
				<input type="text" name="price3" size="11" readonly onkeyup='NumFomat(this)'>
			</td>
			</tr>
		<tr>
			<th>색상</th>
			<td width="70"><input type="text" name="color" size="11" value="${pDTO.color}"> </td>
			<th>사이즈<th>
			<td colspan="5">
			<select name="productsize">
			<c:forEach items="${sizeList}" var="productsize" varStatus="status">
				<c:choose>
						<c:when test="${pDTO.productsize==status.count}">
							<option value="${status.count}" selected="selected">${productsize}</option>
						</c:when>
						<c:otherwise>
							<option value="${status.count}">${productsize}</option>
						</c:otherwise>
					</c:choose>
			</c:forEach>
			</select>
			</td>
		</tr>
		<tr>
			<th>물량</th>
			<td width="70">
				<input type="text" name="suply" size="11" value="${pDTO.suply}">
			</td>
			<th>베스트상품</th>
			<td>
			<c:choose>
				<c:when test='${pDTO.best=="y"}'>
					<input type="checkbox" name="best" value="y" checked="checked">
				</c:when>
				<c:otherwise>
					<input type="checkbox" name="best" value="n">
				</c:otherwise>
			</c:choose>
			</td>
		</tr>
		<tr>
			<th>상세설명</th>
			<td colspan="5">
				<textarea name="content" rows="8" cols="70">${pDTO.content}</textarea>
			</td>
		</tr>
		<tr>
			<th>상품이미지</th>
			<td colspan="5">
				<img src="product_image/${pDTO.image}" width="200pt">
				<br>
				<input type="file" name="image">
			</td>
		</tr>
		<tr>
			<th>상품상세이미지</th>
			<td colspan="5">
				<img src="product_image/${pDTO.image2}" width="200pt">
				<br>
				<input type="file" name="image2">
			</td>
		</tr>
		<tr>
			<th>상품상세이미지</th>
			<td colspan="5">
				<img src="product_image/${pDTO.image3}" width="200pt">
				<br>
				<input type="file" name="image3">
			</td>
		</tr>
		<tr>	
			<th>상품상세이미지</th>
			<td colspan="5">
				<img src="product_image/${pDTO.image4}" width="200pt">
				<br>
				<input type="file" name="image4">
			</td>
		</tr>
		<tr>	
			<th>상품상세이미지</th>
			<td colspan="5">
				<img src="product_image/${pDTO.image5}" width="200pt">
				<br>
				<input type="file" name="image5">
			</td>
		</tr>
	</table>
	<input class="btn" type="button" value="수정" onclick="go_mod_save('${tpage}','${pDTO.num}')">
	<input class="btn" type="button" value="취소" onclick="go_mov()">
	</form>
</article>
<%@ include file="/admin/footer.jsp"%>
</body>
</html>