package com.mycomp.sns_pjt.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.mycomp.sns_pjt.dao.CDao;

public class CWriteCommand implements Command {

	@Override
	public void execute(Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = (HttpSession) map.get("session");
		
		int bdKey = Integer.parseInt(request.getParameter("boardKey"));
		String comm_text = request.getParameter("comm_text");
		String sid = (String)session.getAttribute("sid");
		
		CDao cDao = new CDao();
		int reI = cDao.cInsert(bdKey, sid, comm_text);

	}

}
