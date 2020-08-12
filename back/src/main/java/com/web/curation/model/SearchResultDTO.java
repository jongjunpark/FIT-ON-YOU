package com.web.curation.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import lombok.Data;

@Data
public class SearchResultDTO {
	private int articleNo;
	private String content;
	private LocalDateTime articleDate;
	private String articleUser;
	private String influeUser;
	private int favoriteCnt;
	private List<String> imgList;

	public SearchResultDTO(Board board) {
		this.articleNo = board.getArticleNo();
		this.content = board.getContent();
		this.articleDate = board.getArticleDate();
		this.articleUser = board.getArticleUser();
		this.influeUser = board.getInflueUser();
		this.favoriteCnt = board.getFavoriteCnt();
		imgList = new ArrayList<>();
	}
}
