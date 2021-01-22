package com.mycomp.sns_pjt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mycomp.sns_pjt.dto.BDto;
import com.mycomp.sns_pjt.dto.MDto;

public class BDao {

DataSource dataSource;
	
	public BDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/mysql");
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	// 해당 유저가 작성한 글 목록 반환
	public ArrayList<BDto> bSelect(String id) {
		ArrayList<BDto> bDtos = new ArrayList<BDto>();
		Connection conn = null;
		PreparedStatement ptst = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			String selQuery = "SELECT * FROM board WHERE mem_id ='"+id+"'";
			ptst = conn.prepareStatement(selQuery);
			rs = ptst.executeQuery();
			
			while(rs.next()) {
				int bd_key = rs.getInt("bd_key");
				String mem_id = rs.getString("mem_id");
				String bd_cont = rs.getString("bd_cont");
				
				BDto bDto = new BDto(bd_key, mem_id, bd_cont);
				bDtos.add(bDto);
			}
		} catch(Exception e) {
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
		return bDtos;
	}
	// bdKey를 통해 글 목록 Select 후 반환
	public ArrayList<BDto> bSelectAsKey(int bdKey) {
		ArrayList<BDto> bDtos = new ArrayList<BDto>();
		Connection conn = null;
		PreparedStatement ptst = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			String selQuery = "SELECT * FROM board WHERE bd_key ='"+bdKey+"'";
			ptst = conn.prepareStatement(selQuery);
			rs = ptst.executeQuery();
			
			while(rs.next()) {
				int bd_key = rs.getInt("bd_key");
				String mem_id = rs.getString("mem_id");
				String bd_cont = rs.getString("bd_cont");
				
				BDto bDto = new BDto(bd_key, mem_id, bd_cont);
				bDtos.add(bDto);
			}
		} catch(Exception e) {
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
		return bDtos;
	}
	// 메인화면 타임라인 목록 (로그인한 유저가 팔로우한 유저의 글목록 반환)
	public ArrayList<BDto> timelineSelect(String id) {
		ArrayList<BDto> bDtos = new ArrayList<BDto>();
		Connection conn = null;
		PreparedStatement ptst = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			String selQuery = "SELECT * FROM board WHERE mem_id in (SELECT follow FROM bfollow WHERE follower = '"+id+"') ORDER BY bd_key DESC";
			ptst = conn.prepareStatement(selQuery);
			rs = ptst.executeQuery();
			
			while(rs.next()) {
				int bd_key = rs.getInt("bd_key");
				String mem_id = rs.getString("mem_id");
				String bd_cont = rs.getString("bd_cont");
				
				BDto bDto = new BDto(bd_key, mem_id, bd_cont);
				bDtos.add(bDto);
			}
		} catch(Exception e) {
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
		return bDtos;
	}
	// 해당 유저가 좋아요를 누른 글 목록 반환
	public ArrayList<BDto> likePageTL(String id) {
		ArrayList<BDto> bDtos = new ArrayList<BDto>();
		Connection conn = null;
		PreparedStatement ptst = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			String selQuery = "SELECT * FROM board WHERE bd_key in (SELECT bd_key FROM blike WHERE mem_id = '"+id+"') ORDER BY bd_key DESC";
			ptst = conn.prepareStatement(selQuery);
			rs = ptst.executeQuery();
			
			while(rs.next()) {
				int bd_key = rs.getInt("bd_key");
				String mem_id = rs.getString("mem_id");
				String bd_cont = rs.getString("bd_cont");
				
				BDto bDto = new BDto(bd_key, mem_id, bd_cont);
				bDtos.add(bDto);
			}
		} catch(Exception e) {
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
		return bDtos;
	}
	// 글 작성하기 (ID와 글 내용만 필요, bdKey는 auto_increase)
	public void bInsert(String memid, String content) {
		Connection conn = null;
		PreparedStatement ptst = null;
		
		try {
			conn = dataSource.getConnection();
			String insertQuery = "INSERT INTO board(mem_id, bd_cont) VALUES (?, ?)";
			ptst = conn.prepareStatement(insertQuery);
			ptst.setString(1, memid);
			ptst.setString(2, content);
			int r = ptst.executeUpdate();
			
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
	}
	// 해당 유저의 해당 bdKey 글 삭제
	public void bDelete (String memid, int bdkey) {
		Connection conn = null;
		PreparedStatement ptst = null;
		
		try {
			conn = dataSource.getConnection();
			String deleteQurey = "DELETE FROM board WHERE mem_id = ? AND bd_key = ?";
			ptst = conn.prepareStatement(deleteQurey);
			ptst.setString(1, memid);
			ptst.setInt(2, bdkey);
			int r = ptst.executeUpdate();
			
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
	}
	// 해당 bdKey의 글 내용 수정
	public void bUpdate (int bdkey, String content) {
		Connection conn = null;
		PreparedStatement ptst = null;
		
		try {
			conn = dataSource.getConnection();
			String updateQurey = "UPDATE board SET bd_cont = ? WHERE bd_key = ?";
			ptst = conn.prepareStatement(updateQurey);
			ptst.setString(1, content);
			ptst.setInt(2, bdkey);
			int r = ptst.executeUpdate();
			
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
	}
	
}
