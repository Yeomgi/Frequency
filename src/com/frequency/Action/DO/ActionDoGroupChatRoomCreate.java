package com.frequency.Action.DO;

import com.frequency.Action.Action;
import com.frequency.Modules.Chat.ChatGroupManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Title : 그룹채팅 방개설 Action
 * Author : 염형준
 * Date : 2017-03-21
 */


public class ActionDoGroupChatRoomCreate implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        // 방 이름을 가져온다
        String roomname = request.getParameter("roomname");

        // 그룹채팅 매니저 얻어온다
        ChatGroupManager manager = ChatGroupManager.getInstance();

        // 방을생성
        manager.createRoom(roomname);

        // 생성된방에 개설자를 등록한다
        manager.registRoom(request,roomname);

        // 방페이지로 이동한다.
        doForward( request,response,"/MainView/GroupChatRoom.jsp" );

    }


}
