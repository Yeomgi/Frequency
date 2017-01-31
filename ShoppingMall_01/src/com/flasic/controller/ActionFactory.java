package com.flasic.controller;

import com.flasic.controller.action.Action;

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
	    	
	      }
		
		  return action;
		  
	  }
}
