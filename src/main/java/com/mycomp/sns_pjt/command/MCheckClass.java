package com.mycomp.sns_pjt.command;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.mycomp.sns_pjt.dao.BDao;
import com.mycomp.sns_pjt.dao.FDao;
import com.mycomp.sns_pjt.dao.MDao;
import com.mycomp.sns_pjt.dto.BDto;
import com.mycomp.sns_pjt.dto.FDto;
import com.mycomp.sns_pjt.dto.MDto;

@Component
public class MCheckClass {
	
	@Autowired
	MSha256 sha256;
	
	@Autowired
	MDao mDao;

	// Login Check
	public void loginCheck(Model model, HttpSession session) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String encPw = sha256.encrypt(pw);
		
		BDao bDao = new BDao();
		FDao fDao = new FDao();
		
		List<MDto> mDtos = mDao.mSelect(id);
		
		// size가 0 이하이면 존재하지 않는 아이디, equals가 false이면 비밀번호가 틀렸음
		if(mDtos.size() > 0) {
			if(mDtos.get(0).getPw().equals(encPw)) {
				session.setAttribute("sid", id);
				session.setAttribute("sname", mDtos.get(0).getName());
				
				ArrayList<BDto> bDtos = bDao.timelineSelect(id);
				
				model.addAttribute("boardList", bDtos);
				
			}
		}
		
	}
	
	// Join Check
	public boolean joinCheck(HttpServletRequest request) {
		
		String create_id = request.getParameter("id");
		
		List<MDto> mDtos = mDao.mSelect(create_id);
		// size가 0 이상이면 같은 아이디가 존재함
		if(mDtos.size() > 0) {
			return false;
		} else {
			return true;
		}
		
	}
	
	// Check Member ID equals Session sid
	public boolean areUSession(String memID, HttpSession session) {
		
		String sid = (String) session.getAttribute("sid");
		
		// 게시글 아이디와 세션 아이디가 같으면 True, 아니면 False 반환
		if(sid.equals(memID)) {
			return true;
		} else {
			return false;
		}
	}
	
	// Withdrawal Check
	public boolean withdrawalCheck(Model model, HttpSession session) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String pw = request.getParameter("re_pw");
		String pw_chk = request.getParameter("re_pw_chk");
		String sid = (String) session.getAttribute("sid");
		
		String encPw = sha256.encrypt(pw);
		
		List<MDto> mDtos = mDao.mSelect(sid);
		
		if((mDtos.get(0).getPw()).equals(encPw) && pw.equals(pw_chk)) {
			return true;
		} else {
			return false;
		}
		
	}
	
}
