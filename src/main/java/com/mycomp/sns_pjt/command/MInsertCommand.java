package com.mycomp.sns_pjt.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.mycomp.sns_pjt.dao.MDao;

public class MInsertCommand implements Command {

	@Autowired
	MSha256 sha256;
	
	@Autowired
	MDao mDao;
	
	// ȸ������ (ȸ�� ���Կ� �ʿ��� ���� ���� �� Member Insert)
	@Override
	public void execute(Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		int tel1 = Integer.parseInt(request.getParameter("tel1"));
		int tel2 = Integer.parseInt(request.getParameter("tel2"));
		int tel3 = Integer.parseInt(request.getParameter("tel3"));
		
		String encPw = sha256.encrypt(pw);
		
		mDao.mInsert(id, encPw, name, tel1, tel2, tel3);
		
	}

}
