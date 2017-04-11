package com.frequency.Action.DO;

import com.frequency.Action.Action;
import com.frequency.Modules.Chat.ChatGroupManager;
import com.frequency.Modules.Chat.ChatRoom.ChatGroupRoom;
import com.frequency.Modules.ControlHelper;
import com.frequency.Modules.MemberInfo.MemberInfo;

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

        // 방 참여 메세지를 넣는다
        MemberInfo memberinfo = ControlHelper.getMemberInfo(request);
        String nickname = memberinfo.getNickname();

        ChatGroupRoom room = manager.getChatRoom(request);
        room.addSystemLog( "'"+nickname+"'님께서 대화에 참여 하셨습니다."  );

        // 방이름을 request에 저장한다
        request.setAttribute("roomname",roomname);

        // 방페이지로 이동한다.
        doForward( request,response,"/MainView/GroupChatRoom.jsp" );

    }

}
