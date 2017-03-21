package com.frequency.DAO;

import com.frequency.DTO.DTOMember;

import java.sql.SQLException;

/**
 * Title : 회원가입 DAO
 * Author : 염형준
 * Date : 2017-03-04
 */


public class DAOJoindone extends DAO{

    // 회원 정보를 DB에 넣음
    public void join(DTOMember member){

        String sql =
                "insert into Member (id,pw,nickname,email) values (?,?,?,?)"
                ;

        try {

            psmt = con.prepareStatement(sql);

            psmt.setString(1,member.getId());
            psmt.setString(2,member.getPw());
            psmt.setString(3,member.getNickname());
            psmt.setString(4,member.getEmail());

            psmt.executeUpdate();

        } catch (SQLException e) { System.out.println("DAOJoindone PSMT Error : "+e); }

    }

}
