package com.mycomp.sns_pjt.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.mycomp.sns_pjt.dao.BDao;

public class BUpdateCommand implements Command {

	@Override
	public void execute(Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		int bdKey = Integer.parseInt(request.getParameter("bdEditKey"));
		String content = request.getParameter("content");
		
		BDao bDao = new BDao();
		bDao.bUpdate(bdKey, content);

	}

}
