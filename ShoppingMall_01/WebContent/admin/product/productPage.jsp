<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ include file="/admin/header.jsp"%>
<%@ include file="/admin/sub_menu.jsp"%>
	<article>
	<h1>상품상세보기</h1>
	<form name="fm" method="post">
	<table id="list">
		<tr>
			<th>상품분류</th>
			<td colspan="5">
			${kind}</td>
		</tr>
		<tr>
			<th align="center">상품 명</th>
			<td colspan="5">${productDTO.name}</td>
		</tr>
		   <tr>
        	<th >원가 [A]</th>
        	<td width="60">${productDTO.price1}</td>
        	<th>판매가 [B]</th>
        	<td width="60">${productDTO.price2}</td>
        <th>[B-A]</th><td>${productDTO.price3}</td>
    	</tr>
    	<tr>
        	<th>상세설명</th>
        	<td colspan="5">${productDTO.content}</td>
    	</tr>
    	<tr>
     		<th>상품이미지</th>
     		<td colspan="5" align="center">
  <!--[7] 상품 이미지를 출력하기 -->     
     		<img src="product_images/${productDTO.image}" width="200pt">    
     		</td>
    	</tr>
	</table>
<!--[8] 수정 버튼이 눌리면 상품 수정 페이지로 이동하되 현재 페이지와 상품 일련번호 값을 전달해 준다. --> 
<input class="btn"  type="button" value="수정" onClick="go_mod('${tpage}','${productVO.pseq}')">
<!--[9] 목록 버튼이 눌리면 상품 리스트 페이지로 이동하되 현재 페이지를 전달해 준다. --> 
<input class="btn"  type="button" value="목록" onClick="go_list('${tpage}')">           
</form>
</article>
<%@ include file="/admin/footer.jsp"%>
</body>
</html>

