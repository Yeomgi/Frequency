package com.flasic.admin.controller.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flasic.controller.action.Action;
import com.flasic.dao.productDAO;
import com.flasic.dto.productDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class adminproductupdateAction implements Action {
@Override
public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	String url ="FlasicServlet?command=adminproductlist";
	HttpSession session =request.getSession();
	
	int sizeLimit = 5* 1024 * 1024;
	String savePath = "product_image";
	ServletContext context = session.getServletContext();
	String uploadFilePath = context.getRealPath(savePath);
	
	MultipartRequest multi = new MultipartRequest(request, uploadFilePath, sizeLimit, "UTF-8", new DefaultFileRenamePolicy());
	System.out.println(request.getParameter("kind"));
	
	productDTO pDTO = new productDTO();
	pDTO.setNum(Integer.parseInt(multi.getParameter("num")));
	pDTO.setName(multi.getParameter("name"));
	pDTO.setPrice1(Integer.parseInt(multi.getParameter("price1")));
	pDTO.setPrice2(Integer.parseInt(multi.getParameter("price2")));
	pDTO.setPrice3(Integer.parseInt(multi.getParameter("price2"))
			-Integer.parseInt(multi.getParameter("price1")));
	pDTO.setContent(multi.getParameter("content"));
	if (multi.getFilesystemName("image")==null) {
		pDTO.setImage(multi.getParameter("nonmakeImg"));
	}else {
		pDTO.setImage(multi.getFilesystemName("image"));
	}
	
	productDAO pDAO = productDAO.getInstance();
	pDAO.updateProduct(pDTO);
	response.sendRedirect(url);
}
}
