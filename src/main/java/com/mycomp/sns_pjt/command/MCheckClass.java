package com.mycomp.sns_pjt.command;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.mycomp.sns_pjt.dao.BDao;
import com.mycomp.sns_pjt.dao.FDao;
import com.mycomp.sns_pjt.dao.MDao;
import com.mycomp.sns_pjt.dto.BDto;
import com.mycomp.sns_pjt.dto.FDto;
import com.mycomp.sns_pjt.dto.MDto;

public class MCheckClass {

	public void loginCheck(Model model, HttpSession session) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		BDao bDao = new BDao();
		MDao mDao = new MDao();
		FDao fDao = new FDao();
		
		ArrayList<MDto> mDtos = mDao.mSelect(id);
		
		// mDtos.size 값이 0 이하이면 존재하지 않는 아이디, equals가 false이면 비밀번호가 틀렸음
		if(mDtos.size() > 0) {
			if(mDtos.get(0).getPw().equals(pw)) {
				session.setAttribute("sid", id);
				session.setAttribute("sname", mDtos.get(0).getName());
				
				ArrayList<BDto> bDtos = bDao.bSelect(id);
				ArrayList<FDto> followDtos = fDao.selectFollow(id);
				ArrayList<FDto> followerDtos = fDao.selectFollower(id);
				
				model.addAttribute("boardList", bDtos);
				model.addAttribute("followList", followDtos);
				model.addAttribute("followerList", followerDtos);
				model.addAttribute("followCount", followDtos.size());
				model.addAttribute("followerCount", followerDtos.size());
			}
		}
		
	}
	
	public boolean joinCheck(HttpServletRequest request) {
		
		String create_id = request.getParameter("id");
		
		MDao mDao = new MDao();
		ArrayList<MDto> mDtos = mDao.mSelect(create_id);
		// mDtos 사이즈 0 이상이면 같은 아이디가 존재함
		if(mDtos.size() > 0) {
			return false;
		} else {
			return true;
		}
		
	}
	
	public boolean withdrawalCheck(Model model, HttpSession session) {
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
