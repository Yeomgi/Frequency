package com.frequency.Action.Ajax;

import com.frequency.Action.Action;
import com.frequency.Modules.Chat.ChatGroupManager;
import com.frequency.Modules.Chat.ChatRoom.ChatGroupRoom;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Title : 채팅방 접속자 리스트 반환 ajax
 * Author : 염형준
 * Date : 2017-03-28
 */


public class ActionAjaxGroupChatGetMemberList implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        // UTF-8 인코딩
        setResponseCharacterEncoding_UTF8(response);

        // 그룹채팅 매니저를 얻어온다
        ChatGroupManager manager = ChatGroupManager.getInstance();

        // 멤버목록을 가져온다
        ChatGroupRoom room = manager.getChatRoom(request);
        String[] memberlist = room.getMemberlist();

        // Json 생성에 쓰일 문자열
        String prefixTag = "<div class=\"member\">";
        String postfixTag = "</div>\n";

        JSONArray temparr;
        JSONObject json = new JSONObject();

        // 태그와 함께 목록을만들어준다
        for(int i=0; i<memberlist.length; i++){

            temparr = new JSONArray();
            temparr.add(prefixTag);
            temparr.add(memberlist[i]);
            temparr.add(postfixTag);

            json.put(i,temparr);

        }

        // 작성된 목록을 전송한다.
        try( PrintWriter printWriter = getPrintWirter(response);)
        { printWriter.print( json.toJSONString() ); }

    }

}
