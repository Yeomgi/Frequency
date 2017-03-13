package com.frequency.Action.Ajax;

import com.frequency.Action.Action;
import com.frequency.Controller.Controller;
import com.frequency.Modules.Chat.ChatRandomManager;
import com.frequency.Modules.Chat.ChatRoom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Title : 랜덤채팅 통신 Ajax
 * Author : 염형준
 * Date : 2017-03-09
 */

/**
 * <랜덤채팅 통신 값>
 * -1 : 한쪽의 일방적인 채팅 종료로 종료됨
 *
 */
public class ActionAjaxRandomChat implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        String command = getCommand(request);
        ChatRoom room = ChatRandomManager.getInstance().getChatRoom(request);

        try (
                // Ajax통신에 사용될 printwriter를 가져옴
                PrintWriter printWriter = getPrintWirter(response);
        ){

            if ( room==null ){
                printWriter.print(-1);
            }

            // 커맨드에 따라 처리
            switch ( command ){
                case "randomWrite.ajax" :
                    room.addLog(request);
                    break;
                case "randomRead.ajax" :

                    break;
                case "randomExit.ajax" :

                    break;
            }
        }

    }

    //  Command 문자열을 추출
    private String getCommand(HttpServletRequest request){
        return request.getRequestURI().substring(request.getRequestURI().lastIndexOf('/')+1);
    }

}
