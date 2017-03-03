package com.frequency.ActionHandler;

import com.frequency.Action.Action;

/**
 * Title : Action관리 클래스 interface
 * Author : 염형준
 * Date : 2017-03-04
 */


public interface ActionHandler {
    Action getAction(String command);
}
