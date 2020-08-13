package com.web.curation.model;

import java.util.List;

import lombok.Data;

@Data
public class ResponseData {

	private Board articles;
	private List<ImageStore> imgs;
	private List<Articletag> tags;
	private String profile;
	
	
	public Board getArticles() {
		return articles;
	}
	public void setArticles(Board articles) {
		this.articles = articles;
	}
	public List<ImageStore> getImgs() {
		return imgs;
	}
	public void setImgs(List<ImageStore> imgs) {
		this.imgs = imgs;
	}
	public List<Articletag> getTags() {
		return tags;
	}
	public void setTags(List<Articletag> tags) {
		this.tags = tags;
	}
	
}
