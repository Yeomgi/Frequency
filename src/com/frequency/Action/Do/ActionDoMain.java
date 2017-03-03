package com.frequency.Action.Do;

import com.frequency.Action.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Title : 메인 페이지 Action
 * Author : 염형준
 * Date : 2017-03-02
 */


public class ActionDoMain implements Action {

    private static ActionDoMain ourInstance = new ActionDoMain();

    private ActionDoMain() {}

    public static ActionDoMain getInstance() {
        return ourInstance;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        doForward(request,response,"/MainView/Main.jsp");
    }

}
