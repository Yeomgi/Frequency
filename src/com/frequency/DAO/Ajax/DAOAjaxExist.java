package com.frequency.DAO.Ajax;

import com.frequency.DAO.DAO;

import java.sql.SQLException;

/**
 * Title : ID, NickName 중복체크 DAO
 * Author : 염형준
 * Date : 2017-03-03
 */


public class DAOAjaxExist extends DAO{

    // 해당되는 값의 검색여부로 T/F 반환
    public boolean isExistValue(String column, String value){

        String sql =
                "SELECT * FROM member WHERE ?=?"
                ;

        try {

            psmt = con.prepareStatement(sql);

            psmt.setString(1,column);
            psmt.setString(2,value);

            rs = psmt.executeQuery();

            if( !rs.next() )
                return true;


        } catch (SQLException e) { System.out.println("isExistValue PSMT Error : "+e); }

        return false;

    }

}
