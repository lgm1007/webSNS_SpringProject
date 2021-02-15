package com.mycomp.sns_pjt.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.mycomp.sns_pjt.dao.MDao;

public class MDeleteCommand implements Command {

	@Autowired
	MDao mDao;
	
	// È¸¿ø Å»Åð (Member Delete)
	@Override
	public void execute(Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpSession session = (HttpSession) map.get("session");
		
		String id = (String) session.getAttribute("sid");
		
		mDao.mDelete(id);

	}

}
