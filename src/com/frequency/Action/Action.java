package com.frequency.Action;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Title : Action 공통 인터페이스
 * Author : 염형준
 * Date : 2017-02-02
 */

public interface Action {

    // 공통 실행 추상 메소드
    void execute(HttpServletRequest request, HttpServletResponse response);

    // Redirect 방식으로 페이지 전환
    default void doRedirect(HttpServletResponse response, String uri){
        try {
            response.sendRedirect(uri);
        }
        catch (IOException e) { System.out.println( "Redirect Error : "+e ); }
    }

    // Forward 방식으로 페이지 전환
    default void doForward(HttpServletRequest request, HttpServletResponse response, String uri) {
        try{
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(uri);
            dispatcher.forward(request,response);
        } catch (ServletException e) { System.out.println( "ServletException Error : "+e );
        } catch (IOException e) { System.out.println( "Forward Error : "+e );
        }
    }

    // Request를 UTF-8 형식으로 인코딩
    default void setRequestCharacterEncoding_UTF8(HttpServletRequest request){
        try {
            request.setCharacterEncoding("UTF-8");
        }
        catch (UnsupportedEncodingException e) { System.out.println("RequestCharacterEncoding Error : "+e); }
    }
    // Response를 UTF-8 형식으로 인코딩
    default void setResponseCharacterEncoding_UTF8(HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
    }

    // Response로 부터 PrintWriter스트림 얻기
    default PrintWriter getPrintWirter(HttpServletResponse response){
        try {
            return response.getWriter();
        }
        catch (IOException e) { System.out.println("getPrintWirter Error : "+e);}
        return null;
    }

    // Request로 부터 세션 객체 얻기
    default HttpSession getSessioin(HttpServletRequest request){
        return request.getSession();
    }


}
