package com.flasic.controller.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flasic.dao.memberDAO;
import com.flasic.dto.memberDTO;

public class memberupdateAction implements Action{
@Override
public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	String url = "FlasicServlet?command=mypage";
	HttpSession session = request.getSession();
	ServletContext context = session.getServletContext();
	memberDTO mDTO = new memberDTO();
	mDTO.setId(request.getParameter("id"));
	mDTO.setPnum(request.getParameter("pnum"));
	mDTO.setEmail(request.getParameter("email"));
	mDTO.setAddr(request.getParameter("addr"));
	memberDAO mDAO = memberDAO.getInstance();
	mDAO.updateMember(mDTO);
	response.sendRedirect(url);
}
}
