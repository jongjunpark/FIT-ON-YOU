package com.web.curation.model;

import java.time.LocalDateTime;

import com.web.curation.model.user.User;

import lombok.Data;

@Data
public class UserDTO {
	private String nickname;
	private String password;
	private String email;
	private LocalDateTime create_Date;
	private LocalDateTime birth;
	private String gender;
    private String selfintroduce;
	
	public String getSelfintroduce() {
		return selfintroduce;
	}

	public void setSelfintroduce(String selfintroduce) {
		this.selfintroduce = selfintroduce;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public UserDTO(User user) {
		this.nickname = user.getNickname();
		this.password = user.getPassword();
		this.create_Date = user.getCreate_Date();
		this.email = user.getEmail();
		this.birth = user.getBirth();
	}
	
	public UserDTO() {}
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getCreate_Date() {
		return create_Date;
	}

	public void setCreate_Date(LocalDateTime create_Date) {
		this.create_Date = create_Date;
	}

	public LocalDateTime getBirth() {
		return birth;
	}

	public void setBirth(LocalDateTime birth) {
		this.birth = birth;
	}

}

