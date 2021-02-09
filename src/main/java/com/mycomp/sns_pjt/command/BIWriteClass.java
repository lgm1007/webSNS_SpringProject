package com.mycomp.sns_pjt.command;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycomp.sns_pjt.dao.BDao;
import com.mycomp.sns_pjt.dao.IDao;
import com.mycomp.sns_pjt.dto.BDto;

@Component
public class BIWriteClass {

	/* 글쓰기 메서드
	 * 파일, 내용, 아이디를 BDao를 통해 Insert
	 * 이미지파일은 중복 파일명을 고려해 밀리시간을 파일명 앞에 붙여 저장 후 해당 파일명 DB에 저장 */
	public void write(Model model, HttpSession session) {
		
		Map<String, Object> map = model.asMap();
		MultipartHttpServletRequest mtpRequest = (MultipartHttpServletRequest) map.get("mtprequest");
		
		List<MultipartFile> fileList = mtpRequest.getFiles("upladimg");
		String content = mtpRequest.getParameter("content");
		String userid = (String) session.getAttribute("sid");
		
		IDao iDao = new IDao();
		BDao bDao = new BDao();
		bDao.bInsert(userid, content);
		
		ArrayList<BDto> bDtos = bDao.bSelect(userid);
		int bd_key = 0;
		for(int i = 0; i < bDtos.size(); i++) {
			if(bDtos.get(i).getBd_cont().equals(content))
				bd_key = bDtos.get(i).getBd_key();
		}
		
		String path = "Q:\\uploadimg\\";
		
		for (MultipartFile mf : fileList) {
			
			String originFilename = mf.getOriginalFilename();
			String fileName = System.currentTimeMillis() + originFilename;
			iDao.iInsert(bd_key, fileName);
			
			String saveFile = path + fileName;
			
			try {
				mf.transferTo(new File(saveFile));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}
	
}
