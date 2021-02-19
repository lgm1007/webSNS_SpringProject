package com.mycomp.sns_pjt.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mycomp.sns_pjt.dto.MDto;

@Repository
public class MDao {
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	
	// �ش� ������ ���� ���� ��ȯ, CheckClass���� �ߺ� ���̵� üũ �� ����ϱ⿡ List Ÿ������ ��ȯ
	public List<MDto> mSelect(String word) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			List<MDto> mlist = sqlSession.selectList("memberDB.selectMemberID", word);
			return mlist;
			
		} finally {
			sqlSession.close();
		}
		
	}
	
	// �ش� word�� ���Ե� ���̵� ���� ������ �˻�
	public List<MDto> mSearch(String word) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			List<MDto> mlist = sqlSession.selectList("memberDB.searchMember", word);
			return mlist;
			
		} finally {
			sqlSession.close();
		}
		
	}
	
	// ȸ�� ���� (Insert)
	public void mInsert(String id, String pw, String name, int tel1, int tel2, int tel3) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			MDto mDto = new MDto(id, pw, name, tel1, tel2, tel3);
			sqlSession.insert("memberDB.insertMember", mDto);
			
		} finally {
			sqlSession.close();
		}
	}
	
	// ȸ�� Ż�� (Delete)
	public void mDelete(String id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			sqlSession.delete("memberDB.deleteMember", id);
		} finally {
			sqlSession.close();
		}
	}
	
	// ȸ�� ���� ���� (��й�ȣ, �̸�, �޴�����ȣ)
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
