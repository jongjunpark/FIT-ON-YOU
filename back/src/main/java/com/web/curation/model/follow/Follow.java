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
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Follow {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int followno;
	
	private String followinguser;
	
	private String followeduser;
	
	public Follow(AddFollowRequest request) {
		this.followeduser = request.getFollowedUser();
		this.followinguser = request.getFollowingUser();
	}
}
