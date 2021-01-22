package com.mycomp.sns_pjt.ajaxServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mycomp.sns_pjt.dao.LDao;

public class UnlikeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String mem_id = request.getParameter("mem_id");
		int bd_key = Integer.parseInt(request.getParameter("bd_key"));
		
		response.getWriter().write(unlikeFunction(bd_key, mem_id) + "");
	}
	// 좋아요 취소 (Like delete)
	public int unlikeFunction(int bd_key, String mem_id) {
		LDao lDao = new LDao();
		int reI = lDao.lDelete(bd_key, mem_id);
		return reI;
	}
	
}
