package com.flasic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import DB.DBManager;

public class managerDAO {
	private managerDAO(){}
	private static managerDAO instance = new  managerDAO();
	public static managerDAO getInstance(){
		return instance;
	}
	public int managerCheck(String managerId, String managerPwd) {
		String sql = "select pw from managertable1 where id=?";
		int result = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;			
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, managerId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = 0; 
				String dbPwd = rs.getString(1); 
				if (dbPwd.equals(managerPwd)) {
					result = 1;
				}
			}
			DBManager.close(conn, pstmt, rs);
		} catch (Exception e) {
		}
		return result;
	}
}
