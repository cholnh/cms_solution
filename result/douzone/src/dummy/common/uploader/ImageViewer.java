package com.dummy.common.uploader;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dummy.handler.dao.common.PathDAO;

@WebServlet("/ImageViewer")
public class ImageViewer extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static final int KEY_SIZE = 1024;
    
    public ImageViewer() {
		super();
	}
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String fileName = (String) request.getAttribute("fileName");
		try{
			FileInputStream fis = new FileInputStream(new PathDAO().getPath("imagefilepath") + fileName);
			BufferedInputStream bis = new BufferedInputStream(fis);
			response.setContentType("image/png");
			BufferedOutputStream output = new BufferedOutputStream(response.getOutputStream());
			for (int data; (data = bis.read()) > -1;) {
				output.write(data);
			}
			output.flush();
			output.close();
			bis.close();
			fis.close();
		}
		catch(FileNotFoundException nf){}
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
