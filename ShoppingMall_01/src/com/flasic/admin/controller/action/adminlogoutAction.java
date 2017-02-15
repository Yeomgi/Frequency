package com.flasic.admin.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flasic.controller.action.Action;

public class adminlogoutAction implements Action {
@Override
public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	String url = "FlasicServlet?command=admin_login_form";
	
	HttpSession session=request.getSession(false);
    if(session!=null){
      session.invalidate();
      request.setAttribute("message", "");
    }    
    
    request.getRequestDispatcher(url).forward(request, response);  
}
}
