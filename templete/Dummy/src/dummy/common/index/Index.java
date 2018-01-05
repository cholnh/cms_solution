package com.dummy.common.index;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static final int KEY_SIZE = 1024;
    
    public Index() {
		super();
	}
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
        	response.setContentType("text/html;charset=UTF-8");
        	
        	@SuppressWarnings("unused")
			HttpSession session = request.getSession();
        	@SuppressWarnings("unused")
			String refer = request.getHeader("Referer");
        	/*if(refer != null)
        	{
        		if(refer.matches("\\b(https?)://" + "localhost"  + "[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]"))
        		{
            		request.getRequestDispatcher("WEB-INF/user/intro.jsp").forward(request, response);
        		}
        	}*/
        	request.getRequestDispatcher("WEB-INF/jsp/home.jsp").forward(request, response);
        } catch (Exception ex) {
            throw new ServletException(ex.getMessage(), ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
