package com.web.curation.model;

import com.web.curation.model.follow.Follow;

import lombok.Data;

@Data
public class FollowDTO {
	private int followno;
	private String followinguser;
	private String followeduser;
	private boolean check;
	
	public FollowDTO(Follow follow) {
		this.followeduser = follow.getFolloweduser();
		this.followinguser = follow.getFollowinguser();
		this.followno = follow.getFollowno();
	}
}
