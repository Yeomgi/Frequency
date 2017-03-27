package com.frequency.Action.Ajax;

import com.frequency.Action.Action;
import com.frequency.Modules.Chat.ChatGroupManager;
import com.frequency.Modules.ControlHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Title : 그룹채팅 방 개설 체크 Ajax
 * Author : 염형준
 * Date : 2017-03-21
 */


public class ActionAjaxGroupChatRoomNameExist implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        // 방이름을 가져온다
        String roomname = request.getParameter("roomname");

        // 그룹채팅 매니저를 얻어온다
        ChatGroupManager manager = ChatGroupManager.getInstance();

        try(
                // Ajax통신에 쓰일 PrintWriter 자원을 가져온다
                PrintWriter printWriter = getPrintWirter(response)
        ){

            // 비로그인 / 그룹채팅진행 여부 / 방이름 중복을 체크해 반환
            /*
            * 비로그인: -2
            * 이미 채팅 진행중인 방이 있음 : -1
            * 방 이름이 중복 되어 생성불가능 : 0
            * 방 이름이 중복 안되어 생성가능 : 1
            */

            if( !ControlHelper.isLogin(request) )
                printWriter.print(-2);
            else if ( manager.isExistUser(request) )
                printWriter.print(-1);
            else if ( manager.isExistRoom(roomname) )
                printWriter.print(0);
            else
                printWriter.print(1);


        }

    }

}
