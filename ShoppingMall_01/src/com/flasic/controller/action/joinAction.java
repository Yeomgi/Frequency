package com.flasic.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flasic.dao.memberDAO;
import com.flasic.dto.memberDTO;

public class joinAction implements Action {
@Override
public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	String url = "/member/login.jsp";
	HttpSession session = request.getSession();
	
	memberDTO mDTO=new memberDTO();
	
	mDTO.setId(request.getParameter("id"));
	mDTO.setPw(request.getParameter("pw"));
	mDTO.setName(request.getParameter("name"));
	mDTO.setBirth(request.getParameter("birth"));
	mDTO.setPnum(request.getParameter("pnum"));
	mDTO.setEmail(request.getParameter("email"));
	mDTO.setAddr(request.getParameter("addr"));
	
    session.setAttribute("id", request.getParameter("id"));    
    
    memberDAO memberDAO = com.flasic.dao.memberDAO.getInstance();
    memberDAO.insertMember(mDTO);
	
	RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(url);
    dispatcher.forward(request, response);
}
}
