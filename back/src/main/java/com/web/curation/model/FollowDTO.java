package com.web.curation.model;

import com.web.curation.model.follow.Follow;

import lombok.Data;

@Data
public class FollowDTO {
	private int followno;
	public int getFollowno() {
		return followno;
	}

	public void setFollowno(int followno) {
		this.followno = followno;
	}

	public String getFollowinguser() {
		return followinguser;
	}

	public void setFollowinguser(String followinguser) {
		this.followinguser = followinguser;
	}

	public String getFolloweduser() {
		return followeduser;
	}

	public void setFolloweduser(String followeduser) {
		this.followeduser = followeduser;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	private String followinguser;
	private String followeduser;
	private boolean check;
	
	public FollowDTO(Follow follow) {
		this.followeduser = follow.getFolloweduser();
		this.followinguser = follow.getFollowinguser();
		this.followno = follow.getFollowno();
	}
}
