package com.frequency.Action.DO;

import com.frequency.Action.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Title : 랜덤채팅 페이지 Action
 * Author : 염형준
 * Date : 2017-03-09
 */


public class ActionDoRandomChat implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        doForward(request,response,"/MainView/RandomChat.jsp");
    }

}
