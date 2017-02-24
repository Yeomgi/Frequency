<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@include file="../include/header.jsp" %>
<%@ include file="sub_menu.html" %>
<link href="http://localhost:80/ShoppingMall_01/css/flasic.css" rel="stylesheet">
	<article>
		<h2 style="font-family: BernhardFashion BT;">theFlasic</h2>
		<c:forEach items="${productKindList}" var="pDTO">
		<div id="item">
			<a href="#"><img src="product_image/${pDTO.image}" />
			<p style="font-weight: bold;">${pDTO.name}<p>
			<p style="font-size: 14px;">${pDTO.price2}</p>
		</div>
		</c:forEach>
	</article>
<%@include file="../include/footer.jsp" %> 

