package com.frequency.Controller;

import com.frequency.Action.Action;
import com.frequency.ActionHandler.ActionDoHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Title : '.do' 컨트롤러
 * Author : 염형준
 * Date : 2017-03-02 ~
 */


@WebServlet("*.do")
public class ControllerDOServlet extends HttpServlet implements Controller{

    @Override
    public void doProcess(HttpServletRequest request, HttpServletResponse response) {

        //System.out.println(" 여기옴 ㅋ");
        String command = getCommand(request);
        //System.out.println(command);
        //ActionDoHandler actionHandler = ActionDoHandler.getInstance();
        Action action = ActionDoHandler.getInstance().getAction( command );
        //System.out.println("action 생성전");
        //Action action = new ActionDoMain();
        //System.out.println("액션생성");
        action.execute( request,response );

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request,response);
    }

}
