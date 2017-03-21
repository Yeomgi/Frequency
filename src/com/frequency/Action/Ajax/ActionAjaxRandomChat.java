package com.frequency.Action.Ajax;

import com.frequency.Action.Action;
import com.frequency.Modules.Chat.ChatRandomManager;
import com.frequency.Modules.Chat.ChatRoom.ChatRoom;
import com.frequency.Modules.Chat.ChatRoom.ChatSatuse;
import com.frequency.Modules.Chat.ChatRoom.ChatSingleRoom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Title : 랜덤채팅 통신 Ajax
 * Author : 염형준
 * Date : 2017-03-09
 */

/**
 * <랜덤채팅 통신 코드>
 * -1 : 한쪽의 일방적인 종료로 채팅종료
 * -2 : 방 매치가 안됨
 */
public class ActionAjaxRandomChat implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        setRequestCharacterEncoding_UTF8(request);
        setResponseCharacterEncoding_UTF8(response);

        String command = getCommand(request);
        ChatRandomManager manager = ChatRandomManager.getInstance();
        ChatRoom room = manager.getChatRoom(request);

        try (
                // Ajax통신에 사용될 printwriter를 가져옴
                PrintWriter printWriter = getPrintWirter(response);
        ){
            // 채팅 상태 열거형 일 경우
            if( room instanceof ChatSatuse ){
                ChatSatuse satuse = (ChatSatuse) room;

                switch ( satuse ){
                    case NOMATCH :
                        printWriter.print(-2);
                        break;
                    case EXIT:
                        printWriter.print(-1);
                        break;
                }

            }
            // 일반 채팅방일 경우
            else if( room instanceof ChatSingleRoom ){
                ChatSingleRoom randomRoom = (ChatSingleRoom) room;

                switch ( command ){
                    case "randomWrite.ajax" :
                        randomRoom.addLog(request);
                        break;
                    case "randomRead.ajax" :
                        String log = randomRoom.getlog(request);
                        printWriter.print(log);
                        break;
                    case "randomExit.ajax" :
                        manager.exitChat(request);
                        break;
                }

            }
        }

    }

    //  Command 문자열을 추출
    private String getCommand(HttpServletRequest request){
        return request.getRequestURI().substring(request.getRequestURI().lastIndexOf('/')+1);
    }

}
