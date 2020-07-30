package com.web.curation.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
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
public class ImageStore {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int imageNo;
	
	@NotNull
	private int articleNo;
	
	@NotNull
	@Column(name="imageUrl")
	private String imageUrl;
}
