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
	
	// �ش� ������ �ۼ��� �� ��� ��ȯ (
	public List<BDto> bSelect(String id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			List<BDto> blist = sqlSession.selectList("boardDB.selectBoard2ID", id);
			return blist;
			
		} finally {
			sqlSession.close();
		}
	}
	
	// bdKey�� ���� �� ��� Select �� ��ȯ
	public List<BDto> bSelectAsKey(int bdKey) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			List<BDto> blist = sqlSession.selectList("boardDB.selectBoard2Key", bdKey);
			return blist;
			
		} finally {
			sqlSession.close();
		}
	}
	
	// ����ȭ�� Ÿ�Ӷ��� ��� (�α����� ������ �ȷο��� ������ �۸�� ��ȯ)
	public List<BDto> timelineSelect(String id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			List<BDto> blist = sqlSession.selectList("boardDB.selectTimeline", id);
			return blist;
			
		} finally {
			sqlSession.close();
		}
	}
	
	// �ش� ������ ���ƿ並 ���� �� ��� ��ȯ
	public List<BDto> likePageTL(String id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			List<BDto> blist = sqlSession.selectList("boardDB.selectLikeBoard", id);
			return blist;
			
		} finally {
			sqlSession.close();
		}
	}
	
	// �� �ۼ��ϱ� (ID�� �� ���븸 �ʿ�, bdKey�� auto_increase)
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
	
	// �ش� ������ �ش� bdKey �� ����
	public void bDelete (int bdkey) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			sqlSession.delete("boardDB.deleteBoard", bdkey);
			
		} finally {
			sqlSession.close();
		}
	}
	
	// �ش� bdKey�� �� ���� ����
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
