package com.mycomp.sns_pjt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycomp.sns_pjt.command.BSelectCommand;
import com.mycomp.sns_pjt.command.Command;

@Controller
public class BController {

	Command command;
	
	//메인화면 이동
	@RequestMapping(value = "/home_page")
	public String home_page(Model model, HttpSession session) {
		
		if(session.getAttribute("sid") != null) {
			model.addAttribute("session", session);
			command = new BSelectCommand();
			command.execute(model);
		
			return "home_page";
			
		} else {
			
			model.addAttribute("warn", "유효한 세션이 없습니다");
			model.addAttribute("url", "login_page");
			return "action/no_session";
		}
		
	}
	
}
