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
	// 해당 bdKey의 이미지파일 목록 반환
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
	/* 해당 bdKey에 작성될 이미지파일 Insert
	 * 이미지 삭제는 보드 테이블의 bdKey 칼럼을 외래키로 가지고 있는 이미지 테이블에서 외래키 속성을 cascade로 설정하여
	 * 글이 삭제되면서 해당 bdKey 값을 가진 이미지 테이블의 어트리뷰트도 같이 삭제되므로
	 *  따로 별도의 메서드를 만들 필요 없다 */
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
