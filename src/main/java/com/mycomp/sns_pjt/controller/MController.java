package com.mycomp.sns_pjt.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycomp.sns_pjt.command.Command;
import com.mycomp.sns_pjt.command.MemberCommand;

import com.mycomp.sns_pjt.command.BoardCommand;
import com.mycomp.sns_pjt.dto.MDto;

@Controller
public class MController {

	Command command;
	
	@Autowired
	MemberCommand mCommand;
	
	@Autowired
	BoardCommand timelineSelect;
	
	// 로그인 화면
	@RequestMapping("/login_page")
	public String login_page(Model model, HttpSession session) {
		
		if(session.getAttribute("sid") == null) {
			
			return "login_page";
		} else {
			model.addAttribute("warn", "현재 로그인 중입니다");
			model.addAttribute("url", "home_page");
			return "action/session_exist";
		}
	}
	
	// 로그인 처리
	@RequestMapping(value = "/login", method=RequestMethod.POST)
	public String login(HttpServletRequest request, Model model, HttpSession session) {
		
		model.addAttribute("request", request);
		mCommand.loginCheck(model, session);
		
		if(session.getAttribute("sid") != null) { 
			return "home_page"; 
		} else {
			
			model.addAttribute("warn", "아이디 또는 비밀번호가 틀렸습니다");
			model.addAttribute("url", "login_page");
			return "action/login_fail";
			
		}
	}
	
	// 회원가입 화면
	@RequestMapping("/join_page")
	public String join_page() {
		return "join_page";
	}
	
	// 회원가입 기능
	@RequestMapping(value = "/join", method=RequestMethod.POST)
	public String join(HttpServletRequest request, Model model, HttpSession session) {
		
		boolean bool = mCommand.joinCheck(request);
		
		if(bool == false) {
			model.addAttribute("warn", "같은 아이디가 존재합니다");
			model.addAttribute("url", "join_page");
			return "action/join_fail";
		} else {
			
			model.addAttribute("request", request);
			mCommand.insertExecute(model);
			
			session.setAttribute("sid", request.getParameter("id"));
			session.setAttribute("sname", request.getParameter("name"));
			
			model.addAttribute("session", session);
			timelineSelect.timeLine(model);
			
			return "home_page";
			
		}
	}
	
	// 유저 검색
	@RequestMapping(value = "/search_page", method=RequestMethod.POST)
	public String select(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);
		mCommand.searchExecute(model);
		
		return "search_page";
		
	}
	
	// 게시글 작성이가 세션(본인)인지 확인 후 다른 사람 프로필페이지로 이동
	@RequestMapping(value = "/areUSession", method=RequestMethod.POST)
	public String checkUSession(HttpServletRequest request, Model model, HttpSession session) {
		
		String memID = request.getParameter("userId");
		
		boolean bool = mCommand.areUSession(memID, session);
		
		if(bool == true) {
			return "profile_page";
		} else {
			model.addAttribute("request", request);
			mCommand.getOtherProfile(model);
			
			return "others_page";
		}
		
	}
	
	// 현재 유저 프로필 페이지 (MyProfile)
	@RequestMapping("/profile_page")
	public String profile_page(Model model, HttpSession session) {
		
		if(session.getAttribute("sid") != null) {
			model.addAttribute("session", session);
			mCommand.getMyProfile(model);
			return "profile_page";
			
		} else {
			model.addAttribute("warn", "유효한 세션이 없습니다");
			model.addAttribute("url", "login_page");
			return "action/no_session";
		}
	}
	
	// 회원정보 변경 페이지
	@RequestMapping("/setting_page")
	public String setting_page(Model model) {
		return "setting_page";
	}
	
	@RequestMapping(value = "/update_member", method=RequestMethod.POST)
	public String update_member(HttpServletRequest request, Model model, HttpSession session) {
		
		model.addAttribute("request", request);
		model.addAttribute("session", session);
		mCommand.updateExecute(model);
		
		Map<String, Object> map = model.asMap();
		String updated_name = (String) map.get("update_name");
		session.setAttribute("sname", updated_name);
		
		return "profile_page";
		
	}
	
	// 회원탈퇴 전 확인페이지
	@RequestMapping("/withdrawal_check")
	public String withdrawal_check(Model model) {
		return "withdrawal_check";
	}
	
	// 회원탈퇴 기능
	@RequestMapping(value = "/withdrawal", method=RequestMethod.POST)
	public String delete(HttpServletRequest request, Model model, HttpSession session) {
		
		model.addAttribute("request", request);
		boolean b = mCommand.withdrawalCheck(model, session);
		
		if(b) {
			model.addAttribute("session", session);
			mCommand.deleteExecute(model);
			
			session.invalidate();
			return "login_page";
		} else {
			model.addAttribute("mes", "비밀번호가 다릅니다");
			model.addAttribute("goback", "withdrawal_check");
			return "action/withdrawal_fail";
		}
	}
	
	// 로그아웃 전 확인
	@RequestMapping("/logout")
	public String logoutCheck(Model model) {
		
		model.addAttribute("warn", "로그아웃 하겠습니까?");
		model.addAttribute("urlTrue", "logout_execute");
		model.addAttribute("urlFalse", "profile_page");
		return "action/logout_check";
	}
	
	// 로그아웃 실행
	@RequestMapping("/logout_execute")
	public String logout(Model model, HttpSession session) {
		session.invalidate();
		return "login_page";
	}
	
	// 다른 유저 프로필페이지 이동
	@RequestMapping(value="/others_page", method=RequestMethod.POST)
	public String othersPage(HttpServletRequest request, Model model, HttpSession session) {
		
		if(session.getAttribute("sid") != null) {
			model.addAttribute("request", request);
			mCommand.getOtherProfile(model);
			
			return "others_page";
			
		} else {
			model.addAttribute("warn", "유효한 세션이 없습니다");
			model.addAttribute("url", "login_page");
			return "action/no_session";
		}
	}
	
}
