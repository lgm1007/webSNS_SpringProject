package com.mycomp.sns_pjt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycomp.sns_pjt.command.BIWriteClass;
import com.mycomp.sns_pjt.command.Command;
import com.mycomp.sns_pjt.command.MemberCommand;
import com.mycomp.sns_pjt.command.BoardCommand;

@Controller
public class BController {

	Command command;
	
	@Autowired
	MemberCommand mCommand;
	
	@Autowired
	BoardCommand bCommand;
	
	@Autowired
	BIWriteClass writeClass;
	
	//���������� �̵�
	@RequestMapping(value = "/home_page")
	public String home_page(Model model, HttpSession session) {
		
		if(session.getAttribute("sid") != null) {
			model.addAttribute("session", session);
			// Ÿ�Ӷ��� �����ֱ� ���� ���� ��������
			bCommand.timeLine(model);
		
			return "home_page";
			
		} else {
			
			model.addAttribute("warn", "��ȿ�� ������ �����ϴ�");
			model.addAttribute("url", "login_page");
			return "action/no_session";
		}
		
	}
	
	// ���ƿ� �� �Խù� ���� ������
	@RequestMapping(value = "/like_page")
	public String like_page(Model model, HttpSession session) {
		
		if(session.getAttribute("sid") != null) {
			model.addAttribute("session", session);
			bCommand.likePageTimeLine(model);
			
			return "like_page";
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
	
	//����Ʈ �ۼ� ����
	@RequestMapping(value = "/post_writing", method=RequestMethod.POST)
	public String post_writing(MultipartHttpServletRequest mtpRequest , Model model, HttpSession session) {
		
		model.addAttribute("mtprequest", mtpRequest);
		writeClass.write(model, session);
		
		if(session.getAttribute("sid") != null) {
			model.addAttribute("session", session);

			// �Խù� �ۼ� �� ������ �������� �̵��ϱ�
			mCommand.getMyProfile(model);
			return "profile_page";
			
		} else {
			
			model.addAttribute("warn", "��ȿ�� ������ �����ϴ�");
			model.addAttribute("url", "login_page");
			return "action/no_session";
		}
	}
	
	// �ۼ��� ����Ʈ �̵�
	@RequestMapping(value="/posted_board")
	public String posted_board(HttpServletRequest request, Model model, HttpSession session) {
		
		if(session.getAttribute("sid") != null) {
			model.addAttribute("request", request);
			bCommand.boardView(model);
			
			return "posted_board";
			
		} else {
			model.addAttribute("warn", "��ȿ�� ������ �����ϴ�");
			model.addAttribute("url", "login_page");
			return "action/no_session";
		}
		
	}
	
	// �� �Խñ� ���� ������
	@RequestMapping("/post_edit")
	public String post_edit_page(HttpServletRequest request, Model model, HttpSession session) {
		
		if(session.getAttribute("sid") != null) {
			int bdKey = Integer.parseInt(request.getParameter("bdEditKey"));
			model.addAttribute("bdEditKey", bdKey);
			
			return "edit_page";
			
		} else {
			model.addAttribute("warn", "��ȿ�� ������ �����ϴ�");
			model.addAttribute("url", "login_page");
			return "action/no_session";
		}
	}
	
	// �Խñ� �����ϱ�
	@RequestMapping(value="/edit_action", method=RequestMethod.POST)
	public String edit_post(HttpServletRequest request, Model model, HttpSession session) {
		
		model.addAttribute("request", request);
		
		bCommand.boardUpdate(model);
		model.addAttribute("ok", "�Խñ��� �����߽��ϴ�");
		model.addAttribute("url", "profile_page");
		return "action/edit_post_ok";
	}
	
	// �Խñ� �����ϱ�
	@RequestMapping(value="/post_delete", method=RequestMethod.POST)
	public String post_delete(HttpServletRequest request, Model model, HttpSession session) {
		
		model.addAttribute("request", request);
		model.addAttribute("session", session);
		
		bCommand.boardDelete(model);
		model.addAttribute("ok", "�Խñ� ������ �Ϸ�Ǿ����ϴ�");
		model.addAttribute("url", "profile_page");
		return "action/delete_post_ok";
		
		
	}
	
	// ��� �ۼ��ϱ�
	@RequestMapping(value = "/write_comment", method=RequestMethod.POST)
	public String write_comment(HttpServletRequest request, Model model, HttpSession session) {
		
		model.addAttribute("request", request);
		model.addAttribute("session", session);
		
		if(session.getAttribute("sid") != null) {
			bCommand.commentWrite(model);
			model.addAttribute("ok", "����� �ۼ��߽��ϴ�");
			return "action/comment_write";
			
		} else {
			model.addAttribute("warn", "��ȿ�� ������ �����ϴ�");
			model.addAttribute("url", "login_page");
			return "action/no_session";
		}
	}
	
	// ��� �����ϱ� üũ
	@RequestMapping(value = "/delete_comment", method=RequestMethod.POST)
	public String delete_comment_check(HttpServletRequest request, Model model, HttpSession session) {
		
		model.addAttribute("request", request);
		model.addAttribute("session", session);
		
		if(session.getAttribute("sid") != null) {
			bCommand.commentDelete(model);
			model.addAttribute("ok", "����� �����߽��ϴ�");
			return "action/comment_delete";
			
		} else {
			model.addAttribute("warn", "��ȿ�� ������ �����ϴ�");
			model.addAttribute("url", "login_page");
			return "action/no_session";
		}
		
	}
	
}
