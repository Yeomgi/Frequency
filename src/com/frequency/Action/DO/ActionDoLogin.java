package com.frequency.Action.DO;

import com.frequency.Action.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Title : 로그인 페이지 Action
 * Author : 염형준
 * Date : 2017-03-06
 */


public class ActionDoLogin implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        doForward(request,response,"/MainView/Login.jsp");
    }

}
