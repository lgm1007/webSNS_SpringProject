package com.mycomp.sns_pjt.dto;

public class BDto {

	int bd_key;
	String mem_id;
	String bd_cont;
	
	public BDto(int bd_key, String mem_id, String bd_cont) {
		this.bd_key = bd_key;
		this.mem_id = mem_id;
		this.bd_cont = bd_cont;
	}

	public int getBd_key() {
		return bd_key;
	}

	public void setBd_key(int bd_key) {
		this.bd_key = bd_key;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getBd_cont() {
		return bd_cont;
	}

	public void setBd_cont(String bd_cont) {
		this.bd_cont = bd_cont;
	}
	
}