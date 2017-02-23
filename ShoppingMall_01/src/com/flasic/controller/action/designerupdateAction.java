package com.flasic.controller.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flasic.dao.memberDAO;
import com.flasic.dto.memberDTO;

public class designerupdateAction implements Action {
@Override
public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	String url = "FlasicServlet?command=mypage";
	HttpSession session = request.getSession();
	ServletContext context = session.getServletContext();
	memberDTO mDTO = new memberDTO();
	mDTO.setId(request.getParameter("id"));
	mDTO.setDesigner(request.getParameter("designer"));
	memberDAO mDAO = memberDAO.getInstance();
	mDAO.updateDesigner(mDTO);
	response.sendRedirect(url);
}
}
