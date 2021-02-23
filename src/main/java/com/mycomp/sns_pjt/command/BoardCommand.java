package com.mycomp.sns_pjt.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.mycomp.sns_pjt.dao.BDao;
import com.mycomp.sns_pjt.dao.CDao;
import com.mycomp.sns_pjt.dto.BDto;
import com.mycomp.sns_pjt.dto.CDto;

@Component
public class BoardCommand {
	
	@Autowired
	BDao bDao;
	
	// �ۼ��� �� ��ȸ (View Board)
	public void boardView(Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		int bd_key = Integer.parseInt(request.getParameter("bdKey"));
		String memID = request.getParameter("memID");
		
		CDao cDao = new CDao();
		
		List<BDto> bDtos = bDao.bSelectAsKey(bd_key);
		ArrayList<CDto> cDtos = cDao.cSelect(bd_key);
		
		BDto bDto = bDtos.get(0);
		model.addAttribute("bdKey", bDto.getBd_key());
		model.addAttribute("memID", memID);
		model.addAttribute("bdCont", bDto.getBd_cont());
		model.addAttribute("commList", cDtos);
	}
	
	// �� ���� (Board Delete)
	public void boardDelete(Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = (HttpSession) map.get("session");
		
		int bdKey = Integer.parseInt(request.getParameter("bdKey"));

		bDao.bDelete(bdKey);
	}
	
	// �� ���� (Board Update)
	public void boardUpdate(Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		int bdKey = Integer.parseInt(request.getParameter("bdEditKey"));
		String content = request.getParameter("content");
		
		bDao.bUpdate(bdKey, content);
	}
	
	// ���� ������ Ÿ�Ӷ��� (Timeline in Home)
	public void timeLine(Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpSession session = (HttpSession) map.get("session");
		
		String sid = (String) session.getAttribute("sid");
		
		List<BDto> bDtos = bDao.timelineSelect(sid);
		
		model.addAttribute("boardList", bDtos);
		
	}
	
	// ���ƿ� �� ������ �������� (Timeline in Like page)
	public void likePageTimeLine(Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpSession session = (HttpSession) map.get("session");
		
		String sid = (String) session.getAttribute("sid");
		
		List<BDto> bDtos = bDao.likePageTL(sid);
		
		model.addAttribute("boardList", bDtos);
		
	}
	
	// ��� �ۼ��ϱ� (����� ���� ���� bdKey, ��� ����, ������ ID ����; Write Comment)
	public void commentWrite(Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = (HttpSession) map.get("session");
		
		int bdKey = Integer.parseInt(request.getParameter("boardKey"));
		String comm_text = request.getParameter("comm_text");
		String sid = (String)session.getAttribute("sid");
		
		CDao cDao = new CDao();
		int reI = cDao.cInsert(bdKey, sid, comm_text);
	}
	
	// �α��� �� ������ ��� ����� (Delete Comment)
	public void commentDelete(Model model) {

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
