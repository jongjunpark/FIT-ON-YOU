package com.web.curation.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
public class Alarm {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 매핑 db에서 자동증분으로 설정
	private int alramNo;
	
	// 컬럼 어노테이션 없으면 컬럼명의 디폴트가 필드명이 됨
	private String type;	
	@NotNull
	private String recevier;
	
	
	public int getAlramNo() {
		return alramNo;
	}

	public void setAlramNo(int alramNo) {
		this.alramNo = alramNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRecevier() {
		return recevier;
	}

	public void setRecevier(String recevier) {
		this.recevier = recevier;
	}

	public String getFollower() {
		return follower;
	}

	public void setFollower(String follower) {
		this.follower = follower;
	}

	public int getArticleNo() {
		return articleNo;
	}

	public void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
	}

	public int getIsRead() {
		return isRead;
	}

	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private String follower;
	private int articleNo;
	private int isRead;  // 안읽음 0 , 읽음 1
	@Column(insertable = false, updatable = false) // 일기전용시 false
	private LocalDateTime createAt;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="follower", referencedColumnName="nickname",insertable=false, updatable=false)
	private User user;
}
