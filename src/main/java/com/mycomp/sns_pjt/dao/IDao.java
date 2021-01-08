package com.mycomp.sns_pjt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mycomp.sns_pjt.dto.IDto;

public class IDao {

DataSource dataSource;

	public IDao() {
		try {
			
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/mysql");
			
		}catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<IDto> iSelect(int bd_key) {
		ArrayList<IDto> iDtos = new ArrayList<IDto>();
		Connection conn = null;
		PreparedStatement ptst = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			String selQuery = "SELECT * FROM images WHERE bd_key='"+bd_key+"'";
			ptst = conn.prepareStatement(selQuery);
			rs = ptst.executeQuery();
			
			while(rs.next()) {
				int bkey = rs.getInt("bd_key");
				String fileName = rs.getString("fileName");
				
				IDto iDto = new IDto(bkey, fileName);
				iDtos.add(iDto);
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
		return iDtos;
	}
	
	public void iInsert(int bdkey, String filename) {
		Connection conn = null;
		PreparedStatement ptst = null;
		
		try {
			conn = dataSource.getConnection();
			String insertQuery = "INSERT INTO images VALUES (?, ?)";
			ptst = conn.prepareStatement(insertQuery);
			ptst.setInt(1, bdkey);
			ptst.setString(2, filename);
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
	
}
