package com.mycomp.sns_pjt.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.mycomp.sns_pjt.dao.CDao;

public class CDeleteCommand implements Command {

	// 세션 유저가 쓴 댓글 지우기 (한 유저가 여러 댓글을 쓸 수 있으므로 commKey도 쿼리문의 Where문에 추가)
	@Override
	public void execute(Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = (HttpSession) map.get("session");

		int bdKey = Integer.parseInt(request.getParameter("boardKey"));
		int commKey = Integer.parseInt(request.getParameter("commentKey"));
		String sid = (String)session.getAttribute("sid");
		
		CDao cDao = new CDao();
		int reI = cDao.cDelete(bdKey, commKey, sid);
		
	}

}
