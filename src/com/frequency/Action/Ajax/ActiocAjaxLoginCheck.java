package com.frequency.Action.Ajax;

import com.frequency.Action.Action;
import com.frequency.DAO.DAOLogindone;
import com.frequency.DTO.DTOMember;
import com.frequency.Modules.Json.JsonHandler;
import com.frequency.Modules.MemberInfo.MemberInfo;
import org.json.simple.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * Title : 로그인정보 체크 Ajax Action
 * Author : 염형준 로그인 페이지 -> 메인 페이지 Action 및 로그인정보 체크 Action
 * Date : 2017-03-06
 */


public class ActiocAjaxLoginCheck implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        // UTF-8 인코딩
        setRequestCharacterEncoding_UTF8(request);

        // 정보를 가져온다
        JSONObject json = JsonHandler.getJSONfromBody(request);
        String id = (String) json.get("id");
        String pw = (String) json.get("pw");

        System.out.println(id+pw);

        // DTO에 정보를 넣음
        DTOMember member = new DTOMember();
        member.setId(id);
        member.setPw(pw);

        try(
                // 조회에 쓰일 dao와 PrintWriter 자원을 가져온다
                DAOLogindone dao = new DAOLogindone();
                PrintWriter printWriter = getPrintWirter(response)
        ){

            // 값을 체크하여 전송한다.
            if( dao.isExistLoginInfo(member) )
                printWriter.print(1);
            else
                printWriter.print(0);

        }

    }

}
