package com.frequency.Controller;

import com.frequency.Action.Action;
import com.frequency.ActionHandler.ActionAjaxHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Title : '.ajax' 컨트롤러
 * Author : 염형준
 * Date : 2017-03-03
 */


@WebServlet("*.ajax")
public class ControllerAJAXServlet extends HttpServlet implements Controller{

    @Override
    public void doProcess(HttpServletRequest request, HttpServletResponse response) {


        String command = getCommand(request);

        System.out.println("ajax controller IN");
        System.out.println(command);

        // command에 해당하는 Action을 가져온다
        Action action = ActionAjaxHandler.getInstance().getAction( command );

        action.execute( request,response );

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request,response);
    }

}
