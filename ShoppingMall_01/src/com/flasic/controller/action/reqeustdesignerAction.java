package com.flasic.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flasic.dao.memberDAO;
import com.flasic.dto.memberDTO;

public class reqeustdesignerAction implements Action {
@Override
public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	String url = "/mypage/designerlicense.jsp";
	String id = request.getParameter("id").trim();
	memberDAO mDAO = memberDAO.getInstance();
	memberDTO mDTO = mDAO.getMember(id);
	request.setAttribute("mDTO", mDTO);
	request.getRequestDispatcher(url).forward(request, response);
}
}
