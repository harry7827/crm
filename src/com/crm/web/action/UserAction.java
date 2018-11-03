package com.crm.web.action;

import java.util.List;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.crm.domain.User;
import com.crm.service.UserService;

public class UserAction extends BaseAction<User> {
	
	@Autowired
	private UserService userService;
	
	public String regist() {
		userService.regist(model);
		return "homeSuccess";
	}
	public String login() {
		model=userService.login(model);
		if (model==null) {
			this.addActionError("用户名或密码错误!");
			return "login";
		}else {
			if (model.getUser_state().equals("1")) {
				ServletActionContext.getRequest().getSession().setAttribute("existUser", model);
				return SUCCESS;
			}else {
				this.addActionError("您操作的用户已经登录权限已经过期");
				return "login";
			}
		}
	}
	
	public String findAll(){
		List<User> list = userService.findAll();
		ServletActionContext.getContext().getValueStack().push(list);
		return "list";
	}
}
