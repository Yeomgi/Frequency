package com.flasic.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flasic.dao.productDAO;
import com.flasic.dto.productDTO;

public class productkindAction implements Action {
@Override
public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	String url="product/productKind.jsp";
	String kind = request.getParameter("kind").trim();
	productDAO pDAO = productDAO.getInstance();
	ArrayList<productDTO> productKindList = pDAO.listKindProduct(kind);
	
	request.setAttribute("productKindList",productKindList);
	
	RequestDispatcher dispatcher = request.getRequestDispatcher(url);
	dispatcher.forward(request, response);
}
}
