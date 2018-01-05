package com.manual.douzone.handler.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.manual.douzone.handler.vo.MemberBean;
import com.manual.douzone.common.controller.Action;
import com.manual.douzone.handler.dao.CategoryDAO;
import com.manual.douzone.handler.vo.CategoryBean;

public class CategoryAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		MemberBean member = (MemberBean) session.getAttribute("MemberBean");
		
		CategoryDAO Cdao = new CategoryDAO();
		ArrayList<CategoryBean> categoryList = Cdao.getList();
		
		if(member != null)
		{
			request.setAttribute("categoryList", categoryList);
			
			return "RequestDispatcher:jsp/category.jsp";
		}
		
		return "RequestDispatcher:jsp/404.jsp";
		
	}

}
