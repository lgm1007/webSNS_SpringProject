package com.mycomp.sns_pjt.command;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import com.mycomp.sns_pjt.dao.MDao;
import com.mycomp.sns_pjt.dto.MDto;

public class MSearchCommand implements Command {

	// 검색한 단어가 포함된 아이디를 가진 사용자 Select
	@Override
	public void execute(Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String word = request.getParameter("srch");
		
		MDao mDao = new MDao();
		List<MDto> mDtos = mDao.mSearch(word);

		model.addAttribute("memberSearch", mDtos);
		
	}

}
