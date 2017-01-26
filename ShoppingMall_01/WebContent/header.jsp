<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>The Flasic</title>
<link href="css/flasic.css" rel="stylesheet">
</head>
<body>
	<div id="wrap">
		<header id="hd">
			<div id="logo">
				<a href="FlasicServlet?command=index">
				<img alt="TheFlasic" src="imges/LOGO2.png" width="200px">
				</a>
			<!--로고 끝-->
			<nav id="category_menu">
				<ul>
					<li><a href="#">LOGIN</a></li>
					<li><a href="#">JOIN</a></li>
					<li><a href="#">CART</a></li>
					<li><a href="#">MYPAGE</a></li>
				</ul>
			<!--로고와 같은 태그안 카테고리 메뉴 끝  -->
			</nav>
			<a href="#">
			<img alt="Disigner" src="imges/Disign2.png" width="150px" style="float: right;">
			</a>
			<!--개인디자이너 로고 끝  -->					
			</div>
			<nav id="shopping_menu">
				<ul>
					<li><a href="#">BEST20</a></li>
					<li><a href="#">Outer</a></li>
					<li><a href="#">Top</a></li>
					<li><a href="#">Pants</a></li>
					<li><a href="#">Skirt</a></li>
					<li><a href="#">Inner</a></li>
					<li><a href="#">Shoes</a></li>
					<li><a href="#">Bag&Acc</a></li>
				</ul>
			</nav>
		</header>
	</div>
</body>
</html>