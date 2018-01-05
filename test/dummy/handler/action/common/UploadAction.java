package com.dummy.handler.action.common;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dummy.common.controller.Action;
import com.oreilly.servlet.MultipartRequest;

public class UploadAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		int maxSize = Integer.MAX_VALUE; // 파일허용최대사이즈 
		String realFolder = "";
		String saveFolder = request.getParameter("savefolder"); // 저장할 폴더 명
		String encType = "UTF-8"; // 인코딩방식 
		
		//realFolder = "C:\\" + saveFolder; // 전체경로
		realFolder = request.getServletContext().getRealPath(saveFolder);
		//System.out.println(realFolder);
		MultipartRequest multi = new MultipartRequest(request, realFolder, maxSize, encType);
		
		// rename
		String fileInput = "";
		String oldFileName = "";
		String newFileName = request.getParameter("filename"); // 변경할 이름
		
		fileInput = (String) multi.getFileNames().nextElement(); // 파일인풋 이름
		oldFileName = multi.getFilesystemName(fileInput);
		
		//System.out.println(realFolder + "/" + oldFileName);
		//System.out.println(realFolder + "/" + newFileName);
		
		File oldFile = new File(realFolder + "/" + oldFileName);
		File newFile = new File(realFolder + "/" + newFileName);
		
		if(!newFile.getName().equals(oldFile.getName())) {
			newFile.delete();
			oldFile.renameTo(newFile);
		}
		
		return null;
	}

}
