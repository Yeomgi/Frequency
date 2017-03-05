package com.frequency.Action.Ajax;

import com.frequency.Action.Action;
import com.frequency.DAO.Ajax.DAOAjaxExist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Title : ID,Nickname 중복검사
 * Author : 염형준
 * Date : 2017-03-03
 */


public class ActionAjaxExist implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        setRequestCharacterEncoding_UTF8(request);

        String column = request.getParameter("type");
        String value = request.getParameter("value");

        try(
                DAOAjaxExist dao = new DAOAjaxExist();
                PrintWriter printWriter = getPrintWirter(response)
        ){

            // 자바스크립트의 T/F로 대응되는 1/0으로 값을 전송한다
            if ( dao.isExistValue(column,value) )
                printWriter.print(1);
            else
                printWriter.print(0);

        }

    }

}
