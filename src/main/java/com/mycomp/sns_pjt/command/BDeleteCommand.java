package com.mycomp.sns_pjt.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.mycomp.sns_pjt.dao.BDao;

public class BDeleteCommand implements Command {

	// 세션 유저의 선택한 bdKey의 글 삭제
	@Override
	public void execute(Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = (HttpSession) map.get("session");
		
		int bdKey = Integer.parseInt(request.getParameter("bdKey"));
		String sid = (String)session.getAttribute("sid");

		BDao bDao = new BDao();
		bDao.bDelete(sid, bdKey);
		
	}

}
