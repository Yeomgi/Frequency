package com.frequency.Action.Ajax;

import com.frequency.Action.Action;
import com.frequency.Modules.Chat.ChatGroupManager;
import com.frequency.Modules.ControlHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Title : 그룹채팅 참가전 진행 여부 체크 Ajax
 * Author : 염형준
 * Date : 2017-03-22
 */


public class ActionAjaxGroupChatCheckJoinUser implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        // 그룹채팅 매니저를 얻어온다
        ChatGroupManager manager = ChatGroupManager.getInstance();

        try(
                // printWriter 객체를 가져옴
                PrintWriter printWriter = getPrintWirter(response)
        ){

            // 로그인여부 / 그룹채팅진행 여부를 체크해 반환
            /*
            * 비로그인 : -2
            * 이미 채팅 진행중인 방이 있음 : -1
            * 이미 채팅 진행중인 방이 없음 : 1
            */
            if ( !ControlHelper.isLogin(request))
                printWriter.print(-2);
            else if ( manager.isExistUser(request) )
                printWriter.print(-1);
            else
                printWriter.print(1);

        }

    }

}
