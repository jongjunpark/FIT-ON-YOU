package com.web.curation.model.user;

import javax.validation.Valid;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Valid
@ToString
public class FindPasswordRequest {

	@ApiModelProperty(required = true)
	@NonNull
	String email;
	
	@ApiModelProperty(required = true)
	@NonNull
	String nickname;
	
}
