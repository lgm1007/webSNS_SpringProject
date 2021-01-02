package com.mycomp.sns_pjt.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.mycomp.sns_pjt.dao.MDao;

public class MDeleteCommand implements Command {

	@Override
	public void execute(Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String id = request.getParameter("id");
		
		MDao mDao = new MDao();
		mDao.mDelete(id);

	}

}
