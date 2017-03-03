<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@include file="../include/header.jsp" %>
<%@ include file="sub_menu.html" %>
<link href="http://localhost:80/ShoppingMall_01/css/flasic.css" rel="stylesheet">
	<article>
		<h2 style="font-family: BernhardFashion BT;">theFlasic</h2>
		<%-- <c:forEach items="${pPage}" var="pDTO"> --%>
			<form name="frm" method="post">
			<input type="hidden" value="${pPage.num}">
			<div id="page">
				<img src="product_image/${pPage.image}"/>
				<img src="product_image/${pPage.image2}"/>
				<img src="product_image/${pPage.image3}"/>
				<img src="product_image/${pPage.image4}"/>
				<img src="product_image/${pPage.image5}"/>
				<p style="font-size: 16px; font-weight: bold;">한줄평 - <span style="font-family:문체부 쓰기 흘림체; font-size: 22px;">" ${pPage.content} "</span></p>
			</div>
			<input class="btn" type="button" value="즉시구매" onclick="go_sell()">
			<input class="btn" type="button" value="장바구니" onclick="go_cart()">
			<input class="btn" type="button" value="뒤로가기" onclick="">
			</form>
			<br>
		<%-- </c:forEach> --%>
	</article>
<%@include file="../include/footer.jsp" %> 