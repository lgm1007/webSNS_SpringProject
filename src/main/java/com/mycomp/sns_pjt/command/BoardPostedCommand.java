package com.mycomp.sns_pjt.command;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.mycomp.sns_pjt.dao.BDao;
import com.mycomp.sns_pjt.dao.CDao;
import com.mycomp.sns_pjt.dto.BDto;
import com.mycomp.sns_pjt.dto.CDto;

public class BoardPostedCommand implements Command {

	// 작성된 글 전체보기 (글의 bdKey, 작성자 ID, 글 내용, 해당 bdKey에 적힌 댓글들 전송) 
	@Override
	public void execute(Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		int bd_key = Integer.parseInt(request.getParameter("bdKey"));
		String memID = request.getParameter("memID");
		
		BDao bDao = new BDao();
		CDao cDao = new CDao();
		
		ArrayList<BDto> bDtos = bDao.bSelectAsKey(bd_key);
		ArrayList<CDto> cDtos = cDao.cSelect(bd_key);
		
		BDto bDto = bDtos.get(0);
		model.addAttribute("bdKey", bDto.getBd_key());
		model.addAttribute("memID", memID);
		model.addAttribute("bdCont", bDto.getBd_cont());
		model.addAttribute("commList", cDtos);

	}

}
