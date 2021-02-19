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
public class MemberCommand {
	
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
	
	// Search
	public void searchExecute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String word = request.getParameter("srch");
		System.out.println(word);
		List<MDto> mDtos = mDao.mSearch(word);

		model.addAttribute("memberSearch", mDtos);
	}
	
	//Insert Member
	public void insertExecute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		int tel1 = Integer.parseInt(request.getParameter("tel1"));
		int tel2 = Integer.parseInt(request.getParameter("tel2"));
		int tel3 = Integer.parseInt(request.getParameter("tel3"));
		
		String encPw = sha256.encrypt(pw);
		
		mDao.mInsert(id, encPw, name, tel1, tel2, tel3);
	}
	
	// Update Member
	public void updateExecute(Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = (HttpSession) map.get("session");
		
		String user_id = (String) session.getAttribute("sid");
		String update_pw = request.getParameter("update_pw");
		String update_name = request.getParameter("update_name");
		int tel1 = Integer.parseInt(request.getParameter("tel1"));
		int tel2 = Integer.parseInt(request.getParameter("tel2"));
		int tel3 = Integer.parseInt(request.getParameter("tel3"));
		
		String encPw = sha256.encrypt(update_pw);
		
		mDao.mUpdate(user_id, encPw, update_name, tel1, tel2, tel3);
		
		model.addAttribute("update_name", update_name);
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
	
	// Get for My Profile
	public void getMyProfile(Model model) {

		Map<String, Object> map = model.asMap();
		HttpSession session = (HttpSession) map.get("session");
		
		String sid = (String) session.getAttribute("sid");

		BDao bDao = new BDao();
		FDao fDao = new FDao();
		ArrayList<BDto> bDtos = bDao.bSelect(sid);
		ArrayList<FDto> follows = fDao.selectFollow(sid);
		ArrayList<FDto> followers = fDao.selectFollower(sid);
		
		model.addAttribute("boardList", bDtos);
		model.addAttribute("followCount", follows.size());
		model.addAttribute("followerCount", followers.size());
	}
	
	// Get for OtherUser Profile
	public void getOtherProfile(Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String uid = request.getParameter("userId");
		
		BDao bDao = new BDao();
		FDao fDao = new FDao();
		
		List<MDto> mdtos = mDao.mSelect(uid);
		ArrayList<BDto> bdtos = bDao.bSelect(uid);
		ArrayList<FDto> follows = fDao.selectFollow(uid);
		ArrayList<FDto> followers = fDao.selectFollower(uid);
		
		String uname = mdtos.get(0).getName();
		
		model.addAttribute("userID", uid);
		model.addAttribute("userName", uname);
		model.addAttribute("boardList", bdtos);
		model.addAttribute("followCount", follows.size());
		model.addAttribute("followerCount", followers.size());
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
	
	// Delete Member (Withdrawal)
	public void deleteExecute(Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpSession session = (HttpSession) map.get("session");
		
		String id = (String) session.getAttribute("sid");
		
		mDao.mDelete(id);
	}
	
}
