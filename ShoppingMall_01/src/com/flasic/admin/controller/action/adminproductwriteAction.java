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

public class adminproductwriteAction implements Action {
@Override
public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	String url ="FlasicServlet?command=adminproductlist";
		HttpSession session = request.getSession();
		
		int sizeLimit = 5 * 1024 * 1024;
		String savePath = "/product_image";
		ServletContext context = session.getServletContext();
		String uploadFilePath = context.getRealPath(savePath);
		
		MultipartRequest multi = new MultipartRequest(request, uploadFilePath, sizeLimit, "UTF-8", new DefaultFileRenamePolicy());
	 
		productDTO pDTO = new productDTO();
		pDTO.setKind(multi.getParameter("kind"));
		pDTO.setName(multi.getParameter("name"));
		pDTO.setPrice1(Integer.parseInt(multi.getParameter("price1")));
		pDTO.setPrice2(Integer.parseInt(multi.getParameter("price2")));
		pDTO.setPrice3(Integer.parseInt(multi.getParameter("price2"))
				-Integer.parseInt(multi.getParameter("price1")));
		pDTO.setColor(multi.getParameter("color"));
		pDTO.setProductsize(multi.getParameter("productsize"));
		pDTO.setSuply(Integer.parseInt(multi.getParameter("suply")));
		pDTO.setContent(multi.getParameter("content"));
		pDTO.setImage(multi.getFilesystemName("image"));
		
		productDAO pDAO = productDAO.getInstance();
		pDAO.insertProduct(pDTO);
		
		response.sendRedirect(url);
}
}
