package com.flasic.controller;

import com.flasic.admin.controller.action.adminindexAction;
import com.flasic.admin.controller.action.adminloginAction;
import com.flasic.admin.controller.action.adminlogoutAction;
import com.flasic.admin.controller.action.adminproductlistAction;
import com.flasic.controller.action.Action;
import com.flasic.controller.action.IndexAction;
import com.flasic.controller.action.idcheckAction;
import com.flasic.controller.action.joinAction;
import com.flasic.controller.action.joinformAction;
import com.flasic.controller.action.loginAction;
import com.flasic.controller.action.loginformAction;
import com.flasic.controller.action.loginoutAction;

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
	      }else if (command.equals("loginform")) {
			action = new loginformAction();
	      }else if (command.equals("join")) {
			action = new joinAction();
	      }else if (command.equals("joinform")) {
			action = new joinformAction();
	      }else if (command.equals("idcheck")) {
			action = new idcheckAction();
	      }else if (command.equals("login")) {
			action = new loginAction();
	      }else if (command.equals("loginout")) {
			action = new loginoutAction();
		}
	    
	    //adim
	    if (command.equals("admin_login_form")) {
			action = new adminindexAction();
		}else if (command.equals("admin_login")) {
			action = new adminloginAction();
		}else if (command.equals("adminproductlist")) {
			action = new adminproductlistAction();
		}else if (command.equals("admin_logout")){
			action = new  adminlogoutAction();
		}
		
		  return action;
		  
	  }
}
