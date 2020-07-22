// 하단 DB 설정 부분은 Sub PJT II에서 데이터베이스를 구성한 이후에 주석을 해제하여 사용.

package com.web.curation.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

@Entity
@Data
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
    private LocalDateTime create_Date;
   
    private LocalDateTime birth;
    private String gender;
    private String selfintroduce;
    
	public String getNickname() {
		return nickname;
	}

	@Override
	public String toString() {
		return "User [nickname=" + nickname + ", password=" + password + ", email=" + email + ", create_Date="
				+ create_Date + ", birth=" + birth + ", gender=" + gender + ", selfintroduce=" + selfintroduce + "]";
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

}
