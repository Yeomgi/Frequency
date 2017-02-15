package com.flasic.admin.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flasic.controller.action.Action;
import com.flasic.dao.managerDAO;

public class adminloginAction implements Action {
@Override
public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
    String url = "FlasicServlet?command=admin_login_form";  
    String msg = "";
    String managerId = request.getParameter("managerId").trim();
    String managerPwd = request.getParameter("managerPwd").trim();

    managerDAO mDAO = managerDAO.getInstance();

    int result = mDAO.managerCheck(managerId, managerPwd);
    
    if (result == 1) {
      HttpSession session = request.getSession();
      session.setAttribute("managerId", managerId);
      url = "FlasicServlet?command=adminproductlist";
    } else if (result == 0) {
      msg = "비밀번호를 확인하세요.";
    } else if (result == -1) {
      msg = "아이디를 확인하세요.";
    }
    RequestDispatcher dispatcher=request.getRequestDispatcher(url);
    dispatcher.forward(request, response);
}
}
