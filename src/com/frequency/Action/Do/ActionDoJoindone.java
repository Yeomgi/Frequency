package com.frequency.Action.Do;

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


public class ActionDoJoindone implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        setRequestCharacterEncoding_UTF8(request);

        String id = request.getParameter("id");
        String pw = request.getParameter("pw");
        String nickname = request.getParameter("nickname");
        String email = request.getParameter("email");

        DTOMember member = new DTOMember();
        member.setId(id);
        member.setPw(pw);
        member.setNickname(nickname);
        member.setEmail(email);

        DAOJoindone dao = new DAOJoindone();
        dao.join(member);

        request.setAttribute("nickname",nickname);

        doForward(request,response,"/MainView/Joindone.jsp");

    }

}
