<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Flasic Adimn</title>
<link rel="stylesheet" href="admin/css/admin.css">
<script type="text/javascript">
function workerCheck() {
	  if(document.frm.managerId.value==""){
	      alert("아이디를 입력하세요.");
	      return false;
	  }else if(document.frm.managerPwd.value==""){
	     alert("비밀번호를 입력하세요.");
	      return false;
	    }
	    return true; 
}
</script>
</head>
<body>
	<div id="wrap">
	<header>      
      <div id="logo">
        <a href="admin/main.jsp"> 
        <img src="admin/image/LOGO.png" style="width: 300px;" id="img">
        </a>
      </div>      
    </header>
    <div class="clear"></div>
	<article>
      <div id="loginform">
      <form name="frm" method="post" 
		action="FlasicServlet?command=admin_login">
      <table id="mainlogin">
        <tr>
          <td> 아 이 디 </td>
          <td> <input type="text" name="managerId" size="10" value="flasic"></td>
        </tr>
        <tr>
          <td> 비밀번호 </td>
          <td> 
            <input type="password" name="managerPwd" size="10" value="flasic">
          </td>
        </tr>
        <tr align="center" >
          <td  colspan="2">          
            <input class="btn" type="submit" value="업무 로그인" 
			onclick="return workerCheck()"><br><br>
            <h4 style="color:red">${message}</h4>
          </td>
        </tr>
      </table>
      </form>
      </div>
    </article>
	</div>
</body>
</html>