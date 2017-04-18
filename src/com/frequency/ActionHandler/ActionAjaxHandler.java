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
    private HashMap<String,Action> actions;     // key : command , value : Action instance

    private ActionAjaxHandler() {
        actions = new HashMap<String,Action>();
        setActions();
    }

    public static ActionAjaxHandler getInstance() {
        return ourInstance;
    }

    // 커멘드에 해당하는 Action 인스턴스를 세팅
    private void setActions(){
        actions.put( "joinExist.ajax", new ActionAjaxJoinValueExist() );
        actions.put( "logincheck.ajax", new ActiocAjaxLoginCheck() );
        actions.put( "randomWrite.ajax", new ActionAjaxRandomChat() );
        actions.put( "randomRead.ajax", actions.get("randomWrite.ajax") );
        actions.put( "randomExit.ajax", actions.get("randomWrite.ajax") );
        actions.put( "getIP.ajax", new ActionAjaxGetIP() );
        actions.put( "getNickName.ajax", new ActionAjaxGetNickName() );
        actions.put( "chatRoomNameExist.ajax", new ActionAjaxGroupChatRoomNameExist() );
        actions.put( "chatRoomCheckJoinUser.ajax", new ActionAjaxGroupChatCheckJoinUser() );
        actions.put( "chatRoomListRefresh.ajax", new ActionAjaxGroupChatRoomLIstRefresh() );
        actions.put( "chatRoomListSearch.ajax", new ActionAjaxGroupChatRoomListSearch() );
        actions.put( "chatRoomGetMemberList.ajax", new ActionAjaxGroupChatGetMemberList() );
        actions.put( "groupWrite.ajax", new ActionAjaxGroupChat() );
        actions.put( "groupRead.ajax", actions.get("groupWrite.ajax") );
        actions.put( "groupExit.ajax", actions.get("groupWrite.ajax") );
        actions.put( "sendMessage.ajax", new ActionAjaxSendMessage() );
        actions.put( "isLogin.ajax", new ActionAjaxIsLogin() );
    }

    @Override
    public Action getAction(String command) {
        Action action = actions.get(command);
        // if(action==null)
        // 에러 처리 action 추가
        return action;
    }

}
