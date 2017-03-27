package com.frequency.Action.DO;

import com.frequency.Action.Action;
import com.frequency.Modules.Chat.ChatGroupManager;
import com.frequency.Modules.Json.JsonHandler;
import org.json.simple.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Title : 그룹채팅 리스트 Action
 * Author : 염형준
 * Date : 2017-03-21
 */


public class ActionDoGroupChat implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        // 그룹채팅 매니저를 얻어온다
        ChatGroupManager manager = ChatGroupManager.getInstance();


        /*테스트
        manager.createRoom("test1");
        manager.createRoom("test2");
        manager.createRoom("test3");
        manager.createRoom("test4");
        manager.createRoom("test5");
        manager.createRoom("test5");
        manager.createRoom("test5");
        manager.createRoom("test5");
        manager.createRoom("test5");
        manager.createRoom("test5");
        manager.createRoom("test5");

        */

        manager.createRoom("가가나나다다");
        manager.createRoom("가가나다다다");
        manager.createRoom("가가나나라라");
        manager.createRoom("가가나나다라");
        manager.createRoom("가가가나다다");
        manager.createRoom("가가나나나다");
        manager.createRoom("가가다다다다");
        manager.createRoom("가가나나나나");
        manager.createRoom("가가가가가가");
        manager.createRoom("나나나나나나");
        manager.createRoom("다다다다다다");
        manager.createRoom("라라라라라라");


        // 방 리스트를 가져온다
        JSONObject roomsList = JsonHandler.stringToJson( manager.getJsonRoomlist() );

        // Request에 저장한다
        request.setAttribute("roomslist",roomsList);

        // 페이지 포워딩
        doForward(request,response,"/MainView/GroupChat.jsp");


    }

}
