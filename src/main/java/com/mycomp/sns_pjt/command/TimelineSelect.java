package com.mycomp.sns_pjt.command;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.mycomp.sns_pjt.dao.BDao;
import com.mycomp.sns_pjt.dto.BDto;

public class TimelineSelect {
	
	public void timeLine(Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpSession session = (HttpSession) map.get("session");
		
		String sid = (String) session.getAttribute("sid");
		
		BDao bDao = new BDao();
		
		ArrayList<BDto> bDtos = bDao.timelineSelect(sid);
		
		model.addAttribute("boardList", bDtos);
		
	}

}
