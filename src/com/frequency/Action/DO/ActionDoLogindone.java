package com.frequency.Action.DO;

import com.frequency.Action.Action;
import com.frequency.DAO.DAOLogindone;
import com.frequency.DTO.DTOMember;
import com.frequency.Modules.MemberInfo.MemberInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * Title : 로그인 페이지 -> 메인 페이지 Action 및 로그인정보 체크 Action
 * Author : 염형준
 * Date : 2017-03-06
 */


public class ActionDoLogindone implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        // UTF-8로 인코딩
        setRequestCharacterEncoding_UTF8(request);

        // 정보를 가져온다
        String id = request.getParameter("id");
        String pw = request.getParameter("pw");

        // DTO에 정보를 넣음
        DTOMember member = new DTOMember();
        member.setId(id);
        member.setPw(pw);

        try(
                // 조회에 쓰일 dao와 PrintWriter 자원을 가져온다
                DAOLogindone dao = new DAOLogindone();
                PrintWriter printWriter = getPrintWirter(response)
        ){

            // 값을 체크한다
            if( dao.isExistLoginInfo(member) ){

                // session에 사용할 회원 정보객체를 올린다
                HttpSession session = getSessioin(request);
                MemberInfo memberInfo = dao.getMemeberInfo(member);
                session.setAttribute("memberinfo",memberInfo);

                // 메인페이지로 이동
                doRedirect(response,"/MainView/Main.jsp");

            }
            else{
                // 경고창을 띄움
                printWriter.print("<script>alert(\"입력하신 정보가 옳지 않습니다\")</script>");
            }

        }

    }

}
