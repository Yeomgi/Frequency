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
	pDTO.setKind(multi.getParameter("kind"));
	pDTO.setNum(Integer.parseInt(multi.getParameter("num")));
	pDTO.setName(multi.getParameter("name"));
	pDTO.setPrice1(Integer.parseInt(multi.getParameter("price1")));
	pDTO.setPrice2(Integer.parseInt(multi.getParameter("price2")));
	pDTO.setPrice3(Integer.parseInt(multi.getParameter("price2"))
			-Integer.parseInt(multi.getParameter("price1")));
	pDTO.setColor(multi.getParameter("color"));
	pDTO.setProductsize(multi.getParameter("productsize"));
	pDTO.setSuply(Integer.parseInt(multi.getParameter("suply")));
	pDTO.setBest(multi.getParameter("best"));
	pDTO.setContent(multi.getParameter("content"));
	
	if (!isIamgeExist(multi,"image")) {
		//if (multi.getFilesystemName("image")==null) {
		pDTO.setImage(multi.getParameter("nonmakeImg"));
	}else {pDTO.setImage(multi.getFilesystemName("image"));}
	
	if (multi.getFilesystemName("image2")==null) {
		pDTO.setImage2(multi.getParameter("nonmakeImg"));
	}else {pDTO.setImage2(multi.getFilesystemName("image2"));}
	
	if (multi.getFilesystemName("image3")==null) {
		pDTO.setImage3(multi.getParameter("nonmakeImg"));
	}else {pDTO.setImage3(multi.getFilesystemName("image3"));}
	
	if (multi.getFilesystemName("image4")==null) {
		pDTO.setImage4(multi.getParameter("nonmakeImg"));
	}else {pDTO.setImage4(multi.getFilesystemName("image4"));}
	
	if (multi.getFilesystemName("image5")==null) {
		pDTO.setImage5(multi.getParameter("nonmakeImg"));
	}else {pDTO.setImage5(multi.getFilesystemName("image5"));}
	
	productDAO pDAO = productDAO.getInstance();
	pDAO.updateProduct(pDTO);
	response.sendRedirect(url);
}

	private boolean isIamgeExist(MultipartRequest multi, String str) {
		if (multi.getFilesystemName(str)==null) return false;
		else return true;
	}
}
