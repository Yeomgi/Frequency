<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Flasic Admin</title>
<link rel="stylesheet" href="admin/css/admin.css">
<script type="text/javascript" src="admin/product/product.js"></script>
	<c:choose>
		<c:when test="${empty workerID}">
			<script type="text/javascript">
				location.href = 'FlasicServlet?command=admin_login_form';
			</script>
		</c:when>
	</c:choose>
</head>
<body>
	<div id="wrap">
		<header>
			<div id="logo">
				<a href="FlasicServlet?command=admin_login_form">
					<img src="admin/image/LOGO.png" width="500px">
				</a>
			</div>
			<input class="btn" type="button" value="logout" style="float: right" 
			onclick="FlasicServlet?command=admin_logout">
		</header>
		<div class="clear"></div>