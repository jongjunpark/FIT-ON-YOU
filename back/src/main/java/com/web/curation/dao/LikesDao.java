package com.web.curation.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.web.curation.model.Likes;

public interface LikesDao extends JpaRepository<Likes, String>{
	
	@Modifying
	@Transactional
	long deleteByArticleNoAndNickname(int articleNo, String nickname);

	long countByArticleNoAndNickname(int articleNo, String nickname);
	
}
