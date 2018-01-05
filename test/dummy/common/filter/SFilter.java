package com.dummy.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebFilter("/*")

public class SFilter implements Filter {
	
	@Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {    
		HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("P3P", "CP='CAO PSA CONi OTR OUR DEM ONL'");
        String URI = request.getRequestURI();
		
        if(URI.contains("ImageViewer/"))
        {
        	String fileName = URI;
    		fileName = fileName.substring(fileName.lastIndexOf("/"), fileName.length());
    		request.setAttribute("fileName", fileName);
        	RequestDispatcher dispatcher = request.getRequestDispatcher("/ImageViewer");
			dispatcher.forward(request, response);
			return;
        }
        chain.doFilter(request, response);
    }

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
