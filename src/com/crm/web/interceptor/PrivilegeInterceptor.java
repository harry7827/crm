package com.crm.web.interceptor;

import org.apache.struts2.ServletActionContext;

import com.crm.domain.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class PrivilegeInterceptor extends MethodFilterInterceptor{

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		User user=(User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if (user==null) {
			ActionSupport actionSupport=(ActionSupport) invocation.getAction();
			actionSupport.addActionError("没有登录！没有权限访问！");
			return actionSupport.LOGIN;
		}else {
			return invocation.invoke();
		}
	}
	
}
