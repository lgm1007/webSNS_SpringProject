package com.mycomp.sns_pjt.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.mycomp.sns_pjt.dao.MDao;

public class MDeleteCommand implements Command {

	// È¸¿ø Å»Åð (Member Delete)
	@Override
	public void execute(Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpSession session = (HttpSession) map.get("session");
		
		String id = (String) session.getAttribute("sid");
		
		MDao mDao = new MDao();
		mDao.mDelete(id);

	}

}
