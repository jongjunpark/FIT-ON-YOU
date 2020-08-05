package com.web.curation.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web.curation.model.Articletag;
import com.web.curation.model.ImageStore;

public interface ArticletagDao extends JpaRepository<Articletag, String>{
	
	List<Articletag> findArticletagByArticleNoOrderByArticleNo(int articleNo);

}
