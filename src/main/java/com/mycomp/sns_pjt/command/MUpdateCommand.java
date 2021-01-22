package com.mycomp.sns_pjt.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.mycomp.sns_pjt.dao.MDao;

public class MUpdateCommand implements Command {

	// 회원정보 수정 (수정할 수 있는 사항은 비밀번호, 이름, 휴대폰번호)
	@Override
	public void execute(Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = (HttpSession) map.get("session");
		
		String user_id = (String) session.getAttribute("sid");
		String update_pw = request.getParameter("update_pw");
		String update_name = request.getParameter("update_name");
		int tel1 = Integer.parseInt(request.getParameter("tel1"));
		int tel2 = Integer.parseInt(request.getParameter("tel2"));
		int tel3 = Integer.parseInt(request.getParameter("tel3"));
		
		MDao mDao = new MDao();
		mDao.mUpdate(user_id, update_pw, update_name, tel1, tel2, tel3);
		
		model.addAttribute("update_name", update_name);
		
	}

}
