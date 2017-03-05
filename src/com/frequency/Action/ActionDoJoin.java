package com.frequency.Action;

import com.frequency.Action.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Title : 회원가입 페이지 Action
 * Author : 염형준
 * Date : 2017-03-03
 */


public class ActionDoJoin implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        doForward(request,response,"/MainView/Join.jsp");
    }

}
