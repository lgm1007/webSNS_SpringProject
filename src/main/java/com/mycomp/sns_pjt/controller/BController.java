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
	
	//메인페이지 이동
	@RequestMapping(value = "/home_page")
	public String home_page(Model model, HttpSession session) {
		
		if(session.getAttribute("sid") != null) {
			model.addAttribute("session", session);
			// 타임라인 보여주기 위한 정보 가져오기
			bCommand.timeLine(model);
		
			return "home_page";
			
		} else {
			
			model.addAttribute("warn", "유효한 세션이 없습니다");
			model.addAttribute("url", "login_page");
			return "action/no_session";
		}
		
	}
	
	// 좋아요 한 게시물 보는 페이지
	@RequestMapping(value = "/like_page")
	public String like_page(Model model, HttpSession session) {
		
		if(session.getAttribute("sid") != null) {
			model.addAttribute("session", session);
			bCommand.likePageTimeLine(model);
			
			return "like_page";
		} else {
			
			model.addAttribute("warn", "유효한 세션이 없습니다");
			model.addAttribute("url", "login_page");
			return "action/no_session";
		}
	}
	
	//포스트 작성 페이지 이동
	@RequestMapping(value = "/post_page")
	public String post_page(Model model, HttpSession session) {
		if(session.getAttribute("sid") != null) {
			return "post_page";
		} else {

			model.addAttribute("warn", "유효한 세션이 없습니다");
			model.addAttribute("url", "login_page");
			return "action/no_session";
		}
	}
	
	//포스트 작성 수행
	@RequestMapping(value = "/post_writing", method=RequestMethod.POST)
	public String post_writing(MultipartHttpServletRequest mtpRequest , Model model, HttpSession session) {
		
		model.addAttribute("mtprequest", mtpRequest);
		writeClass.write(model, session);
		
		if(session.getAttribute("sid") != null) {
			model.addAttribute("session", session);

			// 게시물 작성 후 프로필 페이지로 이동하기
			mCommand.getMyProfile(model);
			return "profile_page";
			
		} else {
			
			model.addAttribute("warn", "유효한 세션이 없습니다");
			model.addAttribute("url", "login_page");
			return "action/no_session";
		}
	}
	
	// 작성된 포스트 이동
	@RequestMapping(value="/posted_board")
	public String posted_board(HttpServletRequest request, Model model, HttpSession session) {
		
		if(session.getAttribute("sid") != null) {
			model.addAttribute("request", request);
			bCommand.boardView(model);
			
			return "posted_board";
			
		} else {
			model.addAttribute("warn", "유효한 세션이 없습니다");
			model.addAttribute("url", "login_page");
			return "action/no_session";
		}
		
	}
	
	// 내 게시글 편집 페이지
	@RequestMapping("/post_edit")
	public String post_edit_page(HttpServletRequest request, Model model, HttpSession session) {
		
		if(session.getAttribute("sid") != null) {
			int bdKey = Integer.parseInt(request.getParameter("bdEditKey"));
			model.addAttribute("bdEditKey", bdKey);
			
			return "edit_page";
			
		} else {
			model.addAttribute("warn", "유효한 세션이 없습니다");
			model.addAttribute("url", "login_page");
			return "action/no_session";
		}
	}
	
	// 게시글 편집하기
	@RequestMapping(value="/edit_action", method=RequestMethod.POST)
	public String edit_post(HttpServletRequest request, Model model, HttpSession session) {
		
		model.addAttribute("request", request);
		
		bCommand.boardUpdate(model);
		model.addAttribute("ok", "게시글을 수정했습니다");
		model.addAttribute("url", "profile_page");
		return "action/edit_post_ok";
	}
	
	// 게시글 삭제하기
	@RequestMapping(value="/post_delete", method=RequestMethod.POST)
	public String post_delete(HttpServletRequest request, Model model, HttpSession session) {
		
		model.addAttribute("request", request);
		model.addAttribute("session", session);
		
		bCommand.boardDelete(model);
		model.addAttribute("ok", "게시글 삭제가 완료되었습니다");
		model.addAttribute("url", "profile_page");
		return "action/delete_post_ok";
		
		
	}
	
	// 댓글 작성하기
	@RequestMapping(value = "/write_comment", method=RequestMethod.POST)
	public String write_comment(HttpServletRequest request, Model model, HttpSession session) {
		
		model.addAttribute("request", request);
		model.addAttribute("session", session);
		
		if(session.getAttribute("sid") != null) {
			bCommand.commentWrite(model);
			model.addAttribute("ok", "댓글을 작성했습니다");
			return "action/comment_write";
			
		} else {
			model.addAttribute("warn", "유효한 세션이 없습니다");
			model.addAttribute("url", "login_page");
			return "action/no_session";
		}
	}
	
	// 댓글 삭제하기 체크
	@RequestMapping(value = "/delete_comment", method=RequestMethod.POST)
	public String delete_comment_check(HttpServletRequest request, Model model, HttpSession session) {
		
		model.addAttribute("request", request);
		model.addAttribute("session", session);
		
		if(session.getAttribute("sid") != null) {
			bCommand.commentDelete(model);
			model.addAttribute("ok", "댓글을 삭제했습니다");
			return "action/comment_delete";
			
		} else {
			model.addAttribute("warn", "유효한 세션이 없습니다");
			model.addAttribute("url", "login_page");
			return "action/no_session";
		}
		
	}
	
}
