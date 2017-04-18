package com.frequency.Action.Ajax;

import com.frequency.Action.Action;
import com.frequency.Modules.ControlHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Title : 로그인여부 체크 Ajax
 * Author : 염형준
 * Date : 2017-04-17
 */


public class ActionAjaxIsLogin implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        // 로그인 여부를 1 or 0으로 반환
        try ( PrintWriter printWriter = getPrintWirter(response) )
        {
            if(ControlHelper.isLogin(request))
                printWriter.print(1);
            else
                printWriter.print(0);
        }

    }

}
