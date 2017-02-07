package com.flasic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
}
