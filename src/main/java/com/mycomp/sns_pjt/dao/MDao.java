package com.mycomp.sns_pjt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mycomp.sns_pjt.dto.MDto;

public class MDao {
	
	DataSource dataSource;
	
	public MDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/mysql");
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	// 해당 유저에 관한 내용 반환
	public ArrayList<MDto> mSelect(String word) {
		ArrayList<MDto> mDtos = new ArrayList<MDto>();
		Connection conn = null;
		PreparedStatement ptst = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			String query = "SELECT * FROM member WHERE id='"+word+"'";
			ptst = conn.prepareStatement(query);
			rs = ptst.executeQuery();
			
			while(rs.next()) {
				String id = rs.getString("id");
				String pw = rs.getString("pw");
				String name = rs.getString("name");
				int tel1 = rs.getInt("tel1");
				int tel2 = rs.getInt("tel2");
				int tel3 = rs.getInt("tel3");
				
				MDto mDto = new MDto(id, pw, name, tel1, tel2 ,tel3);
				mDtos.add(mDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(ptst != null) ptst.close();
				if(conn != null) conn.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return mDtos;
	}
	// 해당 word가 포함된 아이디를 가진 유저들 검색
	public ArrayList<MDto> mSearch(String word) {
		ArrayList<MDto> mDtos = new ArrayList<MDto>();
		Connection conn = null;
		PreparedStatement ptst = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			String query = "SELECT * FROM member WHERE id like '%"+word+"%'";
			ptst = conn.prepareStatement(query);
			rs = ptst.executeQuery();
			
			while(rs.next()) {
				String id = rs.getString("id");
				String pw = rs.getString("pw");
				String name = rs.getString("name");
				int tel1 = rs.getInt("tel1");
				int tel2 = rs.getInt("tel2");
				int tel3 = rs.getInt("tel3");
				
				MDto mDto = new MDto(id, name);
				mDtos.add(mDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(ptst != null) ptst.close();
				if(conn != null) conn.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return mDtos;
	}
	// 회원 가입 (Insert)
	public void mInsert(String id, String pw, String name, int tel1, int tel2, int tel3) {
		Connection conn = null;
		PreparedStatement ptst = null;
		
		try {
			conn = dataSource.getConnection();
			String insertQuery = "INSERT INTO member VALUES (?, ?, ?, ?, ?, ?)";
			ptst = conn.prepareStatement(insertQuery);
			ptst.setString(1, id);
			ptst.setString(2, pw);
			ptst.setString(3, name);
			ptst.setInt(4, tel1);
			ptst.setInt(5, tel2);
			ptst.setInt(6, tel3);
			int r = ptst.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(ptst != null) ptst.close();
				if(conn != null) conn.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	// 회원 탈퇴 (Delete)
	public void mDelete(String id) {
		Connection conn = null;
		PreparedStatement ptst = null;
		
		try {
			conn = dataSource.getConnection();
			String delQuery = "DELETE FROM member WHERE id=?";
			ptst = conn.prepareStatement(delQuery);
			ptst.setString(1, id);
			int r = ptst.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(ptst != null) ptst.close();
				if(conn != null) conn.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	// 회원 내용 수정 (비밀번호, 이름, 휴대폰번호)
	public void mUpdate(String user_id, String upw, String uname, int utel1, int utel2, int utel3) {
		Connection conn = null;
		PreparedStatement ptst = null;
		
		try {
			conn = dataSource.getConnection();
			String updateQuery = "Update member Set pw=?, name=?, tel1=?, tel2=?, tel3=? Where id='"+user_id+"'";
			ptst = conn.prepareStatement(updateQuery);
			ptst.setString(1, upw);
			ptst.setString(2, uname);
			ptst.setInt(3, utel1);
			ptst.setInt(4, utel2);
			ptst.setInt(5, utel3);
			int r = ptst.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(ptst != null) ptst.close();
				if(conn != null) conn.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
}
