package com.dummy.handler.action.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dummy.common.controller.Action;
import com.dummy.common.filter.LoginManager;
import com.dummy.handler.dao.common.AdminDAO;
import com.dummy.handler.vo.MemberBean;

public class ReLoginAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession(false);
		// 0 : id, 1 : pw
		String id = (String) session.getAttribute("loginID");
		String pw = (String) session.getAttribute("loginPW");
		//String[] userInfo = data.split("-/-/-");
		if(id == null || pw == null) return null;
		AdminDAO dao = new AdminDAO();
		boolean isAvaliableID = dao.getUsername(id);
		if(!isAvaliableID)
		{
			session.setAttribute("Message", "id");
			return "RequestDispatcher:admin/Login.jsp";
		}
		
		boolean isAvaliablePW = dao.getPassword(id, pw);
		if(isAvaliableID && !isAvaliablePW)
		{
			session.setAttribute("Message", "pw");
			return "RequestDispatcher:admin/Login.jsp";
		}
		
		LoginManager loginManager = LoginManager.getInstance();
		if(loginManager.isLogin(session.getId()) || loginManager.isUsing(id))
		{
			// Login 성공
			loginManager.removeIsUsing(id);
			loginManager.setSession(session, id);
			MemberBean member = dao.getUserInfo(id, pw);
			session.removeAttribute("loginID");
			session.removeAttribute("loginPW");
			session.setAttribute("MemberBean", member);
			request.getRequestDispatcher("/home.do").forward(request, response);
			return "";
		}
		return "RequestDispatcher:admin/Login.jsp";
	}

}
