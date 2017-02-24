<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@include file="../include/header.jsp" %>
<%@ include file="sub_menu.html" %>
<link href="http://localhost:80/ShoppingMall_01/css/flasic.css" rel="stylesheet">
	<article>
		<h2 style="font-family: BernhardFashion BT;">theFlasic</h2>
		<c:forEach items="${pPage}" var="pDTO">
			<div id="page">
				<img src="product_image/${pDTO.imag}"/>
				<img src="product_image/${pDTO.imag2}"/>
				<img src="product_image/${pDTO.imag3}"/>
				<img src="product_image/${pDTO.imag4}"/>
				<img src="product_image/${pDTO.imag5}"/>
				<p>한줄평 - " ${pDTO.content} "</p>
			</div>
			<input type="button" value="즉시구매" onclick="">
			<input type="button" value="장바구니" onclick="">
			<input type="button" value="뒤로가기" onclick="">
		</c:forEach>
	</article>
<%@include file="../include/footer.jsp" %> 