package com.frequency.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Title : Controller 공통 인터페이스
 * Author : 염형준
 * Date : 2017-02-02
 */

public interface Controller{

    // 공통 실행 메소드
    public void doProcess(HttpServletRequest request, HttpServletResponse response);

    //  Command 문자열을 추출

    default String getCommand(HttpServletRequest request){
        return request.getRequestURI().substring(request.getRequestURI().lastIndexOf('/')+1);
    }

}
