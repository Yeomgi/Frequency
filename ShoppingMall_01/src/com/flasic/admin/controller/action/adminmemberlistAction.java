package com.flasic.admin.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flasic.controller.action.Action;
import com.flasic.dao.memberDAO;
import com.flasic.dto.memberDTO;

public class adminmemberlistAction implements Action {
@Override
public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	String url = "admin/member/memberList.jsp";
	String key = "";
	if (request.getParameter("key") != null) {
		key = request.getParameter("key");
	}
	memberDAO mDAO = memberDAO.getInstance();
	ArrayList<memberDTO> memberList = mDAO.listMember(key);
	
	request.setAttribute("memberList", memberList);
	request.getRequestDispatcher(url).forward(request, response);
}
}
