package com.mycomp.sns_pjt.command;

import java.security.MessageDigest;
import org.springframework.stereotype.Component;

// SHA-256 암호화 클래스
@Component
public class MSha256 {
	public static String encrypt(String passward) {
		try {
			
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(passward.getBytes());
			byte byteData[] = md.digest();
			StringBuffer buffer = new StringBuffer();
			
			// 암호화 수행
			for(int i = 0; i < byteData.length; i++) {
				buffer.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}
			StringBuffer hexString = new StringBuffer();
			for(int i = 0; i < byteData.length; i++) {
				String hex = Integer.toHexString(0xff & byteData[i]);
				if(hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}
			return hexString.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}
