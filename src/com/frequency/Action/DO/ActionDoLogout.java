package com.frequency.Action.DO;

import com.frequency.Action.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Title : logout Action
 * Author : 염형준
 * Date : 2017-03-20
 */


public class ActionDoLogout implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        // 세션객체에서 로그인 정보를 지움
        HttpSession session = getSessioin(request);
        session.removeAttribute("memberinfo");

        //메인페이지로 이동
        doForward( request,response,"main.do" );

    }

}
