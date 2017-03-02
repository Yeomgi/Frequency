package com.frequency.Chat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Title :
 * Author : 염형준
 * Date : 2017-02-23
 **/


@WebServlet("*.ChatTest")
public class ChatTestServlet extends HttpServlet {

    private void doProcess(HttpServletRequest request, HttpServletResponse response){


        try {
            request.setCharacterEncoding("UTF-8");
        }
        catch (UnsupportedEncodingException e) { e.printStackTrace();}

        String command = getCommand(request);

        /*ChatRoom chat = ChatRoom.getInstance();

        switch ( command ){
            case "write.ChatTest" :
                chat.addLog(request);
                break;
            case "read.ChatTest" :
                try {
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().print(chat.getlog(request));
                }
                catch (IOException e) { System.out.println("getWriter Error"+e);}
                break;

            case "stop.ChatTest" :
                chat.exitChat(request);
                break;
        }*/

    }

    private String getCommand(HttpServletRequest request){
        return request.getRequestURI().substring(request.getRequestURI().lastIndexOf('/')+1);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request,response);
    }
}
