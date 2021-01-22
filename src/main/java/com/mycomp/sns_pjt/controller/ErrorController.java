package com.mycomp.sns_pjt.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorController {
	
	@RequestMapping("/throwable")
	public String throwableError() {
		return "error/throwable";
	}
	
	@RequestMapping("/exception")
	public String exceptionError() {
		return "error/exception";
	}
	
	@RequestMapping("/4xx")
	public String pageError4xx() {
		return "error/400";
	}
	
	@RequestMapping("/5xx")
	public String pageError5xx() {
		return "error/500";
	}
	
}
