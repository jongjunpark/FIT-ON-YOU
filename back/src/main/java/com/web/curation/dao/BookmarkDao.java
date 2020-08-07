package com.web.curation.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.web.curation.model.Bookmark;

public interface BookmarkDao extends JpaRepository<Bookmark, String>{
	@Modifying
	@Transactional
	void deleteByBookedArticleAndBookUser(int bookedArticle,String bookUser);
}
