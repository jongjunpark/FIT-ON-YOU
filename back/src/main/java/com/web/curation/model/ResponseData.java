package com.web.curation.model;

import java.util.List;

import lombok.Data;

@Data
public class ResponseData {

	private Board articles;
	private List<ImageStore> imgs;
	private List<Articletag> tags;
}
