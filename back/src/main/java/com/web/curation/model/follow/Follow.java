package com.web.curation.model.follow;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Follow {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	private String followinguser;
	
	private String followeduser;
	
	public Follow(AddFollowRequest request) {
		this.followeduser = request.getFollowedUser();
		this.followinguser = request.getFollowingUser();
	}
	
	public Follow() {}
}
