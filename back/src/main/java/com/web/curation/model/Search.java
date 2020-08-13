package com.web.curation.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name="articletag")
@Valid
@Data
@ToString
public class Search {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int tagno;
	
	@ApiModelProperty(required = true)
	String tagname;
	
	@ApiModelProperty(required = true)
	int articleno;

	public int getTagno() {
		return tagno;
	}

	public void setTagno(int tagno) {
		this.tagno = tagno;
	}

	public String getTagname() {
		return tagname;
	}

	public void setTagname(String tagname) {
		this.tagname = tagname;
	}

	public int getArticleno() {
		return articleno;
	}

	public void setArticleno(int articleno) {
		this.articleno = articleno;
	}
	
	
}
