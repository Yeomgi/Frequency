package com.flasic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.flasic.dto.productDTO;

import DB.DBManager;
import oracle.net.aso.p;

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
	
	public productDTO getProduct(String num){
		productDTO pDTO = null;
		String sql = "select * from producttable1 where num=?";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				pDTO = new productDTO();
				pDTO.setNum(rs.getInt("num"));
				pDTO.setName(rs.getString("name"));
				pDTO.setKind(rs.getString("kind"));
				pDTO.setColor(rs.getString("color"));
				pDTO.setProductsize(rs.getString("productsize"));
				pDTO.setPrice1(rs.getInt("price1"));
				pDTO.setPrice2(rs.getInt("price2"));
				pDTO.setPrice3(rs.getInt("price3"));
				pDTO.setContent(rs.getString("content"));
				pDTO.setImage(rs.getString("image"));
				pDTO.setImage2(rs.getString("image2"));
				pDTO.setImage3(rs.getString("image3"));
				pDTO.setImage4(rs.getString("image4"));
				pDTO.setImage5(rs.getString("image5"));
				pDTO.setSuply(rs.getInt("suply"));
				pDTO.setProductdate(rs.getTimestamp("productdate"));
				pDTO.setHit(rs.getInt("hit"));
				pDTO.setBest(rs.getString("best"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt ,rs);
		}
		return pDTO;
	}
	
	  public ArrayList<productDTO> listKindProduct(String kind) {
		    ArrayList<productDTO> productList = new ArrayList<productDTO>();
		    String sql= "select * from producttable1 where kind=?";
		    
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
		String sql = "select count(*) from producttable1 where name like '%'||?||'%'";
		
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
	  //페이지 이동 메서드
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
		return str;
		}
	  
	  public ArrayList<productDTO>listProduct(int tpage, String productName){
		  ArrayList<productDTO> productList = new ArrayList<productDTO>();
		  
		  String str = "select num, productdate, name, price1, price2, best " + 
		  "from producttable1 where name like '%'||?||'%' order by num desc";
		  //"from producttable1 where name like '%'||?||'%' order by num desc";
		  
		  Connection con = null;
		  PreparedStatement pstmt = null;
		  ResultSet rs = null;
		  
		  int absolutepage = 1;
		  
		  try {
			con = DBManager.getConnection();
			absolutepage = (tpage -1) * counts +1;
			pstmt = con.prepareStatement(str, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			
			
			if (productName.equals("")) {
				pstmt.setString(1, "%");
			}else {
				pstmt.setString(1, productName);
			}
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				rs.absolute(absolutepage);
				int count = 0;
				
				while (count < counts) {
					productDTO pDTO = new productDTO();
					pDTO.setNum(rs.getInt(1));
					pDTO.setProductdate(rs.getTimestamp(2));
					pDTO.setName(rs.getString(3));
					pDTO.setPrice1(rs.getInt(4));
					pDTO.setPrice2(rs.getInt(5));
					pDTO.setBest(rs.getString(6));
					productList.add(pDTO);
					if (rs.isLast()) {
						break;
					}
					rs.next();
					count++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, rs);
		}
		return productList;
	  }
	  
	  public int insertProduct(productDTO pDTO){
		int result = 0;
		
		String sql = "insert into producttable1 (" + 
		"num, kind, name, price1, price2, price3, color, productsize, suply, content, image, image2, image3, image4, image5) " + 
				"values(Producttable1sequence1.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pDTO.getKind());
			pstmt.setString(2, pDTO.getName());
			pstmt.setInt(3, pDTO.getPrice1());
			pstmt.setInt(4, pDTO.getPrice2());
			pstmt.setInt(5, pDTO.getPrice3());
			pstmt.setString(6, pDTO.getColor());
			pstmt.setString(7, pDTO.getProductsize());
			pstmt.setInt(8, pDTO.getSuply());
			pstmt.setString(9, pDTO.getContent());
			pstmt.setString(10, pDTO.getImage());
			pstmt.setString(11, pDTO.getImage2());
			pstmt.setString(12, pDTO.getImage3());
			pstmt.setString(13, pDTO.getImage4());
			pstmt.setString(14, pDTO.getImage5());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("추가완료");
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt);
		}
		return result;
	  }
	  
	  //업데이트 수정해야함
	  public int updateProduct(productDTO pDTO){
		  int result = -1;
		  String sql = "update producttable1 set kind=?, name=?" +
		  ", price1=?, price2=?, price3=?, color=?, productsize=?, suply=?, content=?, image=?, image2=?, image3=?, image4=?, image5=?, best=? " +
				  "where num=?";
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				con = DBManager.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, pDTO.getKind());
				pstmt.setString(2, pDTO.getName());
				pstmt.setInt(3, pDTO.getPrice1());
				pstmt.setInt(4, pDTO.getPrice2());
				pstmt.setInt(5, pDTO.getPrice3());
				pstmt.setString(6, pDTO.getColor());
				pstmt.setString(7, pDTO.getProductsize());
				pstmt.setInt(8, pDTO.getSuply());
				pstmt.setString(9, pDTO.getContent());
				pstmt.setString(10, pDTO.getImage());
				pstmt.setString(11, pDTO.getImage2());
				pstmt.setString(12, pDTO.getImage3());
				pstmt.setString(13, pDTO.getImage4());
				pstmt.setString(14, pDTO.getImage5());
				pstmt.setString(15, pDTO.getBest());
				pstmt.setInt(16, pDTO.getNum());
				result = pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				DBManager.close(con, pstmt);
			}
			return result;
	  }
}
