package com.mycomp.sns_pjt.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.mycomp.sns_pjt.dao.MDao;
import com.mycomp.sns_pjt.dto.MDto;

public class JoinCheck {

	public boolean check(HttpServletRequest request) {
		
		String create_id = request.getParameter("id");
		
		MDao mDao = new MDao();
		ArrayList<MDto> mDtos = mDao.mSelect(create_id);
		// mDtos 사이즈 0 이상이면 같은 아이디가 존재함
		if(mDtos.size() > 0) {
			return false;
		} else {
			return true;
		}
		
	}
	
}
