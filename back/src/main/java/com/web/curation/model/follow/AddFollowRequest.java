package com.web.curation.model.follow;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Valid
@ToString
@Data
public class AddFollowRequest {

	@ApiModelProperty(required = true)
	@NotNull
	String followedUser;
	
	public String getFollowedUser() {
		return followedUser;
	}

	public void setFollowedUser(String followedUser) {
		this.followedUser = followedUser;
	}

	public String getFollowingUser() {
		return followingUser;
	}

	public void setFollowingUser(String followingUser) {
		this.followingUser = followingUser;
	}

	@ApiModelProperty(required = true)
	@NotNull
	String followingUser;
}
