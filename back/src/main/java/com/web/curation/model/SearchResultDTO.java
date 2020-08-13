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
	private String userImg;
	

	public String getUserImg() {
		return userImg;
	}

	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}

	public SearchResultDTO(Board board) {
		this.articleNo = board.getArticleNo();
		this.content = board.getContent();
		this.articleDate = board.getArticleDate();
		this.articleUser = board.getArticleUser();
		this.influeUser = board.getInflueUser();
		this.favoriteCnt = board.getFavoriteCnt();
		imgList = new ArrayList<>();
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

	public List<String> getImgList() {
		return imgList;
	}

	public void setImgList(List<String> imgList) {
		this.imgList = imgList;
	}
	
}
