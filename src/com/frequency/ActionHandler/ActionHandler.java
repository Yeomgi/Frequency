package com.frequency.ActionHandler;

import com.frequency.Action.Action;

/**
 * Title : Action관리 클래스 interface
 * Author : 염형준
 * Date : 2017-03-04
 */


public interface ActionHandler {

    // 커멘드를 넘겨주고 Action 인스턴스를 받는 메소드
    Action getAction(String command);

}
