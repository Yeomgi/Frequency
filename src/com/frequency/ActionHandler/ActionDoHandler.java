package com.frequency.ActionHandler;

import com.frequency.Action.Action;
import com.frequency.Action.DO.ActionDoJoindone;
import com.frequency.Action.DO.*;

import java.util.HashMap;

/**
 * Title : DO Action 관리 클래스
 * Author : 염형준
 * Date : 2017-03-04
 */


public class ActionDoHandler implements ActionHandler{

    private static ActionDoHandler ourInstance = new ActionDoHandler();
    private HashMap<String,Action> actions;     // key : command , value : Action instance

    private ActionDoHandler() {
        actions = new HashMap<String,Action>();
        setActions();
    }

    public static ActionDoHandler getInstance() {
        return ourInstance;
    }

    // 커멘드에 해당하는 Action 인스턴스를 세팅
    private void setActions(){
        actions.put( "main.do", new ActionDoMain() );
        actions.put( "join.do", new ActionDoJoin() );
        actions.put( "joindone.do", new ActionDoJoindone() );
        actions.put( "login.do", new ActionDoLogin() );
        actions.put( "logindone.do", new ActionDoLoginDone() );
        actions.put( "logout.do", new ActionDoLogout() );
        actions.put( "randomchat.do", new ActionDoRandomChat() );
        actions.put( "groupchat.do", new ActionDoGroupChat() );
        actions.put( "groupchatRoomCreate.do", new ActionDoGroupChatRoomCreate() );
        actions.put( "groupchatRoomJoin.do", new ActionDoGroupChatRoomJoin() );
    }

    @Override
    public Action getAction(String command) {
        Action action = actions.get(command);
        // if(action==null)
        // 에러 처리 action 추가
        return action;
    }

}
