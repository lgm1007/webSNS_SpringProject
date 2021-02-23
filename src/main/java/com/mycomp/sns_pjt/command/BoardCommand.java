package com.mycomp.sns_pjt.command;

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
	
	@Autowired
	CDao cDao;
	
	// 작성된 글 조회 (View Board)
	public void boardView(Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		int bd_key = Integer.parseInt(request.getParameter("bdKey"));
		String memID = request.getParameter("memID");
		
		List<BDto> bDtos = bDao.bSelectAsKey(bd_key);
		List<CDto> cDtos = cDao.cSelect(bd_key);
		
		BDto bDto = bDtos.get(0);
		model.addAttribute("bdKey", bDto.getBd_key());
		model.addAttribute("memID", memID);
		model.addAttribute("bdCont", bDto.getBd_cont());
		model.addAttribute("commList", cDtos);
	}
	
	// 글 삭제 (Board Delete)
	public void boardDelete(Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = (HttpSession) map.get("session");
		
		int bdKey = Integer.parseInt(request.getParameter("bdKey"));

		bDao.bDelete(bdKey);
	}
	
	// 글 수정 (Board Update)
	public void boardUpdate(Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		int bdKey = Integer.parseInt(request.getParameter("bdEditKey"));
		String content = request.getParameter("content");
		
		bDao.bUpdate(bdKey, content);
	}
	
	// 메인 페이지 타임라인 (Timeline in Home)
	public void timeLine(Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpSession session = (HttpSession) map.get("session");
		
		String sid = (String) session.getAttribute("sid");
		
		List<BDto> bDtos = bDao.timelineSelect(sid);
		
		model.addAttribute("boardList", bDtos);
		
	}
	
	// 좋아요 한 페이지 가져오기 (Timeline in Like page)
	public void likePageTimeLine(Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpSession session = (HttpSession) map.get("session");
		
		String sid = (String) session.getAttribute("sid");
		
		List<BDto> bDtos = bDao.likePageTL(sid);
		
		model.addAttribute("boardList", bDtos);
		
	}
	
	// 댓글 작성하기 (댓글을 남길 글의 bdKey, 댓글 내용, 유저의 ID 수신; Write Comment)
	public void commentWrite(Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = (HttpSession) map.get("session");
		
		int bdKey = Integer.parseInt(request.getParameter("boardKey"));
		String comm_text = request.getParameter("comm_text");
		String sid = (String)session.getAttribute("sid");
		
		cDao.cInsert(bdKey, sid, comm_text);
	}
	
	// 로그인 한 유저의 댓글 지우기 (Delete Comment)
	public void commentDelete(Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = (HttpSession) map.get("session");

		int bdKey = Integer.parseInt(request.getParameter("boardKey"));
		int commKey = Integer.parseInt(request.getParameter("commentKey"));
		String sid = (String)session.getAttribute("sid");
		
		cDao.cDelete(bdKey, commKey, sid);
	}

}
