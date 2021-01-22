package com.mycomp.sns_pjt.ajaxServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mycomp.sns_pjt.dao.FDao;

public class FollowRegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String follow = request.getParameter("follow");
		String follower = request.getParameter("follower");
		
		response.getWriter().write(registerFollow(follow, follower) + "");
	}
	// �ȷο� (Follow insert) 
	public int registerFollow(String follow, String follower) {
		FDao fDao = new FDao();
		int reI = fDao.fInsert(follow, follower);
		return reI;
	}
}
