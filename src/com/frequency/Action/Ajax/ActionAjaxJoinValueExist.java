package com.frequency.Action.Ajax;

import com.frequency.Action.Action;
import com.frequency.DAO.Ajax.DAOAjaxExist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Title : ID,Nickname 중복검사 Ajax
 * Author : 염형준
 * Date : 2017-03-03
 */


public class ActionAjaxJoinValueExist implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        // UTF-8로 인코딩
        setRequestCharacterEncoding_UTF8(request);

        // where절에 쓰일 column과 value를 받아온다
        String column = request.getParameter("type");
        String value = request.getParameter("value");

        try(
                // 조회에 쓰일 dao와 Ajax통신에 쓰일 PrintWriter 자원을 가져온다
                DAOAjaxExist dao = new DAOAjaxExist();
                PrintWriter printWriter = getPrintWirter(response)
        ){

            // 값을 체크하고 자바스크립트의 T/F로 대응되는 1/0으로 값을 전송한다
            if ( dao.isExistValue(column,value) )
                printWriter.print(1);
            else
                printWriter.print(0);

        }

    }

}
