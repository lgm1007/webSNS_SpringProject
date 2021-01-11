package com.mycomp.sns_pjt.command;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.mycomp.sns_pjt.dao.MDao;
import com.mycomp.sns_pjt.dto.MDto;

public class WithdrawalCheck {

	public boolean withCheck(Model model, HttpSession session) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String pw = request.getParameter("re_pw");
		String pw_chk = request.getParameter("re_pw_chk");
		String sid = (String) session.getAttribute("sid");
		
		MDao mDao = new MDao();
		ArrayList<MDto> mDtos = mDao.mSelect(sid);
		
		if((mDtos.get(0).getPw()).equals(pw) || pw.equals(pw_chk)) {
			return true;
		} else {
			return false;
		}
		
	}
	
}
