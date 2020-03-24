package com.xf.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends MethodFilterInterceptor {

    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        Object username = request.getSession().getAttribute("username");
        if (username != null){
            return actionInvocation.invoke();
        }else {
            return "loginPage";
        }
    }
}
