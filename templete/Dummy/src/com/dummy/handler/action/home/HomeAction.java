package com.dummy.handler.action.home;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dummy.common.controller.Action;
import com.dummy.handler.vo.MemberBean;

public class HomeAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		
		HttpSession session = request.getSession(false);
		MemberBean member = (MemberBean) session.getAttribute("MemberBean");
		
		if(member != null)
		{
			return "RequestDispatcher:jsp/Home.jsp";
		}
		return "RequestDispatcher:404.jsp";
	}

}
