package com.web.curation.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.web.curation.model.user.User;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UserDTO {
	private String nickname;
	private String password;
	private String email;
	private LocalDateTime create_Date;
	private LocalDate birth;
	private String gender;
	private String selfintroduce;

	public UserDTO(User user) {
		this.birth = user.getBirth();
		this.create_Date = user.getCreate_Date();
		this.email = user.getEmail();
		this.gender = user.getGender();
		this.nickname = user.getNickname();
		this.password = user.getPassword();
		this.selfintroduce = user.getSelfintroduce();
	}
	
	public UserDTO() {}

}
