package com.flasic.admin.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flasic.controller.action.Action;
import com.flasic.dao.productDAO;
import com.flasic.dto.productDTO;

public class adminproductupdateformAction implements Action{
@Override
public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String url = "admin/product/productUpdate.jsp";
	String num = request.getParameter("num").trim();
	
	productDAO pDAO = productDAO.getInstance();
	productDTO pDTO = pDAO.getProduct(num);
	
	request.setAttribute("pDTO", pDTO);
	
	String tpage = "1";
	if (request.getParameter(tpage) != null) {
		tpage = request.getParameter("tpage");
	}
	request.setAttribute("tpage", tpage);
	
    String kindList[] = { "Outer", "Top", "Pants", "Skirt","Inner", "Shoes","Bag&Acc" };    
    String sizeList[] = { "S", "M", "L", "XL"};    
    request.setAttribute("kindList", kindList);
    request.setAttribute("sizeList", sizeList);
	
	request.getRequestDispatcher(url).forward(request, response);
}
}
