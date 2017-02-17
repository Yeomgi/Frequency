package com.flasic.admin.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flasic.controller.action.Action;
import com.flasic.dao.productDAO;
import com.flasic.dto.productDTO;

public class adminproductdetailAction implements Action {
@Override
public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	String url = "admin/product/productPage.jsp";
	System.out.println("num :"+request.getParameter("num"));
	String num = request.getParameter("num").trim();
	productDAO pDAO = productDAO.getInstance();
	productDTO pDTO = pDAO.getProduct(num);
	
	request.setAttribute("pDTO", pDTO);
	
	String tpage ="1";
	if (request.getParameter("tpage") != null) {
		tpage = request.getParameter("tpage");
	}
	String kindList[] = {"Outer", "Top", "Pants", "Skirt","Inner", "Shoes","Bag&Acc"};
	String sizeList[] = { "S", "M", "L", "XL"}; 
	request.setAttribute("tpage", tpage);
	int index1=Integer.parseInt(pDTO.getKind().trim())-1;
	int index2=Integer.parseInt(pDTO.getProductsize().trim())-1;
	request.setAttribute("kind", kindList[index1]);
	request.setAttribute("productsize", sizeList[index2]);
	
	request.getRequestDispatcher(url).forward(request, response);
}
}
