package com.frequency.Action.DO;

import com.frequency.Action.Action;
import com.frequency.DAO.DAOLogindone;
import com.frequency.DTO.DTOMember;
import com.frequency.Modules.MemberInfo.MemberInfo;
import com.frequency.Modules.MemberInfo.MemberInfoHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * Title : 로그인완료 Action
 * Author : 염형준
 * Date : 2017-03-22
 */


public class ActionDoLoginDone implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        // UTF-8 인코딩
        setRequestCharacterEncoding_UTF8(request);
        setResponseCharacterEncoding_UTF8(response);

        // 정보를 가져온다
        String id = request.getParameter("id");
        String pw = request.getParameter("pw");

        // DTO에 정보를 넣음
        DTOMember member = new DTOMember();
        member.setId(id);
        member.setPw(pw);

        try(
                // dao와 PrintWriter 자원을 가져온다
                DAOLogindone dao = new DAOLogindone();
                PrintWriter printWriter = getPrintWirter(response)
        ){

            // 회원정보객체를 만든다
            MemberInfo memberInfo = dao.getMemeberInfo(member);

            // session에 회원 정보객체를 올리고
            HttpSession session = getSessioin(request);
            session.setAttribute("memberinfo",memberInfo);

            // 회원정보관리 객체에도 올린다
            MemberInfoHandler memberInfoHandler = MemberInfoHandler.getInstance();
            memberInfoHandler.add(memberInfo);

            // 메인페이지로 포워딩
            doForward(request,response,"/MainView/Main.jsp");

        }

    }

}
