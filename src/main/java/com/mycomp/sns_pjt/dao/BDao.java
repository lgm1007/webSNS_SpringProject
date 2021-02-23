package com.mycomp.sns_pjt.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mycomp.sns_pjt.dto.BDto;

@Repository
public class BDao {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	// 해당 유저가 작성한 글 목록 반환 (
	public List<BDto> bSelect(String id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			List<BDto> blist = sqlSession.selectList("boardDB.selectBoard2ID", id);
			return blist;
			
		} finally {
			sqlSession.close();
		}
	}
	
	// bdKey를 통해 글 목록 Select 후 반환
	public List<BDto> bSelectAsKey(int bdKey) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			List<BDto> blist = sqlSession.selectList("boardDB.selectBoard2Key", bdKey);
			return blist;
			
		} finally {
			sqlSession.close();
		}
	}
	
	// 메인화면 타임라인 목록 (로그인한 유저가 팔로우한 유저의 글목록 반환)
	public List<BDto> timelineSelect(String id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			List<BDto> blist = sqlSession.selectList("boardDB.selectTimeline", id);
			return blist;
			
		} finally {
			sqlSession.close();
		}
	}
	
	// 해당 유저가 좋아요를 누른 글 목록 반환
	public List<BDto> likePageTL(String id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			List<BDto> blist = sqlSession.selectList("boardDB.selectLikeBoard", id);
			return blist;
			
		} finally {
			sqlSession.close();
		}
	}
	
	// 글 작성하기 (ID와 글 내용만 필요, bdKey는 auto_increase)
	public void bInsert(String memid, String content) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			BDto bDto = new BDto();
			bDto.setMem_id(memid);
			bDto.setBd_cont(content);
			sqlSession.insert("boardDB.insertBoard", bDto);
			
		} finally {
			sqlSession.close();
		}
	}
	
	// 해당 유저의 해당 bdKey 글 삭제
	public void bDelete (int bdkey) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			sqlSession.delete("boardDB.deleteBoard", bdkey);
			
		} finally {
			sqlSession.close();
		}
	}
	
	// 해당 bdKey의 글 내용 수정
	public void bUpdate (int bdkey, String content) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			BDto bDto = new BDto();
			bDto.setBd_key(bdkey);
			bDto.setBd_cont(content);
			sqlSession.insert("boardDB.updateBoard", bDto);
			
		} finally {
			sqlSession.close();
		}
	}
	
}
