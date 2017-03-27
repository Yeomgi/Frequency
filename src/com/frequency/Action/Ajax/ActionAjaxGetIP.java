package com.frequency.Action.Ajax;

import com.frequency.Action.Action;
import com.frequency.Modules.ControlHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Title : IP 반환 Ajax
 * Author : 염형준
 * Date : 2017-03-20
 */


public class ActionAjaxGetIP implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        // UT8-8 인코딩
        setResponseCharacterEncoding_UTF8(response);

        try(
                // Ajax통신에 쓰일 PrintWriter 자원을 가져온다
                PrintWriter printWriter = getPrintWirter(response);
        ){
            // IP를 반환한다
            printWriter.print( ControlHelper.getIP(request) );
        }

    }

}
