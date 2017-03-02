package com.flasic.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flasic.dao.productDAO;
import com.flasic.dto.productDTO;

public class IndexAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url="/index.jsp";
		productDAO pDAO = productDAO.getInstance();
		ArrayList<productDTO> bestProductList = pDAO.listBestProduct();
		request.setAttribute("bestProductList", bestProductList);
		
	    RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(url);
	    dispatcher.forward(request, response);
	}

}
