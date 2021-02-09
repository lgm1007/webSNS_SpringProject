package com.mycomp.sns_pjt.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycomp.sns_pjt.command.MyProfileCommand;
import com.mycomp.sns_pjt.command.OtherProfileCommand;
import com.mycomp.sns_pjt.command.Command;
import com.mycomp.sns_pjt.command.MCheckClass;
import com.mycomp.sns_pjt.command.MDeleteCommand;
import com.mycomp.sns_pjt.command.MInsertCommand;
import com.mycomp.sns_pjt.command.MSearchCommand;
import com.mycomp.sns_pjt.command.MUpdateCommand;
import com.mycomp.sns_pjt.command.TimelineSelect;

@Controller
public class MController {

	Command command;
	
	@Autowired
	MCheckClass mCheck;
	
	// �α��� ȭ��
	@RequestMapping("/login_page")
	public String login_page(Model model, HttpSession session) {
		
		if(session.getAttribute("sid") == null) {
			return "login_page";
		} else {
			model.addAttribute("warn", "���� �α��� ���Դϴ�");
			model.addAttribute("url", "home_page");
			return "action/session_exist";
		}
	}
	
	// �α��� ó��
	@RequestMapping(value = "/login", method=RequestMethod.POST)
	public String login(HttpServletRequest request, Model model, HttpSession session) {
		
		model.addAttribute("request", request);
		mCheck.loginCheck(model, session);
		
		if(session.getAttribute("sid") != null) { 
			return "home_page"; 
		} else {
			
			model.addAttribute("warn", "���̵� �Ǵ� ��й�ȣ�� Ʋ�Ƚ��ϴ�");
			model.addAttribute("url", "login_page");
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
		
		boolean bool = mCheck.joinCheck(request);
		
		if(bool == false) {
			model.addAttribute("warn", "���� ���̵� �����մϴ�");
			model.addAttribute("url", "join_page");
			return "action/join_fail";
		} else {
			
			model.addAttribute("request", request);
			command = new MInsertCommand();
			command.execute(model);
			
			session.setAttribute("sid", request.getParameter("id"));
			session.setAttribute("sname", request.getParameter("name"));
			
			model.addAttribute("session", session);
			TimelineSelect timelineSelect = new TimelineSelect();
			timelineSelect.timeLine(model);
			
			return "home_page";
			
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
	
	// �Խñ� �ۼ��̰� ����(����)���� Ȯ��
	@RequestMapping(value = "/areUSession", method=RequestMethod.POST)
	public String checkUSession(HttpServletRequest request, Model model, HttpSession session) {
		
		String memID = request.getParameter("userId");
		
		boolean bool = mCheck.areUSession(memID, session);
		
		if(bool == true) {
			return "profile_page";
		} else {
			model.addAttribute("request", request);
			command = new OtherProfileCommand();
			command.execute(model);
			
			return "others_page";
		}
		
	}
	
	// ���� ���� ������ ������
	@RequestMapping("/profile_page")
	public String profile_page(Model model, HttpSession session) {
		
		if(session.getAttribute("sid") != null) {
			model.addAttribute("session", session);
			command = new MyProfileCommand();
			command.execute(model);
			return "profile_page";
			
		} else {
			model.addAttribute("warn", "��ȿ�� ������ �����ϴ�");
			model.addAttribute("url", "login_page");
			return "action/no_session";
		}
	}
	
	// ȸ������ ���� ������
	@RequestMapping("/setting_page")
	public String setting_page(Model model) {
		return "setting_page";
	}
	
	@RequestMapping(value = "/update_member", method=RequestMethod.POST)
	public String update_member(HttpServletRequest request, Model model, HttpSession session) {
		
		model.addAttribute("request", request);
		model.addAttribute("session", session);
		command = new MUpdateCommand();
		command.execute(model);
		
		Map<String, Object> map = model.asMap();
		String updated_name = (String) map.get("update_name");
		session.setAttribute("sname", updated_name);
		
		return "profile_page";
		
	}
	
	// ȸ��Ż�� �� Ȯ��������
	@RequestMapping("/withdrawal_check")
	public String withdrawal_check(Model model) {
		return "withdrawal_check";
	}
	
	// ȸ��Ż�� ���
	@RequestMapping(value = "/withdrawal", method=RequestMethod.POST)
	public String delete(HttpServletRequest request, Model model, HttpSession session) {
		
		model.addAttribute("request", request);
		boolean b = mCheck.withdrawalCheck(model, session);
		
		if(b) {
			model.addAttribute("session", session);
			command = new MDeleteCommand();
			command.execute(model);
			
			session.invalidate();
			return "login_page";
		} else {
			model.addAttribute("mes", "��й�ȣ�� �ٸ��ϴ�");
			model.addAttribute("goback", "withdrawal_check");
			return "action/withdrawal_fail";
		}
	}
	
	// �α׾ƿ� �� Ȯ��
	@RequestMapping("/logout")
	public String logoutCheck(Model model) {
		
		model.addAttribute("warn", "�α׾ƿ� �ϰڽ��ϱ�?");
		model.addAttribute("urlTrue", "logout_execute");
		model.addAttribute("urlFalse", "profile_page");
		return "action/logout_check";
	}
	
	// �α׾ƿ� ����
	@RequestMapping("/logout_execute")
	public String logout(Model model, HttpSession session) {
		session.invalidate();
		return "login_page";
	}
	
	// �ٸ� ���� ������������ �̵�
	@RequestMapping(value="/others_page", method=RequestMethod.POST)
	public String othersPage(HttpServletRequest request, Model model, HttpSession session) {
		
		if(session.getAttribute("sid") != null) {
			model.addAttribute("request", request);
			command = new OtherProfileCommand();
			command.execute(model);
			
			return "others_page";
			
		} else {
			model.addAttribute("warn", "��ȿ�� ������ �����ϴ�");
			model.addAttribute("url", "login_page");
			return "action/no_session";
		}
	}
	
}
