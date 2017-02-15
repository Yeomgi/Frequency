package com.flasic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.flasic.dto.productDTO;

import DB.DBManager;

public class productDAO {
	private productDAO(){}
	private static productDAO instance = new productDAO();
	
	public static productDAO getInstance(){
		return instance;
	}
	//신상품
	public ArrayList<productDTO>listNewProduct() {
		ArrayList<productDTO> productList = new ArrayList<productDTO>();
		String sql = "select * from new_pro_view";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				productDTO pDTO = new productDTO();
				pDTO.setNum(rs.getInt("num"));
				pDTO.setName(rs.getString("name"));
				pDTO.setPrice2(rs.getInt("price2"));
				pDTO.setImage(rs.getString("image"));
				productList.add(pDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt,rs);
		}
		return productList;
	}
	//베스트상품
	public ArrayList<productDTO>listBestProduct() {
		ArrayList<productDTO> productList = new ArrayList<productDTO>();
		String sql = "select * from best_pro_view";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				productDTO pDTO = new productDTO();
				pDTO.setNum(rs.getInt("num"));
				pDTO.setName(rs.getString("name"));
				pDTO.setPrice2(rs.getInt("price2"));
				pDTO.setImage(rs.getString("image"));
				productList.add(pDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt,rs);
		}
		return productList;
	}
	  public ArrayList<productDTO> listKindProduct(String kind) {
		    ArrayList<productDTO> productList = new ArrayList<productDTO>();
		    String sql= "select * from product where kind=?";
		    
		    Connection conn = null;
		    PreparedStatement pstmt = null;
		    ResultSet rs = null;
		    
		    try {
		      conn = DBManager.getConnection();
		      pstmt = conn.prepareStatement(sql);
		      pstmt.setString(1, kind);
		      rs = pstmt.executeQuery();
		      
		      while (rs.next()) {
		    	  productDTO pDTO = new productDTO();
		    	  pDTO.setNum(rs.getInt("num"));
		    	  pDTO.setName(rs.getString("name"));
		    	  pDTO.setPrice2(rs.getInt("price2"));
		    	  pDTO.setImage(rs.getString("image"));
		    	  productList.add(pDTO);
		      }
		    } catch (Exception e) {
		      e.printStackTrace();
		    } finally {
		      DBManager.close(conn, pstmt, rs);
		    }
			return productList;
		  }
	//관리자모드 사용 메서드
	  public int totalRecord(String productName){
		int totalPages = 0;
		String sql = "select count(*) from producttable1 where name like '%'||?||%";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet pageset = null;
		
		try {
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql);
			if (productName.equals("")) {
				pstmt.setString(1, "%");
			}
			else{
				pstmt.setString(1, productName);
			}
			pageset = pstmt.executeQuery();
			if (pageset.next()) {
				totalPages = pageset.getInt(1);
				pageset.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt);
		}
		  return totalPages;
	  }
	  static int viewRows = 5;
	  static int counts =5;
	  public String pageNumber(int tpage, String name){
		  String str = "";
		  int totalPage = totalRecord(name);
		  int pageCount = totalPage/counts + 1;
		 
		  if (totalPage % counts == 0) {
			pageCount--;
		}
		  if (tpage < 1) {
			tpage = 1;
		}
		  int startPage = tpage - (tpage % viewRows) + 1;
		  int endPage = startPage + (counts-1);
		  
		  if (endPage > pageCount) {
			  endPage = pageCount;
		}
		 if (startPage > viewRows) {
			str +="<a href='FlasicServlet?command=adminproductlist&tpage=1&key=" 
		 + name + "'>&lt;&lt;</a>&nbsp;";
			str +="<a href='FlasicServlet?command=adminproductlist&tpage="
		 + (startPage -1);
			str +="<&key=<%=productName%>'>&lt;</a>&nbsp;&nbsp;";
		}
		 for (int i = startPage; i <=endPage; i++) {
			if (i==tpage) {
				str +="<font color=red>[" + i + "]&nbsp;&nbsp;</font>";
			}else {
				str +="<a href='FlasicServlet?command=adminproductlist&tpage=" 
			+ i + "&key=" + name + "'>[" + i + "]</a>&nbsp;&nbsp;";
			}
		}
		 if (pageCount > endPage) {
			str +="<a href='FlasicServlet?command=adminproductlist&tpage=" 
		 + (endPage + 1) + "&key=" + name + "'> &gt; </a>&nbsp;&nbsp;";
			str +="<a href='FlasicServlet?command=adminproductlist&tpage=" 
		 + pageCount + "&key=" + name + "'>&gt; &gt; </a>&nbsp;&nbsp;";
			
		}
		return null;}
}
