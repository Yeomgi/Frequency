package com.frequency.DAO;

import com.frequency.DTO.DTOMember;
import com.frequency.Modules.MemberInfo.MemberInfo;
import com.frequency.Modules.ReseltsetToDTO.ReseltsetToDTO;

import java.sql.SQLException;

/**
 * Title : Login DAO
 * Author : 염형준
 * Date : 2017-03-06
 */


public class DAOLogindone extends DAO{

    // 입력한 로그인 정보가 DB에 존재하는지 확인
    public boolean isExistLoginInfo(DTOMember member){

        String sql =
                "SELECT * FROM member WHERE id=? and pw=?"
                ;

        try {

            psmt = con.prepareStatement(sql);

            psmt.setString(1,member.getId());
            psmt.setString(2,member.getPw());

            rs = psmt.executeQuery();

            if( rs.next() )
                return true;

        } catch (SQLException e) { System.out.println("isExistLoginInfo PSMT Error : "+e); }

        return false;

    }

    // session에 사용할 회원 개인정보 객체를 생성하여 반환
    public MemberInfo getMemeberInfo( DTOMember member ){

        try {

            // 1.회원정보를 가져운다
            String sql =
                    "SELECT * FROM member WHERE id=? and pw=?"
                    ;

            psmt = con.prepareStatement(sql);

            psmt.setString(1,member.getId());
            psmt.setString(2,member.getPw());

            rs = psmt.executeQuery();

            while( rs.next() )
                member = ReseltsetToDTO.getDTOmember(rs);


            // 2.회원의 밴 상태를 정수로 가져온다
            sql =
                "SELECT nvl(TRUNC(sysdate - TO_DATE(TO_CHAR( banenddate , 'yyyymmdd'), 'yyyymmdd') , 0) , 1) as ban FROM MEMBER where id=?"
                ;

            psmt = con.prepareStatement(sql);

            psmt.setString(1,member.getId());

            rs = psmt.executeQuery();

                // 0이상은 밴(권한정지)이 없거나 밴 종료날짜를 지난 상태
                // 음수는 밴 중인 상태

            boolean ban;

            if( rs.next() && rs.getInt("ban")>=0 )
                ban = false;
            else
                ban = true;


            // 3.MemberInfo 객체로 만들어 return 한다
            MemberInfo memberInfo = new MemberInfo();
            memberInfo.setId(member.getId());
            memberInfo.setPw(member.getPw());
            memberInfo.setNickname(member.getNickname());
            memberInfo.setEmail(member.getEmail());
            memberInfo.setJoindate(member.getJoindate());
            memberInfo.setBanstartdate(member.getBanstartdate());
            memberInfo.setBanday(member.getBanday());
            memberInfo.setBanenddate(member.getBanenddate());
            memberInfo.setBan(ban);

            return memberInfo;

        } catch (SQLException e) { System.out.println("getMemeberInfo PSMT Error : "+e); }

        return null;

    }

}
