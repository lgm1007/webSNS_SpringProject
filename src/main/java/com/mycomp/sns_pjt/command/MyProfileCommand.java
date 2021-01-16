package com.mycomp.sns_pjt.command;

import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.ui.Model;
import com.mycomp.sns_pjt.dao.BDao;
import com.mycomp.sns_pjt.dao.FDao;

import com.mycomp.sns_pjt.dto.BDto;
import com.mycomp.sns_pjt.dto.FDto;


// MyProfile 페이지 이동 시 command
public class MyProfileCommand implements Command {

	@Override
	public void execute(Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpSession session = (HttpSession) map.get("session");
		
		String sid = (String) session.getAttribute("sid");

		BDao bDao = new BDao();
		FDao fDao = new FDao();
		ArrayList<BDto> bDtos = bDao.bSelect(sid);
		ArrayList<FDto> follows = fDao.selectFollow(sid);
		ArrayList<FDto> followers = fDao.selectFollower(sid);
		
		model.addAttribute("boardList", bDtos);
		model.addAttribute("followCount", follows.size());
		model.addAttribute("followerCount", followers.size());
		
	}

}
