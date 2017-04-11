package com.frequency.Action.Ajax;

import com.frequency.Action.Action;
import com.frequency.Modules.ControlHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Title : NickName 반환 Ajax;
 * Author : 염형준
 * Date : 2017-04-10
 */


public class ActionAjaxGetNickName implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        // UT8-8 인코딩
        setResponseCharacterEncoding_UTF8(response);

        // NickName을 반환
        try( PrintWriter printWriter = getPrintWirter(response); )
        { printWriter.print( ControlHelper.getMemberInfo(request).getNickname() ); }

    }

}
