package com.mycomp.sns_pjt.dto;

public class LDto {

	private int bd_key;
	private String mem_id;
	private int lk_state;
	
	public LDto(int bd_key, String mem_id, int lk_state) {
		this.bd_key = bd_key;
		this.mem_id = mem_id;
		this.lk_state = lk_state;
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

	public int getLk_state() {
		return lk_state;
	}

	public void setLk_state(int lk_state) {
		this.lk_state = lk_state;
	}
	
}
