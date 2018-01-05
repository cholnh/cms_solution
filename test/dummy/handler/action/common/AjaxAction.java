package com.dummy.handler.action.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dummy.common.controller.Action;


public class AjaxAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");

		String req = request.getParameter("req");
		String data = request.getParameter("data");
		
		HttpSession session = request.getSession(false);
		
		String result = null;
		switch(req)
		{
			default:
				break;
		}

		if (result != null) {
			return result;
		}
		
		return null;
	}

}
