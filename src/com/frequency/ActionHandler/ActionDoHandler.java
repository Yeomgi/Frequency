package com.frequency.ActionHandler;

import com.frequency.Action.Action;
import com.frequency.Action.Do.ActionDoJoin;
import com.frequency.Action.Do.ActionDoMain;

import java.util.HashMap;

/**
 * Title : Do Action 관리 클래스
 * Author : 염형준
 * Date : 2017-03-04
 */


public class ActionDoHandler implements ActionHandler{

    // key : command , value : Action instance
    private HashMap<String,Action> actions;
    private static ActionDoHandler ourInstance = new ActionDoHandler();

    private ActionDoHandler() {
        actions = new HashMap<String,Action>();
        setActions();
    }

    public static ActionDoHandler getInstance() {
        return ourInstance;
    }

    private void setActions(){
        actions.put("main.do", ActionDoMain.getInstance());
        actions.put("join.do", ActionDoJoin.getInstance());
    }

    @Override
    public Action getAction(String command) {
        Action action = actions.get(command);
        // if(action==null)
        // 에러 처리 action 추가
        return action;
    }

}