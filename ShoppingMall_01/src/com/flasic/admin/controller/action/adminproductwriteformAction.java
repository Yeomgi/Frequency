package com.flasic.admin.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flasic.controller.action.Action;

public class adminproductwriteformAction implements Action{
@Override
public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
    String url = "admin/product/productWrite.jsp";
    String kindList[] = { "Outer", "Top", "Pants", "Skirt","Inner", "Shoes","Bag&Acc" };    
    String sizeList[] = { "S", "M", "L", "XL"};    
    request.setAttribute("kindList", kindList);
    request.setAttribute("sizeList", sizeList);
    request.getRequestDispatcher(url).forward(request, response);
} 
}
