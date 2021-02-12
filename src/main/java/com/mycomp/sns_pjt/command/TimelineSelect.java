package com.mycomp.sns_pjt.command;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.mycomp.sns_pjt.dao.BDao;
import com.mycomp.sns_pjt.dto.BDto;

@Component
public class TimelineSelect {
	
	// 메인 페이지 타임라인
	public void timeLine(Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpSession session = (HttpSession) map.get("session");
		
		String sid = (String) session.getAttribute("sid");
		
		BDao bDao = new BDao();
		ArrayList<BDto> bDtos = bDao.timelineSelect(sid);
		
		model.addAttribute("boardList", bDtos);
		
	}
	
	// 좋아요 한 페이지 가져오기
	public void likePageTimeLine(Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpSession session = (HttpSession) map.get("session");
		
		String sid = (String) session.getAttribute("sid");
		
		BDao bDao = new BDao();
		ArrayList<BDto> bDtos = bDao.likePageTL(sid);
		
		model.addAttribute("boardList", bDtos);
		
	}

}
