package com.frequency.DAO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Title : 공통 상속 Super DAO
 * Author : 염형준
 * Date : 2017-02-07
 */


// AutoCloseable을 구현해서 try-catch-resource에서 자원이 자동반환 되도록 한다.
public class DAO implements AutoCloseable {

    protected Connection con;
    protected PreparedStatement psmt;
    protected ResultSet rs;

    // 생성자에서 connection객체 생성
    protected DAO() {
        try {
            Context context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
            con = dataSource.getConnection();
        } catch ( NamingException e ) { System.out.println("DataSource Error : "+e);
        } catch ( SQLException e ) { System.out.println("getConnection Error : "+e);
        }
    }

    // 커서이동가능 PreparedStatement 객체 얻기
    protected PreparedStatement getCursorMovePSMT(String sql){
        try {
            return con.prepareStatement( sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE );
        }
        catch ( SQLException e ) { System.out.println("getCursorMovePSMT Error : "+e); }
        return null;
    };

    // Close 메소드
    protected void closeRS(){
        try {
            if( rs!=null ) rs.close();
        }
        catch ( SQLException e ) { System.out.println("ResultSet close Error : "+e); }
    }
    protected void closePreparedStatement(){
        try {
            if( psmt!=null ) psmt.close();
        }
        catch ( SQLException e ) { System.out.println("PreparedStatement close Error : "+e); }
    }
    protected void closeConnection(){
        try {
            if( con!=null ) con.close();
        }
        catch ( SQLException e ) { System.out.println("Connection close Error : "+e); }
    }

    @Override
    public void close(){
        try {
            closeRS();
            closePreparedStatement();
            closeConnection();
        }
        catch ( Exception e ){ System.out.println("DAO Close Error : "+e); }
    }

}
