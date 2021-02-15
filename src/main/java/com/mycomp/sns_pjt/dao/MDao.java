package com.mycomp.sns_pjt.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mycomp.sns_pjt.dto.MDto;

@Repository
public class MDao {
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	
	// 해당 유저에 관한 내용 반환, CheckClass에서 중복 아이디 체크 시 사용하기에 List 타입으로 반환
	public List<MDto> mSelect(String word) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			List<MDto> mlist = sqlSession.selectList("memberDB.selectMemberID", word);
			return mlist;
			
		} finally {
			sqlSession.close();
		}
	}
	
	// 해당 word가 포함된 아이디를 가진 유저들 검색
	public List<MDto> mSearch(String word) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			List<MDto> mlist = sqlSession.selectList("memberDB.searchMember", word);
			return mlist;
			
		} finally {
			sqlSession.close();
		}
	}
	
	// 회원 가입 (Insert)
	public void mInsert(String id, String pw, String name, int tel1, int tel2, int tel3) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			MDto mDto = new MDto(id, pw, name, tel1, tel2, tel3);
			sqlSession.insert("memberDB.insertMember", mDto);
			
		} finally {
			sqlSession.close();
		}
	}
	
	// 회원 탈퇴 (Delete)
	public void mDelete(String id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			sqlSession.delete("memberDB.deleteMember", id);
		} finally {
			sqlSession.close();
		}
	}
	
	// 회원 내용 수정 (비밀번호, 이름, 휴대폰번호)
	public void mUpdate(String user_id, String upw, String uname, int utel1, int utel2, int utel3) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			MDto mDto = new MDto(user_id, upw, uname, utel1, utel2, utel3);
			sqlSession.update("memberDB.updateMember", mDto);
			
		} finally {
			sqlSession.close();
		}
	}
	
}
