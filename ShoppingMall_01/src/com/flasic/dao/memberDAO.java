package com.flasic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.flasic.dto.memberDTO;

import DB.DBManager;

public class memberDAO {
	private memberDAO() {
	// TODO Auto-generated constructor stub
	}
	private static memberDAO mDAO = new memberDAO();
	
	 public static memberDAO getInstance() {
		    return mDAO;
		  }
	 public int insertMember(memberDTO mDTO){
		 int result=0;
		 String sql = "insert into membertable1(id, pw, name, birth,";
		 sql += " pnum, email, addr) values(?, ?, ?, ?, ?, ?, ?)";
		 
		 Connection conn = null;
		 PreparedStatement pstm = null;
		 
		 try {
			conn=DBManager.getConnection();
			pstm=conn.prepareStatement(sql);
			pstm.setString(1,mDTO.getId());
			pstm.setString(2,mDTO.getPw());
			pstm.setString(3,mDTO.getName());
			pstm.setString(4,mDTO.getBirth());
			pstm.setString(5,mDTO.getPnum());
			pstm.setString(6,mDTO.getEmail());
			pstm.setString(7,mDTO.getAddr());
			pstm.executeQuery();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstm);
		}
		return result;
		 
	 }
	 
	  public static int confirmID(String userid) {
			int result = -1;
		    String sql = "select * from membertable1 where id=?";
		       
		    Connection connn = null;
		    PreparedStatement pstmt = null;
		    ResultSet rs = null;
		    
		    try {
		      connn = DBManager.getConnection();
		      pstmt = connn.prepareStatement(sql);
		      pstmt.setString(1, userid);
		      rs = pstmt.executeQuery();
		      if (rs.next()) { 
		        result = 1;
		      } else { 
		        result = -1;
		      }
		    } catch (Exception e) {
		      e.printStackTrace();
		    } finally {
		      DBManager.close(connn, pstmt, rs);
		    }
		    return result;
		  }
	  
	  public memberDTO getMember(String id) {       
		  memberDTO mDTO= null;
		    String sql = "select * from membertable1 where id=?";
		     
		    Connection connn = null;
		    PreparedStatement pstmt = null;
		    ResultSet rs = null;

		    try {
		      connn = DBManager.getConnection();
		      pstmt = connn.prepareStatement(sql);
		      pstmt.setString(1, id);
		      rs = pstmt.executeQuery();
		      if(rs.next()){
		        mDTO = new memberDTO();
		        mDTO.setId(rs.getString("id"));
		        mDTO.setPw(rs.getString("pw"));
		        mDTO.setName(rs.getString("name"));
		        mDTO.setBirth(rs.getString("birth"));
		        mDTO.setPnum(rs.getString("pnum"));
		        mDTO.setEmail(rs.getString("email"));
		        mDTO.setAddr(rs.getString("addr"));
		      } 
		    } catch (Exception e) {
		      e.printStackTrace();
		    } finally {
		      DBManager.close(connn, pstmt, rs);
		    }
		    return mDTO;
		  }
}
