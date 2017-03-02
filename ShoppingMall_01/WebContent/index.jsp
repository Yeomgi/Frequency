<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@include file="/include/header.jsp" %>
<link href="http://localhost:8092/ShoppingMall_01/css/flasic.css" rel="stylesheet">
	<div class="clear"></div>
		<div id="mainwrap">
			<!--메인사진 부분시작  -->
			<!-- <div id="mainpic">
				<img src="imges/main1.jpg" style="width: 600px; height: 300px;">
				<img src="imges/main2.jpg" style="width: 190px; height: 300px;">
				<img src="imges/main3.jpg" style="width: 190px; height: 300px;">
			</div> -->
			<div id="mainpic">
				<img src="imges/main1.jpg" style=" height: 300px;">
				<img src="imges/main2.jpg" style=" height: 300px;">
				<img src="imges/main3.jpg" style=" height: 300px;">
				<img src="imges/main4.png" style=" width:106px; height: 300px;">
			</div>
			<!--메인 사진부분 끝  -->
			<div class="clear"></div>
			<div id="front">
				<h2 style="font-family: BernhardFashion BT;">Best 20</h2>
				<div id="bestproduct">
					<c:forEach items="${bestProductList}" var="pDTO">
						<div id="item">
							<a href="FlasicServlet?command=producPage&num=${pDTO.num}"><img src="product_image/${pDTO.image}" />
							<p style="font-weight: bold;">${pDTO.name}<p>
							<p style="font-size: 14px;">${pDTO.price2}</p>
							</a>
						</div>
					</c:forEach>
				</div>
			</div>
<%@include file="include/footer.jsp"%>