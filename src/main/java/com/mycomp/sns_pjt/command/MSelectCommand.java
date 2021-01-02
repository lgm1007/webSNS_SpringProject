package com.mycomp.sns_pjt.command;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import com.mycomp.sns_pjt.dao.MDao;
import com.mycomp.sns_pjt.dto.MDto;

public class MSelectCommand implements Command {

	@Override
	public void execute(Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String word = request.getParameter("word");
		
		MDao mDao = new MDao();
		ArrayList<MDto> mDtos = mDao.mSearch(word);

		model.addAttribute("memberSearch", mDtos);
		
	}

}
