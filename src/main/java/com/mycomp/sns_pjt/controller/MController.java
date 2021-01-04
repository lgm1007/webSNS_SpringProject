package com.mycomp.sns_pjt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycomp.sns_pjt.command.Command;
import com.mycomp.sns_pjt.command.JoinCheck;
import com.mycomp.sns_pjt.command.LoginCheck;
import com.mycomp.sns_pjt.command.MDeleteCommand;
import com.mycomp.sns_pjt.command.MInsertCommand;
import com.mycomp.sns_pjt.command.MSearchCommand;

@Controller
public class MController {

	Command command;
	
	// �α��� ȭ��
	@RequestMapping("/login_page")
	public String login_page() {
		return "login_page";
	}
	
	// �α��� ó��
	@RequestMapping(value = "/login", method=RequestMethod.POST)
	public String login(HttpServletRequest request, Model model, HttpSession session) {
		
		model.addAttribute("request", request);
		LoginCheck loginCheck = new LoginCheck();
		loginCheck.check(model, session);
		
		if(session.getAttribute("id") != null) { 
			return "home_page"; 
		} else {
			
			model.addAttribute("warn", "���̵� �Ǵ� ��й�ȣ�� Ʋ�Ƚ��ϴ�");
			model.addAttribute("url", "login_page.jsp");
			return "action/login_fail";
			
		}
	}
	
	// ȸ������ ȭ��
	@RequestMapping("/join_page")
	public String join_page() {
		return "join_page";
	}
	
	// ȸ������ ���
	@RequestMapping(value = "/join", method=RequestMethod.POST)
	public String join(HttpServletRequest request, Model model, HttpSession session) {
		
		JoinCheck joinCheck = new JoinCheck();
		boolean bool = joinCheck.check(request);
		
		if(bool == false) {
			return "action/join_fail";
		} else {
			
			model.addAttribute("request", request);
			command = new MInsertCommand();
			command.execute(model);
			
			return "redirect:home_page";
			
		}
	}
	
	// ���� �˻�
	@RequestMapping("/search_page")
	public String select(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);
		command = new MSearchCommand();
		command.execute(model);
		
		return "search_page";
		
	}
	
	
	// ���� ���� ������ ������
	@RequestMapping("/profile_page")
	public String profile_page(Model model) {
		return "profile_page";
	}
	
	// ȸ������ ���� ������
	@RequestMapping("/setting_page")
	public String setting_page(Model model) {
		return "setting_page";
	}
	
	
	// ȸ��Ż�� �� Ȯ��������
	@RequestMapping("/withdrawal_check")
	public String withdrawal_check(Model model) {
		return "withdrawal_check";
	}
	
	// ȸ��Ż�� ���
	@RequestMapping("/withdrawal")
	public String delete(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);
		command = new MDeleteCommand();
		command.execute(model);
		
		return "redirect:�α��� ��������";
		
	}
	
}
