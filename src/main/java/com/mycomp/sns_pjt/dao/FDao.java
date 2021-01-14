package com.mycomp.sns_pjt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mycomp.sns_pjt.dto.FDto;

public class FDao {
	
DataSource dataSource;
	
	public FDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/mysql");
			
		}catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<FDto> selectFollow(String userid) {
		ArrayList<FDto> fDtos = new ArrayList<FDto>();
		Connection conn = null;
		PreparedStatement ptst = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			String selQuery = "SELECT * FROM bfollow WHERE follower = '"+userid+"'";
			ptst = conn.prepareStatement(selQuery);
			rs = ptst.executeQuery();
			
			while(rs.next()) {
				String follow = rs.getString("follow");
				String follower = rs.getString("follower");
				
				FDto fdto = new FDto(follow, follower);
				fDtos.add(fdto);
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
		return fDtos;
	}
	
	public ArrayList<FDto> selectFollower(String userid) {
		ArrayList<FDto> fDtos = new ArrayList<FDto>();
		Connection conn = null;
		PreparedStatement ptst = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			String selQuery = "SELECT * FROM bfollow WHERE follow = '"+userid+"'";
			ptst = conn.prepareStatement(selQuery);
			rs = ptst.executeQuery();
			
			while(rs.next()) {
				String follow = rs.getString("follow");
				String follower = rs.getString("follower");
				
				FDto fdto = new FDto(follow, follower);
				fDtos.add(fdto);
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
		return fDtos;
	}
	
	public boolean checkIFollowU(String userid, String otherid) {
		boolean b = false;
		Connection conn = null;
		PreparedStatement ptst = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			String selQuery = "SELECT * FROM bfollow WHERE follower = '"+userid+"' AND follow = '"+otherid+"'";
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
	
	public boolean checkUFollowMe(String userid, String otherid) {
		boolean b = false;
		Connection conn = null;
		PreparedStatement ptst = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			String selQuery = "SELECT * FROM bfollow WHERE follower = '"+otherid+"' AND follow = '"+userid+"'";
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
	
	public int fInsert(String follow, String follower) {
		Connection conn = null;
		PreparedStatement ptst = null;
		int i = 0;
		
		try {
			conn = dataSource.getConnection();
			String insQuery = "INSERT INTO bfollow VALUES (?, ?)";
			ptst = conn.prepareStatement(insQuery);
			ptst.setString(1, follow);
			ptst.setString(2, follower);
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
	
	// non-follow
	public int followDelete (String follow, String follower) {
		Connection conn = null;
		PreparedStatement ptst = null;
		int i = 0;
		
		try {
			conn = dataSource.getConnection();
			String deleteQurey = "DELETE FROM bfollow WHERE follow = ? AND follower = ?";
			ptst = conn.prepareStatement(deleteQurey);
			ptst.setString(1, follow);
			ptst.setString(2, follower);
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
