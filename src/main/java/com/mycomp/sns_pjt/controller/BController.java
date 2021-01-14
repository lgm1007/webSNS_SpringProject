package com.mycomp.sns_pjt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycomp.sns_pjt.command.BIWriteClass;
import com.mycomp.sns_pjt.command.BProfileCommand;
import com.mycomp.sns_pjt.command.Command;
import com.mycomp.sns_pjt.command.TimelineSelect;

@Controller
public class BController {

	Command command;
	
	//���������� �̵�
	@RequestMapping(value = "/home_page")
	public String home_page(Model model, HttpSession session) {
		
		if(session.getAttribute("sid") != null) {
			model.addAttribute("session", session);
			TimelineSelect timelineSelect = new TimelineSelect();
			timelineSelect.timeLine(model);
		
			return "home_page";
			
		} else {
			
			model.addAttribute("warn", "��ȿ�� ������ �����ϴ�");
			model.addAttribute("url", "login_page");
			return "action/no_session";
		}
		
	}
	
	//����Ʈ �ۼ� ������ �̵�
	@RequestMapping(value = "/post_page")
	public String post_page(Model model, HttpSession session) {
		if(session.getAttribute("sid") != null) {
			return "post_page";
		} else {

			model.addAttribute("warn", "��ȿ�� ������ �����ϴ�");
			model.addAttribute("url", "login_page");
			return "action/no_session";
		}
	}
	
	//����Ʈ �ۼ� ���
	@RequestMapping(value = "/post_writing", method=RequestMethod.POST)
	public String post_writing(MultipartHttpServletRequest mtpRequest , Model model, HttpSession session) {
		
		model.addAttribute("mtprequest", mtpRequest);
		BIWriteClass writeClass = new BIWriteClass();
		writeClass.write(model, session);
		
		if(session.getAttribute("sid") != null) {
			model.addAttribute("session", session);
			TimelineSelect timelineSelect = new TimelineSelect();
			timelineSelect.timeLine(model);
		
			return "home_page";
			
		} else {
			
			model.addAttribute("warn", "��ȿ�� ������ �����ϴ�");
			model.addAttribute("url", "login_page");
			return "action/no_session";
		}
		
	}
	
}
