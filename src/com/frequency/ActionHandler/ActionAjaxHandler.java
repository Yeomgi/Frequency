package com.frequency.ActionHandler;

import com.frequency.Action.Action;
import com.frequency.Action.Ajax.*;
import com.frequency.Action.Ajax.ActionAjaxRandomChat;

import java.util.HashMap;

/**
 * Title : Ajax Action 관리 클래스
 * Author : 염형준
 * Date : 2017-03-04
 */


public class ActionAjaxHandler implements ActionHandler{

    private static ActionAjaxHandler ourInstance = new ActionAjaxHandler();
    // key : command , value : Action instance
    private HashMap<String,Action> actions;

    private ActionAjaxHandler() {
        actions = new HashMap<String,Action>();
        setActions();
    }

    public static ActionAjaxHandler getInstance() {
        return ourInstance;
    }

    // 커멘드에 해당하는 Action 인스턴스를 세팅
    private void setActions(){
        actions.put( "exist.ajax", new ActionAjaxExist() );
        actions.put( "randomWrite.ajax", new ActionAjaxRandomChat() );
        actions.put( "randomRead.ajax", actions.get("randomWrite.ajax") );
        actions.put( "randomExit.ajax", actions.get("randomWrite.ajax") );
        actions.put( "getIP.ajax", new ActionAjaxGetIP() );
    }

    @Override
    public Action getAction(String command) {
        Action action = actions.get(command);
        // if(action==null)
        // 에러 처리 action 추가
        return action;
    }

}
