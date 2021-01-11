package com.mycomp.sns_pjt.dto;

public class FDto {
	
	private String follow;
	private String follower;
	
	public FDto(String follow, String follower) {
		this.follow = follow;
		this.follower = follower;
	}

	public String getFollow() {
		return follow;
	}

	public void setFollow(String follow) {
		this.follow = follow;
	}

	public String getFollower() {
		return follower;
	}

	public void setFollower(String follower) {
		this.follower = follower;
	}
	

}
