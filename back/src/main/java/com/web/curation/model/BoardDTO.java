package com.web.curation.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Setter
@Getter
@ToString
public class BoardDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 매핑 db에서 자동증분으로 설정
	private int articleNo;
	
	public int getArticleNo() {
		return articleNo;
	}

	public void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getArticleDate() {
		return articleDate;
	}

	public void setArticleDate(LocalDateTime articleDate) {
		this.articleDate = articleDate;
	}

	public String getArticleUser() {
		return articleUser;
	}

	public void setArticleUser(String articleUser) {
		this.articleUser = articleUser;
	}

	public String getInflueUser() {
		return influeUser;
	}

	public void setInflueUser(String influeUser) {
		this.influeUser = influeUser;
	}

	public int getFavoriteCnt() {
		return favoriteCnt;
	}

	public void setFavoriteCnt(int favoriteCnt) {
		this.favoriteCnt = favoriteCnt;
	}

	private String content;
	
	@Column(insertable = false, updatable = false) 
	private LocalDateTime articleDate;
	 
	private String articleUser;
	private String influeUser;
	
	private int favoriteCnt;
	
	private int likechk;
	private int markchk;

	public int getLikechk() {
		return likechk;
	}

	public void setLikechk(int likechk) {
		this.likechk = likechk;
	}

	public int getMarkchk() {
		return markchk;
	}

	public void setMarkchk(int markchk) {
		this.markchk = markchk;
	}
	
	
}
