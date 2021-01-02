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
	
}
