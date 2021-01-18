package com.mycomp.sns_pjt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mycomp.sns_pjt.dto.CDto;

public class CDao {

	DataSource dataSource;
	
	public CDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/mysql");
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<CDto> cSelect(int bdKey) {
		ArrayList<CDto> cDtos = new ArrayList<CDto>();
		Connection conn = null;
		PreparedStatement ptst = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			String selectQuery = "SELECT * FROM comm WHERE bd_key='"+bdKey+"'";
			ptst = conn.prepareStatement(selectQuery);
			rs = ptst.executeQuery();
			
			while(rs.next()) {
				int commentKey = rs.getInt("comment_key");
				int bKey = rs.getInt("bd_key");
				String memID = rs.getString("mem_id");
				String comment = rs.getString("comment_cont");
				
				CDto cDto = new CDto(commentKey, bKey, memID, comment);
				cDtos.add(cDto);
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
		return cDtos;
	}
	
	public int cInsert(int bdKey, String memid, String comment) {
		Connection conn = null;
		PreparedStatement ptst = null;
		int i = 0;
		
		try {
			conn = dataSource.getConnection();
			String insertQuery = "INSERT INTO comm(bd_key, mem_id, comment_cont) VALUES (?, ?, ?)";
			ptst = conn.prepareStatement(insertQuery);
			ptst.setInt(1, bdKey);
			ptst.setString(2, memid);
			ptst.setString(3, comment);
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
	
	public int cDelete(int bdKey, int commKey, String memid) {
		Connection conn = null;
		PreparedStatement ptst = null;
		int i = 0;
		
		try {
			conn = dataSource.getConnection();
			String deleteQuery = "DELETE FROM comm WHERE bd_key = ? AND comment_key = ? AND mem_id = ?";
			ptst = conn.prepareStatement(deleteQuery);
			ptst.setInt(1, bdKey);
			ptst.setInt(2, commKey);
			ptst.setString(3, memid);
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
