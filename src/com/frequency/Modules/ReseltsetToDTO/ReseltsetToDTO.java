package com.frequency.Modules.ReseltsetToDTO;

import com.frequency.DTO.DTOFreeBoard;
import com.frequency.DTO.DTOMember;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Title : resultset에서 정보를 가져오는 객체
 * Author : 염형준
 * Date : 2017-03-06
 */


public class ReseltsetToDTO {

    // Resultset에서 member 테이블 정보를 가져옴
    public static DTOMember getDTOmember(ResultSet rs){
        try {
            return new DTOMember(
                    rs.getString("id"),
                    rs.getString("pw"),
                    rs.getString("nickname"),
                    rs.getString("email"),
                    rs.getTimestamp("joindate"),
                    rs.getTimestamp("banstartdate"),
                    rs.getInt("banday"),
                    rs.getTimestamp("banenddate")
                    );
        }
        catch (SQLException e) { System.out.println("getDTOmember Error : "+e); }
        return null;
    }

    // Resultset에서 FreeBoard 테이블 정보를 가져옴
    public static DTOFreeBoard getDTOFreeBoard(ResultSet rs){
        try {
            return new DTOFreeBoard(
                    rs.getInt("idx"),
                    rs.getString("id"),
                    rs.getString("pw"),
                    rs.getString("title"),
                    rs.getString("content"),
                    rs.getTimestamp("writedate"),
                    rs.getInt("hit"),
                    rs.getInt("up"),
                    rs.getInt("down")
            );
        }
        catch (SQLException e) { System.out.println("getDTOFreeBoard Error : "+e); }
        return null;
    }

}
