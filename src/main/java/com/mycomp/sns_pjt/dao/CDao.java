package com.mycomp.sns_pjt.dao;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mycomp.sns_pjt.dto.BDto;
import com.mycomp.sns_pjt.dto.CDto;

@Repository
public class CDao {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	// �ش� bdKey�� �ۿ� �ۼ��� ��� ��������
	public List<CDto> cSelect(int bdKey) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			List<CDto> clist = sqlSession.selectList("commentDB.selectComment", bdKey);
			return clist;
			
		} finally {
			sqlSession.close();
		}
	}
	
	// �ش� bdKey�� �ۿ� ��� �ۼ�
	public void cInsert(int bdKey, String memid, String comment) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			CDto cDto = new CDto();
			cDto.setBd_key(bdKey);
			cDto.setMem_id(memid);
			cDto.setComment_cont(comment);
			
			sqlSession.insert("commentDB.insertComment", cDto);
			
		} finally {
			sqlSession.close();
		}
	}
	
	// �ش� bdKey�� �ش� commKey�� ��� ����
	public void cDelete(int bdKey, int commKey, String memid) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			CDto cDto = new CDto();
			cDto.setBd_key(bdKey);
			cDto.setMem_id(memid);
			cDto.setComment_key(commKey);
			
			sqlSession.insert("commentDB.deleteComment", cDto);
			
		} finally {
			sqlSession.close();
		}
	}
	
}
