package com.flasic.controller;

import com.flasic.admin.controller.action.adminindexAction;
import com.flasic.admin.controller.action.adminloginAction;
import com.flasic.admin.controller.action.adminlogoutAction;
import com.flasic.admin.controller.action.adminmemberlistAction;
import com.flasic.admin.controller.action.adminproductdetailAction;
import com.flasic.admin.controller.action.adminproductlistAction;
import com.flasic.admin.controller.action.adminproductupdateAction;
import com.flasic.admin.controller.action.adminproductupdateformAction;
import com.flasic.admin.controller.action.adminproductwriteAction;
import com.flasic.admin.controller.action.adminproductwriteformAction;
import com.flasic.controller.action.Action;
import com.flasic.controller.action.IndexAction;
import com.flasic.controller.action.designerupdateAction;
import com.flasic.controller.action.idcheckAction;
import com.flasic.controller.action.joinAction;
import com.flasic.controller.action.joinformAction;
import com.flasic.controller.action.loginAction;
import com.flasic.controller.action.loginformAction;
import com.flasic.controller.action.loginoutAction;
import com.flasic.controller.action.memberupdateAction;
import com.flasic.controller.action.memberupdateformAction;
import com.flasic.controller.action.mypageAction;
import com.flasic.controller.action.reqeustdesignerAction;

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
	      }else if (command.equals("mypage")) {
			action = new mypageAction();
	      }else if (command.equals("rqDesigner")) {
			action = new reqeustdesignerAction();
	      }else if (command.equals("memberupdate")) {
			action = new memberupdateformAction();
	      }else if (command.equals("memberupdatesave")) {
			action = new memberupdateAction();
	      }else if (command.equals("designerupdate")) {
			action = new designerupdateAction();
		}
	    
	    //admin
	    if (command.equals("admin_login_form")) {
			action = new adminindexAction();
		}else if (command.equals("admin_login")) {
			action = new adminloginAction();
		}else if (command.equals("adminproductlist")) {
			action = new adminproductlistAction();
		}else if (command.equals("admin_logout")){
			action = new  adminlogoutAction();
		}else if (command.equals("admin_product_write_form")) {
			action = new adminproductwriteformAction();
		}else if (command.equals("admin_product_write")) {
			action = new adminproductwriteAction();
		}else if (command.equals("admin_product_detail")) {
			action = new adminproductdetailAction();
		}else if (command.equals("admin_product_update_form")) {
			action = new adminproductupdateformAction();
		}else if (command.equals("admin_product_update")) {
			action = new adminproductupdateAction();
		}else if (command.equals("admin_member_list")) {
			action = new adminmemberlistAction();
		}
		
		  return action;
		  
	  }
}
