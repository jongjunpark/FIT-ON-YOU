package com.web.curation.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
public class Likes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 매핑 db에서 자동증분으로 설정
	private int likeNo;
	
	@NotNull
	private int articleNo;
	
	@NotNull
	private String nickname;
}
