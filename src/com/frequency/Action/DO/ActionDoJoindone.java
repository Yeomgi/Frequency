package com.frequency.Action.DO;

import com.frequency.Action.Action;
import com.frequency.DAO.DAOJoindone;
import com.frequency.DTO.DTOMember;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Title : 회원가입완료 페이지 Action 및 회원정보 DB 삽입
 * Author : 염형준
 * Date : 2017-03-04
 */


public class ActionDoJoindone implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        // UTF-8로 인코딩
        setRequestCharacterEncoding_UTF8(request);

        // 정보를 가져온다
        String id = request.getParameter("id");
        String pw = request.getParameter("pw");
        String nickname = request.getParameter("nickname");
        String email = request.getParameter("email");

        // DTO에 정보를 넣음
        DTOMember member = new DTOMember();
        member.setId(id);
        member.setPw(pw);
        member.setNickname(nickname);
        member.setEmail(email);

        // DAO 자원을 생성 하고 전달하여 DB에값을 넣는다
        try(
                DAOJoindone dao = new DAOJoindone()
        ){
            dao.join(member);
        }

        // 가입 환영 메세지를 위한 별명정보 셋팅
        request.setAttribute("nickname",nickname);

        // 페이지 이동
        doForward(request,response,"/MainView/Joindone.jsp");

    }

}
