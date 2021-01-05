package com.mycomp.sns_pjt.command;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.mycomp.sns_pjt.dao.BDao;
import com.mycomp.sns_pjt.dao.MDao;
import com.mycomp.sns_pjt.dto.BDto;
import com.mycomp.sns_pjt.dto.MDto;

public class LoginCheck {

	public void check(Model model, HttpSession session) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		MDao mDao = new MDao();
		ArrayList<MDto> mDtos = mDao.mSelect(id);
		
		// mDtos.size ���� 0 �����̸� �������� �ʴ� ���̵�, equals�� false�̸� ��й�ȣ�� Ʋ����
		if(mDtos.size() > 0) {
			if(mDtos.get(0).getPw().equals(pw)) {
				session.setAttribute("sid", id);
				session.setAttribute("sname", mDtos.get(0).getName());
				
				BDao bDao = new BDao();
				ArrayList<BDto> bDtos = bDao.bSelect(id);
				
				model.addAttribute("boardList", bDtos);
			}
		}
		
	}
	
}
