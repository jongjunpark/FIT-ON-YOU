package com.web.curation.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.web.curation.model.user.User;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserDTO {
   private String nickname;
   private String password;
   private String email;
   private LocalDateTime create_Date;
   private LocalDate birth;
   private String gender;
    private String selfintroduce;

	public UserDTO(User user) {
		this.nickname = user.getNickname();
		this.password = user.getPassword();
		this.create_Date = user.getCreate_Date();
		this.email = user.getEmail();
		this.birth = user.getBirth();
		this.gender = user.getGender();
		this.selfintroduce = user.getSelfintroduce();
	}
	
	public UserDTO() {}
}
