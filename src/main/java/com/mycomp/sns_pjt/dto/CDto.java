package com.mycomp.sns_pjt.dto;

public class CDto {
	
	private int comment_key;
	private int bd_key;
	private String mem_id;
	private String comment_cont;
	
	public CDto(int comment_key ,int bd_key, String mem_id, String comment_cont) {
		this.comment_key = comment_key;
		this.bd_key = bd_key;
		this.mem_id = mem_id;
		this.comment_cont = comment_cont;
	}
	
	public int getComment_key() {
		return comment_key;
	}

	public void setComment_key(int comment_key) {
		this.comment_key = comment_key;
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

	public String getComment_cont() {
		return comment_cont;
	}

	public void setComment_cont(String comment_cont) {
		this.comment_cont = comment_cont;
	}
	
}
