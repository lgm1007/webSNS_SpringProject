package com.mycomp.sns_pjt.command;

import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.ui.Model;
import com.mycomp.sns_pjt.dao.BDao;
import com.mycomp.sns_pjt.dao.FDao;
import com.mycomp.sns_pjt.dao.IDao;
import com.mycomp.sns_pjt.dto.BDto;
import com.mycomp.sns_pjt.dto.FDto;
import com.mycomp.sns_pjt.dto.IDto;

public class BSelectCommand implements Command {

	@Override
	public void execute(Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpSession session = (HttpSession) map.get("session");
		
		String sid = (String) session.getAttribute("sid");

		BDao bDao = new BDao();
		FDao fDao = new FDao();
		ArrayList<BDto> bDtos = bDao.bSelect(sid);
		ArrayList<FDto> followDtos = fDao.selectFollow(sid);
		ArrayList<FDto> followerDtos = fDao.selectFollower(sid);
		
		model.addAttribute("boardList", bDtos);
		model.addAttribute("followCount", followDtos.size());
		model.addAttribute("followerCount", followerDtos.size());
		
	}

}
