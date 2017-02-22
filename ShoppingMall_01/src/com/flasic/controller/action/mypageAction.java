package com.flasic.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flasic.dao.memberDAO;
import com.flasic.dto.memberDTO;

public class mypageAction implements Action {
@Override
public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	String url = "mypage/mypage.jsp";
	HttpSession session = request.getSession();
	memberDTO loginUser = (memberDTO) session.getAttribute("loginUser");
	if (loginUser == null) {
		url = "FlasicServlet?command=loginform";
	}else {
		memberDAO mDAO = memberDAO.getInstance();
		memberDTO mDTO = mDAO.getMember(loginUser.getId());
		System.out.println("designer : "+mDTO.getDesigner());
		request.setAttribute("mDTO", mDTO);
	}
	
	request.getRequestDispatcher(url).forward(request, response);
}
}
