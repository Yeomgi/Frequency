package com.flasic.controller;

import com.flasic.controller.action.Action;
import com.flasic.controller.action.IndexAction;
import com.flasic.controller.action.idcheckAction;
import com.flasic.controller.action.joinAction;
import com.flasic.controller.action.joinformAction;
import com.flasic.controller.action.loginAction;

public class ActionFactory {
	private static ActionFactory instance = new ActionFactory();
	private ActionFactory() {
		super();
	}
	public static ActionFactory getInstance() {
		return instance;
	}
	  public Action getAction(String command) {
		Action action =null;
	    if (command.equals("index")) {
	    	action = new IndexAction();
	      }else if (command.equals("login")) {
			action = new loginAction();
	      }else if (command.equals("join")) {
			action = new joinAction();
	      }else if (command.equals("joinform")) {
			action = new joinformAction();
	      }else if (command.equals("idcheck")) {
			action = new idcheckAction();
	      }
		
		  return action;
		  
	  }
}
