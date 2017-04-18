package com.frequency.DAO;


import com.frequency.DTO.DTOFreeBoard;
import com.frequency.Modules.ReseltsetToDTO.ReseltsetToDTO;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Title : 자유게시판 DAO
 * Author : 염형준
 * Date : 2017-04-12
 */


public class DAOFreeBoard extends DAO {

    // 게시판 목록 조회
    public ArrayList<DTOFreeBoard> getFreeBoardList(String pageNum, int lastpage, String keyword){

        DTOFreeBoard temp;
        ArrayList<DTOFreeBoard> list = null;
        int page;
        int end;
        int start;
        String sql;
        String whereSQl = "";

        // 페이지를 확인하여 시작과 끝 목록번호를 지정한다
        if( pageNum.equals("last") ){
            page = lastpage;
        }
        else{
            page = Integer.parseInt(pageNum);
            if(page>lastpage)
                page = lastpage;
        }

        // 조회할 리스트의 lownum 설정
        start = page*10-9;
        end = start+9;

        // 검색 여부에 따라 where절을 구성
        if( keyword!=null )
            whereSQl = keywordToSQL(keyword);


        sql = "select d.*" +
              "from (select c.*,rownum as rnum, (select nickname from MEMBER a WHERE a.id=c.id ) as nickname from" +
              "       (select b.* from FREEBOARD b "+whereSQl+" ORDER BY b.idx desc) c) d " +
              "WHERE d.rnum BETWEEN ? AND ?"
            ;

        try {

            psmt = con.prepareStatement(sql);

            psmt.setInt(1,start);
            psmt.setInt(2,end);

            rs = psmt.executeQuery();

            list = new ArrayList<DTOFreeBoard>();

            // 목록을 DTO list로 전환
            while (rs.next()){
                temp = ReseltsetToDTO.getDTOFreeBoard(rs);
                temp.setNickname(rs.getString("nickname"));
                list.add(temp);
            }

        }
        catch (SQLException e) { System.out.println("getFreeBoardList psmt Error : "+e);}

        return list;
    }

    // 게시판 글 갯수 반환
    public int getlistCount(String keyword){

        int count=0;

        String whereSQl = "";

        // 검색 여부에 따라 where절을 구성
        if( keyword!=null )
            whereSQl = keywordToSQL(keyword);

        String sql = "SELECT COUNT(rownum) as count FROM FREEBOARD b "+whereSQl;

        try {

            psmt = con.prepareStatement(sql);

            rs = psmt.executeQuery();
            rs.next();

            // 갯수를 반환
            return rs.getInt("count");
        }
        catch (SQLException e) { System.out.println("getlistCount psmt Error : "+e);}

        return count;

    }

    // keyword를 공백 단위로 분리 하여 where절로 반환
    private String keywordToSQL(String keyword){

        String[] keywords = keyword.split("\\s");
        String preSql = "b.title like '%";
        String postSql = "%' ";
        String whereSql = "WHERE ";

        // where절 구성
        for(int i=0; i<keywords.length; i++){
            if( keywords.length==1 || i+1==keywords.length )
                keywords[i] = preSql+keywords[i]+postSql;
            else
                keywords[i] = preSql+keywords[i]+postSql+"and ";
            whereSql += keywords[i];
        }

        System.out.println("where sql : "+whereSql);

        return whereSql;
    }

}
