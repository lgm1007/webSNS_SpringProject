package com.mycomp.sns_pjt.command;

import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;

import com.mycomp.sns_pjt.dao.BDao;
import com.mycomp.sns_pjt.dao.FDao;
import com.mycomp.sns_pjt.dao.MDao;
import com.mycomp.sns_pjt.dto.BDto;
import com.mycomp.sns_pjt.dto.FDto;
import com.mycomp.sns_pjt.dto.MDto;

public class OtherProfileCommand implements Command {

	@Override
	public void execute(Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String uid = request.getParameter("otherUserID");
		
		MDao mDao = new MDao();
		BDao bDao = new BDao();
		FDao fDao = new FDao();
		
		ArrayList<MDto> mdtos = mDao.mSelect(uid);
		ArrayList<BDto> bdtos = bDao.bSelect(uid);
		ArrayList<FDto> follows = fDao.selectFollow(uid);
		ArrayList<FDto> followers = fDao.selectFollower(uid);
		
		String uname = mdtos.get(0).getName();
		
		model.addAttribute("userID", uid);
		model.addAttribute("userName", uname);
		model.addAttribute("boardList", bdtos);
		model.addAttribute("followCount", follows.size());
		model.addAttribute("followerCount", followers.size());

	}

}
