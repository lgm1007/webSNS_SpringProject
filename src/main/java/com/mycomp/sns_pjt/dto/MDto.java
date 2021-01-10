package com.mycomp.sns_pjt.dto;

public class MDto {

	private String id;
	private String pw;
	private String name;
	private int tel1;
	private int tel2;
	private int tel3;
	
	public MDto(String id, String pw, String name, int tel1, int tel2, int tel3) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.tel1 = tel1;
		this.tel2 = tel2;
		this.tel3 = tel3;
	}
	
	public MDto(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTel1() {
		return tel1;
	}

	public void setTel1(int tel1) {
		this.tel1 = tel1;
	}

	public int getTel2() {
		return tel2;
	}

	public void setTel2(int tel2) {
		this.tel2 = tel2;
	}

	public int getTel3() {
		return tel3;
	}

	public void setTel3(int tel3) {
		this.tel3 = tel3;
	}
	
	
}
