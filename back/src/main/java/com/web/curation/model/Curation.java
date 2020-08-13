package com.web.curation.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;

import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "curation")
@Valid
@Data
@ToString
public class Curation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int curationno;
	private String username;
	private String tagname;
	public int getCurationno() {
		return curationno;
	}
	public void setCurationno(int curationno) {
		this.curationno = curationno;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTagname() {
		return tagname;
	}
	public void setTagname(String tagname) {
		this.tagname = tagname;
	}
	
}
