package com.mycomp.sns_pjt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mycomp.sns_pjt.dto.LDto;

public class LDao {

	DataSource dataSource;
	
	public LDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/mysql");
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	// 해당 유저가 좋아요를 누른 글의 목록 반환
	public ArrayList<LDto> likeBoardSelect(String userid) {
		ArrayList<LDto> lDtos = new ArrayList<LDto>();
		Connection conn = null;
		PreparedStatement ptst = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			String selQuery = "SELECT bd_key FROM blike WHERE lk_state = 1 AND mem_id = '"+userid+"'";
			ptst = conn.prepareStatement(selQuery);
			rs = ptst.executeQuery();
			
			while(rs.next()) {
				int bdKey = rs.getInt("bd_key");
				
				LDto ldto = new LDto(bdKey, userid, 1);
				lDtos.add(ldto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(ptst != null) ptst.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return lDtos;
	}
	// 해당 유저가 해당 bdKey의 글에 좋아요를 눌렀는지 확인
	public boolean CheckThisBoardILike(String userid, int bdKey) {
		boolean b = false;
		Connection conn = null;
		PreparedStatement ptst = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			String selQuery = "SELECT * FROM blike WHERE bd_key = '"+bdKey+"' AND mem_id = '"+userid+"'";
			ptst = conn.prepareStatement(selQuery);
			rs = ptst.executeQuery();
			
			b = rs.next();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(ptst != null) ptst.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return b;
	}
	// 해당 bdKey의 글에 좋아요를 한 유저의 수 반환
	public int countLikeSelect(int bdKey) {
		int count = 0;
		Connection conn = null;
		PreparedStatement ptst = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			String selQuery = "SELECT count(*) as 'lc' FROM blike WHERE lk_state = 1 AND bd_key = '"+bdKey+"'";
			ptst = conn.prepareStatement(selQuery);
			rs = ptst.executeQuery();
			
			while(rs.next()) {
				count = rs.getInt("lc");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(ptst != null) ptst.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return count;
	}
	// 좋아요 (Insert)
	public int lInsert(int bdKey, String userid) {
		Connection conn = null;
		PreparedStatement ptst = null;
		int i = 0;
		
		try {
			conn = dataSource.getConnection();
			String insertQuery = "INSERT INTO blike(bd_key ,mem_id, lk_state) VALUES (?, ?, 1)";
			ptst = conn.prepareStatement(insertQuery);
			ptst.setInt(1, bdKey);
			ptst.setString(2, userid);
			i = ptst.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(ptst != null) ptst.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return i;
	}
	// 좋아요 취소 (Delete)
	public int lDelete (int bdKey, String userid) {
		Connection conn = null;
		PreparedStatement ptst = null;
		int i = 0;
		
		try {
			conn = dataSource.getConnection();
			String deleteQurey = "DELETE FROM blike WHERE bd_key = ? AND mem_id = ?";
			ptst = conn.prepareStatement(deleteQurey);
			ptst.setInt(1, bdKey);
			ptst.setString(2, userid);
			i = ptst.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(ptst != null) ptst.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return i;
	}
	
}
