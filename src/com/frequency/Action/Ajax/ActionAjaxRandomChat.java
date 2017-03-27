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

        // UTF-8 인코딩
        setRequestCharacterEncoding_UTF8(request);
        setResponseCharacterEncoding_UTF8(response);

        // command를 재추출해 사용한다
        String command = getCommand(request);

        // 랜덤채팅Manager를 얻어와 사용자에 해당하는 채팅방을 가져온다
        ChatRandomManager manager = ChatRandomManager.getInstance();
        ChatRoom room = manager.getChatRoom(request);

        try (
                // Ajax통신에 사용될 printwriter를 가져옴
                PrintWriter printWriter = getPrintWirter(response);
        ){
            // 채팅 상태 열거형 반환시
            if( room instanceof ChatSatuse ){

                // Type Casting
                ChatSatuse satuse = (ChatSatuse) room;
                // 채팅이 매치되지 않았을때 / 상대방의 종료
                switch ( satuse ){

                    case NOMATCH :
                        printWriter.print(-2);
                        break;

                    case EXIT:
                        printWriter.print(-1);
                        break;

                }

            }
            // 사용자 채팅방 반환시
            else if( room instanceof ChatSingleRoom ){

                // Type Casting
                ChatSingleRoom randomRoom = (ChatSingleRoom) room;
                // 쓰기 / 가져오기 / 종료
                switch ( command ){

                    case "randomWrite.ajax" :
                        randomRoom.addLog(request);
                        break;

                    case "randomRead.ajax" :
                        printWriter.print( randomRoom.getlog(request) );
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
