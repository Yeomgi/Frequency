package com.frequency.Action.Ajax;

import com.frequency.Action.Action;
import com.frequency.Modules.Chat.ChatGroupManager;
import com.frequency.Modules.Json.JsonHandler;
import org.json.simple.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Title : 그룹채팅 방 검색 ajax
 * Author : 염형준
 * Date : 2017-03-24
 */


public class ActionAjaxGroupChatRoomListSearch implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        // UTF-8 인코딩
        setRequestCharacterEncoding_UTF8(request);

        // 검색어를 가져온다
        String keyword = request.getParameter("keyword");

        // 그룹채팅 매니저를 얻어온다
        ChatGroupManager manager = ChatGroupManager.getInstance();

        // 새로운 무작위의 방 리스트를 가져온다
        JSONObject roomsList = JsonHandler.stringToJson( manager.getJsonSearchRoomList(keyword) );

        // Request에 저장한다
        request.setAttribute("roomslist",roomsList);

        // 페이지 포워딩
        doForward(request,response,"/MainView/ajaxPage/GroupChatNewRoomList.jsp");



    }

}
