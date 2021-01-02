package com.mycomp.sns_pjt.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.mycomp.sns_pjt.dao.MDao;

public class MInsertCommand implements Command {

	@Override
	public void execute(Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		int tel1 = Integer.parseInt(request.getParameter("tel1"));
		int tel2 = Integer.parseInt(request.getParameter("tel2"));
		int tel3 = Integer.parseInt(request.getParameter("tel3"));
		
		MDao mDao = new MDao();
		mDao.mInsert(id, pw, name, tel1, tel2, tel3);
		
	}

}
