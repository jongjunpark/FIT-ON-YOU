package com.web.curation.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Bookmark {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 매핑 db에서 자동증분으로 설정
	private int bookmarkNo;
	
	@NotNull
	private int bookedArticle;
	
	@NotNull
	private String bookUser;
	
	
}
