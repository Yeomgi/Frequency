package com.frequency.Action.Ajax;

import com.frequency.Action.Action;
import com.frequency.DAO.Ajax.DAOAjaxSendMessage;
import com.frequency.DTO.DTOMember_Message;
import com.frequency.Modules.ControlHelper;
import com.frequency.Modules.Json.JsonHandler;
import org.json.simple.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Title : 회원간 쪽지 전송 Ajax
 * Author : 염형준
 * Date : 2017-04-10
 */


public class ActionAjaxSendMessage implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        // UTF-8 인코딩
        setRequestCharacterEncoding_UTF8(request);
        setResponseCharacterEncoding_UTF8(response);

        // 정보를 가져온다
        JSONObject json = JsonHandler.getJSONfromBody(request);

        String id = ControlHelper.getMemberInfo(request).getId();
        String toWhom = (String) json.get("towhom");
        String content = (String) json.get("content");

        // DTO를 생성
        DTOMember_Message dto = new DTOMember_Message(id,toWhom,content);

        try(
                // 자원생성
                DAOAjaxSendMessage dao = new DAOAjaxSendMessage();
                PrintWriter printWriter = getPrintWirter(response);
        ){

            // 성공여부로 값 반환
            if( dao.sendMessage(dto) )
                printWriter.print(1);
            else
                printWriter.print(0);

        }

    }

}
