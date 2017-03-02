package com.frequency.Controller;

import com.frequency.Action.Action;
import com.frequency.Action.DO.ActionMain;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Title : '.DO' 컨트롤러
 * Author : 염형준
 * Date : 2017-03-02 ~
 */


@WebServlet("*.do")
public class ControllerDOServlet extends HttpServlet implements Controller{

    @Override
    public void doProcess(HttpServletRequest request, HttpServletResponse response) {

        String command = getCommand(request);
        Action action = null;

        switch ( command ){
            case "main.do" :
                action = new ActionMain();
                break;
            default :
                // 에러페이지 추가
        }

        action.execute(request,response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request,response);
    }

}
