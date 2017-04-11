package com.frequency.Action.Ajax;

import com.frequency.Action.Action;
import com.frequency.Modules.Chat.ChatGroupManager;
import com.frequency.Modules.ControlHelper;
import com.frequency.Modules.MemberInfo.MemberInfo;

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

        // 접속할 방이름을 가져온다
        String roomname = request.getParameter("roomname");

        // 그룹채팅 매니저를 얻어온다
        ChatGroupManager manager = ChatGroupManager.getInstance();

        // 회원정보객체를 가져운다
        MemberInfo memberInfo = ControlHelper.getMemberInfo(request);

        try(
                // printWriter 객체를 가져옴
                PrintWriter printWriter = getPrintWirter(response)
        ){

            // 로그인여부 / 그룹채팅진행 여부를 체크해 반환
            /*
            * 비로그인 : -3
            * 밴처리된 유저 : -2
            * 이미 채팅 진행중인 방이 있음 : -1
            * 방이 존재하지 않음 : 0
            * 이미 채팅 진행중인 방이 없음 : 1
            */

            if ( !ControlHelper.isLogin(request) )
                printWriter.print(-3);
            else if ( memberInfo.isBan() )
                printWriter.print(-2);
            else if ( manager.isExistUser(request) )
                printWriter.print(-1);
            else if ( !manager.isExistRoom(roomname) )
                printWriter.print(0);
            else
                printWriter.print(1);

        }

    }

}
