package com.mycomp.sns_pjt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycomp.sns_pjt.command.Command;
import com.mycomp.sns_pjt.command.LoginCheck;
import com.mycomp.sns_pjt.command.MDeleteCommand;
import com.mycomp.sns_pjt.command.MInsertCommand;
import com.mycomp.sns_pjt.command.MSearchCommand;

@Controller
public class MController {

	Command command;
	
	// 로그인 화면
	@RequestMapping("/login_page")
	public String login_page() {
		return "login_page";
	}
	
	// 로그인 처리
	@RequestMapping(value = "/login", method=RequestMethod.POST)
	public String login(HttpServletRequest request, Model model, HttpSession session) {
		
		model.addAttribute("request", request);
		LoginCheck loginCheck = new LoginCheck();
		loginCheck.check(model, session);
		
		return "home_page";
	}
	
	// 유저 검색
	@RequestMapping("/search_page")
	public String select(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);
		command = new MSearchCommand();
		command.execute(model);
		
		return "search_page";
		
	}
	
	// 회원가입 페이지
	@RequestMapping("/signup_page")
	public String signup_page(Model model) {
		return "signup_page";
	}
	
	// 회원가입 기능
	@RequestMapping(value = "/signup", method=RequestMethod.POST)
	public String insert(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);
		command = new MInsertCommand();
		command.execute(model);
		
		return "redirect:로그인 후 보드페이지로";
		
	}
	
	// 현재 유저 프로필 페이지
	@RequestMapping("/profile_page")
	public String profile_page(Model model) {
		return "profile_page";
	}
	
	// 회원정보 변경 페이지
	@RequestMapping("/setting_page")
	public String setting_page(Model model) {
		return "setting_page";
	}
	
	
	// 회원탈퇴 전 확인페이지
	@RequestMapping("/withdrawal_check")
	public String withdrawal_check(Model model) {
		return "withdrawal_check";
	}
	
	// 회원탈퇴 기능
	@RequestMapping("/withdrawal")
	public String delete(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);
		command = new MDeleteCommand();
		command.execute(model);
		
		return "redirect:로그인 페이지로";
		
	}
	
}
