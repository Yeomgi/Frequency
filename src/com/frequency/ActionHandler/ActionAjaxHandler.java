package com.frequency.ActionHandler;

import com.frequency.Action.Action;
import com.frequency.Action.Ajax.ActionAjaxExist;

import java.util.HashMap;

/**
 * Title : Ajax Action 관리 클래스
 * Author : 염형준
 * Date : 2017-03-04
 */


public class ActionAjaxHandler implements ActionHandler{

    // key : command , value : Action instance
    private HashMap<String,Action> actions;
    private static ActionAjaxHandler ourInstance = new ActionAjaxHandler();

    private ActionAjaxHandler() {
        actions = new HashMap<String,Action>();
        setActions();
    }

    public static ActionAjaxHandler getInstance() {
        return ourInstance;
    }

    // 커멘드에 해당하는 Action 인스턴스를 세팅
    private void setActions(){
        //actions.put( "Exist.ajax", ActionAjaxExist.getInstance() );
        //actions.put( "Exist.ajax", new ActionAjaxExist() );
    }

    @Override
    public Action getAction(String command) {
        Action action = actions.get(command);
        // if(action==null)
        // 에러 처리 action 추가
        return action;
    }

}
