package com.mycomp.sns_pjt.dto;

public class IDto {
	int bd_key;
	String fileName;
	
	public IDto(int bd_key, String fileName) {
		this.bd_key = bd_key;
		this.fileName = fileName;
	}

	public int getBd_key() {
		return bd_key;
	}

	public void setBd_key(int bd_key) {
		this.bd_key = bd_key;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
