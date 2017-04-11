package com.frequency.DAO.Ajax;

import com.frequency.DAO.DAO;
import com.frequency.DTO.DTOMember_Message;

import java.sql.SQLException;

/**
 * Title : 회원간 쪽지전송 DAO;
 * Author : 염형준
 * Date : 2017-04-10
 */


public class DAOAjaxSendMessage extends DAO {

    public boolean sendMessage(DTOMember_Message dto){

        String sql =
                "INSERT INTO MEMBER_MESSAGE (id,TOWHOM,content) VALUES "+
                "(?,(select id from member where nickname=?),?)"
                ;

        try {

            psmt = con.prepareStatement(sql);

            psmt.setString(1,dto.getId());
            psmt.setString(2,dto.getTowhom());
            psmt.setString(3,dto.getContent());

            if( psmt.executeUpdate()>=0 )
                return true;

        }
        catch (SQLException e) { System.out.println("sendMessage PSMT Error : "+e); }

        return false;

    }

}
