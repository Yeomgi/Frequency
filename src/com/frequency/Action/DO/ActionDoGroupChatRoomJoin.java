package com.frequency.Action.DO;

import com.frequency.Action.Action;
import com.frequency.Modules.Chat.ChatGroupManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Title : 그룹채팅 입장 Action
 * Author : 염형준
 * Date : 2017-03-22
 */


public class ActionDoGroupChatRoomJoin implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        // 방이름을 가져온다
        String roomname = request.getParameter("roomname");

        // 그룹채팅 매니저 얻어온다
        ChatGroupManager manager = ChatGroupManager.getInstance();

        // 해당되는 방에 등록한다
        manager.registRoom(request,roomname);

        // 방페이지로 이동한다.
        doForward( request,response,"/MainView/GroupChatRoom.jsp" );

    }

}
