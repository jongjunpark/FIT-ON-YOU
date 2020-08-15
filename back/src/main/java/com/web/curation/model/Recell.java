package com.web.curation.model;



import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
@Table(name = "recellboard")
public class Recell {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int recellNo;
	
	@NotNull
	private String recellUser;
	@NotNull
	private String recellPrice;
	private String recellImage;
	private String recellContent;
	@Column(insertable = false, updatable = false) // 일기전용시 false
	private Date recellDate;
	private String recellSize;
	public int getRecellNo() {
		return recellNo;
	}
	public void setRecellNo(int recellNo) {
		this.recellNo = recellNo;
	}
	public String getRecellUser() {
		return recellUser;
	}
	public void setRecellUser(String recellUser) {
		this.recellUser = recellUser;
	}
	public String getRecellPrice() {
		return recellPrice;
	}
	public void setRecellPrice(String recellPrice) {
		this.recellPrice = recellPrice;
	}
	public String getRecellImage() {
		return recellImage;
	}
	public void setRecellImage(String recellImage) {
		this.recellImage = recellImage;
	}
	public String getRecellContent() {
		return recellContent;
	}
	public void setRecellContent(String recellContent) {
		this.recellContent = recellContent;
	}
	public Date getRecellDate() {
		return recellDate;
	}
	public void setRecellDate(Date recellDate) {
		this.recellDate = recellDate;
	}
	public String getRecellSize() {
		return recellSize;
	}
	public void setRecellSize(String recellSize) {
		this.recellSize = recellSize;
	}
	
	
	}
