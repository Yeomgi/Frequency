package com.flasic.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flasic.dao.memberDAO;
import com.flasic.dto.memberDTO;


public class loginAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    String url="member/loginfail.jsp";
	    HttpSession session=request.getSession();
	  
	    String id=request.getParameter("id");
	    String pw=request.getParameter("pw");
	    
	    memberDAO mDAO=memberDAO.getInstance();
	    memberDTO mDTO=mDAO.getMember(id);
	    System.out.println("요까지옴");
	    if(mDTO!=null){
	      if(mDTO.getPw().equals(pw)){    
	        session.removeAttribute("id");
	        session.setAttribute("loginUser", mDTO);
	        url="FlasicServlet?command=index";
	      }
	    }
	    
	    request.getRequestDispatcher(url).forward(request, response);  
	  }
	}

