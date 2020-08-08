package com.web.curation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    @Id//pk를 지정
//    @GeneratedValue(strategy = GenerationType.IDENTITY)//자동 증분 칼럼
    private String nickname;

    private String password;
    //어노테이션이 없더라도 테이블 칼럼명과 자동 매핑
    private String email;

    @Column(insertable = false, updatable = false)
    private LocalDateTime create_date;
   
    private LocalDate birth;
    private String gender;
    private String selfintroduce;
    private String profile_img;
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
		return create_date;
	}
	public void setCreate_Date(LocalDateTime create_Date) {
		this.create_date = create_Date;
	}
	public LocalDate getBirth() {
		return birth;
	}
	public void setBirth(LocalDate birth) {
		this.birth = birth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getSelfintroduce() {
		return selfintroduce;
	}
	public void setSelfintroduce(String selfintroduce) {
		this.selfintroduce = selfintroduce;
	}
	public String getProfile_img() {
		return profile_img;
	}
	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
	}
    


}
