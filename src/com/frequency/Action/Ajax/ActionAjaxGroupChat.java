package com.frequency.Action.Ajax;

import com.frequency.Action.Action;
import com.frequency.Modules.Chat.ChatGroupManager;
import com.frequency.Modules.Chat.ChatRoom.ChatGroupRoom;
import com.frequency.Modules.MemberInfo.MemberInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Title : 그룹채팅 통신 Ajax
 * Author : 염형준
 * Date : 2017-03-27
 */


public class ActionAjaxGroupChat implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        // 커맨드를 분리한다
        String command = getCommand(request);

        // 닉네임을 가져온다.
        MemberInfo memberinfo = (MemberInfo) getSessioin(request).getAttribute("memberinfo");
        String nickname = memberinfo.getNickname();

        // 그룹채팅 매니저를 얻어온다
        ChatGroupManager manager = ChatGroupManager.getInstance();

        // 유저에 해당하는 채팅방을 가져온다
        ChatGroupRoom room = manager.getChatRoom(request);


        try (
                // Ajax통신에 사용될 printwriter를 가져옴
                PrintWriter printWriter = getPrintWirter(response);
        ){

            // 쓰기 / 가져오기 / 종료
            switch ( command ){

                case "randomWrite.ajax" :
                    room.addLog(request,nickname);
                    break;

                case "randomRead.ajax" :
                    printWriter.print( room.getlog(request,nickname) );
                    break;

                case "randomExit.ajax" :
                    manager.exitChat(request);
                    break;

            }

        }


    }

    //  Command 문자열을 추출
    private String getCommand(HttpServletRequest request){
        return request.getRequestURI().substring(request.getRequestURI().lastIndexOf('/')+1);
    }

}
