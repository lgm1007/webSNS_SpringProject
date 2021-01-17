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

	@Override
	public void execute(Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		int bd_key = Integer.parseInt(request.getParameter("bdKey"));
		String sid = request.getParameter("memID");
		
		BDao bDao = new BDao();
		CDao cDao = new CDao();
		
		ArrayList<BDto> bDtos = bDao.bSelectAsKey(bd_key);
		ArrayList<CDto> cDtos = cDao.cSelect(bd_key);
		
		BDto bDto = bDtos.get(0);
		model.addAttribute("thisBoard", bDto);
		model.addAttribute("commList", cDtos);

	}

}
