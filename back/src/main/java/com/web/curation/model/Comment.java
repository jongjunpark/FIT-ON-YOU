package com.web.curation.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 매핑 db에서 자동증분으로 설정
	private int commentNo;
	
	@NotNull
	private String writer;
	
	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@NotNull
	private int articleNo;
	
	private String content;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="writer", referencedColumnName="nickname",insertable=false, updatable=false)
	private User user;
}
