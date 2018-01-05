package com.dummy.handler.action.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dummy.common.controller.Action;


public class LogOutAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// DAO 호출, 페이지 처리
		HttpSession session = request.getSession(false);
		session.removeAttribute("MemberBean");
		
		if(session != null)
			session.invalidate();
		
		return "RequestDispatcher:admin/Login.jsp";
	}
}
